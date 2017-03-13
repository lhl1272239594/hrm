/**
 * 职业管理
 * @author
 * @version 2016-09-29
 */
$("<link>").attr({rel: "stylesheet", type: "text/css", href: "/static/easyui/css/icon.css"}).appendTo("head");
$("<script>").attr({type: "application/javascript", src: "/static/easyui/js/jquery.easyui.min.js"}).appendTo("head");
$("<script>").attr({
    type: "application/javascript",
    src: "/static/easyui/locale/easyui-lang-zh_CN.js"
}).appendTo("head");
$("<script>").attr({type: "application/javascript", src: "/static/js/tool.js"}).appendTo("head");
$("<script>").attr({type: "application/javascript", src: "/static/js/formSubmit.js"}).appendTo("head");
$("<script>").attr({type: "application/javascript", src: "/static/js/spell.js"}).appendTo("head");
var orgId = parent.config.org_Id;
var deptId='';
var deptName='';
var personName='';
var typeId='';
var personId='';
var page='1';
var rows='30';
var deptIds=parent.orgids;
var search=false;
$(function () {
    $.messager.progress({
        title: '提示！',
        msg: '数据量较大，请稍候...',
        text: '加载中.......'
    });
    //部门树
    $("#staff").treegrid({
        width: '100%',
        height: '100%',
        //fitColumns: true,
        striped: true,
        singleSelect: true,
        rownumbers: false,//行号
        idField: "id",
        treeField: "deptName",
        //toolbar: '#classft',
        loadMsg: '数据正在加载中，请稍后.....',
        columns: [[{
            title: 'id',
            field: "id",
            hidden: true
        }, {
            title: '科室名称',
            field: 'deptName',
            width: '200%'
        }]],
        onClickRow: function (rowIndex, rowData) {
            var node = $("#staff").treegrid("getSelected");
            deptId = node.id;
            deptName = node.deptName;
            var url = basePath + '/salary-data/data-list?orgId=' + orgId+ '&deptId=' + deptId + '&personName=' + personName+ '&typeId=' + typeId+ '&deptIds=' + deptIds;
            $("#staffGrid").datagrid("reload", url);
        }
    });

    //加载树形结构的treegrid数据
    var loadDept = function () {
        var depts = [];
        var treeDepts = [];
        var loadPromise = $.get("/service/salary-data/list?orgId=" + orgId+"&deptIds="+deptIds, function (data) {
            $.each(data, function (index, item) {
                var obj = {};
                obj.deptName = item.deptName;
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
            $("#staff").treegrid('loadData', treeDepts);
        })
    };
    loadDept();

    //人员信息列表
    $("#staffGrid").datagrid({
        width: '100%',
        height: '100%',
        nowrap:false,
        border: true,
        striped: true,
        singleSelect: true,
        rownumbers: true,
        toolbar: '#ft',
        method: 'GET',
        idField: 'personId',
        fit: true,
        url: basePath + '/salary-data/data-list?orgId=' + orgId+ '&deptId=' + deptId + '&personName=' + personName+ '&typeId=' + typeId+ '&deptIds=' + deptIds,
        loadMsg: '数据正在加载中，请稍后.....',
        pagination: true,//分页控件
        pageSize: 30,
        pageList: [10, 20, 30,40,50],//可以设置每页记录条数的列表
        columns: [[
           /* {field: 'ck', title: '', checkbox: true},*/
         {
            title: "人员编号",
            field: "personId",
             hidden: true
        },{
            title: "姓名",
            field: "personName",
            align: 'center',
            width: '20%'

        },{
                title: "性别",
                field: "sex",
                align: 'center',
                width: '12%'

            },{
                title: "身份证号",
                field: "cardNo",
                align: 'center',
                width: '37%'
            }, {
                title: "部门编号",
                field: "deptId",
                hidden: true

            },{
            title: "科室",
            field: "deptName",
            align: 'center',
            width: '31%'
        }
        ]],
        onLoadSuccess:function(data){
            $('#newDialog').css('display','block');
            $('#ft').css('display','block');
            $('#ft1').css('display','block');
            $.messager.progress('close');
            $("#staffGrid").datagrid('getPager').pagination({
                pageList: [10, 20, 30, 40, 50], //可以设置每页记录条数的列表
                displayMsg: '当前显示从第{from}条到第{to}条，共{total}条记录',
                onSelectPage: function (pageNumber, pageSize) {
                    var state1 = $('#staffGrid').data('datagrid');
                    var opts = state1.options;
                    page=opts.pageNumber = pageNumber;
                    rows=opts.pageSize = pageSize;
                    searchData(page,rows);
                }
            });
        },
        //加载职业信息数据
        onClickRow: function (rowIndex, rowData) {
            var row = $("#staffGrid").datagrid("getSelected");
            personId = row.personId;
            var url = basePath + '/salary-data/career-list?orgId=' + orgId+ '&personId=' + personId;
            $("#careerGrid").datagrid("reload", url);
            $("#editBtn").show();
            $("#delBtn").show();
        }
    });
    //职业信息列表
    $("#careerGrid").datagrid({
        width: '100%',
        height: '100%',
        nowrap:false,
        border: true,
        striped: true,
        singleSelect: false,
        rownumbers: true,
        toolbar: '#ft1',
        method: 'GET',
        //idField: 'careerId',
        fit: true,
        url: basePath + '/salary-data/career-list?orgId=' + orgId+ '&personId=' + '000',
        loadMsg: '数据正在加载中，请稍后.....',
        pagination: true,//分页控件
        pageSize: 30,
        // pageList: [10, 20, 30,40,50],//可以设置每页记录条数的列表
        columns: [[
            {field: 'ck', title: '', checkbox: true},
            {
                title: "职业信息编号",
                field: "careerId",
                hidden: true
            },{
                title: "人员编号",
                field: "personId",
                hidden: true
            },{
                title: "机构编号",
                field: "orgId",
                hidden: true
            },{
                title: "组织机构",
                field: "orgName",
                align: 'center',
                width: '24%'
            },{
                title: "部门编号",
                field: "deptId",
                hidden: true
            },{
                title: "科室",
                field: "deptName",
                align: 'center',
                width: '24%'
            },{field: 'inDate', title: '入职时间', width:'17%', align: 'center',formatter: formatDatebox},
            {field: 'outDate', title: '离职时间', width:'17%', align: 'center',formatter: formatDatebox},
            {
                title: "职称类别",
                field: "title",
                align: 'center',
                width: '24%'
            },{
                title: "职称级别",
                field: "titleLevel",
                align: 'center',
                width: '24%'
            },
            {
                title: "备注",
                field: "remark",
                align: 'center',
                width: '60%'
            }/*,
            {field: 'createBy', title: '创建人', width:'10%', align: 'center'},
            {field: 'createDate', title: '创建时间',width:'24%',align: 'center'},
            {field: 'updateBy', title: '更新人', width:'10%',align: 'center'},
            {field: 'updateDate', title: '更新时间',width:'24%', align: 'center'}*/
        ]],
        onLoadSuccess:function(data){
            $('#newDialog').css('display','block');
            $('#ft').css('display','block');
            $("#careerGrid").datagrid('getPager').pagination({
                pageList: [10, 20, 30, 40, 50], //可以设置每页记录条数的列表
                displayMsg: '当前显示从第{from}条到第{to}条，共{total}条记录'
               /* onSelectPage: function (pageNumber, pageSize) {
                    var state1 = $('#careerGrid').data('datagrid');
                    var opts = state1.options;
                    page=opts.pageNumber = pageNumber;
                    rows=opts.pageSize = pageSize;
                    searchData(page,rows);
                }*/
            });
        }
    });


    //格式化时间：年月日
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

        return dt.format("yyyy-MM-dd"); //扩展的Date的format方法(上述插件实现)
    }

    $("#title").combobox({
        url: basePath + '/dict/find-list-by-type?type=' + 'TITLE_DICT',
        idField: 'value',
        textField: 'label',
        // value:'请选择',
        panelWidth: '140px',
        panelHeight: 'auto',
        method: 'GET',
        loadMsg: '数据正在加载',
        mode: 'remote',
        onLoadSuccess:function(data){
            orgCount=data.length;
        },
        onShowPanel:function(){
            //动态调整高度
            if(orgCount>10){
                $(this).combobox('panel').height(160);
            }
        }
    });
    $("#title_level").combobox({
        url: basePath + '/dict/find-list-by-type?type=' + 'TITLE_LEVEL',
        idField: 'value',
        textField: 'label',
        // value:'请选择',
        panelWidth: '140px',
        panelHeight: 'auto',
        method: 'GET',
        loadMsg: '数据正在加载',
        mode: 'remote',
        onLoadSuccess:function(data){
            orgCount=data.length;
        },
        onShowPanel:function(){
            //动态调整高度
            if(orgCount>10){
                $(this).combobox('panel').height(160);
            }
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
        personName = $("#PERSON_NAME").textbox('getValue');
        $("#staffGrid").datagrid('reload', basePath + '/salary-data/data-list?orgId=' + orgId+ '&deptId=' + deptId+ '&personName=' + personName+ '&typeId=' + typeId+'&deptIds=' + deptIds+'&rows=' + rows+ '&page=' + page);
        if(search){
            search=false;
            if(page!='1') {
                $("#staffGrid").datagrid('getPager').pagination('select', 1);
            }
        }
        $("#staffGrid").datagrid('clearSelections');
    };
    //展示全部
    $("#searchAllBtn").on("click", function () {
        deptId = '';
        personName= '';
        typeId = '';
        var url = basePath + '/salary-data/data-list?orgId=' + orgId+ '&deptId=' + deptId + '&personName=' + personName+ '&typeId=' + typeId+'&deptIds=' + deptIds;
        $("#staffGrid").datagrid("reload", url);

        });

//新增:部门树选择
    $("#DEPT_NAME").combotree({
        panelWidth: '180px',
        panelHeight: '180px',
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
                $('#DEPT_NAME').combotree('clear');
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
            $("#DEPT_NAME").combotree('loadData', treeDepts);
        })
    };
    loadDept();

    /**
     * 清空方法
     */
    var clearKey = function () {
        $("#PERSON_NAME").textbox('clear');
        $("#PERSON_NAME").textbox("setValue","");
    };
    /**
     * 清空查询条件
     */
    $("#clearBtn").on("click", function () {
        clearKey();
        page='1';
    });
    /**
     * 历史信息
     */
    $("#infoBtn").on("click", function () {
        var row = $("#staffGrid").datagrid("getSelected");
        if(!row){
            $.messager.alert("提示","请选择一条数据!",'info');
            return;
        }
        personId = row.personId;
        var url = basePath + '/salary-data/career-list-all?orgId=' + orgId + '&personId=' + personId;
        $("#careerGrid").datagrid("reload", url);
        $("#editBtn").hide();
        $("#delBtn").hide();
    });
    /**
     * 职业信息新增弹出框
     */
    $("#newDialog").dialog({
        title: '职业信息增加',
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
        var row = $("#staffGrid").datagrid("getSelected");
        if(!row||row.length==0){
            $.messager.alert("提示","请选择人员!",'info');
            return;
        }
        $("#newDialog").dialog("setTitle","职业信息增加").dialog("open");
        $("#newForm").form('reset');
        $("#CAREER_ID").val('');
        $("#NAME").textbox("setValue",row.personName);
        $("#DEPT_NAME").combobox("setValue",row.deptId);
        $("#DEPT_NAME").combobox("setText",row.deptName);
        $("#title").combobox("setValue","");
        $("#title_level").combobox("setValue","");
        $("#IN_DATE").datebox("setValue","");
        $("#OUT_DATE").datebox("setValue","");
        $("#REMARK").textbox("setValue","");
        $("#NAME").textbox("disable");

    });

    /**
     * 新增保存
     */
    $("#submitBtn").on('click', function () {
        if(!$("#IN_DATE").datebox("getValue")){
            $.messager.alert("提示","请填写入职时间!",'info');
            return ;
        }
        if(!$("#title").combobox("getValue")){
            $.messager.alert("提示","请选择职称类别!",'info');
            return ;
        }
        if(!$("#title_level").combobox("getValue")){
            $.messager.alert("提示","请选择职称级别!",'info');
            return ;
        }
        if($("#REMARK").textbox("getValue").length>200){
            $.messager.alert("提示","备注内容输入过长！",'info');
            return;
        }
        var startDate=$("#IN_DATE").datebox('getValue');
        var endDate=$("#OUT_DATE").datebox('getValue');
        var date1 = startDate.replace(/-/g,"\/");
        var date2 = endDate.replace(/-/g,"\/");
        var start = new Date(date1);
        var end = new Date(date2);

            if (start > end) {
                $.messager.alert("提示", "入职时间不能大于离职时间!","info");
                return;
            }

        var row = $("#staffGrid").datagrid("getSelected");
        var salaryData={};
        salaryData.careerId=$("#CAREER_ID").val();
        salaryData.personId=row.personId;
        salaryData.orgId=orgId;
        salaryData.deptId=$("#DEPT_NAME").combobox('getValue');
        salaryData.inDate=$("#IN_DATE").datebox('getValue');
        salaryData.outDate=$("#OUT_DATE").datebox('getValue');
        salaryData.title=$("#title").combobox("getValue");
        salaryData.titleLevel=$("#title_level").combobox("getValue");
        salaryData.remark=$("#REMARK").textbox('getValue');
                    $.postJSON(basePath + "/salary-data/career-merge", JSON.stringify(salaryData), function (data) {
                        if(data.data=="success")
                        {
                            //alert(data);
                            // $.messager.alert('系统提示', '保存成功', 'info');
                            $('#newDialog').window('close');
                            $("#careerGrid").datagrid('reload');
                            $("#newForm").form('reset');
                            $("#careerGrid").datagrid('clearSelections');
                        }
                        else{
                            $.messager.alert('提示', '保存失败', 'info');
                        }
                    })
    });

    /**
     * 修改
     */
    $("#editBtn").on('click', function () {
        var row = $("#careerGrid").datagrid("getSelected");
        var row1 = $("#staffGrid").datagrid("getSelected");
        var row2 = $("#careerGrid").datagrid("getSelections");
        if(!row){
            $.messager.alert("提示","请选择要修改的数据!",'info');
            return;
        }
        if (row2.length != 1) {
            $.messager.alert("提示", "请选择一条数据进行操作!","info");
            return;
        }
        $("#newDialog").dialog("setTitle","职业信息修改").dialog("open");
        $("#CAREER_ID").val(row.careerId);
        $("#NAME").textbox("setValue",row1.personName);
        $("#DEPT_NAME").combobox("setValue",row.deptId);
        $("#DEPT_NAME").combobox("setText",row.deptName);
        $("#title").combobox("setValue",row.title);
        $("#title_level").combobox("setValue",row.titleLevel);
        $("#IN_DATE").datebox("setValue",row.inDate);
        $("#OUT_DATE").datebox("setValue",row.outDate);
        $("#REMARK").textbox("setValue",row.remark);
        $("#NAME").textbox("disable");

    });
    /**
     * 删除
     */
    $("#delBtn").on('click', function () {
        var row = $("#careerGrid").datagrid('getSelections');
        if (row == null||row.length == 0) {
            $.messager.alert("提示", "请选择要删除的数据!","info");
            return;
        }
        $.messager.confirm('提示', '确定要批量删除所选中的数据么?', function (r) {
            if (r) {
                $.postJSON(basePath + "/salary-data/del-career", JSON.stringify(row), function (data) {
                    /* $.messager.alert('系统提示', '删除成功', 'info');*/
                    $('#careerGrid').datagrid('reload');
                    row.length=0;
                    $("#careerGrid").datagrid('clearSelections');
                })
            }
        });
    });

});




