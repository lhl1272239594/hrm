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
var contractProbationPeriodVo = {};
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
var probationPeriodTimes = '';
var remindTime = '';
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
        url: basePath + '/contractProbationPeriod/contract-probation-period-list?orgId=' + orgId,
        columns: [[
            {field: 'orgId', title: '', hidden: true},
            {field: 'probationPeriodId', title: '', hidden: true},
            {field: 'probationPeriodTimes', title: '试用期(天)', width: '25%', align: 'center',},
            {field: 'remindTime', title: '预警时间(天)', width: '25%', align: 'center',},
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


        $("#primaryGrid").datagrid('reload', basePath + '/contractProbationPeriod/contract-probation-period-list?orgId=' + orgId + '&page=' + page + '&rows=' + rows);

        if (search) {
            search = false;
            $("#primaryGrid").datagrid('getPager').pagination('select', 1);
        }
    };


    //配置窗口
    $("#editWin").window({
        title: '试用期',
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
        $("#editWin").window("setTitle", "试用期新增").window('open');
        $("#flag").val('0');
        $("#id").val('999');

    });


    //修改
    $("#editBtn").on("click", function () {
        //获取选择行
        var row = $('#primaryGrid').datagrid('getSelected');
        if (row) {
            $("#editWin").window("setTitle", "试用期修改").window('open');

            $("#editProbationPeriodTimes").textbox('setValue', row.probationPeriodTimes);
            $("#editRemindTime").textbox('setValue', row.remindTime);

            $("#id").val(row.probationPeriodId);
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

            contractProbationPeriodVo.probationPeriodId = row.probationPeriodId;
            ;
            $.messager.confirm('提示', '确定要进行删除操作吗', function (r) {
                if (r) {
                    $.postJSON(basePath + "/contractProbationPeriod/contract-probation-period-del", JSON.stringify(contractProbationPeriodVo), function (data) {

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
         probationPeriodTimes = $("#editProbationPeriodTimes").textbox('getValue');
         remindTime = $("#editRemindTime").textbox('getValue');




        contractProbationPeriodVo.probationPeriodId = id;
        contractProbationPeriodVo.probationPeriodTimes = probationPeriodTimes;
        contractProbationPeriodVo.remindTime = remindTime;
        //判断是否已存在相同数据
        $.get(basePath + "/contractProbationPeriod/contract-probation-period-boolean?orgId=" + orgId + "&probationPeriodTimes=" + probationPeriodTimes + "&probationPeriodId=" + id,
            function (data) {
                    var num = data[0].num;
                if (num == 1 || num > 1) {
                    var str = '该合同试用期在系统中已存在！';
                    $.messager.alert("提示", str, "info");
                    return
                }
                $.postJSON(basePath + "/contractProbationPeriod/merge", JSON.stringify(contractProbationPeriodVo), function (data) {
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

