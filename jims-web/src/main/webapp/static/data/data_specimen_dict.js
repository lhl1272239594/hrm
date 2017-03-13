var specimen=[];
/**
 * 检验标本
 */
$.ajax({
    'type': 'GET',
    'url':basePath+'/dict/findListByType',
    data: 'type=SPECIMAN_DICT',
    'contentType': 'application/json',
    'dataType': 'json',
    'async': false,
    'success': function(data){
        specimen=data;
    }
});

function specimenFormatter(value,rowData,rowIndex){
    if(value == 0){
        return;
    }
    for(var i = 0;i<specimen.length;i++){
        if(specimen[i].value == value){
            return specimen[i].label;
        }
    }
}