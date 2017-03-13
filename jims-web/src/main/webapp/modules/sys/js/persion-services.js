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

        $('#saveService').click(function () {
            var datas = $('#addServiceModel .curr-btn-save')
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
                o.persionId = persion_id
                o.flag = "1"
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
                        window.location.href = "/modules/sys/default.html";
                    } else {
                        alert("保存失败！！");
                    }
                },
                'error': function (data) {
                    alert("保存失败！！");
                }
            });
        })
        /**
         * 保存信息
         */
        var dataArr
        $.get('/service/sys-service/findServiceWithPrice',{ serviceClass: '1', serviceType: '1', persionId: persion_id, state: '1' }, function (res) {
            dataArr = res
            var liArr = $('#addServiceModel ul li')
            if (liArr.length < 1) {
                for (var i = 0; i < dataArr.length; i++) {
                    //用于判断服务信息是否维护完整
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


            if (dataArr.length != 8) {
                for (var j = 0; j < (8 - dataArr.length); j++) {
                    var li = '<li>';
                    li += '</li>';
                    $('#addServiceModel ul').append(li);
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





