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
    <title>我的工资</title>
    <%@ include file="/static/include/init.html" %>
    <%--<link rel="stylesheet" type="text/css" href="/static/easyui/css/bootstrap/easyui.css">--%>
</head>
<body>
<style type="text/css" rel="stylesheet">
    .fitem  {
        padding: 10px 40px 10px 40px;
        line-height:67px;
    }
    .info {
        color: #ff6b06;
    }
</style>
<table id="dataGrid" class="easyui-datagrid">

</table>
<div id="tb" style="padding:5px;display: none">
    <span id="month">
    月 份：<%--<input id="SALARY_MONTH" class="easyui-datebox" style="width:140px" editable="false">--%>
        <input type="text" id="SALARY_MONTH" onfocus="WdatePicker({dateFmt:'yyyy-MM',readOnly:true})" class="Wdate"/>
        </span>
    <input type="hidden" id="SALARY_MONTH1" />
    <a id="searchBtn" class="easyui-linkbutton" iconCls="icon-search">查询</a>
    <%--<a id="clearBtn" class="easyui-linkbutton" iconCls="icon-cancel">清空</a>--%>
    <%--<a id="editBtn" class="easyui-linkbutton" data-options="iconCls:'icon-edit'">历史信息</a>--%>
    <a id="infoBtn" class="easyui-linkbutton" data-options="iconCls:'icon-save'">工资详单</a>
</div>
<!-- 工资详单start -->
<div id="newDialog" class="easyui-window" style="width:600px;height:500px;overflow: auto;display: none">
    <table id="dataGrid1" class="easyui-datagrid">

    </table>
    <div id="tb1" >

        <span class="info" style="margin-left: 5px">姓 名：</span><span class="info" id="NAME"></span>
        <span class="info" style="margin-left: 30px">工资月份：</span><span class="info" id="TIME"></span>
        <span class="info" style="margin-left: 50px">单 位：</span><span class="info" id="ORG"></span>
    </div>
</div>

<%--<script type="text/javascript" src="/static/jquery/jquery-1.11.3.min.js"></script>--%>
<script type="text/javascript" src="/modules/fbd/hrm/peinfo/js/mypay.js"></script>
<script type="text/javascript" src="/static/datepicker/WdatePicker.js"></script>
<%--<script type="text/javascript" src="/static/js/head.js"></script>--%>


</body>
</html>

