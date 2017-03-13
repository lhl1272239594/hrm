var bloodInuses = [{"value": "1", "text": "血库"}, {"value": "2", "text": "自体"}, {"value": "3", "text": "互助"}];

/**
 * 血源翻译
 * @param value
 * @param rowData
 * @param rowIndex
 * @returns {string|string|string}
 */
function bloodInusesFormatter(value,rowData,rowIndex){
    if(value == 0){
        return;
    }
    for(var i=0;i<bloodInuses.length;i++){
        if(bloodInuses[i].value == value){
            return bloodInuses[i].text;
        }
    }
}