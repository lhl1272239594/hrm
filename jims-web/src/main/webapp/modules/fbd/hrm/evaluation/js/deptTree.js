var depts=[];//部门数组
var deptsName=[];//部门数组
var treeDepts = [];//部门树
var deptArray = [];//考核部门
$(function () {

    //配置窗口
    $("#chooseDept").window({
        closed: true,
        modal: true,
        collapsible:false,
        minimizable:false,
        maximizable:false,
        closable:false,
        onClose: function () {
        },
        onOpen: function () {

        }
    });

    $("#deptTree").tree({
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
            title: '科室列表',
            field: 'treeDes',
            width: '100%'
        }]],
        onClick: function (node) {
            //返回树对象
            var a=node;

        }
    });
});

//添加科室
function addDept() {
    //获取科室
    var deptTree=$("#deptTree").tree('getChecked');
    if(deptTree.length==0){
        $.messager.alert('系统提示', '请选择科室', 'info');
        return;
    }
    var arr = [];//数组
    var arr1=[];//表格内数据组合数组
    var rows=$("#deptGrid").datagrid('getRows');
    $.each( rows, function(i, n){
        arr1.push(n);
    });
    deptArray=[];
    for(var i=0;i<deptTree.length;i++) {
        var isNew=getName(arr1,'userId',deptTree[i].userId);
        if(isNew.length==0) {
            if (deptTree[i].type == 2) {
                var obj = new Object();
                obj.userId = deptTree[i].id;
                deptArray.push(obj);
            }
        }
    }
    var dept = {};
    dept.id = deptId;
    dept.name = deptName;
    dept.dept=deptArray;
    dept.type = "2";
    $.postJSON(basePath + "/deptConfig/Merge", JSON.stringify(dept), function (data) {
        if (data.data == "success") {
            $("#deptGrid").datagrid('reload');
        }
    });
}
//移除人员
function removeDept() {
    var deptGrid=$("#deptGrid").datagrid('getChecked');
    if(deptGrid.length>0){
        var dept = {};
        dept.id = deptId;
        var deptRows=[];
        for(var i=0;i<deptGrid.length;i++){
            var aa={};
            aa.id=deptGrid[i].id;
            deptRows.push(aa);
        }
        dept.dept=deptRows;
        $.postJSON(basePath + "/deptConfig/remove", JSON.stringify(dept), function (data) {
            if (data.data == "success") {
                $("#deptGrid").datagrid('reload');
            }
        });
    }
    else {
        $.messager.alert('系统提示', '请选择要移除的科室', 'info');
        return;
    }
}

function cancelDept() {
    $("#chooseDept").window('close');
    $("#child").datagrid('reload',basePath + "/deptConfig/findListByPid?id=" + deptId);
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
}
