/**
 *试题分
 * @author wangzhiming
 * @version 2016-08-18
 */



var personObj=[];
var arrPerson=[];
$(function () {
    //配置窗口


    $("#userTree").tree({
        width: '100%',
        height: '100%',
        fitColumns: true,
        striped: true,
        singleSelect: false,
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
            alert('123');
        }
    });
});
//人员授权
function choose() {
    var roots = $('#userTree').tree('getRoots');//返回tree的所有根节点数组
    for ( var i = 0; i < roots.length; i++) {
        var node = $('#userTree').tree('find', roots[i].id);
        $('#userTree').tree('uncheck', node.target);
    }

   // $("#personGrid").datagrid('clearChecked');
    //$("#choosePerson").window('open');
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


$(function () {
    orgId= parent.config.org_Id;
    loadtree();
    choose();

});

