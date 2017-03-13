/**
 *考勤记录
 * @author yangchen
 * @version 2016-08-18
 */
var basePath = "/service";
var userName = "";
var page = '1';
var rows = '30';
var attendanceDataVo = {};
var sumDays = "0";
var dg;
var d;
var flag;
var editIndex = undefined;
var fileName = '';
var fileUrl = '';
var search=false;

$(function () {

    //定义组织ID
    var orgId = parent.config.org_Id;

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
        url: basePath + '/attendanceData/attendance-month-report?orgId=' + orgId + '&userName=' + userName,
        remoteSort: false,
        singleSelect: true,//是否单选
        rownumbers: true,//行号
        columns: [[

            {field: 'orgId', title: '', hidden: true},
            {field: 'attDataId', title: '', hidden: true},
            {field: 'deptName', title: '员工科室', width: '20%', align: 'center'},
            {field: 'userName', title: '员工姓名', width: '20%', align: 'center'},
            {field: 'freDate', title: '排班日期', width: '20%', align: 'center'},
            {field: 'freItemDes', title: '班次', width: '20%', align: 'center'},
            {field: 'statusType', title: '考勤状态', width: '20%', align: 'center'},
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
        searchData(page, rows);
    });

    var searchData= function (page,rows){
        $("#attDataGrid").datagrid('reload', basePath + '/attendanceData/attendance-day-report?userName=' + userName + '&orgId=' + orgId + '&page=' + page + '&rows=' + rows);
        if(search){
            search=false;
            $("#attDataGrid").datagrid('getPager').pagination('select',1);
        }
    }


    $("#clearBtn").on('click', function () {
        $("#userName").textbox('setValue', '');
        page = '1';

    });


});

