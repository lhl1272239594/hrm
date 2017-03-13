function onloadMethod() {
    $('#list_data').datagrid({
        iconCls: 'icon-edit',//图标
        width: 'auto',
        height: 'auto',
        nowrap: false,
        striped: true,
        border: true,
        method:'get',
        collapsible: false,//是否可折叠的
        fit: true,//自动大小
        url:basePath+'/DrugDispensProperty/list',
        //sortName: 'code',
        //sortOrder: 'desc',
        remoteSort: false,
        idField: 'fldId',
        singleSelect: false,//是否单选
        pagination:true,//分页控件
        pageSize:15,
        pageList: [10,15,30,50],//可以设置每页记录条数的列表
        rownumbers:true,//行号
        columns: [[      //每个列具体内容
            {field: 'hosId', title: '医院', width: '28%', align: 'center'},
            {field: 'dispensary', title: '调配药房', width: '10%', align: 'center'},
            {field: 'drugCode', title: '药品代码', width: '18%', align: 'center'},
            {field: 'dispensingProperty', title: '摆药类别', width: '18%', align: 'center'},
            {field: 'drugSpec', title: '药品规格', width: '18%', align: 'center'},
            {field: 'dispensingCumulate', title: '摆药累积', width: '18%', align: 'center'},
            {field: 'separable', title: '可分割否', width: '18%', align: 'center'},
            {field: 'virtualCabinet', title: '虚拟药柜', width: '18%', align: 'center'},
            {field: 'createBy', title: '创建人', width: '18%', align: 'center'},
            {field: 'createDate', title: '创建时间', width: '18%', align: 'center'},
            {field: 'updateBy', title: '更新人', width: '18%', align: 'center'},
            {field: 'updateDate', title: '更新时间', width: '18%', align: 'center'},
            {field: 'remarks', title: '备注信息', width: '18%', align: 'center'},
            {
                field: 'id', title: '操作', width: '30%', align: 'center', formatter: function (value, row, index) {
                var html = '<button class="easy-nbtn easy-nbtn-success easy-nbtn-s" onclick="look(\'' + value + '\')"><img src="/static/images/index/icon1.png" width="12"/>查看</button>' +
                    '<button class="easy-nbtn easy-nbtn-info easy-nbtn-s" onclick="get(\'' + value + '\')"><img src="/static/images/index/icon2.png"  width="12" />修改</button>' +
                    '<button class="easy-nbtn easy-nbtn-warning easy-nbtn-s" onclick="deleteRow(\'' + value + '\')"><img src="/static/images/index/icon3.png" width="16"/>删除</button>';
                return html;
            }
            }
        ]],
        frozenColumns: [[
            {field: 'ck', checkbox: true}
        ]],
        toolbar: [{
            text: '添加',
            iconCls: 'icon-add',
            handler: function () {
                $("#drugDispensPropertyForm").form('clear');
                $("#dlg").dialog({title: '添加'}).dialog("open")
            }
        }, '-', {
            text: '修改',
            iconCls: 'icon-edit',
            handler: function() {
                var selectRows = $('#list_data').datagrid("getSelections");
                if (selectRows.length < 1) {
                    $.messager.alert("提示消息", "请选中要删的数据!");
                    return;
                }
                get(selectRows[0].id);
            }
        }, '-', {
            text: '删除',
            iconCls: 'icon-remove',
            handler: function () {
                doDelete();
            }
        }]
    });
//设置分页控件
    var p = $('#list_data').datagrid('getPager');
    $(p).pagination({
        beforePageText: '第',//页数文本框前显示的汉字
        afterPageText: '页    共 {pages} 页',
        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录'
    });


}

///批量删除
function doDelete() {
    //把你选中的 数据查询出来。
    var selectRows = $('#list_data').datagrid("getSelections");
    if (selectRows.length < 1) {
        $.messager.alert("提示消息", "请选中要删的数据!");
        return;
    }

    //真删除数据
    //提醒用户是否是真的删除数据
    $.messager.confirm("确认消息", "您确定要删除信息吗？", function (r) {
        if (r) {
            //真删除了  1,3,4
            var strIds = "";
            for (var i = 0; i < selectRows.length; i++) {
                strIds += selectRows[i].id + ",";
            }
            strIds = strIds.substr(0, strIds.length - 1);
            del(strIds);
        }
    })
}

//列删除
function deleteRow(id) {
    //真删除数据
    //提醒用户是否是真的删除数据
    $.messager.confirm("确认消息", "您确定要删除信息吗？", function (r) {
        if (r) {
            del(id);
        }
    })
}

/**
 * 删除方法
 * @param id
 */
function del(id) {
    $.ajax({
        'type': 'POST',
        'url': basePath + '/DrugDispensProperty/del',
        'contentType': 'application/json',
        'data': id = id,
        'dataType': 'json',
        'success': function (data) {
            if (data.data == 'success') {
                $.messager.alert("提示消息", data.code + "条记录，已经删除");
                $('#list_data').datagrid('load');
                $('#list_data').datagrid('clearChecked');
            } else {
                $.messager.alert('提示', "删除失败", "error");
            }
        },
        'error': function (data) {
            $.messager.alert('提示', "保存失败", "error");
        }
    });
}

/**
 * 保存方法
 */
function saveDict() {
    $.postForm(basePath + '/DrugDispensProperty/save', 'drugDispensPropertyForm', function (data) {
        if (data.data == 'success') {
            $.messager.alert("提示消息", data.code + "条记录，保存成功");
            $("#dlg").dialog('close');
            $('#list_data').datagrid('load');
            $('#list_data').datagrid('clearChecked');
        } else {
            $.messager.alert('提示', "保存失败", "error");
        }
    }, function (data) {
        $.messager.alert('提示', "保存失败", "error");
    })
}

/**
 * 修改字典
 * @param id
 */
function get(id) {
    $("#saveBut").show();
    $("#dlg").dialog({title: '修改字典信息'}).dialog("open");
    $.ajax({
        'type': 'post',
        'url': basePath + '/DrugDispensProperty/get',
        'contentType': 'application/json',
        'data': id = id,
        'dataType': 'json',
        'success': function (data) {
            $('#drugDispensPropertyForm').form('load', data);
        }
    });
}

/**
 * 查看字典
 * @param id
 */
function look(id) {
    $("#dlg").dialog({title: '查看字典信息'}).dialog("open");
    $("#saveBut").hide();
    $.ajax({
        'type': 'post',
        'url': basePath + '/DrugDispensProperty/get',
        'contentType': 'application/json',
        'data': id = id,
        'dataType': 'json',
        'success': function (data) {
            $('#drugDispensPropertyForm').form('load', data);
        }
    });
}