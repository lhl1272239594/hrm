var user=[];//人员数组
var userName=[];//人员数组
var depts=[];//部门数组
var deptsName=[];//部门数组
var treeUser = [];//人员树
var treeDepts = [];//部门树
var userArray = [];//考核人员
var gradeArray = [];//评分人员
var deptArray = [];//考核部门
var type='';//考核对象类型（1部门2人员3考评人员）
var addArray=[];
$(function () {
    $("#personGrid").datagrid({
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
        //url:'/service/templet/getPersonById?templetId='+templetId+'&type='+type,
        remoteSort: false,
        idField: 'userId',
        singleSelect: false,//是否单选
        rownumbers: true,//行号
        columns: [[
            {field: 'ck', title: '', checkbox: true},
            {field: 'name', title: '姓名', width: '46%', align: 'center',
                formatter:function (value, row, index) {
                    return userName[row.userId];
                }
            },
            {field: 'depName', title: '科室', width: '46%', align: 'center',
                formatter:function (value, row, index) {
                    return deptsName[row.depId];
                }
            }
        ]],
        onLoadSuccess:function (data) {
            var row =$("#projectGrid").datagrid('getRows');
            for(var i=0;i<row.length;i++){
                addArray.push(row[i]);
            }
        }
    });
    //配置窗口
    $("#choosePerson").window({
        closed: true,
        modal: true,
        collapsible:false,
        minimizable:false,
        maximizable:false,
        closable:false,
        onClose: function () {
        },
        onOpen: function () {
            $('.datagrid-sort-icon').hide();
            $('#userTree').tree('collapseAll');
        }
    });

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
});
//人员授权
function choose(lx) {
    //清空选择
    var roots = $('#userTree').tree('getChecked');//返回tree的所有根节点数组
    for ( var i = 0; i < roots.length; i++) {
        $('#userTree').tree('uncheck', roots[i].target);
    }
    //lx1为添加考评对象2为评分人员
    if(lx==1){
        //type为1是考核部门，2为考核人员
        type=$("#objType").combobox('getValue');
        if(type==1){
            var check=$("#self").is(':checked');
            var self=0;
            if(check){
                self=1;
            }
            if(self==1){
                $('#userTree').tree({cascadeCheck:true,onlyLeafCheck:false});
                $('#userTree').tree('loadData',treeUser);
            }
            else{
                $('#userTree').tree({cascadeCheck:false,onlyLeafCheck:true});
                $('#userTree').tree('loadData',treeDepts);

            }
        }
        if(type==2){
            $('#userTree').tree({cascadeCheck:true,onlyLeafCheck:false});
            $('#userTree').tree('loadData',treeUser);
        }
        $("#choosePerson").window({title:"考核对象"});
    }
    if(lx==2){
        type=3;
        $('#userTree').tree({cascadeCheck:true,onlyLeafCheck:false});
        $('#userTree').tree('loadData',treeUser);
        $("#choosePerson").window({title:"评分人员"});
    }
    var roots = $('#userTree').tree('getRoots');//返回tree的所有根节点数组
    for ( var i = 0; i < roots.length; i++) {
        var node = $('#userTree').tree('find', roots[i].id);
        $('#userTree').tree('uncheck', node.target);
    }
    var data;
    $("#personGrid").datagrid('showColumn','name');
    var dg = $('#personGrid');
    var typeName = dg.datagrid('getColumnOption', 'name');
    var num = dg.datagrid('getColumnOption', 'depName');
    typeName.width = '46%';
    num.width = '46%';
    dg.datagrid();
    if(type==1){
        var check=$("#self").is(':checked');
        var self=0;
        if(check){
            self=1;
        }
        if(self==1){
            data=userArray;
        }
        else{
            data=deptArray;
            $("#personGrid").datagrid('hideColumn','name');
            var dg = $('#personGrid');
            var num = dg.datagrid('getColumnOption', 'depName');
            num.width = '92%';
            dg.datagrid();
        }
    }
    if(type==2){
        data=userArray;
    }
    if(type==3){
        data=gradeArray;
    }
    $("#personGrid").datagrid('loadData', data);
    $("#personGrid").datagrid('clearChecked');
    $("#choosePerson").window('open');
}
//添加人员
function addPerson() {
    //获取人员
    var userTree=$("#userTree").tree('getChecked');
    if(userTree.length==0){
        if(type==1){
            var check=$("#self").is(':checked');
            var self=0;
            if(check){
                self=1;
            }
            if(self==1){
                $.messager.alert('系统提示', '请选择自评人员', 'info');
                return;
            }
            else{
                $.messager.alert('系统提示', '请选择考核部门', 'info');
                return;
            }
        }
        if(type==2){
            $.messager.alert('系统提示', '请选择考核人员', 'info');
            return;
        }
        if(type==3){
            $.messager.alert('系统提示', '请选择评分人员', 'info');
            return;
        }
    }
    var arr = [];//数组
    var arr1=[];//表格内数据组合数组
    var rows=$("#personGrid").datagrid('getRows');
    $.each( rows, function(i, n){
        arr1.push(n);
    });
    if(type==1){
        var check=$("#self").is(':checked');
        var self=0;
        if(check){
            self=1;
        }
        if(self==1){
            for(var i=0;i<userTree.length;i++) {
                var isNew=getName(arr1,'userId',userTree[i].userId);
                if(isNew.length==0) {
                    if (userTree[i].type == 3) {
                        var obj = new Object();
                        obj.userId = userTree[i].id;
                        obj.type = type;
                        obj.dataId = templetId;
                        obj.depId = userTree[i].depId;
                        obj.depName = userTree[i].depName;
                        if(type==2||type==1){
                            userArray.push(obj);
                        }
                        if(type==3){
                            gradeArray.push(obj);
                        }
                    }
                }
            }
        }
        else{
            for(var i=0;i<userTree.length;i++) {
                var isNew=getName(arr1,'userId',userTree[i].userId);
                if(isNew.length==0) {
                    if (userTree[i].type == 2) {
                        var obj = new Object();
                        obj.depId = userTree[i].id;
                        obj.type = type;
                        obj.dataId = templetId;
                        obj.depId = userTree[i].depId;
                        obj.depName = userTree[i].depName;
                        deptArray.push(obj);
                    }
                }
            }
        }

    }
    else{
        for(var i=0;i<userTree.length;i++) {
            var isNew=getName(arr1,'userId',userTree[i].userId);
            if(isNew.length==0) {
                if (userTree[i].type == 3) {
                    var obj = new Object();
                    obj.userId = userTree[i].id;
                    obj.type = type;
                    obj.dataId = templetId;
                    obj.depId = userTree[i].depId;
                    obj.depName = userTree[i].depName;
                    if(type==2){
                        userArray.push(obj);
                    }
                    if(type==3){
                        gradeArray.push(obj);
                    }
                }
            }
        }
    }
    if(type==1){
        var check=$("#self").is(':checked');
        var self=0;
        if(check){
            self=1;
        }
        if(self==1){
            $("#personGrid").datagrid('loadData',userArray);
        }
        else{
            $("#personGrid").datagrid('loadData',deptArray);
        }

    }
    if(type==2){
        $("#personGrid").datagrid('loadData',userArray);
    }
    if(type==3){
        $("#personGrid").datagrid('loadData',gradeArray);
    }
    $('#personGrid').datagrid('sort', {	        // 指定了排序顺序的列
        sortName: 'depName',
        sortOrder: 'desc'
    });
    $('.datagrid-sort-icon').hide();

}
//移除人员
function removePerson() {
    var userGrid=$("#personGrid").datagrid('getChecked');
    if(userGrid.length>0){
        if(type==1){
            var check=$("#self").is(':checked');
            var self=0;
            if(check){
                self=1;
            }
            if(self==1){
                for(var i=0;i<userGrid.length;i++){
                    userArray=remove(userArray,"userId",userGrid[i].userId);
                }
                $("#personGrid").datagrid('loadData',userArray);
            }
            else{
                for(var i=0;i<userGrid.length;i++){
                    deptArray=remove(deptArray,"depId",userGrid[i].depId);
                }
                $("#personGrid").datagrid('loadData',deptArray);
            }
        }
        if(type==2){
            for(var i=0;i<userGrid.length;i++){
                userArray=remove(userArray,"userId",userGrid[i].userId);
            }
            $("#personGrid").datagrid('loadData',userArray);
        }
        if(type==3){
            for(var i=0;i<userGrid.length;i++){
                gradeArray=remove(gradeArray,"userId",userGrid[i].userId);
            }
            $("#personGrid").datagrid('loadData',gradeArray);
        }
    }
    else {
        if(type==1){
            var check=$("#self").is(':checked');
            var self=0;
            if(check){
                self=1;
            }
            if(self==1){
                $.messager.alert('系统提示', '请选择自评人员', 'info');
                return;
            }
            else {
                $.messager.alert('系统提示', '请选择考核部门', 'info');
                return;
            }
        }
        if(type==2){
            $.messager.alert('系统提示', '请选择考核人员', 'info');
            return;
        }
        if(type==3){
            $.messager.alert('系统提示', '请选择评分人员', 'info');
            return;
        }
    }
    $("#personGrid").datagrid('clearChecked');
}
function savePerson() {
    var value='';
    var rows=$("#personGrid").datagrid('getRows');
    $.each( rows, function(i, n){
        if(type==1){
            var check=$("#self").is(':checked');
            var self=0;
            if(check){
                self=1;
            }
            if(self==1){
                if(i!=rows.length-1){
                    value+=userName[n.userId]+',';
                }
                else{
                    value+=userName[n.userId]
                }
            }
            else {
                if(i!=rows.length-1){
                    value+=deptsName[n.depId]+',';
                }
                else{
                    value+=deptsName[n.depId]
                }
            }
        }
        if(type==2||type==3){
            if(i!=rows.length-1){
                value+=userName[n.userId]+',';
            }
            else{
                value+=userName[n.userId]
            }
        }
    })
    if(type==1||type==2){
        $("#objName").textbox('setValue',value);
    }
    if(type==3){
        $("#userName").textbox('setValue',value);
    }
    $("#choosePerson").window('close');
}
function cancelPerson() {
    /*var value='';
    if(type==1){
        deptArray=[];
        $("#objName").textbox('setValue',value);
    }
    if(type==2){
        userArray=[];
        $("#objName").textbox('setValue',value);
    }
    if(type==3){
        gradeArray=[];
        $("#userName").textbox('setValue',value);
    }*/
    $("#choosePerson").window('close');
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
var loadDept = function () {
    var load = $.get("/service/tool/find-user-tree?orgId=" + orgId, function (data) {
        $.each(data, function (index, item) {
            var obj = {};
            obj.userId = item.treeId;
            obj.name = item.treeDes;
            obj.id = item.treeId;
            obj.text = item.treeDes;
            obj.parent = item.parentId;
            obj.type = item.treeType;//1机构,2部门，3人员
            obj.depId = item.treeId;
            obj.depName = item.treeDes;
            obj.children = [];
            if(item.treeType!='3'){
                deptsName[obj.userId]=obj.name;
                depts.push(obj);
            }
        });

    });
    load.done(function () {
        for (var i = 0; i < depts.length; i++) {
            for (var j = 0; j < depts.length; j++) {
                if (depts[i].id == depts[j].parent) {
                    depts[i].children.push(depts[j]);
                }
            }
            if (depts[i].children.length > 0 && !depts[i].parent) {
                treeDepts.push(depts[i]);
            }

            if (!depts[i].parent && depts[i].children <= 0) {
                treeDepts.push(depts[i])
            }
        }
    });
}
var loadUser = function () {
    var load = $.get("/service/tool/find-user-tree?orgId=" + orgId, function (data) {
        $.each(data, function (index, item) {
            var obj = {};
            obj.userId = item.treeId;
            obj.name = item.treeDes;
            obj.id = item.treeId;
            obj.text = item.treeDes;
            obj.parent = item.parentId;
            obj.type = item.treeType;//1机构,2部门，3人员
            obj.depId = item.treeId;
            obj.depName = item.treeDes;
            obj.children = [];
            if(item.treeType=='3') {
                userName[obj.userId] = obj.name;
            }
            user.push(obj);
        });
    });
    load.done(function () {
        for (var i = 0; i < user.length; i++) {
            for (var j = 0; j < user.length; j++) {
                if (user[i].id == user[j].parent) {
                    if(user[j].type=='3'){
                        user[j].depId=user[i].id;
                        user[j].depName=user[i].name;
                    }
                    user[i].children.push(user[j]);
                }
            }
            if (user[i].children.length > 0 && !user[i].parent&&user[i].type!='3') {
                treeUser.push(user[i]);
            }

            if (!user[i].parent && user[i].children <= 0&&user[i].type!='3') {
                treeUser.push(user[i])
            }
        }
    });
}