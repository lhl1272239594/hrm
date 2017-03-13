<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
  <head>
    <title>培训计划管理</title>
      <link rel="stylesheet" type="text/css" href="/static/easyui/css/bootstrap/easyui.css">



  </head>


  <body class="easyui-layout">
  <style type="text/css" rel="stylesheet">
      .fitem  {
          padding: 10px 40px 10px 40px;
          line-height:27px;
      }

  </style>

  <div id="tb"  style="padding:5px;background:#eee;">
      <div >
          培训主题：<input class="easyui-textbox" id="trainPlanTittle" style="width:140px" type="text"/>
          培训类型：
          <input id="type" class="easyui-combobox combox_width" panelHeight="auto" editable="false"  />



       <a id="searchAllBtn" class="easyui-linkbutton" iconCls="icon-search">查询</a>
       <a id="clearBtn" class="easyui-linkbutton" iconCls="icon-cancel">清空</a>
     </div>
      <div  >
      <a  id="addBtn" class="easyui-linkbutton" data-options="iconCls:'icon-add'">新增</a>
          <a  id="editBtn" class="easyui-linkbutton" iconCls="icon-edit">修改</a>
          <a  id="delBtn" class="easyui-linkbutton" data-options="iconCls:'icon-remove'">删除</a>
       <a  id="viewBtn" class="easyui-linkbutton" data-options="iconCls:'icon-tip'">查看</a>
   </div>
  </div>


  <table id="primaryGrid"  class="easyui-datagrid" ></table>


  <div id="editWin"  style="height:450px;width:780px;"   data-options="modal:true,footer:'#dlg-buttons'">
      <form id="editForm" data-options="fit:true" method="post">
          <input type="hidden"  id="id" />
          <input type="hidden"  id="flag" />
          <table class="editTable">
                  <div  class="fitem">
                      <label style="width: 60px;">培训类型：</label>
                      <input  id="editTrainPlanType" class="easyui-combobox" data-options="multiline:true,editable:false" style="height:27px;width: 150px"/>
                      <label style="width: 60px;" >培训主题：</label>
                      <input  id="editTrainPlanTittle" class="easyui-textbox"  style="height:27px;width: 150px"/>


                  <label style="width: 60px;" >培训讲师：</label>
                  <input class="easyui-textbox" id="editTrainTeacher"   style="height:27px;width: 150px"/>
              </div>

              <div class="fitem" >
                  <label style="width: 60px">培训内容：</label>
                  <textarea id="editTrainPlanContent" name="editTrainPlanContent" style="width:700px;height:260px;" ></textarea>
              </div>


              </table>
      </form>
  </div>
  <div id="dlg-buttons" style="padding:5px;text-align:right;" >
      <a  id="saveBtn" class="easyui-linkbutton" icon="icon-ok">保存</a>
      <a  id="cancelBtn" class="easyui-linkbutton" icon="icon-cancel">取消</a>
  </div>
  </body>

  <script type="text/javascript" src="/static/jquery/jquery-1.11.3.min.js"></script>
  <script type="text/javascript" src="/static/js/head.js"></script>
  <script type="text/javascript" src="/modules/fbd/hrm/train/js/train_plan.js"></script>
  <script type="text/javascript" charset="utf-8" src="/static/kindeditor/kindeditor-min.js"></script>
  <script type="text/javascript" charset="utf-8" src="/static/kindeditor/lang/zh_CN.js"></script>
</html>
