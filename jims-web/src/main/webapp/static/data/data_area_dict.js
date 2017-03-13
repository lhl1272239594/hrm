var areaDictData = [];
var areaDict={};
areaDict.isOrgId=false;
areaDict.dictType="V_SYS_DICT_AREA"
var inputParamVos=new Array();
var InputParamVo1={};
InputParamVo1.colName='rownum';
InputParamVo1.colValue='20';
InputParamVo1.operateMethod='<';
inputParamVos.push(InputParamVo1);

areaDict.inputParamVos=inputParamVos;
/**
 * 地区
 */
$.ajax({
    'type': 'POST',
    'url':basePath+'/input-setting/listParam' ,
    data: JSON.stringify(areaDict),
    'contentType': 'application/json',
    'dataType': 'json',
    'async': false,
    'success': function(data){

        areaDictData = data;
    }
});
/**
 * 地区翻译
 * @param value
 * @param rowData
 * @param rowIndex
 * @returns {*}
 */
function areaFormatter(value,rowData,rowIndex){
    if(value == 0  || value==undefined || value==''){
        return ;
    }
    var ident='';
    for(var i=0;i<areaDictData.length;i++){
        if(areaDictData[i].value == value){
            ident = areaDictData[i].label;
        }
    }
    var areaName={};
    areaName.isOrgId=false;
    areaName.dictType="V_SYS_DICT_AREA"
    if(ident==''){
        var inputParamVos=new Array();
        var InputParamVo1={};
        InputParamVo1.colName='rownum';
        InputParamVo1.colValue='20';
        InputParamVo1.operateMethod='<';
        var InputParamVo={};
        InputParamVo.colName='value';
        InputParamVo.colValue=value;
        InputParamVo.operateMethod='=';
        inputParamVos.push(InputParamVo);
        areaName.inputParamVos=inputParamVos;
        $.ajax({
            'type': 'POST',
            'url':basePath+'/input-setting/listParam' ,
            data: JSON.stringify(areaName),
            'contentType': 'application/json',
            'dataType': 'json',
            'async': false,
            'success': function(data){
                if(data.length>0){
                    areaDictData.push(data[0]);
                    ident= data[0].label;
                }

            }
        });
        return  ident;
    }else{
        return ident;
    }
}
//自动补全
function areacomboGridCompleting(q,id){
    var areaDict={};
    areaDict.dictType="V_SYS_DICT_AREA"
    areaDict.isOrgId=false;
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
    areaDict.inputParamVos=inputParamVos;
    $.ajax({
        'type': 'POST',
        'url':basePath+'/input-setting/listParam' ,
        data: JSON.stringify(areaDict),
        'contentType': 'application/json',
        'dataType': 'json',
        'async': false,
        'success': function(data){
            //areaDictData = data;
            $("#"+id).combogrid("grid").datagrid("loadData", data);
            $("#"+id).combogrid('setText',q);
        }
    });
}
