var clinicDeptCode=[];
/**
 * 门诊科室
 * @type {{}}
 */
var clinicDeptCodeData={};
clinicDeptCodeData.dictType="v_outp_dept_dict"
var inputParamVos=new Array();
var InputParamVo1={};
InputParamVo1.colName='OUTP_OR_INP';
InputParamVo1.colValue='(0,2)';
InputParamVo1.operateMethod='in';
inputParamVos.push(InputParamVo1);
clinicDeptCodeData.inputParamVos=inputParamVos;
$.ajax({
    'type': 'POST',
    'url':basePath+'/input-setting/listParam' ,
    data: JSON.stringify(clinicDeptCodeData),
    'contentType': 'application/json',
    'dataType': 'json',
    'async': false,
    'success': function(data){
        clinicDeptCode=data;
    }
});

/**
 * 科室翻译
 * @param value
 * @param rowData
 * @param rowIndex
 * @returns {string|string|string|string}
 */
function clinicDeptCodeFormatter(value, rowData, rowIndex) {
    if (value == 0) {
        return;
    }

    for (var i = 0; i < clinicDeptCode.length; i++) {
        if (clinicDeptCode[i].id == value) {
            return clinicDeptCode[i].dept_name;
        }
    }
}