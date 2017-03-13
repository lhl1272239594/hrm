//药品库存警告
//朱齐
//2016-8-10
$(function(){

    $.extend({
        ajaxAsync : function(url,callback){
            return $.ajax({
                type: 'get',
                url: url,
                async : false,
                success: callback,
                'contentType': 'application/json'
            });
        }
    });

    var editIndex;
    //停止编辑
    var stopEdit = function () {
        if (editIndex || editIndex == 0) {
            $("#menuList").datagrid('endEdit', editIndex);
            editIndex = undefined;
        }
    };
    $("#menuList").datagrid({
        singleSelect: true,
        rownumbers: true,
        loadMsg: '数据正在加载中，请稍后.....',
        columns: [[{
            title: "id",
            field: "id",
            hidden: true
        },{
            title: "菜单ID",
            field: "menuId",
            hidden: true
        }, {
            title: "菜单名称",
            field: "menuName",
            width: '10%',
            align: 'center',
            editor:{
                type:'combogrid',
                options:{
                    panelWidth:460,
                    idField:'menuName',
                    textField:'menuName',
                    fitColumns: true,
                    url : '/service/menuDict/list-by-name',
                    method:'get',
                    mode:'remote',
                    columns:[[
                        {field:'id',title:'菜单ID',hidden:true},
                        {field:'menuName',title:'菜单名称',width:100,align : "center"},
                        {field:'href',title:'路径',width:160,halign : "center",align : "left" }
                    ]],
                    onSelect:function(index,row){
                        loadList(row);
                    }
                }
            }
        }, {
            title: "菜单路径",
            field: "href",
            width: '20%',
            align: 'center'
        }, {
            title: "执行顺序",
            field: "initSort",
            width: '5%',
            align: 'center',
            editor:{
                type:'numberbox'
            }
        }
        ]]
        ,onClickRow: function (index, row) {
            stopEdit();
            $(this).datagrid('beginEdit', index);
            editIndex=index;
        }

    })

    var load=function(){
        $.get('/service/init-process/find-menu-list',function(data){
            $("#menuList").datagrid('loadData',data);
        })
    }
    load();

    var loadList=function(row){
        var list=$('#menuList').datagrid('getSelected');
        list.menuId=row.id;
        list.menuName=row.menuName;
        list.href=row.href;
        $('#menuList').datagrid('endEdit', editIndex);
        $('#menuList').datagrid('beginEdit', editIndex);
    }

    $('#addBtn').on('click',function(){
        stopEdit();
        var len = $('#menuList').datagrid('getRows').length;
        var addRow = {
            id: '',  // 后台生成
            menuId: '',
            menuName: '',
            href: '',
            initSort:''
        }
        editIndex=len;
        $('#menuList').datagrid('insertRow',{index:len,row:addRow});
        $('#menuList').datagrid('selectRow',editIndex);
        $('#menuList').datagrid('beginEdit',editIndex);
    })

    $('#saveBtn').on('click',function(){
        stopEdit();
        var rows=$('#menuList').datagrid('getRows');
        for (var i = 0; i < rows.length; i++) {
            if((!rows[i].menuName||rows[i].menuName=="")&&(!rows[i].href||rows[i].href=="")&&(!rows[i].initSort||rows[i].initSort=="")){
                $('#menuList').datagrid('deleteRow', i);
            }else if((!rows[i].menuName||rows[i].menuName=="")||(!rows[i].href||rows[i].href=="")||(!rows[i].initSort||rows[i].initSort=="")){
                $('#menuList').datagrid('selectRow', i);
                $('#menuList').datagrid('beginEdit', i);
                $.messager.alert('提示', "请保证菜单名称、路径或执行顺序都不为空", "info");
                return;
            }
        }
        console.log(rows);
        $.postJSON('/service/init-process/save-list',JSON.stringify(rows),function(data){
            if(data.data == "success") {
                $.messager.alert('提示', "保存成功", "info");
                load();
            } else {
                $.messager.alert('提示', "保存失败", "error");
                load();
            }
        })
    });

    $('#delBtn').on('click',function(){
        var row=$('#menuList').datagrid('getSelected');
        $('#menuList').datagrid('deleteRow', editIndex);
        if(row.id !=''){
            $.postJSON('/service/init-process/del?id='+row.id,function(data){
                load();
            });
            $.messager.alert('提示', "删除成功", "info");
        }
    })
})