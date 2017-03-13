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
    <title>加班管理</title>
	  <%@ include file="/static/include/easyui.jsp"%>
      <script type="text/javascript" src="/static/js/head.js"></script>
      <script type="text/javascript" src="/modules/fbd/hrm/attendance/js/over_time.js"></script>
      <script type="text/javascript" src="/modules/fbd/hrm/attendance/js/att_user_tree.js"></script>

  </head>


  <body class="easyui-layout">
  <style type="text/css" rel="stylesheet">
      .fitem  {
          padding: 10px 40px 10px 40px;
          line-height:27px;
      }

  </style>
  <%--人员选择--%>
  <div id="choosePerson"  style="height: 500px;width:800px;display: none"   data-options="modal:true,footer:'#choose-buttons'">
      <div id="west" data-options="region:'west'"  style="width:35%;height:100%;float: left;overflow:auto;">

          <ul  id="userTree" class="easyui-tree" ></ul >

      </div>
      <div id="center" data-options="region:'center'"  style="width:10%;height:100%;float:left;">
          <div class="btn" style="margin-top: 200px">
              <a  onclick="addPerson()" style="float: left" class="easyui-linkbutton" data-options="iconCls:'icon-redo'">添加</a>
          </div>
          <div class="btn" style="margin-top: 30px">
              <a  onclick="removePerson()"style="float: left"  class="easyui-linkbutton" data-options="iconCls:'icon-undo'">移除</a>
          </div>
      </div>
      <div id="east" data-options="region:'east'"  style="width:55%;height:100%;float:left;">
          <table class="easyui-datagrid" title="已选人员" id="personGrid">
          </table>
      </div>

      <div style="padding:5px;text-align:right;" id="choose-buttons">
          <a  onclick="savePerson()" class="easyui-linkbutton" icon="icon-ok">确定</a>
          <a  onclick="cancelPerson()" class="easyui-linkbutton" icon="icon-cancel">取消</a>
      </div>
  </div>
  <table id="primaryGrid"  class="easyui-datagrid" ></table>

  <div id="tb"  style="padding:5px;background:#eee;">
      <div >
          查询月份：
          <input id="month" class="easyui-datebox combox_width" editable="false">
          <input type="hidden" id="month1"/>
          员工姓名：<input class="easyui-textbox combox_width"  id="userId" />
          审批状态：<input class="easyui-combobox combox_width" id="approveStatus" panelHeight="auto" editable="false" />

       <a id="searchAllBtn" class="easyui-linkbutton" iconCls="icon-search">查询</a>
       <a id="clearBtn" class="easyui-linkbutton" iconCls="icon-cancel">清空</a>
     </div>
      <div  >
      <a  id="addBtn" class="easyui-linkbutton" data-options="iconCls:'icon-add'">新增</a>
      <a  id="editBtn" class="easyui-linkbutton" data-options="iconCls:'icon-edit'">修改</a>
      <a  id="delBtn" class="easyui-linkbutton" data-options="iconCls:'icon-remove'">删除</a>
          <a  id="infoBtn" class="easyui-linkbutton" data-options="iconCls:'icon-tip'">详情</a>
   </div>
  </div>




  <div id="editWin"  style="height:320px;width:560px;"   data-options="modal:true,footer:'#dlg-buttons'">
      <form id="editForm" data-options="fit:true" method="post">
          <input type="hidden"  id="id" />
          <input type="hidden"  id="flag" />
          <input type="hidden"  id="editUserId" />
          <input type="hidden"  id="editDeptId" />
          <input type="hidden"  id="editUserFlag" />
          <table class="editTable">

              <div  class="fitem">
                  <label style="width: 60px;" >加班人员：</label>
                  <input class="easyui-textbox" id="editUserName" data-options="multiline:true,editable:false"  style="height:27px;width: 115px"/>
                  <a id="chooseUser" class="easyui-linkbutton"  onclick="chooseUser()" >选择</a>

                  <label style="width: 60px;" >所在部门：</label>
                  <input  id="editDeptName" class="easyui-textbox" data-options="multiline:true,editable:false" style="width: 150px;height:27px"  />
              </div>
              <div class="fitem">
                  <label style="width: 60px;">开始时间：</label>
                  <input id="editStartDate" class="easyui-datetimebox" data-options="showSeconds:false,multiline:true,editable:false" style="width:150px;height:27px" />

                  <label style="width: 60px;">结束时间：</label>
                  <input id="editEndDate"  class="easyui-datetimebox" data-options="showSeconds:false,multiline:true,editable:false" style="width:150px;height:27px">
              </div>

           <div class="fitem">
              <label style="width: 60px;"  >加班原因：</label>
               <input class="easyui-textbox" id="editOverTimeReason" data-options="showSeconds:false,multiline:true" style="width:365px;height:90px"  />

             </div>

              </table>
      </form>
  </div>
  <div id="dlg-buttons" style="padding:5px;text-align:right;" >
      <a  id="saveBtn" class="easyui-linkbutton" icon="icon-ok">提交</a>
      <a  id="cancelBtn" class="easyui-linkbutton" icon="icon-cancel">取消</a>
  </div>
  <div id="infoWin"  style="height:320px;width:560px;"   data-options="modal:true">

      <table class="editTable">

          <div  class="fitem">
              <label style="width: 60px;" >加班人员：</label>
              <input class="easyui-textbox" id="UserName" data-options="multiline:true,editable:false"  style="height:27px;width: 150px"/>

              <label style="width: 60px;" >所在部门：</label>
              <input  id="DeptName" class="easyui-textbox" data-options="multiline:true,editable:false" style="width: 150px;height:27px"  />
          </div>
          <div class="fitem">
              <label style="width: 60px;">开始时间：</label>
              <input id="StartDate" class="easyui-textbox" data-options="multiline:true,editable:false" style="width:150px;height:27px" />

              <label style="width: 60px;">结束时间：</label>
              <input id="EndDate"  class="easyui-textbox" data-options="multiline:true,editable:false" style="width:150px;height:27px">
          </div>

          <div class="fitem">
              <label style="width: 60px;"  >加班原因：</label>
              <input class="easyui-textbox" id="OverTimeReason" data-options="multiline:true,editable:false" style="width:365px;height:90px"  />

          </div>

      </table>
      </form>
  </div>
  </body>

</html>
