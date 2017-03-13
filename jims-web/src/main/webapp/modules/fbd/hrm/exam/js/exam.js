
var basePath = "/service";
var orgId='';
var typeId='';
var examId='';
var itemId='';
var methodId='';
var item='';
var sel_id='';
var sel_score='';
var sel_examId='';
var sel_itemId='';
var type = '';//题型ID
var state = '';//状态
var exam_state = '';//状态
var edit = false;//状态
var num=0;
var page='1';
var rows='30';
var totalScore=0;
var search=false;
var deptIds=parent.orgids;
var Export={};
$(function () {
    orgId= parent.config.org_Id;


    $("#examGrid").datagrid({
        toolbar: '#tb',
        iconCls: 'icon-edit',//图标
        width: 'auto',
        height: '100%',
        nowrap: false,
        striped: true,
        border: true,
        method: 'get',
        loadMsg: '数据正在加载中，请稍后.....',
        pagination: true,//分页控件
        pageSize: 30,
        collapsible: false,//是否可折叠的
        //url: basePath +'/project/evaluationType?orgId=' + orgId,
        url: basePath + '/exam/examList?orgId=' + orgId + '&type=' + type + '&state=' + state,
        remoteSort: false,
        singleSelect: true,//是否单选
        rownumbers: true,//行号
        idField: 'examId',
        fitColumns:true,
         columns: [[
            {field: 'typeName', title: '试卷类型', width: '10%', align: 'center'},
            {field: 'examName', title: '试卷名称', width: '15%', align: 'center'},
            {
                field: 'state', title: '状态', width: '10%', align: 'center',
                formatter: function (value, row, index) {
                    if (value == "1") {
                        return "发布";
                    }
                    if (value == "0") {
                        return "编辑";
                    }
                }
            },

            {field: 'score', title: '总分', width: '10%', align: 'center'},
            {field: 'checkScore', title: '及格分', width: '10%', align: 'center'},
            {field: 'selectScore', title: '已选分值', width: '10%', align: 'center',
                formatter: function (value, row, index) {
                    if(!value){
                        return "0";
                    }
                    else {
                        return value;
                    }
                }
            },
            {field: 'time', title: '考试时长', width: '10%', align: 'center'},
            {field: 'createBy', title: '创建人', width: '10%', align: 'center' },
            {field: 'createDate', title: '创建时间', width: '15%', align: 'center'}

        ]],
        onLoadSuccess:function (data) {

            $("#addExam").css('display','block');
            $("#addQueType").css('display','block');
            $("#addQue").css('display','block');
            $("#queryQue").css('display','block');
            $("#export").css('display','block');
            //配置新增试卷窗口
            $("#export").window({
                title:'试卷导出',
                closed: true,
                modal: true,
                collapsible: true,
                minimizable: false,
                maximizable: true,
                resizable: true,
                onClose: function () {
                },
                onOpen: function () {
                }
            });
            //配置新增试卷窗口
            $("#addExam").window({
                closed: true,
                modal: true,
                collapsible: true,
                minimizable: false,
                maximizable: true,
                resizable: true,
                onClose: function () {
                    $("#itemTree").combotree('enable');
                },
                onOpen: function () {
                    //校验用户名是否已经存在
                    $("#examName").blur(function () {
                        var examName = $("#examName").val();
                        if (examName != '') {
                            jQuery.ajax({
                                'type': 'GET',
                                async:false,
                                'url': basePath + "/exam/getExamName?examName=" + examName + "&orgId=" + orgId,
                                'contentType': 'application/json',
                                'success': function (data) {
                                    if (data && data.examName == examName) {
                                        $.messager.alert('提示', '试卷名称已存在', 'info');
                                        return false;
                                    }
                                },
                                'error': function (data) {
                                    //console.log("失败");
                                }
                            });
                        }
                        return true;
                    });

                }

            });

        }
    });
    $("#examGrid").datagrid('getPager').pagination({
        pageList: [10, 20, 30, 40, 50], //可以设置每页记录条数的列表
        displayMsg: '当前显示从第{from}条到第{to}条，共{total}条记录',
        onSelectPage: function (pageNumber, pageSize) {
            var state1 = $('#examGrid').data('datagrid');
            var opts = state1.options;
            page=opts.pageNumber = pageNumber;
            rows=opts.pageSize = pageSize;
            searchData(page,rows);
            return;

        }

    });
//配置新增题型窗口
    $("#selectQue").window({
        title: '手工选题',
        closed: true,
        modal: true,
        collapsible: true,
        minimizable: false,
        maximizable: true,
        resizable: true,
        fit:true,
        onClose: function () {
            $("#queTypeGrid").datagrid('reload');
            initScore();
        },
        onOpen: function () {
        }
    });
    //配置题目设置窗口
    $("#addQueType").window({
        title: '题目设置',
        closed: true,
        modal: true,
        collapsible: true,
        minimizable: false,
        maximizable: true,
        resizable: true,
        fit:true,
        onClose: function () {
            $("#examGrid").datagrid('reload');
            exam_state='';
            $('#queTypeGrid').datagrid('clearSelections');
            $('#queTypeGrid').datagrid('clearChecked');
        },
        onOpen: function () {
        }
    });
    //配置新增题型窗口
    $("#addQue").window({
        title: '题型设置',
        closed: true,
        modal: true,
        collapsible: true,
        minimizable: false,
        maximizable: true,
        resizable: true,
        width:'auto',
        height:'auto',
        onClose: function () {
            edit=false;
            $("#queType").combobox('enable');
            initScore();
        },
        onOpen: function () {
            $('#method').combobox({    //出题方式
                url: basePath + '/exam/examMethod',
                valueField: 'sort',
                textField: 'name',
                method: 'GET'
                ,onSelect: function (node) {
                    var value = node.sort;
                    if(value=='1'){
                        $("#random").show();
                        methodId=1;
                    }
                    if(value=='2'){
                        $("#random").hide();
                        $("#queNum").numberbox('clear');
                        methodId=2;
                    }
                }
            });
        }
    });

    //配置新增题型窗口
    $("#queryQue").window({
        title: '已选试题',
        closed: true,
        modal: true,
        collapsible: true,
        minimizable: false,
        maximizable: true,
        resizable: true,
        width:'800px',
        height:'500px',
        onClose: function () {
        },
        onOpen: function () {
        }
    });
    $('#queType').combobox({    //题型
        url: basePath + '/exam/questionType',
        valueField: 'sort',
        textField: 'name',
        method: 'GET'
    });

    $("#type").combobox({     //加载考试类型
        url: basePath + '/dict/find-list-by-type?type=' + 'EXAM_TYPE',
        valueField: 'id',
        textField: 'value',
        value:'请选择',
        method: 'GET'

    });
    $("#state").combobox({     //加载考试类型
        url: basePath + '/dict/find-list-by-type?type=' + 'EXAM_STATE',
        valueField: 'label',
        textField: 'value',
        value:'请选择',
        method: 'GET'

    });
    $('#type1').combobox({    //加载考试类型
        url: basePath + '/dict/find-list-by-type?type=' + 'EXAM_TYPE',
        valueField: 'id',
        textField: 'value',
        method: 'GET'
    });

    $("#itemTree").combotree({
        fitColumns: true,
        striped: true,
        multiple: true,
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
            var a=node;
            /*//返回树对象
            var tree = $(this).tree;
            //选中的节点是否为叶子节点,如果不是叶子节点,清除选中
            var isLeaf = tree('isLeaf', node.target);
            if (!isLeaf) {
                //清除选中
                $('#itemTree').combotree('clear');
                $.messager.alert("提示", "请选择子分类", "info");
            }*/
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

    $("#searchBtn").on("click", function () {
        search=true;
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
        $("#examGrid").datagrid('reload',basePath + '/exam/examList?orgId=' + orgId + '&type=' + type + '&state=' + state+ '&page=' + page+ '&rows=' + rows);
        if(search){
            search=false;
            $("#examGrid").datagrid('getPager').pagination('select',1);

        }
    }

    $("#clearBtn").on("click", function () {
        clearKey();
    });

    //打开新增窗口
    $("#addBtn").on('click', function () {
        $("#examId").val('');
        $("#examForm").form('reset');
        $("#addExam").window({title:"试卷新增"});
        $("#addExam").window('open');
    });

    //修改
    $("#editBtn").on("click", function () {
        //获取选择行
        var row=$('#examGrid').datagrid('getChecked');
        if(row.length==1){
            if(row[0].state=='1'){
                $("#examGrid").datagrid('clearSelections');
                $.messager.alert("提示", "该试卷已发布,不能修改","info");
                return;
            }
            //赋值
            var item=row[0].itemId;
            var strs= new Array(); //定义一数组
            strs=item.split(","); //字符分割
            var itemId='';
            for (i=0;i<strs.length-1 ;i++ )
            {
                if(i==strs.length-2){
                    itemId+=strs[i]+''
                }
                else{
                    itemId+=strs[i]+',';
                }

            }
            $("#examId").val(row[0].examId);
            $("#type1").combobox('setValue',row[0].typeId);
            $("#examName").textbox('setValue',row[0].examName);
            $("#time").numberbox('setValue',row[0].time);
            $("#itemTree").combotree('setValues',itemId);
            $("#score").numberbox('setValue',row[0].score);
            $("#checkscore").numberbox('setValue',row[0].checkScore);
            $("#itemTree").combotree('disable');
            $("#addExam").window({title:"试卷修改"});
            $("#addExam").window('open');
        }
        else{
            $("#examGrid").datagrid('clearSelections');
            $.messager.alert("提示", "请选择一个试卷","info");
        }
    });
    //题目设置
    $("#queBtn").on("click", function () {
        //获取选择行
        var row=$('#examGrid').datagrid('getChecked');
        if(row.length==1){
            if(row.state=='1'){
                $("#examGrid").datagrid('clearSelections');
                $.messager.alert("提示", "该试卷已发布,不能设置题目","info");
                return;
            }
            $("#addQueType").window('open');
            exam_state=row[0].state;
            examId=row[0].examId;
            typeId=row[0].typeId;
            itemId=row[0].itemId;
            totalScore=row[0].score;
            //题型数据
            $("#queTypeGrid").datagrid({
                iconCls: 'icon-edit',//图标
                width: '100%',
                height: '100%',
                nowrap: false,
                striped: true,
                border: true,
                method: 'get',
                toolbar: '#tb1',
                loadMsg: '数据正在加载中，请稍后.....',
                pagination: true,//分页控件
                pageSize: 30,
                pageList: [10, 15, 30, 50],//可以设置每页记录条数的列表
                collapsible: false,//是否可折叠的
                url: basePath + '/exam/examQuestionType?examId=' + examId + '&orgId=' + orgId,
                remoteSort: false,
                idField: 'examQueId',
                singleSelect: true,//是否单选
                rownumbers: true,//行号
                fitColumns:true,
                columns: [[
                    {field: 'typeName', title: '题型',width: '20%',align: 'center'},
                    {field: 'score', title: '分数/题',width: '20%',align: 'center'},
                    {
                        field: 'methodId', title: '选题方式', width: '20%', align: 'center',
                        formatter: function (value, row, index) {
                            if (value == "1") {
                                return "随机选题";
                            }
                            if (value == "2") {
                                return "手工选题";
                            }
                        }
                    },
                    {field: 'checkedNum', title: '已选题目数量', width: '20%', align: 'center'},
                    {field:'h',title:'操作',width:'18%',align:'center',formatter:function(value, row, index){
                        if(row.methodId=="2"){
                            return '<a href="#" name="Que"  class="easyui-linkbutton" iconCls="icon-edit"  onclick="addQueType(\''+row.examQueId+'\',\''+row.score+'\',\''+row.num+'\',\''+row.typeId+'\',\''+itemId+'\',\''+examId+'\')">手工选题</a>';
                        }
                        if(row.methodId=="1")
                        return '<a  href="#"  name="Que"  class="easyui-linkbutton" iconCls="icon-edit"  onclick="randomQue(\''+row.examQueId+'\',\''+row.score+'\',\''+row.num+'\',\''+row.typeId+'\')">随机选题</a>';

                    }}

                ]],onLoadSuccess:function(data){
                    $("a[name='Que']").linkbutton({iconCls:'icon-edit'});
                    initScore();
                    $('#queTypeGrid').datagrid('fixRowHeight');
                }
            });
        }
        else{
            $("#examGrid").datagrid('clearSelections');
            $.messager.alert("提示", "请选择一个试卷","info");
        }
        $("#totalScore").html(row[0].score+'分');

    });
    //试卷导出
    $("#okBtn1").on("click", function () {
        var row=$('#examGrid').datagrid('getSelected');
        if(row) {
            if (row.score != row.selectScore) {
                $("#examGrid").datagrid('clearSelections');
                $.messager.alert("提示", "该试卷选中试题分数与设置总分不符", "info");
                return;
            }
            Export.id=row.examId;
            Export.score=row.score;
            Export.time=row.time;
            $("#export").window('open');
            $('#name').textbox('setValue',row.examName);
        }
        else{
            $("#examGrid").datagrid('clearSelections');
            $.messager.alert("提示", "请选择一个试卷","info");
        }

    });
    //发布
    $("#okBtn").on("click", function () {
        var row=$('#examGrid').datagrid('getSelected');
        if(row){
            if(row.state=='1'){
                $("#examGrid").datagrid('clearSelections');
                $.messager.alert("提示", "该试卷已发布","info");
                return;
            }
            if(row.score!=row.selectScore){
                $("#examGrid").datagrid('clearSelections');
                $.messager.alert("提示", "该试卷选中试题分数与设置总分不符","info");
                return;
            }
            $.messager.confirm("提示", "试卷发布后不能修改，确认发布选中的试卷吗?", function (r) {
                if (r) {
                    var exam = {};
                    exam.examId = row.examId;
                    exam.updateBy=parent.config.persion_Id;
                    $.postJSON(basePath + "/exam/examPublish", JSON.stringify(exam), function (data) {
                        if(data.data=="success"){
                            $("#examGrid").datagrid('reload');
                        }
                        else{
                            $.messager.alert("提示", "发布失败","info");
                        }
                    });
                }
            })
        }
        else{
            $("#examGrid").datagrid('clearSelections');
            $.messager.alert("提示", "请选择一个试卷","info");
        }
    });
//删除
    $("#delBtn").on("click", function () {
        var row=$('#examGrid').datagrid('getSelected');
        if(row){
            if(row.state=='1'){
                $("#examGrid").datagrid('clearSelections');
                $.messager.alert("提示", "该试卷已发布,不能删除","info");
                return;
            }
            $.messager.confirm("提示", "确认删除选中的试卷吗?", function (r) {
                if (r) {
                    var exam = {};
                    exam.examId = row.examId;
                    exam.updateBy=parent.config.persion_Id;
                    $.postJSON(basePath + "/exam/examDelete", JSON.stringify(exam), function (data) {
                        if(data.data=="success"){
                            $("#examGrid").datagrid('reload');
                            $("#examGrid").datagrid('clearSelections');
                            row.length=0;
                        }
                        else{
                            $.messager.alert("提示", "发布失败","info");
                        }
                    });
                }
            })
        }
        else{
            $("#examGrid").datagrid('clearSelections');
            $.messager.alert("提示", "请选择一个试卷","info");
        }
    });

});

//保存试卷
$("#saveBtn").on('click', function () {
    //获取试卷名称
    var examName=$("#examName").val();
    if(examName==''||examName==null||examName.indexOf(" ") >=0){
        $.messager.alert('提示', '请填写试卷名称！', 'info');
        return;
    }
    if(getRealLen(examName)>100){
        $.messager.alert("提示","试卷名称输入过长！",'info');
        return;
    }
    /*if(!onExamName()){
        return;
    }*/
    //获取试题分类
    var itemTree=$("#itemTree").combotree('tree').tree('getChecked');
    if(itemTree==null||itemTree==''){
        $.messager.alert('提示', '请选择试题分类！', 'info');
        return;
    }
    var item='';
    $.each(itemTree, function (index, data) {
        if(data.parent){
            item+=data.id+',';
        }
    });
    if(item==''){
        $.messager.alert('提示', '请选择试题分类！', 'info');
        return;
    }
    //获取试卷类型
    var type=$("#type1").combobox('getValue');
    if(type==null||type==''){
        $.messager.alert('提示', '请选择试卷类型！', 'info');
        return;
    }

    //获取考试时长
    var time=$("#time").numberbox('getValue');

    if(time==''){
        $.messager.alert('提示', '请填写考试时长！', 'info');
        return;
    }
    if(time.lenth>5){
        $.messager.alert('提示', '考试时长输入过长！', 'info');
        return;
    }
    //获取考试总分
    var score=$("#score").numberbox('getValue');

    if(score==''){
        $.messager.alert('提示', '请填写考试总分！', 'info');
        return;
    }
    if(score.lenth>5){
        $.messager.alert('提示', '考试总分输入过长！', 'info');
        return;
    }
    //获取及格分数
    var checkscore=$("#checkscore").numberbox('getValue');

    if(checkscore==''){
        $.messager.alert('提示', '请填写及格分数！', 'info');
        return;
    }
    if(checkscore.lenth>5){
        $.messager.alert('提示', '及格分数输入过长！', 'info');
        return;
    }
    if(parseInt(checkscore)>parseInt(score)){
        $.messager.alert('提示', '及格分数不能大于总分！', 'info');
        return;
    }

    var exam = {};
    exam.examId = $("#examId").val();
    exam.examName = examName;
    exam.orgId = orgId;
    exam.createBy = parent.config.persion_Id;
    exam.typeId = type;
    exam.itemId = item;
    exam.score = score;
    exam.checkScore = checkscore;
    exam.time = time;
    $.postJSON(basePath + "/exam/examMerge", JSON.stringify(exam), function (data) {
        if (data.data == "success") {

            if(data.code=="hasName"){
                $.messager.alert('提示', '考试名称已存在', 'info');
            }
            if(data.code=="success") {
                $("#addExam").window('close');
                $("#examGrid").datagrid('reload');
                $("#examForm").form('reset');
            }

        }
    }, function (data) {
        $.messager.alert('提示', '保存失败', 'info');
    });
});
//试卷编辑页取消
$("#cancelBtn").on('click', function () {
    $("#examForm").form('reset');
    $("#addExam").window('close');
});
//导出试卷
$("#exportBtn").on('click', function () {
    var name=$("#name").textbox('getValue');
    if(name==''||name==null||name.indexOf(" ") >=0){
        $.messager.alert('提示', '请填写试卷名称！', 'info');
        return;
    }
    var check=$("#answer").is(':checked');
    var answer=0;
    if(check){
        answer=1;
    }
    var url = "/service/exam/getPaper?id="+Export.id+"&name="+name+"&lx="+answer+"&totalScore="+Export.score+"&time="+Export.time;
    //更改form的action
    $("#exportForm").attr("action", url);
    //触发submit事件，提交表单
    $("#exportForm").submit();
    $("#export").window('close');
});
//新增题型
$("#addQueBtn").on('click', function () {
    if(exam_state=='1'){
        $.messager.alert('提示', '试卷已发布', 'info');
        return;
    }
    $("#addQue").window('open');
    $("#queForm").form('reset');
    $("#random").hide();
    $("#examQueId").val('');
    $("#method").combobox('enable');
    $("#queNum").numberbox('enable');


});
//修改题型
$("#editQueBtn").on('click', function () {
    if(exam_state=='1'){
        $.messager.alert('提示', '试卷已发布', 'info');
        $('#queTypeGrid').datagrid('clearChecked');
        return;
    }
    var row=$('#queTypeGrid').datagrid('getChecked');
    if(row.length==1) {
        $("#addQue").window('open');
        $("#method").combobox("loadData",{});
        $("#method").combobox("reload",basePath + '/exam/examMethod');
        $("#queType").combobox('setValue', row[0].typeId);

        $("#queType").combobox('disable');
        $("#examQueId").val(row[0].examQueId);
        $("#method").combobox('setValue', row[0].methodId);
        $("#method").combobox('disable');
        $("#queScore").numberbox('setValue', row[0].score);
        if(row[0].methodId=='1'){
            $("#random").show();
            $("#queNum").numberbox('setValue', row[0].num);
            $("#queNum").numberbox('disable');
        }
        if(row[0].methodId=='2'){
            num=row[0].num;
            $("#random").hide();
        }

        $('#queTypeGrid').datagrid('clearChecked');
        edit=true;
    }
    else{
        $.messager.alert("提示", "请选择一种题型","info");
        $('#queTypeGrid').datagrid('clearChecked');
    }
});
//删除题型
$("#removeQueBtn").on('click', function () {
    var row=$('#queTypeGrid').datagrid('getSelections');
    if(row.length==1) {
        if(exam_state=='1'){
            $('#queTypeGrid').datagrid('clearSelections');
            $.messager.alert('提示', '试卷已发布', 'info');
            return;
        }
        $.messager.confirm("提示", "确认删除选中的题型吗?", function (r) {
            if (r) {
                var exam = {};
                exam.examId = row[0].examId;
                exam.examQueId = row[0].examQueId;
                $.postJSON(basePath + "/exam/delExamQueType", JSON.stringify(exam), function (data) {
                    if(data.data=="success"){
                        $("#queTypeGrid").datagrid('reload');
                        row.length=0;
                    }
                    else{
                        $.messager.alert("提示", "删除失败","info");
                    }
                    $('#queTypeGrid').datagrid('clearSelections');
                });
            }
        })
    }
    else{

        $.messager.alert("提示", "请选择一种题型","info");
    }
});

//保存题型
$("#saveTypeBtn").on('click', function () {
    //获取题型
    var typeId=$("#queType").combobox('getValue');
    if(typeId==null||typeId==''){
        $.messager.alert('提示', '请选择题型', 'info');
        return;
    }
    //获取出题方式
     var methodId=$("#method").combobox('getValue');
     if(methodId==null||methodId==''){
     $.messager.alert('提示', '请选择出题方式', 'info');
     return;
     }
    //获取分值
    var queScore=$("#queScore").val();
    if(queScore==null||queScore==''){
        $.messager.alert('提示', '请填写每题分值', 'info');
        return;
    }

    var queNum;
    if(methodId=='1'){
        //获取题目数量
        queNum=$("#queNum").val();
        if(queNum==null||queNum==''||queNum=='0'){
            $.messager.alert('提示', '请填写题目数量', 'info');
            return;
        }

    }
    if(methodId=='2'){
        if(edit){
            queNum=num;
        }
        else{
            queNum=0;
        }
    }
    var queType = {};
    queType.examQueId = $("#examQueId").val();
    queType.typeId = typeId;
    queType.score = queScore;
    queType.orgId = orgId;
    queType.methodId = methodId;
    queType.examId = examId;
    queType.num = queNum;
    queType.itemId = itemId;
    queType.totalScore = totalScore;
    $.postJSON(basePath + "/exam/examQueTypeMerge", JSON.stringify(queType), function (data) {
        if (data.data == "success") {

            if(data.code=="add"){
                if(methodId=='1') {
                    $.messager.alert('提示', '题型添加成功,随机生成'+queNum+'道试题',"info");
                }
                else{
                }
                $("#addQue").window('close');
                $("#queTypeGrid").datagrid('reload');
                $("#queForm").form('reset');
            }
            if(data.code=="edit"){
                $.messager.alert('提示', '题型修改成功',"info");
                $("#addQue").window('close');
                $("#queTypeGrid").datagrid('reload');
                $("#queForm").form('reset');
            }
            if(data.code=="over"){
                $.messager.alert('提示', '所选试题分数超过设置的试卷总分，请重新选择',"info");
            }
            if(data.code=="echo"){
                $.messager.alert('提示', '该题型已存在',"info");
                $("#queType").combobox('clear');
            }
            if(data.code=="num"){
                $.messager.alert('提示', '试题库中该类型试题数量不够',"info");
                $("#queNum").numberbox('clear');
            }

        }


    }, function (data) {
        $.messager.alert('提示', '保存失败', 'info');
    });
});
//编辑题型页取消
$("#cancelTypeBtn").on('click', function () {
    $("#queForm").form('reset');
    $("#addQue").window('close');
});
//清空查询条件
function clearKey() {
    //清空题型ID
    $("#type").combobox('clear');//获取表格对象
    $("#type").combobox('setValue','请选择');//获取表格对象
    type = '';
    //清空状态
    $("#state").combobox('setValue','请选择');
    state = '';
    page='1';
}

//随机出题
function  randomQue(id,score,num,typeId) {
    if(exam_state=='1'){
        $.messager.alert('提示', '试卷已发布', 'info');
        return;
    }
    var queType = {};
    queType.examQueId = id;
    queType.typeId = typeId;
    queType.score = score;
    queType.orgId = orgId;
    queType.methodId = methodId;
    queType.examId = examId;
    queType.num = num;
    queType.itemId = itemId;
    $.postJSON(basePath + "/exam/randomQuestion", JSON.stringify(queType), function (data) {
        if (data.data == "success") {
            if(data.code=="success"){
                $.messager.alert('提示', '随机生成'+num+'道试题',"info");
                $("#queTypeGrid").datagrid('reload');
                initScore();
            }
            if(data.code=="num"){
                $.messager.alert('提示', '试题库中该类型试题数量不够',"info");
            }
        }


    }, function (data) {
        $.messager.alert('提示', '保存失败', 'info');
    });
}
//手工选题
function  addQueType(id,score,num,typeId,itemId,examId) {
    if(exam_state=='1'){
        $.messager.alert('提示', '试卷已发布', 'info');
        return;
    }
    sel_id=id;
    sel_score=score;
    sel_examId=examId;
    sel_itemId=itemId;
    $("#selectQue").window('open');
    $("#itemTree1").treegrid({
        width: 'auto',
        height: '99%',
        fitColumns: true,
        striped: true,
        singleSelect: true,
        idField: "itemId",
        treeField: "itemName",
        loadMsg: '数据正在加载中，请稍后.....',
        columns: [[{
            title: 'itemId',
            field: "itemId",
            hidden: true
        }, {
            title: '分类名称',
            field: 'itemName',
            width: '100%'
        }]],
        onClickRow: function (rowIndex, rowData) {
            //返回树对象
            if (rowIndex.parentId==null) {
                var url = basePath + "/exam/examQuestionByItem?itemId=" + sel_itemId + "&examQueTypeId=" + sel_id + "&typeId=" + typeId;
                $("#queGrid1").datagrid("reload", url);
            }
            else{
                var node = $("#itemTree1").treegrid("getSelected");
                var url = basePath + "/exam/examQuestionByItem?itemId=" + rowIndex.itemId + "&examQueTypeId=" + sel_id + "&typeId=" + typeId;
                $("#queGrid1").datagrid("reload", url);
            }

        }
    });
    //加载树形结构的treegrid数据
    var loadDept = function () {

        var depts = [];
        var treeDepts = [];
        var loadPromise = $.get("/service/exam/examItemList?orgId=" + orgId+"&itemId="+itemId, function (data) {
            $.each(data, function (index, item) {
                var obj = {};
                obj.itemName = item.itemName;
                obj.itemId = item.itemId;
                obj.parentId = item.parent;
                obj.state = item.state;
                obj.children = [];
                depts.push(obj);
            });

        });
        loadPromise.done(function () {
            for (var i = 0; i < depts.length; i++) {
                for (var j = 0; j < depts.length; j++) {
                    if (depts[i].itemId == depts[j].parentId) {
                        depts[i].children.push(depts[j]);
                    }
                }
                var aa=depts[i].parentId;
                if (depts[i].children.length > 0 && !depts[i].parentId) {
                    treeDepts.push(depts[i]);
                }

                if (!depts[i].parentId && depts[i].children <= 0) {
                    treeDepts.push(depts[i])
                }
            }
            $("#itemTree1").treegrid('loadData', treeDepts);
        })
    }

    $("#queGrid1").datagrid({
        toolbar: '#tb2',
        width: '100%',
        height: '99%',
        title:'未选试题',
        nowrap: false,
        striped: true,
        border: true,
        method: 'get',
        loadMsg: '数据正在加载中，请稍后.....',
        pagination: true,//分页控件
        pageSize: 20,
        pageList: [10, 20, 30, 40, 50],//可以设置每页记录条数的列表
        collapsible: false,//是否可折叠的
        url: basePath + "/exam/examQuestionByItem?itemId=" + itemId + "&examQueTypeId=" + id + "&typeId=" + typeId,
        remoteSort: false,
        idField: 'queId',
        singleSelect: false,//是否单选
        rownumbers: true,//行号
        fitColumns:true,
        columns: [[
            {field: 'ck', title: '', checkbox: true},
            {field: 'queName', title: '题目',width: '95%',align: 'left',halign: 'center',formatter : function (value, row, index) {
                if (value.length > 76) value = value.substr(0, 76) + "...";
                return value;
            }}
        ]],
        onLoadSuccess:function(data){
        }
    });
    $("#queGrid2").datagrid({
        toolbar: '#tb3',
        width: 'auto',
        height: '99%',
        title:'已选试题',
        nowrap: false,
        striped: true,
        border: true,
        method: 'get',
        loadMsg: '数据正在加载中，请稍后.....',
        pagination: true,//分页控件
        pageSize: 20,
        pageList: [10, 20, 30, 40, 50],//可以设置每页记录条数的列表
        collapsible: false,//是否可折叠的
        url: basePath + '/exam/examQuestion?examQueTypeId=' + id,
        remoteSort: false,
        idField: 'examQueId',
        singleSelect: false,//是否单选
        rownumbers: true,//行号
        fitColumns:true,
        columns: [[
            {field: 'ck', title: '', checkbox: true},
            {field: 'queName', title: '题目',width: '95%',align: 'left',halign: 'center',formatter : function (value, row, index) {
                if (value.length > 76) value = value.substr(0, 76) + "...";
                return value;
            }}


        ]],onLoadSuccess:function(data){
        }
    });
    loadDept();
//保存手工选题
    $("#saveQueBtn").on('click', function () {
        var row=$('#queGrid1').datagrid('getSelections');
        var queId='';
        for(var i=0;i<row.length;i++){
            queId+=row[i].queId+',';
        }
        var queType = {};
        queType.examQueId = sel_id;
        queType.score = sel_score;
        queType.queId = queId;
        queType.examId = sel_examId;
        queType.num = row.length;
        queType.itemId = sel_itemId;
        queType.totalScore = totalScore;
        $.postJSON(basePath + "/exam/selectQuestion", JSON.stringify(queType),function (data) {
            if (data.data == "success") {
                if(data.code=="success"){
                    //$.messager.alert('提示', '已选'+row.length+'道试题');
                    $("#queGrid1").datagrid('reload');

                    $("#queGrid2").datagrid('reload');
                }
                if(data.code=="num"){
                    $.messager.alert('提示', '试题库中该类型试题数量不够',"info");
                }
                if(data.code=="over"){
                    $.messager.alert('提示', '所选试题分数超过设置的试卷总分，请重新选择',"info");
                }
                $("#queGrid1").datagrid('clearSelections');
            }
        }, function (data) {
            $("#queGrid1").datagrid('clearSelections');
            $.messager.alert('提示', '保存失败', 'info');
        });

    });
//删除已选试题
    $("#delQueBtn").on('click', function () {
        var row=$('#queGrid2').datagrid('getSelections');
        if(row.length<=0){
            $.messager.alert('提示', '请选择要删除的试题', 'info');
        }
        var queId='';
        for(var i=0;i<row.length;i++){
            queId+=row[i].queId+',';
        }
        var queType = {};
        queType.examQueId = sel_id;
        queType.score = sel_score;
        queType.queId = queId;
        queType.examId = sel_examId;
        queType.num = row.length;
        queType.itemId = sel_itemId;
        queType.totalScore = totalScore;
        $.postJSON(basePath + "/exam/delSelectQuestion", JSON.stringify(queType),function (data) {
            if (data.data == "success") {
                if(data.code=="success"){
                    //$.messager.alert('提示', '已删除'+row.length+'道试题');
                    $("#queGrid1").datagrid('reload');
                    $("#queGrid2").datagrid('reload');
                }
                $("#queGrid2").datagrid('clearSelections');
            }
        }, function (data) {
            $("#queGrid2").datagrid('clearSelections');
            $.messager.alert('提示', '保存失败', 'info');

        });

    });
}
function queryQue(type) {
    if(type=='1'){
        var row=$('#examGrid').datagrid('getSelections');
    }
    if(type=='2'){
        var row=$('#queTypeGrid').datagrid('getSelections');
    }
        if(row.length==1) {
            $("#queryQue").window('open');
            var queID="";
            var examID="";
            if(type=='1'){
                queID="";
                examID=row[0].examId;
            }
            if(type=='2'){
                queID=row[0].examQueId;
                examID="";
            }
            //已选试题数据
            $("#queGrid").datagrid({
                iconCls: 'icon-edit',//图标
                width: '100%',
                height: '99%',
                nowrap: false,
                striped: true,
                border: true,
                method: 'get',
                loadMsg: '数据正在加载中，请稍后.....',
                pagination: true,//分页控件
                pageSize: 20,
                pageList: [10, 20, 30, 40, 50],//可以设置每页记录条数的列表
                collapsible: false,//是否可折叠的
                url: basePath + '/exam/examQuestion?examQueTypeId=' + queID+'&examId='+examID+'&type='+type,
                remoteSort: false,
                idField: 'examQueId',
                singleSelect: true,//是否单选
                rownumbers: true,//行号
                fitColumns:true,
                columns: [[
                    {field: 'queName', title: '题目',width: '60%',align: 'left',halign: 'center',formatter : function (value, row, index) {
                        if (value.length > 76) value = value.substr(0, 76) + "...";
                        return value;
                    }},
                    {field: 'typeName', title: '题型',width: '20%',align: 'center'},
                    {field: 'score', title: '分数/题',width: '20%',align: 'center'}


                ]],onLoadSuccess:function(data){
                    if(type=='1'){
                        $('#examGrid').datagrid('clearSelections');
                    }
                    if(type=='2'){
                        $('#queTypeGrid').datagrid('clearSelections');
                    }

                }
            });
        }
        else{
            if(type=='1'){
                $.messager.alert("提示", "请选择一个试卷","info");
            }
            if(type=='2'){
                $.messager.alert("提示", "请选择一种题型","info");
            }

        }
}
function initScore() {
    var row =$("#queTypeGrid").datagrid('getRows');
    var score=0;
    for(var i=0;i<row.length;i++){
        score+=row[i].score*row[i].checkedNum;
    }
    $("#tempScore").html(score+'分');

}