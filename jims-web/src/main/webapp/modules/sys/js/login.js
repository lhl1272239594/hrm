$(function () {
    var loginVo = {};


    //$("#validateCode").focus(function () {
    //    $("#login").text("");
    //});
    $("#password").focus(function () {
        $("#login").text("");
    });
    $("#loginName").focus(function () {
        $("#login").text("");
    });


    //$("#validateCode").blur(function () {
    //var validate = $('#validateCode').val();
    //jQuery.ajax({
    //    'type': 'GET',
    //    'url': "/servlet/validateCodeServlet?validateCode=" + validate,
    //    'dataType': 'json',
    //    'success': function (data) {
    //if (data == false) {
    //    $("#login").text("验证码有误");
    //    $("#login").css("color", "red");
    //    return false;
    //}
    $("#btnSubmit").click(function () {
        login();
    });
    //        return true;
    //    },
    //    'error': function (data) {
    //        console.log("失败");
    //    }
    //});

    //});
});

login = function () {
    var loginName=$("#loginName").val();
    var password=$("#password").val();
    loginName=loginName.replace(/\s+/g,"");
    password=password.replace(/\s+/g,"")
    $("#loginName").val(loginName);
    $("#password").val(password);
    if (loginName == '' || password == '') {
        $("#login").text("用户名或者密码不能为空");
        return;
    }
    //身份证最后一位如果用户输入小写x直接转换为大写X
    if (loginName.length == 18 && loginName.substring(17) == 'x' && loginName.indexOf("@") == -1) {
        loginName = loginName.toUpperCase();
        $("#loginName").val(loginName);
    }

    var loginName = $("#loginName").val();
    var password = $("#password").val();

        $.get('/service/skip/loginToHrm?loginName=' + loginName + '&password=' + password, function (data) {
            //console.log(data);
            if (data.data == "nameNull") {
                $("#login").text("用户名错误或不存在");
                return false;
            }
            if (data.data == "passNull") {
                $("#login").text("密码错误");
                return false;
            }
            if (data.code == "success") {
                if (data.data == 1) {

                    window.location.href = ('/modules/admin-index.html');
                }
                else{
                    //弹出窗口供选择组织机构

                    var loginInfo = undefined ;
                    var promise = $.getJSON("/service/skip/get-login-info",function(data){
                        loginInfo = data ;
                        //console.log(data);
                        //阻止非法用户进入和长时间未操作需要再次登录
                        if(loginInfo == undefined || loginInfo==null || !loginInfo.persionId){
                            window.location.href = "/modules/sys/login.html";
                        }
                    }) ;
                    promise.done(function() {
                        var dataArr = [];

                        $.get('/service/persion-service-list/findListByFlag?persionId=' + loginInfo.persionId, function (data) {
                            if (data != null) {
                                if(data.length==0){//若新注册的用户(尚未注册机构或没有被管理员分配机构)，则直接进入默认页面
                                    window.location.href = ('/modules/sys/default.html');
                                }else if(data.length==1){//若管理员或用户已经拥有一个机构，直接进入主页面
                                    $.get('/service/sys-sompany/get-sysCompany-by-id?id=' + data[0].id, function (res) {
                                        if(res.applyStatus=="2"){//机构通过审核
                                            window.location.href="/modules/fbd/hrm/hrm.jsp";
                                        }else{//机构未通过审核
                                            window.location.href="/modules/sys/company.html?flag=1";
                                        }
                                    });
                                }else{//若管理员或用户拥有不止一个机构，需要先选择进入哪一个机构
                                    for (var i = 0; i < data.length; i++) {
                                        dataArr.push({id:data[i].id,serviceName: data[i].serviceName,serviceImage:data[i].serviceImage,
                                            tranServiceDescription:data[i].tranServiceDescription,serviceClass:data[i].serviceClass});
                                    }
                                    $('#addServiceModel ul').html("");
                                    var li='';
                                    for (var i = 0; i < dataArr.length; i++) {
                                        li = '<li id="service_' + dataArr[i].id + '_'+(dataArr[i].serviceClass!=null&&dataArr[i].serviceClass!=""?dataArr[i].serviceClass:"a")+'" data-target="'+(dataArr[i].serviceClass!=null&&dataArr[i].serviceClass=="1"?"#serviceDialog":"")+'" data-toggle="modal">';
                                        li += '<a ><span class="service-name">' + dataArr[i].serviceName + '</span></a>'
                                        li += '<img src="'+(dataArr[i].serviceImage!=null&&dataArr[i].serviceImage!=""?dataArr[i].serviceImage:"/static/bookstrap/images/service/normal.jpg")+'"/>'
                                        li += '<div id="des_'+dataArr[i].id+'" style=" display:none; ">'+(dataArr[i].tranServiceDescription==null?"":dataArr[i].tranServiceDescription)+'</div>'
                                        li += '</li>'
                                        $('#addServiceModel ul').append(li);
                                    }
                                    li = '<li id="service_addNewCompany_1">';
                                    li += '<span class="service-Company" title="注册机构">+</span>';
                                    li += '</li>'
                                    $('#addServiceModel ul').append(li);
                                    //点击服务跳转到首页
                                    $('#addServiceModelDiv ul li').each(function () {
                                        $(this).click(function () {
                                            var id=this.id.substring(8,this.id.length-2);
                                            if(id=='addNewCompany'){
                                                window.location.href = "/modules/sys/company.html";
                                            }else{
                                                $.get('/service/sys-sompany/get-sysCompany-by-id?id=' + id, function (res) {
                                                    if(res.applyStatus=="2"){//机构通过审核
                                                        window.location.href="/modules/fbd/hrm/hrm.jsp";
                                                    }else{//机构未通过审核
                                                        window.location.href="/modules/sys/company.html?flag=1";
                                                    }
                                                });
                                            }
                                        });
                                    });
                                    var width = 200 * (dataArr.length+1) + 43;
                                    var top,left;
                                    if(dataArr.length>7){  top = '5%';    }
                                    else if(dataArr.length<=3){  top = '20%';    }
                                    else{   top = '15%';}
                                    if(dataArr.length>=3){          left = '20%';}
                                    else if(dataArr.length==2){    left = '28%';}
                                    else if(dataArr.length==1){    left = '36%';}
                                    else if(dataArr.length==0){    left = '43%';}
                                    $('#addServiceModel').dialog({
                                        title: '请选择机构',
                                        iconCls: "icon-edit",
                                        closable: false,
                                        left: left,
                                        top: top,
                                        width: width+"px",
                                        maxWidth:"850px",
                                        height: "auto",
                                        modal: true
                                    }).dialog("open");
                                    $("#loginAllDiv")[0].style.opacity = 1/10;
                                }
                            }else{//若新注册的用户直接进入默认页面
                                window.location.href = ('/modules/sys/default.html');
                            }
                            return false;
                        });
                    });
                }

                //window.location.href = ('/modules/sys/default.html');
                //window.location.href = "/modules/sys/company.html";
                //return false;

            }
        });

}