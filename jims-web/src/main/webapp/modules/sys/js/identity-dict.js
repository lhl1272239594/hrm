/**
 * Created by fyg on 2016/6/21.
 */
$(function () {
    var orgId = config.org_Id;
    var editIndex;
    var stopEdit = function () {
        if (editIndex || editIndex == 0) {
            $("#dg").datagrid('endEdit', editIndex);
            editIndex = undefined;
        }
    }

    $("#dg").datagrid({
        fit: true,
        singleSelect: true,
        rownumbers: true,
        toolbar: '#tb',
        method: 'get',
        url: basePath + '/identity-dict/list?orgId=' + orgId,
        loadMsg: '数据正在加载中，请稍后.....',
        columns: [[{
            title: "id",
            field: "id",
            hidden: true
        }, {
            title: "orgId",
            field: "orgId",
            align: "center",
            hidden: true
        }, {
            title: "身份代码",
            field: "identityCode",
            align: 'center',
            width: "17%",
            editor: {
                type: 'textbox',
                options: {
                    required: true,
                    validType:['length[0,1]']
                }
            }
        }, {
            title: "身份名称",
            field: "identityName",
            align: 'center',
            width: '17%',
            editor: {
                type: 'textbox',
                options: {
                    required: true
                }
            }
        }, {
            title: "输入码",
            field: "inputCode",
            align: 'center',
            width: '17%'
        }, {
            title: "优先标志",
            field: "priorityIndicator",             //1,优先;0,不优先
            align: 'center',
            width: '15%',
            editor: {
                type: 'combobox', options: {
                    editable: false,
                    align: 'center',
                    valueField: 'value',
                    textField: 'text',
                    data: [{
                        value: '1',
                        text: '优先',
                        selected: true
                    }, {
                        value: '0',
                        text: '不优先'
                    }]
                }
            },
            formatter: function (value, row, index) {
                if (value == "1") {
                    return "优先";
                }
                if (value == "0") {
                    return "不优先";
                }
            }
        }, {
            title: "军人标志",
            field: "militaryIndicator",
            align: 'center',
            width: '15%',
            editor: {
                type: 'combobox', options: {
                    editable: false,
                    align: 'center',
                    valueField: 'value',
                    textField: 'text',
                    data: [{
                        value: '1',
                        text: '是',
                        selected: true
                    }, {
                        value: '0',
                        text: '否'
                    }]
                }
            },
            formatter: function (value, row, index) {
                if (value == "1") {
                    return "是";
                }
                if (value == "0") {
                    return "否";
                }
            }
        }]],
        onClickRow: function (index, row) {
            var beforeIndex = $(this).datagrid('getRowIndex',$(this).datagrid('getSelected'))
            if(!$(this).datagrid('validateRow',beforeIndex)) {
                return false
            }
            stopEdit();
            $(this).datagrid('beginEdit', index);
            editIndex = index;
        },
        onBeforeSelect: function(index){
            var beforeIndex = $(this).datagrid('getRowIndex',$(this).datagrid('getSelected'))
            if(beforeIndex >= 0) {
                return $(this).datagrid('validateRow',beforeIndex)
            }
            return true
        }
    });

    $("#addBtn").on("click", function () {
        var beforeIndex = $("#dg").datagrid('getRowIndex',$("#dg").datagrid('getSelected'))
        if(!$("#dg").datagrid('validateRow',beforeIndex)) {
            return false
        }
        stopEdit();
        $("#dg").datagrid('appendRow', {priorityIndicator:'0',militaryIndicator:'0'});
        var rows = $("#dg").datagrid('getRows');
        var addRowIndex = $("#dg").datagrid('getRowIndex', rows[rows.length - 1]);
        editIndex = addRowIndex;
        $("#dg").datagrid('selectRow', editIndex);
        $("#dg").datagrid('beginEdit', editIndex);
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
        var beforeIndex = $("#dg").datagrid('getRowIndex',$("#dg").datagrid('getSelected'))
        if(!$("#dg").datagrid('validateRow',beforeIndex)) {
            return false
        }
        if (editIndex || editIndex == 0) {
            $("#dg").datagrid("endEdit", editIndex);
        }

        var insertData = $("#dg").datagrid("getChanges", "inserted");
        var updateDate = $("#dg").datagrid("getChanges", "updated");
        var deleteDate = $("#dg").datagrid("getChanges", "deleted");

        var beanChangeVo = {};
        beanChangeVo.inserted = insertData;
        beanChangeVo.deleted = deleteDate;
        beanChangeVo.updated = updateDate;

        if (beanChangeVo.inserted.length > 0) {
            for (var i = 0; i < beanChangeVo.inserted.length; i++) {
                beanChangeVo.inserted[i].orgId = orgId;
                var identityName = beanChangeVo.inserted[i].identityName;
                if (typeof (identityName) == 'undefined' || identityName.length == 0) {
                    $.messager.alert('提示', '身份名称不能为空!!', 'error');
                    return;
                }
                var identityCode = beanChangeVo.inserted[i].identityCode;
                var reg = /[\u4e00-\u9fa5]/g;
                if (reg.test(identityCode)) {
                    $.messager.alert("系统提示", "身份代码只能是一个字母或数字，不能是中文!", "info");
                    return;
                }
            }
        }
        if (beanChangeVo.updated.length > 0) {
            for (var i = 0; i < beanChangeVo.updated.length; i++) {
                beanChangeVo.updated[i].orgId = orgId;
                var roleName = beanChangeVo.updated[i].identityName;
                if (roleName.length == 0) {
                    $.messager.alert('提示', '身份名称不能改为空!!', 'error');
                    return;
                }
                var identityCode = beanChangeVo.updated[i].identityCode;
                var reg = /[\u4e00-\u9fa5]/g;
                if (reg.test(identityCode)) {
                    $.messager.alert("系统提示", "身份代码只能是一个字母或数字，不能是中文!", "info");
                    return;
                }
            }
        }
        if (beanChangeVo) {
            $.postJSON(basePath + '/identity-dict/merge', JSON.stringify(beanChangeVo), function (data) {
                $.messager.alert("系统提示", "保存成功", "info");
                loadDict();
            }, function (data) {
                $.messager.alert('提示', '保存失败', "error");
            })
        }
    });

    $("#searchBtn").on("click", function () {
        var name = $("#name").textbox("getValue");
        $.get(basePath + '/identity-dict/search?identityName=' + name + '&orgId=' + orgId, function (data) {
            $("#dg").datagrid('loadData', data);
        });
    });

    var loadDict = function () {
        $.get(basePath + '/identity-dict/list?orgId=' + orgId, function (data) {
            $("#dg").datagrid('loadData', data);
        });
    }
    loadDict();
});