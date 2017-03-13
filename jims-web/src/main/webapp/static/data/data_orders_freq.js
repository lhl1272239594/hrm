/**
 * Created by Administrator on 2016/6/29 0029.
 */
var performFreqDict = [];


/**
 * 频率
 */

$.ajax({
    'type': 'GET',
    'url':basePath+'/PerformFreqDict/findPer',
    'contentType': 'application/json',
    'dataType': 'json',
    'async': false,
    'success': function(data){
        performFreqDict=data;
    }
});




/**
 * 频率翻译
 * @param value
 * @param rowData
 * @param rowIndex
 * @returns {string|string|string}
 */
function performFreqFormatter(value, rowData, rowIndex) {
    if (value == 0) {
        return;
    }
    for (var i = 0; i < performFreqDict.length; i++) {
        if (performFreqDict[i].id == value) {
            return performFreqDict[i].freqDesc;
        }
    }
}

