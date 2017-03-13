/**
 * 我的工资
 * @author
 * @version 2016-08-22
 */
var page='1';
var rows='30';
var orgId= parent.config.org_Id;
var pid= parent.config.persion_Id;
var personName = '';
var deptId = '';
var typeId = '';
var adjustMoney ='';
var adjustReason = '';
var salaryMonth='';
var salaryTmp = {};
var search=false;
$(function () {
    /**
     * 数据列表
     */
    $("#dataGrid").datagrid({
        iconCls: 'icon-edit',//图标
        width: '100%',
        height: '100%',
        nowrap: false,
        fit: true,
        fitColumns: true,
        toolbar: '#tb',
        method: 'GET',
        striped: true,
        border: true,
        pagination: true,//分页控件
        pageSize: 30,
        pageList: [10, 20, 30, 40, 50],//可以设置每页记录条数的列表
        loadMsg: '数据正在加载中，请稍后.....',
        collapsible: false,//是否可折叠的
        url: basePath + '/salary-tmp/getmypay?salaryMonth='+salaryMonth+'&orgId='+orgId+'&pid='+pid,
        remoteSort: false,
        //idField: 'tmpId',
        singleSelect: true,//是否单选
        rownumbers: true,//行号
        columns: [[
            /*{field: 'ck', title: '', checkbox: true},*/
            {field: 'orgId', title: '组织机构编码', hidden: true},
            {field: 'tmpId', title: '工资编号', hidden: true},
            {field: 'personId', title: '员工编号', hidden: true},
            {field: 'personName', title: '员工姓名', width:'7%', align: 'center'},
            {field: 'salaryBefore', title: '应发工资', width:'9%', align: 'center',formatter:function(value,row,index){
                var value = row.salaryBefore;
                if(value!=null)
                    return '￥'+(parseFloat(value).toFixed(2)+ '').replace(/\d{1,3}(?=(\d{3})+(\.\d*)?$)/g, '$&,');
            }},
            {field: 'salaryAfter', title: '实发工资', width:'9%', align: 'center',formatter:function(value,row,index){
                var value = row.salaryAfter;
                if(value!=null)
                    return '￥'+(parseFloat(value).toFixed(2)+ '').replace(/\d{1,3}(?=(\d{3})+(\.\d*)?$)/g, '$&,');
            }},
            {field: 'salaryMonth', title: '工资月份', width:'7%', align: 'center'},
            {field: 'orgName', title: '组织机构', width:'10%', align: 'center'},


            {field: 'deptId', title: '科室', width:'11%', align: 'center'},
            {field: 'typeId', title: '工资级别Id', hidden: true},
            {field: 'typeName', title: '工资级别', width:'8%', align: 'center'},
            {field: 'state', title: '工资状态', width:'6%', align: 'center',
                formatter: function (value, row, index) {
                    if (value == "0") {
                        return "正常";
                    }
                    if (value == "1") {
                        return "停发";
                    }
                }
            },
            {field: 'socialSecuritybase', title: '社保基数', width:'8%', align: 'center',formatter:function(value,row,index){
                var value = row.socialSecuritybase;
                if(value!=null)
                    return '￥'+(parseFloat(value).toFixed(2)+ '').replace(/\d{1,3}(?=(\d{3})+(\.\d*)?$)/g, '$&,');
            }},
            {field: 'socialSecuritycompany', title: '单位代缴社保金额', width:'12%', align: 'center',formatter:function(value,row,index){
                var value = row.socialSecuritycompany;
                if(value!=null)
                    return '￥'+(parseFloat(value).toFixed(2)+ '').replace(/\d{1,3}(?=(\d{3})+(\.\d*)?$)/g, '$&,');
            }},

            {field: 'socialSecuritypersonal', title: '代扣社保', width:'8%', align: 'center',formatter:function(value,row,index){
                var value = row.socialSecuritypersonal;
                if(value!=null)
                    return '￥'+(parseFloat(value).toFixed(2)+ '').replace(/\d{1,3}(?=(\d{3})+(\.\d*)?$)/g, '$&,');
            }},
            {field: 'salaryTax', title: '代扣个税', width:'8%', align: 'center',formatter:function(value,row,index){
                var value = row.salaryTax;
                if(value!=null)
                    return '￥'+(parseFloat(value).toFixed(2)+ '').replace(/\d{1,3}(?=(\d{3})+(\.\d*)?$)/g, '$&,');
            }},
            {field: 'adjustMoney', title: '手工调整金额', width:'9%', align: 'center',formatter:function(value,row,index){
                var value = row.adjustMoney;
                if(value!=null)
                    return '￥'+(parseFloat(value).toFixed(2)+ '').replace(/\d{1,3}(?=(\d{3})+(\.\d*)?$)/g, '$&,');
            }},

            {field: 'adjustReason', title: '调整原因', width:'11%', align: 'center'},
            {field: 'adjustBy', title: '调整人', width:'7%', align: 'center'},
            {field: 'adjustDate', title: '调整时间', width:'15%', align: 'center',formatter: formatDatebox},
            /*{field: 'createDate', title: '创建时间', width:'15%',align: 'center'},*/
            {field: 'confirmMan', title: '下发人', width:'7%', align: 'center'},
            {field: 'comfirmDate', title: '下发时间', width:'15%', align: 'center',formatter: formatDatebox}
        ]],
        onLoadSuccess:function(data){
            $('#tb').css('display','block');
            $("#newDialog").css('display','block');
            $("#dataGrid").datagrid('getPager').pagination({
                pageList: [10, 20, 30, 40, 50], //可以设置每页记录条数的列表
                displayMsg: '当前显示从第{from}条到第{to}条，共{total}条记录',
                onSelectPage: function (pageNumber, pageSize) {
                    var state = $('#dataGrid').data('datagrid');
                    var opts = state.options;
                    page=opts.pageNumber = pageNumber;
                    rows=opts.pageSize = pageSize;
                    searchData(page,rows);
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
        return dt.format("yyyy-MM-dd hh:mm:ss"); //扩展的Date的format方法(上述插件实现)
    }
    //查询条件：月份
        /*$("#SALARY_MONTH").datebox({
            onShowPanel: function () {//显示日趋选择对象后再触发弹出月份层的事件，初始化时没有生成月份层
                span.trigger('click'); //触发click事件弹出月份层
                if (!tds) setTimeout(function () {//延时触发获取月份对象，因为上面的事件触发和对象生成有时间间隔
                    tds = p.find('div.calendar-menu-month-inner td');
                    tds.click(function (e) {
                        e.stopPropagation(); //禁止冒泡执行easyui给月份绑定的事件
                        var year = /\d{4}/.exec(span.html())[0]//得到年份
                          var month = parseInt($(this).attr('abbr'), 10) + 1; //月份
                        var month1 = (month-1).toString();
                        if(month1<10) {
                            month1 = '0' + month1;
                        }
                        $("#SALARY_MONTH").datebox('hidePanel')//隐藏日期对象
                            .datebox('setValue', year + '-' + month); //设置日期的值
                        //alert(year + '-' + month);
                        $("#SALARY_MONTH1").val(year + '-' + month1);
                        //var a = $("#SALARY_MONTH1").val(); //隐藏的输入框接受月份数据用于计算
                        //alert(a);

                    });;
                }, 50)
            },
            parser: function (s) {//配置parser，返回选择的日期
                if (!s) return new Date();
                var arr = s.split('-');
                return new Date(parseInt(arr[0], 10), parseInt(arr[1], 10) - 1, 1);
            },
            formatter: function (d) {
                return d.getFullYear() + '-' + d.getMonth();
            }//配置formatter，只返回年月
        });
    var p = $("#SALARY_MONTH").datebox('panel'), //日期选择对象
        tds = false, //日期选择对象中月份
        span = p.find('span.calendar-text'); //显示月份层的触发控件
*/
    /*$('#SALARY_MONTH').datebox({
        onShowPanel: function () {//显示日趋选择对象后再触发弹出月份层的事件，初始化时没有生成月份层
            span.trigger('click'); //触发click事件弹出月份层
            if (!tds) setTimeout(function () {//延时触发获取月份对象，因为上面的事件触发和对象生成有时间间隔
                tds = p.find('div.calendar-menu-month-inner td');
                tds.click(function (e) {
                    e.stopPropagation(); //禁止冒泡执行easyui给月份绑定的事件
                    var year = /\d{4}/.exec(span.html())[0]//得到年份
                    var month = parseInt($(this).attr('abbr'), 10); //月份
                    var month1 =month.toString();
                    if(month1<10) {
                        month1 = '0' + month1;
                    }
                    $("#SALARY_MONTH").datebox('hidePanel')//隐藏日期对象
                        .datebox('setValue', year + '-' + month); //设置日期的值
                    //alert(year + '-' + month);
                    $("#SALARY_MONTH1").val(year + '-' + month1);
                });
            }, 0);
            span.unbind();
            yearIpt.unbind();//解绑年份输入框中任何事件
        },
        parser: function (s) {
            if (!s) return new Date();
            var arr = s.split('-');
            return new Date(parseInt(arr[0], 10), parseInt(arr[1], 10) - 1, 1);
        },
        formatter: function (d) { return d.getFullYear() + '-' + (d.getMonth() + 1);/!*getMonth返回的是0开始的，忘记了。。已修正*!/ }
    });
    var p = $('#SALARY_MONTH').datebox('panel'), //日期选择对象
        tds = false, //日期选择对象中月份
        next=$('#SALARY_MONTH .calendar-prevyear');
    yearIpt = p.find('input.calendar-menu-year'),//年份输入框
        span = p.find('span.calendar-text'); //显示月份层的触发控件
*/
    var loadDept = function () {
        var depts = [];
        var treeDepts = [];
        var loadPromise = $.get("/service/dept-dict/list?orgId=" + orgId, function (data) {
            $.each(data, function (index, item) {
                var obj = {};
                obj.text = item.deptName;
                obj.id = item.id;
                obj.deptCode = item.deptCode;
                obj.deptPropertity = item.deptPropertity;
                obj.parentId = item.parentId;
                obj.children = [];
                depts.push(obj);
            });
        });

        loadPromise.done(function () {
            for (var i = 0; i < depts.length; i++) {
                for (var j = 0; j < depts.length; j++) {
                    if (depts[i].id == depts[j].parentId) {
                        depts[i].children.push(depts[j]);
                    }
                }
                if (depts[i].children.length > 0 && !depts[i].parentId) {
                    treeDepts.push(depts[i]);
                }
                if (!depts[i].parentId && depts[i].children <= 0) {
                    treeDepts.push(depts[i])
                }
            }
            $("#DEPT_ID").combotree('loadData', treeDepts);
        })
    };

    loadDept();

    /**
     * 查询条件搜索
     */
    $("#searchBtn").on("click", function () {
        /*var a = $("#SALARY_MONTH1").val();
        if(a==null||a==''||a.length==0){
            $.messager.alert("系统提示", "请选择月份！",'error');
            return;
        }*/
        search=true;
        searchData(page,rows);
    });

    var searchData= function (page,rows){
        //获取数据
        //salaryMonth = $("#SALARY_MONTH").val();
        salaryMonth = $("#SALARY_MONTH").val();
        if(salaryMonth==null||salaryMonth==''||salaryMonth.length==0){
            $.messager.alert("系统提示", "请选择月份！",'error');
            return;
        }
        $.messager.progress({
            title: '提示！',
            msg:  '数据加载中，请稍候...',
            text: '加载中.......'
        });
            /*$.get(basePath + '/salary-tmp/getmypay?salaryMonth='+salaryMonth+'&orgId='+orgId+'&pid='+pid, function (data) {
                $("#dataGrid").datagrid('loadData', data);*/
                $("#dataGrid").datagrid('reload', basePath + '/salary-tmp/getmypay?salaryMonth='+salaryMonth+'&orgId='+orgId+'&pid='+pid);
                if(search){
                    search=false;
                    $("#dataGrid").datagrid('getPager').pagination('select', 1);
                }
                $("#dataGrid").datagrid('clearSelections');
                $.messager.progress('close');
                var data=$('#dataGrid').datagrid('getData');
                /*if(data.total == 0){
                    $.messager.alert("系统提示", "没有该月工资数据！",'error');

                }*/
    };


    /**
     * 历史信息
     */
    $("#editBtn").on("click", function () {
        var row = $("#dataGrid").datagrid("getSelected");
        if(!row){
            $.messager.alert("提示","请选择一条数据!",'info');
            return;
        }
        var personId = row.personId;
        var url = basePath + '/salary-tmp/salary-history-all?personId='+personId+'&page='+page+'&rows='+rows ;
        $("#dataGrid").datagrid("reload", url);
        $("#dataGrid").datagrid('getPager').pagination({
            pageList: [10, 20, 30, 40, 50], //可以设置每页记录条数的列表
            displayMsg: '当前显示从第{from}条到第{to}条，共{total}条记录',
            onSelectPage: function (pageNumber, pageSize) {
                var state = $('#dataGrid').data('datagrid');
                var opts = state.options;
                page=opts.pageNumber = pageNumber;
                rows=opts.pageSize = pageSize;
                searchData1(page,rows);

            }
        });
        var searchData1= function (page,rows){

            $.get(basePath + '/salary-tmp/salary-history-all?personId='+personId+'&page='+page+'&rows='+rows, function (data) {
                $("#dataGrid").datagrid('loadData', data);
                var data=$('#dataGrid').datagrid('getData');
            });
        }
    });

    //配置工资详单窗口
    $("#newDialog").window({
        title: '工资详单',
        closed: true,
        modal: true,
        minimizable: false,
        width:540,
        height:600
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
    /**
     * 工资详单
     */
    $("#infoBtn").on("click", function () {
        //获取选择行
        var row=$('#dataGrid').datagrid('getSelected');
        if(row){
            $("#newDialog").window('open');
            var personId = row.personId;
            typeId = row.typeId;
            orgId = row.orgId;
            salaryMonth = row.salaryMonth;
            //详单数据
            $("#dataGrid1").datagrid({
                iconCls: 'icon-edit',//图标
                width: '100%',
                height: '100%',
                nowrap: false,
                striped: true,
                border: true,
                method: 'get',
                toolbar: '#tb1',
                loadMsg: '数据正在加载中，请稍后.....',
                collapsible: false,//是否可折叠的
                url: basePath + '/salary-tmp/salary-history-info?orgId=' + orgId + '&personId=' + personId + '&typeId=' + typeId+ '&salaryMonth=' + salaryMonth,
                remoteSort: false,
                //idField: 'examQueId',
                singleSelect: true,//是否单选
                rownumbers: true,//行号
                fitColumns:true,
                columns: [[
                    {field: 'item', title: '项目',width: '40%',align: 'center'},
                    {field: 'money', title: '金额',width: '60%',align: 'center'}

                ]],onLoadSuccess:function(data){
                    $("#NAME").html(row.personName);
                    $("#TIME").html(row.salaryMonth);
                    $("#ORG").html(row.orgName);
                }
            });
        }
        else{
            $.messager.alert("提示", "请选择一条工资数据！","info");
        }

    });

});