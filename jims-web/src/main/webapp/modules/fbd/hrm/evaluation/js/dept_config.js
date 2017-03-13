var basePath = "/service";
var orgId = parent.config.org_Id;
var deptId="";
var deptName="";
$(function () {
    loadDept();
    //科系
    $("#parent").datagrid({
        fit: true,
        fitColumns: true,
        striped: true,
        singleSelect: true,
        idField: "id",
        toolbar: '#classft',
        url: basePath + '/deptConfig/getParent',
        method: 'get',
        loadMsg: '数据正在加载中，请稍后.....',
        columns: [[{
            title: 'id',
            field: "id",
            hidden: true
        },
            {
                title: '科系名称',
                field: 'name',
                width: '100%',
                align: 'center'
            }]],
        onClickRow: function (index, row) {
            deptId=row.id;
            deptName=row.name;
            var url = basePath + "/deptConfig/findListByPid?id=" + deptId;
            $("#child").datagrid("reload", url);
        }
    });
    /**
     * 新增科系名称
     */
    $("#newDialog").dialog({
        title: '科系名称新增',
        modal: true,
        closed: true,
        collapsible: true,
        minimizable: false,
        maximizable: true,
        resizable: true
    });
    //新增
    $("#addBtn").on('click', function () {
        $("#newDialog").dialog("setTitle", "科系名称新增").dialog("open");
        $("#newForm").form('reset');
        $("#id").val('');
    });
    /**
     * 修改
     */
    $("#editBtn").on('click', function () {
        //reset();
        flag = 0;
        var row = $("#parent").datagrid("getSelections");
        if (row.length == 1) {
            $("#newDialog").dialog("setTitle", "科系名称修改").dialog("open");
            $("#id").val(row[0].id);
            $("#deptName").textbox("setValue", row[0].name);
        }
        else {
            $("#parent").datagrid("clearSelections");
            $.messager.alert("提示", "请选择一条数据!", 'info');
            return;
        }

    });
    /**
     * 删除
     */
    $("#delBtn").on('click', function () {
        //stopEdit();
        var row = $("#parent").datagrid('getSelections');
        if (row.length == 0) {
            $("#parent").datagrid("clearSelections");
            $.messager.alert("提示", "请选择要删除的科系!", "info");
            return;
        }
        if (row.length > 0) {

                $.messager.confirm('提示', '确定删除选中的科系吗？', function (r) {
                    if (r) {
                        $.postJSON(basePath + "/deptConfig/delDept", JSON.stringify(row), function (data) {
                            /*$.messager.alert('系统提示', '删除成功', 'info');*/
                            $('#parent').datagrid('reload');
                            row.length = 0;
                            $("#parent").datagrid('clearSelections');
                            $("#child").datagrid('reload', {});
                            deptId='';
                            deptName='';
                        })
                    }
                });
        }
    });
    /**
     * 新增修改保存
     */
    $("#submitBtn").on('click', function () {
        var name = $("#deptName").textbox("getValue");
        if (!name || name.indexOf(" ") >= 0) {
            $("#parent").datagrid("clearSelections");
            $.messager.alert("提示", "请输入科系名称，不能包含空格!", 'info');
            return;
        }
        if (getRealLen(name) > 20) {
            $.messager.alert("提示", "科系名称内容输入过长！", 'info');
            return;
        }
        var dept = {};
        dept.id = $("#id").val();
        dept.createBy = parent.config.persion_Id;
        dept.name = name;
        dept.type = "1";

        $.postJSON(basePath + "/deptConfig/deptMerge", JSON.stringify(dept), function (data) {
            if (data.data == "success") {
                if (data.code == "hasName") {
                    $("#parent").datagrid("clearSelections");
                    $.messager.alert('提示', '该科系名称已存在!', 'info');

                }
                if (data.code == "success") {
                    $('#newDialog').window('close');
                    $("#parent").datagrid('reload');
                    $("#newForm").form('reset');
                    $("#parent").datagrid('clearSelections');
                }
                deptId='';
                deptName='';
            }
            else {
                $("#parent").datagrid("clearSelections");
                $.messager.alert('系统提示', '保存失败', 'error');

            }
        })
    });
    //选择科室
    $("#addBtn1").on('click', function () {
        if(deptId==''){
            $.messager.alert('提示', '请选择科系', 'info');
            return;
        }
        $("#chooseDept").window({title:"选择科室"});
        $("#chooseDept").window('open');

        $('#deptTree').tree('loadData',treeDepts);
        //清空选择
        var roots = $('#deptTree').tree('getChecked');//返回tree的所有根节点数组
        for ( var i = 0; i < roots.length; i++) {
            for ( var i = 0; i < roots.length; i++) {
                $('#deptTree').tree('uncheck', roots[i].target);
            }
        }
        //科室列表
        $("#deptGrid").datagrid({
            fit: true,
            fitColumns: true,
            striped: true,
            singleSelect: false,
            url:basePath + "/deptConfig/getDeptById?id=" + deptId,
            method: 'GET',
            rownumbers: true,
            loadMsg: '数据正在加载中，请稍后.....',
            columns: [[
                {field: 'ck', title: '', checkbox: true},
                {
                title: "科室名称",
                field: "deptName",
                width: '94%',
                align: 'center'
            }
            ]]
        });

    });
    //科系
    $("#child").datagrid({
        fit: true,
        fitColumns: true,
        striped: true,
        singleSelect: false,
        idField: "id",
        toolbar: '#ft',
        //url:basePath + "/deptConfig/findListByPid?id=" + deptId,
        method: 'get',
        loadMsg: '数据正在加载中，请稍后.....',
        columns: [[{
            title: 'id',
            field: "id",
            hidden: true
        },
            {
                title: '科系名称',
                field: 'name',
                width: '20%',
                align: 'center'
            },{
                title: '科室名称',
                field: 'deptName',
                width: '20%',
                align: 'center'
            }]],
        onLoadSuccess:function(data){
            mergeCellsByField("child", "name");
        },onBeforeSelect:function(){
            return false;
        }
    });
    /**
     * 查看全部
     */
    $("#queryBtn").on('click', function () {
        var id="";
        var url = basePath + "/deptConfig/findListByPid?id=" + id;
        $("#child").datagrid("reload", url);
    });
});
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



