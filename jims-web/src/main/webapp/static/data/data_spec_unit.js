var specUnit =  [];
/**
 * 包装单位
 */
$.ajax({
    'type': 'GET',
    'url':basePath+'/dict/findListByType',
    data: 'type=spec_unit',
    'contentType': 'application/json',
    'dataType': 'json',
    'async': false,
    'success': function(data){
        specUnit=data;
    }
});
/**
 * 包装单位翻译
 * @param value
 * @param rowData
 * @param rowIndex
 * @returns {string|string|string}
 */
function specUnitFormatter(value, rowData, rowIndex) {
    if (value == 0) {
        return;
    }

    for (var i = 0; i < specUnit.length; i++) {
        if (specUnit[i].value == value) {
            return specUnit[i].label;
        }
    }
}