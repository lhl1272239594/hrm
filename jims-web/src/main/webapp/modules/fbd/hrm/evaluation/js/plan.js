
var basePath = "/service";
var orgId=parent.config.org_Id;
var name='';
var typeId='';
var startType='';
var page='1';
var rows='30';
var typeNameObj = [];
var typeObj = [];
var projectObj = [];
var pcode='';//项目二级编码
var templetId='';
var orgCount = 0;
var deptIds=parent.orgids;
var search=false;
var lx='';//1为科室自评，2为考评科室，3为普通类型
$(function () {
    loadDept();
    loadUser();
    //考评类型
    $('#searchType').combobox({
        url: basePath + '/templet/evaluationType?orgId='+orgId,
        valueField: 'id',
        textField: 'typeName',
        value:'请选择',
        method: 'GET',
        onLoadSuccess:function (data) {
            $.each(data, function (index, item) {
                typeNameObj[item.id]=item.typeName;
            });
            orgCount = data.length;
        },
        onShowPanel: function() {
            // 动态调整高度
            if (orgCount > 13) {
                $(this).combobox('panel').height(285);
            }
        }
    });
    $("#templetGrid").datagrid({
        toolbar: '#tb',
        iconCls: 'icon-edit',//图标
        width: '100%',
        height: '100%',
        nowrap: false,
        striped: true,
        border: true,
        method: 'get',
        loadMsg: '数据正在加载中，请稍后.....',
        pagination: true,//分页控件
        pageSize: 30,
        pageList: [10, 20, 30, 40, 50], //可以设置每页记录条数的列表
        collapsible: false,//是否可折叠的
        fit: true,//自动大小
        url: basePath + '/templet/templetList?name=' + name + '&orgId=' + orgId + '&typeId=' + typeId + '&startType=' + startType+'&deptIds=' + deptIds,
        remoteSort: false,
        idField: 'queId',
        singleSelect: true,//是否单选
        rownumbers: true,//行号
        columns: [[
            {field: 'name', title: '考评模板名称', width: '20%', align: 'center'},
            {
                field: 'state', title: '状态', width: '10%', align: 'center',
                formatter: function (value, row, index) {
                    if (value == "0") {
                        return "停用";
                    }
                    if (value == "1") {
                        return "启用";
                    }
                }
            },
            {field: 'typeId', title: '考评类型', width: '10%', align: 'center',
                formatter: function (value, row, index) {
                    return typeNameObj[value];
                }
            },
            {field: 'startType', title: '发布类型', width: '10%', align: 'center',
                formatter: function (value, row, index) {
                    if (value == "1") {
                        return "手动";
                    }
                    if (value == "2") {
                        return "自动";
                    }
                }
            },
            {
                field: 'periodType', title: '发布周期', width: '10%', align: 'center',
                formatter: function (value, row, index) {
                    if (value == "0") {
                        return "月度";
                    }
                    if (value == "1") {
                        return "季度";
                    }
                    if (value == "2") {
                        return "年度";
                    }
                }

            },
            {field: 'createBy', title: '创建人', width: '10%', align: 'center'},
            {field: 'createDate', title: '创建时间', width: '15%', align: 'center'},
            {field: 'lastStartDate', title: '最后发布时间', width: '15%', align: 'center'}

        ]],
        onLoadSuccess:function (data) {
            $("#addTemplet").css('display','block');
            $("#Standard").css('display','block');
            $("#plan").css('display','block');

        }
    });
    $("#templetGrid").datagrid('getPager').pagination({
        pageList: [10, 20, 30, 40, 50], //可以设置每页记录条数的列表
        displayMsg: '当前显示从第{from}条到第{to}条，共{total}条记录',
        onSelectPage: function (pageNumber, pageSize) {
            var state1 = $('#templetGrid').data('datagrid');
            var opts = state1.options;
            page=opts.pageNumber = pageNumber;
            rows=opts.pageSize = pageSize;
            searchData(page,rows);
            return;

        }

    });
    var searchData= function (page,rows){
        $("#templetGrid").datagrid('reload',basePath + '/templet/templetList?name=' + name + '&orgId=' + orgId + '&typeId=' + typeId + '&startType=' + startType+ '&page=' + page+ '&rows=' + rows+'&deptIds=' + deptIds);
        if(search){
            search=false;
            $("#templetGrid").datagrid('getPager').pagination('select',1);
        }
    }

    $("#clearBtn").on("click", function () {
        clearKey();
    });
    //新增模板窗口
    $("#addTemplet").window({
        closed: true,
        modal: true,
        fit:true,
        onClose: function () {
            $("#templetGrid").datagrid('reload');
            $("#templetGrid").datagrid('clearChecked');
            $('#startType').combobox('reset');
        },
        onOpen: function () {
            $('#tab').tabs('select', "基本信息");
            //考评类型
            $('#type').combobox({
                url: basePath + '/templet/evaluationType?orgId='+orgId,
                valueField: 'id',
                textField: 'typeName',
                method: 'GET',
                onLoadSuccess: function(data){
                    $.each(data, function (index, item) {
                        typeObj[item.id]=item.type;
                    });
                    orgCount = data.length;
                },
                onShowPanel: function() {
                    // 动态调整高度
                    if (orgCount > 13) {
                        $(this).combobox('panel').height(285);
                    }
                },
                onSelect:function (data) {
                    lx=data.type;
                }
            });
            //发布类型
            $('#startType').combobox({
                url: basePath + '/dict/find-list-by-type?type=' + 'START_TYPE',
                valueField: 'label',
                textField: 'value',
                method: 'GET',
                onSelect:function (data) {
                    //1为手动
                    if(data.label==1) {
                        $('#auto').hide();
                    }
                    //2为自动
                    if(data.label==2){
                        $('#auto').show();
                        var value=$('#periodType').combobox('getValue');
                        if(value==null||value==''){
                            $("*[name='month']").hide();
                        }
                        else{
                            periodType(value);
                        }
                    }

                }
            });
            //周期类别
            $('#periodType').combobox({
                onSelect:function (data) {
                    if(data){
                        periodType(data.value);
                    }
                }
            });
            //考核对象类型
            $('#objType').combobox({
                onChange:function (data) {
                    $("#objName").textbox('setValue','');
                    deptArray=[];
                    userArray=[];
                }
            });
        }
    });
    //新增项目窗口
    $("#Project").window({
        title:'新增考评标准',
        closed: true,
        modal: true,
        collapsible:false,
        minimizable:false,
        maximizable:false,
        closable:false,
        fit:true,
        onClose: function () {

        },
        onOpen: function () {
            $("#projectTree").treegrid({
                width: 'auto',
                height: '100%',
                fitColumns: true,
                striped: true,
                singleSelect: true,
                idField: "id",
                treeField: "name",
                loadMsg: '数据正在加载中，请稍后.....',
                columns: [[{
                    title: 'id',
                    field: "id",
                    hidden: true
                }, {
                    title: '项目名称',
                    field: 'name',
                    width: '100%'
                }]],
                onClickRow: function (rowIndex, rowData) {
                    //返回树对象
                    if(rowIndex.type=='1'){
                        pcode='';
                        $.messager.alert("提示", "请选择二级项目", "info");
                    }
                    if (rowIndex.type=='2') {
                        pcode=rowIndex.id;
                        reloadStandard();

                    }
                }
            });
            var loadProject = function () {

                var pro = [];
                var treePro = [];
                var loadPromise = $.get("/service/templet/projectList?orgId=" + orgId, function (data) {
                    $.each(data, function (index, item) {
                        var obj = {};
                        obj.name = item.name;
                        obj.id = item.id;
                        obj.type = item.type;
                        obj.parent = item.parentId;
                        obj.score = item.score;
                        obj.children = [];
                        obj.parentName='';
                        pro.push(obj);
                    });
                });
                loadPromise.done(function () {
                    for (var i = 0; i < pro.length; i++) {
                        for (var j = 0; j < pro.length; j++) {
                            if (pro[i].type=='1'&&!pro[i].parent&&pro[j].type=='2'&&pro[i].id == pro[j].parent) {
                                pro[j].parentName=pro[i].name;
                                pro[i].children.push(pro[j]);
                            }
                        }
                        if (pro[i].children.length > 0 &&!pro[i].parent) {
                            treePro.push(pro[i]);
                        }

                        if (!pro[i].parent&& pro[i].children <= 0) {
                            treePro.push(pro[i])
                        }
                    }
                    $("#projectTree").treegrid('loadData', treePro);
                    $("#projectTree").treegrid('clearSelections');
                    pcode='';
                })
            }
            loadProject();
            $("#standardGrid").datagrid({
                toolbar: '#tb1',
                width: '100%',
                height: '100%',
                nowrap:false,
                striped: true,
                border: true,
                method: 'get',
                loadMsg: '数据正在加载中，请稍后.....',
                pagination: false,//分页控件
                pageSize: 30,
                collapsible: false,//是否可折叠的
                fit: true,//自动大小
                //url: basePath + "/templet/standardByProject?orgId=" + orgId + "&pcode=" + pcode + "&templetId=" + templetId,
                remoteSort: false,
                idField: 'id',
                singleSelect: false,//是否单选
                rownumbers: true,//行号
                columns: [[
                    {field: 'ck', title: '', checkbox: true},
                    {field: 'name', title: '考评标准名称', width: '78%', align: 'left',halign: 'center',formatter : function (value, row, index) {
                        if (value.length > 145) value = value.substr(0, 145) + "...";
                        return value;
                    }},
                    {field: 'score', title: '考评分值', width: '10%', align: 'center'},
                    {
                        field: 'kpi', title: '是否KPI', width: '10%', align: 'center',
                        formatter: function (value, row, index) {
                            if (value == "1") {
                                return "是";
                            }
                            if (value == "0") {
                                return "否";
                            }
                        }
                    }
                ]],onLoadSuccess:function(data){
                }
            });

        }
    });
    //发布考评
    $("#plan").window({
        closed: true,
        modal: true,
        collapsible: true,
        minimizable: true,
        maximizable: true,
        resizable: true,
        title:'发布考评',
        onClose: function () {

        },
        onOpen: function () {
            $("#planForm").form('reset');
        }
    });

    //发布类型
    $('#searchStartType').combobox({
        url: basePath + '/dict/find-list-by-type?type=' + 'START_TYPE',
        valueField: 'label',
        textField: 'value',
        value:'请选择',
        method: 'GET'
    });
    $("#searchBtn").on("click", function () {
        search=true;
        //获取模板名称
        name = $("#searchName").textbox('getValue');
        //获取考评类型
        typeId = $("#searchType").combobox('getValue');
        if(typeId=='请选择'){
            typeId='';
        }
        //获取发布状态
        startType = $("#searchStartType").combobox('getValue');
        if(startType=='请选择'){
            startType='';
        }
        searchData(page,rows);
    });

    //打开新增窗口
    $("#addBtn").on('click', function () {
        $("#addTemplet").window({title:"新增模板"});
        $("#addTemplet").window('open');
        templetId='';
        deptArray=[];
        userArray=[];
        gradeArray=[];
        projectObj=[];
        $("#gradeUser").hide();
        $("#templetForm").form('reset');
        $('#startType').combobox('select','1');
        $("#self").attr("checked",'checked');
        $("#id").val("");
        $('#auto').hide();
        $("*[name='month']").hide();
        $("*[name='quarter']").hide();
        $("*[name='year']").hide();
        $("#choosePerson").css('display','block');

    });

    //修改
    $("#editBtn").on("click", function () {
        //获取选择行
        var row=$('#templetGrid').datagrid('getChecked');
        if(row.length==1){
            $("#addTemplet").window({title:"修改模板"});
            $("#addTemplet").window('open');
            $("#id").val(row[0].id);
            templetId=row[0].id;
            var name=$("#name").textbox('setValue',row[0].name);
            $("#type").combobox('setValue',row[0].typeId);
            $("#state").combobox('setValue',row[0].state);
            $("#objType").combobox('setValue',row[0].obj);
            $("#startType").combobox('setValue',row[0].startType);
            $("#expiryDate").numberbox('setValue',row[0].expiryDate);
            if(row[0].startType==1){
                $('#auto').hide();
            }
            if(row[0].startType==2){
                $('#auto').show();
                $("#period").numberbox('setValue',row[0].period);
                $("#startDay").numberbox('setValue',row[0].startDay);
                $("#periodType").combobox('setValue',row[0].periodType);
                periodType(row[0].periodType);
            }
            if(row[0].self==1){
                $("#self").attr("checked",'checked');
                $("#gradeUser").hide();
            }
            else{
                $("#self").removeAttr("checked");
                $("#gradeUser").show();
            }
            if(row[0].obj==1){
                $.get('/service/templet/getPersonById?templetId='+templetId+'&type=1', function (data) {
                    var value='';
                    if(row[0].self==1){
                        userArray=data;
                        $.each(data, function (index, item) {
                            if(index!=data.length-1){
                                value+=userName[item.userId]+',';
                            }
                            else{
                                value+=userName[item.userId]
                            }
                        });
                    }
                    else{
                        deptArray=data;
                        $.each(data, function (index, item) {
                            if(index!=data.length-1){
                                value+=deptsName[item.depId]+',';
                            }
                            else{
                                value+=deptsName[item.depId]
                            }
                        });
                    }
                    $("#objName").textbox('setValue',value);
                });


            }
            if(row[0].obj==2){
                $.get('/service/templet/getPersonById?templetId='+templetId+'&type=2', function (data) {
                    var value='';
                    userArray=data;
                    $.each(data, function (index, item) {
                        if(index!=data.length-1){
                            value+=userName[item.userId]+',';
                        }
                        else{
                            value+=userName[item.userId]
                        }
                    });
                    $("#objName").textbox('setValue',value);
                });
            }

            if(row[0].self==0){
                $.get('/service/templet/getPersonById?templetId='+templetId+'&type=3', function (data) {
                    var value='';
                    gradeArray=data;
                    $.each(data, function (index, item) {
                        if(index!=data.length-1){
                            value+=userName[item.userId]+',';
                        }
                        else{
                            value+=userName[item.userId]
                        }
                    });
                    $("#userName").textbox('setValue',value);
                });
            }
            $.get('/service/templet/templetStandard?orgId=' + orgId + '&templetId=' + templetId, function (data) {
                projectObj=data;
            });
        }
        else{
            $("#templetGrid").datagrid('clearChecked');
            $.messager.alert("操作提示", "请选择考评模板","info");
        }
    });
    $('#tab').tabs({
        border:false,
        onSelect:function(title){
            if(title=='考评标准'){
                $("#projectGrid").datagrid({
                    toolbar: '#project_tb',
                    width: '100%',
                    height: '100%',
                    nowrap:false,
                    striped: true,
                    border: true,
                    method: 'get',
                    loadMsg: '数据正在加载中，请稍后.....',
                    pagination: false,//分页控件
                    collapsible: false,//是否可折叠的
                    fit: true,//自动大小
                    //url: basePath + "/templet/templetStandard?orgId=" + orgId + "&templetId=" + templetId,
                    remoteSort: false,
                    idField: 'id',
                    singleSelect: false,//是否单选
                    rownumbers: true,//行号
                    columns: [[
                        {field: 'ck', title: '', checkbox: true},
                        {field: 'pname', title: '一级项目名称', width: '15%', align: 'center'},
                        {field: 'sname', title: '二级项目名称', width: '15%', align: 'center'},
                        {field: 'name', title: '考评标准名称', width: '48%', align: 'left',halign: 'center',formatter : function (value, row, index) {
                            if (value.length > 130) value = value.substr(0, 130) + "...";
                            return value;
                        }},
                        {field: 'score', title: '考评分值', width: '10%', align: 'center'},
                        {
                            field: 'kpi', title: '是否KPI', width: '10%', align: 'center',
                            formatter: function (value, row, index) {
                                if (value == "1") {
                                    return "是";
                                }
                                if (value == "0") {
                                    return "否";
                                }
                            }
                        }

                    ]],onLoadSuccess:function(data){
                        mergeCellsByField("projectGrid", "pname,sname");
                        initScore();
                    }
                });
            }
            $("#projectGrid").datagrid('loadData',projectObj);
        }
    });
    //删除
    $("#delBtn").on('click', function () {
        edit('del');
    });
    //启用
    $("#okBtn").on('click', function () {
        edit('ok');
    });
    //停用
    $("#noBtn").on('click', function () {
        edit('no');
    });
    //发布考评
    $("#publishBtn").on('click', function () {
        //获取选择行
        var row=$('#templetGrid').datagrid('getChecked');
        if(row.length==1){
            if(parseFloat(row[0].score)<=0){
                if(row[0].type=='3'){
                    $("#templetGrid").datagrid('clearChecked');
                    $.messager.alert("提示", "该模板未分配考评标准", "info");
                    return;
                }
            }
            row[0].lastStartDate='';
            row[0].createDate='';
            //查看授权状况
            $.postJSON(basePath + "/templet/checkAuthorize", JSON.stringify(row[0]), function (data) {
                if (data.data == "success") {
                   if(data.code=="none"){
                       $("#plan").window('open');
                       $("#title").textbox('setValue',row[0].name);
                       $("#expiryDate1").numberbox('setValue',row[0].expiryDate);
                       templetId=row[0].id;
                   }
                   if(data.code=="dept"||data.code=="user"){
                       $.messager.alert("提示", "该模板未分配考评对象", "info");
                   }
                    if(data.code=="grade"){
                        $.messager.alert("提示", "该模板未分配评分人员", "info");
                    }
                }
            });

        }
        else{
            $("#templetGrid").datagrid('clearChecked');
            $.messager.alert("提示", "请选择一条考评模板", "info");
            return;
        }
    });
    //判断是否自评
    $("#self").on('click', function () {
        var check=$(this).is(':checked');
        type=$("#objType").combobox('getValue');
        if(check){
            $("#gradeUser").hide();
        }
        else{
            $("#gradeUser").show();
        }
        if(type==1){
            $("#objName").textbox('setValue','');
            deptArray=[];
            userArray=[];
        }
    });
    //删除考评标准
    $("#delProjectBtn").on('click', function () {
        //获取选择行
        var row=$('#projectGrid').datagrid('getChecked');
        if(row.length>0){
            for(var i=0;i<row.length;i++){
                projectObj=remove(projectObj,"id",row[i].id);
            }
            $("#projectGrid").datagrid('loadData',projectObj);
            $("#projectGrid").datagrid('clearChecked');
        }
        else{
            $("#projectGrid").datagrid('clearChecked');
            $.messager.alert("提示", "请选择要删除的考评标准", "info");
            return;
        }
    });
    //打开新增项目窗口
    $("#addProjectBtn").on('click', function () {
        $("#Project").window('open');
    });
    //保存考评标准
    $("#saveStandard").on('click', function () {
        var standardGrid=$("#standardGrid").datagrid('getChecked');
        for(var i=0;i<standardGrid.length;i++){
            projectObj.push(standardGrid[i]);
        }
        $("#standardGrid").datagrid('clearChecked');
        reloadStandard();

    });

});

    //新增考评标准返回
    $("#cancelProjectBtn").on('click', function () {
        $("#projectGrid").datagrid('loadData',projectObj);
        $("#Project").window('close');
    });

    //保存模板
    $("#saveBtn").on('click', function () {
        //获取模板名称
        var name=$("#name").val();
        if(name==null||name==''||name.indexOf(" ") >=0){
            $.messager.alert('提示', '请填写模板名称！', 'info');
            return;
        }
        if(getRealLen(name)>35){
            $.messager.alert("提示","试卷名称输入过长！",'info');
            return;
        }
        //获取题型ID
        var typeId = $("#type").combobox('getValue');
        if(typeId==null||typeId==''){
            $.messager.alert('提示', '请选择考评类型！', 'info');
            return;
        }
        //获取状态
        var state=$("#state").combobox('getValue');
        if(state==null||state==''){
            $.messager.alert('提示', '请选择启用状态！', 'info');
            return;
        }
        //获取发布类型
        var startType=$("#startType").combobox('getValue');
        if(startType==null||startType==''){
            $.messager.alert('提示', '请选择发布类型！', 'info');
            return;
        }
        //获取有效期
        var expiryDate=$("#expiryDate").numberbox('getValue');

        if(expiryDate==null||expiryDate==''){
            $.messager.alert('提示', '请填写有效期！', 'info');
            return;
        }
        if(startType==2){
            //获取周期类别
            var periodType = $("#periodType").combobox('getValue');
            if(periodType==null||periodType==''){
                $.messager.alert('提示', '请选择周期类别！', 'info');
                return;
            }
            //获取发布日期
            var startDay = $("#startDay").numberbox('getValue');
            if(startDay==null||startDay==''){
                $.messager.alert('提示', '请填写发布日期！', 'info');
                return;
            }
            if(parseInt(startDay)>31){
                $.messager.alert('提示', '发布日期应小于31', 'info');
                return;
            }
            //获取考核周期数
            var period=$("#period").numberbox('getValue');

            if(periodType==1){
                if(period==null||period==''){
                    $.messager.alert('提示', '请填写考核周期数！', 'info');
                    return;
                }
                if(parseInt(period)>3){
                    $.messager.alert('提示', '该值表示为季度的第几个月！', 'info');
                    return;
                }
            }
            if(periodType==2){
                if(period==null||period==''){
                    $.messager.alert('提示', '请填写考核周期数！', 'info');
                    return;
                }
                if(parseInt(period)>12){
                    $.messager.alert('提示', '月份应小于12！', 'info');
                    return;
                }
            }
        }
        var obj=$("#objType").combobox('getValue');
        var check=$("#self").is(':checked');
        var self=0;
        if(check){
            if(lx=='2'){
                $.messager.alert('提示', '考评类型为考评科室，不能为自评！', 'info');
                return;
            }
            self=1;
        }
        else {
            if(lx=='1'){
                $.messager.alert('提示', '考评类型为科室自评，只能为自评！', 'info');
                return;
            }
        }
        if(lx=='1'){
            if(obj=='2'){
                $.messager.alert('提示', '考评类型为科室自评，考评对象不能为人员！', 'info');
                return;
            }
        }
        if(lx=='2'){
            if(obj=='2'){
                $.messager.alert('提示', '考评类型为考评科室，考评对象不能为人员！', 'info');
                return;
            }
        }
        if(obj=='1'){
            if(check){
                if(userArray.length==0){
                    $.messager.alert('提示', '请选择考评对象！', 'info');
                    return;
                }
            }
            else {
                if(deptArray.length==0){
                    $.messager.alert('提示', '请选择考评对象！', 'info');
                    return;
                }
            }
        }
        if(obj=='2'){
            if(userArray.length==0){
                $.messager.alert('提示', '请选择考评对象！', 'info');
                return;
            }
        }
        if(self==0){
            if(gradeArray.length==0){
                $.messager.alert('提示', '请选择评分人员！', 'info');
                return;
            }
        }
        if(projectObj.length==0){
            if(lx=='3'){
                $.messager.alert('提示', '请选择考评标准！', 'info');
                return;
            }
        }

        var templetVo = {};
        templetVo.id = $("#id").val();
        templetVo.name = name;
        templetVo.typeId = typeId;
        templetVo.startType = startType;
        templetVo.expiryDate = expiryDate;
        templetVo.startDay = startDay;
        templetVo.period = period;
        templetVo.periodType = periodType;
        templetVo.createBy = parent.config.persion_Id;
        templetVo.state = state;
        templetVo.orgId = orgId;
        templetVo.obj = obj;
        templetVo.self = self;
        templetVo.dept=deptArray;
        templetVo.user=userArray;
        templetVo.grade=gradeArray;
        templetVo.standard=projectObj;
        templetVo.type=lx;
        $.messager.progress({
            title: '提示！',
            msg:  '数据量较大，请稍候...',
            text: '加载中.......'
        });
        $.postJSON(basePath + "/templet/templetMerge", JSON.stringify(templetVo), function (data) {
            $.messager.progress('close')
            if (data.data == "success") {
                if (data.code == "hasName") {
                    $.messager.alert('提示', '考评计划模板名称已存在！', 'info');
                    return;
                }
                $("#id").val(data.code);
                templetId=data.code;
                $("#addTemplet").window("close");
            }
        });
    });
    //取消添加人员维护
    $("#cancelBtn").on('click', function () {
        $("#templetForm").form('reset');
        $("#addTemplet").window('close');
    });
    //新增考评计划
    $("#savePlanBtn").on('click', function () {
        //获取考评标题
        var name=$("#title").val();
        if(name==null||name==''){
            $.messager.alert('提示', '请填写考评标题！', 'info');
            return;
        }
        //获取考评开始时间
        var start=$("#start").datebox('getValue');

        if(start==''){
            $.messager.alert('提示', '请选择考评开始时间！', 'info');
            return;
        }
        //获取有效期
        var expiryDate=$("#expiryDate1").numberbox('getValue');

        if(expiryDate==null||expiryDate==''){
            $.messager.alert('提示', '请填写有效期！', 'info');
            return;
        }

        var row=$('#templetGrid').datagrid('getChecked');
        row[0].id = templetId;
        row[0].name = name;
        row[0].lastStartDate = start;
        row[0].expiryDate = expiryDate;
        $.messager.progress({
            title: '提示！',
            msg:  '数据量较大，请稍候...',
            text: '加载中.......'
        });
        $.postJSON(basePath + "/templet/templetPublish", JSON.stringify(row[0]), function (data) {
            $.messager.progress('close')
            if (data.data == "success") {
                if(data.code=="success"){
                    $.messager.alert('提示', '发布成功！', 'info');
                    $("#templetGrid").datagrid('reload');
                    $("#plan").window('close');
                }
                if(data.code=="hasName"){
                    $.messager.alert('提示', '该考评标题已存在！', 'info');
                }
                if(data.code=="overDay"){
                    $.messager.alert('提示', '发布日期不能小于今天！', 'info');
                }
            }

        }, function (data) {
            $.messager.alert('提示', '保存失败', 'info');
        });

    });
    //新增考评计划返回
    $("#cancelPlanBtn").on('click', function () {
        $("#templetGrid").datagrid('reload');
        $("#plan").window('close');
    });
    function reloadStandard() {
        var value={};
        value.pcode=pcode;
        var standard="";
        for(var i=0;i<projectObj.length;i++){
            if(i==projectObj.length-1){
                standard+="'"+projectObj[i].id+"'";
            }
            else {
                standard+="'"+projectObj[i].id+"',";
            }
        }
        value.templetId=standard;
        value.orgId=orgId;
        $.postJSON(basePath + "/templet/standardByProject", JSON.stringify(value), function (data) {
            $("#standardGrid").datagrid("loadData", data);
        });
    }
    //清空查询条件
    function clearKey() {
        //清空模板名称
        $("#searchName").textbox('setValue','');
        name='';
        //清空考核类型
        $("#searchType").combobox('clear');//获取表格对象
        $("#searchType").combobox('setValue','请选择');//获取表格对象
        typeId = '';
        //清空发布类型
        $("#searchStartType").combobox('clear');
        $("#searchStartType").combobox('setValue','请选择');
        startType = '';
        page='1';
    }

    //清空表单内容
    function clearForm() {
        $("#itemTree1").combotree('clear');
        $("#type1").combogrid('clear');//获取表格对象
        $("#state1").combobox('clear');
        $("#pdt_answer").val(0);
    }
    function periodType(value) {
        if(value=='0'){
            $("*[name='month']").hide();
        }
        else if(value=='1'){
            $("*[name='month']").show();
        }
        else if(value=='2'){
            $("*[name='month']").show();
        }
    }
    function edit(lx) {
        //获取选择行
        var row=$('#templetGrid').datagrid('getChecked');
        if(row.length==1){
            var text;
            if(lx=='del')
                text='删除';
            if(lx=='ok')
                text='启用';
            if(lx=='no')
                text='停用';
            $.messager.confirm("提示", "确认"+text+"选中的考评模板吗?", function (r) {
                if (r) {
                    var merge = {};
                    merge.type = lx;
                    merge.id = row[0].id;
                    $.postJSON(basePath + "/templet/templetState", JSON.stringify(merge), function (data) {
                        if(data.code=="success"){
                            $("#templetGrid").datagrid('reload');
                            $("#templetGrid").datagrid('clearChecked');
                        }
                        else{
                            $.messager.alert("提示", "修改失败", "info");
                            $("#projectGrid").datagrid('clearChecked');
                        }
                    });
                }
            })
        }
        else{
            $("#projectGrid").datagrid('clearChecked');
            $.messager.alert("提示", "请选择考评模板", "info");

            return;
        }
    }
    //datagrid加载完后合并指定单元格
    function mergeCellsByField(tableID, colList) {
        var ColArray = colList.split(",");
        var tTable = $("#" + tableID);
        var TableRowCnts = tTable.datagrid("getRows").length;
        var tmpA;
        var tmpB;
        var PerTxt = "";
        var CurTxt = "";
        var alertStr = "";
        for (j = ColArray.length - 1; j >= 0; j--) {
            PerTxt = "";
            tmpA = 1;
            tmpB = 0;

            for (i = 0; i <= TableRowCnts; i++) {
                if (i == TableRowCnts) {
                    CurTxt = "";
                }
                else {
                    CurTxt = tTable.datagrid("getRows")[i][ColArray[j]];
                }
                if (PerTxt == CurTxt) {
                    tmpA += 1;
                }
                else {
                    tmpB += tmpA;

                    tTable.datagrid("mergeCells", {
                        index: i - tmpA,
                        field: ColArray[j],　　//合并字段
                        rowspan: tmpA,
                        colspan: null
                    });
                    tTable.datagrid("mergeCells", { //根据ColArray[j]进行合并
                        index: i - tmpA,
                        field: "Ideparture",
                        rowspan: tmpA,
                        colspan: null
                    });

                    tmpA = 1;
                }
                PerTxt = CurTxt;
            }
        }
    }
    function initScore() {
        var row =$("#projectGrid").datagrid('getRows');
        var score=0;
        for(var i=0;i<row.length;i++){
            score+=parseFloat(row[i].score);
        }
        $("#totalScore").html(score+'分');

    }