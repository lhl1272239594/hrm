/**
 *排班规则
 * @author yangchen
 * @version 2016-08-18
 */
var basePath = "/service";
var freRuleId="999";
var freRuleDes="999";
var freRuleDes="999";
var freLoopDays="";
var dg;
var d;
var flag;
var frequencyRuleVo = {};
var freItemList=[];
var staffId = parent.config.staffId;

$(function () {
 //定义组织ID
    var orgId = parent.config.org_Id;
    var editIndex;
    var stopEdit = function () {
        if (editIndex || editIndex == 0) {
            $("#freRuleDataGrid").datagrid('endEdit', editIndex);
            editIndex = undefined;
        }
    };
    //班次设置数据
    $("#freRuleGrid").datagrid({
        iconCls: 'icon-edit',//图标
        width: '100%',
        height: '100%',
        nowrap: false,
        striped: true,
        border: true,
        method: 'get',
        toolbar: '#ptb',
        fitColumns:true,
        loadMsg: '数据正在加载中，请稍后.....',
        pagination: true,//分页控件
        pageSize: 30,
        pageList: [10, 20, 30, 40, 50],//可以设置每页记录条数的列表
        collapsible: false,//是否可折叠的
        url:basePath + '/frequencyRule/frequency-rule-list?freRuleDes=' +freRuleDes + '&orgId=' + orgId,
        remoteSort: false,
        idField: 'fesDateId',
        singleSelect: true,//是否单选
        rownumbers: true,//行号
        columns: [[
            {field: 'orgId', title: '', width: 100,hidden: true},
            {field: 'freRuleId', title: '', width: 100,hidden: true},
            {field: 'freRuleDes', title: '排班规则名称', width: 100, align: 'center'},
            {field: 'freLoopDays', title: '循环周期（天）', width: 100, align: 'center'},
            {field: 'createBy', title: '创建人', width: '10%', align: 'center',
                formatter: function (createBy) {
                    return parent.personList[createBy];
                }
            },
            {field: 'createDate', title: '创建时间', width: 100, align: 'center'}

        ]],
        onLoadSuccess: function (data) {
            $("#freRuleGrid").datagrid('getPager').pagination({
                pageList: [10, 20, 30, 40, 50], //可以设置每页记录条数的列表
                displayMsg: '当前显示从第{from}条到第{to}条，共{total}条记录'
            });
        }
    });

   /* $("#freRuleGrid").datagrid('getPager').pagination({
        pageList: [10, 20, 30, 40, 50], //可以设置每页记录条数的列表
        displayMsg: '当前显示从第{from}条到第{to}条，共{total}条记录',
        onSelectPage: function (pageNumber, pageSize) {
            var state = $('#freRuleGrid').data('datagrid');
            var opts = state.options;
            page=opts.pageNumber = pageNumber;
            rows=opts.pageSize = pageSize;
            searchData(page,rows);
            return;
        }
    });*/

    //按条件查询数据
   /* $("#searchBtn").on("click", function () {
        //获取规则名称
        freRuleDes = $("#freRule").val('');
        $.get(basePath + '/frequency/frequency-rule-list?freRuleDes=' + freRuleDes,function (data) {
            $("#freGrid").datagrid('loadData', data);
        });
    });*/


    $.get(basePath + '/frequency/frequency-all-list?orgId='+orgId, function (data) {
        $.each(data, function (index, item) {
            var obj={};
            obj.value=item.freItemId;
            obj.label=item.freItemDes;
            freItemList.push(obj);

        });
    });


    var unitformatter= function (value) {
        for (var i = 0; i < freItemList.length; i++) {
            if (freItemList[i].value == value) {
                return freItemList[i].label;
            }
        }
    };

    //配置窗口
    $("#editFreRuleGrid").window({
        title: '排班规则信息',
        closed: true,
        modal: true,
        width : "250",
        height : "400",
        minimizable: false,

        onClose: function () {
            $('#freRuleGrid').datagrid('clearSelections');

            $("#addDateBtn").show();
            $("#delDateBtn").show();
            $("#saveDateBtn").show();
            $("#freRuleGrid").datagrid('reload');
            $("#editFreRule").textbox('enable');
            $("#freRuleDataGrid").datagrid('loadData', { total: 0, rows: [] });
            $("#freRuleGrid").datagrid('clearSelections');
            $("#freRuleDataGrid").edatagrid('clearSelections');
            $("#editForm").form('reset');
            $('#freRuleGrid').datagrid('clearSelections');
        },
        onOpen: function () {

            $("#addDateBtn").show();
            $("#delDateBtn").show();
            $("#saveDateBtn").show();

        }

    });

  var loadData= function (freRuleId) {
        $.get(basePath + '/frequencyRule/frequency-data-list?freRuleId=' + freRuleId + '&orgId=' + orgId,
            function (data) {
            $("#freRuleDataGrid").edatagrid('loadData',data);
        });
    };



    //新增排班规则窗口
    $("#addBtn").on('click', function () {
        $("#editFreRuleGrid").window("open");
        $("#flag").val('0');
        $("#id").val('999');
        $("#freRuleDataGrid").edatagrid({
            iconCls: 'icon-edit',//图标
            width: '100%',
            height: '100%',
            nowrap: false,
            striped: true,
            border: true,
            method: 'get',
            toolbar: '#ftb',
            fitColumns: true,
            loadMsg: '数据正在加载中，请稍后.....',
            collapsible: false,//是否可折叠的
            remoteSort: false,
            singleSelect: false,//是否单选
            columns: [[
                {field: 'orgId', title: '', width: 100, hidden: true},
                {field: 'freRuleDataId', title: '', width: 100, hidden: true},
                {field: 'freItemOrder', title: '班次顺序', align: 'center',width: 100,},
                {field: 'freItemId', title: '班次项目', width: 100, align: 'center',
                    formatter: unitformatter,
                    editor: {
                        type: 'combobox',
                        options: {
                            mode: 'remote',
                            panelHeight: '180px',
                            method: 'GET',
                            valueField: 'value',
                            textField: 'label',
                            editable: false,
                            data: freItemList
                        }
                    }
                }
            ]],
            onClickRow: function (index, row) {
                stopEdit();
                $(this).datagrid('beginEdit', index);
                editIndex = index;
            }
        });

    });
    //修改班次窗口
    $("#editBtn").on('click', function () {
        var row = $("#freRuleGrid").datagrid('getSelected');
        if(row){
            $("#editFreRuleGrid").window("open");
            freRuleDes= row.freRuleDes;
            freRuleId= row.freRuleId;
            freLoopDays=row.freLoopDays;
            $("#editFreRuleLoopDays").textbox('setValue',freLoopDays);
            $("#editFreRule").textbox('setValue',freRuleDes);
            $("#id").val(freRuleId);
            $("#freRuleDataGrid").edatagrid({
                iconCls: 'icon-edit',//图标
                width: '100%',
                height: '100%',
                nowrap: false,
                striped: true,
                border: true,
                //url:basePath + '/frequencyRule/frequency-data-list?freRuleId=' + freRuleId + '&orgId=' + orgId,
                method: 'get',
                toolbar: '#ftb',
                fitColumns: true,
                loadMsg: '数据正在加载中，请稍后.....',
                collapsible: false,//是否可折叠的
                remoteSort: false,
                singleSelect: false,//是否单选
                columns: [[
                    {field: 'orgId', title: '', width: 100, hidden: true},
                    {field: 'freRuleDataId', title: '', width: 100, hidden: true},
                    {field: 'freItemOrder', title: '班次顺序', width: 100,align: 'center'},

                    {field: 'freItemId', title: '班次项目', width: 100, align: 'center',
                        formatter: unitformatter,
                        editor: {
                            type: 'combobox',
                            options: {
                                mode: 'remote',
                                panelHeight: 'auto',
                                method: 'GET',
                                valueField: 'value',
                                textField: 'label',
                                data: freItemList,
                                editable: false

                            }
                        }
                    }
                ]],
                onClickRow: function (index, row) {
                    stopEdit();
                    $(this).datagrid('beginEdit', index);
                    editIndex = index;
                }

            });
            loadData(freRuleId);
        }else{
            $.messager.alert('提示', '请选择一行记录！', 'info');

            return;
        }

    });

    //查看班次窗口
    $("#viewBtn").on('click', function () {
        var row = $("#freRuleGrid").datagrid('getSelected');
        if(row){
            $("#editFreRuleGrid").window("open");
            freRuleDes= row.freRuleDes;
            freRuleId= row.freRuleId;
            freLoopDays=row.freLoopDays;
            $("#editFreRule").textbox('setValue',freRuleDes);
            $("#editFreRuleLoopDays").textbox('setValue',freLoopDays);

            $("#editFreRule").textbox('disable');
            $("#addDateBtn").hide();
            $("#delDateBtn").hide();
            $("#saveDateBtn").hide();
            $("#freRuleDataGrid").edatagrid({
                iconCls: 'icon-edit',//图标
                width: '100%',
                height: '100%',
                nowrap: false,
                striped: true,
                border: true,
                method: 'get',
                toolbar: '#ftb',
                fitColumns: true,
                loadMsg: '数据正在加载中，请稍后.....',
                collapsible: false,//是否可折叠的
                remoteSort: false,
                singleSelect: false,//是否单选
                columns: [[
                    {field: 'orgId', title: '', width: 100, hidden: true},
                    {field: 'freRuleId', title: '', width: 100, hidden: true},
                    {field: 'freItemOrder', title: '班次顺序', width: 100, align: 'center',},
                    {field: 'freRuleDataId', title: '', width: 100, hidden: true},
                    {field: 'freItemId', title: '班次项目', width: 100, align: 'center',
                        formatter: unitformatter

                    }
                ]]

            });
            loadData(freRuleId);

        }else{
            $.messager.show({
                title:'提示',
                msg:'请选择一行记录！',
                showType:'show',
                width:300,
                height:80,
                timeout:1000,
                style:{
                    right:'',
                    top:document.body.scrollTop+document.documentElement.scrollTop,
                    bottom:''
                }
            });
            return;
        }

    });
   /* 删除*/
    $("#delBtn").on('click', function () {
        flag='1';
        var row = $("#freRuleGrid").datagrid('getSelected');

        if (row) {
            frequencyRuleVo.freRuleId = row.freRuleId;
            freRuleId = row.freRuleId;
            //判断是否在排班管理中被占用，若被占用不能删除。
            $.post(basePath + "/frequencyRule/if-occupy?freRuleId="+freRuleId+"&orgId="+orgId,
                function (data) {
                    if (data.code == "yes") {
                        $.messager.alert('提示', '该排班规则已在排班管理中被使用，请先删除排班管理中的对应数据！', 'info');
                        return;
                    }
                    $.messager.confirm('提示', '确定要进行删除操作吗', function (r) {
                        if (r) {
                            $.postJSON(basePath + "/frequencyRule/frequency-rule-del", JSON.stringify(frequencyRuleVo), function (data) {
                                $('#freRuleGrid').datagrid('reload');
                                row.length = 0;
                                $('#freRuleGrid').datagrid('clearSelections');

                            });
                        }
                    });
                });
        } else {
            $.messager.alert("提示", "请选择要删除的项目");
        }

    });

    //新增班次项目
    $("#addDateBtn").on('click', function () {
        var n=1;
        var order=0;
        var num=$("#editFreRuleLoopDays").textbox('getValue');
        if (num==order&&num==0){
            order=n++;

        }else{
            num++;
            order=num;
        }
        $('#freRuleDataGrid').edatagrid('addRow',{
            row:{
                freRuleDataId:1,
                freItemOrder:order
            }
        });
        $("#editFreRuleLoopDays").textbox('setValue',order);
    });

    //删除班次项目
    $("#delDateBtn").on('click', function () {

        var a = $("#freRuleDataGrid").edatagrid('getRows');

        var rows = $("#freRuleDataGrid").edatagrid('getSelections');
        if (rows.length==0) {
            $.messager.alert("提示", "请选择要删除的项目","info");
            return;
        }
        var num= $("#freRuleDataGrid").edatagrid('getRows').length;

        for (var i = 0; i < rows.length; i++) {
            var index = $('#freRuleDataGrid').edatagrid('getRowIndex',rows[i]);

            if(index+1!=num){
                $.messager.alert("提示", "请从最后一行开始删除！","info");
                return;
            }

            var loopDays=$("#editFreRuleLoopDays").textbox('getValue');


            $.messager.confirm('提示', '确定要进行删除操作吗', function (r) {
                if (r) {
                    frequencyRuleVo.freRuleId = rows[0].freRuleId;
                    frequencyRuleVo.freRuleDataId = rows[0].freRuleDataId;

                    $("#freRuleDataGrid").edatagrid('destroyRow', index);
                    $("#editFreRuleLoopDays").textbox('setValue',loopDays-1);
                    frequencyRuleVo.freLoopDays =loopDays-1;
                    $.postJSON(basePath +  "/frequencyRule/frequency-rule-data-del", JSON.stringify(frequencyRuleVo),
                        function (data) {

                        });
                }
            })

        }

        $('#freRuleDataGrid').edatagrid('clearSelections');


    });
    //保存
    $("#saveDateBtn").on('click', function () {
        var rows= $("#freRuleDataGrid").datagrid("getRows");
        freRuleId=$("#id").val();
        for (var i=0;i<rows.length;i++)
        {
            $("#freRuleDataGrid").edatagrid('endEdit', i);
        }
        //获取班次描述
        freRuleDes= $("#editFreRule").textbox("getValue");
        //获取循环总天数
        freLoopDays=$("#editFreRuleLoopDays").textbox("getValue");
        if(freRuleDes==''||freRuleDes.indexOf(" ") >=0)
        {
            $.messager.alert("提示", "请输入有效的班规则名称，不能包含空格!","info");
            return;
        }
        if (getRealLen(freRuleDes)>20) {
            $.messager.alert("提示", "排班规则名称内容输入过长！", "info");
            return
        }
        if(rows.length==0)
        {
            $.messager.alert("提示", "请增加班次项目!","info");
            return;
        }
        for (var i=0;i<rows.length;i++)
        {
            var freItemId=rows[i].freItemId;
            if(freItemId==''){
                $.messager.alert("提示", "请选择第<"+(i+1)+">行班次项目!","info");
                return;
            }
        }
        var insertData = $("#freRuleDataGrid").datagrid("getChanges", "inserted");
        var updateData = $("#freRuleDataGrid").datagrid("getChanges", "updated");
        frequencyRuleVo.inserted = insertData;
        frequencyRuleVo.updated = updateData;
        frequencyRuleVo.orgId = parent.config.org_Id;
        frequencyRuleVo.freRuleDes = freRuleDes;
        frequencyRuleVo.freRuleId = freRuleId;
        frequencyRuleVo.freLoopDays = freLoopDays;



            $.get(basePath +"/frequencyRule/frequency-rule-boolean?freRuleDes=" + freRuleDes+"&orgId="+orgId+"&freRuleId="+freRuleId,

                function (data) {
                var list = eval(data);
                var num=list[0]['num'];

                if (num == '1') {
                    var str = '该排班规则已经存在，请重新输入！';
                    $.messager.alert("提示", str, "info");
                    return
                }
                else{
                    $.messager.progress({
                        title: '提示！',
                        msg:  '数据量较大，请稍候...',
                        text: '加载中.......'
                    });
                    //如果不重复，执行保存代码
                    $.postJSON(basePath + "/frequencyRule/merge", JSON.stringify(frequencyRuleVo), function (data) {
                        $.messager.progress('close')
                        $("#freRuleGrid").datagrid('reload');
                        $("#editFreRuleGrid").window('close');
                        $('#freRuleGrid').datagrid('clearSelections');
                    })
                }
            });
        /*//查询数据库，新增数据是否重复
         $.get(basePath +"/frequencyRule/frequency-rule-boolean?freRuleDes=" + freRuleDes+"&orgId="+orgId,
         function (data) {
         var list = eval(data);
         var num=list[0]['num'];
         if(num=='1'){
         var str = '该规则描述已经存在，请重新输入！';
         $.messager.alert("提示", str);
         return
         }
         {*/
        //如果不重复，执行保存代码


    });

});



