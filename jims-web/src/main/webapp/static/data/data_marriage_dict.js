var marriageDict = [];
/**
 * 婚姻状况
 */

$.ajax({
    'type': 'GET',
    'url':basePath+'/dict/findListByType',
    data: 'type=MARRIAGE_DICT',
    'contentType': 'application/json',
    'dataType': 'json',
    'async': false,
    'success': function(data){
        marriageDict=data;
    }
});

/**
 * 婚姻状况翻译
 * @param value
 * @param rowData
 * @param rowIndex
 * @returns {string|string|string}
 */
function marriageFormatter(value, rowData, rowIndex) {
    if (value == 0) {
        return;
    }

    for (var i = 0; i < marriageDict.length; i++) {
        if (marriageDict[i].value == value) {
            return marriageDict[i].label;
        }
    }
}
