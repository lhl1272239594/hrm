
var skinResult =  [];

/**
 * 皮试结果
 */
$.ajax({
    'type': 'GET',
    'url':basePath+'/dict/findListByType',
    data: 'type=SKIN_RESULT_DICT',
    'contentType': 'application/json',
    'dataType': 'json',
    'async': false,
    'success': function(data){
        skinResult=data;
    }
});

/**
 * 皮试结果翻译
 * @param value
 * @param rowData
 * @param rowIndex
 * @returns {string|string|string}
 */
function skinResultFormatter(value, rowData, rowIndex) {
    if (value == 0) {
        return;
    }

    for (var i = 0; i < skinResult.length; i++) {
        if (skinResult[i].value == value) {
            return skinResult[i].label;
        }
    }
}