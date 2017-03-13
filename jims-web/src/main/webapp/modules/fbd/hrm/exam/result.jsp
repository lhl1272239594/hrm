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
    <title>成绩查询</title>
    <link rel="stylesheet" type="text/css" href="/static/easyui/css/bootstrap/easyui.css">
    <link rel="stylesheet" type="text/css" href="/modules/fbd/hrm/css/public.css">
</head>
<body class="easyui-layout">

<div  data-options="region:'center'" style="padding:1px;">
    <table id="resultGrid" class="easyui-datagrid">

    </table>
</div>
<div id="tb" >
    考试类型：
    <input id="type" class="easyui-combobox combox_width" panelHeight="auto" editable="false"  />
    考试名称：
    <input id="planName" class="easyui-textbox combox_width" />
    考生姓名：
    <input id="userName" class="easyui-textbox combox_width"    />

    <a id="searchBtn" class="easyui-linkbutton" iconCls="icon-search">查询</a>
    <a id="clearBtn" class="easyui-linkbutton" iconCls="icon-cancel">清空</a>
</div>

<script type="text/javascript" src="/static/jquery/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="/static/js/head.js"></script>
<script type="text/javascript" src="/modules/fbd/hrm/exam/js/result.js"></script>
</body>
</html>