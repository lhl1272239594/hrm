/**
 * Created by Administrator on 2016/5/31.
 */
$(function () {
    InitTreeData();
    var roleId = null;
    var menu = []
    var role = []
    $('#roleId').combogrid({
        panelWidth: 150,
        idField: 'id',
        textField: 'roleName',
        method: 'GET',
        url: basePath+'/org-role/findAllListByOrgId?orgId=1',
        columns: [[
            {field: 'id', title: '角色ID', width: 60},
            {field: 'roleName', title: '职务名称', width: 100}
        ]],
        onClickRow:function(){
            roleId = $("#roleId").combogrid('getValue');
            if (roleId.length > 0) {
                $.ajax({
                    'type': 'GET',
                    'url': basePath + '/roleVs/find?id=' + roleId,
                    'success': function (data) {
                        if(data.length == 0){
                            $('#dd').tree('loadData', InitTreeData());
                        } else{
                            for (i = 0; i < data.length; i++) {
                                var arr = new Array;
                                    arr = data[i].menuId.split(",");
                                for (j = 0; j < arr.length; j++) {
                                    var node = $("#dd").tree('find', arr[j]);
                                    if (node != null) {
                                        var children = $("#dd").tree('getChildren', node.target);
                                        if (children.length > 0) {
                                            continue;
                                        }
                                        $('#dd').tree('check', node.target);//将得到的节点选中
                                    }
                                }
                            }
                        }
                    }
                });
            }
        }
    });
    function InitTreeData() {
        $('#dd').tree({
            method: 'get',
            url: basePath + "/org-service/find-menu?orgId=1",
            checkbox: true,
            loadFilter: function (data) {
                if (data.d) {
                    return data.d;
                } else {
                    return data;
                }
            },
            onBeforeCheck: function (node) {
                roleId = $("#roleId").combogrid('getValue');
                if(roleId.length == 0){
                    $.messager.alert('提示', "请先选择一个服务角色", "error");
                    return false;
                }
            },
            onCheck: function (node, checked) {
                if(checked == true){
                    var parent = $('#dd').tree('getParent', node.target);
                    var children = $('#dd').tree('getChildren', node.target);
                    var s = '';
                    for (var i = 0; i < children.length; i++) {
                        s += children[i].id + ',';
                    }
                    var note= node.id;
                    roleId = $("#roleId").combogrid('getValue');
                    if(note != null && s.length > 0){
                        menu.push({'roleServiceId': note, 'menuId':s})
                        role.push({'serviceId': note, 'roleId': roleId})
                    }else{
                        menu.push({'roleServiceId': parent.id, 'menuId': node.id})
                        role.push({'serviceId': parent.id, 'roleId': roleId})
                    }
                }
            }
        });
    }

    /*//清空选项
    var clearMenuBtn = function () {
        var nodes = $('#dd').tree('getChecked', ['checked', 'indeterminate', 'unchecked']);
        var flag = nodes.checked ? "check" : "uncheck";
        for (var i = 0; i < nodes.length; i++) {
            //$('#dd').tree(flag, nodes[i].target);//将得到的节点清空
        }
    }*/

    $('#saveBtn').on('click', function () {
        if(role.length == 0){
            $.messager.alert('提示', "请选择服务", "error");
        } else{
            parent.$.postJSON('/service/roleVs/save', JSON.stringify(role), function (res) {
                if (res.success = '0'){
                    parent.$.postJSON('/service/service-menu/save', JSON.stringify(menu), function (res) {
                        if (res.success = '0')
                            $.messager.alert('成功', '保存成功', 'success');
                    })
                }
            })
        }
    })
})