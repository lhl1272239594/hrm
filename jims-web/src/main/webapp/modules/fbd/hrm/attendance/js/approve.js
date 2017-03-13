/*
 *管理
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
var approveVo = {};
var orgId = parent.config.org_Id;
var id='999';
var attFunId='';//1请假2加班3公出
var searchId='';
var userId='';
var userName='';
var value='';
var label='';
var flag='';
var obj1 = new  Object();
var attFunList=[];
var depts = [];
var treeDepts = [];
var approveStatusList=[];
var approveStatus='';
var deptIds=parent.orgids;
var month='';
$(function () {


    $.get("/service/tool/find-list-by-type?type=ATT_FUNCTION_DICT&value="+value, function (data) {
        $.each(data, function (index,item) {
            attFunList[item.value]=item.label;
        });
    });


    $.get("/service/tool/find-list-by-type?type=APPROVE_STATUS&value="+value, function (data) {
        $.each(data, function (index,item) {
            approveStatusList[item.value]=item.label;
        });
    });

    //查询条件：审批状态
    $("#approveStatus").combobox({
        idField: 'value',
        textField: 'label',
        value:'请选择',
        loadMsg: '数据正在加载',
        url: '/service/dict/find-list-by-type?type=' + 'APPROVE_STATUS',
        mode: 'remote',
        method: 'GET'
    });

    //查询条件：业务类型
    $("#searchId").combobox({
        idField: 'value',
        textField: 'label',
        value:'请选择',
        loadMsg: '数据正在加载',
        url: '/service/dict/find-list-by-type?type=' + 'ATT_FUNCTION_DICT',
        mode: 'remote',
        method: 'GET'
    });
    $.messager.progress({
        title: '提示！',
        msg:  '数据量较大，请稍候...',
        text: '加载中.......'
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
        pageSize:30,
        collapsible: false,//是否可折叠的
        remoteSort: false,
        singleSelect: true,//是否单选
        rownumbers: true,//行号
        url: basePath + '/approve/approve-list?userId='+userId+'&orgId='+orgId+'&attFunId='+searchId+'&approveStatus='+ approveStatus +'&deptIds='+deptIds+'&month=' + month,
        columns: [[
            {field: 'orgId', title: '', width: '10%',hidden: true},
            {field: 'dataId', title: '', width: '10%',hidden: true},
            {field: 'attFunId', title: '业务分类', width: '10%', align: 'center',
                formatter: function (attFunId) {
                    return attFunList[attFunId];
                }},

            {field: 'userId', title: '申请人', width: '10%', align: 'center',
                formatter: function (userId) {
                    return parent.personList[userId];
                }
            },
            {field: 'deptId', title: '申请人部门', width: '30%', align: 'center',
                formatter: function (deptId) {
                    $.ajaxSettings.async = false;
                    $.get(basePath + '/tool/find-dept-list?deptId='+deptId, function (data) {
                        deptName=data[0].deptName;
                    });
                    return deptName
                }},
            {field: 'approveStatus', title: '审批状态', width: '10%', align: 'center',
                formatter: function (approveStatus) {
                    return approveStatusList[approveStatus];
                }
            },
            {field: 'approvePersonId', title: '审批人', width: '10%', align: 'center',
                formatter: function (approvePersonId) {
                    return parent.personList[approvePersonId];
                }
            },

            {field: 'approveDate', title: '审批时间', width: '10%', align: 'center'},
            {field: 'createBy', title: '提交人', width: '10%', align: 'center',
                formatter: function (createBy) {
                    return parent.personList[createBy];
                }
            },
            {field: 'createDate', title: '提交时间', width: '10%', align: 'center'}
        ]],
        onLoadSuccess:function(){
        $.messager.progress('close')
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
        search=true;
        userId=$("#userId").textbox('getValue');
        searchId = $("#searchId").textbox('getValue');
        if(searchId=='请选择'){
            searchId='';
        }
        approveStatus = $("#approveStatus").textbox('getValue');
        if(approveStatus=='请选择'){
            approveStatus='';
        }
        month = $("#month1").val();
        if(month==null||month==''||month.length==0){
            month='';
        }
        searchAllData(page,rows);

    });
    //按条件查询

    var searchAllData=function (page,rows) {
        //userId=$("#userId").textbox('getValue');
        //attFunId=$("#attFunId").textbox('getValue');

        $("#primaryGrid").datagrid('reload', basePath + '/approve/approve-list?userId=' + userId + '&orgId=' + orgId + '&attFunId=' + searchId + '&approveStatus=' + approveStatus + '&page=' + page+ '&rows=' + rows +'&deptIds='+deptIds+'&month=' + month);
        if(search){
            search=false;
            $("#primaryGrid").datagrid('getPager').pagination('select', 1);
        }
    }



    //配置窗口
    $("#editWin").window({
        closed: true,
        modal: true,
        collapsible: true,
        minimizable: true,
        maximizable: true,
        resizable: true,
        onClose: function () {

            $("#overTime").css("display", "block");
            $("#offWork").css("display", "block");
            $("#tripWork").css("display", "block");
            $("#adjustDay").css("display", "block");
            $("#editForm").form('reset');
        },
        onOpen: function () {

        }
    });


    //
    $("#approveBtn").on("click", function () {
        //获取选择行
        var row=$('#primaryGrid').datagrid('getSelected');
        if(row){

            $("#editWin").window('open');
            var attFunId=row.attFunId;
            var dataId=row.dataId;
            $("#id").val(dataId);
            $("#attFunId").val(attFunId);
            $.get(basePath + '/tool/find-person-list?userId='+row.userId, function (data) {
                $("#editUserId").textbox('setValue', data[0].userName);
            });

            $.get(basePath + '/tool/find-dept-list?deptId='+row.deptId, function (data) {
                $("#editDept").textbox('setValue', data[0].deptName);
            });

            if(attFunId=='4'){
                $.get(basePath + '/tripWork/trip-work-all-list?userId=' + userId + '&orgId=' + orgId + '&value=' + dataId,
                    function (data) {
                        $("#editWin").window("setTitle","公出审批")
                        $("#editStartDate").textbox('setValue', data[0].startDate);
                        $("#editEndDate").textbox('setValue', data[0].endDate);
                        $("#editAttFunId").textbox('setValue', "公出申请");
                        $("#editTripWorkPlace").textbox('setValue', data[0].tripWorkPlace);
                        $("#editTripWorkDestination").textbox('setValue', data[0].tripWorkDestination);
                        $("#editTripWorkReason").textbox('setValue', data[0].tripWorkReason);

                        $("#overTime").css("display", "none");
                        $("#offWork").css("display", "none");
                        $("#adjustDay").css("display", "none");
                });

            }
            if(attFunId=='2'){
                $.get(basePath + '/overTime/over-time-all-list?userId=' + userId + '&orgId=' + orgId + '&value=' + dataId,
                    function (data) {
                        $("#editWin").window("setTitle","加班审批")
                        $("#editStartDate").textbox('setValue', data[0].startDate);
                        $("#editEndDate").textbox('setValue', data[0].endDate);
                        $("#editAttFunId").textbox('setValue', "加班申请");

                        $("#editOverTimeReason").textbox('setValue', data[0].overTimeReason);


                        $("#offWork").css("display", "none");
                        $("#adjustDay").css("display", "none");
                        $("#tripWork").css("display", "none");
                    });
            }
            if(attFunId=='1'){
                $.get(basePath + '/offWork/off-work-all-list?userId=' + userId + '&orgId=' + orgId + '&value=' + dataId,
                    function (data) {
                        $("#editWin").window("setTitle","请假审批")
                        $("#editStartDate").textbox('setValue', data[0].startDate);
                        $("#editEndDate").textbox('setValue', data[0].endDate);
                        $("#editAttFunId").textbox('setValue', "请假申请");

                        $("#editHolidayType").textbox('setValue', data[0].holidayDes);
                        $("#editOffWorkReason").textbox('setValue', data[0].offWorkReason);


                        $("#overTime").css("display", "none");
                        $("#adjustDay").css("display", "none");
                        $("#tripWork").css("display", "none");
                    });
            }
            }
            else{
                $.messager.alert("提示", "请选择一条记录", "info");
                return;
            }

    });
    //审批通过
    $("#passBtn").on("click", function () {

        flag='2';
        save(flag);
    });
    //审批驳回
    $("#rejectBtn").on("click", function () {

        flag='3';
        save(flag);

    });
    var save=function (flag) {

        id=$("#id").val();
        attFunId=$("#attFunId").val();
        approveVo.orgId = parent.config.org_Id;
        approveVo.dataId = id;
        approveVo.flag = flag;
        approveVo.attFunId = attFunId;
        $.postJSON(basePath + "/approve/approve-data", JSON.stringify(approveVo), function (data) {
            $("#editWin").window('close');
            $("#primaryGrid").datagrid('reload');
            $("#editForm").form('reset');

        })
    }

    //取消
    $("#cancelBtn").on('click', function () {
        $("#editForm").form('reset');
        $("#editWin").window('close');
    });
    $("#clearBtn").on('click', function () {
        $("#userId").textbox('setValue', '');
        $("#searchId").combobox('setValue','');
        $("#searchId").combobox('setText','请选择');
        $("#approveStatus").combobox('setValue','');
        $("#approveStatus").combobox('setText','请选择');
        //清空月份
        $("#month").datebox('clear');
        $("#month1").val('');
        month = '';
        page='1';
    });

});
