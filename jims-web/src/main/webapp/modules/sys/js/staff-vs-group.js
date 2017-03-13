/**
 *用户分组人员维护
 * @author yangruidong
 * @version 2016-05-25
 */

$("<link>").attr({rel: "stylesheet", type: "text/css", href: "/static/easyui/css/icon.css"}).appendTo("head");
$("<script>").attr({type: "application/javascript", src: "/static/easyui/js/jquery.easyui.min.js"}).appendTo("head");
$("<script>").attr({
    type: "application/javascript",
    src: "/static/easyui/locale/easyui-lang-zh_CN.js"
}).appendTo("head");
$("<script>").attr({type: "application/javascript", src: "/static/js/tool.js"}).appendTo("head");
$("<script>").attr({type: "application/javascript", src: "/static/js/formSubmit.js"}).appendTo("head");
var basePath = "/service";
$(function () {
    var orgId = parent.config.org_Id;
    var groupId;
    var deptName;
    var staffFrom = [];
    var groupClass;
    $("#groupGrid").treegrid({
        fit: true,
        fitColumns: true,
        striped: true,
        singleSelect: true,
        idField: "groupId",
        treeField: "groupName",
        loadMsg: '数据正在加载中，请稍后.....',
        columns: [[{
            title: 'groupId',
            field: "groupId",
            hidden: true
        }, {
            title: '分组名称',
            field: 'groupName',
            width: '100%'
        }, {
            title: '分组代码',
            field: 'groupCode',
            width: '100%',
            hidden: true
        }]],
        onClickRow: function (rowIndex, rowData) {
            staffFrom = [];
            var node = $("#groupGrid").treegrid("getSelected");
            groupId = node.groupId;     //组id
            groupCode = node.groupCode;    //组代码
            groupClass=node.pid;
            groupName = node.groupName;   //组类
            var url = basePath + "/staff-vs-group/listStaff?groupId=" + groupId + "&orgId=" + orgId;
            $("#staffGrid").datagrid("reload", url);
        }
    });
    //加载树形结构的treegrid数据
    var loadDept = function () {

        var depts = [];
        var treeDepts = [];
        var loadPromise = $.get("/service/staff-vs-group/list?orgId=" + orgId, function (data) {
            $.each(data, function (index, item) {
                var obj = {};
                obj.groupName = item.groupName;
                obj.groupId = item.groupId;
                obj.groupCode = item.groupCode;
                obj.pid = item.pid;
                obj.children = [];
                depts.push(obj);
            });

        });
        loadPromise.done(function () {
            for (var i = 0; i < depts.length; i++) {
                for (var j = 0; j < depts.length; j++) {
                    if (depts[i].groupId == depts[j].pid) {
                        depts[i].children.push(depts[j]);
                    }
                }
                if (depts[i].children.length > 0 && !depts[i].pid) {
                    treeDepts.push(depts[i]);
                }

                if (!depts[i].pid && depts[i].children <= 0) {
                    treeDepts.push(depts[i])
                }
            }
            $("#groupGrid").treegrid('loadData', treeDepts);
        })
    }
    loadDept();


    $("#staffGrid").datagrid({
        fit: true,
        fitColumns: true,
        striped: true,
        singleSelect: true,
        toolbar: '#ft',
        method: 'GET',
        rownumbers: true,
        loadMsg: '数据正在加载中，请稍后.....',
        pagination: true,//分页控件
        pageSize: 15,
        pageList: [10, 15, 30, 50],//可以设置每页记录条数的列表
        columns: [[{
            title: "id",
            field: "id",
            hidden: true
        }, {
            title: "staffId",
            field: "staffId",
            hidden: true
        },
            {
                title: "姓名",
                field: "name",
                width: '15%',
                editor: {
                    type: 'combogrid',
                    options: {
                        panelWidth: 306,
                        idField: 'name',
                        textField: 'name',
                        mode: 'remote',
                        method: 'GET',
                        url: basePath + '/input-setting/listParamByGET?orgId=' + orgId + '&dictType=v_staff_dict',
                        columns: [[
                            {field: 'staff_id', title: 'staffId',hidden:true,width: 100},
                            {field: 'name', title: '姓名', width: 100},
                            {field: 'dept_name', title: '科室名称', width: 100},
                            {field: 'input_code', title: '拼音码', width: 100}
                        ]],
                        onSelect: function (index, data) {
                            var row = $('#staffGrid').datagrid('getSelected');
                            row.staffId = data.staff_id;
                            row.deptName = data.dept_name;
                            $('#staffGrid').datagrid('endEdit', editIndex1);
                        }
                    }
                }
            }, {
                title: '科室名称',
                field: 'deptName',
                width: '15%'
            }, {
                title: '分组名称 ',
                field: 'groupName',
                width: '15%'
            }
        ]],
        onClickCell: onClickCell1
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

    var editIndex1 = undefined;

    function endEditing2() {
        if (editIndex1 == undefined) {
            return true
        }
        if ($('#staffGrid').datagrid('validateRow', editIndex1)) {
            $('#staffGrid').datagrid('endEdit', editIndex1);
            editIndex1 = undefined;
            return true;
        } else {
            return false;
        }
    }

    function onClickCell1(index, field) {
        if (endEditing2()) {
            $('#staffGrid').datagrid('selectRow', index)
                .datagrid('editCell', {index: index, field: field});
            editIndex1 = index;
        }
    }

    //开始编辑行
    $("#addBtn").on('click', function () {
        var classRow = $("#groupGrid").datagrid('getSelected');
        if(groupClass==null){
            if(classRow.children.length>0){
                $.messager.alert("系统提示", "请选择子分组添加人员！", "info");
                return;
            }else{
                $.messager.alert("系统提示", "请前往科室分组维护中添加分组！", "info");
                return;
            }
        }
        if (classRow) {
            $("#staffGrid").datagrid('appendRow', {groupName: groupName,groupClass:groupClass});
            var rows = $("#staffGrid").datagrid('getRows');
            onClickCell1(rows.length - 1, 'name');
        }
        else {
            $.messager.alert("系统提示", "请先选择用户分组类", "info");
        }


    });


    //保存
    $("#saveBtn").on("click", function () {
        //不能添加重复的项目
        var data = $("#staffGrid").datagrid('getData');
        for (var i = 0; i < data.total; i++) {
            for (var j = i + 1; j < data.total; j++) {
                if (data.rows[i].name == data.rows[j].name) {
                    $.messager.alert("系统提示", "不能添加重复的人员,请修改后再保存", "info");
                    return;
                }
            }
        }

        if (editIndex1 || editIndex1 == 0) {
            $("#staffGrid").datagrid("endEdit", editIndex1);
        }
        var insertData = $("#staffGrid").datagrid("getChanges", "inserted");
        var updateData = $("#staffGrid").datagrid("getChanges", "updated");
        var deleteData = $("#staffGrid").datagrid("getChanges", "deleted");

        var staffVsGroupVo = {};
        for (var i = 0; i < insertData.length; i++) {
            delete insertData[i]["deptName"];
            delete insertData[i]["groupName"];
            delete insertData[i]["name"];
        }
        for (var i = 0; i < updateData.length; i++) {
            delete updateData[i]["deptName"];
            delete updateData[i]["groupName"];
            delete updateData[i]["name"];
            delete updateData[i]["inserted"];
            delete updateData[i]["deleted"];
            delete updateData[i]["updated"];
            delete updateData[i]["groupClassId"];
            delete updateData[i]["orgId"];
            delete updateData[i]["pid"];
            delete updateData[i]["staffVsId"];
            delete updateData[i]["inputCode"];

        }
        for (var i = 0; i < deleteData.length; i++) {
            deleteData[i] = {id: deleteData[i].id};
            delete deleteData[i]["deptName"];
            delete deleteData[i]["groupName"];
            delete deleteData[i]["name"];
        }



        staffVsGroupVo.inserted = insertData;
        staffVsGroupVo.deleted = deleteData;
        staffVsGroupVo.updated = updateData;
        staffVsGroupVo.orgId = orgId;
        staffVsGroupVo.groupId = groupId;     //组id
        staffVsGroupVo.groupCode = groupCode;   //组代码
        staffVsGroupVo.groupClass = groupClass;   //组类

        if (staffVsGroupVo != "") {
            $.postJSON(basePath + "/staff-vs-group/saveVsGroup", JSON.stringify(staffVsGroupVo), function (data) {
                if (data.data == "success") {
                    $.messager.alert("系统提示", "保存成功", "info");
                    $("#staffGrid").datagrid('reload');
                }
            }, function (data) {
                console.log(data);
                $.messager.alert('提示', data.responseText, "error");
            })
        }
    });
    //删除
    $("#removeBtn").on("click", function () {
        var row = $("#staffGrid").datagrid('getSelected');
        if (row) {
            var rowIndex = $("#staffGrid").datagrid('getRowIndex', row);
            $("#staffGrid").datagrid('deleteRow', rowIndex);
            if (editIndex1 == rowIndex) {
                editIndex1 = undefined;
            }
        } else {
            $.messager.alert('系统提示', "请选择要删除的行", 'info');
        }
    });

});




