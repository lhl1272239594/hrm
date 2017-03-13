
var basePath = "/service";
var orgId= parent.config.org_Id;
var method = '';//考评方法
var methodId = '';//考评方法ID
var evaName = '';//项目名称
var methodObj = [];
var projectType=0;
var parentId='';//一级项目编码
var pid='';//ID
var rowIndex=0;
var detailReload=false;
var search=false;
$(function () {
    $("#method").combobox({     //加载考评方法
        url: basePath + '/dict/find-list-by-type?type=' + 'EVALUATION_METHOD',
        valueField: 'label',
        textField: 'value',
        method: 'GET',
        onLoadSuccess:function () {
            var data=$("#method").combobox('getData');
            $.each(data, function (index, item) {
                methodObj[item.label]=item.value;
            });
        }
    });
    $("#dep").combotree({
        fitColumns: true,
        striped: true,
        singleSelect: true,
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
                $('#dep').combotree('clear');
                $.messager.alert("提示", "请选择子分类", "info");
            }
        }
    });
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

            $("#dep").combotree('loadData', treeDepts);
        })
    }
    loadDept();
    $("#projectGrid").datagrid({
        toolbar: '#tb',
        width: '100%',
        height: '100%',
        nowrap:false,
        striped: true,
        border: true,
        method: 'get',
        loadMsg: '数据正在加载中，请稍后.....',
        pagination: true,//分页控件
        pageSize: 30,
        collapsible: false,//是否可折叠的
        fit: true,//自动大小
        url: basePath + '/project/firstLevelList?orgId=' + orgId +"&methodId="+methodId+"&name="+name,
        remoteSort: false,
        idField: 'id',
        singleSelect: true,//是否单选
        rownumbers: true,//行号
        view: detailview,
        columns: [[
            {field: 'name', title: '考评项目名称', width: '15%', align: 'center'},
            {
                field: 'state', title: '状态', width: '10%', align: 'center',
                formatter: function (value, row, index) {
                    if (value == "1") {
                        return "启用";
                    }
                    if (value == "0") {
                        return "停用";
                    }
                }
            },
            {field: 'score', title: '考评分值', width: '10%', align: 'center'},
            {
                field: 'methodId', title: '考评方法', width: '10%', align: 'center',
                formatter: function (value, row, index) {
                    return methodObj[value];
                }
            },
            {field: 'depName', title: '制定部门', width: '10%', align: 'center'},
            {field: 'createBy', title: '创建人', width: '10%', align: 'center'},
            {field: 'createDate', title: '创建时间', width: '10%', align: 'center'},
            {field: 'h', title: '操作', width: '25%', align: 'center',
                formatter: function (value, row, index) {
                    var html='<span class="btn-border"><a href="#" name="btn-add" style="padding: 0 3px;" class="easyui-linkbutton" iconCls="icon-add"  onclick="add(\''+row.id+'\','+index+')">新增</a></span>' +
                        '<span class="btn-border"><a href="#" name="btn-add" style="padding: 0 3px;" class="easyui-linkbutton" iconCls="icon-edit"  onclick="edit1(1,\''+row.id+'\','+index+')">修改</a></span>' +
                        '<span class="btn-border"><a href="#" name="btn-del" style="padding: 0 3px;" class="easyui-linkbutton" iconCls="icon-remove"  onclick="del(1,\''+row.id+'\')">删除</a></span>';
                    if(row.state=='0'){
                        html+='<span class="btn-border"><a href="#" name="btn-ok"  style="padding: 0 3px;" class="easyui-linkbutton" iconCls="icon-ok"  onclick="ok(1,\''+row.id+'\')">启用</a></span>';
                    }
                    if(row.state=='1'){
                        html+= '<span class="btn-border"><a href="#" name="btn-no" style="padding: 0 3px;"  class="easyui-linkbutton" iconCls="icon-no"  onclick="no(1,\''+row.id+'\')">停用</a></span>';
                    }
                    return html;

                }}

        ]],onLoadSuccess:function(data){
            icon_init();
            $("#add").css('display','block');
            $('#projectGrid').datagrid('fixRowHeight');
            if(detailReload){
                $("#projectGrid").datagrid('expandRow',rowIndex);
                detailReload=false;
            }
        },detailFormatter:function(index,row){
            return '<div style="padding:2px"><table id="ddv-' + index + '"></table></div>';
        },onExpandRow:function(index,row){//注意3
            rowIndex=index;
            $('#ddv-'+index).datagrid({
                url: basePath + '/project/secondLevelList?id=' + row.id +"&orgId="+orgId,
                width: '100%',
                method: 'get',
                fitColumns:true,
                striped:true,
                singleSelect:true,
                rownumbers:true,
                loadMsg:'',
                height:'auto',
                columns:[[

                    {field: 'name', title: '项目名称', width: '20%', align: 'center'},
                    {
                        field: 'state', title: '状态', width: '10%', align: 'center',
                        formatter: function (value, row, index) {
                            if (value == "1") {
                                return "启用";
                            }
                            if (value == "0") {
                                return "停用";
                            }
                        }
                    },
                    {field: 'score', title: '考评分值', width: '10%', align: 'center'},
                    {
                        field: 'methodId', title: '考评方法', width: '10%', align: 'center',
                        formatter: function (value, row, index) {
                            return methodObj[value];
                        }
                    },
                    {field: 'depName', title: '制定部门', width: '10%', align: 'center'},
                    {field: 'createBy', title: '创建人', width: '10%', align: 'center'},
                    {field: 'createDate', title: '创建时间', width: '10%', align: 'center'},
                    {field: 'h', title: '操作', width: '20%', align: 'center',
                        formatter: function (value, row, index) {
                            var html='<span class="btn-border"><a href="#" name="btn-del" style="padding: 0 3px;" class="easyui-linkbutton" iconCls="icon-edit"  onclick="edit1(2,\''+row.id+'\',\''+index+'\','+rowIndex+')">修改</a></span>' +
                                '<span class="btn-border"><a href="#" name="btn-del" style="padding: 0 3px;" class="easyui-linkbutton" iconCls="icon-remove"  onclick="del(2,\''+row.id+'\','+rowIndex+')">删除</a></span>';
                            if(row.state=='0'){
                                html+='<span class="btn-border"><a href="#" name="btn-ok"  style="padding: 0 3px;" class="easyui-linkbutton" iconCls="icon-ok"  onclick="ok(2,\''+row.id+'\','+rowIndex+')">启用</a></span>';
                            }
                            if(row.state=='1'){
                                html+= '<span class="btn-border"><a href="#" name="btn-no" style="padding: 0 3px;"  class="easyui-linkbutton" iconCls="icon-no"  onclick="no(2,\''+row.id+'\','+rowIndex+')">停用</a></span>';
                            }
                            return html;
                        }}
                ]],
                onResize:function(){
                    $('#projectGrid').datagrid('fixDetailRowHeight',index);
                },
                onLoadSuccess:function(){
                    icon_init();
                    setTimeout(function(){
                        $('#projectGrid').datagrid('fixDetailRowHeight',index);
                    },0);
                    $('#ddv-'+index).datagrid('fixRowHeight');
                }
            });
            $('#projectGrid').datagrid('fixDetailRowHeight',index);
        }
    });
    $("#projectGrid").datagrid('getPager').pagination({

        pageList: [10, 20, 30, 40, 50], //可以设置每页记录条数的列表
        displayMsg: '当前显示从第{from}条到第{to}条，共{total}条记录',

    });
    //配置窗口
    $("#add").window({
        closed: true,
        modal: true,
        collapsible: true,
        minimizable: false,
        maximizable: true,
        resizable: true,
        onClose: function () {
            $("#projectForm").form('reset');
            projectType=0;
        },
        onOpen: function () {

        }
    });
    //新增一级项目
    $("#addOneLevel").on("click", function () {
        $("#add").window({title:"新增一级项目"});
        $("#add").window('open');
        projectType=1;//新增一级项目
        parentId='';
        pid='';
    });

    //保存项目
    $("#saveBtn").on('click', function () {
        //获取项目名称
        var name=$("#name").val();
        if(name==''||name.indexOf(" ") >=0){
            $.messager.alert('提示', '请填写项目名称', 'info');
            return;
        }
        if(getRealLen(name)>100){
            $.messager.alert("提示","项目名称输入过长！",'info');
            return;
        }
        //获取考评方法
        var method=$("#method").combobox('getValue');
        if(method==null||method==''){
            $.messager.alert('提示', '请选择考评方法', 'info');
            return;
        }
        var dep=$("#dep").combotree('getValue');
        if(dep==null||dep==''){
            $.messager.alert('提示', '请选择制定部门', 'info');
            return;
        }

        var project = {};
        project.name = $("#name").val();
        project.orgId = orgId;
        project.id = pid;
        project.methodId = method;
        project.depId = dep;
        project.lx = projectType;
        project.score = 0;
        project.createBy = parent.config.persion_Id;
        if(projectType==2){
            project.parentId = parentId;
        }
        $.postJSON(basePath + "/project/saveProject", JSON.stringify(project), function (data) {
            if (data.data == "success") {
                if(data.code=="hasName"){
                    $.messager.alert('提示', '该项目名称已存在', 'info');
                }
                if(projectType==1){
                    $("#projectGrid").datagrid('reload');
                }
                if(projectType==2){
                    $("#projectGrid").datagrid('reload');
                    detailReload=true;

                    //$('#ddv-'+rowIndex).datagrid('reload');
                }
                if(data.code=="success"){
                    $("#add").window('close');
                }
                $("#method").combobox('clear');
            }
        }, function (data) {
            $.messager.alert('提示', '保存失败', 'info');
        });
    });

    //取消
    $("#cancelBtn").on('click', function () {
        $("#add").window('close');
    });


});

//按钮图标初始化
function icon_init() {
    $("a[name='btn-add']").linkbutton({iconCls:'icon-add'});
    $("a[name='btn-del']").linkbutton({iconCls:'icon-remove'});
    $("a[name='btn-ok']").linkbutton({iconCls:'icon-ok'});
    $("a[name='btn-no']").linkbutton({iconCls:'icon-no'});
}

//新增二级项目
function add(Id,index) {
    rowIndex=index;
    $("#add").window({title:"新增二级项目"});
    $("#add").window('open');
    projectType=2;//新增一级项目
    parentId=Id;
    pid='';
}
//删除
function del(lx,id,index) {
    rowIndex=index;
    $.get('/service/project/checkProjectIsUsed?id='+id+'&lx='+lx, function (data) {
        if (data.data == "success") {
            if (data.code == "isUsed") {
                if(lx==1){
                    $.messager.alert("提示", "该项目下有部分二级项目正在使用中!", "info");
                }
                if(lx==2){
                    $.messager.alert("提示", "该项目正在使用中!", "info");
                }
            }
            if (data.code == "success") {
                if(lx==1){
                    $.messager.confirm("提示", "删除一级项目将删除该项目下的二级项目，确认删除该项目吗?", function (r) {
                        if (r) {
                            edit(lx,'del',id);
                        }
                    })
                }
                if(lx==2){
                    $.messager.confirm("提示", "确认删除该项目吗?", function (r) {
                        if (r) {
                            edit(lx,'del',id);
                        }
                    })
                }
            }
        }
    });

}
//启用
function ok(lx,id,index) {
    rowIndex=index;
    $.messager.confirm("提示", "确认启用该项目吗?", function (r) {
        if (r) {
            edit(lx,'ok',id);
        }
    })
}
//停用
function no(lx,id,index) {
    rowIndex=index;
    $.get('/service/project/checkProjectIsUsed?id='+id+'&lx='+lx, function (data) {
        if (data.data == "success") {
            if (data.code == "isUsed") {
                if(lx==1){
                    $.messager.alert("提示", "该项目下有部分二级项目正在使用中!", "info");
                }
                if(lx==2){
                    $.messager.alert("提示", "该项目正在使用中!", "info");
                }
            }
            if (data.code == "success") {
                if(lx==1){
                    $.messager.confirm("提示", "停用一级项目将停用该项目下的二级项目，确认停用该项目吗?", function (r) {
                        if (r) {
                            edit(lx,'no',id);
                        }
                    })
                }
                if(lx==2){
                    $.messager.confirm("提示", "确认停用该项目吗?", function (r) {
                        if (r) {
                            edit(lx,'no',id);
                        }
                    })
                }
            }
        }
    });

}
function edit1(lx,id,Index,parentIndex) {
    var title;
    var row;
    var parentRow;
    var rows=$("#projectGrid").datagrid('getRows');
    if(lx==1){
        title='修改一级项目';
        projectType=1;
        row=rows[Index];
    }
    if(lx==2){
        title='修改二级项目';
        projectType=2;
        parentRow=rows[parentIndex];
        parentId=parentRow.id;
        rows=$('#ddv-'+parentIndex).datagrid('getRows');
        row=rows[Index];
    }
    pid=row.id;
    $("#name").textbox('setValue',row.name);
    $("#method").combobox('setValue',row.methodId);
    $("#dep").combotree('setValue',row.depId);
    $("#add").window({title:title});
    $("#add").window('open');
}
function edit(lx,type,id) {
    var project = {};
    project.id = id;
    project.lx = lx;
    project.type = type;
    project.orgId = orgId;

    $.postJSON(basePath + "/project/editProject", JSON.stringify(project), function (data) {
        if(data.data=="success"){

            if(lx==2){
                detailReload=true;
                //$('#ddv-'+rowIndex).datagrid('reload');
            }
            $("#projectGrid").datagrid('reload');
        }
        else{
            $.messager.alert("提示", "修改失败", "info");
        }
    });
}
