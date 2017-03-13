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
    <title>社保方案管理</title>
	  <%@ include file="/static/include/easyui.jsp"%>
      <script type="text/javascript" src="/static/js/head.js"></script>
      <script type="text/javascript" src="/modules/fbd/hrm/socialSecurity/js/social_security_plan.js"></script>
  </head>


  <body class="easyui-layout">
  <style type="text/css" rel="stylesheet">
      .fitem  {
          padding: 1px;
      }
  </style>

  <%--<div id="plantb"  style="padding:5px;background:#eee;">
      <a  id="addBtn" class="easyui-linkbutton" data-options="iconCls:'icon-add'">新增</a>
      <a  id="editBtn" class="easyui-linkbutton" data-options="iconCls:'icon-edit'">修改</a>
      <a  id="delBtn" class="easyui-linkbutton" data-options="iconCls:'icon-remove'">删除</a>
      <a  id="viewBtn" class="easyui-linkbutton" data-options="iconCls:'icon-tip'">查看</a>

   </div>--%>
  <div id="tb" style="padding:5px;display: none">
      社保方案名称：<input id="NAME" type="text" class="easyui-textbox" style="width:140px">
      <a id="searchBtn" class="easyui-linkbutton" iconCls="icon-search">查询</a>
      <a id="clearBtn" class="easyui-linkbutton" iconCls="icon-cancel">清空</a>
      <br/>
      <a  id="addBtn" class="easyui-linkbutton" iconCls="icon-add">新增</a>
      <a  id="editBtn" class="easyui-linkbutton" iconCls="icon-edit">修改</a>
      <a  id="delBtn" class="easyui-linkbutton" iconCls="icon-remove">删除</a>
      <a  id="viewBtn" class="easyui-linkbutton" data-options="iconCls:'icon-tip'">查看</a>
      <%--<a  id="okBtn" class="easyui-linkbutton" data-options="iconCls:'icon-ok'">启用</a>
      <a  id="noBtn" class="easyui-linkbutton" data-options="iconCls:'icon-no'">停用</a>--%>
  </div>
  <!-- 数据-->

  <table id="planGrid"  class="easyui-datagrid" ></table>


  <!-- 新增、修改-->
      <div id="editPlan" >
  <div id="datatb"  style="padding:5px;background:#eee;">
      <form id="editForm" data-options="fit:true" method="post">
      <div  class="fitem">
          <input type="hidden"  id="flag" />
          <input type="hidden"  id="planId" />
          方案名称:
          <input class="easyui-textbox" id="editPlanDes" style="width:140px;height:27px" type="text"/>

          <a  id="addDetailBtn" class="easyui-linkbutton" data-options="iconCls:'icon-add'">新增</a>
          <a  id="saveDetailBtn" class="easyui-linkbutton" data-options="iconCls:'icon-save'">保存</a>
          <a  id="removeDetailBtn" class="easyui-linkbutton" data-options="iconCls:'icon-remove'">删除</a>

      </div>
      </form>
  </div>

		   <table id="detailGrid"　class="easyui-datagrid"></table>
  </div>
  </body>

</html>
