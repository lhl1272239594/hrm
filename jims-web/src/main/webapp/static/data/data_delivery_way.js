var deliveryWayData =  [];
 /**
 * 分娩方式
 */
$.ajax({
    'type': 'GET',
    'url':basePath+'/dict/findListByType',
    data: 'type=DELIVERY_WAY',
    'contentType': 'application/json',
    'dataType': 'json',
    'async': false,
    'success': function(data){
        deliveryWayData=data;
    }
});
/**
 * 分娩方式翻译
 * @param value
 * @param rowData
 * @param rowIndex
 * @returns {string|string|string}
 */
function setDataFormatter(value, rowData, rowIndex) {
    if (value == 0) {
        return;
    }

    for (var i = 0; i < deliveryWayData.length; i++) {
        if (deliveryWayData[i].value == value) {
            return deliveryWayData[i].label;
        }
    }
}