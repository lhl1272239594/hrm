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
    <title>班次设置</title>
	  <%@ include file="/static/include/easyui.jsp"%>
      <script type="text/javascript" src="/static/js/head.js"></script>
      <script type="text/javascript" src="/modules/fbd/hrm/attendance/js/frequency_rule.js"></script>
  </head>


  <body class="easyui-layout">
  <style type="text/css" rel="stylesheet">
      .fitem  {
          padding: 1px;
      }
  </style>

  <div id="ptb"  style="padding:5px;background:#eee;">
    <%--  <div  class="fitem">
          排班规则名称:<input class="easyui-textbox" id="freRule" style="width:70px" type="text"/>


      <a id="searchBtn" class="easyui-linkbutton" iconCls="icon-search">搜索</a>
      <a id="clearBtn" class="easyui-linkbutton" iconCls="icon-cancel">清空</a>
     </div>--%>
      <div  class="fitem">
      <a  id="addBtn" class="easyui-linkbutton" data-options="iconCls:'icon-add'">新增</a>
            <a  id="editBtn" class="easyui-linkbutton" data-options="iconCls:'icon-edit'">修改</a>
          <a  id="delBtn" class="easyui-linkbutton" data-options="iconCls:'icon-remove'">删除</a>

          <a id="viewBtn" class="easyui-linkbutton" data-options="iconCls:'icon-tip'">查看</a>

   </div>
  </div>
  <!-- 排班规则数据-->

  <table id="freRuleGrid"  class="easyui-datagrid" ></table>


  <%--<div id="dlg"></div>--%>
  <!-- 排班规则新增、修改-->
  <div id="editFreRuleGrid" >
  <div id="ftb"  style="padding:5px;background:#eee;">
      <form id="editForm" data-options="fit:true" method="post">
          <input type="hidden"  id="id" />
          规则名称：
          <input class="easyui-textbox" id="editFreRule" style="width:130px" type="text"  />
          <br>循环周期：
          <input class="easyui-textbox" id="editFreRuleLoopDays" style="width:130px"  value="0" disabled type="text"  />
          天
           <br>
          <a  id="addDateBtn" class="easyui-linkbutton" data-options="iconCls:'icon-add'">新增</a>
          <a  id="delDateBtn" class="easyui-linkbutton" data-options="iconCls:'icon-remove'">删除</a>
          <a  id="saveDateBtn" class="easyui-linkbutton" data-options="iconCls:'icon-save'">保存</a>
          </br>
      </form>

  </div>
          <!--规则详细数据-->
		   <table id="freRuleDataGrid"　class="easyui-datagrid"></table>
  </div>
  <!-- 时间控件-->
         <div id="cc" class="easyui-calendar"></div>
  </body>

</html>
