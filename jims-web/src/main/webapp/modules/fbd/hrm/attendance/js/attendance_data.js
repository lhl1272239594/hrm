/**
 *考勤记录
 * @author yangchen
 * @version 2016-08-18
 */
var basePath = "/service";
var userName = "";
var page = '1';
var rows = '30';
var attendanceDataVo = {};
var sumDays = "0";
var dg;
var d;
var flag;
var editIndex = undefined;
var fileName = '';
var fileUrl = '';
var search=false;

$(function () {

    //定义组织ID
    var orgId = parent.config.org_Id;


    //考勤数据查询
    $("#attDataGrid").datagrid({
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
        pageList: [10, 15, 30, 50],//可以设置每页记录条数的列表
        collapsible: false,//是否可折叠的
        url: basePath + '/attendanceData/attendanceData-list?orgId=' + orgId + '&userName=' + userName,
        remoteSort: false,
        singleSelect: false,//是否单选
        rownumbers: true,//行号
        columns: [[
            {field: 'orgId', title: '', hidden: true},
            {field: 'attDataId', title: '', hidden: true},
            {field: 'ck', title: '', checkbox: true},
            {field: 'deptName', title: '员工科室', width: '20%', align: 'center'},
            {field: 'userName', title: '员工姓名', width: '18%', align: 'center'},
            {field: 'checkDate', title: '打卡日期', width: '20%', align: 'center',formatter: formatDatebox},
            {field: 'checkInTime', title: '上班打卡时间', width: '20%', align: 'center'},
            {field: 'checkOutTime', title: '下班打卡时间', width: '20%', align: 'center'}

        ]],
        onLoadSuccess:function(data){

        }
    });

    $("#attDataGrid").datagrid('getPager').pagination({
        pageSize: 30,
        pageList: [10, 20, 30, 40, 50], //可以设置每页记录条数的列表
        displayMsg: '当前显示从第{from}条到第{to}条，共{total}条记录',
        onSelectPage: function (pageNumber, pageSize) {
            var state = $('#attDataGrid').data('datagrid');
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
        userName = $("#userName").textbox('getValue');

        searchData(page, rows);

    });


    var searchData= function (page,rows){
            $("#attDataGrid").datagrid('reload', basePath + '/attendanceData/attendanceData-list?userName=' + userName + '&orgId=' + orgId + '&page=' + page + '&rows=' + rows);
            if(search){
                search=false;
                $("#attDataGrid").datagrid('getPager').pagination('select',1);
            }
    }

    //配置窗口
    $("#editWin").window({
        title: '打卡数据导入',
        closed: true,
        minimizable: false,
        modal: true,
        onClose: function () {
            $('#attDataGrid').datagrid('clearSelections');

        },
        onOpen: function () {

        }
    });
    //新增
    $("#addBtn").on('click', function () {
        $("#editWin").window('open');
        $("#flag").val('0');
        $("#id").val('999');
        $("#myFiles").val('');
    });

    $("#saveBtn").on("click", function () {

    });

    $("#saveBtn").on("click", function () {
        var path = document.getElementById("myFiles").value;
        var pos1 = path.lastIndexOf("\\");
        var pos2 = path.lastIndexOf(".");
        contractName = path.substring(pos1 + 1, pos2);
        var list ="xls,XLSX";

        var ext=path.substr(path.lastIndexOf('.')+1);

        var rs=list.indexOf(ext);

        //判断扩展名是否允许
        if(rs<0){
            $.messager.alert('提示', '不允许上传该类型文件！', 'info');
            return
        };


        var oData = new FormData(document.getElementById("editForm"));
        $.ajax({
            url: '/service/upload/upload-file?fileType=KaoQinShuJu&orgId=' + orgId,
            type: 'POST',
            data: oData,
            async: false,
            cache: false,
            contentType: false,
            processData: false,
            success: function (data) {
                fileName = data.fileName;
                fileUrl = data.fileUrl;
                save();
            },
            error: function (data) {

            }
        });
});
    /**
     * 删除
     */
    $("#delBtn").on('click', function () {
        var rows = $("#attDataGrid").datagrid('getSelections');
        if (rows.length!=0) {
            $.messager.confirm('提示', '确定要进行删除操作吗', function (r) {
                if (r) {
                    $.postJSON(basePath + "/attendanceData/del", JSON.stringify(rows), function (data) {
                        $('#attDataGrid').datagrid('reload');
                        rows.length = 0;
                        $("#attDataGrid").datagrid('clearSelections');
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
    });
    $("#clearBtn").on('click', function () {
        $("#userName").textbox('setValue', '');
        page='1';
    });
    var save = function () {
        id = $("#id").val();
        attendanceDataVo.attDataId = id;
        attendanceDataVo.fileName = fileName;
        attendanceDataVo.fileUrl = fileUrl;
        attendanceDataVo.orgId = parent.config.org_Id;
        $.postJSON(basePath + "/attendanceData/merge", JSON.stringify(attendanceDataVo), function (data) {
            if(data.code=="2"){
                var text=data.data;
                $.messager.alert('提示', text, 'info');
            }
            else{
                $("#editWin").window('close');
                $("#editForm").form('reset');
                $("#attDataGrid").datagrid('reload');
            }
        })
    };
});

