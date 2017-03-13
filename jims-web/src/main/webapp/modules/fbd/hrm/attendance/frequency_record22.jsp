<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
  <head>
    <title>排班管理</title>
	  <%@ include file="/static/include/easyui.jsp"%>
      <script type="text/javascript" src="/static/js/head.js"></script>
      <script type="text/javascript" src="/modules/fbd/hrm/attendance/js/fullcalendar.js"></script>

  </head>


  <body class="easyui-layout">
  <style type="text/css" rel="stylesheet">
      .fitem  {
          padding: 1px;
      }
  </style>

  <div id="tb"  style="padding:5px;background:#eee;">
      <div  class="fitem">
          员工姓名:<input class="easyui-combobox" id="userName" style="width:70px" type="text"/>
          员工编号:<input class="easyui-combobox" id="userCode" style="width:70px" type="text"/>


      <a id="searchAllBtn" class="easyui-linkbutton" iconCls="icon-search">查询汇总信息</a>
       <a id="searchDetailBtn" class="easyui-linkbutton" iconCls="icon-search">查询详细信息</a>
      <a id="clearBtn" class="easyui-linkbutton" iconCls="icon-cancel">清空</a>
     </div>
      <div  class="fitem">
      <a  id="addBtn" class="easyui-linkbutton" data-options="iconCls:'icon-add'">新增</a>
      <a  id="editBtn" class="easyui-linkbutton" data-options="iconCls:'icon-edit'">修改</a>
      <a  id="removeBtn" class="easyui-linkbutton" data-options="iconCls:'icon-remove'">删除</a>
   </div>
  </div>
  <!-- 假日数据-->

  <div id='calendar'></div>
  </body>

</html>
