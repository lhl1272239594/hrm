var deliveryResultData =  [];
 /**
 * 分娩结果
 */
$.ajax({
    'type': 'GET',
    'url':basePath+'/dict/findListByType',
    data: 'type=DELIVERY_RESULT',
    'contentType': 'application/json',
    'dataType': 'json',
    'async': false,
    'success': function(data){
        deliveryResultData=data;
    }
});
/**
 * 分娩结果翻译
 * @param value
 * @param rowData
 * @param rowIndex
 * @returns {string|string|string}
 */
function setDataFormatter(value, rowData, rowIndex) {
    if (value == 0) {
        return;
    }

    for (var i = 0; i < deliveryResultData.length; i++) {
        if (deliveryResultData[i].value == value) {
            return deliveryResultData[i].label;
        }
    }
}