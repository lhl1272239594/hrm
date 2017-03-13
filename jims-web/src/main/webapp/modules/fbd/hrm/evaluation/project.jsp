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
    <title>考评项目管理</title>
    <link rel="stylesheet" type="text/css" href="/static/easyui/css/bootstrap/easyui.css">
    <link rel="stylesheet" type="text/css" href="/modules/fbd/hrm/css/public.css">

</head>
<body class="easyui-layout">


<div  data-options="region:'center'" >
    <table id="projectGrid" class="easyui-datagrid">

    </table>
</div>
<div id="tb" >
    <a id="addOneLevel" class="easyui-linkbutton" iconCls="icon-add">新增</a>
</div>
<div id="add"  style="height: 300px;width:370px;display: none"   data-options="modal:true,footer:'#dlg-buttons'">

    <form id="projectForm" data-options="fit:true" method="post">

        <div  class="fitem">
            <label style="width: 60px;" >项目名称：</label>
            <input class="easyui-textbox"  id="name"  style="height:27px;width: 200px"/>
        </div>
<%--        <div  id="div-score" class="fitem">
            <label style="width: 60px;" >考评分值：</label>
            <input class="easyui-numberbox"  id="score" data-options="required:true,min:0,max:1000,precision:1" style="height:27px;width: 200px"/>
        </div>--%>
        <div class="fitem">
            <label style="width: 60px;">考评方法：</label>
            <input id="method" class="easyui-combobox" panelHeight="auto"  style="width:200px;height:27px" editable="false"/>
        </div>
        <div  class="fitem">
            <label style="width: 60px;" >制定部门：</label>
            <input class="easyui-combotree"   id="dep"  editable="false" style="height:27px;width: 200px"/>
        </div>

    </form>

</div>
<div style="padding:5px;text-align:right;" id="dlg-buttons">
    <a  id="saveBtn" class="easyui-linkbutton" icon="icon-ok">保存</a>
    <a  id="cancelBtn" class="easyui-linkbutton" icon="icon-cancel">取消</a>
</div>
<script type="text/javascript" src="/static/jquery/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="/static/js/head.js"></script>
<script type="text/javascript" src="/static/easyui/js/datagrid-detailview.js"></script>
<script type="text/javascript" src="/modules/fbd/hrm/evaluation/js/project.js"></script>
</body>
</html>