/**
 * 工资级别
 * @author 
 * @version 2016-08-22
 */
var page='1';
var rows='30';
var orgId= parent.config.org_Id;
var levelName ='';
var typeId = '请选择';
$(function () {
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
        //pageList: [10, 20, 30, 40, 50],//可以设置每页记录条数的列表
        loadMsg: '数据正在加载中，请稍后.....',
        collapsible: false,//是否可折叠的
        url: basePath +'/salary/level-list?orgId=' + orgId + '&levelName=' + levelName + '&typeId=' + typeId,
        remoteSort: false,
        idField: 'levelId',
        singleSelect: true,//是否单选
        rownumbers: true,//行号
        columns: [[
            {field: 'levelId', title: '工资级别编号', hidden: true},
            {field: 'typeId', title: '工资类别', width:'10%', align: 'center'},
            {field: 'orgId', title: '组织机构编码', hidden: true},
            {field: 'levelName', title: '工资级别名称',width:'10%', align: 'center'},
            {
                field: 'enableFlag', title: '状态', width:'7%', align: 'center',
                formatter: function (value, row, index) {
                    if (value == "1") {
                        return "启用";
                    }
                    if (value == "0") {
                        return "停用";
                    }
                }
            },
            {field: 'createBy', title: '创建人', width:'8%', align: 'center'},
            {field: 'createDate', title: '创建时间', width:'10%',align: 'center'},
            {field: 'updateBy', title: '更新人', width:'7%', align: 'center'},
            {field: 'updateDate', title: '更新时间', width:'10%', align: 'center'}
        ]]
    });
    $("#dataGrid").datagrid('getPager').pagination({
        pageList: [10, 20, 30, 40, 50], //可以设置每页记录条数的列表
        displayMsg: '当前显示从第{from}条到第{to}条，共{total}条记录'


    });

    /**
     * 工资级别新增弹出框
     */
    $("#newDialog").dialog({
        title: '工资级别增加',
        modal: true,
        closed:true

    });

    /**
     * 新增：工资类别
     */
    $("#TYPE_ID").combobox({
        idField: 'value',
        textField: 'label',
        loadMsg: '数据正在加载...',
        url: '/service/dict/find-list-by-type?type=' + 'SALARY_TYPE',
        mode: 'remote',
        method: 'GET'

    });
    /**
     * 启用状态
     */
    /*$("#ENABLE_FLAG").combobox({
        idField: 'value',
        textField: 'label',
        loadMsg: '数据正在加载...',
        url: '/service/dict/find-list-by-type?type=' + 'ENABLE_FLAG',
        mode: 'remote',
        method: 'GET'
    });*/



    //查询条件：工资类别
    $("#typeId").combobox({
        panelWidth: '90px',
        panelHeight: 'auto',
        idField: 'value',
        textField: 'label',
        value:'请选择',
        loadMsg: '数据正在加载...',
        url: '/service/dict/find-list-by-type?type=' + 'SALARY_TYPE',
        mode: 'remote',
        method: 'GET'

    });
    /**
     * 查询条件搜索
     */
    $("#searchBtn").on("click", function () {

        /* $.get(basePath + '/salary/level-list?orgId=' + orgId + '&levelName=' + levelName + '&typeId=' + typeId, function (data) {
         $("#dataGrid").datagrid('loadData', data);
         });*/
        $("#dataGrid").datagrid('getPager').pagination({
            pageList: [10, 20, 30, 40, 50], //可以设置每页记录条数的列表
            displayMsg: '当前显示从第{from}条到第{to}条，共{total}条记录',
            onSelectPage: function (pageNumber, pageSize) {
                var state = $('#dataGrid').data('datagrid');
                var opts = state.options;
                page=opts.pageNumber = pageNumber;
                rows=opts.pageSize = pageSize;
                searchData(page,rows);
                return;

            }

        });
        searchData(page,rows);
    });

    var searchData= function (page,rows){
        //获取级别名称
        var levelName = $("#levelName").textbox('getValue');
        //获取类型id
        var typeId = $("#typeId").combobox('getValue');
        $.get(basePath + '/salary/level-list?orgId=' + orgId + '&levelName=' + levelName + '&typeId=' + typeId+ '&page=' + page+ '&rows=' + rows, function (data) {
            $("#dataGrid").datagrid('loadData', data);
        });
    }
    /**
     * 清空查询条件
     */
    $("#clearBtn").on("click", function () {
        clearKey();
    });
    /**
     * 添加
     */
    $("#addBtn").on('click', function () {
        //reset();
        flag = 1;
        $("#newDialog").dialog("setTitle","工资级别添加").dialog("open");
       /* $("#ENABLE_FLAG").combobox({panelHeight:44});
        $("#ENABLE_FLAG").combobox("setValue","1");*/
        $("#TYPE_ID").combobox("setValue","");
        $("#TYPE_ID").combobox({panelHeight:80});
        $("#LEVEL_NAME").textbox("setValue","");
        $("#LEVEL_ID").textbox("setValue","");
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
        $("#LEVEL_ID").val(row.levelId);
        $("#LEVEL_NAME").textbox("setValue",row.levelName);
        $("#TYPE_ID").combobox({panelHeight:80});
        $("#TYPE_ID").combobox("setValue",row.typeId);
        /*$("#ENABLE_FLAG").combobox({panelHeight:44});
        $("#ENABLE_FLAG").combobox("setValue",row.enableFlag);
*/
    });

    /**
     * 启用
     */
    $("#okBtn").on('click', function () {
        //stopEdit();
        var row = $("#dataGrid").datagrid('getSelected');
        var  a = row.enableFlag;
        var  b = row.levelId;
        if (row == null) {
            $.messager.alert("系统提示", "请选择要启用的数据");
            return;
        }
            $.messager.confirm('系统提示', '确定要进行启用操作吗', function (r) {
                if (r) {
                    $.postJSON(basePath + "/salary/changeUp-enableFlag?levelId="+row.levelId,row.levelId,function (data) {
                        /*$.messager.alert('系统提示', '删除成功', 'info');*/
                        $('#dataGrid').datagrid('reload');
                        row.length=0;
                        $("#dataGrid").datagrid('clearSelections');
                    })
                }
            });


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
                $.postJSON(basePath + "/salary/changeDown-enableFlag?levelId="+row.levelId,row.levelId,function (data) {
                    $('#dataGrid').datagrid('reload');
                    row.length=0;
                    $("#dataGrid").datagrid('clearSelections');
                })
            }
        });

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
        if (!row.levelId) {
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
                    $.postJSON(basePath + "/salary/del_level", row.levelId, function (data) {
                        /*$.messager.alert('系统提示', '删除成功', 'info');*/
                        $('#dataGrid').datagrid('reload');
                        row.length=0;
                        $("#dataGrid").datagrid('clearSelections');
                    })
                }
            });
        }

    });

    /**
     * 新增修改保存
     */
    $("#submitBtn").on('click', function () {
        if(!$("#LEVEL_NAME").textbox("getValue")){
            $.messager.alert("提示","请输入必填项",'error');
            return ;
        }
        if(!$("#TYPE_ID").combobox("getValue")){
            $.messager.alert("提示","请选择工资类别",'error');
            return ;
        }
        /*var insertData = $("#dataGrid").datagrid("getChanges", "inserted");
        var updateData = $("#dataGrid").datagrid("getChanges", "updated");*/
        var salaryVo = {};
       /* salaryVo.inserted = insertData;
        salaryVo.updated = updateData;*/
        salaryVo.orgId = parent.config.org_Id;
        salaryVo.levelId = $("#LEVEL_ID").val();
        salaryVo.levelName = $("#LEVEL_NAME").textbox('getValue');
        salaryVo.typeId = $("#TYPE_ID").combobox('getValue');
        /*salaryVo.enableFlag = $("#ENABLE_FLAG").combobox('getValue');*/
        //判断是否已存在相同名称数据
        $.get(basePath +"/salary/if-exist?orgId=" + orgId+"&typeId="+$("#TYPE_ID").combobox("getValue")+"&levelName="+$("#LEVEL_NAME").textbox("getValue"),
            function (data) {
                var list = eval(data);
                for (var i = 0; i < 1; i++)
                {
                    var num=list[i]['num'];
                }
                if(num=='1'||num>'1'){
                    var str = '该类别下已存在相同级别名称！';
                    $.messager.alert("系统提示", str);
                    return
                }
                {
                    //如果不重复，执行保存代码
                    $.postJSON(basePath + "/salary/merge", JSON.stringify(salaryVo), function (data) {
                        if(data.data=="success")
                        {
                           // $.messager.alert('系统提示', '保存成功', 'info');
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
     * 加载工资级别数据
     */
   /* var loadDict = function () {
        var levelName='';
        var typeId='';
        $.get(basePath + '/salary/level-list?orgId=' + orgId + '&levelName=' + levelName + '&typeId=' + typeId , function (data) {
            $("#dataGrid").datagrid('loadData', data);
        });
    };*/
    /**
     * 清空方法
     */
    var clearKey = function () {
       // $("#LEVEL_ID").textbox("setValue","");
        //$("#LEVEL_NAME").textbox("setValue","");
        $("#levelName").textbox('clear');
        levelName = '';
        $("#typeId").combobox("setValue","请选择");
        typeId = '';
       // $("#ENABLE_FLAG").combobox("setValue","y");
       // $("#TYPE_ID").combobox("setValue","");

    };

    //loadDict();

});