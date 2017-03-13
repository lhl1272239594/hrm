/**
 * 职业变动报表
 * @author
 * @version 2016-11-03
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
var year='';
var month='';
var page='1';
var rows='30';
var deptIds=parent.orgids;
var search=false;
$(function () {
    $.messager.progress({
        title: '提示！',
        msg: '数据量较大，请稍候...',
        text: '加载中.......'
    });
    //部门树
    $("#staff").treegrid({
        width: '100%',
        height: '100%',
        //fitColumns: true,
        striped: true,
        singleSelect: true,
        rownumbers: false,//行号
        idField: "id",
        treeField: "deptName",
        //toolbar: '#classft',
        loadMsg: '数据正在加载中，请稍后.....',
        columns: [[{
            title: 'id',
            field: "id",
            hidden: true
        }, {
            title: '科室名称',
            field: 'deptName',
            width: '200%'
        }]],
        onClickRow: function (rowIndex, rowData) {
            var node = $("#staff").treegrid("getSelected");
            deptId = node.id;
            deptName = node.deptName;
            var url = basePath + '/salary-data/career-search?orgId='+orgId+'&personId='+personId+'&deptId='+deptId+'&year='+year+'&month='+month+'&deptIds='+deptIds;
            $("#staffGrid").datagrid("reload", url);
        }
    });

    //加载树形结构的treegrid数据
    var loadDept = function () {
        var depts = [];
        var treeDepts = [];
        var loadPromise = $.get("/service/salary-data/list?orgId=" + orgId+"&deptIds="+deptIds, function (data) {
            $.each(data, function (index, item) {
                var obj = {};
                obj.deptName = item.deptName;
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
            $("#staff").treegrid('loadData', treeDepts);
        })
    };
    loadDept();

    //职业信息列表
    $("#staffGrid").datagrid({
        width: '100%',
        height: '100%',
        nowrap:false,
        border: true,
        striped: true,
        singleSelect: true,
        rownumbers: true,
        toolbar: '#ft',
        method: 'GET',
        idField: 'personId',
        fit: true,
        url: basePath + '/salary-data/career-search?orgId='+orgId+'&personId='+personId+'&deptId='+deptId+'&year='+year+'&month='+month+'&deptIds='+deptIds,
        loadMsg: '数据正在加载中，请稍后.....',
        pagination: true,//分页控件
        pageSize: 30,
        pageList: [10, 20, 30,40,50],//可以设置每页记录条数的列表
        columns: [[
           /* {field: 'ck', title: '', checkbox: true},*/
         {
            title: "职业编号",
            field: "careerId",
             hidden: true
        },{
                title: "人员编号",
                field: "personId",
                hidden: true
            },{
            title: "姓名",
            field: "personName",
            align: 'center',
            width: '10%'

        }, {
                title: "部门编号",
                field: "deptId",
                hidden: true

            },{
                title: "科室",
                field: "deptName",
                align: 'center',
                width: '11%'
            },{
                title: "性别",
                field: "sex",
                align: 'center',
                width: '6%'

            },{
                title: "身份证号",
                field: "cardNo",
                align: 'center',
                width: '16%'
            },{field: 'inDate', title: '入职时间', width:'11%', align: 'center',formatter: formatDatebox},
            {field: 'outDate', title: '离职时间', width:'11%', align: 'center',formatter: formatDatebox},
            {
                title: "职称类别",
                field: "title",
                align: 'center',
                width: '10%'
            },{
                title: "职称级别",
                field: "titleLevel",
                align: 'center',
                width: '10%'
            },
            {
                title: "备注",
                field: "remark",
                align: 'center',
                width: '16%'
            },
            /*{field: 'createBy', title: '创建人', width:'7%', align: 'center'},*/
            {field: 'createDate', title: '变动时间', width:'15%',align: 'center'}
            // {field: 'updateBy', title: '更新人', width:'7%', align: 'center'},
            // {field: 'updateDate', title: '更新时间', width:'15%', align: 'center'}
        ]],
        onLoadSuccess:function(data){
            $('#ft').css('display','block');
            $.messager.progress('close');
            $("#staffGrid").datagrid('getPager').pagination({
                pageList: [10, 20, 30, 40, 50], //可以设置每页记录条数的列表
                displayMsg: '当前显示从第{from}条到第{to}条，共{total}条记录',
                onSelectPage: function (pageNumber, pageSize) {
                    var state = $('#staffGrid').data('datagrid');
                    var opts = state.options;
                    page=opts.pageNumber = pageNumber;
                    rows=opts.pageSize = pageSize;
                    searchData(page,rows);
                }
            });
        }
    });
    //格式化时间：年月日
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

    /**
     * 查询条件搜索
     */
    $("#searchBtn").on("click", function () {
        search=true;
        searchData(page,rows);
    });

    var searchData= function (page,rows){
        //查询条件
        var personId = $("#NAME").textbox('getValue');
        /*var node = $("#staff").treegrid("getSelected");
        if(node&&node!=''&&node!=null);
        {
            deptId = node.id;
        }*/
        var year =$("#YEAR").combobox('getValue');
        var month =$("#MONTH").combobox('getValue');
        $("#staffGrid").datagrid('reload', basePath + '/salary-data/career-search?orgId='+orgId+'&personId='+personId+'&deptId='+deptId+'&year='+year+'&month='+month+'&deptIds='+deptIds+'&rows=' + rows+ '&page=' + page);

        if(search){
                search=false;
            if(page!='1') {
                $("#staffGrid").datagrid('getPager').pagination('select', 1);
            }
            }
            $("#staffGrid").datagrid('clearSelections');
    };
    //展示全部
    $("#searchAllBtn").on("click", function () {
        deptId = '';
        persionId= '';
        year='';
        month='';
        var url = basePath + '/salary-data/career-search?orgId='+orgId+'&personId='+personId+'&deptId='+deptId+'&year='+year+'&month='+month+'&deptIds='+deptIds;
        $("#staffGrid").datagrid("reload", url);

        });

    /**
     * 清空方法
     */
    var clearKey = function () {
        $("#NAME").textbox('clear');
        $("#NAME").textbox("setValue","");
        personId='';
        $("#MONTH").textbox('clear');
        $("#MONTH").textbox("setValue","");
        $("#MONTH").textbox("setText","全部");
        $("#YEAR").textbox('clear');
        $("#YEAR").textbox("setValue","");
        $("#YEAR").textbox("setText","全部");
    };
    /**
     * 清空查询条件
     */
    $("#clearBtn").on("click", function () {
        clearKey();
        page='1';
    });


});




