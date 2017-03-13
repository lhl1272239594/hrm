
var prescAttrDict = [];

/**
 * 途径
 */

$.ajax({
    'type': 'GET',
    'url':basePath+'/dict/findListByType',
    data: 'type=PRESC_ATTR_DICT',
    'contentType': 'application/json',
    'dataType': 'json',
    'async': false,
    'success': function(data){
        prescAttrDict=data;
    }
});
/**
 * 途径翻译
 * @param value
 * @param rowData
 * @param rowIndex
 * @returns {string|string|string}
 */
function prescAttrFormatter(value, rowData, rowIndex) {
    if (value == 0) {
        return;
    }
    for (var i = 0; i < prescAttrDict.length; i++) {
        if (prescAttrDict[i].value == value) {
            return prescAttrDict[i].label;
        }
    }
}
