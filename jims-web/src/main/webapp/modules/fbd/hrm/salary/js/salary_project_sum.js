/**
 * 工资项目金额
 * @author
 * @version 2016-08-29
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
var typeId = '';
$(function () {

    var orgId = parent.config.org_Id;

    //人员类别树
    $("#staff").treegrid({
        fit: true,
        fitColumns: true,
        striped: true,
        singleSelect: true,
        idField: "typeId",
        treeField: "typeName",
        toolbar: '#classft',
        loadMsg: '数据正在加载中，请稍后.....',
        columns: [[{
            title: '工资级别编号',
            field: "typeId",
            hidden: true
        }, {
            title: '工资级别',
            field: 'typeName',
            width: '100%'
        }]],
        onClickRow: function (rowIndex, rowData) {
            var node = $("#staff").treegrid("getSelected");
            typeId = node.typeId;
            var url = basePath + "/spm/money-list?orgId=" + orgId + "&typeId=" + typeId;
            $("#staffGrid").edatagrid("reload", url);

        }

    });

    //加载树形结构的treegrid数据
    var loadDept = function () {
        var depts = [];
        var treeDepts = [];
        var loadPromise = $.get(basePath+"/salary-ht/ht-list1?orgId=" + orgId, function (data) {
            $.each(data, function (index, item) {
                var obj = {};
                obj.typeName = item.typeName;
                obj.typeId = item.typeId;
                obj.parentId = item.parentId;
                obj.children = [];
                depts.push(obj);
            });

        });

        loadPromise.done(function () {
            for (var i = 0; i < depts.length; i++) {
                for (var j = 0; j < depts.length; j++) {
                    if (depts[i].typeId == depts[j].parentId) {
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
    function listAll() {
        $.get(basePath + '/spm/project-list?orgId=' + orgId, function (data) {
            salaryProject = data;
        });
    }

    listAll();
    //工资项目信息
    $("#staffGrid").edatagrid({
        //fit: true,
        //fitColumns: true,
        striped: true,
        singleSelect: true,
        toolbar: '#ft',
        method: 'GET',
        rownumbers: true,
        idField: 'psId',
        fit: true,
        url:  basePath + "/spm/money-list?orgId=" + orgId + "&typeId=" + typeId,
        loadMsg: '数据正在加载中，请稍后.....',
        pagination: true,//分页控件
        pageSize: 30,
        // pageList: [10, 15, 30, 50],//可以设置每页记录条数的列表
        columns: [[{
            title: "psId",
            field: "psId",
            hidden:true
        }, {
            title: "工资级别Id",
            field: "typeId",
            hidden:true
        },{
            title: "工资级别",
            field: "typeName",
            hidden:true
        },{
            title: "工资项目",
            field: "projectId",
            align: 'center',
            width: '15%',
            editor:
            {
                type: 'combobox', options: {
                mode: 'remote',
                panelHeight: '140px',
                method: 'GET',
                editable:false,
                valueField: 'projectId',
                textField: 'projectName',
                url: basePath +'/spm/project-list?orgId=' + orgId
            }

            },
            formatter: function (value, row, index) {
                for(var i=0; i< salaryProject.length; i++){
                    if(value == salaryProject[i].projectId){
                        return salaryProject[i].projectName;
                    }

                    if(value == salaryProject[i].projectName){
                        return salaryProject[i].projectName;
                    }
                }
            }
        },  {field: 'money', title: '金额（元）', width:'11%', align: 'center', editor: {
            type: 'numberspinner',
            options: {
                increment:100,
                min:0,
                max:999999999999,
                precision:2
            }
        },formatter:function(value,row,index){
            var value = row.money;
            if(value!=null)
                return '￥'+(parseFloat(value).toFixed(2)+ '').replace(/\d{1,3}(?=(\d{3})+(\.\d*)?$)/g, '$&,');
        }},{field: 'createBy', title: '创建人', width:'7%', align: 'center'},
            {field: 'createDeptname', title: '创建科室', width:'10%', align:'center'},
            {field: 'createDept', title: '创建科室编号', hidden:true},
            {field: 'createDate', title: '创建时间', width:'15%',align: 'center'},
            {field: 'updateBy', title: '更新人', width:'7%', align: 'center'},
            {field: 'updateDate', title: '更新时间', width:'15%', align: 'center'}
        ]],
        onLoadSuccess:function(data){
            $('#ft').css('display','block');
            $("#staffGrid").edatagrid('getPager').pagination({
                pageList: [10, 20, 30, 40, 50], //可以设置每页记录条数的列表
                displayMsg: '当前显示从第{from}条到第{to}条，共{total}条记录'
            });
        },
        onClickRow: function (index, row) {

        }
    });

//开始编辑行
    $("#addBtn").on('click', function () {
        var row = $("#staff").treegrid('getSelected');
        if (row ==null||!row) {

            $.messager.alert("提示", "请选择具体的工资级别进行新增！", "info");
            return;
        }
        if (row.typeId ==1) {
            $.messager.alert("提示", "请选择具体的工资级别进行新增！", "info");
            return;
        }

        $("#staffGrid").edatagrid('addRow');
    });
//保存
    $("#saveBtn").on("click", function () {

        var rows= $("#staffGrid").datagrid("getRows");

        for (var i=0;i<rows.length;i++)
        {
            $("#staffGrid").datagrid('endEdit', i);
        }
        for (var i = 0; i < rows.length; i++) {
            if(rows[i].projectId==''){
                $.messager.alert("提示", "请选择工资项目!", "info");
                return;
            }
            if(rows[i].money==''){
                $.messager.alert("提示", "请输入金额!", "info");
                return;
            }
            for (var j = i + 1; j < rows.length; j++) {
                if (rows[i].projectId == rows[j].projectId) {

                    $.messager.alert("提示", "不能添加重复的工资项目金额!", "info");
                    return;
                }
            }
        }
        var insertData = $("#staffGrid").datagrid("getChanges", "inserted");
        var updateData = $("#staffGrid").datagrid("getChanges", "updated");
        var SalaryProjectMoneyVo = {};
        SalaryProjectMoneyVo.inserted = insertData;
        SalaryProjectMoneyVo.updated = updateData;
        SalaryProjectMoneyVo.typeId=typeId;
        if (SalaryProjectMoneyVo.inserted.length > 0) {
            for (var i = 0; i < SalaryProjectMoneyVo.inserted.length; i++) {
                SalaryProjectMoneyVo.inserted[i].orgId = orgId;
            }
        }
        for (var i = 0; i < updateData.length; i++) {
            delete updateData[i]["inserted"];
            delete updateData[i]["updated"];
            delete updateData[i]["deleted"];
        }
        if (SalaryProjectMoneyVo != "") {
            $.postJSON(basePath + "/spm/merge", JSON.stringify(SalaryProjectMoneyVo), function (data) {
                if (data.data == "success") {
                    $("#staffGrid").datagrid('reload');
                }
            }, function (data) {
                $.messager.alert('提示', "保存失败!", "info");
            })
        }
    });
    //删除
    $("#delBtn").on("click", function () {
        var row = $("#staffGrid").datagrid('getSelected');
        if (row == null) {
            $.messager.alert("提示", "请选择要删除的工资项目金额!","info");
            return;
        }

        $.messager.confirm("提示", "确认删除吗?", function (r) {
            if (r) {
                $.postJSON(basePath + "/spm/del-spm" ,row.psId, function (data) {
                    $("#staffGrid").datagrid('reload');
                });
            }
        })

    });


});




