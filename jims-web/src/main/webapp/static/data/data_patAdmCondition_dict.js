var patAdmCondition=[];
/**
 * 入院病情
 */

$.ajax({
    'type': 'POST',
    'url':basePath+'/dict/findListByType' ,
    data: 'type=PAT_ADM_CONDITION_DICT',
    'contentType': 'application/json',
    'dataType': 'json',
    'async': false,
    'success': function(data){
        patAdmCondition=data;
    }
});
/**
 * 入院病情翻译
 * @param value
 * @param rowData
 * @param rowIndex
 * @returns {string|string|string}
 */
function patAdmConditionFormatter(value, rowData, rowIndex) {
    if (value == 0) {
        return;
    }
    for (var i = 0; i < patAdmCondition.length; i++) {
        if (patAdmCondition[i].id == value) {
            return patAdmCondition[i].label;
        }
    }
}
