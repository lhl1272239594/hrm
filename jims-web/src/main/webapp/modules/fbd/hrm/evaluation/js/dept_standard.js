var basePath = "/service";
var orgId = parent.config.org_Id;
var deptId = "";
var deptName = "";
var depts=[];//部门数组
var deptsName=[];//部门数组
var treeDepts = [];//部门树
var orgCount = 0;
var pcode='';//项目二级编码
var userArray=[];
var id='';
$(function () {
    $("#deptTree").tree({
        width: '100%',
        height: '100%',
        fitColumns: true,
        striped: true,
        singleSelect: false,
        checkbox:true,
        idField: "treeId",
        treeField: "treeDes",
        loadMsg: '数据正在加载中，请稍后.....',
        columns: [[{
            title: '序号',
            field: "treeId",
            hidden: true
        }, {
            title: '科室列表',
            field: 'treeDes',
            width: '100%'
        }]],
        onClick: function (node) {
            deptId=node.id;
            var url = basePath + "/deptStandard/findListByid?id=" + node.id;
            $("#standard").datagrid("reload", url);

        },onCheck:function (node,checked) {
            if(checked){
                if(node.type=='1'){
                    $("#deptTree").tree('uncheck',node.target);
                }
            }
        }
    });
    var loadDept = function () {
        var load = $.get("/service/tool/find-user-tree?orgId=" + orgId, function (data) {
            $.each(data, function (index, item) {
                var obj = {};
                obj.userId = item.treeId;
                obj.name = item.treeDes;
                obj.id = item.treeId;
                obj.text = item.treeDes;
                obj.parent = item.parentId;
                obj.type = item.treeType;//1机构,2部门，3人员
                obj.depId = '';//机构ID
                obj.depName = '';//机构名称
                obj.children = [];
                if(item.treeType!='3'){
                    deptsName[obj.userId]=obj.name;
                    depts.push(obj);
                }
            });

        });
        load.done(function () {
            for (var i = 0; i < depts.length; i++) {
                for (var j = 0; j < depts.length; j++) {
                    if (depts[i].id == depts[j].parent) {
                        depts[i].children.push(depts[j]);
                    }
                }
                if (depts[i].children.length > 0 && !depts[i].parent) {
                    treeDepts.push(depts[i]);
                }

                if (!depts[i].parent && depts[i].children <= 0) {
                    treeDepts.push(depts[i])
                }
            }
            $('#deptTree').tree('loadData',treeDepts);
        });
    }
    loadDept();
    $("#standard").datagrid({
        toolbar: '#mould-tb',
        width: '100%',
        height: '100%',
        nowrap:false,
        striped: true,
        border: true,
        method: 'get',
        loadMsg: '数据正在加载中，请稍后.....',
        pagination: false,//分页控件
        collapsible: false,//是否可折叠的
        fit: true,//自动大小
        //url: basePath + "/templet/templetStandard?orgId=" + orgId + "&templetId=" + templetId,
        remoteSort: false,
        idField: 'id',
        singleSelect: false,//是否单选
        rownumbers: true,//行号
        columns: [[
            {field: 'ck', title: '', checkbox: true},
            {field: 'pname', title: '一级项目名称', width: '10%', align: 'center'},
            {field: 'sname', title: '二级项目名称', width: '10%', align: 'center'},
            {field: 'name', title: '考评标准名称', width: '57%', align: 'left',halign: 'center'},
            {field: 'score', title: '考评分值', width: '10%', align: 'center'},
            {
                field: 'kpi', title: '是否KPI', width: '10%', align: 'center',
                formatter: function (value, row, index) {
                    if (value == "1") {
                        return "是";
                    }
                    if (value == "0") {
                        return "否";
                    }
                }
            }

        ]],onLoadSuccess:function(data){
            mergeCellsByField("standard", "pname,sname");
            initScore();
        }
    });
    //打开新增标准窗口
    $("#addStandardBtn").on('click', function () {
        if(deptId==''){
            $.messager.alert("提示", "请选择科室!", 'info');
            return;
        }
        $("#standardWin").window('open');
        $("#projectTree").treegrid({
            width: 'auto',
            height: '100%',
            fitColumns: true,
            striped: true,
            singleSelect: true,
            idField: "id",
            treeField: "name",
            loadMsg: '数据正在加载中，请稍后.....',
            columns: [[{
                title: 'id',
                field: "id",
                hidden: true
            }, {
                title: '项目名称',
                field: 'name',
                width: '100%'
            }]],
            onClickRow: function (rowIndex, rowData) {
                //返回树对象
                if(rowIndex.type=='1'){
                    pcode='';
                    $.messager.alert("提示", "请选择二级项目", "info");
                }
                if (rowIndex.type=='2') {
                    pcode=rowIndex.id;
                    reloadStandard();

                }
            }
        });
        var loadProject = function () {

            var pro = [];
            var treePro = [];
            var loadPromise = $.get("/service/templet/projectList?orgId=" + orgId, function (data) {
                $.each(data, function (index, item) {
                    var obj = {};
                    obj.name = item.name;
                    obj.id = item.id;
                    obj.type = item.type;
                    obj.parent = item.parentId;
                    obj.score = item.score;
                    obj.children = [];
                    obj.parentName='';
                    pro.push(obj);
                });
            });
            loadPromise.done(function () {
                for (var i = 0; i < pro.length; i++) {
                    for (var j = 0; j < pro.length; j++) {
                        if (pro[i].type=='1'&&!pro[i].parent&&pro[j].type=='2'&&pro[i].id == pro[j].parent) {
                            pro[j].parentName=pro[i].name;
                            pro[i].children.push(pro[j]);
                        }
                    }
                    if (pro[i].children.length > 0 &&!pro[i].parent) {
                        treePro.push(pro[i]);
                    }

                    if (!pro[i].parent&& pro[i].children <= 0) {
                        treePro.push(pro[i])
                    }
                }
                $("#projectTree").treegrid('loadData', treePro);
                $("#projectTree").treegrid('clearSelections');
                pcode='';
                reloadStandard();
            })
        }
        loadProject();
        $("#standardGrid").datagrid({
            toolbar: '#tb1',
            width: '100%',
            height: '100%',
            nowrap:false,
            striped: true,
            border: true,
            method: 'get',
            loadMsg: '数据正在加载中，请稍后.....',
            pagination: false,//分页控件
            pageSize: 30,
            collapsible: false,//是否可折叠的
            fit: true,//自动大小
            //url: basePath + "/templet/standardByProject?orgId=" + orgId + "&pcode=" + pcode + "&templetId=" + templetId,
            remoteSort: false,
            idField: 'id',
            singleSelect: false,//是否单选
            rownumbers: true,//行号
            columns: [[
                {field: 'ck', title: '', checkbox: true},
                {field: 'name', title: '考评标准名称', width: '78%', align: 'left',halign: 'center',formatter : function (value, row, index) {
                    if (value.length > 145) value = value.substr(0, 145) + "...";
                    return value;
                }},
                {field: 'score', title: '考评分值', width: '10%', align: 'center'},
                {
                    field: 'kpi', title: '是否KPI', width: '10%', align: 'center',
                    formatter: function (value, row, index) {
                        if (value == "1") {
                            return "是";
                        }
                        if (value == "0") {
                            return "否";
                        }
                    }
                }
            ]],onLoadSuccess:function(data){
            }
        });
    });
    /**
     * 新增科系名称
     */
    $("#importWin").window({
        title: '选择模板',
        modal: true,
        closed: true,
        collapsible: true,
        minimizable: false,
        maximizable: true,
        resizable: false,
        onClose: function () {

        },
        onOpen: function () {
            //模板名称
            $('#name').combobox({
                valueField: 'id',
                textField: 'name',
                method: 'GET',
                onShowPanel: function() {
                    // 动态调整高度
                    if (orgCount > 13) {
                        $(this).combobox('panel').height(285);
                    }
                }
            });
            //模板分类
            $('#type').combobox({
                url: basePath + '/deptStandard/getMouldType',
                valueField: 'id',
                textField: 'pname',
                method: 'GET',
                onChange: function (id, oldValue) {
                    $.get(basePath + '/deptStandard/getMouldName?pid='+id,
                        function (data) {
                            $("#name").combobox('loadData',data);
                            $('#name').combobox('setValue','');
                        }
                    );
                },
                onShowPanel: function() {
                    // 动态调整高度
                    if (orgCount > 13) {
                        $(this).combobox('panel').height(285);
                    }
                }
            });
        }
    });
    //新增考评窗口
    $("#standardWin").window({
        title: '新增考评标准',
        closed: true,
        modal: true,
        collapsible: false,
        minimizable: false,
        maximizable: false,
        closable: false,
        fit: true,
        onClose: function () {

        },
        onOpen: function () {

        }
    });
    //修改窗口
    $("#edit").window({
        closed: true,
        modal: true,
        title:'考评标准修改',
        collapsible: true,
        minimizable: false,
        maximizable: true,
        resizable: true,
        onClose: function () {
            $("#standardForm").form('reset');
        },
        onOpen: function () {

        }
    });
    //导入模板
    $("#import").on('click', function () {
        //获取科室
        var deptTree=$("#deptTree").tree('getChecked');
        if(deptTree.length==0){
            $.messager.alert('系统提示', '请选择科室', 'info');
            return;
        }
        else {
            $("#importWin").window('open');
        }
    });
    //修改
    $("#editStandardBtn").on('click', function () {
        var row = $("#standard").datagrid("getSelections");
        if (row.length == 1) {
            $("#edit").window('open');
            $("#sname").textbox('setValue',row[0].name);
            $("#score").textbox('setValue',row[0].score);
            $("input[name='kpi'][value="+row[0].kpi+"]").attr("checked",true);
            id=row[0].id;
            var value='';
            $.get("/service/standard/getPersonById?id=" + row[0].id, function (data) {
                $.each(data, function (index, item) {
                    if(index!=data.length-1){
                        value+=item.name+',';
                    }
                    else{
                        value+=item.name;
                    }
                });
                $("#userName").textbox('setValue',value);
            });
        }
        else {
            $("#standard").datagrid("clearSelections");
            $.messager.alert("提示", "请选择一条数据!", 'info');
            return;
        }
    });
    //保存标准
    $("#saveBtn").on('click', function () {

        //获取标准名称
        var name=$("#sname").val();
        if(name==''){
            $.messager.alert('提示', '请填写考评标准名称', 'info');
            return;
        }
        if(getRealLen(name)>1000){
            $.messager.alert("提示","考评标准名称输入过长！",'info');
            return;
        }
        //获取考评分值
        var score=$("#score").numberbox('getValue');

        if(score==''){
            $.messager.alert('提示', '请填写考评分值', 'info');
            return;
        }
        if(parseFloat(score)<=0){
            $.messager.alert("提示", "分值应大于零", 'info');
            return;
        }
        //获取是否KPI
        var kpi=$("input[name='kpi']:checked").val();
        if(kpi==null){
            $.messager.alert('提示', '请选择是否KPI', 'info');
            return;
        }
        var standard = {};
        standard.id = id;
        standard.name = name;
        standard.kpi = kpi;
        standard.score = score;
        standard.depId = deptId;
        standard.createBy = parent.config.persion_Id;
        $.postJSON(basePath + "/deptStandard/editStandard", JSON.stringify(standard), function (data) {
            if (data.data == "success") {
                if(data.code=="hasName"){
                    $.messager.alert('提示', '该考评标准名称已存在', 'info');
                }
                if(data.code=="success"){
                    id='';
                    $("#edit").window('close');
                    var url = basePath + "/deptStandard/findListByid?id=" + deptId;
                    $("#standard").datagrid("reload", url);
                }
            }
        }, function (data) {
            $.messager.alert('提示', '保存失败', 'info');
        });
    });
    //取消
    $("#cancelBtn").on('click', function () {
        $("#edit").window('close');
    });
    /**
     * 新增修改保存
     */
    $("#submitBtn").on('click', function () {
        var pid = $("#type").combobox('getValue');
        if(pid==null||pid==''){
            $.messager.alert('提示', '请选择模板分类！', 'info');
            return;
        }
        var id = $("#name").combobox('getValue');
        if(id==null||id==''){
            $.messager.alert('提示', '请选择模板！', 'info');
            return;
        }
        var deptTree=$("#deptTree").tree('getChecked');
        var dept=[];
        for(var i=0;i<deptTree.length;i++){
            var obj = {};
            obj.id=deptTree[i].id;
            dept.push(obj);
        }
        var mould={};
        mould.id=id;
        mould.pid=pid;
        mould.dept=dept;
        $.postJSON(basePath + "/deptStandard/importMould", JSON.stringify(mould), function (data) {
            if(data.data=='success')
            $.messager.alert('提示', '导入成功!', 'info');
            //清空选择
            var roots = $('#deptTree').tree('getChecked');//返回tree的所有根节点数组
            for ( var i = 0; i < roots.length; i++) {
                $('#deptTree').tree('uncheck', roots[i].target);
            }
            $("#importWin").window('close');
            $("#standard").datagrid('reload', {});
        })
    });
    //保存考评标准
    $("#saveStandard").on('click', function () {
        var standardGrid=$("#standardGrid").datagrid('getChecked');
        var templetVo = {};
        templetVo.id = deptId;
        templetVo.standard=standardGrid;
        $.postJSON(basePath + "/deptStandard/saveStandard", JSON.stringify(templetVo), function (data) {
            if (data.data == "success") {
                $("#standardGrid").datagrid('clearChecked');
                reloadStandard();
            }
        });
    });
    //新增考评标准返回
    $("#cancelStandard").on('click', function () {
        var url = basePath + "/deptStandard/findListByid?id=" + deptId;
        $("#standard").datagrid("reload", url);
        $("#standardWin").window('close');
    });
    //删除考评标准
    $("#delStandardBtn").on('click', function () {
        //获取选择行
        var rows=$('#standard').datagrid('getChecked');
        if(rows.length>0){
            var templetVo = {};
            templetVo.id = deptId;
            templetVo.standard=rows;
            $.postJSON(basePath + "/deptStandard/delStandardById", JSON.stringify(templetVo), function (data) {
                if (data.data == "success") {
                    var url = basePath + "/deptStandard/findListByid?id=" + deptId;
                    $("#standard").datagrid("reload", url);
                    $("#standard").datagrid('clearChecked');
                }
            });
        }
        else{

            $.messager.alert("提示", "请选择要删除的考评标准", "info");
            return;
        }
    });
});
function reloadStandard() {
    var url=basePath + "/deptStandard/standardByProject?pcode="+pcode+"&deptId="+deptId+"&orgId="+orgId;
    $("#standardGrid").datagrid("reload", url);
}
//datagrid加载完后合并指定单元格
function mergeCellsByField(tableID, colList) {
    var ColArray = colList.split(",");
    var tTable = $("#" + tableID);
    var TableRowCnts = tTable.datagrid("getRows").length;
    var tmpA;
    var tmpB;
    var PerTxt = "";
    var CurTxt = "";
    var alertStr = "";
    for (j = ColArray.length - 1; j >= 0; j--) {
        PerTxt = "";
        tmpA = 1;
        tmpB = 0;

        for (i = 0; i <= TableRowCnts; i++) {
            if (i == TableRowCnts) {
                CurTxt = "";
            }
            else {
                CurTxt = tTable.datagrid("getRows")[i][ColArray[j]];
            }
            if (PerTxt == CurTxt) {
                tmpA += 1;
            }
            else {
                tmpB += tmpA;

                tTable.datagrid("mergeCells", {
                    index: i - tmpA,
                    field: ColArray[j],　　//合并字段
                    rowspan: tmpA,
                    colspan: null
                });
                tTable.datagrid("mergeCells", { //根据ColArray[j]进行合并
                    index: i - tmpA,
                    field: "Ideparture",
                    rowspan: tmpA,
                    colspan: null
                });

                tmpA = 1;
            }
            PerTxt = CurTxt;
        }
    }
}
function initScore() {
    var row =$("#standard").datagrid('getRows');
    var score=0;
    for(var i=0;i<row.length;i++){
        score+=parseFloat(row[i].score);
    }
    $("#totalScore").html(score+'分');

}


