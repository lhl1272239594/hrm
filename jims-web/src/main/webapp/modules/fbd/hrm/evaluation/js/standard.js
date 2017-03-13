
var basePath = "/service";
var orgId= parent.config.org_Id;
var pcode='';//项目二级编码
var projectName=''//项目一级名称
var projectSName=''//项目二级名称
var arrPerson=[];
var queryPerson=[];
var Person=[];
var page='1';
var rows='30';
$(function () {

    $("#projectTree").treegrid({
        width: 'auto',
        height: '100%',
        animate: false,
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
                pcode='1';
                var url = basePath + "/standard/standardByProject?orgId=" + orgId + "&pcode=" + pcode;
                $("#standardGrid").datagrid("reload", url);
                $("#standardGrid").datagrid("clearSelections");
            }
            if (rowIndex.type=='2') {
                pcode=rowIndex.id;
                projectName=rowIndex.parentName;
                projectSName=rowIndex.name;
                var url = basePath + "/standard/standardByProject?orgId=" + orgId + "&pcode=" + pcode;
                $("#standardGrid").datagrid("reload", url);
                $("#standardGrid").datagrid("clearSelections");
            }
        }
    });
    var loadProject = function () {

        var depts = [];
        var treeDepts = [];
        var aa = [];
        var loadPromise = $.get("/service/standard/projectList?orgId=" + orgId, function (data) {
            $.each(data, function (index, item) {
                var obj = {};
                obj.name = item.name;
                obj.id = item.id;
                obj.type = item.type;
                obj.parent = item.parentId;
                obj.score = item.score;
                obj.children = [];
                obj.parentName='';
                depts.push(obj);
            });
        });
        loadPromise.done(function () {
            for (var i = 0; i < depts.length; i++) {
                for (var j = 0; j < depts.length; j++) {
                    if (depts[i].type=='1'&&!depts[i].parent&&depts[j].type=='2'&&depts[i].id == depts[j].parent) {
                        depts[j].parentName=depts[i].name;
                        depts[i].children.push(depts[j]);
                    }
                }
                if (depts[i].children.length > 0 &&!depts[i].parent) {
                    treeDepts.push(depts[i]);
                }

                if (!depts[i].parent&& depts[i].children <= 0) {
                    treeDepts.push(depts[i])
                }
            }
            var obj = {}
            obj.name="项目";
            obj.id=1;
            obj.type=1;
            obj.parentName="项目";
            obj.children=[];
            for(var j = 0; j < treeDepts.length; j++){
                obj.children.push(treeDepts[j]);
            }
            aa.push(obj);
            $("#projectTree").treegrid('loadData', treeDepts);
            $("#projectTree").treegrid('collapseAll');
        })
    }
    loadProject();
    loadtree();
    $("#userTree").tree({
        width: '100%',
        height: '100%',
        fitColumns: true,
        striped: true,
        singleSelect: false,
        checkbox:true,
        idField: "treeId",
        treeField: "treeDes",
        loadMsg: '数据正在加载中，请稍后.....',
        columns: [[{
            title: '序号',
            field: "treeId",
            hidden: true
        }, {
            title: '人员列表',
            field: 'treeDes',
            width: '100%'
        }]],
        onClick: function (node) {
            //返回树对象
            var a=node;

        }
    });

    $("#standardGrid").datagrid({
        toolbar: '#tb',
        width: '100%',
        height: '100%',
        nowrap:false,
        striped: true,
        border: true,
        method: 'get',
        loadMsg: '数据正在加载中，请稍后.....',
        pagination: true,//分页控件
        pageSize: 30,
        collapsible: false,//是否可折叠的
        fit: true,//自动大小
        url: basePath + "/standard/standardByProject?orgId=" + orgId + "&pcode=" + pcode,
        remoteSort: false,
        idField: 'id',
        singleSelect: false,//是否单选
        rownumbers: true,//行号
        columns: [[
            {field: 'ck', title: '', checkbox: true},
            {field: 'pname', title: '一级项目名称', width: '10%', align: 'center'},
            {field: 'sname', title: '二级项目名称', width: '10%', align: 'center'},
            {field: 'name', title: '考评标准名称', width: '36%', align: 'left',halign: 'center',formatter : function (value, row, index) {
                if (value.length > 76) value = value.substr(0, 76) + "...";
                return "<span title='" + row.name + "'>" + value + "</span>";
            }},
            {
                field: 'state', title: '状态', width: '5%', align: 'center',
                formatter: function (value, row, index) {
                    if (value == "1") {
                        return "启用";
                    }
                    if (value == "0") {
                        return "停用";
                    }
                }
            },
            {
                field: 'kpi', title: '是否KPI', width: '6%', align: 'center',
                formatter: function (value, row, index) {
                    if (value == "1") {
                        return "是";
                    }
                    if (value == "0") {
                        return "否";
                    }
                }
            },
            {field: 'score', title: '考评分值', width: '6%', align: 'center'},
            {field: 'createBy', title: '创建人', width: '10%', align: 'center'},
            {field: 'createDate', title: '创建时间', width: '15%', align: 'center'}

        ]],onLoadSuccess:function(data){
            $("#add").css('display','block');
            $("#choosePerson").css('display','block');

        }
    });
    $("#standardGrid").datagrid('getPager').pagination({

        pageList: [10, 20, 30, 40, 50], //可以设置每页记录条数的列表
        displayMsg: '当前显示从第{from}条到第{to}条，共{total}条记录',
        onSelectPage: function (pageNumber, pageSize) {
            var state1 = $('#standardGrid').data('datagrid');
            var opts = state1.options;
            page=opts.pageNumber = pageNumber;
            rows=opts.pageSize = pageSize;
            searchData(page,rows);
            return;

        }
    });
    var searchData= function (page,rows){
        $.get(basePath + '/standard/standardByProject?orgId=' + orgId + '&pcode=' + pcode+ '&page=' + page+ '&rows=' + rows, function (data) {
            $("#standardGrid").datagrid('loadData', data);
        });
    }
    //配置窗口
    $("#add").window({
        closed: true,
        modal: true,
        collapsible: true,
        minimizable: false,
        maximizable: true,
        resizable: true,
        onClose: function () {
            $("#projectForm").form('reset');
            $("#standardGrid").datagrid("clearSelections");
        },
        onOpen: function () {

        }
    });
    //配置窗口
    $("#choosePerson").window({
        closed: true,
        modal: true,
        collapsible: true,
        minimizable: false,
        maximizable: true,
        resizable: true,
        title:'授权人员',
        onClose: function () {
            $("#west").show();
            $("#center").show();
            $("#choosePerson").window('resize',{
                width: 800
            });
            $("#east").css('width','55%');
            $("#choose-buttons").show();
            $("#personGrid").datagrid('showColumn','ck');
            var dg = $('#personGrid');
            var typeName = dg.datagrid('getColumnOption', 'name');
            var num = dg.datagrid('getColumnOption', 'depName');
            typeName.width = '46%';
            num.width = '46%';
            dg.datagrid();
            $("#standardGrid").datagrid("clearSelections");
        },
        onOpen: function () {

            $("#personGrid").datagrid({
                iconCls: 'icon-edit',//图标
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
                data:arrPerson ,
                remoteSort: false,
                idField: 'userId',
                singleSelect: false,//是否单选
                rownumbers: true,//行号
                columns: [[
                    {field: 'ck', title: '', checkbox: true},
                    {field: 'name', title: '姓名', width: '46%', align: 'center'},
                    {field: 'depName', title: '科室', width: '46%', align: 'center'}
                ]]

            });
            $('#userTree').tree('collapseAll');
        }
    });

    //保存标准
    $("#saveBtn").on('click', function () {

        //获取标准名称
        var name=$("#name").val();
        if(name==''){
            $.messager.alert('提示', '请填写考评标准名称', 'info');
            return;
        }
        if(getRealLen(name)>1000){
            $.messager.alert("提示","考评标准名称输入过长！",'info');
            return;
        }
        //获取授权人员
        var person=$("#userName").textbox('getValue');
        if(person==''){
            $.messager.alert('提示', '请选择授权人员', 'info');
            return;
        }
        //获取考评分值
        var score=$("#score").numberbox('getValue');

        if(score==''){
            $.messager.alert('提示', '请填写考评分值', 'info');
            return;
        }
        if(parseFloat(score)<=0){
            $.messager.alert("提示", "分值应大于零", 'info');
            return;
        }
        //获取是否KPI
        var kpi=$("input[name='kpi']:checked").val();
        if(kpi==null){
            $.messager.alert('提示', '请选择是否KPI', 'info');
            return;
        }
        var standard = {};
        standard.id = $("#id").val();
        standard.name = name;
        standard.pcode=pcode;
        standard.orgId = orgId;
        standard.kpi = kpi;
        standard.score = score;
        standard.createBy = parent.config.persion_Id;
        standard.standardPersonVos=arrPerson;
        $.postJSON(basePath + "/standard/saveStandard", JSON.stringify(standard), function (data) {
            if (data.data == "success") {
                if(data.code=="hasName"){
                    $.messager.alert('提示', '该考评标准名称已存在', 'info');
                }
                if(data.code=="success"){
                    $("#add").window('close');
                    $.get(basePath + '/standard/standardByProject?orgId=' + orgId + '&pcode=' + pcode+ '&page=' + page+ '&rows=' + rows, function (data) {
                        $("#standardGrid").datagrid('loadData', data);
                    });
                }
            }
        }, function (data) {
            $.messager.alert('提示', '保存失败', 'info');
        });
    });

    //取消
    $("#cancelBtn").on('click', function () {
        $("#add").window('close');
    });

    //新增考评标准
    $("#addBtn").on('click', function () {
        if(pcode=='1'||pcode==''){
            $.messager.alert('提示', '请选择二级考评项目', 'info');
            return;
        }
        $("#add").window({title:"新增考评标准"});
        $("#add").window('open');
        $("#projectForm").form('reset');
        $("#id").val("");
        $("#projectName").textbox('setValue',projectName);
        $("#projectName").textbox('disable');
        $("#projectSName").textbox('setValue',projectSName);
        $("#projectSName").textbox('disable');
        $("#choosePerson").window('resize',{
            width: 800
        });
        $("input[name='kpi'][value='0']").attr("checked",true);
        arrPerson=[];
    });
    //修改
    $("#editBtn").on('click', function () {
        var row = $("#standardGrid").datagrid("getSelections");
        if (row.length == 1) {
            var id=row[0].id;
            $.get("/service/standard/getPersonById?id=" + id, function (data) {
                var value='';
                arrPerson=data;
                $.each(data, function (index, item) {
                    if(index!=data.length-1){
                        value+=item.name+',';
                    }
                    else{
                        value+=item.name;
                    }
                });
                $("#userName").textbox('setValue',value);
                $("#id").val(id);
                $("#add").window({title:"修改考评标准"});
                $("#add").window('open');
                $("#personGrid").datagrid('loadData',arrPerson);
                pcode=row[0].scode;
                $("#projectName").textbox('setValue',row[0].pname);
                $("#projectName").textbox('disable');
                $("#projectSName").textbox('setValue',row[0].sname);
                $("#projectSName").textbox('disable');
                $("#choosePerson").window('resize',{
                    width: 800
                });
                $("#name").textbox('setValue',row[0].name);
                $("#score").textbox('setValue',row[0].score);
                $("input[name='kpi'][value="+row[0].kpi+"]").prop("checked",true);
            });
        }
        else {
            $("#standardGrid").datagrid("clearSelections");
            $.messager.alert("提示", "请选择一条数据!", 'info');
            return;
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
    //全部合并
    $("#allNodeClose").on("click", function () {
        $("#projectTree").treegrid('collapseAll');
    });
    //全部展开
    $("#allNodeOpen").on("click", function () {
        $("#projectTree").treegrid('expandAll');
    });
});

//查看授权人员
function info() {
    var row = $("#standardGrid").datagrid('getSelections');
    if(row.length!=1){
        $("#standardGrid").datagrid('clearSelections');
        $.messager.alert("提示", "请选择一条考评标准", 'info');
        return;
    }
    var id=row[0].id;
    $.get("/service/standard/getPersonById?id=" + id, function (data) {
        queryPerson=[];
        queryPerson=data;
        $.each(queryPerson, function (index, item) {
            var obj=getName(Person,'userId',item.userId);
            queryPerson[index].name=obj[0].name;
            queryPerson[index].depName=obj[0].depName;
        });
        $("#west").hide();
        $("#center").hide();
        $("#choose-buttons").hide();
        $("#choosePerson").window('resize',{
            width: 500
        });
        $("#east").css('width','100%');
        $("#choosePerson").window('open');
        $("#personGrid").datagrid('hideColumn','ck');
        var dg = $('#personGrid');
        var typeName = dg.datagrid('getColumnOption', 'name');
        var num = dg.datagrid('getColumnOption', 'depName');
        typeName.width = '50%';
        num.width = '50%';
        dg.datagrid();
        $("#personGrid").datagrid('loadData',queryPerson);
    });
}
//查看授权人员
function queryAll() {
    pcode='';
    $.get(basePath + '/standard/standardByProject?orgId=' + orgId + '&pcode=' + pcode+ '&page=' + page+ '&rows=' + rows, function (data) {
        $("#standardGrid").datagrid('loadData', data);
    });
}
//人员授权
function choose() {
    var roots = $('#userTree').tree('getChecked');//返回tree的所有根节点数组
    for ( var i = 0; i < roots.length; i++) {
        $('#userTree').tree('uncheck', roots[i].target);
    }
    $("#personGrid").datagrid('clearChecked');
    $("#choosePerson").window('open');
}
//添加人员
function addPerson() {
    //获取人员
    var userTree=$("#userTree").tree('getChecked');
    for(var i=0;i<userTree.length;i++) {
        var isNew=getName(arrPerson,'userId',userTree[i].userId);
        if(isNew.length==0){
            if (userTree[i].type == '3') {
                var obj = new Object();
                obj.userId = userTree[i].userId;
                obj.name = userTree[i].name;
                obj.depId = userTree[i].depId;
                obj.depName = userTree[i].depName;
                arrPerson.push(obj);
            }
        }
    }
    if(arrPerson.length==0){
        $.messager.alert('提示', '请选择授权人员', 'info');
        return;
    }
    $("#personGrid").datagrid('loadData',arrPerson);
}
//移除人员
function removePerson() {
    var userGrid=$("#personGrid").datagrid('getChecked');
    if(userGrid){
        for(var i=0;i<userGrid.length;i++){
            arrPerson=remove(arrPerson,"userId",userGrid[i].userId);
        }
        $("#personGrid").datagrid('loadData',arrPerson);
        $("#personGrid").datagrid('clearChecked');
    }
}
function savePerson() {
    var value='';
    $.each( arrPerson, function(i, n){
        if(i!=arrPerson.length-1){
            value+=n.name+',';
        }
        else{
            value+=n.name;
        }
    })
    $("#userName").textbox('setValue',value);
    $("#choosePerson").window('close');
}
function cancelPerson() {
    $("#choosePerson").window('close');
}
function edit(type) {
    var row = $("#standardGrid").datagrid('getSelections');
    if(row.length==0){
        $("#standardGrid").datagrid('clearSelections');
        $.messager.alert("提示", "请选择考评标准", 'info');
        return;
    }
    if (row.length>0) {
        var text;
        if(type=='del')
            text='删除';
        if(type=='ok')
            text='启用';
        if(type=='no')
            text='停用';
        if(type=='del'||type=='no'){
            $.postJSON(basePath + "/standard/checkStandardIsUsed",JSON.stringify(row), function (data) {
                if (data.data == "success") {
                    if(data.code=="isUsed"){
                        $("#standardGrid").datagrid('clearSelections');
                        $.messager.alert("提示", "所选考评标准中有部分正在使用中!","info");
                    }
                    if(data.code=="success"){
                        $.messager.confirm("提示", "确认"+text+"选中的考评标准吗?", function (r,c) {
                            if (r) {
                                var merge = {};
                                merge.type = type;
                                merge.standardVos = row;
                                $.postJSON(basePath + "/standard/standardMerge", JSON.stringify(merge), function (data) {
                                    if(data.code=="success"){
                                        $.get(basePath + '/standard/standardByProject?orgId=' + orgId + '&pcode=' + pcode+ '&page=' + page+ '&rows=' + rows, function (data) {
                                            $("#standardGrid").datagrid('loadData', data);
                                        });
                                        $("#standardGrid").datagrid('clearSelections');
                                    }
                                    else{
                                        $.messager.alert("提示", "修改失败", 'info');
                                    }
                                });
                            }
                            else {
                                $("#standardGrid").datagrid('clearSelections');
                            }
                        })
                    }
                }
            })
        }
        if(type=='ok'){
            $.messager.confirm("提示", "确认"+text+"选中的考评标准吗?", function (r) {
                if (r) {
                    var merge = {};
                    merge.type = type;
                    merge.standardVos = row;
                    $.postJSON(basePath + "/standard/standardMerge", JSON.stringify(merge), function (data) {
                        if(data.code=="success"){
                            $.get(basePath + '/standard/standardByProject?orgId=' + orgId + '&pcode=' + pcode+ '&page=' + page+ '&rows=' + rows, function (data) {
                                $("#standardGrid").datagrid('loadData', data);
                            });
                            $("#standardGrid").datagrid('clearSelections');
                        }
                        else{
                            $.messager.alert("提示", "修改失败", 'info');
                        }
                    });
                }
            })
        }
    }
}
var loadtree = function () {

    var depts = [];
    var treeDepts = [];
    var loadPromise = $.get("/service/tool/find-user-tree?orgId=" + orgId, function (data) {
        $.each(data, function (index, item) {
            var obj = {};
            obj.userId = item.treeId;
            obj.name = item.treeDes;
            obj.id = item.treeId;
            obj.text = item.treeDes;
            obj.parent = item.parentId;
            obj.type = item.treeType;//1机构,2部门，3人员
            obj.depId = '';//机构ID
            obj.depName = '';//机构名称
            obj.children = [];
            if(item.treeType=='3'){
                arrPerson.push(obj);
                Person.push(obj);
            }
            depts.push(obj);
        });
    });
    loadPromise.done(function () {
        for (var i = 0; i < depts.length; i++) {
            for (var j = 0; j < depts.length; j++) {
                if (depts[i].id == depts[j].parent) {
                    if(depts[j].type=='3'){
                        depts[j].depId=depts[i].id;
                        depts[j].depName=depts[i].name;
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
        $("#userTree").tree('loadData', treeDepts);
    })
}
function remove(arrPerson,objPropery,objValue)
{
    return $.grep(arrPerson, function(cur,i){
        return cur[objPropery]!=objValue;
    });
}
function getName(arrPerson,objPropery,objValue)
{
    return $.grep(arrPerson, function(cur,i){
        if(cur[objPropery]==objValue){
            return cur;
        }
    });
}

