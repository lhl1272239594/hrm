/**
 * 人员维护
 * @author yangruidong
 * @version 2016-04-29
 */
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

    var orgId = parent.config.org_Id;
    var deptId;
    var deptName;
    //检查类别
    $("#staff").treegrid({
        fit: true,
        fitColumns: true,
        striped: true,
        singleSelect: true,
        idField: "id",
        treeField: "deptName",
        toolbar: '#classft',
        loadMsg: '数据正在加载中，请稍后.....',
        columns: [[{
            title: 'id',
            field: "id",
            hidden: true
        }, {
            title: '科室名称',
            field: 'deptName',
            width: '100%'
        }]],
        onClickRow: function (rowIndex, rowData) {
            var node = $("#staff").treegrid("getSelected");
            deptId = node.id;
            deptName = node.deptName;
            var url = basePath + "/orgStaff/list?orgId=" + orgId + "&deptId=" + deptId;
            $("#staffGrid").datagrid("reload", url);

        }
    });

    // var orgId = parent.config.org_id;
    //加载树形结构的treegrid数据
    var loadDept = function () {

        var depts = [];
        var treeDepts = [];
        var loadPromise = $.get("/service/dept-dict/list?orgId=" + orgId, function (data) {
            $.each(data, function (index, item) {
                var obj = {};
                obj.deptName = item.deptName;
                obj.id = item.id;
                obj.deptCode = item.deptCode;
                obj.deptPropertity = item.deptPropertity;
                obj.parentId = item.parentId;
                obj.children = [];

                depts.push(obj);

            });

        });


        loadPromise.done(function () {
            for (var i = 0; i < depts.length; i++) {
                for (var j = 0; j < depts.length; j++) {
                    if (depts[i].id == depts[j].parentId) {
                        depts[i].children.push(depts[j]);
                    }
                }
                if (depts[i].children.length > 0 && !depts[i].parentId) {
                    treeDepts.push(depts[i]);
                }

                if (!depts[i].parentId && depts[i].children <= 0) {
                    treeDepts.push(depts[i])
                }
            }

            $("#staff").treegrid('loadData', treeDepts);
        })
    }
    loadDept();

    //人员信息
    $("#staffGrid").datagrid({
        //fit: true,
        //fitColumns: true,
        striped: true,
        singleSelect: true,
        toolbar: '#ft',
        method: 'GET',
        rownumbers: true,
        fit: true,
        url: basePath + "/orgStaff/list?orgId=" + orgId + "&deptId=" + deptId,
        loadMsg: '数据正在加载中，请稍后.....',
        pagination: true,//分页控件
        pageSize: 15,
        pageList: [10, 15, 30, 50],//可以设置每页记录条数的列表
        columns: [[{
            title: "id",
            field: "id",
            hidden: true
        }, {
            title: "姓名",
            field: "name",
            align: 'center',
            width: '10%'

        }, {
            title: '性别',
            field: 'sex',
            align: 'center',
            width: '8%',
            formatter: function (value, row, index) {
                var sex = value;
                $.each(sexList, function (index, item) {
                    if (item.value == value) {
                        sex = item.label;
                    }
                });
                return sex;
            }
        }, {
            title: '身份证号',
            field: 'cardNo',
            align: 'center',
            width: '12%'
        }, {
            title: '联系电话',
            field: 'phoneNum',
            align: 'center',
            width: '10%'
        }, {
            title: '邮箱',
            field: 'email',
            align: 'center',
            width: '10%'
        }, {
            title: '职称',
            field: 'title',
            align: 'center',
            width: '10%',
            formatter: function (value, row, index) {
                var title = value;
                $.each(titleList, function (index, item) {
                    if (item.value == value) {
                        title = item.label;
                    }
                });
                return title;
            }
        }, {
            title: '昵称',
            field: 'nickName',
            align: 'center',
            width: '10%'
        }, {
            title: '民族',
            field: 'nation',
            align: 'center',
            width: '10%',
            formatter: function (value, row, index) {
                var nation = value;
                $.each(nationList, function (index, item) {
                    if (item.value == value) {
                        nation = item.label;
                    }
                });
                return nation;
            }
        }, {
            title: '人员id',
            field: 'staffId',
            hidden: 'true'
        }
        ]]
    });

    var sexList = [];   //性别
    $.get(basePath + '/dict/findListByType?type=SEX_DICT', function (data) {
        sexList = data;
    });

    var nationList = [];    //民族
    $.get(basePath + '/dict/find-list-by-type?type=NATION_DICT', function (data) {
        nationList = data;
    });

    var titleList = [];     //职称
    $.get(basePath + '/dict/find-list-by-type?type=' + 'TITLE_DICT', function (data) {
        titleList = data
    });

    $('#sex').combobox({    //性别
        url: basePath + '/dict/findListByType?type=SEX_DICT',
        valueField: 'value',
        textField: 'label',
        method: 'GET'
    });
    $('#nation').combogrid({     //民族
        panelWidth: 320,
        idField: 'value',
        textField: 'label',
        loadMsg: '数据正在加载',
        url: basePath + '/dict/find-list-by-type?type=' + 'NATION_DICT',
        mode: 'remote',
        method: 'GET',
        fitColumns: true,
        columns: [[
            {field: 'label', title: '民族', align: 'center', width: 120},
            {field: 'value', title: '标签', align: 'center', width: 50},
            {field: 'inputCode', title: '拼音码', align: 'center', width: 50}
        ]]
    });

    $('#deptName').combobox({
        url: basePath + '/dept-dict/selectParentByOrgId?orgId=' + orgId,
        valueField: 'id',
        textField: 'deptName'
    });

    //加载角色
    $('#role').combobox({
        url: basePath + '/org-role/findAllListByOrgId?orgId=' + orgId,
        valueField: 'id',
        textField: 'roleName',
        method: 'GET',
        multiple: true
    });

    $("#title").combogrid({     //加载职称
        panelWidth: 320,
        idField: 'value',
        textField: 'label',
        loadMsg: '数据正在加载',
        url: basePath + '/dict/find-list-by-type?type=' + 'TITLE_DICT',
        mode: 'remote',
        method: 'GET',
        fitColumns: true,
        columns: [[
            {field: 'label', title: '标签', align: 'center', width: 50},
            {field: 'value', title: '键值', align: 'center', width: 120},
            {field: 'inputCode', title: '拼音码', align: 'center', width: 50}
        ]]
    });

    //检查子类别模态框
    $("#addStaff").window({
        title: '组织机构人员维护',
        closed: true,
        modal: true,
        onClose: function () {
            $("#staffForm").form('reset');
        },
        onOpen: function () {
            var node = $("#staff").treegrid("getSelected");
            if (node) {
                $("#deptName").combobox('setValue', node.id);
                $("#deptName").combobox('setText', node.deptName);
            }
        }
    });

    //添加人员按钮
    $("#addBtn").on('click', function () {
        // $("#hiddenDiv")[0].style.display = '';     //显示检索框
        $("#hiddenDiv0")[0].style.display = '';     //显示检索框
        $("#hiddenDiv1")[0].style.display = '';     //显示检索框
        $("#password").validatebox({'disabled': false});
        var node = $("#staff").treegrid("getSelected");
        if (node) {
            $("#addStaff").window('open');
            $("#role").combobox('clear');
            $("#id").val("");
            $("#selectCardNo").val("");
            $("#res-cardNo").html("");
            $("#res-nickName").html("");
            $("#res-phoneNum").html("");
            $("#res-password").html("");
            $("#res-email").html("");
            $("#res-name").html("");
            $("#res-title").html("");
        } else {
            $.messager.alert("系统提示", "请先选择科室信息");
        }
    });

    //修改人员按钮
    $("#editBtn").on('click', function () {
        // $("#hiddenDiv")[0].style.display = 'none';     //隐藏检索框
        $("#hiddenDiv0")[0].style.display = 'none';     //隐藏密码框
        $("#hiddenDiv1")[0].style.display = 'none';     //隐藏确认密码框
        $("#res-cardNo").html("");
        $("#res-nickName").html("");
        $("#res-phoneNum").html("");
        $("#res-password").html("");
        $("#res-email").html("");
        $("#res-name").html("");
        $("#res-title").html("");
        var node = $("#staffGrid").datagrid("getSelected");
        if (node) {
            $("#addStaff").window('open');
            $("#password").validatebox({'disabled': true});
            $("#selectCardNo").val("");
            $("#cardNo").val(node.cardNo);
            $("#phoneNum").val(node.phoneNum);
            $("#name").val(node.name);
            $("#email").val(node.email);
            $("#nickName").val(node.nickName);
            $("#title").combogrid('setValue', node.title);
            $("#sex").combobox('setValue', node.sex);
            $("#nation").combogrid('setValue', node.nation);
            $("#id").val(node.id);
            var staffId = node.staffId;
            var role = [];
            $.get("/service/orgStaff/findRole?staffId=" + staffId, function (data) {
                if (data != null) {
                    for (var i = 0; i < data.length; i++) {
                        role.push(data[i].id);
                    }
                    $("#role").combobox('setValues', role);
                }
            });
        } else {
            $.messager.alert("系统提示", "请选择要修改的行");
        }
    });

    //取消添加人员维护
    $("#cancelBtn").on('click', function () {
        $("#staffForm").form('reset');
        $("#addStaff").window('close');
    });

    //dialog的回车键检索
    $("#cardNo").on('keypress', function () {
        var cardNo = $("#cardNo").val();
        if (event.keyCode == 13) {
            //身份证最后一位如果用户输入小写x直接转换为大写X
            if (cardNo.length == 18 && cardNo.substring(17) == 'x') {
                cardNo = cardNo.toUpperCase();
                $("#cardNo").val(cardNo);
            }
            jQuery.ajax({
                'type': 'GET',
                'url': basePath + "/orgStaff/findInfoByCardNo?cardNo=" + cardNo,
                'contentType': 'application/json',
                'dataType': 'json',
                'success': function (data) {
                    if (data != "") {
                        // $("#cardNo").val(data.cardNo);
                        $("#phoneNum").val(data.phoneNum);
                        $("#name").val(data.name);
                        $("#email").val(data.email);
                        $("#nickName").val(data.nickName);
                        $("#sex").combobox('setValue', data.sex);
                        $("#nation").combogrid('setValue', data.nation);
                        $("#id").val(data.id);
                        $.get("/service/orgStaff/findTitleByPersionId?persionId=" + data.id + "&orgId=" + orgId, function (data) {

                            if (data != null) {
                                $("#title").combogrid('setValue', data.title);
                                $("#staffId").val(data.id);
                            }
                            var staffId = $("#staffId").val();
                            var role = [];
                            $.get("/service/orgStaff/findRole?staffId=" + staffId, function (data) {
                                if (data != null) {
                                    for (var i = 0; i < data.length; i++) {
                                        role.push(data[i].id);
                                    }
                                    $("#role").combobox('setValues', role);
                                }
                            });
                        });
                        $.get("/service/orgStaff/findPasswordByPersionId?persionId=" + data.id, function (data) {
                            if (data != null) {
                                $("#password").val(data.password);
                            }
                        });
                        $("#hiddenDiv0")[0].style.display = 'none';     //隐藏密码框
                        $("#hiddenDiv1")[0].style.display = 'none'; //隐藏确认密码框

                    }
                },
                'error': function (data) {
                    $.messager.alert("系统提示", "此用户没有注册，请添加人员");
                }
            });
        }
    });


    //组织机构人员保存
    $("#saveBtn").on('click', function () {
        var flag = false
        $('.fitem  span').each(function () {
            if ($(this).css('color') == 'rgb(255, 0, 0)' && $.trim($(this).html()) != '*') {
                flag = true
            }
        })
        if (flag) return
        var orgStaffVo = {};
        orgStaffVo.id = $("#id").val();
        orgStaffVo.selectCardNo = $("#selectCardNo").val();
        orgStaffVo.sex = $("#sex").combobox('getValue');
        orgStaffVo.name = $("#name").val();
        orgStaffVo.title = $("#title").combogrid('getValue');
        orgStaffVo.cardNo = $("#cardNo").val();
        orgStaffVo.phoneNum = $("#phoneNum").val();
        orgStaffVo.email = $("#email").val();
        var name = $("#name").val();
        orgStaffVo.inputCode = makePy(name)[0];
        orgStaffVo.nickName = $("#nickName").val();
        orgStaffVo.deptId = $("#deptName").combobox('getValue');
        var array = [];
        array = $('#role').combobox('getValues');
        if (array == "") {
            orgStaffVo.role = null;
        }
        else {
            orgStaffVo.role = array;
        }
        if ($("#password").val() == null) {

        } else {
            orgStaffVo.password = $("#password").val();
        }
        // orgStaffVo.orgId = parent.config.org_id;
        orgStaffVo.orgId = orgId;
        orgStaffVo.nation = $("#nation").combogrid('getValue');
        if (orgStaffVo.cardNo != "" && orgStaffVo.email != "" && orgStaffVo.nickName != "" && orgStaffVo.phoneNum != "") {
            $.postJSON(basePath + "/orgStaff/save", JSON.stringify(orgStaffVo), function (data) {
                if (data.data == "success") {
                    $("#addStaff").window('close');
                    $.messager.alert('系统提示', '人员变动成功');
                    $("#staffGrid").datagrid('reload');
                    $("#staffForm").form('reset');
                }
            }, function (data) {
                $.messager.alert('系统提示', '保存失败', 'info');
            });
        }
        else {
            $.messager.alert('系统提示', '保存失败,邮箱、昵称、身份证号、手机号不能为空', 'info');
        }

    });


    //删除
    $("#removeBtn").on('click', function () {
        var row = $("#staffGrid").datagrid('getSelected');
        if (!row) {
            $.messager.alert("系统提示", '请选择要删除的项目', 'error');
            return;
        }
        $.messager.confirm('系统提示', '确定要进行删除操作吗', function (r) {
            if (r) {
                $.postJSON(basePath + "/orgStaff/del", row.id, function (data) {
                    if (data.data == "success") {
                        $.messager.alert('系统提示', '删除成功', 'info');
                        $("#staffGrid").datagrid('reload');
                    }

                });
            }
        });

    })


    var registerVo = {};
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
        }
    });

    //文本框获取焦点的时候，显示
    $("#title").focus(function () {
        $("#res-title").text("*请输入职称");
        $("#res-title").css("color", "gray");
    });
    //判断用户名不能为空
    $("#title").blur(function () {
        if ($('#title').val() == "") {
            $("#res-title").text("*职称不能为空");
            $("#res-title").css("color", "red");
            return false;
        }
    });


    //文本框获取焦点的时候，显示
    $("#cardNo").focus(function () {
        if($("#cardNo").val()==''){
            $("#cardNo").attr('placeholder','');
            $("#res-cardNo").text("*请输入正确的身份证号");
            $("#res-cardNo").css("color", "gray");
        }
    })
    $(".fitem").each(function(){
        $('.validatebox-text', this).keydown(function () {
            var liDesId = $(this).attr('id');
            if($("#res-"+liDesId)){
                $("#res-"+liDesId).text("");
            }
        })
    });
    //$("#cardNo").keydown(function(){
    //    $("#res-cardNo").text("");
    //})
    //检验身份证号是否已存在
    $("#cardNo").blur(function () {
        registerVo.cardNo = $("#cardNo").val();
        $("#cardNo").attr('placeholder','');
        if ($("#cardNo").val() == "") {
            $("#res-cardNo").text("*身份证号不能为空");
            $("#res-cardNo").css("color", "red");
            return false;
        }
        var cardNo = $("#cardNo").val();
        //身份证最后一位如果用户输入小写x直接转换为大写X
        if (cardNo.length == 18 && cardNo.substring(17) == 'x') {
            cardNo = cardNo.toUpperCase();
            $("#cardNo").val(cardNo);
        }

        //身份证正则表达式(18位)
        //var isIdCard2 = /^[1-9]\d{5}(19\d{2}|[2-9]\d{3})((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])(\d{4}|\d{3}X)$/i;
        var isIdCard2 = /^[1-9]\d{5}(?:(?!0000)[0-9]{4}(?:(?:0[1-9]|1[0-2])(?:0[1-9]|1[0-9]|2[0-8])|(?:0[13-9]|1[0-2])(?:29|30)|(?:0[13578]|1[02])31)|(?:[0-9]{2}(?:0[48]|[2468][048]|[13579][26])|(?:0[48]|[2468][048]|[13579][26])00)0229)(\d{4}|\d{3}X)$/;

        if (!isIdCard2.test(cardNo)) {
            $("#res-cardNo").text("*身份证号不合法");
            $("#res-cardNo").css("color", "red");
            return false;
        }

        var persion_id = $("#id").val();
        jQuery.ajax({
            'type': 'GET',
            'url': "/service/register/getCard?cardNo=" + cardNo,
            'success': function (data) {
                if (data && data != '' && typeof(data) != 'undefined') {
                    if (data.id != persion_id) {
                        $("#res-cardNo").text("*此人已存在");
                        $("#res-cardNo").css("color", "red");
                        return false;
                    }
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
        $("#res-nickName").text("*6-12位字符");
        $("#res-nickName").css("color", "gray");
    });

    //校验用户名是否已经存在
    $("#nickName").blur(function () {
        var nickName = $("#nickName").val();
        var persion_id = $("#id").val();
        var name = $("#nickName").val();
        if ($("#nickName").val() == "") {
            $("#res-nickName").text("*昵称(用户名)不能为空");
            $("#res-nickName").css("color", "red");
            return false;
        }
        if (name.length < 6) {
            $("#res-nickName").text("*请输入正确长度的字符");
            $("#res-nickName").css("color", "red");
            return false;
        }
        if (name.length > 12) {
            $("#res-nickName").text("*请输入正确长度的字符");
            $("#res-nickName").css("color", "red");
            return false;
        }
        var usern = /^[a-zA-Z0-9_]{1,}$/;
        var username = $("#nickName").val();
        if (!username.match(usern)) {
            $("#res-nickName").text("数字字母和下划线组成");
            $("#res-nickName").css("color", "red");
            return false;
        }
        jQuery.ajax({
            'type': 'GET',
            'url': "/service/register/getNick?nickName=" + nickName,
            'contentType': 'application/json',
            'success': function (data) {
                if (data && data.id != persion_id) {
                    $("#res-nickName").text("*用户名已经存在");
                    $("#res-nickName").css("color", "red");
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
        $("#res-email").text("*请输入正确的邮箱");
        $("#res-email").css("color", "gray");
    });
    //校验邮箱是否合法，是否已被注册
    $("#email").blur(function () {
        var email = $("#email").val();
        var persion_id = $("#id").val();
        if ($("#email").val() == "") {
            $("#res-email").text("*邮箱不能为空");
            $("#res-email").css("color", "red");
            return false;
        }
        if (!$("#email").val().match(/^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/)) {
            $("#res-email").text("*邮箱格式不正确");
            $("#res-email").css("color", "red");
            return false;
        }
        if ($("#email").val().length > 50) {
            $("#res-email").css("color", "red");
            $("#res-email").text("*邮箱长度不合法,请重新填写");
            return false;
        }
        jQuery.ajax({
            'type': 'GET',
            'url': "/service/register/getEmail?email=" + email,
            'dataType': 'json',
            'success': function (data) {
                if (data && data != '' && typeof(data) != 'undefined') {
                    if (data.id != persion_id) {
                        $("#res-email").text("*邮箱已注册");
                        $("#res-email").css("color", "red");
                        return false;
                    }
                }
            },
            'error': function (data) {
                console.log("失败");
            }
        });

        return true;
    });


    $("#phoneNum").focus(function () {
        $("#res-phoneNum").text("*请输入正确的手机号");
        $("#res-phoneNum").css("color", "gray");
    });
    //文本框获取焦点的时候，显示
    $("#phoneNum").blur(function () {
        var phone = $("#phoneNum").val();
        var persion_id = $("#id").val();
        if ($("#phoneNum").val() == "") {
            $("#res-phoneNum").text("*手机号不能为空");
            $("#res-phoneNum").css("color", "red");
            return false;
        }
        if (phone.length != 11) {
            $("#res-phoneNum").text("*请输入有效的手机号");
            $("#res-phoneNum").css("color", "red");
            return false;
        }
        var myreg = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1})|(17[0-9]{1})|(14[0-9]{1}))+\d{8})$/;
        if (!myreg.test(phone)) {
            $("#res-phoneNum").text('*请输入有效的手机号码');
            $("#res-phoneNum").css("color", "red");
            return false;
        }
        jQuery.ajax({
            'type': 'GET',
            'url': "/service/register/getPhone?phoneNum=" + phone,
            'success': function (data) {

                if (data && data.id != persion_id) {
                    $("#res-phoneNum").text("*手机号已经注册");
                    $("#res-phoneNum").css("color", "red");
                    return false;
                }
            },
            'error': function (data) {
                console.log("失败");
            }
        });
        return true;
    });


    //密码输入框
    $("#password").focus(function () {
        $("#res-password").text("*请输入密码");
        $("#res-password").css("color", "gray");
    });
    $("#password").blur(function () {
        var password = $("#password").val();
        if (password.length == 0) {
            $("#res-password").text("*密码不能为空");
            $("#res-password").css("color", "red");
        }
    });

    //确认密码
    $("#confirm-password").focus(function () {
        $("#res-confirm-password").text("*请输入确认密码");
        $("#res-confirm-password ").css("color", "gray");
    });
    $("#confirm-password").blur(function () {
        var password = $("#password").val();
        var confirmPassword = $("#confirm-password").val();
        if (confirmPassword.length == 0) {
            $("#res-confirm-password").text("*确认密码不能为空");
            $("#res-confirm-password").css("color", "red");
        } else {
            if (password.length != 0) {
                if (password != confirmPassword) {
                    $("#res-confirm-password").text("*确认密码与密码不一致");
                    $("#res-confirm-password").css("color", "red");
                }
            }
        }
    });

    //导出
    $("#exportXls").on('click', function () {
        var node = $("#staff").treegrid("getSelected");
        if (!node) {
            location.href = "/service/orgStaff/findListByExcel?orgId=" + orgId;
        } else {
            location.href = "/service/orgStaff/findListByExcel?orgId=" + orgId + "&deptId=" + node.id
        }
    });

    //下载导入模板
    $("#exportStaffXlsTemplate").on('click', function () {

        location.href = basePath + "/orgStaff/getStaffXlsTemplate?orgId=" + orgId;
    });

    $("#importForm").dialog({
        width: 400,
        height: 200,
        title: '用户导入',
        closed: true,
        modal: true,
        footer: '#addImport'
    })
    //导入
    $("#importStaffXls").on('click', function () {
        $("#importForm").dialog("open").dialog("setTitle", "导入Execl");
    });

    //文件上传
    $('#file_upload').uploadify({
        'swf': '/static/uploadify/uploadify.swf',
        'uploader': basePath + '/orgStaff/up-xls',
        buttonText: '选择文件',
        method: 'POST',
        multi: false,
        fileObjName: 'file',
        auto: false,
        fileTypeExts: '*.xls',
        onUploadSuccess: function (file, res, response) {
            res = eval('(' + res + ')')

             $.messager.alert('系统提示', res.data, 'info');
            $("#importForm").dialog("close")
        },
        onUploadError: function (file, errorCode, errorMsg, errorString) {
            alert('The file ' + file.name + ' could not be uploaded: ' + errorString);
        },
        onComplete: function () {

        }
    });
    $("#importBtn").on('click', function () {
        $('#file_upload').uploadify('upload');
    })

});




