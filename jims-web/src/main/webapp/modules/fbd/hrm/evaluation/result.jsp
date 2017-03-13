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
    <title>考评成绩</title>
    <link rel="stylesheet" type="text/css" href="/static/easyui/css/bootstrap/easyui.css">
    <link rel="stylesheet" type="text/css" href="/modules/fbd/hrm/css/public.css">
</head>

<body class="easyui-layout">

<div  data-options="region:'center'" style="padding:1px;">
    <table id="planGrid" class="easyui-datagrid">
    </table>
</div>
<div id="tb" >
    模板名称：
    <input id="searchName" class="easyui-textbox combox_width"  />
    考评类型：
    <input id="searchType" class="easyui-combobox combox_width"  panelHeight="auto"  editable="false"/>

    <a id="searchBtn" class="easyui-linkbutton" iconCls="icon-search">查询</a>
    <a id="clearBtn" class="easyui-linkbutton" iconCls="icon-cancel">清空</a>
</div>
<!--查看成绩 start-->
<div id="grade" style="display: none" data-options="modal:true,footer:'#grade-buttons'">
    <table id="gradeGrid" class="easyui-datagrid"></table>
</div>
<div style="padding:5px;text-align:right;" id="grade-buttons">
    <a  id="gradeBackBtn" class="easyui-linkbutton" icon="icon-ok">返回</a>
</div>
<!--查看具体评分 start-->
<div id="gradeByEach" style="display: none" data-options="modal:true,footer:'#gradeEach-buttons'">
    <div  id="left" style="width:20%;height:100%;float:left;">
        <table id="objGrid" class="easyui-datagrid"></table>
    </div>
    <div  id="right" style="width:80%;height:100%;float:left;">
        <table id="gradeByEachGrid" class="easyui-datagrid"></table>
        <div id="grade_tb">
            <div style="padding: 3px;">
                <span class="score">总分：</span><span class="score" id="totalScore"></span><span class="score" style="margin-left: 10px">当前分数：</span><span class="score" id="tempScore"></span>
            </div>
        </div>
    </div>
</div>

<div style="padding:5px;text-align:right;" id="gradeEach-buttons">
    <a  id="gradeEachBackBtn" class="easyui-linkbutton" icon="icon-ok">返回</a>
</div>
<script type="text/javascript" src="/static/jquery/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="/static/js/head.js"></script>
<script type="text/javascript" src="/modules/fbd/hrm/evaluation/js/result.js"></script>

</body>
</html>