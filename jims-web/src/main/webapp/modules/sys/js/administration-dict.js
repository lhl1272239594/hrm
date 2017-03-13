/**
 * 检查类别维护
 * @author tangxb
 * @version 2016-04-29
 */

$(function () {


    var data= [{
        value: '门诊',
        text: '门诊'
    },{
        value: '住院',
        text: '住院'
    },{
        value: '综合',
        text: '综合'
    }]

    var editIndex;
    var stopEdit = function () {
        if (editIndex || editIndex == 0) {
            $("#adminDict").datagrid('endEdit', editIndex);
            editIndex = undefined;
        }
    }

    function unitformatter(value, rowData, rowIndex) {
        if (value == 0) {
            return;
        }

        for (var i = 0; i < data.length; i++) {
            if (data[i].value == value) {
                return data[i].text;
            }
        }
    }

        //检查子类别
        $("#adminDict").datagrid({
            fit: true,
            fitColumns: true,
            striped: true,
            singleSelect: true,
            toolbar: '#tb',
            method: 'GET',
            rownumbers: true,
            url: basePath + "/AdministrationDict/listAll",
            loadMsg: '数据正在加载中，请稍后.....',
            /* pagination: true,//分页控件
             pageSize: 15,
             pageList: [10, 15, 30, 50],//可以设置每页记录条数的列表*/
            columns: [[{
                title: "id",
                field: "id",
                hidden: true
            }, {
                title: "给药途径代码",
                field: "administrationCode",
                width: '20%',
                editor: 'text'


            }, {
                title: '给药途径名称',
                field: 'administrationName',
                width: '20%',
                editor: 'text'

            }, {
                title: '输入码',
                field: 'inputCode',
                width: '20%',
                editor: 'text'

            }, {
                title: '门诊住院标识',
                field: 'inpOutpFlag',
                width: '20%',
                formatter: unitformatter,
                editor: { type: 'combobox', options: { data: data, valueField: "value", textField: "text" } }

            }
            ]],
            onClickRow: function (index, row) {
                stopEdit();
                $(this).datagrid('beginEdit', index);
                editIndex = index;
            }

        });

//开始编辑行
        $("#addBtn").on('click', function () {
            stopEdit();
            $("#adminDict").datagrid('appendRow', {});
            var rows = $("#adminDict").datagrid('getRows');
            var addRowIndex = $("#adminDict").datagrid('getRowIndex', rows[rows.length - 1]);
            editIndex = addRowIndex;
            $("#adminDict").datagrid('selectRow', editIndex);
            $("#adminDict").datagrid('beginEdit', editIndex);
        });

        $("#editBtn").on("click", function () {
            var row = $("#adminDict").datagrid("getSelected");
            var index = $("#adminDict").datagrid("getRowIndex", row);

            if (index == -1) {
                $.messager.alert("提示", "请选择要修改的行！", "info");
                return;
            }

            if (editIndex == undefined) {
                $("#adminDict").datagrid("beginEdit", index);
                editIndex = index;
            } else {
                $("#adminDict").datagrid("endEdit", editIndex);
                $("#adminDict").datagrid("beginEdit", index);
                editIndex = index;
            }
        });


//组织机构人员保存
        $("#saveBtn").on("click", function () {
            if (editIndex || editIndex == 0) {
                $("#adminDict").datagrid("endEdit", editIndex);
            }

            var insertData = $("#adminDict").datagrid("getChanges", "inserted");
            var updateData = $("#adminDict").datagrid("getChanges", "updated");
            var deleteData = $("#adminDict").datagrid("getChanges", "deleted");

            var administrationDictVo = {};
            administrationDictVo.inserted = insertData;
            administrationDictVo.deleted = deleteData;
            administrationDictVo.updated = updateData;


            if (administrationDictVo) {
                $.postJSON(basePath + "/AdministrationDict/saveAll", JSON.stringify(administrationDictVo), function (data) {
                    if (data.data == "success") {
                        $.messager.alert("系统提示", "保存成功", "info");
                        $("#adminDict").datagrid('reload');
                    }

                }, function (data) {
                    $.messager.alert('提示', "保存失败", "error");
                })
            }
        });


        $("#delBtn").on("click", function () {
            var row = $("#adminDict").datagrid('getSelected');
            if (row) {
                var rowIndex = $("#adminDict").datagrid('getRowIndex', row);
                $("#adminDict").datagrid('deleteRow', rowIndex);
                if (editIndex == rowIndex) {
                    editIndex = undefined;
                }
                //$("#adminDict").datagrid('reload');
            } else {
                $.messager.alert('系统提示', "请选择要删除的行", 'info');
            }
        });

        $("#inpOutpFlag").combobox();

        $("#inpOutpFlag").combobox('loadData', data);


        $("#seaBtn").on("click", function () {

           var inpOutpFlag = $("#inpOutpFlag").combobox('getValue');

            $.getJSON(basePath + "/AdministrationDict/listAdministrationByInpOrOutpFlag?inpOrOutpFlag="+inpOutpFlag, function (data) {
                if (data) {
                    $('#adminDict').datagrid("loadData", data);
                }
            });
        });

});




