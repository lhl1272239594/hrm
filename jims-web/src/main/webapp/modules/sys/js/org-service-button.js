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
        toolbar: [{
            text: '保存',
            iconCls: 'icon-save',
            handler: function () {
                saveMenu();
            }
        }],
        singleSelect: true,
        columns: [[
            {
                title: '菜单名称',
                field: 'menuName',
                width: "45%"
            }, {
                title: '按钮功能',
                field: 'menuOperate',
                width: "100%",
                editor: {
                    type: 'checkbox', options: {
                        valueField: 'value',
                        textField: 'label',
                        editable: false
                    }
                },
                formatter: function (value,row,index) {
                    if(value==null || value == '#')
                    {
                        return "";
                    }
                    else
                    {
                        var mbs = value.split('#');//分开 名字和选择
                        var name = mbs[0].split(',');
                        var sta = mbs[1].split(',');
                        var h="";
                        for(var i=0;i<name.length;i++)
                        {
                            if(sta[i]=='true'){
                                h+='<input yz='+wz+++' type="checkbox" checked="true"  name='+name[i]+' id='+row.menuId+' value="+row.id+" onclick=cbtn(this);>'+name[i]+'&nbsp;&nbsp;';
                            }
                            else
                            {
                                h+='<input yz='+wz+++' type="checkbox"  name='+name[i]+' id='+row.menuId+' value="+row.id+" onclick=cbtn(this);>'+name[i]+'&nbsp;&nbsp;';
                            }
                            //h+=  "<a href='javacript:;' onclick=cbtn('" + s[i] + "');>" + s[i] + "</a>";
                        }
                        /*  var s = value.split(',');
                         var h="";
                         for(var i=0;i<s.length;i++)
                         {
                         h+='<input yz='+wz+++' type="checkbox" checked="true"  name='+s[i]+' id='+row.menuId+' value="+row.id+" onclick=cbtn(this);>'+s[i]+'&nbsp;&nbsp;';
                         //h+=  "<a href='javacript:;' onclick=cbtn('" + s[i] + "');>" + s[i] + "</a>";
                         }*/
                        return  h;
                    }
                }
            }
        ]]
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
    var rolerow = $('#roleId').datagrid('getSelected');
    var serrow = $('#serviceId').datagrid('getSelected');
    //ORGID,ROLEID,ROLENAME,SERVICEID,SERVICENAME,MENUID,BTNAME,BTSTATE
    var info="";
    //var objs="";
    var objs=new Array();
    $.each(arrBtns, function(index,callback){
        var buttonDict = {};
        buttonDict.menuName =orgId;
        buttonDict.pid = rolerow.id;
        buttonDict.permission = rolerow.roleName;
        buttonDict.menuLevel =serrow.serviceId;
        buttonDict.target=serrow.serviceName;
        buttonDict.id = arrBtns[index].id;//serrow.serviceName;
        buttonDict.href=arrBtns[index].name;
        buttonDict.icon=arrBtns[index].check;
        objs.push(buttonDict)
    });


    /*    var buttonDict = {};
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
    $.postJSON(bp + "/buttonDict/upbtnrole", JSON.stringify(objs) , function (data) {
            $.messager.alert('系统提示', '保存成功', 'info');
            $('#tt').datagrid('reload');
            $("#tt").datagrid('clearSelections');
        }, function (theback) {
        },
        //返回类型
        "json");
}