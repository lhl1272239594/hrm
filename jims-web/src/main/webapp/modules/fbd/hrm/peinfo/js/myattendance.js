/**
 *我的考勤
 * @author yangchen
 * @version 2016-09-18
 */
var basePath = "/service";
var userName="-999";
var userCode="-999";
var attendanceDataVo = {};
var sumDays="0";
var dg;
var d;
var flag;
var editIndex=undefined;
var fileName='';
var fileUrl='';

$(function () {

    //定义组织ID
    var orgId = parent.config.org_Id;
    var personId = parent.config.persion_Id;

    //考勤数据查询
    $("#attDataGrid").edatagrid({
        iconCls: 'icon-edit',//图标
        width: '100%',
        height: '100%',
        nowrap: false,
        striped: true,
        border: true,
        singleSelect:true,
        method: 'get',
        //toolbar: '#tb',
        fitColumns:true,
        loadMsg: '数据正在加载中，请稍后.....',
        pagination: true,//分页控件
        pageSize: 30,
        pageList: [10, 15, 30, 50],//可以设置每页记录条数的列表
        collapsible: false,//是否可折叠的
        url: basePath + '/attendanceData/attendanceData-list?orgId=' + orgId + '&userName=' + personId,
        remoteSort: false,
        idField: 'attId',
        singleSelect: true,//是否单选
        rownumbers: true,//行号
        columns: [[
            {field: 'orgId', title: '', width: 100,hidden: true},
            {field: 'attId', title: '', width: 100,hidden: true},
            {field: 'userName', title: '员工姓名', width: '10%', align: 'center'},
            {field: 'deptName', title: '员工部门', width: '10%', align: 'center'},
            {field: 'attDate', title: '考勤日期', width: '10%', align: 'center'},
            {field: 'checkTime', title: '打卡时间', width: '10%', align: 'center'},

        ]]
    });



});


