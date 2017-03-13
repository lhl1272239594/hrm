var user=[];//人员数组
var userName=[];//人员数组
var depts=[];//部门数组
var deptsName=[];//部门数组
var treeDepts = [];//部门树
var arrDept=[];
var userId='';
var userName='';
var deptId='';
var deptName='';
$(function () {
    //配置窗口
    $("#chooseAppDept").window({
        closed: true,
        modal: true,
        title:'授权人员',
        onClose: function () {
        },
        onOpen: function () {

            $("#appDeptGrid").datagrid({
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
                    {field: 'name', title: '姓名', width: '50%', align: 'center'},
                    {field: 'depName', title: '科室', width: '50%', align: 'center'}
                ]]

            });

        }
    });

    $("#appDeptTree").tree({
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
            title: '部门列表',
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
function chooseDept() {
    var roots = $('#appDeptTree').tree('getRoots');//返回tree的所有根节点数组
    for ( var i = 0; i < roots.length; i++) {
        var node = $('#appDeptTree').tree('find', roots[i].id);
        $('#appDeptTree').tree('uncheck', node.target);
    }

    $("#appDeptGrid").datagrid('clearChecked');
    $("#chooseAppDept").window('open');
}
//添加部门
function addAppDept() {
    //获取部门
    var deptTree=$("#appDeptTree").tree('getChecked');
    for(var i=0;i<deptTree.length;i++) {
        var isNew=getName(arrDept,'userId',deptTree[i].deptId);
        if(isNew.length==0){
            if (deptTree[i].type == '3') {
                var obj = new Object();
                obj.userId = userTree[i].userId;
                obj.name = userTree[i].name;
                obj.depId = userTree[i].depId;
                obj.depName = userTree[i].depName;
                arrPerson.push(obj);
            }
        }
    }
    if(arrDept.length==0){
        $.messager.alert('系统提示', '请选择授权人员', 'info');
        return;
    }
    $("#appDeptGrid").datagrid('loadData',arrPerson);
}
//移除部门
function removeAppDept() {
    var deptGrid=$("#appDeptGrid").datagrid('getChecked');
    if(deptGrid){
        for(var i=0;i<deptGrid.length;i++){
            arrDept=remove(arrDept,"userId",deptGrid[i].userId);
        }
        $("#appDeptGrid").datagrid('loadData',arrDept);
    }
}
function saveAppDept() {

    if(arrDept.length==0){
        $("#editUserName").textbox('setValue','');
        $("#editUserId").val('');
        $("#editDeptId").val('');
    }
    else{
        userId=''
        userName=''
        deptId=''
        deptName=''
        $.each( arrDept, function(i, n){
            userId+=n.userId+',';
            userName+=n.name+',';
            deptId+=n.depId+',';
            deptName+=n.deptName+',';
        })
        userId=userId.substring(0,userId.length-1);
        userName=userName.substring(0,userName.length-1);
        deptId=deptId.substring(0,deptId.length-1);
        deptName=deptName.substring(0,deptName.length-1);
        $("#editUserName").textbox('setValue',userName);
        $("#editUserId").val(userId);
        $("#editDeptId").val(deptId);
        $("#editDeptName").val(deptName);
        $("#editUserFlag").val('1');
    }
    $("#chooseAppDept").window('close');
}
function cancelAppDept() {
    $("#chooseAppDept").window('close');
}
function remove(arrDept,objPropery,objValue)
{
    return $.grep(arrDept, function(cur,i){
        return cur[objPropery]!=objValue;
    });
}
function getName(arrDept,objPropery,objValue)
{
    return $.grep(arrDept, function(cur,i){
        if(cur[objPropery]==objValue){
            return cur;
        }
    });
}
var loadDeptTree = function () {
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
        $("#appDeptTree").tree('loadData', treeDepts);
    });
}