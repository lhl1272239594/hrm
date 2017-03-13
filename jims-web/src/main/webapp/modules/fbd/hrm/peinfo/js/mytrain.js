/*
 *培训计划管理
 * @author yangchen
 * @version 2016-08-18
 *!/*/
var basePath = "/service";

var dg;
var d;
var flag;
var editIndex=undefined;
var page='1';
var rows='30';
var trainNoticeVo = {};
var orgId = parent.config.org_Id;
var id='999';
var userId='';
var deptId='';
var userName='';
var value='';
var label='';
var obj1 = new  Object();
var depts = [];
var treeDepts = [];
var trainPlanId='';
var ssPlanId='';
var trainPlanTittle='';
var trainPlanContent='';
var change=false;
var trainPlanTittle;
var search=false;
var deptIds=parent.orgids;
var month='';
var type='';
var state='';
$(function () {
    //初始化富文本编辑器

    var editor;
    KindEditor.ready(function(K) {
        editor = K.create('textarea[name="editTrainContent"]', {
            readonlyMode : true,
            items : []
        });
    });
    loadtree();
    $.get(basePath + '/tool/find-list-by-type?type=TRAIN_TYPE_DICT&value='+value, function (data) {
        $.each(data, function (index, item) {
            obj1[item.value]=item.label;
        });
    });

    $("#type").combobox({
            idField: 'value',
            textField: 'label',
            value: '请选择',
            loadMsg: '数据正在加载',
            url: '/service/tool/find-list-by-type?type=TRAIN_TYPE_DICT',
            method: 'GET'
        }
    );
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
    $("#primaryGrid").datagrid({
        iconCls: 'icon-edit',//图标
        width: '100%',
        height: '100%',
        nowrap: false,
        striped: true,
        border: true,
        singleSelect:true,
        method: 'get',
        toolbar: '#tb',
        fitColumns:true,
        loadMsg: '数据正在加载中，请稍后.....',
        pagination: true,//分页控件
        pageSize: 30,
        collapsible: false,//是否可折叠的
        remoteSort: false,
        rownumbers: true,//行号
        url: basePath + '/trainNotice/mytrain?trainPlanTittle=' + trainPlanTittle + '&orgId=' + orgId +'&type='+type +'&month='+month ,
        columns: [[
            {field: 'orgId', title: '', width: '10%',hidden: true},
            {field: 'trainNoticeId', title: '', width: '10%',hidden: true},
            {field: 'name', title: '培训名称', width: '20%',align: 'center',},
            {field: 'trainPlanTittle', title: '培训主题', width: '20%',align: 'center',},
            {field: 'trainTeacher', title: '培训讲师', width: '10%', align: 'center',},
            {field: 'trainPlanType', title: '培训类型', width: '10%', align: 'center',
                formatter: function (trainPlanType) {
                    return obj1[trainPlanType];
                }
            },
            {field: 'trainPlace', title: '培训地点', width: '20%',align: 'center',},
            {field: 'trainDate', title: '培训时间', width: '20%',align: 'center',}
        ]],
        onLoadSuccess:function(){

        },
    });
    $("#primaryGrid").datagrid('getPager').pagination({
        pageList: [10, 20, 30, 40, 50], //可以设置每页记录条数的列表
        displayMsg: '当前显示从第{from}条到第{to}条，共{total}条记录',
        onSelectPage: function (pageNumber, pageSize) {
            var state = $('#primaryGrid').data('datagrid');
            var opts = state.options;
            page=opts.pageNumber = pageNumber;
            rows=opts.pageSize = pageSize;
            searchAllData(page,rows);
            return;
        }
    });

    //按条件查询(汇总)
    $("#searchAllBtn").on("click", function () {
        search = true;
        //获取培训类型
        type = $("#type").combobox('getValue');
        if(type=='请选择'){
            type='';
        }
        //获取月份
        month = $("#month1").val();
        if(month==null||month==''||month.length==0){
            month='';
        }
        trainPlanTittle=$("#trainPlanTittle").textbox('getValue');
        searchAllData(page,rows);

    });
    //按条件查询

    var searchAllData=function (page,rows) {

        $("#primaryGrid").datagrid('reload',  basePath + '/trainNotice/mytrain?trainPlanTittle=' + trainPlanTittle + '&orgId=' + orgId  +'&month='+month +'&type='+type + '&page=' + page+ '&rows=' + rows);
        if(search){
            search=false;
            $("#primaryGrid").datagrid('getPager').pagination('select', 1);
        }
    }

//配置窗口
    $("#infoWin").window({
        closed: true,
        modal: true,
        collapsible: true,
        minimizable: false,
        resizable: true,
        onClose: function () {
        },
        onOpen: function () {
        }
    });

//查看
    $("#viewBtn").on('click', function () {
        var row=$('#primaryGrid').datagrid('getSelected');
        if(row){
            $("#TrainPlace").textbox('setValue',row.trainPlace);
            $("#StartDate").textbox('setValue', row.startDate);
            $("#EndDate").textbox('setValue', row.endDate);
            $("#TrainPlanType").textbox('setText',obj1[row.trainPlanType]);
            $("#TrainTeacher").textbox('setValue',row.trainTeacher);
            $("#TrainPlan").textbox('setText',row.trainPlanTittle);
            $("#name1").textbox('setValue', row.name);
            $.get("/service/trainNotice/train-notice-to-person?trainNoticeId="+row.trainNoticeId+"&orgId="+orgId,
                function (data) {
                    var arrPerson=[];
                    arrPerson=data;
                    $.each(arrPerson, function (index, item) {
                        var obj=getName(personObj,'id',item.userId);
                        arrPerson[index].userId=obj[0].id;
                        arrPerson[index].userName=obj[0].name;
                        arrPerson[index].deptName=obj[0].deptName;
                    });
                    var editUserName='';
                    var editUserId='';
                    var editDeptId='';
                    $.each( arrPerson, function(i, n){
                        editUserName+=n.userName+',';
                        editUserId+=n.userId+',';
                        editDeptId+=n.deptId+',';
                    })

                    editUserName=editUserName.toString().substring(0,editUserName.length-1);
                    editUserId=editUserId.toString().substring(0,editUserId.length-1);
                    editDeptId=editDeptId.toString().substring(0,editDeptId.length-1);

                    $("#UserName").textbox('setValue',editUserName);
                });
            if(row.trainPlanContent!=null){
                //解码
                var varEdit= parent.utf8to16(parent.base64decode(row.trainPlanContent));
                editor.html(varEdit);
                editor.readonly;
            }
            $("#infoWin").window("setTitle","培训通知查看").window('open');
        }
        else{
            $.messager.alert('提示', '请选择一行记录！', 'info');

        }
        $("#saveBtn").hide();

    });
    $("#clearBtn").on('click', function () {
        $("#trainPlanTittle").textbox('setValue','');
        $("#type").combobox('setValue','请选择');
        type='';
        $("#state").combobox('setValue','请选择');
        state='';
        //清空月份
        $("#month").datebox('clear');
        $("#month1").val('');
        month = '';
        page='1';
    });
    //取消
    $("#cancelBtn").on('click', function () {
        $("#editWin").window('close');
    });
});

