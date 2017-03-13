
var basePath = "/service";
var orgId= parent.config.org_Id;
var name='';
var user=[];//人员数组
var treeUser = [];//人员树
$(function () {
    $("#standardGrid").datagrid({
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
        url: basePath + "/templet/myStandard?name="+name,
        remoteSort: false,
        idField: 'code',
        singleSelect: false,//是否单选
        rownumbers: true,//行号
        columns: [[
            {field: 'pname', title: '一级项目名称', width: '13%', align: 'center'},
            {field: 'sname', title: '二级项目名称', width: '13%', align: 'center'},
            {field: 'name', title: '考评标准名称', width: '50%', align: 'left',halign: 'center',formatter : function (value, row, index) {
                if (value.length > 130) value = value.substr(0, 130) + "...";
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
            mergeCellsByField("standardGrid", "pname,sname");
        }
    });
    $("#searchBtn").on("click", function () {
        //获取员工名称
        $("#standardGrid").datagrid('reload', basePath + '/templet/myStandard?name='+name);
    });
    $("#clearBtn").on("click", function () {
        name='';
        $("#searchName").combotree("setValue","请选择");
    });
    //查询条件:人员树选择
    $("#searchName").combotree({
        panelWidth: '200px',
        fitColumns: true,
        striped: true,
        singleSelect: true,
        value:'请选择',
        text:'请选择',
        loadMsg: '数据正在加载中，请稍后.....',
        columns: [[{
            title: 'id',
            field: "id",
            hidden: true
        }, {
            title: '员工姓名',
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
                $('#searchName').combotree('clear');
                $.messager.alert("提示", "请选择员工!", "info");
            }
            else {
                name=node.id;
            }
        }
    });
    var loadtree = function () {

        var depts = [];
        var treeDepts = [];
        var loadPromise = $.get("/service/tool/find-user-tree?orgId=" + orgId, function (data) {
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
                depts.push(obj);
            });
        });
        loadPromise.done(function () {
            for (var i = 0; i < depts.length; i++) {
                for (var j = 0; j < depts.length; j++) {
                    if (depts[i].id == depts[j].parent) {
                        if(depts[j].type=='3'){
                            depts[j].depId=depts[i].id;
                            depts[j].depName=depts[i].name;
                        }
                        depts[i].children.push(depts[j]);
                    }
                }
                if (depts[i].children.length > 0 && !depts[i].parent&&depts[i].type!='3') {
                    treeDepts.push(depts[i]);
                }

                if (!depts[i].parent && depts[i].children <= 0&&depts[i].type!='3') {
                    treeDepts.push(depts[i])
                }
            }
            $("#searchName").combotree('loadData', treeDepts);
            $('#searchName').combotree('tree').tree('collapseAll');
        })
    }
    loadtree();
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