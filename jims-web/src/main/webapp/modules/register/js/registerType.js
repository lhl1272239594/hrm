var rowNum=-1;

function onloadMethod(id,clinicName){
    if(id!="" || id!=null){
        rowNum=-1;
    }
    $("#type").val(clinicName);
    $("#clinicTypeId").val(id);
    $('#list_data').datagrid({
        iconCls:'icon-edit',//图标
        width: 'auto',
        height: 'auto',
        nowrap: false,
        striped: true,
        border: true,
        method:'get',
        collapsible:false,//是否可折叠的
        //fit: true,//自动大小
        url:basePath+'/clinicType/itemList?typeId='+id,
        remoteSort:false,
        idField:'id',
        singleSelect:true,//是否单选
        //pagination:true,//分页控件
        //pageSize:15,
        //pageList: [10,15,30,50],//可以设置每页记录条数的列表
        columns:[[      //每个列具体内容
            {field:'chargeItem',title:'收费项目',width:'24%',align:'center',formatter:itemFormatter,editor:{
                type:'combobox',
                options:{
                    data :item,
                    valueField:'value',
                    textField:'label',
                    required: true
                }}
            },
            {field:'priceItem',title:'项目名称',width:'25%',align:'center',formatter:function(value, rowData, rowIndex){
                    return priceItmeFormatter(value,'','')
            },editor:{
                type:'combogrid',
                options:{
                    data:priceItme,
                    idField:'item_code',
                    required: true,
                    textField:'item_name',
                    columns:[[
                        {field:'item_code',title:'项目代码',width:120},
                        {field:'item_name',title:'项目名称',width:120},
                        {field:'price',title:'项目价格',width:70}
                    ]],
                    onClickRow: function (index, data) {
                        var itemName = $("#list_data").datagrid('getEditor',{index:rowNum,field:'itemName'});
                        $(itemName.target).textbox('setValue',data.item_code);
                        var price = $("#list_data").datagrid('getEditor',{index:rowNum,field:'price'});
                        $(price.target).textbox('setValue',data.price);
                }
                }
            }},
            {field:'itemName',title:'项目代码',width:'25%',editor: {type:'textbox',options:{editable:false,disable:false}},align:'center',formatter:function(value, rowData, rowIndex){
                return rowData.priceItem;
            }
            },
            {field:'price',title:'项目价格',width:'24%',editor: {type:'textbox',options:{editable:false,disable:false}},align:'center',formatter:function(value, rowData, rowIndex){
                if (value==undefined) {
                    value=0;
                }
                return value+"/元";
             }
            },
        ]],
        frozenColumns:[[
            {field:'ck',checkbox:true}
        ]],
        toolbar: [{
            text: '新增号类',
            iconCls: 'icon-add',
            handler: function() {
                $("#clinicTypeId").val('');
                $("#type").val('');
                $("#type").focus()
                $('#list_data').datagrid('loadData', { total: 0, rows: [] });
                $("#list_data").datagrid('insertRow', {
                    index:0,
                    row:{

                    }
                });
                rowNum=0;
                $("#list_data").datagrid('beginEdit', rowNum);
            }
        },'-',{
            text: '增加',
            iconCls: 'icon-add',
            handler: function() {
                if (!$('#list_data').datagrid('validateRow', rowNum)) {
                    $.messager.alert('提示', "请填写完本行数据后，再添加下一行", "Warning");
                    return false;
                }
                if(rowNum!=-1){
                    $("#list_data").datagrid('endEdit', rowNum);
                }
                $("#list_data").datagrid('insertRow', {
                    index:0,
                    row:{

                    }
                });
                rowNum=0;
                $("#list_data").datagrid('beginEdit', rowNum);
            }
        },'-',{
            text: '删除',
            iconCls: 'icon-remove',
            handler: function(){
                deleteItem();
            }
        },'-',{
            text: '保存',
            iconCls:'icon-save',
            handler:function(){
                save();
            }
        }
        ],onClickRow: function (rowIndex, rowData) {
            var dataGrid=$('#list_data');
            if(!dataGrid.datagrid('validateRow', rowNum)){
                return false
            }
            if(rowNum!=rowIndex){
                if(rowNum>=0){
                    dataGrid.datagrid('endEdit', rowNum);
                }
                rowNum=rowIndex;
                dataGrid.datagrid('beginEdit', rowIndex);
                var itemName = $("#list_data").datagrid('getEditor',{index:rowIndex,field:'priceItem'});
                var code=$(itemName.target).combobox('getValue');
                var itemName = $("#list_data").datagrid('getEditor',{index:rowIndex,field:'itemName'});
                $(itemName.target).textbox('setValue',code);
            }
        }
    });
    ////设置分页控件
    //var p = $('#list_data').datagrid('getPager');

}
//号类列表
function clinicTypeList(){
    var typeHtml='';
    $.get(basePath + '/clinicType/findList',function(data){
        for(var i=0;i<data.length;i++){
            typeHtml+='<li><a  onclick="onloadMethod(\''+data[i].id+'\',\''+data[i].clinicTypeName+'\')">'+data[i].clinicTypeName+'</a><a  class="rp-close" onclick="deleteClinicType(\''+data[i].id+'\')">X</a></li>';
        }
        $("#clinicType").html(typeHtml);
    })
}
//加载号类列表
clinicTypeList();

//保存数据
function save(){
    if (!$('#list_data').datagrid('validateRow', rowNum)) {
        $.messager.alert('提示', "请填写完本行数据后，再保存", "Warning");
        return false;
    }
    var  rows=$('#list_data').datagrid('getRows');
    if(rows==null || rows==''){
        $.messager.alert('提示', "请添加收费项目", "Warning");
        return false;
    }
    var type=encodeURI($("#type").val());
    if(type=='' || type==null){
        $.messager.alert('提示',"挂号类型不能为空", "Warning");
        return false;
    }
    $("#list_data").datagrid('endEdit', rowNum);
    var  rows=$('#list_data').datagrid('getRows');
    var tableJson=JSON.stringify(rows);
    var clinicTypeId=$("#clinicTypeId").val();
    $.postJSON(basePath+'/clinicType/saveItem?type='+type+'&clinicTypeId='+clinicTypeId,tableJson,function(data){
        if(data.code=='1'){
            $.messager.alert("提示消息","保存成功");
            $("#clinicTypeId").val('');
            $("#type").val('');
            $('#list_data').datagrid('loadData', { total: 0, rows: [] });
            clinicTypeList();
        }else{
            $.messager.alert('提示',"保存失败", "error");
        }
    },function(data){
        $.messager.alert('提示',"保存失败", "error");
    })
}
//修改数据
function editItem(){
    var selectRows = $('#list_data').datagrid("getSelections");
    if (selectRows.length < 1) {
        $.messager.alert("提示消息", "请选中要修改的数据!");
        return;
    }
}
//删除数据
function deleteItem(){
    var selectRows = $('#list_data').datagrid("getSelections");
    if (selectRows.length < 1) {
        $.messager.alert("提示消息", "请选中要删的数据!");
        return;
    }
    $.messager.confirm("确认消息", "您确定要删除信息吗？", function (r) {
        if (r) {
            var strIds = "";
            for (var i = 0; i < selectRows.length; i++) {
                if(selectRows[i].id=="" || selectRows[i].id==undefined){
                    $('#list_data').datagrid('deleteRow', $('#list_data').datagrid('getRowIndex', selectRows[i]));

                }
                strIds += selectRows[i].id + ",";
            }
            strIds = strIds.substr(0, strIds.length - 1);
            if(strIds=="" || strIds=="undefined"){

                return false
            }
            //删除
            $.ajax({
                'type': 'POST',
                'url': basePath+'/clinicType/delete',
                'contentType': 'application/json',
                'data': id=strIds,
                'dataType': 'json',
                'success': function(data){
                    if(data.code=='1'){
                        $.messager.alert("提示消息",data.code+"条记录删除成功！");
                        $('#list_data').datagrid('load');
                        $('#list_data').datagrid('clearChecked');
                    }else{
                        $.messager.alert('提示',"删除失败", "error");
                    }
                },
                'error': function(data){
                    $.messager.alert('提示',"删除失败", "error");
                }
            });
        }
    })
}
//删除号类
function deleteClinicType(typeId){

    $.messager.confirm("确认消息", "您确定要删除信息吗？", function (r) {
        if(r){
            $.ajax({
                'type': 'POST',
                'url': basePath+'/clinicType/deleteClinicType',
                'contentType': 'application/json',
                'data': id=typeId,
                'dataType': 'json',
                'success': function(data){
                    if(data.code=='1'){
                        $.messager.alert("提示消息",data.code+"条记录删除成功！");
                        clinicTypeList();
                        onloadMethod('','');
                    }else if(data.code=='0'){
                        $.messager.alert('提示',"请先删除有关此号类的号别数据");
                        clinicTypeList();
                        onloadMethod('','');
                    }
                },
                'error': function(data){
                    $.messager.alert('提示',"删除失败", "error");
                }
            });
        }


    })
}

/**
 * 校验号类名称
 * @param inp
 * @returns {boolean}
 */
function loseFocus(inp){
    var id=$("#clinicTypeId").val();
    var name=$(inp).val();
    if(id=="" || id==null){
        if(name=="" || name==null){
            return false;
        }
        $.ajax({
            'type': 'get',
            'url': basePath+'/clinicType/check',
            'contentType': 'application/json',
            'data':"clinicName="+ name,
            'dataType': 'json',
            'success': function(data){
                if(data.data!="" && data.data!=null){
                    $.messager.alert('提示',"号类名称不能重复");
                    $(inp).val("");
                }
            }
        });
    }
}
