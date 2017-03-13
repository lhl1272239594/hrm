/**
 * 工资级别项目
 * @author
 * @version 2016-08-22
 */

$(function () {
    var orgId= parent.config.org_Id;
    /*var deptId=parent.config.deptId;
    var roleId=parent.config.roleId;*/
    var userName=parent.config.userName;
    var flag = 0;//增加修改状态
    var editIndex;
    var stopEdit = function () {
        if (editIndex || editIndex == 0) {
            $("#dataGrid").datagrid('endEdit', editIndex);
            editIndex = undefined;
        }
    };
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
        pageList: [10, 15, 30, 50],//可以设置每页记录条数的列表
        loadMsg: '数据正在加载中，请稍后.....',
        collapsible: false,//是否可折叠的
        //url: basePath +"/salary/list_level?orgId=" + orgId, /*+ '&levelName=' + levelName + '&typeId=' + typeId*/
        remoteSort: false,
        idField: 'lpId',
        singleSelect: true,//是否单选
        rownumbers: true,//行号
        columns: [[
            {field: 'levelName', title: '工资级别',width:'10%', align: 'center'},
            {field: 'orgId', title: '组织机构编码', hidden: true},
            {field: 'lpId', title: '关系编码', hidden: true},
            {field: 'levelId', title: '工资级别编码', hidden: true},
            {field: 'projectId', title: '工资项目',width:'10%', align: 'center'},
            {
                field: 'enableFlag',width:'7%', title: '状态', align: 'center',
                formatter: function (value, row, index) {
                    if (value == "1") {
                        return "启用";
                    }
                    if (value == "0") {
                        return "停用";
                    }
                }
            },
            {field: 'createBy', width:'10%',title: '创建人', align: 'center'},
            {field: 'createDate',width:'10%', title: '创建时间', align: 'center'},
            {field: 'updateBy',width:'10%', title: '更新人', align: 'center'},
            {field: 'updateDate', width:'10%',title: '更新时间', align: 'center'}

        ]]
    });

    /**
     * 新增弹出框
     */
    $("#newDialog").dialog({
        title: '增加',
        modal: true,
        closed:true

    });

    /**
     * 新增：工资级别项目对应关系
     */
    $("#LEVEL_ID").combobox({
        valueField: 'levelId',
        textField: 'levelName',
        loadMsg: '数据正在加载...',
        url: basePath + '/salary/level-downlist?orgId=' + orgId ,
        mode: 'remote',
        method: 'GET'

    });
    /**
     * 新增：工资项目
     */
    $("#PROJECT_ID").combobox({
        idField: 'value',
        textField: 'label',
        loadMsg: '数据正在加载...',
        url: '/service/dict/find-list-by-type?type=' + 'SALARY_PROJECT',
        mode: 'remote',
        method: 'GET',
        style:'height:auto'
    });
    /**
     * 启用状态
     */
   /* $("#ENABLE_FLAG").combobox({
        idField: 'value',
        textField: 'label',
        loadMsg: '数据正在加载...',
        url: '/service/dict/find-list-by-type?type=' + 'ENABLE_FLAG',
        mode: 'remote',
        method: 'GET'
    });
*/


    //查询条件：工资级别
    $("#levelId").combobox({
        panelWidth: '90px',
        panelHeight: 'auto',
        valueField: 'levelId',
        textField: 'levelName',
        value:'请选择',
        loadMsg: '数据正在加载...',
        url: basePath + '/salary/level-downlist?orgId=' + orgId ,
        mode: 'remote',
        method: 'GET'

    });
    //查询条件：工资项目
    $("#projectId").combobox({
        panelWidth: '90px',
        panelHeight: 'auto',
        idField: 'value',
        textField: 'label',
        value:'请选择',
        loadMsg: '数据正在加载...',
        url: '/service/dict/find-list-by-type?type=' + 'SALARY_PROJECT',
        mode: 'remote',
        method: 'GET'

    });
    /**
     * 查询条件搜索
     */
    $("#searchBtn").on("click", function () {
        //获取级别Id
        var levelId = $("#levelId").combobox('getValue');
        //获取项目Id
        var projectId = $("#projectId").combobox('getValue');
        $.get(basePath + '/salary-lp/lp-list?orgId=' + orgId + '&levelId=' + levelId + '&projectId=' + projectId, function (data) {
            $("#dataGrid").datagrid('loadData', data);
        });
    });

    /**
     * 添加
     */
    $("#addBtn").on('click', function () {
        //reset();
        flag = 1;
        $("#newDialog").dialog("setTitle","增加").dialog("open");
        /*$("#ENABLE_FLAG").combobox({panelHeight:44});
        $("#ENABLE_FLAG").combobox("setValue","1");*/
        $("#LEVEL_ID").combobox("setValue","");
        $("#LEVEL_ID").combobox({panelHeight:120});
        $("#PROJECT_ID").combobox("setValue","");
        $("#PROJECT_ID").combobox({panelHeight:100});
        $("#LPID").val("");


    });
    /**
     * 修改
     */
    $("#editBtn").on('click', function () {
        //reset();
        flag = 0;
        var row = $("#dataGrid").datagrid("getSelected");
        if(!row){
            $.messager.alert("提示","请选择一条数据",'error');
            return;
        }
        $("#newDialog").dialog("setTitle","工资级别修改").dialog("open");
        $("#LPID").val(row.lpId);

        $("#LEVEL_ID").combobox("setValue",row.levelId);
        $("#PROJECT_ID").combobox("setValue",row.projectId);
        /*$("#ENABLE_FLAG").combobox("setValue",row.enableFlag);*/

    });

    /**
     * 启用
     */
    $("#okBtn").on('click', function () {
        //stopEdit();
        var row = $("#dataGrid").datagrid('getSelected');
        if (row == null) {
            $.messager.alert("系统提示", "请选择要启用的数据");
            return;
        }
        $.messager.confirm('系统提示', '确定要进行启用操作吗', function (r) {
            if (r) {
               $.postJSON(basePath + "/salary-lp/changeUp-enableFlag?lpId="+row.lpId,row.lpId,function (data) {
                    /*$.messager.alert('系统提示', '删除成功', 'info');*/
                    loadDict();
                })
            }
        });
        loadDict();

    });

    /**
     * 停用
     */
    $("#noBtn").on('click', function () {
        //stopEdit();
        var row = $("#dataGrid").datagrid('getSelected');
        if (row == null) {
            $.messager.alert("系统提示", "请选择要停用的数据");
            return;
        }

        $.messager.confirm('系统提示', '确定要进行停用操作吗', function (r) {
            if (r) {
                $.postJSON(basePath + "/salary-lp/changeDown-enableFlag?lpId="+row.lpId,row.lpId,function (data) {
                    /*$.messager.alert('系统提示', '删除成功', 'info');*/
                    loadDict();
                })
            }
        });
        loadDict();

    });

    /**
     * 删除
     */
    $("#delBtn").on('click', function () {
        //stopEdit();
        var row = $("#dataGrid").datagrid('getSelected');
        if (row == null) {
            $.messager.alert("系统提示", "请选择要删除的数据");
            return;
        }
        if (!row.lpId) {
            //判断是否是新加项目
            var index = $("#dataGrid").datagrid('getRowIndex', row);

            $.messager.confirm('系统提示', '确定要进行删除操作吗', function (r) {
                if (r) {
                    $("#dataGrid").datagrid('deleteRow', index);
                }
            });

        } else {
            $.messager.confirm('系统提示', '确定要进行删除操作吗', function (r) {
                if (r) {
                    $.postJSON(basePath + "/salary-lp/del-lp", row.lpId, function (data) {
                       /* $.messager.alert('系统提示', '删除成功', 'info');*/
                        loadDict();
                        row==null;
                        $('#dataGrid').datagrid('clearSelections');
                    })
                }
            });
        }

    });

    /**
     * 新增修改保存
     */
    $("#submitBtn").on('click', function () {
        if(!$("#LEVEL_ID").combobox("getValue")){
            $.messager.alert("提示","请选择工资级别",'error');
            return ;
        }
        if(!$("#PROJECT_ID").combobox("getValue")){
            $.messager.alert("提示","请选择工资项目",'error');
            return ;
        }

        /*var insertData = $("#dataGrid").datagrid("getChanges", "inserted");
         var updateData = $("#dataGrid").datagrid("getChanges", "updated");*/
        var salarylpVo = {};
        /* salaryVo.inserted = insertData;
         salaryVo.updated = updateData;*/
        salarylpVo.orgId = parent.config.org_Id;
        salarylpVo.userName = parent.config.userName;
        salarylpVo.lpId = $("#LPID").val();

        salarylpVo.levelId = $("#LEVEL_ID").combobox('getValue');
        salarylpVo.projectId = $("#PROJECT_ID").combobox('getValue');
        //salarylpsVo.enableFlag = $("#ENABLE_FLAG").combobox('getValue')
        //判断是否已存在相同名称数据
        $.get(basePath +"/salary-lp/if-lp-exist?orgId=" + orgId+"&levelId="+$("#LEVEL_ID").combobox("getValue")+"&projectId="+$("#PROJECT_ID").textbox("getValue"),
            function (data) {
                var list = eval(data);
                for (var i = 0; i < 1; i++)
                {
                    var num=list[i]['num'];
                }
                if(num=='1'||num>'1'){
                    var str = '该级别工资项目对应关系已存在！';
                    $.messager.alert("系统提示", str);
                    return
                }
                {
                    //如果不重复，执行保存代码
                    $.postJSON(basePath + "/salary-lp/merge", JSON.stringify(salarylpVo), function (data) {
                        if(data.data=="success")
                        {
                            //$.messager.alert('系统提示', '保存成功', 'info');
                            $('#newDialog').window('close');
                            loadDict();
                        }
                        else{
                            $.messager.alert('系统提示', '保存失败', 'info');
                        }
                    })

                }
            });





    });


    /**
     * 加载工资级别项目金额数据
     */
    var loadDict = function () {
        var levelId='';
        var projectId='';
        $.get(basePath + '/salary-lp/lp-list?orgId=' + orgId + '&levelId=' + levelId + '&projectId=' + projectId , function (data) {
            $("#dataGrid").datagrid('loadData', data);
        });
    };
    /**
     * 清空查询条件
     */
    $("#clearBtn").on("click", function () {
        clearKey();
    });
    /**
     * 清空方法
     */
    var clearKey = function () {
        // $("#LEVEL_ID").textbox("setValue","");
        //$("#LEVEL_NAME").textbox("setValue","");
        $("#levelId").combobox("setValue","请选择");
        levelId = '';
        $("#projectId").combobox("setValue","请选择");
        projectId = '';
        // $("#ENABLE_FLAG").combobox("setValue","y");
        // $("#TYPE_ID").combobox("setValue","");

    };

    loadDict();


});