var inpRcptFee=[];//住院收据类型

$.ajax({
    'type': 'GET',
    'url':basePath+'/dict/findListByType',
    data: 'type=INP_RCPT_FEE_DICT',
    'contentType': 'application/json',
    'dataType': 'json',
    'async': false,
    'success': function(data){
        inpRcptFee=data;
    }
});
/**
 * 血型翻译
 * @param value
 * @param rowData
 * @param rowIndex
 * @returns {*}
 */
function inpRcptFeeFormatter(value,rowData,rowIndex){
    if(value == 0){
        return;
    }
    for(var i = 0; i<inpRcptFee.length; i++){
        if(inpRcptFee[i].value == value){
            return inpRcptFee[i].label;
        }
    }
}