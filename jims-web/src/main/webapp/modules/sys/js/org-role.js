/**
 * Created by fyg on 2016/6/2.
 */
$(function () {

    $("#win").window('close')
    var orgId = parent.config.org_Id;
    var editIndex;
    var stopEdit = function () {
        if (editIndex || editIndex == 0) {
            $("#dg").datagrid('endEdit', editIndex);
            editIndex = undefined;
        }
    }
    $("#dg").datagrid({
        fit: true,
        toolbar: '#tb',
        singleSelect: true,
        rownumbers: true,
        method: 'get',
        url: basePath + '/org-role/findAllListByOrgId?orgId=' + orgId,
        columns: [[{
            title: "id",
            field: "id",
            hidden: true
        }, {
            title: "组织ID",
            field: "orgId",
            align: 'center',
            width: "20%",
            hidden: true
        }, {
            title: "职务名称",
            field: "roleName",
            align: 'center',
            width: '20%',
            editor: {
                type: 'textbox'
                //options: {
                //    required: true
                //}
            }
        }]],
        onClickRow: function (index, row) {
            stopEdit();
            $(this).datagrid('beginEdit', index);
            editIndex = index;
            var editor = $('#dg').datagrid('getEditor', {index: editIndex, field: 'roleName'});
            editor.target.focus();
        }
    });

    $("#addBtn").on("click", function () {
        stopEdit();
        $("#dg").datagrid('appendRow', {});
        var rows = $("#dg").datagrid('getRows');
        var addRowIndex = $("#dg").datagrid('getRowIndex', rows[rows.length - 1]);
        editIndex = addRowIndex;
        $("#dg").datagrid('selectRow', editIndex);
        $("#dg").datagrid('beginEdit', editIndex);
        var editor = $('#dg').datagrid('getEditor', {index: editIndex, field: 'roleName'});
        editor.target.focus();


        document.onkeydown = function (event) {
            var e = event || window.event || arguments.callee.caller.arguments[0];
            if (e && e.keyCode == 13) {
                stopEdit();
                $("#dg").datagrid('appendRow', {});
                var rows = $("#dg").datagrid('getRows');
                var addRowIndex = $("#dg").datagrid('getRowIndex', rows[rows.length - 1]);
                editIndex = addRowIndex;
                $("#dg").datagrid('selectRow', editIndex);
                $("#dg").datagrid('beginEdit', editIndex);
                var editor = $('#dg').datagrid('getEditor', {index: editIndex, field: 'roleName'});
                editor.target.focus();
            }
        }
    });

    $("#delBtn").on("click", function () {
        var row = $("#dg").datagrid('getSelected');
        if (row) {
            var rowIndex = $("#dg").datagrid('getRowIndex', row);
            $("#dg").datagrid('deleteRow', rowIndex);
            if (editIndex == rowIndex) {
                editIndex = undefined;
            }
        } else {
            $.messager.alert('系统提示', "请选择要删除的行", 'info');
        }
    });

    $("#saveBtn").on("click", function () {
        if (editIndex || editIndex == 0) {
            $("#dg").datagrid("endEdit", editIndex);
        }
        var rows = $("#dg").datagrid("getRows");
        for (var i=0;i<rows.length;i++){
            if(trim(rows[i].roleName)=='' ||rows[i].roleName==null){
                $.messager.alert('提示', '职务名称不能为空!!', 'error');
                $("#dg").datagrid("selectRow", i);
                return;
            }
        }

        var insertData = $("#dg").datagrid("getChanges", "inserted");
        var updateData = $("#dg").datagrid("getChanges", "updated");
        var deleteData = $("#dg").datagrid("getChanges", "deleted");

        var beanChangeVo = {};
        beanChangeVo.inserted = insertData;
        beanChangeVo.deleted = deleteData;
        beanChangeVo.updated = updateData;

        if (beanChangeVo.inserted.length > 0) {
            for (var i = 0; i < beanChangeVo.inserted.length; i++) {
                beanChangeVo.inserted[i].orgId = orgId;     //设置组织ID
                //var roleName = beanChangeVo.inserted[i].roleName;
                //if (typeof(roleName) == "undefined" || trim(roleName) == "") {
                //    $.messager.alert('提示', '角色名称不能为空!!', 'error');
                //    return;
                //}2
            }
        }
        if (beanChangeVo) {
            $.postJSON(basePath + '/org-role/merge', JSON.stringify(beanChangeVo), function (data) {
                console.log(data);
                $.messager.alert("系统提示", "保存成功", "info");
                loadDict();
            }, function (data) {
                $.messager.alert('提示', '保存失败', "error");
            })
        }
    });
    $("#searchBtn").on("click", function () {
        var name = $("#name").textbox("getValue");
        $.get(basePath + '/org-role/find-by-name?roleName=' + name + '&orgId=' + orgId, function (data) {
            $("#dg").datagrid('loadData', data);
        });
    });

    var loadDict = function () {
        $.get(basePath + '/org-role/findAllListByOrgId?orgId=' + orgId, function (data) {
            $("#dg").datagrid('loadData', data);
        });
    }
    loadDict();

    //去除字符串两边空格
    function trim(value) {
        if (typeof(value) != "undefined") {
            value = value.trim();
            return value;
        }
    }


    //使用模板数据
    $("#templateBtn").on('click', function () {
        $("#area").combotree('setValue', '');
        var node = $("#dg").datagrid('getRows')
        if (node.length > 0) {
            $.messager.alert("系统提示", "已经有角色,不允许再使用模板数据！", "info")
            return;
        } else {
            $('#win').dialog('open');
        }
    })

    $("#seaBtn").on('click', function () {
        var area = $("#area").combotree('getValue');
        $.get(basePath + '/templateMaster/findListByArea?area=' + area, function (data) {
            $("#orgRoleTemplate").datagrid('loadData', data);
        });
    })

    $('#area').combotree({
        method: 'get',
        animate: true,
        data: getAreaTree(),
        onSelect: function (row) {
            var area = row.value;
            $.get(basePath + '/templateMaster/findListByArea?area=' + area, function (data) {
                $("#orgRoleTemplate").datagrid('loadData', data);
            });
        }
    })


    /*$('#orgRoleTemplate').datagrid({
        width: 440,
        height: 300,
        //fitColumns: true,
        singleSelect: true,
        method: 'get',
        url: basePath + "/templateMaster/findList?tableName=ORG_ROLE_TEMPLATE",
        idField: 'id',
        singleSelect: true,//是否单选
        rownumbers: true,//行号
        columns: [[{
            title: "id",
            field: "id",
            hidden: true
        }, {
            title: "区域",
            field: 'area',
            width: '0',
            align: 'center',
            hidden: 'true'
        }
            , {
                title: "区域名称",
                field: 'areaName',
                width: '130',
                align: 'center'
            },
            {
                title: "模板名称",
                field: 'templateName',
                width: '130',
                align: 'center'
            },
            {
                title: "模板描述",
                field: 'details',
                width: '140',
                align: 'center'
            }
        ]],
        onDblClickRow: function (index, row) {
            $.get(basePath + '/orgRoleTemplate/findListByMasterId', {masterId: row.id}, function (res) {
                if (res=="") {
                    $.messager.alert('提示', '该模板中没有可导入的数据,请维护数据后再导入！', 'error')
                    return
                } else {
                    $.messager.confirm("系统提示", "确定要导入模板数据吗？", function (r) {
                        if (r) {
                            $.messager.progress({
                                text: '',
                                msg: '导入数据中......',
                                interval: 300
                            });
                            $.get("/service/org-role/insertTemplate?orgId=" + orgId + '&masterId=' + row.id, function (data) {
                                $.messager.progress('close');
                                if (data.data == "success") {
                                    $("#win").dialog('close');
                                    $.messager.alert("系统提示", "保存成功", "info");
                                }
                            }, function (data) {
                                $.messager.alert('提示', "保存失败", "error");
                            })
                        }
                    });
                }})
        }
    });*/


});