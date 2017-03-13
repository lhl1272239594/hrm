var priceItme= [];
var priceItmeData={};
priceItmeData.dictType="V_INPUT_REGISTRATION_LIST"
/**
 * 门诊收费
 */
$.ajax({
    'type': 'POST',
    'url':basePath+'/input-setting/listParam' ,
    data: JSON.stringify(priceItmeData),
    'contentType': 'application/json',
    'dataType': 'json',
    'async': false,
    'success': function(data){
        priceItme=data;
    }
});


/**
 * 回显项目价表翻译
 * @param value
 * @param rowData
 * @param rowIndex
 * @returns {string|string|string|string|string}
 */
function priceItmeFormatter(value, rowData, rowIndex) {
    if (value == 0) {
        return;
    }

    for (var i = 0; i < priceItme.length; i++) {
        if (priceItme[i].item_code == value) {
            return priceItme[i].item_name;
        }
    }
}