var nurseClass= [];


/**
 * 护理等级
 */

$.ajax({
    'type': 'GET',
    'url':basePath+'/dict/findListByType',
    data: 'type=NURSING_CLASS_DICT',
    'contentType': 'application/json',
    'dataType': 'json',
    'async': false,
    'success': function(data){
        nurseClass=data;
    }
});
/**
 * 护理等级翻译
 * @param value
 * @param rowData
 * @param rowIndex
 * @returns {string|string|string}
 */
function nurseClassFormatter(value, rowData, rowIndex) {
    if (value == 0) {
        return;
    }

    for (var i = 0; i < nurseClass.length; i++) {
        if (nurseClass[i].value == value) {
            return nurseClass[i].label;
        }
    }
}