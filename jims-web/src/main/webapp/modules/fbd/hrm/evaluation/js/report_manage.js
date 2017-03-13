var page = '1';
var rows = '30';
var orgId = parent.config.org_Id;
var rowIndex=0;//点击行索引
var title;//报表名
var reportState;//1未发布，2已发布
$(function () {
    $.get(basePath + '/report/checkReport', function (data) {
        if(data){
            init_title(data.name,data.selfAvg,data.avg,data.state);
        }
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
            url: basePath +'/report/getReport',
            remoteSort: false,
            idField: 'id',
            singleSelect: false,//是否单选
            rownumbers: true,//行号
            view: detailview,//子表
            columns: [
                [{ title: '<span  id="title" ></span>', colspan: 3,align:'center'}], //第一行
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
                        {field: 'systemName', title: '科系名称', width: '13%', align: 'center'},
                        {field: 'deptName', title: '科室名称', width: '13%', align: 'center'},
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
                                if(row.systemName!='合计：')
                                    return '<input class="easyui-numberbox"  name="btn-save" id="num-'+index+'-'+rowIndex+'" value="'+rateAvg+'" data-options="min:0,max:1000,precision:2" style="height:90%;width: 90%;text-align: center;">';
                                else {
                                    return rateAvg;
                                }
                            }
                        },
                        {
                            field: 'h', title: '操作', width: '10%', align: 'center',
                            formatter: function (value, row, rowIndex) {
                                if(row.systemName!='合计：')
                                    return '<span class="btn-border"><a href="#" name="save" style="padding: 0 3px;"  class="easyui-linkbutton" iconCls="icon-save"  onclick="save(\''+index+'\',\''+rowIndex+'\')">保存</a></span>';
                            }
                        }
                    ]],
                    onResize:function(){
                        $('#reportGrid').datagrid('fixDetailRowHeight',index);
                    },
                    onLoadSuccess:function(){
                        $("a[name='save']").linkbutton({iconCls:'icon-save'});
                        var nb=$("input[name='btn-save']");
                        nb.numberbox({
                            min:0,
                            precision:2
                        });
                        nb.numberbox();

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
    });

    /**
     * 生成报表
     */
    $("#createBtn").on('click', function () {
        if(reportState=='2'){
            $.messager.alert("提示", "报表已发布，不能重新生成！","info");
            return;
        }
        $.get(basePath + '/report/checkReport', function (data) {
            if(data){
                $.messager.confirm("提示", "确定重新生成报表吗?", function (r) {
                    if (r) {
                        createReport();
                    }
                })
            }
            else {
                createReport();
            }
        });
    });
    /**
     * 发布报表
     */
    $("#publicBtn").on('click', function () {
        if(reportState=='2'){
            $.messager.alert("提示", "报表已发布，不能再次发布！","info");
            return;
        }
        $.get(basePath + '/report/publishReport', function (data) {
            if (data.data == "success") {
                $.messager.alert("提示", "报表已发布！","info");
                $.get(basePath + '/report/checkReport', function (data) {
                    if(data){
                        init_title(data.name,data.selfAvg,data.avg,data.state);
                        $("#reportGrid").datagrid('reload');
                    }
                });
            }
        });
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
function createReport() {
    $.get(basePath + '/report/createReport', function (data) {
        if (data.data == "success") {
            $.get(basePath + '/report/checkReport', function (data) {
                if(data){
                    init_title(data.name,data.selfAvg,data.avg,data.state);
                    $("#reportGrid").datagrid('reload');
                }
            });
        }
    });
}
function  init_title(name,selfAvg,avg,state) {
    reportState=state;
    if(selfAvg!='0')
        selfAvg=selfAvg+'%';
    if(avg!='0')
        avg=avg+'%';
    var text='（未发布）';
    if(state=='2')
        text='   （已发布）';
    title=name+'   '+'自评均值：'+selfAvg+'   '+'考评均值：'+avg+'    '+text;
    $("#title").html(title);
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
function save(index,rowIndex) {
    if(reportState=='2'){
        $.messager.alert("提示", "报表已发布，不能修改！","info");
        return;
    }
    var rateAvg=$("#num-"+index+"-"+rowIndex).val();
    var rows=$("#ddv-"+index).datagrid('getRows');
    var row = rows[rowIndex];
    row.rateAvg = rateAvg;
    $.postJSON(basePath + "/report/editRateAvg", JSON.stringify(row), function (data) {
        if(data.data=="success"){
            $('#ddv-'+index).datagrid('reload');
        }
        else{
            $.messager.alert("提示", "失败","info");
        }
    });
}
