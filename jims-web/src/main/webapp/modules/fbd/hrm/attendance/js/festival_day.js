/**
 *法定节日设置
 * @author yangchen
 * @version 2016-08-18
 */
var basePath = "/service";
var fesDesId="999";
var yearId="999";
var year="";
var sumDays="0";
var fesId='999';
var fesDateId='999';
var dg;
var d;
var flag;
var page='1';
var rows='30';
var orgId = parent.config.org_Id;
var festivalVo = {};
var yearDes='';
var fesDes='';
var userName='';
var userId='';
var fesDateList=[];
var fesDateType='';
var obj1 = new  Object();
var obj2 = new  Object();
var obj3 = new  Object();
var value='';
var obj={};
var search=false;
var editIndex;
var yearList;
var festivalList ;
var orgCount=0;
var array=[];
$(function () {

    var editIndex;
    var stopEdit = function () {
        if (editIndex || editIndex == 0) {
            $("#festivalDateGrid").datagrid('endEdit', editIndex);
            editIndex = undefined;
        }
    };

    yearDict();

    festivalDict();

    $("#festivalGrid").datagrid({
        iconCls: 'icon-edit',//图标
        width: '100%',
        height: '100%',
        nowrap: false,
        striped: true,
        border: true,
        method: 'get',
        toolbar: '#festb',
        pageSize: 30,
        fitColumns: true,
        idField: 'fesId',
        loadMsg: '数据正在加载中，请稍后.....',
        pagination: true,//分页控件,
        collapsible: false,//是否可折叠的
        url: basePath + '/festival/festival-list?fesDesId=' + fesDesId + '&orgId=' + orgId + '&yearId=' + year,
        remoteSort: false,
        singleSelect: true,//是否单选
        rownumbers: true,//行号
        columns: [[
            {field: 'orgId', title: '', width: 100, hidden: true},
            {field: 'fesId', title: '', width: 100, hidden: true},
            {field: 'yearId', title: '年份', width: 100, align: 'center',
                formatter: function (a) {
                    for (var i = 0; i <= yearList.length; i++) {
                        if (a == yearList[i].value) {
                            return yearList[i].label;
                        }

                    }
                },
            },
            {
                field: 'fesDesId', title: '节日名称', width: 100, align: 'center',
                formatter: function (fesDesId) {
                    for (var i = 0; i <=festivalList.length; i++) {
                        if (fesDesId == festivalList[i].value) {
                            return festivalList[i].label;
                        }

                    }
                },
            },
            {field: 'createBy', title: '创建人', width: '10%', align: 'center',
                formatter: function (createBy) {
                    return parent.personList[createBy];
                }
            },
            {field: 'createDate', title: '创建时间', width: 100, align: 'center'}
        ]],
        onLoadSuccess:function(data){

        }
    });

    $("#festivalGrid").datagrid('getPager').pagination({
        pageList: [10, 20, 30, 40, 50], //可以设置每页记录条数的列表
        displayMsg: '当前显示从第{from}条到第{to}条，共{total}条记录',
        onSelectPage: function (pageNumber, pageSize) {
            var state = $('#festivalGrid').data('datagrid');
            var opts = state.options;
            page=opts.pageNumber = pageNumber;
            rows=opts.pageSize = pageSize;
            searchData(page,rows);
        }
    });


    //查询条件：节日
    $("#festival").combobox({
        idField: 'value',
        textField: 'label',
        value:'请选择',
        loadMsg: '数据正在加载',
        //data:festivalList,
        url: '/service/dict/find-list-by-type?type=' + 'FESTIVAL_TYPE',
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
    //查询条件：年份
    $("#year").combobox({
        idField: 'value',
        textField: 'label',
        value:'请选择',
        loadMsg: '数据正在加载',
        //data:yearList,
        url: '/service/dict/find-list-by-type?type=' + 'YEAR_DICT',
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
    $("#festivalDate").combobox({
        idField: 'value',
        textField: 'label',
        loadMsg: '数据正在加载',
        //data:festivalList,
        url: '/service/dict/find-list-by-type?type=' + 'FESTIVAL_TYPE',
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
    //输入项目：年份
    $("#yearDate").combobox({
        idField: 'value',
        textField: 'label',
        loadMsg: '数据正在加载',
        // data:yearList,
        url: '/service/dict/find-list-by-type?type=' + 'YEAR_DICT',
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

    //按条件查询节日数据
    $("#searchBtn").on("click", function () {
        search=true;

        //获取节日描述
        fesDesId = $("#festival").combobox('getValue');
        //获取年份
        year = $("#year").combobox('getValue');

        if(fesDesId=='请选择'){
            fesDesId='999'
        }
        if(year=='请选择'){
            year=''
        }

        searchData(page,rows);
    });


    var searchData= function (page,rows){
            $("#festivalGrid").datagrid('reload', basePath + '/festival/festival-list?fesDesId=' + fesDesId + '&orgId=' + orgId + '&yearId=' + year+ '&page=' + page+ '&rows=' + rows);
            if(search){
                search=false;
                $("#festivalGrid").datagrid('getPager').pagination('select',1);

            }
    }

    $.get(basePath + '/tool/find-list-by-type?type=DATE_TYPE', function (data) {
        $.each(data, function (index, item) {
            var obj={};
            obj.value=item.value;
            obj.label=item.label;
            fesDateList.push(obj);

        });
    });

    var unitformatter= function (value, rowData, rowIndex) {
        for (var i = 0; i < fesDateList.length; i++) {
            if (fesDateList[i].value == value) {
                return fesDateList[i].label;
            }
        }
    }
    //配置窗口
    $("#editFestival").window({
        minimizable: false,
        title: '节日信息',
        closed: true,
        modal: true,
        width : "460",
        height : "400",
        onClose: function () {
            $('#festivalGrid').datagrid('clearSelections');
            $("#festivalGrid").datagrid('reload');
            $('#festivalDateGrid').edatagrid('loadData', { total: 0, rows: [] });
            $('#festivalDateGrid').edatagrid('clearSelections');
            $("#editForm").form('reset');

        },
        onOpen: function () {
            //输入项目：节日


        }

    });
//配置窗口
    $("#addWin").window({
        minimizable: false,
        title: '新增节日',
        closed: true,
        modal: true,
        width : "520",
        height : "160",
        onClose: function () {
            $('#festivalDateGrid').edatagrid('clearSelections');
            $("#editForm").form('reset');

        },
        onOpen: function () {
            //输入项目：节日


        }

    });
    //加载数据
    var loadData= function (fesId) {
        $.get(basePath + '/festival/festival-date-list?fesId=' + fesId + '&orgId=' + orgId,
            function (data) {
                array=data;
                $("#festivalDateGrid").datagrid('loadData',array);
            });
    }
    //新增节日窗口
    $("#addBtn").on('click', function () {
        $("#editFestival").window("open");
        $("#datetb").show();
        $("#fesId").val("999");
        $("#yearDate").combobox('enable');
        $("#festivalDate").combobox('enable');
        $("#Form").form('reset');
        $("#festivalDateGrid").edatagrid({
            width: '100%',
            height: '100%',
            nowrap: false,
            striped: true,
            border: true,
            method: 'get',
            toolbar: '#datetb',
            loadMsg: '数据正在加载中，请稍后.....',
            collapsible: false,//是否可折叠
            remoteSort: false,
            singleSelect: false,//是否单选
            rownumbers: true,//行号
            fitColumns: true,
            columns: [[
                {field: 'fesDateId', title: '', width: 100, hidden:true},
                {field: 'ck', title: '', checkbox: true},
                {field: 'orgId', title: '', width: 100, hidden: true},
                {
                    field: 'fesDate', title: '日期', width: 100, align: 'center'
                },
                {
                    field: 'fesDateType', title: '日期类型', width: 100, align: 'center',

                    formatter: unitformatter,
                    editor: {
                        type: 'combobox',
                        options: {
                            mode: 'remote',
                            panelHeight: 'auto',
                            method: 'GET',
                            valueField: 'value',
                            textField: 'label',
                            editable: false,
                            data: fesDateList
                        }
                    },
                }

            ]],
            onClickRow: function (index, row) {
                stopEdit();
                $(this).datagrid('beginEdit', index);
                editIndex = index;
            }
        });


    });
    //修改节日
    $("#editBtn").on('click', function () {
        var row=$("#festivalGrid").datagrid("getSelected");
        if(row) {
            $("#editFestival").window("open");
            flag==1;
            $("#datetb").show();
            $("#festivalDateGrid").edatagrid({
                width: '100%',
                height: '100%',
                nowrap: false,
                striped: true,
                border: true,
                method: 'get',
                toolbar: '#datetb',
                loadMsg: '数据正在加载中，请稍后.....',
                collapsible: false,//是否可折叠
                remoteSort: false,
                singleSelect: false,//是否单选
                rownumbers: true,//行号
                fitColumns: true,
                columns: [[
                    {field: 'fesDateId', title: '', width: 100, hidden: true},
                    {field: 'ck', title: '', checkbox: true},
                    {field: 'orgId', title: '', width: 100, hidden: true},
                    {
                        field: 'fesDate', title: '日期', width: 100, align: 'center'
                    },
                    {
                        field: 'fesDateType', title: '日期类型', width: 100, align: 'center',
                        formatter: unitformatter,
                        editor: {
                            type: 'combobox',
                            options: {
                                mode: 'remote',
                                panelHeight: 'auto',
                                method: 'GET',
                                valueField: 'value',
                                textField: 'label',
                                editable: false,
                                data: fesDateList
                            }
                        }
                    }

                ]],
                onClickRow: function (index, row) {
                    stopEdit();
                    $(this).datagrid('beginEdit', index);
                    editIndex = index;
                }

            });
            for (var i = 0; i < festivalList.length; i++) {
                if (row.fesDesId == festivalList[i].value) {
                    fesDes=festivalList[i].label;
                }
            }
            for (var i = 0; i < yearList.length; i++) {
                if (row.yearId == yearList[i].value) {
                    yearDes = yearList[i].label;
                }
            }


            $("#yearDate").textbox("setValue",yearDes);
            $("#festivalDate").textbox("setValue",fesDes);

            $("#yearDate").combobox('disable');
            $("#festivalDate").combobox('disable');
            $("#flag").val('1');
            $("#fesId").val(row.fesId);

            loadData(row.fesId);

        }
        else{
            $.messager.alert('提示', '请选择一条记录!', 'info');  //提示信息
        }
    });
    //查看节日日期信息
    $("#viewBtn").on('click', function () {

        var row=$("#festivalGrid").datagrid("getSelected");
        if(row) {

            $("#festivalDateGrid").edatagrid({
                width: '100%',
                height: '100%',
                nowrap: false,
                striped: true,
                border: true,
                method: 'get',
                toolbar: '#datetb',
                loadMsg: '数据正在加载中，请稍后.....',
                collapsible: false,//是否可折叠
                remoteSort: false,
                singleSelect: false,//是否单选
                rownumbers: true,//行号
                fitColumns: true,
                columns: [[
                    {field: 'orgId', title: '', width: 100, hidden: true},
                    {field: 'fesDate', title: '日期', width: 100, align: 'center',},
                    {
                        field: 'fesDateType', title: '日期类型', width: 100, align: 'center',
                        formatter: function (fesDesId) {
                            $.ajaxSettings.async = false;
                            $.get(basePath + '/tool/find-list-by-type?value='+fesDesId+'&type=DATE_TYPE', function (data) {
                                fesDateType=data[0].label;
                            });
                            return fesDateType;
                        },
                    }

                ]],

            });

            $("#editFestival").window("open");
            $("#datetb").hide();
            loadData(row.fesId);


        }
        else{
            $.messager.alert('提示', '请选择一行记录！', 'info');

        }
    });

    //刷新
    var reload = function () {

        $.get(basePath + "/festival/festival-list?fesDesId="+fesDesId+'&=orgId' +orgId+'&yearId='+year , function (data) {
            $("#festivalGrid").datagrid('loadData', data);
        });
    };

    //删除节日数据
    $("#delBtn").on('click', function () {
        flag='1';
        var row = $("#festivalGrid").datagrid('getSelected');

        if (row) {
            festivalVo.fesId = row.fesId;
            $.messager.confirm('提示', '确定要进行删除操作吗', function (r) {
                if (r) {
                    $.postJSON(basePath + "/festival/festival-del",JSON.stringify(festivalVo),function (data) {
                        $('#festivalGrid').datagrid('reload');
                        row.length=0;
                        $('#festivalGrid').datagrid('clearSelections');

                    });
                }
            });

        } else {
            $.messager.alert('提示', '请选择一行记录！', 'info');
        }

    });

    //新增节日信息
    $("#addDateBtn").on('click', function () {

        $("#addWin").window('open');
    });

    //删除节日详细信息数据
    $("#delDateBtn").on('click', function () {
        var rows = $("#festivalDateGrid").edatagrid('getSelections');
        if (rows.length==0) {
            $.messager.alert('提示', '请选择要删除的记录！', 'info');
            return;
        }
        for(var i=0;i<rows.length;i++){
            var index=$("#festivalDateGrid").datagrid('getRowIndex',rows[i]);
            array.remove(index);
            $("#festivalDateGrid").datagrid('loadData',array);

        }
        $('#festivalDateGrid').edatagrid('clearSelections');

    });
    //确定
    $("#saveBtn").on('click', function () {

        var startDate=$("#startDate").datebox('getValue');
        if(startDate==''){
            $.messager.alert("提示", "请选择开始日期!","info");
            return
        }
        var num=$("#num").numberbox('getValue');
        if(date==''){
            $.messager.alert("提示", "请选择开始日期!","info");
            return
        }
        var date = Date.parse(startDate);
        array=[];
        for(var i=0;i<num;i++){
            var obj={};
            var nd=addNDays(StringToDate(startDate),i);
            var getNewDay = nd.format('yyyy-MM-dd');
            obj.fesDateId=i;
            obj.fesDate=getNewDay;
            obj.fesDateType=2;
            array.push(obj);
        }

        $("#festivalDateGrid").datagrid('loadData',array);
        $("#addWin").window('close');
    });
    function StringToDate(DateStr)
    {
        var myDate;
        if (isNaN(DateStr))
        {
            //var delimCahar = DateStr.indexOf('/')!=-1?'/':'-';
            var arys= DateStr.split('-');
            myDate = new Date(arys[0], arys[1]-1, arys[2]);
        }
        return myDate;
    }
    var addNDays=function(date,n){
        var time=date.getTime();
        var newTime=time+n*24*60*60*1000;
        return new Date(newTime);
    };
    //取消
    $("#cancelBtn").on('click', function () {
        $("#editForm").form('reset');
        $("#addWin").window('close');
    });
    //清除
    $("#clearBtn").on('click', function () {
        $("#year").combobox('setValue','请选择');
        $("#festival").combobox('setValue','请选择');
        fesDesId="999";
        year="999";
        page = '1';
    });
    //取消
    $("#cancelBtn").on('click', function () {
        $("#editForm").form('reset');
        $("#addWin").window('close');
    });

    //保存节日全部数据
    $("#saveDateBtn").on('click', function () {


        //获取节日
        DesId = $("#festivalDate").combobox('getValue');

        //获取年份
        yearId = $("#yearDate").combobox('getValue');
        yearDes = $("#yearDate").combobox('getText');
        var rows= $("#festivalDateGrid").edatagrid("getRows");
        for (var i=0;i<rows.length;i++) {
            $("#festivalDateGrid").edatagrid('endEdit', i)
        }

        flag = $("#flag").val();
        id = $("#fesId").val();
        //获取总天数
        sumDays =rows.length;

        if(yearId==''){
            $.messager.alert("提示", "请选择节日年份!","info");
            return
        }

        if(DesId==''){
            $.messager.alert("提示", "请选择节日!","info");
            return
        }

        if(rows.length==0){
            $.messager.alert("提示", "请输入节日具体时间!","info");
            return;

        }

        festivalVo.orgId = parent.config.org_Id;
        festivalVo.fesDesId = DesId;
        festivalVo.fesId = id;
        festivalVo.yearId = yearId;
        festivalVo.sumDays = sumDays;
        festivalVo.festivals=rows;
        //查询数据库，新增数据是否重复

        $.get(basePath +"/festival/fes-boolean?yearId=" + yearId+"&orgId="+orgId+"&fesDesId="+DesId+"&fesId="+fesId,
            function (data) {
                var list = eval(data);

                var num=list[0]['num'];

                if(num!=0){
                    var str = '所选年份的节日已经创建，不能重复创建！';
                    $.messager.alert("提示", str,"info");
                    return
                }else{
                    $.messager.progress({
                        title: '提示！',
                        msg:  '数据量较大，请稍候...',
                        text: '加载中.......'
                    });
                    //如果不重复，执行保存代码
                    $.postJSON(basePath + "/festival/merge", JSON.stringify(festivalVo), function (data) {
                        $.messager.progress('close')
                        $('#editFestival').window('close');
                        $("#festivalGrid").datagrid('reload');

                    })
                }
            });
    });
});
function getDate(strDate) {
    var date = eval('new Date(' + strDate.replace(/\d+(?=-[^-]+$)/,
            function (a) { return parseInt(a, 10) - 1; }).match(/\d+/g) + ')');
    return date;
}

function yearDict() {
    $.get(basePath + '/tool/find-list-by-type?type=YEAR_DICT&value=' + value, function (data) {
        yearList=data;

    });
}
function festivalDict() {
    $.get(basePath + '/tool/find-list-by-type?type=FESTIVAL_TYPE&value=' + value, function (data) {
        festivalList=data;

    });
}
Array.prototype.remove=function(obj){
    for(var i =0;i <this.length;i++){
        var temp = this[i];
        if(!isNaN(obj)){
            temp=i;
        }
        if(temp == obj){
            for(var j = i;j <this.length;j++){
                this[j]=this[j+1];
            }
            this.length = this.length-1;
        }
    }
}