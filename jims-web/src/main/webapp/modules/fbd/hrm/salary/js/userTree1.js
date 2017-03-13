var user=[];//人员数组
var userName=[];//人员数组
var depts=[];//部门数组
var deptsName=[];//部门数组
var treeUser = [];//人员树
var treeDepts = [];//部门树
var type=1;//考核对象类型（1部门2人员3考评人员）
var addArray=[];
var templetId='';
//var arr = [];//数组
var arr1=[];//表格内数据组合数组
$(function () {
    $("#personGrid").datagrid({
        //iconCls: 'icon-edit',//图标
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
        url:'/service/templet/getPersonById?templetId='+templetId+'&type='+type,
        remoteSort: false,
        idField: 'userId',
        singleSelect: false,//是否单选
        rownumbers: true,//行号
        columns: [[
            {field: 'ck', title: '', checkbox: true},
            {field: 'userId', title: '名称', width: '80%', align: 'center',
                formatter:function (userId) {
                    if (type == 1) {
                        return deptsName[userId];
                    }
                    else{
                        return userName[userId];
                    }
                }}
        ]],
        onLoadSuccess:function (data) {
        }

    });
    //配置窗口
    $("#choosePerson").window({
        closed: true,
        modal: true,
        collapsible:true,
        minimizable:false,
        maximizable:true,
        closable:true,
        onClose: function () {
        },
        onOpen: function () {
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
    //lx1为添加考评对象2为评分人员
    if(lx==1){
        //type为0是考核部门，1为考核人员
        //var check=$("input[name='self']").is(':checked');
        //type=$("#objType").combobox('getValue');
        if(type==1){
            $('#userTree').tree('loadData',treeDepts);
        }
        if(type==2){
            $('#userTree').tree('loadData',treeUser);
        }
        $("#choosePerson").window({title:"科室选择"});
    }

    var roots = $('#userTree').tree('getRoots');//返回tree的所有根节点数组
    for ( var i = 0; i < roots.length; i++) {
        var node = $('#userTree').tree('find', roots[i].id);
        $('#userTree').tree('uncheck', node.target);
    }
    $("#choosePerson").window('open');
}
//添加人员
function addPerson() {
    //获取人员
    var userTree=$("#userTree").tree('getChecked');
    var rows=$("#personGrid").datagrid('getRows');
    $.each( rows, function(i, n){
        arr1.push(n);
    });
    if(type==1){
        for(var i=0;i<userTree.length;i++) {
            var isNew=getName(arr1,'userId',userTree[i].userId);
            if(isNew.length==0) {
                if (userTree[i].type == 2) {
                    var obj = {};
                    obj.userId = userTree[i].id;
                    obj.type = type;
                    obj.dataId = templetId;
                    arr.push(obj);
                }
            }
        }
    }
    else{
        for(var i=0;i<userTree.length;i++) {
            var isNew=getName(arr1,'userId',userTree[i].userId);
            if(isNew.length==0) {
                if (userTree[i].type == 3) {
                    var obj = {};
                    obj.userId = userTree[i].id;
                    obj.type = type;
                    obj.dataId = templetId;
                    arr.push(obj);
                }
            }
        }
    }
                if(userTree.length==0){
                        $.messager.alert('提示', '请选择科室', 'info');
                        return;
                }
                $("#personGrid").datagrid('loadData', arr);
}
//移除人员
function removePerson() {
    var userGrid=$("#personGrid").datagrid('getChecked');
    if(userGrid){
        for(var i=0;i<userGrid.length;i++){
            arr=remove(arr,"userId",userGrid[i].userId);
        }
        $("#personGrid").datagrid('loadData',arr);
    }
    else{
        $.messager.alert('提示', '请选择科室', 'info');
    }
}
//保存按钮
function savePerson() {
    var value='';
    var rows=$("#personGrid").datagrid('getRows');
    if(rows.length==0){
        $.messager.alert('提示', '请选择科室', 'info');
        return;
    }
    $.each( rows, function(i, n){
        if(type==1){
            if(i!=rows.length-1){
                value+=deptsName[n.userId]+',';
            }
            else{
                value+=deptsName[n.userId]
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
    });
    if(type==1||type==2){
        $("#objName").textbox('setValue',value);
    }
    if(type==3){
        $("#userName").textbox('setValue',value);
    }
    $("#choosePerson").window('close');
}
function cancelPerson() {
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
var loadDept1 = function () {
    var load = $.get("/service/tool/find-user-tree?orgId=" + orgId, function (data) {
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
};
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
            obj.depId = '';//机构ID
            obj.depName = '';//机构名称
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
};