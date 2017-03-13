
//加载门诊号别
$(function(){
    jQuery(document).bind('keydown', 'F1',function (evt){
        appointsRegister();
        return false;
    });

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
    if(clinicDeptCode.length){
        $("#visitDeptId").combobox('select',clinicDeptCode[0].id);
    }

    /**
     * 性别下拉框
     */
    $('#setId').combobox({
        data: setData,
        valueField: 'value',
        textField: 'label'
    })

    if(setData.length){
        $("#setId ").combobox('select',setData[0].value);
    }

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

    if(chargeType.length){
        $("#chargeTypeId ").combobox('select',chargeType[0].id);
    }

    /**
     * 诊别下拉框
     */
    $('#clinicTypeId').combobox({
        data: chargeTypeDict,
        valueField: 'value',
        textField: 'label'
    })

    if(chargeTypeDict.length){
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
    if(identityDict.length){
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
})
//获取挂号
function getClinicForRegist(){
    var liHtml="";
    var deptName=$("#deptNameId").combobox('getValue');
    var clinicTypeName=$("#clinicTypeNameId").val();
    var clinicDate=$("#clinicDateId").datebox('getValue');
    /**
     * 获取号表
     */
    $.postJSON(basePath+'/clinicRegister/findListReg',"{\"clinicDept\":\""+deptName+"\",\"clinicDate\":\""+clinicDate+"\",\"clinicLabelName\":\""+clinicTypeName+"\"}",function(data){
        liHtml+='<ul>';
        for(var i=0;i<data.length;i++){
            var appointmentNum=0;
            var appointmentLimits="";
            var registrationXi=0;
            if(data[i].appointmentNum!=null){
                appointmentNum=data[i].appointmentNum+data[i].registrationNum;;
            }
            if(data[i].appointmentLimits!='0'){
                appointmentLimits=data[i].appointmentLimits;
                registrationXi=data[i].appointmentLimits;
            }
            liHtml+= '<li onclick="centerTypeActive(this,'+appointmentNum+','+registrationXi+')" input_id="liClinicLabel'+i+'">'+
            '<div>' +
            '<strong>'+data[i].clinicLabelName+'</strong>' +
            ' <input type="hidden" value="'+data[i].id+'" class="simInput" >'+
            ' <input type="hidden" value="'+data[i].price+'" input_hidden="liClinicLabel'+i+'" >'+
            '</div>' +
            '<span style="padding-right:10px;">'+timeDescFormatter(data[i].timeDesc)+'</span>';

            liHtml=liHtml+'<a  class="color-red">'+appointmentNum+'</a>/'+appointmentLimits+
            '<span class="color-blue" style="padding-left:10px;">'+clinicDeptCodeFormatter(data[i].clinicDept, '', '')+'</span>' +
            '</li>' ;
        }
        liHtml+='</ul>';
        $("#registerCenter").html(liHtml);

    },function(){
        $.messager.alert("提示信息","网络连接失败");
    });
}


function centerTypeActive(li,num,xi){
    if(xi!=0){
        if(num>=xi){
            $.messager.alert("提示信息","号已经挂满不能再进行预约");
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
//预约挂号 ---
function appointsRegister(){
   if($("#patMasterInfoForm").form("validate")) {
        var tableJson = "[";
        $('#registerCenter li.active input.simInput').each(function (index, element) {
            tableJson += '{"id":"' + $(this).val() + '"},';
        });
        tableJson = tableJson.substring(0, tableJson.length - 1);
        tableJson += "]";

        if(tableJson.length<='1' ){
            $.messager.alert("提示信息", "请选择所要预约的号别");
            return false;
        }
        var formJson = fromJson('patMasterInfoForm');
        formJson = formJson.substring(0, formJson.length - 1);
        var submitJson = formJson + ",\"clinicForRegistList\":" + tableJson + "}";
        $.postJSON(basePath + '/clinicAppoints/saveAppoints', submitJson, function (data) {
            if (data.code == "1") {
                $.messager.alert("提示信息", "预约成功");
                $("#patMasterInfoForm").form('clear');
                sub = true;
                getClinicForRegist()
            } else {
                $.messager.alert("提示信息", "预约失败", "error");
            }

        }, function (data) {
            $.messager.alert("提示信息", "预约失败", "error");
        })
    }
}