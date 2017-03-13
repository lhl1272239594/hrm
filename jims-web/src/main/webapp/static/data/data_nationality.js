var nationalityDict = [];


/**
 * 国籍
 */

$.ajax({
    'type': 'GET',
    'url':basePath+'/dict/findListByType',
    data: 'type=NATIONALITY_DICT',
    'contentType': 'application/json',
    'dataType': 'json',
    'async': false,
    'success': function(data){
        nationalityDict=data;
    }
});

/**
 * 国籍翻译
 * @param value
 * @param rowData
 * @param rowIndex
 * @returns {string|string|string}
 */
function nationalityFormatter(value, rowData, rowIndex) {
    if (value == 0) {
        return;
    }

    for (var i = 0; i < nationalityDict.length; i++) {
        if (nationalityDict[i].value == value) {
            return nationalityDict[i].label;
        }
    }
}
