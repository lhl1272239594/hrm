/**
 * Created by pq on 2016/8/31 0031.
 * 翻译药品单位字典
 */
var doseUnit = [];
/**
 * 药品单位
 */

$.ajax({
    'type': 'GET',
    'url':basePath+'/dict/findListByType',
    data: 'type=dose_unit',
    'contentType': 'application/json',
    'dataType': 'json',
    'async': false,
    'success': function(data){
        doseUnit=data;
    }
});

/**
 * 药品单位翻译
 * @param value
 * @param rowData
 * @param rowIndex
 * @returns {string|string|string}
 */
function doseUnitFormatter(value, rowData, rowIndex) {
    if (value == undefined || value ==null||value==0) {
        return "";
    }

    for (var i = 0; i < doseUnit.length; i++) {
        if (doseUnit[i].value == value) {
            return doseUnit[i].label;
        }
    }
}
