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
    <link rel="stylesheet" type="text/css" href="/static/easyui/css/default/easyui.css">

</head>
<body class="easyui-layout">
<style type="text/css" rel="stylesheet">
    .fitem  {
        padding: 10px 40px 10px 40px;
        line-height:27px;
    }

</style>

    <div id="west" data-options="region:'west'"  style="width:20%;height:100%;float: left;overflow:auto;">
        <div id="p" class="easyui-panel" title="人员信息" style="width:100%;height:100%;padding:10px;">
            <ul  id="userTree" class="easyui-tree" ></ul >
         </div>
    </div>

    <div id="east" data-options="region:'east'"  style="width:80%;height:100%;float:left;">
        <div id="tb2" style="padding:5px;height:auto">
            <div style="margin-bottom:5px">
                <a href="#" class="easyui-linkbutton" iconCls="icon-add" plain="true">增加</a>
                <a href="#" class="easyui-linkbutton" iconCls="icon-edit" plain="true">修改</a>
                <a href="#" class="easyui-linkbutton" iconCls="icon-remove" plain="true">删掉</a>
            </div>

        </div>
        <table class="easyui-datagrid" title="职业信息" style="width:100%;height:100%;"  toolbar="#tb2"
               data-options="singleSelect:true,collapsible:true">
            <thead>
            <tr>
                <th data-options="field:'itemid',width:200">工作单位</th>
                <th data-options="field:'productid',width:100">职务</th>
                <th data-options="field:'listprice',width:150,align:'right'">入职时间</th>
                <th data-options="field:'unitcost',width:150,align:'right'">离职时间</th>
            </tr>
            </thead>
        </table>
    </div>

    <div style="padding:5px;text-align:right;" id="choose-buttons">
        <a  onclick="savePerson()" class="easyui-linkbutton" icon="icon-ok">确定</a>
        <a  onclick="cancelPerson()" class="easyui-linkbutton" icon="icon-cancel">取消</a>
    </div>
<script type="text/javascript" src="/static/jquery/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="/static/js/head.js"></script>
<script type="text/javascript" src="/modules/fbd/hrm/peinfo/js/percareer.js"></script>

</body>
</html>