var isolationIndicator=[];//隔离标志
$.ajax({
    'type':'get',
    'url': basePath + '/dict/findListByType',
    data: 'type=QUARANTINE_DICT',
    'contentType':'application/json',
    'dataType':'json',
    'async':'false',
    'success':function(data){
        isolationIndicator=data;
    }

})
/**
 * 隔离标志翻译
 * @param value
 * @param rowData
 * @param RowIndex
 * @returns {*}
 */
function isolationIndicatorFormatter(value, rowData, RowIndex) {
    if (value == 0) {
        return;
    }
    for (var i = 0; i < isolationIndicator.length; i++) {
        if (isolationIndicator[i].value == value) {
            return isolationIndicator[i].label;
        }
    }
}