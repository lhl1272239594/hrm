


var basePath = "/service";
var danxt_num=4;//单选题当前选项数量
var duoxt_num=5;//多选题当前选项数量
var orgId='';
var page='1';
var rows='30';
var itemId = '';//试题分类ID
var type = '';//题型ID
var state = '';//状态
var search=false;
var itemObj = [];
var typeObj = [];
$(function () {
    orgId= parent.config.org_Id;

    var obj = new  Object();
    $.get(basePath + '/tool/find-list-by-type?type=ENABLE_FLAG', function (data) {
        $.each(data, function (index, item) {
            obj[item.value]=item.label;
        });
    });
    $("#questionGrid").datagrid({
        toolbar: '#tb',
        iconCls: 'icon-edit',//图标
        width: '100%',
        height: '100%',
        nowrap: false,
        striped: true,
        border: true,
        method: 'get',
        loadMsg: '数据正在加载中，请稍后.....',
        pagination: true,//分页控件
        pageSize: 30,
        pageList: [10, 20, 30, 40, 50], //可以设置每页记录条数的列表
        collapsible: false,//是否可折叠的
        fit: true,//自动大小
        url: basePath + '/exam/questionList?itemId=' + itemId + '&orgId=' + orgId + '&type=' + type + '&state=' + state,
        remoteSort: false,
        idField: 'queId',
        singleSelect: false,//是否单选
        rownumbers: true,//行号
        columns: [[
            {field: 'ck', title: '', checkbox: true},
            {field: 'orgId', title: '', hidden: true},
            {field: 'itemId', title: '', hidden: true},
            {field: 'typeId', title: '', hidden: true},
            {field: 'itemName', title: '试题分类', width: '10%', align: 'center'},
            {field: 'typeName', title: '题型', width: '10%', align: 'center',},
            {field: 'queName', title: '试题题目', width: '40%', align: 'left',halign:'center',formatter : function (value, row, index) {
                if (value.length > 86) value = value.substr(0, 86) + "...";
                return value;
            }},
            {
                field: 'state', title: '状态', width: '10%', align: 'center',
                formatter: function (value, row, index) {
                    return obj[value];
                }
            },
            {field: 'createBy', title: '创建人', width: '10%', align: 'center'
              },
            {field: 'createDate', title: '创建时间', width: '15%', align: 'center'}

        ]],
        onLoadSuccess:function (data) {
            $("#addQuestion").css('display','block');


        }
    });
    $("#questionGrid").datagrid('getPager').pagination({
        pageList: [10, 20, 30, 40, 50], //可以设置每页记录条数的列表
        displayMsg: '当前显示从第{from}条到第{to}条，共{total}条记录',
        onSelectPage: function (pageNumber, pageSize) {
            var state1 = $('#questionGrid').data('datagrid');
            var opts = state1.options;
            page=opts.pageNumber = pageNumber;
            rows=opts.pageSize = pageSize;
            searchData(page,rows);
            return;

        }
    });
    $('#state').combobox({    //停用启用
        url: basePath + '/dict/find-list-by-type?type=' + 'ENABLE_FLAG',
        valueField: 'value',
        textField: 'label',
        value:'请选择',
        method: 'GET'
    });

    $('#type').combobox({    //题型
        url: basePath + '/exam/questionType',
        valueField: 'sort',
        textField: 'name',
        value:'请选择',
        method: 'GET',
        onLoadSuccess: function(data){
            $.each(data, function (index, item) {
                typeObj[item.sort]=item.name;
            });
        }
    });
    $('#type1').combobox({    //题型
        url: basePath + '/exam/questionType',
        valueField: 'sort',
        textField: 'name',
        method: 'GET',
        onChange: function (newValue, oldValue) {
            if (newValue == '1') {
                $("#pdt").show();
                $("#danxt").hide();
                $("#duoxt").hide();
                $("#jdt").hide();
            }
            if (newValue == '2') {
                $("#danxt").show();
                $("#pdt").hide();
                $("#duoxt").hide();
                $("#jdt").hide();
            }
            if (newValue == '3') {
                $("#duoxt").show();
                $("#pdt").hide();
                $("#danxt").hide();
                $("#jdt").hide();
            }
            if (newValue == '4') {
                $("#jdt").show();
                $("#danxt").hide();
                $("#duoxt").hide();
                $("#pdt").hide();
            }
        }
    });

    $("#itemTree").combotree({
        fitColumns: true,
        striped: true,
        singleSelect: true,
        treeWidth: 400,
        value:'请选择',
        loadMsg: '数据正在加载中，请稍后.....',
        columns: [[{
            title: 'id',
            field: "id",
            hidden: true
        }, {
            title: '分类名称',
            field: 'text',
            width: '100%'
        }]],
        onSelect: function (node) {
            //返回树对象
            var tree = $(this).tree;
            //选中的节点是否为叶子节点,如果不是叶子节点,清除选中
            var isLeaf = tree('isLeaf', node.target);
            if (!isLeaf) {
                //清除选中
                $('#itemTree').combotree('clear');
                $.messager.alert("提示", "请选择子分类！", "info");
            }
        }
    });
    var loadDept = function () {

        var depts = [];
        var treeDepts = [];
        var loadPromise = $.get("/service/exam/itemList?orgId=" + orgId, function (data) {
            $.each(data, function (index, item) {
                //console.log(data);
                var obj = {};
                obj.text = item.itemName;
                obj.id = item.itemId;
                obj.parent = item.parent;
                obj.children = [];
                depts.push(obj);
                itemObj[item.itemId]=item.itemName;
            });

        });
        loadPromise.done(function () {
            for (var i = 0; i < depts.length; i++) {
                for (var j = 0; j < depts.length; j++) {
                    if (depts[i].id == depts[j].parent) {
                        depts[i].children.push(depts[j]);
                    }
                }
                if (depts[i].children.length > 0 && !depts[i].parent) {
                    treeDepts.push(depts[i]);
                }

                if (!depts[i].parent && depts[i].children <= 0) {
                    treeDepts.push(depts[i])
                }
            }
            $("#itemTree").combotree('loadData', treeDepts);
        })
    }
    loadDept();
    $("#itemTree1").combotree({
        fitColumns: true,
        striped: true,
        singleSelect: true,
        treeWidth: 400,
        loadMsg: '数据正在加载中，请稍后.....',
        columns: [[{
            title: 'id',
            field: "id",
            hidden: true
        }, {
            title: '分类名称',
            field: 'text',
            width: '100%'
        }]],
        onSelect: function (node) {
            //返回树对象
            var tree = $(this).tree;
            //选中的节点是否为叶子节点,如果不是叶子节点,清除选中
            var isLeaf = tree('isLeaf', node.target);
            if (!isLeaf) {
                //清除选中
                $('#itemTree1').combotree('clear');
                $.messager.alert("提示", "请选择子分类！", "info");
            }
        }
    });
    var loadDept1 = function () {

        var depts = [];
        var treeDepts = [];
        var loadPromise = $.get("/service/exam/itemList?orgId=" + orgId, function (data) {
            $.each(data, function (index, item) {
                //console.log(data);
                var obj = {};
                obj.text = item.itemName;
                obj.id = item.itemId;
                obj.parent = item.parent;
                obj.children = [];
                depts.push(obj);
            });

        });
        loadPromise.done(function () {
            for (var i = 0; i < depts.length; i++) {
                for (var j = 0; j < depts.length; j++) {
                    if (depts[i].id == depts[j].parent) {
                        depts[i].children.push(depts[j]);
                    }
                }
                if (depts[i].children.length > 0 && !depts[i].parent) {
                    treeDepts.push(depts[i]);
                }

                if (!depts[i].parent && depts[i].children <= 0) {
                    treeDepts.push(depts[i])
                }
            }
            $("#itemTree1").combotree('loadData', treeDepts);
        })
    }
    loadDept1();
    $("#searchBtn").on("click", function () {
        search=true;
        //获取试题分类ID
        itemId = $("#itemTree").combotree('getValue');
        if(itemId=='请选择'){
            itemId='';
        }
        //获取题型ID
        type = $("#type").combobox('getValue');
        if(type=='请选择'){
            type='';
        }
        //获取状态
        state = $("#state").combobox('getValue');
        if(state=='请选择'){
            state='';
        }
        searchData(page,rows);
    });

    var searchData= function (page,rows){

        $("#questionGrid").datagrid('reload',basePath + '/exam/questionList?itemId=' + itemId + '&orgId=' + orgId + '&type=' + type + '&state=' + state+ '&page=' + page+ '&rows=' + rows);
        if(search){
            search=false;
            $("#questionGrid").datagrid('getPager').pagination('select',1);

        }

    }
    $("#clearBtn").on("click", function () {
        clearKey();
    });

    //配置窗口
    $("#addQuestion").window({

        closed: true,
        modal: true,
        collapsible: true,
        minimizable: false,
        maximizable: true,
        resizable: true,
        onClose: function () {
            $("#queForm").form('reset');
            danxt_num = 4;
            duoxt_num = 5;
            $("#danxt_ul ul li").remove();
            $("#duoxt_ul ul li").remove();
            $("#type1").combobox('enable');

        },
        onOpen: function () {

        }


    });
    //配置窗口
    $("#infoQuestion").window({

        closed: true,
        modal: true,
        collapsible: true,
        minimizable: false,
        maximizable: true,
        resizable: true,
        onClose: function () {
            danxt_num = 4;
            duoxt_num = 5;
            $("#danxt_ul1 ul li").remove();
            $("#duoxt_ul1 ul li").remove();

        },
        onOpen: function () {

        }


    });

    //打开新增窗口
    $("#addBtn").on('click', function () {
        //初始化编辑窗口
        danxt_num = 4;
        duoxt_num = 5;
        danxt_init('add', 4,'');
        duoxt_init('add', 5,'');
        $("#danxt").hide();
        $("#duoxt").hide();
        $("#pdt").hide();
        $("#jdt").hide();
        $("#queId").val('');
        $("input[name='pdt_flag'][value='1']").prop("checked",true);
        $("#type1").combobox("loadData",{});
        $("#type1").combobox("reload",basePath + '/exam/questionType');
        $("#addQuestion").window({title:"试题新增"});
        $("#addQuestion").window('open');
        $("#queForm").form('reset');
    });

    //修改
    $("#editBtn").on("click", function () {
        //获取选择行
        var row=$('#questionGrid').datagrid('getChecked');
        if(row.length==1){
            //赋值
            $.postJSON(basePath + "/exam/checkQuestionIsUsed",JSON.stringify(row), function (data) {
                if (data.data == "success") {
                    if(data.code=="isUsed"){
                        $("#questionGrid").datagrid('clearSelections');
                        $.messager.alert("提示", "所选试题正在使用中!","info");
                    }
                    if(data.code=="success"){
                        $("#itemTree1").combotree('setValue',row[0].itemId);
                        $("#type1").combobox('setValue',row[0].typeId);
                        $("#queName").textbox('setValue',row[0].queName);
                        $("#queId").val(row[0].queId);
                        $("#type1").combobox('disable');
                        if(row[0].typeId=='1'){
                            danxt_num = 4;
                            duoxt_num = 5;
                            danxt_init('add', 4,'');
                            duoxt_init('add', 5,'');
                            $("#pdt").show();
                            $("#danxt").hide();
                            $("#duoxt").hide();
                            $("#jdt").hide();
                            $("input[name='pdt_flag'][value="+row[0].queAnswer+"]").prop("checked",true);
                        }
                        if(row[0].typeId=='2'){
                            danxt_num = row[0].queNum;
                            duoxt_num = 5;
                            danxt_init('edit', row[0].queNum,row[0].queContent);
                            duoxt_init('add', 5,'');
                            $("#pdt").hide();
                            $("#danxt").show();
                            $("#duoxt").hide();
                            $("#jdt").hide();
                            $("input[name='dxt_flag'][value="+row[0].queAnswer+"]").prop("checked",true);
                        }
                        if(row[0].typeId=='3'){
                            danxt_num = 4;
                            duoxt_num = row[0].queNum;
                            danxt_init('add', 4,'');
                            duoxt_init('edit', row[0].queNum,row[0].queContent);
                            $("#pdt").hide();
                            $("#danxt").hide();
                            $("#duoxt").show();
                            $("#jdt").hide();
                            for(var i=0;i<row[0].queAnswer.length;i++){
                                var num=row[0].queAnswer.substr(i,1)-1;
                                var check= $("#duoxt_ul ul li:eq(" + num + ") :checkbox").prop("checked",'checked');
                            }

                        }
                        if(row[0].typeId=='4'){
                            danxt_num = 4;
                            duoxt_num = 5;
                            danxt_init('add', 4,'');
                            duoxt_init('add', 5,'');
                            $("#pdt").hide();
                            $("#danxt").hide();
                            $("#duoxt").hide();
                            $("#jdt").show();
                            $("#queAnswer").val(row[0].queAnswer);
                        }
                        $("#addQuestion").window({title:"试题修改"});
                        $("#addQuestion").window('open');
                    }
                }
            })

        }
        else{
            $("#questionGrid").datagrid('clearSelections');
            $.messager.alert("提示", "请选择一道试题！","info");
        }

    });
//查看
    $("#infoBtn").on("click", function () {
        //获取选择行
        var row=$('#questionGrid').datagrid('getChecked');
        if(row.length==1){
            $("#itemName").textbox('setValue',itemObj[row[0].itemId]);
            $("#type2").textbox('setValue',typeObj[row[0].typeId]);
            $("#queName1").textbox('setValue',row[0].queName);
            if(row[0].typeId=='1'){
                $("#pdt1").show();
                $("#danxt1").hide();
                $("#duoxt1").hide();
                $("#jdt1").hide();
                $("input[name='pdt_flag1'][value="+row[0].queAnswer+"]").prop("checked",true);
            }
            if(row[0].typeId=='2'){
                danxt_num = row[0].queNum;
                duoxt_num = 5;
                danxt_init('info', row[0].queNum,row[0].queContent);
                $("#pdt1").hide();
                $("#danxt1").show();
                $("#duoxt1").hide();
                $("#jdt1").hide();
                $("input[name='dxt_flag1'][value="+row[0].queAnswer+"]").prop("checked",true);
            }
            if(row[0].typeId=='3'){
                danxt_num = 4;
                duoxt_num = row[0].queNum;
                duoxt_init('info', row[0].queNum,row[0].queContent);
                $("#pdt1").hide();
                $("#danxt1").hide();
                $("#duoxt1").show();
                $("#jdt1").hide();
                for(var i=0;i<row[0].queAnswer.length;i++){
                    var num=row[0].queAnswer.substr(i,1)-1;
                    var check= $("#duoxt_ul1 ul li:eq(" + num + ") :checkbox").prop("checked",'checked');
                }

            }
            if(row[0].typeId=='4'){
                $("#pdt1").hide();
                $("#danxt1").hide();
                $("#duoxt1").hide();
                $("#jdt1").show();
            //.val().replace(/\n/g,"<br/>");
                $("#queAnswer1").val(row[0].queAnswer);
            }
            $("#infoQuestion").window({title:"试题查看"});
            $("#infoQuestion").window('open');

        }
        else{
            $("#questionGrid").datagrid('clearSelections');
            $.messager.alert("提示", "请选择一道试题！","info");
        }

    });
    //删除
    $("#delBtn").on("click", function () {
        var row = $("#questionGrid").datagrid('getSelections');
        if(row.length==0){
            $.messager.alert("提示", "请选择试题！","info");
            return;
        }
        if (row.length>0) {
            for(var i=0;i<row.length;i++){
                row[i].state='0';
                row[i].updateBy = parent.config.persion_Id;
            }
            $.postJSON(basePath + "/exam/checkQuestionIsUsed",JSON.stringify(row), function (data) {
                if (data.data == "success") {
                    if(data.code=="isUsed"){
                        $("#questionGrid").datagrid('clearSelections');
                        $.messager.alert("提示", "所选试题中有部分试题正在使用中!","info");
                    }
                    if(data.code=="success"){
                        $.messager.confirm("提示", "确认删除选中的试题吗?", function (r) {
                            if (r) {
                                $.postJSON(basePath + "/exam/questionRemove", JSON.stringify(row), function (data) {
                                    if(data.code=="sucsess"){
                                        $("#questionGrid").datagrid('reload');
                                        $("#questionGrid").datagrid('clearSelections');
                                    }
                                });
                            }
                        })
                    }
                }
            })
        }
    });
    //启用
    $("#okBtn").on("click", function () {
        var row = $("#questionGrid").datagrid('getSelections');
        if(row.length==0){
            $("#questionGrid").datagrid('clearSelections');
            $.messager.alert("提示", "请选择试题！","info");
            return;
        }
        if (row.length>0) {
            for(var i=0;i<row.length;i++){
                row[i].state='1';
                row[i].updateBy = parent.config.persion_Id;
            }
            row.updateBy = parent.config.persion_Id;
            $.messager.confirm("提示", "确认启用选中的试题吗?", function (r) {
                if (r) {
                    $.postJSON(basePath + "/exam/questionStatus", JSON.stringify(row), function (data) {
                        if(data.code=="sucsess"){
                            $("#questionGrid").datagrid('reload');
                            $("#questionGrid").datagrid('clearSelections');
                        }
                    });
                }
            })

        }
    });
    //停用
    $("#noBtn").on("click", function () {
        var row = $("#questionGrid").datagrid('getSelections');
        if(row.length==0){
            $.messager.alert("提示", "请选择试题！","info");
            return;
        }
        if (row.length>0) {
            for(var i=0;i<row.length;i++){
                row[i].state='0';
                row[i].updateBy = parent.config.persion_Id;
            }
            $.postJSON(basePath + "/exam/checkQuestionIsUsed",JSON.stringify(row), function (data) {
                if (data.data == "success") {
                    if(data.code=="isUsed"){
                        $("#questionGrid").datagrid('clearSelections');
                        $.messager.alert("提示", "所选试题中有部分试题正在使用中!","info");
                    }
                    if(data.code=="success"){
                        $.messager.confirm("提示", "确认停用选中的试题吗?", function (r) {
                            if (r) {
                                $.postJSON(basePath + "/exam/questionStatus", JSON.stringify(row), function (data) {
                                    if(data.code=="sucsess"){
                                        $("#questionGrid").datagrid('reload');
                                        $("#questionGrid").datagrid('clearSelections');
                                    }
                                });
                            }
                        })
                    }
                }
            })
        }
    });
    $("#importWin").dialog({
        width: 400,
        height: 200,
        title: '试题导入',
        closed: true,
        modal: true,
        footer: '#xls-buttons'
    })
    //导出模板
    $("#exportXls").on("click", function () {
        var url = basePath + "/exam/exportXls?orgId=" + orgId;
        //更改form的action
        $("#importForm").attr("action", url);
        //触发submit事件，提交表单
        $("#importForm").submit();
    });
    //导入模板
    $("#importXls").on("click", function () {
        $("#importWin").dialog("open").dialog("setTitle", "导入Execl");
    });
    $("#importBtn").on('click', function () {
        var path = document.getElementById("myFiles").value;
        var pos1 = path.lastIndexOf("\\");
        var pos2 = path.lastIndexOf(".");
        contractName = path.substring(pos1 + 1, pos2);
        var list ="xls,XLSX";
        var ext=path.substr(path.lastIndexOf('.')+1);

        var rs=list.indexOf(ext);

        //判断扩展名是否允许
        if(rs<0){
            $.messager.alert('提示', '不允许上传该类型文件！', 'info');
            return
        };
        $.messager.progress({
            title: '提示！',
            msg:  '数据量较大，请稍候...',
            text: '加载中.......'
        });
        var oData = new FormData(document.getElementById("editForm"));
        $.ajax({
            url: '/service/exam/up-xls?orgId=' + orgId,
            type: 'POST',
            data: oData,
            async: false,
            cache: false,
            contentType: false,
            processData: false,
            success: function (data) {
                $.messager.progress('close');
                $.messager.alert('系统提示', data.data, 'info');
                $("#importWin").dialog("close");
                $("#questionGrid").datagrid('reload');
            },
            error: function (data) {

            }
        });
    })
});
    function danxt_add(){
        //添加一行,danxt_num加一
        danxt_num++;
        //通过知道当前有的按钮数算出选项名
        var str=String.fromCharCode(64+danxt_num);
        //编辑新选项的html代码
        var $li=$("<li>" +
            "<span class='spanClass'>"+str+":</span> " +
            "<input type='radio' name='dxt_flag' style='vertical-align:middle; margin-top:-2px; margin-bottom:1px;' value='"+danxt_num+"'/> " +
            "<input  class='easyui-textbox'  name='danxt_text' data-options='multiline:true' style='width:280px;height:60px;margin-left: 5px' />"+
            "<a class='easyui-linkbutton' icon='icon-remove' style='margin-left: 5px'  class='del' >删除</a></li>");
        //将新的一行添加到<ul>中
        var $parent=$("#danxt_ul ul");
        var targetObj=$parent.append($li);
        $.parser.parse(targetObj);
        //因为添加了新的选项需要重新绑定按钮
        danxt_click();
    }
    function duoxt_add(){
        // alert(num)
        //添加一行,num加一
        duoxt_num++;
        //通过知道当前有的按钮数算出选项名
        var str=String.fromCharCode(64+duoxt_num);
        //编辑新选项的html代码
        var $li=$("<li>" +
            "<span  class='spanClass'>"+str+":</span> " +
            "<input type=\"checkbox\"/> " +
            "<input  class='easyui-textbox' name='duoxt_text' data-options='multiline:true'  style='width:280px;height:60px;margin-left: 5px' />"+
            "<a class='easyui-linkbutton' icon='icon-remove' style='margin-left: 5px'  class='del' >删除</a></li>");
        var $parent=$("#duoxt_ul ul");
        var targetObj=$parent.append($li);
        $.parser.parse(targetObj);
        //因为添加了新的选项需要重新绑定按钮
        duoxt_click();
    }
    function danxt_click(){
        $("#danxt_ul li a").unbind("click").click( function(){
            //调用renmove()将整个<li>节点删除
            $(this).parent().remove();
            //for循环刷新列表
            for(var i=0;i<danxt_num-1;i++){
                //ascii码65对应的A,65加上当前索引值再转成字符即可
                var str=String.fromCharCode(65+i)+":";
                //定位到每个<li>下的<span>节点，将选项号刷新到页面
                var targetObj=$("#danxt_ul ul li:eq(" + i + ") span.spanClass").text(str);
                $("input[name='dxt_flag']:eq(" + i + ")").val(i+1);
                /*var targetObj=$("#danxt_ul li:eq(" + i + ") span").html(str);*/
                $.parser.parse(targetObj);
            }
            //删除一行，num减一
            danxt_num--;
        });
    }
    function duoxt_click(){
        //这里其实用ul li input :button就可以，但是给按钮加一个class方便用css给按钮添加样式，这里本人比较懒没有添加样式。
        $("#duoxt_ul li a").unbind("click").click( function(){
            //$(this).parent().remove();链式操作，$(this)为该按钮本事,parent()为其父元素即<li>，调用renmove()将整个<li>节点删除
            $(this).parent().remove();
            //alert(num)
            //for循环刷新列表，因为考试往往用ABC，所以利用ascii码通过获取当前控件的索引来转换成对应的英文字母,
            for(var i=0;i<duoxt_num-1;i++){
                //ascii码65对应的A,65加上当前索引值再转成字符即可
                var str=String.fromCharCode(65+i)+":";
                //定位到每个<li>下的<span>节点，将选项号刷新到页面
                $("#duoxt_ul ul li:eq(" + i + ") span.spanClass").text(str);
            }
            //删除一行，num减一
            duoxt_num--;
        });

    }
    function danxt_init(type,num,content){
        if(type=='add'){
            for(var i=1;i<=num;i++){
                var str=String.fromCharCode(64+i);
                //编辑新选项的html代码
                var $li=$("<li>" +
                    "<span class='spanClass'>"+str+":</span> " +
                    "<input type='radio' name='dxt_flag' style='vertical-align:middle; margin-top:-2px; margin-bottom:1px;' value='"+i+"'/> " +
                    "<input  class='easyui-textbox' name='danxt_text' data-options='multiline:true' style='width:280px;height:60px;margin-left: 5px' />"+
                    "<a class='easyui-linkbutton' icon='icon-remove' style='margin-left: 5px'  class='del' >删除</a></li>");
                //将新的一行添加到<ul>中
                var $parent=$("#danxt_ul ul");
                var targetObj=$parent.append($li);
                $.parser.parse(targetObj);
            }
        }
        if(type=='edit'){
            var array = content.split("^&*");
            for(var i=1;i<=num;i++){
                var str=String.fromCharCode(64+i);
                //编辑新选项的html代码
                var $li=$("<li>" +
                    "<span class='spanClass'>"+str+":</span> " +
                    "<input type='radio' name='dxt_flag' style='vertical-align:middle; margin-top:-2px; margin-bottom:1px;' value='"+i+"'/> " +
                    "<input  class='easyui-textbox' name='danxt_text' data-options='multiline:true'  value='"+array[i-1]+"' style='width:280px;height:60px;margin-left: 5px' />"+
                    "<a class='easyui-linkbutton' icon='icon-remove' style='margin-left: 5px'  class='del' >删除</a></li>");
                //将新的一行添加到<ul>中
                var $parent=$("#danxt_ul ul");
                var targetObj=$parent.append($li);
                $.parser.parse(targetObj);
            }
        }
        if(type=='info'){
            var array = content.split("^&*");
            for(var i=1;i<=num;i++){
                var str=String.fromCharCode(64+i);
                //编辑新选项的html代码
                var $li=$("<li>" +
                    "<span class='spanClass'>"+str+":</span> " +
                    "<input type='radio' name='dxt_flag1' style='vertical-align:middle; margin-top:-2px; margin-bottom:1px;' value='"+i+"'/> " +
                    "<input  class='easyui-textbox' name='danxt_text' data-options='multiline:true'  value='"+array[i-1]+"' style='width:340px;height:60px;margin: 5px' />"+
                    "</li>");
                //将新的一行添加到<ul>中
                var $parent=$("#danxt_ul1 ul");
                var targetObj=$parent.append($li);
                $.parser.parse(targetObj);
            }
        }
        danxt_click();
    }
    function duoxt_init(type,num,content){
        if(type=='add'){
            for(var i=1;i<=num;i++){
                var str=String.fromCharCode(64+i);
                //编辑新选项的html代码
                var $li=$("<li>" +
                    "<span  class='spanClass'>"+str+":</span> " +
                    "<input type=\"checkbox\" style='vertical-align:middle; margin-top:-2px; margin-bottom:1px;'/> " +
                    "<input  class='easyui-textbox' name='duoxt_text'data-options='multiline:true' style='width:280px;height:60px;margin-left: 5px' />"+
                    "<a class='easyui-linkbutton' icon='icon-remove' style='margin-left: 5px'  class='del' >删除</a></li>");
                //将新的一行添加到<ul>中
                var $parent=$("#duoxt_ul ul");
                var targetObj=$parent.append($li);
                $.parser.parse(targetObj);
            }
        }
        if(type=='edit'){
            var array = content.split("^&*");
            for(var i=1;i<=num;i++){
                var str=String.fromCharCode(64+i);
                //编辑新选项的html代码
                var $li=$("<li>" +
                    "<span  class='spanClass'>"+str+":</span> " +
                    "<input type=\"checkbox\" style='vertical-align:middle; margin-top:-2px; margin-bottom:1px;'/> " +
                    "<input  class='easyui-textbox' name='duoxt_text' data-options='multiline:true' value='"+array[i-1]+"' style='width:280px;height:60px;margin-left: 5px' />"+
                    "<a class='easyui-linkbutton' icon='icon-remove' style='margin-left: 5px'  class='del' >删除</a></li>");
                //将新的一行添加到<ul>中
                var $parent=$("#duoxt_ul ul");
                var targetObj=$parent.append($li);
                $.parser.parse(targetObj);
            }
        }
        if(type=='info'){
            var array = content.split("^&*");
            for(var i=1;i<=num;i++){
                var str=String.fromCharCode(64+i);
                //编辑新选项的html代码
                var $li=$("<li>" +
                    "<span  class='spanClass'>"+str+":</span> " +
                    "<input type=\"checkbox\" style='vertical-align:middle; margin-top:-2px; margin-bottom:1px;'/> " +
                    "<input  class='easyui-textbox' name='duoxt_text' data-options='multiline:true' value='"+array[i-1]+"' style='width:340px;height:60px;margin: 5px' />"+
                    "</li>");
                //将新的一行添加到<ul>中
                var $parent=$("#duoxt_ul1 ul");
                var targetObj=$parent.append($li);
                $.parser.parse(targetObj);
            }
        }
        duoxt_click();
    }

    //保存试题
    $("#saveBtn").on('click', function () {
        var queNum='';
        var queContent='';
        var queAnswer='';
        var t = $("#itemTree1").combotree('tree'); // 得到树对象
        if(!t.tree('getSelected')){
            $.messager.alert('提示', '请选择试题分类！', 'info');
            return;
        }
        //获取题型ID
        var typeId = $("#type1").combobox('getValue');
        if(!typeId){
            $.messager.alert('提示', '请选择试题题型！', 'info');
            return;
        }
        var queName=$("#queName").val();
        if(queName==''){
            $.messager.alert('提示', '请填写试题题目！', 'info');
            return;
        }
        if(getRealLen(queName)>1000){
            $.messager.alert("提示","试题题目输入过长！",'info');
            return;
        }
        if(typeId=='1'){
            var pdt_check=$("input[name='pdt_flag']:checked").val();
            if(pdt_check==null){
                $.messager.alert('提示', '请选择判断题答案！', 'info');
                return;
            }
            queAnswer=pdt_check;
        }
        if(typeId=='2'){
            var danxt_check=$("input[name='dxt_flag']:checked").val();
            if(danxt_check==null){
                $.messager.alert('提示', '请选择单选题答案！', 'info');
                return;
            }
            queAnswer=danxt_check;
            for(var i=0;i<danxt_num;i++){
                var text=$("#danxt_ul ul li:eq(" + i + ") input[name='danxt_text']").val();
                if(text==''){
                    $.messager.alert('提示', '请填写选项内容！', 'info');
                    return;
                }
                queContent+=text+"^&*";
            }
            queNum=danxt_num;
        }
        if(typeId=='3'){
            for(var i=0;i<duoxt_num;i++){
                var text= $("#duoxt_ul ul li:eq(" + i + ") input[name='duoxt_text']").val();
                var check= $("#duoxt_ul ul li:eq(" + i + ") :checkbox").is(':checked');
                if(text==''){
                    $.messager.alert('提示', '请填写选项内容！', 'info');
                    return;
                }
                if(check)
                queAnswer+=i+1;
                queContent+=text+"^&*";
            }
            if(queAnswer==''){
                $.messager.alert('提示', '请选择多选题答案！', 'info');
                return;
            }
            queNum=duoxt_num;
        }
        if(typeId=='4'){
            var queAnswer=$("#queAnswer").val();
            if(queAnswer==''){
                $.messager.alert('提示', '请填写简单题答案！', 'info');
                return;
            }
        }
        if(getRealLen(queAnswer)>2000){
            $.messager.alert("提示","试题答案输入过长！",'info');
            return;
        }
        var questionVo = {};
        questionVo.queId = $("#queId").val();
        questionVo.queName = queName;
        questionVo.orgId = orgId;
        questionVo.itemId = $("#itemTree1").combotree('getValue');
        questionVo.typeId = typeId;
        questionVo.queNum = queNum;
        questionVo.queContent = queContent;
        questionVo.queAnswer = queAnswer;
        questionVo.createBy = parent.config.persion_Id;

        $.postJSON(basePath + "/exam/questionMerge", JSON.stringify(questionVo), function (data) {
            if (data.data == "success") {

                if(data.code=="hasName"){
                    $.messager.alert('提示', '考试名称已存在', 'info');
                }
                if(data.code=="success") {
                    $("#addQuestion").window('close');
                    $("#questionGrid").datagrid('reload');
                    $("#queForm").form('reset');
                }
            }
        }, function (data) {
            $.messager.alert('提示', '保存失败', 'info');
        });


    });
    //取消添加人员维护
    $("#cancelBtn").on('click', function () {
        $("#queForm").form('reset');
        $("#addQuestion").window('close');
    });
    //清空查询条件
    function clearKey() {
        //清空试题分类ID
        $("#itemTree").combotree('clear');
        $("#itemTree").combotree("setValue","请选择");
        itemId = '';
        //清空题型ID
        $("#type").combobox('clear');//获取表格对象
        $("#type").combobox("setValue","请选择");
        type = '';
        //清空状态
        $("#state").combobox('clear');
        $("#state").combobox("setValue","请选择");
        state = '';
        page='1';

    }

    //清空表单内容
    function clearForm() {
        $("#itemTree1").combotree('clear');
        $("#type1").combogrid('clear');//获取表格对象
        $("#pdt_answer").val(0);
    }
