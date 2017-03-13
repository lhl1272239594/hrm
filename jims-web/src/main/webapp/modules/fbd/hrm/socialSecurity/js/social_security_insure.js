/*
 *社保投保管理
 * @author yangchen
 * @version 2016-08-18
 *!/*/
var basePath = "/service";

var dg;
var d;
var flag;
var editIndex=undefined;
var page='1';
var rows='30';
var socialSecurityInsureVo = {};
var orgId = parent.config.org_Id;
var id='999';
var userId='';
var userName='';
var value='';
var label='';
var obj1 = {};
var depts = [];
var treeDepts = [];
var ssInsureTypeId='';
var ssInsureTypeDes='';
var ssPlanId='';
var search = false;
var deptId = '';
var orgCount=0;
$(function () {
    $("#userId").combotree({
        fitColumns: true,
        striped: true,
        panelWidth: 300,
        panelHeight: 500,
        onlyLeafCheck:true,
        loadMsg: '数据正在加载中，请稍后.....',
        columns: [[{
            title: '序号',
            field: "id",
            hidden: true
        }, {
            title: '人员列表',
            field: 'text',
            width: '100%'
        }]]
    });
    loadUserTree();
    /*$.messager.progress({
     title: '提示！',
     msg:  '数据量较大，请稍候...',
     text: '加载中.......'
     });*/

    $("#primaryGrid").datagrid({
        iconCls: 'icon-edit',//图标
        width: '100%',
        height: '100%',
        nowrap: false,
        striped: true,
        border: true,
        pageSize:30,
        method: 'get',
        toolbar: '#tb',
        fitColumns:true,
        loadMsg: '数据正在加载中，请稍后.....',
        pagination: true,//分页控件
        collapsible: false,//是否可折叠的
        remoteSort: false,
        //idField: 'ssInsureid',
        singleSelect: false,//是否单选
        rownumbers: true,//行号
        url: basePath + '/socialSecurityInsure/ss-insure-list?userId=' + userId + '&orgId=' + orgId +'&deptId=' + deptId,
        columns: [[
            {field: 'ck', title: '', checkbox: true},
            {field: 'orgId', title: '', width: '10%',hidden: true},
            {field: 'ssInsureid', title: '', width: '10%',hidden: true},
            /*{field: 'userId', title: '员工姓名', width: '10%', align: 'center',
             formatter: function (userId) {
             $.ajaxSettings.async = false;
             $.get(basePath + '/tool/find-person-list?userId='+userId, function (data) {
             userName=data[0].userName;
             });
             return userName
             }},*/
            {field: 'personName', title: '员工姓名', width:'7%', align: 'center'},
            {field: 'personId', title: '员工编号', hidden: true},
            {field: 'deptId', title: '科室', width: '20%', align: 'center',
                formatter: function (deptId) {
                    $.ajaxSettings.async = false;
                    $.get(basePath + '/tool/find-dept-list?deptId='+deptId, function (data) {
                        deptName=data[0].deptName;
                    });
                    return deptName
                }},
            {field: 'ssPlanDes', title: '社保方案', width: '10%', align: 'center'},
            {
                field: 'ssInsureTypeId', title: '投保方式', width: '10%', align: 'center',
                formatter: function (value) {
                    if (value == "1") {
                        return "正常投保";
                    }
                    if (value == "2") {
                        return "停缴社保";
                    }
                }
            },
            /*{field: 'createBy', title: '创建人', width: '7%', align: 'center',
             formatter: function (createBy) {
             $.ajaxSettings.async = false;
             $.get(basePath + '/tool/find-person-list?userId='+createBy, function (data) {
             userName=data[0].userName;
             });
             return userName
             }},*/
            {field: 'createBy', title: '创建人', width:'7%', align: 'center'},
            {field: 'createDeptname', title: '创建科室', width:'10%', align:'center'},
            {field: 'createDept', title: '创建科室编号', hidden:true},
            {field: 'createDate', title: '创建时间', width:'15%',align: 'center'},
            {field: 'updateBy', title: '更新人', width:'7%', align: 'center'},
            {field: 'updateDate', title: '更新时间', width:'15%', align: 'center'}
        ]],
        onLoadSuccess:function(){
            $.messager.progress('close');

        }
    });

    $("#primaryGrid").datagrid('getPager').pagination({
        pageSize:30,
        pageList: [10, 20, 30, 40, 50], //可以设置每页记录条数的列表
        displayMsg: '当前显示从第{from}条到第{to}条，共{total}条记录',
        onSelectPage: function (pageNumber, pageSize) {
            var state = $('#primaryGrid').data('datagrid');
            var opts = state.options;
            page=opts.pageNumber = pageNumber;
            rows=opts.pageSize = pageSize;
            searchAllData(page,rows);

        }
    });
    //按条件查询(汇总)
    $("#searchAllBtn").on("click", function () {
        search=true;
        searchAllData(page,rows);

    });
    //按条件查询

    var searchAllData=function (page,rows) {
        userId=$("#NAME").textbox('getValue');
        deptId = $("#DEPT_ID").combotree('getValue');
        /*$.get( basePath + '/socialSecurityInsure/ss-insure-list?userId=' + userId + '&orgId=' + orgId + '&deptId=' + deptId + '&page=' + page+ '&rows=' + rows,
            function (data) {
                $("#primaryGrid").datagrid('loadData', data);*/
                $("#primaryGrid").datagrid('reload', basePath + '/socialSecurityInsure/ss-insure-list?userId=' + userId + '&orgId=' + orgId + '&deptId=' + deptId + '&page=' + page+ '&rows=' + rows);

                if(search){
                    search=false;
                    $("#primaryGrid").datagrid('getPager').pagination('select', 1);
                }
                $("#primaryGrid").datagrid('clearSelections');
    };
    /**
     * 清空查询条件
     */
    $("#clearBtn").on("click", function () {
        clearKey();
    });
    /**
     * 清空方法
     */
    var clearKey = function () {
        $("#NAME").textbox('clear');
        $("#DEPT_ID").combobox('clear');
        $("#DEPT_ID").combobox("setValue","");
    };
    //查询条件:部门树选择
    $("#DEPT_ID").combotree({
        panelWidth: '160px',
        panelHeight: '180px',
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
                $('#DEPT_ID').combotree('clear');
                $.messager.alert("提示", "请选择子分类!", "info");
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
            $("#DEPT_ID").combotree('loadData', treeDepts);
        })
    };

    loadDept();

    //配置窗口
    $("#editWin").window({
        title: '投保管理',
        closed: true,
        modal: true,
        minimizable: false,
        onClose: function () {
            $("#primaryGrid").datagrid('reload');
            $("#editForm").form('reset');
        },
        onOpen: function () {
            loadUserTree();
            $("#editSsPlanId").combobox({
                panelWidth: '195px',
                panelHeight: '145px',
                idField: 'value',
                textField: 'label',
                //value:'请选择',
                loadMsg: '数据正在加载',
                url: '/service/socialSecurityPlan/ss-plan-all-list?orgId=' + orgId+'&value='+value,
                mode: 'remote',
                method: 'GET',
                onLoadSuccess:function(data){
                    orgCount=data.length;
                },
                onShowPanel:function(){
                    //动态调整高度
                    if(orgCount>13){
                        $(this).combobox('panel').height(285);
                    }
                }
            });
        }
    });

    //新增
    $("#addBtn").on('click', function () {
        $("#editWin").window('open');
        $("#flag").val('0');
        $("#id").val('999');
        $("#editInsureTypeId").combobox('setValue','1');
        $("#chooseUser").show();
        $("#editUserName").textbox('enable');
        arrPerson=[];
        $("#editForm").form('reset');
    });



    //修改
    $("#editBtn").on("click", function () {
        //获取选择行
        var row=$('#primaryGrid').datagrid('getSelected');
        var row1 = $("#primaryGrid").datagrid("getSelections");
        if (row1.length != 1) {
            $.messager.alert("提示", "请选择一条数据进行操作!","info");
            return;
        }
        if(row){

            $("#editWin").window('open');
            /*$.get(basePath + '/tool/find-person-list?userId='+row.userId, function (data) {
             $("#editUserName").textbox('setValue', data[0].userId);
             $("#editUserName").textbox('setText', data[0].userName);
             $("#editUserName").textbox('disable');

             });

             $.get(basePath + '/socialSecurityPlan/ss-plan-all-list?orgId=' + orgId+'&value='+row.ssPlanId, function (data) {
             $("#editSsPlanId").combobox('setValue', data[0].value);
             $("#editSsPlanId").combobox('setText', data[0].label);

             });*/

            //ssInsureTypeId=row.ssInsureTypeId;
            /*if (value == "1") {
             ssInsureTypeDes=="正常投保";
             }
             else {
             ssInsureTypeDes=="停缴社保";
             }*/
            $("#editUserName").textbox('setValue',row.personName);
            $("#editInsureTypeId").combobox('setValue',row.ssInsureTypeId);
            //$("#editHoliday").combobox('setText', ssInsureTypeDes);
            $("#editSsPlanId").combobox('setValue',row.ssPlanId);
            $("#editUserName").textbox('disable');
            $("#chooseUser").hide();
            $("#id").val(row.ssInsureId);
            $("#flag").val('1');

        }
        else{
            $.messager.alert('提示', '请选择一条记录!', 'info');  //提示信息
        }

    });
    //删除
    /*$("#removeBtn").on('click', function () {
     flag='0';
     var row = $("#primaryGrid").datagrid('getSelected');
     if (row) {
     socialSecurityInsureVo.ssInsureId = row.ssInsureId;;
     $.messager.confirm('系统提示', '确定要进行删除操作吗', function (r) {
     if (r) {
     $.postJSON(basePath + "/socialSecurityInsure/ss-insure-del",JSON.stringify(socialSecurityInsureVo),function (data) {
     $.messager.show({
     title:'提示',
     msg:'删除成功！',
     showType:'show',
     width:300,
     height:80,
     timeout:1000,
     style:{
     right:'',
     top:document.body.scrollTop+document.documentElement.scrollTop,
     bottom:''
     }
     });
     $('#primaryGrid').datagrid('reload');
     row.length=0;
     });
     }
     })
     }else{
     $.messager.show({
     title:'提示',
     msg:'只有被驳回的请假审批才可以被删除！',
     showType:'show',
     width:300,
     height:80,
     timeout:1000,
     style:{
     right:'',
     top:document.body.scrollTop+document.documentElement.scrollTop,
     bottom:''
     }
     });
     return;

     }

     });*/
    $("#delBtn").on('click', function () {
        //stopEdit();
        var row = $("#primaryGrid").datagrid('getSelections');
        if (row == null||row.length == 0||!row) {
            $.messager.alert("提示", "请选择要删除的数据!","info");
            return;
        }
        //判断是否在工资公式中被占用，若被占用不能删除。
        /*$.postJSON(basePath + "/salary-part/if-occupy", JSON.stringify(row),
         function (data) {
         if (data.code == "yes") {
         $.messager.alert('系统提示', '该组成部分已在工资计算公式中被使用，请先删除对应的工资计算公式数据！', 'error');
         return;
         }*/
        {
            $.messager.confirm('提示', '确定要批量删除所选中的数据么?', function (r) {
                if (r) {
                    $.postJSON(basePath + "/socialSecurityInsure/ss-insure-del", JSON.stringify(row), function (data) {
                        /*$.messager.alert('系统提示', '删除成功', 'info');*/
                        $('#primaryGrid').datagrid('reload');
                        row.length = 0;
                        $("#primaryGrid").datagrid('clearSelections');
                    })
                }
            });
        }
        //});
    });
    //保存数据
    $("#saveBtn").on('click', function () {

        id=$("#id").val();
        flag=$("#flag").val();
        ssInsureTypeId=$("#editInsureTypeId").combobox('getValue');
        ssPlanId=$("#editSsPlanId").combobox('getValue');
        userId=$("#editUserId").val();
        deptId=$("#editDeptId").val();
        var user=userId.split(",");
        var dept=deptId.split(",");

        var person=[];
        for(var i=0;i<user.length;i++){
            var obj={};
            obj.userId=user[i];
            obj.deptId=dept[i];
            person.push(obj);
        }


        if(flag=='0')
        {
            if($('#editUserName').textbox('getValue')=='')
            {
                $.messager.alert("提示", "请选择投保人员!","info");
                return
            }

            if(ssInsureTypeId=='')
            {
                $.messager.alert("提示", "请选择投保方式!","info");
                return
            }
            if(ssPlanId=='')
            {
                $.messager.alert("提示", "请选择投保方案!","info");
                return
            }


        }

        socialSecurityInsureVo.orgId = parent.config.org_Id;
        socialSecurityInsureVo.ssInsureTypeId = ssInsureTypeId;
        socialSecurityInsureVo.ssPlanId = ssPlanId;
        socialSecurityInsureVo.ssInsureId = id;
        socialSecurityInsureVo.socialSecurityInsurePerson = person;
        $.messager.progress({
            title: '提示！',
            msg:  '数据处理中，请稍候...',
            text: '加载中.......'
        });
        $.postJSON(basePath + "/socialSecurityInsure/merge", JSON.stringify(socialSecurityInsureVo), function (data) {

            //$("#editForm").form('reset');
            $("#editWin").window('close');
            $("#primaryGrid").datagrid('reload');
        });
        $.messager.progress('close');

    });

    //取消
    $("#cancelBtn").on('click', function () {
        $("#editForm").form('reset');
        $("#editWin").window('close');
    });

});

function loadtree1() {
    depts = [];
    treeDepts = [];
    var loadPromise = $.get("/service/tool/find-user-tree?orgId=" + orgId, function (data) {
        $.each(data, function (index, item) {
            //console.log(data);
            var obj = {};
            obj.id = item.treeId;
            obj.text = item.treeDes;
            obj.parent = item.parentId;
            obj.children = [];
            depts.push(obj);
        });
    });

    loadPromise.done(function () {
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
        $("#userId").combotree('loadData', treeDepts);

    })
}