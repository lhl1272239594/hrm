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
    <title>我的评分</title>
    <link rel="stylesheet" type="text/css" href="/static/easyui/css/bootstrap/easyui.css">
    <link rel="stylesheet" type="text/css" href="/modules/fbd/hrm/css/public.css">
</head>

<body class="easyui-layout">

    <table id="gradeGrid" class="easyui-datagrid">
    </table>
    <div id="tb" style="padding:5px;display: none">
        月份： <input id="month" class="easyui-datebox combox_width"    editable="false">
        <input type="hidden" id="month1" />
        考评标题：
        <input id="searchName" class="easyui-textbox combox_width"  />
        考评类型：
        <input id="searchType" class="easyui-combobox combox_width"  panelHeight="auto"  editable="false"/>

        <a id="searchBtn" class="easyui-linkbutton" iconCls="icon-search">查询</a>
        <a id="clearBtn" class="easyui-linkbutton" iconCls="icon-cancel">清空</a>
    </div>
<!--按人评分 start-->
<div id="grade" style="display: none" data-options="modal:true,footer:'#grade-buttons'">
    <div  style="width:20%;height:100%;float:left;">
        <table id="objGrid" class="easyui-datagrid"></table>
    </div>
    <div  style="width:80%;height:100%;float:left;">
        <table id="gradeByEachGrid" class="easyui-datagrid"></table>
        <div id="grade_tb">
            <div style="padding: 3px;">
                <span class="score">总分：</span><span class="score" id="totalScore"></span><span class="score" style="margin-left: 10px">当前分数：</span><span class="score" id="tempScore"></span>

            </div>
        </div>
    </div>
</div>
<div style="padding:5px;text-align:right;" id="grade-buttons">
    <a  id="backBtn" class="easyui-linkbutton" icon="icon-cancel">返回</a>
</div>
<script type="text/javascript" src="/static/jquery/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="/static/js/head.js"></script>
<script type="text/javascript" src="/modules/fbd/hrm/evaluation/js/my_grade.js"></script>

</body>
</html>