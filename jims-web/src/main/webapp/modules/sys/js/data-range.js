/**
 * Created by Administrator on 2016/6/6.
 */
var bp=basePath;
$(function () {
    var currentSelectId = null;
    orgId=parent.config.org_Id;
    $("#roleId").datagrid({
        url: basePath + '/org-role/findAllListByOrgId?orgId='+orgId,
        method: 'get',
        idField: 'id',
        fit: true,
        singleSelect: true,//是否单选
        columns: [[//显示的列
            {
                field: 'roleName', title: '职务名称', width: '95%'
            }
        ]],
        onClickRow: function (index, data) {
            $("#tt").treegrid("loadData", []);
            $("#staff").treegrid('loadData', []);
            $("#serviceId").datagrid({
                url: basePath + '/roleVs/findrole?roleId=' + data.id,
                method: 'get',
                idField: 'id',
                rownumbers: true,
                fitColumns: true, //列自适应宽度
                singleSelect: true,
                mode:'remote',
                columns: [[//显示的列
                    {
                        field: 'serviceId', title: '服务ID', hidden: true
                    }, {
                        field: 'serviceName', title: '服务名称', width: '95%', editor: {
                            type: 'combobox',
                            options: {
                                panelHeight: '150',
                                valueField: 'id',
                                textField: 'serviceName',
                                data: styleArr
                            }
                        },
                        formatter: function (value, row) {
                            for (var i = 0, j = styleArr.length; i < j; i++) {
                                if (styleArr[i].serviceName == value) {
                                    return styleArr[i].serviceName;
                                }
                                if (styleArr[i].id == value) {
                                    return styleArr[i].serviceName;
                                }
                            }
                            if (!value && styleArr && styleArr.length > 0) {
                                row.serviceName = styleArr[0].id;
                                return styleArr[0].serviceName;
                            }
                        }
                    }
                ]],
                onSelect: function (index, data) {
                    $("#staff").treegrid('loadData', []);
                    menuDict()
                }
            })
        }
    });
    function data(){
        $.get(basePath + "/org-service/find-self-service?orgId=" + orgId, function (data){
            styleArr = data
        })
    }
    data();
    $("#tt").treegrid({
        idField: 'id',
        treeField: 'menuName',
        fit: true,
        singleSelect: true,
        columns: [[
            {
                title: '菜单名称',
                field: 'menuName',
                width: "100%"
            }
        ]],onClickRow: function (rowIndex, rowData) {
            loadDept();
            //$('#staff').treegrid('reload');//添加成功重新加载数据

        }
    });

    function menuDict() {
        var menus = [];//菜单列表
        var menuTreeData = [];//菜单树的列表
        var node = $('#serviceId').datagrid('getSelected');
        var row = $('#roleId').datagrid('getSelected');

        var menuPromise = $.get(basePath + "/buttonDict/find-menu",{serviceId:node.serviceId,roleId:row.id,isTree:true,orgid:orgId}, function (data) {
            $("#tt").treegrid('loadData', data);
        });
    }

    $("#staff").treegrid({
        fit: true,
        fitColumns: true,
        striped: true,
        singleSelect: true,
        idField: "id",
        treeField: "deptName",
        checkbox:true,
        onlyLeafCheck:true,
        toolbar: [{
            text: '保存',
            iconCls: 'icon-save',
            handler: function () {
                saveMenu();

            }
        }],
        loadMsg: '数据正在加载中，请稍后.....',
        columns: [[ {field:'deptName',title:'科室名称',width:100,formatter:function(value, row, index){
            // var xs = [];for (var p in row) {xs.push(p + ":=" + row[p]);}alert(xs.join('\n'));　　return '<span style="color;red" >' + value + row.id + index + '</span>';

            if(row.remarks==1){
                row.checked=true;
                row.checkState="checked";
            }
            else
            {
                //row.checked=false;
                row.checkState="unchecked";

            }
            return value;

        }}]]
    });
    var loadDept = function () {
        delrows();
        var rolerow = $('#roleId').datagrid('getSelected');
        var serrow = $('#serviceId').datagrid('getSelected');
        var cdrow = $('#tt').treegrid('getSelected');
        var depts = [];
        var treeDepts = [];
        var loadPromise = $.get(basePath + "/buttonDict/getlist?orgId=" + orgId+"&roleid="+rolerow.id+"&serid="+serrow.serviceId+"&mid="+cdrow.menuId, function (data) {
            $.each(data, function (index, item) {
                var obj = {};
                obj.deptName = item.deptName;
                obj.id = item.id;
                obj.deptCode = item.deptCode;
                obj.deptPropertity = item.deptPropertity;
                obj.parentId = item.parentId;
                obj.remarks=item.remarks;
                obj.checked=false;
                obj.checkState="unchecked";
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
    }

})

//数据 存放 选中的信息  serviceId
var wz=0;
var orgId;
var arrBtns=new Array();
function  cbtn(v) {
    var btn=new Object();


    //alert(orgId);
    //var row = $('#roleId').datagrid('getSelected');
    // var row = $('#serviceId').datagrid('getSelected');
    // alert(row.roleName)  serviceId   serviceName
    // alert(row.id)
    // alert(row.orgId)
    // alert($(v).attr("yz"))

    //如果是选中，添加

    arrBtns=remove(arrBtns,"yz",$(v).attr("yz"));
    btn.id=$(v).attr("id");
    btn.name=$(v).attr("name");
    btn.check=$(v).is(":checked");
    btn.yz=$(v).attr("yz");
    arrBtns.push(btn);
    //如果没有选中  删除

}
function remove(arrBtns,objP1,objv1)
{
    return $.grep(arrBtns, function(cur,i){
        return cur[objP1]!=objv1;
    });
}
function saveMenu()
{
    var nodes = $('#staff').treegrid('getChildren');
    if(nodes.length==0)
    {
        return;
    }
    var s = '';
    for(var i=0; i<nodes.length; i++){
        if( nodes[i].checkState=='checked')
            s += nodes[i].id+",";
    }

    var rolerow = $('#roleId').datagrid('getSelected');
    var serrow = $('#serviceId').datagrid('getSelected');
    var cdrow = $('#tt').treegrid('getSelected');
    var buttonDict = {};
    buttonDict.menuName =orgId;
    buttonDict.pid = rolerow.id;
    buttonDict.permission = rolerow.roleName;
    buttonDict.menuLevel =serrow.serviceId;
    buttonDict.target=serrow.serviceName;

    buttonDict.id = cdrow.menuId;//serrow.serviceName;
    buttonDict.href=s.substr(0,s.length-1);//部门ID


    /*    var buttonDict = {};
     buttonDict
     buttonDict.menuName =orgId;
     buttonDict.pid = rolerow.id;
     buttonDict.permission = rolerow.roleName;
     buttonDict.menuLevel =serrow.serviceId;
     buttonDict.id = serrow.serviceName;
     var buttonDict2 = {};
     buttonDict2.menuName =orgId;
     buttonDict2.pid = rolerow.id;
     buttonDict2.permission = rolerow.roleName;
     buttonDict2.menuLevel =serrow.serviceId;
     buttonDict2.id = serrow.serviceName;

     alert(buttonDict)*/
    //ORGID:orgId,ROLEID:rolerow.id,ROLENAME:rolerow.roleName,SERVICEID:serrow.serviceId,SERVICENAME:serrow.serviceName,
    $.postJSON(bp + "/buttonDict/upmdata", JSON.stringify(buttonDict) , function (data) {
            $.messager.alert('系统提示', '保存成功', 'info');
            $('#staff').datagrid('reload');
            $("#staff").datagrid('clearSelections');
        }, function (theback) {
        },
        //返回类型
        "json");
}

function delrows()
{

    var nodes = $('#staff').treegrid('getChildren');
    for(var i=0; i<nodes.length; i++){
        if( nodes[i].checkState=='checked')
            $("#staff").treegrid('remove',nodes[i].id);
    }
    $("#staff").treegrid('loadData', {});
}