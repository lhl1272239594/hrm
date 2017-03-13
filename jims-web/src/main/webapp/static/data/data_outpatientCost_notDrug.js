/**
 * Created by Administrator on 2016/10/13.
 * 门诊划价 ，非药品 var nonDrugs = [];//非药品
 */

var NorDrugs = [];//非药品
var clinicCostNotDrugsData={};
clinicCostNotDrugsData.orgId = config.org_Id;
clinicCostNotDrugsData.dictType="V_CLINIC_NAME_PRICE";
var inputParamVos=new Array();
var InputParamVo1={};
InputParamVo1.colName = 'rownum';
InputParamVo1.colValue = "20";
InputParamVo1.operateMethod='<';
inputParamVos.push(InputParamVo1);
//var InputParamVo2={};
//InputParamVo2.colName = 'item_class';
//InputParamVo2.colValue = "('A','B')";
//InputParamVo2.operateMethod='not in ';
//inputParamVos.push(InputParamVo2);
clinicCostNotDrugsData.inputParamVos=inputParamVos;
/**
 * 查药品
 */
$.ajax({
    'type': 'POST',
    'url':basePath+'/input-setting/listParam' ,
    data: JSON.stringify(clinicCostNotDrugsData),
    'contentType': 'application/json',
    'dataType': 'json',
    'async': false,
    'success': function(data){
        NorDrugs = data;
    }
});

function NorDrugsFormatter(q,id){
    var clinicCostNotDrugsData={};
    clinicCostNotDrugsData.orgId=config.org_Id;
    clinicCostNotDrugsData.dictType="V_CLINIC_NAME_PRICE";
    var inputParamVos=new Array();
    var InputParamVo1={};
    InputParamVo1.colName = 'rownum';
    InputParamVo1.colValue = "20";
    InputParamVo1.operateMethod='<';
    //var InputParamVo2={};
    //InputParamVo2.colName = 'item_class';
    //InputParamVo2.colValue = "('A','B')";
    //InputParamVo2.operateMethod='not in ';
    inputParamVos.push(InputParamVo1);
    //inputParamVos.push(InputParamVo2);
    if(q!='' && q!=null){
        var InputParamVo={};
        InputParamVo.colName='input_code';
        InputParamVo.colValue=q;
        InputParamVo.operateMethod='like';
        inputParamVos.push(InputParamVo);
    }
    clinicCostNotDrugsData.inputParamVos=inputParamVos;
    if(q!='' && q!=null){
        var InputParamVo={};
        InputParamVo.colName='input_code';
        InputParamVo.colValue=q;
        InputParamVo.operateMethod='like';
        inputParamVos.push(InputParamVo);
    }
    clinicCostData.inputParamVos=inputParamVos;

    $.ajax({
        'type': 'POST',
        'url':basePath+'/input-setting/listParam' ,
        data: JSON.stringify(clinicCostNotDrugsData),
        'contentType': 'application/json',
        'dataType': 'json',
        'async': false,
        'success': function(data){
            NorDrugs = data;

        }
    });
}