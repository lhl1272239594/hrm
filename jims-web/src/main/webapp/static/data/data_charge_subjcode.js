/**
 * Created by Administrator on 2016/7/13 0013.
 * 会计科目翻译
 */
var subjcodedata = [];

$.ajax({
    'type': 'GET',
    'url':basePath+'/dict/findListByType',
    data: 'type=TALLY_SUBJECT_DICT',
    'contentType': 'application/json',
    'dataType': 'json',
    'async': false,
    'success': function(data){
        subjcodedata=data;
    }
});

function subjcodeFormatter(value){
     if(value ==""){
        return "";
    }else if(value=='合计'|| value=="undefined"){
         return "合计";
     }
    for(var i = 0; i<subjcodedata.length; i++){
        if(subjcodedata[i].value == value){
            return subjcodedata[i].label;
        }
    }
}