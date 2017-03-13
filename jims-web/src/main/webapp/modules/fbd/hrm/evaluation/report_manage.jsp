
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>报表管理</title>
    <link rel="stylesheet" type="text/css" href="/static/easyui/css/bootstrap/easyui.css">
    <link rel="stylesheet" type="text/css" href="/modules/fbd/hrm/css/public.css">
</head>
<body>

<table id="reportGrid" class="easyui-datagrid">
</table>
<div id="tb" style="padding:5px">
    <a  id="createBtn" class="easyui-linkbutton" iconCls="icon-edit">生成报表</a>
    <a  id="publicBtn" class="easyui-linkbutton" iconCls="icon-ok">发布报表</a>
    <a  id="openBtn" class="easyui-linkbutton" iconCls="icon-add">展开</a>
    <a  id="closeBtn" class="easyui-linkbutton" iconCls="icon-remove">合并</a>
</div>

<script type="text/javascript" src="/static/jquery/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="/static/js/head.js"></script>
<script type="text/javascript" src="/static/easyui/js/datagrid-detailview.js"></script>
<script type="text/javascript" src="/modules/fbd/hrm/evaluation/js/report_manage.js"></script>



</body>
</html>
