var methodDict=[];
/**
 * 入院方式
 */

$.ajax({
    'type': 'POST',
    'url':basePath+'/dict/findListByType' ,
    data: 'type=METHOD_DICT',
    'contentType': 'application/json',
    'dataType': 'json',
    'async': false,
    'success': function(data){
        methodDict=data;
    }
});
/**
 * 途径翻译
 * @param value
 * @param rowData
 * @param rowIndex
 * @returns {string|string|string}
 */
function methodDictFormatter(value, rowData, rowIndex) {
    if (value == 0) {
        return;
    }
    for (var i = 0; i < methodDict.length; i++) {
        if (methodDict[i].id == value) {
            return methodDict[i].label;
        }
    }
}
