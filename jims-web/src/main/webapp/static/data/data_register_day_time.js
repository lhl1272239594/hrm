var timeInterval=[];


/**
 * 时间
 * @type {{}}
 */
var timeIntervalData={};
timeIntervalData.isOrgId=false;
timeIntervalData.dictType="TIME_INTERVAL_DICT"
$.ajax({
    'type': 'POST',
    'url':basePath+'/input-setting/listParam' ,
    data: JSON.stringify(timeIntervalData),
    'contentType': 'application/json',
    'dataType': 'json',
    'async': false,
    'success': function(data){
        timeInterval=data;
    }
});

/**
 * 时间翻译
 * @param value
 * @param rowData
 * @param rowIndex
 * @returns {string|string|string|string}
 */
function timeDescFormatter(value, rowData, rowIndex) {
    if (value == 0) {
        return;
    }

    for (var i = 0; i < timeInterval.length; i++) {
        if (timeInterval[i].time_interval_code == value) {
            return timeInterval[i].time_interval_name;
        }
    }
}