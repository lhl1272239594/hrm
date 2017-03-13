var bloodType=[];//血型

$.ajax({
    'type': 'GET',
    'url':basePath+'/dict/findListByType',
    data: 'type=blood_type_dict',
    'contentType': 'application/json',
    'dataType': 'json',
    'async': false,
    'success': function(data){
        bloodType=data;
    }
});
/**
 * 血型翻译
 * @param value
 * @param rowData
 * @param rowIndex
 * @returns {*}
 */
function bloodTypeFormatter(value,rowData,rowIndex){
    if(value == 0){
        return;
    }
    for(var i = 0; i<bloodType.length; i++){
        if(bloodType[i].value == value){
            return bloodType[i].label;
        }
    }
}