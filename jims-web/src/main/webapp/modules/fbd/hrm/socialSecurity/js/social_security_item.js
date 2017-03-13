/*
 *社保项目管理
 * @author yangchen
 * @version 2016-08-18
 */
var basePath = "/service";

var dg;
var d;
var flag;
var editIndex=undefined;
var page='1';
var rows='30';
var socialSecurityItemVo = {};
var orgId = parent.config.org_Id;
var id='999';
var userId='';
var deptId='';
var userName='';
var ssItemTypeDes='';
var ssItemTypeId='';
var ssItemId='';
var ssItemDes='';
var orgCount=0;
$(function () {
    /*  $.messager.progress({
     title: '提示！',
     msg:  '数据读取中，请稍候...',
     text: '加载中.......'
     });*/

    $("#primaryGrid").edatagrid({
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
        url: basePath + '/socialSecurityItem/ss-item-list?userId=' + userId + '&orgId=' + orgId,
        remoteSort: false,
        idField: 'ssItemId',
        singleSelect: true,//是否单选
        rownumbers: true,//行号
        columns: [[
            {field: 'orgId', title: '', width: '10%',hidden: true},
            {field: 'ssItemId', title: '', width: '10%',hidden: true},
            {field: 'ssItemDes', title: '社保项目名称', width: '10%', align: 'center',},
            {field: 'ssItemTypeId', title: '社保项目类别', width: '10%', align: 'center',
                formatter: function (ssItemTypeId) {
                    $.ajaxSettings.async = false;
                    $.get(basePath + '/tool/find-list-by-type?value='+ssItemTypeId+'&type=SOCIAL_SECURITY_DICT', function
                        (data) {
                        ssItemTypeDes=data[0].label;
                    });
                    return ssItemTypeDes
                }},
            {field: 'createBy', title: '创建人', width:'7%', align: 'center'},
            {field: 'createDeptname', title: '创建科室', width:'10%', align:'center'},
            {field: 'createDept', title: '创建科室编号', hidden:true},
            {field: 'createDate', title: '创建时间', width:'15%',align: 'center'},
            {field: 'updateBy', title: '更新人', width:'7%', align: 'center'},
            {field: 'updateDate', title: '更新时间', width:'15%', align: 'center'}
            /*{field: 'createBy', title: '创建人', width: '10%', align: 'center',
                formatter: function (createBy) {
                    $.ajaxSettings.async = false;
                    $.get(basePath + '/tool/find-person-list?userId='+createBy, function (data) {
                        if(data[0].userName!=null) {
                            userName = data[0].userName;
                        }
                        else{
                            userName = '';
                        }
                    });
                    return userName
                }},
            {field: 'createDate', title: '创建时间', width: '15%', align: 'center'},
            {field: 'updateBy', title: '更新人', width: '10%', align: 'center',
                formatter: function (updateBy) {
                    $.ajaxSettings.async = false;
                    $.get(basePath + '/tool/find-person-list?userId='+updateBy, function (data) {
                        if(data[0].userName!=null) {
                            userName = data[0].userName;
                        }
                        else{
                            userName = '';
                        }
                    });
                    return userName
                }},
            {field: 'updateDate', title: '更新时间', width: '15%', align: 'center'}*/
        ]],
        onLoadSuccess:function(){
            $.messager.progress('close')
        }
    });

    /*$("#primaryGrid").datagrid('getPager').pagination({
        pageSize:30,
        pageList: [10, 20, 30, 40, 50], //可以设置每页记录条数的列表
        displayMsg: '当前显示从第{from}条到第{to}条，共{total}条记录'
    });*/



    //删除
    $("#delBtn").on('click', function () {
        flag='0';
        var row = $("#primaryGrid").datagrid('getSelected');
        if (row) {
            ssItemId = row.ssItemId;
            //判断是否在社保方案中被占用，若被占用不能删除。
            $.post(basePath + "/socialSecurityItem/if-occupy?ssItemId="+ssItemId+"&orgId="+orgId,
                function (data) {
                    if (data.code == "yes") {
                        $.messager.alert('提示', '该社保项目已在社保方案中被使用，请先删除社保方案中的对应数据！', 'info');
                        return;
                    }
                    socialSecurityItemVo.ssItemId = row.ssItemId;
                    $.messager.confirm('提示', '确定要进行删除操作吗', function (r) {
                        if (r) {
                            $.postJSON(basePath + "/socialSecurityItem/ss-item-del", JSON.stringify(socialSecurityItemVo), function (data) {
                                $('#primaryGrid').datagrid('reload');
                                $("#primaryGrid").datagrid('clearSelections');
                            });
                        }
                    })
                });
        }else{
            $.messager.alert("提示", "请选择一条记录！","info");

        }
    });
    $("#editItemTypeId").combobox({
        panelWidth: '150px',
        panelHeight: '90px',
        idField: 'value',
        textField: 'label',
        loadMsg: '数据正在加载',
        url: '/service/dict/find-list-by-type?type=SOCIAL_SECURITY_DICT',
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

    //配置窗口
    $("#editWin").window({
        title: '社保项目管理',
        closed: true,
        modal: true,
        minimizable: false,
        //top: 50,
        onClose: function () {
            $("#editForm").form('reset');
        },
        onOpen: function () {
        }

    });

    //新增
    $("#addBtn").on('click', function () {
        $("#editWin").window('open');
        $("#editForm").form('reset');
        $("#flag").val('0');
        $("#id").val('999');
    });



    //修改
    $("#editBtn").on("click", function () {

        //获取选择行
        var row=$('#primaryGrid').datagrid('getSelected');
        if(row){
            $("#editWin").window('open');

            $.get(basePath + '/tool/find-list-by-type?value='+row.ssItemTypeId+'&type=SOCIAL_SECURITY_DICT',
                function (data) {
                    ssItemTypeDes=data[0].label;
                });

            $("#id").val(row.ssItemId);
            $("#flag").val('1');
            $("#editItemDes").textbox('setValue', row.ssItemDes);
            $("#editItemTypeId").combobox('setValue', row.ssItemTypeId);
            $("#editItemTypeId").combobox('setText', ssItemTypeDes);

        }
        else{
            /*$.messager.show({
                title:'提示',
                msg:'请选择一条记录！',
                showType:'show',
                width:300,
                height:80,
                timeout:1000,
                style:{
                    right:'',
                    top:document.body.scrollTop+document.documentElement.scrollTop,
                    bottom:''
                }
            });*/
            $.messager.alert("提示", "请选择一条记录！","info");
        }

    });

    //保存数据
    $("#saveBtn").on('click', function () {

        ssItemDes=$("#editItemDes").textbox('getValue');
        ssItemTypeId=$("#editItemTypeId").combobox('getValue');
        id=$("#id").val();
        flag=$("#flag").val();


        if(flag=='0')
        {
            //if(ssItemDes=='')
            if(!$("#editItemDes").textbox("getValue")||$("#editItemDes").textbox("getValue").indexOf(" ") >=0)
            {
                $.messager.alert("提示","请输入有效的社保项目名称，不能包含空格！",'info');
                return;
            }
            if($("#editItemDes").textbox("getValue").length>20){
                $.messager.alert("提示","社保项目名称内容输入过长！",'info');
                return;
            }
            if(ssItemTypeId=='')
            {
                $.messager.alert("提示", "请选择社保项目类型！","info");
                return;
            }

        }
        socialSecurityItemVo.orgId = orgId;
        socialSecurityItemVo.ssItemDes = ssItemDes;
        socialSecurityItemVo.ssItemTypeId = ssItemTypeId;
        socialSecurityItemVo.ssItemId = id;
        //判断是否已存在相同名称数据
        $.get(basePath +"/socialSecurityItem/if-exist?orgId="+orgId+"&editItemDes="+$("#editItemDes").textbox("getValue")+"&id="+id,
            function (data) {
                var list = eval(data);
                for (var i = 0; i < 1; i++)
                {
                    var num=list[i]['num'];
                }
                if(num=='1'||num>'1'){
                    var str = '该社保项目已存在！';
                    $.messager.alert("提示", str,"info");
                    return;
                }

        $.postJSON(basePath + "/socialSecurityItem/merge", JSON.stringify(socialSecurityItemVo), function (data) {
            $("#editWin").window('close');
            $("#primaryGrid").datagrid('reload');
            $("#editForm").form('reset');
            $("#primaryGrid").datagrid('clearSelections');
            /*$.messager.show({
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
                });*/
            });
        });
    });

    //取消
    $("#cancelBtn").on('click', function () {
        $("#editForm").form('reset');
        $("#editWin").window('close');
    });

});



