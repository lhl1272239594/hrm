/**
 *考勤记录
 * @author yangchen
 * @version 2016-08-18
 */
var basePath = "/service";
var userName = "";
var page = '1';
var rows = '30';
var sumDays = "0";
var dg;
var d;
var flag;
var editIndex = undefined;
var fileName = '';
var fileUrl = '';
var search=false;
var attMonth='';
var orgId = parent.config.org_Id;
$(function () {

    //定义组织ID

    $("#freTimeMonth").datebox({
        onShowPanel: function () {//显示日趋选择对象后再触发弹出月份层的事件，初始化时没有生成月份层
            span.trigger('click'); //触发click事件弹出月份层
            if (!tds) setTimeout(function () {//延时触发获取月份对象，因为上面的事件触发和对象生成有时间间隔
                tds = p.find('div.calendar-menu-month-inner td');
                tds.click(function (e) {
                    e.stopPropagation(); //禁止冒泡执行easyui给月份绑定的事件
                    var year = /\d{4}/.exec(span.html())[0]//得到年份
                        , month = parseInt($(this).attr('abbr'), 10); //月份，这里不需要+1
                    $('#freTimeMonth').datebox('hidePanel')//隐藏日期对象
                        .datebox('setValue', year + '-' + month); //设置日期的值
                });
            }, 0);
            yearIpt.unbind();//解绑年份输入框中任何事件
        },
        parser: function (s) {
            if (!s) return new Date();
            var arr = s.split('-');
            return new Date(parseInt(arr[0], 10), parseInt(arr[1], 10) - 1, 1);
        },
        formatter: function (d) {
            var a = parseInt(d.getMonth()) < parseInt('9') ? '0' + parseInt(d.getMonth() + 1) : d.getMonth() + 1;
            return d.getFullYear() + '-' + a;
        }
    });
    var p = $('#freTimeMonth').datebox('panel'), //日期选择对象
        tds = false, //日期选择对象中月份
        yearIpt = p.find('input.calendar-menu-year'),//年份输入框
        span = p.find('span.calendar-text'); //显示月份层的触发控件
    //考勤数据查询
    $("#attDataGrid").datagrid({
        iconCls: 'icon-edit',//图标
        width: '100%',
        height: '100%',
        nowrap: false,
        striped: true,
        border: true,
        method: 'get',
        toolbar: '#tb',
        fitColumns: true,
        loadMsg: '数据正在加载中，请稍后.....',
        pagination: true,//分页控件
        pageSize: 30,
        pageList: [10, 15, 30, 50],//可以设置每页记录条数的列表
        collapsible: false,//是否可折叠的
        url: basePath + '/attendanceData/attendance-month-report?orgId=' + orgId + '&userName=' + userName+'&attMonth='+attMonth,
        remoteSort: false,
        singleSelect: true,//是否单选
        rownumbers: true,//行号
        columns: [[
            {field: 'orgId', title: '', hidden: true},
            {field: 'attDataId', title: '', hidden: true},
            {field: 'deptName', title: '员工科室', width: '15%', align: 'center'},
            {field: 'userName', title: '员工姓名', width: '15%', align: 'center'},
            {field: 'freDate', title: '考勤月份', width: '10%', align: 'center'},
            {field: 'nums1', title: '正常（次）', width: '10%', align: 'center'},
            {field: 'nums6', title: '休息（次）', width: '10%', align: 'center'},
            {field: 'nums2', title: '请假（次）', width: '10%', align: 'center'},
            {field: 'nums3', title: '旷工（次）', width: '10%', align: 'center'},
            {field: 'nums4', title: '迟到（次）', width: '10%', align: 'center'},
            {field: 'nums5', title: '早退（次）', width: '10%', align: 'center'}

        ]],
        onLoadSuccess:function(data){

        }
    });

    $("#attDataGrid").datagrid('getPager').pagination({
        pageList: [10, 20, 30, 40, 50], //可以设置每页记录条数的列表
        displayMsg: '当前显示从第{from}条到第{to}条，共{total}条记录',
        onSelectPage: function (pageNumber, pageSize) {
            var state = $('#attDataGrid').data('datagrid');
            var opts = state.options;
            page=opts.pageNumber = pageNumber;
            rows=opts.pageSize = pageSize;
            searchData(page,rows);
        }
    });

    //按条件查询数据
    $("#searchBtn").on("click", function () {
        search=true;
        userName = $("#userName").textbox('getValue');
        attMonth = $("#freTimeMonth").datebox('getValue');
        searchData(page, rows);
    });

    var searchData= function (page,rows){
        $("#attDataGrid").datagrid('reload', basePath + '/attendanceData/attendance-month-report?userName=' + userName + '&orgId=' + orgId + '&attMonth='+attMonth+'&page=' + page + '&rows=' + rows);
        if(search){
            search=false;
            $("#attDataGrid").datagrid('getPager').pagination('select',1);
        }
    }

    //配置窗口
    $("#editWin").window({
        title: '考勤详情',
        closed: true,
        minimizable: false,
        modal: true,
        onClose: function () {
            $('#attDataGrid').datagrid('clearSelections');

        },
        onOpen: function () {

        }
    });

    // 查看
    $("#viewBtn").on("click", function () {
        var row = $("#attDataGrid").datagrid('getSelected');
        if (row) {
            $("#editWin").window('open');
            $("#recordUserName").textbox('setValue', row.userName);
            $("#recordDeptName").textbox('setValue', row.deptName);
                userId=row.userId;
            $("#dataGrid").datagrid({
                iconCls: 'icon-edit',//图标
                width: '100%',
                height: '100%',
                nowrap: false,
                striped: true,
                border: true,
                method: 'get',
                loadMsg: '数据正在加载中，请稍后.....',
                collapsible: false,//是否可折叠的
                url: basePath + '/attendanceData/attendance-day-report-all?orgId=' + orgId + '&userId=' + row.userId,
                remoteSort: false,
                singleSelect: true,//是否单选
                rownumbers: true,//行号
                pageSize: 50,
                columns: [[
                    {field: 'orgId', title: '', hidden: true},
                    {field: 'attDataId', title: '', hidden: true},
                    {field: 'freDate', title: '排班日期', width: '20%', align: 'center'},
                    {field: 'freItemDes', title: '班次', width: '20%', align: 'center'},
                    {field: 'ruleTime', title: '规定打卡时间', width: '20%', align: 'center'},
                    {field: 'checkTime', title: '实际打卡时间', width: '20%', align: 'center'},
                    {field: 'statusType', title: '考勤状态', width: '20%', align: 'center'},
                ]],

                onLoadSuccess: function () {
                    $.messager.progress('close');

                }
            });
        } else {
            $.messager.alert("提示", "请选择一条记录!", "info");

            return;

        }
    });
    $("#clearBtn").on('click', function () {
        $("#userName").textbox('setValue', '');

        $("#freTimeMonth").datebox('setValue', '');

        page = '1';

    });


});

