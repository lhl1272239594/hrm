<%--
  Created by IntelliJ IDEA.
  User:
  Date: 2016/9/17
  Time: 23:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>职业管理</title>
    <link rel="stylesheet" type="text/css" href="/static/easyui/css/bootstrap/easyui.css">

</head>
<body class="easyui-layout">
<style type="text/css" rel="stylesheet">
    .fitem  {
        padding: 10px 10px 10px 10px;
        line-height:27px;
    }
</style>
<!--部门信息  start-->
<div id=examClass" data-options="region:'west'"  style="width:13%;height:100%;float: left;overflow:auto;">
    <table id="staff" class="easyui-datagrid" >

    </table>
</div>

<!--部门信息  end-->


<!--人员信息  start-->
<div id="examSubclass" data-options="region:'center'" style="width:40%;height:100%;float:left;">
    <table id="staffGrid" class="easyui-datagrid">

    </table>
</div>
<div id="ft" style="display: none">
    &nbsp;姓 名：<input id="PERSON_NAME" type="text" class="easyui-textbox" style="width:140px">
    <a id="searchBtn" class="easyui-linkbutton" iconCls="icon-search">查询</a>
    <a id="searchAllBtn" class="easyui-linkbutton" iconCls="icon-search">展示全部</a>
    <a id="clearBtn" class="easyui-linkbutton" iconCls="icon-cancel">清空</a>
   <%-- <a id="infoBtn" class="easyui-linkbutton" iconCls="icon-save">历史信息</a>--%>

</div>
<!--人员信息  end-->


<!--职业信息  start-->
<div id="career" data-options="region:'east'" style="width:47%;height:100%;float:left;">
    <table id="careerGrid" class="easyui-datagrid">

    </table>
</div>
<div id="ft1" style="display: none">
    <a  id="addBtn" class="easyui-linkbutton" iconCls="icon-add">新增</a>
    <a  id="editBtn" class="easyui-linkbutton" iconCls="icon-edit">修改</a>
    <a  id="delBtn" class="easyui-linkbutton" iconCls="icon-remove">删除</a>
</div>
<!--职业信息  end-->


<!-- 职业信息编辑框start -->
<div id="newDialog" style="height:400px;width:560px;display: none;overflow: auto" data-options="modal:true,footer:'#dlg-buttons'">
    <form id="newForm" method="post" data-options="fit:true">
        <input id="CAREER_ID" type="hidden">
        <br/>
        <div class="fitem" >
            <label style="width: 80px">姓&nbsp;&nbsp;名：</label>
            <input id="NAME" name="NAME" class="easyui-textbox" data-options="editable:false" style="width:140px;height:27px">
            <label style="width: 80px">科&nbsp;&nbsp;室：</label>
            <select id="DEPT_NAME" class="easyui-combobox" panelHeight="180px" data-options="editable:false" style="width:140px;height:27px"></select>
        </div>
        <div class="fitem" >
            <label style="width: 80px">入职时间：</label>
            <input id="IN_DATE" class="easyui-datebox" data-options="showSeconds:false" style="width:140px;height:27px" editable="false" />
            <label style="width: 80px">离职时间：</label>
            <input id="OUT_DATE" class="easyui-datebox" data-options="showSeconds:false" style="width:140px;height:27px" editable="false" />
        </div>
        <div class="fitem" >
            <label style="width: 80px">职称类别：</label>
            <input class="easyui-combobox" id="title" style="width:140px;height:27px"  editable="false"/>
            <label style="width: 80px">职称级别：</label>
            <input class="easyui-combobox" id="title_level" style="width:140px;height:27px"  editable="false"/>
        </div>
        <div class="fitem" >
            <label style="width: 80px">备&nbsp;&nbsp;注：</label>
            <input id="REMARK" name="REMARK" class="easyui-textbox" data-options="multiline:true"  style="width:372px;height:130px">
        </div>

    </form>

</div>
<div id="dlg-buttons" style="padding:5px;text-align:right;">
    <a href="javascript:void(0)" id="submitBtn" class="easyui-linkbutton c6" iconCls="icon-ok">保存</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel"
       onclick="$('#newDialog').dialog('close')">关闭</a>
</div>
<!-- 职业信息编辑框end -->
<script type="text/javascript" src="/static/jquery/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="/modules/fbd/hrm/peinfo/js/careerinfo.js"></script>
<script type="text/javascript" src="/static/js/head.js"></script>
</body>
</html>
