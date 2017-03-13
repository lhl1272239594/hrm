var item =  [];
/**
 * 挂号项目
 */
$.ajax({
    'type': 'GET',
    'url':basePath+'/dict/findListByType',
    data: 'type=classno_itme_dict',
    'contentType': 'application/json',
    'dataType': 'json',
    'async': false,
    'success': function(data){
        item=data;
    }
});
/**
 * 收费项目翻译
 * @param value
 * @param rowData
 * @param rowIndex
 * @returns {string|string|string}
 */
function itemFormatter(value, rowData, rowIndex) {
    if (value == 0) {
        return;
    }

    for (var i = 0; i < item.length; i++) {
        if (item[i].value == value) {
            return item[i].label;
        }
    }
}