<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html>
<head>
    <title>培训通知管理</title>
    <%@ include file="/static/include/easyui.jsp" %>
    <script type="text/javascript" src="/modules/fbd/hrm/peinfo/js/mytrain.js"></script>
    <script type="text/javascript" src="/modules/fbd/hrm/train/js/user_tree.js"></script>

</head>


<body class="easyui-layout">
<style type="text/css" rel="stylesheet">
    .fitem {
        padding: 10px 40px 10px 40px;
        line-height: 27px;
    }

</style>

<div id="tb" style="padding:5px;background:#eee;">
    <div>
        查询月份：
        <input id="month" class="easyui-datebox combox_width"    editable="false">
        <input type="hidden" id="month1" />
        培训名称：<input class="easyui-textbox" id="trainPlanTittle" style="width:140px" type="text"/>
        培训类型：
        <input id="type" class="easyui-combobox combox_width" panelHeight="auto" editable="false"  />

        <a id="searchAllBtn" class="easyui-linkbutton" iconCls="icon-search">查询</a>
        <a id="clearBtn" class="easyui-linkbutton" iconCls="icon-cancel">清空</a>
    </div>
    <div>
        <a id="viewBtn" class="easyui-linkbutton" data-options="iconCls:'icon-tip'">查看</a>
    </div>
</div>


<table id="primaryGrid" class="easyui-datagrid"></table>
<div id="infoWin" style="height:500px;width:810px;" data-options="modal:true">
    <table class="editTable">
        <div class="fitem">
            <label style="width: 60px;">培训名称：</label>
            <input id="name1" class="easyui-textbox" data-options="editable:false" style="height:27px;width: 266px"/>

            <label style="width: 80px;">培训地点：</label>
            <input id="TrainPlace" class="easyui-textbox" data-options="editable:false"
                   style="height:27px;width: 266px"/>
        </div>
        <div class="fitem">
            <label style="width: 60px;">培训类型：</label>
            <input id="TrainPlanType" class="easyui-textbox"    data-options="editable:false"
                   style="height:27px;width: 150px"/>

            <label style="width: 79px;">培训主题：</label>
            <input id="TrainPlan" class="easyui-textbox"     data-options="editable:false"
                   style="height:27px;width: 150px"/>


            <label style="width: 78px;">培训讲师：</label>
            <input class="easyui-textbox" id="TrainTeacher" data-options="editable:false" style="height:27px;width: 150px"/>
        </div>
        <div class="fitem">
            <label style="width: 60px">培训内容：</label>
            <textarea id="TrainContent" name="editTrainContent"data-options="editable:false"  style="width:680px;height:200px;"></textarea>
        </div>

        <div class="fitem">
            <label style="width: 60px;">开始时间：</label>
            <input id="StartDate" class="easyui-textbox"
                   data-options="multiline:true,editable:false" style="width:150px;height:27px"/>

            <label style="width: 80px;">结束时间：</label>
            <input id="EndDate" class="easyui-textbox"
                   data-options="multiline:true,editable:false" style="width:150px;height:27px">

        </div>
        <div class="fitem">
            <label style="width: 60px;text-align: left" >培训人员</label>
            <input class="easyui-textbox" id="UserName" data-options="multiline:true,editable:false"
                   style="height:80px;width: 620px"/>
        </div>

    </table>
</div>
</body>

</html>
