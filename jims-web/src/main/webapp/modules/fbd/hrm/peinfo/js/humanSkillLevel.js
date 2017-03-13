/*
 *人员技能等级
 * @author
 * @version 2016-08-18
 */
var basePath = "/service";

var dg;
var d;
var flag;
var editIndex=undefined;
var page='1';
var rows='30';
var humanSkill = {};
var orgId = parent.config.org_Id;
var id='999';
var userId='';
var deptId='';
var userName='';
var skill='';
var skillId='';
var levelId='';
var level='';
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
        url: basePath + '/peinfo/level-list?orgId=' + orgId,
        remoteSort: false,
        //idField: 'ssItemId',
        singleSelect: true,//是否单选
        rownumbers: true,//行号
        columns: [[
            {field: 'orgId', title: '', width: '10%',hidden: true},
            {field: 'levelId', title: '', width: '10%',hidden: true},
            {field: 'skillId', title: '', width: '10%',hidden: true},
            {field: 'level', title: '技能等级', width: '10%', align: 'center'},
            {field: 'skill', title: '人员技能', width: '10%', align: 'center'},
            {field: 'createBy', title: '创建人', width:'7%', align: 'center'},
            {field: 'createDate', title: '创建时间', width:'15%',align: 'center'},
            {field: 'updateBy', title: '更新人', width:'7%', align: 'center'},
            {field: 'updateDate', title: '更新时间', width:'15%', align: 'center'}

        ]],
        onLoadSuccess:function(){
            mergeCells();//合并单元格
            $("#primaryGrid").datagrid('getPager').pagination({
                pageList: [10, 20, 30, 40, 50], //可以设置每页记录条数的列表
                displayMsg: '当前显示从第{from}条到第{to}条，共{total}条记录'
            });
            $.messager.progress('close');
            $('#tb').css('display','block');
        }
    });


    //删除
    $("#delBtn").on('click', function () {
        flag='0';
        var row = $("#primaryGrid").datagrid('getSelected');
        if (row) {
            levelId = row.levelId;
                    humanSkill.levelId = row.levelId;
                    $.messager.confirm('提示', '确定要进行删除操作吗', function (r) {
                        if (r) {
                            $.postJSON(basePath + "/peinfo/level-del", JSON.stringify(humanSkill), function (data) {
                                $('#primaryGrid').datagrid('reload');
                                $("#primaryGrid").datagrid('clearSelections');
                            });
                        }
                    });
        }else{
            $.messager.alert("提示", "请选择一条记录！","info");

        }
    });
    /*$("#editItemTypeId").combobox({
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
    });*/
    $("#SKILL").combobox({
        panelWidth: '150px',
        panelHeight: '90px',
        valueField: 'skillId',
        textField: 'skill',
        loadMsg: '数据正在加载...',
        url: basePath + '/peinfo/skill-downlist?orgId=' + orgId ,
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
        title: '技能级别设置',
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
        $("#LEVEL_ID").val('999');
    });



    //修改
    $("#editBtn").on("click", function () {
        //获取选择行
        var row=$('#primaryGrid').datagrid('getSelected');
        if(row){
            $("#editWin").window('open');

            /*$.get(basePath + '/tool/find-list-by-type?value='+row.ssItemTypeId+'&type=SOCIAL_SECURITY_DICT',
                function (data) {
                    ssItemTypeDes=data[0].label;
                });*/

            $("#LEVEL_ID").val(row.levelId);
            $("#flag").val('1');
            $("#LEVEL").textbox('setValue', row.level);
            $("#SKILL").combobox('setValue', row.skillId);
            $("#SKILL").combobox('setText', row.skill);

        }
        else{

            $.messager.alert("提示", "请选择一条记录！","info");
        }

    });

    //保存数据
    $("#saveBtn").on('click', function () {

        level=$("#LEVEL").textbox('getValue');
        skillId=$("#SKILL").combobox('getValue');
        levelId=$("#LEVEL_ID").val();
        flag=$("#flag").val();


        if(flag=='0')
        {
            //if(ssItemDes=='')
            if(!$("#LEVEL").textbox("getValue")||$("#LEVEL").textbox("getValue").indexOf(" ") >=0)
            {
                $.messager.alert("提示","请输入有效的技能等级名称，不能包含空格！",'info');
                return;
            }
            if($("#LEVEL").textbox("getValue").length>20){
                $.messager.alert("提示","技能等级名称内容输入过长！",'info');
                return;
            }
            if(skillId=='')
            {
                $.messager.alert("提示", "请选择人员技能！","info");
                return;
            }

        }
        humanSkill.orgId = orgId;
        humanSkill.level = level;
        humanSkill.skillId = skillId;
        humanSkill.levelId = levelId;
        //判断是否已存在相同名称数据
        $.get(basePath +"/peinfo/if-level-exist?orgId="+orgId+"&level="+$("#LEVEL").textbox('getValue')+"&levelId="+$("#LEVEL_ID").val()+"&skillId="+$("#SKILL").combobox('getValue'),
            function (data) {
                var list = eval(data);
                for (var i = 0; i < 1; i++)
                {
                    var num=list[i]['num'];
                }
                if(num=='1'||num>'1'){
                    var str = '该技能下已存在相同名称的等级数据！';
                    $.messager.alert("提示", str,"info");
                    return;
                }

        $.postJSON(basePath + "/peinfo/level-merge", JSON.stringify(humanSkill), function (data) {
            $("#editWin").window('close');
            $("#primaryGrid").datagrid('reload');
            $("#editForm").form('reset');
            $("#primaryGrid").datagrid('clearSelections');

            });
        });
    });

    //取消
    $("#cancelBtn").on('click', function () {
        $("#editForm").form('reset');
        $("#editWin").window('close');
    });

});
//datagrid加载完后合并指定单元格
function mergeCells(data){
    var arr =[{mergeFiled:"skill",premiseFiled:"skill"}	//合并列的field数组及对应前提条件filed（为空则直接内容合并）
    ];
    var dg = $("#primaryGrid");	//要合并的datagrid中的表格id
    var rowCount = dg.datagrid("getRows").length;
    var cellName;
    var span;
    var perValue = "";
    var curValue = "";
    var perCondition="";
    var curCondition="";
    var flag=true;
    var condiName="";
    var length = arr.length - 1;
    for (i = length; i >= 0; i--) {
        cellName = arr[i].mergeFiled;
        condiName=arr[i].premiseFiled;
        if(condiName!=null){
            flag=false;
        }
        perValue = "";
        perCondition="";
        span = 1;
        for (row = 0; row <= rowCount; row++) {
            if (row == rowCount) {
                curValue = "";
                curCondition="";
            } else {
                curValue = dg.datagrid("getRows")[row][cellName];
                /* if(cellName=="ORGSTARTTIME"){//特殊处理这个时间字段
                 curValue =formatDate(dg.datagrid("getRows")[row][cellName],"");
                 } */
                if(!flag){
                    curCondition=dg.datagrid("getRows")[row][condiName];
                }
            }
            if (perValue == curValue&&(flag||perCondition==curCondition)) {
                span += 1;
            } else {
                var index = row - span;
                dg.datagrid('mergeCells', {
                    index : index,
                    field : cellName,
                    rowspan : span,
                    colspan : null
                });
                span = 1;
                perValue = curValue;
                if(!flag){
                    perCondition=curCondition;
                }
            }
        }
    }
}


