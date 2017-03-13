
var basePath = "/service";
var orgId='';
var examId='';
var planId='';
var start='';
var end='';
var limitStart='';//进入考试时间限制
var limitSubmit='';//交卷时间限制
var time='';//考试时长
var id='';//行表ID
var scoreId='';//头表ID
var planName='';//计划名称
var SysSecond;//倒计时试卷（秒）
var InterValObj;//倒计时对象
var firstTypeId='';//第一个题型
var queSort=1;//当前题号
var queType=1;//当前题型
var queNum=1;//选项数量
var queTotal=1;//试题总数
var queAnswer;//试题答案
var byHand;//是否手工判分（0否，1是）
var score;//试题分值
var change=false;
var testRow;
var scoreState='';
var page='1';
var rows='30';
var deptIds=parent.orgids;
var search=false;
$(function () {
    orgId= parent.config.org_Id;
    var type = '';//题型ID
    var state = '';//状态
    $("#testGrid").datagrid({
        toolbar: '#tb',
        width: 'auto',
        height: 'auto',
        nowrap: false,
        striped: true,
        border: true,
        method: 'get',
        loadMsg: '数据正在加载中，请稍后.....',
        pagination: true,//分页控件
        pageSize: 30,
        pageList: [10, 20, 30, 40, 50],//可以设置每页记录条数的列表
        collapsible: false,//是否可折叠的
        fit: true,//自动大小
        url: basePath + '/exam/testList?orgId=' + orgId+'&deptIds=' + deptIds,
        remoteSort: false,
        idField: 'planId',
        singleSelect: true,//是否单选
        rownumbers: true,//行号
        columns: [[
            {field: 'orgId', title: '', hidden: true},
            {field: 'typeName', title: '考试类型', width: '10%', align: 'center'},
            {field: 'planName', title: '考试名称', width: '39%', align: 'center',},
            {field: 'time', title: '考试时长', width: '10%', align: 'center'},
            {field: 'startTime', title: '开始时间', width: '20%', align: 'center'},
            {field: 'endTime', title: '结束时间', width: '20%', align: 'center'}

        ]],
        onLoadSuccess:function (data) {
            $("#testWin").css('display','block');
        }
    });
    $("#type").combobox({     //加载计划类型
        url: basePath + '/dict/find-list-by-type?type=' + 'EXAM_TYPE',
        valueField: 'id',
        textField: 'value',
        method: 'GET'

    });
//配置窗口
    $("#test").window({
        fit:true,
        closed: true,
        modal: true,
        onClose: function () {
        },
        onOpen: function () {

        }

    });

    //配置考试说明窗口
    $("#infoWin").window({
        width:'400px',
        height:'300px',
        closed: true,
        modal: true,
        shadow:false,
        onClose: function () {
        },
        onOpen: function () {

        }

    });
    //配置考试窗口
    $("#testWin").window({
        fit:true,
        closed: true,
        modal: true,
        onClose: function () {
            clearTest();
            if(InterValObj!=null)
            window.clearInterval(InterValObj);
            $("#testGrid").datagrid('reload');
        },
        onOpen: function () {

        }

    });

});


//进入考试
$("#testBtn").on('click', function () {
    var row=$('#testGrid').datagrid('getSelections');
    if(row.length==1) {
        $("#infoWin").window('open');
        $("#info").html(row[0].info);
        testRow=row[0];
        examId=row[0].examId;
        planId=row[0].planId;
        planName=row[0].planName;
        start=row[0].startTime;
        orgId=row[0].orgId;
        limitStart=row[0].limitStart;
        limitSubmit=row[0].limitSubmit;
        time=row[0].time;
        byHand=row[0].byHand;
    }
    else{
        $.messager.alert("提示", "请选择考试!","info");
    }

});
//答题卡初始化
function test_init(type,  id, time, examDetails) {
    init();
    queTotal=examDetails.length;
    scoreId=id;
    $("#index_pdt").hide();
    $("#index_danxt").hide();
    $("#index_duoxt").hide();
    $("#index_jdt").hide();

    $("#title h1").text(planName);
    SysSecond = time; //这里获取倒计时的起始时间
    InterValObj = setInterval(SetRemainTime, 1000); //间隔函数，1秒执行
    var qusid='0';//存当前题型
    var qusnum=0;//存第一类答题
    var $card_ul=$("#card_title");
    $card_ul.empty();
    for(var i=0;i<examDetails.length;i++) {
        if (examDetails[i].typeId != qusid) {
            //判断题
            if (examDetails[i].typeId == "1"){
                var $card_li = $("<li id='title_pdt'>" +
                                    "<a href='#' onclick='switchAnswerCard(\"pdt\"); return false;'>判断题</a>" +
                                    "<span class='subtitle_line'></span>" +
                                 "</li>");
                if(qusnum==0){
                    firstTypeId='pdt';
                }
                $card_ul.append($card_li);
            }
            //多选题
            if (examDetails[i].typeId == "2"){
                var $card_li = $("<li id='title_danxt'>" +
                                    "<a href='#' onclick='switchAnswerCard(\"danxt\"); return false;'>单选题</a>" +
                                    "<span class='subtitle_line'></span>" +
                                 "</li>");
                if(qusnum==0){
                    firstTypeId='danxt';
                }
                $card_ul.append($card_li);
            }
            //多选题
            if (examDetails[i].typeId == "3"){
                var $card_li = $("<li id='title_duoxt'>" +
                                    "<a href='#' onclick='switchAnswerCard(\"duoxt\"); return false;'>多选题</a>" +
                                    "<span class='subtitle_line'></span>" +
                                 "</li>");
                if(qusnum==0){
                    firstTypeId='duoxt';
                }
                $card_ul.append($card_li);
            }
            //简答题
            if (examDetails[i].typeId == "4"){
                var $card_li = $("<li id='title_jdt'>" +
                                    "<a href='#' onclick='switchAnswerCard(\"jdt\"); return false;'>简答题</a>" +
                                    "<span class='subtitle_line'></span>" +
                                 "</li>");
                if(qusnum==0){
                    firstTypeId='jdt';
                }
                $card_ul.append($card_li);
            }
            qusid = examDetails[i].typeId;
            qusnum++;
        }
        var questionIndex_class='questionIndex';
        if(examDetails[i].answer!=null){
            questionIndex_class+=' questionAnswered';
        }
        if (examDetails[i].typeId == "1"){
            var pdt = "<a href='#' class='"+questionIndex_class+"' id='index_"+examDetails[i].sort+"'  onclick='toQuestion("+examDetails[i].sort+"); return false;'>"+examDetails[i].sort+"</a>";
            var $pdt_parent=$("#index_pdt");
            var targetObj=$pdt_parent.append(pdt);
            $.parser.parse(targetObj);
        }
        if (examDetails[i].typeId == "2"){
            var danxt = "<a href='#' class='"+questionIndex_class+"' id='index_"+examDetails[i].sort+"' onclick='toQuestion("+examDetails[i].sort+"); return false;'>"+examDetails[i].sort+"</a>";
            var $danxt_parent=$("#index_danxt");
            var targetObj=$danxt_parent.append(danxt);
            $.parser.parse(targetObj);
        }
        if (examDetails[i].typeId == "3"){
            var duoxt = "<a href='#' class='"+questionIndex_class+"' id='index_"+examDetails[i].sort+"' onclick='toQuestion("+examDetails[i].sort+"); return false;'>"+examDetails[i].sort+"</a>";
            var $duoxt_parent=$("#index_duoxt");
            var targetObj=$duoxt_parent.append(duoxt);
            $.parser.parse(targetObj);
        }
        if (examDetails[i].typeId == "4"){
            var jdt = "<a href='#' class='"+questionIndex_class+"' id='index_"+examDetails[i].sort+"' onclick='toQuestion("+examDetails[i].sort+"); return false;'>"+examDetails[i].sort+"</a>";
            var $jdt_parent=$("#index_jdt");
            var targetObj=$jdt_parent.append(jdt);
            $.parser.parse(targetObj);
        }
        if(examDetails[i].sort==1){
            question_init(examDetails[i]);
        }
    }
    $("#index_"+firstTypeId).show();
    $("#title_"+firstTypeId+" a").addClass("question-card-title_checked");

}
//试题初始化
function  question_init(question) {
    queSort=question.sort;
    queType=question.typeId;
    queNum=question.queNum;
    queAnswer=question.queAnswer;
    id=question.id;
    score=question.score;
    if(change==true){
        changeCardTab();
        change=false;
    }
    var question_html="<span class='question_type'>"+question.typeName+"（每题"+question.score+"分）</span>" +
                            "<span class='question_mode'>" +
                                "<span class='question_number' >"+question.sort+".</span>" +
                                "<span class='question_border'>" +
                                    "<span class='question-title   question-name_text_border'>"+question.queName+"</span>"
    if (question.typeId == "1"){
        question_html+=
            "<ul class='binaryChoice-block_body_options'>" +
                "<span class='binaryChoice-block_mode_options spanBackground'>" +
                    "<span class='binaryChoice-form_border_option'>" +
                        "<input type='radio' name='binary' value='1'>" +
                    "</span>" +
                    "<span class='binaryChoice-text_border_synopsis'>对</span>" +
                "</span>" +
                "<span class='binaryChoice-block_mode_options spanBackground'>" +
                    "<span class='binaryChoice-form_border_option'>" +
                        "<input type='radio' name='binary' value='0'>" +
                    "</span>" +
                    "<span class='binaryChoice-text_border_synopsis'>错</span>" +
                "</span>" +
            "</ul>";
    }
    if (question.typeId == "2"){
        var array = question.queContent.split("^&*");
        question_html+=
            "<ul class='singleAnswer-block_body_options'>";
            for(var i=1;i<=question.queNum;i++){
                question_html+=
                    "<span class='singleAnswer-block_mode_options spanBackground'>" +
                        "<span class='singleAnswer-form_border_option'>" +
                            "<input type='radio' name='single' value='"+i+"'/>"+
                        "</span>" +
                        "<span class='singleAnswer-text_border_synopsis'>"+array[i-1]+"</span>"+
                    "</span>";
            }
        question_html+="</ul>";

    }
    if (question.typeId == "3"){
        var array = question.queContent.split("^&*");
        question_html+=
            "<ul class='multipleChoice-block_body_options'>";
        for(var i=1;i<=question.queNum;i++){
            question_html+=
                "<span class='multipleChoice-block_mode_options spanBackground'>" +
                "<span class='multipleChoice-form_border_option'>" +
                "<input type='checkbox' name='multiple' value='"+i+"'/>"+
                "</span>" +
                "<span class='multipleChoice-text_border_synopsis'>"+array[i-1]+"</span>"+
                "</span>";
        }
        question_html+="</ul>";

    }
    if (question.typeId == "4"){
        question_html+=
            "<ul class='shortAnswer-block_body_options'>"+
            "<textarea id='shortAnswer' style='font-size:16px;height:180px;width:860px'></textarea>" +

/*
            "<input class='easyui-textbox' id='shortAnswer'  data-options='multiline:true' style='font-size:16px;height:180px;width:860px'/>"+
*/
            "</ul>";

    }
    question_html+='</span></span>'
    var $jdt_parent=$(".section");
    var targetObj=$jdt_parent.append(question_html);
    $.parser.parse(targetObj);
    select_init();
    if(question.answer!=null){
        answer_init(question);
    }
}
//考试答案初始化
function answer_init(question) {
    var answer=question.answer;
    if(question.typeId=="1"){
        $("input[name='binary'][value="+answer+"]").prop("checked",true);
    }
    if(question.typeId=="2"){
        $("input[name='single'][value="+answer+"]").prop("checked",true);
    }
    if(question.typeId=="3"){
        for(var i=0;i<answer.length;i++){
            var num=answer.substr(i,1);
            $("input[name='multiple'][value="+num+"]").prop("checked",'checked');
        }
    }
    if(question.typeId=="4"){
        $("#shortAnswer").val(answer);
    }

}
//切换题型标签
function switchAnswerCard(typeId){
    $("#index_"+typeId).show().siblings().hide();
    $("#title_"+typeId+" a").addClass("question-card-title_checked").parent().siblings().find("a").removeClass("question-card-title_checked");
}
//倒计时
function SetRemainTime() {
    if (SysSecond > 0) {
        SysSecond = SysSecond - 1;
        var second= Math.floor(SysSecond % 60);             // 计算秒
        if(second<10){
            second='0'+Math.floor(SysSecond % 60);
        }
        var minite = Math.floor((SysSecond / 60) % 60);      //计算分
        if(minite<10){
            minite='0'+Math.floor((SysSecond / 60) % 60);
        }
        var hour = Math.floor((SysSecond / 3600) % 24);      //计算小时
        if(hour<10){
            hour='0'+Math.floor((SysSecond / 3600) % 24);
        }


        $("#remainTime").html("剩余时间： "+hour + "小时" + minite + "分" + second + "秒");
    } if(SysSecond<=0) {//剩余时间小于或等于0的时候，就停止间隔函数
        clearInterval(InterValObj);
        submitExam();
    }
}
//确认进入考试
$("#testConfirmBtn").on('click', function () {
    $("#infoWin").window('close');
    var test = {};
    test.examId = examId;
    test.planId = planId;
    test.limitStart = limitStart;
    test.orgId = orgId;
    test.time = time;
    test.userId = parent.config.persion_Id;
    $.messager.progress({
        title: '提示！',
        msg:  '数据量较大，请稍候...',
        text: '加载中.......'
    });
    $.postJSON(basePath + "/test/startTest", JSON.stringify(test), function (data) {
        $.messager.progress('close')
        if(data.data=="success"){
            if(data.code=="overtime"){
                $.messager.alert('提示', '已过考试截止时间，不能参加考试!',"info");
            }
            if(data.code=="none"){
                $.messager.alert('提示', '没有您的考试信息!',"info");
            }

            if(data.code=="start"){
                $("#testWin").window('open');
                test_init('start',data.id,time*60,data.examDetails);
                scoreState=data.state;
            }
            if(data.code=="continue"){
                if(data.time<=0){
                    $.messager.alert('提示', '已过考试时间，不能参加考试!',"info");
                }
                else {
                    $("#testWin").window('open');
                    test_init('continue',data.id,data.time,data.examDetails);
                    scoreState=data.state;
                }

            }
        }
        else{
            $.messager.alert("提示", "失败","info");
        }
    });

});
//取消
$("#cancelBtn").on('click', function () {

    $("#infoWin").window('close');
});
//试题事件初始化
function  select_init() {
    //单选题选择
    $(".singleAnswer-block_mode_options").on('click', function () {
        var num=$(this).index()+1;
        $("input[name='single'][value="+num+"]").prop("checked",true);
    });

    //多选题选择
    $(".multipleChoice-block_mode_options").on('click', function () {
        var num=$(this).index()+1;
        var multiple=$("input[name='multiple'][value="+num+"]");
        if(multiple.is(':checked')){
            multiple.prop("checked", false);
        }
        else{
            multiple.prop("checked", true);
        }
    });
    //判断题选择
    $(".binaryChoice-block_mode_options").on('click', function () {
        var num=$(this).index();
        var check;
        if(num==0){
            check=1;
        }
        if(num==1){
            check=0;
        }
        $("input[name='binary'][value="+check+"]").prop("checked",true);
    });

    $(".spanBackground").on('mouseover', function () {
        $(this).addClass("question-mouseon_color");
    });
    $(".spanBackground").on('mouseleave', function () {
        $(this).removeClass("question-mouseon_color");
    });
}

//清空查询条件
function clearKey() {
    //清空题型ID
    $("#type").combobox('clear');//获取表格对象
    type = '';
    //清空状态
    $("#state").combobox('clear');
    state = '';
}
//清空试卷内容
function clearTest() {
    //清空题型标签
    $("#card_title").empty();
    //清空题目索引
    $("#pdt").empty();
    $("#danxt").empty();
    $("#duoxt").empty();
    $("#jdt").empty();
    //清空试题
    $("#section").empty();
    testRow=null;
}
//上一题
$(".leftButton").on('click', function () {
    if(queSort==1){
        saveAnswer();
        $.messager.alert("提示", "已经是第一道题!","info");
        return;
    }
    toQuestion(parseInt(queSort)-1);
    change=true;
});
//下一题
$(".rightButton").on('click', function () {
    if(queSort==queTotal){
        saveAnswer();
        $.messager.alert("提示", "已经是最后一道题!","info");
        return;
    }
    toQuestion(parseInt(queSort)+1);
    change=true;
});
//按照题目序号跳转
function toQuestion(sort) {
    saveAnswer();
    if(sort!=''){
        $.get(basePath + '/test/getQuestion?&sort=' + sort + '&scoreId=' + scoreId, function (data) {
            if(data.data=="success"){
                $(".section").empty();
                question_init(data.examDetails[0]);
            }
        });
    }
}
//切换题型标签
function  changeCardTab() {
    var typeId='';
    if(queType==1){
        typeId='pdt';
    }
    if(queType==2){
        typeId='danxt';
    }
    if(queType==3){
        typeId='duoxt';
    }
    if(queType==4){
        typeId='jdt';
    }
    switchAnswerCard(typeId);
}
//保存答案
function saveAnswer() {
    var answer='';
    if(queType==1){
        var pdt_check=$("input[name='binary']:checked").val();
        if(pdt_check!=null){
            answer=pdt_check;
        }
    }
    if(queType==2){
        var danxt_check=$("input[name='single']:checked").val();
        if(danxt_check!=null){
            answer=danxt_check;
        }
    }
    if(queType==3){
        for(var i=0;i<queNum;i++){
            var check= $("input[name='multiple']:eq(" + i + "):checkbox").is(':checked');
            if(check)
                answer+=i+1;
        }
    }
    if(queType==4){
        answer=$("#shortAnswer").val();
    }
        var test = {};
        test.id = id;
        test.queAnswer = queAnswer;
        test.answer = answer;
        test.score = score;
        test.typeId = queType;
        $.postJSONAsync(basePath + "/test/saveAnswer", JSON.stringify(test), function (data) {
            if(data.data=="success"){
                if(answer!=''){
                    $("#index_"+queSort).addClass("questionAnswered");
                }
                else{
                    $("#index_"+queSort).removeClass("questionAnswered");
                }
            }
            else{
                $.messager.alert("提示", "保存答案失败!");
            }
        });
}
function submitValidate() {
    saveAnswer();
    $.get(basePath + "/test/submitValidate?scoreId="+scoreId+"&limitSubmit="+limitSubmit, function (data) {
        if(data.data=="success"){
            if(data.code=="notEnoughTime"){
                $.messager.alert("提示", "考试开始"+limitSubmit+"分钟内不能交卷!","info");
                return;
            }
            if(data.code=="hasNotAnswer"){
                $.messager.confirm("提示", "还有"+data.num+"道题没有作答，确认交卷?", function (r) {
                    if (r) {
                        submitExam();
                    }
                })
            }
            if(data.code=="success"){
                $.messager.confirm("提示", "确认交卷?", function (r) {
                    if (r) {
                        submitExam();
                    }
                })
            }
        }
    });
}
function submitExam() {
    testRow.scoreId=scoreId;
    testRow.startTime='';
    testRow.endTime='';
    if(scoreState!='')
        testRow.state=scoreState;
    $.postJSON(basePath + "/test/submitExam", JSON.stringify(testRow), function (data) {
        if (data.data == "success") {
            if(data.code == "success"){
                $("#testWin").window('close');
                $.messager.alert('提示', '考试得分'+data.score, 'info');
            }
            if (data.code == "byHand") {
                $("#testWin").window('close');
                $.messager.alert('提示', '非主观题得 '+data.score+' 分主观题成绩请等待老师评分', 'info');
            }
            if (data.code == "hasSubmit") {
                $.messager.alert('提示', '该试卷已提交！', 'info');
            }
        }
    }, function (data) {
        $.messager.alert('提示', '提交失败', 'info');
    });


}
function init(){
    queSort=1;//当前题号
    queType=1;//当前题型
    queNum=1;//选项数量
    queTotal=1;//试题总数
    var $card_ul=$("#card_title");
    $card_ul.empty();
    var $index_pdt=$("#index_pdt");
    $index_pdt.empty();
    var $index_danxt=$("#index_danxt");
    $index_danxt.empty();
    var $index_duoxt=$("#index_duoxt");
    $index_duoxt.empty();
    var $index_jdt=$("#index_jdt");
    $index_jdt.empty();
    var $jdt_parent=$(".section");
    $jdt_parent.empty();
}