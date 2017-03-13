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
    <title>我的考试</title>
    <link rel="stylesheet" type="text/css" href="/static/easyui/css/bootstrap/easyui.css">

</head>
<body class="easyui-layout">
<style type="text/css" rel="stylesheet">
    .fitem  {
        padding: 10px 40px 10px 40px;
        line-height:27px;
    }


</style>

<div  data-options="region:'center'" style="padding:1px;">
    <table id="resultGrid" class="easyui-datagrid">

    </table>
</div>
<div id="tb" >
    查询月份：
    <input type="text" id="month" onfocus="WdatePicker({dateFmt:'yyyy-MM',readOnly:true})" class="Wdate"/>
<%--
    <input id="month" class="easyui-datebox combox_width" data-options="sharedCalendar:'#cc'"   editable="false">
--%>
    <input type="hidden" id="month1" />
    考试类型：
    <input id="type" class="easyui-combobox" panelHeight="auto" editable="false" style="width:150px" />
    考试名称：
    <input id="planName" class="easyui-textbox"   style="width:150px" />

    <a id="searchBtn" class="easyui-linkbutton" iconCls="icon-search">查询</a>
    <a id="clearBtn" class="easyui-linkbutton" iconCls="icon-cancel">清空</a>
</div>
<script type="text/javascript" src="/static/jquery/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="/static/js/head.js"></script>
<script type="text/javascript" src="/static/datepicker/WdatePicker.js"></script>
<script type="text/javascript" src="/modules/fbd/hrm/peinfo/js/myexam.js"></script>
</body>
</html>