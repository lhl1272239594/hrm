<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html>
<head>
    <title>班次设置</title>
    <%@ include file="/static/include/easyui.jsp" %>
    <script type="text/javascript" src="/modules/fbd/hrm/attendance/js/frequency.js"></script>


</head>


<body class="easyui-layout">
<style type="text/css" rel="stylesheet">
    .fitem {
        padding: 10px 40px 10px 40px;
        line-height: 27px;
    }

</style>
<table id="primaryGrid" class="easyui-datagrid"></table>
<div id="tb" style="padding:5px;background:#eee;">

    <div>
        <a id="addBtn" class="easyui-linkbutton" data-options="iconCls:'icon-add'">新增</a>
        <a id="editBtn" class="easyui-linkbutton" data-options="iconCls:'icon-edit'">修改</a>
        <a id="delBtn" class="easyui-linkbutton" data-options="iconCls:'icon-remove'">删除</a>

    </div>
</div>


<div id="editWin" style="height:300px;width:380px;" data-options="modal:true,footer:'#dlg-buttons'">
    <form id="editForm" data-options="fit:true" method="post">
        <input type="hidden" id="id"/>
        <input type="hidden" id="flag"/>
        <input type="hidden" id="editUserId"/>
        <input type="hidden" id="editDeptId"/>
        <input type="hidden" id="editUserFlag"/>
        <table class="editTable">
            <div class="fitem">
                <label style="width: 100px;">班次名称：</label>
                <input class="easyui-textbox" id="editFreItem"
                       style="width:150px;height:27px"/>
            </div>
            <div class="fitem">
                <label style="width: 100px;">班次类型：</label>
                <input class="easyui-textbox" id="editFreType" data-options="multiline:true,editable:false"
                       style="width: 150px;height:27px"/>
            </div>
            <div id="checkTime">
            <div class="fitem">
                <label style="width: 100px;">上班打卡时间：</label>
                <input id="editStartTime" class="easyui-timespinner"  data-options="editable:false,showSeconds:true" style="width:150px;height:27px" />
            </div>
            <div class="fitem">
                <label style="width:100px;">下班打卡时间：</label>
                <input id="editEndTime" class="easyui-timespinner" data-options="editable:false,showSeconds:true"  style="width:150px;height:27px" >
            </div>
            </div>
        </table>
    </form>
</div>
<div id="dlg-buttons" style="padding:5px;text-align:right;">
    <a id="saveBtn" class="easyui-linkbutton" icon="icon-ok">保存</a>
    <a id="cancelBtn" class="easyui-linkbutton" icon="icon-cancel">取消</a>
</div>
</body>
<script type="text/javascript" src="/static/js/head.js"></script>

</html>
