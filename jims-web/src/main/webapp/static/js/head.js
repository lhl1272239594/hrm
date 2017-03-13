$("head").append('<meta HTTP-EQUIV="pragma" CONTENT="no-cache">');
$("head").append('<meta HTTP-EQUIV="Cache-Control" CONTENT="no-cache, must-revalidate">');
$("head").append('<meta HTTP-EQUIV="expires" CONTENT="0">');
$("head").append('<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />');
$("head").append('<meta name="renderer" content="webkit">');
$("head").append('<meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1" />');
$("<link>").attr({rel: "stylesheet", type: "text/css", href: "/static/easyui/css/icon.css"}).appendTo("head");
$("<link>").attr({rel: "stylesheet", type: "text/css", href: "/static/css/index.css"}).appendTo("head");
$("<link>").attr({rel: "stylesheet", type: "text/css", href: "/static/css/minoral.css"}).appendTo("head");
$("<link>").attr({rel: "stylesheet", type: "text/css", href: "/static/circularNav/css/component2.css"}).appendTo("head");
$("<script>").attr({type: "application/javascript", src: "/static/easyui/js/jquery.easyui.min.js"}).appendTo("head");
$("<script>").attr({type: "application/javascript", src: "/static/easyui/locale/easyui-lang-zh_CN.js"}).appendTo("head");
$("<script>").attr({type: "application/javascript", src: "/static/js/tool.js"}).appendTo("head");
$("<script>").attr({type: "application/javascript", src: "/static/js/formSubmit.js"}).appendTo("head");



basePath=getRootPath()+"/service";
function getRootPath(){
    //获取当前网址，如： http://localhost:8083/uimcardprj/share/meun.jsp
    var curWwwPath=window.document.location.href;
    //获取主机地址之后的目录，如： uimcardprj/share/meun.jsp
    var pathName=window.document.location.pathname;
    var pos=curWwwPath.indexOf(pathName);
    //获取主机地址，如： http://localhost:8083
    var localhostPaht=curWwwPath.substring(0,pos);
    return(localhostPaht);
}
var inputParamVos=new Array();
var InputParamVo1={};
InputParamVo1.colName='rownum';
InputParamVo1.colValue='20';
InputParamVo1.operateMethod='<';
inputParamVos.push(InputParamVo1);


var data = parent.zbuts;
if(typeof(data)!="undefined"){
    $.each(data, function (index, item) {
        var oid = '#'+item.id;
        if(oid)
        {
            if(item.menuOperate == 'false')
            {
                $(oid).hide();
            }
        }
    });
}

var orgids = parent.orgids;//所有的部门ID  sql in ( orgids )111 ;




