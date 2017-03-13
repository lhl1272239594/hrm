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
    <title>法定节日设置</title>
    <%@ include file="/static/include/easyui.jsp" %>
    <script type="text/javascript" src="/modules/fbd/hrm/attendance/js/festival_day.js"></script>
</head>


<body class="easyui-layout">


<div id="festb" style="padding:5px;background:#eee;">
        年份：<input id="year" style="width: 140px;height:27px" panelHeight="auto"  data-options="editable:false" type="text"/>
        节日：<input id="festival" style="width: 140px;height:27px" panelHeight="auto"  data-options="editable:false" type="text"/>


        <a id="searchBtn" class="easyui-linkbutton" iconCls="icon-search">查询</a>
        <a id="clearBtn" class="easyui-linkbutton" iconCls="icon-cancel">清空</a>
        <br>

        <a id="addBtn" class="easyui-linkbutton" data-options="iconCls:'icon-add'">新增</a>

        <a id="editBtn" class="easyui-linkbutton" data-options="iconCls:'icon-edit'">修改</a>
        <a id="delBtn" class="easyui-linkbutton" data-options="iconCls:'icon-remove'">删除</a>

        <a id="viewBtn" class="easyui-linkbutton" data-options="iconCls:'icon-tip'">查看</a>
</div>
<!-- 节日数据-->

<table id="festivalGrid"></table>


<%--<div id="dlg"></div>--%>
<!-- 节日详情-->
<div id="editFestival">
    <div id="datetb" style="padding:5px;background:#eee;">
        <div style="margin-bottom: 4px;">
            <form id="Form">
            <label style="width: 80px;">节日年份：</label>

            <input class="easyui-combobox" id="yearDate" panelHeight="auto"  data-options="editable:false" style="width:140px;height:27px"
                   type="text"/>
            <label style="width: 80px;margin-left: 10px;">节日名称：</label>
            <input class="easyui-combobox" id="festivalDate" panelHeight="auto"  data-options="editable:false"
                   style="width:140px;height:27px" type="text"/>
            </form>
        </div>
            <input type="hidden" id="flag"/>
            <input type="hidden" id="fesId"/>
            <a id="addDateBtn" class="easyui-linkbutton" data-options="iconCls:'icon-add'">新增</a>
            <a id="saveDateBtn" class="easyui-linkbutton" data-options="iconCls:'icon-save'">保存</a>
            <a id="delDateBtn" class="easyui-linkbutton" data-options="iconCls:'icon-remove'">删除</a>

    </div>
    <!-- 节日日期数据-->
    <table id="festivalDateGrid" 　class="easyui-datagrid"></table>
</div>
<!-- 时间控件-->

<div id="cc" class="easyui-calendar"></div>

<!-- 节日新增、修改-->
<div id="addWin" data-options="modal:true,footer:'#dlg-buttons'">
    <form id="editForm">
        <div class="fitem" style="margin-top: 20px;">
            <label style="width: 80px;">开始日期：</label>
            <input class="easyui-datebox" id="startDate" data-options="editable:false" style="width:120px;height:27px"
                   type="text"/>
            <label style="width: 80px;">节日天数：</label>
            <input class="easyui-numberbox" id="num" data-options="min:0,max:100,precision:0"
                   style="width:120px;height:27px"  />

        </div>
    </form>

</div>
<div id="dlg-buttons" style="padding:5px;text-align:right;" >
    <a  id="saveBtn" class="easyui-linkbutton" icon="icon-ok">确定</a>
    <a  id="cancelBtn" class="easyui-linkbutton" icon="icon-cancel">取消</a>
</div>
</body>
<script type="text/javascript" src="/static/js/head.js"></script>

</html>
