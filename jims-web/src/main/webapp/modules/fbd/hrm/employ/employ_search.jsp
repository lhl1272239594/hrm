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
    <title>招聘查询</title>
    <%@ include file="/static/include/init.html" %>
    <%--<link rel="stylesheet" type="text/css" href="/static/easyui/css/bootstrap/easyui.css">--%>
</head>
<body>
<style type="text/css" rel="stylesheet">
    .fitem  {
        padding: 5px 10px 10px 10px;
        line-height:22px;
    }
</style>
<table id="dataGrid" class="easyui-datagrid">

</table>
<div id="tb" style="padding:5px;display: none">
    招聘主题：<input id="NAME1" type="text" class="easyui-textbox" style="width:140px">
    招聘科室：<select id="EMPLOY_DEPT_ID1" class="easyui-combobox" panelHeight="180px" data-options="editable:false" style="width:140px"></select>
    招聘岗位：<select id="EMPLOY_POST1" class="easyui-combobox" panelHeight="auto" data-options="editable:false" style="width:140px"></select>
    <%--状 态：<select id="STATE" class="easyui-combobox" panelHeight="auto" data-options="editable:false" style="width:140px">
    <option value="">请选择</option>
    <option value="0">起草</option>
    <option value="1">已发布</option>
    <option value="2">结束</option>
    <option value="3">已到期</option>--%>
</select>
    <a id="searchBtn" class="easyui-linkbutton" iconCls="icon-search">查询</a>
    <a id="clearBtn" class="easyui-linkbutton" iconCls="icon-cancel">清空</a>
    <a  id="infoBtn" class="easyui-linkbutton" data-options="iconCls:'icon-save'">详情</a>
</div>
<!-- 新增招聘编辑框start -->
<div id="newDialog" style="height:500px;width:520px;display: none;overflow: auto" data-options="modal:true,footer:'#dlg-buttons'">
    <form id="newForm" method="post" data-options="fit:true">
        <input id="EMPLOY_ID" type="hidden">
        <br/>
        <div class="fitem" >
            <label style="width: 80px">招聘主题：</label>
            <input id="NAME" name="NAME" class="easyui-textbox" style="width:372px;height:27px">
        </div>

        <div class="fitem" >
            <label style="width: 80px">招聘科室：</label>
            <input id="EMPLOY_DEPT_ID" class="easyui-textbox" panelHeight="180px" data-options="editable:false" style="width:140px;height:27px">
            <label style="width: 80px">招聘岗位：</label>
            <input id="EMPLOY_POST" class="easyui-textbox" data-options="editable:false" style="width:140px;height:27px">
        </div>
        <div class="fitem" >
            <label style="width: 80px">招聘性质：</label>
            <input id="EMPLOY_PROPERTY" class="easyui-textbox" data-options="editable:false" style="width:140px;height:27px">
            <label style="width: 80px">学历要求：</label>
            <input id="EDUCATION_REQUIRE" class="easyui-textbox"data-options="editable:false" style="width:140px;height:27px">
        </div>
        <div class="fitem" >
            <label style="width: 80px">工作经验：</label>
            <input id="WORK_EXPERIENCE" class="easyui-textbox" data-options="editable:false" style="width:140px;height:27px">
            <label style="width: 80px">薪资条件：</label>
            <input id="SALARY" class="easyui-textbox"  data-options="editable:false" style="width:140px;height:27px">
        </div>
        <div class="fitem" >
            <label style="width: 80px">工作省份：</label>
            <input id="province" class="easyui-textbox"  data-options="editable:false" style="width:140px;height:27px">

            <label style="width: 80px">工作城市：</label>
            <input id="citys" class="easyui-textbox"  data-options="editable:false" style="width:140px;height:27px">

        </div>
        <div class="fitem" >
            <label style="width: 80px">工作县区：</label>
            <input id="county"  class="easyui-textbox"  data-options="editable:false" style="width:140px;height:27px">

            <label style="width: 80px">详细地址：</label>
            <input id="WORK_PLACE" name="WORK_PLACE" class="easyui-textbox" style="width:140px;height:27px">
        </div>
        <div class="fitem" >
            <label style="width: 80px">联系电话：</label>
            <input id="TEL" name="TEL" class="easyui-textbox" style="width:140px;height:27px">
            <label style="width: 80px">联系邮箱：</label>
            <input id="EMAIL" name="EMAIL" class="easyui-textbox" style="width:140px;height:27px">
        </div>
        <div class="fitem" >
            <label style="width: 80px">任职要求：</label>
            <input id="WORK_REQUIRE" name="WORK_REQUIRE" class="easyui-textbox" data-options="multiline:true"  style="width:372px;height:110px">
        </div>
        <div class="fitem" id="TIME">
            <label style="width: 80px">有效期至：</label>
            <input id="TIME_UNTIL" class="easyui-textbox" data-options="editable:false" style="width:140px;height:27px" editable="false" />
        </div>
    </form>

</div>
<div id="dlg-buttons" style="padding:5px;text-align:right;">
    <a href="javascript:void(0)" id="submitBtn" class="easyui-linkbutton c6" iconCls="icon-ok">保存</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel"
       onclick="javascript:$('#newDialog').dialog('close')">关闭</a>
</div>
<!-- 新增招聘编辑框end -->


<%--<script type="text/javascript" src="/static/jquery/jquery-1.11.3.min.js"></script>--%>
<script type="text/javascript" src="/modules/fbd/hrm/employ/js/employ_search.js"></script>
<%--<script type="text/javascript" src="/static/js/head.js"></script>--%>



</body>
</html>
