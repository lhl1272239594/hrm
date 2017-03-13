var patientClass = [];


/**
 * 入院方式
 */

$.ajax({
    'type': 'GET',
    'url':basePath+'/dict/findListByType',
    data: 'type=METHOD_DICT',
    'contentType': 'application/json',
    'dataType': 'json',
    'async': false,
    'success': function(data){
        patientClass=data;
    }
});

/**
 * 入院方式翻译
 * @param value
 * @param rowData
 * @param rowIndex
 * @returns {string|string|string}
 */
function relationshipFormatter(value, rowData, rowIndex) {
    if (value == 0) {
        return;
    }

    for (var i = 0; i < patientClass.length; i++) {
        if (patientClass[i].value == value) {
            return patientClass[i].label;
        }
    }
}
