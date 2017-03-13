
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>报表查询</title>
    <link rel="stylesheet" type="text/css" href="/static/easyui/css/bootstrap/easyui.css">
    <link rel="stylesheet" type="text/css" href="/modules/fbd/hrm/css/public.css">
</head>
<body>

<table id="reportGrid" class="easyui-datagrid">
</table>
<div id="tb" style="padding:5px">
    查询月份：
    <input id="month" class="easyui-datebox combox_width"  editable="false">
    <input type="hidden" id="month1" />

    <a id="searchAllBtn" class="easyui-linkbutton" iconCls="icon-search">查询</a>
    <a id="clearBtn" class="easyui-linkbutton" iconCls="icon-cancel">清空</a>
    <a  id="openBtn" class="easyui-linkbutton" iconCls="icon-add">展开</a>
    <a  id="closeBtn" class="easyui-linkbutton" iconCls="icon-remove">合并</a>
</div>


<script type="text/javascript" src="/static/jquery/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="/static/js/head.js"></script>
<script type="text/javascript" src="/static/easyui/js/datagrid-detailview.js"></script>
<script type="text/javascript" src="/modules/fbd/hrm/evaluation/js/report.js"></script>



</body>
</html>
