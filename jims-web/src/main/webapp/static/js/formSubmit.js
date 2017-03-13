//保存
function formSubmitInput(fromId){
    $("#"+fromId+" div").each(function(){
        var inputId=$(this).attr("submit_id");
        if(typeof(inputId) != "undefined"){
            var html=$(this).html();
            $("#"+inputId).val(escape(html));
        }
    })
}
//查看
function getDiv(fromId){
    $("#"+fromId+" div").each(function(){
        var inputId=$(this).attr("submit_id");
        if(typeof(inputId) != "undefined"){
            $(this).html(unescape($("#"+inputId).val()));
        }
    })
}

/**
 * 获取form表单转json
 * @param formId
 * @returns {*}
 */
function fromJson(formId){
    var json = '{';
    $($('#'+formId).serializeArray()).each(function (index, element) {
        if(element.value!=null && element.value!=''){
            json=json+'"'+element.name+'"'+':'+'"'+element.value+'",'
        }
    })
    json = json.substring(0, json.length - 1);
    json=json+'}';
    return json;
}
