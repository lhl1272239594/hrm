$("<link>").attr({rel: "stylesheet", type: "text/css", href: "/static/easyui/css/icon.css"}).appendTo("head");
$("<script>").attr({type: "application/javascript", src: "/static/easyui/js/jquery.easyui.min.js"}).appendTo("head");
$("<script>").attr({
    type: "application/javascript",
    src: "/static/easyui/locale/easyui-lang-zh_CN.js"
}).appendTo("head");
$("<script>").attr({type: "application/javascript", src: "/static/js/tool.js"}).appendTo("head");
$("<script>").attr({type: "application/javascript", src: "/static/js/formSubmit.js"}).appendTo("head");
$("<script>").attr({type: "application/javascript", src: "/static/js/spell.js"}).appendTo("head");
$(function () {

    var registerVo = {};

    function limitLength(value, byteLength,title) {
        var newvalue = value.replace(/[^\x00-\xff]/g, "**");
        var length = newvalue.length;
        //当输入文字的字节数小于设定的字节数
        if (length * 1 <=byteLength * 1){
            return true;
        }
        var limitDate = newvalue.substr(0, byteLength);
        var count = 0;
        var limitvalue = "";
        for (var i = 0; i < limitDate.length; i++) {
            var flat = limitDate.substr(i, 1);
            if (flat == "*") {
                count++;
            }
        }
        var size = 0;
        var istar = newvalue.substr(byteLength * 1 - 1, 1);//校验点是否为“×”
        //if 基点是×; 判断在基点内有×为偶数还是奇数
        if (count % 2 == 0) {
            //当为偶数时
            size = count / 2 + (byteLength * 1 - count);
            limitvalue = value.substr(0, size);
        } else {
            //当为奇数时
            size = (count - 1) / 2 + (byteLength * 1 - count);
            limitvalue = value.substr(0, size);
        }
        alert( "["+title+"]最大输入" + byteLength + "个字节（相当于"+byteLength /2+"个汉字）！");
        //document.getElementById(csId).value = limitvalue;
        return false;
    }
    //文本框获取焦点的时候，显示
    $("#name").focus(function () {
        $("#res-name").text("*请输入正确的姓名");
        $("#res-name").css("color", "gray");
    });
    //判断用户名不能为空
    $("#name").blur(function () {
        if ($('#name').val() == "") {
            $("#res-name").text("*姓名不能为空");
            $("#res-name").css("color", "red");
            return false;
        }else if(!limitLength($('#name').val(),20,'姓名')){
            $("#res-name").text("*输入的姓名太长");
            $("#res-name").css("color", "red");
            return false;
        }
    });


    //添加注册信息
    $("#btnSubmit").click(function () {
        $("#btnSubmit").attr('disabled','disabled')
        var flag = false
        $('.reg-inp li span').each(function(){
            if($(this).css('color') == 'rgb(255, 0, 0)' && $.trim($(this).html()) != '*'){
                flag = true
            }
        })
        if(flag){
            $('#btnSubmit').removeAttr("disabled");
            alert("注册信息有误或不完整");
            return;
        }
        var cardNo= $("#cardNo").val();

        if(cardNo.charAt(cardNo.length-1)=='x'){
            var newCard=cardNo.substr(0,cardNo.length-1)+"X";
            registerVo.cardNo = newCard;
        } else{
            registerVo.cardNo = $("#cardNo").val();
        }
        var name=$("#name").val();
        registerVo.name = $("#name").val();
        registerVo.inputCode=makePy(name)[0];
        registerVo.nickName = $("#nickName").val();
        registerVo.email = $("#email").val();
        registerVo.phoneNum = $("#phoneNum").val();
        registerVo.password = $("#password").val();

        if (registerVo.cardNo != "" && registerVo.name != "" && registerVo.email != "" && registerVo.nickName != "" && registerVo.password != "" && registerVo.phoneNum != "") {

            jQuery.ajax({
                'type': 'POST',
                'url': "/service/register/add-info",
                'contentType': 'application/json',
                'data': JSON.stringify(registerVo),
                'dataType': 'json',
                'success': function (data) {
                    if (data.data == "success") {
                        if (confirm("注册成功，是否跳转到登录页面，进行登录")) {
                            window.location.href = "/modules/sys/login.html";
                        }else{
                            $('.l-inptext').val("");
                            $('#btnSubmit').removeAttr("disabled");
                        }
                    } else {
                        $('#btnSubmit').removeAttr("disabled");
                        alert("注册失败");
                    }
                },
                'error': function (data) {
                    $('#btnSubmit').removeAttr("disabled");
                    console.log("失败");
                }
            });
        } else {
            $('#btnSubmit').removeAttr("disabled");
            alert("请先填写注册的信息");
        }

    });


    //文本框获取焦点的时候，显示
    $("#cardNo").focus(function () {
        $("#res-card").text("*请输入正确的身份证号");
        $("#res-card").css("color", "gray");
    })
    ;

    //检验身份证号是否已存在
    $("#cardNo").blur(function () {
        var cardNo = $("#cardNo").val();
        if ($("#cardNo").val() == "") {
            $("#res-card").text("*身份证号不能为空");
            $("#res-card").css("color", "red");
            return false;
        }
        //身份证最后一位如果用户输入小写x直接转换为大写X
        if(cardNo.length==18&&cardNo.substring(17)=='x'){
            cardNo = cardNo.toUpperCase();
            $("#cardNo").val(cardNo);
        }
        //身份证正则表达式(18位)
        //var isIdCard2 = /^[1-9]\d{5}(19\d{2}|[2-9]\d{3})((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])(\d{4}|\d{3}X)$/i;
        var isIdCard2 = /^[1-9]\d{5}(?:(?!0000)[0-9]{4}(?:(?:0[1-9]|1[0-2])(?:0[1-9]|1[0-9]|2[0-8])|(?:0[13-9]|1[0-2])(?:29|30)|(?:0[13578]|1[02])31)|(?:[0-9]{2}(?:0[48]|[2468][048]|[13579][26])|(?:0[48]|[2468][048]|[13579][26])00)0229)(\d{4}|\d{3}X)$/;
        var stard = "10X98765432"; //最后一位身份证的号码
        var first = [7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2]; //1-17系数
        var sum = 0;

        if (!isIdCard2.test(cardNo)) {
            $("#res-card").text("*身份证号不合法");
            $("#res-card").css("color", "red");
            return false;
        }
        jQuery.ajax({
            'type': 'GET',
            'url': "/service/register/getCard?cardNo="+cardNo,
            'success': function (data) {
                if (data && data.cardNo !="") {
                    $("#res-card").text("*身份证号已经存在");
                    $("#res-card").css("color", "red");
                    return false;
                }
            },
            'error': function (data) {
                console.log("失败");
            }
        });
        return true;

    });

    //文本框获取焦点的时候，显示
    $("#nickName").focus(function () {
        $("#res-nick").text("*6-12位字符");
        $("#res-nick").css("color", "gray");
    });

    //校验用户名是否已经存在
    $("#nickName").blur(function () {
        var nickName = $("#nickName").val();
        var name = $("#nickName").val();
        if ($("#nickName").val() == "") {
            $("#res-nick").text("*用户名不能为空");
            $("#res-nick").css("color", "red");
            return false;
        }
        if (name.length < 6) {
            $("#res-nick").text("*请输入正确长度的字符");
            $("#res-nick").css("color", "red");
            return false;
        }
        if (name.length > 12) {
            $("#res-nick").text("*请输入正确长度的字符");
            $("#res-nick").css("color", "red");
            return false;
        }

        var usern = /^[a-zA-Z0-9_]{1,}$/;
        var username=$("#nickName").val();
        if (!username.match(usern)) {
            $("#res-nick").text("只能由数字字母和下划线组成");
            $("#res-nick").css("color", "red");
            return false;
        }
        jQuery.ajax({
            'type': 'GET',
            'url': "/service/register/getNick?nickName="+nickName,
            'success': function (data) {
                if (data && data.nickName !="") {
                    $("#res-nick").text("*用户名已经存在");
                    $("#res-nick").css("color", "red");
                    return false;
                }
            },
            'error': function (data) {
                console.log("失败");
            }
        });

        return true;
    });

    //文本框获取焦点的时候，显示
    $("#email").focus(function () {
        $("#res-email").text("*");
    });
    //校验邮箱是否合法，是否已被注册
    $("#email").blur(function () {
        var email = $("#email").val();

        if ($("#email").val() == "") {
            $("#res-email").text("*邮箱不能为空");
            return false;
        }
        if ($("#email").val().length < 7) {
            $("#res-email").css("color", "red");
            $("#res-email").text("*邮箱长度不合法（7-45个字符）");
            return false;
        }
        if ($("#email").val().length > 45) {
            $("#res-email").css("color", "red");
            $("#res-email").text("*邮箱长度不合法（7-45个字符）");
            return false;
        }
        if (!$("#email").val().match(/^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/)) {
            $("#res-email").text("*邮箱格式不正确");
            $("#email").focus(function () {
                $("#res-email").text("*");
            });
            return false;
        }
        jQuery.ajax({
            'type': 'GET',
            'url': "/service/register/getEmail?email="+email,
            'success': function (data) {
                if (data && data.email !="") {
                    $("#res-email").text("*邮箱已注册");
                    $("#res-email").css("color", "red");
                    return false;
                }
            },
            'error': function (data) {
                console.log("失败");
            }
        });

        return true;
    });


    $("#phoneNum").focus(function () {
        $("#res-phone").text("*");
    });
    //文本框获取焦点的时候，显示
    $("#phoneNum").blur(function () {
        var phone = $("#phoneNum").val();
        if ($("#phoneNum").val() == "") {
            $("#res-phone").text("*手机号不能为空");
            return false;
        }
        if (phone.length != 11) {
            $("#res-phone").text("*请输入有效的手机号");
            return false;
        }
        var myreg = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1})|(17[0-9]{1})|(14[0-9]{1}))+\d{8})$/;
        if (!myreg.test(phone)) {
            $("#res-phone").text('*请输入有效的手机号码');
            return false;
        }
        jQuery.ajax({
            'type': 'GET',
            'url': "/service/register/getPhone?phoneNum="+phone,
            'success': function (data) {
                if (data && data.phoneNum!="") {
                    $("#res-phone").text("*手机号已经注册");
                    $("#res-phone").css("color", "red");
                    return false;
                }
            },
            'error': function (data) {
                console.log("失败");
            }
        });
        return true;
    });


    //文本框获取焦点的时候，显示
    $("#password").focus(function () {
        $("#res-password").text("*");
    });
    $("#password").blur(function () {
        var password = $("#password").val();
        if (password.length == 0) {
            $("#res-password").css("color", "red");
            $("#res-password").text("*密码不能为空");
            return false;
        }else if(password.length > 40){
            $("#res-password").css("color", "red");
            $("#res-password").text("*密码长度不能大于40");
            return false;
        }
    });

    //确认密码
    $("#repassword").focus(function () {
        $("#res-repassword").text("*");
    });

    $("#repassword").blur(function () {
        var password = $("#password").val();
        var confirmPassword = $("#repassword").val();
        if (confirmPassword.length == 0) {
            $("#res-repassword").text("*确认密码不能为空");
            $("#res-repassword").css("color", "red");
        } else {
            if (password.length != 0) {
                if (password != confirmPassword) {
                    $("#res-repassword").text("*确认密码与密码不一致");
                    $("#res-repassword").css("color", "red");
                }
            }
        }
    });

});
