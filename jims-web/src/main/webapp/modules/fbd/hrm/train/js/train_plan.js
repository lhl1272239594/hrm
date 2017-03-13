/*
 *培训计划管理
 * @author yangchen
 * @version 2016-08-18
 *!/*/
var basePath = "/service";

var dg;
var d;
var flag;
var editIndex = undefined;
var page = '1';
var rows = '30';
var trainPlanVo = {};
var orgId = parent.config.org_Id;
var id = '999';
var userId = '';
var userName = '';
var value = '';
var label = '';
var obj1 = new Object();
var obj2 = new Object();
var depts = [];
var treeDepts = [];
var ssPlanId = '';
var trainPlanTittle = '';
var search = false;
var type='';
$(function () {
    //初始化富文本编辑器
    $("#editBtn").hide();
    var editor;
    KindEditor.ready(function (K) {
        editor = K.create('textarea[name="editTrainPlanContent"]', {

            allowImageUpload: false,
            items: [
                'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor', 'bold', 'italic', 'underline',
                'removeformat', '|', 'justifyleft', 'justifycenter', 'justifyright', 'insertorderedlist',
                'insertunorderedlist']
        });

    });

    $.get(basePath + '/tool/find-list-by-type?type=TRAIN_TYPE_DICT&value=' + value, function (data) {
        $.each(data, function (index, item) {
            obj1[item.value] = item.label;
        });
    });
    $.get(basePath + '/tool/find-list-by-type?type=STATUS_TYPE&value=' + value, function (data) {
        $.each(data, function (index, item) {
            obj2[item.value] = item.label;
        });
    });
    $("#type").combobox({
        panelWidth: '150px',
        panelHeight: 'auto',
        idField: 'value',
        textField: 'label',
        value: '请选择',
        loadMsg: '数据正在加载',
        url: '/service/tool/find-list-by-type?type=TRAIN_TYPE_DICT',
        mode: 'remote',
        method: 'GET'
    });
    $("#primaryGrid").datagrid({
        iconCls: 'icon-edit',//图标
        width: '100%',
        height: '100%',
        nowrap: false,
        striped: true,
        border: true,
        singleSelect: false,
        method: 'get',
        toolbar: '#tb',
        fitColumns: true,
        loadMsg: '数据正在加载中，请稍后.....',
        pagination: true,//分页控件
        collapsible: false,//是否可折叠的
        remoteSort: false,
        pageSize: 30,
        rownumbers: true,//行号
        url: basePath + '/trainPlan/train-plan-list?trainPlanTittle=' + trainPlanTittle + '&orgId=' + orgId +'&type='+type,
        columns: [[
            {field: 'orgId', title: '', width: '10%', hidden: true},
            {field: 'ck', title: '', checkbox: true},
            {field: 'trainPlanId', title: '', width: '10%', hidden: true},
            {field: 'trainPlanTittle', title: '培训主题', width: '25%', align: 'center',},
            {field: 'trainPlanContent', title: '培训内容', hidden: true,},
            {
                field: 'trainPlanType', title: '培训类型', width: '20%', align: 'center',
                formatter: function (trainPlanType) {
                    return obj1[trainPlanType];
                }
            },
            {field: 'trainTeacher', title: '培训讲师', width: '20%', align: 'center'},
            {
                field: 'createBy', title: '创建人', width: '15%', align: 'center',
                formatter: function (createBy) {
                    return parent.personList[createBy];
                }
            },
            {field: 'createDate', title: '创建时间', width: '18%', align: 'center'}
        ]],
        onLoadSuccess: function (data) {


        },

    });
    $("#primaryGrid").datagrid('getPager').pagination({
        pageList: [10, 20, 30, 40, 50], //可以设置每页记录条数的列表
        displayMsg: '当前显示从第{from}条到第{to}条，共{total}条记录',
        onSelectPage: function (pageNumber, pageSize) {
            var state = $('#primaryGrid').data('datagrid');
            var opts = state.options;
            page = opts.pageNumber = pageNumber;
            rows = opts.pageSize = pageSize;
            searchAllData(page, rows);
        }
    });
    //按条件查询(汇总)
    $("#searchAllBtn").on("click", function () {
        search=true;
        trainPlanTittle = $("#trainPlanTittle").textbox('getValue');
        //获取状态
        type = $("#type").combobox('getValue');
        if(type=='请选择'){
            type='';
        }
        searchAllData(page, rows);

    });
    //按条件查询

    var searchAllData = function (page, rows) {
        $("#primaryGrid").datagrid('reload', basePath + '/trainPlan/train-plan-list?trainPlanTittle=' + trainPlanTittle + '&orgId=' + orgId +'&type='+type + '&page=' + page + '&rows=' + rows);
        if(search){
            search=false;
            $("#primaryGrid").datagrid('getPager').pagination('select', 1);
        }
    }


    //配置窗口
    $("#editWin").window({
        title: '培训计划管理',
        closed: true,
        modal: true,
        minimizable: false,

        onClose: function () {
            $('#primaryGrid').datagrid('clearSelections');
            $("#primaryGrid").datagrid('reload');

            $("#editForm").form('reset');

            $("#editTrainPlanTittle").textbox('enable');
            $("#editTrainTeacher").textbox('enable');
            $("#editTrainPlanType").combobox('enable');
            editor.html('');
            editor.readonly(false);
        },
        onOpen: function () {
            $("#saveBtn").show();

            $("#editTrainPlanType").combobox({
                panelWidth: '150px',
                panelHeight: 'auto',
                idField: 'value',
                textField: 'label',
                value: '',
                loadMsg: '数据正在加载',
                url: '/service/tool/find-list-by-type?type=TRAIN_TYPE_DICT',
                mode: 'remote',
                method: 'GET'
            });


        }
    });

    //新增
    $("#addBtn").on('click', function () {
        $("#editWin").window("setTitle", "新增培训计划").window('open');

        $("#editWin").window('open');
        $("#flag").val('0');
        $("#id").val('999');
        $("#editTrainPlanTittle").textbox('enable');
        $("#editTrainTeacher").textbox('enable');
        $("#editTrainPlanType").combobox('enable');
    });


    //修改
    $("#editBtn").on("click", function () {
        //获取选择行
        var row = $('#primaryGrid').datagrid('getSelected');
        if (row) {
            row.orgId=orgId;
            var rows=[];
            rows.push(row);
            $.postJSON(basePath + "/trainPlan/train-boolean", JSON.stringify(rows), function (data) {

                if (data.code == 'isUsed') {
                    $('#primaryGrid').datagrid('clearSelections');
                    $.messager.alert("提示", "所选培训计划已经创建培训通知，不能修改！", "info");
                    return;
                } else {
                    $("#editWin").window("setTitle", "修改培训计划").window('open');

                    $("#editTrainPlanTittle").textbox('setValue', row.trainPlanTittle);

                    $.get('/service/tool/find-list-by-type?type=TRAIN_TYPE_DICT&value=' + row.trainPlanType, function (data) {
                        $("#editTrainPlanType").textbox('setValue', data[0].value);
                        $("#editTrainPlanType").textbox('setText', data[0].label);

                    });
                    $("#editTrainTeacher").textbox('setValue', row.trainTeacher);

                    if (row.trainPlanContent != null) {
                        //解码
                        var val = parent.utf8to16(parent.base64decode(row.trainPlanContent));
                        editor.html(val);
                    }

                    $("#id").val(row.trainPlanId);
                    $("#flag").val('1');
                }
            });

        }
        else {
            $('#primaryGrid').datagrid('clearSelections');
            $.messager.alert("提示", "请选择一行记录!", "info");

        }

    });
    //删除
    $("#delBtn").on('click', function () {
        flag = '2';
        var rows = $("#primaryGrid").datagrid('getSelections');
        if (rows == null || rows.length == 0 || !rows) {
            $('#primaryGrid').datagrid('clearSelections');
            $.messager.alert("提示", "请选择要删除的记录!", "info");
            return;
        }

        $.postJSON(basePath + "/trainPlan/train-boolean", JSON.stringify(rows), function (data) {

            if (data.code == 'isUsed') {
                $('#primaryGrid').datagrid('clearSelections');
                $.messager.alert("提示", "所选培训计划已经创建培训通知，不能删除！", "info");
                return
            } else {
                $.messager.confirm('提示', '确定要进行删除操作吗', function (r) {
                    if (r) {
                        $.postJSON(basePath + "/trainPlan/train-plan-del", JSON.stringify(rows), function (data) {

                            $('#primaryGrid').datagrid('reload');
                            rows.length = 0;
                            $("#primaryGrid").datagrid('clearSelections');

                        });
                    }
                })
            }
        });
    });
//查看
    $("#viewBtn").on('click', function () {
        var row = $("#primaryGrid").datagrid("getSelected");
        if (!row) {
            $('#primaryGrid').datagrid('clearSelections');
            $.messager.alert('提示', '请选择一行记录', 'info');
            return;
        }
        $("#editWin").window("setTitle", "查看培训计划").window('open');
        $("#saveBtn").hide();

        $("#editTrainPlanTittle").textbox('setValue', row.trainPlanTittle);
        $("#editTrainTeacher").textbox('setValue', row.trainTeacher);
        $("#editTrainPlanTittle").textbox('disable');
        $("#editTrainTeacher").textbox('disable');

        $.get('/service/tool/find-list-by-type?type=TRAIN_TYPE_DICT&value=' + row.trainPlanType, function (data) {
            $("#editTrainPlanType").combobox('setValue', data[0].value);
            $("#editTrainPlanType").combobox('setText', data[0].label);

        });
        $("#editTrainPlanType").combobox('disable');

        if (row.trainPlanContent != null) {
            //解码
            var val = parent.utf8to16(parent.base64decode(row.trainPlanContent));
            editor.html(val);
            editor.readonly();
        }

    });
    //保存数据
    $("#saveBtn").on('click', function () {


        id = $("#id").val();
        flag = $("#flag").val();
        var trainPlanTittle = $("#editTrainPlanTittle").textbox('getValue');
        var trainPlanType = $("#editTrainPlanType").combobox('getValue');
        var trainTeacher = $("#editTrainTeacher").textbox('getValue');


        editor.sync();
        var val = $("#editTrainPlanContent").val();
        var trainPlanContent = parent.base64encode(parent.utf16to8(val));


            if (trainPlanType == '') {
                $.messager.alert("提示", "请选择培训类型 !", "info");
                return
            }
            if (trainPlanTittle == '') {
                $.messager.alert("提示", "请填写培训主题!", "info");
                return
            }
            if (getRealLen(trainPlanTittle) > 64) {
                $.messager.alert("提示", "培训主题输入过长！", 'info');
                return;
            }
            if (trainTeacher == '') {
                $.messager.alert("提示", "请填写培训讲师!", "info");
                return
            }
            if (getRealLen(trainTeacher) > 64) {
                $.messager.alert("提示", "培训讲师输入过长！", 'info');
                return;
            }
            if (trainPlanContent == '') {
                $.messager.alert("提示", "请填写培训内容!", "info");
                return
            }

        trainPlanVo.orgId = parent.config.org_Id;
        trainPlanVo.trainPlanId = id;
        trainPlanVo.trainPlanTittle = trainPlanTittle;
        trainPlanVo.trainPlanType = trainPlanType;
        trainPlanVo.trainPlanContent = trainPlanContent;
        trainPlanVo.trainTeacher = trainTeacher;
        $.postJSON(basePath + "/trainPlan/merge", JSON.stringify(trainPlanVo), function (data) {

            if (data.data == "success") {
                $("#primaryGrid").datagrid('reload');
                $("#editForm").form('reset');
                $("#editWin").window('close');
            }
            else if(data.data=="hasName"){
                $.messager.alert("提示", "该名称已存在!", "info");
            }
            else {
                $.messager.alert('提示', '保存失败', 'info');
            }

        })

    });

    //取消
    $("#cancelBtn").on('click', function () {
        $("#editForm").form('reset');
        $("#editWin").window('close');
    });
    $("#clearBtn").on('click', function () {
        //清空题型ID
        $("#type").combobox('clear');//获取表格对象
        $("#type").combobox('setValue','请选择');//获取表格对象
        $("#trainPlanTittle").textbox('setValue', '');
        page='1';
    });

});

