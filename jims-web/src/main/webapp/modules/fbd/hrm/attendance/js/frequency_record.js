/*
 *排班管理
 * @author yangchen
 * @version 2016-08-18
 *!/*/
var basePath = "/service";
var freRecordId = "999";
var dg;
var d;
var flag;
var editIndex = undefined;
var page = '1';
var rows = '30';
var page1 = '1';
var rows1 = '30';
var frequencyRecordVo = {};
var orgId = parent.config.org_Id;
var id = '999';
var startDate = '';
var endDate = '';
var freDate = '';
var freItemId = '';
var freItemOrder = '';
var searchFlag = '';
var freFirstItemOrder = '';
var depts = [];
var treeDepts = [];
var userList = new Object();
var deptList = new Object();
var search = false;
var deptId = '';
var orgCount = 0;
var freTimeMonth = '';
var freTimeDay = '';
var userId = '';
var ruleOrderList=[];
var time='';
$(function () {

    $("#order").hide();

    $("#freItem").hide();
    $("#firstFreItem").show();

//初始化日期控件
/*
    $("#freTimeMonth").datebox({
                    onShowPanel: function () {//显示日趋选择对象后再触发弹出月份层的事件，初始化时没有生成月份层
                        span.trigger('click'); //触发click事件弹出月份层
                        if (!tds) setTimeout(function () {//延时触发获取月份对象，因为上面的事件触发和对象生成有时间间隔
                            tds = p.find('div.calendar-menu-month-inner td');
                            tds.click(function (e) {
                                e.stopPropagation(); //禁止冒泡执行easyui给月份绑定的事件
                                var year = /\d{4}/.exec(span.html())[0]//得到年份
                                    , month = parseInt($(this).attr('abbr'), 10); //月份，这里不需要+1
                                $('#freTimeMonth').datebox('hidePanel')//隐藏日期对象
                                    .datebox('setValue', year + '-' + month); //设置日期的值
                            });
                        }, 0);
                        yearIpt.unbind();//解绑年份输入框中任何事件
                    },
                    parser: function (s) {
                        if (!s) return new Date();
                        var arr = s.split('-');
                        return new Date(parseInt(arr[0], 10), parseInt(arr[1], 10) - 1, 1);
                    },
                    formatter: function (d) {
                        var a = parseInt(d.getMonth()) < parseInt('9') ? '0' + parseInt(d.getMonth() + 1) : d.getMonth() + 1;
                        return d.getFullYear() + '-' + a;
                    }
                });
                var pp = $('#freTimeMonth').datebox('panel'), //日期选择对象
                    tds = false, //日期选择对象中月份
                    yearIpt = pp.find('input.calendar-menu-year'),//年份输入框
                    span = pp.find('span.calendar-text'); //显示月份层的触发控件
*/

    //初始化日期控件
    $("#freTimeMonth1").datebox({
        onShowPanel: function () {//显示日趋选择对象后再触发弹出月份层的事件，初始化时没有生成月份层
            span.trigger('click'); //触发click事件弹出月份层
            if (!tds) setTimeout(function () {//延时触发获取月份对象，因为上面的事件触发和对象生成有时间间隔
                tds = p.find('div.calendar-menu-month-inner td');
                tds.click(function (e) {
                    e.stopPropagation(); //禁止冒泡执行easyui给月份绑定的事件
                    var year = /\d{4}/.exec(span.html())[0]//得到年份
                        , month = parseInt($(this).attr('abbr'), 10); //月份，这里不需要+1
                    $('#freTimeMonth1').datebox('hidePanel')//隐藏日期对象
                        .datebox('setValue', year + '-' + month); //设置日期的值
                });
            }, 0);
            yearIpt.unbind();//解绑年份输入框中任何事件
        },
        parser: function (s) {
            if (!s) return new Date();
            var arr = s.split('-');
            return new Date(parseInt(arr[0], 10), parseInt(arr[1], 10) - 1, 1);
        },
        formatter: function (d) {
            var a = parseInt(d.getMonth()) < parseInt('9') ? '0' + parseInt(d.getMonth() + 1) : d.getMonth() + 1;
            return d.getFullYear() + '-' + a;
        }
    });
    var p = $('#freTimeMonth1').datebox('panel'), //日期选择对象
        tds = false, //日期选择对象中月份
        yearIpt = p.find('input.calendar-menu-year'),//年份输入框
        span = p.find('span.calendar-text'); //显示月份层的触发控件

    //月份查询下拉框联动
    $("#TIME").combobox({
        onChange: function(){
            time = $("#TIME").combobox("getValue");
            var row1 = $("#dataGrid").datagrid('getSelected');
            $("#detailGrid").datagrid('reload', basePath + '/frequencyRecord/frequency-record-detail-all-list?userId=' + userId+'&freRecordHeadId='+row1.freRecordHeadId + '&orgId=' + orgId+'&time=' + time);
        }
    });
//排班头表
    $("#dataGrid").datagrid({
        //iconCls: 'icon-edit',//图标
        width: '100%',
        height: '100%',
        nowrap: false,
        striped: true,
        border: true,
        singleSelect: true,
        method: 'get',
        toolbar: '#tb1',
        url: basePath + '/frequencyRecord/frequency-head-list?userId=' + userId + '&freTimeMonth=' + freTimeMonth+ '&orgId=' + orgId,
        loadMsg: '数据正在加载中，请稍后.....',
        pagination: true,//分页控件
        collapsible: false,//是否可折叠的
        remoteSort: false,
        pageSize: 30,
        pageList: [10, 20, 30, 40, 50],
        //idField: 'freRecordId',
        rownumbers: true,//行号
        columns: [[
            {field: 'orgId', title: '', width: '10%', hidden: true},
            {field: 'freRecordHeadId', title: '', width: '10%', hidden: true},
            {field: 'personName', title: '排班人员', width: '15%', align: 'center',formatter : function (value, row, index) {
                if (value.length > 26) value = value.substr(0,24) + "...";
                return value;
            }},
            //{field: 'freItemId', title: '', width: '10%', hidden: true},
            /*{
                field: 'userId', title: '排班人员', width: '10%', align: 'center',
                formatter: function (userId) {
                    return parent.personList[userId];
                }
            },*/
            /*{
                field: 'deptId', title: '员工部门', width: '20%', align: 'center',
                formatter: function (deptId) {
                    $.ajaxSettings.async = false;
                    $.get(basePath + '/tool/find-dept-list?deptId=' + deptId, function (data) {
                        deptName = data[0].deptName;
                    });
                    return deptName
                }
            },*/
            {field: 'freRuleDes', title: '排班规则', width: '10%', align: 'center'},
            {field: 'startDate', title: '排班开始日期', width: '10%', align: 'center'},
            {field: 'endDate', title: '排班结束日期', width: '10%', align: 'center'},
            {field: 'sumDays', title: '总天数', width: '10%', align: 'center'},
            {field: 'createBy', title: '创建人', width:'7%', align: 'center'},
            {field: 'createDept', title: '创建科室编号', hidden:true},
            {field: 'createDate', title: '创建时间', width:'15%',align: 'center',formatter: formatDatebox}
           /* {field: 'updateBy', title: '更新人', width:'7%', align: 'center'},
            {field: 'updateDate', title: '更新时间', width:'15%', align: 'center'}*/
        ]],
        onLoadSuccess: function () {
            $.messager.progress('close');
            $("#dataGrid").datagrid('getPager').pagination({
                //pageSize: 30,
                pageList: [10, 20, 30, 40, 50], //可以设置每页记录条数的列表
                displayMsg: '当前显示从第{from}条到第{to}条，共{total}条记录',
                onSelectPage: function (pageNumber, pageSize) {
                    var state = $('#dataGrid').data('datagrid');
                    var opts = state.options;
                    page = opts.pageNumber = pageNumber;
                    rows = opts.pageSize = pageSize;
                    searchData1(page, rows);

                }
            });
        }
    });
//格式化时间：时分秒
    function formatDatebox(value) {
        if (value == null || value == '') {
            return '';
        }
        var dt;
        if (value instanceof Date) {
            dt = value;
        } else {
            dt = new Date(value);
        }
        return dt.format("yyyy-MM-dd"); //扩展的Date的format方法(上述插件实现)
    }

//按条件查询
    $("#searchBtn1").on("click", function () {
        search = true;
        searchData1(page, rows);
    });
    var searchData1=function(page, rows) {
        freTimeMonth = $("#freTimeMonth1").datebox('getValue');
        userId = $("#userId1").textbox('getValue');
        $("#dataGrid").datagrid('reload', basePath + '/frequencyRecord/frequency-head-list?userId=' + userId + '&freTimeMonth=' + freTimeMonth+ '&orgId=' + orgId + '&page=' + page + '&rows=' + rows);

        if (search) {
            search = false;
            $("#dataGrid").datagrid('getPager').pagination('select', 1);
        }
        $("#dataGrid").datagrid('clearSelections');

    };
    $("#clearBtn1").on('click', function () {
        $("#userId1").textbox('setValue', '');
        $("#freTimeMonth1").datebox('setValue', '');
        $("#freTimeDay1").datebox('setValue', '');
        page='1';
    });

    //配置查看窗口
    $("#newDialog").window({
        title: '人员排班查看',
        closed: true,
        modal: true,
        minimizable: false,
        width:940,
        height:660
        //fit:true
        /* onClose: function () {
         $("#dataGrid").datagrid('reload');

         $('#queTypeGrid').datagrid('clearSelections');
         $('#queTypeGrid').datagrid('clearChecked');
         },
         onOpen: function () {
         $("#selectQue").css('display','block');
         }*/
    });
    $("#viewBtn1").on("click", function () {
        var row=$('#dataGrid').datagrid('getSelected');
        if(row) {
            userId='';
            var freRecordHeadId = row.freRecordHeadId;
            $("#newDialog").window('open');
            $("#primaryGrid").datagrid({
                iconCls: 'icon-edit',//图标
                width: '100%',
                height: '91%',
                nowrap: false,
                striped: true,
                border: true,
                singleSelect: true,
                method: 'get',
                toolbar: '#tb',
                url: basePath + '/frequencyRecord/frequency-record-all-list?userId=' + userId + '&orgId=' + orgId+'&freRecordHeadId='+freRecordHeadId,
                loadMsg: '数据正在加载中，请稍后.....',
                pagination: true,//分页控件
                collapsible: false,//是否可折叠的
                remoteSort: false,
                pageSize: 30,
                pageList: [10, 20, 30, 40, 50],
                idField: 'freRecordId',
                rownumbers: true,//行号
                columns: [[
                    {field: 'orgId', title: '', width: '10%', hidden: true},
                    {field: 'freRecordId', title: '', width: '10%', hidden: true},
                    {field: 'freItemId', title: '', width: '10%', hidden: true},
                    {
                        field: 'userId', title: '人员编号', hidden: true
                        /*formatter: function (userId) {
                            return parent.personList[userId];
                        }*/
                    },
                    {field: 'personName', title: '人员姓名', width: '10%', align: 'center'},
                    {
                        field: 'deptId', title: '员工部门', width: '20%', align: 'center',
                        formatter: function (deptId) {
                            $.ajaxSettings.async = false;
                            $.get(basePath + '/tool/find-dept-list?deptId=' + deptId, function (data) {
                                deptName = data[0].deptName;
                            });
                            return deptName
                        }
                    },
                    {field: 'freRuleDes', title: '排班规则', width: '10%', align: 'center'},
                    {field: 'startDate', title: '排班开始日期', width: '10%', align: 'center'},
                    {field: 'endDate', title: '排班结束日期', width: '10%', align: 'center'},
                    {field: 'sumDays', title: '总天数', width: '10%', align: 'center'},
                    {
                        field: 'createBy', title: '创建人', width: '10%', align: 'center',
                        formatter: function (createBy) {
                            return parent.personList[createBy];
                        }
                    },
                    {field: 'createDate', title: '创建时间', width: '15%', align: 'center'}
                ]],
                onLoadSuccess: function () {
                    $.messager.progress('close');
                    $("#primaryGrid").datagrid('getPager').pagination({
                        //pageSize: 30,
                        pageList: [10, 20, 30, 40, 50], //可以设置每页记录条数的列表
                        displayMsg: '当前显示从第{from}条到第{to}条，共{total}条记录',
                        onSelectPage: function (pageNumber, pageSize) {
                            var state = $('#primaryGrid').data('datagrid');
                            var opts = state.options;
                            page1 = opts.pageNumber = pageNumber;
                            rows1 = opts.pageSize = pageSize;
                            searchData(page1, rows1);

                        }
                    });
                }
            });
        }
        else{
            $.messager.alert("提示", "请选择一条数据！","info");
        }
    });




    //按条件查询
    $("#searchBtn").on("click", function () {
        /*$.messager.progress({
            title: '提示！',
            msg:  '数据处理中，请稍候...',
            text: '加载中.......'
        });*/
           search = true;
            searchData(page1, rows1);
    });
    var searchData=function(page1, rows1) {
        var row=$('#dataGrid').datagrid('getSelected');
        var freRecordHeadId = row.freRecordHeadId;
        //freTimeMonth = $("#freTimeMonth").datebox('getValue');
        userId = $("#userId").textbox('getValue');
                $("#primaryGrid").datagrid('reload', basePath + '/frequencyRecord/frequency-record-all-list?' +
                    'userId=' + userId + '&orgId=' + orgId +'&freRecordHeadId='+freRecordHeadId+ '&page=' + page1 + '&rows=' + rows1);

                if (search) {
                    search = false;
                    $("#primaryGrid").datagrid('getPager').pagination('select', 1);
                }
        //$.messager.progress('close');
                $("#primaryGrid").datagrid('clearSelections');

    };


    //配置详情窗口
    $("#detailWin").window({
        title: '排班明细',
        closed: true,
        modal: true,
        minimizable: false,
        onClose: function () {
            $("#saveDetailBtn").show();
            $('#primaryGrid').datagrid('clearSelections');
            $("#primaryGrid").datagrid('reload');
            $('#detailGrid').datagrid('loadData', { total: 0, rows: [] });
        },
        onOpen: function () {
        }
    });


    //配置窗口
    $("#editWin").window({
        title: '排班管理',
        closed: true,
        modal: true,
        minimizable: false,
        onClose: function () {

            $("#editAllDate").show();
            $("#editOneDate").hide();

            $("#firstFreItem").show();
            $("#freItem").hide();

            $("#editFreRule").combobox('enable');
            $("#editUserName").textbox('enable');
            $("#editFreRule").combobox('clear');
            $("#editUserName").textbox('enable');
            $("#chooseUser").show();
            //$('#primaryGrid').datagrid('clearSelections');
            //$("#primaryGrid").datagrid('reload');
            //$('#detailGrid').datagrid('loadData', { total: 0, rows: [] });

            $("#editForm").form('reset');
            arrPerson = [];
        },
        onOpen: function () {
            $("#editFreRule").combobox({
                panelWidth: '200px',
                panelHeight: 'auto',
                idField: 'freRuleId',
                textField: 'freRuleDes',
                value: '--请选择--',
                loadMsg: '数据正在加载',
                url: '/service/frequencyRule/frequency-rule-all-list?orgId=' + orgId,
                mode: 'remote',
                method: 'GET',
                onChange: function () {
                    $("#editFreFirstItem").combogrid('setValue', '--请选择--');
                    freRuleId = $("#editFreRule").combobox('getValue');
                    $.get(basePath + '/frequencyRule/frequency-rule-data-all-list?freRuleId=' + freRuleId + '&orgId=' + orgId,
                        function (data) {
                            $("#editFreItem").combogrid("enable");

                            $("#editFreItem").combogrid("grid").datagrid("loadData", data);
                        });
                }
            });

        }
    });

    /**
     * linkbutton方法扩展
     * @param {Object} jq
     */
    $.extend($.fn.linkbutton.methods, {
        /**
         * 激活选项（覆盖重写）
         * @param {Object} jq
         */
        enable: function(jq){
            return jq.each(function(){
                var state = $.data(this, 'linkbutton');
                if ($(this).hasClass('l-btn-disabled')) {
                    var itemData = state._eventsStore;
                    //恢复超链接
                    if (itemData.href) {
                        $(this).attr("href", itemData.href);
                    }
                    //回复点击事件
                    if (itemData.onclicks) {
                        for (var j = 0; j < itemData.onclicks.length; j++) {
                            $(this).bind('click', itemData.onclicks[j]);
                        }
                    }
                    //设置target为null，清空存储的事件处理程序
                    itemData.target = null;
                    itemData.onclicks = [];
                    $(this).removeClass('l-btn-disabled');
                }
            });
        },
        /**
         * 禁用选项（覆盖重写）
         * @param {Object} jq
         */
        disable: function(jq){
            return jq.each(function(){
                var state = $.data(this, 'linkbutton');
                if (!state._eventsStore)
                    state._eventsStore = {};
                if (!$(this).hasClass('l-btn-disabled')) {
                    var eventsStore = {};
                    eventsStore.target = this;
                    eventsStore.onclicks = [];
                    //处理超链接
                    var strHref = $(this).attr("href");
                    if (strHref) {
                        eventsStore.href = strHref;
                        $(this).attr("href", "javascript:void(0)");
                    }
                    //处理直接耦合绑定到onclick属性上的事件
                    var onclickStr = $(this).attr("onclick");
                    if (onclickStr && onclickStr != "") {
                        eventsStore.onclicks[eventsStore.onclicks.length] = new Function(onclickStr);
                        $(this).attr("onclick", "");
                    }
                    //处理使用jquery绑定的事件
                    var eventDatas = $(this).data("events") || $._data(this, 'events');
                    if (eventDatas["click"]) {
                        var eventData = eventDatas["click"];
                        for (var i = 0; i < eventData.length; i++) {
                            if (eventData[i].namespace != "menu") {
                                eventsStore.onclicks[eventsStore.onclicks.length] = eventData[i]["handler"];
                                $(this).unbind('click', eventData[i]["handler"]);
                                i--;
                            }
                        }
                    }
                    state._eventsStore = eventsStore;
                    $(this).addClass('l-btn-disabled');
                }
            });
        }
    });
    //新增
    $("#addBtn").on('click', function () {
        $("#editWin").window('open');
        $("#flag").val('0');
        $("#id").val('999');
        loadtree();
        $("#editOneDate").hide();
        $("#editAllDate").show();
        $("#editForm").form('reset');
        $("#saveBtn").linkbutton("enable");
        //初始班次
        $("#editFreItem").combogrid({
            panelWidth: '200px',
            panelHeight: '200px',
            idField: 'freItemOrder',
            textField: 'freItemDes',
            fitColumns: true,
            columns: [[
                {field: 'freItemId', title: '', align: 'center', hidden: true, width: 100},
                {field: 'freItemOrder', title: '顺序', align: 'center', width: 200},
                {field: 'freItemDes', title: '班次描述', align: 'center', width: 200},

            ]],
            onChange: function () {
                $("#editFreItemOrder").textbox('clear');
                freItemOrder = $("#editFreItem").combobox('getValue');
                $("#editFreItemOrder").textbox('setValue', freItemOrder);
            }
        });
    });
    var editIndex;
    var stopEdit = function () {
        if (editIndex || editIndex == 0) {
            $("#detailGrid").datagrid('endEdit', editIndex);
            editIndex = undefined;
        }
    };
  /*  /!* 删除*!/
    $("#delBtn1").on('click', function () {
        //flag='1';
        var row = $("#dataGrid").datagrid('getSelected');

        if (row) {
            //frequencyRuleVo.freRuleId = row.freRuleId;
            //freRuleId = row.freRuleId;
            //判断是否在排班管理中被占用，若被占用不能删除。
           /!* $.post(basePath + "/frequencyRule/if-occupy?freRuleId="+freRuleId+"&orgId="+orgId,
                function (data) {
                    if (data.code == "yes") {
                        $.messager.alert('提示', '该排班规则已在排班管理中被使用，请先删除排班管理中的对应数据！', 'info');
                        return;
                    }*!/
                    $.messager.confirm('提示', '确定要进行删除操作吗', function (r) {
                        if (r) {
                            $.postJSON(basePath + "/frequencyRule/frequency-head-del", JSON.stringify(row), function (data) {
                                $('#dataGrid').datagrid('reload');
                                row.length = 0;
                                $('#dataGrid').datagrid('clearSelections');

                            });
                        }
                    });
                //});
        } else {
            $.messager.alert("提示", "请选择要删除的排班数据");
        }

    });*/
    //删除
    $("#delBtn1").on("click", function () {
        var row = $("#dataGrid").datagrid('getSelected');
        if (row == null||!row) {
            $.messager.alert("提示", "请选择要删除的排班数据!","info");
            return;
        }

        $.messager.confirm("提示", "确认删除吗?", function (r) {
            if (r) {
                $.postJSON(basePath + "/frequencyRecord/frequency-head-del" ,row.freRecordHeadId, function (data) {
                    $("#dataGrid").datagrid('reload');
                    $('#dataGrid').datagrid('clearSelections');
                });
            }
        })

    });
    //修改
    $("#editBtn").on("click", function () {

        //获取选择行
        var row = $('#primaryGrid').datagrid('getSelected');
        var row1 = $("#dataGrid").datagrid('getSelected');
        if (row) {
            freRuleId=row.freRuleId;

            $.get(basePath + '/frequencyRule/frequency-rule-data-all-list?freRuleId=' + freRuleId + '&orgId=' + orgId,
                function (data) {
                $.each(data, function (index, item) {
                    var obj={};
                    obj.value=item.value;
                    obj.label=item.label;
                    ruleOrderList.push(obj);

                });
            });

            var unitFormatter= function (value) {
                for (var i = 0; i < ruleOrderList.length; i++) {
                    if (ruleOrderList[i].value == value) {
                        return ruleOrderList[i].label;
                    }
                }
            };
            $("#detailWin").window('open');
            userId = row.userId;
            $("#saveDetailBtn").show();
            $.get(basePath + '/tool/find-person-list?userId=' + userId+ '&orgId=' + orgId, function (data) {
                var userName = data[0].userName;
                $("#recordUserName").textbox('setValue', userName);

            });
            deptId = row.deptId;
            $.get(basePath + '/tool/find-dept-list?deptId=' + deptId+ '&orgId=' + orgId, function (data) {
                var deptName = data[0].deptName;
                $("#recordDeptName").textbox('setValue', deptName);
            });
            $("#recordFreRule").textbox('setValue', row.freRuleId);
            $("#recordFreRule").textbox('setText', row.freRuleDes);

            $("#detailGrid").datagrid({
                iconCls: 'icon-edit',//图标
                width: '100%',
                height: '100%',
                nowrap: false,
                striped: true,
                border: true,
                method: 'get',
                toolbar: '#db',
                loadMsg: '数据正在加载中，请稍后.....',
                collapsible: false,//是否可折叠的
                url: basePath + '/frequencyRecord/frequency-record-detail-all-list?userId=' + userId+'&freRecordHeadId='+row1.freRecordHeadId + '&orgId=' + orgId+'&time=' + time,
                remoteSort: false,
                singleSelect: true,//是否单选
                rownumbers: true,//行号
                pageSize: 50,
                columns: [[
                    {field: 'orgId', title: '', width: '10%', hidden: true},
                    {field: 'freRecordId', title: '', width: '10%', hidden: true},
                    {field: 'freItemId', title: '', width: '10%', hidden: true},
                    {field: 'freDate', title: '排班日期', width: '30%', align: 'center'},
                    {
                        field: 'freItemOrder', title: '班次', width: '30%', align: 'center',
                        formatter: unitFormatter,
                        editor: {
                            type: 'combogrid',
                            options: {
                                panelWidth: '200px',
                                panelHeight: '200px',
                                idField: 'value',
                                textField: 'label',
                                fitColumns: true,
                                data: ruleOrderList,
                                columns: [[
                                    {field: 'value', title: '顺序', align: 'center', width: 200},
                                    {field: 'label', title: '班次描述', align: 'center', width: 200},

                                ]]
                            }
                        }
                    },
                    {
                        field: 'createBy', title: '创建人', width: '20%', align: 'center',
                        formatter: function (createBy) {
                            return parent.personList[createBy];
                        }
                    },
                    {field: 'createDate', title: '创建时间', width: '20%', align: 'center', formatter: formatDatebox}
                ]],
                onClickRow: function (index, row) {
                    stopEdit();
                    $(this).datagrid('beginEdit', index);
                    editIndex = index;
                }

            });
        } else {
            $.messager.alert("提示", "请选择一条记录!", "info");

            return;

        }
    });
    //删除
    $("#delBtn").on('click', function () {
        flag = '0';
        var row = $("#primaryGrid").datagrid('getSelected');
        if (row) {
            frequencyRecordVo.orgId = parent.config.org_Id;
            frequencyRecordVo.startDate = row.startDate;
            frequencyRecordVo.endDate = row.endDate;
            frequencyRecordVo.userId = row.userId;
            $.messager.confirm('提示', '确定要进行删除操作吗', function (r) {
                if (r) {
                    $.postJSON(basePath + "/frequencyRecord/frequency-record-del", JSON.stringify(frequencyRecordVo), function (data) {
                        //$.messager.alert("提示", "删除成功!", "info");

                        $('#primaryGrid').datagrid('clearSelections');
                        $("#primaryGrid").datagrid('reload');
                        row.length = 0;
                    });
                }
            })
        } else {
            $.messager.alert("提示", "请选择一条记录!", "info");
            return;
        }
    });
    // 查看
    $("#viewBtn").on("click", function () {
        $("#TIME").combobox("setValue","");
        var row = $("#primaryGrid").datagrid('getSelected');
        var row1 = $("#dataGrid").datagrid('getSelected');
        if (row) {
            $("#detailWin").window('open');
            $.messager.progress({
                title: '提示！',
                msg:  '数据处理中，请稍候...',
                text: '加载中.......'
            });
            userId = row.userId;
            $("#saveDetailBtn").hide();
            $.get(basePath + '/tool/find-person-list?userId=' + userId+ '&orgId=' + orgId, function (data) {
                var userName = data[0].userName;
                $("#recordUserName").textbox('setValue', userName);

            });
            deptId = row.deptId;
            $.get(basePath + '/tool/find-dept-list?deptId=' + deptId+ '&orgId=' + orgId, function (data) {
                var deptName = data[0].deptName;
                $("#recordDeptName").textbox('setValue', deptName);
            });

            $("#recordFreRule").textbox('setValue', row.freRuleDes);
            $("#detailGrid").datagrid({
                iconCls: 'icon-edit',//图标
                width: '100%',
                height: '100%',
                nowrap: false,
                striped: true,
                border: true,
                method: 'get',
                toolbar: '#db',
                loadMsg: '数据正在加载中，请稍后.....',
                collapsible: false,//是否可折叠的
                url: basePath + '/frequencyRecord/frequency-record-detail-all-list?userId=' + userId+'&freRecordHeadId='+row1.freRecordHeadId + '&orgId=' + orgId+'&time=' + time,
                remoteSort: false,
                singleSelect: true,//是否单选
                rownumbers: true,//行号
                pageSize: 50,
                columns: [[
                    {field: 'orgId', title: '', width: '10%', hidden: true},
                    {field: 'freRecordId', title: '', width: '10%', hidden: true},
                    {field: 'freItemId', title: '', width: '10%', hidden: true},
                    {field: 'freDate', title: '排班日期', width: '30%', align: 'center'},
                    {field: 'freItemDes', title: '班次', width: '30%', align: 'center'},
                    {
                        field: 'createBy', title: '创建人', width: '20%', align: 'center',
                        formatter: function (createBy) {
                            return parent.personList[createBy];
                        }
                    },
                    {field: 'createDate', title: '创建时间', width: '20%', align: 'center', formatter: formatDatebox}
                ]],

            onLoadSuccess: function () {
                $.messager.progress('close');

            }
            });
        } else {
            $.messager.alert("提示", "请选择一条记录!", "info");

            return;

        }
    });
    //保存新增数据
    $("#saveBtn").on('click', function () {
        startDate = $("#editStartDate").datebox('getValue');
        endDate = $("#editEndDate").datebox('getValue');
        freDate = $("#editDate").datebox('getValue');
        id = $("#id").val();
        flag = $("#flag").val();
        freItemOrder = $("#editFreItemOrder").textbox('getValue');
        freRuleId = $("#editFreRule").combobox('getValue');
        userId = $("#editUserId").val();
        deptId = $("#editDeptId").val();


        var date1 = startDate.split("-");
        var date2 = endDate.split("-");
        var start = new Date(date1[0], date1[1], date1[2]);
        var end = new Date(date2[0], date2[1], date2[2]);
        if (flag == '0') {
            if (userId == '') {
                $.messager.alert("提示", "请选择排班人员!", "info");
                return
            }
            if (start >= end) {
                $.messager.alert("提示", "结束日期必须大于开始日期!", "info");
                return
            }
            var a = end-start;
            if (a >6*31*24*60*60*1000 ) {   //6个月转化成毫秒计算比较
                $.messager.alert("提示", "结束日期与开始日期跨度不能大于6个月!", "info");
                return
            }

            if (startDate == '') {
                $.messager.alert("提示", "请选择排班开始日期!", "info");
                return
            }
            if (endDate == '') {
                $.messager.alert("提示", "请选择排班结束日期!", "info");
                return
            }
            if (freRuleId == '') {
                $.messager.alert("提示", "请选择排班规则!", "info");
                return
            }
            if (freItemOrder == '') {
                $.messager.alert("提示", "请选择初始班次!", "info");
                return
            }

        }
        frequencyRecordVo.orgId = parent.config.org_Id;
        frequencyRecordVo.startDate = startDate;
        frequencyRecordVo.endDate = endDate;
        frequencyRecordVo.freDate = freDate;
        frequencyRecordVo.freItemId = freItemId;
        frequencyRecordVo.freItemOrder = freItemOrder;
        frequencyRecordVo.freFirstItemOrder = freItemOrder;
        frequencyRecordVo.freRuleId = freRuleId;
        frequencyRecordVo.id = id;
        frequencyRecordVo.userId = userId;
        frequencyRecordVo.deptId = deptId;
        $("#saveBtn").linkbutton("disable");
        $.messager.progress({
            title: '提示！',
            msg:  '数据处理中，请稍候...',
            text: '加载中.......'
        });
        $.postJSON(basePath + "/frequencyRecord/merge", JSON.stringify(frequencyRecordVo), function (data) {
            //$.messager.alert("提示", "保存成功!", "info");
            $.messager.progress('close');
            $("#editWin").window('close');
            $("#editForm").form('reset');
            $('#dataGrid').datagrid('clearSelections');
            $("#dataGrid").datagrid('reload');
        })
    });

    //保存修改数据
    $("#saveDetailBtn").on('click', function () {

        id = '';
        freRuleId = $("#recordFreRule").textbox('getValue');

        var rows= $("#detailGrid").datagrid("getRows");
        for (var i=0;i<rows.length;i++) {
            $("#detailGrid").datagrid('endEdit', i)
        }
        var insertData = $("#detailGrid").datagrid("getChanges", "inserted");
        var updateData = $("#detailGrid").datagrid("getChanges", "updated");

        frequencyRecordVo.inserted = insertData;
        frequencyRecordVo.updated = updateData;
        frequencyRecordVo.freRuleId = freRuleId;
        frequencyRecordVo.orgId = parent.config.org_Id;
        frequencyRecordVo.id = id;

        $.postJSON(basePath + "/frequencyRecord/merge", JSON.stringify(frequencyRecordVo), function (data) {
            $.messager.alert("提示", "保存成功!", "info");
            $("#detailGrid").datagrid('reload');
            //$("#detailWin").window('close');
        })
    });

    //取消
    $("#cancelBtn").on('click', function () {
        $("#editForm").form('reset');
        $("#editWin").window('close');
    });
    $("#clearBtn").on('click', function () {
        $("#userId").textbox('setValue', '');
        $("#freTimeMonth").datebox('setValue', '');
        $("#freTimeDay").datebox('setValue', '');
        page1='1';
    });
});



//格式化时间：时分秒
function formatDatebox(value) {
    if (value == null || value == '') {
        return '';
    }
    var dt;
    if (value instanceof Date) {
        dt = value;
    } else {

        dt = new Date(value);

    }

    return dt.format("yyyy-MM-dd"); //扩展的Date的format方法(上述插件实现)
}