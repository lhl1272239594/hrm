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
    <title>考评模板管理</title>
    <link rel="stylesheet" type="text/css" href="/static/easyui/css/bootstrap/easyui.css">
    <link rel="stylesheet" type="text/css" href="/modules/fbd/hrm/css/public.css">

</head>
<body class="easyui-layout">
<div title="模板类型" data-options="region:'west'" style="padding:1px;background:#eee;width: 15%;">
    <table id="type" class="easyui-datagrid">

    </table>
    <div id="type-tb" >
        <a  id="addTypeBtn" class="easyui-linkbutton" data-options="iconCls:'icon-add'">新增</a>
        <a  id="editTypeBtn" class="easyui-linkbutton" data-options="iconCls:'icon-save'">修改</a>
        <a  id="delTypeBtn" class="easyui-linkbutton" data-options="iconCls:'icon-remove'">删除</a>
    </div>
</div>

<div title="模板名称" data-options="region:'center'" style="padding:1px;background:#eee;width: 15%;">
    <table id="templet" class="easyui-datagrid">

    </table>
</div>
<div  id="ft" style="margin:5px;">
    <a  id="addBtn" class="easyui-linkbutton" data-options="iconCls:'icon-add'">新增</a>
    <a  id="editBtn" class="easyui-linkbutton" data-options="iconCls:'icon-save'">修改</a>
    <a  id="delBtn" class="easyui-linkbutton" data-options="iconCls:'icon-remove'">删除</a>
</div>

<div title="模板标准" data-options="region:'east'" style="padding:1px;background:#eee;width: 70%;">
    <table id="standard" class="easyui-datagrid">

    </table>
</div>
<div id="mould-tb"  style="margin:5px;">
    <a  id="addStandardBtn" class="easyui-linkbutton" data-options="iconCls:'icon-add'">新增</a>
    <a  id="delStandardBtn" class="easyui-linkbutton" data-options="iconCls:'icon-remove'">删除</a>
    <span class="score">模板总分：</span><span class="score" id="totalScore"></span>
</div>
<!-- 新增编辑框start -->
<div id="newType" style="height:160px;width:390px;display: none" data-options="modal:true,footer:'#type-buttons'">
    <form id="typeForm" method="post" data-options="fit:true">
        <input type="hidden" id="typeId" />
        <div class="fitem" style="margin-top: 10px;" >
            <label style="width: 80px">类型名称：</label>
            <input id="pname" class="easyui-textbox" style="width:200px;height:27px" >
        </div>
    </form>
</div>
<div id="type-buttons" style="padding:5px;text-align:right;">
    <a href="javascript:void(0)" id="submitTypeBtn" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel"
       onclick="javascript:$('#newType').dialog('close')">关闭</a>
</div>
<!-- 新增编辑框start -->
<div id="newDialog" style="height:160px;width:390px;display: none" data-options="modal:true,footer:'#dlg-buttons'">
    <form id="newForm" method="post" data-options="fit:true">
        <input type="hidden" id="id" />
        <div class="fitem" style="margin-top: 10px;" >
            <label style="width: 80px">模板名称：</label>
            <input id="name" class="easyui-textbox" style="width:200px;height:27px" >
        </div>
    </form>
</div>
<div id="dlg-buttons" style="padding:5px;text-align:right;">
    <a href="javascript:void(0)" id="submitBtn" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel"
       onclick="javascript:$('#newDialog').dialog('close')">关闭</a>
</div>
<!--新增模板  start-->
<div id="standardWin" style="display: none" data-options="footer:'#standard-buttons'">
    <div data-options="region:'west'"  style="width:15%;height:100%;float: left;overflow:auto;">

        <table id="projectTree" class="easyui-treegrid" ></table>

    </div>
    <div  data-options="region:'center'"  style="width:85%;height:100%;float:left;">
        <table class="easyui-datagrid" title="考评标准" id="standardGrid">
        </table>
        <div id="tb1" style="padding:2px 5px;">
            <a href="#" id="saveStandard" class="easyui-linkbutton" iconCls="icon-add"  >添加</a>
        </div>
    </div>
    <div style="padding:5px;text-align:right;" id="standard-buttons">
        <a  id="cancelStandard" class="easyui-linkbutton" icon="icon-cancel">返回</a>
    </div>
</div>
<script type="text/javascript" src="/static/jquery/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="/static/js/head.js"></script>
<script type="text/javascript" src="/modules/fbd/hrm/evaluation/js/mould.js"></script>

</body>
</html>