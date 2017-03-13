var menuObj=[];
var menuIdObj=[];
var deptObj=[];
var personList=[];
var deptList=[];
var userId='';
var title='';
var zbuts="";
var orgids="";
var noticeObj=[];
var orgids="";
var check=false;
var res;
$.ajaxSetup({

    async : false
});
var myTime = setTimeout("Timeout()", 1000*1800);
window.addTab = function (title, href,mid) {
    //如果路径为空，则直接返回
    if (!href) {
        return;
    }
    config.mienuid=mid;//添加菜单的ID
    if(title=='合同到期查看'){
        check=true;
    }
    getBut();
    var tabs = $("#mainContent").tabs('tabs');
    if (tabs.length > 15) {
        $.messager.alert("系统提示", "打开的Tab页面太多，请关闭不需要的页面！", 'info');
        return;
    }
    if ($("#mainContent").tabs('exists', title)) {
        $("#mainContent").tabs('select', title);
    } else {
        var content = undefined;
        content = '<iframe scrolling="auto" frameborder="0"  src="' + href + '" style="width:100%;height:99%;"></iframe>'
        $("#mainContent").tabs('add', {
            title: title,
            content: content,
            closable: true
        });
        //获取最后一个tabs 在新加的选项卡后面添加"关闭全部"
        var li = $(".tabs-wrap ul li:last-child");
        $("#close").remove();
        li.after("<li id='close'><a class='tabs-inner' href='javascript:void()' onClick='javascript:closeAll()'>关闭全部</a></li>");
    }

}
/**
 * 生成菜单
 * @param menus
 */
var makeTree = function (menus) {
    //测试
    $("#west").empty();
    var menuDatas = [];
    for (var i = 0; i < menus.length; i++) {
        menus[i].children = [];
    }

    for (var i = 0; i < menus.length; i++) {
        var n=0;
        for (var j = 0; j < menus.length; j++) {
            if (menus[j].pid == menus[i].menuId) {
                menus[i].children.push(menus[j])
                if(n==0) {
                    if (!menus[i].pid == "" || !menus[i].pid == undefined || !menus[i].pid == null) {
                        menuDatas.push(menus[i]);
                        n++;
                    }
                }
            }
        }
        //if (menus[i].pid == "" || menus[i].pid == undefined || menus[i].pid == null) {
        //menuDatas.push(menus[i]);
        //}
    }
    var data = {
        title: '嵌入子模板',
        list: menuDatas
    };

    //console.log(menuDatas)

    var html = template("his-index", data);
    $("#west").append(html);
    $("#content").layout({
        fit: true
    });

    $("#aa").accordion({
        fit: true
    });
    $(".easyui-tree").tree();

}

var addMenu = function (serviceId, staffId) {
    config.serviceId = serviceId;
    config.staffId = staffId;

    var promise = $.get(basePath + "/service-param/find-by-self-service-id?selfServiceId=" + config.serviceId + "&orgId=" + config.org_Id, function (data) {
        //console.log(data);
        var objs = [];

        for (var i = 0; i < data.length; i++) {
            var obj = {};
            obj.paramDesp = data[i].paramDesp;
            obj.paramName = data[i].paramName;
            var temp1 = data[i].valueRange.split(";");
            obj.options = [];
            for (j = 0; j < temp1.length; j++) {
                var temp2 = temp1[j].split("|");
                var obj1 = {};
                obj1.label = temp2[0];
                obj1.value = temp2[1];
                obj.options.push(obj1);
            }
            objs.push(obj);
        }
        var data = {};
        data.list = objs;
        var html = template('param', data);
        $("#paramDialog").empty();
        $("#paramDialog").append(html);
        $(".easyui-combobox").combobox();

    })
    promise.done(function (obj) {
        var op = {width: 400, height: 70 * obj.length}
        if (obj.length <= 0) {
            $.get(basePath + "/tool/getMenu?serviceId=" + config.serviceId + "&staffId=" + config.staffId + "&roleId=" + config.roleId, function (data) {
                $.each(data, function (index, item) {
                    menuObj[item.menuName]=item.href;
                    menuIdObj[item.menuName]=item.menuId;
                });
                makeTree(data);
                loadSchedule();
                loadShortCut();
                loadMydata();
                loadNotice();
                $("#userName").empty();
                var menuName='我的信息';
                var html='<a onClick="addTab(\''+menuName+'\',\''+menuObj[menuName]+'\')" style="font-size: 14px;line-height: 22px;"  href="javascript:void(0);">'+config.userName+'</a>';
                $("#userName").append(html);
            })
        } else {
            $("#paramDialog").dialog("resize", op);
            $("#paramDialog").dialog('open');
            $("#paramDialog").dialog('center');
        }

    })
}

var config = {};
var personInfo = {};

$(function () {

    var sysService;    //名称为系统管理的服务
    var loginInfo = undefined ;
    var sysUser = undefined ;


    var promise = $.getJSON(basePath+"/skip/get-login-info?date="+new Date(),function(data){
        loginInfo = data ;
        config = loginInfo ;
        config.org_Id=loginInfo.orgId ;
        config.persion_Id = loginInfo.persionId;
        config.staffId =loginInfo.staffId;
        config.userName =loginInfo.userName;
        config.roleId=loginInfo.roleId;
        config.deptId=loginInfo.deptId;

    }) ;



    var init=function(){
        if(config.currentStorage){
            $.get(basePath + '/drug-storage-dept/list',{orgId:config.org_Id,storageCode:config.currentStorage},function(res){
                //console.log(res)
                if(res && res.length > 0){
                    config.currentStorageObj = res[0];
                    $.get(basePath + '/dict/findListByType',{type:'DRUG_STOCK_TYPE_DICT',value:res[0].storageType},function(r){
                        if(r && r.length > 0){
                            config.currentStorageObj.level = r[0].remarks;
                        }
                    })
                }
            })
        }
    }

    promise.done(function(){
        var orgId = config.org_Id;
        var personId = config.persion_Id;
        var staffId = config.staffId;   //员工Id

        //根据机构ID和人员ID查询该员工在该机构的所有服务
        $.get(basePath + '/org-service/find-selfServiceList-by-orgId-personId?personId=' + personId + '&orgId=' + orgId, function (data) {
            for (var i = 0; i < data.length; i++) {
                if(i=='0'){
                    addMenu(data[i].id,staffId);
                }
                /*if(data[i].serviceName=='人力资源服务'){
                    addMenu(data[i].id,staffId);
                }*/
            }
        });
        $.get("/service/tool/find-user-tree?orgId=" + orgId, function (data) {
            $.each(data, function (index, item) {
                 deptObj[item.treeId]=item.treeDes;
            });
        });

        $.get("/service/tool/find-person-list?orgId="+orgId+"&userId="+userId, function (data) {
            $.each(data, function (index, item) {
                personList[item.userId]=item.userName;
                deptList[item.deptId]=item.deptName;
            });
        });
        //退出
        $("#exit").on("click", function () {
            $.getJSON("/service/skip/exit",function(data){
                quit();
            }) ;
        });;
        $("#paramDialog").dialog({
            width: 400,
            height: 500,
            modal: 'true',
            title: '参数选择',
            closed: true,
            closable: false,
            buttons: [{
                text: '确定',
                handler: function () {
                    $("#paramDialog .easyui-combobox").each(function (index, item) {
                        var value = $("#" + item.id).combobox('getValue');
                        config[item.id] = value;
                        //console.log("config");
                        //console.log(config);
                        if(item.id == 'currentStorage') {
                            init();
                        }
                    })
                    $.get(basePath + "/orgStaff/find-list-by-serviceId?serviceId=" + config.serviceId + "&staffId=" + config.staffId, function (data) {
                        makeTree(data)
                    })
                    $("#paramDialog").dialog("close");
                }
            }, {
                text: '取消',
                handler: function () {
                    $("#paramDialog").dialog("close")
                }
            }]
        })
    })
    $(".groupItem").mouseover(function () {
        $(this).addClass("shadow");
    });
    $(".groupItem").mouseleave(function () {
        $(this).removeClass("shadow");
    });
    $(".itemHeader").mouseover(function () {
        $(this).find(".action").css('display','block');
    });
    $(".itemHeader").mouseleave(function () {
        $(this).find(".action").css('display','none');
    });

    $(".refresh").click(function () {
        var a=$(this).closest("div .groupWrapper").attr("id");
        if(a=='schedule'){
            loadSchedule();
        }
        if(a=='iconLink'){
            loadShortCut();
        }
        if(a=='myData'){
            loadMydata();
        }
        if(a=='notice'){
            loadNotice();
        }
    });
    $(".min").click(function () {
        $(this).css('display','none').closest(".itemHeader").next(".itemContent").hide().end().end()
            .next(".max").css('display','block');

    });
    $(".max").click(function () {
        $(this).css('display','none').closest(".itemHeader").next(".itemContent").show().end().end()
            .prev(".min").css('display','block');

    });
    $(".close").click(function () {
        $(this).closest("div .groupWrapper").hide();
    });

});
//工作台
//查询代办
var loadSchedule = function () {
    $("#schedule").find(".swiper-container").empty();
    var schedule = {};
    schedule.roleId = config.roleId;
    schedule.contract = menuIdObj['合同管理'];
    schedule.examGrade = menuIdObj['在线评分'];
    schedule.orgId = config.orgId;
    schedule.serviceId = config.serviceId;
    $.postJSON(basePath + '/desk/getSchedule', JSON.stringify(schedule), function (data) {
        if(data){
            var sort=0;
            var html='<table cellspacing="0" style="margin-top: 5px">' +
                '<tr><th width="60px;">序号</th><th>标题</th><th>状态</th><th>数量</th><th width="140px;">操作</th></tr> ';
            if(data.trip&&parseInt(data.trip)>0){
                sort++;
                var menuName='考勤审批管理';
                html+=editHtml(menuName,'公出审批','待审批',data.trip,sort,menuObj[menuName]);
            }
            if(data.leave&&parseInt(data.leave)>0){
                sort++;
                var menuName='考勤审批管理';
                html+=editHtml(menuName,'请假审批','待审批',data.leave,sort,menuObj[menuName]);
            }

            if(data.overtime&&parseInt(data.overtime)>0){
                sort++;
                var menuName='考勤审批管理';
                html+=editHtml(menuName,'加班审批','待审批',data.overtime,sort,menuObj[menuName]);
            }
            if(data.contract&&parseInt(data.contract)>0){
                sort++;
                var menuName='合同到期查看';
                html+=editHtml(menuName,'合同即将到期','待查看',data.contract,sort,"/modules/fbd/hrm/contract/contract.jsp",menuIdObj['合同管理']);
            }
            if(data.train&&parseInt(data.train)>0){
                sort++;
                var menuName='我的培训';
                html+=editHtml(menuName,'培训通知','待参加',data.train,sort,menuObj[menuName]);
            }
            if(data.exam&&parseInt(data.exam)>0){
                sort++;
                var menuName='在线考试';
                html+=editHtml(menuName,'在线考试','待考试',data.exam,sort,menuObj[menuName]);
            }
            if(data.examGrade&&parseInt(data.examGrade)>0){
                sort++;
                var menuName='在线评分';
                html+=editHtml(menuName,'在线评分','待评卷',data.examGrade,sort,menuObj[menuName],menuIdObj['在线评分']);
            }
            if(parseInt(data.evaluation)>0){
                sort++;
                var menuName='在线考评';
                html+=editHtml(menuName,'在线考评','待考评',data.evaluation,sort,menuObj[menuName]);
            }
            if(parseInt(data.evaluationGrade)>0){
                sort++;
                var menuName='考评发布';
                html+=editHtml(menuName,'考评发布','待发布',data.evaluationGrade,sort,menuObj['考评管理']);
            }
            if(sort>=1){
                html+="</table>";
                $("#schedule").find(".swiper-container").append(html).css('overflow','auto');
                lxD();
            }
            else{
                var html=noData();
                $("#schedule").find(".swiper-container").append(html).css('overflow','hidden');
            }
        }
        else{
            var html=noData();
            $("#schedule").find(".swiper-container").append(html).css('overflow','hidden');
        }
    });
}
//查询公告
var loadNotice = function () {
    $("#notice").find(".swiper-container").empty();
    var isOver=false;
    $.get(basePath + '/desk/getNotice', function (data) {
        if(data){
            noticeObj=data;
            var html='<ul style="padding:10px;margin:0px;">'+
                '<div class="widgetContent" >'+
                '<table class="viewTable"'+
            'style="display: table; width: 100%;">'+
                '<tbody>'+
                '<tr class="header widgetItem">'+
                '<td style="font-weight: bold;">标题'+
                '</td>'+
                '<td style="font-weight: bold;">日期'+
                '</td>'+
                '</tr>';
            $.each(data, function (index, item) {
                if(index>=6){
                    isOver=true;
                    return false;
                }
               html+='<tr id="'+item.noticeId+'" class="widgetItem getNotice">'+
                   '<td>'+
                   '<div class="notice_title">'+item.name+'</div>'+
                   '</td>'+
                   '<td>'+
                   '<div name="result">'+item.publishDate+'</div>'+
                   '</td>'+
                   '</tr>'
            });
            if(data.length>0){
                html+="</tbody></table></div></ul>";
                if(isOver){
                    html+=more();
                }
                $("#notice").find(".swiper-container").append(html).css('overflow','auto');
                lxD();
            }
            else{
                var html=noData();
                $("#notice").find(".swiper-container").append(html).css('overflow','hidden');
            }
        }
        else{
            var html=noData();
            $("#notice").find(".swiper-container").append(html).css('overflow','hidden');
        }
    });
}
//快捷方式
var loadShortCut = function () {
    $("#iconLink").find(".swiper-container").empty();
    $.get(basePath + '/desk/getShortCut', function (data) {

        var html='<div class="tooli">';
        $.each(data, function (index, item) {
            html+='<a class="icon_con" onClick="addTab(\''+item.menuName+'\',\''+menuObj[item.menuName]+'\')"  href="javascript:void(0);" title="'+item.menuName+'" >'+
                    '<div class="icon_s"><img src='+item.iconUrl+'></div>'+
                    '<div class="text">'+item.menuName+'</div>'+
                 '</a>';
        });

        if(html!='<div class="tooli">'){
            html+='</div>';
            $("#iconLink").find(".swiper-container").append(html).css('overflow','auto');
        }
        else{
            var html=noData();
            $("#iconLink").find(".swiper-container").append(html).css('overflow','hidden');
        }
    });
}
//信息统计
var loadMydata = function () {
    $("#myData").find(".swiper-container").empty();
    $.get(basePath + '/desk/getMyData', function (data) {
        if(data) {
            var html = '<ul class="data-ul">';
            if (parseInt(data.leave_time) > 0) {
                var menuName='请假管理';
                html += '<li> ' +
                    '<a  /*onClick="addTab(\'' + menuName + '\',\'' + menuObj[menuName] + '\')"*/  href="javascript:void(0);">本月请假时长:' + data.leave_time + '小时</a>' +
                    '</li>';
            }
            if (parseInt(data.leave_nums) > 0) {
                var menuName='请假管理';
                html += '<li> ' +
                    '<a  /*onClick="addTab(\'' + menuName + '\',\'' + menuObj[menuName] + '\')"*/  href="javascript:void(0);">本月请假:' + data.leave_nums + '次</a>' +
                    '</li>';
            }
            if (parseInt(data.overtime_time) > 0) {
                var menuName='加班管理';
                html += '<li> ' +
                    '<a  /*onClick="addTab(\'' + menuName + '\',\'' + menuObj[menuName] + '\')"*/  href="javascript:void(0);">本月加班时长:' + data.overtime_time + '小时</a>' +
                    '</li>';
            }
            if (parseInt(data.overtime_nums) > 0) {
                var menuName='加班管理';
                html += '<li> ' +
                    '<a  /*onClick="addTab(\'' + menuName + '\',\'' + menuObj[menuName] + '\')"*/  href="javascript:void(0);">本月加班:' + data.overtime_nums + '次</a>' +
                    '</li>';
            }
            if (parseInt(data.trip_time) > 0) {
                var menuName='公出管理';
                html += '<li> ' +
                    '<a  /*onClick="addTab(\'' + menuName + '\',\'' + menuObj[menuName] + '\')"*/  href="javascript:void(0);">本月公出时长:' + data.trip_time + '小时</a>' +
                    '</li>';
            }
            if (parseInt(data.trip_nums) > 0) {
                var menuName='公出管理';
                html += '<li> ' +
                    '<a  /*onClick="addTab(\'' + menuName + '\',\'' + menuObj[menuName] + '\')"*/  href="javascript:void(0);">本月公出:' + data.trip_nums + '次</a>' +
                    '</li>';
            }
            if (html != '<ul class="data-ul">') {
                html += '</ul>';
                $("#myData").find(".swiper-container").append(html).css('overflow', 'auto');
            }
            else {
                var html = noData();
                $("#myData").find(".swiper-container").append(html).css('overflow', 'hidden');
            }
        }
        else {
            $("#myData").hide();
            /*var html = noData();
            $("#myData").find(".swiper-container").append(html).css('overflow', 'hidden');*/
        }
    });
}

function closeAll() {
    $(".tabs li").each(function(index, obj) {
        //获取所有可关闭的选项卡
        var tab = $(".tabs-closable", this).text();
        $(".easyui-tabs").tabs('close', tab);
    });
    $("#close").remove();//同时把此按钮关闭
}
//生成代办HTML
function editHtml(menuName,type,state,num,sort,url,menuId) {

    var $html='<tr>'+
                '<td>'+sort+'</td>'+
                '<td>'+type+'</td>'+
                '<td>'+state+'</td>'+
                '<td>'+num+'</td>'+
                '<td>'+
                    '<div class="schedule-image" >'+
                        '<a onClick="addTab(\''+menuName+'\',\''+url+'\',\''+menuId+'\')"  href="javascript:void(0);">'+
                    '<img src="../../../../static/images/index/banli.jpg" width="72px" height="25px" style="border: 0;"/>'+
                    '</a>'+
                '</div>'+
                '</td>'+
                '</tr>';
    return $html;

}
//交互初始化
function lxD() {
    $(".swiper-container tr").mouseover(function () {
        $(this).addClass("tr-mouseon_color");
    });
    $(".swiper-container tr").mouseleave(function () {
        $(this).removeClass("tr-mouseon_color");
    });
    $("#queryNotice").window({
            title:'公告',
            fit:true,
            modal: true,
            closed:true
        }
    );
    $("#msg").window({
        title:'提示',
        closed: false,
        modal: true,
        closable:false,
        collapsible: false,
        minimizable: false,
        maximizable: false,
        resizable: false
        }
    );
    $("#msg").window("close");
    $("#quit").click(function () {
        quit();
    });
    $(".getNotice").click(function () {
        var id=$(this).attr("id");
        var obj=getName(noticeObj,'noticeId',id);
        var name=obj[0].name;
        $.get(basePath + '/desk/getNoticeById?id='+id, function (data) {
            if(data){
                var t = formatDatebox(data.publishDate);
                var val;
                if(data.content!=null){
                    //解码
                    val = utf8to16(base64decode(data.content));
                    $("#bodys").html(val);
                }
                var html='<div  id="detail" style="overflow: scroll;display: block;height: 100%;margin: 0 40px;">' +
                    '<div width="100%" border="0">' +
                    '<div style="width: 100%;height: auto;font-size:30px;text-align: center;font-weight:bold;margin: 30px 0 20px 0">' +
                    ''+data.name+'' +
                    '</div>' +
                    '<div style="width: 100%;height: auto;color:#888888;text-align: center;margin-bottom: 20px;">' +
                    '发布时间：'+t+'' +
                    '</div>' +
                    '<div style="margin: 0 20px;">'+
                    ''+val+'' +
                    '</div>' +
                    '</div></div>';
                var tabs = $("#mainContent").tabs('tabs');
                if (tabs.length > 15) {
                    $.messager.alert("系统提示", "打开的Tab页面太多，请关闭不需要的页面！", 'info');
                    return;
                }
                if ($("#mainContent").tabs('exists', name)) {
                    $("#mainContent").tabs('select', name);
                } else {
                    $("#mainContent").tabs('add', {
                        title: name,
                        content: html,
                        closable: true
                    });
                    //获取最后一个tabs 在新加的选项卡后面添加"关闭全部"
                    var li = $(".tabs-wrap ul li:last-child");
                    $("#close").remove();
                    li.after("<li id='close'><a class='tabs-inner' href='javascript:void()' onClick='javascript:closeAll()'>关闭全部</a></li>");
                }
            }
        });
    });
}
//无数据时显示
function more() {
    var menuName='我的公告';
    var html='<a class="bottom-menu moreBtn" onClick="addTab(\'' + menuName + '\',\'' + menuObj[menuName] + '\')"  href="javascript:void(0);">更多>></a>';
    return html;
}
//无数据时显示
function noData() {
    var html='<p class="zhanwei">当前没有数据<span>。。。</span><img src="../../../../static/images/index/null.png" class="animated" style="left: 0px;"></p>';
    return html;
}
function getName(arrPerson,objPropery,objValue)
{
    return $.grep(arrPerson, function(cur,i){
        if(cur[objPropery]==objValue){
            return cur;
        }
    });
}

//屏蔽按钮  config.mienuid
function getBut() {
    var menuPromise = $.get(basePath + "/buttonDict/find-menu-btn",{serviceId:config.serviceId,roleId:config.roleId,isTree:true,orgid:config.orgId,meid:config.mienuid}, function (data) {
        //$("#tt").treegrid('loadData', data);
        zbuts="";
        zbuts= data;
    });
    $.get(basePath + "/buttonDict/getlist?orgId=" + config.orgId+"&roleid="+config.roleId+"&serid="+config.serviceId+"&mid="+config.mienuid, function (data) {
        orgids="";
        $.each(data, function (index, item) {
            if(item.remarks=='1')
            {
                orgids+= "'"+item.id+"',";
            }
        });
        orgids=orgids.substr(0,orgids.length-1);
    });

}
function quit() {
            window.location.href = "/modules/sys/login.html";
    /*if (navigator.userAgent.indexOf("Firefox") != -1 || navigator.userAgent.indexOf("Chrome") !=-1) {

        window.location.href="about:blank";
        window.close();
    } else {

        window.opener = null;

        window.open("", "_self");

        window.close();

    }*/
}
//格式化时间：时分秒
function formatDatebox(value) {
    if (value == null || value == '') {
        return '';
    }
    var dt;
    if (value instanceof Date) {
        dt = value;
    } else {

        dt = new Date(value);

    }

    return dt.format("yyyy-MM-dd hh:mm:ss"); //扩展的Date的format方法(上述插件实现)
}
//base64编码解码
var base64EncodeChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
var base64DecodeChars = new Array(
    -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
    -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1,
    -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, 62, -1, -1, -1, 63,
    52, 53, 54, 55, 56, 57, 58, 59, 60, 61, -1, -1, -1, -1, -1, -1,
    -1,　0,　1,　2,　3,  4,　5,　6,　7,　8,　9, 10, 11, 12, 13, 14,
    15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, -1, -1, -1, -1, -1,
    -1, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40,
    41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, -1, -1, -1, -1, -1);
function base64encode(str) {
    var out, i, len;
    var c1, c2, c3;
    len = str.length;
    i = 0;
    out = "";
    while(i < len) {
        c1 = str.charCodeAt(i++) & 0xff;
        if(i == len)
        {
            out += base64EncodeChars.charAt(c1 >> 2);
            out += base64EncodeChars.charAt((c1 & 0x3) << 4);
            out += "==";
            break;
        }
        c2 = str.charCodeAt(i++);
        if(i == len)
        {
            out += base64EncodeChars.charAt(c1 >> 2);
            out += base64EncodeChars.charAt(((c1 & 0x3)<< 4) | ((c2 & 0xF0) >> 4));
            out += base64EncodeChars.charAt((c2 & 0xF) << 2);
            out += "=";
            break;
        }
        c3 = str.charCodeAt(i++);
        out += base64EncodeChars.charAt(c1 >> 2);
        out += base64EncodeChars.charAt(((c1 & 0x3)<< 4) | ((c2 & 0xF0) >> 4));
        out += base64EncodeChars.charAt(((c2 & 0xF) << 2) | ((c3 & 0xC0) >>6));
        out += base64EncodeChars.charAt(c3 & 0x3F);
    }
    return out;
}
function base64decode(str) {
    var c1, c2, c3, c4;
    var i, len, out;
    len = str.length;
    i = 0;
    out = "";
    while(i < len) {
        /* c1 */
        do {
            c1 = base64DecodeChars[str.charCodeAt(i++) & 0xff];
        } while(i < len && c1 == -1);
        if(c1 == -1)
            break;
        /* c2 */
        do {
            c2 = base64DecodeChars[str.charCodeAt(i++) & 0xff];
        } while(i < len && c2 == -1);
        if(c2 == -1)
            break;
        out += String.fromCharCode((c1 << 2) | ((c2 & 0x30) >> 4));
        /* c3 */
        do {
            c3 = str.charCodeAt(i++) & 0xff;
            if(c3 == 61)
                return out;
            c3 = base64DecodeChars[c3];
        } while(i < len && c3 == -1);
        if(c3 == -1)
            break;
        out += String.fromCharCode(((c2 & 0XF) << 4) | ((c3 & 0x3C) >> 2));
        /* c4 */
        do {
            c4 = str.charCodeAt(i++) & 0xff;
            if(c4 == 61)
                return out;
            c4 = base64DecodeChars[c4];
        } while(i < len && c4 == -1);
        if(c4 == -1)
            break;
        out += String.fromCharCode(((c3 & 0x03) << 6) | c4);
    }
    return out;
}
function utf16to8(str) {
    var out, i, len, c;
    out = "";
    len = str.length;
    for(i = 0; i < len; i++) {
        c = str.charCodeAt(i);
        if ((c >= 0x0001) && (c <= 0x007F)) {
            out += str.charAt(i);
        } else if (c > 0x07FF) {
            out += String.fromCharCode(0xE0 | ((c >> 12) & 0x0F));
            out += String.fromCharCode(0x80 | ((c >>　6) & 0x3F));
            out += String.fromCharCode(0x80 | ((c >>　0) & 0x3F));
        } else {
            out += String.fromCharCode(0xC0 | ((c >>　6) & 0x1F));
            out += String.fromCharCode(0x80 | ((c >>　0) & 0x3F));
        }
    }
    return out;
}
function utf8to16(str) {
    var out, i, len, c;
    var char2, char3;
    out = "";
    len = str.length;
    i = 0;
    while(i < len) {
        c = str.charCodeAt(i++);
        switch(c >> 4)
        {
            case 0: case 1: case 2: case 3: case 4: case 5: case 6: case 7:
            // 0xxxxxxx
            out += str.charAt(i-1);
            break;
            case 12: case 13:
            // 110x xxxx　 10xx xxxx
            char2 = str.charCodeAt(i++);
            out += String.fromCharCode(((c & 0x1F) << 6) | (char2 & 0x3F));
            break;
            case 14:
                // 1110 xxxx　10xx xxxx　10xx xxxx
                char2 = str.charCodeAt(i++);
                char3 = str.charCodeAt(i++);
                out += String.fromCharCode(((c & 0x0F) << 12) |
                    ((char2 & 0x3F) << 6) |
                    ((char3 & 0x3F) << 0));
                break;
        }
    }
    return out;
}
$(document).ajaxComplete(function(event, response, settings) {
    var sessionTimeout = response.getResponseHeader("SessionTimeout");
    var sessionIsAlive = response.getResponseHeader("sessionIsAlive");
    if(sessionTimeout != null && typeof sessionTimeout != "undefined" && sessionTimeout.length > 0){
        // 这里写Session超时后的处理方法
        $.getJSON("/service/skip/exit",function(data){
            $("#msg").window("open");
        }) ;
    }
    if(sessionIsAlive != null && typeof sessionIsAlive != "undefined" && sessionIsAlive.length > 0){
        resetTime()
    }
});

function resetTime() {
    clearTimeout(myTime);
    myTime = setTimeout('Timeout()', 1000*1800);
}
function Timeout() {
    $.getJSON("/service/skip/exit",function(data){
        $("#msg").window("open");
    }) ;
}