/**
 * 人员技能设置
 * @author
 * @version 2016-09-22
 */
var orgId= parent.config.org_Id;
var deptId='';
var humanSkill = {};
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
        loadMsg: '数据正在加载中，请稍后.....',
        collapsible: false,//是否可折叠的
        url: basePath +'/peinfo/skill-list?orgId=' + orgId,
        remoteSort: false,
        //idField: 'timeId',
        singleSelect: true,//是否单选
        rownumbers: true,//行号
        columns: [[
            {field: 'skillId', title: '人员技能编号', hidden: true},
            /*{field: 'orgName', title: '机构', width:'20%', align: 'center'},
            {field: 'deptId', title: '科室编号', hidden: true},
            {field: 'deptName', title: '科室', hidden: true},*/
            {field: 'skill', title: '人员技能', width:'10%', align: 'center'},
            {field: 'orgId', title: '组织机构编码', hidden: true},
            {field: 'createBy', title: '创建人', width:'7%', align: 'center'},
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
                /*onSelectPage: function (pageNumber, pageSize) {
                    var state = $('#dataGrid').data('datagrid');
                    var opts = state.options;
                    page=opts.pageNumber = pageNumber;
                    rows=opts.pageSize = pageSize;
                    searchData(page,rows);
                }*/
            });
        }
    });



    /**
     * 新增弹出框
     */
    $("#newDialog").dialog({
        title: '新增人员技能',
        modal: true,
        closed:true,
        collapsible: true,
        minimizable: false,
        maximizable: true,
        resizable: true
    });


    /**
     * 添加
     */
    $("#addBtn").on('click', function () {
        $("#newForm").form('reset');
        $("#newDialog").dialog("setTitle", "新增").dialog("open");
        $("#SKILL_ID").val("");
        $("#SKILL").textbox("setValue", '');
    });
    /**
     * 修改
     */
    $("#editBtn").on('click', function () {
        var row = $("#dataGrid").datagrid("getSelected");
        if(!row){
            $.messager.alert("提示","请选择要修改的数据!",'info');
            return;
        }
        $("#newDialog").dialog("setTitle","人员技能修改").dialog("open");
        $("#SKILL_ID").val(row.skillId);

        $("#SKILL").textbox("setValue",row.skill);
        $("#DEPT_ID").combobox("setValue",row.deptId);
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
        //判断是否在技能等级中被占用，若被占用不能删除。
        $.postJSON(basePath + "/peinfo/if-occupy", JSON.stringify(row),
            function (data) {
                if (data.code == "yes") {
                    $.messager.alert('提示', '该人员技能已在技能等级中被使用，请先删除对应的技能等级数据！', 'info');
                    return;
                }
                {
                    $.messager.confirm('提示', '确定要删除所选中的数据么?', function (r) {
                        if (r) {
                            $.postJSON(basePath + "/peinfo/del-skill", JSON.stringify(row), function (data) {
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
        if (!$("#SKILL").textbox("getValue")||$("#SKILL").textbox("getValue").indexOf(" ") >=0) {
            $.messager.alert("提示", "请输入有效的人员技能名称，不能包含空格！！", 'info');
            return;
        }
        if($("#SKILL").textbox("getValue").length>20){
            $.messager.alert("提示","人员技能名称内容输入过长！",'info');
            return;
        }
        humanSkill.orgId = orgId;
        humanSkill.skillId = $("#SKILL_ID").val();
        humanSkill.skill = $("#SKILL").textbox('getValue');
        //判断是否已存在相同名称数据
        $.get(basePath +"/peinfo/if-exist?orgId="+orgId+"&skill="+$("#SKILL").textbox("getValue")+"&skillId="+$("#SKILL_ID").val(),
            function (data) {
                var list = eval(data);
                for (var i = 0; i < 1; i++)
                {
                    var num=list[i]['num'];
                }
                if(num=='1'||num>'1'){
                    var str = '该人员技能名称已存在！';
                    $.messager.alert("提示", str,"info");
                    return
                }
                //执行保存代码
                $.postJSON(basePath + "/peinfo/merge", JSON.stringify(humanSkill), function (data) {
                    if (data.data == "success") {
                        //$.messager.alert('系统提示', '保存成功', 'info');
                        $('#newDialog').window('close');
                        $('#dataGrid').datagrid('reload');
                        $("#dataGrid").datagrid('clearSelections');
                    }
                    else {
                        $.messager.alert('提示', '保存失败', 'info');
                    }
                })
            });
    });
    /**
     * 关闭
     */
    $("#close").on('click', function ()  {
        $('#newDialog').dialog('close');
        $("#dataGrid").datagrid('clearSelections');
    });


});