var doctorName=[];
/**
 * 医生
 * @type {{}}
 */
var doctorNameData={};
doctorNameData.dictType="v_staff_dict";
var inputParamVos=new Array();
var InputParamVo1={};
InputParamVo1.colName='rownum';
InputParamVo1.colValue='20';
InputParamVo1.operateMethod='<';
inputParamVos.push(InputParamVo1);
doctorNameData.inputParamVos=inputParamVos;
$.ajax({
    'type': 'POST',
    'url':basePath+'/input-setting/listParam' ,
    data: JSON.stringify(doctorNameData),
    'contentType': 'application/json',
    'dataType': 'json',
    'async': false,
    'success': function(data){
        doctorName=data;
    }
});
/**
 * 医生翻译
 * @param value
 * @param rowData
 * @param rowIndex
 * @returns {string|string|string|string|string}
 */
function doctorNameFormatter(value, rowData, rowIndex) {
    if (value == 0 || value==undefined || value=='') {
        return;
    }
    var ident='';
    for (var i = 0; i < doctorName.length; i++) {
        if(doctorName[i]!=undefined){
            if (doctorName[i].id == value) {
                ident =doctorName[i].name;
            }
        }

    }
    var doctorNameData={};
    doctorNameData.dictType="v_staff_dict"
    if(ident==''){
        var inputParamVos=new Array();
        var InputParamVo1={};
        InputParamVo1.colName='rownum';
        InputParamVo1.colValue='20';
        InputParamVo1.operateMethod='<';
        var InputParamVo={};
        InputParamVo.colName='id';
        InputParamVo.colValue=value;
        InputParamVo.operateMethod='=';
        inputParamVos.push(InputParamVo);
        doctorNameData.inputParamVos=inputParamVos;
        $.ajax({
            'type': 'POST',
            'url':basePath+'/input-setting/listParam' ,
            data: JSON.stringify(doctorNameData),
            'contentType': 'application/json',
            'dataType': 'json',
            'async': false,
            'success': function(data){
                if(data.length>0){
                    doctorName.push(data[0]);
                    ident= data[0].name;
                }
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
    var doctorNameData={};
    doctorNameData.dictType="v_staff_dict"
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
    doctorNameData.inputParamVos=inputParamVos;
    $.ajax({
        'type': 'POST',
        'url':basePath+'/input-setting/listParam' ,
        data: JSON.stringify(doctorNameData),
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


function comboGridCompleting(q,id){
    var doctorNameData={};
    doctorNameData.dictType="v_staff_dict"
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
    doctorNameData.inputParamVos=inputParamVos;
    $.ajax({
        'type': 'POST',
        'url':basePath+'/input-setting/listParam' ,
        data: JSON.stringify(doctorNameData),
        'contentType': 'application/json',
        'dataType': 'json',
        'async': false,
        'success': function(data){
            $("#"+id).combogrid("grid").datagrid("loadData", data);
            $("#"+id).combogrid('setText',q);
        }
    });
}