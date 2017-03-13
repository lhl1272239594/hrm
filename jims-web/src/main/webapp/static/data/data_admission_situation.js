var admissionDict = [];


/**
 * 入院情况
 */

$.ajax({
    'type': 'GET',
    'url':basePath+'/dict/findListByType',
    data: 'type=ADMISSION_SITUATION_DICT',
    'contentType': 'application/json',
    'dataType': 'json',
    'async': false,
    'success': function(data){
        admissionDict=data;
    }
});

/**
 * 入院情况翻译
 * @param value
 * @param rowData
 * @param rowIndex
 * @returns {string|string|string}
 */
function admissionFormatter(value, rowData, rowIndex) {
    if (value == 0) {
        return;
    }

    for (var i = 0; i < admissionDict.length; i++) {
        if (admissionDict[i].value == value) {
            return admissionDict[i].label;
        }
    }
}
