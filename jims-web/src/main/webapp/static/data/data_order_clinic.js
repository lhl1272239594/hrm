//非药品
var clinicData={};
clinicData.dictType="V_CLINIC_NAME_PRICE";
clinicData.orgId=config.org_Id;
var inputParamVos=new Array();
var InputParamVo1={};
InputParamVo1.colName='rownum';
InputParamVo1.colValue='20';
InputParamVo1.operateMethod='<';
inputParamVos.push(InputParamVo1);
clinicData.inputParamVos=inputParamVos;

var clinicOrderData = [];
$.ajax({
    'type': 'POST',
    'url':basePath+'/input-setting/listParam' ,
    data: JSON.stringify(clinicData),
    'contentType': 'application/json',
    'dataType': 'json',
    'async': false,
    'success': function(data){
        clinicOrderData = data;
    }
});

//非药品自动补全
function clinicCompleting(q,id){
    var clinicNameData={};
    clinicNameData.orgId=config.org_Id;
    clinicNameData.dictType="V_CLINIC_NAME_PRICE";
    var inputParamVos=new Array();
    var InputParamVo1={};
    InputParamVo1.colName='rownum';
    InputParamVo1.colValue='20';
    InputParamVo1.operateMethod='<';
    inputParamVos.push(InputParamVo1);
    if(q!='' && q!=null){
        var InputParamVo={};
        InputParamVo.colName='input_code';
        InputParamVo.colValue=q;
        InputParamVo.operateMethod='like';
        inputParamVos.push(InputParamVo);
    }
    clinicNameData.inputParamVos=inputParamVos;
    $.ajax({
        'type': 'POST',
        'url':basePath+'/input-setting/listParam' ,
        data: JSON.stringify(clinicNameData),
        'contentType': 'application/json',
        'dataType': 'json',
        'async': false,
        'success': function(data){
            clinicOrderData = data;

        }
    });
}