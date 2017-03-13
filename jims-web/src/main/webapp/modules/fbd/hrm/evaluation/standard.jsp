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
    <title>考评标准</title>
    <link rel="stylesheet" type="text/css" href="/static/easyui/css/bootstrap/easyui.css">
    <link rel="stylesheet" type="text/css" href="/modules/fbd/hrm/css/public.css">

</head>
<style>
    .datagrid-btable .datagrid-cell{padding:6px 4px;overflow: hidden;text-overflow:ellipsis;white-space: nowrap;}
</style>
<body class="easyui-layout">

<div data-options="region:'west'"  style="width:15%;height:100%;float: left;overflow:hidden;">
  <%--  <div id="tb1" style="display: block">
        &nbsp;&lt;%&ndash;科室选择：&ndash;%&gt;
        &lt;%&ndash;<a id="clearBtnDept" class="easyui-linkbutton" iconCls="icon-cancel">清空选择</a>&ndash;%&gt;
        <a  id="allNodeClose" class="easyui-linkbutton" data-options="iconCls:'icon-undo',plain:true">全部合并</a>
        <a  id="allNodeOpen" class="easyui-linkbutton" data-options="iconCls:'icon-redo',plain:true">全部展开</a>
    </div>--%>
    <table id="projectTree" class="easyui-treegrid" ></table>

</div>
<div  data-options="region:'center'"  style="width:85%;height:100%;float:left;">

    <table class="easyui-datagrid" title="考评标准" id="standardGrid">

    </table>

    <div id="tb" style="padding:2px 5px;">
        <a href="#" id="addBtn" class="easyui-linkbutton" iconCls="icon-add"  >新增</a>
        <a href="#" id="editBtn" class="easyui-linkbutton" iconCls="icon-edit"  >修改</a>
        <a href="#" id="delBtn" class="easyui-linkbutton" iconCls="icon-remove" >删除</a>
        <a href="#" id="okBtn" class="easyui-linkbutton" iconCls="icon-ok" >启用</a>
        <a href="#" id="noBtn" class="easyui-linkbutton" iconCls="icon-no" >停用</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-search" onclick="queryAll()">查看全部标准</a>
        <a href="#" class="easyui-linkbutton" iconCls="icon-tip" onclick="info()">查看授权人员</a>
    </div>
</div>

<div id="add"  style="height: 480px;width:480px;display: none"   data-options="modal:true,footer:'#dlg-buttons'">

    <form id="projectForm" data-options="fit:true" method="post">
        <input type="hidden" id="id" />
        <div  class="fitem">
            <label style="width: 60px;" >考评项目：</label>
            <input class="easyui-textbox"  id="projectName" editable="false" style="height:27px;width: 143px"/>
            <input class="easyui-textbox"  id="projectSName" editable="false" style="height:27px;width: 143px"/>
        </div>
        <div  class="fitem">
            <label style="width: 60px;" >标准名称：</label>
            <input class="easyui-textbox"  id="name" data-options="multiline:true" style="height:120px;width: 290px"/>
        </div>
        <div  id="div-score" class="fitem">
            <label style="width: 60px;" >考评分值：</label>
            <input class="easyui-numberbox"  id="score" data-options="min:0,max:100,precision:1" style="height:27px;width: 120px"/>
        </div>
        <div class="fitem">
            <label style="width: 60px;">是否KPI：</label>
            <input type='radio' style="vertical-align:middle; margin-top:-2px; margin-bottom:1px;
" name='kpi' text='是' value='1'/> 是
            <input type='radio' style="vertical-align:middle; margin-top:-2px; margin-bottom:1px;
" name='kpi' text='否' value='0'/> 否
        </div>
        <div  class="fitem">
            <label style="width: 60px;" ><a href="#" class="easyui-linkbutton"  onclick="choose()" >授权人员</a></label>
            <input class="easyui-textbox" id="userName" data-options="multiline:true"  editable="false" style="height:90px;width: 290px"/>
        </div>
    </form>

</div>
<div style="padding:5px;text-align:right;" id="dlg-buttons">
    <a  id="saveBtn" class="easyui-linkbutton" icon="icon-ok">保存</a>
    <a  id="cancelBtn" class="easyui-linkbutton" icon="icon-cancel">取消</a>
</div>

<div id="choosePerson"  style="height: 500px;width:800px;display: none"   data-options="modal:true,footer:'#choose-buttons'">
    <div id="west" data-options="region:'west'"  style="width:35%;height:100%;float: left;overflow:auto;">

        <ul  id="userTree" class="easyui-tree"  ></ul >

    </div>
    <div id="center" data-options="region:'center'"  style="width:10%;height:100%;float:left;">
        <div class="btn" style="margin-top: 200px">
            <a  onclick="addPerson()" style="float: left" class="easyui-linkbutton" data-options="iconCls:'icon-redo'">添加</a>
        </div>
        <div class="btn" style="margin-top: 30px">
            <a  onclick="removePerson()"style="float: left"  class="easyui-linkbutton" data-options="iconCls:'icon-undo'">移除</a>
        </div>
    </div>
    <div id="east" data-options="region:'east'"  style="width:55%;height:100%;float:left;">
        <table class="easyui-datagrid" id="personGrid">
        </table>
    </div>

    <div style="padding:5px;text-align:right;" id="choose-buttons">
        <a  onclick="savePerson()" class="easyui-linkbutton" icon="icon-ok">确定</a>
        <a  onclick="cancelPerson()" class="easyui-linkbutton" icon="icon-cancel">取消</a>
    </div>
</div>
<script type="text/javascript" src="/static/jquery/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="/static/js/head.js"></script>
<script type="text/javascript" src="/modules/fbd/hrm/evaluation/js/standard.js"></script>
</body>
</html>