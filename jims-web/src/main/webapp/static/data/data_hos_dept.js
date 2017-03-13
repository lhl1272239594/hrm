var hosDeptCode=[];
/**
 * 住院科室
 * @type {{}}
 */
var hosDeptCodeData={};
hosDeptCodeData.dictType="v_outp_dept_dict"
var inputParamVos=new Array();
var InputParamVo1={};
InputParamVo1.colName='OUTP_OR_INP';
InputParamVo1.colValue='(1,2)';
InputParamVo1.operateMethod='in';
inputParamVos.push(InputParamVo1);
hosDeptCodeData.inputParamVos=inputParamVos;
$.ajax({
    'type': 'POST',
    'url':basePath+'/input-setting/listParam' ,
    data: JSON.stringify(hosDeptCodeData),
    'contentType': 'application/json',
    'dataType': 'json',
    'async': false,
    'success': function(data){
        hosDeptCode=data;
    }
});

/**
 * 科室翻译
 * @param value
 * @param rowData
 * @param rowIndex
 * @returns {string|string|string|string}
 */
function hosDeptCodeFormatter(value, rowData, rowIndex) {
    if (value == 0) {
        return;
    }

    for (var i = 0; i < hosDeptCode.length; i++) {
        if (hosDeptCode[i].id == value) {
            return hosDeptCode[i].dept_name;
        }
    }
}