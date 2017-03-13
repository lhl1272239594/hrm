var clinicMaster={};
$(function(){
    //addTabs('0','安排管理','/modules/register/registerHome.html','');

        //添加Tabs
    //$(".tabs-header").bind('contextmenu',function(e){//给标头添加右击事件
    //    e.preventDefault();
    //    $('#rcmenu').menu('show', {
    //        left: e.pageX,
    //        top: e.pageY
    //    });
    //});
    // 刷新当前标签页
    $("#refresh").bind("click",function(){
        var currTab = $('#tabs-header').tabs('getSelected'); //获得当前tab
        var frameObj=$("iframe",currTab);
        $(frameObj).attr("src", $(frameObj).attr("src"))
        //var url = $(currTab.panel('options').content).attr('src');
        //currTab.panel('refresh', url);
    });

    //关闭当前标签页
    $("#closecur").bind("click",function(){
        var tab = $('#tabs-header').tabs('getSelected');
        var index = $('#tabs-header').tabs('getTabIndex',tab);
        $('#tabs-header').tabs('close',index);
    });
    //关闭所有标签页
    $("#closeall").bind("click",function(){
        //var tablist = $('#tabs-header').tabs('tabs');
        //for(var i=tablist.length-1;i>=0;i--){
        //    $('#tabs-header').tabs('close',i);
        //}
        closeTabs();
    });
    //关闭非当前标签页（先关闭右侧，再关闭左侧）
    $("#closeother").bind("click",function(){
        var tablist = $('#tabs-header').tabs('tabs');
        var tab = $('#tabs-header').tabs('getSelected');
        var index = $('#tabs-header').tabs('getTabIndex',tab);
        for(var i=tablist.length-1;i>index;i--){
            $('#tabs-header').tabs('close',i);
        }
        var num = index-1;
        for(var i=num;i>=0;i--){
            $('#tabs-header').tabs('close',0);
        }
    });
    //关闭当前标签页右侧标签页
    $("#closeright").bind("click",function(){
        var tablist = $('#tabs-header').tabs('tabs');
        var tab = $('#tabs-header').tabs('getSelected');
        var index = $('#tabs-header').tabs('getTabIndex',tab);
        for(var i=tablist.length-1;i>index;i--){
            $('#tabs-header').tabs('close',i);
        }
    });
    //关闭当前标签页左侧标签页
    $("#closeleft").bind("click",function(){
        var tab = $('#tabs-header').tabs('getSelected');
        var index = $('#tabs-header').tabs('getTabIndex',tab);
        var num = index-1;
        for(var i=0;i<=num;i++){
            $('#tabs-header').tabs('close',0);
        }
    });
});
function closeTabs(){
    var tablist = $('#tabs-header').tabs('tabs');
    for(var i=tablist.length-1;i>=0;i--){
        $('#tabs-header').tabs('close',i);
    }
}

function reOwnTabHead(id,thisId){
    //重置own-tab-head里面li的样式：开始
    $('.own-tab-head-text').each(function(i){
        $(this)[0].style.background = '#37bfc1';
    });
    $('.own-tab-head-left').each(function(i){
        $(this)[0].style.background = '#37bfc1';
    });
    $('.own-tab-head-right').each(function(i){
        $(this)[0].style.borderLeft = '10px solid #37bfc1';
    });
    //重置own-tab-head里面li的样式：结束
    //修改当前对应tab的标头背景样式：开始
    if(id!=1){$("#own-tab-head-left-"+id)[0].style.background = '#47CCD9';}
    $("#"+thisId)[0].style.background = '#47CCD9';
    $("#own-tab-head-right-"+id)[0].style.borderLeft = '10px solid #47CCD9';
}
/**
 * tabs 增加
 * @param id
 * @param name
 * @param url
 * @param lia
 */
function addTabs(id,name,url,lia){
    //重置own-tab-head里面li的样式,并修改当前对应tab的标头背景样式
    reOwnTabHead(id,"own-tab-head-text-"+id);

    if(lia!=''){
        closeTabs();//关闭所有标签页
        //$(lia).parent().parent().find("li a").removeClass();
        //$(lia).addClass("active");
    }
    var content = '<iframe  src="'+url+'" frameborder="0" border="0" marginheight="0" marginwidth="0" width="100%" height="99.5%"></iframe>';
    if(!$("#tabs-header").tabs('exists',name)){
        $('#tabs-header').tabs('add',{
            id:id,
            title: name,
            selected: true,
            content:content,
            //href:url,
            closable:false
        });
    }else $('#tabs-header').tabs('select',name);

}







