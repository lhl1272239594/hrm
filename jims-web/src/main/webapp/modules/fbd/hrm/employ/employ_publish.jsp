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
    <title>招聘发布</title>
    <%@ include file="/static/include/init.html" %>
    <%--<link rel="stylesheet" type="text/css" href="/static/easyui/css/bootstrap/easyui.css">--%>
</head>
<body>
<style type="text/css" rel="stylesheet">
    .fitem  {
        padding: 5px 10px 10px 10px;
        line-height:22px;
    }
    .fitem1  {
        padding: 30px 20px 20px 45px;
        line-height:22px;
    }
</style>
<table id="dataGrid" class="easyui-datagrid">

</table>
<div id="tb" style="padding:5px;display: none">
    招聘主题：<input id="NAME1" type="text" class="easyui-textbox" style="width:140px">
    招聘科室：<select id="EMPLOY_DEPT_ID1" class="easyui-combobox" panelHeight="180px" data-options="editable:false" style="width:140px"></select>
    招聘岗位：<select id="EMPLOY_POST1" class="easyui-combobox" panelHeight="auto" data-options="editable:false" style="width:140px"></select>
    状 态：<select id="STATE" class="easyui-combobox" panelHeight="auto" data-options="editable:false" style="width:140px">
    <option value="">请选择</option>
    <option value="0">起草</option>
    <option value="1">已发布</option>
    <option value="2">结束</option>
    <option value="3">已到期</option>
</select>
    <a id="searchBtn" class="easyui-linkbutton" iconCls="icon-search">查询</a>
    <a id="clearBtn" class="easyui-linkbutton" iconCls="icon-cancel">清空</a>
    <br/>
    <a  id="addBtn" class="easyui-linkbutton" iconCls="icon-add">新增</a>
    <a  id="editBtn" class="easyui-linkbutton" iconCls="icon-edit">修改</a>
    <a  id="delBtn" class="easyui-linkbutton" iconCls="icon-remove">删除</a>
    <a  id="infoBtn" class="easyui-linkbutton" data-options="iconCls:'icon-save'">详情</a>
    <a  id="dealBtn" class="easyui-linkbutton" data-options="iconCls:'icon-ok'">发布</a>
    <a  id="endBtn" class="easyui-linkbutton" data-options="iconCls:'icon-no'">结束</a>
    <a  id="redealBtn" class="easyui-linkbutton" iconCls="icon-reload">重新发布</a>
    <%--<a  id="noBtn" class="easyui-linkbutton" data-options="iconCls:'icon-no'">停用</a>
    <a  id="delBtn" class="easyui-linkbutton" iconCls="icon-remove">删 除</a>--%>
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
            <select id="EMPLOY_DEPT_ID" class="easyui-combobox" panelHeight="180px" data-options="editable:false" style="width:140px;height:27px"></select>
            <label style="width: 80px">招聘岗位：</label>
            <select id="EMPLOY_POST" class="easyui-combobox" panelHeight="auto" data-options="editable:false" style="width:140px;height:27px"></select>
        </div>
        <div class="fitem" >
            <label style="width: 80px">招聘性质：</label>
            <select id="EMPLOY_PROPERTY" class="easyui-combobox" panelHeight="auto" data-options="editable:false" style="width:140px;height:27px"></select>
            <label style="width: 80px">学历要求：</label>
            <select id="EDUCATION_REQUIRE" class="easyui-combobox" panelHeight="auto" data-options="editable:false" style="width:140px;height:27px"></select>
        </div>
        <div class="fitem" >
            <label style="width: 80px">工作经验：</label>
            <select id="WORK_EXPERIENCE" class="easyui-combobox" panelHeight="auto" data-options="editable:false" style="width:140px;height:27px"></select>
            <label style="width: 80px">薪资条件：</label>
            <select id="SALARY" class="easyui-combobox" panelHeight="auto" data-options="editable:false" style="width:140px;height:27px"></select>
        </div>
        <div class="fitem" >
            <label style="width: 80px">工作省份：</label>
            <select id="province" onchange="doProvAndCityRelation();"  data-options="editable:false" style="width:140px;height:27px;">
                <option id="choosePro" value=""></option>
            </select>
            <label style="width: 80px">工作城市：</label>
            <select id="citys" onchange="doCityAndCountyRelation();" panelHeight="180px" data-options="editable:false" style="width:140px;height:27px">
                <option id='chooseCity' value=''></option>
            </select>
        </div>
        <div class="fitem" >
            <label style="width: 80px">工作县区：</label>
            <select id="county"  panelHeight="180px" data-options="editable:false" style="width:140px;height:27px">
                <option id='chooseCounty' value=''></option>
            </select>
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
            <input id="WORK_REQUIRE" name="WORK_REQUIRE" class="easyui-textbox" data-options="multiline:true"  style="width:372px;height:96px">
        </div>
        <div class="fitem" id="TIME">
            <label style="width: 80px">有效期至：</label>
            <input id="TIME_UNTIL" class="easyui-datebox" data-options="showSeconds:false" style="width:140px;height:27px" editable="false" />
        </div>
    </form>

</div>
<div id="dlg-buttons" style="padding:5px;text-align:right;">
    <a href="javascript:void(0)" id="submitBtn" class="easyui-linkbutton c6" iconCls="icon-ok">保存</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel"
       onclick="$('#newDialog').dialog('close');$('#newForm').form('reset')" >关闭</a>
</div>
<!-- 新增招聘编辑框end -->
    <%--招聘详情--%>
<div id="newDialog2" style="height:500px;width:520px;display: none;overflow: auto" data-options="modal:true,footer:'#dlg-buttons2'">
    <form id="newForm2" method="post" data-options="fit:true">
        <input id="EMPLOY_ID2" type="hidden">
        <br/>
        <div class="fitem" >
            <label style="width: 80px">招聘主题：</label>
            <input id="NAME2" name="NAME" class="easyui-textbox" style="width:372px;height:27px">
        </div>

        <div class="fitem" >
            <label style="width: 80px">招聘科室：</label>
            <input id="EMPLOY_DEPT_ID2" class="easyui-textbox" panelHeight="180px" data-options="editable:false" style="width:140px;height:27px">
            <label style="width: 80px">招聘岗位：</label>
            <input id="EMPLOY_POST2" class="easyui-textbox" data-options="editable:false" style="width:140px;height:27px">
        </div>
        <div class="fitem" >
            <label style="width: 80px">招聘性质：</label>
            <input id="EMPLOY_PROPERTY2" class="easyui-textbox" data-options="editable:false" style="width:140px;height:27px">
            <label style="width: 80px">学历要求：</label>
            <input id="EDUCATION_REQUIRE2" class="easyui-textbox"data-options="editable:false" style="width:140px;height:27px">
        </div>
        <div class="fitem" >
            <label style="width: 80px">工作经验：</label>
            <input id="WORK_EXPERIENCE2" class="easyui-textbox" data-options="editable:false" style="width:140px;height:27px">
            <label style="width: 80px">薪资条件：</label>
            <input id="SALARY2" class="easyui-textbox"  data-options="editable:false" style="width:140px;height:27px">
        </div>
        <div class="fitem" >
            <label style="width: 80px">工作省份：</label>
            <input id="province2" class="easyui-textbox"  data-options="editable:false" style="width:140px;height:27px">

            <label style="width: 80px">工作城市：</label>
            <input id="citys2" class="easyui-textbox"  data-options="editable:false" style="width:140px;height:27px">

        </div>
        <div class="fitem" >
            <label style="width: 80px">工作县区：</label>
            <input id="county2"  class="easyui-textbox"  data-options="editable:false" style="width:140px;height:27px">

            <label style="width: 80px">详细地址：</label>
            <input id="WORK_PLACE2" name="WORK_PLACE" class="easyui-textbox" style="width:140px;height:27px">
        </div>
        <div class="fitem" >
            <label style="width: 80px">联系电话：</label>
            <input id="TEL2" name="TEL" class="easyui-textbox" style="width:140px;height:27px">
            <label style="width: 80px">联系邮箱：</label>
            <input id="EMAIL2" name="EMAIL" class="easyui-textbox" style="width:140px;height:27px">
        </div>
        <div class="fitem" >
            <label style="width: 80px">任职要求：</label>
            <input id="WORK_REQUIRE2" name="WORK_REQUIRE" class="easyui-textbox" data-options="multiline:true"  style="width:372px;height:110px">
        </div>
        <div class="fitem" id="TIME2">
            <label style="width: 80px">有效期至：</label>
            <input id="TIME_UNTIL2" class="easyui-textbox" data-options="editable:false" style="width:140px;height:27px" editable="false" />
        </div>
    </form>

</div>
<div id="dlg-buttons2" style="padding:5px;text-align:right;">

    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel"
       onclick="javascript:$('#newDialog2').dialog('close')">关闭</a>
</div>
<!-- 重新发布编辑框start -->
<div id="newDialog1" style="height:160px;width:320px;display: none" data-options="modal:true,footer:'#dlg-buttons1'">
    <form id="newForm1" method="post" data-options="fit:true">

        <div class="fitem1" >
            <label style="width: 80px">有效期至：</label>
            <input id="TIME_UNTIL1" class="easyui-datebox" data-options="showSeconds:false" style="width:140px;height:27px" editable="false" />
        </div>
    </form>

</div>
<div id="dlg-buttons1" style="padding:5px;text-align:right;">
    <a href="javascript:void(0)" id="submitBtn1" class="easyui-linkbutton c6" iconCls="icon-ok">保存</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel"
       onclick="$('#newDialog1').dialog('close')">关闭</a>
</div>
<!-- 重新发布编辑框end -->

<%--<script type="text/javascript" src="/static/jquery/jquery-1.11.3.min.js"></script>--%>
<script type="text/javascript" src="/modules/fbd/hrm/employ/js/employ_publish.js"></script>
<%--<script type="text/javascript" src="/static/js/head.js"></script>--%>



</body>
</html>
