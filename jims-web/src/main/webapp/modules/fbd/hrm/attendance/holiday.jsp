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
    <title>假日设置</title>
	  <%@ include file="/static/include/easyui.jsp"%>
      <script type="text/javascript" src="/static/js/head.js"></script>
      <script type="text/javascript" src="/modules/fbd/hrm/attendance/js/holiday.js"></script>
  </head>


  <body class="easyui-layout">

  <div id="tb"  style="padding:5px;background:#eee;">
      <a  id="addBtn" class="easyui-linkbutton" data-options="iconCls:'icon-add'">新增</a>
      <a  id="editBtn" class="easyui-linkbutton" data-options="iconCls:'icon-edit'">修改</a>
      <a  id="delBtn" class="easyui-linkbutton" data-options="iconCls:'icon-remove'">删除</a>
  </div>
  <!-- 假日数据-->

  <table id="holidayGrid"  class="easyui-datagrid" ></table>
  <!--添加假日-->
  <div id="addHoliday"  style="height: 160px;width:300px;"   data-options="modal:true,footer:'#dlg-buttons'">
      <form id="holForm" data-options="fit:true" method="post">
          <input type="hidden" id="holId" />
          <input type="hidden" id="flag" />
          <div class="fitem">
              <label style="width: 60px;">假日名称：</label>
              <input  class="easyui-textbox" id="editHolDes" style="width:140px;height:27px" />
          </div>
      </form>
  </div>
  <div id="dlg-buttons" style="padding:5px;text-align:right;" >
      <a  id="saveBtn" class="easyui-linkbutton" icon="icon-ok">确定</a>
      <a  id="cancelBtn" class="easyui-linkbutton" icon="icon-cancel">取消</a>
  </div>
  </body>

</html>
