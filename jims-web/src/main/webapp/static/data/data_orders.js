var ordersType =  [];
var billingAttr = [];
var Oclass =[{ "value": "药品", "label": "药品" }, { "value": "非药品", "label": "非药品" }];










/**
 * 医嘱类型
 */
$.ajax({
    'type': 'GET',
    'url':basePath+'/dict/findListByType',
    data: 'type=REPEAT_INDICATOR_DICT',
    'contentType': 'application/json',
    'dataType': 'json',
    'async': false,
    'success': function(data){
        ordersType=data;
    }
});




/**
 * 医嘱类型翻译
 * @param value
 * @param rowData
 * @param rowIndex
 * @returns {string|string|string}
 */
function indicatorFormatter(value, rowData, rowIndex) {
    if (value ==null||value =="") {
        return "";
    }
    for (var i = 0; i < ordersType.length; i++) {
        if (ordersType[i].value == value) {
            return ordersType[i].label;
        }
    }
}





function orderClassFormatter(value, rowData, rowIndex) {
    if (rowData.orderClass == 0) {
        return;
    }
    if(rowData.orderClass=='A'||rowData.orderClass =='B'){
        return "药品";
    }else{
        return "非药品";
    }


}







/**
 * 计价属性
 */

$.ajax({
    'type': 'GET',
    'url':basePath+'/dict/findListByType',
    data: 'type=BILLING_ATTR_DICT',
    'contentType': 'application/json',
    'dataType': 'json',
    'async': false,
    'success': function(data){
        billingAttr=data;
    }
});

/**
 * 计价属性翻译
 * @param value
 * @param rowData
 * @param rowIndex
 * @returns {string|string|string}
 */
function billingAttrFormatter(value, rowData, rowIndex) {
    if (value == null) {
        return;
    }

    for (var i = 0; i < billingAttr.length; i++) {
        if (billingAttr[i].value == value) {
            return billingAttr[i].label;
        }
    }
}











