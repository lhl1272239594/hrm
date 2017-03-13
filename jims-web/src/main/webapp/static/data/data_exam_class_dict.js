var examSubClass = [] // 检查子类
var examSubClassData = {};
examSubClassData.dictType="EXAM_SUBCLASS_DICT";

$.ajax({
    'type': 'POST',
    'url': basePath + '/input-setting/listParam',
    data: JSON.stringify(examSubClassData),
    'contentType': 'application/json',
    'dataType': 'json',
    'async': false,
    'success': function (data) {
        examSubClass=data;
    }
});

/**
 * 检查类别翻译
 * @param value
 * @param rowData
 * @param rowIndex
 * @returns {*}
 */
function examSubClassFormatter(value,rowData,rowIndex){
    if(value == 0){
        return;
    }
    for(var i = 0; i<examSubClass.length; i++){
        if(examSubClass[i].id == value){
            return examSubClass[i].exam_subclass_name;
        }
    }
}