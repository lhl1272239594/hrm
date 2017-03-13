var basePath = "/service";
var orgId = parent.config.org_Id;
var id="";
var typeId="";
var typeName="";
var deptName="";
var pcode='';//项目二级编码
$(function () {
    //模板类型
    $("#type").datagrid({
        fit: true,
        fitColumns: true,
        striped: true,
        singleSelect: true,
        idField: "id",
        toolbar: '#type-tb',
        url: basePath + '/mould/getType',
        method: 'get',
        loadMsg: '数据正在加载中，请稍后.....',
        columns: [[{
            title: 'id',
            field: "id",
            hidden: true
        },
            {
                title: '模板类型',
                field: 'pname',
                width: '100%',
                align: 'center'
            }]],
        onClickRow: function (index, row) {
            typeId=row.id;
            var url = basePath + "/mould/getTemplet?id=" + row.id;
            $("#templet").datagrid("reload", url);
        }
    });
    //模板名称
    $("#templet").datagrid({
        fit: true,
        fitColumns: true,
        striped: true,
        singleSelect: true,
        idField: "id",
        toolbar: '#ft',
        method: 'get',
        loadMsg: '数据正在加载中，请稍后.....',
        columns: [[{
            title: 'id',
            field: "id",
            hidden: true
        },
            {
                title: '模板名称',
                field: 'name',
                width: '100%',
                align: 'center'
            }]],
        onClickRow: function (index, row) {
            id=row.id;
            typeName=row.pname;
            var url = basePath + "/mould/findListByid?id=" + row.id;
            $("#standard").datagrid("reload", url);
        }
    });
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
    /**
     * 新增模板
     */
    $("#newDialog").dialog({
        title: '模板新增',
        modal: true,
        closed: true,
        collapsible: true,
        minimizable: false,
        maximizable: true,
        resizable: true
    });
    /**
     * 新增模板
     */
    $("#newType").dialog({
        title: '类型新增',
        modal: true,
        closed: true,
        collapsible: true,
        minimizable: false,
        maximizable: true,
        resizable: true
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
    //新增类型
    $("#addTypeBtn").on('click', function () {
        $("#newType").dialog("setTitle", "类型新增").dialog("open");
        $("#typeForm").form('reset');
        $("#typeId").val('');
    });
    //新增模板
    $("#addBtn").on('click', function () {
        if(typeId==''){
            $.messager.alert("提示", "请选择模板类型!", 'info');
            return;
        }
        $("#newDialog").dialog("setTitle", "模板新增").dialog("open");
        $("#newForm").form('reset');
        $("#id").val('');
    });
    /**
     * 修改类型
     */
    $("#editTypeBtn").on('click', function () {
        //reset();
        var row = $("#type").datagrid("getSelections");
        if (row.length == 1) {
            $("#newType").dialog("setTitle", "类型修改").dialog("open");
            $("#typeId").val(row[0].id);
            $("#pname").textbox("setValue", row[0].pname);
        }
        else {
            $("#type").datagrid("clearSelections");
            $.messager.alert("提示", "请选择一条数据!", 'info');
            return;
        }

    });
    /**
     * 修改
     */
    $("#editBtn").on('click', function () {
        //reset();
        flag = 0;
        var row = $("#templet").datagrid("getSelections");
        if (row.length == 1) {
            $("#newDialog").dialog("setTitle", "模板修改").dialog("open");
            $("#id").val(row[0].id);
            $("#name").textbox("setValue", row[0].name);
        }
        else {
            $("#templet").datagrid("clearSelections");
            $.messager.alert("提示", "请选择一条数据!", 'info');
            return;
        }

    });
    /**
     * 删除类型
     */
    $("#delTypeBtn").on('click', function () {
        //stopEdit();
        var row = $("#type").datagrid('getSelections');
        if (row.length == 0) {
            $("#type").datagrid("clearSelections");
            $.messager.alert("提示", "请选择要删除的类型!", "info");
            return;
        }
        if (row.length > 0) {

                $.messager.confirm('提示', '确定删除选中的类型吗？', function (r) {
                    if (r) {
                        row[0].type='1';
                        $.postJSON(basePath + "/mould/delMould", JSON.stringify(row), function (data) {
                            /*$.messager.alert('系统提示', '删除成功', 'info');*/
                            $('#type').datagrid('reload');
                            row.length = 0;
                            $("#type").datagrid('clearSelections');
                            $("#templet").datagrid('clearSelections');
                            $("#templet").datagrid('reload', {});
                            $("#standard").datagrid('reload', {});
                            typeId='';
                            typeName='';
                            id='';
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
        var row = $("#templet").datagrid('getSelections');
        if (row.length == 0) {
            $("#templet").datagrid("clearSelections");
            $.messager.alert("提示", "请选择要删除的模板!", "info");
            return;
        }
        if (row.length > 0) {
                $.messager.confirm('提示', '确定删除选中的模板吗？', function (r) {
                    if (r) {
                        row[0].type='2';
                        $.postJSON(basePath + "/mould/delMould", JSON.stringify(row), function (data) {
                            /*$.messager.alert('系统提示', '删除成功', 'info');*/
                            $('#templet').datagrid('reload');
                            row.length = 0;
                            $("#templet").datagrid('clearSelections');
                            $("#standard").datagrid('reload', {});
                            id='';
                        })
                    }
                });
        }
    });
    /**
     * 新增修改类型保存
     */
    $("#submitTypeBtn").on('click', function () {
        var pname = $("#pname").textbox("getValue");
        if (!pname || pname.indexOf(" ") >= 0) {
            $("#type").datagrid("clearSelections");
            $.messager.alert("提示", "请输入类型名称，不能包含空格!", 'info');
            return;
        }
        if (getRealLen(pname) > 20) {
            $.messager.alert("提示", "类型名称内容输入过长！", 'info');
            return;
        }
        var mould = {};
        mould.id = $("#typeId").val();
        mould.createBy = parent.config.persion_Id;
        mould.pname = pname;
        mould.type = '1';

        $.postJSON(basePath + "/mould/Merge", JSON.stringify(mould), function (data) {
            if (data.data == "success") {
                if (data.code == "hasName") {
                    $("#type").datagrid("clearSelections");
                    $.messager.alert('提示', '该类型名称已存在!', 'info');
                }
                if (data.code == "success") {
                    $('#newType').window('close');
                    $("#type").datagrid('reload');
                    $("#typeForm").form('reset');
                    $("#type").datagrid('clearSelections');
                    $("#templet").datagrid('clearSelections');
                }
                typeId='';
                typeName='';
            }
            else {
                $("#templet").datagrid("clearSelections");
                $.messager.alert('系统提示', '保存失败', 'error');

            }
        })
    });
    /**
     * 新增修改保存
     */
    $("#submitBtn").on('click', function () {
        var name = $("#name").textbox("getValue");
        if (!name || name.indexOf(" ") >= 0) {
            $("#type").datagrid("clearSelections");
            $.messager.alert("提示", "请输入模板名称，不能包含空格!", 'info');
            return;
        }
        if (getRealLen(name) > 20) {
            $.messager.alert("提示", "模板名称内容输入过长！", 'info');
            return;
        }
        var mould = {};
        mould.id = $("#id").val();
        mould.createBy = parent.config.persion_Id;
        mould.name = name;
        mould.pid = typeId;
        mould.pname = typeName;
        mould.type = '2';

        $.postJSON(basePath + "/mould/Merge", JSON.stringify(mould), function (data) {
            if (data.data == "success") {
                if (data.code == "hasName") {
                    $("#templet").datagrid("clearSelections");
                    $.messager.alert('提示', '该模板名称已存在!', 'info');
                }
                if (data.code == "success") {
                    $('#newDialog').window('close');
                    $("#templet").datagrid('reload');
                    $("#newForm").form('reset');
                    $("#templet").datagrid('clearSelections');
                }
                id='';
            }
            else {
                $("#templet").datagrid("clearSelections");
                $.messager.alert('系统提示', '保存失败', 'error');

            }
        })
    });
    //打开新增标准窗口
    $("#addStandardBtn").on('click', function () {
        if(id==''){
            $.messager.alert("提示", "请选择模板!", 'info');
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

    //删除考评标准
    $("#delStandardBtn").on('click', function () {
        //获取选择行
        var rows=$('#standard').datagrid('getChecked');
        if(rows.length>0){
            var templetVo = {};
            templetVo.id = id;
            templetVo.standard=rows;
            $.postJSON(basePath + "/mould/delStandardById", JSON.stringify(templetVo), function (data) {
                if (data.data == "success") {
                    var url = basePath + "/mould/findListByid?id=" + id;
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
    //保存考评标准
    $("#saveStandard").on('click', function () {
        var standardGrid=$("#standardGrid").datagrid('getChecked');
        var templetVo = {};
        templetVo.id = id;
        templetVo.standard=standardGrid;
        $.postJSON(basePath + "/mould/saveStandard", JSON.stringify(templetVo), function (data) {
            if (data.data == "success") {
                $("#standardGrid").datagrid('clearChecked');
                reloadStandard();
            }
        });
    });
    //新增考评标准返回
    $("#cancelStandard").on('click', function () {
        var url = basePath + "/mould/findListByid?id=" +id;
        $("#standard").datagrid("reload", url);
        $("#standardWin").window('close');
    });
});
function reloadStandard() {
    var url=basePath + "/mould/standardByProject?pcode="+pcode+"&templetId="+id+"&orgId="+orgId;
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


