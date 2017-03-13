//function centerRefresh(id, name, url) {
//    $(window.parent.document).contents().find("#centerIframe")[0].contentWindow.addTabs(id, name, url);
//}
//var str = decodeURI(window.location.search);   //location.search是从当前URL的
//if (str.indexOf(name) != -1) {
//    var pos_start = str.indexOf(name) + name.length + 1;
//    var pos_end = str.indexOf("&", pos_start);
//    if (pos_end == -1) {
//        var id = str.substring(4, str.lastIndexOf("?"));
//        //人员id
//        var pid = str.substring(id.length + 16);
//    }
//}
var stepFlag = 0;//初始化向导执行步数，点击下一步按钮，增加1，点击上一步减少1
var stepAll = 0;//初始化向导总步数
var cshData = [];//初始化加载的菜单服务
var jzTab = 0;//初始为0；当加载过第一个向导页面之后变为1
var cshMeuns = [];//初始化向导内的所有菜单
var jzProcess = 0;//判断是否加载向导页

var cshdis0 = function(){//显示“跳过”和“下一步”按钮
    $("#dlg-buttons")[0].style.display = "";
    $("#goBack")[0].style.display = "none";
    $("#goSkip")[0].style.display = "";
    $("#goNext")[0].style.display = "";
    $("#goFinish")[0].style.display = "none";
}
var cshdis1 = function(){//显示除“完成”外的所有按钮
    $("#goBack")[0].style.display = "";
    $("#goSkip")[0].style.display = "";
    $("#goNext")[0].style.display = "";
    $("#goFinish")[0].style.display = "none";
}
var cshdis2 = function(){//显示“上一步”按钮
    $("#goBack")[0].style.display = "";
    $("#goSkip")[0].style.display = "none";
    $("#goNext")[0].style.display = "none";
    $("#goFinish")[0].style.display = "none";
}
var cshdis3 = function(){//只显示“上一步”和“完成”按钮
    $("#goBack")[0].style.display = "";
    $("#goSkip")[0].style.display = "none";
    $("#goNext")[0].style.display = "none";
    $("#goFinish")[0].style.display = "";
}
/**
 * @name:addTab:添加横向菜单栏页面:内容页
 * @param:title:横向菜单栏页面的title
 * @param:href:横向菜单栏页面的url
 */
window.addTab = function (title, href) {
    if(stepAll>0&&$("#dlg-buttons")[0].style.display == ""){
        var menuName = title;
        if(menuName=="组织机构人员管理"||menuName=="科室分组维护"||menuName=="科室组人员维护"){
            $.get(basePath + "/dept-dict/list?orgId=" + config.orgId , function (data) {
                if(data==null||data=="undefined"||data==undefined||data.length<=0){
                    alert("在操作【"+menuName+"】之前，\n请先在【组织机构科室管理】里面添加科室信息");
                    stepFlag = stepFlag - 1;
                    return false;
                }else if(menuName=="科室组人员维护"){
                    $.get(basePath + "/staff-group/findAllListByOrgId?orgId=" + config.orgId , function (data1) {
                        if(data1==null||data1=="undefined"||data1==undefined||data1.length<=0){
                            alert("在操作【"+menuName+"】之前，\n请先在【科室分组维护】里面添加科室分组信息");
                            stepFlag = stepFlag - 1;
                            return false;
                        }else{
                            $.get(basePath + "/input-setting/listParamByGET?orgId=" + config.orgId + '&dictType=v_staff_dict', function (data2) {
                                if(data2==null||data2=="undefined"||data2==undefined||data2.length<=0){
                                    alert("在操作【"+menuName+"】之前，\n请先在【组织机构人员管理】里面添加人员信息");
                                    stepFlag = stepFlag - 1;
                                    return false;
                                }else{ pd(title, href); }
                            });
                        }
                    });
                }else{ pd(title, href); }
            });
        }else if(menuName=="检查项目维护"){//当下一步的name是“检查项目维护”时
            $.get(basePath + "/examClassDict/listByOrgId?orgId=" + config.orgId , function (data) {
                if(data==null||data=="undefined"||data==undefined||data.length<=0){
                    alert("在操作【"+menuName+"】之前，\n请先在【检查类别维护】里面添加类别");
                    stepFlag = stepFlag - 1;
                    return false;
                }else{
                    $.get(basePath + "/examSubclassDict/listByOrgId?orgId=" + config.orgId , function (data1) {
                        if(data1==null||data1=="undefined"||data1==undefined||data1.length<=0){
                            alert("在操作【"+menuName+"】之前，\n请保证【检查类别维护】里面的类别至少有一个设置了子类别");
                            stepFlag = stepFlag - 1;
                            return false;
                        }else{
                            $.get(basePath + "/examRptPattern/itemListByOrgId?orgId=" + config.orgId , function (data2) {
                                if(data2==null||data2=="undefined"||data2==undefined||data2.length<=0){
                                    alert("在操作【"+menuName+"】之前，\n请保证【价表项目维护】里面“检查”项目中至少设置了一个检查项目");
                                    stepFlag = stepFlag - 1;
                                    return false;
                                }else{ pd(title, href); }
                            });
                        }
                    });
                }
            });
        }else if(menuName=="诊疗项目维护"){//当下一步的name是“诊疗项目维护”时
            var typeArr = [];
            $.get('/service/dict/findListByType',{type:'CLINIC_ITEM_CLASS_DICT'},function(res){
                typeArr = res
            })
            var type = "";
            var t = ''
            for(var i=0;i<typeArr.length;i++){
                if(typeArr[i].value != 'A' && typeArr[i].value != 'B'){
                    t += ',' + typeArr[i].value;
                }
            }
            type = t.length > 0 ? t.substr(1) : t
            $.get(basePath + "/price/findListWithLimit?limit=50&orgId=" + config.orgId+'&priceType='+type , function (data) {
                if(data==null||data=="undefined"||data==undefined||data.length<=0){
                    alert("在操作【"+menuName+"】之前，\n请保证【价表项目维护】里至少设置了一个项目的价表");
                    stepFlag = stepFlag - 1;
                    return false;
                }else{ pd(title, href); }
            });
        }else{ pd(title, href); }
    }else{
        pd(title, href);
    }

}
var pd = function(title, href){
    //如果路径为空，则直接返回
    if (!href) {
        return;
    }
    $("#mainContentIframe").attr("src",href);//子页面(内容页)中的上栏（不是顶栏的系统管理）
    if(cshMeuns&&cshMeuns.length>0&&stepAll>0&&$("#dlg-buttons")[0].style.display == ""){
        for (var i = 0; i < cshMeuns.length; i++) {
            if(cshMeuns[i] == title){
                stepFlag = i;
                $("#goBack").attr("title","上一步：第"+(stepFlag)+"步："+cshMeuns[i-1]);
                $("#goSkip").attr("title","跳过本步，直接进入下一步："+cshMeuns[i+1]);
                $("#goNext").attr("title","下一步：第"+(stepFlag+2)+"步："+cshMeuns[i+1]);
                if(stepFlag == 0){
                    cshdis0();
                }else if(stepFlag == cshMeuns.length-1){
                    cshdis3();
                }else{
                    cshdis1();
                }
                $("#_easyui_tree_"+(i+1)).attr('class','tree-node tree-node-selected');
            }else{
                $("#_easyui_tree_"+(i+1)).attr('class','tree-node');
            }
        }
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
        for (var j = 0; j < menus.length; j++) {
            if (menus[j].pid == menus[i].menuId) {
                cshMeuns.push(menus[j].menuName);
                menus[i].children.push(menus[j])
            }
        }
        if (menus[i].pid == "" || menus[i].pid == undefined || menus[i].pid == null) {
            menuDatas.push(menus[i]);
        }
    }

    var data = {
        title: '嵌入子模板',
        list: menuDatas
    };

    console.log(menuDatas)

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

var addMenu = function (serviceId, staffId) {//添加左栏的树形菜单
    config.serviceId = serviceId;
    config.staffId = staffId;

    var promise = $.get(basePath + "/service-param/find-by-self-service-id?selfServiceId=" + config.serviceId + "&orgId=" + config.org_Id, function (data) {
        console.log(data);
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
    promise.done(function (obj) {//点击顶栏的系统管理等,obj.length表示的是系统管理中配置的参数(弹窗提醒)的个数
        var op = {width: 400, height: 70 * obj.length}//弹窗的尺寸
        if (obj.length <= 0) {
            $.get(basePath + "/orgStaff/find-list-by-serviceId?serviceId=" + config.serviceId + "&staffId=" + config.staffId, function (data) {
                $("#dlg-buttons")[0].style.display = "none";
                $("#dlg-step")[0].style.display  = "none";
                stepAll = 0;
                makeTree(data)
            })
        } else {
            $("#paramDialog").dialog("resize", op);
            $("#paramDialog").dialog('open');
            $("#paramDialog").dialog('center');
        }

    })
}

var config = {};


$(function () {

    var sysService;    //名称为系统管理的服务
    var loginInfo = undefined ;
    var promise = $.getJSON(basePath+"/login/get-login-info?date="+new Date(),function(data){
        loginInfo = data ;
        config = loginInfo ;
        config.org_Id=loginInfo.orgId ;
        config.persion_Id = loginInfo.persionId;
        config.staffId =loginInfo.staffId;
        config.userName =loginInfo.userName;
        $("#userNameId").html(loginInfo.userName);
    }) ;

    var init=function(){
        if(config.currentStorage){
            $.get(basePath + '/drug-storage-dept/list',{orgId:config.org_Id,storageCode:config.currentStorage},function(res){
                console.log(res)
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

        //初始化向导开始
        var addProcess = function(){//加载第一个向导页，并遍历生成向导树
            $.get(basePath + "/init-process/find-by-orgId?orgId=" + config.org_Id, function (data) {
                makeTree(data);
                jzTab = 0;
                for (var i = 0; i < data.length; i++) {
                    if(data[i].pid != ""){
                        cshData.push(data[i]);
                        stepAll = i;
                    }
                    if(data[i].sysServiceId == "0" && jzTab == 0){//当初始化状态为0(初始状态)，且还没有加载过向导首页时执行
                        stepFlag = i-1;
                        cshdis0();
                        addTab(data[i].menuName,data[i].href);//内容页加载默认向导页面
                        jzTab = 1;//置为1；再次执行for循环时，此if就不再执行
                        if(i==data.length-1){//若步数是最后一步，就显示“上一步”，“跳过”和“下一步”按钮
                            //cshdis1();
                            cshdis3();//隐藏掉“跳过”和“下一步”按钮，显示“上一步”和“完成”
                        }
                    }
                }
                if(jzTab == 0){
                    cshdis0();
                    stepFlag = 0;
                    addTab(data[1].menuName,data[1].href);//内容页加载默认向导页面
                }
            })
        }
        var promiseProcess = $.get('/service/sys-sompany/findIsNoByOwner', {//判断是否为管理员，若是超管，就显示向导页
            owner: personId
        }, function (data) {
            if(data&&data != null){
                if(data.id!=null&&data.id!=''){
                    jzProcess = 1;//此值用来判断是否加载了向导页，若是加载了，此值为1，否则为0
                    addProcess();//加载向导页
                }
            }
        });
        //初始化向导结束

        promiseProcess.done(function(){
            //加载上栏的服务：根据机构ID和人员ID查询该员工在该机构的所有服务
            $.get(basePath + '/org-service/find-selfServiceList-by-orgId-personId?personId=' + personId + '&orgId=' + orgId, function (data) {
                for (var i = 0; i < data.length; i++) {
                    $("#menu").append("<li><a href=\"#\" onclick=\'addMenu(\"" + data[i].id + "\",\"" + staffId + "\")\'>" + data[i].serviceName + "</a></li>");
                    if(jzProcess==0&&i==0){//若不是管理员，没有加载导航页，左侧菜单树直接加载上栏第一个服务的菜单树
                        addMenu(data[i].id,staffId);
                        var welcome = '/modules/clinic/welcome.html';//欢迎页地址
                        addTab('欢迎使用'+data[i].serviceName,welcome);//若不是管理员，没有加载导航页，内容页直接加载欢迎页
                    }
                }
            });
        });


        //退出
        $("#exit").on("click", function () {
            $.getJSON("/service/login/exit",function(data){
                window.location.href = "/modules/sys/login.html";
            }) ;
        });

        //参数窗口的加载
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
                        console.log("config");
                        console.log(config);
                        if(item.id == 'currentStorage') {
                            init();
                        }
                    })
                    $.get(basePath + "/orgStaff/find-list-by-serviceId?serviceId=" + config.serviceId + "&staffId=" + config.staffId, function (data) {
                        //for(var ai=stepAll;ai>=0;ai--){
                        //    if($('#mainContent').tabs("exists",ai)){
                        //        $('#mainContent').tabs("close",ai);
                        //    }
                        //}
                        $("#dlg-buttons")[0].style.display = "none";
                        $("#dlg-step")[0].style.display  = "none";
                        stepAll = 0;
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

    //点击上一步
    $("#goBack").click(function () {//点击“上一步”执行
        stepFlag = stepFlag - 1;//步数减少1
        if(stepFlag==0){//当当前步数是0，表示首个向导页，隐藏“上一页”按钮
            cshdis0();
        }else if(stepFlag==stepAll-2){//点击上一步，当前步数是总步数-2，表示到倒数第二页，显示所有按钮
            cshdis1();
        }
        $.each(cshData,function(index,row){
            if(stepFlag==index){
                addTab(row.menuName,row.href);//内容页加载向导页面
            }
        });
    });
    //点击下一步
    $("#goNext").click(function () {
        updateInitFlagAndGoNext(0);
    });
    //点击跳过
    $("#goSkip").click(function () {
        updateInitFlagAndGoNext(0);
    });
    //点击完成
    $("#goFinish").click(function () {
        updateInitFlagAndGoNext(1);
    });
    var updateInitFlagAndGoNext = function(flag){//flag=0,b表示点击“下一步”或者“跳过”执行；flag=1，表示点击“完成”执行
        //alert(stepFlag+","+stepAll);
        stepFlag = stepFlag + 1;//步数增加1
        if(flag==0){
            if(stepFlag==stepAll-1){//点击“下一步”或“跳过”按钮：若下一步是最后一步，就隐藏掉“跳过”和“下一步”按钮，只显示“上一步”和“完成”按钮
                cshdis3();
            }else{
                cshdis1();
            }
        }else if(flag==1){//点击“完成”按钮：就只显示“上一步”按钮
            cshdis2();
        }
        $.each(cshData,function(index,row){
            var initId = row.id;
            var initFlag = row.sysServiceId;
            var menuName = row.menuName;
            if(stepFlag-1==index){
                if(initId!=null&&initId!=""&&initId!="undefined"&&initFlag!=null&&initFlag!=""&&initFlag!="undefined"){
                    if(initFlag=="0"){//若当前初始化状态为0：未初始化，才执行下面的方法，否者不执行
                        initFlag = '1';
                        $.post(basePath + "/init-process/save?id=" + initId + "&initFlag=" + initFlag , function (data) {
                            row.sysServiceId = '1';//点击“下一步”或者“跳过”按钮时，修改当前步数对应的向导服务的初始化状态,直接修改加载到本页面上的
                            // 向导数据中当前向导菜单的sysServiceId，即初始化状态initFlag，这样再次进入本方法判断initFlag时就不需要去后台获取了
                        });
                    }
                }
            }
            if(stepFlag==index&&flag==0){//flag=0表示点击“下一步”或“跳过”，会新加载页面；flag=1表示点击“完成”，不加载新页面
                //if(initFlag=="0"){
                addTab(row.menuName,row.href);//内容页加载向导页面
                //}else{
                //    stepFlag = stepFlag+1;
                //}
            }
        });
    }

    //当点击上面的横向菜单时
    //$('#mainContent').tabs({
    //    onSelect: function(title,i){
    //        tabSel(title);
    //    },
    //    onContextMenu: function (e, title, index) { //右键时触发事件
    //        e.preventDefault(); //阻止浏览器捕获右键事件
    //        $(this).tabs('select', index);
    //        tabSel(title);
    //        $('#mm').menu('show', {
    //            left: e.pageX,
    //            top: e.pageY
    //        }).data("tabTitle", title);
    //    }
    //})
    //右键菜单click
    $("#mm").menu({
        onClick : function (item) {
            closeTab(item.id);
        }
    })
    var tabSel = function(tt){
        if(stepAll>0&&$("#dlg-buttons")[0].style.display == "") {
            $.each(cshData, function (index, row) {
                if (tt == row.menuName) {
                    stepFlag = index;
                    if (index == 0) {
                        cshdis0();
                    } else if (index == cshData.length - 1) {
                        cshdis2();
                    } else {
                        cshdis1();
                    }
                }
            });
        }
    }

    //function closeTab(action) {
    //    var alltabs = $('#mainContent').tabs('tabs');
    //    var currentTab = $('#mainContent').tabs('getSelected');
    //    var allTabtitle = [];
    //    $.each(alltabs, function (i, n) {
    //        allTabtitle.push($(n).panel('options').title);
    //    })
    //    switch (action) {
    //        case "close":
    //            var currtab_title = currentTab.panel('options').title;
    //            $('#mainContent').tabs('close', currtab_title);
    //            break;
    //        case "closeall":
    //            $.each(allTabtitle, function (i, n) {
    //                $('#mainContent').tabs('close', n);
    //            });
    //            break;
    //        case "closeother":
    //            var currtab_title = currentTab.panel('options').title;
    //            $.each(allTabtitle, function (i, n) {
    //                if (n != currtab_title) {
    //                    $('#mainContent').tabs('close', n);
    //                }
    //            });
    //            break;
    //        case "closeright":
    //            var tabIndex = $('#mainContent').tabs('getTabIndex', currentTab);
    //            if (tabIndex == alltabs.length - 1) {
    //                $.messager.alert("提示", "该页已经是最右页！", "info");
    //                return false;
    //            }
    //            $.each(allTabtitle, function (i, n) {
    //                if (i > tabIndex) {
    //                    $('#mainContent').tabs('close', n);
    //                }
    //            });
    //
    //            break;
    //        case "closeleft":
    //            var tabIndex = $('#mainContent').tabs('getTabIndex', currentTab);
    //            if (tabIndex == 0) {
    //                $.messager.alert("提示", "该页已经是最左页！", "info");
    //                return false;
    //            }
    //            $.each(allTabtitle, function (i, n) {
    //                if (i < tabIndex) {
    //                    $('#mainContent').tabs('close', n);
    //                }
    //            });
    //
    //            break;
    //        case "exit":
    //            $('#mm').menu('hide');
    //            break;
    //    }
    //}
});