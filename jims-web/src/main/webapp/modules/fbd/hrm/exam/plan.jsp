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
    <title>考试计划</title>
    <link rel="stylesheet" type="text/css" href="/static/easyui/css/bootstrap/easyui.css">
    <link rel="stylesheet" type="text/css" href="/modules/fbd/hrm/css/public.css">

</head>
<body class="easyui-layout">

<div  data-options="region:'center'" style="padding:1px;background:#eee;">
    <table id="planGrid" class="easyui-datagrid">

    </table>
</div>
<div id="tb" >
    考试类型：
    <input id="type" class="easyui-combobox combox_width" panelHeight="auto" editable="false"  />
    状态：
    <input id="state" class="easyui-combobox combox_width" editable="false" panelHeight="auto" />

    <a id="searchBtn" class="easyui-linkbutton" iconCls="icon-search">查询</a>
    <a id="clearBtn" class="easyui-linkbutton" iconCls="icon-cancel">清空</a>
    <br>
    <a  id="addBtn" class="easyui-linkbutton" data-options="iconCls:'icon-add'">新增</a>
    <a  id="editBtn" class="easyui-linkbutton" data-options="iconCls:'icon-edit'">修改</a>
    <a  id="delBtn" class="easyui-linkbutton" data-options="iconCls:'icon-remove'">删除</a>
    <a  id="okBtn" class="easyui-linkbutton" data-options="iconCls:'icon-ok'">发布</a>
</div>
<!--添加计划 start-->
<div id="addPlan"  style="height:500px;width:580px;display: none;overflow: auto"   data-options="modal:true,footer:'#dlg-buttons'">


    <form id="planForm" data-options="fit:true" method="post">
        <input type="hidden" id="planId" />
        <div  class="fitem">
            <label style="width: 80px;" >计划名称：</label>
            <input class="easyui-textbox"  id="planName" data-options="multiline:true" style="height:27px;width: 360px"/>
            <span id="res-name"></span>
        </div>
        <div class="fitem">
            <label style="width: 80px;">选择试卷：</label>
            <input id="exam" class="easyui-combogrid"  style="width:360px;height:27px" editable="false"/>
        </div>

        <div  class="fitem">
            <label style="width: 80px;" >开始时间：</label>
            <input class="easyui-datetimebox"  data-options="showSeconds:false"  id="start"  editable="false" style="height:27px;width: 140px"/>
            <label style="width: 60px;margin-left: 5px" >结束时间：</label>
            <input class="easyui-datetimebox"  data-options="showSeconds:false"  id="end"  editable="false" style="height:27px;width: 140px"/>
        </div>
        <div  class="fitem">
            <label style="width: 80px;" >考试开始：</label>
            <input class="easyui-numberbox"  data-options="min:0,max:1000,precision:0"  id="limitStart"  style="height:27px;width: 140px"/><span style="margin-left: 5px;">分钟后禁止考生参加</span>
        </div>
        <div  class="fitem">
            <label style="width: 80px;" >考试开始：</label>
            <input class="easyui-numberbox"  data-options="min:0,max:1000,precision:0"  id="limitSubmit"  style="height:27px;width: 140px"/><span style="margin-left: 5px;">分钟内禁止考生交卷</span>
        </div>
        <div  class="fitem">
            <label style="width: 80px;" ><a href="#" class="easyui-linkbutton"  onclick="choose()" >选择考生</a></label>
            <input class="easyui-textbox" id="userName" data-options="multiline:true" editable="false" style="height:80px;width: 360px"/>
        </div>
        <div  class="fitem">
            <label style="width: 80px;" >考试说明：</label>
            <textarea id="info"  class="easyui-validatebox validatebox-text validatebox-textarea"
                      style="width:360px;height:120px;"></textarea>
        </div>
    </form>

</div>
<div style="padding:5px;text-align:right;" id="dlg-buttons">
    <a  id="saveBtn" class="easyui-linkbutton" icon="icon-ok">保存</a>
    <a  id="cancelBtn" class="easyui-linkbutton" icon="icon-cancel">取消</a>
</div>
<div id="choosePerson"  style="height: 500px;width:800px;display: none"   data-options="modal:true,footer:'#choose-buttons'">
    <div id="west" data-options="region:'west'"  style="width:35%;height:100%;float: left;overflow:auto;">

        <ul  id="userTree" class="easyui-tree" ></ul >

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
        <table class="easyui-datagrid" title="已选人员" id="personGrid">
        </table>
    </div>

    <div style="padding:5px;text-align:right;" id="choose-buttons">
        <a  onclick="savePerson()" class="easyui-linkbutton" icon="icon-ok">确定</a>
        <a  onclick="cancelPerson()" class="easyui-linkbutton" icon="icon-cancel">取消</a>
    </div>
</div>
<script type="text/javascript" src="/static/jquery/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="/static/js/head.js"></script>
<script type="text/javascript" src="/modules/fbd/hrm/js/userTree.js"></script>
<script type="text/javascript" src="/modules/fbd/hrm/exam/js/plan.js"></script>

</body>
</html>