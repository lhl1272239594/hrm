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
var orgCount = 0;
var edit=false;
$(function () {
    //初始化富文本编辑器

    var editor;
    KindEditor.ready(function(K) {
        editor = K.create('textarea[name="editTrainContent2"]', {
            readonlyMode : true,
            items : []
        });
    });
    var editor1;
    KindEditor.ready(function(K) {
        editor1 = K.create('textarea[name="editTrainContent"]', {
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
    $("#state").combobox({
        idField: 'value',
        textField: 'label',
        value: '请选择',
        loadMsg: '数据正在加载',
        url: '/service/tool/find-list-by-type?type=TRAIN_NOTICE_STATE',
        method: 'GET'
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

    $("#editTrainPlan").combobox({
            idField: 'value',
            textField: 'label',
            loadMsg: '数据正在加载',
            mode: 'remote',
            method: 'GET',
            onLoadSuccess: function(data){
                orgCount = data.length;
            },
            onShowPanel: function() {
                // 动态调整高度
                if (orgCount > 13) {
                    $(this).combobox('panel').height(285);
                }
            },
            onSelect: function (record) {
                $("#editTrainTeacher").textbox('clear');
                editor.html('');
                $.get(basePath + '/trainPlan/train-plan-all-list?trainPlanId=' + record.value + '&orgId=' + orgId,
                    function (data) {
                        if(data.length>0){
                            $("#editTrainTeacher").textbox("setValue",data[0].trainTeacher);
                            if(data[0].trainPlanContent!=null){
                                //解码
                                var val = parent.utf8to16(parent.base64decode(data[0].trainPlanContent));
                                editor.html(val);
                                editor.readonly();
                            }
                        }

                    });
            },
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
        url: basePath + '/trainNotice/train-notice-list?trainPlanTittle=' + trainPlanTittle + '&orgId=' + orgId +'&state='+state +'&type='+type +'&month='+month+'&deptIds='+deptIds ,
        columns: [[
            {field: 'orgId', title: '', width: '10%',hidden: true},
            {field: 'trainNoticeId', title: '', width: '10%',hidden: true},
            {field: 'name', title: '培训名称', width: '20%',align: 'center',},
            {field: 'trainPlanTittle', title: '培训主题', width: '15%',align: 'center',},
            {field: 'trainTeacher', title: '培训讲师', width: '10%', align: 'center',},
            {field: 'trainPlanType', title: '培训类型', width: '10%', align: 'center',
                formatter: function (trainPlanType) {
                    return obj1[trainPlanType];
                }
            },
            {field: 'state', title: '状态', width: '5%', align: 'center',
                formatter: function (state) {
                    if(state=='0'){
                        return '编辑';
                    }
                    else {
                        return '发布';
                    }
                }
            },
            {field: 'trainPlace', title: '培训地点', width: '10%',align: 'center',},
            {field: 'trainDate', title: '培训时间', width: '20%',align: 'center',},
            {field: 'createBy', title: '创建人', width: '5%', align: 'center',
                formatter: function (createBy) {
                    return parent.personList[createBy];
                }
            },
            {field: 'createDate', title: '创建时间', width: '5%', align: 'center'}
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
            if (!search) {
                searchAllData(page,rows);
            }
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
        //获取培训状态
        state = $("#state").combobox('getValue');
        if(state=='请选择'){
            state='';
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
        $("#primaryGrid").datagrid('reload', basePath + '/trainNotice/train-notice-list?trainPlanTittle=' + trainPlanTittle + '&orgId=' + orgId +'&deptIds='+deptIds +'&state='+state +'&type='+type + '&month='+month+ '&page=' + page+ '&rows=' + rows);
        if(search){
            search=false;
            $("#primaryGrid").datagrid('getPager').pagination('select', 1);
        }
    }
    //配置窗口
    $("#editWin").window({
        title: '培训通知管理',
        closed: true,
        modal: true,
        minimizable: false,

        onClose: function () {
            $('#primaryGrid').datagrid('clearSelections');
            $("#primaryGrid").datagrid('reload');
            change=false;
            editor.html('');
            $("#editForm").form('reset');
            $("#editTrainPlan").combobox('loadData',[]);

        },
        onOpen: function () {
            $("#saveBtn").show();
            $("#editTrainPlanType").combobox({
                panelWidth: '150px',
                panelHeight: 'auto',
                idField: 'value',
                textField: 'label',
                loadMsg: '数据正在加载',
                url: '/service/tool/find-list-by-type?type=TRAIN_TYPE_DICT',
                mode: 'remote',
                method: 'GET',
                onSelect: function (record) {
                    if(!edit){
                        $("#editTrainPlan").combobox("enable");
                        $("#editTrainPlan").combobox('clear');
                        $("#editTrainTeacher").textbox('clear');
                        editor.html('');
                    }
                    else{
                        edit=false;
                    }
                    $("#editTrainPlan").combobox('reload',basePath + '/trainPlan/train-plan-all-list?trainPlanType=' + record.value + '&orgId=' + orgId);
                },
            });

        }
    });
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
    //新增
    $("#addBtn").on('click', function () {
        $("#editWin").window("setTitle","培训通知新增").window('open');
        $("#flag").val('0');
        $("#id").val('999');
        arrPerson=[];

    });



    //修改
    $("#editBtn").on("click", function () {
        //获取选择行
        var row=$('#primaryGrid').datagrid('getSelected');
        if(row){
            if(row.state=='1'){
                $('#primaryGrid').datagrid('clearSelections');
                $.messager.alert("提示", "该培训通知已发布，不能修改!","info");
                return;
            }
            $("#editTrainPlan").combobox('enable');
            edit=true;
            $("#editWin").window("setTitle","培训通知修改").window('open');
            $("#editTrainPlace").textbox('setValue',row.trainPlace);
            $("#editStartDate").datetimebox('setValue', row.startDate);
            $("#editEndDate").datetimebox('setValue', row.endDate);
            $("#name").textbox('setValue', row.name);


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

                 $("#editDeptId").val(editDeptId);
                 $("#editUserId").val(editUserId);
                $("#editUserName").textbox('setValue',editUserName);
                     $("#personGrid").datagrid('loadData',arrPerson);
              });

            $("#editTrainPlanType").combobox('setValue',row.trainPlanType);
            $("#editTrainPlan").combobox('setValue',row.trainPlanId);

            $("#editTrainTeacher").textbox('setValue',row.trainTeacher);

            if(row.trainPlanContent!=null){
                //解码
                var varEdit= parent.utf8to16(parent.base64decode(row.trainPlanContent));
                editor.html(varEdit);
                editor.readonly;
            }
            $("#id").val(row.trainNoticeId);
            $("#flag").val('1');

        }
        else{

                $.messager.alert("提示", "请选择一行记录!","info");

        }

    });
    //删除
    $("#delBtn").on('click', function () {
        flag='0';
        var row = $("#primaryGrid").datagrid('getSelected');
        if (row) {
            /*if(row.state=='1'){
                $('#primaryGrid').datagrid('clearSelections');
                $.messager.alert("提示", "该培训通知已发布，不能删除!","info");
                return;
            }*/
            trainNoticeVo.trainNoticeId = row.trainNoticeId;;
            $.messager.confirm('提示', '确定要进行删除操作吗', function (r) {
                if (r) {
                    $.postJSON(basePath + "/trainNotice/train-notice-del",JSON.stringify(trainNoticeVo),function (data) {
                        $('#primaryGrid').datagrid('reload');
                        row.length=0;
                    });
                }
            })
        }else{
            $.messager.alert("提示", "请选择一行记录!","info");

            return;

        }

    });
    //发布
    $("#publishBtn").on('click', function () {
        var row = $("#primaryGrid").datagrid('getSelected');
        if (row) {
            if(row.state=='1'){
                $('#primaryGrid').datagrid('clearSelections');
                $.messager.alert("提示", "该培训通知已发布!","info");
                return;
            }
            trainNoticeVo.trainNoticeId = row.trainNoticeId;;
            $.messager.confirm('提示', '发布后不能修改，确定要发布吗', function (r) {
                if (r) {
                    $.postJSON(basePath + "/trainNotice/train-notice-publish",JSON.stringify(trainNoticeVo),function (data) {
                        $('#primaryGrid').datagrid('reload');
                        row.length=0;
                    });
                }
            });
        }else{
            $.messager.alert("提示", "请选择一行记录!","info");

            return;

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
                editor1.html(varEdit);
                editor1.readonly;
            }
            $("#infoWin").window("setTitle","培训通知查看").window('open');
        }
        else{
            $.messager.alert('提示', '请选择一行记录！', 'info');

        }
        $("#saveBtn").hide();

    });
    //保存数据
    $("#saveBtn").on('click', function () {


        id=$("#id").val();
        flag=$("#flag").val();
        var trainPlanId=$("#editTrainPlan").combogrid('getValue');
        var trainPlace=$("#editTrainPlace").textbox('getValue');
        var trainTeacher=$("#editTrainTeacher").textbox('getValue');

        var startDate=$("#editStartDate").datetimebox('getValue');
        var endDate=$("#editEndDate").datetimebox('getValue');

        var date1 = startDate.replace(/-/g,"\/");

        var date2 = endDate.replace(/-/g,"\/");
        var start = new Date(date1);
        var end = new Date(date2);

        var userId=$("#editUserId").val();
        var deptId=$("#editDeptId").val();

        var userName=$("#editUserName").textbox('getValue');
        var user=userId.split(",");
        var dept=deptId.split(",");

        var name=$("#name").textbox('getValue');
        var type=$("#editTrainPlanType").textbox('getValue');
        var person=[];
        for(var i=0;i<user.length;i++){
            var obj={};
            obj.userId=user[i];
            obj.deptId=dept[i];
            person.push(obj);
        }


        if(flag=='0')
        {
            if(name==''||name==null||name.indexOf(" ") >=0){
                $.messager.alert('提示', '请填写培训名称！', 'info');
                return;
            }
            if(getRealLen(name)>100){
                $.messager.alert("提示","培训名称输入过长！",'info');
                return;
            }
            if(trainPlace==''||trainPlace.indexOf(" ") >=0)
            {
                $.messager.alert("提示", "请填写培训地点 !","info");
                return
            }
            if(getRealLen(trainPlace)>100){
                $.messager.alert("提示","培训地点输入过长！",'info');
                return;
            }

            if(trainPlanId==''&&type!='')
            {
                $.messager.alert("提示", "请选择培训主题!","info");
                return
            }
            if(trainPlanId=='')
            {
                $.messager.alert("提示", "请先选择培训类型，然后选择培训主题!","info");
                return
            }
            if (trainTeacher == '') {
                $.messager.alert("提示", "请填写培训讲师!", "info");
                return
            }
            if (getRealLen(trainTeacher) > 64) {
                $.messager.alert("提示", "培训讲师输入过长！", 'info');
                return;
            }
            if (start > end) {
                $.messager.alert("提示", "结束时间不能早于开始时间!","info");
                return
            }

            if(startDate=='')
            {
                $.messager.alert("提示", "请选择培训开始时间!","info");
                return
            }
            if(endDate=='')
            {
                $.messager.alert("提示", "请选择培训结束时间!","info");
                return
            }
            if(userName=='')
            {
                $.messager.alert("提示", "请选择参加培训人!","info");
                return
            }

        }
        trainNoticeVo.name = name;
        trainNoticeVo.orgId = parent.config.org_Id;
        trainNoticeVo.trainNoticeId= id;
        trainNoticeVo.trainPlanId = trainPlanId;
        trainNoticeVo.startDate = startDate;
        trainNoticeVo.endDate = endDate;
        trainNoticeVo.trainPlace = trainPlace;
        trainNoticeVo.trainNoticePerson = person;
        trainNoticeVo.trainTeacher = trainTeacher;

        $.postJSON(basePath + "/trainNotice/merge", JSON.stringify(trainNoticeVo), function (data) {

            if(data.data=="success")
            {
                $("#primaryGrid").datagrid('reload');
                $("#editForm").form('reset');
                $("#editWin").window('close');
            }
            else if(data.data=="hasName"){
                $.messager.alert("提示", "该名称已存在!", "info");
            }
            else{
                $.messager.alert('提示', '保存失败', 'info');
            }

        })

    });

    //取消
    $("#cancelBtn").on('click', function () {
        $("#editForm").form('reset');
        $("#editWin").window('close');
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

});

