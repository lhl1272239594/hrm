/**
 * 药品厂商
 * @type {Array}
 */
var drugFirmDict = [];

/**
 *
 */

$.ajax({
    'type': 'GET',
    'url':basePath+'/drug-supplier-catalog/list',
    'contentType': 'application/json',
    'dataType': 'json',
    'async': false,
    'success': function(data){
        drugFirmDict=data;
    }
});
/**
 * 药品厂商翻译
 * @param value
 * @param rowData
 * @param rowIndex
 * @returns {string|string|string}
 */
function drugFirmFormatter(value, rowData, rowIndex) {
    if (value == 0) {
        return;
    }
    for (var i = 0; i < drugFirmDict.length; i++) {
        if (drugFirmDict[i].id == value) {
            return drugFirmDict[i].supplierId;
        }
    }
    return value;
}
