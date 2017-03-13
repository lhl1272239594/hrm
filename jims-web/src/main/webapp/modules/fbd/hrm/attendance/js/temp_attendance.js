/**
 *临时考勤
 * @author yangchen
 * @version 2016-08-18
 */
var basePath = "/service";
var tempAttendanceName ='';
var page = '1';
var rows = '30';
var tempAttendancePersonVo = {};
var sumDays = "0";
var dg;
var d;
var flag;
var editIndex = undefined;
var fileName = '';
var fileUrl = '';
var search=false;
var staffId = parent.config.staffId;
var orgId = parent.config.org_Id;
var tempAttName='';

var tempAttDate='';
var tempAttPlace='';

var editStartDate='';
var editEndDate='';
var checkInStartTime='';
var checkInEndTime='';
var checkOutStartTime='';
var checkOutEndTime='';
var adjustStartTime='';
var adjustEndTime='';
var date1 =  new Date();
var date2 =  new Date();
var date3 =  new Date();
var date4 =  new Date();
var date5 =  new Date();
var date6 =  new Date();
$(function () {
    //定义组织ID
    loadtree();

    //考勤数据查询
    $("#primaryGrid").datagrid({
        //iconCls: 'icon-edit',//图标
        width: '100%',
        height: '100%',
        nowrap: false,
        striped: true,
        border: true,
        method: 'get',
        toolbar: '#tb',
        fitColumns: true,
        loadMsg: '数据正在加载中，请稍后.....',
        pagination: true,//分页控件
        pageSize: 30,
        collapsible: false,//是否可折叠的
        url: basePath + '/tempAttendance/temp-attendance-list?orgId=' + orgId + '&tempAttendanceName=' + tempAttendanceName,
        remoteSort: false,
        singleSelect: true,//是否单选
        rownumbers: true,//行号
        columns: [[
            {field: 'orgId', title: '', hidden: true},
            {field: 'tempAttId', title: '', hidden: true},
            {field: 'tempAttName', title: '临时考勤名称', width: '10%', align: 'center'},
            {field: 'tempAttPlace', title: '临时考勤地点', width: '10%', align: 'center'},
            {field: 'tempAttDate', title: '临时考勤日期', width: '10%', align: 'center',formatter:formatDatebox},
            {field: 'startTime', title: '开始时间', width: '5%', align: 'center'},
            {field: 'effectiveCheckInTime', title: '最晚开始签到时间', width: '10%', align: 'center'},
            {field: 'endTime', title: '结束时间', width: '5%', align: 'center'},
            {field: 'effectiveCheckOutTime', title: '最早结束签到时间', width: '10%', align: 'center'},
            {field: 'userName', title: '考勤员工', width: '20%', align: 'left',halign: 'center',
                formatter : function (userName, row, index) {
                    if (userName.length > 50)
                    {
                        var  returnUserName = userName.substr(0, 50) + "......";
                        return "<span title='" + row.userName + "'>" + returnUserName + "</span>";
                    }
                    else{
                        return userName;
                    }
                }},
            {field: 'createBy', title: '创建人', width: '5%', align: 'center'},
            {field: 'createDate', title: '创建时间', width: '15%', align: 'center'}

        ]],
        onLoadSuccess:function(data){

        }
    });

    $("#primaryGrid").datagrid('getPager').pagination({
        pageList: [10, 20, 30, 40, 50], //可以设置每页记录条数的列表
        displayMsg: '当前显示从第{from}条到第{to}条，共{total}条记录',
        onSelectPage: function (pageNumber, pageSize) {
            var state = $('#primaryGrid').data('datagrid');
            var opts = state.options;
            page=opts.pageNumber = pageNumber;
            rows=opts.pageSize = pageSize;
            searchData(page,rows);
        }
    });

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
    //按条件查询数据
    $("#searchBtn").on("click", function () {
        search=true;
        tempAttendanceName = $("#tempAttendanceName").textbox('getValue');
        searchData();

    });


    var searchData= function (){
            $("#primaryGrid").datagrid('reload', basePath + '/tempAttendance/temp-attendance-list?tempAttendanceName=' + tempAttendanceName + '&orgId=' + orgId + '&page=' + page + '&rows=' + rows);
            if(search){
                search=false;
                    $("#primaryGrid").datagrid('getPager').pagination('select', 1);

            }
    }

    //配置窗口
    $("#editWin").window({
        title: '临时考勤信息',
        closed: true,
        minimizable: false,
        modal: true,
        onClose: function () {
            $('#primaryGrid').datagrid('clearSelections');
            $("#editForm").form('reset');

        },
        onOpen: function () {

        }
    });
    //配置窗口
    $("#viewWin").window({
        title: '临时考勤信息',
        closed: true,
        minimizable: false,
        modal: true,
        onClose: function () {
            $('#primaryGrid').datagrid('clearSelections');

        },
        onOpen: function () {

        }
    });
    //新增
    $("#addBtn").on('click', function () {
        $("#editWin").window('open');
        $("#flag").val('0');
        $("#id").val('999');
        arrPerson=[];

    });

    //修改
    $("#editBtn").on("click", function () {
        //获取选择行
        var row=$('#primaryGrid').datagrid('getSelected');

        if(row){
            if(row.statusType=='1'){
                $('#primaryGrid').datagrid('clearSelections');
                $.messager.alert("提示", "该临时考勤已开始打卡签到，不能修改!","info");
                return;
            }
            $("#editWin").window("setTitle","临时考勤修改").window('open');
            $("#tempAttName").textbox('setValue',row.tempAttName);

            $("#editAttDate").datebox('setValue',row.tempAttDate);
            $("#tempAttPlace").textbox('setValue',row.tempAttPlace);

            $("#editStartDate").timespinner('setValue',row.startTime);
            $("#editEndDate").timespinner('setValue',row.endTime);


            $("#adjustStartTime").numberspinner('setValue',row.adjustStartTime);
            $("#adjustEndTime").numberspinner('setValue',row.adjustEndTime);


            $.get("/service/tempAttendance/temp-attendance-person-list?tempAttId="+row.tempAttId+"&orgId="+orgId,
                function (data) {
                    var arrPerson=[];
                    arrPerson=data;
                    $.each(arrPerson, function (index, item) {
                        var obj=getName(personObj,'id',item.userId);
                        arrPerson[index].userId=obj[0].id;
                        arrPerson[index].userName=obj[0].name;
                        arrPerson[index].deptId=obj[0].depId;

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


            $("#id").val(row.tempAttId);

        }
        else{

            $.messager.alert("提示", "请选择一行记录!","info");

        }

    });
    //查看
    $("#viewBtn").on("click", function () {
        //获取选择行
        var row=$('#primaryGrid').datagrid('getSelected');
        if(row){
            $("#dataGrid").datagrid({
                width: '100%',
                height: '100%',
                nowrap: false,
                striped: true,
                border: true,
                method: 'get',
                loadMsg: '数据正在加载中，请稍后.....',
                collapsible: false,//是否可折叠
                remoteSort: false,
                singleSelect: true,//是否单选
                rownumbers: true,//行号
                fitColumns: true,
                pagination: false,//分页控件
                url: basePath + '/tempAttendance/temp-attendance-person-list?tempAttId='+row.tempAttId+'&orgId='+orgId,
                columns: [[
                    {field: 'orgId', title: '', hidden: true},
                    {field: 'tempAttId', title: '', hidden: true},
                    {field: 'userName', title: '员工姓名', width: '13%', align: 'center'},
                    {field: 'deptName', title: '员工部门', width: '17%', align: 'center'},
                    {field: 'effectiveCheckInTime', title: '最晚开始签到时间', width: '18%', align: 'center'},
                    {field: 'realCheckInTime', title: '实际开始签到时间', width: '18%', align: 'center'},
                    {field: 'effectiveCheckOutTime', title: '最早结束签到时间', width: '18%', align: 'center'},
                    {field: 'realCheckOutTime', title: '实际结束签到时间', width: '18%', align: 'center'},

                ]],
            });
            $("#viewWin").window("setTitle","临时考勤信息查看").window('open');

        }
        else{

            $.messager.alert("提示", "请选择一行记录!","info");

        }

    });
    /**
     * 删除
     */
    $("#delBtn").on('click', function () {
        var row=$('#primaryGrid').datagrid('getSelected');
        if(row){
            if(row.statusType=='1'){
                $('#primaryGrid').datagrid('clearSelections');
                $.messager.alert("提示", "该临时考勤已开始打卡签到，不能删除!","info");
                return;
            }
            tempAttendancePersonVo.tempAttId = row.tempAttId;
            $.messager.confirm('提示', '确定要进行删除操作吗', function (r) {
                if (r) {
                    $.postJSON(basePath + "/tempAttendance/temp-attendance-del", JSON.stringify(tempAttendancePersonVo), function (data) {
                        $('#primaryGrid').datagrid('reload');
                        rows.length = 0;
                        $("#primaryGrid").datagrid('clearSelections');
                    })
                }
            })
        }else{
            $.messager.alert('提示', '请选择一行记录！', 'info');

            return;

        }


    });

    //取消
    $("#cancelBtn").on('click', function () {
        $("#editForm").form('reset');
        $("#editWin").window('close');
        $("#viewWin").window('close');

    });
    //取消
    $("#cancelViewBtn").on('click', function () {
        $("#editForm").form('reset');
        $("#viewWin").window('close');
    });
    $("#clearBtn").on('click', function () {
        $("#tempAttendanceName").textbox('setValue', '');
        page='1';
    });
    //保存数据
    $("#saveBtn").on('click', function () {


        id=$("#id").val();
         tempAttName=$("#tempAttName").textbox('getValue');

         tempAttDate=$("#editAttDate").datebox('getValue');

         tempAttPlace=$("#tempAttPlace").textbox('getValue');

         editStartDate=$("#editStartDate").timespinner('getValue');
         editEndDate=$("#editEndDate").timespinner('getValue');

         adjustStartTime=$("#adjustStartTime").numberspinner('getValue');
         adjustEndTime=$("#adjustEndTime").numberspinner('getValue');




        if(tempAttName==''||tempAttName==null||tempAttName.indexOf(" ") >=0){
            $.messager.alert('提示', '请填写临时考勤名称，不能包含空格！', 'info');
            return;
        }
        if(tempAttPlace==''||tempAttPlace==null||tempAttPlace.indexOf(" ") >=0){
            $.messager.alert('提示', '请填写临时考勤地点，不能包含空格！', 'info');
            return;
        }
        if(tempAttDate==''){
            $.messager.alert('提示', '请选择临时考勤日期！', 'info');
            return;
        }
        if(getRealLen(tempAttName)>60){
            $.messager.alert("提示","临时考勤名称输入过长！",'info');
            return;
        }
        if(getRealLen(tempAttPlace)>60){
            $.messager.alert("提示","临时考勤地点输入过长！",'info');
            return;
        }

        if(editStartDate==''){
            $.messager.alert('提示', '请输入临时考勤开始时间！', 'info');
            return;
        }

        if(editStartDate==''){
            $.messager.alert('提示', '请输入临时考勤结束时间！', 'info');
            return;
        }

        if(adjustStartTime==''){
            $.messager.alert('提示', '请输入开始浮动时间！', 'info');
            return;
        }
        if(adjustEndTime==''){
            $.messager.alert('提示', '请输入结束浮动时间！', 'info');
            return;
        }





        date1.setHours($("#editStartDate").timespinner('getHours'));
        date1.setHours($("#editStartDate").timespinner('getMinutes'));

        date2.setHours($("#editEndDate").timespinner('getHours'));
        date2.setMinutes($("#editEndDate").timespinner('getMinutes'));


        var userId=$("#editUserId").val();
        var deptId=$("#editDeptId").val();

        var userName=$("#editUserName").textbox('getValue');
        var user=userId.split(",");
        var dept=deptId.split(",");

        var person=[];
        for(var i=0;i<user.length;i++){
            var obj={};
            obj.userId=user[i];
            obj.deptId=dept[i];
            person.push(obj);
        }

            if (date1 > date2) {
                $.messager.alert("提示", "临时考勤开始时间<"+editStartDate+">必须小于结束时间<"+editEndDate+">","info");
                return
            }
             if (date1 === date2) {
            $.messager.alert("提示", "临时考勤开始时间<"+editStartDate+">不能等于结束时间<"+editEndDate+">","info");
            return
            }


            if(userName=='')
            {
                $.messager.alert("提示", "请选择临时考勤参加人员!","info");
                return
            }

        tempAttendancePersonVo.tempAttName = tempAttName;
        tempAttendancePersonVo.orgId = parent.config.org_Id;
        tempAttendancePersonVo.tempAttId= id;
        tempAttendancePersonVo.tempAttDate = tempAttDate;
        tempAttendancePersonVo.tempAttPlace = tempAttPlace;
        tempAttendancePersonVo.startTime = editStartDate;
        tempAttendancePersonVo.endTime = editEndDate;
        tempAttendancePersonVo.adjustStartTime = adjustStartTime;
        tempAttendancePersonVo.adjustEndTime = adjustEndTime;
        tempAttendancePersonVo.tempAttendancePerson = person;

        $.get(basePath + "/tempAttendance/temp-attendance-boolean?" +
            "orgId=" + orgId +
            "&tempAttId=" + id +
            "&editStartDate=" + editStartDate +
            "&editEndDate=" + editEndDate +
            "&tempAttDate=" + tempAttDate +
            "&tempAttPlace=" + tempAttPlace,
            function (data) {
                var num = data[0].num;
                if (num == 1 || num > 1) {
                    var str = '与已有临时考勤数据冲突，请检查！';
                    $.messager.alert("提示", str, "info");
                    return
                }
                $.postJSON(basePath + "/tempAttendance/merge", JSON.stringify(tempAttendancePersonVo), function (data) {
                    if(data.data=="success")
                    {
                        $("#primaryGrid").datagrid('reload');
                        $("#editForm").form('reset');
                        $("#editWin").window('close');
                    }
                    else{
                        $.messager.alert('提示', '保存失败', 'info');
                    }
                })

            });

    });
});

