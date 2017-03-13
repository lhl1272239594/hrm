/*
 *加班管理
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
var offWorkVo = {};
var orgId = parent.config.org_Id;
var id = '999';
var startDate = '';
var endDate = '';
var freItemId = '';
var freItemOrder = '';
var userId = '';
var searchId = '';
var offWorkReason = '';
var holidayType = '';
var deptId = '';
var depts = [];
var treeDepts = [];
var approveStatus = '';
var deptIds=parent.orgids;
var search=false;
var lx='add';
var month='';
var orgCount = 0;
$(function () {

    var obj1 = new Object();
    var obj2 = new Object();
    var obj3 = new Object();


    //查询条件：审批状态
    $("#approveStatus").combobox({
        idField: 'value',
        textField: 'label',
        value: '请选择'
    });
    $.get(basePath + '/tool/find-list-by-type?type=APPROVE_STATUS', function (data) {
        $.each(data, function (index, item) {
            obj3[item.value] = item.label;
        });
        $("#approveStatus").combobox('loadData',data);
    });
    $("#primaryGrid").datagrid({
        width: '100%',
        height: '100%',
        nowrap: false,
        striped: true,
        border: true,
        singleSelect: true,
        method: 'get',
        toolbar: '#tb',
        fitColumns: true,
        loadMsg: '数据正在加载中，请稍后.....',
        pagination: true,//分页控件
        pageSize: 30,
        collapsible: false,//是否可折叠的
        url: basePath + '/offWork/myvacation?orgId=' + orgId + '&approveStatus=' + approveStatus+'&month='+month,
        remoteSort: false,
        singleSelect: true,//是否单选
        rownumbers: true,//行号
        columns: [[
            {field: 'orgId', title: '', width: '10%', hidden: true},
            {field: 'offWorkId', title: '', width: '10%', hidden: true},
            {field: 'holidayId', title: '', width: '10%', hidden: true},
            {
                field: 'userId', title: '员工姓名', width: '10%', align: 'center',
                formatter: function (userId) {
                    return parent.personList[userId];
                }
            },
            {
                field: 'deptId', title: '员工部门', width: '17%', align: 'center',
                formatter: function (deptId) {
                    $.ajaxSettings.async = false;
                    $.get(basePath + '/tool/find-dept-list?deptId=' + deptId, function (data) {
                        deptName = data[0].deptName;
                    });
                    return deptName
                }
            },
            {field: 'startDate', title: '开始时间', width: '10%', align: 'center'},
            {field: 'endDate', title: '结束时间', width: '10%', align: 'center'},
            {field: 'holidayDes', title: '请假类型', width: '10%', align: 'center'},
            {field: 'offWorkReason', title: '请假原因', width: '10%', align: 'center'},
            {
                field: 'approveStatus', title: '审批状态', width: '10%', align: 'center',
                formatter: function (value) {
                    return obj3[value]
                }
            },
            {field: 'approvePersonId', title: '审批人', width: '10%', align: 'center',
                formatter: function (approvePersonId) {
                    return parent.personList[approvePersonId];
                }
            },

            {field: 'approveDate', title: '审批时间', width: '10%', align: 'center'},
            {
                field: 'createBy', title: '提交人', width: '10%', align: 'center',
                formatter: function (createBy) {
                    return parent.personList[createBy];
                }
            },
            {field: 'updateDate', title: '提交时间', width: '13%', align: 'center'}
        ]],
        onLoadSuccess: function () {

        },
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
            return;

        }

    });
    //按条件查询(汇总)
    $("#searchAllBtn").on("click", function () {
        search=true;
        approveStatus = $("#approveStatus").textbox('getValue');
        if(approveStatus=='请选择'){
            approveStatus='';
        }
        month = $("#month1").val();
        if(month==null||month==''||month.length==0){
            month='';
        }
        searchAllData(page, rows);

    });
    //按条件查询

    var searchAllData = function (page, rows) {
        $("#primaryGrid").datagrid('reload', basePath + '/offWork/myvacation?orgId=' + orgId + '&approveStatus=' + approveStatus+'&month='+month + '&page=' + page + '&rows=' + rows);
        if(search){
            search=false;
            $("#primaryGrid").datagrid('getPager').pagination('select', 1);
        }
    }
    $('#month').datebox({
        onShowPanel: function () {//显示日趋选择对象后再触发弹出月份层的事件，初始化时没有生成月份层
            span.trigger('click'); //触发click事件弹出月份层
            if (!tds) setTimeout(function () {//延时触发获取月份对象，因为上面的事件触发和对象生成有时间间隔
                tds = p.find('div.calendar-menu-month-inner td');
                tds.click(function (e) {
                    e.stopPropagation(); //禁止冒泡执行easyui给月份绑定的事件
                    var year = /\d{4}/.exec(span.html())[0]//得到年份
                    var month = parseInt($(this).attr('abbr'), 10); //月份
                    var month1 =month.toString();
                    if(month1<10) {
                        month1 = '0' + month1;
                    }
                    $("#month").datebox('hidePanel')//隐藏日期对象
                        .datebox('setValue', year + '-' + month); //设置日期的值
                    //alert(year + '-' + month);
                    $("#month1").val(year + '-' + month1);
                });
            }, 0);
            span.unbind();
            yearIpt.unbind();//解绑年份输入框中任何事件
        },
        parser: function (s) {
            if (!s) return new Date();
            var arr = s.split('-');
            return new Date(parseInt(arr[0], 10), parseInt(arr[1], 10) - 1, 1);
        },
        formatter: function (d) { return d.getFullYear() + '-' + (d.getMonth() + 1);/*getMonth返回的是0开始的，忘记了。。已修正*/ }
    });
    var p = $('#month').datebox('panel'), //日期选择对象
        tds = false, //日期选择对象中月份
        next=$('#month .calendar-prevyear');
    yearIpt = p.find('input.calendar-menu-year'),//年份输入框
        span = p.find('span.calendar-text'); //显示月份层的触发控件

    $("#clearBtn").on('click', function () {
        $("#approveStatus").combobox('setValue','');
        $("#approveStatus").combobox('setText','请选择');
        //清空状态
        $("#month").datebox('clear');
        $("#month1").val('');
        month = '';
        page='1';
    });

});


