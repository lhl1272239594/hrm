

var basePath = "/service";
var page='1';
var rows='30';
var search=false;
$(function () {
    var orgId = parent.config.org_Id;
    var itemId;
    var itemName;
    var parentId;
    var state;

    $("#itemGrid").datagrid({
        fit: true,
        fitColumns: true,
        striped: true,
        singleSelect: false,
        toolbar: '#ft',
        method: 'GET',
        rownumbers: true,
        loadMsg: '数据正在加载中，请稍后.....',
        url : basePath + "/exam/findItemByParent?parent=" + parentId + "&orgId=" + orgId,
        pagination: true,//分页控件
        pageSize: 30,
        columns: [[{
            title: "parentId",
            field: "parentId",
            hidden: true
        },{
            title: "orgId",
            field: "orgId",
            hidden: true
        },
            {field: 'ck', title: '', checkbox: true},
            {
                title: "分类名称",
                field: "itemName",
                width: '18%',
                align: 'center'
            }, {
                title: "状态",          //0，否，1是
                field: "state",
                align: 'center',
                width: '10%',
                align: 'center',
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
            {field: 'createDate', title: '创建时间', width:'20%',align: 'center'},
            {field: 'updateBy', title: '更新人', width:'15%', align: 'center'},
            {field: 'updateDate', title: '更新时间', width:'20%', align: 'center'}
        ]],onLoadSuccess:function(data){
            $("#newDialog").css('display','block');
            $("#itemGrid").datagrid('getPager').pagination({
                pageList: [10, 20, 30, 40, 50], //可以设置每页记录条数的列表
                displayMsg: '当前显示从第{from}条到第{to}条，共{total}条记录',
                onSelectPage: function (pageNumber, pageSize) {
                    var state1 = $('#itemGrid').data('datagrid');
                    var opts = state1.options;
                    page=opts.pageNumber = pageNumber;
                    rows=opts.pageSize = pageSize;
                    searchData(page,rows);
                    return;

                }
            });
        }
        });

    var searchData= function (page,rows){
        $.get(basePath + '/exam/findItemByParent?parent=' + parentId + '&orgId=' + orgId+ '&page=' + page+ '&rows=' + rows, function (data) {
            $("#itemGrid").datagrid('loadData', data);
            if(search){
                search=false;
                $("#itemGrid").datagrid('getPager').pagination('select',1);

            }
        });
    }
    /**
     * 新增试题类型
     */
    $("#newDialog").dialog({
        title: '试题类型新增',
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
        $("#newDialog").dialog("setTitle","试题类型新增").dialog("open");
        $("#newForm").form('reset');
        $("#itemId").val('');
    });
    /**
     * 修改
     */
    $("#editBtn").on('click', function () {
        //reset();
        flag = 0;
        var row = $("#itemGrid").datagrid("getSelections");
        if(row.length==1){
            $.postJSON(basePath + "/exam/checkItemIsUsed",JSON.stringify(row), function (data) {
                if (data.data == "success") {
                    if(data.code=="isUsed"){
                        $("#itemGrid").datagrid("clearSelections");
                        $.messager.alert("提示", "该试题类型正在使用中!","info");
                    }
                    if(data.code=="success"){
                        $("#newDialog").dialog("setTitle","试题类型修改").dialog("open");
                        $("#itemId").val(row[0].itemId);
                        $("#itemName").textbox("setValue",row[0].itemName);
                    }
                }
            })
        }
        else{
            $("#itemGrid").datagrid("clearSelections");
            $.messager.alert("提示","请选择一条数据!",'info');
            return;
        }

    });

    /**
     * 启用
     */
    $("#okBtn").on('click', function () {
        var row = $("#itemGrid").datagrid('getSelections');
        if (row.length==0) {
            $("#itemGrid").datagrid("clearSelections");
            $.messager.alert("提示", "请选择要启用的试题类型!","info");
            return;
        }
        if (row.length>0) {
            for (var i = 0; i < row.length; i++) {
                row[i].state = '1';
                row[i].updateBy=parent.config.persion_Id;
            }
            $.messager.confirm('提示', '确定启用选中的试题类型吗？', function (r) {
                if (r) {
                    $.postJSON(basePath + "/exam/itemState",JSON.stringify(row), function (data) {
                        /*$.messager.alert('系统提示', '删除成功', 'info');*/
                        $('#itemGrid').datagrid('reload');
                        row.length = 0;
                        $("#itemGrid").datagrid('clearSelections');
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
        var row = $("#itemGrid").datagrid('getSelections');
        if (row.length==0) {
            $("#itemGrid").datagrid("clearSelections");
            $.messager.alert("提示", "请选择要停用的试题类型!","info");
            return;
        }
        if (row.length>0) {
            for (var i = 0; i < row.length; i++) {
                row[i].state = '0';
                row[i].updateBy=parent.config.persion_Id;
            }
            $.postJSON(basePath + "/exam/checkItemIsUsed",JSON.stringify(row), function (data) {
                if (data.data == "success") {
                    if(data.code=="isUsed"){
                        $.messager.alert("提示", "所选试题类型中有部分试题类型正在使用中!","info");
                    }
                    if(data.code=="success"){
                        $.messager.confirm('提示', '确定停用选中的试题类型吗？', function (r) {
                            if (r) {
                                $.postJSON(basePath + "/exam/itemState",JSON.stringify(row), function (data) {
                                    /*$.messager.alert('系统提示', '删除成功', 'info');*/
                                    $('#itemGrid').datagrid('reload');
                                    row.length = 0;
                                    $("#itemGrid").datagrid('clearSelections');
                                })
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
        var row = $("#itemGrid").datagrid('getSelections');
        if (row.length==0) {
            $("#itemGrid").datagrid("clearSelections");
            $.messager.alert("提示", "请选择要删除的试题类型!","info");
            return;
        }
        if (row.length>0) {
            for (var i = 0; i < row.length; i++) {
                row[i].state = '1';
                row[i].updateBy=parent.config.persion_Id;
            }
            $.postJSON(basePath + "/exam/checkItemIsUsed",JSON.stringify(row), function (data) {
                if (data.data == "success") {
                    if(data.code=="isUsed"){
                        $("#itemGrid").datagrid("clearSelections");
                        $.messager.alert("提示", "所选试题类型中有部分试题类型正在使用中!","info");
                    }
                    if(data.code=="success"){
                        $.messager.confirm('提示', '确定删除选中的试题类型吗？', function (r) {
                            if (r) {
                                $.postJSON(basePath + "/exam/delItem", JSON.stringify(row), function (data) {
                                    /*$.messager.alert('系统提示', '删除成功', 'info');*/
                                    $('#itemGrid').datagrid('reload');
                                    row.length = 0;
                                    $("#itemGrid").datagrid('clearSelections');
                                })
                            }
                        });
                    }
                }
            })

        }

    });

    /**
     * 新增修改保存
     */
    $("#submitBtn").on('click', function () {
        var itemName=$("#itemName").textbox("getValue");
        if (!itemName||itemName.indexOf(" ") >=0) {
            $("#itemGrid").datagrid("clearSelections");
            $.messager.alert("提示", "请输入试题类型名称，不能包含空格!", 'info');
            return;
        }
        if(getRealLen(itemName)>20){
            $.messager.alert("提示","试题类型名称内容输入过长！",'info');
            return;
        }
        var item = {};
        item.itemId = $("#itemId").val();
        item.orgId = parent.config.org_Id;
        item.createBy = parent.config.persion_Id;
        item.itemName = itemName;

        $.postJSON(basePath + "/exam/itemMerge", JSON.stringify(item), function (data) {
            if (data.data == "success") {
                if(data.code=="hasName"){
                    $("#itemGrid").datagrid("clearSelections");
                    $.messager.alert('提示', '该试题类型名称已存在!', 'info');

                }
                if(data.code=="success"){
                    $('#newDialog').window('close');
                    $("#itemGrid").datagrid('reload');
                    $("#newForm").form('reset');
                    $("#itemGrid").datagrid('clearSelections');
                }
            }
            else {
                $("#itemGrid").datagrid("clearSelections");
                $.messager.alert('系统提示', '保存失败', 'error');

            }
        })

    });
    

});



