/**
 * 系统服务维护
 * @author txb
 * @version 2016-05-31
 */

$(function () {
    var ue = UE.getEditor('editor1');
    var editIndex = undefined;
    var stopEdit = function () {
        if (editIndex || editIndex == 0) {
            $("#serviceDetailDg").datagrid('endEdit', editIndex);
            editIndex = undefined;
        }
    };
    var checkedMenus = []; //选中菜单
    var flag = 0;//增加修改状态
    /**
     * 服务数据框
     */
    $("#serviceDg").datagrid({
        title: '基础服务维护',
        fit: true,
        fitColumns: true,
        singleSelect: true,
        toolbar: '#tb',
        method: 'GET',
        rownumbers: true,

        loadMsg: '数据正在加载中，请稍后.....',
        columns: [[{
            title: "id",
            field: "id",
            hidden: true
        }, {
            title: "服务名称",
            field: "serviceName",
            width: '16%',
            align: 'center',
            editor:{
                type:"textbox",options:{

                }
            }

        }, {
            title: "服务描述",
            field: "tranServiceDescription",
            width: '11%',
            align: 'center',
            hidden:true,
            editor:{
                type:"textbox",options:{

                }
            }
        }, {
            title: "服务类型",
            field: "serviceType",
            width: '17%',
            align: 'center',
            formatter: function (value,row,index) {
                if (value == "0"){
                    return '无偿服务'
                }else if(value == "1"){
                    return '有偿服务'
                }else {
                    return ''
                }
            }
        }, {
            title: "服务类别",
            field: "serviceClass",
            width: '11%',
            align: 'center',
            formatter: function (value,row,index) {
                if (value == "0"){
                    return '机构服务'
                }else if(value == "1"){
                    return '个人服务'
                }else if(value == "2"){
                    return '所有服务'
                }else {
                    return ''
                }
            }
        }, {
            title: "服务图片",
            field: "serviceImage",
            width: '66%',
            align: 'center',
            formatter: function (value,index,row) {
                return "<img src='"+value+"' style='width:50px;height:50px;'/>"
            }
        }
        ]],
        onDblClickRow:function(index,row){
//            var ue2=UE.getEditor("editor2");
//            ue2.setContent("");//清空编辑器
//            if(row.serviceDescription!=null){
//                ue2.setContent(row.serviceDescription);
//            }
            $("#serviceDialog1").html("");
            $("#serviceDialog1").html(row.tranServiceDescription);
            $("#serviceDialog1").dialog("open");
         }

    });
    /**
     * 服务名称
     */
    $("#serviceName").textbox({
            width:'200px'
        }
    );
    /**
     * 服务类型
     */
    $("#serviceType").combobox({
        valueField:"value",
        textField:"text",
        width:'200px',
        data: [{
            text: '无偿服务',
            value: "0"
        },{
            text: '有偿服务',
            value: "1",
            selected:true
        }]
    });
    /**
     * 服务类别
     */
    $("#serviceClass").combobox({
        valueField:'value',
        textField:'text',
        width:'200px',
        data: [{
            text: '机构服务',
            value: "0"
        },{
            text: '个人服务',
            value: "1",
            selected:true
        },{
            text: '所有服务',
            value: "2"
        },{
            text: '机构管理服务',
            value: "3"
        }]
    });

    /**
     * 服务弹出框
     */
    $("#serviceDialog").dialog({
        title: '基础服务增加',
        width: 600,
        height: 500,
        closed:true

    });
    $("#serviceDialog1").dialog({
        title: '基础服务描述',
        width: 580,
        height: 250,
        closed:true
     });
    /**
     * 服务定位
     */
    $("#serviceNameLocation").textbox({
        buttonIcon:"icon-search",
        onClickButton: function () {
            var rows = $("#serviceDg").datagrid("getRows");
            var value = $("#serviceNameLocation").textbox("getValue");
            $.each(rows, function (index,row) {
                if(row.serviceName.indexOf(value) != -1){
                    $("#serviceDg").datagrid("scrollTo",index);
                    $("#serviceDg").datagrid("selectRow",index);
                }
            });
        }
    });

    /**
     * 添加
     */
    $("#addBtn").on('click', function () {
        reset();
        flag = 1;
        $("#serviceType").combobox("setValue","0");
        $("#serviceClass").combobox("setValue","0");
        $("#serviceDialog").dialog("setTitle","基础服务添加").dialog("open");
    });
    /**
     * 修改
     */
    $("#editBtn").on('click', function () {
        reset();
        flag = 0;
        var row = $("#serviceDg").datagrid("getSelected");
        if(!row){
            $.messager.alert("提示","请选择一个服务",'error');
            return;
        }
        $("#id").textbox("setValue",row.id);
        $("#serviceName").textbox("setValue",row.serviceName);
        $("#serviceType").combobox("setValue",row.serviceType);
        $("#serviceClass").combobox("setValue",row.serviceClass);
        //service.serviceImage = $("#serviceImage").filebox("setValue",row.serviceImage);
//        $("#serviceDescription").val(row.serviceDescription);//version 1.1
        if(row.tranServiceDescription!=null){
            ue.setContent(row.tranServiceDescription);
        }
        $("#serviceDialog").dialog("setTitle","基础服务修改").dialog("open");
     });

    /**
     * 删除
     */
    $("#delBtn").on('click', function () {
        stopEdit();
        var row = $("#serviceDg").datagrid('getSelected');
        if (row == null) {
            $.messager.alert("系统提示", "请选择要删除的项目");
            return;
        }
        if (!row.id) {
            //判断是否是新加项目
            var index = $("#serviceDg").datagrid('getRowIndex', row);

            $.messager.confirm('系统提示', '确定要进行删除操作吗', function (r) {
                if (r) {
                    $("#serviceDg").datagrid('deleteRow', index);
                }
            });

        } else {
            $.messager.confirm('系统提示', '确定要进行删除操作吗', function (r) {
                if (r) {
                    $.postJSON(basePath + "/sys-service/del", row.id, function (data) {
                        $.messager.alert('系统提示', '删除成功', 'info');
                        loadDict();
                    })
                }
            });
        }

    });

    /**
     * 保存
     */
    $("#submitBtn").on('click', function () {
        if(!$("#serviceName").textbox("getValue")){
            $.messager.alert("提示","请输入必填项",'error');
            return ;
        }
        var row = $("#serviceDg").datagrid("getSelected");
        var maxsize = 2*1024*1024;//2M
        var errMsg = "";
        var browserCfg = {};
        var ua = window.navigator.userAgent;
//        console.log(ua);
        if (ua.indexOf("MSIE")>=1){//IE8以及以下的浏览器
            browserCfg.ie = true;
        }
        var obj_file = document.getElementById("serviceImage");
        if(obj_file.value==""){
            errMsg = "请先选择上传文件";
            if(flag == 1){$.messager.alert("系统提示", errMsg,"error"); return; }
            if(row && !row.id){$.messager.alert("系统提示", errMsg,"error"); return; }
        }
        var serviceImage = $("#serviceImage").val();
        var suffer=serviceImage.substring(serviceImage.lastIndexOf(".")+1).toLowerCase();
        if(suffer!="jpg"&&suffer!="png"&&suffer!="gif"&&suffer!="jpeg"&&suffer!="bmp"&&suffer!="swf"){
            errMsg = "请选择正确的图片格式<br/><br/>建议格式：jpg,jpeg,png,gif,bmp,swf";
            if(flag == 1){$.messager.alert("系统提示", errMsg,"error"); return; }
            if(row && !row.id){$.messager.alert("系统提示", errMsg,"error"); return; }
        }
        var filesize = 0;
        if(!browserCfg.ie){
            if(obj_file.files[0]){
                filesize = Math.round(obj_file.files[0].size);
            }
        }else{
            var ImgObj;
            if(obj_file.value.indexOf("fakepath")>0){
                obj_file.select();
                window.parent.document.body.focus();
                var realpath = document.selection.createRange().text;
                if(realpath.indexOf("file:")==0){
                    realpath = realpath.substr(0,9)+"|"+realpath.substring(10,realpath.length);
                }else{
                    realpath = "file:///"+realpath.substr(0,1)+"|"+realpath.substring(2,realpath.length);
                }
                ImgObj=document.createElement("img");
                ImgObj.src=realpath;
                filesize=Math.round(ImgObj.fileSize);//取得图片文件的大小
            }else{
                ImgObj=new Image();
                ImgObj.src=obj_file.value;
                filesize=Math.round(ImgObj.fileSize);//取得图片文件的大小
            }
        }
        if(filesize=='NaN'||filesize==0||filesize==-1){
            errMsg = "浏览器版本过低，暂不支持计算上传文件的大小，建议使用高版本浏览器";
            if(flag == 1){$.messager.alert("系统提示", errMsg,"error"); return; }
            if(row && !row.id){$.messager.alert("系统提示", errMsg,"error"); return; }
        }else if(filesize>maxsize){
            errMsg = "请上传小于2M的图片，当前照片大小为"+Math.round(filesize/1024/1024*100)/100+"M";
            alert(errMsg);
            return;
        }
        var serviceDescription=ue.getContent();//获得带格式的文本
        if(serviceDescription!=null){
            $("#serviceDescription").text(serviceDescription);
        }else{
            $.messager.alert("请输入服务描述！");
            return;
        }

        var oData = new FormData(document.getElementById("serviceForm"));

        $.ajax({
            url: basePath + "/sys-service/save?serviceDescription="+$("#serviceDescription").text() ,
            type: 'POST',
            data:  oData,
            async: false,
            cache: false,
            contentType: false,
            processData: false,
            success: function (returndata) {
                    $.messager.alert('系统提示', '保存成功', 'info');
                    loadDict();
                    reset();
                $("#serviceDialog").dialog("close");
            },
            error: function (returndata) {
                    $.messager.alert('系统提示', '保存失败', 'error');
                    loadDict();
                    reset();
            }
        });
        //$.postJSON(basePath + "/sys-service/save", JSON.stringify(oData), function (data) {
        //    $.messager.alert('系统提示', '保存成功', 'info');
        //    flag = 0;
        //    loadDict();
        //    reset();
        //})


    });

    /**
    *基础服务价格模态
    */
    $("#serviceDetailDialog").dialog({
        title: '基础服务价格',
        width: 1000,
        height: 350,
        closed:true

    });
    /**
    *基础服务菜单模态
    */
    $("#serviceMenuDialog").dialog({
        title: '服务菜单维护',
        width: 400,
        height:450,
        closed:true

    });
    /**
     *基础服务价格数据框
     */
     $("#serviceDetailDg").datagrid({
        fit: true,
        fitColumns: true,
        //striped: true,
        singleSelect: true,
        toolbar: '#serviceDetailTb',
        method: 'GET',
        rownumbers: true,
        loadMsg: '数据正在加载中，请稍后.....',
        columns: [[{
            title: "id",
            field: "id",
            hidden: true
        },{
            title:"serviceId",
            field:"serviceId",
            hidden:true
        },{
            title:"服务价格",
            field:"servicePrice",
            width:"40%",
            editor:{
                type:"textbox",options:{

                }
            }
        },{
            title:"服务时限",
            field:"serviceTimeLimit",
            width:"40%",
            editor:{
                type:"combobox",options:{
                    valueField:"value",
                    textField:"text",
                    data:[{
                        text:"年",
                        value:"年"
                    },{
                        text:"月",
                        value:"月"
                    }]
                }
            }
        }]],
         onSelect:function(rowIndex,rowDate){
             if (editIndex == undefined) {
                 $("#serviceDetailDg").datagrid("beginEdit", rowIndex);
                 editIndex = rowIndex;
             } else {
                 $("#serviceDetailDg").datagrid("endEdit", editIndex);
                 $("#serviceDetailDg").datagrid("beginEdit", rowIndex);
                 editIndex = rowIndex;
             }
         }

    });

    /**
     * 明细维护
     */
    $("#detailBtn").on("click", function () {
        $("#serviceDetailDg").datagrid("loadData",{total:0,rows:[]});
        var row = $("#serviceDg").datagrid("getSelected");
        if(row){
            var url = basePath + "/sys-service/detail-list?serviceId=" + row.id;
            $("#serviceDetailDg").datagrid("reload",url);
            $("#serviceDetailDialog").dialog("open");
        }else{
            $.messager.alert("提示","请选择一个服务项目","info");
        }

    });
    /**
     * 基础服务价格添加
     */
    $("#addDetailBtn").on("click", function () {
        stopEdit();
        var rows = $("#serviceDetailDg").datagrid("getRows");

        if(rows.length >= 2){
            $.messager.alert("提示","最多只能添加一个月价格，一个年价格","error");
            return;
        }
        var row = $("#serviceDg").datagrid("getSelected");
        if(rows.length ==0){
            $("#serviceDetailDg").datagrid("appendRow",{serviceId:row.id,serviceTimeLimit:'月'});
        }else{
            $("#serviceDetailDg").datagrid("appendRow",{serviceId:row.id,serviceTimeLimit:'年'});

        }
        var rows = $("#serviceDetailDg").datagrid("getRows");
        var addRowIndex = $("#serviceDetailDg").datagrid('getRowIndex', rows[rows.length - 1]);
        editIndex = addRowIndex;
        $("#serviceDetailDg").datagrid('selectRow', editIndex);
        $("#serviceDetailDg").datagrid('beginEdit', editIndex);


    });
    /**
     * 基础服务价格修改
     */
    $("#editDetailBtn").on("click", function () {
        var row = $("#serviceDetailDg").datagrid("getSelected");
        var index = $("#serviceDetailDg").datagrid("getRowIndex", row);

        if (index == -1) {
            $.messager.alert("提示", "请选择要修改的行！", "info");
            return;
        }

        if (editIndex == undefined) {
            $("#serviceDetailDg").datagrid("beginEdit", index);
            editIndex = index;
        } else {
            $("#serviceDetailDg").datagrid("endEdit", editIndex);
            $("#serviceDetailDg").datagrid("beginEdit", index);
            editIndex = index;
        }
    });
    /**
     * 基础服务价格删除
     */
    $("#delDetailBtn").on("click", function () {
        var row = $("#serviceDetailDg").datagrid('getSelected');
        if (row) {
            var rowIndex = $("#serviceDetailDg").datagrid('getRowIndex', row);
            $("#serviceDetailDg").datagrid('deleteRow', rowIndex);
            if (editIndex == rowIndex) {
                editIndex = undefined;
            }
        } else {
            $.messager.alert('系统提示', "请选择要删除的行", 'info');
        }
    });
    /**
     * 基础服务价格保存
     */
    $("#submitDetailBtn").on("click",function(){
        var flag = 0;

        if (editIndex || editIndex == 0) {
            $("#serviceDetailDg").datagrid("endEdit", editIndex);
        }
        var rows = $("#serviceDetailDg").datagrid("getRows");
        if(rows.length == 0){
            $.messager.alert("提示","请添加一条数据","error");
            return;
        }else{
            $.each(rows, function (index,row) {
                if(!row.servicePrice){
                    flag = 1;
                }
            });

        if(flag == 1){
            $.messager.alert("提示","添加服务价格","error");
            return;
        }
        }
        if(rows.length >= 2){
            if(rows[0].serviceTimeLimit == rows[1].serviceTimeLimit){
                $.messager.alert("提示","最多只能添加一个月价格，一个年价格","error");
                return;
            }
        }

        var insertData = $("#serviceDetailDg").datagrid("getChanges", "inserted");
        var updateDate = $("#serviceDetailDg").datagrid("getChanges", "updated");
        var deleteDate = $("#serviceDetailDg").datagrid("getChanges", "deleted");

        var priceBeanVo = {};
        priceBeanVo.inserted = insertData;
        priceBeanVo.deleted = deleteDate;
        priceBeanVo.updated = updateDate;

        if (priceBeanVo) {
            $.postJSON(basePath + "/sys-service/save-detail", JSON.stringify(priceBeanVo), function (data) {
                $.messager.alert("系统提示", "保存成功", "info");
                loadDetail()
            }, function (data) {
                $.messager.alert("系统提示", "保存失败", "error");
                $('#serviceDetailDg').datagrid('loadData', { total: 0, rows: [] });
            })
        }
    });




    /**
     * 菜单维护
     */
    $("#menuBtn").on("click", function () {
        var row = $("#serviceDg").datagrid("getSelected");
        if(row){
            var menus=[];
            var menuTreeData=[];
            var serviceVsMenu;
            checkedMenus = [];
            $.ajax({
                type: 'get',
                async:false,
                url: basePath + "/sys-service/service-vs-menu-list?serviceId=" + row.id,
                contentType: 'application/json',
                dataType: 'json',
                success: function(data){
                    serviceVsMenu = data
                },
                error: function(data){

                }
            });
            var menuPromise = $.get(basePath + "/menuDict/list", function (data) {
                $.each(data,function(index,item){
                    var menu ={} ;
                    menu.id = item.id ;
                    menu.pid = item.pid ;
                    menu.text = item.menuName ;
                    menu.state = 'open' ;
                    menu.checked = false ;
                    menu.children=[] ;
                    menus.push(menu) ;
                });
                for(var i = 0 ;i<menus.length;i++){
                    //判断儿子节点
                    for(var j = 0 ;j<menus.length;j++){
                        if(menus[i].id ==menus[j].pid){
                            menus[i].children.push(menus[j]) ;
                        }
                    }
                    //判断服务菜单选中
                    for(var x = 0 ; x<serviceVsMenu.length;x++){
                        if (serviceVsMenu[x].menuId == menus[i].id && menus[i].children.length == 0 ){
                            menus[i].checked = true;
                        }
                    }
                    //判断是不是根节点  start
                    if(menus[i].children.length>0 && !menus[i].pid){
                        menuTreeData.push(menus[i]) ;
                    }

                    if(!menus[i].pid&&menus[i].children.length<=0){
                        menuTreeData.push(menus[i]) ;
                    }
                    //判断是不是根节点  end
                }
            });

            menuPromise.done(function () {
                $("#serviceMenuTree").tree('loadData',menuTreeData) ;
            });
            $("#serviceMenuDialog").dialog("open");
        }else{
            $.messager.alert("提示","请选择一个服务项目","info");
        }
    });
    /**
     * 菜单树
     */
    $("#serviceMenuTree").tree({
        method:'get',
        animate:true,
        checkbox:true
    });
    /**
     * 菜单明细保存
     */
    $("#submitMenuBtn").on("click", function () {
        var row = $("#serviceDg").datagrid("getSelected");
        var menuVsServices  = [];
        var menuVsServicesParent  = [];
        var menus = $('#serviceMenuTree').tree('getChecked');
        for (var n = 0;n<menus.length;n++){
            if(menus[n].children.length == 0){
                var menuVsService = {};
                menuVsService.serviceId = row.id;
                menuVsService.menuId = menus[n].id;
                menuVsServices.push(menuVsService);
            }

            var parentNode = $('#serviceMenuTree').tree('getParent',menus[n].target);
            var parentPid;
            if(parentNode){
                parentPid = parentNode.pid ;
                do{
                    var menuVsServiceParentNode = {};
                    var ok = 0;//是否父节点存在标志

                    if(menuVsServicesParent.length == 0 ){
                        menuVsServiceParentNode.serviceId = row.id;
                        menuVsServiceParentNode.menuId = parentNode.id;
                        menuVsServicesParent.push(menuVsServiceParentNode);
                    }
                    for(var i = 0; i < menuVsServicesParent.length ; i++){
                        if(menuVsServicesParent[i].menuId == parentNode.id){
                            ok = 1;
                            break;
                        }
                    }
                    if(ok==0 ){
                        menuVsServiceParentNode.serviceId = row.id;
                        menuVsServiceParentNode.menuId = parentNode.id;
                        menuVsServicesParent.push(menuVsServiceParentNode);
                    }
                        var parentNode = $('#serviceMenuTree').tree('getParent',parentNode.target);
                        if(parentNode){
                            parentPid = parentNode.pid;
                        }
                }while(parentPid)
            }


        }

        if(menuVsServices.concat(menuVsServicesParent).length > 0){
            $.postJSON(basePath + "/sys-service/save-serviceVsMenu",JSON.stringify(menuVsServices.concat(menuVsServicesParent)), function () {
                $.messager.alert("系统提示", "保存成功", "info");
            })
        }else{
            $.messager.alert("系统提示", "请选择菜单", "info");
        }


    });

    /**
     *全部展开
     */
    $("#menuExpandBtn").on("click", function () {
        $('#serviceMenuTree').tree('expandAll');
    });
    /**
     *全部折叠
     */
    $("#menuCollapseBtn").on("click", function () {
        $('#serviceMenuTree').tree('collapseAll');
    });
    /**
     *菜单查询
     */
    $("#menuSelectBtn").on("click", function () {
        var menuName = $("#searchMenu").textbox("getValue");
        if(menuName){
            //调用查询方法 返回id
            var node = $('#serviceMenuTree').tree('find','35E111DB41F9420B9B19B200A41488CB');
            $('#serviceMenuTree').tree('expandTo', node.target);
            $('#serviceMenuTree').tree('scrollTo', node.target).tree('select', node.target);
        }
    });

    /**
     * 加载服务
     */
    var loadDict = function () {
        $.get(basePath + "/sys-service/list" , function (data) {
            $("#serviceDg").datagrid('loadData', data);
        });
    };
    /**
     * 加载服务明细
     */
    var loadDetail = function () {
        var row = $("#serviceDg").datagrid("getSelected");
        $.get(basePath + "/sys-service/detail-list?serviceId=" + row.id , function (data) {
            $("#serviceDetailDg").datagrid('loadData', data);
        });
    };
    /**
     * 重置服务明细
     */
    var reset = function () {
        $("#id").textbox("setValue","");
        $("#serviceName").textbox("setValue","");
        $("#serviceType").combobox("setValue","");
        $("#serviceClass").combobox("setValue","");
        $("#serviceImage").val("");
        $("#serviceDescription").val("");
        ue.setContent("");
    };

    loadDict();

//    服务参数设置

    var paramEditIndex = undefined ;

    $("#serviceParamDialog").dialog({
        modal:true,
        width:800,
        height:400,
        title:'参数设置',
        closed:true,
        onBeforeOpen:function(){
            var row = $("#serviceDg").datagrid('getSelected') ;
            var serviceId = row.id ;

            var options = $("#serviceParamDg").datagrid('options') ;
            options.url= basePath+"/service-param/list?serviceId="+serviceId ;
            $("#serviceParamDg").datagrid('reload') ;
        }
    })

    //定义参数表格
    $("#serviceParamDg").datagrid({
        fit:true,
        fitColumn:true,
        method:'GET',
        columns:[[{
            title:'id',
            field:'id',
            hidden:true
        },{
            title:'serviceId',
            field:'serviceId',
            hidden:true
        },{
            title:'参数名称',
            field:'paramName',
            width:'20%',
            editor:{type:'text',options:{}}
        },{
            title:'值域',
            field:'valueRange',
            width:'45%',
            editor:{type:'text',options:{}}
        },{
            title:"参数描述",
            field:"paramDesp",
            width:'30%',
            editor:{type:'text',options:{}}
        }]],
        onDblClickRow:function(index,row){
            if(paramEditIndex==0||paramEditIndex){
                $("#serviceParamDg").datagrid('endEdit',paramEditIndex) ;
                paramEditIndex = index ;
            }else{
                paramEditIndex = index ;
            }
            $("#serviceParamDg").datagrid('beginEdit',paramEditIndex) ;
        }
    }) ;

    //添加参数按钮
    $("#addServiceParamBtn").on('click',function(){
        var row = $("#serviceDg").datagrid('getSelected') ;
        if(paramEditIndex==undefined){
            var test = $("#serviceParamDg").datagrid('appendRow',{serviceId:row.id}) ;
            var rows = $("#serviceParamDg").datagrid('getRows') ;
            var temIndex = $("#serviceParamDg").datagrid('getRowIndex',rows[rows.length-1]) ;
            paramEditIndex = temIndex ;
            $("#serviceParamDg").datagrid('beginEdit',paramEditIndex) ;
        }else{
            $("#serviceParamDg").datagrid('endEdit',paramEditIndex) ;
            $("#serviceParamDg").datagrid("appendRow",{serviceId:row.id})
            var rows = $("#serviceParamDg").datagrid('getRows') ;
            var temIndex = $("#serviceParamDg").datagrid('getRowIndex',rows[rows.length-1]) ;
            paramEditIndex = temIndex ;
            $("#serviceParamDg").datagrid('beginEdit',paramEditIndex) ;
        }
    }) ;

    //修改参数按钮
    $("#editServiceParamBtn").on('click',function(){
        var rows = $("#serviceParamDg").datagrid('getSelections');
        if(rows.length == 0){
            $.messager.alert('提示','请选择要修改的数据！','info');
            return ;
        } else if(rows.length > 1){
            $.messager.alert('提示','一次只能修改一条数据！','info');
            return ;
        }
        var index = $("#serviceParamDg").datagrid('getRowIndex',rows[0])
        $("#serviceParamDg").datagrid('endEdit',paramEditIndex) ;
        paramEditIndex = index ;
        $("#serviceParamDg").datagrid('beginEdit',paramEditIndex) ;
    }) ;

    //删除按钮
    $("#delServiceParamBtn").on('click',function(){

        if(paramEditIndex==0||paramEditIndex){
            $("#serviceParamDg").datagrid('endEdit',paramEditIndex) ;
            paramEditIndex = undefined ;
        }

        var rows = $("#serviceParamDg").datagrid('getSelections') ;
        for(var i = 0 ;i<rows.length;i++){
            var tempIndex = $("#serviceParamDg").datagrid('getRowIndex',rows[i]) ;
            $("#serviceParamDg").datagrid('deleteRow',tempIndex) ;
        }

    })

    //保存按钮
    $("#submitServiceParamBtn").on('click',function(){


        var beanChangeVo = {} ;
        beanChangeVo.inserted=[] ;
        beanChangeVo.deleted=[] ;
        beanChangeVo.updated=[] ;

        if(paramEditIndex==0||paramEditIndex){
            $("#serviceParamDg").datagrid('endEdit',paramEditIndex) ;
            paramEditIndex=undefined ;
        }

        var inserted = $("#serviceParamDg").datagrid('getChanges','inserted') ;

        for(var i = 0 ;i<inserted.length;i++){
            if(inserted[i].paramName){
                beanChangeVo.inserted.push(inserted[i]) ;
            }
        }

        var deleted = $("#serviceParamDg").datagrid('getChanges','deleted') ;
        for(var i = 0 ;i<deleted.length;i++){
            if(deleted[i].id){
                beanChangeVo.deleted.push(deleted[i]) ;
            }
        }

        var updated = $("#serviceParamDg").datagrid('getChanges','updated') ;
        for(var i = 0 ;i<updated.length;i++){
            if(updated[i].paramName){
                beanChangeVo.updated.push(updated[i]) ;
            }
        }
        $.postJSON(basePath+"/service-param/merge",JSON.stringify(beanChangeVo),function(data){
            $("#serviceParamDg").datagrid('reload') ;
            $.messager.alert("系统提示","操作成功",'info') ;
        })
    }) ;
    //参数设置按钮
    $("#paramBtn").on('click',function(){

        var row = $("#serviceDg").datagrid('getSelected') ;
        if(row == null){
            $.messager.alert("系统提示","请选择服务!!");
            return ;
        }

        if(row.serviceClass=='1'){
            $.messager.alert("系统提示","个人服务，不许允许定制参数")
            return ;
        }
        $("#serviceParamDialog").dialog("open") ;
    }) ;

/****************菜单修改说明（开始）*******************/

    //服务菜单修改说明弹出框
    $("#menuUpdateExplainDialog").dialog({
        title: '服务菜单修改说明',
        width: 600,
        height: 500,
        closed:true
    });
    $("#explainDialog").dialog({
        title: '服务菜单修改说明',
        width: 600,
        height: 500,
        closed:true,
        buttons: '#explainSaveBtn'
    });
    //服务菜单修改说明数据框
    $("#menuUpdateExplainTable").datagrid({
        fit: true,
        fitColumns: true,
        toolbar: '#explainBtn',
        method: 'GET',
        loadMsg: '数据正在加载中，请稍后.....',
        columns: [[{
            title: "日期",
            field: "updateDate",
            width: '120',
            align: 'center'
        }, {
            title: "标题",
            field: "title",
            width: '300',
            halign: 'center',
            align: 'left'
        }
        ]]
    });
    $('#updateExplainBtn').click(function(){
        var row = $("#serviceDg").datagrid("getSelected");
        if(!row){
            $.messager.alert("提示","请选择一个服务",'error');
            return;
        } else if(!row.id){
            $.messager.alert("提示","新建服务不能维护说明！",'error');
            return;
        }
        $.get(basePath + '/menuUpdateExplain/findList?serviceId='+row.id, function (res) {
            $("#menuUpdateExplainTable").datagrid('loadData',res)
        })
        $("#menuUpdateExplainDialog").dialog('open')
    })
    var explainEditor=UE.getEditor("explainEditor");
    //添加
    $('#addExplainBtn').click(function () {
        explainEditor.setContent('')
        $('#explainTitle').textbox('setValue','')
        $('#explainId').val('')
        $("#explainDialog").dialog("open");
        $("#menuUpdateExplainDialog").dialog('close')
    })
    //修改
    $('#editExplainBtn').click(function () {
        var rows = $("#menuUpdateExplainTable").datagrid('getSelections');
        if(rows.length > 1) {
            $.messager.alert('修改','每次只能修改一条数据！','info');
            return;
        } else if(rows.length == 0){
            $.messager.alert('修改','请选择一条数据！','info');
            return;
        }
        explainEditor.setContent(rows[0].explainStr)
        $('#explainTitle').textbox('setValue',rows[0].title)
        $('#explainId').val(rows[0].id)
        $("#explainDialog").dialog("open");
        $("#menuUpdateExplainDialog").dialog('close')
    })
    //删除
    $('#delExplainBtn').click(function () {
        var rows = $("#menuUpdateExplainTable").datagrid('getSelections');
        if(rows.length == 0){
            $.messager.alert('删除','请选择要删除的数据！','info');
            return;
        }
        var ids = '';
        for(var i=0;i<rows.length ;i++){
            ids += ',' + rows[i].id;
        }
        ids = ids.length == 0 ? ids : ids.substr(1);
        $.get(basePath + '/menuUpdateExplain/delete?ids='+ids,function(res){
            if(res){
                $.messager.alert('删除','删除成功！','info');
                $("#menuUpdateExplainDialog").dialog('close');
            } else {
                $.messager.alert('删除','删除失败！','error');
            }
        })
    })
    //保存
    $('#explainSave').click(function () {
        var row = $("#serviceDg").datagrid("getSelected");
        var record = {
            explain: explainEditor.getContent(),
            serviceId: row.id,
            title: $('#explainTitle').textbox('getValue'),
            id: $('#explainId').val()
        }
        $.get(basePath + '/menuUpdateExplain/saveExplain',record, function (res) {
            if(res){
                $.messager.alert('保存','保存成功！','info');
                $("#explainDialog").dialog("close");
            } else {
                $.messager.alert('保存','保存失败！','error');
            }
        })
    })
    //关闭
    $('#explainClose').click(function () {
         $("#explainDialog").dialog("close");
    })

/****************菜单修改说明（结束）*******************/
});