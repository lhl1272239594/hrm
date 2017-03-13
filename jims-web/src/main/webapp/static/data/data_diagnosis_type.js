/**
 * 住院诊断类型字典
 */
var diagnosisType = [];

/**
 * 诊断类型
 */

$.ajax({
    'type': 'GET',
    'url':basePath+'/dict/findListByType',
    data: 'type=DIAGNOSIS_TYPE',
    'contentType': 'application/json',
    'dataType': 'json',
    'async': false,
    'success': function(data){
        diagnosisType=data;
    }
});




/**
 * 诊断类型
 * @param value
 * @param rowData
 * @param rowIndex
 * @returns {string|string|string}
 */
function diagnosisTypeFormatter(value, rowData, rowIndex) {
    if (value == 0) {
        return;
    }
    for (var i = 0; i < diagnosisType.length; i++) {
        if (diagnosisType[i].value == value) {
            return diagnosisType[i].label;
        }
    }
}


