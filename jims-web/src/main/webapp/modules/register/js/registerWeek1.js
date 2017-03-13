$(function(){
    var rowNum=-1;
    var week=[{"value":"星期一","text":"星期一"},{"value":"星期二","text":"星期二"},{"value":"星期三","text":"星期三"},{"value":"星期四","text":"星期四"}
        ,{"value":"星期五","text":"星期五"},{"value":"星期六","text":"星期六"},{"value":"星期日","text":"星期日"}];
    var time =[{"value":"上午","text":"上午"},{"value":"下午","text":"下午"},{"value":"昼夜","text":"昼夜"}];
    $('#list_data').datagrid({
        iconCls:'icon-edit',//图标
        width: 'auto',
        height: 'auto',
        nowrap: false,
        striped: true,
        border: true,
        method:'get',
        collapsible:false,//是否可折叠的
        fit: true,//自动大小
        url:basePath+'/clinicIndex/findList',
        remoteSort:false,
        idField:'id',
        singleSelect:false,//是否单选
        pagination:true,//分页控件
        pageSize:15,
        pageList: [10,15,30,50],//可以设置每页记录条数的列表
        columns:[[      //每个列具体内容
            {field:'clinicLabel',title:'门诊名称',width:'20%',align:'center'},
            {field:'clinicDept',title:'门诊科室',width:'25%',align:'center'},
            {field:'doctor',title:'医师',width:'20%',align:'center'},
            {field:'doctorTitle',title:'医师职称',width:'20%',align:'center'},
            {field:'clinicType',title:'号类',width:'15%',align:'center'},
        ]],
        frozenColumns:[[
            {field:'ck',checkbox:true}
        ]],
        toolbar: "#ddddddddd",
        onClickRow:function(rowIndex,rowData){
            var selectRows = $('#list_data').datagrid("getSelected");
            var clinicIndexId=  selectRows['id'];//号别ID
            listWeek(clinicIndexId);

        }
    });
    //设置分页控件
    var p = $('#list_data').datagrid('getPager');

    $('#listWeek').datagrid({
        iconCls:'icon-edit',//图标
        width: 'auto',
        height: 'auto',
        nowrap: false,
        striped: true,
        border: true,
        method:'get',
        collapsible:false,//是否可折叠的
        fit: true,//自动大小
        url:'',
        remoteSort:false,
        idField:'id',
        singleSelect:true,//是否单选
        pagination:true,//分页控件
        pageSize:15,
        pageList: [10,15,30,50],//可以设置每页记录条数的列表
        columns:[[      //每个列具体内容
            {field:'clinicLabel',title:'门诊号名称',width:'20%',align:'center',editor: 'text'},
            {field:'dayOfWeek',title:'星期',width:'25%',align:'center',editor:{
                type:"combobox",
                options:{
                    data :week,
                    valueField:'value',
                    textField:'text'
                }
            }},
            {field:'timeDesc',title:'门诊时间',width:'20%',align:'center',editor:{
                type:"combobox",
                options:{
                    data:time,
                    valueField:'value',
                    textField:'text'
                }
            }},
            {field:'registrationLimits',title:'限号数',width:'20%',align:'center',editor: 'text'},
            {field:'appointmentLimits',title:'限约号数',width:'15%',align:'center',editor: 'text'},
        ]],
        frozenColumns:[[
            {field:'ck',checkbox:true}
        ]],
        toolbar: [{
            text: '新增',
            iconCls: 'icon-add',
            handler: function() {
                if(rowNum>=0){
                    rowNum++;
                }
                $("#listWeek").datagrid('insertRow', {
                    index: 0,
                    row: {}
                });
            }
        },
            '-',{
                text: '删除',
                iconCls: 'icon-remove',
                handler: function(){
                    deleteSchedule();
                }
            },{
                text: '保存',
                iconCls:'icon-save',
                handler:function(){
                    $("#listWeek").datagrid('endEdit', rowNum);
                    saveClinicWeek();
                }
            }
        ], onClickRow: function (rowIndex, rowData) {
            var dataGrid=$('#listWeek');
            if(!dataGrid.datagrid('validateRow', rowNum)){
                return false
            }else{
                if(rowNum!=rowIndex){
                    if(rowNum>=0){
                        dataGrid.datagrid('endEdit', rowNum);
                    }
                    rowNum=rowIndex;
                    dataGrid.datagrid('beginEdit', rowIndex);
                }
            }
        }
    });

    //设置分页控件
    var p = $('#listWeek').datagrid('getPager');
});
//加载安排录入list
function listWeek(clinicIndexId){
    $('#listWeek').datagrid({url:basePath+'/clinicSchedule/findList?clinicIndexId='+clinicIndexId});
}
//条件查询
function searchClinicIndex(){
    var deptName=$("#deptName").val();
    var clinicIndexName=$("#clinicIndexName").val();
    var doctor=$("#doctor").val();
    $("#list_data").datagrid({queryParams :{"deptName":deptName,"clinicIndexName":clinicIndexName,"doctor":doctor}});
}
//保存号别安排
function saveClinicWeek(){
    var selectRows = $('#list_data').datagrid("getSelected");
    var clinicIndexId=  selectRows['id'];//号别ID
    if (clinicIndexId ==null || clinicIndexId=='' || clinicIndexId==undefined) {
        $.messager.alert("提示消息", "请选中要录入的号别信息!");
        return;
    }
    var  rows=$('#listWeek').datagrid('getRows');
    var tableJson=JSON.stringify(rows);
    $.postJSON(basePath+'/clinicSchedule/save?clinicIndexId='+clinicIndexId,tableJson,function(data){
        if(data.code=='1'){
            $.messager.alert("提示消息",data.code+"条记录，保存成功");
            $('#listWeek').datagrid('load');
            $('#listWeek').datagrid('clearChecked');
        }else{
            $.messager.alert('提示',"保存失败", "error");
        }
    },function(data){
        $.messager.alert('提示',"保存失败", "error");
    })
}
//删除 安排录入
function deleteSchedule(){
    var selectRows = $('#listWeek').datagrid("getSelections");
    if (selectRows.length < 1) {
        $.messager.alert("提示消息", "请选中要删的数据!");
        return;
    }
    $.messager.confirm("确认消息", "您确定要删除信息吗？", function (r) {
        if (r) {
            var strIds = "";
            for (var i = 0; i < selectRows.length; i++) {
                strIds += selectRows[i].id + ",";
            }
            strIds = strIds.substr(0, strIds.length - 1);
            //删除
            $.ajax({
                'type': 'POST',
                'url': basePath+'/clinicSchedule/delete',
                'contentType': 'application/json',
                'data': id=strIds,
                'dataType': 'json',
                'success': function(data){
                    if(data.code=='1'){
                        $.messager.alert("提示消息",data.code+"条记录删除成功！");
                        $('#listWeek').datagrid('load');
                        $('#listWeek').datagrid('clearChecked');
                    }else{
                        $.messager.alert('提示',"删除失败", "error");
                    }
                },
                'error': function(data){
                    $.messager.alert('提示',"删除失败", "error");
                }
            });
        }
    })
}


