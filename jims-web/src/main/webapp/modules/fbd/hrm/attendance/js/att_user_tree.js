var personObj=[];
var arrPerson=[];
var userId='';
var userName='';
var deptId='';
var deptName='';
$(function () {
    //配置窗口
    $("#choosePerson").window({
        closed: true,
        modal: true,
        minimizable: false,
        title:'选择人员',
        onClose: function () {
        },
        onOpen: function () {

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
                data:arrPerson ,
                remoteSort: false,
                idField: 'userId',
                singleSelect: false,//是否单选
                rownumbers: true,//行号
                columns: [[
                    {field: 'ck', title: '', checkbox: true},
                    {field: 'name', title: '姓名', width: '50%', align: 'center'},
                    {field: 'depName', title: '科室', width: '48%', align: 'center'}
                ]]

            });

        }
    });

    $("#userTree").tree({
        width: '100%',
        height: '100%',
        fitColumns: true,
        striped: true,
        singleSelect: false,
        onlyLeafCheck:true,
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
function chooseUser() {
    loadUserTree();
    var roots = $('#userTree').tree('getRoots');//返回tree的所有根节点数组
    for ( var i = 0; i < roots.length; i++) {
        var node = $('#userTree').tree('find', roots[i].id);
        $('#userTree').tree('uncheck', node.target);
    }

    $("#personGrid").datagrid('clearChecked');
    $("#choosePerson").window('open');
}
//添加人员
function addPerson() {
    arrPerson=[];
    //获取人员
    var userTree=$("#userTree").tree('getSelected');
    if(userTree) {
        var isNew=getName(arrPerson,'userId',userTree.userId);
        if(isNew.length==0){
            if (userTree.type == '3') {
                var obj = new Object();
                obj.userId = userTree.userId;
                obj.name = userTree.name;
                obj.depId = userTree.depId;
                obj.depName = userTree.depName;
                arrPerson.push(obj);
            }
        }
    }
    if(arrPerson.length==0){
        $.messager.alert('系统提示', '请选择人员', 'info');
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
    }
}
function savePerson() {

    if(arrPerson.length==0){
        $("#editUserName").textbox('setValue','');
        $("#editUserId").val('');
        $("#editDeptId").val('');
    }
    else{
        userId=''
        userName=''
        deptId=''
        deptName=''
        $.each( arrPerson, function(i, n){
            userId=n.userId;
            userName=n.name;
            deptId=n.depId;
            deptName=n.depName;
        })
        $("#editUserName").textbox('setValue',userName);
        $("#editUserId").val(userId);
        $("#editDeptId").val(deptId);
        $("#editDeptName").textbox('setValue',deptName);
        $("#editUserFlag").val('1');
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
var loadUserTree = function () {

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
                personObj.push(obj);
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