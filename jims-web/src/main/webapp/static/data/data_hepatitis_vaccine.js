var hepatitisData =  [];
 /**
 * 乙肝疫苗接种卡
 */
$.ajax({
    'type': 'GET',
    'url':basePath+'/dict/findListByType',
    data: 'type=HEPATITTIS_VACCINATION',
    'contentType': 'application/json',
    'dataType': 'json',
    'async': false,
    'success': function(data){
        hepatitisData=data;
    }
});
/**
 * 乙肝疫苗接种卡翻译
 * @param value
 * @param rowData
 * @param rowIndex
 * @returns {string|string|string}
 */
function setDataFormatter(value, rowData, rowIndex) {
    if (value == 0) {
        return;
    }

    for (var i = 0; i < hepatitisData.length; i++) {
        if (hepatitisData[i].value == value) {
            return hepatitisData[i].label;
        }
    }
}