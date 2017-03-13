var operationScaleName = [];//手术等级

$.ajax({
    'type': 'GET',
    'url':basePath+'/dict/findListByType',
    data: 'type=OPERATION_SCALE_DICT',
    'contentType': 'application/json',
    'dataType': 'json',
    'async': false,
    'success': function(data){
        operationScaleName=data;
    }
});


/**
 * 手术等级翻译
 * @param value
 * @param rowData
 * @param RowIndex
 * @returns {*}
 */
function operationScaleFormatter(value, rowData, RowIndex) {
    if (value == 0) {
        return;
    }
    for (var i = 0; i < operationScaleName.length; i++) {
        if (operationScaleName[i].value == value) {
            return operationScaleName[i].label;
        }
    }
}