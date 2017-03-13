/**
 * 工资组成
 * @author
 * @version 2016-08-22
 */
var page='1';
var rows='30';
var orgId= parent.config.org_Id;
var personId= parent.config.persion_Id;
var partName = '';
var salaryPart = {};
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
        url: basePath +'/salary-part/part-list?orgId=' + orgId + '&partName=' + partName,
        remoteSort: false,
        //idField: 'partId',
        singleSelect: false,//是否单选
        rownumbers: true,//行号
        columns: [[
            {field: 'ck', title: '', checkbox: true},
            {field: 'partId', title: '工资组成编号', hidden: true},
            {field: 'partName', title: '组成部分名称', width:'20%', align: 'center'},
            {field: 'orgId', title: '组织机构编码', hidden: true},
            {
                field: 'flag', title: '分类', width:'10%', align: 'center',
                formatter: function (value, row, index) {
                    if (value == "1") {
                        return "社保基数";
                    }
                    if (value == "0") {
                        return "普通工资";
                    }
                }
            },
            {field: 'delFlag', title: '删除标识', hidden: true},
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
        title: '工资组成部分增加',
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
        var partName = $("#partName").textbox('getValue');
        $("#dataGrid").datagrid('reload', basePath + '/salary-part/part-list?orgId=' + orgId + '&partName=' + partName+ '&page=' + page+ '&rows=' + rows);
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
        $("#newDialog").dialog("setTitle","工资组成部分增加").dialog("open");
            $("#PART_ID").val('');
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
            $.messager.alert("提示","请选择要修改的数据！",'info');
            return;
        }
        if (row1.length != 1) {
            $.messager.alert("提示", "请选择一条数据进行操作!","info");
            return;
        }
        //alert(row);
        $("#newDialog").dialog("setTitle","工资组成部分修改").dialog("open");
        $("#PART_ID").val(row.partId);
        $("#PART_NAME").textbox("setValue",row.partName);
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
                    $.postJSON(basePath + "/salary-part/enableFlag",JSON.stringify(row), function (data) {
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
            $.messager.confirm('提示', '确定要进行停用操作吗', function (r) {
                if (r) {
                    $.postJSON(basePath + "/salary-part/enableFlag",JSON.stringify(row), function (data) {
                        $('#dataGrid').datagrid('reload');
                        row.length = 0;
                        $("#dataGrid").datagrid('clearSelections');
                    })
                }
            });
        }
    });


    /**
     * 社保基数匹配
     */
    $("#matchBtn").on('click', function () {
        //stopEdit();
        var row = $("#dataGrid").datagrid('getSelections');
        if (row == null||row.length == 0) {
            $.messager.alert("提示", "请选择要设为社保基数的数据!","info");
            return;
        }
        if (row.length != 1) {
            $.messager.alert("提示", "请选择一条数据进行操作!","info");
            return;
        }
        if (row.length>0) {
            for (var i = 0; i < row.length; i++) {
                row[i].flag = '1';
            }
            $.get(basePath +"/salary-part/if-sb-exist?orgId="+orgId,
                function (data) {
                    var list = eval(data);
                    for (var i = 0; i < 1; i++) {
                        var num = list[i]['num'];
                    }
                    if (num == '1' || num > '1') {
                        var str = '已存在社保基数，请先将其恢复为普通工资后再设置社保基数！';
                        $.messager.alert("提示", str, "info");
                        return
                    }

                    {
                        $.messager.confirm('提示', '确定要设置该项工资组成部分为社保计算基数么？', function (r) {
                            if (r) {
                                $.postJSON(basePath + "/salary-part/match-part", JSON.stringify(row), function (data) {
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
     * 恢复普通工资
     */
    $("#backBtn").on('click', function () {
        //stopEdit();
        var row = $("#dataGrid").datagrid('getSelections');
        if (row == null) {
            $.messager.alert("提示", "请选择要恢复为普通工资的数据项！","info");
            return;
        }
        if (row.length != 1) {
            $.messager.alert("提示", "请选择一条数据进行操作！","info");
            return;
        }
        if (row.length>0) {
            for (var i = 0; i < row.length; i++) {
                row[i].flag = '0';
            }
            $.messager.confirm('提示', '确定要设定所选项为普通工资项吗？', function (r) {
                if (r) {
                    $.postJSON(basePath + "/salary-part/back-normal",JSON.stringify(row), function (data) {
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
        //判断是否在工资公式中被占用，若被占用不能删除。
        $.postJSON(basePath + "/salary-part/if-occupy", JSON.stringify(row),
            function (data) {
                if (data.code == "yes") {
                        $.messager.alert('提示', '该组成部分已在工资计算公式中被使用，请先删除对应的工资计算公式数据！', 'info');
                        return;
                }
                {
                    $.messager.confirm('提示', '确定要批量删除所选中的数据么?', function (r) {
                        if (r) {
                            $.postJSON(basePath + "/salary-part/del-part", JSON.stringify(row), function (data) {
                                /*$.messager.alert('系统提示', '删除成功', 'info');*/
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
        if(!$("#PART_NAME").textbox("getValue")||$("#PART_NAME").textbox("getValue").indexOf(" ") >=0){
            $.messager.alert("提示","请输入有效的工资组成名称，不能包含空格！",'info');
            return ;
        }
        if($("#PART_NAME").textbox("getValue").length>20){
            $.messager.alert("提示","工资组成名称内容输入过长！",'info');
            return;
        }
        salaryPart.orgId = parent.config.org_Id;
        salaryPart.createBy = parent.config.persion_Id;
        salaryPart.updateBy = parent.config.persion_Id;
        salaryPart.partId = $("#PART_ID").val();
        salaryPart.partName = $("#PART_NAME").textbox('getValue');
        //判断是否已存在相同名称数据
        $.get(basePath +"/salary-part/if-exist?orgId="+orgId+"&partName="+$("#PART_NAME").textbox("getValue")+"&partId="+$("#PART_ID").val(),
            function (data) {
                var list = eval(data);
                for (var i = 0; i < 1; i++)
                {
                    var num=list[i]['num'];
                }
                if(num=='1'||num>'1'){
                    var str = '该工资组成部分已存在！';
                    $.messager.alert("提示", str,"info");
                    return
                }
                {
                    //如果不重复，执行保存代码
                    $.postJSON(basePath + "/salary-part/merge", JSON.stringify(salaryPart), function (data) {
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
        $("#partName").textbox('clear');
        partName = '';
    };
    /**
     * 关闭
     */
    $("#close").on('click', function ()  {
        $('#newDialog').dialog('close');
        $("#dataGrid").datagrid('clearSelections');
    });


});