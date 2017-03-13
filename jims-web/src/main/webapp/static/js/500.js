 $(function(){
   /*
    function getUrlParameter(name){
    name = name.replace(/[\[]/,"\\\[").replace(/[\]]/,"\\\]");
    var regexS = "[\\?&]"+name+"=([^&#]*)";
    var regex = new RegExp( regexS );
    var results = regex.exec(window.parent.location.href );
    if( results == null )    return "";  else {
    return results[1];
    }
    }
    var  exceptionVO={};
    exceptionVO=getUrlParameter("e");
    console.info(decodeURI(exceptionVO));
    $("#exceptionMessage").html(decodeURI(exceptionVO));*/

     function getUrlParameter(name){
         name = name.replace(/[\[]/,"\\\[").replace(/[\]]/,"\\\]");
         var regexS = "[\\?&]"+name+"=([^&#]*)";
         var regex = new RegExp( regexS );
         var results = regex.exec(window.parent.location.href );
         if( results == null )    return "";  else {
             return results[1];
         }
     }
     var key=getUrlParameter("key");
     $.get('/service/exceptions/getCookie?key=' + key).always(function(data){
         console.info(data);
         $("#exceptionMessage").html(data.responseText);
     });

 })














