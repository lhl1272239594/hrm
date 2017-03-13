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
    <title>社保项目管理</title>
	  <%@ include file="/static/include/easyui.jsp"%>
      <script type="text/javascript" src="/static/js/head.js"></script>
      <script type="text/javascript" src="/modules/fbd/hrm/socialSecurity/js/social_security_item.js"></script>

  </head>


  <body class="easyui-layout">
  <style type="text/css" rel="stylesheet">
      .fitem  {
          padding: 20px 40px 10px 40px;
          line-height:27px;
      }

  </style>

  <div id="tb"  style="padding:5px;background:#eee;">

      <div  >
      <a  id="addBtn" class="easyui-linkbutton" data-options="iconCls:'icon-add'">新增</a>
      <a  id="editBtn" class="easyui-linkbutton" data-options="iconCls:'icon-edit'">修改</a>
      <a  id="delBtn" class="easyui-linkbutton" data-options="iconCls:'icon-remove'">删除</a>
   </div>
  </div>


  <table id="primaryGrid"  class="easyui-datagrid" >

  </table>

  <div id="editWin"  style="height:240px;width:380px;"   data-options="modal:true,footer:'#dlg-buttons'">
      <form id="editForm" data-options="fit:true" method="post">
          <input type="hidden"  id="id" />
          <input type="hidden"  id="flag" />
          <input type="hidden"  id="freItemId" />
          <input type="hidden"  id="editDeptId" />
          <table class="editTable">
              <div  class="fitem">
                  <label style="width: 90px;">项目名称：</label>
                        <input  class="easyui-textbox" id="editItemDes"  style="width: 150px;height:27px"/>
              </div>
              <div class="fitem">
                  <label style="width: 90px;">项目类型：</label>
                        <input class="easyui-combobox" id="editItemTypeId" panelHeight="140px" style="width:150px;height:27px" editable="false" />
              </div>
          </table>
      </form>
  </div>
  <div id="dlg-buttons" style="padding:5px;text-align:right;" >
      <a  id="saveBtn" class="easyui-linkbutton" icon="icon-ok">保存</a>
      <a  id="cancelBtn" class="easyui-linkbutton" icon="icon-cancel">关闭</a>
  </div>
  </body>

</html>
