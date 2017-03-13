//icd10
var icdData={};
icdData.dictType="v_emr_data_icd10";
icdData.isOrgId=false;
var inputParamVos=new Array();
var InputParamVo1={};
InputParamVo1.colName='rownum';
InputParamVo1.colValue='20';
InputParamVo1.operateMethod='<';
inputParamVos.push(InputParamVo1);
icdData.inputParamVos=inputParamVos;
var icdAllData = [];

//
function getDiagnosis(obj){
    $.ajax({
        'type': 'POST',
        'url':basePath+'/input-setting/listParam' ,
        data: JSON.stringify(icdData),
        'contentType': 'application/json',
        'dataType': 'json',
        'success': function(data){
            icdAllData = data;
            obj();
        }
    });
}


$.ajax({
    'type': 'POST',
    'url':basePath+'/input-setting/listParam' ,
    data: JSON.stringify(icdData),
    'contentType': 'application/json',
    'dataType': 'json',
    'success': function(data){
        icdAllData = data;
    }
});

//datagrid的自动补全

function icdGirdCompleting(q,id){
    var inputParamVos=new Array();
    var InputParamVo1={};
    InputParamVo1.colName='rownum';
    InputParamVo1.colValue='20';
    InputParamVo1.operateMethod='<';
    inputParamVos.push(InputParamVo1);
    if(q!='' && q!=null){
        var InputParamVo={};
        InputParamVo.colName='KEYWORD_SHUOMING';
        //InputParamVo.colValue=q.toUpperCase();
        InputParamVo.colValue=q;
        InputParamVo.operateMethod='like';
        inputParamVos.push(InputParamVo);
    }
    icdData.inputParamVos=inputParamVos;
    $.ajax({
        'type': 'POST',
        'url':basePath+'/input-setting/listParam' ,
        data: JSON.stringify(icdData),
        'contentType': 'application/json',
        'dataType': 'json',
        'success': function(data){
            icdAllData = data;
            var ed = $('#zhenduan').datagrid('getEditor', {index:rowNum1,field:'diagnosisId'});
            $(ed.target).combogrid("grid").datagrid("loadData", icdAllData);
            $(ed.target).combogrid('setText',q);
        }
    });
}






//form表单的自动补全
function icdCompleting(q,id){
    var icdData={};
    icdData.dictType="v_emr_data_icd10";
    icdData.isOrgId=false;
    var inputParamVos=new Array();
    var InputParamVo1={};
    InputParamVo1.colName='rownum';
    InputParamVo1.colValue='20';
    InputParamVo1.operateMethod='<';
    inputParamVos.push(InputParamVo1);
    if(q!='' && q!=null){

        var InputParamVo={};
        InputParamVo.colName='KEYWORD_SHUOMING';
        InputParamVo.colValue= q.toUpperCase();
        InputParamVo.operateMethod='like';
        inputParamVos.push(InputParamVo);
    }
    icdData.inputParamVos=inputParamVos;
    $.ajax({
        'type': 'POST',
        'url':basePath+'/input-setting/listParam' ,
        data: JSON.stringify(icdData),
        'contentType': 'application/json',
        'dataType': 'json',
        'success': function(data){
            $("#"+id).combogrid("grid").datagrid("loadData", data);
            $("#"+id).combogrid('setText',q);
        }
    });
}



/**
 * icd翻译
 * @param value
 * @param rowData
 * @param rowIndex
 * @returns {string|string|string}
 */
function icdFormatter(value, rowData, rowIndex) {
    if (rowData.icdName == 0 ||rowData.icdName == null) {
        return;
    }
    return rowData.icdName;
}


/**
 * Created by pq on 2016/7/20 0020.
 * 通过病人Id拿到病人姓名
 */
var loginUser={};
loginUser.dictType="persion_info";
loginUser.isOrgId = false;
var currentUser =[];

function formatUserName(value, rowData, rowIndex){

    var inputParamVos=new Array();
    if(value!='' && value!=null && value !='undefined'){
        var InputParamVo={};
        InputParamVo.colName='id';
        InputParamVo.colValue=value;
        InputParamVo.operateMethod='=';
        inputParamVos.push(InputParamVo);
        loginUser.inputParamVos=inputParamVos;
        $.ajax({
            'type': 'POST',
            'url':basePath+'/input-setting/listParam' ,
            data: JSON.stringify(loginUser),
            'contentType': 'application/json',
            'dataType': 'json',
            'async': false,
            'success': function(data){
                currentUser = data;
            }
        });
       if(currentUser==null ||currentUser ==''){
         return "";
       }else{
           return currentUser[0].name;
       }

    }else{
      return ;
    }

}