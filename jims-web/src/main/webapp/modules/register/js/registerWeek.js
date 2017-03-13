$(function(){
    /**
     * 科室下拉框
     */
    $('#deptNameId').combobox({
        data: clinicDeptCode,
        valueField: 'id',
        textField: 'dept_name'
    })
    /**
     * 医生下拉框
     */
    $('#doctorNameId').combogrid({
        data: doctorName,
        idField:'id',
        textField:'name',
        columns:[[
            {field:'name',title:'医生姓名',width:70},
            {field:'dept_name',title:'科室',width:120},
            {field:'title',title:'职称',width:70}
        ]],keyHandler: {
            up: function() {},
            down: function() {},
            enter: function() {},
            query: function(q) {
                comboGridCompleting(q,'doctorNameId');
            }
        }
    })
    var rowNum=-1;
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
        singleSelect:true,//是否单选
        pagination:true,//分页控件
        pageSize:15,
        pageList: [10,15,30,50],//可以设置每页记录条数的列表
        columns:[[      //每个列具体内容
            {field:'clinicLabel',title:'号别名称',width:'20%',align:'center'},
            {field:'deptName',title:'门诊科室',width:'25%',align:'center'},
            {field:'doctorName',title:'医师',width:'20%',align:'center'},
            {field:'doctorTitle',title:'医师职称',width:'20%',align:'center'},
            {field:'clinicTypeName',title:'号类',width:'15%',align:'center'},
        ]],
        frozenColumns:[[
            {field:'ck',checkbox:true}
        ]],
        toolbar: "#lookListData",
        onClickRow:function(rowIndex,rowData){
            var selectRows = $('#list_data').datagrid("getSelected");
            var clinicIndexId=  selectRows['id'];//号别ID
            weekTable(clinicIndexId);
        }
    });
    //设置分页控件
    var p = $('#list_data').datagrid('getPager');
    $(p).pagination({
        beforePageText: '第',//页数文本框前显示的汉字
        afterPageText: '页    共 {pages} 页',
        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录'
    });
    weekTable('1');
});

function weekTable(id){
    var html="";
    $.ajax({
        'type': 'POST',
        'url': basePath+'/clinicSchedule/findListTable',
        'contentType': 'application/json',
        'data': clinicIndexId=id,
        'dataType': 'json',
        'success': function(data){
            var a=1;
            html+="<tr><td></td><td>出诊</td><td>限号数</td><td>限约号数</td><td>出诊</td><td>限号数</td><td>限约号数</td><td>出诊</td>"+
                "<td>限号数</td><td>限约号数</td><td>出诊</td><td>限号数</td><td>限约号数</td><td>出诊</td><td>限号数</td><td>限约号数</td>"+
                "<td>出诊</td><td>限号数</td><td>限约号数</td><td>出诊</td><td>限号数</td><td>限约号数</td></tr>";
            for(var i=1;i<=7;i++ ){
                html+="<tr><td>"+data[a+1].time_interval_name+"</td>";
                for(var j=0;j<7;j++){
                    a=(i-1)*7+j;
                    var inputCheckBox=""
                    if(data[a].cliniclabel=='' ||data[a].cliniclabel==null){
                        inputCheckBox="<input type='checkbox'  inputText='weekId"+a+"'/>";
                    }else{
                        inputCheckBox="<input type='checkbox'  inputText='weekId"+a+"' checked='checked'/>";
                    }
                    var id=data[a].id;
                    if(id==null || id==''|| id=='null'){
                        id='';
                    }
                    html+="<td>"+inputCheckBox+"<input type='hidden' inputValue='weekId"+a+"' submitName='timeDesc' value='"+data[a].sj+"'><input type='hidden' inputValue='weekId"+a+"' submitName='dayOfWeek' value='"+data[a].xx+"'><input type='hidden' inputValue='weekId"+a+"' submitName='id' value='"+id+"'></td>";
                    html+="<td><input type='text' class='nonbor-inp' value='"+data[a].registrationlimits+"' submitName='registrationLimits'  inputValue='weekId"+a+"'/></td>";
                    html+="<td><input type='text' class='nonbor-inp' value='"+data[a].appointmentlimits+"'  submitName='appointmentLimits' inputValue='weekId"+a+"'/></td>";
                }
                html+="</tr>";
            }
            $("#weekTbodyId").html(html);
        }
    });
}
//加载安排录入list
function listWeek(clinicIndexId){
    $('#listWeek').datagrid({url:basePath+'/clinicSchedule/findList?clinicIndexId='+clinicIndexId});
}
//条件查询
function searchClinicIndex(){
    var deptName=$("#deptNameId").combobox('getValue');
    var doctor=$("#doctorNameId").combogrid('getValue');
    var clinicIndexName=$("#clinicTypeNameId").val();
    $("#list_data").datagrid('reload',{"deptName":deptName,"clinicIndexName":clinicIndexName,"doctor":doctor});
}
//保存号别安排
function saveClinicWeek(){
    var selectRows = $('#list_data').datagrid("getSelected");
    if (selectRows ==null || selectRows=='' || selectRows==undefined) {
        $.messager.alert("提示消息", "请选中要录入的号别信息!");
        return;s
    }
    var clinicIndexId=  selectRows['id'];//号别ID
    if($("#weekTbodyId input[type=checkbox]:checked").length==0){
        $.postJSON(basePath+'/clinicSchedule/deleteType?id='+clinicIndexId,'',function(data){
            if(data.code=='1'){
                $.messager.alert("提示消息","保存成功");
                var selectRows = $('#list_data').datagrid("getSelected");
                var clinicIndexId=  selectRows['id'];//号别ID
                weekTable(clinicIndexId);
            }else{
                $.messager.alert('提示',"保存失败", "error");
            }
        },function(data){
            $.messager.alert('提示',"保存失败", "error");
        })
        return false;
    }
    var tableJson='[';
    $("#weekTbodyId input[type=checkbox]:checked").each(function(){
        var valueInput= $(this).attr("inputText");
        tableJson+="{";
        $("input[inputValue='"+valueInput+"']").each(function(){
            var name=$(this).attr("submitName");
            var value=$(this).val();
            tableJson+='"'+name+'":"'+value+'",';
        })
        tableJson = tableJson.substring(0, tableJson.length - 1);
        tableJson+="},";
    })
    tableJson = tableJson.substring(0, tableJson.length - 1);
    tableJson+="]";
    $.postJSON(basePath+'/clinicSchedule/save?clinicIndexId='+clinicIndexId,tableJson,function(data){
        if(data.code=='1'){
            $.messager.alert("提示消息","保存成功");
            var selectRows = $('#list_data').datagrid("getSelected");
            var clinicIndexId=  selectRows['id'];//号别ID
            weekTable(clinicIndexId);
        }else{
            $.messager.alert('提示',"保存失败", "error");
        }
    },function(data){
        $.messager.alert('提示',"保存失败", "error");
    })
}



