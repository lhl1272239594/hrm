var drugBillingAttr =[];//摆药类型


$.ajax({
    'type': 'GET',
    'url':basePath+'/dict/findListByType',
    data: 'type=BILLING_ATTR_DICT',
    'contentType': 'application/json',
    'dataType': 'json',
    'async': false,
    'success': function(data){
        drugBillingAttr=data;
    }
});

/**
 * @param value
 * @param rowData
 * @param rowIndex
 * @returns {*}
 */
function drugBillingAttrFormatter(value,rowData,rowIndex){
    for(var i = 0; i<drugBillingAttr.length; i++){
        if(drugBillingAttr[i].value == value){
            return drugBillingAttr[i].label;
        }
    }
}
