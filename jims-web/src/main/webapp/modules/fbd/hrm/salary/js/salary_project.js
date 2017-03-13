/**
 * 工资项目
 * @author
 * @version 2016-08-22
 */
var page='1';
var rows='30';
var orgId= parent.config.org_Id;
var personId= parent.config.persion_Id;
var projectName = '';
var salaryProject = {};
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
        url: basePath +'/salary-project/project-list?orgId=' + orgId + '&projectName=' + projectName,
        remoteSort: false,
        //idField: 'typeId',
        singleSelect: false,//是否单选
        rownumbers: true,//行号
        columns: [[
            {field: 'ck', title: '', checkbox: true},
            {field: 'typeId', title: '项目编号', hidden: true},
            {field: 'projectName', title: '项目名称', width:'20%', align: 'center'},
            {field: 'orgId', title: '组织机构编码', hidden: true},
            {field: 'delFlag', title: '删除标识', hidden: true},
            /*{
                field: 'enableFlag', title: '状态', width:'8%', align: 'center',
                formatter: function (value, row, index) {
                    if (value == "1") {
                        return "启用";
                    }
                    if (value == "0") {
                        return "停用";
                    }
                }
            },*/
            {field: 'createBy', title: '创建人', width:'9%', align: 'center'},
            {field: 'createDeptname', title: '创建科室', width:'10%', align:'center'},
            {field: 'createDept', title: '创建科室编号', hidden:true},
            {field: 'createDate', title: '创建时间', width:'15%',align: 'center'},
            {field: 'updateBy', title: '更新人', width:'9%', align: 'center'},
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
     * 工资项目新增弹出框
     */
    $("#newDialog").dialog({
        title: '工资项目增加',
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
        var projectName = $("#projectName").textbox('getValue');
        $("#dataGrid").datagrid('reload', basePath + '/salary-project/project-list?orgId=' + orgId + '&projectName=' + projectName+ '&page=' + page+ '&rows=' + rows);
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
        $("#newDialog").dialog("setTitle","工资项目增加").dialog("open");
        $("#PROJECT_ID").val('');
        $("#PROJECT_NAME").textbox("setValue",'');
        $("#newForm").form('reset');
    });
    /**
     * 修改
     */
    $("#editBtn").on('click', function () {
        //reset();
        flag = 0;
        var row = $("#dataGrid").datagrid("getSelected");
        var row1 = $("#dataGrid").datagrid("getSelections");
        if(!row){
            $.messager.alert("提示","请选择要修改的数据!",'info');
            return;
        }
        if (row1.length != 1) {
            $.messager.alert("提示", "请选择一条数据进行操作!","info");
            return;
        }
        $("#newDialog").dialog("setTitle","工资项目修改").dialog("open");
        $("#PROJECT_ID").val(row.projectId);
        $("#PROJECT_NAME").textbox("setValue",row.projectName);
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
            $.messager.confirm('提示', '确定启用选中的数据吗？', function (r) {
                if (r) {
                    $.postJSON(basePath + "/salary-project/enableFlag",JSON.stringify(row), function (data) {
                        /*$.messager.alert('系统提示', '删除成功', 'info');*/
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
            $.messager.confirm('提示', '确定停用选中的数据吗？', function (r) {
                if (r) {
                    $.postJSON(basePath + "/salary-project/enableFlag",JSON.stringify(row), function (data) {
                        /*$.messager.alert('系统提示', '删除成功', 'info');*/
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
        if (row.length>0) {
            for (var i = 0; i < row.length; i++) {
                row[i].delFlag = '1';
            }
            //判断是否在工资项目金额中被占用，若被占用不能删除。
            $.postJSON(basePath + "/salary-project/if-occupy", JSON.stringify(row),
                function (data) {
                    if (data.code == "yes") {
                        $.messager.alert('提示', '该项目已在工资项目金额中被使用，请先删除工资项目金额中该项目的数据！', 'info');
                        return;
                    }
                    {
                        $.messager.confirm('提示', '确定要批量删除所选中的数据么？', function (r) {
                            if (r) {
                                $.postJSON(basePath + "/salary-project/del-project", JSON.stringify(row), function (data) {
                                    /*$.messager.alert('系统提示', '删除成功', 'info');*/
                                    $('#dataGrid').datagrid('reload');
                                    row.length = 0;
                                    $("#dataGrid").datagrid('clearSelections');
                                })
                            }
                        });
                    }
                });
        }
    });

    /**
     * 新增修改保存
     */
    $("#submitBtn").on('click', function () {
        if(!$("#PROJECT_NAME").textbox("getValue")||$("#PROJECT_NAME").textbox("getValue").indexOf(" ") >=0){
            $.messager.alert("提示","请输入有效的项目名称，不能包含空格！",'info');
            return ;
        }
        if($("#PROJECT_NAME").textbox("getValue").length>20){
            $.messager.alert("提示","工资项目名称内容输入过长！",'info');
            return;
        }
        salaryProject.orgId = parent.config.org_Id;
        salaryProject.createBy = parent.config.persion_Id;
        salaryProject.updateBy = parent.config.persion_Id;
        salaryProject.projectId = $("#PROJECT_ID").val();
        salaryProject.projectName = $("#PROJECT_NAME").textbox('getValue');
        //判断是否已存在相同名称数据
        $.get(basePath +"/salary-project/if-exist?orgId="+orgId+"&projectName="+$("#PROJECT_NAME").textbox("getValue")+"&projectId="+$("#PROJECT_ID").val(),
            function (data) {
                var list = eval(data);
                for (var i = 0; i < 1; i++)
                {
                    var num=list[i]['num'];
                }
                if(num=='1'||num>'1'){
                    var str = '该项目已存在！';
                    $.messager.alert("提示", str,"info");
                    return
                }
                {
                    //如果不重复，执行保存代码
                    $.postJSON(basePath + "/salary-project/merge", JSON.stringify(salaryProject), function (data) {
                        if(data.data=="success")
                        {
                           // $.messager.alert('系统提示', '保存成功', 'info');
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
        $("#projectName").textbox('clear');
        projectName = '';
    };

    /**
     * 关闭
     */
    $("#close").on('click', function ()  {
        $('#newDialog').dialog('close');
        $("#dataGrid").datagrid('clearSelections');
    });

});