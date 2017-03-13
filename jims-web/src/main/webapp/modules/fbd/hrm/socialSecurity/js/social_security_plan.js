/**
 *社保方案
 * @author yangchen
 * @version 2016-08-18
 */
var basePath = "/service";
var fesDesId="999";
var yearId="999";
var sumDays="0";
var fesDateId='999';
var dg;
var d;
var flag;
var page='1';
var rows='30';
var orgId = parent.config.org_Id;
var ssPlanVo = {};
var userName='';
var userId='';
var ssItemList = [];
var obj={};
var state = '';//状态
var name = '';//社保方案名称
var search = false;
var num=0;
$(function () {
    var editIndex;
    var stopEdit = function () {
        if (editIndex || editIndex == 0) {
            $("#detailGrid").datagrid('endEdit', editIndex);
            editIndex = undefined;
        }
    };
    $.messager.progress({
        title: '提示！',
        msg:  '数据读取中，请稍候...',
        text: '加载中.......'
    });

    $("#planGrid").datagrid({
        iconCls: 'icon-edit',//图标
        width: '100%',
        height: '100%',
        nowrap: false,
        striped: true,
        border: true,
        method: 'get',
        toolbar: '#tb',
        pageSize: 30,
        fitColumns: true,
        loadMsg: '数据正在加载中，请稍后.....',
        pagination: true,//分页控件,
        collapsible: false,//是否可折叠的
        url: basePath + '/socialSecurityPlan/ss-plan-list?&orgId=' + orgId+'&name='+name,
        remoteSort: false,
        idField: 'ssPlanId',
        singleSelect: false,//是否单选
        rownumbers: true,//行号
        columns: [[
            {field: 'ck', title: '', checkbox: true},
            {field: 'orgId', title: '', hidden: true},
            {field: 'ssPlanId', title: '', hidden: true},
            {field: 'ssPlanDes', title: '社保方案名称', width: '15%', align: 'center',},
            {field: 'ssItemDes', title: '社保项目', width: '15', align: 'center',},
            /*{field: 'createBy', title: '创建人', width: '10%', align: 'center',
                formatter: function (createBy) {
                    return parent.personList[createBy];
                }
            },
            {field: 'createDate', title: '创建时间', width: 100, align: 'center'}*/
            {field: 'createBy', title: '创建人', width:'7%', align: 'center'},
            {field: 'createDeptname', title: '创建科室', width:'10%', align:'center'},
            {field: 'createDept', title: '创建科室编号', hidden:true},
            {field: 'createDate', title: '创建时间', width:'15%',align: 'center'},
            {field: 'updateBy', title: '更新人', width:'7%', align: 'center'},
            {field: 'updateDate', title: '更新时间', width:'15%', align: 'center'}
        ]],
        onLoadSuccess:function(){
            $.messager.progress('close');

        }
    });

    $("#planGrid").datagrid('getPager').pagination({
        pageList: [10, 20, 30, 40, 50], //可以设置每页记录条数的列表
        displayMsg: '当前显示从第{from}条到第{to}条，共{total}条记录',
        onSelectPage: function (pageNumber, pageSize) {
            var state = $('#planGrid').data('datagrid');
            var opts = state.options;
            page=opts.pageNumber = pageNumber;
            rows=opts.pageSize = pageSize;
            searchData(page,rows);

        }
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
         name = $("#NAME").textbox('getValue');

            $("#planGrid").datagrid('reload', basePath + '/socialSecurityPlan/ss-plan-list?&orgId=' + orgId+'&name='+name+ '&page=' + page+ '&rows=' + rows);

            if(search){
                search=false;
                if(page!='1') {
                    $("#planGrid").datagrid('getPager').pagination('select', 1);
                }
            }
            $("#planGrid").datagrid('clearSelections');

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
        $("#NAME").textbox('clear');
        //partName = '';
    };
    $.get(basePath + '/socialSecurityItem/ss-item-all-list?orgId='+orgId, function (data) {
        $.each(data, function (index, item) {
            var obj={};
            obj.value=item.ssItemId;
            obj.label=item.ssItemDes;
            ssItemList.push(obj);
        });
    });
    var unitformatter= function (value) {
        for (var i = 0; i < ssItemList.length; i++) {
            if (ssItemList[i].value == value) {
                return ssItemList[i].label;
            }
        }
    };

    //配置窗口
    $("#editPlan").window({
        title: '社保方案信息',
        closed: true,
        modal: true,
        width : "460",
        height : "400",
        minimizable: false,
        onClose: function () {
            $("#editPlanDes").textbox('setValue','');
            $("#removeDetailBtn").show();
            $("#addDetailBtn").show();
            $("#saveDetailBtn").show();
            $("#editPlanDes").textbox("enable");
            $("#planGrid").datagrid('reload');
            $('#detailGrid').datagrid('clearSelections');
            $('#detailGrid').datagrid('loadData', { total: 0, rows: [] });
            $("#planGrid").datagrid('clearSelections');
        },
        onOpen: function () {

        }
    });

    //新增窗口
    $("#addBtn").on('click', function () {
        $("#editPlan").window("open");
        $("#planId").val('999');
        $("#editForm").form('reset');
        $("#planGrid").datagrid('clearSelections');
        $("#editPlanDes").textbox("enable");
        $("#removeDetailBtn").show();
        $("#addDetailBtn").show();
        $("#saveDetailBtn").show();
        num=0;
        $("#detailGrid").datagrid({
            iconCls: 'icon-edit',//图标
            width: '100%',
            height: '100%',
            nowrap: false,
            striped: true,
            border: true,
            method: 'get',
            toolbar: '#datatb',
            url:basePath + '/socialSecurityPlan/ss-plan-detail-list?orgId='+orgId+'&ssPlanId=""',
            loadMsg: '数据正在加载中，请稍后.....',
            collapsible: false,//是否可折叠
            remoteSort: false,
            idField: 'ssDetailId',
            singleSelect: false,//是否单选
            rownumbers: true,//行号
            fitColumns: true,
            columns: [[
                {field: 'orgId', title: '', width: 100, hidden: true},
               /* {field: 'ck', title: '', width: 200, checkbox: true},*/
                {field: 'ssItemId', title: '社保项目', width: 110, align: 'center',
                    formatter: unitformatter,
                    editor: {
                        type: 'combobox',
                        options: {
                            mode: 'remote',
                            panelHeight: '120px',
                            method: 'GET',
                            valueField: 'value',
                            textField: 'label',
                            data: ssItemList,
                            editable:false
                        }
                    }
                },
                {
                    field: 'companyPay',
                    title: '单位缴纳（%）',
                    width: 80,
                    align: 'right',
                    editor: {type:'numberspinner',options:{min:0,max:100,precision:2}}
                },
                {field: 'personPay', title: '个人缴纳（%）', width: 80, align: 'right',
                    editor: {type:'numberspinner',options:{min:0,max:100,precision:2}}
                }
            ]],
            onClickRow: function (index, row) {
                stopEdit();
                $(this).datagrid('beginEdit', index);
                editIndex = index;
            }
        });
     });
    //

    //修改
    $("#editBtn").on('click', function () {
        var row=$("#planGrid").datagrid("getSelected");
        var row1 = $("#planGrid").datagrid("getSelections");
        if (row1.length != 1) {
            $.messager.alert("提示", "请选择一条数据进行操作!","info");
            return;
        }
        if(row) {
            $("#editPlan").window("open");
            $("#editPlanDes").textbox("setValue",row.ssPlanDes);
            $("#flag").val('1');
            $("#planId").val(row.ssPlanId);
            $("#editPlanDes").textbox("enable");
            $("#removeDetailBtn").show();
            $("#addDetailBtn").show();
            $("#saveDetailBtn").show();
            num=0;
            $("#detailGrid").datagrid({
                iconCls: 'icon-edit',//图标
                width: '100%',
                height: '100%',
                nowrap: false,
                striped: true,
                border: true,
                method: 'get',
                url:basePath + '/socialSecurityPlan/ss-plan-detail-list?orgId='+orgId+'&ssPlanId='+row.ssPlanId,
                toolbar: '#datatb',
                loadMsg: '数据正在加载中，请稍后.....',
                collapsible: false,//是否可折叠
                remoteSort: false,
                idField: 'ssDetailId',
                singleSelect: true,//是否单选
                rownumbers: true,//行号
                fitColumns: true,
                columns: [[
                    /*{field: 'ck', title: '', width: 200, checkbox: true},*/
                    {field: 'orgId', title: '', width: 100, hidden: true},
                    {field: 'ssItemId', title: '社保项目', width: 100, align: 'center',
                        formatter: unitformatter,
                        editor: {
                            type: 'combobox',
                            options: {
                                mode: 'remote',
                                panelHeight: '120px',
                                method: 'GET',
                                valueField: 'value',
                                textField: 'label',
                                data: ssItemList
                            }
                        }
                    },
                    {
                        field: 'companyPay',
                        title: '单位缴纳（%）',
                        width: 80,
                        align: 'right',
                        editor: {type:'numberspinner',options:{min:0,max:100,precision:2}}
                    },
                    {field: 'personPay', title: '个人缴纳（%）', width: 80, align: 'right',
                        editor: {type:'numberspinner',options:{min:0,max:100,precision:2}}
                    }
                ]],
                onClickRow: function (index, row) {
                    stopEdit();
                    $(this).datagrid('beginEdit', index);
                    editIndex = index;
                }
            });
        }
        else{
            $.messager.alert('提示', '请选择一条记录!', 'info');  //提示信息
        }
    });
    //查看信息
    $("#viewBtn").on('click', function () {
        var row=$("#planGrid").datagrid("getSelected");
        var rows = $("#planGrid").datagrid("getSelections");
        if (rows.length != 1) {
            $.messager.alert("提示", "请选择一条数据进行操作!","info");
            return;
        }
        if(row) {
            $("#editPlan").window("open");

            $("#editPlanDes").textbox("setValue",row.ssPlanDes);
            $("#flag").val('1');
            $("#planId").val(row.ssPlanId);
            $("#detailGrid").datagrid({
                iconCls: 'icon-edit',//图标
                width: '100%',
                height: '100%',
                nowrap: false,
                striped: true,
                border: true,
                method: 'get',
                url:basePath + '/socialSecurityPlan/ss-plan-detail-list?orgId='+orgId+'&ssPlanId='+row.ssPlanId,
                toolbar: '#datatb',
                loadMsg: '数据正在加载中，请稍后.....',
                collapsible: false,//是否可折叠
                remoteSort: false,
                idField: 'ssDetailId',
                singleSelect: true,//是否单选
                rownumbers: true,//行号
                fitColumns: true,
                columns: [[
                    /*{field: 'ck', title: '', width: 200, checkbox: true},*/
                    {field: 'orgId', title: '', width: 100, hidden: true},
                    {field: 'ssItemId', title: '社保项目', width: 100, align: 'center',
                        formatter: unitformatter
                        /*editor: {
                            type: 'combobox',
                            options: {
                                mode: 'remote',
                                panelHeight: 'auto',
                                method: 'GET',
                                valueField: 'value',
                                textField: 'label',
                                data: ssItemList,
                                readonly:true
                            }
                        }*/
                    },
                    {
                        field: 'companyPay',
                        title: '单位缴纳（%）',
                        width: 80,
                        align: 'right'
                        //editor: {type:'numberspinner',options:{min:0,max:100,precision:2,readonly:true}}
                    },
                    {field: 'personPay', title: '个人缴纳（%）', width: 80, align: 'right'
                        //editor: {type:'numberspinner',options:{min:0,max:100,precision:2,readonly:true}}
                    }
                ]],
                onClickRow: function (index, row) {
                    stopEdit();
                    $(this).datagrid('beginEdit', index);
                    editIndex = index;
                }
            });
            $("#removeDetailBtn").hide();
            $("#addDetailBtn").hide();
            $("#saveDetailBtn").hide();

            $("#editPlanDes").textbox("setValue",row.ssPlanDes);
            $("#editPlanDes").textbox("disable");

            //loadData(row.ssPlanId);
        }
        else{
            $.messager.alert('提示', '请选择一条记录!', 'info');  //提示信息
        }
    });

   //刷新
    var reload = function () {

        $.get(basePath + "/socialSecurityPlan/ss-plan-list?orgId="+orgId, function (data) {
            $("#planGrid").datagrid('loadData', data);
        });
    };
    //删除
    $("#delBtn").on('click', function () {
        //stopEdit();
        var row = $("#planGrid").datagrid('getSelections');
        if (row == null||row.length == 0||!row) {
            $.messager.alert("提示", "请选择要删除的数据!","info");
            return;
        }
        //row.createOrg = orgId;
        //判断是否在社保投保中被占用，若被占用不能删除。
        $.postJSON(basePath + "/socialSecurityPlan/if-occupy", JSON.stringify(row),
            function (data) {
                if (data.code == "yes") {
                    $.messager.alert('提示', '所选社保方案中包含被占用项，请先删除其对应的社保投保数据！', 'info');
                    return;
                }
                {
                    $.messager.confirm('提示', '确定要批量删除所选中的数据么?', function (r) {
                        if (r) {
                            $.postJSON(basePath + "/socialSecurityPlan/ss-plan-del", JSON.stringify(row), function (data) {
                                /*$.messager.alert('系统提示', '删除成功', 'info');*/
                                $('#planGrid').datagrid('reload');
                                row.length = 0;
                                $("#planGrid").datagrid('clearSelections');
                            })
                        }
                    });
                }
            });
    });
    //新增信息
    $("#addDetailBtn").on('click', function () {
        stopEdit();
        $("#detailGrid").datagrid('appendRow', {ssDetailId:'&'+num});
        num++;
        var rows = $("#detailGrid").datagrid('getRows');
        var addRowIndex = $("#detailGrid").datagrid('getRowIndex', rows[rows.length - 1]);
        editIndex = addRowIndex;
        $("#detailGrid").datagrid('selectRow', editIndex);
        $("#detailGrid").datagrid('beginEdit', editIndex);
    });

    //删除详细信息数据
    $("#removeDetailBtn").on('click', function () {

        if (editIndex || editIndex == 0) {
            $("#detailGrid").datagrid("endEdit", editIndex);
        }
        var row1 = $("#detailGrid").datagrid('getSelected');

        if (row1 == null||!row1) {
            $.messager.alert("提示", "请选择要删除的项目", "info");
            return;
        }
        $.messager.confirm('提示', '确定要进行删除操作吗', function (r) {
            if (r) {
                var a=[];
                var b=[];
                //for (var i = 0; i < row1.length; i++) {
                    //if (row1[i].ssDetailId == 1) {
                        a.push(row1);

                    //} else {
                        b.push(row1.ssDetailId)

                   // }

                //}
                if(a.length>0&&row1.ssDetailId.substring(0,1)=='&'){
                    for(var j =0;j<a.length;j++){
                        var index = $('#detailGrid').datagrid('getRowIndex',row1);
                        $("#detailGrid").datagrid('deleteRow', index);
                    }
                    $('#detailGrid').datagrid('clearSelections');
                }
                if(b.length>0&&row1.ssDetailId.substring(0,1)!='&'){
                    for(var j =0;j<b.length;j++){
                        ssPlanVo.ssDetailId =b[j];
                       /* $.postJSON(basePath + "/socialSecurityPlan/ss-detail-del", JSON.stringify(ssPlanVo), function (data) {
                            var index = $('#detailGrid').datagrid('getRowIndex',b[j]);
                            $("#detailGrid").datagrid('deleteRow', index);
                        });*/
                        $.get(basePath + "/socialSecurityPlan/ss-detail-del?id="+b[j],  function (data) {
                            $("#detailGrid").datagrid('reload');
                            $('#detailGrid').datagrid('clearSelections');
                        });
                    }

                }


            }
        });

    });

    //保存数据
    $("#saveDetailBtn").on('click', function () {
        var rows= $("#detailGrid").datagrid("getRows");
        //获取
        var ssPlanDes = $("#editPlanDes").textbox('getValue');
        var id= $("#planId").val();
        if(ssPlanDes==''||ssPlanDes.indexOf(" ") >=0)
        {
            $.messager.alert("提示", "请输入有效的社保方案名称，不能包含空格！","info");
            return;
        }
        if(ssPlanDes.length>20){
            $.messager.alert("提示","社保方案名称内容输入过长！",'info');
            return;
        }
        if(!rows||rows.length==0){
            $.messager.alert("提示", "请添加对应社保项目!", "info");
            return;
        }
        for (var i=0;i<rows.length;i++)
        {
            $("#detailGrid").datagrid('endEdit', i);
        }
        for (var i = 0; i < rows.length; i++) {
            if(rows[i].ssItemId==''){
                $.messager.alert("提示", "请选择社保项目!", "info");
                return;
            }
            if(rows[i].companyPay==''){
                $.messager.alert("提示", "请输入单位缴纳百分比!", "info");
                return;
            }
            if(rows[i].personPay==''){
                $.messager.alert("提示", "请输入个人缴纳百分比!", "info");
                return;
            }
            for (var j = i + 1; j < rows.length; j++) {
                if (rows[i].ssItemId == rows[j].ssItemId) {
                    $.messager.alert("提示", "不能添加重复的社保项目!", "info");
                    return;
                }
            }
        }

        var insertData = $("#detailGrid").datagrid("getChanges", "inserted");
        var updateData = $("#detailGrid").datagrid("getChanges", "updated");
        if(insertData.length==0){
            insertData=[];
        }
        if(updateData.length==0){
            updateData=[];
        }
        /*if(insertData.length==0&&updateData.length==0){
            $("#editPlan").window('close');
           return;
        }*/
        ssPlanVo.inserted = insertData;
        ssPlanVo.updated = updateData;
        ssPlanVo.orgId = parent.config.org_Id;
        ssPlanVo.ssPlanDes = ssPlanDes;
        ssPlanVo.ssPlanId = id;
        //判断是否已存在相同方案
        $.get(basePath +"/socialSecurityPlan/if-exist?orgId="+orgId+"&ssPlanDes="+ssPlanDes+"&id="+id,
            function (data) {
                var list = eval(data);
                for (var i = 0; i < 1; i++) {
                    var num = list[i]['num'];
                }
                if (num == '1' || num > '1') {
                    var str = '该社保方案已存在！';
                    $.messager.alert("提示", str, "info");
                    return;
                }
                $.postJSON(basePath + "/socialSecurityPlan/merge", JSON.stringify(ssPlanVo), function (data) {
                    $("#editPlan").window('close');
                    //$("#planGrid").datagrid('reload');
                    $("#editForm").form('reset');
                    $("#detailGrid").datagrid('clearSelections');
                    $("#planGrid").datagrid('clearSelections');
                })
            });
        $("#editPlan").window('close');
        $("#planGrid").datagrid('clearSelections');
        $("#planGrid").datagrid('reload');
    });
});
