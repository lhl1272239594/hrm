var patSource = [{"value": "1", "text": "市区"}, {"value": "2", "text": "郊县"}, {"value": "3", "text": "外省市"},
    {"value": "4", "text": "港澳台"}, {"value": "5", "text": "外国人"}];
/**
 * 属地翻译
 * @param value
 * @param rowData
 * @param rowIndex
 * @returns {string|string|string|string|string}
 */
function patSourceFormatter(value,rowData,rowIndex){
    if(value == 0){
        return;
    }
    for(var i =0;i<patSource.length;i++){
        if(patSource[i].value == value){
            return patSource[i].text;
        }
    }
}