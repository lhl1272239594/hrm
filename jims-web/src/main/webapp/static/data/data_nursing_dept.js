var nursingDeptCode=[];
/**
 * 护理单元
 * @type {{}}
 */
var nursingDeptCodeData={};
nursingDeptCodeData.dictType="v_outp_dept_dict"
var inputParamVos=new Array();
var InputParamVo1={};
InputParamVo1.colName='OUTP_OR_INP';
InputParamVo1.colValue='(1,2)';
InputParamVo1.operateMethod='in';
var InputParamVo2={};
InputParamVo2.colName='CLINIC_ATTR';
InputParamVo2.colValue='2';
InputParamVo2.operateMethod='=';
inputParamVos.push(InputParamVo1);
inputParamVos.push(InputParamVo2);
nursingDeptCodeData.inputParamVos=inputParamVos;
$.ajax({
    'type': 'POST',
    'url':basePath+'/input-setting/listParam' ,
    data: JSON.stringify(nursingDeptCodeData),
    'contentType': 'application/json',
    'dataType': 'json',
    'async': false,
    'success': function(data){
        nursingDeptCode=data;
    }
});

/**
 * 科室翻译
 * @param value
 * @param rowData
 * @param rowIndex
 * @returns {string|string|string|string}
 */
function nursingDeptCodeFormatter(value, rowData, rowIndex) {
    if (value == 0) {
        return;
    }

    for (var i = 0; i < nursingDeptCode.length; i++) {
        if (nursingDeptCode[i].id == value) {
            return nursingDeptCode[i].dept_name;
        }
    }
}