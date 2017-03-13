/**
 * 薪资档案
 * @author
 * @version 2016-08-29
 */
/*$("<link>").attr({rel: "stylesheet", type: "text/css", href: "/static/easyui/css/icon.css"}).appendTo("head");
$("<script>").attr({type: "application/javascript", src: "/static/easyui/js/jquery.easyui.min.js"}).appendTo("head");
$("<script>").attr({
    type: "application/javascript",
    src: "/static/easyui/locale/easyui-lang-zh_CN.js"
}).appendTo("head");
$("<script>").attr({type: "application/javascript", src: "/static/js/tool.js"}).appendTo("head");
$("<script>").attr({type: "application/javascript", src: "/static/js/formSubmit.js"}).appendTo("head");
$("<script>").attr({type: "application/javascript", src: "/static/js/spell.js"}).appendTo("head");*/
var orgId = parent.config.org_Id;
var deptId='';
var deptName='';
var personName='';
var typeId='';
var personId='';
var sex='';
var card='';
var state='';
var page='1';
var rows='30';
var orgCount=0;
var deptIds=parent.orgids;
var search=false;
$(function () {

    $.messager.progress({
        title: '提示！',
        msg: '数据量较大，请稍候...',
        text: '加载中.......'
    });
    //科室树加载
    $("#staff").treegrid({
        fit: true,
        fitColumns: true,
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
            var url = basePath + '/salary-data/data-list?orgId=' + orgId+ '&deptId=' + deptId + '&personName=' + personName+ '&typeId=' + typeId+'&sex=' + sex+'&card=' + card+'&state=' + state+'&deptIds=' + deptIds;
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

    //档案信息列表
    $("#staffGrid").datagrid({
        width: '100%',
        height: '100%',
        nowrap:false,
        border: true,
        striped: true,
        singleSelect: false,
        rownumbers: true,
        toolbar: '#ft',
        method: 'GET',
        //idField: 'personId',
        fit: true,
        url: basePath + '/salary-data/data-list?orgId=' + orgId+ '&deptId=' + deptId + '&personName=' + personName+ '&typeId=' + typeId+'&sex=' + sex+'&card=' + card+'&state=' + state+'&deptIds=' + deptIds,
        loadMsg: '数据正在加载中，请稍后.....',
        pagination: true,//分页控件
        pageSize: 30,
       // pageList: [10, 20, 30,40,50],//可以设置每页记录条数的列表
        columns: [[
            {field: 'ck', title: '', checkbox: true},
         /*{
            title: "dataId",
            field: "dataId",
            hidden: true
        }, */{
            title: "人员编号",
            field: "personId",
                hidden: true
        },{
                title: "姓名",
                field: "personName",
                align: 'center',
                width: '10%'

            },{
                title: "性别",
                field: "sex",
                align: 'center',
                width: '7%'

            },{
            title: "身份证号",
            field: "cardNo",
            align: 'center',
            width: '17%'
         },{
                title: "部门编号",
                field: "deptId",
                hidden: true

            },{
            title: "科室",
            field: "deptName",
            align: 'center',
            width: '13%'

        },{
            title: "工资级别",
            field: "typeId",
            align: 'center',
            width: '10%'

        },{
            field: 'state', title: '工资状态', width:'9%', align: 'center',
            formatter: function (value, row, index) {
                if (value == "1") {
                    return "停发";
                }
                if (value == "0") {
                    return "正常";
                }
            }
        },{
            field: 'updateBy', title: '更新人', width:'7%', align: 'center'
        },{
            field: 'updateDate', title: '更新时间', width:'15%', align: 'center'
        }
        ]],
        onLoadSuccess:function(data){
            $('#newDialog').css('display','block');
            $('#newDialog1').css('display','block');
            $('#ft').css('display','block');
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
        //姓名
        var personName = $("#PERSON_NAME").textbox('getValue');
        //性别
        sex = $("#SEX").combobox('getValue');
        if(sex=='全部'){
            sex='';
        }
        //身份证号
        card = $("#PERSON_CARD").textbox('getValue');
        //工资级别
        var typeId = $("#TYPE_ID").combobox('getValue');
        if(typeId=='请选择'){
            typeId='';
        }
        //工资状态
        state = $("#STATE").combobox('getValue');
        if(state=='全部'){
            state='';
        }
        $("#staffGrid").datagrid('reload', basePath + '/salary-data/data-list?orgId=' + orgId+ '&deptId=' + deptId+ '&personName=' + personName+ '&typeId=' + typeId+'&sex=' + sex+'&card=' + card+'&state=' + state+'&deptIds=' + deptIds+'&rows=' + rows+ '&page=' + page);
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
        var url = basePath + '/salary-data/data-list?orgId=' + orgId+ '&deptId=' + deptId + '&personName=' + personName+ '&typeId=' + typeId+'&sex=' + sex+'&card=' + card+'&state=' + state+'&deptIds=' + deptIds;
        $("#staffGrid").datagrid("reload", url);

        });


//查询条件：人员类别(工资级别)
    $("#TYPE_ID").combobox({
        panelWidth: '136px',
        panelHeight: 'auto',
        valueField: 'typeId',
        textField: 'typeName',
        value:'请选择',
        loadMsg: '数据正在加载...',
        //url: basePath + '/salary-cal/find-humanType?orgId=' + orgId ,
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
            $.get(basePath + '/salary-cal/find-humanType?orgId=' + orgId, function (data) {
                $("#TYPE_ID").combobox('loadData',data);
            });
        }

    });

    //新增：人员类别
    $("#HUMAN_TYPE_ID").combobox({
        panelWidth: '198px',
        panelHeight: '100px',
        valueField: 'typeId',
        textField: 'typeName',
        loadMsg: '数据正在加载...',
        url: basePath + '/salary-cal/find-humanType?orgId=' + orgId ,
        mode: 'remote',
        method: 'GET'

    });
    //变更：人员类别
    $("#HUMAN_TYPE_ID1").combobox({
        panelWidth: '198px',
        panelHeight: '100px',
        valueField: 'typeId',
        textField: 'typeName',
        loadMsg: '数据正在加载...',
        //url: basePath + '/salary-cal/find-humanType?orgId=' + orgId ,
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
            $.get(basePath + '/salary-cal/find-humanType?orgId=' + orgId, function (data) {
                $("#HUMAN_TYPE_ID1").combobox('loadData',data);
            });
        }

    });
    /**
     * 清空方法
     */
    var clearKey = function () {
        $("#PERSON_NAME").textbox('clear');
        $("#PERSON_NAME").textbox("setValue","");
        $("#SEX").combobox('clear');
        $("#SEX").combobox("setValue","全部");
        $("#PERSON_CARD").textbox('clear');
        $("#PERSON_CARD").textbox("setValue","");
        $("#TYPE_ID").combobox('clear');
        $("#TYPE_ID").combobox("setValue","请选择");
        $("#STATE").combobox('clear');
        $("#STATE").combobox("setValue","全部");
    };
    /**
     * 清空查询条件
     */
    $("#clearBtn").on("click", function () {
        clearKey();
        page='1';
    });

    /**
     * 薪资档案新增弹出框
     */
    $("#newDialog").dialog({
        title: '薪资档案增加',
        modal: true,
        closed:true,
        collapsible: true,
        minimizable: true,
        maximizable: true,
        resizable: true
    });
    /**
     * 变更弹出框
     */
    $("#newDialog1").dialog({
        title: '工资级别变更',
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
        $("#newDialog").dialog("setTitle","薪资档案增加").dialog("open");
        $("#DATA_ID").val('');
            $("#HUMAN_TYPE_ID").combobox("setValue","");
        $("#userTree").combotree("setValue","");
            $("#newForm").form('reset');
        $("#userTree").combotree({
            fitColumns: true,
            striped: true,
            panelWidth: 260,
            panelHeight: 260,
            multiple: true,
            loadMsg: '数据正在加载中，请稍后.....',
            columns: [[{
                title: '序号',
                field: "id",
                hidden: true
            }, {
                title: '人员列表',
                field: 'text',
                width: '100%'
            }]],
            onClick: function (node) {
                //返回树对象
                var a=node;

            }
        });
        loadtree();
    });
    /**
     * 加载人员树
     */
    var loadtree = function () {

        var depts = [];
        var treeDepts = [];
        var loadPromise = $.get("/service/tool/find-user-tree?orgId=" + orgId, function (data) {
            $.each(data, function (index, item) {
                //console.log(data);
                var obj = {};
                obj.id = item.treeId;
                obj.text = item.treeDes;
                obj.parent = item.parentId;
                obj.type = item.treeType;//1机构,2部门，3人员
                obj.depId = '';//机构ID
                obj.children = [];
                depts.push(obj);
            });
        });

        loadPromise.done(function () {
            for (var i = 0; i < depts.length; i++) {
                for (var j = 0; j < depts.length; j++) {
                    if (depts[i].id == depts[j].parent) {
                        if(depts[j].type=='3'){
                            depts[j].depId=depts[i].id;
                        }
                        depts[i].children.push(depts[j]);
                    }
                }
                if (depts[i].children.length > 0 && !depts[i].parent&&depts[i].type!='3') {
                    treeDepts.push(depts[i]);
                }

                if (!depts[i].parent && depts[i].children <= 0&&depts[i].type!='3') {
                    treeDepts.push(depts[i])
                }
            }
            $("#userTree").combotree('loadData', treeDepts);
        })
    };

    /**
     * 新增保存
     */
    $("#submitBtn").on('click', function () {
        if(!$("#HUMAN_TYPE_ID").combobox("getValue")){
            $.messager.alert("提示","请选择工资级别!",'info');
            return ;
        }
        var userTree=$("#userTree").combotree('tree').tree('getChecked');
        if(userTree==null||userTree==''){
            $.messager.alert("提示","请选择人员!",'info');
            return ;
        }
        var typeId = $("#HUMAN_TYPE_ID").combobox('getValue');
        var salaryDataPersonVo=[];
        for(var i=0;i<userTree.length;i++){
            if(userTree[i].type=='3'){
                var obj={};
                obj.userId=userTree[i].id;
                obj.text=userTree[i].text;
                obj.depId=userTree[i].depId;
                obj.orgId = parent.config.org_Id;
                obj.createBy = parent.config.persion_Id;
                obj.updateBy = parent.config.persion_Id;
                obj.typeId = typeId;
                salaryDataPersonVo.push(obj);
            }
        }
                    $.postJSON(basePath + "/salary-data/merge", JSON.stringify(salaryDataPersonVo), function (data) {
                        if(data.data=="success")
                        {
                            //alert(data);
                            // $.messager.alert('系统提示', '保存成功', 'info');
                            $('#newDialog').window('close');
                            $("#staffGrid").datagrid('reload');
                            $("#newForm").form('reset');
                            $("#staffGrid").datagrid('clearSelections');
                        }
                        else{
                            $.messager.alert('提示', '保存失败', 'info');
                        }
                    })

    });

    /**
     * 起薪
     */
    $("#okBtn").on('click', function () {
        //stopEdit();
        var row = $("#staffGrid").datagrid('getSelections');

        if (row == null||!row||row.length==0) {
            $.messager.alert("提示", "请选择要执行起薪操作的数据！",'info');
            return;
        }
        if (row.length>0) {
            for (var i = 0; i < row.length; i++) {
                row[i].state = '0';
            }
            $.messager.confirm('提示', '确定要执行起薪操作吗?', function (r) {
                if (r) {
                    $.postJSON(basePath + "/salary-data/enableFlag",JSON.stringify(row), function (data) {
                        /*$.messager.alert('系统提示', '删除成功', 'info');*/
                        $('#staffGrid').datagrid('reload');
                        row.length = 0;
                        $("#staffGrid").datagrid('clearSelections');
                    })
                }
            });
        }

    });
    /**
     * 停薪
     */
    $("#noBtn").on('click', function () {
        //stopEdit();
        var row = $("#staffGrid").datagrid('getSelections');
        if (row == null||!row||row.length==0) {
            $.messager.alert("提示", "请选择要执行停薪操作的数据！",'info');
            return;
        }
        if (row.length>0) {
            for (var i = 0; i < row.length; i++) {
                row[i].state = '1';
            }
            $.messager.confirm('提示', '确定要进行停薪操作吗?', function (r) {
                if (r) {
                    $.postJSON(basePath + "/salary-data/enableFlag", JSON.stringify(row), function (data) {
                        /*$.messager.alert('系统提示', '删除成功', 'info');*/
                        $('#staffGrid').datagrid('reload');
                        row.length = 0;
                        $("#staffGrid").datagrid('clearSelections');
                    })
                }
            });
        }
    });
    /**
     * 变更
     */
    $("#editBtn").on('click', function () {
        var row = $("#staffGrid").datagrid('getSelections');
        if (row == null||!row||row.length==0) {
            $.messager.alert("提示", "请选择要变更工资级别的数据！",'info');
            return;
        }
        $("#newDialog1").dialog("setTitle","变更工资级别").dialog("open");
        $("#HUMAN_TYPE_ID1").combobox("setValue","");
        $("#newForm1").form('reset');

    });
    /**
     * 变更保存
     */
    $("#submitBtn1").on('click', function () {
        var row = $("#staffGrid").datagrid('getSelections');
        if (row.length>0) {

            typeId = $("#HUMAN_TYPE_ID1").combobox("getValue");
            if (typeId == null||typeId=='') {
                $.messager.alert("提示", "请选择工资级别！",'info');
                return;
            }
            for (var i = 0; i < row.length; i++) {
                row[i].typeId = typeId;
               /* row[i].personId = personId;
                row[i].deptId = deptId;*/
                row[i].orgId = parent.config.org_Id;
            }

            $.postJSON(basePath + "/salary-data/change", JSON.stringify(row), function (data) {
                if(data.data=="success")
                {
                    //alert(data);
                    // $.messager.alert('系统提示', '保存成功', 'info');
                    $('#newDialog1').window('close');
                    $("#staffGrid").datagrid('reload');
                    $("#newForm1").form('reset');
                    $("#staffGrid").datagrid('clearSelections');
                }
                else{
                    $.messager.alert('提示', '保存失败', 'info');
                }
            })
        }

    });

});




