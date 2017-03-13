var basePath = "/service";
var orgId = '';
var examId = '';
var planId = '';
var examId = '';
var planName = '';
var sortArray = new Array();
var id = '';
var score = 0;
var checkScore = 0;
var scoreId;
var page = '1';
var rows = '30';
var deptIds = parent.orgids;
var search = false;
$(function () {
    orgId = parent.config.org_Id;
    var type = '';//考试类型
    var planName = '';//考试计划名称
    $("#gradeGrid").datagrid({
        toolbar: '#tb',
        width: 'auto',
        height: '99%',
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
        url: basePath + '/examGrade/gradeList?orgId=' + orgId + "&type=" + type + "&planName=" + planName + '&deptIds=' + deptIds,
        remoteSort: false,
        idField: 'planId',
        singleSelect: true,//是否单选
        rownumbers: true,//行号
        columns: [[
            {field: 'typeName', title: '考试类型', width: '10%', align: 'center'},
            {field: 'planName', title: '考试名称', width: '30%', align: 'center'},
            {field: 'start', title: '开始时间', width: '15%', align: 'center'},
            {field: 'end', title: '结束时间', width: '15%', align: 'center'},
            {field: 'gradeNum', title: '已评试卷', width: '10%', align: 'center'},
            {field: 'unGradeNum', title: '未评试卷', width: '10%', align: 'center'},
            {
                field: 'h', title: '操作', width: '10%', align: 'center', formatter: function (value, row, index) {
                return '<a href="#" name="grade"  class="easyui-linkbutton" iconCls="icon-edit"  onclick="gradeByEach(\'' + row.planId + '\',\'' + row.examId + '\',\'' + row.checkScore + '\')">按人评分</a>';
            }
            }

        ]], onLoadSuccess: function (data) {
            $("a[name='grade']").linkbutton({iconCls: 'icon-edit'});
            $("#testWin").css('display', 'block');
            $('#gradeGrid').datagrid('fixRowHeight');
        }
    });
    //配置考试窗口
    $("#testWin").window({
        fit: true,
        closed: true,
        modal: true,
        onClose: function () {
            $("#gradeByEachGrid").datagrid('reload');
        },
        onOpen: function () {

        }

    });
    //配置新增题型窗口
    $("#gradeByEachWin").window({
        title: '按人评分',
        closed: true,
        modal: true,
        width: '1000px',
        height: '500px',
        onClose: function () {
            $("#gradeGrid").datagrid('reload');
        },
        onOpen: function () {
            $("#gradeByEachGrid").datagrid({
                width: 'auto',
                height: 'auto',
                nowrap: false,
                striped: true,
                border: true,
                method: 'get',
                loadMsg: '数据正在加载中，请稍后.....',
                pagination: false,//分页控件
                pageSize: 30,
                pageList: [10, 20, 30, 40, 50],//可以设置每页记录条数的列表
                collapsible: false,//是否可折叠的
                fit: true,//自动大小
                url: basePath + '/examGrade/gradeByEachList?orgId=' + orgId + "&planId=" + planId,
                remoteSort: false,
                idField: 'socreId',
                singleSelect: true,//是否单选
                rownumbers: true,//行号
                columns: [[
                    {field: 'examName', title: '试卷名称', width: '20%', align: 'center'},
                    {field: 'userName', title: '考生姓名', width: '10%', align: 'center'},
                    {
                        field: 'state', title: '状态', width: '10%', align: 'center',
                        formatter: function (state) {
                            if (state == '0') {
                                return "未考试";
                            }
                            if (state == '1') {
                                return "未交卷";
                            }
                            if (state == '2') {
                                return "等待评分";
                            }
                            if (state == '3') {
                                return "及格";
                            }
                            if (state == '4') {
                                return "不及格";
                            }
                        }
                    },
                    {field: 'score', title: '考试分数', width: '10%', align: 'center'},
                    {field: 'endTime', title: '交卷时间', width: '15%', align: 'center'},
                    {field: 'gradeTime', title: '评卷时间', width: '15%', align: 'center'},
                    {field: 'gradeBy', title: '评卷人', width: '10%', align: 'center'},
                    {
                        field: 'h',
                        title: '操作',
                        width: '10%',
                        align: 'center',
                        formatter: function (value, row, index) {
                            if (row.state == '2') {
                                return '<a href="#" name="grade"  class="easyui-linkbutton" iconCls="icon-edit"  onclick="grade(\'' + row.socreId + '\',\'' + row.state + '\',\'' + row.examName + '\')">人工评分</a>';
                            }
                        }
                    }

                ]], onLoadSuccess: function (data) {
                    $("a[name='grade']").linkbutton({iconCls: 'icon-edit'});
                    $('#gradeByEachGrid').datagrid('fixRowHeight');
                }
            });
        }
    });
    $("#type").combobox({     //加载计划类型
        url: basePath + '/dict/find-list-by-type?type=' + 'EXAM_TYPE',
        valueField: 'id',
        textField: 'value',
        value: '请选择',
        method: 'GET'

    });

    var searchData = function (page, rows) {
            $("#gradeGrid").datagrid('reload',basePath + '/examGrade/gradeList?orgId=' + orgId + "&type=" + type + "&planName=" + planName + '&page=' + page + '&rows=' + rows + '&deptIds=' + deptIds);
            if (search) {
                search = false;
                $("#gradeGrid").datagrid('getPager').pagination('select', 1);
            }
    }
    $("#searchBtn").on("click", function () {
        search = true;
        //获取计划类型ID
        type = $("#type").combobox('getValue');
        if (type == '请选择') {
            type = '';
        }
        //获取考试计划名称
        planName = $("#planName").textbox('getValue');
        searchData(page, rows);
    });

    $("#clearBtn").on("click", function () {
        clearKey();
    });


});
function gradeByEach(id, id1, score) {
    planId = id;
    examId = id1;
    checkScore = score;
    $("#gradeByEachWin").window('open');
}
function grade(scoreId, state, name) {
    id = scoreId;
    if (state == '3' || state == '4') {
        $.messager.alert('提示', '已评分', 'info');
        return;
    }
    if (state == '0') {
        $.messager.alert('提示', '该考生未参加考试', 'info');
        return;
    }
    $("#testWin").window('open');
    if (state == '1' || state == '2') {

        var test = {};
        test.scoreId = scoreId;

        $.postJSON(basePath + "/examGrade/getQuestion", JSON.stringify(test), function (data) {
            if (data.data == "success") {
                if (data.examDetails.length > 0)
                    test_init(scoreId, data.examDetails, name)
            }
            else {
                $.messager.alert("提示", "失败", 'info');
            }
        });
    }
}
//清空查询条件
function clearKey() {
    //清空题型ID
    $("#type").combobox('clear');//获取表格对象
    $("#type").combobox('setValue', '请选择');//获取表格对象
    type = '';
    //清空考试计划名称
    $("#planName").textbox('clear');
    planName = '';

}
//清空试卷内容
function clearTest() {
    //清空试题
    $(".answer_section").empty();

}
//答题卡初始化
function test_init(id, examDetails, name) {
    clearTest();
    score = parseInt(examDetails[0].score);
    $("#title h1").text(name);
    var question_html = "<div class='lefts'><span class='answer_type'>标准答案</span></div>" +
        "<div class='rights'><span class='answer_type'>考生答案</span></div>";
    for (var i = 0; i < examDetails.length; i++) {
        var answer = '';
        if (examDetails[i].answer != null) {
            answer = examDetails[i].answer;
        }
        sortArray.push(examDetails[i].sort);
        question_html += "<div class='lefts'>" +
            "<span class='answer_mode ' style='margin-bottom: 37px;'>" +
            "<span class='question_number' >" + examDetails[i].sort + ".</span>" +
            "<span class='answer_border'>" +
            "<span class='question-title   question-name_text_border'>" + examDetails[i].queName + "</span>" +
            "<ul class='shortAnswer-block_body_options'>" +
            "<textarea readonly='readonly' style='resize:none;font-size:16px;height:180px;width:520px'>" + examDetails[i].queAnswer + "</textarea>" +
            "</ul><span class='answer-score'></span></span></span></div>" +
            "<div class='rights'>" +
            "<span class='answer_mode'>" +
            "<span class='question_number' >" + examDetails[i].sort + ".</span>" +
            "<span class='answer_border'>" +
            "<span class='question-title   question-name_text_border'>" + examDetails[i].queName + "</span>" +
            "<ul class='shortAnswer-block_body_options'>" +
            "<textarea readonly='readonly' style='resize:none;font-size:16px;height:180px;width:520px'>" + answer + "</textarea>" +
            "</ul>" +
            "<span class='answer-score'><span>本题分值" + examDetails[i].score + "分</span><span style='padding-left: 10px;'>得分<span style='padding: 0 5px;'><input id='score_" + examDetails[i].sort + "'  class='easyui-numberbox'  data-options='min:0,precision:0' style='width:80px;height:27px' /></span>分</span> </span>" +
            "</span></span></div>";
    }
    var $jdt_parent = $(".answer_section");
    var targetObj = $jdt_parent.append(question_html);
    $.parser.parse(targetObj);
}
function submitValidate() {
    var test = {};
    var examDetails = [];

    for (var i = 0; i < sortArray.length; i++) {
        var gradeScore = $("#score_" + sortArray[i]).numberbox('getValue');
        if (gradeScore == '') {
            $.messager.alert("提示", "第" + sortArray[i] + "道题没有评分！", 'info');
            return;
        }
        if (gradeScore > score) {
            $.messager.alert("提示", "第" + sortArray[i] + "题评分大于题目分值！", 'info');
            return;
        }
        var examDetail = {};
        examDetail.sort = sortArray[i];
        examDetail.resultScore = gradeScore;
        examDetail.scoreId = id;
        examDetails.push(examDetail);
    }
    $.messager.confirm("提示", "确认提交评分?", function (r) {
        if (r) {
            test.examDetails = examDetails;
            test.scoreId = id;
            test.planId = planId;
            test.examId = examId;
            test.score = checkScore;
            test.userId = parent.config.persion_Id;
            $.postJSON(basePath + "/examGrade/saveGrade", JSON.stringify(test), function (data) {
                if (data.data == "success") {
                    $("#testWin").window('close');
                }
            }, function (data) {
                $.messager.alert('提示', '保存失败', 'info');
            });

        }
    });
}
