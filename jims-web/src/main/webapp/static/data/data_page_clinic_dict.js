var clinicDictName=[];
/**
 * 诊疗项目
 * @type {{}}
 */
var clinicDictNameData={};
clinicDictNameData.dictType="V_CLINIC_NAME_DICT";
var inputParamVos=new Array();
var InputParamVo1={};
InputParamVo1.colName='rownum';
InputParamVo1.colValue='20';
InputParamVo1.operateMethod='<';
inputParamVos.push(InputParamVo1);
clinicDictNameData.inputParamVos=inputParamVos;
$.ajax({
    'type': 'POST',
    'url':basePath+'/input-setting/listParam' ,
    data: JSON.stringify(clinicDictNameData),
    'contentType': 'application/json',
    'dataType': 'json',
    'async': false,
    'success': function(data){
        clinicDictName=data;
    }
});
/**
 * 诊疗项目翻译
 * @param value
 * @param rowData
 * @param rowIndex
 * @returns {string|string|string|string|string}
 */
function clinicDictNameFormatter(value, rowData, rowIndex) {
    if (value == 0 || value==undefined) {
        return;
    }
    var ident='';
    for (var i = 0; i < clinicDictName.length; i++) {
        if (clinicDictName[i].id == value) {
            ident =clinicDictName[i].name;
        }
    }
    if(ident==''){
        var InputParamVo={};
        InputParamVo.colName='id';
        InputParamVo.colValue=value;
        InputParamVo.operateMethod='=';
        inputParamVos.push(InputParamVo);
        $.ajax({
            'type': 'POST',
            'url':basePath+'/input-setting/listParam' ,
            data: JSON.stringify(clinicDictNameData),
            'contentType': 'application/json',
            'dataType': 'json',
            'async': false,
            'success': function(data){
                clinicDictName.push(data[0]);
                ident= data[0].name;
            }
        });
        return  ident;
    }else{
        return ident;
    }
}

/**
 * dataGrid中自动补全
 * @param q
 */
function dataGridCompleting(q,dataId,column){
    var clinicDictNameData={};
    clinicDictNameData.dictType="V_CLINIC_NAME_DICT"
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
    clinicDictNameData.inputParamVos=inputParamVos;
    $.ajax({
        'type': 'POST',
        'url':basePath+'/input-setting/listParam' ,
        data: JSON.stringify(clinicDictNameData),
        'contentType': 'application/json',
        'dataType': 'json',
        'async': false,
        'success': function(data){
            clinicDictName=data;
            var ed = $('#'+dataId).datagrid('getEditor', {index:rowNum,field:column});
            $(ed.target).combogrid("grid").datagrid("loadData", data);
            $(ed.target).combogrid('setText',q);
        }
    });
}


function comboGridCompleting(q,id){
    var clinicDictNameData={};
    clinicDictNameData.dictType="V_CLINIC_NAME_DICT"
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
    }else{
        $("#"+id).combogrid('setValue','');
    }
    clinicDictNameData.inputParamVos=inputParamVos;
    $.ajax({
        'type': 'POST',
        'url':basePath+'/input-setting/listParam' ,
        data: JSON.stringify(clinicDictNameData),
        'contentType': 'application/json',
        'dataType': 'json',
        'async': false,
        'success': function(data){
            $("#"+id).combogrid("grid").datagrid("loadData", data);
            $("#"+id).combogrid('setText',q);
        }
    });
}