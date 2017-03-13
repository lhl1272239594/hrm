/*
 *管理
 * @author yangchen
 * @version 2016-08-18
 *!/*/
var basePath = "/service";

var dg;
var d;
var flag;
var editIndex = undefined;
var page = '1';
var rows = '30';
var holidayPersonVo = {};
var orgId = parent.config.org_Id;
var id = '999';
var startDate = '';
var userId = '';
var searchId = '';
var yearDes = '';
var userName = '';
var value = '';
var label = '';
var obj1 = new Object();
var obj2 = new Object();
var obj3 = new Object();
var depts = [];
var treeDepts = [];
var search = false;
var deptIds=parent.orgids;
var lx = 'add';
$(function () {


    $("#editHoliday").combobox({
        panelWidth: '150',
        panelHeight: 'auto',
        idField: 'value',
        textField: 'label',
        value: '请选择',
        loadMsg: '数据正在加载',
        url: '/service/holiday/holiday-all-list?orgId=' + orgId + '&value=' + value,
        mode: 'remote',
        method: 'GET'
    });
    $("#editYearId").combobox({
        panelWidth: '150',
        panelHeight: 'auto',
        idField: 'value',
        textField: 'label',
        value: '请选择',
        loadMsg: '数据正在加载',
        url: '/service/tool/find-list-by-type?type=YEAR_DICT',
        mode: 'remote',
        method: 'GET'
    });
    loadtree();
    $.messager.progress({
        title: '提示！',
        msg: '数据量较大，请稍候...',
        text: '加载中.......'
    });
    $.get("/service/tool/find-list-by-type?type=YEAR_DICT&value=" + value, function (data) {
        $.each(data, function (index, item) {
            obj1[item.value] = item.label;
        });
    });
    $("#primaryGrid").datagrid({
        iconCls: 'icon-edit',//图标
        width: '100%',
        height: '100%',
        nowrap: false,
        striped: true,
        border: true,
        singleSelect: true,
        method: 'get',
        toolbar: '#tb',
        fitColumns: true,
        loadMsg: '数据正在加载中，请稍后.....',
        pagination: true,//分页控件
        pageSize: 30,
        collapsible: false,//是否可折叠的
        remoteSort: false,
        singleSelect: true,//是否单选
        rownumbers: true,//行号
        url: basePath + '/holidayPerson/holiday-person-list?userId=' + searchId + '&orgId=' + orgId +'&deptIds='+deptIds,
        columns: [[
            {field: 'orgId', title: '', width: '10%', hidden: true},
            {field: 'holPersonId', title: '', width: '15%', hidden: true},
            {
                field: 'yearId', title: '年份', width: '10%', align: 'center',
                formatter: function (yearId) {
                    return obj1[yearId];
                }
            },

            {field: 'holDes', title: '假日', width: '15%', align: 'center'},
            {
                field: 'userId', title: '员工姓名', width: '15%', align: 'center',
                formatter: function (userId) {
                    return parent.personList[userId];
                }
            },
            {
                field: 'deptId', title: '员工部门', width: '40%', align: 'center',
                formatter: function (deptId) {
                    $.ajaxSettings.async = false;
                    $.get(basePath + '/tool/find-dept-list?deptId=' + deptId, function (data) {
                        deptName = data[0].deptName;
                    });
                    return deptName
                }
            },
            {field: 'sumDays', title: '总天数', width: '10%', align: 'center'},
            /* {field: 'createBy', title: '创建人', width: '10%', align: 'center'},*/
            {field: 'createDate', title: '创建时间', width: '10%', align: 'center'}
        ]],
        onLoadSuccess: function () {
            $.messager.progress('close');
            $("#primaryGrid").datagrid('getPager').pagination({
                pageList: [10, 20, 30, 40, 50], //可以设置每页记录条数的列表
                displayMsg: '当前显示从第{from}条到第{to}条，共{total}条记录',
                onSelectPage: function (pageNumber, pageSize) {
                    var state = $('#primaryGrid').data('datagrid');
                    var opts = state.options;
                    page = opts.pageNumber = pageNumber;
                    rows = opts.pageSize = pageSize;
                    searchAllData(page, rows);
                    return;
                }
            });
        },
    });
    //按条件查询(汇总)
    $("#searchAllBtn").on("click", function () {
        search = true;
        //获取姓名
        searchId = $("#userId").textbox('getValue');
        searchAllData(page, rows);

    });
    //按条件查询

    var searchAllData = function (page, rows) {
        $.get(basePath + '/holidayPerson/holiday-person-list?userId=' + searchId + '&orgId=' + orgId +'&deptIds='+deptIds + '&page=' + page + '&rows=' + rows,
            function (data) {
                $("#primaryGrid").datagrid('loadData', data);
                if (search) {
                    search = false;
                    $("#primaryGrid").datagrid('getPager').pagination('select', 1);

                }
            });
    };
    //配置窗口
    $("#editWin").window({
        title: '假日人员管理',
        closed: true,
        modal: true,
        onClose: function () {

            $("#editForm").form('reset');
        },
        onOpen: function () {


        }
    });

    //新增
    $("#addBtn").on('click', function () {
        $("#editWin").window('open');
        $("#flag").val('0');
        $("#id").val('999');
        $("#editUserId").val('');
        arrPerson = [];
        lx = 'add';
    });


    //修改
    $("#editBtn").on("click", function () {
        //获取选择行
        var row = $('#primaryGrid').datagrid('getSelected');
        if (row) {
            lx = 'edit';
            $("#editWin").window('open');
            $("#editUserId").val(row.userId);
            $.get(basePath + '/tool/find-person-list?userId=' + row.userId, function (data) {
                $("#editUserName").textbox('setValue', data[0].userId);
                $("#editUserName").textbox('setText', data[0].userName);
                $("#editUserName").textbox('disable');

            });

            $.get(basePath + '/tool/find-list-by-type?type=YEAR_DICT&value=' + row.yearId, function (data) {
                $("#editYearId").combobox('setValue', data[0].value);
                $("#editYearId").combobox('setText', data[0].label);

            });
                $("#editHoliday").combobox('setValue', row.holId);


            //$("#chooseUser").hide();
            $("#id").val(row.holPersonId);
            $("#flag").val('1');
            $("#editSumDays").textbox('setValue', row.sumDays);

        }

        else {
            $.messager.alert("系统提示", "请选择一条记录");
            return;
        }

    });
    //删除
    $("#removeBtn").on('click', function () {
        flag = '0';
        var row = $("#primaryGrid").datagrid('getSelected');
        if (row) {
            holidayPersonVo.holPersonId = row.holPersonId;
            $.messager.confirm('系统提示', '确定要进行删除操作吗', function (r) {
                if (r) {
                    $.postJSON(basePath + "/holidayPerson/holiday-person-del", JSON.stringify(holidayPersonVo), function (data) {

                        $('#primaryGrid').datagrid('reload');
                        row.length = 0;
                    });
                }
            })
        } else {
            $.messager.alert("系统提示", "请选择一条记录");
            return;
        }

    });
    //保存数据
    $("#saveBtn").on('click', function () {


        id = $("#id").val();
        flag = $("#flag").val();
        var yearId = $("#editYearId").combobox('getValue');
        var sumDays = $("#editSumDays").textbox('getValue');
        var holId = $("#editHoliday").combobox('getValue');
        userId = $("#editUserId").val();
        deptId = $("#editDeptId").val();
        var user = userId.split(",");
        var dept = deptId.split(",");

        var person = [];
        for (var i = 0; i < user.length; i++) {
            var obj = {};
            obj.userId = user[i];
            obj.deptId = dept[i];
            person.push(obj);
        }
        if (yearId == ''||yearId=='请选择') {
            $.messager.alert("系统提示", "请选择年份!", "alert");
            return
        }
        if (userId == null || userId == '') {
            $.messager.alert('系统提示', '请选择人员', 'alert');
            return;
        }
        if (holId == ''||holId=='请选择') {
            $.messager.alert("系统提示", "请选择假日!", "alert");
            return
        }
        if (sumDays == '') {
            $.messager.alert("系统提示", "请输入总天数!", "alert");
            return
        }
        holidayPersonVo.orgId = parent.config.org_Id;
        holidayPersonVo.yearId = yearId;
        holidayPersonVo.holId = holId;
        holidayPersonVo.sumDays = sumDays;
        holidayPersonVo.holPersonId = id;
        holidayPersonVo.holidayPersonUser = person;
        $.postJSON(basePath + "/holidayPerson/merge", JSON.stringify(holidayPersonVo), function (data) {
            $("#primaryGrid").datagrid('reload');
            $("#editForm").form('reset');
            $("#editWin").window('close');
        })

    });

    //取消
    $("#cancelBtn").on('click', function () {
        $("#editForm").form('reset');
        $("#editWin").window('close');
    });
    $("#clearBtn").on('click', function () {
        $("#userId").textbox('setValue', '');
    });

});