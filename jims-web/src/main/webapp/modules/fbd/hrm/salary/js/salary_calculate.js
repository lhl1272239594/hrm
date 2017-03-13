/**
 * 工资计算公式
 * @author 
 * @version 2016-08-22
 */
var orgId= parent.config.org_Id;
var personId=parent.config.persion_Id;
var page='1';
var rows='30';
var salaryCalculate = {};
var typeId='';
var partId='';
var orgCount = 0;
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
        url: basePath +"/salary-cal/cal-list?orgId=" + orgId + '&typeId=' + typeId + '&partId=' + partId,
        remoteSort: false,
        //idField: 'contentId',
        singleSelect: false,//是否单选
        rownumbers: true,//行号
        columns: [[
            {field: 'ck', title: '', checkbox: true},
            {field: 'contentId', title: '公式编号', hidden: true},
            {field: 'typeName', title: '工资级别', width:'10%', align: 'center'},
            {field: 'typeId', title: '工资级别编号', hidden: true},
            {field: 'partName', title: '工资组成', width:'10%', align: 'center'},
            {field: 'partId', title: '工资组成编号', hidden: true},
            {field: 'content', title: '公式内容', width:'30%', align: 'center'},
            {field: 'code', title: '公式编码',hidden: true},
            {field: 'orgId', title: '组织机构编码', hidden: true},
            {field: 'delFlag', title: '删除标识', hidden: true},
            {
                field: 'enableFlag', title: '状态', width:'7%', align: 'center',
                formatter: function (value, row, index) {
                    if (value == "1") {
                        return "启用";
                    }
                    if (value == "0") {
                        return "停用";
                    }
                }
            },
            {field: 'createBy', title: '创建人', width:'8%', align: 'center'},
            {field: 'createDeptname', title: '创建科室', width:'10%', align:'center'},
            {field: 'createDept', title: '创建科室编号', hidden:true},
            {field: 'createDate', title: '创建时间', width:'15%',align: 'center'}
            /*{field: 'updateBy', title: '更新人', width:'7%', align: 'center'},
            {field: 'updateDate', title: '更新时间', width:'10%', align: 'center'}*/
        ]],
        onLoadSuccess:function(data){
            mergeCells();//合并单元格
            $('#newDialog').css('display','block');
            $('#tb').css('display','block');
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



    /**
     * 查询条件搜索
     */
    $("#searchBtn").on("click", function () {
        search=true;
        searchData(page,rows);
    });

    var searchData= function (page,rows){
        var typeId = $("#TYPE_NAME").combobox('getValue');
        var partId = $("#PART_NAME2").combobox('getValue');
        if(typeId=='请选择'){
            typeId='';
        }
        if(partId=='请选择'){
            partId='';
        }
        /*$.get(basePath +"/salary-cal/cal-list?orgId=" + orgId + '&typeId=' + typeId + '&partId=' + partId+ '&page=' + page+ '&rows=' + rows, function (data) {
            $("#dataGrid").datagrid('loadData', data);*/
            $("#dataGrid").datagrid('reload', basePath +"/salary-cal/cal-list?orgId=" + orgId + '&typeId=' + typeId + '&partId=' + partId+ '&page=' + page+ '&rows=' + rows);
            if(search){
                search=false;
                if(page!='1') {
                    $("#dataGrid").datagrid('getPager').pagination('select', 1);
                }
            }
            $("#dataGrid").datagrid('clearSelections');
    };

    //查询条件：人员类别
    $("#TYPE_NAME").combobox({
        panelWidth: '138px',
        panelHeight: 'auto',
        valueField: 'typeId',
        textField: 'typeName',
        value:'请选择',
        loadMsg: '数据正在加载...',
        url: basePath + '/salary-cal/find-humanType?orgId=' + orgId ,
        mode: 'remote',
        method: 'GET',
        onLoadSuccess:function(data){
            orgCount=data.length;
        },
        onShowPanel:function(){
            //动态调整高度
            if(orgCount>8){
                $(this).combobox('panel').height(140);
            }
        }

    });
    //查询条件：工资组成
    $("#PART_NAME2").combobox({
        panelWidth: '138px',
        panelHeight: 'auto',
        valueField: 'partId',
        textField: 'partName',
        value:'请选择',
        loadMsg: '数据正在加载...',
        url: basePath + '/salary-cal/find-salaryPart?orgId=' + orgId ,
        mode: 'remote',
        method: 'GET',
        onLoadSuccess:function(data){
            orgCount=data.length;
        },
        onShowPanel:function(){
            //动态调整高度
            if(orgCount>8){
                $(this).combobox('panel').height(140);
            }
        }
    });
    /**
     * 工资计算公式新增编辑弹出框
     */
    $("#newDialog").dialog({
        title: '计算公式',
        modal: true,
        closed:true,
        collapsible: true,
        minimizable: false,
        maximizable: true,
        resizable: true
    });
    /**
     * 添加
     */
    $("#addBtn").on('click', function () {
        $("#CONTENT_ID").val('');
        $("#newForm").form('reset');
        $("#newDialog").dialog("setTitle","工资计算公式添加").dialog("open");
        $("#sumValue").val('');
        $("#sumText").textbox("setValue","");
        $("#selectPerformance").val('');
        $("#selectType").val('');
        $("#selectPart").val('');
        arrayObj.length = 0;
        arrayObjText.length = 0;
        arrayObj.pop();
        $("#sumText").textbox('clear');
        $("#select1").removeAttr("disabled", "disabled");
        $("#selectPerformance").removeAttr("disabled", "disabled");
    });
    /**
     * 复制
     */
    $("#editBtn").on('click', function () {
        var row = $("#dataGrid").datagrid("getSelected");
        var row1 = $("#dataGrid").datagrid("getSelections");
        if(!row){
            $.messager.alert("提示","请选择要复制的公式数据!",'info');
            return;
        }
        if (row1.length != 1) {
            $.messager.alert("提示", "请选择一条数据进行操作!","info");
            return;
        }
        $("#newDialog").dialog("setTitle","工资公式复制").dialog("open");
        $("#sumValue").val(row.code);
        $("#sumText").textbox("setValue",row.content);
        $("#selectType").val("setValue","");
        $("#selectPart").val("setValue","");
        $("#selectPerformance").attr("disabled", "disabled");
        $("#select1").attr("disabled", "disabled");
        $("#editor").attr("disabled", "disabled");

    });
    /**
     * 清空查询条件
     */
    $("#clearBtn").on("click", function () {
        clearKey();
        page='1';
    });
    /**
     * 清空方法
     */
    var clearKey = function () {
        // $("#LEVEL_ID").textbox("setValue","");
        //$("#LEVEL_NAME").textbox("setValue","");
        $("#PART_NAME2").combobox('clear');
        $("#PART_NAME2").combobox("setValue","请选择");
        $("#TYPE_NAME").combobox('clear');
        $("#TYPE_NAME").combobox("setValue","请选择");

    };

    $(function() {
        $("#selectType").empty();
        $.ajax({
                type: "GET",
                //data:{cx:cx},
                async: false,
                //url: '/service/dict/find-list-by-type?type=SALARY_PROJECT', //数据字典方法
                url:  basePath +'/salary-cal/find-humanType?orgId=' + orgId,
                success: function (data) {
                    for (var i = 0; i < data.length; i++) {
                        // alert(data[0].value);
                        var o = data[i].typeId;
                        var m = data[i].typeName;
                        $("#selectType").append("<option value='" + o + "'>" + m + "</option>");

                    }

                },
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    alert(XMLHttpRequest);
                }
                //false表示同步
            }
        );
    });
    /**
     * 工资组成下拉框
     */
    $(function() {
        $("#selectPart").empty();
        $.ajax({
                type: "GET",
                async: false,
                url:  basePath +'/salary-cal/find-salaryPart?orgId=' + orgId,
                success: function (data) {
                    for (var i = 0; i < data.length; i++) {
                        // alert(data[0].value);
                        var o = data[i].partId;
                        var m = data[i].partName;
                        $("#selectPart").append("<option value='" + o + "'>" + m + "</option>");

                    }

                },
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    alert(XMLHttpRequest);
                }
            }
        );
    });

    /**
     * 绩效评分下拉框
     */
    $(function() {
        $("#selectPerformance").empty();
        $.ajax({
                type: "GET",
                async: false,
                url:  basePath +'/salary-cal/find-performance-type?orgId=' + orgId,
                success: function (data) {
                    for (var i = 0; i < data.length; i++) {
                        // alert(data[0].value);
                        var o = data[i].id;
                        var m = data[i].typeName;
                        $("#selectPerformance").append("<option value='" + o + "'>" + m + "</option>");
                    }
                },
                error: function (XMLHttpRequest, textStatus, errorThrown) {
                    alert(XMLHttpRequest);
                }
            }
        );
    });

    /**
     * 启用
     */
    $("#okBtn").on('click', function () {
        //stopEdit();
        var row = $("#dataGrid").datagrid('getSelections');

        if (row == null||row.length == 0) {
            $.messager.alert("提示", "请选择要启用的数据!","info");
            return;
        }
        if (row.length>0) {
            for (var i = 0; i < row.length; i++) {
                row[i].enableFlag = '1';
            }
            $.messager.confirm('提示', '确定要进行启用操作吗', function (r) {
                if (r) {
                    $.postJSON(basePath + "/salary-cal/enableFlag",JSON.stringify(row),  function (data) {
                        $('#dataGrid').datagrid('reload');
                        row.length = 0;
                        $("#dataGrid").datagrid('clearSelections');
                    })
                }
            });
        }
     });
    /**
     * 停用
     */
    $("#noBtn").on('click', function () {
        //stopEdit();
        var row = $("#dataGrid").datagrid('getSelections');
        if (row == null||row.length == 0||!row) {
            $.messager.alert("提示", "请选择要停用的数据!","info");
            return;
        }
        if (row.length>0) {
            for (var i = 0; i < row.length; i++) {
                row[i].enableFlag = '0';
            }
            $.messager.confirm('提示', '确定要进行停用操作吗', function (r) {
                if (r) {
                    $.postJSON(basePath + "/salary-cal/enableFlag",JSON.stringify(row), function (data) {
                        $('#dataGrid').datagrid('reload');
                        row.length = 0;
                        $("#dataGrid").datagrid('clearSelections');
                    })
                }
            });
        }
    });

    /**
     * 删除
     */
    $("#delBtn").on('click', function () {
        //stopEdit();
        var row = $("#dataGrid").datagrid('getSelections');
        if (row == null||row.length == 0) {
            $.messager.alert("提示", "请选择要删除的数据!","info");
            return;
        }
            $.messager.confirm('提示', '确定要批量删除所选中的数据么？', function (r) {
                if (r) {
                    $.postJSON(basePath + "/salary-cal/del-cal", JSON.stringify(row), function (data) {
                        $("#dataGrid").datagrid('reload');
                        row.length=0;
                        row==null;
                        $('#dataGrid').datagrid('clearSelections');
                    })
                }
            });


    });
    /**
     * 新增修改保存
     */
    $("#submitBtn").on('click', function () {
        if(!$("#sumText").textbox("getValue")){
            $.messager.alert("提示","请输入公式",'info');
            return ;
        }
        if(!$("#selectType").val()){
            $.messager.alert("提示","请选择工资级别",'info');
            return ;
        }
        if(!$("#selectPart").val()){
            $.messager.alert("提示","请选择工资组成部分",'info');
            return ;
        }
        salaryCalculate.orgId = parent.config.org_Id;
        salaryCalculate.createBy = parent.config.persion_Id;
        salaryCalculate.updateBy = parent.config.persion_Id;
        salaryCalculate.contentId = $("#CONTENT_ID").val();
        salaryCalculate.code = $("#sumValue").val();
        salaryCalculate.content = $("#sumText").textbox('getValue');
        salaryCalculate.typeName = $("#TYPE_NAME1").textbox('getValue');
        salaryCalculate.partName = $("#PART_NAME").textbox('getValue');
        //alert(salaryCalculate.content);
        //alert(salaryCalculate.typeName);

        //判断是否已存在相同内容的公式
        $.get(basePath +"/salary-cal/if-exist?orgId=" + orgId+"&content="+$("#sumText").textbox('getValue')+"&typeName="+$("#TYPE_NAME1").textbox('getValue')+"&partName="+$("#PART_NAME").textbox('getValue'),
            function (data) {
                var list = eval(data);
                for (var i = 0; i < 1; i++)
                {
                    var num=list[i]['num'];
                }
                //alert(num);
                if(num=='1'||num>'1'){
                    var str = '该工资级别的工资组成部分公式已存在！';
                    $.messager.alert("提示", str,'info');

                }
                else
                {
                     //如果不重复，先验证公式的正确性
                        var result = arrayObj.join("");
                        //alert(result);
                        var p = /\[.*?\]/g;
                        result = result.replace(p, '111.11');
                        //alert(result);
                    //连续两次出现运算符
                    if( /[\+\-\*\/]{2,}/.test(result) ){
                        $.messager.alert("提示","公式错误：连续出现运算符！",'info');
                        return false;
                    }
                // 错误情况，括号不配对
                var stack = [];
                for(var i = 0, item; i < result.length; i++){
                    item = result.charAt(i);
                    if('(' === item){
                        stack.push('(');
                    }else if(')' === item){
                        if(stack.length > 0){
                            stack.pop();
                        }else{
                            $.messager.alert("提示","公式错误：请检查括号的使用是否规范！",'info');
                            return false;
                        }
                    }
                    }
                    // 空括号
                    if(/\(\)/.test(result)){
                        $.messager.alert("提示","公式错误：空括号！",'info');
                        return false;
                    }
                    if(0 !== stack.length){
                        $.messager.alert("提示","公式错误！",'info');
                        return false;
                    }

                    // 错误情况，(后面是运算符
                    if(/\([\+\-\*\/]/.test(result)){
                        $.messager.alert("提示","公式错误：请检查括号的使用是否规范！",'info');
                        return false;
                    }

                    // 错误情况，)前面是运算符
                    if(/[\+\-\*\/]\)/.test(result)){
                        $.messager.alert("提示","公式错误：请检查括号的使用是否规范！",'info');
                        return false;
                    }

                    // 错误情况，(前面不是运算符
                    if(/[^\+\-\*\/]\(/.test(result)){
                        $.messager.alert("提示","公式错误：请检查括号的使用是否规范！",'info');
                        return false;
                    }

                    // 错误情况，)后面不是运算符
                    if(/\)[^\+\-\*\/]/.test(result)){
                        $.messager.alert("提示","公式错误：请检查括号的使用是否规范！",'info');
                        return false;
                    }
                    //如果不重复，且公式正确，执行保存代码
                    $.postJSON(basePath + "/salary-cal/merge", JSON.stringify(salaryCalculate), function (data) {
                        if(data.data=="success")
                        {
                            // $.messager.alert('提示', '保存成功', 'info');
                            $('#newDialog').window('close');
                            $("#dataGrid").datagrid('reload');
                            $('#dataGrid').datagrid('clearSelections');
                        }
                        else{
                            $.messager.alert('提示', '保存失败', 'info');
                        }
                    })

                }
            });

    });
});

/**
 * 工资项目select加载数据
 */
/*$(function() {
    $("#select1").empty();
    $.ajax({
            type: "GET",
            //data:{cx:cx},
            async: false,
            //url: '/service/dict/find-list-by-type?type=SALARY_PROJECT', //数据字典方法
             url:  basePath +'/salary-cal/find-project?orgId=' + orgId,
            success: function (data) {
                for (var i = 0; i < data.length; i++) {
                   // alert(data[0].value);
                    var o = data[i].projectId;
                    var m = data[i].projectName;
                    $("#select1").append("<option value='" + o + "'>" + m + "</option>");

                }
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                alert(XMLHttpRequest);
            },
            //false表示同步
        }
    );
});*/



    /**
     * 编辑器按钮
     */
    function onButton(v) {

        /*if (yz == v && isNaN(v) && v != 'C') {
            alert('不能连续输入运算符号！');
            return;
        }*/
        if (v == 'clearAll') {  //全部清除
            arrayObj.length = 0;
            arrayObjText.length = 0;
            //alert(v);
            arrayObj.pop();
            $("#sumText").textbox('clear');
            $("#sumValue").val('');
            $("#select1").removeAttr("disabled", "disabled");
            return;
        }
        if (v == 'C') {
            //alert(v);
            arrayObj.pop();//清除数组最后一个值
            arrayObjText.pop();

        }
        else {

            arrayObj.push(v);//值放入数组
            arrayObjText.push(v);
        }
        var s = "";
        for (var i = 0; i < arrayObj.length; i++) {
            s += arrayObj[i];
        }
        var l = "";
        for (var i = 0; i < arrayObj.length; i++) {
            l += arrayObjText[i];
        }
        $("#sumText").textbox('setValue', l);//打印
        $("#sumValue").val(s);//打印
        yz = v;
        $("#select1").removeAttr("disabled", "disabled");
        //$("#selectPerformance").removeAttr("disabled", "disabled");

    }
/**
 * selectType选择人员类别编辑器
 */
function onSelect1(v) {
    $("#TYPE_NAME1").textbox('setValue', v);
    //给工资项目下拉框赋值
    var typeId = v;
    //alert(typeId);
    $("#select1").empty();
    $.ajax({
            type: "GET",
            //data:{cx:cx},
            async: false,
            //url: '/service/dict/find-list-by-type?type=SALARY_PROJECT', //数据字典方法
            url:  basePath +'/salary-cal/find-project?orgId=' + orgId+'&typeId='+typeId,
            success: function (data) {
                for (var i = 0; i < data.length; i++) {
                    // alert(data[0].value);
                    var o = data[i].projectId;
                    var m = data[i].projectName;
                    $("#select1").append("<option value='" + o + "'>" + m + "</option>");
                        //重新选人员类别后清除公式内容
                    arrayObj.length = 0;
                    arrayObjText.length = 0;
                    arrayObj.pop();
                    $("#sumText").textbox('clear');
                    $("#sumValue").val('');
                    $("#select1").removeAttr("disabled", "disabled");
                }
            },
            error: function (XMLHttpRequest, textStatus, errorThrown) {
                alert(XMLHttpRequest);
            },
            //false表示同步
        }
    );

}
/**
 * 工资组成部分选择赋值
 */
function onSelect3(v) {
    $("#PART_NAME").textbox('setValue', v);

}
    /**
     * 工资项目选择编辑器
     */
    function onSelect(v,t) {
        var q = "["+v+"]";
        arrayObj.push(q);
        arrayObjText.push(t);
        var s = "";
        for (var i = 0; i < arrayObj.length; i++) {
            s += arrayObj[i];
        }
        var l = "";
        for (var i = 0; i < arrayObj.length; i++) {
            l += arrayObjText[i];
        }
        $("#sumText").textbox('setValue', l);//打印
        $("#sumValue").val(s);//打印
        //yz = v;
        $("#select1").attr("disabled", "disabled");
        //$("#selectPerformance").attr("disabled", "disabled");

    }
/**
 * 绩效评分选择
 */
function onSelect2(v,t) {
    var q = "@"+v+"@";
    arrayObj.push(q);
    arrayObjText.push(t);
    var s = "";
    for (var i = 0; i < arrayObj.length; i++) {
        s += arrayObj[i];
    }
    var l = "";
    for (var i = 0; i < arrayObj.length; i++) {
        l += arrayObjText[i];
    }
    $("#sumText").textbox('setValue', l);//打印
    $("#sumValue").val(s);//打印
    //yz = v;
    $("#select1").attr("disabled", "disabled");
    $("#selectPerformance").attr("disabled", "disabled");

}


    var arrayObj = [];
    var arrayObjText = [];
    var yz = "";


//datagrid加载完后合并指定单元格
function mergeCells(data){
    var arr =[{mergeFiled:"typeName",premiseFiled:"typeName"}	//合并列的field数组及对应前提条件filed（为空则直接内容合并）
    ];
    var dg = $("#dataGrid");	//要合并的datagrid中的表格id
    var rowCount = dg.datagrid("getRows").length;
    var cellName;
    var span;
    var perValue = "";
    var curValue = "";
    var perCondition="";
    var curCondition="";
    var flag=true;
    var condiName="";
    var length = arr.length - 1;
    for (i = length; i >= 0; i--) {
        cellName = arr[i].mergeFiled;
        condiName=arr[i].premiseFiled;
        if(condiName!=null){
            flag=false;
        }
        perValue = "";
        perCondition="";
        span = 1;
        for (row = 0; row <= rowCount; row++) {
            if (row == rowCount) {
                curValue = "";
                curCondition="";
            } else {
                curValue = dg.datagrid("getRows")[row][cellName];
                /* if(cellName=="ORGSTARTTIME"){//特殊处理这个时间字段
                 curValue =formatDate(dg.datagrid("getRows")[row][cellName],"");
                 } */
                if(!flag){
                    curCondition=dg.datagrid("getRows")[row][condiName];
                }
            }
            if (perValue == curValue&&(flag||perCondition==curCondition)) {
                span += 1;
            } else {
                var index = row - span;
                dg.datagrid('mergeCells', {
                    index : index,
                    field : cellName,
                    rowspan : span,
                    colspan : null
                });
                span = 1;
                perValue = curValue;
                if(!flag){
                    perCondition=curCondition;
                }
            }
        }
    }
}

