var courseRecord  = [];//病程记录

$.ajax({
    'type':'get',
    'url': basePath + '/dict/findListByType',
    data: 'type=COURSE_RECORD_DICT',
    'contentType':'application/json',
    'dataType':'json',
    'async':'false',
    'success':function(data){
        courseRecord=data;
    }
})
/**
 * 手术病情翻译
 * @param value
 * @param rowData
 * @param RowIndex
 * @returns {*}
 */
function courseRecordFormatter(value, rowData, RowIndex) {
    if (value == 0) {
        return;
    }
    for (var i = 0; i < courseRecord.length; i++) {
        if (courseRecord[i].value == value) {
            return courseRecord[i].label;
        }
    }
}
