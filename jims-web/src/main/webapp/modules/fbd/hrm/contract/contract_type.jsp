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
    <title>合同管理</title>
	  <%@ include file="/static/include/easyui.jsp"%>
      <script type="text/javascript" src="/modules/fbd/hrm/contract/js/contract_type.js"></script>
      <script type="text/javascript" src="/modules/fbd/hrm/contract/js/contract_user_tree.js"></script>


  </head>


  <body class="easyui-layout">
  <style type="text/css" rel="stylesheet">
      .fitem  {
          padding: 10px 40px 10px 40px;
          line-height:27px;
      }

  </style>

  <table id="primaryGrid"  class="easyui-datagrid" ></table>

  <div id="tb"  style="padding:5px;background:#eee;">

      <div >
      <a  id="addBtn" class="easyui-linkbutton" data-options="iconCls:'icon-add'">新增</a>
      <a  id="editBtn" class="easyui-linkbutton" data-options="iconCls:'icon-edit'">修改</a>
      <a  id="delBtn" class="easyui-linkbutton" data-options="iconCls:'icon-remove'">删除</a>
   </div>
  </div>



  <div id="editWin"  style="height:250px;width:360px;"   data-options="modal:true,footer:'#dlg-buttons'">
      <form id="editForm" data-options="fit:true" method="post" enctype="multipart/form-data">
          <input type="hidden"  id="id" />
          <input type="hidden"  id="flag" />
          <input type="hidden"  id="url" />
          <table class="editTable">
                  <div  class="fitem">
                      <label style="width: 60px;" >合同类型：</label>
                      <input  id="editContractTypeDes" class="easyui-textbox"  style="width: 150px;height:27px"/>
                  </div>
              <div  class="fitem">

              <label style="width: 60px;" >提醒时间：</label>
                  <input  id="editContractRemindTime" class="easyui-numberspinner"  data-options="min:0,max:365"style="width: 150px;height:27px"  />天
                  </div>

           </table>
      </form>

  </div>
  <div id="dlg-buttons" style="padding:5px;text-align:right;" >
      <a  id="saveBtn" class="easyui-linkbutton" icon="icon-ok" >保存</a>
      <a  id="cancelBtn" class="easyui-linkbutton" icon="icon-cancel">取消</a>
  </div>
  </body>
  <script type="text/javascript" src="/static/js/head.js"></script>

</html>
