/**
 * 工资人员类别
 * @author
 * @version 2016-08-22
 */
var page='1';
var rows='30';
var orgId= parent.config.org_Id;
var personId= parent.config.persion_Id;
var typeName = '';
var salaryHumanType = {};
var search=false;
$(function () {
    /**
     * 数据列表
     */
    $("#dataGrid").datagrid({
        iconCls: 'icon-edit',//图标
        width: '100%',
        height: '100%',
        nowrap: false,
        fit: true,
        fitColumns: true,
        toolbar: '#tb',
        method: 'GET',
        striped: true,
        border: true,
        pagination: true,//分页控件
        pageSize: 30,
        pageList: [10, 20, 30, 40, 50],//可以设置每页记录条数的列表
        loadMsg: '数据正在加载中，请稍后.....',
        collapsible: false,//是否可折叠的
        url: basePath +'/salary-ht/ht-list?orgId=' + orgId + '&typeName=' + typeName,
        remoteSort: false,
        //idField: 'typeId',
        singleSelect: false,//是否单选
        rownumbers: true,//行号
        columns: [[
            {field: 'ck', title: '', checkbox: true},
            {field: 'typeId', title: '工资级别编号', hidden: true},
            {field: 'typeName', title: '工资级别名称', width:'20%', align: 'center'},
            {field: 'orgId', title: '组织机构编码', hidden: true},
            {field: 'delFlag', title: '删除标识', hidden: true},
            {
                field: 'enableFlag', title: '状态', width:'5%', align: 'center',
                formatter: function (value, row, index) {
                    if (value == "1") {
                        return "启用";
                    }
                    if (value == "0") {
                        return "停用";
                    }
                }
            },
            {field: 'createBy', title: '创建人', width:'7%', align: 'center'},
            {field: 'createDeptname', title: '创建科室', width:'10%', align:'center'},
            {field: 'createDept', title: '创建科室编号', hidden:true},
            {field: 'createDate', title: '创建时间', width:'15%',align: 'center'},
            {field: 'updateBy', title: '更新人', width:'7%', align: 'center'},
            {field: 'updateDate', title: '更新时间', width:'15%', align: 'center'}
        ]],
        onLoadSuccess:function(data){
            $('#newDialog').css('display','block');
            $('#tb').css('display','block');
            $("#dataGrid").datagrid('getPager').pagination({
                pageList: [10, 20, 30, 40, 50], //可以设置每页记录条数的列表
                displayMsg: '当前显示从第{from}条到第{to}条，共{total}条记录',
                onSelectPage: function (pageNumber, pageSize) {
                    var state = $('#dataGrid').data('datagrid');
                    var opts = state.options;
                    page=opts.pageNumber = pageNumber;
                    rows=opts.pageSize = pageSize;
                    searchData(page,rows);
                }
            });
        }
    });


    /**
     * 人员类别新增弹出框
     */
    $("#newDialog").dialog({
        title: '工资级别增加',
        modal: true,
        closed:true,
        collapsible: true,
        minimizable: false,
        maximizable: true,
        resizable: true
    });

    /**
     * 查询条件搜索
     */
    $("#searchBtn").on("click", function () {
        search=true;
        searchData(page,rows);
    });

    var searchData= function (page,rows){
        //获取级别名称
        var typeName = $("#typeName").textbox('getValue');
        $("#dataGrid").datagrid('reload', basePath + '/salary-ht/ht-list?orgId=' + orgId + '&typeName=' + typeName+ '&page=' + page+ '&rows=' + rows);
                if(search){
                    search=false;
                    if(page!='1') {
                        $("#dataGrid").datagrid('getPager').pagination('select', 1);
                    }
                }
                $("#dataGrid").datagrid('clearSelections');
    };
    /**
     * 清空查询条件
     */
    $("#clearBtn").on("click", function () {
        clearKey();
        page='1';
    });
    /**
     * 添加
     */
    $("#addBtn").on('click', function () {
        $("#dataGrid").datagrid('clearSelections');
        $("#newDialog").dialog("setTitle","工资级别增加").dialog("open");
        $("#TYPE_ID").val('');
        $("#TYPE_NAME").textbox("setValue",'');
        $("#newForm").form('reset');
    });
    /**
     * 修改
     */
    $("#editBtn").on('click', function () {
        var row = $("#dataGrid").datagrid("getSelected");
        var row1 = $("#dataGrid").datagrid("getSelections");
        if(!row){
            $.messager.alert("提示","请选择要修改的数据！",'info');
            return;
        }
        if (row1.length != 1) {
            $.messager.alert("提示", "请选择一条数据进行操作!","info");
            return;
        }
        $("#newDialog").dialog("setTitle","工资级别修改").dialog("open");
        $("#TYPE_ID").val(row.typeId);
        $("#TYPE_NAME").textbox("setValue",row.typeName);
    });

    /**
     * 启用
     */
    $("#okBtn").on('click', function () {
        //stopEdit();
        var row = $("#dataGrid").datagrid('getSelections');
        if (row == null||row.length == 0) {
            $.messager.alert("提示", "请选择要启用的数据!","info");
            return;
        }
        if (row.length>0) {
            for (var i = 0; i < row.length; i++) {
                row[i].enableFlag = '1';
            }
            $.messager.confirm('提示', '确定要进行启用操作吗', function (r) {
                if (r) {
                    $.postJSON(basePath + "/salary-ht/enableFlag",JSON.stringify(row), function (data) {
                        /*$.messager.alert('提示', '删除成功', 'info');*/
                        $('#dataGrid').datagrid('reload');
                        row.length = 0;
                        $("#dataGrid").datagrid('clearSelections');
                    })
                }
            });
        }

    });
    /**
     * 停用
     */
    $("#noBtn").on('click', function () {
        //stopEdit();
        var row = $("#dataGrid").datagrid('getSelections');
        if (row == null||row.length == 0) {
            $.messager.alert("提示", "请选择要停用的数据!","info");
            return;
        }
        if (row.length>0) {
            for (var i = 0; i < row.length; i++) {
                row[i].enableFlag = '0';
            }
            $.messager.confirm('提示', '确定要进行停用操作吗', function (r) {
                if (r) {
                    $.postJSON(basePath + "/salary-ht/enableFlag",JSON.stringify(row), function (data) {
                        $('#dataGrid').datagrid('reload');
                        row.length = 0;
                        $("#dataGrid").datagrid('clearSelections');
                    })
                }
            });
        }
    });
    /**
     * 删除
     */
    $("#delBtn").on('click', function () {
        //stopEdit();
        var row = $("#dataGrid").datagrid('getSelections');
        //var row1 = $("#dataGrid").datagrid('getSelected');
        if (row == null||row.length == 0||!row) {
            $.messager.alert("提示", "请选择要删除的数据!","info");
            return;
        }
        //判断所要删除的人员类别是否在工资项目金额中包含项目金额，若包含不能删除。
        $.postJSON(basePath + "/salary-ht/if-occupy", JSON.stringify(row),
            function (data) {
                if (data.code == "yes") {
                    $.messager.alert('提示', '该工资级别下包含工资项目金额，请先删除工资项目金额中对应数据！', 'info');
                    return;
                }
                {
                    $.messager.confirm('提示', '确定要批量删除所选中的数据么？', function (r) {
                        if (r) {
                            $.postJSON(basePath + "/salary-ht/del-ht", JSON.stringify(row), function (data) {
                                /*$.messager.alert('提示', '删除成功', 'info');*/
                                $('#dataGrid').datagrid('reload');
                                row.length = 0;
                                $("#dataGrid").datagrid('clearSelections');
                            })
                        }
                    });
                }
            });
    });

    /**
     * 新增修改保存
     */
    $("#submitBtn").on('click', function () {
        if(!$("#TYPE_NAME").textbox("getValue")||$("#TYPE_NAME").textbox("getValue").indexOf(" ") >=0){
            $.messager.alert("提示","请输入有效的工资级别名称，不能包含空格！",'info');
            return ;
        }
        if($("#TYPE_NAME").textbox("getValue").length>20){
            $.messager.alert("提示","工资级别名称内容输入过长！",'info');
            return;
        }
        salaryHumanType.orgId = parent.config.org_Id;
        salaryHumanType.createBy = parent.config.persion_Id;
        salaryHumanType.updateBy = parent.config.persion_Id;
        salaryHumanType.typeId = $("#TYPE_ID").val();
        salaryHumanType.typeName = $("#TYPE_NAME").textbox('getValue');
        //判断是否已存在相同名称数据
        $.get(basePath +"/salary-ht/if-exist?orgId="+orgId+"&typeName="+$("#TYPE_NAME").textbox("getValue")+"&typeId="+$("#TYPE_ID").val(),
            function (data) {
                var list = eval(data);
                for (var i = 0; i < 1; i++)
                {
                    var num=list[i]['num'];
                }
                if(num=='1'||num>'1'){
                    var str = '该工资级别已存在！';
                    $.messager.alert("提示", str,'info');
                    return
                }
                {
                    //如果不重复，执行保存代码
                    $.postJSON(basePath + "/salary-ht/merge", JSON.stringify(salaryHumanType), function (data) {
                        if(data.data=="success")
                        {
                           // $.messager.alert('提示', '保存成功', 'info');
                            $('#newDialog').window('close');
                            $("#dataGrid").datagrid('reload');
                            $("#newForm").form('reset');
                            $("#dataGrid").datagrid('clearSelections');
                        }
                        else{
                            $.messager.alert('提示', '保存失败', 'info');
                        }
                    })
                }
            });
    });



    /**
     * 清空方法
     */
    var clearKey = function () {
        $("#typeName").textbox('clear');
        typeName = '';
    };
    /**
     * 关闭
     */
    $("#close").on('click', function ()  {
        $('#newDialog').dialog('close');
        $("#dataGrid").datagrid('clearSelections');
    });

});

