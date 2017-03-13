var setData =  [];
 /**
 * 性别
 */
$.ajax({
    'type': 'GET',
    'url':basePath+'/dict/findListByType',
    data: 'type=SEX_DICT',
    'contentType': 'application/json',
    'dataType': 'json',
    'async': false,
    'success': function(data){
        setData=data;
    }
});
/**
 * 性别翻译
 * @param value
 * @param rowData
 * @param rowIndex
 * @returns {string|string|string}
 */
function setDataFormatter(value, rowData, rowIndex) {
    if (value == 0) {
        return;
    }

    for (var i = 0; i < setData.length; i++) {
        if (setData[i].value == value) {
            return setData[i].label;
        }
    }
}