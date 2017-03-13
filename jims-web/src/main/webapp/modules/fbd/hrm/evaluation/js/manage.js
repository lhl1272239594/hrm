
var basePath = "/service";
var orgId= parent.config.org_Id;
var name='';
var typeId='';
var state='';
var scoreState='';
var page='1';
var rows='30';
var gradePage='1';
var gradeRows='30';
var templetId;//考评模板ID
var planId;//考评计划ID
var typeObj = [];
var tid;//考评头表ID
var orgCount = 0;
var deptIds=parent.orgids;
var beforeChange;//修改前的评分数值
var index=0;//当前选中行索引
var editIndex = undefined; //定义全局变量：当前编辑的行
var objArray=[];
var search=false;
var change=false;//选择考评对象
var type='';//（1科室自评，2考评科室，3普通）
var rowData={};
$(function () {
    //考评类型
    $('#searchType').combobox({
        url: basePath + '/templet/evaluationType?orgId='+orgId,
        valueField: 'id',
        textField: 'typeName',
        value:'请选择',
        method: 'GET',
        onLoadSuccess:function (data) {
            $.each(data, function (index, item) {
                typeObj[item.id]=item.typeName;
            });
            orgCount = data.length;
        },
        onShowPanel: function() {
            // 动态调整高度
            if (orgCount > 13) {
                $(this).combobox('panel').height(285);
            }
        }
    });
    $("#state").combobox({     //加载考试类型
        url: basePath + '/dict/find-list-by-type?type=' + 'EVALUATION_STATE',
        valueField: 'label',
        textField: 'value',
        value:'请选择',
        method: 'GET'

    });
    $("#planGrid").datagrid({
        toolbar: '#tb',
        iconCls: 'icon-edit',//图标
        width: 'auto',
        height: '99%',
        nowrap: false,
        striped: true,
        border: true,
        method: 'get',
        loadMsg: '数据正在加载中，请稍后.....',
        pagination: true,//分页控件
        pageSize: 30,
        pageList: [10, 15, 30, 50],//可以设置每页记录条数的列表
        collapsible: false,//是否可折叠的
        fit: true,//自动大小
        url: basePath + '/manage/planList?name=' + name + '&orgId=' + orgId + '&typeId=' + typeId +'&state='+state+'&deptIds=' + deptIds,
        remoteSort: false,
        idField: 'id',
        singleSelect: true,//是否单选
        rownumbers: true,//行号
        columns: [[
            {field: 'typeName', title: '考评类型', width: '10%', align: 'center'},
            {field: 'name', title: '考评标题', width: '15%', align: 'center'},
            {field: 'self', title: '是否自评', width: '6%', align: 'center',
                formatter:function (self) {
                    if(self=='0'){
                        return '否';
                    }
                    if(self=='1'){
                        return '是';
                    }
                }
            },
            {field: 'state', title: '状态', width: '6%', align: 'center',
                formatter:function (state) {
                    if(state=='0'){
                        return '未开始';
                    }
                    if(state=='1'){
                        return '进行中';
                    }
                    if(state=='2'){
                        return '已结束';
                    }
                    if(state=='3'){
                        return '已汇总';
                    }
                    if(state=='4'){
                        return '已发布';
                    }
                }
            },
            {field: 'startType', title: '启动类型', width: '6%', align: 'center',
                formatter: function (value, row, index) {
                    if (value == "1") {
                        return "手动";
                    }
                    if (value == "2") {
                        return "自动";
                    }
                }
            },
            {field: 'submit', title: '评分进度', width: '10%', align: 'center',
                formatter:function (value,row) {
                    return row.submit+'/'+row.unsubmit;
                }
            },
            {field: 'startDate', title: '开始时间', width: '10%', align: 'center',
                formatter:function (startDate) {
                    return formatDatebox(startDate);
                }
            },
            {field: 'endDate', title: '结束时间', width: '10%', align: 'center',
                formatter:function (endDate) {
                    return formatDatebox(endDate);
                }
            },
            {field: 'h', title: '操作', width: '27%', align: 'center',
                formatter: function (value, row, index) {
                    var html='';
                    if(row.state=='2'||row.state=='1'){
                        html+= '' +
                            '<span class="btn-border"><a href="#" name="btn-save" style="padding: 0 3px;"  class="easyui-linkbutton" iconCls="icon-save"  onclick="value(\''+index+'\')">汇总</a></span>' +
                            '<span class="btn-border"><a href="#" name="btn-save" style="padding: 0 3px;"  class="easyui-linkbutton" iconCls="icon-remove"  onclick="del(\''+index+'\')">删除</a></span>';
                    }
                    if(row.state=='3'){
                        html+= '<span class="btn-border"><a href="#" name="btn-tip" style="padding: 0 3px;"  class="easyui-linkbutton" iconCls="icon-tip"  onclick="info(\''+index+'\')">查看</a></span>' +
                            '<span class="btn-border"><a href="#" name="btn-save" style="padding: 0 3px;"  class="easyui-linkbutton" iconCls="icon-save"  onclick="value(\''+index+'\')">汇总</a></span>'+
                        '<span class="btn-border"><a href="#" name="btn-ok" style="padding: 0 3px;"  class="easyui-linkbutton" iconCls="icon-ok"  onclick="publish(\''+index+'\')">发布</a></span>' +
                            '<span class="btn-border"><a href="#" name="btn-save" style="padding: 0 3px;"  class="easyui-linkbutton" iconCls="icon-remove"  onclick="del(\''+index+'\')">删除</a></span>';
                    }
                    if(row.state=='4'){
                        html+= '<span class="btn-border"><a href="#" name="btn-tip" style="padding: 0 3px;"  class="easyui-linkbutton" iconCls="icon-tip"  onclick="info(\''+index+'\')">查看</a></span>';
                    }
                    return html;
                }}

        ]],onLoadSuccess:function(data){
            $("#grade").css('display','block');
            $("#grade_tb").css('display','none');
            icon_init();
            $('#planGrid').datagrid('fixRowHeight');

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
    $("#searchBtn").on("click", function () {
        search=true;
        //获取计划名称
        name = $("#searchName").textbox('getValue');
        //获取考评类型
        typeId = $("#searchType").combobox('getValue');
        if(typeId=='请选择'){
            typeId='';
        }
        //获取状态
        state = $("#state").combobox('getValue');
        if(state=='请选择'){
            state='';
        }
        searchData(page,rows);
    });

    var searchData= function (page,rows){
        $("#planGrid").datagrid('reload', basePath + '/manage/planList?name=' + name + '&orgId=' + orgId + '&typeId=' + typeId +'&state='+state+ '&page=' + page+ '&rows=' + rows+'&deptIds=' + deptIds);
        if(search){
            search=false;
            $("#planGrid").datagrid('getPager').pagination('select',1);

        }
    }

    $("#clearBtn").on("click", function () {
        clearKey();
    });

    //考评成绩窗口
    $("#grade").window({
        fit:true,
        title:'考评成绩',
        closed: true,
        modal: true,
        onClose: function () {
        },
        onOpen: function () {
            $("#gradeGrid").datagrid({
                iconCls: 'icon-edit',//图标
                width: 'auto',
                height: '99%',
                nowrap: false,
                striped: true,
                border: true,
                method: 'get',
                loadMsg: '数据正在加载中，请稍后.....',
                pagination: false,//分页控件
                collapsible: false,//是否可折叠的
                fit: true,//自动大小
                remoteSort: false,
                idField: 'id',
                singleSelect: true,//是否单选
                rownumbers: true,//行号
                columns: [[
                    {field: 'userId', title: '考评对象', width: '20%', align: 'center',
                        formatter: function (value, row, index) {
                            if(row.obj==1){
                                return row.deptName;
                            }
                            if(row.obj==2){
                                return row.userName;
                            }
                            if(row.obj==4){
                                return row.deptName;
                            }
                        }
                    },
                    {field: 'planName', title: '考评标题', width: '30%', align: 'center'},
                    {field: 'typeName', title: '考评类型', width: '10%', align: 'center'},
                    {field: 'totalScore', title: '总分', width: '10%', align: 'center'},
                    {field: 'score', title: '得分', width: '10%', align: 'center',
                        formatter: function (value, row, index) {
                            if(type=='1'){
                                if(row.state=='0'||row.state=='1'){
                                    return '未自评';
                                }
                                else {
                                    if(value){
                                        if(value.charAt(0)=='.'){
                                            return '0'+value;
                                        }
                                        else {
                                            return value;
                                        }
                                    }
                                }
                            }
                            else {
                                if(value){
                                    if(value.charAt(0)=='.'){
                                        return '0'+value;
                                    }
                                    else {
                                        return value;
                                    }
                                }
                            }
                        }
                    },
                    {field: 'h', title: '操作', width: '20%', align: 'center',
                        formatter: function (value, row, index) {
                            if(type=='1'){
                                if(row.state=='0'||row.state=='1'){
                                    return '';
                                }
                                else {
                                    var html='<span class="btn-border"><a href="#" name="btn-tip" style="padding: 0 3px;"  class="easyui-linkbutton" iconCls="icon-tip"  onclick="detail(\''+index+'\')">评分详情</a></span>';
                                    return html;
                                }
                            }
                            else {
                                var html='<span class="btn-border"><a href="#" name="btn-tip" style="padding: 0 3px;"  class="easyui-linkbutton" iconCls="icon-tip"  onclick="detail(\''+index+'\')">评分详情</a></span>';
                                return html;
                            }
                        }
                    }

                ]],onLoadSuccess:function(data){
                    $("#gradeByEachGrid").css('display','block');
                    $("#grade_tb").css('display','block');
                    icon_init();
                    $('#gradeGrid').datagrid('fixRowHeight');
                }
            });
        }
    });
    //评分明细窗口
    $("#gradeByEach").window({
        fit: true,
        title: '评分明细',
        closed: true,
        modal: true,
        onClose: function () {
            first=false;
        },
        onOpen: function () {
            $("#gradeByEachGrid").datagrid({
                toolbar: '#grade_tb',
                iconCls: 'icon-edit',//图标
                width: '100%',
                height: '100%',
                nowrap: false,
                striped: true,
                border: true,
                onClickCell: onClickCell,
                onAfterEdit:onAfterEdit,
                method: 'get',
                loadMsg: '数据正在加载中，请稍后.....',
                pagination: true,//分页控件
                pageSize: 30,
                collapsible: false,//是否可折叠的
                fit: true,//自动大小
                remoteSort: false,
                idField: 'id',
                singleSelect: true,//是否单选
                rownumbers: true,//行号
                columns: [[
                    {field: 'pname', title: '一级项目名称', width: '10%', align: 'center'},
                    {field: 'sname', title: '二级项目名称', width: '10%', align: 'center'},
                    {field: 'name', title: '考评标准名称', width: '39%', align: 'center'},
                    {field: 'score', title: '考评分值', width: '8%', align: 'center'},
                    {title: '得分', field: 'resultScore', width: '8%', align: 'center',
                        editor: {
                            type: 'numberbox',
                            options:{
                                precision:1,
                                min:0
                            }
                        }
                    },{
                        field: 'kpi', title: '是否KPI', width: '5%', align: 'center',
                        formatter: function (value, row, index) {
                            if (value == "1") {
                                return "是";
                            }
                            if (value == "0") {
                                return "否";
                            }
                        }
                    },
                    {field: 'grade', title: '评分人', width: '10%', align: 'center'},
                    {field: 'updateBy', title: '修改人', width: '10%', align: 'center'}
                ]], onLoadSuccess: function (data) {
                    mergeCellsByField("gradeByEachGrid", "pname,sname");
                    $.get(basePath + '/evaluation/getScoreById?tid=' + tid +'&state='+scoreState, function (data) {
                        if(data){
                            initScore(data.totalScore,data.score);
                        }
                    });
                }
            });
            $("#gradeByEachGrid").datagrid('getPager').pagination({
                pageList: [10, 20, 30, 40, 50], //可以设置每页记录条数的列表
                displayMsg: '当前显示从第{from}条到第{to}条，共{total}条记录',
                onSelectPage: function (pageNumber, pageSize) {
                    var state1 = $('#gradeByEachGrid').data('datagrid');
                    var opts = state1.options;
                    gradePage=opts.pageNumber = pageNumber;
                    gradeRows=opts.pageSize = pageSize;
                    datagrid_reload(tid);
                    return;
                }
            });
            datagrid_reload(tid);
            $("#objGrid").datagrid({
                width: 'auto',
                height: '99%',
                nowrap: false,
                striped: true,
                border: true,
                method: 'get',
                loadMsg: '数据正在加载中，请稍后.....',
                pagination: false,//分页控件
                collapsible: false,//是否可折叠的
                fit: true,//自动大小
                //url: basePath + '/evaluation/getObj?templetId=' + templetId+'&planId='+planId,
                remoteSort: false,
                idField: 'id',
                singleSelect: true,//是否单选
                rownumbers: true,//行号
                columns: [[
                    {field: 'gradeByName', title: '评分人员', width: '50%', align: 'center'},
                    {field: 'score', title: '得分', width: '58%', align: 'center',
                        formatter: function (value, row, index) {
                            if(row.state=='2'){
                                if(row.score){
                                    if(row.score.charAt(0)=='.'){
                                        return '0'+row.score;
                                    }
                                    else {
                                        return row.score;
                                    }
                                }
                            }
                            else {
                                return '未提交';
                            }
                        }
                    }
                ]],onClickRow:function(i, row){
                    first=false;
                    tid=row.id;
                    index=i;
                    change=true;
                    datagrid_reload(row.id);
                }
                ,onLoadSuccess:function (data) {
                    if(first){
                        $("#objGrid").datagrid('selectRow',0);
                    }
                    else {
                        $("#objGrid").datagrid('selectRow',index);
                    }
                }
            });
            $("#objGrid").datagrid('loadData', objArray);
        }
    });
    $("#gradeBackBtn").on("click", function () {
        $("#grade").window('close');
        $("#planGrid").datagrid('reload');
    });
    $("#gradeEachBackBtn").on("click", function () {
        $("#gradeByEach").window('close');
        $("#planGrid").datagrid('reload');
    });
});
//成绩汇总
function value(index) {
    var rows=$("#planGrid").datagrid('getRows');
    var row=rows[index];
    row.endDate='';
    row.startDate='';
    row.type=row.type;
    $.messager.progress({
        title: '提示！',
        msg:  '数据量较大，请稍候...',
        text: '加载中.......'
    });
    $.postJSON(basePath + "/manage/summaryData", JSON.stringify(row), function (data) {
        $.messager.progress('close')
        if(data.data=="success"){
            $("#planGrid").datagrid('reload');
        }
        else{
            $.messager.alert("提示", "保存失败",'info');
        }
    });
}
//发布考评
function publish(index) {
    $.messager.confirm("提示", "确认发布考评成绩？", function (r) {
        if (r) {
            var rows=$("#planGrid").datagrid('getRows');
            var row=rows[index];
            row.endDate='';
            row.startDate='';
            $.messager.progress({
                title: '提示！',
                msg:  '数据量较大，请稍候...',
                text: '加载中.......'
            });
            $.postJSON(basePath + "/manage/publish", JSON.stringify(row), function (data) {
                $.messager.progress('close')
                if(data.data=="success"){
                    $("#planGrid").datagrid('reload');
                }
                else{
                    $.messager.alert("提示", "保存失败",'info');
                }
            });
        }
    })
}
//查看成绩
function info(index) {
    var rows=$("#planGrid").datagrid('getRows');
    var row=rows[index];
    var self=row.self;
    var obj=row.obj;
    var planId=row.id;
    var state=row.state;
    scoreState=state;
    $.get(basePath + '/manage/getScore?planId='+planId+'&obj='+obj+'&state='+state, function (data) {
        $("#grade").window('open');
        type=row.type;
        $("#gradeGrid").datagrid('loadData', data);
    });
}
//查看评分详情
function detail(index) {
    var rows=$("#gradeGrid").datagrid('getRows');
    rowData={};
    rowData=rows[index];
    obj=rowData.obj;
    rowData.state=scoreState;
    rowData.submitDate='';
    $.postJSON(basePath + "/manage/getScoreList", JSON.stringify(rowData), function (data) {
        if(data.data=="success"){
            if(data.es){
                if(data.es.length>0){
                    objArray=[];
                    first=true;
                    templetId=rowData.templetId;
                    planId=rowData.id;
                    tid=data.es[0].id;
                    gradePage='1';
                    gradeRows='30';
                    for(var i=0;i<data.es.length;i++){
                        objArray.push(data.es[i]);
                    }
                    if(type=='1'||type=='2'){
                        $("#left").hide();
                        $("#right").css("width","0px");
                        $("#right").css("width","100%");
                    }
                    if(type=='3'){
                        $("#left").show();
                        $("#right").css("width","20%");
                        $("#right").css("width","80%");
                    }
                    $("#gradeByEach").window('open');
                }
                else {
                    $.messager.alert("系统提示", "没有对该对象的评分");
                    return;
                }
            }
            else {
                $.messager.alert("提示", "没有对该对象的评分",'info');
                return;
            }
        }
        else{
            $.messager.alert("提示", "失败",'info');
        }
    });
}
//删除考评计划
function del(index) {
    var rows=$("#planGrid").datagrid('getRows');
    var row=rows[index];
    row.endDate='';
    row.startDate='';
    row.type=row.type;
    $.messager.confirm("提示", "确认删除选中的计划吗?", function (r) {
        if (r) {
            $.postJSON(basePath + "/manage/del", JSON.stringify(row), function (data) {
                if(data.data=="success"){
                    $("#planGrid").datagrid('reload');
                }
                else{
                    $.messager.alert("提示", "删除失败",'info');
                }
            });
        }
    });
}
function combobox_init(type) {
    var tf;
    if(type==1){
        tf='deptName';
    }
    if(type==2){
        tf='userName';
    }
    $("#obj").combobox({     //加载考评对象
        valueField: 'gradeBy',
        textField: 'gradeByName',
        onSelect:function (data) {
            if(data.id!=tid){
                datagrid_reload(data.id);
                tid=data.id;
            }
        }
    });
}
function datagrid_reload(id) {
    $("#gradeByEachGrid").datagrid('reload', basePath + '/manage/getDetail?id='+id+'&state='+scoreState+ '&page=' + gradePage+ '&rows=' + gradeRows);
    if(change){
        change=false;
        $("#gradeByEachGrid").datagrid('getPager').pagination('select',1);
    }
}

//清空查询条件
function clearKey() {
    //清空模板名称
    $("#searchName").textbox('setValue','');
    name='';
    //清空考核类型
    $("#searchType").combobox('clear');
    $("#searchType").combobox('setValue','请选择');
    typeId = '';
    //清空状态
    $("#state").combobox('clear');
    $("#state").combobox('setValue','请选择');
    page='1';
    state = '';
}

//datagrid加载完后合并指定单元格
function mergeCellsByField(tableID, colList) {
    var ColArray = colList.split(",");
    var tTable = $("#" + tableID);
    var TableRowCnts = tTable.datagrid("getRows").length;
    var tmpA;
    var tmpB;
    var PerTxt = "";
    var CurTxt = "";
    var alertStr = "";
    for (var j=0;j <= ColArray.length - 1; j++) {
        PerTxt = "";
        tmpA = 1;
        tmpB = 0;

        for (i = 0; i <= TableRowCnts; i++) {
            if (i == TableRowCnts) {
                CurTxt = "";
            }
            else {
                CurTxt = tTable.datagrid("getRows")[i][ColArray[j]];
            }
            if (PerTxt == CurTxt) {
                tmpA += 1;
            }
            else {
                tmpB += tmpA;

                tTable.datagrid("mergeCells", {
                    index: i - tmpA,
                    field: ColArray[j],　　//合并字段
                    rowspan: tmpA,
                    colspan: null
                });
                tTable.datagrid("mergeCells", { //根据ColArray[j]进行合并
                    index: i - tmpA,
                    field: "Ideparture",
                    rowspan: tmpA,
                    colspan: null
                });

                tmpA = 1;
            }
            PerTxt = CurTxt;
        }
    }
}
//格式化时间：时分秒
function formatDatebox(value) {
    if (value == null || value == '') {
        return '';
    }
    var dt;
    if (value instanceof Date) {
        dt = value;
    } else {

        dt = new Date(value);

    }

    return dt.format("yyyy-MM-dd"); //扩展的Date的format方法(上述插件实现)
}
//按钮图标初始化
function icon_init() {
    $("a[name='btn-tip']").linkbutton({iconCls:'icon-tip'});
    $("a[name='btn-save']").linkbutton({iconCls:'icon-save'});
    $("a[name='btn-ok']").linkbutton({iconCls:'icon-ok'});
}
//指定列求和
function compute(name) {
    var rows = $('#gradeByEachGrid').datagrid('getRows');
    var total = 0;
    for (var i = 0; i < rows.length; i++) {
        if(!rows[i][name]){
            var score=0;
        }
        else{
            var score=rows[i][name];
        }
        total += parseFloat(score);
    }
    return total.toFixed(1);;
}
function endEditing() {//该方法用于关闭上一个焦点的editing状态
    if (editIndex == undefined) {
        return true
    }
    if ($('#gradeByEachGrid').datagrid('validateRow', editIndex)) {
        $('#gradeByEachGrid').datagrid('endEdit', editIndex);
        editIndex = undefined;
        return true;
    } else {
        return false;
    }
}
//点击单元格事件：
function onClickCell(index,field,value) {
    if(scoreState=='4')
        return;
    if (endEditing()) {
        if(field=="resultScore"){
            $(this).datagrid('beginEdit', index);
            var ed = $(this).datagrid('getEditor', {index:index,field:field});
            ed.target.focus();
        }
        editIndex = index;
    }
    $('#gradeByEachGrid').datagrid('onClickRow')
}
//单元格失去焦点执行的方法
function onAfterEdit(index, rowData, changes) {
    mergeCellsByField("gradeByEachGrid", "pname,sname");
    var updated = $('#gradeByEachGrid').datagrid('getChanges', 'updated');
    if (updated.length < 1) {
        editRow = undefined;
        $('#gradeByEachGrid').datagrid('unselectAll');
        return;
    } else {
        var row=[];
        row=rowData;
        mergeCellsByField("gradeByEachGrid", "pname,sname");
        var score=parseFloat(rowData.score);
        var resultScore=parseFloat(rowData.resultScore);
        if(resultScore>score){
            $.messager.alert("提示", "得分不能大于考评分值","info");
            datagrid_reload(tid);
            return;
        }
        if(!rowData.resultScore){
            return;
        }
        row.resultScore=resultScore;
        row.value=resultScore-beforeChange;
        row.fieldName='UPDATE_BY';
        row.type=type;
        row.tid=tid;
        $.postJSONAsync(basePath + "/evaluation/saveScore", JSON.stringify(row), function (data) {
            if(data.data=="success"){
                $.get(basePath + '/evaluation/getScoreById?tid=' + tid +'&state='+scoreState, function (data) {
                    if(data){
                        initScore(data.totalScore,data.score);
                    }
                });
            }
            else{
                $.messager.alert("提示", "保存失败","info");
            }
        });
        objgrid_reload();
        //datagrid_reload(tid);
        editRow = undefined;
    }
}
function initScore(totalScore,score) {
    $("#totalScore").html(totalScore+'分');
    $("#tempScore").html(score+'分');

}
function objgrid_reload() {
    $.postJSON(basePath + "/manage/getScoreList", JSON.stringify(rowData), function (data) {
        if (data.data == "success") {
            if (data.es.length > 0) {
                objArray = [];
                for (var i = 0; i < data.es.length; i++) {
                    objArray.push(data.es[i]);
                }
                $("#objGrid").datagrid('loadData', objArray);
            }
        }
    });
}