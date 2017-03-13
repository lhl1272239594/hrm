var relationshipDict = [];


/**
 * 关系
 */

$.ajax({
    'type': 'GET',
    'url':basePath+'/dict/findListByType',
    data: 'type=RELATIONSHIP_DICT',
    'contentType': 'application/json',
    'dataType': 'json',
    'async': false,
    'success': function(data){
        relationshipDict=data;
    }
});

/**
 * 关系翻译
 * @param value
 * @param rowData
 * @param rowIndex
 * @returns {string|string|string}
 */
function relationshipFormatter(value, rowData, rowIndex) {
    if (value == 0) {
        return;
    }

    for (var i = 0; i < relationshipDict.length; i++) {
        if (relationshipDict[i].value == value) {
            return relationshipDict[i].label;
        }
    }
}
