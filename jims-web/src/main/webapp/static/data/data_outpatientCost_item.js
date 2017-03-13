/**
 * Created by Administrator on 2016/10/12.
 * 门诊划价 ，药品 var nonDrugs = [];//非药品
 */

var Drugs = [];//药品
var clinicCostData={};
clinicCostData.orgId = config.org_Id;
clinicCostData.dictType="PRICE_LIST";
var inputParamVos=new Array();
var InputParamVo1={};
InputParamVo1.colName = 'rownum';
InputParamVo1.colValue = "20";
InputParamVo1.operateMethod='<';
inputParamVos.push(InputParamVo1);
var InputParamVo2={};
InputParamVo2.colName = 'item_class';
InputParamVo2.colValue = "A";
InputParamVo2.operateMethod='=';
inputParamVos.push(InputParamVo2);
clinicCostData.inputParamVos=inputParamVos;
/**
 * 查药品
 */
$.ajax({
    'type': 'POST',
    'url':basePath+'/input-setting/listParam' ,
    data: JSON.stringify(clinicCostData),
    'contentType': 'application/json',
    'dataType': 'json',
    'async': false,
    'success': function(data){
        Drugs = data;
    }
});

function DrugsFormatter(q,id){
    var inputParamVos=new Array();
    var InputParamVo1={};
    InputParamVo1.colName = 'rownum';
    InputParamVo1.colValue = "20";
    InputParamVo1.operateMethod='<';
    var InputParamVo2={};
    InputParamVo2.colName = 'item_class';
    InputParamVo2.colValue = "A";
    InputParamVo2.operateMethod='=';
    inputParamVos.push(InputParamVo1);
    inputParamVos.push(InputParamVo2);
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
        data: JSON.stringify(clinicCostData),
        'contentType': 'application/json',
        'dataType': 'json',
        'async': false,
        'success': function(data){
            Drugs = data;

        }
    });
}