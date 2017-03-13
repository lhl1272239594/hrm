/**
 *法定节日设置
 * @author yangchen
 * @version 2016-08-18
 */
var basePath = "/service";
var holDes = "999";
var holPayTypeId = "999";
var sumDays = "0";
var dg;
var d;
var flag;
var editIndex = undefined;
var page = '1';
var rows = '30';
var holidayVo = {};
var orgId = parent.config.org_Id;
var obj = new Object();

$(function () {
/*

    $.get(basePath + '/tool/find-list-by-type?type=HOLIDAY_PAY_TYPE', function (data) {
        $.each(data, function (index, item) {
            obj[item.value] = item.label;
        });
    });*/


  /*  //查询条件：类型
    $("#holType").combobox({
        panelWidth: '70px',
        panelHeight: 'auto',
        idField: 'value',
        textField: 'label',
        value: '请选择',
        loadMsg: '数据正在加载',
        url: '/service/dict/find-list-by-type?type=' + 'HOLIDAY_PAY_TYPE',
        mode: 'remote',
        method: 'GET'
    });*/
    //节日设置数据
    $("#holidayGrid").edatagrid({
        iconCls: 'icon-edit',//图标
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
        collapsible: false,//是否可折叠的
        url: basePath + '/holiday/holiday-list?holDes=' + holDes + '&orgId=' + orgId + '&holPayTypeId=' + holPayTypeId,
        remoteSort: false,
        idField: 'holId',
        singleSelect: true,//是否单选
        rownumbers: true,//行号
        columns: [[
            {field: 'orgId', title: '', width: 100, hidden: true},
            {field: 'holId', title: '', width: 100, hidden: true},
            {field: 'holDes', title: '假日名称', width: 100, align: 'center'},
            {field: 'createBy', title: '创建人', width: 100, align: 'center'},
            {field: 'createDate', title: '创建时间', width: 100, align: 'center'}

        ]]
    });
    $("#holidayGrid").datagrid('getPager').pagination({
        pageSize: 30,
        pageList: [10, 20, 30, 40, 50], //可以设置每页记录条数的列表
        displayMsg: '当前显示从第{from}条到第{to}条，共{total}条记录',
    });

    //删除
    $("#delBtn").on('click', function () {
        flag = '0';
        var row = $("#holidayGrid").datagrid('getSelected');

        if (row) {
            $.get('/service/holiday/checkUsed?id=' + row.holId, function (data) {
                if(data.code=='hasUsed'){
                    $.messager.alert("提示", "该假日正在使用中,不能删除!", "info");
                    return;
                }
                else {
                    holidayVo.holId = row.holId;
                    holidayVo.flag = flag;
                    $.messager.confirm('提示', '确定要进行删除操作吗', function (r) {
                        if (r) {
                            $.postJSON(basePath + "/holiday/holiday-del", JSON.stringify(holidayVo), function (data) {
                                $('#holidayGrid').datagrid('reload');
                                row.length = 0;
                            });
                        }
                    })
                }
            });

        } else {
            $.messager.alert("提示", "请选择一条记录", "info");
            return;

        }
    });

    //配置窗口
    $("#addHoliday").window({
        title: '假日信息',
        closed: true,
        modal: true,
        collapsible: true,
        minimizable: false,
        resizable: true,
        onClose: function () {
            $("#holForm").form('reset');

        },
        onOpen: function () {

        }


    });


    //新增
    $("#addBtn").on('click', function () {
        $("#addHoliday").window('open');
        $("#holId").val('999');
        $("#flag").val('add');

    });
    //修改
    $("#editBtn").on("click", function () {

        //获取选择行
        var row = $('#holidayGrid').datagrid('getChecked');
        if (row.length == 1) {
            $.get('/service/holiday/checkUsed?id=' + row[0].holId, function (data) {
                if(data.code=='hasUsed'){
                    $.messager.alert("提示", "该假日正在使用中,不能修改!", "info");
                    return;
                }
                else {
                    $("#addHoliday").window('open');
                    $("#editSumDays").textbox('setValue', row[0].sumDays);
                    $("#holId").val(row[0].holId);
                    $("#flag").val('edit');
                    $("#editHolDes").textbox('setValue', row[0].holDes);
                }
            });

        }
        else {
            $.messager.alert("提示", "请选择一条记录", "info");
        }

    });

    //保存数据
    $("#saveBtn").on('click', function () {

        var holDes = $("#editHolDes").textbox('getValue');
        var holId = $("#holId").val();
        var flag = $("#flag").val();
        if(holDes==''||holDes==null||holDes.indexOf(" ") >=0){
            $.messager.alert('提示', '请填写假期名称！', 'info');
            return;
        }
        if(getRealLen(holDes)>100){
            $.messager.alert("提示","假期名称输入过长！",'info');
            return;
        }

        holidayVo.orgId = parent.config.org_Id;
        holidayVo.holId = holId;
        holidayVo.holDes = holDes;
        holidayVo.holPayTypeId = '0';
        $.postJSON(basePath + "/holiday/merge", JSON.stringify(holidayVo), function (data) {
            if(data.data=="success"){
                $("#holidayGrid").datagrid('reload');
                $("#holForm").form('reset');
                $("#addHoliday").window('close');
            }
            if(data.data=="hasName"){
                $.messager.alert("提示", "该名称已存在!", "info");
            }

        })

    });

    //取消
    $("#cancelBtn").on('click', function () {
        $("#holForm").form('reset');
        $("#addHoliday").window('close');
    });

});
function  checkUsed(id) {
    $.get('/service/holiday/checkUsed?id=' + id, function (data) {
        if(data.code=='hasUsed'){
            return true;
        }
        else {
            return false;
        }
    });
}

