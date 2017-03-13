var ordersDrugData={};
ordersDrugData.dictType="v_clinic_item_price";
ordersDrugData.inputParamVos =inputParamVos;
/*ordersDrugData.orgId=parent.config.org_Id;*/
ordersDrugData.orgId=config.org_Id;
var drugData = [];


/**
 * 药品
 */
$.ajax({
    'type': 'POST',
    'url':basePath+'/input-setting/listParam' ,
    data: JSON.stringify(ordersDrugData),
    'contentType': 'application/json',
    'dataType': 'json',
    'async': false,
    'success': function(data){
        drugData = data;
    }
});


//药品自动补全

function ordersDrugCom(q,id){
    var inputParamVos=new Array();
    inputParamVos.push(InputParamVo1);
    if(q!='' && q!=null){
        var InputParamVo={};
        InputParamVo.colName='input_code';
        InputParamVo.colValue=q;
        InputParamVo.operateMethod='like';
        inputParamVos.push(InputParamVo);
    }
    ordersDrugData.inputParamVos=inputParamVos;

    $.ajax({
        'type': 'POST',
        'url':basePath+'/input-setting/listParam' ,
        data: JSON.stringify(ordersDrugData),
        'contentType': 'application/json',
        'dataType': 'json',
        'async': false,
        'success': function(data){
            drugData = data;

        }
    });
}