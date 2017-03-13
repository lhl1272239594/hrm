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
    <title>考评模板管理</title>
    <link rel="stylesheet" type="text/css" href="/static/easyui/css/bootstrap/easyui.css">
    <link rel="stylesheet" type="text/css" href="/modules/fbd/hrm/css/public.css">

</head>
<body class="easyui-layout">
<style type="text/css" rel="stylesheet">
    .fitdiv  {
        padding: 10px 40px 10px 40px;
        line-height:27px;
        display: block;
        min-width: 600px;
    }
    .fitdiv1  {
        padding: 10px 40px 10px 40px;
        line-height:27px;
        display: block;
    }
    .score {
        line-height:30px;
        color: red;
    }
</style>
<table id="templetGrid" class="easyui-datagrid">

</table>

<div id="tb">
    模板名称：
    <input id="searchName" class="easyui-textbox combox_width"   />
    考评类型：
    <input id="searchType" class="easyui-combobox combox_width"    panelHeight="auto"  editable="false"/>
    启动类型：
    <select id="searchStartType" class="easyui-combobox combox_width" panelHeight="auto"  editable="false">

    </select>
    <a id="searchBtn" class="easyui-linkbutton" iconCls="icon-search">查询</a>
    <a id="clearBtn" class="easyui-linkbutton" iconCls="icon-cancel">清空</a>
    <br>
    <a  id="addBtn" class="easyui-linkbutton" data-options="iconCls:'icon-add'">新增</a>
    <a  id="editBtn" class="easyui-linkbutton" data-options="iconCls:'icon-edit'">修改</a>
    <a  id="delBtn" class="easyui-linkbutton" data-options="iconCls:'icon-remove'">删除</a>
    <a  id="okBtn" class="easyui-linkbutton" data-options="iconCls:'icon-ok'">启用</a>
    <a  id="noBtn" class="easyui-linkbutton" data-options="iconCls:'icon-no'">停用</a>
    <a  id="publishBtn" class="easyui-linkbutton" data-options="iconCls:'icon-ok'">发布</a>
</div>
<!--新增模板  start-->
<div id="addTemplet" style="display: none" data-options="footer:'#dlg-buttons'">
    <div data-options="region:'south',border:false" style="height: 100%;width: 100%">
        <div class="easyui-tabs" id="tab" style="width:100%;height:100%">
            <div title="基本信息" style="padding:10px"  style="height: 100%;width: 100%;overflow: auto">
                <form id="templetForm" style="padding:10px;width: 100%;" method="post">
                    <input type="hidden" id="id" />
                    <div class="fitdiv">
                        <label style="width: 100px;text-align: left">考评模板名称：</label>
                        <input id="name"  class="easyui-textbox" style="width:330px;height:27px" />
                    </div>
                    <div class="fitdiv">
                        <label style="width: 100px;text-align: left" >选择考评类型：</label>
                        <input class="easyui-combobox"  panelHeight="auto"  id="type" style="width:120px;height:27px" editable="false"/>
                        <label style="width: 80px;" >启用状态：</label>
                        <select class="easyui-combobox" editable="false"  id="state" panelHeight="auto" style="width:120px;height:27px">
                            <option value="1">启用</option>
                            <option value="0">停用</option>
                        </select>
                    </div>
                    <div class="fitdiv" >
                        <label style="width: 100px;text-align: left" >考评发布类型：</label>
                        <input   panelHeight="auto"  id="startType"  style="width:120px;height:27px" editable="false"/>
                        <label style="width: 80px;" >有效日期：</label>
                        <input class="easyui-numberbox"    id="expiryDate" data-options="min:0,max:365,precision:0" style="width:120px;height:27px" />
                        <span style="margin-left: 5px;">天</span>
                    </div>

                    <div id="auto" >
                        <div class="fitdiv">
                            <label style="width: 100px;text-align: left" >考评周期类别：</label>
                            <select class="easyui-combobox" editable="false"  id="periodType" panelHeight="auto" style="width:120px;height:27px">
                                <option value="0">月度考评</option>
                                <option value="1">季度考评</option>
                                <option value="2">年度考评</option>
                            </select>

                            <span name="month">
                            <label style="width: 80px;" >启动月份：</label>
                            <input class="easyui-numberbox" id="period" data-options="min:0,max:12,precision:0" style="width:120px;height:27px" />
                            </span>
                            <label style="width: 80px;" >启动日期：</label>
                            <input class="easyui-numberbox"    id="startDay" data-options="min:0,precision:0" style="width:120px;height:27px" />
                            <span style="margin-left: 3px;">（日）</span>
                        </div>
                    </div>
                    <div class="fitdiv" style="overflow: auto">
                        <label style="width: 100px;text-align: left" >考核对象类型：</label>
                        <select class="easyui-combobox" editable="false" id="objType" panelHeight="auto" style="width:120px;height:27px">
                            <option value="1">科室</option>
                            <option value="2">人员</option>
                        </select>
                        <label style="width: 80px;" >是否自评：</label>
                        <input type="checkbox" id="self" style="vertical-align:middle;" />
                    </div>
                    <div  class="fitdiv">
                        <label style="width: 100px;" ><a href="#" class="easyui-linkbutton" style="float: left;margin-right: 8px;"  onclick="choose(1)" >添加考评对象</a></label>
                        <input class="easyui-textbox" id="objName" data-options="multiline:true"  editable="false" style="height:90px;width: 360px;"/>
                    </div>
                    <div  class="fitdiv" id="gradeUser">
                        <label style="width: 100px;" ><a href="#" class="easyui-linkbutton" style="float: left;margin-right: 8px;" onclick="choose(2)" >添加评分人员</a></label>
                        <input class="easyui-textbox" id="userName" data-options="multiline:true"  editable="false" style="height:90px;width: 360px;"/>
                    </div>
                </form>
                </div>
            <div title="考评标准" style="padding:10px">
                <table id="projectGrid" class="easyui-datagrid">
                </table>
                <div id="project_tb">
                    <a  id="addProjectBtn" class="easyui-linkbutton" data-options="iconCls:'icon-add'">新增</a>
                    <a  id="delProjectBtn" class="easyui-linkbutton" data-options="iconCls:'icon-edit'">移除</a>
                    <span class="score">总分：</span><span class="score" id="totalScore"></span>
                </div>
            </div>
        </div>
    </div>
    <div style="padding:5px;text-align:right;" id="dlg-buttons">
        <a  id="saveBtn" class="easyui-linkbutton" icon="icon-ok">保存</a>
        <a  id="cancelBtn" class="easyui-linkbutton" icon="icon-cancel">返回</a>
    </div>
</div>
<!--新增模板  start-->
<div id="Project" style="display: none" data-options="footer:'#standard-buttons'">
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
        <a  id="cancelProjectBtn" class="easyui-linkbutton" icon="icon-cancel">返回</a>
    </div>
</div>
<div id="choosePerson"  style="height: 500px;width:800px;display: none"   data-options="modal:true,footer:'#choose-buttons'">
    <div id="west" data-options="region:'west'"  style="width:35%;height:100%;float: left;overflow:auto;">

        <ul  id="userTree" class="easyui-tree" ></ul>
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
        <table class="easyui-datagrid"   id="personGrid">
        </table>
    </div>

    <div style="padding:5px;text-align:right;" id="choose-buttons">
        <a  onclick="savePerson()" class="easyui-linkbutton" icon="icon-ok">确定</a>
        <a  onclick="cancelPerson()" class="easyui-linkbutton" icon="icon-cancel">取消</a>
    </div>
</div>
<div id="plan"  style="height: 300px;width:370px;display: none"   data-options="modal:true,footer:'#plan-buttons'">

    <form id="planForm" data-options="fit:true" method="post">

        <div  class="fitdiv1">
            <label style="width: 80px;" >考评标题：</label>
            <input class="easyui-textbox"  id="title"  style="height:27px;width: 200px"/>
        </div>
        <div class="fitdiv1">
            <label style="width: 80px;">开始日期：</label>
            <input id="start" class="easyui-datebox" panelHeight="auto" style="width:120px;height:27px" editable="false"/>
        </div>
        <div  class="fitdiv1">
            <label style="width: 80px;" >有效日期：</label>
            <input class="easyui-numberbox"    id="expiryDate1" data-options="min:0,max:300,precision:0" style="width:120px;height:27px" />
        </div>

    </form>

</div>
<div style="padding:5px;text-align:right;" id="plan-buttons">
    <a  id="savePlanBtn" class="easyui-linkbutton" icon="icon-ok">发布</a>
    <a  id="cancelPlanBtn" class="easyui-linkbutton" icon="icon-cancel">取消</a>
</div>
<script type="text/javascript" src="/static/jquery/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="/static/js/head.js"></script>
<script type="text/javascript" src="/modules/fbd/hrm/evaluation/js/userTree.js"></script>
<script type="text/javascript" src="/modules/fbd/hrm/evaluation/js/plan.js"></script>

</body>
</html>