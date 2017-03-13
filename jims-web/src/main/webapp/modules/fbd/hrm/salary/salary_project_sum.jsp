<%--
  Created by IntelliJ IDEA.
  User:
  Date: 2016/8/21
  Time: 10:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>工资项目金额</title>
    <%@ include file="/static/include/init.html" %>
    <%--<link rel="stylesheet" type="text/css" href="/static/easyui/css/bootstrap/easyui.css">--%>
</head>
<body class="easyui-layout">
<style type="text/css" rel="stylesheet">
    .fitem label {
        width: 60px;
    }
</style>
<!--人员类别  start-->
<div id=examClass" data-options="region:'west'" style="width:15%;height:100%;float: left;overflow:auto;">
    <table id="staff" class="easyui-datagrid">

    </table>
</div>

<!--人员类别  end-->

<!--工资项目金额  start-->
<div id="examSubclass" data-options="region:'center'" style="width:85%;height:100%;float:left;overflow:auto">
    <table id="staffGrid" class="easyui-datagrid">

    </table>
</div>
<div id="ft" style="display: none">
    <a  id="addBtn" class="easyui-linkbutton" data-options="iconCls:'icon-add'">新增</a>
    <a  id="saveBtn" class="easyui-linkbutton" data-options="iconCls:'icon-save'">保存</a>
    <a  id="delBtn" class="easyui-linkbutton" data-options="iconCls:'icon-remove'">删除</a>
</div>
<!--工资项目金额  end-->

<%--<script type="text/javascript" src="/static/jquery/jquery-1.11.3.min.js"></script>--%>
<script type="text/javascript" src="/modules/fbd/hrm/salary/js/salary_project_sum.js"></script>
<%--<script type="text/javascript" src="/static/js/head.js"></script>--%>
<script type="text/javascript" src="/static/easyui/js/jquery.edatagrid.js"></script>
</body>
</html>