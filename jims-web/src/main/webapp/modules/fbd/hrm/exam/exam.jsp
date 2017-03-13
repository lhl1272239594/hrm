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
    <title>试卷管理</title>
    <link rel="stylesheet" type="text/css" href="/static/easyui/css/bootstrap/easyui.css">
    <link rel="stylesheet" type="text/css" href="/modules/fbd/hrm/css/public.css">

</head>
<body class="easyui-layout">


<table id="examGrid" class="easyui-datagrid">

</table>
<div id="tb" >
    考试类型：
    <input id="type" class="easyui-combobox combox_width" panelHeight="auto" editable="false"  />
    状态：
    <input id="state" class="easyui-combobox combox_width"   editable="false" panelHeight="auto" />
    <a id="searchBtn" class="easyui-linkbutton" iconCls="icon-search">查询</a>
    <a id="clearBtn" class="easyui-linkbutton" iconCls="icon-cancel">清空</a>
    <br>
    <a  id="addBtn" class="easyui-linkbutton" data-options="iconCls:'icon-add'">新增</a>
    <a  id="editBtn" class="easyui-linkbutton" data-options="iconCls:'icon-edit'">修改</a>
    <a  id="delBtn" class="easyui-linkbutton" data-options="iconCls:'icon-remove'">删除</a>
    <a  id="queBtn" class="easyui-linkbutton" data-options="iconCls:'icon-save'">题目设置</a>
    <a  onclick="queryQue(1)" class="easyui-linkbutton" data-options="iconCls:'icon-tip'">查看已选试题</a>
    <a  id="okBtn" class="easyui-linkbutton" data-options="iconCls:'icon-ok'">发布</a>
    <a  id="okBtn1" class="easyui-linkbutton" data-options="iconCls:'icon-save'">导出试卷</a>
</div>
    <!--添加试卷  start-->
    <div id="addExam" class="easyui-window" style="height: 300px;width:500px;display: none"   data-options="modal:true,footer:'#dlg-buttons',closed: true">


        <form id="examForm" data-options="fit:true" method="post">
            <input type="hidden" id="examId" />
            <div  class="fitem">
                <label style="width: 60px;" >试卷名称：</label>
                <input class="easyui-textbox"  id="examName" data-options="multiline:true" style="height:27px;width: 300px"/>
            </div>
            <div  class="fitem">
                <label style="width: 60px;" >试题分类：</label>
                <input class="easyui-combotree"  id="itemTree" data-options="multiline:true" style="height:60px;width: 300px"/>
            </div>
            <div class="fitem">
                <label style="width: 60px;">试卷类型：</label>
                <input id="type1" class="easyui-combobox" panelHeight="auto"   style="width:100px;height:27px" editable="false"/>
                <label style="width: 88px;" >考试时长：</label>
                <input class="easyui-numberbox"  id="time" data-options="min:0,max:1000,precision:0" style="height:27px;width: 100px"/><span style="margin-left: 5px;">分钟</span>
            </div>

            <div  class="fitem">
                <label style="width: 60px;margin-left: 1px" >考试总分：</label>
                <input class="easyui-numberbox"  id="score" data-options="min:0,max:1000,precision:0" style="height:27px;width: 100px"/><span style="margin-left: 5px;">分</span>
                <label style="width: 72px;" >及格分数：</label>
                <input class="easyui-numberbox"  id="checkscore" data-options="min:0,max:1000,precision:0" style="height:27px;width: 100px"/><span style="margin-left: 5px;">分</span>
            </div>

        </form>

    </div>
    <div style="padding:5px;text-align:right;" id="dlg-buttons">
        <a  id="saveBtn" class="easyui-linkbutton" icon="icon-ok">保存</a>
        <a  id="cancelBtn" class="easyui-linkbutton" icon="icon-cancel">取消</a>
    </div>


    <!--题目设置  start-->
    <div id="addQueType" style="display: none">
        <table id="queTypeGrid" class="easyui-datagrid"></table>
        <div id="tb1" >

            <a  id="addQueBtn" class="easyui-linkbutton" data-options="iconCls:'icon-add'">新增题型</a>
<%--
            <a  id="editQueBtn" class="easyui-linkbutton" data-options="iconCls:'icon-edit'">修改题型</a>
--%>
            <a  id="removeQueBtn" class="easyui-linkbutton" data-options="iconCls:'icon-remove'">删除题型</a>
            <a  onclick="queryQue(2)" class="easyui-linkbutton" data-options="iconCls:'icon-tip'">查看已选试题</a>

            <span class="score">试卷总分：</span><span class="score" id="totalScore"></span><span class="score" style="margin-left: 10px">当前分数：</span><span class="score" id="tempScore"></span>
        </div>

    </div>
    <!--新增题型 start-->
    <div id="addQue" class="easyui-window" style="height: 500px;width:500px;display: none"   data-options="modal:true,footer:'#type-buttons',closed: true">


        <form id="queForm" data-options="fit:true" method="post">
            <input type="hidden" id="examQueId" />
            <div class="fitem">
                <label style="width: 60px;" >试题题型：</label>
                <input class="easyui-combobox" id="queType"  panelHeight="auto" style="width:100px;height:27px" editable="false"/>
                <label style="width: 88px;" >出题方式：</label>
                <input id="method" class="easyui-combobox"   panelHeight="auto" style="width:100px;height:27px" editable="false"/>

            </div>

            <div class="fitem">
                <label style="width: 60px;" >每题分数：</label>
                <input id="queScore" class="easyui-numberbox"  data-options="min:0,max:100,precision:0" style="width:100px;height:27px" />
                <span id="random">
                <label style="width: 88px;" >题目数量：</label>
                <input id="queNum" class="easyui-numberbox"  data-options="min:0,max:100,precision:0" style="width:100px;height:27px" />
            </span>
            </div>

        </form>

    </div>
    <div style="padding:5px;text-align:right;" id="type-buttons">
        <a  id="saveTypeBtn" class="easyui-linkbutton" icon="icon-ok">保存</a>
        <a  id="cancelTypeBtn" class="easyui-linkbutton" icon="icon-cancel">取消</a>
    </div>


    <!--查看已选试题  start-->
    <div id="queryQue" style="display: none">
        <table id="queGrid" class="easyui-datagrid"></table>

    </div>


<!--手工选题 start-->
<div id="selectQue" >
    <div  title="试题分类" style="width:19%;height:100%;float:left">
        <table id="itemTree1" class="easyui-treegrid" ></table>

    </div>
    <div  style="width:40%;height:100%;float:left;" data-options="region:'center'" >
        <table id="queGrid1" style="width:100%;" class="easyui-datagrid"  ></table>
        <div id="tb2" style="margin:0 5px;height:26px;">
            <a  style="float: right" id="saveQueBtn" class="easyui-linkbutton" data-options="iconCls:'icon-redo',iconAlign:'right'">保存试题</a>
        </div>
    </div>
    <div title="已选试题" style="width:40%;height:100%;float:left" data-options="region:'east'">
        <table id="queGrid2" class="easyui-datagrid" ></table>
        <div id="tb3" style="margin:0 5px;">
            <a  id="delQueBtn" class="easyui-linkbutton" data-options="iconCls:'icon-undo'">删除试题</a>
        </div>
    </div>

</div>
<!--导出试卷  start-->
<div id="export" style="height:200px;width:400px;display: none" data-options="modal:true,footer:'#export-buttons'">
    <div class="fitem" style="margin-top: 10px;" >
        <label style="width: 60px;text-align: left">试卷名称：</label>
        <input id="name" class="easyui-textbox" style="width:200px;height:27px" >
    </div>
    <div class="fitem">
        <label style="width: 140px;text-align: left">导出试卷是否包含答案：</label>
        <input type="checkbox" id="answer" style="vertical-align:middle;" />
    </div>
</div>
<div id="export-buttons" style="padding:5px;text-align:right;">
    <form id="exportForm" action='' method='post'/>
        <a href="javascript:void(0)" id="exportBtn" class="easyui-linkbutton c6" iconCls="icon-ok">导出试卷</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel"
           onclick="javascript:$('#export').window('close')">关闭</a>
    </form>
</div>
<script type="text/javascript" src="/static/jquery/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="/static/js/head.js"></script>
<script type="text/javascript" src="/modules/fbd/hrm/exam/js/exam.js"></script>
</body>
</html>