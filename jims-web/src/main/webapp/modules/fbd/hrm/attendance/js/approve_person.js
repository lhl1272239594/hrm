/*
 *管理
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
var approvePersonVo = {};
var orgId = parent.config.org_Id;
var id='999';
var attFunId='';
var userId='';
var attFunDes='';
var userName='';
var value='';
var label='';
var obj1 = new  Object();
var attFunList=[];
var depts = [];
var treeDepts = [];

$(function () {


    $.get("/service/tool/find-list-by-type?type=ATT_FUNCTION_DICT&value="+value, function (data) {
        $.each(data, function (index,item) {
            attFunList[item.value]=item.label;
        });
    });
    loadUserTree();
    loadDeptTree();
    $.messager.progress({
        title: '提示！',
        msg:  '数据量较大，请稍候...',
        text: '加载中.......'
    });

    $("#primaryGrid").datagrid({
        iconCls: 'icon-edit',//图标
        width: '100%',
        height: '100%',
        nowrap: false,
        striped: true,
        border: true,
        singleSelect:true,
        method: 'get',
        toolbar: '#tb',
        fitColumns:true,
        loadMsg: '数据正在加载中，请稍后.....',
        pagination: true,//分页控件
        collapsible: false,//是否可折叠的
        remoteSort: false,
        singleSelect: true,//是否单选
        rownumbers: true,//行号
        url: basePath + '/approvePerson/approve-person-list?userId=' + userId + '&orgId=' + orgId ,
        columns: [[
            {field: 'orgId', title: '', width: '10%',hidden: true},
            {field: 'appPersonId', title: '', width: '10%',hidden: true},
            {field: 'attFunId', title: '功能模块', width: '10%', align: 'center',
                formatter: function (attFunId) {
                    return attFunList[attFunId];
                }},
            {field: 'userId', title: '审批人员', width: '10%', align: 'center',
                formatter: function (userId) {
                    return parent.personList[userId];
                }
            },
            {field: 'deptId', title: '员工部门', width: '20%', align: 'center',
                formatter: function (deptId) {
                    $.ajaxSettings.async = false;
                    $.get(basePath + '/tool/find-dept-list?deptId='+deptId, function (data) {
                        deptName=data[0].deptName;
                    });
                    return deptName
                }},
            {field: 'createBy', title: '创建人', width: '10%', align: 'center',
                formatter: function (createBy) {
                    return parent.personList[createBy];
                }
            },
            {field: 'createDate', title: '创建时间', width: '10%', align: 'center'}
        ]],
        onLoadSuccess:function(){
        $.messager.progress('close')
    },
    });
    $("#primaryGrid").datagrid('getPager').pagination({
        pageSize:30,
        pageList: [10, 20, 30, 40, 50], //可以设置每页记录条数的列表
        displayMsg: '当前显示从第{from}条到第{to}条，共{total}条记录',
        onSelectPage: function (pageNumber, pageSize) {
            var state = $('#freGrid').data('datagrid');
            var opts = state.options;
            page=opts.pageNumber = pageNumber;
            rows=opts.pageSize = pageSize;
            searchAllData(page,rows);
            return;
        }
    });

    //按条件查询(汇总)
    $("#searchAllBtn").on("click", function () {

        searchAllData(page,rows);

    });
    //按条件查询

    var searchAllData=function (page,rows) {
        userId=$("#userId").textbox('getValue');
        $.get( basePath + '/approvePerson/approve-person-list?userId=' + userId + '&orgId=' + orgId + '&page=' + page+ '&rows=' + rows,
            function (data) {
                $("#primaryGrid").datagrid('loadData', data);
            });
    }



    //配置窗口
    $("#editWin").window({
        title: '考勤审批人管理',
        closed: true,
        modal: true,
        onClose: function () {
            $('#personGrid').datagrid('loadData', { total: 0, rows: [] });
            $("#editForm").form('reset');
        },
        onOpen: function () {

            $("#editAttFunId").combobox({
                panelWidth: '70px',
                panelHeight: 'auto',
                idField: 'value',
                textField: 'label',
                value:'请选择',
                loadMsg: '数据正在加载',
                url: '/service/tool/find-list-by-type?type=ATT_FUNCTION_DICT',
                mode: 'remote',
                method: 'GET'
            });

        }
    });

    //新增
    $("#addBtn").on('click', function () {
        $("#editWin").window('open');
        $("#flag").val('0');
        $("#id").val('999');
        arrPerson=[];
    });



    //修改
    $("#editBtn").on("click", function () {
        //获取选择行
        var row=$('#primaryGrid').datagrid('getSelected');
        if(row){

            $("#editWin").window('open');
                $.get(basePath + '/tool/find-person-list?userId='+row.userId, function (data) {
                    $("#editUserName").textbox('setValue', data[0].userId);
                    $("#editUserName").textbox('setText', data[0].userName);

                });

            $.get("/service/tool/find-list-by-type?type=ATT_FUNCTION_DICT&value="+row.attFunId, function (data) {
                attFunId =data[0].value
                attFunDes =data[0].label

            });

                $("#id").val(row.appPersonId);
                $("#flag").val('1');

            $("#editAttFunId").combobox('setText', attFunDes);
            $("#editAttFunId").combobox('setValue', attFunId);

            }
            else{
                $.messager.show({
                    title:'提示',
                    msg:'请选择一行记录!',
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
            }

    });
    //删除
    $("#removeBtn").on('click', function () {
        flag='0';
        var row = $("#primaryGrid").datagrid('getSelected');
        if (row) {
            approvePersonVo.appPersonId = row.appPersonId;;
                $.messager.confirm('系统提示', '确定要进行删除操作吗', function (r) {
                    if (r) {
                        $.postJSON(basePath + "/approvePerson/approve-person-del",JSON.stringify(approvePersonVo),function (data) {
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

    });
    //保存数据
    $("#saveBtn").on('click', function () {


        id=$("#id").val();
        flag=$("#flag").val();
        var attFunId=$("#editAttFunId").combobox('getValue');


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



            if(attFunId=='')
            {
                $.messager.alert("系统提示", "请选择业务功能模块!","error");
                return
            }

            if(userId==null||userId==''){
                $.messager.alert('系统提示', '请选择人员', 'error');
                return;
            }


        approvePersonVo.orgId = parent.config.org_Id;
        approvePersonVo.appPersonId = id;
        approvePersonVo.attFunId = attFunId;
        approvePersonVo.approvePersonUser = person;
        $.postJSON(basePath + "/approvePerson/merge", JSON.stringify(approvePersonVo), function (data) {
            $("#editWin").window('close');
            $("#primaryGrid").datagrid('reload');
            $("#editForm").form('reset');
            $.messager.show({
                title:'提示',
                msg:'保存成功！',
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

        })

    });

    //取消
    $("#cancelBtn").on('click', function () {
        $("#editForm").form('reset');
        $("#editWin").window('close');
    });
    $("#clearBtn").on('click', function () {
        $("#userId").combotree('setValue','');
        $("#userId").combotree('setText','');
    });

});
