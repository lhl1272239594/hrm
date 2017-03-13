/**
 * Created by Administrator on 2016/5/7.
 */
$(function () {
    var orgId = config.org_Id;
    var thisItemClass = undefined;  //当前选中的项目类别
    var itemClassList = []; //项目类别
    $.get(basePath + '/dict/label-value-list?type=' + 'BILL_ITEM_CLASS_DICT', function (data) {
        itemClassList = data;
    });

    //定义三个价格的数字框
    $('#price').numberbox({
        min:0,
        max:9999999.99,
        precision:2
    });
    $('#preferPrice').numberbox({
        min:0,
        max:9999999.99,
        precision:2
    });
    $('#foreignerPrice').numberbox({
        min:0,
        max:9999999.99,
        precision:2
    });

    //修改基本价格后给其他价格自动赋值
    $("#price").numberbox({
        onChange: function (n,o) {
            $("#preferPrice").numberbox('setValue',n);
            $("#foreignerPrice").numberbox('setValue',n);
        }
    });


    initStartDate();    //初始化启用日期

    init_clinic_data();
    //类别
    $("#item_class").combobox({
        valueField: 'value',
        textField: 'label',
        width: 150,
        method: 'GET',
        url: basePath + '/dict/label-value-list?type=' + 'BILL_ITEM_CLASS_DICT',
        editable: false,
        onSelect: function (data) {
            if (data.label == '西药' || data.label == '中药') {
                $.messager.alert('系统提示', '药品类价表是在药品管理系统中维护,请选择其他类别!', 'info');
                $('#item_class').combobox('setValue', '');
                $("#clinic_item").datagrid('loadData', []);
                reset();
                $("#dt").textbox("setValue", '');
                initStartDate();
                $("#aa").combobox('setValue', '');
                return;
            } else {
                $("#clinic_item").datagrid('loadData', []);
                var itemClass = $("#item_class").combobox('getValue');
                $.get(basePath + '/price/find-by-item-class?itemClass=' + itemClass + '&orgId=' + orgId, function (data) {
                    reset();
                    $("#aa").combobox('setValue', '');  //清空右侧的类别
                    $("#clinic_item").datagrid('loadData', data);
                    $('#code_gps').val('');
                });
            }
        }
    });
    //项目类别
    $("#aa").combobox({
        valueField: 'value',
        textField: 'label',
        method: 'GET',
        url: basePath + '/dict/label-value-list?type=' + 'BILL_ITEM_CLASS_DICT',
        editable: false,
        onSelect: function (data) {   //选中生成项目代码
            addPrice(data.value)
        }
    });

    function addPrice(itemClass) {
        if (itemClass == 'A' || itemClass == 'B') {
            $.messager.alert('系统提示', '药品类价表请在药品管理系统中维护!', 'info');
            $('#aa').combobox('setValue', '');
            return;
        } else {
            var name = $('#itemName').val();
            if ($("#saveDict").linkbutton("options").disabled && name != '') {
                $("#saveDict").linkbutton('enable');
                reset();
                initStartDate();
            }
            $.ajax({
                'type': 'GET',
                'url': basePath + '/price/code/' + itemClass,
                'success': function (data) {
                    $("#itemCode").textbox('setValue', data.data);
                }
            });
        }
    }

    //计价单位
    $('#units').combogrid({
        panelWidth: 260,
        idField: 'value',
        textField: 'label',
        loadMsg: '数据正在加载',
        'url': basePath + '/dict/find-list-by-type?type=' + 'dose_unit',
        mode: 'remote',
        method: 'GET',
        fitColumns: true,
        columns: [[
            {field: 'value', title: '代码', width: 100, align: 'center'},
            {field: 'label', title: '名称', width: 100, align: 'center'},
            {field: 'inputCode', title: '拼音', width: 80, align: 'center'}
        ]]
    });

    //会计科目
    $('#bb').combobox({
        url: basePath + '/orgSysDict/findUnits?type=TALLY_SUBJECT_DICT&orgId=' + orgId,
        valueField: 'value',
        textField: 'label',
        method: 'GET',
        editable: false
    });
    //门诊收据
    $('#cc').combobox({
        url: basePath + '/orgSysDict/findUnits?type=OUTP_RCPT_FEE_DICT&orgId=' + orgId,
        valueField: 'value',
        textField: 'label',
        method: 'GET',
        editable: false
    });
    //住院收据
    $('#dd').combobox({
        url: basePath + '/orgSysDict/findUnits?type=INP_RCPT_FEE_DICT&orgId=' + orgId,
        valueField: 'value',
        textField: 'label',
        method: 'GET',
        editable: false
    });
    //病案首页
    $('#ee').combobox({
        url: basePath + '/dict/label-value-list?type=' + 'MR_FEE_CLASS_DICT',
        valueField: 'value',
        textField: 'label',
        method: 'GET',
        editable: false
    });
    //核算项目
    $('#ff').combobox({
        url: basePath + '/orgSysDict/findUnits?type=RECK_ITEM_CLASS_DICT&orgId=' + orgId,
        valueField: 'value',
        textField: 'label',
        method: 'GET',
        editable: false
    });
    //启用日期
    $('#dt').datetimebox({
        showSeconds: false
    });

    //执行科室
    $("#performedBy").combogrid({
        panelWidth: 300,
        idField: 'deptCode',
        textField: 'deptName',
        mode: 'remote',
        method: 'GET',
        url: basePath + '/dept-dict/findListWithFilter?orgId=' + orgId,
        columns: [[
            {field: 'deptCode', title: '科室编码', width: 100},
            {field: 'deptName', title: '科室名称', width: 100},
            {field: 'inputCode', title: '拼音码', width: 100}
        ]], filter: function (q, row) {

            return row.inputCode.indexOf(q) == 0;
        }
    });
    //是否自费   1,自费；0,非自费
    $("#feeTypeMask").on("click", function () {
        if ($("#feeTypeMask").prop("checked") == true) {
            $("#feeTypeMask").val(1);
        } else {
            $("#feeTypeMask").val(0);
        }
    });

    //是否生成诊疗项目
    $("#clinicDict").on("click", function () {
        if ($("#clinicDict").prop("checked") == true) {
            $("#clinicDict").val(1);
        } else {
            $("#clinicDict").val(0);
        }
    });
    //刷新按钮
    $('#refreshData').on('click', function () {
        $("#aa").combobox('setValue', '');  //类别
        reset();
        var itemClass = $('#item_class').combobox('getValue');
        $('#aa').combobox('setValue', itemClass);
        $.ajax({
            'type': 'GET',
            'url': basePath + '/price/code/' + itemClass,
            'success': function (data) {
                $("#itemCode").textbox('setValue', data.data);
            }
        });
        initStartDate();
        $("#saveDict").linkbutton('enable');
    });

    $('#addBtn').click(function () {
        addPrice($('#aa').combobox('getValue'))
    })
    //保存当前标签页
    $("#saveDict").on("click", function () {
        if ($("#saveDict").linkbutton("options").disabled) {
            $.messager.alert("系统提示", "该项目已经存在，请不要重复提交", 'info');
            return;
        }
        var priceDictListVo = {};
        priceDictListVo.itemClass = $("#aa").combobox('getValue');  //类别
        priceDictListVo.itemName = $("#itemName").val();            //名称
        priceDictListVo.itemCode = $("#itemCode").val();            //代码
        priceDictListVo.itemSpec = $("#itemSpec").val();            //规格
        priceDictListVo.units = $("#units").combobox('getValue');    //计价单位
        priceDictListVo.price = $("#price").numberbox('getValue');                  //基本价格
        priceDictListVo.preferPrice = $("#preferPrice").numberbox('getValue');    //优惠价格
        priceDictListVo.foreignerPrice = $("#foreignerPrice").numberbox('getValue');    //外宾价格
        priceDictListVo.startDate = $("#dt").datetimebox('getValue');    //启用日期
        priceDictListVo.performedBy = $("#performedBy").combogrid('getValue');  //执行科室
        priceDictListVo.feeTypeMask = $("#feeTypeMask").val();   //是否自费
        priceDictListVo.classOnInpRcpt = $("#dd").combobox('getValue');     //住院收据
        priceDictListVo.classOnOutpRcpt = $("#cc").combobox('getValue');     //门诊收据
        priceDictListVo.classOnReckoning = $("#ff").combobox('getValue');     //核算科目
        priceDictListVo.subjCode = $("#bb").combobox('getValue');           //会计科目
        priceDictListVo.classOnMr = $("#ee").combobox('getValue');      //病案首页
        priceDictListVo.memo = $("#memo").val();            //备注信息
        priceDictListVo.inputCode = $("#inputCode").val();      //拼音码
        priceDictListVo.materialCode = $("#materialCode").val();    //物价码
        priceDictListVo.clinicDict = $("#clinicDict").val();        //诊疗标识
        priceDictListVo.orgId = orgId;      //所属组织机构

        if (priceDictListVo.itemClass == null || priceDictListVo.itemClass == '') {
            $.messager.alert('系统提示', '请选择项目类别!', 'info');
            return;
        }
        if (priceDictListVo.itemCode == null || priceDictListVo.itemCode == '') {
            $.messager.alert('系统提示', '请生成项目代码!', 'info');
            return;
        }
        if (priceDictListVo.itemSpec == null || priceDictListVo.itemSpec == '') {
            $.messager.alert('系统提示', '项目规格不能为空!', 'info');
            return;
        }
        if (priceDictListVo.units == null || priceDictListVo.units == '') {
            $.messager.alert('系统提示', '计价单位不能为空!', 'info');
            return;
        }
        console.log(priceDictListVo.inputCode);
        if (priceDictListVo.itemName == null || priceDictListVo.itemName == '') {
            $.messager.alert('系统提示', '请输入项目名称!', 'info');
            return;
        }
        if (priceDictListVo.startDate == null || priceDictListVo.startDate == '') {
            $.messager.alert('系统提示', '请设定启用日期!', 'info');
            return;
        }

        if (priceDictListVo) {
            $.postJSON(basePath + '/price/save', JSON.stringify(priceDictListVo), function (data) {
                if (data.data == 'success') {
                    $.messager.alert("提示消息", "保存成功", "success");
                    $("#performedBy").combogrid('grid').datagrid('load', {orgId: orgId});
                    reset();
                    initStartDate();
                    $("#aa").combobox('setValue', priceDictListVo.itemClass);
                    $.ajax({
                        'type': 'GET',
                        'url': basePath + '/price/code/' + priceDictListVo.itemClass,
                        'success': function (data) {
                            $("#itemCode").textbox('setValue', data.data);
                        }
                    });
                    $("#item_class").combobox('setValue', priceDictListVo.itemClass);
                    $.get(basePath + '/price/find-by-item-class?itemClass=' + priceDictListVo.itemClass + '&orgId=' + orgId, function (data) {
                        $("#clinic_item").datagrid('loadData', data);
                    });
                } else {
                    $.messager.alert('提示消息', data.code, "error");
                }
            }, function (data) {
                $.messager.alert('提示', "保存失败", "error");
            });
        }
    });


    //查询
    $("#seaBtn").on('click', function () {
        var area = $("#area").combotree('getValue');
        $.get(basePath + '/templateMaster/findList?tableName=PRICE_LIST_TEMPLATE&area=' + area, function (data) {
            $("#priceListemplate").datagrid('loadData', data);
        });
    })

    $('#area').combotree({
        method: 'get',
        animate: true,
        data: getAreaTree(),
        onSelect: function (row) {
            var area = row.value;
            $.get(basePath + '/templateMaster/findList?tableName=PRICE_LIST_TEMPLATE&area=' + area, function (data) {
                $("#priceListemplate").datagrid('loadData', data);
            });
        }
    })

    //使用模板数据
    $('#importData').click(function () {
        $("#area").combotree('setValue', '');
        $.get(basePath + '/price/sum', {orgId: orgId}, function (res) {
            if (res) {
                $.messager.alert('提示', '已有数据，不能使用模板数据！', 'error')
                return
            }else{
                $('#win').dialog('open');
            }
        })
    })

    $('#priceListemplate').datagrid({
        width: 440,
        height: 300,
        //fitColumns: true,
        singleSelect: true,
        method: 'get',
        url: basePath + "/templateMaster/findList?tableName=PRICE_LIST_TEMPLATE",
        idField: 'id',
        singleSelect: true,//是否单选
        rownumbers: true,//行号
        columns: [[{
            title: "id",
            field: "id",
            hidden: true
        }, {
            title: "区域",
            field: 'area',
            width: '0',
            align: 'center',
            hidden: 'true'
        }
            , {
                title: "区域名称",
                field: 'areaName',
                width: '130',
                align: 'center'
            },
            {
                title: "模板名称",
                field: 'templateName',
                width: '130',
                align: 'center'
            },
            {
                title: "模板描述",
                field: 'details',
                width: '140',
                align: 'center'
            }
        ]],
        onDblClickRow: function (index, row) {
                    $.get(basePath + '/priceListTemplate/findList', {masterId: row.id}, function (res) {
                        if (res=="") {
                            $.messager.alert('提示', '该模板中没有可导入的数据,请维护数据后再导入！', 'error')
                            return
                        } else {
                            $.messager.confirm("系统提示", "确定要导入模板数据吗？", function (r) {
                                if (r) {
                                    $.messager.progress({
                                        text: '',
                                        msg: '导入数据中......',
                                        interval: 100
                                    });
                                    $.get(basePath + '/priceListTemplate/useTemplate', {
                                        id: row.id,
                                        orgId: orgId
                                    }, function (res) {
                                        $.messager.progress('close');
                                        if (res == '1') {
                                            $.messager.alert('导入', '导入完成', 'info')
                                            $("#win").dialog('close')
                                            var url=basePath + '/price/find-by-item-class?orgId='+orgId
                                            $("#clinic_item").datagrid('reload', url);

                                        } else {
                                            $.messager.alert('导入', '导入失败', 'error')
                                        }
                                    })
                                }
                            });
                        }
                    })
        }

    });

    //清空输入框
    var reset = function () {
        //$("#aa").combobox('setValue','');  //类别
        $("#itemName").val('');            //名称
        $("#itemCode").textbox('setValue', '');    //代码
        $("#itemSpec").textbox('setValue', '');         //规格
        $("#units").combobox('setValue', '');           //计价单位
        $("#price").numberbox('setValue',0);              //基本价格
        $("#preferPrice").numberbox('setValue',0);    //优惠价格
        $("#foreignerPrice").numberbox('setValue',0);     //外宾价格
        $("#dt").datetimebox('setValue', '');    //启用日期
        $("#performedBy").combogrid('setValue', '');  //执行科室
        $("#feeTypeMask").prop('checked', false);   //是否自费
        $("#dd").combobox('setValue', '');     //住院收据
        $("#cc").combobox('setValue', '');     //门诊收据
        $("#ff").combobox('setValue', '');     //核算科目
        $("#bb").combobox('setValue', '');           //会计科目
        $("#ee").combobox('setValue', '');      //病案首页
        $("#memo").val('');            //备注信息
        $("#inputCode").textbox('setValue', '');      //拼音码
        $("#materialCode").textbox('setValue', '');    //物价码
        $("#clinicDict").prop('checked', true);        //诊疗标识
    }

    function init_clinic_data() {
        $("#clinic_item").datagrid({
            title: "价表管理列表",
            //url: basePath +'/price/find',
            method: 'get',
            idField: 'id',
            toolbar: '#tb',
            fit: true,
            pagination: false, //显示分页
            singleSelect: true,//是否单选
            pageSize: 10, //页大小
            pageList: [10, 15, 20, 25], //页大小下拉选项此项各value是pageSize的倍数
            fitColumns: true, //列自适应宽度
            columns: [[//显示的列
                {field: 'itemCode', title: '项目代码', width: '15%', align: 'center'},
                {field: 'itemName', title: '项目名称', width: '25%', align: 'center'},
                {field: 'itemSpec', title: '项目规格', width: '15%', align: 'center'},
                {field: 'materialCode', title: '物价码', width: '15%', align: 'center'},
                {
                    field: 'itemClass',
                    title: '项目类别',
                    width: '15%',
                    align: 'center',
                    formatter: function (value, row, index) {
                        var itemClass = value;
                        $.each(itemClassList, function (index, item) {
                            if (item.value == value) {
                                itemClass = item.label;
                            }
                        });
                        return itemClass;
                    }
                },
                {field: 'inputCode', title: '拼音码', width: '15%', align: 'center'}
            ]],
            onDblClickRow: function (index, row) {
                $("#aa").combobox('setValue', row.itemClass);
                $("#itemCode").textbox('setValue', row.itemCode);
                $("#materialCode").textbox('setValue', row.materialCode);
                $("#itemSpec").textbox('setValue', row.itemSpec);
                $("#units").combobox('setValue', row.units);
                $("#itemName").val(row.itemName);
                $("#inputCode").textbox('setValue', row.inputCode);
                $("#price").numberbox('setValue',row.price);
                $("#preferPrice").numberbox('setValue',row.preferPrice);
                $("#foreignerPrice").numberbox('setValue',row.foreignerPrice);
                $("#dt").textbox('setValue', row.startDate);
                $("#performedBy").combogrid('setValue', row.performedBy);
                $("#memo").val(row.memo);
                $("#bb").combobox('setValue', row.subjCode);
                $("#cc").combobox('setValue', row.classOnOutpRcpt);
                $("#dd").combobox('setValue', row.classOnInpRcpt);
                $("#ee").combobox('setValue', row.classOnMr);
                $("#ff").combobox('setValue', row.classOnReckoning);

                //禁用保存按钮
                $("#saveDict").linkbutton('disable');
            }
        });
    }



    //下载导入模板
    $("#exportPriceListXlsTemplate").on('click', function () {

        location.href = basePath + "/price/getPriceListXlsTemplate?orgId=" + orgId;
    });



    $("#importForm").dialog({
        width: 500,
        height: 300,
        title: '用户导入',
        closed: true,
        modal: true,
        footer: '#addImport'
    })
    //导入
    $("#importPriceListXls").on('click', function () {
        $("#importForm").dialog("open").dialog("setTitle", "导入Execl");
    });

    //文件上传
    $('#file_upload').uploadify({
        'swf': '/static/uploadify/uploadify.swf',
        'uploader': basePath + '/price/up-xls',
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

    $("#exitBtn").on('click', function () {
        $("#importForm").dialog("close")
    })


});

function ShowInfo() {
    var oDiv = $("#itemName").val();    //获取项目名称的值
    if (oDiv != '') {
        $.ajax({
            'type': 'GET',
            'url': basePath + '/price/abb/' + oDiv,
            'success': function (data) {
                $("#inputCode").textbox('setValue', data.code);
            }
        });
    } else {
        $("#inputCode").textbox('setValue', "");
    }
}
//初始化启用日期
function initStartDate() {
    var date = new Date();
    var year = date.getFullYear();//当前年份
    var month = date.getMonth();//当前月份
    var data = date.getDate();//天
    var hours = date.getHours();//小时
    var minute = date.getMinutes();//分
    var second = date.getSeconds();//秒
    var time = year + "-" + fnW((month + 1)) + "-" + fnW(data) + " " + fnW(hours) + ":" + fnW(minute) + ":" + fnW(second);

    //补位 当某个字段不是两位数时补0
    function fnW(str) {
        var num;
        str > 10 ? num = str : num = "0" + str;
        return num;
    }

    $("#dt").textbox("setValue", time);
}
//根据用户输入的基本价格给优惠价格和外宾价格赋默认值
function addPrice() {
    var price = $("#price").numberbox('getValue');
    $("#preferPrice").numberbox('setValue',price);
    $("#foreignerPrice").numberbox('setValue',price);
}



//根据拼音码定位加载数据
function load_data(value) {
    //var value = $("#code_gps").val();
    var itemClass = $('#item_class').combobox('getValue');
    value = value.toLocaleLowerCase();
    if (value && value != "") {
        $.ajax({
            'type': 'GET',
            'url': basePath + '/price/get-by-inputCode?q=' + value + '&orgId=' + config.org_Id + '&itemClass=' + itemClass,
            'success': function (data) {
                $("#clinic_item").datagrid('loadData', data);
                $("#clinic_item").datagrid('selectRow', 0);     //定位到指定行
            }
        });
    } else {
        $.get('/service/price/find-by-item-class?itemClass=' + itemClass + '&orgId=' + config.org_Id, function (data) {
            $("#clinic_item").datagrid('loadData', data);
        });
    }

}


/*function CheckCode(t) {
 var Error = "";
 var re = /^[\u4e00-\u9fa5a-z]+$/gi;
 if (!re.test(t)) {
 Error = "名称中含有特殊字符!";
 }
 return Error;
 }*/


