$("<link>").attr({rel: "stylesheet", type: "text/css", href: "/static/easyui/css/icon.css"}).appendTo("head");
$("<script>").attr({type: "application/javascript", src: "/static/easyui/js/jquery.easyui.min.js"}).appendTo("head");
$("<script>").attr({
    type: "application/javascript",
    src: "/static/easyui/locale/easyui-lang-zh_CN.js"
}).appendTo("head");
$("<script>").attr({type: "application/javascript", src: "/static/js/tool.js"}).appendTo("head");
$("<script>").attr({type: "application/javascript", src: "/static/js/formSubmit.js"}).appendTo("head");
$("<script>").attr({type: "application/javascript", src: "/static/js/spell.js"}).appendTo("head");
var basePath="/service"
$(function (){
    $.extend($.fn.validatebox.defaults.rules, {
        IsNotExisted: {
            validator: function (value, param) {
                var rows = $('#templateMaster').datagrid('getRows')
                var select_index = $('#templateMaster').datagrid('getRowIndex', $('#templateMaster').datagrid('getSelected'))
                for (var index = rows.length - 1; index > -1; index--) {
                    if (index != select_index && rows[index][param[0]] == value ) {
                        return false
                    }
                }
                return true
            },
            message: '模板名称不能相同'
        }
    });


    //获取区域
    $('#area').combotree({
        method: 'get',
        animate: true,
        data: getAreaTree(),
        onSelect:function(row){
            var area = row.value;
            $.get(basePath + '/templateMaster/findList?tableName=ORG_ROLE_TEMPLATE&area='+area, function (data) {
                $("#templateMaster").datagrid('loadData', data);
            });
        }
    })

    //模板主表
    $("#templateMaster").datagrid({
        fit: true,
        fitColumns: true,
        striped: true,
        singleSelect: true,
        idField: "id",
        toolbar: '#classft',
        method: 'get',
        loadMsg: '数据正在加载中，请稍后.....',
        url:basePath + "/templateMaster/findList?tableName=ORG_ROLE_TEMPLATE",
        columns: [[      //每个列具体内容
            {field: 'id', title: '主键', width: '100', align: 'center',hidden:'true'},
            {field: 'tableName', title: '模板表名称', width: '150', align: 'center',hidden:'true'},
            {field: 'area', title: '区域值', width: '100', align: 'center',hidden:'true'},
            {field: 'areaName', title: '区域名称', width: '100', align: 'center',hidden:'true'},
            {field: 'templateName', title: '模板名称', width: '150', align: 'center' ,  editor: {
                type: 'textbox',
                options: {
                    required: true,
                    validType: 'IsNotExisted["templateName"]',
                    missingMessage: '模板名称不能为空'
                }
            }},
            {field: 'details', title: '模板描述', width: '100', align: 'center' ,editor: {type: 'textbox'}}
        ]] ,
        onClickRow:function(index, row){

            var node = $("#templateMaster").datagrid("getSelected");
            if (!endEditing2()) {
                return
            }
            var insertData = $("#orgRole").datagrid("getChanges", "inserted");
            var updateData = $("#orgRole").datagrid("getChanges", "updated");
            var deleteData = $("#orgRole").datagrid("getChanges", "deleted");

            if (insertData != "" || updateData != "" || deleteData != "") {
                $.messager.confirm("系统提示", "确定要保存吗？", function (r) {
                    if (r) {
                        var orgRoleTemplateVo = {};
                        orgRoleTemplateVo.inserted = insertData;
                        orgRoleTemplateVo.deleted = deleteData;
                        orgRoleTemplateVo.updated = updateData;

                        if (orgRoleTemplateVo) {
                            $.postJSON("/service/orgRoleTemplate/saveAll", JSON.stringify(orgRoleTemplateVo), function (data) {
                                if (data.data == "success") {
                                    $.messager.alert("系统提示", "保存成功", "info");
                                }
                            }, function (data) {
                                $.messager.alert('提示', "保存失败", "error");
                            })
                        }
                    }
                });
            }
            var url = basePath + "/orgRoleTemplate/findListByMasterId?masterId=" + node.id;
            $("#orgRole").datagrid("reload", url);

        },
        onClickCell:onClickCell
    });
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
        if ($('#templateMaster').datagrid('validateRow', editIndex)) {
            $('#templateMaster').datagrid('endEdit', editIndex);
            editIndex = undefined;
            return true;
        } else {
            return false;
        }
    }

    function onClickCell(index, field) {
        if (endEditing1()) {
            $('#templateMaster').datagrid('selectRow', index)
                .datagrid('editCell', {index: index, field: field});
            editIndex = index;
        }
    }

    //添加
    $('#addMasterBtn').click(function () {
        if (!$("#area").combotree('getValue')) {
            $.messager.alert("系统提示", "请先选择区域,再添加模板！");
            return;
        } else {
            var node = $("#area").combotree('tree').tree('find',$("#area").combotree('getValue'))
            $("#templateMaster").datagrid('appendRow', {tableName:'ORG_ROLE_TEMPLATE',area:node.value,areaName:node.label});
            var rows = $("#templateMaster").datagrid('getRows');
            onClickCell(rows.length - 1, 'templateMaster');
        }
    })
    //删除
    $('#removeMasterBtn').click(function () {
        var row = $("#templateMaster").datagrid('getSelected');
        if (!row) {
            $.messager.alert('系统提示', "请选择要删除的行", 'info');
            return;
        } else {
            $.get(basePath + "/orgRoleTemplate/findListByMasterId?masterId=" + row.id,function(data){
                 if(data!=""){
                     $.messager.alert('系统提示', "请先删除模板对应的信息!!", 'info');
                     return;
                 }else{
                     var rowIndex = $("#templateMaster").datagrid('getRowIndex', row);
                     $("#templateMaster").datagrid('deleteRow', rowIndex);
                     if (editIndex == rowIndex) {
                         editIndex = undefined;
                     }
                 }
            })
        }
    })
    //保存
    $('#saveMasterBtn').click(function () {
        if (!endEditing1()) {
            return
        }
        var insertData = $("#templateMaster").datagrid("getChanges", "inserted");
        var updateData = $("#templateMaster").datagrid("getChanges", "updated");
        var deleteData = $("#templateMaster").datagrid("getChanges", "deleted");
        //保存时判断值不能为空
        for (var i = 0; i < insertData.length; i++) {
            if (!("templateName" in insertData[i])) {
                $.messager.alert("系统提示", "名称不能为空", "info");
                return
            }
        }
        var templateVo = {};
        templateVo.inserted = insertData;
        templateVo.deleted = deleteData;
        templateVo.updated = updateData;
        if (templateVo) {
            $.postJSON("/service/templateMaster/saveAll", JSON.stringify(templateVo), function (data) {
                if (data.data == "success") {
                    $.messager.alert("系统提示", "保存成功", "info");
                    var url=basePath + "/templateMaster/findList?tableName=ORG_ROLE_TEMPLATE";
                    $("#templateMaster").datagrid('reload', url);
                    $("#area").combotree('setValue',"");
                }
            }, function (data) {
                $.messager.alert('提示', "保存失败", "error");
            })
        }
    })




    $.extend($.fn.validatebox.defaults.rules, {
        IsExisted: {
            validator: function (value, param) {
                var rows = $('#orgRole').datagrid('getRows')
                var select_index = $('#orgRole').datagrid('getRowIndex', $('#orgRole').datagrid('getSelected'))
                for (var index = rows.length - 1; index > -1; index--) {
                    if (index != select_index && rows[index][param[0]] == value ) {
                        return false
                    }
                }
                return true
            },
            message: '职务名称不能相同！'
        }
    });

    //角色
    $("#orgRole").datagrid({
        fit: true,
        fitColumns: true,
        striped: true,
        singleSelect: true,
        toolbar: '#ft',
        method: 'GET',
        rownumbers: true,
        loadMsg: '数据正在加载中，请稍后.....',
        columns: [[{
            title: "id",
            field: "id",
            hidden: true
        }, {
            title: "职务名称",
            field: 'roleName',
            width: '10%',
            align: 'center' ,  editor: {
                type: 'textbox',
                options: {
                    required: true,
                    validType: 'IsExisted["roleName"]',
                    missingMessage: '职务名称不能为空'
                }
            }}, {
            title: "masterId",
            field: 'masterId',
            width: '10%',
            align: 'center',
            hidden:'true'
            }
        ]]  ,
        onClickCell:onClickCell1
    });


    var editIndex1 = undefined;
    function endEditing2() {
        if (editIndex1 == undefined) {
            return true
        }
        if ($('#orgRole').datagrid('validateRow', editIndex1)) {
            $('#orgRole').datagrid('endEdit', editIndex1);
            editIndex1 = undefined;
            return true;
        } else {
            return false;
        }
    }

    function onClickCell1(index, field) {
        if (endEditing2()) {
            $('#orgRole').datagrid('selectRow', index)
                .datagrid('editCell', {index: index, field: field});
            editIndex1 = index;
        }
    }


    //添加
    $('#addRoleBtn').click(function () {
        var rows=$("#templateMaster").datagrid("getSelected");
        if (!rows) {
            $.messager.alert("系统提示", "请先选模板,再添加数据！");
            return;
        } else {
            //alert(rows.id)
            $("#orgRole").datagrid('appendRow', {masterId:rows.id});
            var rows = $("#orgRole").datagrid('getRows');
            onClickCell1(rows.length - 1, 'orgRole');
        }
    })
    //删除
    $('#removeRoleBtn').click(function () {
        var row = $("#orgRole").datagrid('getSelected');
        if (!row) {
            $.messager.alert('系统提示', "请选择要删除的行", 'info');
            return;
        } else {
            var rowIndex = $("#orgRole").datagrid('getRowIndex', row);
            $("#orgRole").datagrid('deleteRow', rowIndex);
            if (editIndex1 == rowIndex) {
                editIndex1 = undefined;
            }
        }
    })

    //保存
    $('#saveRoleBtn').click(function () {
        var node=$("#templateMaster").datagrid("getSelected");
        if (!endEditing2()) {
            return
        }
        var insertData = $("#orgRole").datagrid("getChanges", "inserted");
        var updateData = $("#orgRole").datagrid("getChanges", "updated");
        var deleteData = $("#orgRole").datagrid("getChanges", "deleted");
        //保存时判断值不能为空
        for (var i = 0; i < insertData.length; i++) {
            if (!("roleName" in insertData[i])) {
                $.messager.alert("系统提示", "职务名称不能为空！", "info");
                return
            }
        }
        var orgRoleTemplateVo = {};
        orgRoleTemplateVo.inserted = insertData;
        orgRoleTemplateVo.deleted = deleteData;
        orgRoleTemplateVo.updated = updateData;
        if (orgRoleTemplateVo) {
            $.postJSON("/service/orgRoleTemplate/saveAll", JSON.stringify(orgRoleTemplateVo), function (data) {
                if (data.data == "success") {
                    $.messager.alert("系统提示", "保存成功", "info");
                    var url = basePath + "/orgRoleTemplate/findListByMasterId?masterId=" + node.id;
                    $("#orgRole").datagrid("reload", url);
                }
            }, function (data) {
                $.messager.alert('提示', "保存失败", "error");
            })
        }
    })

})