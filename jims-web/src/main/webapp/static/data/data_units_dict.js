//var units = [{"value": "1", "text": "毫升"}, {"value": "2", "text": "单位"}, {"value": "3", "text": "人/份"}];
var units = [];
/**
 * 检验标本
 */
$.ajax({
    'type': 'GET',
    'url': basePath + '/dict/findListByType',
    data: 'type=BLOOD_UNIT_DICT',
    'contentType': 'application/json',
    'dataType': 'json',
    'async': false,
    'success': function (data) {
        units = data;
    }
})
/**
 * 血量单位翻译
 * @param value
 * @param rowData
 * @param rowIndex
 * @returns {string|string|string}
 */
function unitsFormatter(value, rowData, rowIndex) {
    if (value == 0) {
        return;
    }
    for (var i = 0; i < units.length; i++) {
        if (units[i].value == value) {
            return units[i].label;
        }
    }
}