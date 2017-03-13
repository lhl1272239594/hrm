function getLabel(type,value){
    $.ajax({
        'type': 'get',
        'url': basePath+"/dict/getLabel",
        'contentType': 'application/json',
        'data': "type="+type+"&value="+value,
        'dataType': 'json',
        'success': function(data) {
            return data;
        }
    });
}