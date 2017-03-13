var mrStatus = [];

/**
 * 病历状态
 */
$.ajax({
    'type': 'GET',
    'url':basePath+'/dict/findListByType',
    data: 'type=mr_status_dict',
    'contentType': 'application/json',
    'dataType': 'json',
    'async': false,
    'success': function(data){
        mrStatus=data;
    }
});



/**
 *病历状态
 * @param value
 * @param rowData
 * @param RowIndex
 * @returns {*}
 */
function operationScaleNameFormatter(value, rowData, RowIndex) {
    if (value == 0) {
        return;
    }
    for (var i = 0; i < mrStatus.length; i++) {
        if (mrStatus[i].id == value) {
            return mrStatus[i].label;
        }
    }
}