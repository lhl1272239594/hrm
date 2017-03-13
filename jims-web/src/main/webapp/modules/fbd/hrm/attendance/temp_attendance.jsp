<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
    <title>临时考勤数据查询</title>
    <%@ include file="/static/include/easyui.jsp" %>
    <script type="text/javascript" src="/static/js/head.js"></script>
    <script type="text/javascript" src="/modules/fbd/hrm/attendance/js/temp_attendance.js"></script>
    <script type="text/javascript" src="/modules/fbd/hrm/attendance/js/user_tree.js"></script>

</head>

<style type="text/css" rel="stylesheet">
    .fitem {
        padding: 10px 40px 10px 40px;
        line-height: 27px;
    }

</style>

<body class="easyui-layout">


<div id="tb" style="padding:5px;background:#eee;">
    临时考勤名称：<input class="easyui-textbox" id="tempAttendanceName" style="width:140px" type="text"/>


    <a id="searchBtn" class="easyui-linkbutton" iconCls="icon-search">查询</a>
    <a id="clearBtn" class="easyui-linkbutton" iconCls="icon-cancel">清空</a>
    <br>
    <a id="addBtn" class="easyui-linkbutton" data-options="iconCls:'icon-add'">新增</a>
    <a id="editBtn" class="easyui-linkbutton" data-options="iconCls:'icon-edit'">修改</a>
    <a id="delBtn" class="easyui-linkbutton" data-options="iconCls:'icon-remove'">删除</a>
    <a id="viewBtn" class="easyui-linkbutton" data-options="iconCls:'icon-tip'">查看</a>

</div>


<table id="primaryGrid"></table>
<div id="choosePerson" style="height: 510px;width:850px;" data-options="modal:true,footer:'#choose-buttons'">
    <div id="west" data-options="region:'west'" style="width:35%;height:100%;float: left;overflow:auto;">

        <ul id="userTree" class="easyui-tree"></ul>

    </div>
    <div id="center" data-options="region:'center'" style="width:10%;height:100%;float:left;">
        <div class="btn" style="margin-top: 200px">
            <a onclick="addPerson()" style="float: left" class="easyui-linkbutton"
               data-options="iconCls:'icon-redo'">添加</a>
        </div>
        <div class="btn" style="margin-top: 30px">
            <a onclick="removePerson()" style="float: left" class="easyui-linkbutton"
               data-options="iconCls:'icon-undo'">移除</a>
        </div>
    </div>
    <div id="east" data-options="region:'east'" style="width:55%;height:100%;float:left;">

        <table title="已选人员" id="personGrid"></table>

    </div>

    <div style="padding:5px;text-align:right;" id="choose-buttons">
        <a onclick="savePerson()" class="easyui-linkbutton" icon="icon-ok">确定</a>
        <a onclick="cancelPerson()" class="easyui-linkbutton" icon="icon-cancel">取消</a>
    </div>
</div>
<div id="editWin" style="height:480px;width:760px;" data-options="modal:true,footer:'#dlg-buttons'">
    <form id="editForm" data-options="fit:true" method="post">
        <input type="hidden" id="id"/>
        <input type="hidden" id="flag"/>
        <div class="fitem">
            <div class="fitem">
            <label style="width: 120px;">临时考勤名称：</label>
            <input id="tempAttName" class="easyui-textbox" style="height:27px;width: 150px"/>


                <label style="width: 120px;">临时考勤地点：</label>
                <input id="tempAttPlace" class="easyui-textbox" style="height:27px;width: 150px"/>

            </div>
            <div class="fitem">
                <label style="width: 120px;">临时考勤日期：</label>
                <input id="editAttDate" class="easyui-datebox"
                       data-options="showSeconds:false,multiline:true,editable:false" style="width:150px;height:27px"/>

            </div>
            <div class="fitem">
                <label style="width: 120px;">临时考勤开始时间：</label>
                <input id="editStartDate" class="easyui-timespinner"
                       data-options="showSeconds:false,multiline:true" style="width:150px;height:27px"/>

                <label style="width: 120px;">开始浮动时间：</label>
                <input id="adjustStartTime" class="easyui-numberspinner" value="0"
                       data-options=" min: -60,max: 60,multiline:true" style="width:150px;height:27px"/>

            </div>
            <div class="fitem">
                <label style="width: 120px;">临时考勤结束时间：</label>

                <input id="editEndDate" class="easyui-timespinner"
                       data-options=" showSeconds:false,multiline:true" style="width:150px;height:27px">

                <label style="width: 120px;">结束浮动时间：</label>
                <input id="adjustEndTime" class="easyui-numberspinner"  value="0"
                       data-options=" min: -60,max: 60,multiline:true" style="width:150px;height:27px"/>
            </div>


            <div class="fitem">
                <label style="width: 120px;"> 参与人员：</label>
                <a href="#" class="easyui-linkbutton" onclick="chooseUser()">选择</a>
            </div>
            <div class="fitem">
                <input class="easyui-textbox" id="editUserName" data-options="multiline:true,editable:false"
                       style="height:80px;width: 550px"/>
                <input type="hidden" id="editUserId"/>
                <input type="hidden" id="editDeptId"/>
                <input type="hidden" id="editDeptName"/>
                <input type="hidden" id="editUserFlag"/>
            </div>
        </div>
    </form>
    <div id="dlg-buttons" style="padding:5px;text-align:right;">
        <a id="saveBtn" class="easyui-linkbutton" icon="icon-ok">保存</a>
        <a id="cancelBtn" class="easyui-linkbutton" icon="icon-cancel">取消</a>
    </div>
  </div>

       <div id="viewWin" style="height:480px;width:740px;" data-options="footer:'#view-buttons'">
        <table id="dataGrid" ></table>
        <div id="view-buttons" style="padding:5px;text-align:right;">
               <a id="cancelViewBtn" class="easyui-linkbutton" icon="icon-cancel">取消</a>
           </div>
      </div>

</body>

</html>
