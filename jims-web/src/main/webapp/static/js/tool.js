/**
 * Created by heren on 2016/4/20.
 */

var sub = true;
//扩展验证输入框
$.extend($.fn.validatebox.defaults.rules, {
    idcard: {// 验证身份证
        validator: function (value) {
            return /^\d{15}(\d{2}[A-Za-z0-9])?$/i.test(value);
        },
        message: '身份证号码格式不正确'
    },
    minLength: {
        validator: function (value, param) {
            return value.length >= param[0];
        },
        message: '请输入至少（2）个字符.'
    },
    length: {
        validator: function (value, param) {
            var len = $.trim(value).length;
            return len >= param[0] && len <= param[1];
        },
        message: "输入内容长度必须介于{0}和{1}之间."
    },
    phone: {// 验证电话号码
        validator: function (value) {
            return /^((\d2,3)|(\d{3}\-))?(0\d2,3|0\d{2,3}-)?[1-9]\d{6,7}(\-\d{1,4})?$/i.test(value);
        },
        message: '格式不正确,请使用下面格式:020-88888888'
    },
    mobile: {// 验证手机号码
        validator: function (value) {
            return /^(13|15|18)\d{9}$/i.test(value);
        },
        message: '手机号码格式不正确'
    },
    intOrFloat: {// 验证整数或小数
        validator: function (value) {
            return /^\d+(\.\d+)?$/i.test(value);
        },
        message: '请输入数字，并确保格式正确'
    },
    currency: {// 验证货币
        validator: function (value) {
            return /^\d+(\.\d+)?$/i.test(value);
        },
        message: '货币格式不正确'
    },
    qq: {// 验证QQ,从10000开始
        validator: function (value) {
            return /^[1-9]\d{4,9}$/i.test(value);
        },
        message: 'QQ号码格式不正确'
    },
    integer: {// 验证整数 可正负数
        validator: function (value) {
            //return /^[+]?[1-9]+\d*$/i.test(value);

            return /^([+]?[0-9])|([-]?[0-9])+\d*$/i.test(value);
        },
        message: '请输入整数'
    },
    age: {// 验证年龄
        validator: function (value) {
            return /^(?:[1-9][0-9]?|1[01][0-9]|120)$/i.test(value);
        },
        message: '年龄必须是0到120之间的整数'
    },

    chinese: {// 验证中文
        validator: function (value) {
            return /^[\Α-\￥]+$/i.test(value);
        },
        message: '请输入中文'
    },
    english: {// 验证英语
        validator: function (value) {
            return /^[A-Za-z]+$/i.test(value);
        },
        message: '请输入英文'
    },
    unnormal: {// 验证是否包含空格和非法字符
        validator: function (value) {
            return /.+/i.test(value);
        },
        message: '输入值不能为空和包含其他非法字符'
    },
    username: {// 验证用户名
        validator: function (value) {
            return /^[a-zA-Z][a-zA-Z0-9_]{5,15}$/i.test(value);
        },
        message: '用户名不合法（字母开头，允许6-16字节，允许字母数字下划线）'
    },
    faxno: {// 验证传真
        validator: function (value) {
            //            return /^[+]{0,1}(\d){1,3}[ ]?([-]?((\d)|[ ]){1,12})+$/i.test(value);
            return /^((\d2,3)|(\d{3}\-))?(0\d2,3|0\d{2,3}-)?[1-9]\d{6,7}(\-\d{1,4})?$/i.test(value);
        },
        message: '传真号码不正确'
    },
    zip: {// 验证邮政编码
        validator: function (value) {
            return /^[1-9]\d{5}$/i.test(value);
        },
        message: '邮政编码格式不正确'
    },
    ip: {// 验证IP地址
        validator: function (value) {
            return /d+.d+.d+.d+/i.test(value);
        },
        message: 'IP地址格式不正确'
    },
    name: {// 验证姓名，可以是中文或英文
        validator: function (value) {
            return /^[\Α-\￥]+$/i.test(value) | /^\w+[\w\s]+\w+$/i.test(value);
        },
        message: '请输入姓名'
    },
    date: {// 验证姓名，可以是中文或英文
        validator: function (value) {
            //格式yyyy-MM-dd或yyyy-M-d
            return /^(?:(?!0000)[0-9]{4}([-]?)(?:(?:0?[1-9]|1[0-2])\1(?:0?[1-9]|1[0-9]|2[0-8])|(?:0?[13-9]|1[0-2])\1(?:29|30)|(?:0?[13578]|1[02])\1(?:31))|(?:[0-9]{2}(?:0[48]|[2468][048]|[13579][26])|(?:0[48]|[2468][048]|[13579][26])00)([-]?)0?2\2(?:29))$/i.test(value);
        },
        message: '清输入合适的日期格式'
    },
    msn: {
        validator: function (value) {
            return /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/.test(value);
        },
        message: '请输入有效的msn账号(例：abc@hotnail(msn/live).com)'
    },
    same: {
        validator: function (value, param) {
            if ($("#" + param[0]).val() != "" && value != "") {
                return $("#" + param[0]).val() == value;
            } else {
                return true;
            }
        },
        message: '两次输入的密码不一致！'
    }
});
$.extend({
    postForm: function (url,formId,callback, error) {
        if(sub) {
            sub=false;
            if($("#"+formId).form("validate")) {
                var dict = '{';
                $($('#'+formId).serializeArray()).each(function (index, element) {
                    if(element.value!=null && element.value!=''){
                        dict=dict+'"'+element.name+'"'+':'+'"'+element.value+'",'
                    }

                })
                dict = dict.substring(0, dict.length - 1);
                dict=dict+'}'
                return jQuery.ajax({
                    cache: true,
                    'type': 'POST',
                    'url': url,
                    'contentType': 'application/json',
                    'data': dict,
                    'dataType': 'json',
                    'success': callback,
                    'error': error,
                    'complete':function(xhr,status){
                        sub=true;
                    }
                });
            }else{
                return false;
            }
        }
    },
    postRows: function (url,tableId,callback, error) {
        if(sub) {
            sub = false;
            $("#" + tableId).datagrid('endEdit', editRow);
            var rows = $('#' + tableId).datagrid('getRows');
            return jQuery.ajax({
                cache: true,
                'type': 'POST',
                'url': url,
                'contentType': 'application/json',
                'data': JSON.stringify(rows),
                'dataType': 'json',
                'success': callback,
                'error': error,
                'complete': function (xhr, status) {
                    sub = true;
                }
            })
        }
    },
    postJSON: function  (url, data, callback, error) {
        if(sub) {
            sub = false;
            if (typeof(data) == Object) {
                data = JSON.stringify(data);
            }
            return jQuery.ajax({
                'type': 'POST',
                'url': url,
                'contentType': 'application/json',
                'data': data,
                'dataType': 'json',
                'success': callback,
                'error': function(XMLHttpRequest, textStatus, errorThrown) {
                    if(XMLHttpRequest.status==401){
                        $.getJSON("/service/skip/exit",function(data){
                            parent.$("#msg").window("open");
                        }) ;
                    }
                    return error(XMLHttpRequest, textStatus, errorThrown);
            },
                'complete': function (xhr, status) {
                    sub = true;
                    if(xhr.status==200){
                        parent.resetTime();
                    }
                }
            });
        }
    },
    postJSONAsync: function  (url, data, callback, error) {
        if(sub) {
            sub = false;
            if (typeof(data) == Object) {
                data = JSON.stringify(data);
            }
            return jQuery.ajax({
                'type': 'POST',
                'url': url,
                'contentType': 'application/json',
                'data': data,
                'async': false,      //ajax同步
                'dataType': 'json',
                'success': callback,
                'error': function(XMLHttpRequest, textStatus, errorThrown) {
                    if(XMLHttpRequest.status==401){
                        $.getJSON("/service/skip/exit",function(data){
                            parent.$("#msg").window("open");
                        }) ;
                    }
                    return error(XMLHttpRequest, textStatus, errorThrown);
                },
                'complete': function (xhr, status) {
                    sub = true;
                    if(xhr.status==200){
                        parent.resetTime();
                    }
                }
            });
        }
    }
})


/*该方法使日期列的显示符合阅读习惯*/
//datagrid中用法：{ field:'StartDate',title:'开始日期', formatter: formatDatebox, width:80}
function formatDatebox(value) {
    if (value == null || value == '') {
        return '';
    }
    var dt = parseToDate(value);
    return dt.format("yyyy-MM-dd");
}

/*转换日期字符串为带时间的格式*/
function formatDateBoxFull(value) {
    if (value == null || value == '') {
        return '';
    }
    var dt = parseToDate(value);
    return dt.format("yyyy-MM-dd hh:mm:ss");
}

//辅助方法(转换日期)
function parseToDate(value) {
    if (value == null || value == '') {
        return undefined;
    }

    var dt;
    if (value instanceof Date) {
        dt = value;
    }
    else {
        if (!isNaN(value)) {
            dt = new Date(value);
        }
        else if (value.indexOf('/Date') > -1) {
            value = value.replace(/\/Date\((-?\d+)\)\//, '$1');
            dt = new Date();
            dt.setTime(value);
        } else if (value.indexOf('/') > -1) {
            dt = new Date(Date.parse(value.replace(/-/g, '/')));
        } else {
            dt = new Date(value);
        }
    }
    return dt;
}

//为Date类型拓展一个format方法，用于格式化日期
Date.prototype.format = function (format) //author: meizz
{
    var o = {
        "M+": this.getMonth() + 1, //month
        "d+": this.getDate(),    //day
        "h+": this.getHours(),   //hour
        "m+": this.getMinutes(), //minute
        "s+": this.getSeconds(), //second
        "q+": Math.floor((this.getMonth() + 3) / 3),  //quarter
        "S": this.getMilliseconds() //millisecond
    };
    if (/(y+)/.test(format))
        format = format.replace(RegExp.$1,
            (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
        if (new RegExp("(" + k + ")").test(format))
            format = format.replace(RegExp.$1,
                RegExp.$1.length == 1 ? o[k] :
                    ("00" + o[k]).substr(("" + o[k]).length));
    return format;
};

//以下拓展是为了datagrid的日期列在编辑状态下显示正确日期
$.extend(
    $.fn.datagrid.defaults.editors, {
        datebox: {
            init: function (container, options) {
                var input = $('<input type="text">').appendTo(container);
                input.datebox(options);
                return input;
            },
            destroy: function (target) {
                $(target).datebox('destroy');
            },
            getValue: function (target) {
                return $(target).datebox('getValue');
            },
            setValue: function (target, value) {
                $(target).datebox('setValue', formatDatebox(value));
            },
            resize: function (target, width) {
                $(target).datebox('resize', width);
            }
        },
        datetimebox:{
            init: function (container, options) {
                var input = $('<input type="text">').appendTo(container);
                input.datetimebox(options);
                return input;
            },
            destroy: function (target) {
                $(target).datetimebox('destroy');
            },
            getValue: function (target) {
                return $(target).datetimebox('getValue');
            },
            setValue: function (target, value) {
                $(target).datetimebox('setValue', formatDateBoxFull(value));
            },
            resize: function (target, width) {
                $(target).datetimebox('resize', width);
            }
        }
    });
/**
 * formatter datebox
 * @param value
 * @returns {*}
 */
$.fn.datebox.defaults.formatter = function(value){
    if (value == null || value == '') {
        return '';
    }
    var dt = parseToDate(value);
    return dt.format("yyyy-MM-dd");
}
/**
 * 赋值 formatter datebox
 * @param value
 * @returns {*}
 */
$.fn.datebox.defaults.parser = function(s){
    if (!isNaN(s)) {
        var dt = parseToDate(s);
        if(dt!=undefined){
            s=dt.format("yyyy-MM-dd")
        }
    }
    if (!s) return new Date();
    var ss = s.split('-');
    var y = parseInt(ss[0],10);
    var m = parseInt(ss[1],10);
    var d = parseInt(ss[2],10);
    if (!isNaN(y) && !isNaN(m) && !isNaN(d)){
        return new Date(y,m-1,d);
    } else {
        return new Date();
    }
};
/**
 * datetimebox datebox
 * @param value
 * @returns {*}
 */
$.fn.datetimebox.defaults.formatter = function(value){
    if (value == null || value == '') {
        return '';
    }
    var dt = parseToDate(value);
    return dt.format("yyyy-MM-dd hh:mm:ss");
}
/**
 * 赋值 datetimebox datebox
 * @param value
 * @returns {*}
 */
$.fn.datetimebox.defaults.parser = function(s){
    if (s != null || s != '') {
        var dt = parseToDate(s);
        if(dt!=undefined){
            s=dt.format("yyyy-MM-dd hh:mm:ss")
        }
    }
    if (!s) return new Date();
    var dt=s.split(" ");
    var d=$.fn.datebox.defaults.parser(dt[0]);
    if(dt.length<2){
        return d;
    }
    var _ae3=$(this).datetimebox("spinner").timespinner("options").separator;
    var tt=dt[1].split(_ae3);
    var hour=parseInt(tt[0],10)||0;
    var _ae4=parseInt(tt[1],10)||0;
    var _ae5=parseInt(tt[2],10)||0;
    return new Date(d.getFullYear(),d.getMonth(),d.getDate(),hour,_ae4,_ae5);
};



//datagrid的提示信息
var myview = $.extend({},$.fn.datagrid.defaults.view,{
    onAfterRender:function(target){
        $.fn.datagrid.defaults.view.onAfterRender.call(this,target);
        var opts = $(target).datagrid('options');
        var vc = $(target).datagrid('getPanel').children('div.datagrid-view');
        vc.children('div.datagrid-empty').remove();
        if (!$(target).datagrid('getRows').length){
            var d = $('<div class="datagrid-empty"></div>').html(opts.emptyMsg || 'no records').appendTo(vc);
            d.css({
                position:'absolute',
                left:0,
                top:50,
                width:'100%',
                textAlign:'center'
            });
        }
    }
});



$.extend($.fn.datagrid.methods, {
    addEditor : function(jq, param) {
        if (param instanceof Array) {
            $.each(param, function(index, item) {
                var e = $(jq).datagrid('getColumnOption', item.field);
                e.editor = item.editor; });
        } else {
            var e = $(jq).datagrid('getColumnOption', param.field);
            e.editor = param.editor;
        }
    },
    removeEditor : function(jq, param) {
        if (param instanceof Array) {
            $.each(param, function(index, item) {
                var e = $(jq).datagrid('getColumnOption', item);
                e.editor = {};
            });
        } else {
            var e = $(jq).datagrid('getColumnOption', param);
            e.editor = {};
        }
    }
});

//让window居中
var easyuiPanelOnOpen = function (left, top) {
    var iframeWidth = $(this).parent().parent().width();

    var iframeHeight = $(this).parent().parent().height();

    var windowWidth = $(this).parent().width();
    var windowHeight = $(this).parent().height();

    var setWidth = (iframeWidth - windowWidth) / 2;
    var setHeight = (iframeHeight - windowHeight) / 2;
    $(this).parent().css({/* 修正面板位置 */
        left: setWidth,
        top: setHeight
    });

    if (iframeHeight < windowHeight)
    {
        $(this).parent().css({/* 修正面板位置 */
            left: setWidth,
            top: 0
        });
    }
    $(".window-shadow").hide();
};
$.fn.window.defaults.onOpen = easyuiPanelOnOpen;

function getRealLen( str ) {
    return str.replace(/[^\x00-\xff]/g, '__').length; //这个把所有双字节的都给匹配进去了
}
//datagrid加载完后合并指定单元格
function mergeCellsByField(tableID, colList) {
    var ColArray = colList.split(",");
    var tTable = $("#" + tableID);
    var TableRowCnts = tTable.datagrid("getRows").length;
    var tmpA;
    var tmpB;
    var PerTxt = "";
    var CurTxt = "";
    var alertStr = "";
    for (var j=0;j <= ColArray.length - 1; j++) {
        PerTxt = "";
        tmpA = 1;
        tmpB = 0;

        for (i = 0; i <= TableRowCnts; i++) {
            if (i == TableRowCnts) {
                CurTxt = "";
            }
            else {
                CurTxt = tTable.datagrid("getRows")[i][ColArray[j]];
            }
            if (PerTxt == CurTxt) {
                tmpA += 1;
            }
            else {
                tmpB += tmpA;

                tTable.datagrid("mergeCells", {
                    index: i - tmpA,
                    field: ColArray[j],　　//合并字段
                    rowspan: tmpA,
                    colspan: null
                });
                tTable.datagrid("mergeCells", { //根据ColArray[j]进行合并
                    index: i - tmpA,
                    field: "Ideparture",
                    rowspan: tmpA,
                    colspan: null
                });

                tmpA = 1;
            }
            PerTxt = CurTxt;
        }
    }
}
