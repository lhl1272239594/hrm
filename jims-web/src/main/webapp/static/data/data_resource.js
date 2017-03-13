var resourceDict = [];


/**
 * 入院来源
 */

$.ajax({
    'type': 'GET',
    'url':basePath+'/dict/findListByType',
    data: 'type=RESOURCE_DICT',
    'contentType': 'application/json',
    'dataType': 'json',
    'async': false,
    'success': function(data){
        resourceDict=data;
    }
});

/**
 * 入院来源翻译
 * @param value
 * @param rowData
 * @param rowIndex
 * @returns {string|string|string}
 */
function resourceFormatter(value, rowData, rowIndex) {
    if (value == 0) {
        return;
    }

    for (var i = 0; i < resourceDict.length; i++) {
        if (resourceDict[i].value == value) {
            return resourceDict[i].label;
        }
    }
}
