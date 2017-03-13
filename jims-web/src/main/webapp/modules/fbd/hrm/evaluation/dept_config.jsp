<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>

<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>考评部门设置</title>
    <link rel="stylesheet" type="text/css" href="/static/easyui/css/bootstrap/easyui.css">
    <link rel="stylesheet" type="text/css" href="/modules/fbd/hrm/css/public.css">

</head>
<body class="easyui-layout">
<div data-options="region:'west'" style="padding:1px;background:#eee;width: 200px;">
    <table id="parent" class="easyui-datagrid">

    </table>
</div>
<div id="classft" style="margin:5px;">
    <a  id="addBtn" class="easyui-linkbutton" data-options="iconCls:'icon-add'">新增</a>
    <a  id="editBtn" class="easyui-linkbutton" data-options="iconCls:'icon-save'">修改</a>
    <a  id="delBtn" class="easyui-linkbutton" data-options="iconCls:'icon-remove'">删除</a>
</div>


<div data-options="region:'center',fit:true" style="padding:1px;background:#eee;">
    <table id="child" class="easyui-datagrid">

    </table>
</div>
<div id="ft" style="margin:5px;">
    <a  id="addBtn1" class="easyui-linkbutton" data-options="iconCls:'icon-add'">选择科室</a>
    <a  id="queryBtn" class="easyui-linkbutton" data-options="iconCls:'icon-tip'">查看全部</a>
</div>
<!-- 新增编辑框start -->
<div id="newDialog" style="height:160px;width:390px;display: none" data-options="modal:true,footer:'#dlg-buttons'">
    <form id="newForm" method="post" data-options="fit:true">
        <input type="hidden" id="id" />
        <div class="fitem" style="margin-top: 10px;" >
            <label style="width: 80px">科系名称：</label>
            <input id="deptName" class="easyui-textbox" style="width:200px;height:27px" >
        </div>
    </form>
</div>
<div id="dlg-buttons" style="padding:5px;text-align:right;">
    <a href="javascript:void(0)" id="submitBtn" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel"
       onclick="javascript:$('#newDialog').dialog('close')">关闭</a>
</div>
<div id="chooseDept"  style="height: 500px;width:800px;display: none"   data-options="modal:true,footer:'#choose-buttons'">
    <div id="west" data-options="region:'west'"  style="width:35%;height:100%;float: left;overflow:auto;">

        <ul  id="deptTree" class="easyui-tree" data-options="cascadeCheck :false,onlyLeafCheck:true"></ul >

    </div>
    <div id="center" data-options="region:'center'"  style="width:10%;height:100%;float:left;">
        <div class="btn" style="margin-top: 200px">
            <a  onclick="addDept()" style="float: left" class="easyui-linkbutton" data-options="iconCls:'icon-redo'">添加</a>
        </div>
        <div class="btn" style="margin-top: 30px">
            <a  onclick="removeDept()"style="float: left"  class="easyui-linkbutton" data-options="iconCls:'icon-undo'">移除</a>
        </div>
    </div>
    <div id="east" data-options="region:'east'"  style="width:55%;height:100%;float:left;">
        <table class="easyui-datagrid" id="deptGrid">
        </table>
    </div>

    <div style="padding:5px;text-align:right;" id="choose-buttons">
        <a  onclick="cancelDept()" class="easyui-linkbutton" icon="icon-cancel">返回</a>
    </div>
</div>
<script type="text/javascript" src="/static/jquery/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="/static/js/head.js"></script>
<script type="text/javascript" src="/modules/fbd/hrm/evaluation/js/deptTree.js"></script>
<script type="text/javascript" src="/modules/fbd/hrm/evaluation/js/dept_config.js"></script>

</body>
</html>