var registerTyp=[];
/**
 * 获取号类
 */
$.ajax({
    'type': 'get',
    'url':basePath+'/clinicType/findList' ,
    'contentType': 'application/json',
    'dataType': 'json',
    'async': false,
    'success': function(data){
        registerTyp=data;
    }
});
/**
 * 翻译号类
 * @param value
 * @param rowData
 * @param rowIndex
 * @returns {string|string|string|string|string}
 */
function registerTypFormatter(value, rowData, rowIndex) {
    if (value == 0) {
        return;
    }
    for (var i = 0; i < registerTyp.length; i++) {
        if (registerTyp[i].id == value) {
            return registerTyp[i].clinicTypeName;
        }
    }
}
