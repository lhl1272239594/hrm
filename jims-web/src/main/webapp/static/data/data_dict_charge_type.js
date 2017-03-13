var chargeTypeDict =  [];
/**
 * 诊别
 */
$.ajax({
    'type': 'GET',
    'url':basePath+'/dict/findListByType',
    data: 'type=charge_type',
    'contentType': 'application/json',
    'dataType': 'json',
    'async': false,
    'success': function(data){
        chargeTypeDict=data;
    }
});
/**
 * 诊别翻译
 * @param value
 * @param rowData
 * @param rowIndex
 * @returns {string|string|string}
 */
function itemFormatter(value, rowData, rowIndex) {
    if (value == 0) {
        return;
    }

    for (var i = 0; i < chargeTypeDict.length; i++) {
        if (chargeTypeDict[i].value == value) {
            return chargeTypeDict[i].label;
        }
    }
}