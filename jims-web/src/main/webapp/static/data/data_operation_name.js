var operation=[];//手术名称
var operationData={};
//operationData.isOrgId=false;
operationData.dictType="clinic_item_dict"
$.ajax({ 
    'type': 'POST',
    'url':basePath+'/input-setting/listParam' ,
    data: JSON.stringify(operationData),
    'contentType': 'application/json',
    'dataType': 'json',
    'async': false,
    'success': function(data) {
        operation = data;
    }
})

function operationNameFormatter(value,rowData,rowIndex){
    if(value == 0){
        return ;
    }
    for(var i=0;i<operation.length;i++){
        if(operation[i].item_code == value){
            return operation[i].item_name;
        }
    }
}

/**
 * dataGrid中自动补全
 * @param q
 */
function dataGridCompletings(q,dataId,column){
    //var operationData={};
    ////doctorNameData.orgId="1";
    //operationData.isOrgId=false;
    //operationData.dictType="clinic_item_dict"
    //var inputParamVos=new Array();
    //var InputParamVo1={};
    //InputParamVo1.colName='rownum';
    //InputParamVo1.colValue='20';
    //InputParamVo1.operateMethod='<';
    //inputParamVos.push(InputParamVo1);
    ////var InputParamVo2={};
    ////InputParamVo2.colName='item_class';
    ////InputParamVo2.colValue='F';
    ////InputParamVo2.operateMethod='=';
    ////inputParamVos.push(InputParamVo2);
    //if(q!='' && q!=null){
    //    var InputParamVo={};
    //    InputParamVo.colName='input_code';
    //    InputParamVo.colValue=q;
    //    InputParamVo.operateMethod='like';
    //    inputParamVos.push(InputParamVo);
    //}
    operationData.inputParamVos=inputParamVos;
    $.ajax({
        'type': 'get',
        'url':basePath + '/clinicItem/list-by-item-class?orgId='+""+'&itemClass=F'+"&q="+q,
        //data: JSON.stringify(operationData),
        'contentType': 'application/json',
        'dataType': 'json',
        'async': false,
        'success': function(data){
            doctorName=data;
            var ed = $('#'+dataId).datagrid('getEditor', {index:rowNum,field:column});
            $(ed.target).combogrid("grid").datagrid("loadData", data);
            $(ed.target).combogrid('setText',q);
        }
    });
}