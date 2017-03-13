var westernDrugData = [];
var westernDrug={};

westernDrug.dictType="v_clinic_item_price"
var inputParamVos=new Array();
var InputParamVo1={};
InputParamVo1.colName='rownum';
InputParamVo1.colValue='20';
InputParamVo1.operateMethod='<';
inputParamVos.push(InputParamVo1);

var InputParamVo2={};
InputParamVo2.colName="item_class";
InputParamVo2.colValue="A";
InputParamVo2.operateMethod='=';
inputParamVos.push(InputParamVo2);
westernDrug.inputParamVos=inputParamVos;
/**
 * 西药药品
 */
$.ajax({
    'type': 'POST',
    'url':basePath+'/input-setting/listParam' ,
    data: JSON.stringify(westernDrug),
    'contentType': 'application/json',
    'dataType': 'json',
    'async': false,
    'success': function(data){

        westernDrugData = data;
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