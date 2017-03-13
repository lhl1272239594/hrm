
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
var month='';
var orgCount = 0;
$(function () {
    //考评类型
    $('#searchType').combobox({
        url: basePath + '/templet/evaluationType?orgId='+orgId,
        valueField: 'id',
        textField: 'typeName',
        value: '请选择',
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
    $("#gradeGrid").datagrid({
        toolbar: '#tb',
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
        url: basePath + '/evaluationQuery/myEvaluation?orgId=' + orgId +'&month='+month+'&name='+name+'&typeId='+typeId,
        idField: 'id',
        singleSelect: true,//是否单选
        rownumbers: true,//行号
        columns: [[
            {field: 'userId', title: '考评对象', width: '10%', align: 'center',
                formatter: function (value, row, index) {
                    if(row.obj==1){
                        return row.deptName;
                    }
                    if(row.obj==2){
                        return row.userName;
                    }
                }
            },
            {field: 'planName', title: '考评标题', width: '20%', align: 'center'},
            {field: 'typeName', title: '考评类型', width: '10%', align: 'center'},
            {field: 'totalScore', title: '总分', width: '10%', align: 'center'},
            {field: 'score', title: '得分', width: '10%', align: 'center'},
            {field: 'startDate', title: '开始时间', width: '15%', align: 'center',
                formatter:function (startDate) {
                    return formatDatebox(startDate);
                }
            },
            {field: 'endDate', title: '结束时间', width: '15%', align: 'center',
                formatter:function (endDate) {
                    return formatDatebox(endDate);
                }
            },
            {field: 'h', title: '操作', width: '10%', align: 'center',
                formatter: function (value, row, index) {
                    var html='<span class="btn-border"><a href="#" name="btn-tip" style="padding: 0 3px;"  class="easyui-linkbutton" iconCls="icon-tip"  onclick="detail(\''+index+'\')">评分详情</a></span>';
                    return html;
                }
            }

        ]],onLoadSuccess:function(data){
            $("#gradeByEachGrid").css('display','block');
            $("#grade_tb").css('display','block');
            icon_init();
            $('#gradeGrid').datagrid('fixRowHeight');

        }
    });
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
    $("#searchBtn").on("click", function () {
        search=true;
        //获取计划名称
        name = $("#searchName").textbox('getValue');
        //获取考评类型
        typeId = $("#searchType").combobox('getValue');
        if(typeId=='请选择'){
            typeId='';
        }
        //获取月份
        month = $("#month1").val();
        if(month==null||month==''||month.length==0){
            month='';
        }
        searchData(page,rows);
    });

    var searchData= function (page,rows){
        $("#gradeGrid").datagrid('reload', basePath + '/evaluationQuery/myEvaluation?orgId=' + orgId +'&month='+month+ '&page=' + page+ '&rows=' + rows+'&name='+name+'&typeId='+typeId);
        if(search){
            search=false;
            $("#gradeGrid").datagrid('getPager').pagination('select',1);

        }
    }
    //查询条件：月份

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
                toolbar: '#grade_tb',
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
                    {title: '得分', field: 'resultScore', width: '10%', align: 'center'},
                    {field: 'grade', title: '评分人', width: '10%', align: 'center'}
                ]], onLoadSuccess: function (data) {
                    mergeCellsByField("gradeByEachGrid", "pname,sname");
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
                    {field: 'score', title: '得分', width: '58%', align: 'center',
                        formatter: function (score) {
                            if(score.charAt(0)=='.'){
                                return '0'+score;
                            }
                            else {
                                return score;
                            }
                        }}
                ]],onClickRow:function(index, row){
                    if(row.id!=tid){
                        datagrid_reload(row.id);
                        tid=row.id;
                        $.get(basePath + '/evaluation/getScoreById?tid=' + tid, function (data) {
                            if(data){
                                initScore(data.totalScore,data.score);
                            }
                        });
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
            if(data.es){
                if(data.es.length>0){
                    first=true;
                    templetId=row.templetId;
                    planId=row.id;
                    tid=data.es[0].id;
                    objArray=[];
                    gradePage='1';
                    gradeRows='30';
                    for(var i=0;i<data.es.length;i++){
                        objArray.push(data.es[i]);
                    }
                    $("#grade").window('open');
                    $.get(basePath + '/evaluation/getScoreById?tid=' + tid, function (data) {
                        if(data){
                            initScore(data.totalScore,data.score);
                        }
                    });
                }
                else {
                    $.messager.alert("系统提示", "没有对该对象的评分");
                    return;
                }
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
}//格式化时间：时分秒
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
    $("#searchType").combobox('setValue','请选择');//获取表格对象
    typeId = '';
    //清空月份
    $("#month").datebox('clear');
    $("#month1").val('');
    month = '';
    page='1';
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
function initScore(totalScore,score) {
    $("#totalScore").html(totalScore+'分');
    $("#tempScore").html(score+'分');

}