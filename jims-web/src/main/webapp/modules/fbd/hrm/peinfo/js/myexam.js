
var basePath = "/service";
var orgId='';
var examId='';
var planId='';
var userName='';
var page='1';
var rows='30';
var type = '';//考试类型
var planName = '';//考试计划名称
var userName = '';//考生名称
var search=false;
var month='';
$(function () {
    orgId= parent.config.org_Id;

    $("#resultGrid").datagrid({
        toolbar: '#tb',
        iconCls: 'icon-edit',//图标
        width: 'auto',
        height: 'auto',
        nowrap: false,
        striped: true,
        border: true,
        method: 'get',
        loadMsg: '数据正在加载中，请稍后.....',
        pagination: true,//分页控件
        pageSize: 30,
        collapsible: false,//是否可折叠的
        fit: true,//自动大小
        url: basePath + '/examResult/getmyexam?pid='+parent.config.persion_Id+'&orgId=' + orgId +"&type="+type+"&planName="+planName+"&month="+month,
        remoteSort: false,
        idField: 'resultId',
        singleSelect: true,//是否单选
        rownumbers: true,//行号
        columns: [[
            {field: 'typeName', title: '考试类型', width: '15%', align: 'center'},
            {field: 'planName', title: '考试名称', width: '20%', align: 'center'},
            {field: 'userName', title: '考生姓名', width: '15%', align: 'center'},
            {field: 'score', title: '得分', width: '10%', align: 'center'},
            {field: 'state', title: '结果', width: '10%', align: 'center',
                formatter: function (value, row, index) {
                    if (value == "3") {
                        return "及格";
                    }
                    if (value == "4") {
                        return "不及格";
                    }
                }},
            {field: 'time', title: '考试时长', width: '10%', align: 'center'},
            {field: 'endTime', title: '结束时间', width: '20%', align: 'center'}

        ]],onLoadSuccess:function(data) {

        }
    });
    $("#resultGrid").datagrid('getPager').pagination({
        pageList: [10, 20, 30, 40, 50], //可以设置每页记录条数的列表
        displayMsg: '当前显示从第{from}条到第{to}条，共{total}条记录',
        onSelectPage: function (pageNumber, pageSize) {
            var state1 = $('#resultGrid').data('datagrid');
            var opts = state1.options;
            page = opts.pageNumber = pageNumber;
            rows = opts.pageSize = pageSize;
            searchData(page, rows);
            return;

        }

    });
    $("#type").combobox({     //加载计划类型
        url: basePath + '/dict/find-list-by-type?type=' + 'EXAM_TYPE',
        valueField: 'id',
        textField: 'value',
        value: '请选择',
        method: 'GET'

    });
    $("#searchBtn").on("click", function () {
        search=true;
        //获取考试类型
        type = $("#type").combobox('getValue');
        if(type=='请选择'){
            type='';
        }
        //获取考试计划名称
        planName = $("#planName").textbox('getValue');
        //获取考试计划名称
        //var userName = $("#userName").textbox('getValue');
        //获取月份
        month = $("#month1").val();
        if(month==null||month==''||month.length==0){
            month='';
        }
        searchData(page,rows);
    });
    var searchData= function (page,rows){
        $("#resultGrid").datagrid('reload', basePath + '/examResult/getmyexam?pid='+parent.config.persion_Id+'&orgId=' + orgId +"&type="+type+"&planName="+planName+"&month="+month);
        if(search){
            search=false;
            $("#resultGrid").datagrid('getPager').pagination('select',1);

        }
    }
    $("#clearBtn").on("click", function () {
        clearKey();
    });
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
            next.unbind();
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
        next=p.find('.calendar-prevyear'),
    yearIpt = p.find('input.calendar-menu-year'),//年份输入框
        span = p.find('span.calendar-text'); //显示月份层的触发控件

});
//清空查询条件
function clearKey() {
    //清空考试类型
    $("#type").combobox('clear');//获取表格对象
    $("#type").combobox('setValue','请选择');
    type = '';
    //清空考试计划名称
    $("#planName").textbox('clear');
    planName = '';
    //清空状态
    $("#month").datebox('clear');
    $("#month1").val('');
    month = '';
    page='1';

}
