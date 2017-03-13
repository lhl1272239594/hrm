var doctorRole = [];
/**
 * 医生权限
 */
$.ajax({
    'type': 'GET',
    'url':basePath+'/dict/findListByType',
    data: 'type=doctor_role',
    'contentType': 'application/json',
    'dataType': 'json',
    'async': false,
    'success': function(data){
        doctorRole=data;
    }
});


/**
 *
 * @param value
 * @param rowData
 * @param RowIndex
 * @returns {*}
 */
function operationScaleNameFormatter(value, rowData, RowIndex) {
    if (value == 0) {
        return;
    }
    for (var i = 0; i < doctorRole.length; i++) {
        if (doctorRole[i].id == value) {
            return doctorRole[i].label;
        }
    }
}