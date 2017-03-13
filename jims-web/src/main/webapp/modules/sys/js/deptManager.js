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

        //校验数据是否合法
        $.extend($.fn.validatebox.defaults.rules, {
            IsExisted: {
                validator: function (value, param) {
                    var rows = $('#dg').datagrid('getRows')
                    var select_index = $('#dg').datagrid('getRowIndex', $('#dg').datagrid('getSelected'))
                    var p;
                    if (param[0] == "propertyType") {
                        p = "propertyValue"
                    } else if (param[0] == "propertyValue") {
                        p = "propertyType"
                    }
                    for (var index = rows.length - 1; index > -1; index--) {
                        if (index != select_index && rows[index][param[0]] == value && rows[index][p] == rows[select_index][p]) {
                            return false
                        }
                    }
                    return true
                },
                message: '同类型的值不能相同'
            }
        });


        var clinicList = [];   //临床属性
        $.get(basePath + '/dict/findListByType?type=DEPT_CLINIC_ATTR_DICT', function (data) {
            clinicList = data;
        });
        var outpList = [];   //门诊住院属性
        $.get(basePath + '/dict/findListByType?type=DEPT_OI_ATTR_DICT', function (data) {
            outpList = data;
        });
        var internalList = [];   //内外科属性
        $.get(basePath + '/dict/findListByType?type=DEPT_IS_ATTR_DICT', function (data) {
            internalList = data;
        });

        var property = [];
        var orgId = parent.config.org_Id;
        //设置列
        $("#tt").treegrid({
            fit: true,

            idField: "id",
            treeField: "deptName",
            toolbar: '#tb',
            fitColumns: true,
            columns: [[{
                title: 'id',
                field: 'id',
                hidden: true
            }, {

                title: '科室名称',
                field: 'deptName',
                width: '300',
                align: 'left'

            }, {
                title: '科室编码',
                field: 'deptCode',
                width: '150',
                align: 'left'

            }, {
                title: '拼音码',
                field: 'inputCode',
                width: '150',
                align: 'center'

            }, {
                title: '临床属性',
                field: 'clinicAttr',
                width: '150',
                formatter: function (value, row, index) {
                    var clinic = value;
                    $.each(clinicList, function (index, item) {
                        if (item.value == value) {
                            clinic = item.label;
                        }
                    });
                    return clinic;
                },
                align: 'center'

            }, {
                title: '门诊住院属性',
                field: 'outpOrInp',
                width: '150',
                formatter: function (value, row, index) {
                    var outp = value;
                    $.each(outpList, function (index, item) {
                        if (item.value == value) {
                            outp = item.label;
                        }
                    });
                    return outp;
                },
                align: 'center'

            }, {
                title: '内外科属性',
                field: 'internalOrSergery',
                width: '150',
                formatter: function (value, row, index) {
                    var internal = value;
                    $.each(internalList, function (index, item) {
                        if (item.value == value) {
                            internal = item.label;
                        }
                    });
                    return internal;
                },
                align: 'center'

            }, {
                title: '其他科室属性',
                field: 'deptPropertityName',
                width: '150',
                align: 'center'

            }
            ]]
        });

        var loadDept = function () {

            var depts = [];
            var treeDepts = [];

            var loadPromise = $.get("/service/dept-dict/list?orgId=" + orgId, function (data) {
                $.each(data, function (index, item) {
                    var obj = {};
                    obj.deptName = item.deptName;
                    obj.id = item.id;
                    obj.deptCode = item.deptCode;
                    obj.inputCode = item.inputCode;
                    obj.deptPropertity = item.deptPropertity;
                    obj.clinicAttr = item.clinicAttr;
                    obj.outpOrInp = item.outpOrInp;
                    obj.internalOrSergery = item.internalOrSergery;
                    obj.deptPropertityName = item.deptPropertityName;
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
                $("#tt").treegrid('loadData', treeDepts);
            })
        }
        loadDept();


        $("#cancelBtn").on('click', function () {
            //清空
            $("#deptPropertity").html("");
            $('#dlg').dialog('close');
        });
        var propertyId;
        var deptPropertitys;


        //给上级科室的下拉列表赋值
        $("#parentId").combogrid({
            delay: 500,
            mode: 'remote',
            url: '/service/dept-dict/findListWithFilter?orgId=' + orgId,
            idField: 'id',
            textField: 'deptName',
            method: 'GET',
            columns: [[
                {field: 'deptCode', title: '科室代码', width: 100, sortable: true},
                {field: 'deptName', title: '科室名称', width: 100, sortable: true},
                {field: 'inputCode', title: '拼音码', width: 100, sortable: true}
            ]]
        });

        //添加临床属性
        $("#clinicAttr").combobox({
            url: basePath + '/dict/findListByType?type=' + 'DEPT_CLINIC_ATTR_DICT',
            idField: 'value',
            textField: 'label',
            method: 'GET'
        });

        //添加门诊住院属性
        $("#outpOrInp").combobox({
            url: basePath + '/dict/findListByType?type=' + 'DEPT_OI_ATTR_DICT',
            idField: 'value',
            textField: 'label',
            method: 'GET'
        });

        //添加内外科属性
        $("#internalOrSergery").combobox({
            url: basePath + '/dict/findListByType?type=' + 'DEPT_IS_ATTR_DICT',
            idField: 'value',
            textField: 'label',
            method: 'GET'
        });


        /**
         * 添加科室信息
         */
        $("#addBtn").on('click', function () {
            $('#parentId').combogrid('disable');

            var node = $("#tt").treegrid("getSelected");
            $("#deptCode").textbox('setValue', "");
            $("#clinicAttr").combobox('setValue', "");
            $("#outpOrInp").combobox('setValue', "");
            $("#internalOrSergery").combobox('setValue', "");

            $("#deptCode").textbox('disable');
            if (!node) {
                //在没有选中任何行时给一级添加同级科室
                $("#parentId").combogrid('setValue', "");
                var rows = $("#tt").treegrid('getRoots');
                if (rows.length > 0) {
                    var c = parseInt(rows[rows.length - 1].deptCode)
                    $("#deptCode").textbox('setValue', c + 1);
                    $("#deptCode").textbox('disable');
                } else {
                    $("#deptCode").textbox('setValue', '10');
                    $("#deptCode").textbox('disable');
                }
            } else {
                $("#parentId").combogrid('setValue', node.parentId);
                //给二级或者三级添加同级科室
                if (node.parentId != null) {
                    var rows = $("#tt").treegrid('find', node.parentId).children;
                    if (rows.length > 0) {
                        var c = parseInt(rows[rows.length - 1].deptCode)
                        $("#deptCode").textbox('setValue', c + 1);
                        $("#deptCode").textbox('disable');
                    }
                } else {
                    //给一级添加同级科室
                    var rows = $("#tt").treegrid('getRoots');
                    if (rows.length > 0) {
                        var c = parseInt(rows[rows.length - 1].deptCode)
                        $("#deptCode").textbox('setValue', c + 1);
                        $("#deptCode").textbox('disable');
                    } else {
                        $("#deptCode").textbox('setValue', '10');
                        $("#deptCode").textbox('disable');
                    }
                }

            }


            $("#id").val("");

            $("#deptName").textbox('setValue', "");
            $("#deptPropertity").html("");
            $("#dlg").dialog("open").dialog("setTitle", "添加科室");
            $("#inputCode").attr('readonly', true);

            $.ajax({
                url: "/service/dept-property/selectProperty?orgId=" + orgId,
                type: 'get',
                dataType: 'json',
                error: function (data) {
                    alert("加载json 文件出错！");
                },
                success: function (data1) {
                    var data2 = eval(data1);
                    deptPropertitys = data2;
                    for (var i = 0; i < data2.length; i++) {
                        deptPropertity = data2[i].propertyType;
                        propertyId = "propertyName" + i;
                        var propertyFitem = "propertyFitem" + i;
                        if (deptPropertity.length == 3) {
                            var a = deptPropertity.substring(0, 1);
                            var b = deptPropertity.substring(1, 2);
                            var c = deptPropertity.substring(2, 3);
                            $("#deptPropertity").append("<div  id='" + propertyFitem + "' style='float: left ;margin-bottom: 10px;'>");
                            $("#" + propertyFitem).append("<label>" + a + " " + b + " " + c + ": </label>");
                            $("#" + propertyFitem).append("<select style='width: 150px;' name='propertyName' id='" + propertyId + "'/>");

                        } else {
                            $("#deptPropertity").append("<div  id='" + propertyFitem + "' style='float: left ;margin-bottom: 10px;'>");

                            $("#" + propertyFitem).append("<label>" + deptPropertity + ": </label>");
                            $("#" + propertyFitem).append("<select style='width: 150px;' name='propertyName' id='" + propertyId + "'/>");
                        }

                        $("#" + propertyId).combobox({
                            'url': '/service/dept-property/selectName/' + deptPropertity + '/' + orgId,
                            valueField: 'propertyValue',
                            textField: 'propertyName'
                        });

                        $("#deptPropertity").append("</div>")
                    }
                }
            });

            //$("#parentId").combobox({
            //    'url': '/service/dept-dict/selectParentByOrgId?orgId=' + orgId,
            //    valueField: 'id',
            //    textField: 'deptName'
            //});

            $("#deptName").textbox({
                onChange: function () {
                    var dept = $("#deptName").val();
                    var inputCode = makePy(dept)[0];
                    $("#inputCode").textbox('setValue', inputCode);
                }
            });
        });


        /**
         * 添加科室信息
         */
        $("#addBtnProperty").on('click', function () {
            $("#dlg_property").dialog("open").dialog("setTitle", "添加科室属性");
        });


        /**
         * 保存科室信息
         */
        $("#saveBtn").on('click', function () {
            //用于存放属性值的数组
            var deptProperty = [];
            //用于存放所有保存的数组
            var deptDictVo = {};
            deptDictVo.id = $("#id").val();
            deptDictVo.deptCode = $("#deptCode").val();
            deptDictVo.deptName = $("#deptName").val();
            deptDictVo.orgId = orgId
            deptDictVo.inputCode = $("#inputCode").val();
            deptDictVo.parentId = $("#parentId").combogrid('getValue');
            deptDictVo.clinicAttr = $("#clinicAttr").combobox('getValue');
            deptDictVo.outpOrInp = $("#outpOrInp").combobox('getValue');
            deptDictVo.internalOrSergery = $("#internalOrSergery").combobox('getValue');
            for (var i = 0; i < deptPropertitys.length; i++) {
                var propertyIds = "propertyName" + i;
                var deptIds = $("#" + propertyIds).combobox('getValue');
                deptProperty.push(deptIds);
            }
            deptDictVo.array = deptProperty;
            if($("#deptName").val()==""){
                $.messager.alert("系统提示", "科室名称不能为空");
                return;
            }

            jQuery.ajax({
                'type': 'POST',
                'url': "/service/dept-dict/add",
                'contentType': 'application/json',
                'data': JSON.stringify(deptDictVo),
                'dataType': 'json',
                'success': function (data) {
                    if (data.data == "success") {
                        $.messager.alert("系统提示", "保存成功", "info", function () {
                            window.location.reload();
                        });
                        loadDept();
                        clearInput();
                        $("#deptPropertity").html("");
                        $("#tt").treegrid("reload");
                        $("#dlg").dialog('close');
                    }
                },
                'error': function (data) {
                    $.messager.alert("系统提示", "保存失败");
                }
            });


        });

        //添加下级科室
        $("#addSubBtn").on('click', function () {
            $("#deptCode").textbox('setValue', "");
            $("#clinicAttr").combobox('setValue', "");
            $("#outpOrInp").combobox('setValue', "");
            $("#internalOrSergery").combobox('setValue', "");
            $("#deptCode").textbox('disable');
            $('#parentId').combogrid('disable');
            var node = $("#tt").treegrid("getSelected");
            if (!node) {
                $.messager.alert("系统提示", "请选择上级科室");
                return;
            }

            //如果parentId为空时，表示给一级科室添加下级科室
            if (node.parentId == null) {
                var rows = $("#tt").treegrid('find', node.id).children;
                if (rows.length > 0) {
                    var deptCode = rows[rows.length - 1].deptCode
                    var c = parseInt(rows[rows.length - 1].deptCode.substr(deptCode.length - 1));
                    var d = deptCode.substr(0, deptCode.length - 1) + (c + 1);
                    $("#deptCode").textbox('setValue', d);
                } else {
                    var rows = $("#tt").treegrid('getSelected');
                    var deptCode = rows.deptCode;
                    deptCode += '01';
                    $("#deptCode").textbox('setValue', deptCode);
                }
                //如果parentId不为空，并且科室编码长度为4，则表示给二级科室添加下级科室
            } else if (node.parentId != null && $("#tt").treegrid('getLevel', node.id)) {
                var rows = $("#tt").treegrid('find', node.id).children;
                if (rows.length > 0) {
                    var deptCode = rows[rows.length - 1].deptCode
                    var c = parseInt(rows[rows.length - 1].deptCode.substr(deptCode.length - 1));
                    var d = deptCode.substr(0, deptCode.length - 1) + (c + 1);
                    $("#deptCode").textbox('setValue', d);
                } else {
                    var rows = $("#tt").treegrid('getSelected');
                    var deptCode = rows.deptCode;
                    deptCode += '01';
                    $("#deptCode").textbox('setValue', deptCode);
                }
            }


            $("#id").val("");


            $("#deptName").textbox('setValue', "");

            $("#deptPropertity").html("");
            $("#dlg").dialog("open").dialog("setTitle", "添加科室");
            $("#inputCode").attr('readonly', true);
            $("#parentId").combogrid('setValue', node.id);

            $.ajax({
                url: "/service/dept-property/selectProperty?orgId=" + orgId,
                type: 'get',
                dataType: 'json',
                error: function (data) {
                    alert("加载json 文件出错！");
                },
                success: function (data1) {
                    var data2 = eval(data1);
                    deptPropertitys = data2;
                    for (var i = 0; i < data2.length; i++) {
                        deptPropertity = data2[i].propertyType;
                        propertyId = "propertyName" + i;
                        var propertyFitem = "propertyFitem" + i;
                        if (deptPropertity.length == 3) {
                            var a = deptPropertity.substring(0, 1);
                            var b = deptPropertity.substring(1, 2);
                            var c = deptPropertity.substring(2, 3);
                            $("#deptPropertity").append("<div  id='" + propertyFitem + "' style='float: left ;margin-bottom: 10px;'>");
                            $("#" + propertyFitem).append("<label>" + a + " " + b + " " + c + ": </label>");
                            $("#" + propertyFitem).append("<select style='width: 150px;' name='propertyName' id='" + propertyId + "'/><br/>");

                        } else {
                            $("#deptPropertity").append("<div  id='" + propertyFitem + "' style='float: left ;margin-bottom: 10px;'>");
                            $("#" + propertyFitem).append("<label>" + deptPropertity + ": </label>");
                            $("#" + propertyFitem).append("<select style='width: 150px;' name='propertyName' id='" + propertyId + "'/><br/>");

                        }

                        $("#" + propertyId).combobox({
                            'url': '/service/dept-property/selectName/' + deptPropertity + '/' + orgId,
                            valueField: 'propertyValue',
                            textField: 'propertyName'
                        });

                        $("#deptPropertity").append("</div>")
                    }
                }
            });

        })


        $("#deptName").textbox({
            onChange: function () {
                var dept = $("#deptName").val();
                var inputCode = makePy(dept)[0];
                $("#inputCode").textbox('setValue', inputCode);
            }
        });
        /**
         * 修改科室信息
         *
         */

        $("#editBtn").on('click', function () {
            $('#parentId').combogrid('enable');
            $("#deptPropertity").html("");
            $("#deptName").textbox({
                onChange: function () {
                    var dept = $("#deptName").val();
                    var inputCode = makePy(dept)[0];
                    $("#inputCode").textbox('setValue', inputCode);
                }
            });

            var node = $("#tt").treegrid("getSelected");
            if (!node) {
                $.messager.alert("系统提示", "请选择要修改的科室");
                return;
            }


            $("#id").val(node.id);
            $("#deptCode").textbox('setValue', node.deptCode);
            $("#deptCode").textbox('disable');
            $("#deptName").textbox('setValue', node.deptName);
            $("#inputCode").textbox('setValue', node.inputCode);
            $("#parentId").combogrid('setValue', node.parentId);
            $("#clinicAttr").combobox('setValue', node.clinicAttr);
            $("#outpOrInp").combobox('setValue', node.outpOrInp);
            $("#internalOrSergery").combobox('setValue', node.internalOrSergery);

            //给上级科室的下拉列表赋值

            $("#dlg").dialog('open').dialog('setTitle', "科室修改");


            $.ajax({
                url: "/service/dept-property/selectProperty?orgId=" + orgId,
                type: 'get',
                dataType: 'json',
                error: function (data) {
                    alert("加载json 文件出错！");
                },
                success: function (data1) {
                    var data2 = eval(data1);
                    deptPropertitys = data2;
                    for (var i = 0; i < data2.length; i++) {
                        deptPropertity = data2[i].propertyType;
                        propertyId = "propertyName" + i;
                        var propertyFitem = "propertyFitem" + i;
                        $("#deptPropertity").append("<div  id='" + propertyFitem + "' style='float: left ;margin-bottom: 10px;'>")
                        $("#" + propertyFitem).append("<label>" + deptPropertity + ": </label>");
                        $("#" + propertyFitem).append("<select style='width: 150px;' name='propertyName' id='" + propertyId + "'/><br/>");
                        $("#" + propertyId).combobox({
                            'url': '/service/dept-property/selectName/' + deptPropertity + '/' + orgId,
                            valueField: 'propertyValue',
                            textField: 'propertyName'
                        });

                        $("#deptPropertity").append("</div>")
                    }


                    //修改科室属性时，给回显的属性赋值
                    var deptPro = node.deptPropertity;
                    var dept = [];
                    dept = deptPro.split(";");
                    for (var item = 0; item < dept.length; item++) {
                        var propertyIds = "propertyName" + item;
                        $("#" + propertyIds).combo("setValue", dept[item]);
                    }


                }
            });
        });
        /**
         * 删除
         */
        $("#delBtn").on('click', function () {
            var node = $("#tt").treegrid("getSelected");
            if (!node) {
                $.messager.alert("系统提示", "请选择要删除的科室");
                return;
            }

            if ($("#tt").treegrid("getChildren", node.id).length > 0) {
                $.messager.alert("系统提示", "请先删除子科室，在删除");
                return;
            }


            $.messager.confirm("系统提示", "确定要删除【" + node.deptName + "】吗？", function (r) {
                if (r) {
                    $.ajax({
                        'type': 'POST',
                        'url': "/service/dept-dict/del/",
                        'contentType': 'application/json',
                        'data': id = node.id,
                        'dataType': 'json',
                        'success': function (data) {
                            if (data.data == 'success') {
                                $.messager.alert("系统提示", "删除成功");
                                loadDept();
                            } else {
                                $.messager.alert('提示', "删除失败", "error");
                            }
                        }
                    });
                }
            });

        });


        /**
         * 清除输入框信息
         */
        var clearInput = function () {
            $("#deptCode").textbox('setValue', "");
            $("#deptName").textbox('setValue', "");
            $("#parentId").combogrid('setValue', "");
            $("#deptProperty").combobox('setValue', "");

            $("#propertyType").remove();
        }

        //查询
        $("#selectPropertyBtn").on('click', function () {
            var propertyTypeData = $("#propertyType").textbox('getValue');
            var propertyNameData = $("#propertyName").textbox('getValue');
            var propertyValueData = $("#propertyValue").textbox('getValue');

            $.get(basePath + "/dept-property/findByCondition", {
                propertyType: propertyTypeData,
                orgId: orgId,
                propertyName: propertyNameData,
                propertyValue: propertyValueData
            }, function (data) {
                $("#dg").datagrid('loadData', data);
            });

        });
        $('#dg').datagrid({
            width: 720,
            height: 350,
            fitColumns: true,
            singleSelect: true,
            method: 'get',
            url: '/service/dept-property/list?orgId=' + orgId,
            idField: 'id',
            singleSelect: true,//是否单选
            rownumbers: true,//行号

            columns: [[
                {
                    field: 'propertyType', title: '属性类型', width: 235, align: 'center',
                    editor: {
                        type: 'textbox',
                        options: {
                            required: true,
                            validType: 'IsExisted["propertyType"]',
                            missingMessage: '属性类型不能为空'
                        }
                    }
                },
                {
                    field: 'propertyName', title: '属性名称', width: 235, align: 'center',
                    editor: {
                        type: 'textbox',
                        options: {
                            required: true,
                            missingMessage: '属性名称不能为空'
                        }
                    }
                },
                {
                    field: 'propertyValue', title: '属性值', width: 240, align: 'center',
                    editor: {
                        type: 'textbox',
                        options: {
                            required: true,
                            validType: 'IsExisted["propertyValue"]',
                            missingMessage: '属性值不能为空'
                        }
                    }
                }
            ]],
            onClickCell: onClickCell,
            onBeforeSelect: function (index) {
                return $('#dg').datagrid('validateRow', editIndex)
            }
        });


//datagrid的单元格编辑
        $.extend($.fn.datagrid.methods, {
            editCell: function (jq, param) {
                return jq.each(function () {
                    var opts = $(this).datagrid('options');
                    var fields = $(this).datagrid('getColumnFields', true).concat($(this).datagrid('getColumnFields'));
                    for (var i = 0; i < fields.length; i++) {
                        var col = $(this).datagrid('getColumnOption', fields[i]);
                        col.editor1 = col.editor;
                        if (fields[i] != param.field) {
                            col.editor = null;
                        }
                    }
                    $(this).datagrid('beginEdit', param.index);
                    for (var i = 0; i < fields.length; i++) {
                        var col = $(this).datagrid('getColumnOption', fields[i]);
                        col.editor = col.editor1;
                    }
                });
            }
        });

        var editIndex = undefined;

        function endEditing1() {
            if (editIndex == undefined) {
                return true
            }
            if ($('#dg').datagrid('validateRow', editIndex)) {
                $('#dg').datagrid('endEdit', editIndex);
                editIndex = undefined;
                return true;
            } else {
                return false;
            }
        }

        function onClickCell(index, field) {
            if (endEditing1()) {
                if (field == 'propertyType') {
                    var row = $('#dg').datagrid('getRows')[index];
                    if (row.id)return;
                }
                if (field == 'propertyValue') {
                    var row = $('#dg').datagrid('getRows')[index];
                    if (row.id)return;
                }
                $('#dg').datagrid('selectRow', index)
                    .datagrid('editCell', {index: index, field: field});
                editIndex = index;
            }
        }

        //开始编辑行
        $("#addPropertyBtn").on('click', function () {
            $.get(basePath + "/dept-dict/list?orgId=" + orgId, function (data) {
                var flag = false;
                for (var i = 0; i < data.length; i++) {
                    //alert(data[i].deptPropertity)
                    if (data[i].deptPropertity!=";;") {
                        flag = true;
                        break;
                    }
                }
                if (flag == true) {
                    $.messager.alert('系统提示', "已有其它科室属性,不能再维护科室属性了", 'info');
                    return;
                } else {
                    $("#dg").datagrid('appendRow', {orgId: orgId});
                    var rows = $("#dg").datagrid('getRows');
                    onClickCell(rows.length - 1, 'dg');
                }
            });
        });

        //删除
        $("#delPropertyBtn").on("click", function () {
            $.get(basePath + "/dept-dict/listByOrgId?orgId=" + orgId, function (data) {
                if (data != "") {
                    $.messager.alert('系统提示', "已有其它科室属性,不能再维护科室属性了", 'info');
                    return;
                } else {
                    var row = $("#dg").datagrid('getSelected');
                    if (!row) {
                        $.messager.alert('系统提示', "请选择要删除的行", 'info');
                        return;
                    } else {
                        var rowIndex = $("#dg").datagrid('getRowIndex', row);
                        $("#dg").datagrid('deleteRow', rowIndex);
                        if (editIndex == rowIndex) {
                            editIndex = undefined;
                        }
                    }
                }
            });
        });

        //关闭
        $("#cancelPropertyBtn").on("click", function () {
            $("#dg").datagrid('reload');
        });

        /* *
         * 保存科室属性信息*/

        $("#savePropertyBtn").on('click', function () {
            if (!endEditing1()) {
                return
            }
            var insertData = $("#dg").datagrid("getChanges", "inserted");
            var updateData = $("#dg").datagrid("getChanges", "updated");
            var deleteData = $("#dg").datagrid("getChanges", "deleted");
            //保存时判断值不能为空
            for (var i = 0; i < insertData.length; i++) {
                if (!("propertyValue" in insertData[i]) || !("propertyName" in insertData[i]) || !("propertyType" in insertData[i])) {
                    $.messager.alert("系统提示", "值,名称,类型不能为空", "info");
                    return
                }
            }
            var orgDeptPropertyDictVo = {};
            orgDeptPropertyDictVo.inserted = insertData;
            orgDeptPropertyDictVo.deleted = deleteData;
            orgDeptPropertyDictVo.updated = updateData;
            orgDeptPropertyDictVo.orgId = orgId;
            if (orgDeptPropertyDictVo) {
                $.postJSON("/service/dept-property/saveAll", JSON.stringify(orgDeptPropertyDictVo), function (data) {
                    if (data.data == "success") {
                        $.messager.alert("系统提示", "保存成功", "info");
                        $("#dg").datagrid('reload');
                        $("#dlg_property").dialog('close');
                    }
                }, function (data) {
                    $.messager.alert('提示', "保存失败", "error");
                })
            }
        });
    }
);




