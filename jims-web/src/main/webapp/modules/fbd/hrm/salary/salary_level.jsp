<%--
  Created by IntelliJ IDEA.
  User:
  Date: 2016/8/21
  Time: 10:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>工资级别管理</title>
    <link rel="stylesheet" type="text/css" href="/static/easyui/css/bootstrap/easyui.css">
</head>
<body>
<style type="text/css" rel="stylesheet">
    .fitem  {
        padding: 20px 10px 10px 10px;
        line-height:27px;
    }
    #danxt_ul ul {
        margin-left: 10px;
    }
    #danxt_ul ul li {
        margin: 10px 0;
    }
    #duoxt_ul ul {
        margin-left: 10px;
    }
    #duoxt_ul ul li {
        margin: 10px 0;
    }
</style>
<table id="dataGrid" class="easyui-datagrid">

</table>
<div id="tb" style="padding:5px">
    工资级别名称：<input id="levelName" type="text" class="easyui-textbox" style="width:140px">
    工资类别：
    <select id="typeId" class="easyui-combobox" panelHeight="auto" data-options="editable:false" style="width:140px">


    </select>
    <a id="searchBtn" class="easyui-linkbutton" iconCls="icon-search">搜索</a>
    <a id="clearBtn" class="easyui-linkbutton" iconCls="icon-cancel">清空</a>
    <br/>
    <a  id="addBtn" class="easyui-linkbutton" iconCls="icon-add">增加</a>
    <a  id="editBtn" class="easyui-linkbutton" iconCls="icon-edit">修改</a>
    <a  id="okBtn" class="easyui-linkbutton" data-options="iconCls:'icon-ok'">启用</a>
    <a  id="noBtn" class="easyui-linkbutton" data-options="iconCls:'icon-no'">停用</a>
    <a  id="delBtn" class="easyui-linkbutton" iconCls="icon-remove">删除</a>
</div>
<!-- 新增编辑框start -->
<div id="newDialog" style="height:200px;width:auto;" data-options="modal:true,footer:'#dlg-buttons'">
    <form id="newForm" method="post" data-options="fit:true">
        <input type="hidden" id="LEVEL_ID" />

        <div class="fitem" style="margin-left: -70px">
            <label>级别名称：</label>
            <input name="LEVEL_NAME" id="LEVEL_NAME" class="easyui-textbox" style="width:200px;height:27px" required="true">
        </div>

        <div class="fitem" style="margin-left: -70px">
            <label>工资类别：</label>
            <select id="TYPE_ID" name="TYPE_ID" class="easyui-combobox" style="width:200px;height:27px" data-options="editable:false" required="true"></select>
        </div>

       <%-- <div class="fitem" style="margin-left: -70px">
            <label>启用状态：</label>
            <select id="ENABLE_FLAG" name="ENABLE_FLAG" data-options="editable:false"   style="width:200px;height:27px" class="easyui-combobox"></select>
        </div>--%>
    </form>

</div>
<div id="dlg-buttons" style="padding:5px;text-align:right;">
    <a href="javascript:void(0)" id="submitBtn" class="easyui-linkbutton c6" iconCls="icon-ok">保存</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel"
       onclick="javascript:$('#newDialog').dialog('close')">关闭</a>
</div>
<!-- 新增编辑框end -->




<script type="text/javascript" src="/static/jquery/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="/modules/fbd/hrm/salary/js/salary_level.js"></script>
<script type="text/javascript" src="/static/js/head.js"></script>

</body>
</html>
