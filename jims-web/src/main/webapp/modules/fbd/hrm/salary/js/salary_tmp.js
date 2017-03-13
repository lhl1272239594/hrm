/**
 * 创建工资
 * @author
 * @version 2016-08-22
 */
var page='1';
var rows='30';
var orgId= parent.config.org_Id;
var personId= parent.config.persion_Id;
var personName = '';
var deptId = '';
var typeId = '';
var adjustMoney ='';
var adjustReason = '';
var salaryTmp = {};
var arr=[];
var orgCount=0;
var deptIds=parent.orgids;
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
        url: basePath +'/salary-tmp/salary-list?orgId='+orgId+'&personName='+personName+'&deptId='+deptId+'&typeId='+typeId+'&deptIds='+deptIds,
        remoteSort: false,
        //idField: 'tmpId',
        singleSelect: false,//是否单选
        rownumbers: true,//行号
        columns: [[
            {field: 'ck', title: '', checkbox: true},
            {field: 'orgId', title: '组织机构编码', hidden: true},
            {field: 'tmpId', title: '工资编号', hidden: true},
            {field: 'deptId', title: '科室', width:'11%', align: 'center'},
            {field: 'personName', title: '员工姓名', width:'7%', align: 'center'},
            {field: 'salaryMonth', title: '工资月份', width:'7%', align: 'center'},
            {field: 'personId', title: '员工编号', hidden: true},
            {field: 'flag', title: '当前状态', width:'7%', align: 'center',
                formatter: function (value, row, index) {
                    if (value == "0") {
                        return "起草";
                    }
                    if (value == "1") {
                        return "已发放";
                    }
                }
            },
            {field: 'typeId', title: '工资级别编号', hidden: true},
            {field: 'typeName', title: '工资级别', width:'8%', align: 'center'},
            {field: 'state', title: '工资状态', width:'6%', align: 'center',
                formatter: function (value, row, index) {
                    if (value == "0") {
                        return "正常";
                    }
                    if (value == "1") {
                        return "停发";
                    }
                }
            },
            {field: 'socialSecuritybase', title: '社保基数', width:'8%', align: 'center',formatter:function(value,row,index){
                var value = row.socialSecuritybase;
                if(value!=null)
                    return '￥'+(parseFloat(value).toFixed(2)+ '').replace(/\d{1,3}(?=(\d{3})+(\.\d*)?$)/g, '$&,');
            }},
            {field: 'socialSecuritycompany', title: '单位代缴社保金额', width:'12%', align: 'center',formatter:function(value,row,index){
                var value = row.socialSecuritycompany;
                if(value!=null)
                    return '￥'+(parseFloat(value).toFixed(2)+ '').replace(/\d{1,3}(?=(\d{3})+(\.\d*)?$)/g, '$&,');
            }},
            {field: 'salaryBefore', title: '应发工资', width:'9%', align: 'center',formatter:function(value,row,index){
                var value = row.salaryBefore;
                if(value!=null)
                    return '￥'+(parseFloat(value).toFixed(2)+ '').replace(/\d{1,3}(?=(\d{3})+(\.\d*)?$)/g, '$&,');
            }},
            {field: 'socialSecuritypersonal', title: '代扣社保', width:'8%', align: 'center',formatter:function(value,row,index){
                var value = row.socialSecuritypersonal;
                if(value!=null)
                    return '￥'+(parseFloat(value).toFixed(2)+ '').replace(/\d{1,3}(?=(\d{3})+(\.\d*)?$)/g, '$&,');
            }},
            {field: 'salaryTax', title: '代扣个税', width:'8%', align: 'center',formatter:function(value,row,index){
                var value = row.salaryTax;
                if(value!=null)
                    return '￥'+(parseFloat(value).toFixed(2)+ '').replace(/\d{1,3}(?=(\d{3})+(\.\d*)?$)/g, '$&,');
            }},
            {field: 'adjustMoney', title: '手工调整金额', width:'9%', align: 'center',formatter:function(value,row,index){
                var value = row.adjustMoney;
                if(value!=null)
                    return '￥'+(parseFloat(value).toFixed(2)+ '').replace(/\d{1,3}(?=(\d{3})+(\.\d*)?$)/g, '$&,');
            }},
            {field: 'salaryAfter', title: '实发工资', width:'9%', align: 'center',formatter:function(value,row,index){
                var value = row.salaryAfter;
                if(value!=null)
                    return '￥'+(parseFloat(value).toFixed(2)+ '').replace(/\d{1,3}(?=(\d{3})+(\.\d*)?$)/g, '$&,');
            }},
            {field: 'adjustReason', title: '调整原因', width:'11%', align: 'center'},
            {field: 'adjustBy', title: '调整人', width:'7%', align: 'center'},
            {field: 'adjustDate', title: '调整时间', width:'15%', align: 'center',formatter: formatDatebox},
            {field: 'createBy', title: '创建人', width:'7%', align: 'center'},
            {field: 'createDate', title: '创建时间', width:'15%',align: 'center'},
            {field: 'confirmMan', title: '下发人', width:'7%', align: 'center'},
            {field: 'comfirmDate', title: '下发时间', width:'15%', align: 'center',formatter: formatDatebox}
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
        return dt.format("yyyy-MM-dd hh:mm:ss"); //扩展的Date的format方法(上述插件实现)
    }
    //查询条件：人员类别
    $("#TYPE_ID").combobox({
        panelWidth: '136px',
        panelHeight: 'auto',
        valueField: 'typeId',
        textField: 'typeName',
        value:'请选择',
        loadMsg: '数据正在加载...',
        url: basePath + '/salary-cal/find-humanType?orgId=' + orgId ,
        mode: 'remote',
        method: 'GET',
        onLoadSuccess:function(data){
            orgCount=data.length;
        },
        onShowPanel:function(){
            //动态调整高度
            if(orgCount>8){
                $(this).combobox('panel').height(140);
            }
        }
    });
    //查询条件:部门树选择
    $("#DEPT_ID").combotree({
        panelWidth: '160px',
        fitColumns: true,
        striped: true,
        singleSelect: true,
        value:'请选择',
        text:'请选择',
        loadMsg: '数据正在加载中，请稍后.....',
        columns: [[{
            title: 'id',
            field: "id",
            hidden: true
        }, {
            title: '科室名称',
            field: 'text',
            width: '100%'
        }]],
        onSelect: function (node) {
            //返回树对象
            var tree = $(this).tree;
            //选中的节点是否为叶子节点,如果不是叶子节点,清除选中
            var isLeaf = tree('isLeaf', node.target);
            if (!isLeaf) {
                //清除选中
                $('#DEPT_ID').combotree('clear');
                $.messager.alert("提示", "请选择具体科室!", "info");
            }
        }
    });
    var loadDept = function () {
        var depts = [];
        var treeDepts = [];
        var loadPromise = $.get("/service/dept-dict/list?orgId=" + orgId, function (data) {
            $.each(data, function (index, item) {
                var obj = {};
                obj.text = item.deptName;
                obj.id = item.id;
                obj.deptCode = item.deptCode;
                obj.deptPropertity = item.deptPropertity;
                obj.parentId = item.parentId;
                obj.children = [];
                depts.push(obj);
            });
        });

        loadPromise.done(function () {
            for (var i = 0; i < depts.length; i++) {
                for (var j = 0; j < depts.length; j++) {
                    if (depts[i].id == depts[j].parentId) {
                        depts[i].children.push(depts[j]);
                    }
                }
                if (depts[i].children.length > 0 && !depts[i].parentId) {
                    treeDepts.push(depts[i]);
                }
                if (!depts[i].parentId && depts[i].children <= 0) {
                    treeDepts.push(depts[i])
                }
            }
            $("#DEPT_ID").combotree('loadData', treeDepts);
        })
    };

    loadDept();
    loadDept1();
    /**
     * 查询条件搜索
     */
    $("#searchBtn").on("click", function () {
        search=true;
        searchData(page,rows);
    });

    var searchData= function (page,rows){
        //获取数据
        var personName = $("#PERSON_NAME").textbox('getValue');
        var deptId = $("#DEPT_ID").combotree('getValue');
        var typeId = $("#TYPE_ID").combobox('getValue');
        if(typeId=='请选择'){
            typeId='';
        }
            /*$.get(basePath + '/salary-tmp/salary-list?orgId='+orgId+'&personName='+personName+'&deptId='+deptId+'&typeId='+typeId+'&page='+page+'&rows='+rows, function (data) {
                $("#dataGrid").datagrid('loadData', data);*/
                $("#dataGrid").datagrid('reload', basePath + '/salary-tmp/salary-list?orgId='+orgId+'&personName='+personName+'&deptId='+deptId+'&typeId='+typeId+'&page='+page+'&rows='+rows);

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
     * 清空方法
     */
    var clearKey = function () {
        $("#PERSON_NAME").textbox('clear');
        $("#PERSON_NAME").textbox("setValue","");
        $("#DEPT_ID").combobox('clear');
        $("#DEPT_ID").combobox("setValue","");
        $("#TYPE_ID").combobox('clear');
        $("#TYPE_ID").combobox("setValue","请选择");
    };

    /**
     * 手工调整弹出框
     */
    $("#newDialog").dialog({
        title: '手工调整明细',
        modal: true,
        closed:true,
        collapsible: true,
        minimizable: false,
        maximizable: true,
        resizable: true
    });
    /**
     * 手工调整
     */
    $("#adjustBtn").on('click', function () {
        //判断是否已发放
        /*$.get(basePath +"/salary-tmp/if-exist?orgId="+orgId,
            function (data) {
                //alert(data);
                if (data.length != 0) {
                    var str = '当月工资已下发，不能调整！';
                    $.messager.alert("系统提示", str, "error");
                    return
                }*/
                var row = $("#dataGrid").datagrid('getSelections');
                for (var i = 0; i < row.length; i++) {
                    if (row[i].adjustMoney.indexOf("0.0") == -1||row[i].adjustMoney!="0.0") {
                        $.messager.alert("提示", "包含已调整过的工资项，不能重复调整！", 'info');
                        return;
                    }
                }
                for (var i = 0; i < row.length; i++) {
                    if (row[i].flag.indexOf("0") == -1||row[i].flag!="0") {
                        $.messager.alert("提示", "包含已发放的工资项，不能调整！", 'info');
                        return;
                     }
                }
                if (row == null || !row || row.length == 0) {
                    $.messager.alert("提示", "请选择要进行手工调整金额的人员！", 'info');
                    return;
                }
                //alert(row[0].adjustReason);
                //alert(row[0].adjustMoney);
                $("#newDialog").dialog("open");
                $("#ADJUST_MONEY").numberbox("setValue", "");
                $("#ADJUST_REASON").textbox("setValue", "");
                $("#newForm").form('reset');
            //});
    });
    /**
     * 手工调整保存
     */
    $("#submitBtn").on('click', function () {
        var val1 = $("#ADJUST_MONEY").numberbox("getValue");
        var val2 = $("#ADJUST_REASON").textbox("getValue");
        if (val1 == null||val1=='') {
            $.messager.alert("提示", "请填写调整金额！",'info');
            return;
        }
        if (val2 == null||val2=='') {
            $.messager.alert("提示", "请填写调整原因！",'info');
            return;
        }
        //$("#ADJUST_REASON").textbox("setValue","");
        var row = $("#dataGrid").datagrid('getSelections');
        if (row.length>0) {
            adjustMoney = $("#ADJUST_MONEY").numberbox("getValue");
            adjustReason = $("#ADJUST_REASON").textbox("getValue");
            for (var i = 0; i < row.length; i++) {
                row[i].adjustMoney = adjustMoney;
                row[i].adjustReason = adjustReason;

            }
            $.messager.confirm('提示', '确定要进行调整吗？', function (r) {
                if (r) {
                    $.postJSON(basePath + "/salary-tmp/change", JSON.stringify(row), function (data) {
                        if (data.data == "success") {
                            //alert(data);
                            // $.messager.alert('系统提示', '保存成功', 'info');
                            $('#newDialog').window('close');
                            $("#dataGrid").datagrid('reload');
                            $("#newForm").form('reset');
                            $("#dataGrid").datagrid('clearSelections');
                        }
                        else {
                            $.messager.alert('提示', '保存失败!', 'info');
                        }
                    })
                }
            });
        }

    });
    /**
     * 创建工资弹出框配置
     */
    $("#chooseDept").dialog({
        title: '科室选择',
        modal: true,
        closed:true,
        collapsible: true,
        minimizable: false,
        maximizable: true,
        resizable: true
    });
    /**
     * 创建工资弹出框
     */
    $("#createBtn").on('click', function () {
        salaryTmp.orgId = parent.config.org_Id;
        //判断是否已发放
        /*$.get(basePath +"/salary-tmp/if-exist?orgId="+orgId,
            function (data) {
                if (data.length != 0) {
                    var str = '当月工资已下发，不能重复创建！';
                    $.messager.alert("系统提示", str, "error");
                    return
                }*/
                    $("#chooseDept").dialog("open");
                    $("#objName").textbox("setValue", "");
                    $("#newForm1").form('reset');
                //});
    });
    /**
     * 确定创建工资
     */
    $("#submitBtn1").on('click', function () {
        if(!$("#objName").textbox("getValue")){
            $.messager.alert("提示","请选择科室！",'info');
            return ;
        }
        salaryTmp.orgId = parent.config.org_Id;
        salaryTmp.personVos = arr;
        //判断是否已发放
                $.messager.confirm('提示', '确定要创建所选部门当月工资吗？注意:如果该部门当月工资已创建并且未发放，将会删除数据重新创建！', function (r) {
                    if (r) {
                        $.messager.progress({
                            title: '提示！',
                            msg:  '数据加载中，请稍候...',
                            text: '加载中.......'
                        });
                        $.postJSON(basePath + "/salary-tmp/create", JSON.stringify(salaryTmp), function (data) {
                            if (data.data == "success") {
                                //alert(data);
                                // $.messager.alert('系统提示', '保存成功', 'info');
                                // $('#newDialog').window('close');
                                $.messager.progress('close');
                                $('#chooseDept').window('close');
                                $("#dataGrid").datagrid('reload');
                                // $("#newForm").form('reset');
                                $("#dataGrid").datagrid('clearSelections');
                            }
                            else {
                                $.messager.progress('close');
                                $.messager.alert('提示', '创建失败!', 'info');
                            }
                        })
                    }
                });
            arr = [];
            $("#personGrid").datagrid('loadData',arr);
    });

    /**
     * 重新计算
     */
    $("#recalBtn").on('click', function () {
        var row = $("#dataGrid").datagrid('getSelections');
        if (row == null||!row||row.length==0) {
            $.messager.alert("提示", "请选择要进行重计算的人员！",'info');
            return;
        }
        for (var i = 0; i < row.length; i++) {
            if (row[i].flag.indexOf("0") == -1||row[i].flag!="0") {
                $.messager.alert("提示", "包含已发放的工资项，不能重新计算！", 'info');
                return;
            }
        }
        if (row.length>0) {
            //判断是否已发放
                    for (var i = 0; i < row.length; i++) {
                        row[i].typeId = typeId;
                        /* row[i].personId = personId;
                         row[i].deptId = deptId;*/
                        row[i].orgId = parent.config.org_Id;
                    }
                    $.messager.confirm('提示', '确定要重计算吗？注意:重计算后手工调整项将清零！', function (r) {
                        if (r) {
                            $.messager.progress({
                                title: '提示！',
                                msg:  '数据加载中，请稍候...',
                                text: '加载中.......'
                            });
                            $.postJSON(basePath + "/salary-tmp/recal", JSON.stringify(row), function (data) {
                                if (data.data == "success") {
                                    //alert(data);
                                    // $.messager.alert('系统提示', '保存成功', 'info');
                                    // $('#newDialog1').window('close');
                                    $("#dataGrid").datagrid('reload');
                                    $.messager.progress('close');
                                    // $("#newForm1").form('reset');
                                    $("#dataGrid").datagrid('clearSelections');
                                }
                                else {
                                    $.messager.alert('提示', '保存失败', 'info');
                                    $.messager.progress('close');
                                }
                            })
                        }
                    });
        }

    });

    /**
     * 确认下发
     */
    $("#dealBtn").on('click', function () {
        //判断是否已发放
        $.get(basePath +"/salary-tmp/if-exist?orgId="+orgId,
            function (data) {
                //alert(data);
                if(data.length!=0){
                    var str = '当月工资已下发，不能重复下发！';
                    $.messager.alert("提示", str,"info");
                    return
                }

        salaryTmp.orgId = parent.config.org_Id,
            $.messager.confirm('系统提示', '确定要下发该机构人员工资吗？注意:工资下发后将不可修改！', function (r) {
                if (r) {
                    $.postJSON(basePath + "/salary-tmp/deal", JSON.stringify(salaryTmp), function (data) {
                        if (data.data == "success") {
                            //alert(data);
                            // $.messager.alert('系统提示', '保存成功', 'info');
                            // $('#newDialog').window('close');
                            $("#dataGrid").datagrid('reload');
                            // $("#newForm").form('reset');
                            $("#dataGrid").datagrid('clearSelections');
                        }
                        else {
                            $.messager.alert('提示', '创建失败!', 'info');
                        }
                    })
                }
            });
        });

    });

    //配置工资详单窗口
    $("#newDialog1").window({
        title: '工资详单',
        closed: true,
        modal: true,
        minimizable: false,
        width:540,
        height:500
        //fit:true
        /* onClose: function () {
         $("#dataGrid").datagrid('reload');

         $('#queTypeGrid').datagrid('clearSelections');
         $('#queTypeGrid').datagrid('clearChecked');
         },
         onOpen: function () {
         $("#selectQue").css('display','block');
         }*/
    });
    /**
     * 工资详单
     */
    $("#infoBtn").on("click", function () {
        //获取选择行
        var row=$('#dataGrid').datagrid('getSelected');
        var row1=$('#dataGrid').datagrid('getSelections');
        if (row1.length != 1) {
            $.messager.alert("提示", "请选择一条数据进行操作!","info");
            return;
        }
        if(row){
            $("#newDialog1").window('open');
            var personId = row.personId;
            typeId = row.typeId;
            orgId = row.orgId;
            salaryMonth = row.salaryMonth;
            //详单数据
            $("#dataGrid1").datagrid({
                iconCls: 'icon-edit',//图标
                width: '100%',
                height: '100%',
                nowrap: false,
                striped: true,
                border: true,
                method: 'get',
                toolbar: '#tb1',
                loadMsg: '数据正在加载中，请稍后.....',
                collapsible: false,//是否可折叠的
                url: basePath + '/salary-tmp/salary-history-detail?orgId=' + orgId + '&personId=' + personId + '&typeId=' + typeId+ '&salaryMonth=' + salaryMonth,
                remoteSort: false,
                //idField: 'examQueId',
                singleSelect: true,//是否单选
                rownumbers: true,//行号
                fitColumns:true,
                columns: [[
                    {field: 'item', title: '项目',width: '40%',align: 'center'},
                    {field: 'money', title: '金额',width: '60%',align: 'center'}

                ]],onLoadSuccess:function(data){
                    $("#NAME").html(row.personName);
                    $("#TIME").html(row.salaryMonth);
                    //$("#ORG").html(row.orgName);
                }
            });
        }
        else{
            $.messager.alert("提示", "请选择一条工资数据！","info");
        }

    });
});