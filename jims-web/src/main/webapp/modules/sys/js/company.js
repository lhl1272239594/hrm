$(function () {
    var loginInfo = undefined ;
    var promise = $.getJSON("/service/login/get-login-info",function(data){
        loginInfo = data ;
        console.log(data);
        //阻止非法用户进入和长时间未操作需要再次登录
        if(loginInfo == undefined || loginInfo==null || !loginInfo.persionId){
            window.location.href = "/modules/sys/login.html";
        }
    }) ;
    promise.done(function() {
        var persion_id = loginInfo.persionId;
        var str = decodeURI(window.location.search);   //location.search是从当前URL的?号开始的字符串
        if (str.indexOf(name) != -1) {
            /**flag; 若在个人服务(default.html)页点击“注册机构”进入本页面，则在保存新机构之前，flag=0，前三步尚可点击；
             * 保存之后，或是由个人服务(default.html)页点击某一未审核机构进入本页面，则flag='1'，直接进入审核等待状态，前三步不可操作；
             */
            var flag = '0';
            if (str.indexOf("flag=") >= 0) {//点击某一个未通过审核的服务跳转到本页
                flag = str.substr(str.indexOf("flag=") + 5, 1);
            }
        }
        if (flag == '1') {
            $('#addServiceModel3')[0].style.display = "inline-block";
            $('#addServiceModel1')[0].style.display = "none";
            $('#addServiceModel2')[0].style.display = "none";
            $('#addServiceModel0')[0].style.display = "none";
            $('#nextBut1').attr("class", "done");
            $('#nextBut2').attr("class", "done");
            $('#nextBut3').attr("class", "done");
            $('#nextBut4').attr("class", "go-on");
        }
        //var currentOrgId = '1';

        var currentPersonId = persion_id;
        var company = {};
        //查询父机构
        if (flag == '0' && (persion_id != null || persion_id != '')) {
            jQuery.ajax({
                'type': 'get',
                'url': "/service/sys-company/select?persionId=" + persion_id,
                'contentType': 'application/json',
                'dataType': 'json',
                'success': function (data) {
                    if (data.length > 0) {
                        for (var i = 0; i <= data.length; i++) {
                            var orgName = data[i].orgName;
                            $("#parentId").append("<option value='" + data[i].id + "'>" + orgName + "</option>");
                        }
                    }
                    if (data.length == 1) {
                        var orgName = data.orgName;
                        $("#parentId").append("<option value='" + data.id + "'>" + orgName + "</option>");
                    }
                },
                'error': function (data) {
                    alert("系统提示", "查询上级机构失败");
                }
            });
        }

        //校验组织机构名称是否存在
        $("#orgName").focus(function () {
            $("#res-orgName").css("color", "gray");
            $("#res-orgName").text("*请输入真实的机构名称，以便验证通过！");
            return false;
        });
        $("#orgName").blur(function () {
            if ($("#orgName").val().trim() == "") {
                $("#res-orgName").css("color", "red");
                $("#res-orgName").text("*组织机构名称不能为空");
                return false;
            }
        });

        //校验邮箱是否正确
        $("#email").focus(function () {
            $("#res-email").text("*请输入真实的邮箱地址，以便验证通过！");
            $("#res-email").css("color", "gray");
            return false;
        });
        $("#email").blur(function () {
            if ($("#email").val().trim() == "") {
                $("#res-email").css("color", "red");
                $("#res-email").text("*邮箱不能为空");
                return false;
            }
            if (!$("#email").val().trim().match(/^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/)) {
                $("#res-email").css("color", "red");
                $("#res-email").text("*邮箱格式不正确");
                return false;
            }
            //return true;
        })
        //校验组织机构代码
        $("#orgCode").focus(function () {
            $("#res-orgCode").css("color", "gray");
            $("#res-orgCode").text("*请输入真实证书号，以便验证通过！");
            return false;
        });
        $("#orgCode").blur(function () {
            if ($("#orgCode").val().trim() == "") {
                $("#res-orgCode").css("color", "red");
                $("#res-orgCode").text("*组织机构代码不能为空");
                return false;
            }
        });

        //校验组织机构地址
        $("#address").focus(function () {
            $("#res-address").css("color", "gray");
            $("#res-address").text("*请输入真实机构地址，以便验证通过！");
            return false;
        });
        $("#address").blur(function () {
            if ($("#address").val().trim() == "") {
                $("#res-address").css("color", "red");
                $("#res-address").text("*组织机构地址不能为空");
                return false;
            }
        });
        //校验联系人
        $("#linkMan").focus(function () {
            $("#res-linkMan").css("color", "gray");
            $("#res-linkMan").text("*请输入真实的联系人，以方便联系！");
            return false;
        });
        $("#linkMan").blur(function () {
            if ($("#linkMan").val().trim() == "") {
                $("#res-linkMan").css("color", "red");
                $("#res-linkMan").text("*联系人不能为空");
                return false;
            }
        });

        //校验联系电话
        $("#linkPhoneNum").focus(function () {
            $("#res-linkPhoneNum").css("color", "gray");
            $("#res-linkPhoneNum").text("*请输入真实的联系电话，以方便联系！");
            return false;
        });
        $("#linkPhoneNum").blur(function () {
            if ($("#linkPhoneNum").val().trim() == "") {
                $("#res-linkPhoneNum").css("color", "red");
                $("#res-linkPhoneNum").text("*联系电话不能为空");
                return false;
            }

            var phone = $("#linkPhoneNum").val().trim();
            if (phone.length != 11) {
                $("#res-linkPhoneNum").css("color", "red");
                $("#res-linkPhoneNum").text("*请输入有效的手机号");
                return false;
            }
            var myreg = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1})|(17[0-9]{1})|(14[0-9]{1}))+\d{8})$/;
            if (!myreg.test(phone)) {
                $("#res-linkPhoneNum").css("color", "red");
                $("#res-linkPhoneNum").text('*请输入有效的手机号码');
                return false;
            }
        });

        validForm = function () {
            var result = true;
            if ($("#orgName").val().trim() == "") {
                $("#res-orgName").css("color", "red");
                $("#res-orgName").text("*组织机构名称不能为空");
                result = false;
            }
            if ($("#orgCode").val().trim() == "") {
                $("#res-orgCode").css("color", "red");
                $("#res-orgCode").text("*组织机构代码不能为空");
                result = false;
            }
            if ($("#address").val().trim() == "") {
                $("#res-address").css("color", "red");
                $("#res-address").text("*组织机构地址不能为空");
                result = false;
            }
            if ($("#linkMan").val().trim() == "") {
                $("#res-linkMan").css("color", "red");
                $("#res-linkMan").text("*联系人不能为空");
                result = false;
            }
            if ($("#linkPhoneNum").val().trim() == "") {
                $("#res-linkPhoneNum").css("color", "red");
                $("#res-linkPhoneNum").text("*联系电话不能为空");
                result = false;
            }
            if ($("#email").val().trim() == "") {
                $("#res-email").css("color", "red");
                $("#res-email").text("*邮箱不能为空");
                result = false;
            }
            if (!$("#email").val().trim().match(/^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/)) {
                $("#res-email").css("color", "red");
                $("#res-email").text("*邮箱格式不正确");
                result = false;
            }
            if ($("#email").val().trim().length > 50) {
                $("#res-email").css("color", "red");
                $("#res-email").text("*邮箱长度不合法,请重新填写");
                result = false;
            }
            var phone = $("#linkPhoneNum").val().trim();
            var myreg = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1})|(17[0-9]{1})|(14[0-9]{1}))+\d{8})$/;
            if (!myreg.test(phone)) {
                $("#res-linkPhoneNum").css("color", "red");
                $("#res-linkPhoneNum").text('*请输入有效的手机号码');
                result = false;
            }
            return result
        }

        saveService = function () {
            if (!validForm()) {
                $('#addServiceModel0')[0].style.display = "inline-block";
                $('#addServiceModel1')[0].style.display = "none";
                $('#addServiceModel2')[0].style.display = "none";
                $('#addServiceModel3')[0].style.display = "none";
                $('#nextBut1').attr("class", "go-on");
                $('#nextBut2').attr("class", "default");
                $('#nextBut3').attr("class", "default");
                $('#nextBut4').attr("class", "default");
                alert('请检查机构基本信息！')
                return false
            }
            $.get("/service/sys-sompany/findIsNoByOwner",{owner:persion_id,orgName:$("#orgName").val().trim()},function(res){
                if(res){
                    $("#res-orgName").css("color", "red");
                    $("#res-orgName").text("*当前用户下已经有一个同名机构！");
                    $('#addServiceModel0')[0].style.display = "inline-block";
                    $('#addServiceModel1')[0].style.display = "none";
                    $('#addServiceModel2')[0].style.display = "none";
                    $('#addServiceModel3')[0].style.display = "none";
                    $('#nextBut1').attr("class", "go-on");
                    $('#nextBut2').attr("class", "default");
                    $('#nextBut3').attr("class", "default");
                    $('#nextBut4').attr("class", "default");
                    return false;
                }
            })
            var datas = $('#addServiceModel1 .curr-btn-save')
            if (datas.length == 0) {
                alert('至少定制一项服务！')
                return false
            }
            var total = 0
            var saveData = []
            /**
             * 处理日期，
             * @param d 日期
             * @param t 年、月
             * @param n 数量
             * @returns {Date}
             */
            var handlerDate = function (d, t, n) {
                if (!d) return new Date()
                if (t == '年') {
                    var year = d.getFullYear()
                    d.setFullYear(+year + (isNaN(n) ? 0 : +n))
                } else if (t == '月') {
                    var month = +d.getMonth() + (isNaN(n) ? 0 : +n)
                    d.setFullYear(parseInt(month / 12) + d.getFullYear())
                    d.setMonth(month = month % 12)
                }
                return d
            }
            for (var i = 0; i < datas.length; i++) {
                var o = {}
                var liId = $(datas[i]).attr('id')
                var serviceId = liId.substr(liId.indexOf('_') + 1)
                o.serviceId = serviceId
                o.serviceStartDate = new Date()
                o.serviceEndDate = handlerDate(new Date(), $('.span-class2', datas[i]).html(), $('.service-num', datas[i]).val())
                saveData.push(o)

                var v = $('div tr:eq(2) td:eq(1)', datas[i]).html()
                total += +v.substr(0, v.indexOf('　'))
            }

            //alert('支付界面，金额'+total+'元！！')

            company.parentId = $("#parentId").val().trim();
            company.orgName = $("#orgName").val().trim();
            company.orgCode = $("#orgCode").val().trim();
            company.address = $("#address").val().trim();
            company.linkPhoneNum = $("#linkPhoneNum").val().trim();
            company.email = $("#email").val().trim();
            company.linkMan = $("#linkMan").val().trim();
            company.owner = currentPersonId;
            company.serviceList = saveData
            var name = $("#orgName").val().trim();

            $.ajax({
                'type': 'POST',
                'url': "/service/sys-company/saveCompanyAndService",
                'contentType': 'application/json',
                'data': JSON.stringify(company),
                'dataType': 'json',
                'success': function (data) {
                    if (data == "1") {
                        //$.messager.alert("系统提示", "保存成功");
                        $('#addServiceModel2')[0].style.display = "none";
                        $('#addServiceModel3')[0].style.display = "inline-block";
                        $('#nextBut3').attr("class", "done");
                        $('#nextBut4').attr("class", "go-on");
                        flag = '1';
                        alert("提交成功！！");
                        //解决传到另一个htnl中的乱码问题
                    } else {
                        flag = '0';
                        alert("提交失败，请重新提交");
                    }
                },
                'error': function (data) {
                    flag = '0';
                    alert("提交失败，请重新提交");
                }
            });
        }
        /**
         * 查询所有有偿的可选择的机构服务和所有服务
         */
        var dataArr
        $.get('/service/sys-service/findServiceWithPrice', {serviceClass: '0', serviceType: '1'}, function (res) {
            dataArr = res
        })
        if (company) {
            $("#nextBtn0").on('click', function () {
                if (!validForm()) return false
                $.get("/service/sys-sompany/findIsNoByOwner",{owner:persion_id,orgName:$("#orgName").val().trim()},function(res){
                    if(res){
                        $("#res-orgName").css("color", "red");
                        $("#res-orgName").text("*当前用户下已经有一个同名机构！");
                        return false;
                    }else{
                        $('#addServiceModel0')[0].style.display = "none";
                        $('#addServiceModel1')[0].style.display = "inline-block";
                        $('#nextBut1').attr("class", "done");
                        $('#nextBut2').attr("class", "go-on");
                        addNext();
                    }
                })
            });
            $("#nextBtn1").on('click', function () {
                //var datas = $('#addServiceModel1 .active')
                var datas = $('#addServiceModel1 .curr-btn-save')
                if (datas.length == 0) {
                    alert('至少定制一项服务！')
                    return false
                }
                $('#nextBut2').attr("class", "done");
                $('#nextBut3').attr("class", "go-on");
                if ($('#parentId').val().trim() != "") {
                    $('#parentId0').html($("#parentId").find("option:selected").text());
                }
                else {
                    $('#parentId0').html(" 无 ");
                }
                if ($('#orgName').val().trim() != "") {
                    $('#orgName0').html($('#orgName').val().trim());
                }
                if ($('#orgCode').val().trim() != "") {
                    $('#orgCode0').html($('#orgCode').val().trim());
                }
                if ($('#address').val().trim() != "") {
                    $('#address0').html($('#address').val().trim());
                }
                if ($('#linkMan').val().trim() != "") {
                    $('#linkMan0').html($('#linkMan').val().trim());
                }
                if ($('#linkPhoneNum').val().trim() != "") {
                    $('#linkPhoneNum0').html($('#linkPhoneNum').val().trim());
                }
                if ($('#email').val().trim() != "") {
                    $('#email0').html($('#email').val().trim());
                }
                $('#addServiceModel1')[0].style.display = "none";
                $('#addServiceModel2')[0].style.display = "inline-block";
                if (datas.length != 0) {
                    var reStr = "";
                    for (var i = 0; i < datas.length; i++) {
                        var liName = $(datas[i]).attr('name')
                        var serviceName = liName.substr(liName.indexOf('_') + 1);
                        //alert(serviceName);
                        var v = $('div tr:eq(2) td:eq(1)', datas[i]).html()
                        reStr += (i + 1) + "、" + serviceName + "<span class='font-orange'>(" + $('.service-num', datas[i]).val() + $('.span-class2', datas[i]).html() + " ￥" + v.substr(0, v.indexOf('　')) + ")</span><br/>";
                    }
                    $('#myService0').html(reStr);
                }
            });
            $("#nextBtn2").on('click', function () {
                $(this).attr("disabled","disabled");
                saveService();
            });
            $("#nextBtn3").on('click', function () {
                //解决传到另一个htnl中的乱码问题
                window.location.href = "/modules/sys/default.html";
            });

            $("#nextBut1").on('click', function () {
                if (flag == '1') return false;
                $('#addServiceModel0')[0].style.display = "inline-block";
                $('#addServiceModel1')[0].style.display = "none";
                $('#addServiceModel2')[0].style.display = "none";
                $('#addServiceModel3')[0].style.display = "none";
                $('#nextBut1').attr("class", "go-on");
                $('#nextBut2').attr("class", "default");
                $('#nextBut3').attr("class", "default");
                $('#nextBut4').attr("class", "default");
            });
            $("#nextBut2").on('click', function () {
                if (flag == '1') return false;
                if($('#addServiceModel1')[0].style.display=="inline-block") return false;
                if (!validForm()) return false;
                $.get("/service/sys-sompany/findIsNoByOwner",{owner:persion_id,orgName:$("#orgName").val().trim()},function(res){
                    if(res){
                        $("#res-orgName").css("color", "red");
                        $("#res-orgName").text("*当前用户下已经有一个同名机构！");
                        return false;
                    }else{
                        $('#nextBut1').attr("class", "done");
                        $('#nextBut2').attr("class", "go-on");
                        $('#nextBut3').attr("class", "default");
                        $('#nextBut4').attr("class", "default");
                        $('#addServiceModel1')[0].style.display = "inline-block";
                        $('#addServiceModel0')[0].style.display = "none";
                        $('#addServiceModel2')[0].style.display = "none";
                        $('#addServiceModel3')[0].style.display = "none";
                        addNext();
                    }
                })
            });
            $("#nextBut3").on('click', function () {
                if (flag == '1') return false;
                if($('#addServiceModel2')[0].style.display=="inline-block") return false;
                if (!validForm()) return false
                $('#nextBut1').attr("class", "done");
                var datas = $('#addServiceModel1 .curr-btn-save')
                if (datas.length == 0) return false;
                $('#nextBut2').attr("class", "done");
                if ($('#parentId').val().trim() != "") {
                    $('#parentId0').html($("#parentId").find("option:selected").text());
                }
                else {
                    $('#parentId0').html(" 无 ");
                }
                if ($('#orgName').val().trim() != "") {
                    $('#orgName0').html($('#orgName').val().trim());
                }
                if ($('#orgCode').val().trim() != "") {
                    $('#orgCode0').html($('#orgCode').val().trim());
                }
                if ($('#address').val().trim() != "") {
                    $('#address0').html($('#address').val().trim());
                }
                if ($('#linkMan').val().trim() != "") {
                    $('#linkMan0').html($('#linkMan').val().trim());
                }
                if ($('#linkPhoneNum').val().trim() != "") {
                    $('#linkPhoneNum0').html($('#linkPhoneNum').val().trim());
                }
                if ($('#email').val().trim() != "") {
                    $('#email0').html($('#email').val().trim());
                }
                $('#nextBut3').attr("class", "go-on");
                $('#nextBut4').attr("class", "default");
                $('#addServiceModel2')[0].style.display = "inline-block";
                $('#addServiceModel1')[0].style.display = "none";
                $('#addServiceModel0')[0].style.display = "none";
                $('#addServiceModel3')[0].style.display = "none";
            });
            $("#backBtn1").on('click', function () {
                $('#addServiceModel0')[0].style.display = "inline-block";
                $('#addServiceModel1')[0].style.display = "none";
                $('#addServiceModel2')[0].style.display = "none";
                $('#addServiceModel3')[0].style.display = "none";
                $('#nextBut1').attr("class", "go-on");
                $('#nextBut2').attr("class", "default");
                $('#nextBut3').attr("class", "default");
                $('#nextBut4').attr("class", "default");
            });
            $("#backBtn2").on('click', function () {
                $('#nextBut1').attr("class", "done");
                $('#nextBut2').attr("class", "go-on");
                $('#nextBut3').attr("class", "default");
                $('#nextBut4').attr("class", "default");
                $('#addServiceModel1')[0].style.display = "inline-block";
                $('#addServiceModel0')[0].style.display = "none";
                $('#addServiceModel2')[0].style.display = "none";
                $('#addServiceModel3')[0].style.display = "none";
                addNext();
            });
        }

        addNext = function () {
            var liArr = $('#addServiceModel1 ul li')
            if (liArr.length < 1) {
                for (var i = 0; i < dataArr.length; i++) {
                    var sysServicePriceList = dataArr[i].sysServicePriceList;
                    if (sysServicePriceList[0].serviceTimeLimit != null && sysServicePriceList[0].servicePrice != null) {
                        var li = '<li id="service_' + dataArr[i].id + '" name="serviceName_' + dataArr[i].serviceName + '">';
                        li += '<div class="service-set">'
                        li += '<h3>' + dataArr[i].serviceName + '</h3>'
                        li += '<table width="100%">'
                        li += '<tr style="height: 35px">'
                        li += '<td width="60"><span class="text-success">　类别：</span></td>'
                        li += '<td colspan="3">'
                        var priceArr = dataArr[i].sysServicePriceList
                        for (var j = 0, k = (priceArr ? priceArr.length : 0); j < k; j++) {
                            li += '<span class="span-class' + (j == 0 ? '2' : '') + '">' + priceArr[j].serviceTimeLimit + '</span>&nbsp;&nbsp;&nbsp;&nbsp;'
                        }
                        li += '</td></tr>'
                        li += '<tr style="height: 35px">'
                        li += '<td width="60"><span class="text-success">　时长：</span></td>'
                        li += '<td colspan="3"><input class="service-num" id="srsjh" maxlength="11" type="text" style="width: 50px" value="';
                        if (priceArr && priceArr.length > 0) {
                            li += (priceArr[0].serviceTimeLimit == '年' ? '1' : '12')
                        }
                        li += '"/><span>　' + (priceArr && priceArr.length > 0 ? priceArr[0].serviceTimeLimit : '') + '</span></td>'

                        li += '</tr>'
                        li += '<tr style="height: 35px">'
                        li += '<td width="60"><span class="text-success">　金额：</span></td>'
                        li += '<td colspan="3" style="color: red">'
                        if (priceArr && priceArr.length > 0) {
                            var num = priceArr[0].serviceTimeLimit == '年' ? '1' : '12';
                            li += ((isNaN(priceArr[0].servicePrice) ? 0 : (+priceArr[0].servicePrice)) * num).toFixed(2)
                        } else {
                            li += '0.00'
                        }
                        li += '　元</td>'
                        li += '</tr>'
                        li += '</table></div>';
                        li += '<div class="curr-btn" style="margin-left: 140px;"><button>定制</button></div>'
                        li += '</li>'
                        $('#addServiceModel1 ul').append(li);
                    }
                }
                $('#addServiceModel1 ul li').each(function () {
                    var liObj = $(this)
                    $(this).children('div').slideDown('normal')

                    $('div tr:eq(0) td:eq(1) span', this).click(function () {
                        if ($(liObj).attr('class') == 'active') return
                        $(this).parent('td').children('span').attr('class', 'span-class')
                        $(this).attr('class', 'span-class2');
                        var v = $(this).text()
                        $('div tr:eq(1) td:eq(1) input', liObj).val(v == '年' ? 1 : 12)
                        $('div tr:eq(1) td:eq(1) span', liObj).html('　' + v)
                        initPrice(liObj)
                    })
                    $('.service-num', this).keyup(function () {
                        validNum(this, liObj)
                    })
                    $('.service-num', this).mousedown(function () {
                        validNum(this, liObj)
                    })

                    $('.curr-btn', this).click(function () {
                        if ($('.service-num', liObj).attr('disabled')) {
                            $(".curr-btn", liObj).html("<button>确定</button>");
                            $('.service-num', liObj).attr('disabled', false)
                            $(liObj).attr('class', 'curr-btn');
                        } else {
                            $(".curr-btn", liObj).html("<button>取消</button>");
                            $('.service-num', liObj).attr('disabled', true)
                            $(liObj).attr('class', 'curr-btn-save');
                        }
                    })
                })
                var validNum = function (o, li) {
                    var p = parseInt($(o).val());
                    if(!p) p = 1
                    $(o).val(p)
                    initPrice(li)
                }
                var initPrice = function (li) {
                    var n = $('.service-num', li).val()
                    var numcheck = /^[1-9][0-9]*$/;
                    if (!numcheck.test(n)) {
                        n = '1';
                    }
                    if(+n > 100) n = '100';
                    $('.service-num', li).val(n);
                    var t = $('.span-class2', li).html()
                    var liId = $(li).attr('id')
                    var serviceId = liId.substr(liId.indexOf('_') + 1)
                    for (var i = 0; i < dataArr.length; i++) {
                        if (serviceId == dataArr[i].id) {
                            var price = dataArr[i].sysServicePriceList
                            for (var j = 0; j < price.length; j++) {
                                if (price[j].serviceTimeLimit == t) {
                                    var p = ((isNaN(price[j].servicePrice) ? 0 : (+price[j].servicePrice)) * n).toFixed(2)
                                    $('div tr:eq(2) td:eq(1)', li).html(p + '　元')
                                    return
                                }
                            }
                        }
                    }
                }
            }
        }
        addNext();
    })

    //注册机构
    $("#setCompany").on('click', function () {
        window.location.href = "/modules/sys/company.html";
    });
    //个人首页
    $("#default").on('click', function () {
        window.location.href = "/modules/sys/default.html";
    });
    //我的机构服务
    $("#myOrgServices").on('click', function () {
        window.location.href = "/modules/sys/self-company-service-list.html";
    });
    //我的服务
    $("#myServices").on('click', function () {
        window.location.href = "/modules/sys/service-list.html";
    });
    //退出
    $("#exit").on("click", function () {
        $.getJSON("/service/login/exit",function(data){
            window.location.href = "/modules/sys/login.html";
        }) ;
    });
});





