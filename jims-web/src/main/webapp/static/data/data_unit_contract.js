var unitContract=[];
/**
 * 合同单位
 */
$.ajax({
    'type': 'get',
    'url':basePath+'/UnitInContract/find-by-input-code' ,
    'contentType': 'application/json',
    'dataType': 'json',
    'async': false,
    'success': function(data){
        unitContract=data;
    }
});

/**
 *合同单位翻译
 * @param value
 * @param rowData
 * @param rowIndex
 * @returns {string|string|string|string|string}
 */
function unitContractFormatter(value, rowData, rowIndex) {
    if (value == 0) {
        return;
    }
    for (var i = 0; i < unitContract.length; i++) {
        if (unitContract[i].id == value) {
            return unitContract[i].unitName;
        }
    }
}