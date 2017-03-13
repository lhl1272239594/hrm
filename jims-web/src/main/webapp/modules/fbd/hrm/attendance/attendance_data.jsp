<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%

  String  fileName="kqsj.xls";
  String  path="\\modules\\fbd\\hrm\\template\\";
  String serverPath=request.getRealPath("\\")+path;

  String realPath=serverPath+fileName;
%>
<!DOCTYPE html>
<html>
  <head>
    <title>打卡数据查询</title>
	  <%@ include file="/static/include/easyui.jsp"%>
      <script type="text/javascript" src="/modules/fbd/hrm/attendance/js/attendance_data.js"></script>
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
  <br>
      <a  id="addBtn" class="easyui-linkbutton" data-options="iconCls:'icon-add'">导入</a>
<%--
    <a  id="editBtn" class="easyui-linkbutton" data-options="iconCls:'icon-edit'">修改</a>
--%>
    <a  id="delBtn" class="easyui-linkbutton" data-options="iconCls:'icon-remove'">删除</a>


  </div>
  <!-- 考勤数据-->

  <table id="attDataGrid"   ></table>

  <div id="editWin"  style="height:200px;width:550px;"   data-options="modal:true,footer:'#dlg-buttons'">
    <form action='/service/download/download-file?fileName=<%=fileName%>&path=<%=path%>' method='post'/>
    <div  class="fitem">
      <label style="width: 60px;" >数据模版：</label>

      <input  class="easyui-linkbutton" type='submit' value='点击下载'  style="width:200px;height:27px"/>
    </div>
    </form>
    <form id="editForm" method="post" enctype="multipart/form-data" name="editForm">
    <input type="hidden"  id="id" />
    <input type="hidden"  id="flag" />
    <input type="hidden"  id="url" />
    <input type="hidden"  id="editDeptId" />
      <table class="editTable">
        <div  class="fitem">
          <label style="width: 60px;">数据导入：</label>
          <input   type="file" id="myFiles"  name="myFiles"   style="width:200px;height:27px"/>
        </div>
      </table>
  </form>


  <div id="dlg-buttons" style="padding:5px;text-align:right;" >
    <a  id="saveBtn" class="easyui-linkbutton" icon="icon-ok" >保存</a>
    <a  id="cancelBtn" class="easyui-linkbutton" icon="icon-cancel">取消</a>
  </div>
  </div>
  <script type="text/javascript" src="/static/js/head.js"></script>

  </body>

</html>
