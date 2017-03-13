/**
 *试题分
 * @author wangzhiming
 * @version 2016-08-18
 */

var basePath = "/service";
var orgId='';
var typeId='';
var time='';//考试时长
var page='1';
var rows='30';
var deptIds=parent.orgids;
var search=false;
$(function () {
    orgId= parent.config.org_Id;
    var type = '';//题型ID
    var state = '';//状态
    $("#planGrid").datagrid({
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
        url: basePath + '/exam/planList?orgId=' + orgId + '&type=' + type + '&state=' + state+'&deptIds=' + deptIds,
        remoteSort: false,
        idField: 'planId',
        singleSelect: true,//是否单选
        rownumbers: true,//行号
        columns: [[
            {field: 'orgId', title: '', hidden: true},
            {field: 'typeName', title: '计划类型', width: '10%', align: 'center'},
            {field: 'planName', title: '计划名称', width: '15%', align: 'center'},
            {field: 'examName', title: '试卷名称', width: '15%', align: 'center'},
            {
                field: 'state', title: '状态', width: '10%', align: 'center',
                formatter: function (value, row, index) {
                    if (value == "3") {
                        return "结束";
                    }
                    if (value == "2") {
                        return "待评分";
                    }
                    if (value == "1") {
                        return "发布";
                    }
                    if (value == "0") {
                        return "编辑";
                    }
                }
            },
            {field: 'start', title: '开始时间', width: '12%', align: 'center',
                formatter:function (start) {
                    return start.substr(0,start.length-3);
                }
            },
            {field: 'end', title: '结束时间', width: '12%', align: 'center',
                formatter:function (end) {
                    return end.substr(0,end.length-3);
                }
            },
            {field: 'createBy', title: '创建人', width: '10%', align: 'center' },
            {field: 'createDate', title: '创建时间', width: '15%', align: 'center'}

        ]],onLoadSuccess:function(data){
            $("#addPlan").css('display','block');
            $("#choosePerson").css('display','block');

        }
    });
    $("#planGrid").datagrid('getPager').pagination({
        pageList: [10, 20, 30, 40, 50], //可以设置每页记录条数的列表
        displayMsg: '当前显示从第{from}条到第{to}条，共{total}条记录',
        onSelectPage: function (pageNumber, pageSize) {
            var state1 = $('#planGrid').data('datagrid');
            var opts = state1.options;
            page=opts.pageNumber = pageNumber;
            rows=opts.pageSize = pageSize;
            searchData(page,rows);
            return;
        }
    });
    var searchData= function (page,rows){
        $("#planGrid").datagrid('reload',basePath + '/exam/planList?orgId=' + orgId + '&type=' + type + '&state=' + state+ '&page=' + page+ '&rows=' + rows+'&deptIds=' + deptIds);
        if(search){
            search=false;
            $("#planGrid").datagrid('getPager').pagination('select',1);

        }
    }
    loadtree();
    $("#type").combobox({     //加载考试类型
        url: basePath + '/dict/find-list-by-type?type=' + 'EXAM_TYPE',
        valueField: 'id',
        textField: 'value',
        value:'请选择',
        method: 'GET'

    });

    $("#exam").combogrid({     //加载考试
        panelWidth: 500,
        idField: 'examId',
        textField: 'examName',
        loadMsg: '数据正在加载',
        url: basePath + '/exam/examList?orgId=' + orgId + '&type=' + '' + '&state=1',
        mode: 'remote',
        method: 'GET',
        fitColumns: true,
        columns: [[
            {field: 'typeName', title: '试卷类型',align: 'center', width: 40},
            {field: 'examName', title: '试卷名称',align: 'center', width: 110},
            {field: 'time', title: '考试时长',align: 'center', width: 40}
        ]],onSelect:function(index, row){
            typeId=row.typeId;
            time=row.time;
        }
    });
    $("#state").combobox({     //加载考试类型
        url: basePath + '/dict/find-list-by-type?type=' + 'EXAM_PLAN_STATE',
        valueField: 'label',
        textField: 'value',
        value:'请选择',
        method: 'GET'

    });
    $("#searchBtn").on("click", function () {
        search=true;
        //获取计划类型ID
        type = $("#type").combobox('getValue');
        if(type=='请选择'){
            type='';
        }
        //获取状态
        state = $("#state").combobox('getValue');
        if(state=='请选择'){
            state='';
        }
        searchData(page,rows);
    });
    $("#clearBtn").on("click", function () {
        clearKey();
    });

    //配置窗口
    $("#addPlan").window({
        closed: true,
        modal: true,
        collapsible: true,
        minimizable: false,
        maximizable: true,
        resizable: true,
        onClose: function () {
        },
        onOpen: function () {

        }
    });
    //打开新增窗口
    $("#addBtn").on('click', function () {
        arrPerson=[];
        $("#planId").val('');
        $("#planForm").form('reset');
        $("#addPlan").window({title:"考试计划新增"});
        $("#addPlan").window('open');
    });

    //修改
    $("#editBtn").on("click", function () {
        //获取选择行
        var row=$('#planGrid').datagrid('getChecked');
        if(row.length==1){
            if(row[0].state=='1'){
                $("#planGrid").datagrid('clearChecked');
                $.messager.alert("提示", "考试计划已发布，不能修改！","info");
                return;
            }
            if(row[0].state=='3'){
                $("#planGrid").datagrid('clearChecked');
                $.messager.alert("提示", "考试计划已结束，不能修改！","info");
                return;
            }
            if(row[0].state=='2'){
                $("#planGrid").datagrid('clearChecked');
                $.messager.alert("提示", "考试计划待评分，不能修改！","info");
                return;
            }
            var id=row[0].planId;
            $.get("/service/exam/getPersonById?id=" + id, function (data) {
                arrPerson=[];
                arrPerson=data;
                $.each(arrPerson, function (index, item) {
                    var obj=getName(personObj,'userId',item.userId);
                    arrPerson[index].name=obj[0].name;
                    arrPerson[index].depName=obj[0].depName;
                });
                var value='';
                $.each( arrPerson, function(i, n){
                    if(i!=arrPerson.length-1){
                        value+=n.name+',';
                    }
                    else{
                        value+=n.name;
                    }
                })
                $("#userName").textbox('setValue',value);
                $("#personGrid").datagrid('loadData',arrPerson);
                //赋值
                $("#planId").val(row[0].planId);
                time=row[0].time;
                $("#exam").combogrid('setValue',row[0].examId);
                $("#planName").textbox('setValue',row[0].planName);
                $("#start").datetimebox('setValue',row[0].start);
                $("#end").datetimebox('setValue',row[0].end);
                $("#addPlan").window({title:"考试计划修改"});
                typeId=row[0].typeId;
                $("#addPlan").window('open');
                $("#info").val(row[0].info);
                $("#limitStart").textbox('setValue',row[0].limitStart);
                $("#limitSubmit").textbox('setValue',row[0].limitSubmit);
            });

        }
        else{
            $("#planGrid").datagrid('clearChecked');
            $.messager.alert("提示", "请选择一个计划！","info");
        }
    });

    //发布
    $("#okBtn").on("click", function () {
        var row=$('#planGrid').datagrid('getSelected');
        if(row){
            if(row.auth=='0'){
                $("#planGrid").datagrid('clearChecked');
                $.messager.alert("提示", "该考试计划未选择考生！","info");
                return;
            }
            if(row.state=='1'){
                $("#planGrid").datagrid('clearChecked');
                $.messager.alert("提示", "考试计划已发布，不能重复发布！","info");
                return;
            }
            if(row.state=='3'){
                $("#planGrid").datagrid('clearChecked');
                $.messager.alert("提示", "考试计划已结束，不能发布！","info");
                return;
            }
            if(row.state=='2'){
                $("#planGrid").datagrid('clearChecked');
                $.messager.alert("提示", "考试计划待评分，不能发布！","info");
                return;
            }
            $.messager.confirm("提示", "计划发布后不能修改，确认发布选中的计划吗?", function (r) {
                if (r) {
                    var plan = {};
                    plan.planId = row.planId;
                    plan.orgId = orgId;
                    plan.examId = row.examId;
                    plan.updateBy = parent.config.persion_Id;
                    $.postJSON(basePath + "/exam/planPublish", JSON.stringify(plan), function (data) {
                        if(data.data=="success"){
                            $("#planGrid").datagrid('reload');
                            $("#planGrid").datagrid('clearChecked');
                        }
                        else{
                            $("#planGrid").datagrid('clearChecked');
                            $.messager.alert("提示", "发布失败");
                        }
                    });
                }
            })
        }
        else{
            $("#planGrid").datagrid('clearChecked');
            $.messager.alert("提示", "请选择一个计划！","info");
        }
    });
    //删除
    $("#delBtn").on("click", function () {
        var row=$('#planGrid').datagrid('getSelected');
        if(row){
            if(row.state=='1'){
                $("#planGrid").datagrid('clearChecked');
                $.messager.alert("提示", "考试计划已发布，不能删除！","info");
                return;
            }
            if(row.state=='3'){
                $("#planGrid").datagrid('clearChecked');
                $.messager.alert("提示", "考试计划已结束，不能删除！","info");
                return;
            }
            if(row.state=='2'){
                $("#planGrid").datagrid('clearChecked');
                $.messager.alert("提示", "考试计划待评分，不能删除！","info");
                return;
            }
            $.messager.confirm("提示", "确认删除选中的计划吗?", function (r) {
                if (r) {
                    var plan = {};
                    plan.planId = row.planId;
                    plan.orgId = orgId;
                    plan.examId = row.examId;
                    plan.updateBy = parent.config.persion_Id;
                    $.postJSON(basePath + "/exam/planRemove", JSON.stringify(plan), function (data) {
                        if(data.data=="success"){
                            $("#planGrid").datagrid('reload');
                            $("#planGrid").datagrid('clearChecked');
                        }
                        else{
                            $("#planGrid").datagrid('clearChecked');
                            $.messager.alert("提示", "发布失败");
                        }
                    });
                }
            })
        }
        else{
            $("#planGrid").datagrid('clearChecked');
            $.messager.alert("提示", "请选择一个计划！","info");
        }
    });

});

//保存试题
$("#saveBtn").on('click', function () {

    //获取计划名称
    var planName=$("#planName").val();
    if(planName==''||$("#planName").textbox("getValue").indexOf(" ") >=0){
        $.messager.alert('提示', '请填写计划名称！', 'info');
        return;
    }
    if(getRealLen(planName)>100){
        $.messager.alert("提示","计划名称输入过长！",'info');
        return;
    }
    //获取试题ID
    var examId=$("#exam").combobox('getValue');
    if(examId==null||examId==''){
        $.messager.alert('提示', '请选择试题！', 'info');
        return;
    }
    //获取考试开始时间
    var start=$("#start").datetimebox('getValue');

    if(start==''){
        $.messager.alert('提示', '请选择考试开始时间！', 'info');
        return;
    }
    //获取考试结束时间
    var end=$("#end").datetimebox('getValue');

    if(end==''){
        $.messager.alert('提示', '请选择考试结束时间！', 'info');
        return;
    }
    var date1 = start.replace(/-/g,"\/");
    var date2 = end.replace(/-/g,"\/");
    var start = new Date(date1);
    var end = new Date(date2);
    if (start > end) {
        $.messager.alert("提示", "结束时间不能早于开始时间！","info");
        return
    }
    var date3=end.getTime()-start.getTime(); //时间差秒
    var minute=date3/(60*1000);
    if(minute<time){
        $.messager.alert("提示", "时间范围小于考试时长！","info");
        return
    }
    //获取开始考试截止时间
    var limitStart=$("#limitStart").numberbox('getValue');

    if(limitStart==''){
        $.messager.alert('提示', '请填写考试截止时间！', 'info');
        return;
    }
    if(limitStart.lenth>5){
        $.messager.alert('提示', '考试截止时间输入过长！', 'info');
        return;
    }
    if(parseInt(limitStart)>time){
        $.messager.alert("提示", "考试截止时间不能小于考试时长！","info");
        return
    }
    //获取考试提交时间
    var limitSubmit=$("#limitSubmit").numberbox('getValue');

    if(limitSubmit==''){
        $.messager.alert('提示', '请填写考试提交时间！', 'info');
        return;
    }
    if(limitSubmit.lenth>5){
        $.messager.alert('提示', '考试提交时间输入过长！', 'info');
        return;
    }
    if(parseInt(limitSubmit)>time){
        $.messager.alert("提示", "考试提交时间不能小于考试时长！","info");
        return
    }
    //获取试卷说明
    var info=$("#info").val();
    if(info==''){
        $.messager.alert('提示', '请填写考试说明！', 'info');
        return;
    }
    if(getRealLen(info)>1000){
        $.messager.alert("提示","试说明输入过长！",'info');
        return;
    }
    //获取是否授权
    var userName=$("#userName").val();
    if(userName==''){
        $.messager.alert('提示', '请选择授权人员！', 'info');
        return;
    }
    var plan = {};
    plan.planId = $("#planId").val();
    plan.planName = planName;
    plan.orgId = orgId;
    plan.typeId = typeId;
    plan.examId = examId;
    plan.info = info;
    plan.createBy = parent.config.persion_Id;
    plan.limitStart = limitStart;
    plan.limitSubmit = limitSubmit;
    plan.start = start;
    plan.end = end;
    plan.personVos = arrPerson;
    $.postJSON(basePath + "/exam/planMerge", JSON.stringify(plan), function (data) {
        if (data.data == "success") {
            if(data.code=="hasName"){
                $.messager.alert('提示', '考试名称已存在！', 'info');
            }
            if(data.code=="success") {
                $("#addPlan").window('close');
                $("#planGrid").datagrid('reload');
                $("#planForm").form('reset');
                $("#planGrid").datagrid('clearChecked');
            }

        }
    }, function (data) {
        $("#planGrid").datagrid('clearChecked');
        $.messager.alert('提示', '保存失败', 'info');
    });
    $(".calendar-nextmonth").on('click', function () {
        $("#planForm").form('reset');
        $("#addPlan").window('close');
    });

});
//取消
$("#cancelBtn").on('click', function () {
    $("#planForm").form('reset');
    $("#addPlan").window('close');
});
//清空查询条件
function clearKey() {
    //清空题型ID
    $("#type").combobox('clear');//获取表格对象
    $("#type").combobox('setValue','请选择');//获取表格对象
    type = '';
    //清空状态
    $("#state").combobox('setValue','请选择');
    state = '';
    page='1';

}
