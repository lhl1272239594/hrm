/**
 * 工资结算日期
 * @author
 * @version 2016-09-22
 */
var orgId= parent.config.org_Id;
var deptId='';
var page='1';
var rows='30';
var salaryTime = {};
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
        loadMsg: '数据正在加载中，请稍后.....',
        collapsible: false,//是否可折叠的
        url: basePath +'/salary-time/time-list?orgId=' + orgId+'&deptId=' + deptId,
        remoteSort: false,
        //idField: 'timeId',
        singleSelect: true,//是否单选
        rownumbers: true,//行号
        columns: [[
            {field: 'timeId', title: '结算日编号', hidden: true},
            {field: 'orgName', title: '机构', width:'20%', align: 'center'},
            {field: 'deptId', title: '科室编号', hidden: true},
            {field: 'deptName', title: '科室', hidden: true},
            {field: 'time', title: '工资结算日期', width:'10%', align: 'center'},
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
                displayMsg: '当前显示从第{from}条到第{to}条，共{total}条记录'/*,
                onSelectPage: function (pageNumber, pageSize) {
                    var state = $('#dataGrid').data('datagrid');
                    var opts = state.options;
                    page=opts.pageNumber = pageNumber;
                    rows=opts.pageSize = pageSize;
                    searchData(page,rows);
                }*/
            });
        }
    });


    //查询、新增条件:招聘科室树选择
    $("#DEPT_ID1").combotree({
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
                $('#DEPT_ID1').combotree('clear');
                $.messager.alert("提示", "请选择具体科室!", "info");
            }
        }
    });
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
    var loadDept1 = function () {

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
            $("#DEPT_ID1").combotree('loadData', treeDepts);
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
        //deptId = $("#DEPT_ID1").combotree('getValue');
        /*$.get(basePath +'/salary-time/time-list?orgId=' + orgId+'&deptId=' + deptId+'&page='+page+'&rows='+rows, function (data) {
            $("#dataGrid").datagrid('loadData', data);*/
            $("#dataGrid").datagrid('reload', basePath +'/salary-time/time-list?orgId=' + orgId+'&deptId=' + deptId+'&page='+page+'&rows='+rows);
            if(search){
                search=false;
                $("#dataGrid").datagrid('getPager').pagination('select', 1);
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
        $("#DEPT_ID1").combobox('clear');
        $("#DEPT_ID1").combobox("setValue","");
    };

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
//$.get(basePath + "/salary-time/if-time-exist?orgId=" + orgId+'&deptId='+$("#DEPT_ID").combobox('getValue')+'&timeId='+$("#TIME_ID").val(),
        $.get(basePath + "/salary-time/if-time-exist?orgId=" + orgId,
            function (data) {
                var list = eval(data);
                for (var i = 0; i < 1; i++) {
                    var num = list[i]['num'];
                }
                if (num != '0') {
                    var str = '该机构已存在工资结算日，不能重复添加，如需修改请点击“修改”按钮！';
                    $.messager.alert("提示", str, "info");
                    return
                }
                $("#newForm").form('reset');
                $("#newDialog").dialog("setTitle", "新增").dialog("open");
                $("#TIME_ID").val("");
                $("#TIME").combobox("setValue", '');
                $("#DEPT_ID").combobox("setValue", "");
                $("#DEPT_ID").combobox('enable');
            });
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
        $("#newDialog").dialog("setTitle","工资结算日修改").dialog("open");
        $("#TIME_ID").val(row.timeId);
        $("#DEPT_ID").combobox("disable");
        $("#TIME").combobox("setValue",row.time);
        $("#DEPT_ID").combobox("setValue",row.deptId);
        $("#DEPT_ID").combobox("setText",row.deptName);
        $("#DEPT_ID").combobox("disable");
    });

    /**
     * 新增修改保存
     */
    $("#submitBtn").on('click', function () {
        /*if (!$("#DEPT_ID").combobox("getValue")) {
            $.messager.alert("提示", "请选择科室！", 'info');
            return;
        }*/
        if (!$("#TIME").combobox("getValue")) {
            $.messager.alert("提示", "请选择结算日期！", 'info');
            return;
        }
        salaryTime.orgId = orgId;
        salaryTime.timeId = $("#TIME_ID").val();
        salaryTime.time = $("#TIME").combobox('getValue');
        //salaryTime.deptId = $("#DEPT_ID").combobox('getValue');
        //判断该部门是否已存在结算日信息
        //$.get(basePath + "/salary-time/if-time-exist?orgId=" + orgId+'&deptId='+$("#DEPT_ID").combobox('getValue')+'&timeId='+$("#TIME_ID").val(),
       /* $.get(basePath + "/salary-time/if-time-exist?orgId=" + orgId+'&timeId='+$("#TIME_ID").val(),
        function (data) {
                var list = eval(data);
                for (var i = 0; i < 1; i++) {
                    var num = list[i]['num'];
                }
                if (num == '1' || num > '1') {
                    var str = '该机构已存在工资结算日，不能重复添加，如需修改请点击“修改”按钮！';
                    $.messager.alert("提示", str, "info");
                    return
                }*/
                //执行保存代码
                $.postJSON(basePath + "/salary-time/merge", JSON.stringify(salaryTime), function (data) {
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
            //});
    });
    /**
     * 关闭
     */
    $("#close").on('click', function ()  {
        $('#newDialog').dialog('close');
        $("#dataGrid").datagrid('clearSelections');
    });


});