<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>

<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>在线评分</title>
    <link rel="stylesheet" type="text/css" href="/static/easyui/css/bootstrap/easyui.css">
    <link rel="stylesheet" type="text/css" href="/modules/fbd/hrm/css/public.css">
    <link href="/static/css/exam/style.css" rel="stylesheet" type="text/css" />
    <link href="/static/css/exam/layout.css" rel="stylesheet" type="text/css" />
    <script src="/static/css/exam/maxheight.js" type="text/javascript"></script>
</head>

<body class="easyui-layout">


<div  data-options="region:'center'" style="padding:1px;">
    <table id="gradeGrid" class="easyui-datagrid">

    </table>
</div>
<div id="tb" >
    考试类型：
    <input id="type" class="easyui-combobox combox_width" panelHeight="auto" editable="false"  />
    考试名称：
    <input id="planName" class="easyui-textbox combox_width"    />
    <a id="searchBtn" class="easyui-linkbutton" iconCls="icon-search">搜索</a>
    <a id="clearBtn" class="easyui-linkbutton" iconCls="icon-cancel">清空</a>
</div>
<!--按人评分 start-->
<div id="gradeByEachWin" >
    <table id="gradeByEachGrid" class="easyui-datagrid"></table>
</div>
<div id="testWin" style="height: auto;display: none">
        <!-- content -->
        <div style="height:30px;width: 100%">
            <div id="queContent">
                <div class="main-banner-small">
                    <div id="title" class="inner">
                        <h1></h1>
                        <div style="position:absolute;right:0;top:0;line-height:40px">
                            <a id="submitExam" style="color: white" href="###" class="test-submitBtn" onclick="submitValidate(); return false;">提交得分</a>
                        </div>

                    </div>

                </div>
            </div>
        </div>
        <div class="container">

            <div class="answer_section">

            </div>
        </div>

</div>
<script type="text/javascript" src="/static/jquery/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="/static/js/head.js"></script>
<script type="text/javascript" src="/modules/fbd/hrm/exam/js/grade.js"></script>

</body>
</html>