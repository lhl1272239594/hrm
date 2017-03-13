/**
 *试题分
 * @author wangzhiming
 * @version 2016-08-18
 */


var basePath = "/service";
var danxt_num=4;//单选题当前选项数量
var duoxt_num=4;//多选题当前选项数量
var orgId='';
$(function () {
    orgId= parent.org_Id;
    var itemId = '';//试题分类ID
    var type = '';//题型ID
    var state = '';//状态
    $("#questionGrid").datagrid({
        toolbar: '#tb',
        iconCls: 'icon-edit',//图标
        width: 'auto',
        height: 'auto',
        nowrap: false,
        striped: true,
        border: true,
        method: 'get',
        loadMsg: '数据正在加载中，请稍后.....',
        pagination: true,//分页控件
        pageSize: 30,
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
            {field: 'queName', title: '试题题目', width: '40%', align: 'center'},
            {
                field: 'state', title: '状态', width: '10%', align: 'center',
                formatter: function (value, row, index) {
                    if (value == "1") {
                        return "启用";
                    }
                    if (value == "0") {
                        return "停用";
                    }
                }
            },
            {field: 'createBy', title: '创建人', width: '10%', align: 'center'},
            {field: 'createDate', title: '创建时间', width: '20%', align: 'center'}

        ]]
    });
    $("#questionGrid").datagrid('getPager').pagination({

        pageList: [10, 20, 30, 40, 50], //可以设置每页记录条数的列表
        displayMsg: '当前显示从第{from}条到第{to}条，共{total}条记录',

    });

});
    function danxt_add(){
        //添加一行,danxt_num加一
        danxt_num++;
        //通过知道当前有的按钮数算出选项名
        var str=String.fromCharCode(64+danxt_num);
        //编辑新选项的html代码
        var $li=$("<li>" +
            "<span class='spanClass'>"+str+":</span> " +
            "<input type='radio' name='dxt_flag' value='"+danxt_num+"'/> " +
            "<input  class='easyui-textbox' data-options='required:true' name='danxt_text' style='width:280px;height:60px;margin-left: 5px' />"+
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
            "<input  class='easyui-textbox' name='duoxt_text' data-options='required:true' style='width:280px;height:60px;margin-left: 5px' />"+
            "<a class='easyui-linkbutton' icon='icon-remove' style='margin-left: 5px'  class='del' >删除</a></li>");
        //将新的一行添加到<ul>中
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
                    "<input type='radio' name='dxt_flag' value='"+i+"'/> " +
                    "<input  class='easyui-textbox' name='danxt_text' data-options='required:true' style='width:280px;height:60px;margin-left: 5px' />"+
                    "<a class='easyui-linkbutton' icon='icon-remove' style='margin-left: 5px'  class='del' >删除</a></li>");
                //将新的一行添加到<ul>中
                var $parent=$("#danxt_ul ul");
                var targetObj=$parent.append($li);
                $.parser.parse(targetObj);
            }
        }
        if(type=='edit'){
            var array = content.split("##");
            for(var i=1;i<=num;i++){
                var str=String.fromCharCode(64+i);
                //编辑新选项的html代码
                var $li=$("<li>" +
                    "<span class='spanClass'>"+str+":</span> " +
                    "<input type='radio' name='dxt_flag' value='"+i+"'/> " +
                    "<input  class='easyui-textbox' name='danxt_text' data-options='required:true' value='"+array[i-1]+"' style='width:280px;height:60px;margin-left: 5px' />"+
                    "<a class='easyui-linkbutton' icon='icon-remove' style='margin-left: 5px'  class='del' >删除</a></li>");
                //将新的一行添加到<ul>中
                var $parent=$("#danxt_ul ul");
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
                    "<input type=\"checkbox\"/> " +
                    "<input  class='easyui-textbox' name='duoxt_text' data-options='required:true' style='width:280px;height:60px;margin-left: 5px' />"+
                    "<a class='easyui-linkbutton' icon='icon-remove' style='margin-left: 5px'  class='del' >删除</a></li>");
                //将新的一行添加到<ul>中
                var $parent=$("#duoxt_ul ul");
                var targetObj=$parent.append($li);
                $.parser.parse(targetObj);
            }
        }
        if(type=='edit'){
            var array = content.split("##");
            for(var i=1;i<=num;i++){
                var str=String.fromCharCode(64+i);
                //编辑新选项的html代码
                var $li=$("<li>" +
                    "<span  class='spanClass'>"+str+":</span> " +
                    "<input type=\"checkbox\"/> " +
                    "<input  class='easyui-textbox' name='duoxt_text' data-options='required:true' value='"+array[i-1]+"' style='width:280px;height:60px;margin-left: 5px' />"+
                    "<a class='easyui-linkbutton' icon='icon-remove' style='margin-left: 5px'  class='del' >删除</a></li>");
                //将新的一行添加到<ul>中
                var $parent=$("#duoxt_ul ul");
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
            $.messager.alert('系统提示', '请选择试题分类', 'info');
            return;
        }
        //获取题型ID
        var typeId = $("#type1").combobox('getValue');
        if(!typeId){
            $.messager.alert('系统提示', '请选择试题题型', 'info');
            return;
        }
        //获取状态
        var state=$("#state1").combobox('getValue');
        if(state==null||state==''){
            $.messager.alert('系统提示', '请选择启用状态', 'info');
            return;
        }
        var queName=$("#queName").val();
        if(queName==''){
            $.messager.alert('系统提示', '请填写试题题目', 'info');
            return;
        }
        if(typeId=='1'){
            var pdt_check=$("input[name='pdt_flag']:checked").val();
            if(pdt_check==null){
                $.messager.alert('系统提示', '请选择判断题答案', 'info');
                return;
            }
            queAnswer=pdt_check;
        }
        if(typeId=='2'){
            var danxt_check=$("input[name='dxt_flag']:checked").val();
            if(danxt_check==null){
                $.messager.alert('系统提示', '请选择单选题答案', 'info');
                return;
            }
            queAnswer=danxt_check;
            for(var i=0;i<danxt_num;i++){
                var text=$("#danxt_ul ul li:eq(" + i + ") input[name='danxt_text']").val();
                if(text==''){
                    $.messager.alert('系统提示', '请填写选项内容', 'info');
                    return;
                }
                queContent+=text+"##";
            }
            queNum=danxt_num;
        }
        if(typeId=='3'){
            for(var i=0;i<duoxt_num;i++){
                var text= $("#duoxt_ul ul li:eq(" + i + ") input[name='duoxt_text']").val();
                var check= $("#duoxt_ul ul li:eq(" + i + ") :checkbox").is(':checked');
                if(text==''){
                    $.messager.alert('系统提示', '请填写选项内容', 'info');
                    return;
                }
                if(check)
                queAnswer+=i+1;
                queContent+=text+"##";
            }
            if(queAnswer==''){
                $.messager.alert('系统提示', '请选择多选题答案', 'info');
                return;
            }
            queNum=duoxt_num;
        }
        if(typeId=='4'){
            var queAnswer=$("#queAnswer").val();
            if(queAnswer==''){
                $.messager.alert('系统提示', '请填写简单题答案', 'info');
                return;
            }
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
        questionVo.state = state;

        $.postJSON(basePath + "/exam/questionMerge", JSON.stringify(questionVo), function (data) {
            if (data.data == "success") {
                $("#addQuestion").window('close');
                if(data.code=="add"){
                    $.messager.alert('系统提示', '试题添加成功');
                }
                if(data.code=="edit"){
                    $.messager.alert('系统提示', '试题修改成功');
                }
                $("#questionGrid").datagrid('reload');
                $("#queForm").form('reset');

            }
        }, function (data) {
            $.messager.alert('系统提示', '保存失败', 'info');
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
        itemId = '';
        //清空题型ID
        $("#type").combobox('clear');//获取表格对象
        type = '';
        //清空状态
        $("#state").combobox('clear');
        state = '';
    }

    //清空表单内容
    function clearForm() {
        $("#itemTree1").combotree('clear');
        $("#type1").combogrid('clear');//获取表格对象
        $("#state1").combobox('clear');
        $("#pdt_answer").val(0);
    }
