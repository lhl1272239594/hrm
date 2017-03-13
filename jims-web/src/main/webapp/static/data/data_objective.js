var objectiveDict = [];


/**
 * 住院目的
 */

$.ajax({
    'type': 'GET',
    'url':basePath+'/dict/findListByType',
    data: 'type=OBJECTIVE_DICT',
    'contentType': 'application/json',
    'dataType': 'json',
    'async': false,
    'success': function(data){
        objectiveDict=data;
    }
});

/**
 * 住院目的翻译
 * @param value
 * @param rowData
 * @param rowIndex
 * @returns {string|string|string}
 */
function objectiveFormatter(value, rowData, rowIndex) {
    if (value == 0) {
        return;
    }

    for (var i = 0; i < objectiveDict.length; i++) {
        if (objectiveDict[i].value == value) {
            return objectiveDict[i].label;
        }
    }
}
