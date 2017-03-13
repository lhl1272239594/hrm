var payWayDict =  [];
var payWayParam={};
payWayParam.inpIndicator=1;
payWayParam.orgId=parent.config.org_Id;
 /**
 * 支付方式
 */
$.ajax({
    'type': 'POST',
    'url':basePath+'/pay-way-dict/list-by-inpIndicator',
    data: JSON.stringify(payWayParam),
    'contentType': 'application/json',
    'dataType': 'json',
    'async': false,
    'success': function(data){
        payWayDict=data;
    }
});
/**
 * 支付方式译
 * @param value
 * @param rowData
 * @param rowIndex
 * @returns {string|string|string}
 */
function payWayFormatter(value, rowData, rowIndex) {
    if (value == 0) {
        return;
    }

    for (var i = 0; i < payWayDict.length; i++) {
        if (payWayDict[i].id == value) {
            return payWayDict[i].payWayName;
        }
    }
}