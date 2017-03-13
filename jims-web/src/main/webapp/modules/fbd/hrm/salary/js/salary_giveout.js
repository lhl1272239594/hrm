/**
 * 工资发放
 * @author
 * @version 2016-09-29
 */
$("<link>").attr({rel: "stylesheet", type: "text/css", href: "/static/easyui/css/icon.css"}).appendTo("head");
$("<script>").attr({type: "application/javascript", src: "/static/easyui/js/jquery.easyui.min.js"}).appendTo("head");
$("<script>").attr({
    type: "application/javascript",
    src: "/static/easyui/locale/easyui-lang-zh_CN.js"
}).appendTo("head");
$("<script>").attr({type: "application/javascript", src: "/static/js/tool.js"}).appendTo("head");
$("<script>").attr({type: "application/javascript", src: "/static/js/formSubmit.js"}).appendTo("head");
$("<script>").attr({type: "application/javascript", src: "/static/js/spell.js"}).appendTo("head");
var orgId = parent.config.org_Id;
var deptId='';
var deptName='';
var personName='';
var typeId='';
var personId='';
var page='1';
var rows='30';
var orgCount=0;
var deptIds=parent.orgids;
var salaryTmp = {};
var arr=[];
var search=false;
var user=[];//人员数组
var userName=[];//人员数组
var type=1;//考核对象类型（1部门2人员3考评人员）
var addArray=[];
var templetId='';
var arr1=[];//表格内数据组合数组
$(function () {

    $.messager.progress({
        title: '提示！',
        msg: '数据量较大，请稍候...',
        text: '加载中.......'
    });

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
        url: basePath +'/salary-tmp/salary-list-todo?orgId='+orgId+'&personName='+personName+'&deptId='+deptId+'&typeId='+typeId+'&deptIds='+deptIds,
        remoteSort: false,
        //idField: 'tmpId',
        singleSelect: true,//是否单选
        rownumbers: true,//行号
        columns: [[
            //{field: 'ck', title: '', checkbox: true},
            {field: 'orgId', title: '组织机构编码', hidden: true},
            {field: 'tmpId', title: '工资编号', hidden: true},
            {field: 'flag', title: '当前状态', width:'7%', align: 'center',
                formatter: function (value, row, index) {
                    if (value == "0") {
                        return "起草";
                    }
                    if (value == "1") {
                        return "已发放";
                    }
                }
            },
            {field: 'salaryMonth', title: '工资月份', width:'7%', align: 'center'},
            {field: 'personId', title: '员工编号', hidden: true},
            {field: 'personName', title: '员工姓名', width:'7%', align: 'center'},
            {field: 'deptId', title: '部门', width:'11%', align: 'center'},
            {field: 'typeId', title: '工资级别编号', hidden: true},
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
            {field: 'salaryBefore', title: '应发工资', width:'9%', align: 'center',formatter:function(value,row,index){
                var value = row.salaryBefore;
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
            {field: 'salaryAfter', title: '实发工资', width:'9%', align: 'center',formatter:function(value,row,index){
                var value = row.salaryAfter;
                if(value!=null)
                    return '￥'+(parseFloat(value).toFixed(2)+ '').replace(/\d{1,3}(?=(\d{3})+(\.\d*)?$)/g, '$&,');
            }},
            {field: 'adjustReason', title: '调整原因', width:'11%', align: 'center'},
            {field: 'adjustBy', title: '调整人', width:'7%', align: 'center'},
            {field: 'adjustDate', title: '调整时间', width:'15%', align: 'center',formatter: formatDatebox},
            {field: 'createBy', title: '创建人', width:'7%', align: 'center'},
            {field: 'createDate', title: '创建时间', width:'15%',align: 'center'},
            {field: 'confirmMan', title: '下发人', width:'7%', align: 'center'},
            {field: 'comfirmDate', title: '下发时间', width:'15%', align: 'center',formatter: formatDatebox}
        ]],
        onLoadSuccess:function(data){
            $.messager.progress('close');
            $("#dataGrid").datagrid('getPager').pagination({
                pageList: [10, 20, 30, 40, 50], //可以设置每页记录条数的列表
                displayMsg: '当前显示从第{from}条到第{to}条，共{total}条记录',
                onSelectPage: function (pageNumber, pageSize) {
                    var state1 = $('#dataGrid').data('datagrid');
                    var opts = state1.options;
                    page=opts.pageNumber = pageNumber;
                    rows=opts.pageSize = pageSize;
                    searchData(page,rows);
                }
            });
        }
    });

    loadDept2();
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

    /**
     * 查询条件搜索
     */
    $("#searchBtn").on("click", function () {
        search=true;
        searchData(page,rows);
    });


    var searchData= function (page,rows){
        //获取级别名称
        var personName = $("#PERSON_NAME").textbox('getValue');
        //var deptId = $("#DEPT_ID").combotree('getValue');
            $("#dataGrid").datagrid('reload', basePath +'/salary-tmp/salary-list-todo?orgId='+orgId+'&personName='+personName+'&deptId='+deptId+'&typeId='+typeId+'&deptIds='+deptIds+'&rows=' + rows+ '&page=' + page);
            if(search){
                search=false;
                if(page!='1') {
                    $("#dataGrid").datagrid('getPager').pagination('select', 1);
                }
            }
            $("#dataGrid").datagrid('clearSelections');
    };

//展示全部
    $("#searchAllBtn").on("click", function () {
        deptId = '';
        personName= '';
        typeId = '';
        var url = basePath +'/salary-tmp/salary-list-todo?orgId='+orgId+'&personName='+personName+'&deptId='+deptId+'&typeId='+typeId+'&deptIds='+deptIds;
        $("#dataGrid").datagrid("reload", url);

    });
    /**
     * 清空方法
     */
    var clearKey = function () {
        $("#PERSON_NAME").textbox('clear');
        $("#PERSON_NAME").textbox("setValue","");
    };
    /**
     * 清空查询条件
     */
    $("#clearBtn").on("click", function () {
        clearKey();
        page='1';
    });
    //全部合并
    $("#allNodeClose").on("click", function () {
        $("#userTree").tree('collapseAll');
    });
    //全部展开
    $("#allNodeOpen").on("click", function () {
        $("#userTree").tree('expandAll');
    });

    /**
     * 按部门发放
     */
    $("#dealBtn").on('click', function () {
        //获取人员
        var userTree=$("#userTree").tree('getChecked');
        var row=$("#personGrid").datagrid('getRows');
        $.each( row, function(i, n){
            arr1.push(n);
        });
            for(var i=0;i<userTree.length;i++) {
                var isNew=getName(arr1,'userId',userTree[i].userId);
                if(isNew.length==0) {
                    if (userTree[i].type == 2) {
                        var obj = {};
                        obj.userId = userTree[i].id;
                        obj.type = type;
                        obj.dataId = templetId;
                        arr.push(obj);
                    }
                }
            }
        if(userTree.length==0||!userTree){
            $.messager.alert('提示', '请选择科室', 'info');
            return;
        }
        salaryTmp.personVos = arr;
        salaryTmp.orgId = parent.config.org_Id;
            $.messager.confirm('提示', '确定要下发该科室人员工资吗？注意：工资下发后将不可再修改！', function (r) {
                if (r) {
                    $.messager.progress({
                        title: '提示！',
                        msg:  '数据加载中，请稍候...',
                        text: '加载中.......'
                    });
                    $.postJSON(basePath + "/salary-tmp/dealbyDept", JSON.stringify(salaryTmp), function (data) {
                        if (data.data == "success") {
                            $.messager.progress('close');
                            $('#chooseDept').window('close');
                            $("#dataGrid").datagrid('reload');
                            $("#dataGrid").datagrid('clearSelections');
                        }
                        else {
                            $.messager.alert('提示', '创建失败!', 'info');
                        }
                    })
                }
            });
    });


});




