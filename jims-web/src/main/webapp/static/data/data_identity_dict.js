var identityDict= [];
/**
 * 身份
 */
$.ajax({
    'type': 'get',
    'url':basePath+'/identity-dict/list' ,
    'contentType': 'application/json',
    'dataType': 'json',
    'async': false,
    'success': function(data){
        identityDict=data;
    }
});
/**
 * 身份翻译
 * @param value
 * @param rowData
 * @param rowIndex
 * @returns {string|string|string}
 */
function itemFormatter(value, rowData, rowIndex) {
    if (value == 0) {
        return;
    }

    for (var i = 0; i < identityDict.length; i++) {
        if (identityDict[i].id == value) {
            return identityDict[i].identityName;
        }
    }
}