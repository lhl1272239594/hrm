/**
 * 招聘查询
 * @author
 * @version 2016-09-25
 */
var page='1';
var rows='30';
var orgId= parent.config.org_Id;
var personId= parent.config.persion_Id;
var name = '';
var deptId = '';
var post = '';
var employ = {};
var orgCount=0;
var deptIds=parent.orgids;
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
        url: basePath + '/employ/employ-search?orgId=' + orgId + '&name=' + name + '&deptId=' + deptId + '&post=' + post+ '&deptIds=' + deptIds,
        remoteSort: false,
        //idField: 'employId',
        singleSelect: true,//是否单选
        rownumbers: true,//行号
        columns: [[
           /* {field: 'ck', title: '', checkbox: true},*/
            {field: 'employId', title: '', hidden: true},
            {field: 'orgId', title: '组织机构编码', hidden: true},
            {field: 'orgName', title: '招聘机构', width: '13%', align: 'center'},
            {field: 'deptId', title: '招聘科室ID', hidden: true},
            {field: 'deptName', title: '招聘科室', width: '14%', align: 'center'},
            {field: 'name', title: '招聘主题', width: '18%', align: 'center'},
            /*{field: 'required', title: '任职要求', width: '30%', align: 'center'},*/

            {field: 'post', title: '招聘岗位', width: '10%', align: 'center'},
            {field: 'property', title: '招聘性质', width: '10%', align: 'center'},
            {field: 'education', title: '学历要求', width: '10%', align: 'center'},
            {field: 'experience', title: '工作经验', width: '10%', align: 'center'},
            {field: 'salary', title: '薪资', width: '10%', align: 'center'},
            {field: 'province', title: '工作省份', hidden: true},
            {field: 'city', title: '工作城市', hidden: true},
            {field: 'county', title: '工作县区',hidden: true},
            {field: 'address1', title: '详细地址',hidden: true},
            {field: 'address', title: '工作地址', width:'24%', align: 'center'},
            {field: 'tel', title: '联系电话', width: '10%', align: 'center'},
            {field: 'email', title: '联系邮箱', width: '10%', align: 'center'},
            {
                field: 'until', title: '有效期至', width: '10%', align: 'center',
                formatter: formatDatebox1
            },
            /*{field: 'createBy', title: '创建人', width: '8%', align: 'center'},
            {field: 'createDate', title: '创建时间', width: '14%', align: 'center'},
            {field: 'updateBy', title: '更新人', width: '7%', align: 'center'},
            {field: 'updateDate', title: '更新时间', width: '14%', align: 'center'},*/
            {field: 'createDeptname', title: '创建科室', width:'10%', align:'center'},
            {field: 'createDept', title: '创建科室编号', hidden:true},
            {field: 'publishBy', title: '发布人', width: '7%', align: 'center'},
            {field: 'publishDate', title: '发布时间', width: '14%', align: 'center',
                formatter:formatDatebox
            }
        ]],
        onLoadSuccess: function (data) {
            $('#newDialog').css('display', 'block');
            $('#tb').css('display', 'block');
            $("#dataGrid").datagrid('getPager').pagination({
                pageList: [10, 20, 30, 40, 50], //可以设置每页记录条数的列表
                displayMsg: '当前显示从第{from}条到第{to}条，共{total}条记录',
                onSelectPage: function (pageNumber, pageSize) {
                    var state = $('#dataGrid').data('datagrid');
                    var opts = state.options;
                    page = opts.pageNumber = pageNumber;
                    rows = opts.pageSize = pageSize;
                    searchData(page, rows);
                }
            });
        }
    });


    //格式化时间
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
    //格式化时间
    function formatDatebox1(value) {
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

    //查询、新增条件：招聘岗位
    $("#EMPLOY_POST1").combobox({
        //url: basePath + '/dict/find-list-by-type?type=' + 'POST_DICT',
        idField: 'value',
        textField: 'label',
        value: '请选择',
        panelWidth: '136px',
        panelHeight: 'auto',
        method: 'GET',
        loadMsg: '数据正在加载',
        mode: 'remote',
        onLoadSuccess:function(data){
            orgCount=data.length;
        },
        onShowPanel:function(){
            //动态调整高度
            if(orgCount>13){
                $(this).combobox('panel').height(285);
            }
            $.get(basePath + '/dict/find-list-by-type?type=' + 'POST_DICT', function (data) {
                $("#EMPLOY_POST1").combobox('loadData',data);
            });
        }
    });
    //查询、新增条件:招聘科室树选择
    $("#EMPLOY_DEPT_ID1").combotree({
        panelWidth: '160px',
        fitColumns: true,
        striped: true,
        singleSelect: true,
        value: '请选择',
        text: '请选择',
        loadMsg: '数据正在加载中，请稍后.....',
        columns: [[{
            title: 'id',
            field: "id",
            hidden: true
        }, {
            title: '科室名称',
            field: 'text',
            width: '100%'
        }]],
        onSelect: function (node) {
            //返回树对象
            var tree = $(this).tree;
            //选中的节点是否为叶子节点,如果不是叶子节点,清除选中
            var isLeaf = tree('isLeaf', node.target);
            if (!isLeaf) {
                //清除选中
                $('#EMPLOY_DEPT_ID1').combotree('clear');
                $.messager.alert("提示", "请选择具体科室!", "info");
            }
        }
    });

    var loadDept1 = function () {

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
            $("#EMPLOY_DEPT_ID1").combotree('loadData', treeDepts);
        })
    };
    //loadDept();
    loadDept1();

    /**
     * 查询条件搜索
     */
    $("#searchBtn").on("click", function () {
        search=true;
        searchData(page, rows);
    });

    var searchData = function (page, rows) {
        //获取数据
        name = $("#NAME1").textbox('getValue');
        deptId = $("#EMPLOY_DEPT_ID1").combotree('getValue');
        post = $("#EMPLOY_POST1").combobox('getText');
        if(post=='请选择'){
            post='';
        }
       /* $.get(basePath + '/employ/employ-search?orgId=' + orgId + '&name=' + name + '&deptId=' + deptId + '&post=' + post+ '&page=' + page+ '&rows=' + rows, function (data) {
            $("#dataGrid").datagrid('loadData', data);*/
            $("#dataGrid").datagrid('reload', basePath + '/employ/employ-search?orgId=' + orgId + '&name=' + name + '&deptId=' + deptId + '&post=' + post+ '&page=' + page+ '&rows=' + rows);

            if(search){
                search=false;
                if(page!='1') {
                    $("#dataGrid").datagrid('getPager').pagination('select', 1);
                }
            }
            $("#dataGrid").datagrid('clearSelections');

    };
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
        $("#NAME1").textbox('clear');
        $("#NAME1").textbox("setValue", "");
        $("#EMPLOY_DEPT_ID1").combobox('clear');
        $("#EMPLOY_DEPT_ID1").combobox("setValue", "");
        $("#EMPLOY_POST1").combobox('clear');
        $("#EMPLOY_POST1").combobox("setValue", "请选择");
    };

    /**
     * 新增弹出框
     */
    $("#newDialog").dialog({
        title: '新增招聘',
        modal: true,
        closed: true,
        collapsible: true,
        minimizable: false,
        maximizable: true,
        resizable: true
    });

    /**
     * 详情
     */
    $("#infoBtn").on('click', function () {
        var row = $("#dataGrid").datagrid("getSelected");
        if (!row) {
            $.messager.alert("提示", "请选择一条数据!", 'info');
            return;
        }
        $("#newDialog").dialog("setTitle", "招聘信息详情").dialog("open");
        $("#submitBtn").hide();
        $("#EMPLOY_ID").val(row.employId);
        $("#province").textbox("setValue", row.province);
        $("#citys").textbox("setValue", row.city);
        $("#county").textbox("setValue", row.county);
        /*if(row.province==null||row.province==''){
            $("#province").find("option:selected").text('');
        }
        else{
            $("#province").find("option:selected").text(row.province);
        }
        if(row.city==null||row.city==''){
            $("#citys").find("option:selected").text('');
        }
        else{
            $("#citys").find("option:selected").text(row.city);
        }
        if(row.county==null||row.county==''){
            $("#county").find("option:selected").text('');
        }
        else{
            $("#county").find("option:selected").text(row.county);
        }*/
        $("#NAME").textbox("setValue", row.name);
        $("#EMPLOY_DEPT_ID").textbox("setValue", row.deptName);
       /* $("#EMPLOY_DEPT_ID").combobox("setValue", row.deptId);
        $("#EMPLOY_DEPT_ID").combobox("setText", row.deptName);*/
        $("#EMPLOY_POST").textbox("setValue", row.post);
        //$("#EMPLOY_POST").combobox("setText", row.post);
        $("#EMPLOY_PROPERTY").textbox("setValue", row.property);
        //$("#EMPLOY_PROPERTY").combobox("setText", row.property);
        $("#EDUCATION_REQUIRE").textbox("setValue", row.education);
        //$("#EDUCATION_REQUIRE").combobox("setText", row.education);
        $("#WORK_EXPERIENCE").textbox("setValue", row.experience);
        //$("#WORK_EXPERIENCE").combobox("setText", row.experience);
        $("#SALARY").textbox("setValue", row.salary);
        //$("#SALARY").combobox("setText", row.salary);
        $("#WORK_PLACE").textbox("setValue", row.address1);
        $("#TEL").textbox("setValue", row.tel);
        $("#EMAIL").textbox("setValue", row.email);
        $("#WORK_REQUIRE").textbox("setValue", row.required);
        $("#TIME_UNTIL").textbox("setValue", row.until);
        //$("#TIME_UNTIL").datebox("setValue", row.until);
        //元素不可编辑
        $("#EMPLOY_ID").attr("readonly", true);
        $("#province").textbox('textbox').attr("readonly", true);
        //$("#province").attr("readonly", "readonly");
        $("#citys").textbox('textbox').attr("readonly", true);
        //$("#citys").attr("readonly", "readonly");
        $("#county").textbox('textbox').attr("readonly", true);
        //$("#county").attr("readonly", "readonly");
        $("#NAME").textbox('textbox').attr("readonly", true);
        $("#EMPLOY_DEPT_ID").textbox('textbox').attr("readonly", true);
        //$("#EMPLOY_DEPT_ID").attr("readonly", "readonly");
        $("#EMPLOY_POST").textbox('textbox').attr("readonly", true);
        //$("#EMPLOY_POST").attr("readonly", "readonly");
        $("#EMPLOY_PROPERTY").textbox('textbox').attr("readonly", true);
        //$("#EMPLOY_PROPERTY").attr("readonly", "readonly");
        $("#WORK_EXPERIENCE").textbox('textbox').attr("readonly", true);
        //$("#WORK_EXPERIENCE").attr("readonly", "readonly");
        $("#SALARY").textbox('textbox').attr("readonly", true);
        //$("#SALARY").attr("readonly", "readonly");
        $("#WORK_PLACE").textbox('textbox').attr("readonly", true);
        $("#TEL").textbox('textbox').attr("readonly", true);
        $("#EMAIL").textbox('textbox').attr("readonly", true);
        $("#WORK_REQUIRE").textbox('textbox').attr("readonly", true);
        $("#TIME" ).css("display", "none");
    });
});
