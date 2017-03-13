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

        var persionServiceList = {};

        /**
         * 展示我的服务
         */
        var myDataArr
        $.get('/service/persion-service-list/findListByPersionId', {
            serviceClass: '1',
            serviceType: '1',
            persionId: persion_id,
            state: '0'
        }, function (res) {
            myDataArr = res
            var liArr = $('#myServiceModel div')
            if (liArr.length < 1) {
				if(myDataArr.length<=0){
					$('#myServiceModel').append('<p align="center" style="padding-top: 30px;">尚未定制任何个人服务，请尝试定制下面的推荐服务。</p>');
					return false;
				}
                var now = new Date();
                var year = now.getFullYear(); //getFullYear getYear
                var month = now.getMonth() + 1;
                var date = now.getDate();
                if (month < 10) month = "0" + month;
                if (date < 10) date = "0" + date;
                var nowTime = new Date(year + "-" + month + "-" + date).getTime();
                for (var i = 0; i < myDataArr.length; i++) {
                    var li = '<div class="doctortesebox">';
                    li += '<div class="doctorteseboxL">';
                    li += '<a href="#">';
                    if (myDataArr[i].serviceImage == null) {
                        li += '<img src="/static/bookstrap/images/service/normal.jpg" height="100" width="100" />'
                    } else {
                        li += '<img src="' + myDataArr[i].serviceImage + '" height="100" width="100" />'
                    }
                    li += '</a></div>'
                    li += '<div class="doctorteseboxR">';
                    li += '<div class="doctorteseboxR_name" data-target="#serviceDialog" data-toggle="modal"><a href="#" id="desid_' + myDataArr[i].serviceId + '">' + myDataArr[i].serviceName;
                    var startDate = myDataArr[i].startDate;
                    var endDate = myDataArr[i].endDate;
                    if (endDate == null) {
                        li += '(长期)';
                    } else {
                        if (Number(endDate.substr(0, 4)) > Number(startDate.substr(0, 4))) {
                            if (Number(endDate.substr(5, 2)) > Number(startDate.substr(5, 2))) {
                                li += '(' + (Number(endDate.substr(0, 4)) - Number(startDate.substr(0, 4))) + '年' + (Number(endDate.substr(5, 2)) - Number(startDate.substr(5, 2))) + '月)';
                            } else if (Number(endDate.substr(5, 2)) == Number(startDate.substr(5, 2))) {
                                li += '(' + (Number(endDate.substr(0, 4)) - Number(startDate.substr(0, 4))) + '年)';
                            } else {
                                if (Number(endDate.substr(0, 4)) - Number(startDate.substr(0, 4)) > 1) {
                                    li += '(' + (Number(endDate.substr(0, 4)) - Number(startDate.substr(0, 4)) - 1) + '年' + (Number(endDate.substr(5, 2)) + 12 - Number(startDate.substr(5, 2))) + '月)';
                                } else {
                                    li += '(' + (Number(endDate.substr(5, 2)) + 12 - Number(startDate.substr(5, 2))) + '月)';
                                }
                            }
                        } else if (Number(endDate.substr(0, 4)) == Number(startDate.substr(0, 4))) {
                            if (Number(endDate.substr(5, 2)) > Number(startDate.substr(5, 2))) {
                                li += '(' + (Number(endDate.substr(5, 2)) - Number(startDate.substr(5, 2))) + '月)';
                            } else {
                                li += '(0月)';
                            }
                        } else {
                            li += '(0月)';
                        }
                    }
                    li += '</a>'
                    li += '</div>'
                    if (endDate == null) {
                        li += '<div class="teseprice"><span>服务状态：</span>正常</div>';
                    } else {
                        var endTime = new Date(endDate).getTime();
                        if (nowTime > endTime) {
                            li += '<div class="teseprice font-red"><span>服务状态：</span>已过期</div>';
                        } else {
                            var cha = ((Date.parse(month + '/' + date + '/' + year) - Date.parse(endDate.substr(5, 2) + '/' + endDate.substr(8, 2) + '/' + endDate.substr(0, 4))) / 86400000);
                            if (Math.abs(cha) > 30) {
                                li += '<div class="teseprice font-green"><span>服务状态：</span>正常</div>';
                            } else {
                                li += '<div class="teseprice"><span>服务状态：</span>即将到期</div>';
                            }
                        }
                    }
                    li += '<div class="teseCont">有效期：'
                    if (endDate == null) {
                        li += '长期';
                    } else {
                        li += startDate + '至' + endDate
                    }
                    li += '</div>'
                    if (endDate == null) {
                        li += '<div><button class="btn btn-default btn-small">免费服务</button></div>';
                    } else {
                        var endTime = new Date(endDate).getTime();
                        if (nowTime > endTime) {
                            li += '<div><button class="btn btn-success btn-small" id="next' + myDataArr[i].serviceId + '" data-target="#goServiceModel" data-toggle="modal">继续定制</button></div>';
                        } else {
                            li += '<div><button class="btn btn-success btn-small" id="next' + myDataArr[i].serviceId + '" data-target="#goServiceModel" data-toggle="modal">续费</button></div>';
                        }
                    }
                    li += '</div>'
                    li += '</div>'
                    li += '<div id="des_'+myDataArr[i].serviceId+'" style="display:none;">'+(myDataArr[i].tranServiceDescription==null?"":myDataArr[i].tranServiceDescription)+'</div>'
                    $('#myServiceModel').append(li);
                }
                var fi = 0;
                //alert($('#myServiceModel').html());

                $('#myServiceModel .doctorteseboxR_name').each(function () {
                    var liDesObj = $(this)
                    $('a', this).click(function () {
                        var liDesId = ($(this).attr('id')).substring(6, $(this).attr('id').length);
                        if($("#des_"+liDesId).html()==null||$("#des_"+liDesId).html()==""){
                            $("#serviceDialogDes").html('尚未添加描述');
                        }else{
                            $("#serviceDialogDes").html($("#des_"+liDesId).html());
                        }
                    })
                })
                $('#myServiceModel div').each(function () {
                    var liObj = $(this)
                    $(this).children('div').slideDown('normal')

                    $('.btn-success', this).click(function () {
                        var liSubId = ($(this).attr('id')).substring(4, $(this).attr('id').length);
                        var liSubArr = $('#goServiceModel ul li')
                        if (liSubArr.length < 1 && fi == 0) {
                            fi = 1;
                            var goDataArr;
                            $.get('/service/sys-service/findServiceWithPrice?id=' + liSubId, function (res) {
                                goDataArr = res;
                                for (var i = 0; i < goDataArr.length; i++) {
                                    var liSub = '<li id="service_' + goDataArr[i].id + '">';
                                    liSub += '<div class="service-set">'
                                    liSub += '<h3>' + goDataArr[i].serviceName + '</h3>'
                                    liSub += '<table width="100%">'
                                    liSub += '<tr style="height: 35px">'
                                    liSub += '<td width="60"><span class="text-success">　类别：</span></td>'
                                    liSub += '<td colspan="3">'
                                    var priceSubArr = goDataArr[i].sysServicePriceList
                                    for (var j = 0, k = (priceSubArr ? priceSubArr.length : 0); j < k; j++) {
                                        liSub += '<span class="span-class' + (j == 0 ? '2' : '') + '">' + priceSubArr[j].serviceTimeLimit + '</span>&nbsp;&nbsp;&nbsp;&nbsp;'
                                    }
                                    liSub += '</td></tr>'
                                    liSub += '<tr style="height: 35px">'
                                    liSub += '<td width="60"><span class="text-success">　时长：</span></td>'
                                    liSub += '<td colspan="3"><input class="service-num" id="srsjh" maxlength="11" type="text" style="width: 50px" value="';
                                    if (priceSubArr && priceSubArr.length > 0) {
                                        liSub += (priceSubArr[0].serviceTimeLimit == '年' ? '1' : '12')
                                    }
                                    liSub += '"/><span>　' + (priceSubArr && priceSubArr.length > 0 ? priceSubArr[0].serviceTimeLimit : '') + '</span></td>'

                                    liSub += '</tr>'
                                    liSub += '<tr style="height: 35px">'
                                    liSub += '<td width="60"><span class="text-success">　金额：</span></td>'
                                    liSub += '<td colspan="3" style="color: red">'
                                    if (priceSubArr && priceSubArr.length > 0) {
                                        var numSub = priceSubArr[0].serviceTimeLimit == '年' ? '1' : '12';
                                        liSub += ((isNaN(priceSubArr[0].servicePrice) ? 0 : (+priceSubArr[0].servicePrice)) * numSub).toFixed(2)
                                    } else {
                                        liSub += '0.00'
                                    }
                                    liSub += '　元</td>'
                                    liSub += '</tr>'
                                    liSub += '</table></div>';
                                    liSub += '<div class="curr-btn" style="margin-left: 140px;"><button>定制</button></div>'
                                    liSub += '</li>'
                                    $('#goServiceModel ul').append(liSub);
                                }
                                $('#goServiceModel ul li').each(function () {
                                    var liObj = $(this)
                                    $(this).children('div').slideDown('normal')

                                    $('div tr:eq(0) td:eq(1) span', this).click(function () {
                                        if ($(liObj).attr('class') == 'active') return
                                        $(this).parent('td').children('span').attr('class', 'span-class')
                                        $(this).attr('class', 'span-class2');
                                        var v = $(this).text()
                                        $('div tr:eq(1) td:eq(1) input', liObj).val(v == '年' ? 1 : 12)
                                        $('div tr:eq(1) td:eq(1) span', liObj).html('　' + v)
                                        initSubPrice(liObj)
                                    })

                                    $('.service-num', this).keyup(function () {
                                        validSubNum(this, liObj)
                                    })
                                    $('.service-num', this).mousedown(function () {
                                        validSubNum(this, liObj)
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
                                var validSubNum = function (o, li) {
                                    if (isNaN($(o).val())) {
                                        $(o).val('1')
                                    }
                                    initSubPrice(li)
                                }
                                var initSubPrice = function (li) {
                                    var n = $('.service-num', li).val()
                                    var numcheck = /^[1-9][0-9]*$/;
                                    if (!numcheck.test(n)) {
                                        $('.service-num', li).val('1');
                                    }
                                    var t = $('.span-class2', li).html()
                                    var liId = $(li).attr('id')
                                    var serviceId = liId.substr(liId.indexOf('_') + 1)
                                    for (var i = 0; i < goDataArr.length; i++) {
                                        if (serviceId == goDataArr[i].id) {
                                            var price = goDataArr[i].sysServicePriceList
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
                            })
                        }
                    })
                })
            }

        })
        /**
         * 保存定制的服务
         */
        var datas;
        saveService = function (upFlag) {
            if (datas.length == 0) {
                alert('至少定制一项服务！')
                return false
            }
            var total = 0
            var saveData = []
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
                o.persionId = persion_id
                o.flag = "1"
                o.upFlag = upFlag;
                saveData.push(o)

                var v = $('div tr:eq(2) td:eq(1)', datas[i]).html()
                total += +v.substr(0, v.indexOf('　'))
            }


            alert('支付界面，金额'+total+'元！！')

            persionServiceList.serviceList = saveData
            jQuery.ajax({
                'type': 'POST',
                'url': "/service/persion-service-list/saveService",
                'contentType': 'application/json',
                'data': JSON.stringify(persionServiceList),
                'dataType': 'json',
                'success': function (data) {
                    if (data == "1") {
                        alert("保存成功！！");
                        window.location.href = "/modules/sys/service-list.html";
                    } else {
                        alert("保存失败！！");
                    }
                },
                'error': function (data) {
                    alert("保存失败！！");
                }
            });
        }

        $('#saveService').click(function () {
            datas = $('#addServiceModel .curr-btn-save')
            saveService('0');//新增
        });
        $('#saveGoService').click(function () {
            datas = $('#goServiceModel .curr-btn-save')
            saveService('1');//修改
        });


        /**
         * 展示可定制的有偿的个人服务
         */
        var dataArr
        $.get('/service/sys-service/findServiceWithPrice', { serviceClass: '1', serviceType: '1', persionId: persion_id, state: '1' }, function (res) {
            dataArr = res
            var liArr = $('#addServiceModel ul li')
            if (liArr.length < 1) {
                for (var i = 0; i < dataArr.length; i++) {
                    var sysServicePriceList = dataArr[i].sysServicePriceList;
                    if (sysServicePriceList[0].serviceTimeLimit != null && sysServicePriceList[0].servicePrice != null) {
                        var li = '<li id="service_' + dataArr[i].id + '">';
                        li += '<div class="service-set">'
                        li += '<h3 data-target="#serviceDialog" data-toggle="modal"><span id="desh3_' + dataArr[i].id + '" style="cursor: pointer">' + dataArr[i].serviceName + '</span></h3>'
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
                        li += '<td colspan="3"><input class="service-num" type="text" style="width: 50px" value="';
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
                        li += '<div id="desdiv_'+dataArr[i].id+'" style=" display:none; ">'+(dataArr[i].tranServiceDescription==null?"":dataArr[i].tranServiceDescription)+'</div>'
                        $('#addServiceModel ul').append(li);
                    }
                }

                $('#addServiceModel h3').each(function () {
                    var liDesObj = $(this)
                    $('span', this).click(function () {
                        var liDesId = ($(this).attr('id')).substring(6, $(this).attr('id').length);
                        if($("#desdiv_"+liDesId).html()==null||$("#desdiv_"+liDesId).html()==""){
                            $("#serviceDialogDes").html('尚未添加描述');
                        }else{
                            $("#serviceDialogDes").html($("#desdiv_"+liDesId).html());
                        }
                    })
                })
                $('#addServiceModel ul li').each(function () {
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
                    if (isNaN($(o).val())) {
                        $(o).val('1')
                    }
                    initPrice(li)
                }
                var initPrice = function (li) {
                    var n = $('.service-num', li).val()
                    var numcheck = /^[1-9][0-9]*$/;
                    if (!numcheck.test(n)) {
                        $('.service-num', li).val('1');
                    }
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

        })

    })

    //注册机构
    $("#setCompany").on('click', function () {
        window.location.href = "/modules/sys/company.html";
    });
    //个人首页
    $("#default").on('click', function () {
        window.location.href = "/modules/sys/default.html";
    });
    //我的服务
    $("#myServices").on('click', function () {
        window.location.href = "/modules/sys/service-list.html";
    });
    //我的机构服务
    $("#myOrgServices").on('click', function () {
        window.location.href = "/modules/sys/self-company-service-list.html";
    });
    //定制更多个人服务
    $("#moreServices").click(function () {
        window.location.href = "/modules/sys/persion-services.html";
    });
    //退出
    $("#exit").on("click", function () {
        $.getJSON("/service/login/exit",function(data){
            window.location.href = "/modules/sys/login.html";
        }) ;
    });


});





