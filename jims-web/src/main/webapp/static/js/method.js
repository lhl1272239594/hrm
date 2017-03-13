
/**
 * 打印页面
 * @param url
 */
function printWindow(url){
    windowOpen(url,"",$(window).width()*0.9,$(window).height()*0.9);
}
// 打开一个窗体
function windowOpen(url, name, width, height){
    var top=parseInt((window.screen.height-height)/2,10),left=parseInt((window.screen.width-width)/2,10),
        options="location=no,menubar=no,toolbar=no,dependent=yes,minimizable=no,modal=yes,alwaysRaised=yes,"+
            "resizable=yes,scrollbars=yes,"+"width="+width+",height="+height+",top="+top+",left="+left;
    window.open(url ,name , options);
}

/**
 * 转化pdf
 * @param url   页面显示数据路径
 * @param printDocType  生成PDF的名称
 */
function printPdf(url,data) {
    var getTimestamp=new Date().getTime();
    if(url.indexOf("?")>-1){
        url=url+"&t="+getTimestamp
    }else{
        url=url+"?t="+getTimestamp
    }
    url=basePath+url;
    if (sub) {
        sub = false;
        if (typeof(data) == Object) {
            data = JSON.stringify(data);
        }
        $.ajax({
            'type': 'get',
            'url': url,
            'contentType': 'application/json',
            'data': data,
            'dataType': 'json',
            async: false,
            'success': function (d) {
                printWindow(d.data);
            },
            'error': function () {
                $.messager.alert("提示信息", "生成报表失败,请联系管理员", "error");
            },
            'complete': function (xhr, status) {
                sub = true;
            }
        });
    }
}
