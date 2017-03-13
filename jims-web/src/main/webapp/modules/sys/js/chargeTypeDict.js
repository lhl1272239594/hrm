/**
 * Created by fyg on 2016/6/24.
 */
$(function () {
    var orgId = config.org_Id;  //组织机构ID
    var editIndex;
    var stopEdit = function () {
        if (editIndex || editIndex == 0) {
            $("#dg").datagrid('endEdit', editIndex);
            $("#dg").datagrid('unselectAll');
            editIndex = undefined;
        }
    }



    $("#dg").datagrid({
        fit: true,//让#dg数据创铺满父类容器
        toolbar: '#tb',
        width: 'auto',
        height: 'auto',
        nowrap: true,   //设置为true，当数据长度超出列宽时将会自动截取
        striped: true,  //设置为true将交替显示行背景
        collapsible: true,//显示可折叠按钮
        fitColumns: true,//允许表格自动缩放，以适应父容器
        singleSelect: true,
        border: true,
        method: 'get',
        collapsible: false,//是否可折叠的
        url: basePath + '/ChargeTypeDict/list-by-orgId?orgId=' + orgId,
        idField: 'fldId',
        remoteSort: false,
        //pagination: true,//分页控件
        //pageSize: 15,
        //pageList: [10, 15, 30, 50],//可以设置每页记录条数的列表
        rownumbers: true,   //行数
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
            title: "费别代码",
            field: "chargeTypeCode",
            align: 'center',
            width: "12%",
            editor: {
                type: 'textbox',
                options: {
                    required: true
                }
            }
        }, {
            title: "费别名称",
            field: "chargeTypeName",
            align: 'center',
            width: '12%',
            editor: {
                type: 'textbox',
                options: {
                    required: true
                }
            }
        }, {
            title: "享受优惠价格",                  //1—享受 0—不享受
            field: "chargePriceIndicator",
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
                        text: '享受',
                        selected: true
                    }, {
                        value: '0',
                        text: '不享受'
                    }]
                }
            },
            formatter: function (value, row, index) {
                if (value == "1") {
                    return "享受";
                }
                if (value == "0") {
                    return "不享受";
                }
            }
        }, {
            title: "拼音码",
            field: "inputCodeWb",
            align: 'center',
            width: '11%'
            //, editor: {
            //    type: 'textbox'
            //}
        }, {
            title: "医保类别",              //0－非医保 1－医保
            field: "isInsur",
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
                        text: '医保',
                        selected: true
                    }, {
                        value: '0',
                        text: '非医保'
                    }]
                }
            },
            formatter: function (value, row, index) {
                if (value == "1") {
                    return "医保";
                }
                if (value == "0") {
                    return "非医保";
                }
            }
        }, {
            title: "费别分组号",             //01自费  02医保 03合作医疗
            field: "groupNo",
            align: 'center',
            width: '12%',
            editor: {
                type: 'combobox', options: {
                    editable: false,
                    align: 'center',
                    valueField: 'value',
                    textField: 'text',
                    data: [{
                        value: '01',
                        text: '自费'
                    }, {
                        value: '02',
                        text: '医保',
                        selected: true
                    }, {
                        value: '03',
                        text: '合作医疗'
                    }]
                }
            },
            formatter: function (value, row, index) {
                if (value == "01") {
                    return "自费";
                }
                if (value == "02") {
                    return "医保";
                }
                if (value == "03") {
                    return "合作医疗";
                }
            }
        }, {
            title: "费别分组名称",
            field: "groupName",
            align: 'center',
            width: "12%",
            editor: {
                type: 'textbox'
            }
        }, {
            title: "院长查询用的医保类别",                //0:自费 1:医保  2:合作医疗
            field: "insuranceTypeInq",
            align: 'center',
            width: "15%",
            editor: {
                type: 'combobox', options: {
                    editable: false,
                    align: 'center',
                    valueField: 'value',
                    textField: 'text',
                    data: [{
                        value: '0',
                        text: '自费'
                    }, {
                        value: '1',
                        text: '医保',
                        selected: true
                    }, {
                        value: '2',
                        text: '合作医疗'
                    }]
                }
            },
            formatter: function (value, row, index) {
                if (value == "0") {
                    return "自费";
                }
                if (value == "1") {
                    return "医保";
                }
                if (value == "2") {
                    return "合作医疗";
                }
            }
        }]],
        onClickRow: function (index, row) {
            stopEdit();
            editIndex = index;
            $("#dg").datagrid('selectRow', editIndex);
            $("#dg").datagrid('beginEdit', editIndex);
        }
    });

    //设置分页控件
    var p = $('#dg').datagrid('getPager');
    $(p).pagination({
        pageSize: 10,//每页显示的记录条数，默认为10 
        pageList: [5, 10, 15],//可以设置每页记录条数的列表 
        beforePageText: '第',//页数文本框前显示的汉字 
        afterPageText: '页    共 {pages} 页',
        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录'
    });

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

    function check(str) {
        if(typeof(str) != 'undefined' && str != null && str != ''){
            return /^[\da-z]+$/i.test(str);
        }
    }

    $("#saveBtn").on("click", function () {
        if (editIndex || editIndex == 0) {
            $("#dg").datagrid("endEdit", editIndex);
        }
        var rows =$('#dg').datagrid('getRows');
        for(var i=0;i<rows.length;i++){
            if(rows[i].chargeTypeCode.length > 2|| !check(rows[i].chargeTypeCode )){
                editIndex=i;
                $('#dg').datagrid('selectRow',editIndex);
                $.messager.alert("系统提示","费别代码只能2位字母或数字!","error");
                return ;
            }
            var numCode=0;
            var numName=0;
            for(var j=0;j<rows.length;j++){
                if(rows[i].chargeTypeCode==rows[j].chargeTypeCode){
                    numCode++;
                    if(numCode>1){
                        editIndex=j;
                        console.log(editIndex);
                        $('#dg').datagrid('selectRow',editIndex);
                        $.messager.alert("系统提示","费别代码不能重复!","error");
                        return
                    }
                }
                if(rows[i].chargeTypeName==rows[j].chargeTypeName){
                    numName++;
                    if(numName>1){
                        $.messager.alert("系统提示","费别名称不能重复!","error");
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
                var chargeTypeCode = beanChangeVo.inserted[i].chargeTypeCode;
            }
        }
        if (beanChangeVo.updated.length > 0) {
            for (var i = 0; i < beanChangeVo.updated.length; i++) {
                beanChangeVo.updated[i].orgId = orgId;
                var chargeTypeCode = beanChangeVo.updated[i].chargeTypeCode;
            }
        }
        if (beanChangeVo) {
            $.postJSON(basePath + '/ChargeTypeDict/merge', JSON.stringify(beanChangeVo), function (data) {
                $.messager.alert("系统提示", "保存成功", "info");
                loadDict();
                stopEdit();
            }, function (data) {
                $.messager.alert('提示', '保存失败', "error");
                stopEdit();
            })
        }
    });

    $("#searchBtn").on("click", function() {
        var name = $("#name").textbox("getValue");
        $.get(basePath + '/ChargeTypeDict/search?chargeTypeName=' + name + '&orgId=' + orgId, function (data) {
            $("#dg").datagrid('loadData', data);
        });
    });

    var loadDict = function () {
        $.get(basePath + '/ChargeTypeDict/list-by-orgId?orgId=' + orgId, function (data) {
            $("#dg").datagrid('loadData', data);
        });
    }
    loadDict();
});
