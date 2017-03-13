/**
 * 科室分组字典维护
 * @author yangruidong
 * @version 2016-05-18
 */
$("<link>").attr({rel: "stylesheet", type: "text/css", href: "/static/easyui/css/icon.css"}).appendTo("head");
$("<script>").attr({type: "application/javascript", src: "/static/easyui/js/jquery.easyui.min.js"}).appendTo("head");
$("<script>").attr({
    type: "application/javascript",
    src: "/static/easyui/locale/easyui-lang-zh_CN.js"
}).appendTo("head");
$("<script>").attr({type: "application/javascript", src: "/static/js/tool.js"}).appendTo("head");
$("<script>").attr({type: "application/javascript", src: "/static/js/formSubmit.js"}).appendTo("head");
var basePath = "/service";
$(function () {

    //校验数据是否合法
    $.extend($.fn.validatebox.defaults.rules, {
        IsExisted: {
            validator: function (value, param) {
                var rows = $('#groupClass').datagrid('getRows')
                var select_index = $('#groupClass').datagrid('getRowIndex', $('#groupClass').datagrid('getSelected'))
                for (var index = rows.length - 1; index > -1; index--) {
                    if (index != select_index && rows[index][param[0]] == value ) {
                        return false
                    }
                }
                return true
            },
            message: '分组名称已经存在'
        }
    });



    var staffFrom = [];
    var orgId = parent.config.org_Id;
    var group_class_id;
    var editIndex1 = undefined;
    //用户分组类
    $("#groupClass").datagrid({
        fit: true,
        fitColumns: true,
        striped: true,
        singleSelect: true,
        idField: "id",
        toolbar: '#classft',
        url: basePath + '/staff-group/findAllListByOrgId?orgId=' + orgId,
        method: 'get',
        loadMsg: '数据正在加载中，请稍后.....',
        columns: [[{
            title: 'id',
            field: "id",
            hidden: true
        }, {
            title: '分组名称',
            field: 'groupClass',
            width: '50%',
            align: 'center',
            editor: {
                type: 'textbox',
                options: {
                    required: true,
                    validType: 'IsExisted["groupClass"]',
                    missingMessage: '分组名称不能为空'
                }
            }
        }]],
        onClickRow: function (index, row) {
            staffFrom = [];
            var node = $("#groupClass").datagrid("getSelected");

            group_class_id = node.id;

          /*  //加载字段名称
            var chargeType = [{colName: 'org_id', colValue: orgId, operateMethod: "="}]
            var param = {dictType: 'v_staff_group_dict', orgId: orgId, inputParamVos: chargeType}


            $.postJSON('/service/input-setting/listParam',
                JSON.stringify(param), function (data) {
                    for (var i = 0; i < data.length; i++) {
                        staffFrom.push({
                            value: data[i].dept_code,
                            text: data[i].dept_name,
                            inputcode: data[i].input_code ,
                            id:data[i].id
                        });
                    }
                })*/

            if (!endEditing2()) {
                return
            }
            var insertData = $("#groupDict").datagrid("getChanges", "inserted");
            var updateData = $("#groupDict").datagrid("getChanges", "updated");
            var deleteData = $("#groupDict").datagrid("getChanges", "deleted");

            if (insertData != "" || updateData != "" || deleteData != "") {
                $.messager.confirm("系统提示", "确定要保存吗？", function (r) {
                    if (r) {
                        var staffGroupVo = {};
                        staffGroupVo.inserted = insertData;
                        staffGroupVo.deleted = deleteData;
                        staffGroupVo.updated = updateData;

                        if (staffGroupVo) {
                            $.postJSON(basePath + "/staff-group/saveGroup", JSON.stringify(staffGroupVo), function (data) {
                                if (data.data == "success") {
                                    $.messager.alert("系统提示", "保存成功", "info");
                                    $("#groupDict").datagrid('reload');
                                }
                            }, function (data) {
                                $.messager.alert('提示', "保存失败", "error");
                            })
                        }
                    }
                });
            }
            var url = basePath + "/staff-group/findListGroupDict?id=" + node.id;
            $("#groupDict").datagrid("reload", url);

        },
        onClickCell: onClickCell
    });


    //datagrid的单元格编辑
    $.extend($.fn.datagrid.methods, {
        editCell: function (jq, param) {
            return jq.each(function () {
                var opts = $(this).datagrid('options');
                var fields = $(this).datagrid('getColumnFields', true).concat($(this).datagrid('getColumnFields'));
                for (var i = 0; i < fields.length; i++) {
                    var col = $(this).datagrid('getColumnOption', fields[i]);
                    col.editor1 = col.editor;
                    if (fields[i] != param.field) {
                        col.editor = null;
                    }
                }
                $(this).datagrid('beginEdit', param.index);
                for (var i = 0; i < fields.length; i++) {
                    var col = $(this).datagrid('getColumnOption', fields[i]);
                    col.editor = col.editor1;
                }
            });
        }
    });

    var editIndex = undefined;

    function endEditing1() {
        if (editIndex == undefined) {
            return true
        }
        if ($('#groupClass').datagrid('validateRow', editIndex)) {
            $('#groupClass').datagrid('endEdit', editIndex);
            editIndex = undefined;
            return true;
        } else {
            return false;
        }
    }

    function onClickCell(index, field) {
        if (endEditing1()) {
            $('#groupClass').datagrid('selectRow', index)
                .datagrid('editCell', {index: index, field: field});
            editIndex = index;
        }
    }

    //开始编辑行
    $("#addClassBtn").on('click', function () {
        $("#groupClass").datagrid('appendRow', {});
        var rows = $("#groupClass").datagrid('getRows');
        onClickCell(rows.length - 1, 'groupClass');
    });


    //用户工作组类保存
    $("#saveClassBtn").on("click", function () {
        if (!endEditing1()) {
            return
        }


        var insertData = $("#groupClass").datagrid("getChanges", "inserted");
        var updateData = $("#groupClass").datagrid("getChanges", "updated");
        var deleteData = $("#groupClass").datagrid("getChanges", "deleted");


        var staffGroupVo = {};
        staffGroupVo.inserted = insertData;
        staffGroupVo.deleted = deleteData;
        staffGroupVo.updated = updateData;
        staffGroupVo.orgId = orgId;


        if (staffGroupVo) {
            $.postJSON(basePath + "/staff-group/saveGroupClass", JSON.stringify(staffGroupVo), function (data) {
                if (data.data == "success") {
                    $.messager.alert("系统提示", "保存成功", "info");
                    $("#groupClass").datagrid('reload');
                }
            }, function (data) {
                $.messager.alert('提示', "保存失败", "error");
            })
        }
    });

    //删除
    $("#removeClassBtn").on("click", function () {
        var row = $("#groupClass").datagrid('getSelected');

        $.get("/service/staff-group/findListGroupDict?id=" + row.id, function (data) {
            if (data != "") {
                $.messager.alert('系统提示', "请先删除子表数据", 'info');
                return;
            }
            else {
                if (row) {
                    var rowIndex = $("#groupClass").datagrid('getRowIndex', row);
                    $("#groupClass").datagrid('deleteRow', rowIndex);
                    if (editIndex == rowIndex) {
                        editIndex = undefined;
                    }
                } else {
                    $.messager.alert('系统提示', "请选择要删除的行", 'info');
                }
            }

        });
    });


    //用户工作组
    $("#groupDict").datagrid({
        fit: true,
        fitColumns: true,
        striped: true,
        singleSelect: true,
        toolbar: '#ft',
        method: 'GET',
        rownumbers: true,

        loadMsg: '数据正在加载中，请稍后.....',
        columns: [[{
            title: "id",
            field: "id",
            hidden: true
        }, {
            title: "分组代码",
            field: 'groupCode',
            width: '10%',
            align: 'center',
            editor: {

                type: 'combogrid',
                options: {
                    panelWidth: 320,
                    idField: 'dept_code',
                    textField: 'dept_code',
                    mode: 'remote',
                    method: 'GET',
                    url: basePath + '/input-setting/listParamByGET?orgId=' + orgId + '&dictType=v_staff_group_dict',
                    columns: [[
                        {field: 'dept_code', title: '科室代码', width: 100},
                        {field: 'dept_name', title: '科室名称', width: 100},
                        {field: 'input_code', title: '拼音码', width: 100}  ,
                        {field: 'id', title: '科室ID', width: 100,hidden:true}
                    ]],
                    onSelect: function (index, data) {
                        var row = $('#groupDict').datagrid('getSelected');
                        row.groupName = data.dept_name;
                        row.deptId= data.id;
                        row.inputCode=data.input_code;
                        $('#groupDict').datagrid('endEdit', editIndex1);
                    },
                    filter: function (field, row) {
                        if (field && (row['value'] && row['value'].toUpperCase().indexOf(field.toUpperCase()) == 0 )
                            || (row['text'] && row['text'].toUpperCase().indexOf(field.toUpperCase()) == 0)
                            || (row['inputcode'] && row['inputcode'].toUpperCase().indexOf(field.toUpperCase()) == 0)) {
                            return true
                        }
                    }
                }
            }


        }, {
            title: "分组名称",
            field: "groupName",
            width: '10%',
            align: 'center'
        }

        ]],
        onClickCell: onClickCell1
    });


    //datagrid的单元格编辑

    function endEditing2() {
        if (editIndex1 == undefined) {
            return true
        }
        if ($('#groupDict').datagrid('validateRow', editIndex1)) {
            $('#groupDict').datagrid('endEdit', editIndex1);
            editIndex1 = undefined;
            return true;
        } else {
            return false;
        }
    }

    function onClickCell1(index, field) {
        if (endEditing2()) {
            $('#groupDict').datagrid('selectRow', index)
                .datagrid('editCell', {index: index, field: field});
            editIndex1 = index;
            if (field == 'groupCode') {
                var editor = $("#groupDict").datagrid('getEditor', {index: index, field: 'groupCode'});
                $(editor.target).combogrid('grid').datagrid('loadData', staffFrom);
            }
        }
    }

    //开始编辑行
    $("#addDictBtn").on('click', function () {

        if (!$("#groupClass").datagrid('getSelected')) {
            $.messager.alert("系统提示", "请先选择用户分组类", "info");
            return false;
        }
        $("#groupDict").datagrid('appendRow', {groupClassId: $("#groupClass").datagrid('getSelected').id});
        var rows = $("#groupDict").datagrid('getRows');
        onClickCell1(rows.length - 1, 'groupCode');
    });


    //用户分组保存
    $("#saveDictBtn").on("click", function () {
        if (editIndex1 || editIndex1 == 0) {
            $("#groupDict").datagrid("endEdit", editIndex1);
        }

        //不能添加重复的项目
        var data = $("#groupDict").datagrid('getData');
        for (var i = 0; i < data.total; i++) {
            for (var j = i + 1; j < data.total; j++) {
                if (data.rows[i].groupCode == data.rows[j].groupCode) {
                    $.messager.alert("系统提示", "不能添加重复的项目,请修改后再保存", "info");
                    return;
                }
            }
        }

        var insertData = $("#groupDict").datagrid("getChanges", "inserted");
        var updateData = $("#groupDict").datagrid("getChanges", "updated");
        var deleteData = $("#groupDict").datagrid("getChanges", "deleted");

        var staffGroupVo = {};
        staffGroupVo.inserted = insertData;
        staffGroupVo.deleted = deleteData;
        staffGroupVo.updated = updateData;





        staffGroupVo.staff_group_class__id = group_class_id;

        if (staffGroupVo) {
            $.postJSON(basePath + "/staff-group/saveGroup", JSON.stringify(staffGroupVo), function (data) {
                if (data.data == "success") {
                    $.messager.alert("系统提示", "保存成功", "info");
                    $("#groupDict").datagrid('reload');
                }
            }, function (data) {
                $.messager.alert('提示', "保存失败", "error");
            })
        }
    });
    //删除
    $("#removeDictBtn").on("click", function () {
        var row = $("#groupDict").datagrid('getSelected');
        if (row) {
            var rowIndex = $("#groupDict").datagrid('getRowIndex', row);
            $("#groupDict").datagrid('deleteRow', rowIndex);
            if (editIndex == rowIndex) {
                editIndex = undefined;
            }
        } else {
            $.messager.alert('系统提示', "请选择要删除的行", 'info');
        }
    });

});




