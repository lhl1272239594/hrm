var groupType = [];//会诊类型

$.ajax({
    'type': 'GET',
    'url':basePath+'/dict/findListByType',
    data: 'type=GROUP_TYPE',
    'contentType': 'application/json',
    'dataType': 'json',
    'async': false,
    'success': function(data){
        groupType=data;
    }
});
/**
 * 会诊类型翻译
 * @param value
 * @param rowData
 * @param rowIndex
 * @returns {*}
 */
function groupTypeformatter(value,rowData,rowIndex){
    if(value == 0){
        return;
    }
    for(var i=0;i<groupType.length;i++){
        if(groupType[i].value == value){
            return groupType[i].label;
        }
    }
}