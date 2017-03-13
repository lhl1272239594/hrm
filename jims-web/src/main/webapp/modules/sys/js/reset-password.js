$("<link>").attr({rel: "stylesheet", type: "text/css", href: "/static/easyui/css/icon.css"}).appendTo("head");
$("<script>").attr({type: "application/javascript", src: "/static/easyui/js/jquery.easyui.min.js"}).appendTo("head");
$("<script>").attr({
    type: "application/javascript",
    src: "/static/easyui/locale/easyui-lang-zh_CN.js"
}).appendTo("head");
$("<script>").attr({type: "application/javascript", src: "/static/js/tool.js"}).appendTo("head");
$("<script>").attr({type: "application/javascript", src: "/static/js/formSubmit.js"}).appendTo("head");
$("<script>").attr({type: "application/javascript", src: "/static/js/spell.js"}).appendTo("head");
var basePath = "/service";

$(function () {
    var persionId = parent.config.persion_Id;

    //文本框获取焦点的时候
    $("#oldPassword").focus(function () {
        $("#res-oldPassword").html("");
        $("#res-oldPassword").css("color", "gray");
    });
    //判断原密码不能为空
    $("#oldPassword").blur(function () {
        var oldPassword = $("#oldPassword").val();
        if ($('#oldPassword').val() == "") {
            $("#res-oldPassword").text("*原密码不能为空");
            $("#res-oldPassword").css("color", "red");
            return false;
        }
        $.get(basePath + '/login/findPasswordByPid?persionId=' + persionId, function (data) {
            if (data.data != oldPassword) {
                $("#res-oldPassword").text("*原密码不正确，不能进行重置密码！");
                $("#res-oldPassword").css("color", "red");
                return false;
            }
        });
    });


    $("#newPassword").focus(function () {
        $("#res-newPassword").html("");
        $("#res-newPassword").css("color", "gray");
    });
    $("#newPassword").blur(function () {
        if ($('#newPassword').val() == "") {
            $("#res-newPassword").text("*密码不能为空");
            $("#res-newPassword").css("color", "red");
            return false;
        }
    });


    $("#resetPassword").focus(function () {
        $("#res-resetPassword").html("");
        $("#res-resetPassword").css("color", "gray");
    });
    $("#resetPassword").blur(function () {
        var newPassword = $("#newPassword").val();
        var resetPassword = $("#resetPassword").val();
        if ($('#resetPassword').val() == "") {
            $("#res-resetPassword").text("*确认密码不能为空");
            $("#res-resetPassword").css("color", "red");
            return false;
        }
        if (newPassword != resetPassword) {
            $("#res-resetPassword").text("*密码 确认密码不一致，请确认后再保存！");
            $("#res-resetPassword").css("color", "red");
            return false;
        }
    });


    $("#saveBtn").on('click', function () {
        var newPassword = $("#newPassword").val();
        var flag = false
        $('.fitem  span').each(function () {
            if ($(this).css('color') == 'rgb(255, 0, 0)') {
                flag = true
            }
        }) ;
        if (flag) return
        $.get(basePath + '/login/updatePassword?password=' + newPassword + '&persionId=' + persionId, function (data) {
            if (data.data == "success") {
                $.messager.alert("系统提示", "重置密码成功！");
                $("#oldPassword").val("");
                $("#newPassword").val("");
                $("#resetPassword").val("");
            }
        });

    });


});




