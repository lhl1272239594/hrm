/**
 * Created by Administrator on 2016/8/17.
 */


var clinicAttrCode=[];
/**
 * 门诊科室
 * @type {{}}
 */
var clinicAttrCodeData={};
clinicAttrCodeData.dictType="v_outp_dept_dict"
var inputParamVos=new Array();
var InputParamVo1={};
InputParamVo1.colName='CLINIC_ATTR';
InputParamVo1.colValue='1';
InputParamVo1.operateMethod='=';
inputParamVos.push(InputParamVo1);
clinicAttrCodeData.inputParamVos=inputParamVos;
$.ajax({
    'type': 'POST',
    'url':basePath+'/input-setting/listParam' ,
    data: JSON.stringify(clinicAttrCodeData),
    'contentType': 'application/json',
    'dataType': 'json',
    'async': false,
    'success': function(data){
        clinicAttrCode=data;
    }
});

/**
 * 临床科室翻译
 * @param value
 * @param rowData
 * @param rowIndex
 * @returns {string|string|string|string}
 */
function clinicAttrCodeFormatter(value, rowData, rowIndex) {
    if (value == 0) {
        return;
    }

    for (var i = 0; i < clinicAttrCode.length; i++) {
        if (clinicAttrCode[i].id == value) {
            return clinicAttrCode[i].dept_name;
        }
    }
}