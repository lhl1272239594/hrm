
var basePath = "/service";
var orgId= parent.config.org_Id;
var name='';
var typeId='';
var state='';
var scoreState='';
var templetId;//考评模板ID
var planId;//考评计划ID
var page='1';
var rows='30';
var gradePage='1';
var gradeRows='30';
var typeObj = [];
var tid;//考评头表ID
var objArray=[];
var first=false;//第一次考评
var search=false;
$(function () {
    //考评类型
    $('#searchType').combobox({
        url: basePath + '/templet/evaluationType?orgId='+orgId,
        valueField: 'id',
        textField: 'typeName',
        method: 'GET',
        onLoadSuccess:function (data) {
            $.each(data, function (index, item) {
                typeObj[item.id]=item.typeName;
            });
        }
    });
    $("#gradeGrid").datagrid({
        width: 'auto',
        height: '99%',
        nowrap: false,
        striped: true,
        border: true,
        method: 'get',
        pagination: true,//分页控件
        loadMsg: '数据正在加载中，请稍后.....',
        collapsible: false,//是否可折叠的
        fit: true,//自动大小
        remoteSort: false,
        url: basePath + '/evaluationQuery/myEvaluation?orgId=' + orgId,
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
                }
            },
            {field: 'planName', title: '考评标题', width: '30%', align: 'center'},
            {field: 'typeName', title: '考评类型', width: '10%', align: 'center'},
            {field: 'totalScore', title: '总分', width: '10%', align: 'center'},
            {field: 'score', title: '得分', width: '10%', align: 'center'},
            {field: 'h', title: '操作', width: '20%', align: 'center',
                formatter: function (value, row, index) {
                    var html='<span class="btn-border"><a href="#" name="btn-tip" style="padding: 0 3px;"  class="easyui-linkbutton" iconCls="icon-tip"  onclick="detail(\''+index+'\')">评分详情</a></span>';
                    return html;
                }}

        ]],onLoadSuccess:function(data){
            $("#gradeByEachGrid").css('display','block');
            $("#grade_tb").css('display','block');
            icon_init();
            $('#gradeGrid').datagrid('fixRowHeight');
            $("#gradeGrid").datagrid('getPager').pagination({
                pageList: [10, 20, 30, 40, 50], //可以设置每页记录条数的列表
                displayMsg: '当前显示从第{from}条到第{to}条，共{total}条记录',
                onSelectPage: function (pageNumber, pageSize) {
                    var state1 = $('#gradeGrid').data('datagrid');
                    var opts = state1.options;
                    page=opts.pageNumber = pageNumber;
                    rows=opts.pageSize = pageSize;
                    searchData(page,rows);
                    return;

                }

            });
        }
    });
    $("#searchBtn").on("click", function () {
        search=true;
        //获取计划名称
        name = $("#searchName").textbox('getValue');
        //获取考评类型
        typeId = $("#searchType").combobox('getValue');
        //获取状态
        state = $("#state").combobox('getValue');
        searchData(page,rows);
    });

    var searchData= function (page,rows){
        $.get(basePath + '/evaluationQuery/myEvaluation?orgId=' + orgId+ '&page=' + page+ '&rows=' + rows, function (data) {
            $("#gradeGrid").datagrid('loadData', data);
            if(search){
                search=false;
                $("#gradeGrid").datagrid('getPager').pagination('select',1);

            }
        });
    }

    $("#clearBtn").on("click", function () {
        clearKey();
    });

    //评分明细窗口
    $("#grade").window({
        fit: true,
        title: '评分明细',
        closed: true,
        modal: true,
        onClose: function () {
            first=false;
        },
        onOpen: function () {
            $("#gradeByEachGrid").datagrid({
                iconCls: 'icon-edit',//图标
                width: '100%',
                height: '100%',
                nowrap: false,
                striped: true,
                border: true,
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
                    {field: 'pname', title: '一级项目名称', width: '15%', align: 'center'},
                    {field: 'sname', title: '二级项目名称', width: '15%', align: 'center'},
                    {field: 'name', title: '考评标准名称', width: '50%', align: 'center'},
                    {field: 'score', title: '考评分值', width: '10%', align: 'center'},
                    {title: '得分', field: 'resultScore', width: '10%', align: 'center'}
                ]], onLoadSuccess: function (data) {
                    mergeCellsByField("gradeByEachGrid", "pname,sname");
                    $('#gradeByEachGrid').datagrid('appendRow', {
                        pname: '',
                        sname: '',
                        name: '',
                        score: '合计：',
                        resultScore: compute()
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
                    {field: 'gradeByName', title: '评分人员', width: '50%', align: 'center'
                    },
                    {field: 'score', title: '得分', width: '58%', align: 'center'}
                ]],onClickRow:function(index, row){
                    if(row.id!=tid){
                        datagrid_reload(row.id);
                        tid=row.id;
                    }
                }
                ,onLoadSuccess:function (data) {
                    if(first){
                        $("#objGrid").datagrid('selectRow',0);
                    }
                }
            });
            $("#objGrid").datagrid('loadData', objArray);
        }
    });
    $("#backBtn").on("click", function () {
        $("#grade").window('close');
        $("#gradeGrid").datagrid('reload');
    });
});

//查看评分详情
function detail(index) {
    var rows=$("#gradeGrid").datagrid('getRows');
    var row=rows[index];
    obj=row.obj;
    scoreState=4;
    row.submitDate='';
    row.state=4;
    $.postJSON(basePath + "/manage/getScoreList", JSON.stringify(row), function (data) {
        if(data.data=="success"){
            if(data.es.length>0){
                first=true;
                templetId=row.templetId;
                planId=row.id;
                tid=data.es[0].id;
                objArray=[];
                for(var i=0;i<data.es.length;i++){
                    objArray.push(data.es[i]);
                }
                $("#grade").window('open');

            }
            else {
                $.messager.alert("系统提示", "没有对该对象的评分");
                return;
            }
        }
        else{
            $.messager.alert("系统提示", "失败");
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
    $.get(basePath + '/manage/getDetail?id='+id+'&state='+scoreState+ '&page=' + gradePage+ '&rows=' + gradeRows, function (data) {
        $("#gradeByEachGrid").datagrid('loadData', data);
    });
}
//清空查询条件
function clearKey() {
    //清空模板名称
    $("#searchName").textbox('setValue','');
    name='';
    //清空考核类型
    $("#searchType").combobox('clear');
    typeId = '';
    //清空状态
    $("#state").combobox('clear');
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
//按钮图标初始化
function icon_init() {
    $("a[name='btn-tip']").linkbutton({iconCls:'icon-tip'});
    $("a[name='btn-save']").linkbutton({iconCls:'icon-save'});
    $("a[name='btn-ok']").linkbutton({iconCls:'icon-ok'});
}
//指定列求和
function compute() {
    var colName='resultScore';
    var rows = $('#gradeByEachGrid').datagrid('getRows');
    var total = 0;
    for (var i = 0; i < rows.length; i++) {
        if(!rows[i][colName]){
            var score=0;
        }
        else{
            var score=rows[i][colName];
        }
        total += parseFloat(score);
    }
    return total;
}