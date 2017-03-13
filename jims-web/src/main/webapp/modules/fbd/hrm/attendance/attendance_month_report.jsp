<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
  <head>
    <title>考勤记录查询</title>
	  <%@ include file="/static/include/easyui.jsp"%>
      <script type="text/javascript" src="/static/js/head.js"></script>
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


      <a id="searchBtn" class="easyui-linkbutton" iconCls="icon-search">搜索</a>
      <a id="clearBtn" class="easyui-linkbutton" iconCls="icon-cancel">清空</a>



  </div>
  <!-- 考勤数据-->

  <table id="attDataGrid"   ></table>



  </div>

  </body>

</html>
