<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
  <head>
    <title>考勤记录查询</title>
	  <%@ include file="/static/include/easyui.jsp"%>
      <script type="text/javascript" src="/modules/fbd/hrm/attendance/js/attendance_day_report.js"></script>
  </head>

  <style type="text/css" rel="stylesheet">
    .fitem  {
      padding: 10px 40px 10px 40px;
      line-height:27px;
    }

  </style>

  <body class="easyui-layout">

  <div id="tb"  style="padding:5px;background:#eee;">
          员工姓名：<input class="easyui-textbox" id="userName" style="width:140px" type="text"/>
          考勤时间：<input id="freTimeMonth" class="easyui-datebox" style="width:140px;height:27px" editable="false">
      <a id="searchBtn" class="easyui-linkbutton" iconCls="icon-search">搜索</a>
      <a id="clearBtn" class="easyui-linkbutton" iconCls="icon-cancel">清空</a>

    <br>
    <a  id="viewBtn" class="easyui-linkbutton" data-options="iconCls:'icon-tip'">详情</a>
  </div>
  <!-- 考勤数据-->
  <table id="attDataGrid"   ></table>

  <div id="editWin" style="height:500px;width:700px;" data-options="modal:true">
    人员：<input class="easyui-textbox" disabled id="recordUserName"  style="height:27px;width: 80px"/>
    科室：<input class="easyui-textbox" disabled id="recordDeptName"  style="height:27px;width: 160px"/>
    <table id="dataGrid"   ></table>

  </div>

  </body>
  <script type="text/javascript" src="/static/js/head.js"></script>

</html>
