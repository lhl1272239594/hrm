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
    <title>我的考勤</title>
    <%@ include file="/static/include/easyui.jsp"%>
    <script type="text/javascript" src="/static/js/head.js"></script>
    <script type="text/javascript" src="/modules/fbd/hrm/peinfo/js/myattendance.js"></script>
</head>


<body class="easyui-layout">
<style type="text/css" rel="stylesheet">
    .fitem  {
        padding: 1px;
    }
</style>

<%--<div id="tb"  style="padding:5px;background:#eee;">
    <div  class="fitem">
        员工姓名:<input class="easyui-combobox" id="userName" style="width:70px" type="text"/>


        <a id="searchBtn" class="easyui-linkbutton" iconCls="icon-search">搜索</a>
        <a id="clearBtn" class="easyui-linkbutton" iconCls="icon-cancel">清空</a>
    </div>
    <div  class="fitem">

        <a  id="addBtn" class="easyui-linkbutton" data-options="iconCls:'icon-add'">导入</a>
    </div>
</div>--%>
<!-- 考勤数据-->

<table id="attDataGrid"  class="easyui-datagrid" ></table>

<%--<div id="editWin"  style="height:200px;width:550px;"   data-options="modal:true,footer:'#dlg-buttons'">
    <form id="editForm" method="post" enctype="multipart/form-data" name="editForm">

        <input type="hidden"  id="id" />
        <input type="hidden"  id="flag" />
        <input type="hidden"  id="url" />
        <input type="hidden"  id="editDeptId" />
        <table class="editTable">

            <div class="fitem">
                <label style="width: 60px;">考勤数据:</label>
                <input id="myFiles" type="file"  style="width:200px" name="myFiles">
            </div>
        </table>
    </form>
    <div id="dlg-buttons" style="padding:5px;text-align:right;" >
        <a  id="saveBtn" class="easyui-linkbutton" icon="icon-ok" onclick="upFile()">保存</a>
        <a  id="cancelBtn" class="easyui-linkbutton" icon="icon-cancel">取消</a>
    </div>
</div>--%>

</body>

</html>
