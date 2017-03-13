var page = '1';
var rows = '30';
var orgId = parent.config.org_Id;
var month='';
var title;//报表名
$(function () {
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
    $.get(basePath + '/report/getReportTitle?month='+month, function (data) {
        if (data) {
            init_title(data.name, data.selfAvg, data.avg);
            loadDatagrid();
        }
        else {
            title='';
            loadDatagrid();
        }
    });
    //按条件查询(汇总)
    $("#searchAllBtn").on("click", function () {
        //获取月份
        month = $("#month1").val();
        if(month==null||month==''||month.length==0){
            month='';
        }
        $.get(basePath + '/report/getReportTitle?month='+month, function (data) {
            if (data) {
                init_title(data.name, data.selfAvg, data.avg);
                loadDatagrid();
            }
            else {
                title='';
                loadDatagrid();
            }
        });
    });
    //清空
    $("#clearBtn").on('click', function () {
        //清空月份
        $("#month").datebox('clear');
        $("#month1").val('');
        month = '';
    });
    /**
     * 展开
     */
    $("#openBtn").on('click', function () {
        var rows=$("#reportGrid").datagrid('getRows');
        for(var i=0;i<rows.length;i++){
            $("#reportGrid").datagrid('expandRow',i);
        }
    });
    /**
     * 收缩
     */
    $("#closeBtn").on('click', function () {
        var rows=$("#reportGrid").datagrid('getRows');
        for(var i=0;i<rows.length;i++){
            $("#reportGrid").datagrid('collapseRow',i);
        }
    });
});

function  init_title(name,selfAvg,avg) {
    if(selfAvg!='0')
        selfAvg=selfAvg+'%';
    if(avg!='0')
        avg=avg+'%';
    title=name+'   '+'自评均值：'+selfAvg+'   '+'考评均值：'+avg;
}
//指定列求和
function compute(name,tableName) {
    var rows = $('#'+tableName).datagrid('getRows');
    var total = 0;
    var num=0;
    for (var i = 0; i < rows.length; i++) {
        if(rows[i].state=='1'){
            if(!rows[i][name]){
                var score=0;
            }
            else{
                var score=rows[i][name];
            }
            total += parseFloat(score);
            num++;
        }
    }
    return (total/num).toFixed(2);
}
function loadDatagrid() {
    $("#reportGrid").datagrid({
        iconCls: 'icon-edit',//图标
        width: '100%',
        height: '100%',
        nowrap: false,
        fit: true,
        fitColumns: true,
        toolbar: '#tb',
        method: 'GET',
        striped: true,
        border: true,
        pagination: false,//分页控件
        loadMsg: '数据正在加载中，请稍后.....',
        collapsible: false,//是否可折叠的
        url: basePath +'/report/getPublishReport?month='+month,
        remoteSort: false,
        idField: 'id',
        singleSelect: false,//是否单选
        rownumbers: true,//行号
        view: detailview,//子表
        columns: [[
            {  title:title,colspan: 3,align:'center'}], //第一行
            [{field: 'systemName', title: '科系名称', width: '30%', align: 'center'},
                {field: 'selfAvg', title: '科系自评均值', width: '30%', align: 'center',
                    formatter: function (value, row, index) {
                        if(value){
                            if(parseFloat(value)>0){
                                return value+'%';
                            }
                            else {
                                return '0';
                            }
                        }
                    }},
                {field: 'avg', title: '科系考评均值', width: '30%', align: 'center',
                    formatter: function (value, row, index) {
                        if(value){
                            if(parseFloat(value)>0){
                                return value+'%';
                            }
                            else {
                                return '0';
                            }
                        }
                    }}
            ]], onLoadSuccess: function (data) {
            $('#reportGrid').datagrid('fixRowHeight');
        },detailFormatter:function(index,row){
            return '<div style="padding:2px"><table id="ddv-' + index + '"></table></div>';
        },onExpandRow:function(index,row){//注意3
            rowIndex=index;
            var rate=row.avg;
            //点击展开该科系下所有科室得分
            $('#ddv-'+index).datagrid({
                url: basePath + '/report/getReportData?pid=' + row.id,
                width: '100%',
                method: 'get',
                fitColumns:true,
                striped:true,
                singleSelect:true,
                rownumbers:true,
                loadMsg:'',
                idField: 'id',
                height:'auto',
                columns:[[
                    {field: 'systemName', title: '科系名称', width: '16%', align: 'center'},
                    {field: 'deptName', title: '科室名称', width: '16%', align: 'center'},
                    {field: 'score', title: '科室得分', width: '16%', align: 'center'},
                    {field: 'totalScore', title: '总分', width: '16%', align: 'center'},
                    {field: 'rate', title: '科室达标率(%)', width: '16%', align: 'center',
                        formatter: function (value, row, index) {
                            if(value){
                                return value;
                            }
                        }
                    },
                    {
                        field: 'rateAvg', title: '科室平均达标率(%)', width: '16%', align: 'center',
                        formatter: function (rateAvg, row, rowIndex) {
                            if(rateAvg){
                                return rateAvg;
                            }
                        }
                    }
                ]],
                onResize:function(){
                    $('#reportGrid').datagrid('fixDetailRowHeight',index);
                },
                onLoadSuccess:function(){

                    mergeCellsByField("ddv-"+index, "systemName");
                    var rateAvg=compute('rateAvg',"ddv-"+index);
                    $('#ddv-'+index).datagrid('appendRow',{
                        systemName: '合计：',
                        deptName: '',
                        score: '',
                        totalScore: '',
                        rate: rate,
                        rateAvg: rateAvg
                    });
                    setTimeout(function(){
                        $('#reportGrid').datagrid('fixDetailRowHeight',index);
                    },0);
                    $('#ddv-'+index).datagrid('fixRowHeight');
                }
            });
            $('#reportGrid').datagrid('fixDetailRowHeight',index);
        }
    });
}
