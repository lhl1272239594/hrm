/**
 * 输入法字典维护
 * @author yangruidong
 * @version 2016-05-18
 */
$("<link>").attr({rel: "stylesheet", type: "text/css", href: "/static/easyui/css/icon.css"}).appendTo("head");
$("<script>").attr({type: "application/javascript", src: "/static/easyui/js/jquery.easyui.min.js"}).appendTo("head");
$("<script>").attr({type: "application/javascript", src: "/static/easyui/locale/easyui-lang-zh_CN.js"}).appendTo("head");
$("<script>").attr({type: "application/javascript", src: "/static/js/tool.js"}).appendTo("head");
$("<script>").attr({type: "application/javascript", src: "/static/js/formSubmit.js"}).appendTo("head");
var basePath="/service";
$(function () {

    var drugFrom=[];
    //var orgId=parent.config.org_id;
    var orgId=1;
    var input_setting_master_id;
    var tableName;
    var editIndex1 = undefined;
    //检查类别
    $("#masterGrid").datagrid({
        fit: true,
        fitColumns: true,
        striped: true,
        singleSelect: true,
        idField: "id",
        toolbar: '#classft',
        url:basePath+'/input-setting/findAllListByOrgId?orgId='+orgId,
        method:'get',
        loadMsg: '数据正在加载中，请稍后.....',
        columns: [[{
            title: 'id',
            field: "id",
            hidden: true
        }, {
            title: '字典类型',
            field: 'dictName',
            width: '40%'    ,
            align: 'center',
            editor: 'textbox'
        }, {
            title: '表/视图',
            field: 'dictType',
            width: '60%'    ,
            align: 'center' ,
            editor: 'textbox'
        }]] ,
        onClickRow: function (index, row) {
            drugFrom=[];
            var node = $("#masterGrid").datagrid("getSelected");
            tableName=node.dictType;
            input_setting_master_id=node.id;
            //加载字段名称
            jQuery.ajax({
                'type': 'GET',
                'url':basePath+'/input-setting/listTableColByTableName?tableName='+tableName,
                'contentType': 'application/json',
                'dataType': 'json',
                'success': function (data) {
                    console.log(data);
                    for(var i=0;i<data.length;i++)
                    {
                        drugFrom.push({value:data[i],text:data[i]});
                    }
                    console.log(drugFrom);
                },
                'error': function (data) {
                    $.messager.alert("系统提示", "加载数据出错");
                }
            });

            //在点击切换输入法主记录时，判断输入法明细表是否有变动的数据
            if (editIndex1 || editIndex1 == 0) {
                $("#detailGrid").datagrid("endEdit", editIndex1);
            }
            var insertData = $("#detailGrid").datagrid("getChanges", "inserted");
            var updateData = $("#detailGrid").datagrid("getChanges", "updated");
            var deleteData = $("#detailGrid").datagrid("getChanges", "deleted");

            if(insertData!=""||updateData!=""||deleteData!="")
            {
                $.messager.confirm("系统提示", "确定要保存吗？", function (r) {
                    if (r) {
                        var inputSettingVo = {};
                        inputSettingVo.inserted = insertData;
                        inputSettingVo.deleted = deleteData;
                        inputSettingVo.updated = updateData;

                        //  inputSettingVo.input_setting_master_id=input_setting_master_id;

                        if (inputSettingVo) {
                            $.postJSON(basePath + "/input-setting/saveDetail", JSON.stringify(inputSettingVo), function (data) {
                                if (data.data == "success") {
                                    $.messager.alert("系统提示", "保存成功", "info");
                                    $("#detailGrid").datagrid('reload');
                                }
                            }, function (data) {
                                $.messager.alert('提示', "保存失败，字段是唯一键或者其他字段不能为空", "error");
                            })
                        }
                    }
                });
            }




            var url = basePath + "/input-setting/findListDetail?id="+ node.id;
            $("#detailGrid").datagrid("reload", url);

        } ,

        onClickCell: onClickCell
    });


    //datagrid的单元格编辑
    $.extend($.fn.datagrid.methods, {
        editCell: function(jq,param){
            return jq.each(function(){
                var opts = $(this).datagrid('options');
                var fields = $(this).datagrid('getColumnFields',true).concat($(this).datagrid('getColumnFields'));
                for(var i=0; i<fields.length; i++){
                    var col = $(this).datagrid('getColumnOption', fields[i]);
                    col.editor1 = col.editor;
                    if (fields[i] != param.field){
                        col.editor = null;
                    }
                }
                $(this).datagrid('beginEdit', param.index);
                for(var i=0; i<fields.length; i++){
                    var col = $(this).datagrid('getColumnOption', fields[i]);
                    col.editor = col.editor1;
                }
            });
        }
    });

    var editIndex = undefined;
    function endEditing1(){
        if (editIndex == undefined){return true}
        if ($('#masterGrid').datagrid('validateRow', editIndex)){
            $('#masterGrid').datagrid('endEdit', editIndex);
            editIndex = undefined;
            return true;
        } else {
            return false;
        }
    }

    function onClickCell(index, field){
        if (endEditing1()){
            $('#masterGrid').datagrid('selectRow', index)
                .datagrid('editCell', {index:index,field:field});
            editIndex = index;
        }
    }

    //开始编辑行
    $("#addMasterBtn").on('click', function () {

        $("#masterGrid").datagrid('appendRow', {});
        var rows = $("#masterGrid").datagrid('getRows');
        onClickCell(rows.length-1,'dictName');
    });

    //输入法字典主记录保存
    $("#saveMasterBtn").on("click", function () {
        if (editIndex || editIndex == 0) {
            $("#masterGrid").datagrid("endEdit", editIndex);
        }
        var rows=$('#masterGrid').datagrid('getRows');
        for (var i = 0; i < rows.length; i++) {
            if((!rows[i].dictName||rows[i].dictName=="")&&(!rows[i].dictType||rows[i].dictType=="")){
                $('#masterGrid').datagrid('deleteRow', i);
            }else if((!rows[i].dictName||rows[i].dictName=="")||(!rows[i].dictType||rows[i].dictType=="")){
                $('#masterGrid').datagrid('selectRow', i);
                $('#masterGrid').datagrid('beginEdit', i);
                $.messager.alert('提示', "请保证字典类型、表/视图均不为空", "info");
                return;
            }
        }
        var insertData = $("#masterGrid").datagrid("getChanges", "inserted");
        var updateData = $("#masterGrid").datagrid("getChanges", "updated");
        var deleteData = $("#masterGrid").datagrid("getChanges", "deleted");

        var inputSettingVo = {};
        inputSettingVo.inserted = insertData;
        inputSettingVo.deleted = deleteData;
        inputSettingVo.updated = updateData;
        inputSettingVo.orgId=orgId;


        if (inputSettingVo) {
            $.postJSON(basePath + "/input-setting/saveAll", JSON.stringify(inputSettingVo), function (data) {
                if (data.data == "success") {
                    $.messager.alert("系统提示", "保存成功", "info");
                    $("#masterGrid").datagrid('reload');
                }
            }, function (data) {
                $.messager.alert('提示', "保存失败", "error");
            })
        }
    });

    //删除
    $("#removeMasterBtn").on("click", function () {
        var row = $("#masterGrid").datagrid('getSelected');

        $.get("/service/input-setting/findListDetail?id=" + row.id, function (data) {
            console.log(data);
            if(data!="")
            {
                $.messager.alert('系统提示', "请先删除子表数据", 'info');
                return;
            }
            else{
                if(row)
                {
                    var rowIndex = $("#masterGrid").datagrid('getRowIndex', row);
                    $("#masterGrid").datagrid('deleteRow', rowIndex);
                    if (editIndex == rowIndex) {
                        editIndex = undefined;
                    }
                }else{
                    $.messager.alert('系统提示', "请选择要删除的行", 'info');
                }
            }

        });
    });


    //输入法字典明细
    $("#detailGrid").datagrid({
        fit: true,
        fitColumns: true,
        striped: true,
        singleSelect: true,
        toolbar: '#ft',
        method: 'GET',
        rownumbers: true,

        loadMsg: '数据正在加载中，请稍后.....',
        columns: [[{
            title: "id",
            field: "id",
            hidden: true
        }, {
            title: "字段",
            field: "dataCol",
            width: '9%' ,
            align:'center' ,
            editor: {
                type: 'combobox',
                options: {
                    valueField:'value',
                    textField: 'text'
                }
            }

        }, {
            title: '输入码',
            field: 'inputCode',
            width: '9%' ,
            align:'center',
            formatter:function(value){
                if(value=='00')
                {
                    return "拼音码";
                }else if(value=='01'){
                    return "输入码";
                }
                return "";
            },
            editor: {
                type: 'combobox',
                options: {
                    valueField: 'value',
                    textField: 'text',
                    data: [{value: '00', text: '拼音码'}, {value: '01', text: '输入码'}]
                }
            }
        }, {
            title: '标题',
            field: 'dataTitle',
            width: '9%' ,
            align:'center'  ,
            editor: {
                type: 'textbox'
            }
        }, {
            title: '是否显示',
            field: 'flagShow',
            width: '9%' ,
            align:'center'  ,
            formatter:function(value){
                if(value=='1')
                {
                    return "是";
                }else if(value=='0'){
                    return "否";
                }
                return "";
            },
            editor: {
                type: 'combobox',
                options: {
                    valueField: 'value',
                    textField: 'text',
                    data: [{value: '1', text: '是'}, {value: '0', text: '否'}]
                }
            }
        }, {
            title: '显示顺序',
            field: 'showSort',
            width: '9%' ,
            align:'center' ,
            editor: {
                type: 'textbox'
            }
        }, {
            title: '是否名称字段',
            field: 'flagIsname',
            width: '9%' ,
            align:'center',
            formatter:function(value){
                if(value=='Y')
                {
                    return "是";
                }else if(value=='N'){
                    return "否";
                }
                return "";
            },
            editor: {
                type: 'combobox',
                options: {
                    valueField: 'value',
                    textField: 'text',
                    data: [{value: 'Y', text: '是'}, {value: 'N', text: '否'}]
                }
            }
        } , {
            title: '结果顺序位',
            field: 'resultSort',
            width: '9%' ,
            align:'center' ,
            editor: {
                type: 'textbox',
                options:{
                }
            }
        }, {
            title: '显示宽度',
            field: 'showWidth',
            width: '9%' ,
            align:'center'  ,
            editor: {
                type: 'textbox'
            }
        }
        ]]  ,
        onClickCell: onClickCell1
    });


    //datagrid的单元格编辑

    function endEditing2(){
        if (editIndex1 == undefined){return true}
        if ($('#detailGrid').datagrid('validateRow', editIndex1)){
            $('#detailGrid').datagrid('endEdit', editIndex1);
            editIndex1 = undefined;
            return true;
        } else {
            return false;
        }
    }
    function onClickCell1(index, field){
        if (endEditing2()){
            $('#detailGrid').datagrid('selectRow', index) .datagrid('editCell', {index:index,field:field});
            editIndex1 = index;
            var editor= $("#detailGrid").datagrid('getEditor',{index:index,field:'dataCol'})  ;
            $(editor.target).combobox('loadData',drugFrom);
            //drugFrom=[];
        }
    }
    var i=0;

    //开始编辑行
    $("#addDetailBtn").on('click', function () {
        if(!$("#masterGrid").datagrid("getSelected"))
        {
            $.messager.alert("系统提示", "请先选择字典类型", "info");
            return false;
        }
        $("#detailGrid").datagrid('appendRow', {'showSort':i++,'resultSort':'0',inputSettingMasterId:$("#masterGrid").datagrid("getSelected").id});
        var rows = $("#detailGrid").datagrid('getRows');
        onClickCell1(rows.length-1,'dataCol');
    });

    //输入法字典主记录保存
    $("#saveDetailBtn").on("click", function () {
        if (editIndex1 || editIndex1 == 0) {
            $("#detailGrid").datagrid("endEdit", editIndex1);
        }

        var rows=$('#detailGrid').datagrid('getRows');
        for (var i = 0; i < rows.length; i++) {
            if((!rows[i].dataCol||rows[i].dataCol=="")&&(!rows[i].inputCode||rows[i].inputCode=="")&&(!rows[i].dataTitle||rows[i].dataTitle=="")&&(!rows[i].flagIsname||rows[i].flagIsname=="")&&(!rows[i].flagShow||rows[i].flagShow=="")&&(!rows[i].showWidth||rows[i].showWidth=="")){
                $('#detailGrid').datagrid('deleteRow', i);
            }else if((!rows[i].dataCol||rows[i].dataCol=="")||(!rows[i].inputCode||rows[i].inputCode=="")||(!rows[i].dataTitle||rows[i].dataTitle=="")||(!rows[i].flagIsname||rows[i].flagIsname=="")||(!rows[i].flagShow||rows[i].flagShow=="")||(!rows[i].showWidth||rows[i].showWidth=="")){
                $('#detailGrid').datagrid('selectRow', i);
                $('#detailGrid').datagrid('beginEdit', i);
                $('#datagrid-row-r4-2-'+ i + ' .textbox-text').blur();
                $.messager.alert('提示', "请保证明细中所有字段均不为空", "info");
                return;
            }
        }
        var insertData = $("#detailGrid").datagrid("getChanges", "inserted");
        var updateData = $("#detailGrid").datagrid("getChanges", "updated");
        var deleteData = $("#detailGrid").datagrid("getChanges", "deleted");

        var inputSettingVo = {};
        inputSettingVo.inserted = insertData;
        inputSettingVo.deleted = deleteData;
        inputSettingVo.updated = updateData;

        inputSettingVo.input_setting_master_id=input_setting_master_id;

        if (inputSettingVo) {
            $.postJSON(basePath + "/input-setting/saveDetail", JSON.stringify(inputSettingVo), function (data) {
                if (data.data == "success") {
                    $.messager.alert("系统提示", "保存成功", "info");
                    $("#detailGrid").datagrid('reload');
                }
            }, function (data) {
                $.messager.alert('提示', "保存失败，字段是唯一键或者其他字段不能为空", "error");
            })
        }
    });

    //删除
    $("#removeDetailBtn").on("click", function () {
        var row = $("#detailGrid").datagrid('getSelected');
        if (row) {
            var rowIndex = $("#detailGrid").datagrid('getRowIndex', row);
            $("#detailGrid").datagrid('deleteRow', rowIndex);
            if (editIndex == rowIndex) {
                editIndex = undefined;
            }
        } else {
            $.messager.alert('系统提示', "请选择要删除的行", 'info');
        }
    });



});




