/**
 * Created by pq on 2016/8/29 0029.
 * 诊疗项目分类字典
 */


var clinicClassDict;


$.ajax({
    'type': 'GET',
    'url':basePath+'/dict/findListByType',
    data: 'type=CLINIC_ITEM_CLASS_DICT',
    'contentType': 'application/json',
    'dataType': 'json',
    'async': false,
    'success': function(data){
        clinicClassDict=data;
    }
});
/**
 * 翻译类别字典
 * @param value
 * @param rowData
 * @param rowIndex
 * @returns {*}
 */

function clinicClassFormatter(value, rowData, rowIndex) {
    if (value == undefined ||value == null) {
        return;
    }

    for (var i = 0; i < clinicClassDict.length; i++) {
        if (clinicClassDict[i].value == value) {
            return clinicClassDict[i].label;
        }
    }
}