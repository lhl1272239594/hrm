var registrationStatusData=[{ "value": "1", "text": "待诊" }, { "value": "2", "text": "已诊" }];
var firstVisitIndicatorData=[{ "value": "0", "text": "初诊" }, { "value": "1", "text": "复诊" }];
$(function(){
    jQuery(document).bind('keydown', 'F1',function (evt){
       openDialog('chargeId','挂号结果')
        return false;
    });
    jQuery(document).bind('keydown', 'F4',function (evt){
        openDialogRetreat('backNumberId','退号')
        return false;
    });
    jQuery(document).bind('keydown', 'return',function (evt){
        saveClinic();
        return false;
    });

    $('#visitDate').datebox('setValue', formatDatebox(new Date()));
    getClinicForRegist();
    /**
     * 时间
     */
    $("#visitTimeDescId").combobox({
        data: timeInterval,
        valueField: 'time_interval_code',
        textField: 'time_interval_name'
    })
    /**
     * 挂号状态
     */
    $("#registrationStatusId").combobox({
        data: registrationStatusData,
        valueField: 'value',
        textField: 'text'
    })
    /**
     * 初诊状态
     */
    $("#firstVisitIndicatorId").combobox({
        data: firstVisitIndicatorData,
        valueField: 'value',
        textField: 'text'
    })


    /**
     * 科室下拉框
     */
    $('#deptNameId').combobox({
        data: clinicDeptCode,
        valueField: 'id',
        textField: 'dept_name'
    })
    /**
     * 就诊科室
     */
    $('#visitDeptId').combobox({
        data: clinicDeptCode,
        valueField: 'id',
        textField: 'dept_name'
    })
    if(clinicDeptCode.length>0){
        $("#visitDeptId").combobox('select',clinicDeptCode[0].id);
    }
    $('#visitDeptIdt').combobox({
        data: clinicDeptCode,
        valueField: 'id',
        textField: 'dept_name'
    })
    /**
     * 性别下拉框
     */
    $('#setId').combobox({
        data: setData,
        valueField: 'value',
        textField: 'label'
    })
    if(setData.length>0) {
        $("#setId ").combobox('select', setData[0].value);
    }
    $('#setIdt').combobox({
        data: setData,
        valueField: 'value',
        textField: 'label'
    })

    /**
     * 费别下拉框
     */
    $('#chargeTypeId').combobox({
        data: chargeType,
        valueField: 'id',
        textField: 'charge_type_name',
        onSelect: function (record) {
            $("#chargeTypeCodeId").val(record.charge_type_code);
        }
    })
    $('#chargeTypeIdt').combobox({
        data: chargeType,
        valueField: 'id',
        textField: 'charge_type_name'
    })


    /**
     * 诊别下拉框
     */
    $('#clinicTypeId').combobox({
        data: chargeTypeDict,
        valueField: 'value',
        textField: 'label'
    })
    if(chargeTypeDict.length>0) {
        $("#clinicTypeId ").combobox('select',chargeTypeDict[0].value);
    }

    /**
     * 身份下拉框
     */
    $('#identityId').combobox({
        data: identityDict,
        valueField: 'id',
        textField: 'identityName'
    })
    $('#identityIdt').combobox({
        data: identityDict,
        valueField: 'id',
        textField: 'identityName'
    })
    if(identityDict.length>0) {
        $("#identityId ").combobox('select',identityDict[0].id);
    }
    /**
     * 合同单位下拉框
     */
    $('#companyId').combobox({
        data: unitContract,
        valueField: 'id',
        textField: 'unitName'
    })
    $('#companyIdt').combobox({
        data: unitContract,
        valueField: 'id',
        textField: 'unitName'
    })

    if(chargeType.length>0) {
        for(var i = 0;i<chargeType.length;i++){
            if(chargeType[i].charge_type_name == '自费'){
                $("#chargeTypeId ").combobox('select',chargeType[i].id);
                return;
            }else {
                $("#chargeTypeId ").combobox('select',chargeType[0].id);
            }
        }
    }

})
//获取挂号
function getClinicForRegist(){
    var liHtml="";
    var deptName=$("#deptNameId").combobox('getValue');
    var clinicTypeName=$("#clinicTypeNameId").val();
    var clinicDate=new Date().format('yyyy-MM-dd');
    /**
     * 获取号表
     */
    $.postJSON(basePath+'/clinicRegister/findListReg',"{\"clinicDept\":\""+deptName+"\",\"clinicDate\":\""+clinicDate+"\",\"clinicLabelName\":\""+clinicTypeName+"\"}",function(data){
        liHtml+='<ul>';
        for(var i=0;i<data.length;i++){
            var registrationNum=0;
            var registrationLimits="";
            var registrationXi=0;
            if(data[i].registrationNum!=null){
                registrationNum=data[i].registrationNum+data[i].appointmentNum;
            }
            if(data[i].registrationLimits!='0'){
                registrationLimits=data[i].registrationLimits
                registrationXi=data[i].registrationLimits;
            }
            liHtml+= '<li onclick="centerTypeActive(this,'+registrationNum+','+registrationXi+')" input_id="liClinicLabel'+i+'">'+
            '<div>' +
            '<strong>'+data[i].clinicLabelName+'</strong>' +
            ' <input type="hidden" value="'+data[i].id+'" class="simInput" >'+
            ' <input type="hidden" value="'+data[i].price+'" input_hidden="liClinicLabel'+i+'" >'+
            '</div>' +
            '<span style="padding-right:10px;">'+timeDescFormatter(data[i].timeDesc)+'</span>';
            liHtml=liHtml+'<a  class="color-red">'+registrationNum+'</a>/'+registrationLimits+
            '<span class="color-blue" style="padding-left:10px;">'+clinicDeptCodeFormatter(data[i].clinicDept, '', '')+'</span>' +
            '</li>' ;
        }
        liHtml+='</ul>';
        $("#clinicIndex").html(liHtml);

    },function(){
        $.messager.alert("提示信息","网络连接失败");
    });
}
//挂号
function openDialog(id,name){
    var clinic ;
    $('#clinicIndex li.active input.simInput').each(function (index, element) {
        clinic=$(this).val();
    })
    if(clinic==null || clinic=='undefined'){
        $.messager.alert("提示信息","请选择挂号类型");
        return false;
    }
    if($("#clinicForm").form("validate")) {
        $("#"+id).dialog({title: name}).dialog("open");
        var lis = $('#clinicIndex li.active');
        var price=0;
        var labelHtml="";
        for(var i=0;i<lis.length;i++){
            var li=lis[i];
            var inputName=$(li).attr("input_id");
            price= Number(price)+ Number($("input[input_hidden='"+inputName+"']").val());
            var clinicLabel=lis[i].getElementsByTagName("strong")[0].innerHTML;
            labelHtml+='<tbody>' +
            '<tr>' +
            ' <td name="clinicLabel" class="easyui-validatebox">'+clinicLabel+'</td>' +
            '</tr>' +
            '</tbody>';
        }
        $("#receiptsId").val(price);
        $("#receiptsHiddenId").val(price);
        $("#changeReceiptsId").val("");
        $("#clinicLabe").html(labelHtml);
    }
}
//退号
function openDialogRetreat(id,name){
    $("#"+id).dialog({title: name}).dialog("open");
}
function closeDialog(id){
    $("#"+id).dialog("close");
}

//改变实收触发
function onchangeInput(){
   var receipts=$("#receiptsId").val();
    var receiptsHidden=$("#receiptsHiddenId").val();
    if(receipts!=receiptsHidden){
        receipts=receipts-receiptsHidden;
        if(receipts>0){
            $("#changeReceiptsId").val(receipts);
        }else{
            $.messager.alert("提示信息","输入金额有误，请重新输入");
            $("#receiptsId").val(receiptsHidden);
            $("#changeReceiptsId").val("");
        }
    }else{
        $("#changeReceiptsId").val("");
    }
}
//选中号类
function centerTypeActive(li,num,xi){
    if(xi!=0){
        if(num>=xi){
            $.messager.alert("提示信息","号已经挂满不能再挂此号");
            return false;
        }
    }
    var classLi=$(li).attr("class");
    if(classLi=='active'){
        $(li).removeClass();
    }else{
        $(li).attr("class","active");
    }
}
//保存信息
function saveClinic(){
    var clinic;
    $('#clinicIndex li.active input.simInput').each(function (index, element) {
        clinic=$(this).val();
    })
    if(clinic==null || clinic=='undefined'){
        $.messager.alert("提示信息","请选择挂号类型");
        return false;
    }
    if(!$("#clinicForm").form("validate")) {
        return false;
    }
    var tableJson="[";
    $('#clinicIndex li.active input.simInput').each(function (index, element) {
        tableJson+='{"id":"'+$(this).val()+'"},';
    })
    tableJson = tableJson.substring(0, tableJson.length - 1);
    tableJson+="]";
    var formJson=fromJson('clinicForm');
    formJson = formJson.substring(0, formJson.length - 1);
    var submitJson=formJson+",\"clinicForRegists\":"+tableJson+"}";
    $.postJSON(basePath+"/clinicRegister/saveClinic",submitJson,function(data){
        sub = true;
        if(data != null){
            $.messager.alert("提示信息","挂号成功");
            $("#clinicForm").form('clear');
            $("#chargeId").dialog("close");
            for(var i = 0;i<data.length;i++){
                var printId = data[i].id;
                printPdf('/clinicRegister/print', 'id=' + printId);
            }
        }else{
            $.messager.alert("提示信息","挂号失败","error");
        }
        getClinicForRegist();
    }),function(data){
        $.messager.alert("提示信息","挂号失败","errorzn");
    }
}
//根据条件查询退号信息
function searchReturn(){
    var visitDate =$("#visitDate").datebox('getValue');
    var clinicNo=$("#clinicNoId").val();
    if(visitDate==null || visitDate==''){
        $.messager.alert("提示信息","查询时间不能为空");
        return;
    }
    if(clinicNo==null || clinicNo==''){
        $.messager.alert("提示信息","门诊号不能为空");
        return;
    }
    $.get(basePath+'/clinicReturned/getClinicMaster?visitDate='+visitDate+'&clinicNo='+clinicNo,function(data){
        if(data!=null){
            $("#regreteatInfo").form('load',data);
        }else{
            $.messager.alert("提示信息","没有可以退号信息");
        }
    });
}
//退号
function clinicReturnInfo(){
    var registrationStatus=$("#registrationStatusId").combobox('getValue');
    if(registrationStatus=='2'){
        $.messager.alert("提示信息","已经就诊，不能退号");
        return false;
    }
   /* var formJson=fromJson('regreteatInfo');
    alert(formJson);*/
    var clinicId=$("#clinicId").val();
    var charge=$("#charge").val();
    var returnDate=$("#returnDate").val();
    if(returnDate!=null && returnDate!=''){
        $.messager.alert("提示信息","已经退号，请勿重复退号");
        return;
    }
    if(clinicId=="" || clinicId==null){
        $.messager.alert("提示信息","请提取信息后在进行退号操作");
        return;
    }
    $.messager.confirm("确认消息", "确认进行退号？", function () {
        $.post(basePath+'/clinicReturned/returnedAcct?id='+clinicId,function(data){
            if(data.code=="1"){
                $.messager.alert("提示信息","退号成功，请退还病人"+charge+"元");
                $("#regreteatInfo").form('clear');
                closeDialog('backNumberId')
            }else{
                $.messager.alert("提示信息",data.code,"error");
            }

        }),function(data){
            $.messager.alert("提示信息","退号失败","error");
        }
    })

}




