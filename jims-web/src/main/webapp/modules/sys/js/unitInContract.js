$(function () {

    var unitType = [];//单位类型
    $.ajax({
        url: '/service/dict/findListByType?type=UNIT_TYPE_DICT',
        type: 'get',
        async: false,
        success: function(res){
            unitType = res
        }
    })

    var editIndex;
    var stopEdit = function () {
        if (editIndex || editIndex == 0) {
            $("#dg").datagrid('endEdit', editIndex);
            editIndex = undefined;
        }
    };
    $("#dg").datagrid({
        toolbar: '#tb',
        iconCls: 'icon-edit',//图标
        width: 'auto',
        height: 'auto',
        nowrap: false,
        striped: true,
        border: true,
        method:'get',
        collapsible: false,//是否可折叠的
        fit: true,//自动大小
        url:basePath+'/UnitInContract/list-all?orgId='+parent.config.org_Id,
        remoteSort: false,
        idField: 'fldId',
        singleSelect: true,//是否单选
        rownumbers:true,//行号
        columns: [[
            {field: 'unitName', title: '合同单位名称', width: '10%', align: 'center', editor: {type: 'textbox',options:{required:true}}},
            {field: 'inputCode', title: '输入码', width: '10%', align: 'center'},
            {field: 'address', title: '单位地址', width: '10%', align: 'center',editor: {type: 'textbox',options:{required:true}}},
            {field: 'unitType', title: '单位性质', width: '10%', align: 'center',
                editor: {
                    type: 'combobox',
                    options:{
                        valueField: 'value',
                        textField: 'label',
                        data: unitType,
                        required:true
                    }
                },
                formatter: function(value){
                    if(value) {
                        for (var i = 0; i < unitType.length; i++) {
                            if(unitType[i].value == value){
                                return unitType[i].label
                            }
                        }
                    }
                    return ''
                }
            },
            {field: 'subordinateTo', title: '隶属单位', width: '10%', align: 'center',editor: {type: 'textbox',options:{required:true}}},
            {
                field: 'distanceToHospital',
                title: '就医距离', width: '10%',
                align: 'center',
                editor: {
                    type: 'numberbox',
                    options:{
                        min:1,
                        max:999.9,
                        precision: 1
                    }
                }
            },
            {field: 'regularNum', title: '在编人数', width: '10%', align: 'center',editor:{type: 'textbox',options:{required:true}}},
            {field: 'tempNum', title: '非编人数', width: '10%', align: 'center',editor: {type: 'textbox',options:{required:true}}},
            {field: 'retiredNum', title: '离退休人数', width: '10%', align: 'center',editor: {type: 'textbox',options:{required:true}}}
        ]],
        onClickRow: function (index, row) {
            stopEdit();
            $(this).datagrid('beginEdit', index);
            editIndex = index;
        }
    });



    $("#addBtn").on('click', function () {
        stopEdit();
        $("#dg").datagrid('appendRow', {});
        var rows = $("#dg").datagrid('getRows');
        var addRowIndex = $("#dg").datagrid('getRowIndex', rows[rows.length - 1]);
        editIndex = addRowIndex;
        $("#dg").datagrid('selectRow', editIndex);
        $("#dg").datagrid('beginEdit', editIndex);
    });

    $("#editBtn").on('click', function () {
        var row = $("#dg").datagrid("getSelected");
        var index = $("#dg").datagrid("getRowIndex", row);

        if (index == -1) {
            $.messager.alert("提示", "请选择要修改的行！", "info");
            return;
        }

        if (editIndex == undefined) {
            $("#dg").datagrid("beginEdit", index);
            editIndex = index;
        } else {
            $("#dg").datagrid("endEdit", editIndex);
            $("#dg").datagrid("beginEdit", index);
            editIndex = index;
        }
    });


    $("#delBtn").on('click', function () {
        var row = $("#dg").datagrid('getSelected');
        if (row == null) {
            $.messager.alert("系统提示", "请选择要删除的项目");
            return;
        }
        if (!row.id) {
            //判断是否是新加项目
            var index = $("#dg").datagrid('getRowIndex', row);

            $.messager.confirm('系统提示', '确定要进行删除操作吗', function (r) {
                if (r) {
                    $("#dg").datagrid('deleteRow', index);
                }
            });

        } else {
            $.messager.confirm('系统提示', '确定要进行删除操作吗', function (r) {
                if (r) {
                    $.postJSON(basePath + "/UnitInContract/del", row.id, function (data) {
                        $.messager.alert('系统提示', '删除成功', 'info');
                        $('#dg').datagrid('reload');
                    })
                }
            });
        }
    });


    $("#searchBtn").on("click", function () {

        var inputCode = $("#inputCode").textbox("getValue");
        $.get(basePath +"/UnitInContract/find-by-input-code?inputCode=" + inputCode+"&orgId="+parent.config.org_Id, function (data) {

            $("#dg").datagrid('loadData', data);

        });
    });

    $("#saveBtn").on('click', function () {
        if (editIndex || editIndex == 0) {
            $("#dg").datagrid("endEdit", editIndex);
        }

        var insertData = $("#dg").datagrid("getChanges", "inserted");
        var updateData = $("#dg").datagrid("getChanges", "updated");
        var examRptPatternVo = {};
        examRptPatternVo.inserted = insertData;
        examRptPatternVo.updated = updateData;
        examRptPatternVo.orgId = parent.config.org_Id;

        $.postJSON(basePath + "/UnitInContract/merge", JSON.stringify(examRptPatternVo), function (data) {
            $.messager.alert('系统提示', '保存成功', 'info');
            $('#dg').datagrid('reload');
        })


    });

});


