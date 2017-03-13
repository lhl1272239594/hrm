/*TMODJS:{"version":"1.0.0"}*/
!function () {
    function a(a, b) {
        return (/string|function/.test(typeof b) ? h : g)(a, b)
    }

    function b(a, c) {
        return "string" != typeof a && (c = typeof a, "number" === c ? a += "" : a = "function" === c ? b(a.call(a)) : ""), a
    }

    function c(a) {
        return l[a]
    }

    function d(a) {
        return b(a).replace(/&(?![\w#]+;)|[<>"']/g, c)
    }

    function e(a, b) {
        if (m(a))for (var c = 0, d = a.length; d > c; c++)b.call(a, a[c], c, a); else for (c in a)b.call(a, a[c], c)
    }

    function f(a, b) {
        var c = /(\/)[^\/]+\1\.\.\1/, d = ("./" + a).replace(/[^\/]+$/, ""), e = d + b;
        for (e = e.replace(/\/\.\//g, "/"); e.match(c);)e = e.replace(c, "/");
        return e
    }

    function g(b, c) {
        var d = a.get(b) || i({filename: b, name: "Render Error", message: "Template not found"});
        return c ? d(c) : d
    }

    function h(a, b) {
        if ("string" == typeof b) {
            var c = b;
            b = function () {
                return new k(c)
            }
        }
        var d = j[a] = function (c) {
            try {
                return new b(c, a) + ""
            } catch (d) {
                return i(d)()
            }
        };
        return d.prototype = b.prototype = n, d.toString = function () {
            return b + ""
        }, d
    }

    function i(a) {
        var b = "{Template Error}", c = a.stack || "";
        if (c)c = c.split("\n").slice(0, 2).join("\n"); else for (var d in a)c += "<" + d + ">\n" + a[d] + "\n\n";
        return function () {
            return "object" == typeof console && console.error(b + "\n\n" + c), b
        }
    }

    var j = a.cache = {}, k = this.String, l = {
        "<": "&#60;",
        ">": "&#62;",
        '"': "&#34;",
        "'": "&#39;",
        "&": "&#38;"
    }, m = Array.isArray || function (a) {
            return "[object Array]" === {}.toString.call(a)
        }, n = a.utils = {
        $helpers: {}, $include: function (a, b, c) {
            return a = f(c, a), g(a, b)
        }, $string: b, $escape: d, $each: e
    }, o = a.helpers = n.$helpers;
    a.get = function (a) {
        return j[a.replace(/^\.\//, "")]
    }, a.helper = function (a, b) {
        o[a] = b
    }, "function" == typeof define ? define(function () {
        return a
    }) : "undefined" != typeof exports ? module.exports = a : this.template = a, /*v:57*/
        a("his-index", function (a, b) {
            "use strict";
            var c = this, d = (c.$helpers, c.$each), e = a.list, f = (a.menu, a.i, c.$escape), g = function (d, e) {
                e = e || a;
                var f = c.$include(d, e, b);
                return h += f
            }, h = "";
            return h += '      <div id="aa" class="easyui-accordion" style="width:300px;height:200px;"> ', d(e, function (a) {
                h += ' <div title="', h += f(a.menuName), h += '"> ', g("./tree", a), h += " </div> "
            }), h += " </div>", new k(h)
        }), /*v:3*/
        a("param", function (a) {
            "use strict";
            var b = this, c = (b.$helpers, b.$each), d = a.list, e = (a.item, a.$index, b.$escape), f = (a.op, "");
            return c(d, function (a) {
                f += ' <div class="fitem"> <label>', f += e(a.paramDesp), f += ':</label> <select id="', f += e(a.paramName), f += '" class="easyui-combobox" style="width:200px;"> ', c(a.options, function (a) {
                    f += ' <option value="', f += e(a.value), f += '">', f += e(a.label), f += "</option> "
                }), f += " </select></div> "
            }), new k(f)
        }), /*v:33*/
        a("sub-tree", function (a, b) {
            "use strict";
            var c = this, d = (c.$helpers, c.$each), e = a.children, f = (a.value, a.i, c.$escape), g = function (d, e) {
                e = e || a;
                var f = c.$include(d, e, b);
                return h += f
            }, h = "";
            return h += "<ul> ", d(e, function (a) {
                h += " ", a.children.length > 0 ? (h += " <li> <span>", h += f(a.menuName), h += "</span> ", a.children.length > 0 && (h += " ", g("./sub-tree", a), h += " "), h += " </li> ") : (h += ' <li class="tadaye"> <a href="#" onclick="addTab(\'', h += f(a.menuName), h += "','", h += f(a.href), h += "')\"><span>", h += f(a.menuName), h += "</span></a> </li> "), h += " "
            }), h += " </ul>", new k(h)
        }), /*v:37*/
        a("tree", function (a, b) {
            "use strict";
            var c = this, d = (c.$helpers, c.$each), e = a.children, f = (a.value, a.i, c.$escape), g = function (d, e) {
                e = e || a;
                var f = c.$include(d, e, b);
                return h += f
            }, h = "";
            return h += '<ul class="easyui-tree"> ', d(e, function (a) {
                h += " ", 0 == a.children.length ? (h += ' <li><a href="#" onclick="addTab(\'', h += f(a.menuName), h += "','", h += f(a.href), h += "','"+a.menuId+"')\"><span>", h += f(a.menuName), h += "</span></a> </li> ") : (h += " <li><span>", h += f(a.menuName), h += "</span> ", a.children.length > 0 && (h += " ", g("./sub-tree", a), h += " "), h += " </li> "), h += " "
            }), h += " </ul>", new k(h)
        })
}();