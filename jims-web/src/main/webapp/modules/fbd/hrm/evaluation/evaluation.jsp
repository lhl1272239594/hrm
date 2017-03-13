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
</head>

<body class="easyui-layout">


<div  data-options="region:'center'" style="padding:1px;">
    <table id="gradeGrid" class="easyui-datagrid">
    </table>
</div>
<div id="tb" >
    <a  id="gradeBtn" class="easyui-linkbutton" data-options="iconCls:'icon-add'">进入评分</a>
</div>
<!--按人评分 start-->
<div id="grade" style="display: none" data-options="modal:true,footer:'#grade-buttons'">
    <div  style="width:25%;height:100%;float:left;">
        <table id="objGrid" class="easyui-datagrid"></table>
    </div>
    <div  style="width:75%;height:100%;float:left;">
        <table id="gradeByEachGrid" class="easyui-datagrid"></table>
        <div id="grade_tb">
            <%--考评对象：<input id="obj" class="easyui-combobox" panelHeight="auto" editable="false" style="width:150px" />--%>
            <a  id="submitBtn" class="easyui-linkbutton" data-options="iconCls:'icon-ok'">提交</a>
            <a  id="submitAllBtn" class="easyui-linkbutton" data-options="iconCls:'icon-ok'">全部提交</a>
            <span class="score">总分：</span><span class="score" id="totalScore"></span><span class="score" style="margin-left: 10px">当前分数：</span><span class="score" id="tempScore"></span>

        </div>
    </div>
</div>
<div style="padding:5px;text-align:right;" id="grade-buttons">
    <a  id="backBtn" class="easyui-linkbutton" icon="icon-cancel">返回</a>
</div>
<script type="text/javascript" src="/static/jquery/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="/static/js/head.js"></script>
<script type="text/javascript" src="/static/easyui/js/jquery.edatagrid.js"></script>
<script type="text/javascript" src="/modules/fbd/hrm/evaluation/js/evaluation.js"></script>

</body>
</html>