var provideWay =[]//输血方式


$.ajax({
    'type': 'GET',
    'url':basePath+'/dict/findListByType',
    data: 'type=TRANSFUSION_DICT',
    'contentType': 'application/json',
    'dataType': 'json',
    'async': false,
    'success': function(data){
        provideWay=data;
    }
});


function provideFormatter(value,rowData,rowIndex){
    if(value == 0){
        return;
    }
    for(var i = 0;i<provideWay.length;i++){
        if(provideWay[i].value == value){
            return provideWay[i].label;
        }
    }
}