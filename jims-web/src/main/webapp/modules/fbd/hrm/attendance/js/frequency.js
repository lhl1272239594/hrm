/**
 *班次设置
 * @author yangchen
 * @version 2016-08-18
 */
var basePath = "/service";
var freItemId = "999";
var freTypeId = "999";
var freItemDes = "999";
var freTypeDes = "999";
var dg;
var d;
var flag;
var list;
var frequencyVo = {};
var value = '';
var startTime= '';
var endTime= '';
var orgCount=0;
$(function () {
    //定义组织ID
    var orgId = parent.config.org_Id;
    var editIndex;
    var stopEdit = function () {
        if (editIndex || editIndex == 0) {
            $("#freTimeGrid").datagrid('endEdit', editIndex);
            editIndex = undefined;
        }
    };
    freTypeDict();
    //查询条件：班次名称

    //查询条件:班次类型
    $("#freType").combobox({
        panelWidth: '70px',
        panelHeight: 'auto',
        idField: 'value',
        textField: 'label',
        value: '请选择',
        loadMsg: '数据正在加载',
        data: freTypeList,
        // url: '/service/dict/find-list-by-type?type=' + 'DATE_TYPE',
        mode: 'remote',
        method: 'GET'
    });
    //班次设置数据
    $("#primaryGrid").datagrid({
        iconCls: 'icon-edit',//图标
        width: '100%',
        height: '100%',
        nowrap: false,
        striped: true,
        border: true,
        method: 'get',
        toolbar: '#tb',
        fitColumns: true,
        loadMsg: '数据正在加载中，请稍后.....',
        pagination: true,//分页控件
        pageSize: 30,
        pageList: [10, 20, 30, 40, 50],
        collapsible: false,//是否可折叠的
        url: basePath + '/frequency/frequency-list?freItemId=' + freItemId + '&orgId=' + orgId + '&freTypeId=' + freTypeId + '&freItemDes=' + freItemDes,
        remoteSort: false,
        idField: 'freItemId',
        singleSelect: true,//是否单选
        rownumbers: true,//行号
        columns: [[
            {field: 'orgId', title: '', width: 100, hidden: true},
            {field: 'freItemId', title: '', width: 100, hidden: true},
            {field: 'freItemDes', title: '班次名称', width: 100, align: 'center'},
            {
                field: 'freTypeId', title: '班次类型', width: 100, align: 'center',
                formatter: function (a) {
                    for (var i = 0; i <= freTypeList.length; i++) {
                        if (a == freTypeList[i].value) {
                            return freTypeList[i].label;
                        }

                    }
                },
            },
            {
                field: 'startTime', title: '上班打卡时间', width: 100, align: 'center',
                formatter: function (startTime) {
                    if(startTime==null){
                        return '无'
                    }
                    else{
                        return startTime
                    }
                }
            },
            {
                field: 'endTime', title: '下班打卡时间', width: 100, align: 'center',
                formatter: function (endTime) {
                    if(endTime==null){
                        return '无'
                    }
                    else{
                        return endTime
                    }
                }
            },
            {
                field: 'createBy', title: '创建人', width: '10%', align: 'center',
                formatter: function (createBy) {
                    return parent.personList[createBy];
                }
            },
            {field: 'createDate', title: '创建时间', width: 100, align: 'center'}

        ]],
        onLoadSuccess: function (data) {
            $("#primaryGrid").datagrid('getPager').pagination({
                pageList: [10, 20, 30, 40, 50], //可以设置每页记录条数的列表
                displayMsg: '当前显示从第{from}条到第{to}条，共{total}条记录'
            });
        }
    });

    //配置窗口
    $("#editWin").window({
        title: '班次设置',
        closed: true,
        modal: true,
        minimizable: false,
        onClose: function () {
            $('#freGrid').datagrid('clearSelections');

            $("#editFreItem").textbox('enable');
            $("#editFreType").textbox('enable');
            $("#editStartTime").timespinner('enable');
            $("#editEndTime").timespinner('enable');
            $("#delBtn").show();
            $("#saveBtn").show();
            $("#primaryGrid").datagrid('reload');
            $("#editForm").form('reset');
        },
        onOpen: function () {
            //班次类型
            $("#editFreType").combobox({
                panelWidth: '150px',
                panelHeight: 'auto',
                idField: 'value',
                textField: 'label',
                loadMsg: '数据正在加载',
                url: '/service/dict/find-list-by-type?type=' + 'DATE_TYPE',
                mode: 'remote',
                method: 'GET',
                onLoadSuccess:function(data){
                    orgCount=data.length;
                },
                onShowPanel:function(){
                    //动态调整高度
                    if(orgCount>6){
                        $(this).combobox('panel').height(120);
                    }
                },
                onChange: function (newValue) {
                    var value = $("#editFreType").combobox('getValue');
                    if (value == '2') {
                        $("#checkTime").hide();


                    }
                    if (value == '1') {
                        $("#checkTime").show();

                    }

                }
            });
        }
    });


    //新增班次窗口
    $("#addBtn").on('click', function () {
        $("#editWin").window("open");
        //班次时间数据
        $("#flag").val('0');
        $("#id").val('999');

    });
    //修改班次窗口
    $("#editBtn").on('click', function () {
        var row = $("#primaryGrid").datagrid('getSelected');
        if (row) {
            $("#editWin").window("open");
            freItemId = row.freItemId;
            freItemDes = row.freItemDes;
            freTypeId = row.freTypeId;
            startTime = row.startTime;
            endTime = row.endTime;

            $.get(basePath + '/tool/find-list-by-type?value=' + row.freTypeId + '&type=DATE_TYPE',
                function (data) {
                    freTypeDes = data[0].label;
                });
            $("#editFreItem").textbox('setValue', freItemDes);
            $("#editFreType").combobox('setValue', freTypeId);
            $("#editFreType").combobox('setText', freTypeDes);
            $("#editFreType").combobox('disable');
            $("#editStartTime").timespinner('setValue', startTime);
            $("#editEndTime").timespinner('setValue', endTime);

            $("#flag").val('1');
            $("#id").val(row.freItemId);
        } else {
            $.messager.alert("提示", "请选择一行记录!", "info");
            return;
        }

    });

    //删除
    $("#delBtn").on('click', function () {
        flag = '0';
        var row = $("#primaryGrid").datagrid('getSelected');
        if (row) {
            frequencyVo.freItemId = row.freItemId;
            freItemId = row.freItemId;
            frequencyVo.flag = flag;
            //判断是否在排班管理中被占用，若被占用不能删除。
            $.post(basePath + "/frequency/if-occupy?freItemId=" + freItemId + "&orgId=" + orgId,
                function (data) {
                    if (data.code == "yes") {
                        $.messager.alert('提示', '该班次已在排班管理中被使用，请先删除排班管理中的对应数据！', 'info');
                        return;
                    }
                    $.messager.confirm('提示', '确定要进行删除操作吗', function (r) {
                        if (r) {
                            $.postJSON(basePath + "/frequency/frequency-del", JSON.stringify(frequencyVo), function (data) {

                                $('#primaryGrid').datagrid('reload');
                                row.length = 0;
                                $('#primaryGrid').datagrid('clearSelections');
                            });
                        }
                    })
                });
        } else {
            $.messager.alert('提示', '请选择一行记录！', 'info');

        }
    });
   /* //查看
    $("#viewBtn").on('click', function () {
        var row = $("#freGrid").datagrid("getSelected");
        if (row) {
            freItemId = row.freItemId;
            freItemDes = row.freItemDes;
            freTypeId = row.freTypeId;
            $("#editFreGrid").window("open");
            $("#freTimeGrid").edatagrid({
                iconCls: 'icon-edit',//图标
                width: '100%',
                height: '100%',
                nowrap: false,
                striped: true,
                border: true,
                method: 'get',
                toolbar: '#ftb',
                fitColumns: true,
                loadMsg: '数据正在加载中，请稍后.....',
                collapsible: false,//是否可折叠的
                remoteSort: false,
                uri: basePath + '/frequency/frequency-time-list?freItemId=' + freItemId + '&orgId=' + orgId,
                idField: 'freItemId',
                singleSelect: false,//是否单选
                rownumbers: true,//行号
                columns: [[
                    {field: 'orgId', title: '', width: 100, hidden: true},
                    {field: 'freItemId', title: '', width: 100, hidden: true},
                    {field: 'freTimeId', title: '', width: 100, hidden: true},
                    {field: 'startTime', title: '上班时间', width: 100, align: 'center',},
                    {
                        field: 'startCheckType', title: '上班打卡', width: 100, align: 'center',
                        formatter: function (value) {
                            if (value == "1") {
                                return "是";
                            }
                            if (value == "0") {
                                return "否";
                            }
                        }
                    },
                    {field: 'lastComeTime', title: '不记迟到时间', width: 100, align: 'center',},
                    {field: 'endTime', title: '下班时间', width: 100, align: 'center',},
                    {
                        field: 'endCheckType', title: '下班打卡', width: 100, align: 'center',
                        formatter: function (value) {
                            if (value == "1") {
                                return "是";
                            }
                            if (value == "0") {
                                return "否";
                            }
                        }
                    },
                    {field: 'firstLeaveTime', title: '不计早退时间', width: 100, align: 'center',}

                ]]
            });

            $.get(basePath + '/tool/find-list-by-type?value=' + freTypeId + '&type=DATE_TYPE',
                function (data) {
                    freTypeDes = data[0].label;
                });
            $("#editFreItem").textbox('setValue', freItemDes);
            $("#editFreType").combobox('setValue', freTypeId);
            $("#editFreType").combobox('setText', freTypeDes);

            $("#editFreItem").textbox('disable');
            $("#editFreType").textbox('disable');


            $("#addDateBtn").hide();
            $("#delDateBtn").hide();
            $("#saveDateBtn").hide();

            loadData(freItemId);

        }
        else {
            $.messager.alert("提示", "请选择一行记录!", "info");

        }
    });*/
  /*  //新增班次时间
    $("#addDateBtn").on('click', function () {

        $('#freTimeGrid').edatagrid('addRow', {
            row: {
                freTimeId: '1'
            }
        });

    });
*/
  /*  //删除班次时间
    $("#delDateBtn").on('click', function () {

        var rows = $("#freTimeGrid").edatagrid('getSelections');
        if (rows.length == 0) {
            $.messager.alert('提示', '请选择一行记录！', 'info');
            return;
        }
        var a = [];
        for (var i = 0; i < rows.length; i++) {
            var index = $('#freTimeGrid').edatagrid('getRowIndex', rows[i]);
            if (rows[i].freTimeId != 1) {
                a.push(rows[i]);
            }
        }
        for (var i = 0; i < rows.length; i++) {
            var index = $('#freTimeGrid').edatagrid('getRowIndex', rows[i]);
            $.messager.confirm('提示', '确定要进行删除操作吗', function (r) {
                if (r) {
                    //大于一行直接删除
                    if (a.length > 0) {
                        var num = $("#freTimeGrid").edatagrid('getData');//获取总条数
                        if (num.rows.length > 1) {
                            $.postJSON(basePath + "/frequency/frequency-time-del", JSON.stringify(a), function (data) {
                                if (data.data == 'success') {
                                    $("#freTimeGrid").edatagrid('destroyRow', index);
                                }
                            });
                        }
                        else {
                            $.messager.confirm('提示', '该行数据已是最后一条，删除后会关联删除此项班次信息，确定删除吗？', function (r) {
                                if (r) {
                                    $.postJSON(basePath + "/frequency/frequency-time-del1", JSON.stringify(a), function (data) {
                                        if (data.data == 'success') {
                                            $("#freTimeGrid").edatagrid('destroyRow', index);
                                            $("#editFreGrid").window('close');
                                            $('#freGrid').datagrid('reload');
                                            $("#freGrid").datagrid('clearSelections');
                                        }
                                    });
                                }
                                else {
                                    $("#freTimeGrid").edatagrid('reload');
                                }
                            });
                        }
                    }
                    if (a.length == 0) {
                        $("#freTimeGrid").edatagrid('destroyRow', index);
                    }
                }
            })
        }

        $('#festivalDateGrid').edatagrid('clearSelections');
    });*/

    //保存
    $("#saveBtn").on('click', function () {
        freItemDes = $("#editFreItem").textbox('getValue');
        freTypeId = $("#editFreType").combobox('getValue');
        freTypeDes = $("#editFreType").combobox('getText');
        flag = $("#flag").val();
        freItemId = $("#id").val();
        startTime = $("#editStartTime").timespinner('getValue');
        endTime = $("#editEndTime").timespinner('getValue');

        if (freItemDes == '' || freItemDes.indexOf(" ") >= 0) {
            $.messager.alert("提示", "请输入有效的班次名称，不能包含空格!", "info");
            return
        }

        if (getRealLen(freItemDes) > 20) {
            $.messager.alert("提示", "班次名称内容输入过长！", "info");
            return
        }

        if (freTypeId == '') {
            $.messager.alert("提示", "请选择班次类型!", "info");
            return
        }
        if (freTypeDes == '工作日') {

            if (startTime == '') {
                $.messager.alert("提示", "请选择上班打卡时间!", "info");
                return
            }
            if (endTime == '') {
                $.messager.alert("提示", "请选择下班打卡时间!", "info");
                return
            }
            if (endTime == startTime) {
                $.messager.alert("提示", "上班打卡时间与下班打卡时间不能相同!", "info");
                return
            }
           /* var start=new Date();
            var end=new Date();
            start.setHours($("#editStartTime").timespinner('getHours'));
            var endHour=$("#editEndTime").timespinner('getHours');

            end.setHours(endHour);
            start.setMinutes($("#editStartTime").timespinner('getMinutes'));
            end.setMinutes($("#editEndTime").timespinner('getMinutes'));
            start.setSeconds($("#editStartTime").timespinner('getSeconds'));
            end.setSeconds($("#editEndTime").timespinner('getSeconds'));
            if(endHour!='0'){
                if (end < start) {
                    $.messager.alert("提示", "下班打卡时间不能早于上班打卡时间!", "info");
                    return
                }
            }*/
        }

        frequencyVo.orgId = parent.config.org_Id;
        frequencyVo.freItemDes = freItemDes;
        frequencyVo.freItemId = freItemId;
        frequencyVo.freTypeId = freTypeId;
        frequencyVo.startTime = startTime;
        frequencyVo.endTime = endTime;

        //查询数据库，新增数据是否重复
        $.get(basePath + "/frequency/frequency-boolean?freItemDes=" + freItemDes + "&orgId=" + orgId + "&freItemId=" + freItemId,
            function (data) {
                var list = eval(data);
                var num = list[0]['num'];

                if (num == '1') {
                    var str = '该班次描述已经存在，请重新输入！';
                    $.messager.alert("提示", str);
                    return
                }
                else {
                    //如果不重复，执行保存代码
                    $.postJSON(basePath + "/frequency/merge", JSON.stringify(frequencyVo), function (data) {
                        //$("#editFreGrid").window('close');
                        $("#editForm").form('reset');
                        $("#editWin").window('close');
                        $('#primaryGrid').datagrid('clearSelections');
                    })
                }
            });
    });
    $("#cancelBtn").on('click', function () {
        $("#editForm").form('reset');
        $("#editWin").window('close');
    });
});
//取消

var freTypeList;
function freTypeDict() {
    $.get(basePath + '/tool/find-list-by-type?type=DATE_TYPE&value=' + value, function (data) {
        freTypeList = data;

    });
}
