
var page='1';
var rows='30';
var orgId= parent.config.org_Id;
var projectName = '';
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
        //pageList: [10, 20, 30, 40, 50],//可以设置每页记录条数的列表
        loadMsg: '数据正在加载中，请稍后.....',
        collapsible: false,//是否可折叠的
        url: basePath +'/project/evaluationType?orgId=' + orgId,
        remoteSort: false,
        idField: 'typeId',
        singleSelect: false,//是否单选
        rownumbers: true,//行号
        columns: [[
            {field: 'ck', title: '', checkbox: true},
            {field: 'typeName', title: '类型名称', width:'18%', align: 'center'},
            {
                field: 'type', title: '分类', width:'10%', align: 'center',
                formatter: function (value, row, index) {
                    if (value == "1") {
                        return "科室自评";
                    }
                    if (value == "2") {
                        return "考评科室";
                    }
                    if (value == "3") {
                        return "普通类型";
                    }
                }
            },
            {
                field: 'state', title: '状态', width:'10%', align: 'center',
                formatter: function (value, row, index) {
                    if (value == "1") {
                        return "启用";
                    }
                    if (value == "0") {
                        return "停用";
                    }
                }
            },
            {field: 'createBy', title: '创建人', width: '15%', align: 'center'},
            {field: 'createDate', title: '创建时间', width:'15%',align: 'center'},
            {field: 'updateBy', title: '更新人', width:'15%', align: 'center'},
            {field: 'updateDate', title: '更新时间', width:'15%', align: 'center'}
        ]],onLoadSuccess:function(data){
            $("#newDialog").css('display','block');
        }
    });
    $("#dataGrid").datagrid('getPager').pagination({
        pageSize:30,
        pageList: [10, 20, 30, 40, 50], //可以设置每页记录条数的列表
        displayMsg: '当前显示从第{from}条到第{to}条，共{total}条记录',
    });

    /**
     * 新增考评类型
     */
    $("#newDialog").dialog({
        title: '新增考评类型',
        modal: true,
        closed:true,
        collapsible: true,
        minimizable: false,
        maximizable: true,
        resizable: true,
        onClose: function () {
            $("#dataGrid").datagrid("clearSelections");
        }
    });

    /**
     * 添加
     */
    $("#addBtn").on('click', function () {
        $("#newDialog").dialog("setTitle","新增考评类型").dialog("open");
        $("#newForm").form('reset');
        $("#typeId").val('');
    });
    /**
     * 修改
     */
    $("#editBtn").on('click', function () {
        //reset();
        flag = 0;
        var row = $("#dataGrid").datagrid("getSelections");
        if(row.length==1){
            $.postJSON(basePath + "/project/checkTypeIsUsed",JSON.stringify(row), function (data) {
                if (data.data == "success") {
                    if(data.code=="isUsed"){
                        if(row.length>1){
                            $.messager.alert("提示", "所选考评类型中有部分正在使用中!","info");
                        }
                        if(row.length==1){
                            $.messager.alert("提示", "所选考评类型正在使用中!","info");
                        }
                        $("#dataGrid").datagrid("clearSelections");
                    }
                    if(data.code=="success"){
                        $("#newDialog").dialog("setTitle","考评类型修改").dialog("open");
                        $("#typeId").val(row[0].id);
                        $("#typeName").textbox("setValue",row[0].typeName);
                    }
                }
            })

        }
        else{
            $("#dataGrid").datagrid("clearSelections");
            $.messager.alert("提示","请选择一条数据!",'info');
            return;
        }

    });

    /**
     * 启用
     */
    $("#okBtn").on('click', function () {
        var row = $("#dataGrid").datagrid('getSelections');
        if (row.length==0) {
            $("#dataGrid").datagrid("clearSelections");
            $.messager.alert("提示", "请选择要启用的考评类型!","info");
            return;
        }
        if (row.length>0) {
            for (var i = 0; i < row.length; i++) {
                row[i].state = '1';
                row[i].updateBy=parent.config.persion_Id;
            }
            $.messager.confirm('提示', '确定启用选中的考评类型吗？', function (r) {
                if (r) {
                    $.postJSON(basePath + "/project/typeStatus",JSON.stringify(row), function (data) {
                        /*$.messager.alert('提示', '删除成功', 'info');*/
                        $('#dataGrid').datagrid('reload');
                        row.length = 0;
                        $("#dataGrid").datagrid('clearSelections');
                    })
                }
                else {
                    $("#dataGrid").datagrid('clearSelections');
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
        if (row.length==0) {
            $("#dataGrid").datagrid("clearSelections");
            $.messager.alert("提示", "请选择要停用的考评类型!","info");
            return;
        }
        if (row.length>0) {
            for (var i = 0; i < row.length; i++) {
                row[i].state = '0';
                row[i].updateBy=parent.config.persion_Id;
            }
            $.postJSON(basePath + "/project/checkTypeIsUsed",JSON.stringify(row), function (data) {
                if (data.data == "success") {
                    if(data.code=="isUsed"){
                        if(row.length>1){
                            $.messager.alert("提示", "所选考评类型中有部分正在使用中!","info");
                        }
                        if(row.length==1){
                            $.messager.alert("提示", "所选考评类型正在使用中!","info");
                        }
                        $("#dataGrid").datagrid("clearSelections");
                    }
                    if(data.code=="success"){
                        $.messager.confirm('提示', '确定停用选中的考评类型吗？', function (r) {
                            if (r) {
                                $.postJSON(basePath + "/project/typeStatus",JSON.stringify(row), function (data) {
                                    /*$.messager.alert('提示', '删除成功', 'info');*/
                                    $('#dataGrid').datagrid('reload');
                                    row.length = 0;
                                    $("#dataGrid").datagrid('clearSelections');
                                })
                            }
                            else {
                                $("#dataGrid").datagrid('clearSelections');
                            }
                        });
                    }
                }
            })

        }

    });
    /**
     * 删除
     */
    $("#delBtn").on('click', function () {
        //stopEdit();
        var row = $("#dataGrid").datagrid('getSelections');
        if (row.length==0) {
            $("#dataGrid").datagrid("clearSelections");
            $.messager.alert("提示", "请选择要删除的考评类型!","info");
            return;
        }
        if (row.length>0) {
            for (var i = 0; i < row.length; i++) {
                row[i].state = '1';
                row[i].updateBy=parent.config.persion_Id;
            }
            $.postJSON(basePath + "/project/checkTypeIsUsed",JSON.stringify(row), function (data) {
                if (data.data == "success") {
                    if(data.code=="isUsed"){
                        if(row.length>1){
                            $.messager.alert("提示", "所选考评类型中有部分正在使用中!","info");
                        }
                        if(row.length==1){
                            $.messager.alert("提示", "所选考评类型正在使用中!","info");
                        }
                        $("#dataGrid").datagrid("clearSelections");
                    }
                    if(data.code=="success"){
                        $.messager.confirm('提示', '确定要批量删除所选中的考评类型么？', function (r) {
                            if (r) {
                                $.postJSON(basePath + "/project/delType", JSON.stringify(row), function (data) {
                                    /*$.messager.alert('提示', '删除成功', 'info');*/
                                    $('#dataGrid').datagrid('reload');
                                    row.length = 0;
                                    $("#dataGrid").datagrid('clearSelections');
                                })
                            }
                            else {
                                $("#dataGrid").datagrid('clearSelections');
                            }
                        });
                    }
                }
            })

            }

    });
    /**
     * 设置为科室自评
     */
    $("#selfBtn").on('click', function () {
        var row = $("#dataGrid").datagrid('getSelections');
        if (row.length==1) {
            if(row[0].type=='1'){
                $("#dataGrid").datagrid("clearSelections");
                $.messager.alert("提示","改考评类型已经是科室自评!",'info');
                return;
            }
            else {
                changeType(row[0].id,'1');
            }
        }
        else{
            $("#dataGrid").datagrid("clearSelections");
            $.messager.alert("提示","请选择一条数据!",'info');
            return;
        }

    });
    /**
     * 设置为考评科室
     */
    $("#otherBtn").on('click', function () {
        var row = $("#dataGrid").datagrid('getSelections');
        if (row.length==1) {
            if(row[0].type=='2'){
                $("#dataGrid").datagrid("clearSelections");
                $.messager.alert("提示","改考评类型已经是考评科室!",'info');
                return;
            }
            else {
                changeType(row[0].id,'2');
            }
        }
        else{
            $("#dataGrid").datagrid("clearSelections");
            $.messager.alert("提示","请选择一条数据!",'info');
            return;
        }

    });
    /**
     * 设置为普通考评
     */
    $("#backBtn").on('click', function () {
        var row = $("#dataGrid").datagrid('getSelections');
        if (row.length==1) {
            if(row[0].type=='3'){
                $("#dataGrid").datagrid("clearSelections");
                $.messager.alert("提示","改考评类型已经是普通考评!",'info');
                return;
            }
            else {
                changeType(row[0].id,'3');
            }
        }
        else{
            $("#dataGrid").datagrid("clearSelections");
            $.messager.alert("提示","请选择一条数据!",'info');
            return;
        }

    });
    /**
     * 新增修改保存
     */
    $("#submitBtn").on('click', function () {
        if (!$("#typeName").textbox("getValue")||$("#typeName").textbox("getValue").indexOf(" ") >=0) {
            $.messager.alert("提示", "请输入考评类型名称，不能包含空格!", 'info');
            return;
        }
        if(getRealLen($("#typeName").textbox("getValue"))>20){
            $.messager.alert("提示","考评类型名称输入过长！",'info');
            return;
        }
        var evaluationType = {};
        evaluationType.id = $("#typeId").val();
        evaluationType.orgId = parent.config.org_Id;
        evaluationType.createBy = parent.config.persion_Id;
        evaluationType.typeName = $("#typeName").textbox('getValue');

        $.postJSON(basePath + "/project/typeMerge", JSON.stringify(evaluationType), function (data) {
            if (data.data == "success") {
                if(data.code=="hasName"){
                    $.messager.alert('提示', '该考评类型名称已存在', 'info');
                }
                if(data.code=="success"){
                    $('#newDialog').window('close');
                    $("#dataGrid").datagrid('reload');
                    $("#newForm").form('reset');
                    $("#dataGrid").datagrid('clearSelections');
                }
            }
            else {
                $.messager.alert('提示', '保存失败', 'info');
            }
        })

    });
});
function changeType(id,type) {

    var row={};
    row.id=id;
    row.type=type;
    row.updateBy = parent.config.persion_Id;
    $.postJSON(basePath + "/project/changeType",JSON.stringify(row), function (data) {
        if (data.data == "success") {
            /*if(data.code=="has"){
                $("#dataGrid").datagrid("clearSelections");
                var name;
                if(type=='1'){
                    name='科室自评';
                }
                if(type=='2'){
                    name='考评科室';
                }
                $.messager.alert("提示", "已有"+name+"分类!","info");
            }*/
            if(data.code=="success"){
                $('#dataGrid').datagrid('reload');
                row.length = 0;
                $("#dataGrid").datagrid('clearSelections');
            }
        }
    })
}