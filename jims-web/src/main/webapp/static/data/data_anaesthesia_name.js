var anaesthesiaName =[];//麻醉方式


$.ajax({
    'type': 'GET',
    'url':basePath+'/dict/findListByType',
    data: 'type=ANAESTHESIA_DICT',
    'contentType': 'application/json',
    'dataType': 'json',
    'async': false,
    'success': function(data){
        anaesthesiaName=data;
    }
});

/**
 * 麻醉方式翻译
 * @param value
 * @param rowData
 * @param rowIndex
 * @returns {*}
 */
function anaesthesiaNameFormatter(value,rowData,rowIndex){
    if(value == 0){
        return;
    }
    for(var i = 0; i<anaesthesiaName.length; i++){
         if(anaesthesiaName[i].value == value){
             return anaesthesiaName[i].label;
         }
    }
}
