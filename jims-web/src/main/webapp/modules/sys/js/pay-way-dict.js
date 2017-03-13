/**
 * Created by fyg on 2016/6/23.
 */
$(function () {
    var orgId = config.org_Id;
    var chargeTypeDict;
    var editIndex;
    var stopEdit = function () {
        if (editIndex || editIndex == 0) {
            $("#dg").datagrid('endEdit', editIndex);
            editIndex = undefined;
        }
    }

    function listAll() {
        $.get(basePath + '/ChargeTypeDict/list-all?orgId=' + orgId, function (data) {
            chargeTypeDict = data;
        });
    }

    listAll();

    $("#dg").datagrid({
        fit: true,
        toolbar: '#tb',
        singleSelect: true,
        rownumbers: true,
        method: 'get',
        url: basePath + '/pay-way-dict/list?orgId=' + orgId,
        loadMsg: '数据正在加载中，请稍后.....',
        columns: [[{
            title: "id",
            field: "id",
            hidden: true
        }, {
            title: "所属组织机构",
            field: "orgId",
            align: "center",
            hidden: true
        }, {
            title: "支付方式代码",
            field: "payWayCode",
            align: 'center',
            width: "12%",
            editor: {
                type: 'textbox',
                options: {
                    required: true
                }
            }
        }, {
            title: "支付方式名称",
            field: "payWayName",
            align: 'center',
            width: '12%',
            editor: {
                type: 'textbox',
                options: {
                    required: true
                }
            }
        }, {
            title: "记账标志",                  //0，不记账，1记账
            field: "acctingIndicator",
            align: 'center',
            width: '11%',
            editor: {
                type: 'combobox', options: {
                    editable: false,
                    align: 'center',
                    valueField: 'value',
                    textField: 'text',
                    data: [{
                        value: '1',
                        text: '记账',
                        selected: true
                    }, {
                        value: '0',
                        text: '不记账'
                    }]
                }
            },
            formatter: function (value, row, index) {
                if (value == "1") {
                    return "记账";
                }
                if (value == "0") {
                    return "不记账";
                }
            }
        }, {
            title: "门诊病人适用",          //0，否，1是
            field: "outpIndicator",
            align: 'center',
            width: '12%',
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
        }, {
            title: "住院病人适用",              //0，否，1是
            field: "inpIndicator",
            align: 'center',
            width: '12%',
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
        }, {
            title: "输入码",
            field: "inputCode",
            align: 'center',
            width: '12%'
        },{
            title: "已注册",                  //0，否，1是
            field: "registIndicator",
            align: 'center',
            width: "12%",
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
        },{
            title: "医保类别",
            field: "chargeType",
            align: 'center',
            width: "13%",
            editor: {
                type: 'combogrid',
                options: {
                    panelWidth: 300,
                    idField: 'chargeTypeCode',
                    textField: 'chargeTypeName',
                    url: basePath + '/ChargeTypeDict/list-by-orgId?orgId=' + orgId,
                    mode: 'remote',
                    method: 'get',
                    remoteSort: false,
                    columns: [[
                        {field: 'id', title: 'id', hidden: true},
                        {field: 'chargeTypeCode', title: '类别代码', width: 70  },
                        {field: 'chargeTypeName', title: '类别名称', width: 150},
                        {field: 'inputCodeWb', title: '拼音码', width: 70}
                    ]]
                }
            }
            ,
            formatter: function (value, row, index) {
                var name=value;
                for(var i=0; i< chargeTypeDict.length; i++){
                    if(value == chargeTypeDict[i].chargeTypeCode){
                        name= chargeTypeDict[i].chargeTypeName;
                    }
                }
                return name;
            }
        }]],
        onClickRow: function (index, row) {
            stopEdit();
            $(this).datagrid('beginEdit', index);
            editIndex = index;
        }
    });

    //设置分页控件
    var p = $('#dg').datagrid('getPager');

    $("#addBtn").on("click", function () {
        stopEdit();
        $("#dg").datagrid('appendRow', {});
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
        if (editIndex || editIndex == 0) {
            $("#dg").datagrid("endEdit", editIndex);
        }

        var rows =$('#dg').datagrid('getRows');
        console.log(rows);
        for(var i=0;i<rows.length;i++){
            if(rows[i].payWayCode.length > 2|| !check(rows[i].payWayCode )){
                editIndex=i;
                $('#dg').datagrid('selectRow',editIndex);
                $.messager.alert("系统提示","支付方式代码只能2位字母或数字!","error");
                return ;
            }
            var numCode=0;
            var numName=0;
            for(var j=0;j<rows.length;j++){
                if(rows[i].payWayCode==rows[j].payWayCode){
                    numCode++;
                    if(numCode>1){
                        editIndex=j;
                        console.log(editIndex);
                        $('#dg').datagrid('selectRow',editIndex);
                        $.messager.alert("系统提示","支付方式代码不能重复!","error");
                        return
                    }
                }
                if(rows[i].payWayName==rows[j].payWayName){
                    numName++;
                    if(numName>1){
                        $.messager.alert("系统提示","支付方式名称不能重复!","error");
                        editIndex=j;
                        $("#dg").datagrid("selectRow",editIndex);
                        return
                    }
                }

            }
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
                //var flag = check(beanChangeVo.inserted[i].payWayCode);
                //if(!flag){
                //    $.messager.alert('系统提示','支付方式代码只能输入字母或数字!','info');
                //    return;
                //}else{
                //    if(beanChangeVo.inserted[i].payWayCode.length > 2){
                //        $.messager.alert('系统提示', '支付方式代码最多两位!', 'info');
                //        return;
                //    }
                //}
            }
        }
        if (beanChangeVo.updated.length > 0) {
            for (var i = 0; i < beanChangeVo.updated.length; i++) {
                beanChangeVo.updated[i].orgId = orgId;
                //var flag = check(beanChangeVo.updated[i].payWayCode);
                //if (!flag) {
                //    $.messager.alert('系统提示', '支付方式代码只能输入字母或数字!', 'info');
                //    return;
                //} else {
                //    if (beanChangeVo.updated[i].payWayCode.length > 2) {
                //        $.messager.alert('系统提示', '支付方式代码最多两位!', 'info');
                //        return;
                //    }
                //}
            }
        }

        if (beanChangeVo) {
            $.postJSON(basePath + '/pay-way-dict/merge', JSON.stringify(beanChangeVo), function (data) {
                $.messager.alert("系统提示", "保存成功", "info");
                loadDict();
            }, function (data) {
                $.messager.alert('提示', '保存失败', "error");
            })
        }
    });
    //检查输入的是否是字母或数字
    function check(str) {
        if(typeof(str) != 'undefined' && str != null && str != ''){
            return /^[\da-z]+$/i.test(str);
        }
    }

    var loadDict = function () {
        $.get(basePath + '/pay-way-dict/list?orgId=' + orgId, function (data) {
            $("#dg").datagrid('loadData', data);
        });
    }
    loadDict();
});
