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
    <title>试题分类</title>

    <%@ include file="/static/include/init.html" %>
</head>
<body class="easyui-layout">


    <table id="itemGrid" class="easyui-datagrid">

    </table>
<div id="ft" >
    <a  id="addBtn" class="easyui-linkbutton" iconCls="icon-add">增 加</a>
    <a  id="editBtn" class="easyui-linkbutton" iconCls="icon-edit">修 改</a>
    <a  id="delBtn" class="easyui-linkbutton" iconCls="icon-remove">删 除</a>
    <a  id="okBtn" class="easyui-linkbutton" data-options="iconCls:'icon-ok'">启用</a>
    <a  id="noBtn" class="easyui-linkbutton" data-options="iconCls:'icon-no'">停用</a>
</div>
    <!-- 新增编辑框start -->
    <div id="newDialog" style="height:160px;width:390px;display: none" data-options="modal:true,footer:'#dlg-buttons'">
        <form id="newForm" method="post" data-options="fit:true">
            <input type="hidden" id="itemId" />
            <div class="fitem" style="margin-top: 10px;" >
                <label style="width: 80px">类型名称：</label>
                <input id="itemName" class="easyui-textbox" style="width:200px;height:27px" >
            </div>
        </form>

    </div>
    <div id="dlg-buttons" style="padding:5px;text-align:right;">
        <a href="javascript:void(0)" id="submitBtn" class="easyui-linkbutton c6" iconCls="icon-ok">保存</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel"
           onclick="javascript:$('#newDialog').dialog('close')">关闭</a>
    </div>

<script type="text/javascript" src="/modules/fbd/hrm/exam/js/question_item.js"></script>

</body>
</html>