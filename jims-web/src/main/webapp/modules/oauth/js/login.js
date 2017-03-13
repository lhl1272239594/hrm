$(function () {

    $("#validateCode").focus(function () {
        $("#login").text("");
    });
    $("#password").focus(function () {
        $("#login").text("");
    });
    $("#loginName").focus(function () {
        $("#login").text("");
    });

    $("#validateCode").blur(function () {
        var validate = $('#validateCode').val();
        jQuery.ajax({
            'type': 'GET',
            'url': "/servlet/validateCodeServlet?validateCode=" + validate,
            'dataType': 'json',
            'success': function (data) {
                if (data == false) {
                    $("#login").text("验证码有误");
                    $("#login").css("color", "red");
                    return false;
                }
                else {
                    $("#btnSubmit").click(function () {
                        var loginName= $("#loginName").val();
                        var password = $("#password").val();
                        var response_type = getUrlParameter("response_type");
                        var client_id = getUrlParameter("client_id");
                        var client_secret = getUrlParameter("client_secret");
                        var redirect_uri = getUrlParameter("redirect_uri");
                        if ($("#loginName").val() == "") {
                            $("#login").text("用户名不能为空");
                            return false;
                        }
                        if ($("#password").val() == "") {
                            $("#login").text("密码不能为空");
                            return false;
                        }
                        if ($("#validateCode").val() == "") {
                            $("#login").text("验证码不能为空");
                            return false;
                        }
                        jQuery.ajax({
                            'type': 'GET',
                            'url': "/service/authz?loginName=" + loginName + "&password=" + password + "&response_type=" + response_type + "&client_id=" + client_id + "&client_secret=" + client_secret + "&redirect_uri=" + redirect_uri + "",
                            'contentType': 'application/json',
                            'dataType': 'json',
                            'success': function (data) {
                                if (data.code == "false") {
                                    parent.$.messager.alert('提示', "用户名密码不正确", "error");
                                } else{
                                    window.location.href = (data.code);
                                }
                            },
                            'error': function (data) {
                                console.log("失败");
                            }
                        });

                    });
                }
            },
            'error': function (data) {
                console.log("失败");
            }
        });

        return true;
    });
    function getUrlParameter(name) {
        name = name.replace(/[\[]/, "\\\[").replace(/[\]]/, "\\\]");
        var regexS = "[\\?&]" + name + "=([^&#]*)";
        var regex = new RegExp(regexS);
        var results = regex.exec(window.parent.location.href);
        if (results == null)    return ""; else {
            return results[1];
        }
    }
});

