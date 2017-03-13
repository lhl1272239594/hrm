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
    <title>人员技能设置</title>
    <link rel="stylesheet" type="text/css" href="/static/easyui/css/bootstrap/easyui.css">
</head>
<body>
<style type="text/css" rel="stylesheet">
    .fitem  {
        padding: 40px 20px 10px 20px;
        line-height:27px;
    }
    .info {
        color: #ff4f04;
    }
</style>
<table id="dataGrid" class="easyui-datagrid">

</table>
<div id="tb" style="padding:5px;display: none">
    <%--科 室：<select id="DEPT_ID1" class="easyui-combobox" panelHeight="180px" data-options="editable:false" style="width:140px"></select>
    <a id="searchBtn" class="easyui-linkbutton" iconCls="icon-search">查询</a>
    <a id="clearBtn" class="easyui-linkbutton" iconCls="icon-cancel">清空</a>
    <br/>--%>
    <a  id="addBtn" class="easyui-linkbutton" iconCls="icon-add">新增</a>
    <a  id="editBtn" class="easyui-linkbutton" iconCls="icon-edit">修改</a>
        <a  id="delBtn" class="easyui-linkbutton" iconCls="icon-remove">删除</a>
    <%--<span class="info" style="margin-left: 80px">说明：工资结算日用于定义结算上月该日期到本月该日期之间的数据时间范围。</span><span class="info" id="NAME"></span>
    --%><%--<a  id="delBtn" class="easyui-linkbutton" iconCls="icon-remove">删除</a>
    <a  id="okBtn" class="easyui-linkbutton" data-options="iconCls:'icon-ok'">启用</a>
    <a  id="noBtn" class="easyui-linkbutton" data-options="iconCls:'icon-no'">停用</a>--%>

</div>
<!-- 新增编辑框start -->
<div id="newDialog" style="height:220px;width:370px;display: none" data-options="modal:true,footer:'#dlg-buttons'">
    <form id="newForm" method="post" data-options="fit:true">
        <input type="hidden" id="SKILL_ID" />
        <%--<div class="fitem" >
            <label style="width: 80px">科&nbsp;&nbsp;室：</label>
            <select id="DEPT_ID" class="easyui-combobox" panelHeight="180px" data-options="editable:false" style="width:200px;height:27px"></select>
        </div>--%>
        <div class="fitem">
            <label style="width: 80px;" >技能名称：</label>
            <input class="easyui-textbox" id="SKILL" style="width:200px;height:27px" />
        </div>
    </form>

</div>
<div id="dlg-buttons" style="padding:5px;text-align:right;">
    <a href="javascript:void(0)" id="submitBtn" class="easyui-linkbutton c6" iconCls="icon-ok">保存</a>
    <a id="close" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
</div>
<!-- 新增编辑框end -->




<script type="text/javascript" src="/static/jquery/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="/modules/fbd/hrm/peinfo/js/humanSkill.js"></script>
<script type="text/javascript" src="/static/js/head.js"></script>


</body>
</html>
