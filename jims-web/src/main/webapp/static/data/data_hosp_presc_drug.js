var westernDrugData = [];
var westernDrug={};

westernDrug.orgId=orgId;
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
var InputParamVo3={};
InputParamVo3.colName="storage_id";
InputParamVo3.colValue=stg;
InputParamVo3.operateMethod='=';
inputParamVos.push(InputParamVo3);
westernDrug.inputParamVos=inputParamVos;
var comboGridComplete = [];
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


//药品自动补全
function comboGridCompleting(q,id){
    var drugNameData={};
    drugNameData.orgId=orgId;
    drugNameData.dictType="v_clinic_item_price"
    var inputParamVos=new Array();
    var InputParamVo1={};
    InputParamVo1.colName='rownum';
    InputParamVo1.colValue='20';
    InputParamVo1.operateMethod='<';
    var InputParamVo2={};
    InputParamVo2.colName="item_class";
    InputParamVo2.colValue='A';
    InputParamVo2.operateMethod='=';
    var InputParamVo3={};
    InputParamVo3.colName="storage_id";
    InputParamVo3.colValue=stg;
    InputParamVo3.operateMethod='=';
    inputParamVos.push(InputParamVo1,InputParamVo2,InputParamVo3);
    if(q!='' && q!=null){
        var InputParamVo={};
        InputParamVo.colName='input_code';
        InputParamVo.colValue=q;
        InputParamVo.operateMethod='like';
        inputParamVos.push(InputParamVo);
    }
    drugNameData.inputParamVos=inputParamVos;
    $.ajax({
        'type': 'POST',
        'url':basePath+'/input-setting/listParam' ,
        data: JSON.stringify(drugNameData),
        'contentType': 'application/json',
        'dataType': 'json',
        'async': false,
        'success': function(data){
            westernDrugData = data;

        }
    });
}
