/*
 *排班管理
 * @author yangchen
 * @version 2016-08-18
 *!/*/
var basePath = "/service";

var dg;
var d;
var flag;
var editIndex = undefined;
var page = '1';
var rows = '30';
var contractTypeVo = {};
var orgId = parent.config.org_Id;
var id = '999';
var value = '';
var userName = '';
var label = '';
var url = '';
var obj1 = new Object();
var obj2 = new Object();
var deptName;
var search = false;
var depts = [];
var treeDepts = [];
var contractTypeDes = '';
var contractRemindTime = '';
$(function () {


    $("#primaryGrid").edatagrid({
        iconCls: 'icon-edit',//图标
        width: '100%',
        height: '100%',
        nowrap: false,
        striped: true,
        border: true,
        pageSize: 30,
        method: 'get',
        toolbar: '#tb',
        fit: true,
        loadMsg: '数据正在加载中，请稍后.....',
        pagination: true,//分页控件
        collapsible: false,//是否可折叠的
        remoteSort: false,
        singleSelect: true,//是否单选
        rownumbers: true,//行号
        url: basePath + '/contractType/contract-type-list?orgId=' + orgId,
        columns: [[
            {field: 'orgId', title: '', hidden: true},
            {field: 'contractTypeId', title: '', hidden: true},
            {field: 'contractTypeDes', title: '合同类型', width: '25%', align: 'center',},
            {field: 'contractRemindTime', title: '提醒时间(天)', width: '25%', align: 'center',},
            {
                field: 'createBy', title: '创建人', width: '25%', align: 'center',
                formatter: function (createBy) {
                    return parent.personList[createBy];
                }
            },
            {field: 'createDate', title: '创建时间', width: '25%', align: 'center'}
        ]],
        onLoadSuccess: function (data) {

        }
    });

    $("#primaryGrid").datagrid('getPager').pagination({
        pageList: [10, 20, 30, 40, 50], //可以设置每页记录条数的列表
        displayMsg: '当前显示从第{from}条到第{to}条，共{total}条记录',
        onSelectPage: function (pageNumber, pageSize) {
            var state = $('#primaryGrid').data('datagrid');
            var opts = state.options;
            page = opts.pageNumber = pageNumber;
            rows = opts.pageSize = pageSize;
            searchAllData(page, rows);
        }
    });


    var searchAllData = function (page, rows) {


        $("#primaryGrid").datagrid('reload', basePath + '/contractType/contract-type-list?orgId=' + orgId + '&page=' + page + '&rows=' + rows);

        if (search) {
            search = false;
            $("#primaryGrid").datagrid('getPager').pagination('select', 1);
        }
    };


    //配置窗口
    $("#editWin").window({
        title: '合同类别',
        closed: true,
        modal: true,
        minimizable: false,

        onClose: function () {
            $('#primaryGrid').datagrid('clearSelections');
            $("#primaryGrid").datagrid('reload');
            $("#editForm").form('reset');
            $("#chooseUser").show();
        },
        onOpen: function () {

        }
    });

    //新增
    $("#addBtn").on('click', function () {
        $("#editWin").window("setTitle", "合同类型新增").window('open');
        $("#flag").val('0');
        $("#id").val('999');

    });


    //修改
    $("#editBtn").on("click", function () {
        //获取选择行
        var row = $('#primaryGrid').datagrid('getSelected');
        if (row) {
            $("#editWin").window("setTitle", "合同类型修改").window('open');

            $("#editContractTypeDes").textbox('setValue', row.contractTypeDes);
            $("#editContractRemindTime").textbox('setValue', row.contractRemindTime);

            $("#id").val(row.contractTypeId);
            $("#flag").val('1');
        }
        else {
            $.messager.alert('提示', '请选择一行记录！', 'info');

        }
    });
    //删除
    $("#delBtn").on('click', function () {
        flag = '0';
        var row = $("#primaryGrid").datagrid('getSelected');
        if (row) {

            contractTypeVo.contractTypeId = row.contractTypeId;
            ;
            $.messager.confirm('提示', '确定要进行删除操作吗', function (r) {
                if (r) {
                    $.postJSON(basePath + "/contractType/contract-type-del", JSON.stringify(contractTypeVo), function (data) {

                        $('#primaryGrid').datagrid('reload');
                        row.length = 0;
                    });
                }
            })
        } else {
            $.messager.alert('提示', '请选择一行记录！', 'info');

            return;

        }
    });
    //保存
    $("#saveBtn").on("click", function () {
        id = $("#id").val();
        flag = $("#flag").val();
        contractTypeDes = $("#editContractTypeDes").textbox('getValue');
        contractRemindTime = $("#editContractRemindTime").textbox('getValue');

        if (contractTypeDes == '' || contractTypeDes.indexOf(" ") >= 0) {
            $.messager.alert("提示", "请输入有效的合同类型名称，不能包含空格!", "info");
            return
        }


        contractTypeVo.contractTypeId = id;
        contractTypeVo.contractTypeDes = contractTypeDes;
        contractTypeVo.contractRemindTime = contractRemindTime;
        //判断是否已存在相同名称数据
        $.get(basePath + "/contractType/contract-type-boolean?orgId=" + orgId + "&contractTypeDes=" + contractTypeDes + "&contractTypeId=" + id,
            function (data) {
                    var num = data[0].num;
                if (num == 1 || num > 1) {
                    var str = '该合同类型描述在系统中已存在！';
                    $.messager.alert("提示", str, "info");
                    return
                }
                $.postJSON(basePath + "/contractType/merge", JSON.stringify(contractTypeVo), function (data) {
                    $("#editForm").form('reset');
                    $("#editWin").window('close');
                    $("#primaryGrid").datagrid('reload');
                });

            });
    });

    //取消
    $("#cancelBtn").on('click', function () {
        $("#editForm").form('reset');
        $("#editWin").window('close');
    });
});

