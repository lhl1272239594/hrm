var nationDict = [];


/**
 * 民族
 */

$.ajax({
    'type': 'GET',
    'url':basePath+'/dict/findListByType',
    data: 'type=NATION_DICT',
    'contentType': 'application/json',
    'dataType': 'json',
    'async': false,
    'success': function(data){
        nationDict=data;
    }
});

/**
 * 民族翻译
 * @param value
 * @param rowData
 * @param rowIndex
 * @returns {string|string|string}
 */
function nationFormatter(value, rowData, rowIndex) {
    if (value == 0) {
        return;
    }

    for (var i = 0; i < nationDict.length; i++) {
        if (nationDict[i].value == value) {
            return nationDict[i].label;
        }
    }
}
