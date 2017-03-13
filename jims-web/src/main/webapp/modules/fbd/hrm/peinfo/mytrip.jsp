<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html>
<head>
    <title>我的公出</title>
    <%@ include file="/static/include/easyui.jsp" %>
    <script type="text/javascript" src="/static/js/head.js"></script>
    <script type="text/javascript" src="/modules/fbd/hrm/peinfo/js/mytrip.js"></script>
    <script type="text/javascript" src="/modules/fbd/hrm/attendance/js/att_user_tree.js"></script>


</head>


<body class="easyui-layout">
<div id="tb" style="padding:5px;background:#eee;">
    查询月份：
    <input id="month" class="easyui-datebox combox_width" editable="false">
    <input type="hidden" id="month1"/>
    审批状态:<input class="easyui-combobox combox_width" id="approveStatus" panelHeight="auto" editable="false"/>

    <a id="searchAllBtn" class="easyui-linkbutton" iconCls="icon-search">查询</a>
    <a id="clearBtn" class="easyui-linkbutton" iconCls="icon-cancel">清空</a>
</div>


<table id="primaryGrid" class="easyui-datagrid"></table>
</body>

</html>
