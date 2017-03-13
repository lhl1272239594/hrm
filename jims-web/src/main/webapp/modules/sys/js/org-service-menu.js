/**
 * Created by Administrator on 2016/6/6.
 */
$(function () {
    var currentSelectId = null;
    var orgId=parent.config.org_Id;
    var storage = [{label: '可预览', value: '0'}, {label: '可编辑', value: '1'}, {label: '不可预览', value: '2'}];
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
                toolbar: [{
                    text: '新增',
                    iconCls: 'icon-add',
                    handler: function () {
                        doAdd();
                    }
                }, {
                    text: '保存',
                    iconCls: 'icon-save',
                    handler: function () {
                        doSave('/roleVs/saveService');
                    }
                }, '-', {
                    text: '删除',
                    iconCls: 'icon-remove',
                    handler: function () {
                        doDelete();
                    }
                }],
                onSelect: function (index, data) {
                    menuDict()
                }
            })
        }
    });
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
        },{
            text: '全部可预览',
            iconCls: 'icon-edit',
            handler: function(){
                allMenuOperate('0');
            }
        },{
            text: '全部可编辑',
            iconCls: 'icon-edit',
            handler: function(){
                allMenuOperate('1');
            }
        }],
        singleSelect: true,
        columns: [[
            {
                title: '菜单名称',
                field: 'menuName',
                width: "45%"
            }, {
                title: '权限功能',
                field: 'menuOperate',
                width: "45%",
                editor: {
                    type: 'combobox', options: {
                        valueField: 'value',
                        textField: 'label',
                        editable: false,
                        data: storage
                    }
                },
                formatter: function (value, row, index) {
                    var child = row.children
                    if(child && child.length > 0){
                        return ''
                    }
                    if (value == 0) {
                        return '可预览';
                    } else if (value == 1) {
                        return '可编辑';
                    } else {
                        return '不可预览';
                    }
                }
            }]],
        onClickRow: function (row) {
            var child = row.children
            if (child && child.length > 0) {
                return
            }
            if(currentSelectId){
                $('#tt').treegrid('endEdit', currentSelectId);
            }
            $('#tt').treegrid('beginEdit', row.id);
            currentSelectId = row.id
        }
    });

    function data(){
        $.get(basePath + "/org-service/find-self-service?orgId=" + orgId, function (data){
            styleArr = data
        })
    }
    data();

    /*    var menuOperatorSet = function(data){
     for(var i=0;i<data.length;i++){
     if(!data[i].id){
     data[i].id=data[i].menuId;
     }
     for(var j=0;j<data[i].children.length;j++){

     for(var k=0 ;k<data[i].children[j].children.length;k++){

     if(data[i].children[j].children[k].menuOperate==null){
     data[i].children[j].children[k].menuOperate='2';

     }
     }
     }


     }

     }*/

    function menuDict() {
        var menus = [];//菜单列表
        var menuTreeData = [];//菜单树的列表
        var node = $('#serviceId').datagrid('getSelected');
        var row = $('#roleId').datagrid('getSelected');

        var menuPromise = $.get(basePath + "/org-service/find-menu",{serviceId:node.serviceId,roleId:row.id,isTree:true}, function (data) {
            $("#tt").treegrid('loadData', data);
        });
    }


    //datagrid的单元格编辑
    $.extend($.fn.datagrid.methods, {
        editCell: function (jq, param) {
            return jq.each(function () {
                var opts = $(this).datagrid('options');
                var fields = $(this).datagrid('getColumnFields', true).concat($(this).datagrid('getColumnFields'));
                for (var i = 0; i < fields.length; i++) {
                    var col = $(this).datagrid('getColumnOption', fields[i]);
                    col.editor1 = col.editor;
                    if (fields[i] != param.field) {
                        col.editor = null;
                    }
                }
                $(this).datagrid('beginEdit', param.index);
                for (var i = 0; i < fields.length; i++) {
                    var col = $(this).datagrid('getColumnOption', fields[i]);
                    col.editor = col.editor1;
                }
            });
        }
    });
    var editIndex = undefined;

    function endEditing1() {
        if (editIndex == undefined) {
            return true
        }
        if ($('#serviceId').datagrid('validateRow', editIndex)) {
            $('#serviceId').datagrid('endEdit', editIndex);
            editIndex = undefined;
            return true;
        } else {
            return false;
        }
    }

    function onClickCell(index, field) {
        if (endEditing1()) {
            $('#serviceId').datagrid('selectRow', index)
                .datagrid('editCell', {index: index, field: field});
            editIndex = index;
        }
    }

    function doAdd() {
        $("#serviceId").datagrid('appendRow', {});
        var rows = $("#serviceId").datagrid('getRows');
        onClickCell(rows.length - 1, 'serviceName');
    }

    function doSave(path) {
        var services = [];// 服务
        var _index = $('#serviceId').datagrid('getRowIndex', $('#serviceId').datagrid('getSelected'));
        $('#serviceId').datagrid('endEdit', _index)
        var node = $('#roleId').datagrid('getSelected');
        var param = []
        param = $("#serviceId").datagrid('getChanges', 'inserted');
        var serviceId = ''
        for (var i = 0; i < param.length; i++) {
            var id = param[i].serviceName
            serviceId += id + ',';
        }
        services.push({'serviceId': serviceId, 'roleId': node.id})
        $.postJSON(basePath + path, JSON.stringify(services), function (res) {
                if (res == 0) {
                    $.messager.alert("提示消息", "保存成功", "success");
                    $('#serviceId').datagrid('load');
                } else {
                    $.messager.alert('提示消息', "此服务已存在", "error");
                    $('#serviceId').datagrid('load');
                }
            }, function (data) {
                $.messager.alert('提示', "保存失败", "error");
            }
        )
    }

    //删除
    function doDelete() {
        //把你选中的 数据查询出来。
        var selectRows = $('#serviceId').datagrid("getSelected");
        var roleRow = $('#roleId').datagrid('getSelected');
        if (selectRows.length < 1) {
            $.messager.alert("提示消息", "请选中要删的数据!");
            return;
        }

        //真删除数据
        //提醒用户是否是真的删除数据
        $.messager.confirm("确认消息", "您确定要删除信息吗？", function (r) {
            if (r) {
                var orgRoleVsService = [];
                orgRoleVsService.push({'serviceId': selectRows.serviceId, 'roleId': roleRow.id});
                $.postJSON(basePath + '/roleVs/delete',JSON.stringify(orgRoleVsService), function (res) {
                    if (res.data == "success") {
                        $.messager.alert("提示消息", "删除成功", "success");
                        $('#serviceId').datagrid('reload');
                        $("#serviceId").datagrid('clearSelections');
                        $("#tt").treegrid('loadData',[]);
                    } else {
                        $.messager.alert('提示消息', "删除失败", "error");
                    }
                });
            }
        })
    }

    function saveMenu() {

        if (currentSelectId)
            $('#tt').treegrid("endEdit", currentSelectId);
        var node = $('#serviceId').datagrid('getSelected');
        var row = $('#roleId').datagrid('getSelected');
        var roots = $('#tt').treegrid('getRoots');
        var changes=$("#tt").treegrid("getChanges");

        var ids="";
        for(var i=0;i<changes.length;i++){
            if(changes[i].menuOperate== '2'){
                ids=ids+changes[i].id+","
            }
        }
        if(ids !=''){
            $.postJSON(basePath + '/roleVs/delete-orgRole',ids, function (res) {
                $.messager.alert("提示消息", "保存成功", "success");
                $('#tt').treegrid('reload');
            });
        }

        var handleData = function (datas) {
            var ds = []
            if (datas && datas.length > 0) {
                for (var i = 0; i < datas.length; i++) {
                    var data = datas[i]
                    if (!data.children || data.children.length == 0) {
                        if (data.menuOperate && data.menuOperate != '2') {
                            var d = {
                                menuId: datas[i].menuId,
                                menuOperate: datas[i].menuOperate,
                                id: node.id,
                                roleId: row.id,
                                serviceId: node.serviceId
                            }
                            ds.push(d)
                        }
                    } else {
                        var childs = handleData(data.children)
                        if (childs.length > 0) {
                            ds = ds.concat(childs)
                            ds.push({
                                menuId: datas[i].menuId,
                                menuOperate: null,
                                id: node.id,
                                roleId: row.id,
                                serviceId: node.serviceId
                            })
                        }
                    }
                }
            }
            return ds
        }
        saveData = handleData(roots)
        saveData.unshift({id: node.id})

        $.postJSON(basePath + '/roleVs/save', JSON.stringify(saveData), function (res) {
                if (res!= null) {
                    $.messager.alert("提示消息", "保存成功", "success");
                    $('#tt').treegrid('reload');
                }else{
                    $.messager.alert('保存','保存失败','error');
                }
            }
        )
    }

    //全部可预览或可编辑
    function allMenuOperate(value){
        var node = $('#serviceId').datagrid('getSelected');
        var row = $('#roleId').datagrid('getSelected');
        var roots = $('#tt').treegrid('getRoots');
        var url = basePath + '/roleVs/update-menu-operate?roleId=' + row.id + '&serviceId=' + node.serviceId + '&operate=' + value
        $.get(url,function(resp){
            $.messager.alert('提示消息','保存成功','success');
            $("#tt").treegrid('loadData',[]);
            menuDict();
        });
    }

})