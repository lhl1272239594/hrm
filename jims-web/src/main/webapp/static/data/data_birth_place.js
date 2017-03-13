var placeOfbirth =  [];
 /**
 * 出生地字典
 */
$.ajax({
    'type': 'GET',
    'url':basePath+'/dict/findListByType',
    data: 'type=PALCE_OF_BIRTH',
    'contentType': 'application/json',
    'dataType': 'json',
    'async': false,
    'success': function(data){
        placeOfbirth=data;
    }
});
/**
 * 出生地翻译
 * @param value
 * @param rowData
 * @param rowIndex
 * @returns {string|string|string}
 */
function setDataFormatter(value, rowData, rowIndex) {
    if (value == 0) {
        return;
    }

    for (var i = 0; i < placeOfbirth.length; i++) {
        if (placeOfbirth[i].value == value) {
            return placeOfbirth[i].label;
        }
    }
}