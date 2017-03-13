/**
 * 个人所得税税率
 * @author
 * @version 2016-08-22
 */
var orgId= parent.config.org_Id;
var personId= parent.config.persion_Id;
var salaryTax = {};
var page='1';
var rows='30';
var search=false;
$(function () {

    /**
     * 格式化数字，小数点后是0不表示
     * @param value
     * @param fixed 小数位数
     */
    function formatNumber(value, fixed) {
        var number = Number(value);
        if (isNaN(number)) {
            return '';
        } else {
            if (fixed == 2) {
                return number.toFixed(2) * 100 / 100;
            } else if (fixed == 6) {
                return number.toFixed(6) * 1000000 / 1000000;
            }
        }
    }
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
        url: basePath +'/salary-tax/tax-list?orgId=' + orgId, /*+ '&levelName=' + levelName + '&typeId=' + typeId*/
        remoteSort: false,
        //idField: 'taxId',
        singleSelect: false,//是否单选
        rownumbers: true,//行号
        columns: [[
            {field: 'ck', title: '', checkbox: true},
            {field: 'baseNum', title: '起征基数', width: '10%', align: 'center',formatter:function(value,row,index){
                var value = row.baseNum;
                if(value!=null&&value>=0) {
                    return '￥' + (parseFloat(value).toFixed(2) + '').replace(/\d{1,3}(?=(\d{3})+(\.\d*)?$)/g, '$&,');
                }
                else{
                    return '0.00';
                }
            }},
            {field: 'taxId', title: '税率编码', hidden: true},
            {field: 'lowLimit', title: '应纳所得下限',width:'10%', align: 'center',formatter:function(value,row,index){
                var value = row.lowLimit;
                if(value!=null)
                    return '￥'+(parseFloat(value).toFixed(2)+ '').replace(/\d{1,3}(?=(\d{3})+(\.\d*)?$)/g, '$&,');
            }},
            {field: 'highLimit', title: '应纳所得上限(含)', width:'13%', align: 'center',formatter:function(value,row,index){
                var value = row.highLimit;
                if(value!=null)
                    return '￥'+(parseFloat(value).toFixed(2)+ '').replace(/\d{1,3}(?=(\d{3})+(\.\d*)?$)/g, '$&,');
            }},
            {field: 'rate', title: '税率', width:'7%', align: 'center',formatter:function(value,row,index){
                var value = row.rate;
                if(value!=null)
                    return parseFloat(value).toFixed(2);
            }},
            {field: 'decuteNum', title: '速算扣除数',width:'10%', align: 'center',formatter:function(value,row,index){
                var value = row.decuteNum;
                if(value!=null)
                    return '￥'+(parseFloat(value).toFixed(2)+ '').replace(/\d{1,3}(?=(\d{3})+(\.\d*)?$)/g, '$&,');
            }},
            {
                field: 'enableFlag', title: '状态', width:'5%',align: 'center',
                formatter: function (value, row, index) {
                    if (value == "1") {
                        return "启用";
                    }
                    if (value == "0") {
                        return "停用";
                    }
                }
            },
            {field: 'delFlag', title: '删除标识', hidden: true},
            {field: 'createBy', title: '创建人', width:'7%', align: 'center'},
            {field: 'createDate', title: '创建时间',width:'15%',align: 'center'},
            {field: 'updateBy', title: '更新人', width:'7%',align: 'center'},
            {field: 'updateDate', title: '更新时间',width:'15%', align: 'center'}

        ]],
        onLoadSuccess:function(data){
            $('#newDialog').css('display','block');
            $('#tb').css('display','block');
        }
    });

    /**
     * 新增弹出框
     */
    $("#newDialog").dialog({
        title: '增加',
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
        $("#dataGrid").datagrid('clearSelections');
        $("#newForm").form('reset');
        $("#newDialog").dialog("setTitle","新增").dialog("open");
        $("#BASE_NUM").numberbox("setValue","");
        $("#LOW_LIMIT").numberbox("setValue","");
        $("#HIGH_LIMIT").numberbox("setValue","");
        $("#RATE").numberbox("setValue","");
        $("#DECUTE_NUM").numberbox("setValue","");
        $("#TAX_ID").val("");
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
        $("#newDialog").dialog("setTitle","工资级别修改").dialog("open");
        $("#TAX_ID").val(row.taxId);
        $("#BASE_NUM").numberbox("setValue",row.baseNum);
        $("#LOW_LIMIT").numberbox("setValue",row.lowLimit);
        $("#HIGH_LIMIT").numberbox("setValue",row.highLimit);
        $("#RATE").numberbox("setValue",row.rate);
        $("#DECUTE_NUM").numberbox("setValue",row.decuteNum);
       /* $("#ENABLE_FLAG").combobox("setValue",row.enableFlag);*/

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
            $.messager.confirm('提示', '确定要进行启用操作吗?', function (r) {
                if (r) {
                    $.postJSON(basePath + "/salary-tax/enableFlag",JSON.stringify(row), function (data) {
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
            $.messager.confirm('提示', '确定要进行停用操作吗?', function (r) {
                if (r) {
                    $.postJSON(basePath + "/salary-tax/enableFlag", JSON.stringify(row), function (data) {
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
        if (row == null||row.length == 0||!row) {
            $.messager.alert("提示", "请选择要删除的数据!","info");
            return;
        }
            $.messager.confirm('提示', '确定要批量删除所选中的数据么?', function (r) {
                if (r) {
                    $.postJSON(basePath + "/salary-tax/del-tax", JSON.stringify(row), function (data) {
                       /* $.messager.alert('系统提示', '删除成功', 'info');*/
                        $('#dataGrid').datagrid('reload');
                        row.length=0;
                        $("#dataGrid").datagrid('clearSelections');
                    })
                }
            });
    });

    /**
     * 新增修改保存
     */
    $("#submitBtn").on('click', function () {
        if(!$("#BASE_NUM").numberbox("getValue")||$("#BASE_NUM").numberbox("getValue")<0){
            $.messager.alert("提示","请输入有效的起征基数，不能小于0！",'info');
            return ;
        }
        if($("#BASE_NUM").numberbox("getValue")=='0.00'){
            $.messager.alert("提示","请输入有效的起征基数，不能小于0！",'info');
            return ;
        }
        if(!$("#LOW_LIMIT").numberbox("getValue")||$("#LOW_LIMIT").numberbox("getValue")<0){
            $.messager.alert("提示","请输入有效的应纳所得下限，不能小于0！",'info');
            return ;
        }
        if(!$("#HIGH_LIMIT").numberbox("getValue")||$("#HIGH_LIMIT").numberbox("getValue")<0){
            $.messager.alert("提示","请输入有效的应纳所得上限，不能小于0！",'info');
            return ;
        }
        if(!$("#RATE").numberbox("getValue")||$("#RATE").numberbox("getValue")<0){
            $.messager.alert("提示","请输入有效的税率，不能小于0！",'info');
            return ;
        }
        if(!$("#DECUTE_NUM").numberbox("getValue")||$("#DECUTE_NUM").numberbox("getValue")<0){
            $.messager.alert("提示","请输入有效的速算扣除数，不能小于0！",'info');
            return ;
        }


        salaryTax.orgId = parent.config.org_Id;
        salaryTax.createBy = parent.config.persion_Id;
        salaryTax.updateBy = parent.config.persion_Id;
        salaryTax.taxId = $("#TAX_ID").val();
        salaryTax.baseNum = $("#BASE_NUM").numberbox('getValue');
        salaryTax.lowLimit = $("#LOW_LIMIT").numberbox('getValue');
        salaryTax.highLimit = $("#HIGH_LIMIT").numberbox('getValue');
        salaryTax.rate = $("#RATE").numberbox('getValue');
        salaryTax.decuteNum = $("#DECUTE_NUM").numberbox('getValue');

        //判断是否已存在相同名称数据
        $.get(basePath +"/salary-tax/if-tax-exist?orgId=" + orgId+"&baseNum="+$("#BASE_NUM").numberbox('getValue')+"&rate="+$("#RATE").numberbox('getValue')+"&lowLimit="+$("#LOW_LIMIT").numberbox('getValue')+"&highLimit="+$("#HIGH_LIMIT").numberbox('getValue')+"&decuteNum="+$("#DECUTE_NUM").numberbox('getValue')+"&taxId="+$("#TAX_ID").val(),
            function (data) {
                var list = eval(data);
                for (var i = 0; i < 1; i++)
                {
                    var num=list[i]['num'];
                }
                if(num=='1'||num>'1'){
                    var str = '该税率方案已经存在！';
                    $.messager.alert("提示", str,"info");
                    return
                }
                {
                    //如果不重复，执行保存代码
                    $.postJSON(basePath + "/salary-tax/merge", JSON.stringify(salaryTax), function (data) {
                        if(data.data=="success")
                        {
                            //$.messager.alert('系统提示', '保存成功', 'info');
                            $('#newDialog').window('close');
                            $('#dataGrid').datagrid('reload');
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
     * 关闭
     */
    $("#close").on('click', function ()  {
        $('#newDialog').dialog('close');
        $("#dataGrid").datagrid('clearSelections');
    });


});