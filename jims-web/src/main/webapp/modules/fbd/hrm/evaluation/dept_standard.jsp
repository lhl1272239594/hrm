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
    <title>科室标准设置</title>
    <link rel="stylesheet" type="text/css" href="/static/easyui/css/bootstrap/easyui.css">
    <link rel="stylesheet" type="text/css" href="/modules/fbd/hrm/css/public.css">

</head>
<body class="easyui-layout">
<!--科室信息  start-->
<div data-options="region:'west'" style="padding:1px;width: 20%;">
    <div style="margin: 5px">
        <a  id="import" class="easyui-linkbutton" data-options="iconCls:'icon-add'">导入模板</a>
    </div>
    <table id="deptTree" class="easyui-tree"  data-options="cascadeCheck :false" style="width: 90%">
    </table>
</div>


<div data-options="region:'center'" style="padding:1px;background:#eee;width: 80%;">
    <table id="standard" class="easyui-datagrid">

    </table>
    <div id="mould-tb" >
        <a  id="addStandardBtn" class="easyui-linkbutton" data-options="iconCls:'icon-add'">新增</a>
        <a  id="editStandardBtn" class="easyui-linkbutton" data-options="iconCls:'icon-edit'">修改</a>
        <a  id="delStandardBtn" class="easyui-linkbutton" data-options="iconCls:'icon-remove'">删除</a>
        <span class="score">总分：</span><span class="score" id="totalScore"></span>
    </div>
</div>

<!-- 新增编辑框start -->
<div id="importWin" style="height:240px;width:320px;display: none" data-options="modal:true,footer:'#dlg-buttons'">
    <form id="newForm" method="post" data-options="fit:true">
        <input type="hidden" id="id" />
        <div class="fitem" style="margin-top: 10px;" >
            <label style="width: 80px">模板分类：</label>
            <input id="type" class="easyui-combobox" editable="false" panelHeight="auto" style="width:140px;height:27px" >
        </div>
        <div class="fitem" style="margin-top: 10px;" >
            <label style="width: 80px">模板名称：</label>
            <input id="name" class="easyui-combobox" editable="false" panelHeight="auto" style="width:140px;height:27px" >
        </div>
    </form>
</div>
<div id="dlg-buttons" style="padding:5px;text-align:right;">
    <a href="javascript:void(0)" id="submitBtn" class="easyui-linkbutton" iconCls="icon-ok">确定</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel"
       onclick="javascript:$('#importWin').window('close')">关闭</a>
</div>
<!--添加标准  start-->
<div id="standardWin" style="display: none" data-options="footer:'#standard-buttons'">
    <div data-options="region:'west'"  style="width:15%;height:100%;float: left;overflow:auto;">

        <table id="projectTree" class="easyui-treegrid" ></table>

    </div>
    <div  data-options="region:'center'"  style="width:85%;height:100%;float:left;">
        <table class="easyui-datagrid" title="考评标准" id="standardGrid">
        </table>
        <div id="tb1" style="padding:2px 5px;">
            <a href="#" id="saveStandard" class="easyui-linkbutton" iconCls="icon-add"  >添加</a>
        </div>
    </div>
    <div style="padding:5px;text-align:right;" id="standard-buttons">
        <a  id="cancelStandard" class="easyui-linkbutton" icon="icon-cancel">返回</a>
    </div>
</div>
<div id="edit"  style="height: 480px;width:480px;display: none"   data-options="modal:true,footer:'#edit-buttons'">

    <form id="standardForm" data-options="fit:true" method="post">
        <div  class="fitem">
            <label style="width: 60px;" >标准名称：</label>
            <input class="easyui-textbox"  id="sname" data-options="multiline:true" style="height:120px;width: 290px"/>
        </div>
        <div  id="div-score" class="fitem">
            <label style="width: 60px;" >考评分值：</label>
            <input class="easyui-numberbox"  id="score" data-options="min:0,max:100,precision:1" style="height:27px;width: 120px"/>
        </div>
        <div class="fitem">
            <label style="width: 60px;">是否KPI：</label>
            <input type='radio' style="vertical-align:middle; margin-top:-2px; margin-bottom:1px;
" name='kpi' text='是' value='0'/> 是
            <input type='radio' style="vertical-align:middle; margin-top:-2px; margin-bottom:1px;
" name='kpi' text='否' value='1'/> 否
        </div>
        <div  class="fitem">
            <label style="width: 60px;" >授权人员：</label>
            <input class="easyui-textbox" id="userName" data-options="multiline:true"  editable="false" style="height:90px;width: 290px"/>
        </div>
    </form>

</div>
<div style="padding:5px;text-align:right;" id="edit-buttons">
    <a  id="saveBtn" class="easyui-linkbutton" icon="icon-ok">保存</a>
    <a  id="cancelBtn" class="easyui-linkbutton" icon="icon-cancel">取消</a>
</div>
<script type="text/javascript" src="/static/jquery/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="/static/js/head.js"></script>
<script type="text/javascript" src="/modules/fbd/hrm/evaluation/js/dept_standard.js"></script>

</body>
</html>