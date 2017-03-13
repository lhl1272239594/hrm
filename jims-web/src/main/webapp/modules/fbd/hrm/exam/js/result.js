
var basePath = "/service";
var orgId='';
var examId='';
var planId='';
var page='1';
var rows='30';
var deptIds=parent.orgids;
var search=false;
$(function () {
    orgId= parent.config.org_Id;
    var type = '';//考试类型
    var planName = '';//考试计划名称
    var userName = '';//考生名称
    $("#resultGrid").datagrid({
        toolbar: '#tb',
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
        url: basePath + '/examResult/resultList?orgId=' + orgId +"&type="+type+"&planName="+planName+"&userName="+userName+'&deptIds=' + deptIds,
        remoteSort: false,
        idField: 'resultId',
        singleSelect: true,//是否单选
        rownumbers: true,//行号
        columns: [[
            {field: 'typeName', title: '考试类型', width: '10%', align: 'center'},
            {field: 'planName', title: '考试名称', width: '30%', align: 'center'},
            {field: 'userName', title: '考生姓名', width: '10%', align: 'center'},
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
        ]],onLoadSuccess:function(data){

        }
    });
    $("#resultGrid").datagrid('getPager').pagination({
        pageList: [10, 20, 30, 40, 50], //可以设置每页记录条数的列表
        displayMsg: '当前显示从第{from}条到第{to}条，共{total}条记录',
        onSelectPage: function (pageNumber, pageSize) {
            var state1 = $('#resultGrid').data('datagrid');
            var opts = state1.options;
            page=opts.pageNumber = pageNumber;
            rows=opts.pageSize = pageSize;
            searchData(page,rows);
            return;
        }
    });
    $("#type").combobox({     //加载计划类型
        url: basePath + '/dict/find-list-by-type?type=' + 'EXAM_TYPE',
        valueField: 'id',
        textField: 'value',
        value:'请选择',
        method: 'GET'

    });
    var searchData= function (page,rows){
        $("#resultGrid").datagrid('reload',basePath + '/examResult/resultList?orgId=' + orgId +"&type="+type+"&planName="+planName+"&userName="+userName+ '&page=' + page+ '&rows=' + rows+'&deptIds=' + deptIds);
        if(search){
            search=false;
            $("#resultGrid").datagrid('getPager').pagination('select',1);

        }
    }

    $("#searchBtn").on("click", function () {
        search=true;
        //获取计划类型ID
        type = $("#type").combobox('getValue');
        if(type=='请选择'){
            type='';
        }
        //获取考试计划名称
        planName = $("#planName").textbox('getValue');
        //获取考试计划名称
        userName = $("#userName").textbox('getValue');
        searchData(page,rows);
    });
    $("#clearBtn").on("click", function () {
        clearKey();
    });



});
//清空查询条件
function clearKey() {
    //清空题型ID
    $("#type").combobox('clear');//获取表格对象
    $("#type").combobox('setValue','请选择');//获取表格对象
    type = '';
    //清空考试计划名称
    $("#planName").textbox('clear');
    planName = '';
    //清空考生姓名
    $("#userName").textbox('clear');
    userName = '';
    page='1';

}
