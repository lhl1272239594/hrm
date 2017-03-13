var professionDict = [];


/**
 * 职业
 */

$.ajax({
    'type': 'GET',
    'url':basePath+'/dict/findListByType',
    data: 'type=PROFESSION_DICT',
    'contentType': 'application/json',
    'dataType': 'json',
    'async': false,
    'success': function(data){
        professionDict=data;
    }
});

/**
 * 职业翻译
 * @param value
 * @param rowData
 * @param rowIndex
 * @returns {string|string|string}
 */
function professionFormatter(value, rowData, rowIndex) {
    if (value == 0) {
        return;
    }

    for (var i = 0; i < professionDict.length; i++) {
        if (professionDict[i].value == value) {
            return professionDict[i].label;
        }
    }
}
