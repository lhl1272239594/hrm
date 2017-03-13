/**
 * Created by fengyuguang on 2016/5/6.
 */
$("<script>").attr({type: "application/javascript", src: "/static/js/spell.js"}).appendTo("head");
$(function(){
    var editTypeIndex;
    var editLabelIndex;
    var sysDictType;    //定义字典表类型
    var theRowData = {};    //左边datagrid某一行的数据(只有type和description)
    var datasForType = [];  //某个字典类型下的所有键值数据

    //定义整体多个操作的增删改数据
    var inserted = [];
    var updated = [];

    var stopTypeEdit = function () {    //停止编辑左侧列表
        if (editTypeIndex || editTypeIndex == 0) {
            $("#descrip").datagrid('endEdit', editTypeIndex);
            editTypeIndex = undefined;
        }
    }
    var stopLabelEdit = function () {   //停止编辑右侧列表
        if (editLabelIndex || editLabelIndex == 0) {
            $("#label_value").datagrid('endEdit', editLabelIndex);
            editLabelIndex = undefined;
        }
    }

    //临时保存增删改数据
    var tempSave = function () {
        if (editTypeIndex || editTypeIndex == 0) {
            $("#descrip").datagrid("endEdit", editTypeIndex);
        }
        if (editLabelIndex || editLabelIndex == 0) {
            $("#label_value").datagrid("endEdit", editLabelIndex);
        }
        var insertedData = {};
        var updatedData = {};
        //保存刷新之前的左侧列表里面的增删改数据倒数组里面
        if ($("#descrip").datagrid("getChanges").length > 0) {
            var typeInsertData = $("#descrip").datagrid("getChanges", "inserted");
            var typeUpdateData = $("#descrip").datagrid("getChanges", "updated");
            if (typeInsertData && typeInsertData.length > 0) {
                for (var i = 0; i < typeInsertData.length; i++) {
                    typeInsertData[i].type = trim(typeInsertData[i].type);
                    typeInsertData[i].description = trim(typeInsertData[i].description);
                    if(typeof(typeInsertData[i].type) != "undefined" && typeInsertData[i].type != ""
                        && typeof(typeInsertData[i].description) != "undefined" && typeInsertData[i].description != ""){
                        var labelInsertData = $("#label_value").datagrid("getChanges", "inserted");
                        if (labelInsertData && labelInsertData.length > 0) {
                            for (var j = 0; j < labelInsertData.length; j++) {
                                labelInsertData[j].label = trim(labelInsertData[j].label);
                                labelInsertData[j].value = trim(labelInsertData[j].value);
                                labelInsertData[j].sort = trim(labelInsertData[j].sort);
                                if(typeof(labelInsertData[j].label) != "undefined" && labelInsertData[j].label != ""
                                && typeof(labelInsertData[j].value) != "undefined" && labelInsertData[j].value != ""
                                && typeof(labelInsertData[j].sort) != "undefined" && labelInsertData[j].sort != ""){
                                    insertedData.type = typeInsertData[i].type;
                                    insertedData.description = typeInsertData[i].description;
                                    insertedData.label = labelInsertData[j].label;
                                    insertedData.value = labelInsertData[j].value;
                                    insertedData.sort = labelInsertData[j].sort;
                                    insertedData.remarks = labelInsertData[j].remarks;
                                    insertedData.inputCode = labelInsertData[j].inputCode;

                                    inserted.push(insertedData);
                                    insertedData = {};
                                }else{
                                    $.messager.alert("提示消息", "请完整填写必填项!");
                                    $("#label_value").datagrid('reload');
                                    $("#label_value").datagrid('clearSelections');
                                    return;
                                }
                            }
                        }
                    }else{
                        $.messager.alert("提示消息", "类型或描述不能为空!");
                        $("#descrip").datagrid('reload');
                        $("#label_value").datagrid('clearSelections');
                        return ;
                    }
                }
            }
            if (typeUpdateData && typeUpdateData.length > 0) {
                for (var i = 0; i < typeUpdateData.length; i++) {
                    for(var j=0;j< datasForType.length;j++){
                        typeUpdateData[i].type = trim(typeUpdateData[i].type);
                        typeUpdateData[i].description = trim(typeUpdateData[i].description);
                        if(typeof(typeUpdateData[i].type) != "undefined" && typeUpdateData[i].type != ""
                            && typeUpdateData[i].description != "undefined" && typeUpdateData[i].description != ""){
                            updatedData.id = datasForType[j].id;
                            updatedData.label = datasForType[j].label;
                            updatedData.value = datasForType[j].value;
                            updatedData.remarks = datasForType[j].remarks;
                            updatedData.inputCode = datasForType[j].inputCode;
                            updatedData.sort = datasForType[j].sort;
                            updatedData.type = typeUpdateData[i].type;
                            updatedData.description = typeUpdateData[i].description;
                            updated.push(updatedData);
                            updatedData = {};
                        }else{
                            $.messager.alert("提示消息", "类型或描述不能为空!");
                            $("#descrip").datagrid('reload');
                            $("#label_value").datagrid('clearSelections');
                            return;
                        }
                    }
                }
            }
        }else if($("#label_value").datagrid("getChanges").length > 0) {
            var theRowData = $("#descrip").datagrid('getSelected');
            var labelInsertData = $("#label_value").datagrid("getChanges", "inserted");
            var labelUpdatedData = $("#label_value").datagrid("getChanges", "updated");
            if (labelInsertData && labelInsertData.length > 0) {    //插入数据
                for (var j = 0; j < labelInsertData.length; j++) {
                    labelInsertData[j].label = trim(labelInsertData[j].label);
                    labelInsertData[j].value = trim(labelInsertData[j].value);
                    labelInsertData[j].sort = trim(labelInsertData[j].sort);
                    if(typeof(labelInsertData[j].label) != "undefined" && labelInsertData[j].label != ""
                    && typeof(labelInsertData[j].value) != "undefined" && labelInsertData[j].value != ""
                    && typeof(labelInsertData[j].sort) != "undefined" && labelInsertData[j].sort != ""){
                        insertedData.type = theRowData.type;
                        insertedData.description = theRowData.description;
                        insertedData.label = labelInsertData[j].label;
                        insertedData.value = labelInsertData[j].value;
                        insertedData.sort = labelInsertData[j].sort;
                        insertedData.remarks = labelInsertData[j].remarks;
                        insertedData.inputCode = labelInsertData[j].inputCode;
                        inserted.push(insertedData);
                        insertedData = {};
                    }else{
                        $.messager.alert("提示消息", "请完整填写必填项!");
                        $("#label_value").datagrid('reload');
                        $("#label_value").datagrid('clearSelections');
                        return;
                    }
                }
            }
            if(labelUpdatedData && labelUpdatedData.length > 0){    //更新数据
                for (var i = 0; i < labelUpdatedData.length; i++) {
                    labelUpdatedData[i].label = trim(labelUpdatedData[i].label);
                    labelUpdatedData[i].value = trim(labelUpdatedData[i].value);
                    labelUpdatedData[i].sort = trim(labelUpdatedData[i].sort);
                    if(typeof(labelUpdatedData[i].label) != "undefined" && labelUpdatedData[i].label != ""
                    && typeof(labelUpdatedData[i].value) != "undefined" && labelUpdatedData[i].value != ""
                    && typeof(labelUpdatedData[i].sort) != "undefined" && labelUpdatedData[i].sort != ""){
                        updatedData.id = datasForType[i].id;
                        updatedData.label = labelUpdatedData[i].label;
                        updatedData.value = labelUpdatedData[i].value;
                        updatedData.remarks = labelUpdatedData[i].remarks;
                        updatedData.inputCode = labelUpdatedData[i].inputCode;
                        updatedData.sort = labelUpdatedData[i].sort;
                        updatedData.type = theRowData.type;
                        updatedData.description = theRowData.description;
                        updated.push(updatedData);
                        updatedData = {};
                    }else{
                        $.messager.alert("提示消息", "请完整填写必填项!");
                        $("#label_value").datagrid('reload');
                        $("#label_value").datagrid('clearSelections');
                        return;
                    }
                }
            }
        }
    }

    /**
     * 左侧列表显示字典表的类型和描述字段
     */
    $("#descrip").datagrid({
        iconCls: 'icon-edit',//图标
        width: 'auto',
        height: 'auto',
        //footer: '#searchTb',
        nowrap: false,  //如果为true，则在同一行中显示数据
        striped: true,  //显示斑马线效果
        border: true,
        method: 'get',
        collapsible: false,//是否可折叠的
        //fit: true,//自动大小
        url: basePath + '/dict/type-description-list',
        remoteSort: false,  //定义从服务器对数据进行排序
        //idField: 'fldId',
        singleSelect: true,//是否单选
        columns:[[
            {
                title: '类型',
                field: 'type',
                width: '40%',
                align: 'center',
                editor:{
                    type: 'validatebox',
                    options:{
                        required: true
                    }
                }
            },
            {
                field: 'description',
                title: '描述',
                width: '60%',
                align: 'center',
                editor: {
                    type: 'textbox',
                    options: {
                        required: true
                    }
                }
            }
        ]],
        onLoadSuccess: function (data) {
            var defaultRow =  $(this).datagrid('selectRow', 0);   //加载成功默认选中第一行
            var thisRow = $(this).datagrid('getSelected');      //获取被选中的行
            if(thisRow!=undefined){
                $.get(basePath + "/dict/label-value-list?type=" + thisRow.type, function (data) {
                    $("#label_value").datagrid('loadData', data);
                });
            }else{
                $("#label_value").datagrid('loadData', []);
            }

        },
        //点击一行触发事件查询属于该类型的所有数据
        onClickRow:function(rowIndex, rowData){
            stopTypeEdit();
            //tempSave();
            decide();
            theRowData = {};
            sysDictType = rowData.type;  //赋值给字典表类型
            $.get(basePath + "/dict/label-value-list?type=" + sysDictType,function(data){
                $("#label_value").datagrid('loadData',data);
                $("#label_value").datagrid('clearSelections');
                var oneData = {};
                if (datasForType == null) {
                    for (var a = 0; a < data.length; a++) {
                        oneData.id = data[a].id;
                        oneData.label = data[a].label;
                        oneData.value = data[a].value;
                        oneData.remarks = data[a].remarks;
                        oneData.inputCode = data[a].inputCode;
                        oneData.sort = data[a].sort;
                        datasForType.push(oneData);
                        oneData = {};
                    }
                }else{
                    datasForType = [];
                    for (var a = 0; a < data.length; a++) {
                        oneData.id = data[a].id;
                        oneData.label = data[a].label;
                        oneData.value = data[a].value;
                        oneData.remarks = data[a].remarks;
                        oneData.inputCode = data[a].inputCode;
                        oneData.sort = data[a].sort;
                        datasForType.push(oneData);
                        oneData = {};
                    }
                }
            });
            $("#label_value").datagrid('clearSelections');
        },
        //用户双击一行时触发事件编辑
        onDblClickRow: function (rowIndex, rowData) {
            stopTypeEdit();
            stopLabelEdit();
            $(this).datagrid('beginEdit', rowIndex);
            editTypeIndex = rowIndex;
        }
    });

    //var p = $('#label_value').datagrid('getPager');

    /**
     * 右侧列表显示根据类型查询的数据
     */
    $("#label_value").datagrid({
        iconCls: 'icon-edit',//图标
        width: 'auto',
        height: 'auto',
        //footer: '#ft',
        nowrap: false,  //如果为true，则在同一行中显示数据
        striped: true,  //显示斑马线效果
        border: true,
        method: 'get',
        collapsible: false,//是否可折叠的
        //fit: true,//自动大小
        remoteSort: false,  //定义从服务器对数据进行排序
        //idField: 'fldId',
        singleSelect: false,//是否单选
        //pagination: true,
        //pageSize: 15,
        //pageList: [10,15,30,50],
        columns: [[
            {
                field: 'id',
                title: 'ID',
                hidden: true
            },
            {
                field: 'label',
                title: '标签',
                width: '16%',
                align: 'center',
                editor: {
                    type: 'validatebox',
                    options: {
                        required: true
                    }
                }
            },
            {
                field: 'value',
                title: '键值',
                width: '16%',
                align: 'center',
                editor: {
                    type: 'textbox',
                    options: {
                        required: true
                    }
                }
            },
            {field: 'remarks', title: '备注', width: '16%', align: 'center', editor: 'text'},
            {field: 'inputCode', title: '拼音码', width: '15.9%', align: 'center'},
            {
                field: 'sort',
                title: '排序',
                width: '15.9%',
                align: 'center',
                editor: {
                    type: 'numberbox',
                    options: {
                        required: true
                    }
                }
            }
        ]],
        onLoadSuccess: function (data) {
            $(this).datagrid('selectRow', 0);   //加载成功默认选中第一行
        },
        onClickRow:function(rowIndex, rowData){
            stopTypeEdit();
            if(editLabelIndex != null || editLabelIndex != undefined){
                stopLabelEdit();    //点击一行选中前，先判断有没有别的行处于编辑状态，如果有，则停止编辑
            }
        },
        onDblClickRow: function (rowIndex, rowData) {   //双击一行触发事件编辑
            stopTypeEdit();
            stopLabelEdit();
            $(this).datagrid('clearSelections');    //编辑此行前先清空选择的其他行
            $(this).datagrid('beginEdit', rowIndex);
            editLabelIndex = rowIndex;
            var oneData = {};
            if (datasForType == null) {
                oneData.id = rowData.id;
                oneData.label = rowData.label;
                oneData.value = rowData.value;
                oneData.remarks = rowData.remarks;
                oneData.inputCode = rowData.inputCode;
                oneData.sort = rowData.sort;
                datasForType.push(oneData);
                oneData = {};
            } else {
                datasForType = [];
                oneData.id = rowData.id;
                oneData.label = rowData.label;
                oneData.value = rowData.value;
                oneData.remarks = rowData.remarks;
                oneData.inputCode = rowData.inputCode;
                oneData.sort = rowData.sort;
                datasForType.push(oneData);
                oneData = {};
            }
        }
    });

    var loadTypeDict = function () {
        //提交成功后刷新左侧datagrid
        $("#descrip").datagrid('reload');
        $("#label_value").datagrid('clearSelections');
        //提交完成后重置增删改数据
        inserted = [];
        updated = [];
    }

    var loadLabelDict = function () {
        //提交成功后刷新右侧datagrid
        var thisTypeData = $("#descrip").datagrid('getSelected');
        sysDictType = thisTypeData.type;
        $.get(basePath + "/dict/label-value-list?type=" + sysDictType, function (data) {
            $("#label_value").datagrid('load', data);
            $("#label_value").datagrid('clearSelections');
        });
        //提交完成后重置增删改数据
        inserted = [];
        updated = [];
    }

    //添加字典
    $("#addTypeBtn").on('click', function () {
        stopTypeEdit();
        stopLabelEdit();
        tempSave();
        decide();
        //增加字典
        $('#descrip').datagrid('appendRow',{});
        var typeRows = $("#descrip").datagrid('getRows');
        var addTypeRowIndex = $("#descrip").datagrid('getRowIndex', typeRows[typeRows.length - 1]);
        editTypeIndex = addTypeRowIndex;
        $("#descrip").datagrid('selectRow', editTypeIndex);  //选择新增加的一行
        $("#descrip").datagrid('beginEdit', editTypeIndex);  //开始编辑新增加的行数据

        $("#label_value").datagrid('loadData', {total: 0, rows: []});   //清空右侧表格数据
        //增加键值
        $('#label_value').datagrid('appendRow', {});
        var labelRows = $("#label_value").datagrid('getRows');
        var addLabelRowIndex = $("#label_value").datagrid('getRowIndex', labelRows[labelRows.length - 1]);
        editLabelIndex = addLabelRowIndex;
        $("#label_value").datagrid('selectRow', editLabelIndex);  //选择新增加的一行
        $("#label_value").datagrid('beginEdit', editLabelIndex);  //开始编辑新增加的行数据

        var editor = $("#descrip").datagrid('getEditor', {index: editTypeIndex, field: 'type'});
        editor.target.focus();
    });
    //添加键值
    $("#addChildBtn").on('click',function(){
        stopTypeEdit();
        stopLabelEdit();
        $("#label_value").datagrid('clearSelections');
        $('#label_value').datagrid('appendRow', {id:'',label:'',value:'',remarks:'',inputCode:'',sort:'2'});
        var labelRows = $("#label_value").datagrid('getRows');
        var addLabelRowIndex = $("#label_value").datagrid('getRowIndex', labelRows[labelRows.length - 1]);
        editLabelIndex = addLabelRowIndex;
        $("#label_value").datagrid('selectRow', editLabelIndex);  //选择新增加的一行
        $("#label_value").datagrid('beginEdit', editLabelIndex);  //开始编辑新增加的行数据

        var editor = $("#label_value").datagrid('getEditor', {index: editLabelIndex, field: 'label'});
        editor.target.focus();
    });
    //批量删除

    $("#delBtn").on('click', function () {
        stopLabelEdit();
        var deleteRows = $("#label_value").datagrid('getSelections');	//获取所有被选中的行，即要删除的所有行
        if (deleteRows == null || deleteRows.length == 0) {
            $.messager.alert("系统提示", "请选择要删除的项目");
            return;
        }if (deleteRows.id=='') {
            var cc=[];
            for(var i=0;i<deleteRows.length;i++){
                var index = $("#label_value").datagrid('getRowIndex',deleteRows[i]);
                cc.push(index);
            }
            //判断是否是新加项目
            $.messager.confirm('系统提示', '确定要进行删除操作吗', function (r) {
                if (r) {
                    for(var j=cc.length-1;j>=0;j--){
                        $("#label_value").datagrid('deleteRow', cc[j]);
                    }
                }
            });
        }else {
            $.messager.confirm('系统提示', '确定要进行删除操作吗', function (r) {

                if (r) {
                    var strIds = "";
                    for (var i = 0; i < deleteRows.length; i++) {
                        strIds += deleteRows[i].id + ",";
                    }
                    strIds = strIds.substr(0, strIds.length - 1);
                    del(strIds);
                }
            });
        }
    });

    //批量删除
    function del(ids) {
        $.ajax({
            'type': 'POST',
            'url': basePath + '/dict/del',
            'contentType': 'application/json',
            'data': ids,
            'dataType': 'json',
            'success': function (data) {
                if (data.data == 'success') {
                    $.messager.alert("提示消息", data.code + "条记录，已经删除");
                    loadTypeDict();
                    loadLabelDict();
                } else {
                    $.messager.alert('提示', "删除失败", "error");
                }
            },
            'error': function (data) {
                $.messager.alert('提示', "保存失败", "error");
            }
        });
    }

    //保存
    $("#saveBtn").on('click', decide);

    function save(){
        var beanChangeVo = {};
        for(var i = 0;i<inserted.length;i++){
            inserted[i].inputCode = makePy(inserted[i].label)[0];
        }
        for(var i = 0;i<updated.length;i++){
            updated[i].inputCode = makePy(updated[i].label)[0];
        }
        beanChangeVo.inserted = inserted;// inserted;
        beanChangeVo.updated = updated; //updated;
        if (beanChangeVo) {
            $.postJSON(basePath + '/dict/merge', JSON.stringify(beanChangeVo), function (resp) {
                if (resp.data == 'success') {
                    $.messager.alert("提示消息","保存成功!");
                    loadTypeDict();
                }else{
                    $.messager.alert('提示', "保存失败", "error");
                    loadTypeDict();
                }
            }, function (data) {
                $.messager.alert('提示', "保存失败", "error");
                loadTypeDict();
            });
        }
    }

    function decide(){
        tempSave();
        if(inserted.length > 0 || updated.length > 0){
            $.messager.confirm('提示', '您要保存刚才的操作吗?', function (r) {
                if (r) {
                    save();
                } else {
                    inserted = [];
                    updated = [];
                    loadTypeDict();
                }
            });
        }
    }

    function trim(value) {
        if (typeof(value) != "undefined") {
            value = value.trim();
            return value;
        }
    }

    //查询
    $("#searchBtn").on('click',function(){
        var selectName = $("#name").val();
        selectName=encodeURI(selectName);
        $.get(basePath + '/dict/search?type='+selectName+'&description='+selectName,function(data){
            $("#descrip").datagrid('loadData',data);
            $("#label_value").datagrid('clearSelections');
        });
    });
});
