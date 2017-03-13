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
    <title>工资级别设置</title>
    <link rel="stylesheet" type="text/css" href="/static/easyui/css/bootstrap/easyui.css">
</head>
<body>
<style type="text/css" rel="stylesheet">
    .fitem  {
        padding: 20px 20px 10px 20px;
        line-height:27px;
    }
</style>
<table id="dataGrid" class="easyui-datagrid">

</table>
<div id="tb" style="padding:5px;display: none">
    类别名称：<input id="typeName" type="text" class="easyui-textbox" style="width:140px">

    <a id="searchBtn" class="easyui-linkbutton" iconCls="icon-search">查询</a>
    <a id="clearBtn" class="easyui-linkbutton" iconCls="icon-cancel">清空</a>
    <br/>
    <a  id="addBtn" class="easyui-linkbutton" iconCls="icon-add">新增</a>
    <a  id="editBtn" class="easyui-linkbutton" iconCls="icon-edit">修改</a>
    <a  id="delBtn" class="easyui-linkbutton" iconCls="icon-remove">删除</a>
    <%--<a  id="matchBtn" class="easyui-linkbutton" iconCls="icon-save">匹配人员</a>--%>
    <a  id="okBtn" class="easyui-linkbutton" data-options="iconCls:'icon-ok'">启用</a>
    <a  id="noBtn" class="easyui-linkbutton" data-options="iconCls:'icon-no'">停用</a>
</div>
<!-- 新增编辑框start -->
<div id="newDialog" style="height:160px;width:370px;display: none" data-options="modal:true,footer:'#dlg-buttons'">
    <form id="newForm" method="post" data-options="fit:true">
        <input type="hidden" id="TYPE_ID" />

        <div class="fitem">
            <label style="width: 80px">工资级别：</label>
            <input id="TYPE_NAME" class="easyui-textbox" style="width:200px;height:27px">
        </div>
    </form>

</div>
<div id="dlg-buttons" style="padding:5px;text-align:right;">
    <a href="javascript:void(0)" id="submitBtn" class="easyui-linkbutton c6" iconCls="icon-ok">保存</a>
    <a id="close" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
</div>
<!-- 新增编辑框end -->




<script type="text/javascript" src="/static/jquery/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="/modules/fbd/hrm/salary/js/salary_human_type.js"></script>
<script type="text/javascript" src="/static/js/head.js"></script>


</body>
</html>
