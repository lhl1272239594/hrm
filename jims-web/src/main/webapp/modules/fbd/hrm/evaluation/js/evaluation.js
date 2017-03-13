
var basePath = "/service";
var orgId= parent.config.org_Id;
var self=0;//是否自评（0否1是）
var obj=0;//1部门,2人员
var tid;//考评头表ID
var templetId;//考评模板ID
var beforeChange;//修改前的评分数值
var planId;//考评计划ID
var type='';//（1科室自评，2考评科室，3普通）
var first=false;//第一次考评
var search=false;
var index=0;//当前选中行索引
var editRow = undefined; //定义全局变量：当前编辑的行.
var editIndex = undefined;
var page='1';
var rows='30';
var gradePage='1';
var gradeRows='30';
$(function () {
    $("#gradeGrid").datagrid({
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
        pageList: [10,20, 30, 40,50],//可以设置每页记录条数的列表
        collapsible: false,//是否可折叠的
        fit: true,//自动大小
        url: basePath + '/evaluation/gradeList?orgId=' + orgId,
        remoteSort: false,
        idField: 'id',
        singleSelect: true,//是否单选
        rownumbers: true,//行号
        columns: [[
            {field: 'typeName', title: '考评类型', width: '10%', align: 'center'},
            {field: 'name', title: '考评标题', width: '30%', align: 'center'},
            {field: 'self', title: '是否自评', width: '10%', align: 'center',
                formatter:function (self) {
                    if(self=='0'){
                        return '否';
                    }
                    if(self=='1'){
                        return '是';
                    }
                }
            },
            {field: 'submit', title: '评分进度', width: '20%', align: 'center',
                formatter:function (value,row) {
                    return row.submit+'/'+row.unsubmit;
                }
            },
            {field: 'startDate', title: '开始时间', width: '15%', align: 'center',
                formatter:function (startDate) {
                    return formatDatebox(startDate);
                }},
            {field: 'endDate', title: '结束时间', width: '15%', align: 'center',
                formatter:function (endDate) {
                    return formatDatebox(endDate);
                }}

        ]],onLoadSuccess:function(data){
            $("#grade").css('display','block');
        }
    });

    //配置评分窗口
    $("#grade").window({
        fit:true,
        title:'在线评分',
        closed: true,
        modal: true,
        collapsible:false,
        minimizable:false,
        maximizable:false,
        closable:false,
        onClose: function () {
            $('#gradeGrid').datagrid('clearChecked');
            first=false;
            type='';
        },
        onOpen: function () {
            $("#gradeByEachGrid").datagrid({
                toolbar: '#grade_tb',
                iconCls: 'icon-edit',//图标
                width: '100%',
                height: '100%',
                nowrap:false,
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
                    {field: 'name', title: '考评标准名称', width: '40%', align: 'left',halign:'center'},
                    {field: 'score', title: '考评分值', width: '10%', align: 'center'},
                    {
                        title: '得分',
                        field: 'resultScore',
                        width: '10%',
                        align: 'center',
                        editor: {
                            type: 'numberbox',
                            options:{
                                precision:1,
                                min:0
                            }
                        }
                    },{
                        field: 'kpi', title: '是否KPI', width: '10%', align: 'center',
                        formatter: function (value, row, index) {
                            if (value == "1") {
                                return "是";
                            }
                            if (value == "0") {
                                return "否";
                            }
                        }
                    },{
                        field: 'grade', title: '评分人', width: '10%', align: 'center'
                    }
                ]],onLoadSuccess:function(data){
                    mergeCellsByField("gradeByEachGrid", "pname,sname");
                    $.get(basePath + '/evaluation/getScoreById?tid=' + tid, function (data) {
                        if(data){
                            initScore(data.totalScore,data.score);
                        }
                    });
                }/*,
                 onBeforeEdit:function(rowIndex, rowData){
                 if(!rowData.resultScore){
                 beforeChange=0;
                 }
                 else{
                 beforeChange=rowData.resultScore;
                 }
                 },
                 onAfterEdit:function(rowIndex, rowData, changes){
                 var row=[];
                 row=rowData;
                 $("#gradeByEachGrid").datagrid('endEdit', rowIndex);
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
                 row.fieldName='GRADE_BY';
                 row.type=type;
                 $.postJSONAsync(basePath + "/evaluation/saveScore", JSON.stringify(row), function (data) {
                 if(data.data=="success"){
                 var rownum=$('#gradeByEachGrid').datagrid('getRows').length-1;
                 $('#gradeByEachGrid').datagrid('deleteRow',rownum);
                 $('#gradeByEachGrid').datagrid('appendRow',{
                 pname: '合计：',
                 sname: '',
                 name: '',
                 score: compute('score'),
                 resultScore: compute('resultScore')
                 });
                 }
                 else{
                 $.messager.alert("提示", "保存失败","info");
                 }
                 });
                 //objgrid_reload(tid);
                 //datagrid_reload(tid);
                 editRow = undefined;
                 }*/
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
                    {field: 'typeName', title: '考评对象', width: '40%', align: 'center',
                        formatter:function (value,row,index) {
                            if(obj=="1"){
                                return row.deptName;
                            }
                            if(obj=="2"){
                                return row.userName;
                            }
                        }
                    },
                    {field: 'state', title: '状态', width: '30%', align: 'center',
                        formatter:function (state) {
                            if(state=='1'){
                                return '未提交';
                            }
                            if(state=='2'){
                                return '提交';
                            }
                        }
                    },
                    {field: 'num', title: '已评分/未评分', width: '30%', align: 'center',
                        formatter:function (value,row,index) {
                            if(type=='2'){
                                return row.hasGrade1+'/'+row.num1;
                            }
                            else{
                                return row.hasGrade+'/'+row.num;
                            }
                        }
                    }
                ]],onClickRow:function(i, row){
                        first=false;
                        tid=row.id;
                        index=i;
                        search=true;
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
            if(type=='2'){
                $("#objGrid").datagrid('hideColumn','state');
                var dg = $('#objGrid');
                var typeName = dg.datagrid('getColumnOption', 'typeName');
                var num = dg.datagrid('getColumnOption', 'num');
                typeName.width = '50%';
                num.width = '45%';
                dg.datagrid();
            }
            objgrid_reload(tid);

        }
    });
    $("#backBtn").on("click", function () {
        $("#grade").window('close');
        $("#gradeGrid").datagrid('reload');
    });
    $("#gradeBtn").on("click", function () {
        //获取选择行
        var row=$('#gradeGrid').datagrid('getChecked');
        if(row.length==1){
            obj=row[0].obj;
            var plan = {};
            plan.id = row[0].id;
            plan.templetId = row[0].templetId;
            plan.self = row[0].self;
            plan.orgId = orgId;
            plan.obj = obj;
            plan.type = row[0].type;
            $.messager.progress({
                title: '提示！',
                msg:  '数据量较大，请稍候...',
                text: '加载中.......'
            });
            $.postJSON(basePath + "/evaluation/startGrade", JSON.stringify(plan), function (data) {
                $.messager.progress('close')
                if(data.data=="success"){
                    first=true;
                    templetId=row[0].templetId;
                    planId=row[0].id;
                    tid=data.es[0].id;
                    type=row[0].type;
                    gradePage='1';
                    gradeRows='30';
                    if(type=='1'){

                    }
                    $("#grade").window('open');

                }
                else{
                    $.messager.alert("提示", "失败","info");
                }
            });
        }
        else{
            $.messager.alert("提示", "请选择一个考评","info");
        }
    });
    $("#submitBtn").on("click", function () {
        checkBeforeSubmit(1);
    });
    $("#submitAllBtn").on("click", function () {
        checkBeforeSubmit(2);
    });
});
function checkBeforeSubmit(lx) {
    if(lx==1){
        var id=tid;
    }
    if(lx==2){
        var id='all';
    }
    $.get(basePath + '/evaluation/checkBeforeSubmit?tid='+id+'&planId='+planId, function (data) {
        if(data.data=="success"){
            if(data.code=='none'){
                submitGrade(lx);
            }
            if(data.code=='one'){
                $.messager.alert("提示", "有未评分项目","info");
            }
            if(data.code=='all'){
                if(data.es.length>0){
                    $.messager.alert("提示", "还有考评对象未评分","info");
                }
            }
        }
        else{
            $.messager.alert("提示", "查询失败","info");
        }
    });
}
function confirm(text,lx) {
    $.messager.confirm("提示", text, function (r) {
        if (r) {
            submitGrade(lx);
        }
    })
}
function submitGrade(lx) {
    if(lx==1){
        var id=tid;
    }
    if(lx==2){
        var id='all';
    }
    $.messager.progress({
        title: '提示！',
        msg: '数据量较大，请稍候...',
        text: '加载中.......'
    });
    $.get(basePath + '/evaluation/submitGrade?tid='+id+'&planId='+planId, function (data) {
        $.messager.progress('close')
        if (data.data == "success") {
            objgrid_reload(tid);
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
        valueField: 'id',
        textField: tf,
        onSelect:function (data) {
            if(data.id!=tid){
                datagrid_reload(data.id);
                tid=data.id;
            }
        }
    });
}
function datagrid_reload(id) {
    $("#gradeByEachGrid").datagrid('reload', basePath + '/evaluation/getDetail?id='+id+'&type='+type+ '&page=' + gradePage+ '&rows=' + gradeRows);
    if(search){
        search=false;
        $("#gradeByEachGrid").datagrid('getPager').pagination('select',1);
    }
}
function objgrid_reload(id) {
    $.get(basePath + '/evaluation/getObj?templetId=' + templetId+'&planId='+planId+'&type='+type, function (data) {
        $("#objGrid").datagrid('loadData', data);
        if(type=='2'){
            $("#submitBtn").hide();
            $("#submitAllBtn").hide();
        }
        else {
            $("#submitBtn").show();
            $("#submitAllBtn").show();
        }
        if(data.length==1){
            $("#submitAllBtn").hide();
        }
        if(data.length>1&&type!='2'){
            $("#submitAllBtn").show();
        }
    });
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
/**
 * 格式化数字，小数点后是0不表示
 * @param value
 * @param fixed 小数位数
 */
function formatNumber(value, fixed) {
    var number = Number(value);
    if (isNaN(number)) {
        return '';
    } else {
        if (fixed == 2) {
            return number.toFixed(2) * 100 / 100;
        } else if (fixed == 6) {
            return number.toFixed(6) * 1000000 / 1000000;
        }
    }
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
        editIndex = undefined;
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
        row.fieldName='GRADE_BY';
        row.type=type;
        row.tid=tid;
        $.postJSONAsync(basePath + "/evaluation/saveScore", JSON.stringify(row), function (data) {
            if(data.data=="success"){
                $.get(basePath + '/evaluation/getScoreById?tid=' + tid, function (data) {
                    if(data){
                        initScore(data.totalScore,data.score);
                    }
                });
            }
            else{
                $.messager.alert("提示", "保存失败","info");
            }
        });
        objgrid_reload(tid);
        //datagrid_reload(tid);
        editIndex = undefined;
    }
}
function initScore(totalScore,score) {
    $("#totalScore").html(totalScore+'分');
    $("#tempScore").html(score+'分');

}