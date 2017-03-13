<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
    <title>审批管理</title>
    <%@ include file="/static/include/easyui.jsp"%>
    <script type="text/javascript" src="/static/js/head.js"></script>
    <script type="text/javascript" src="/modules/fbd/hrm/attendance/js/approve.js"></script>

</head>


<body class="easyui-layout">
<style type="text/css" rel="stylesheet">
    .fitem  {
        padding: 10px 40px 10px 40px;
        line-height:27px;
    }

</style>

<div id="tb"  style="padding:5px;background:#eee;">
    提交月份：
    <input id="month" class="easyui-datebox combox_width" editable="false">
    <input type="hidden" id="month1"/>
    员工姓名：<input class="easyui-textbox combox_width"  id="userId" />
        考勤业务:<input class="easyui-combobox combox_width" id="searchId" panelHeight="auto" editable="false" />
       <%-- 申请人:<input class="easyui-textbox" id="userId" style="width:100px" type="text"/>--%>
        审批状态:<input class="easyui-combobox combox_width" id="approveStatus" panelHeight="auto" editable="false" />


        <a id="searchAllBtn" class="easyui-linkbutton" iconCls="icon-search">查询</a>
        <a id="clearBtn" class="easyui-linkbutton" iconCls="icon-cancel">清空</a>
        <br/>
        <a id="approveBtn" class="easyui-linkbutton" data-options="iconCls:'icon-edit'">审批</a>
</div>


<table id="primaryGrid"  class="easyui-datagrid" ></table>

<div id="editWin"  style="height:420px;width:550px;"   data-options="modal:true,footer:'#dlg-buttons'">
    <form id="editForm" data-options="fit:true" method="post">
        <input type="hidden"  id="id" />
        <input type="hidden"  id="attFunId" />
        <table class="editTable">
            <div  class="fitem">
                <label style="width: 60px;" >考勤业务:</label>
                <input  id="editAttFunId" class="easyui-textbox" style="width:150px;height:27px"  editable="false"  />
              </div>
                <div class="fitem">
                <label style="width: 60px;" >申请人员:</label>
                <input  id="editUserId" class="easyui-textbox" style="width:150px;height:27px" editable="false" />
            </div>
                <div class="fitem">
                <label style="width: 60px;" >所在部门:</label>
                <input  id="editDept" class="easyui-textbox" style="width:360px;height:27px"  editable="false" />
            </div>
            <div class="fitem">
                <label style="width: 60px;">开始时间:</label>
                <input id="editStartDate" class="easyui-textbox"  style="width:150px;height:27px"editable="false" />

                <label style="width: 60px;">结束时间:</label>
                <input id="editEndDate"  class="easyui-textbox"  style="width:150px;height:27px"editable="false" >
            </div>
            <div id= "tripWork" >
                <div class="fitem">
                <label style="width: 60px;" >出发地:</label>
                <input class="easyui-textbox" id="editTripWorkPlace" style="width:150px;height:27px" editable="false" />

                <label style="width: 60px;" >目的地:</label>
                <input class="easyui-textbox" id="editTripWorkDestination" style="width:150px;height:27px" editable="false" />
                </div>
                <div class="fitem">
                    <label style="width: 60px;"  >公出原因:</label>
                    <input class="easyui-textbox" id="editTripWorkReason" style="width:355px;height:54px" data-options="multiline:true"  editable="false"/>
                </div>

            </div>
            <div id= "overTime" >
             <div class="fitem">
                <label style="width: 60px;"  >加班原因:</label>
                <input class="easyui-textbox" id="editOverTimeReason" style="width:355px;height:54px" data-options="multiline:true" editable="false"/>
             </div>
            </div>
            <div id= "offWork" >
             <div class="fitem">
                <label style="width: 60px;" >假期类型:</label>
                <input class="easyui-textbox" id="editHolidayType" style="width:150px;height:27px" editable="false" />
            </div>
             <div class="fitem">
                <label style="width: 60px;"  >请假原因:</label>
                <input class="easyui-textbox" id="editOffWorkReason" style="width:355px;height:54px" data-options="multiline:true"editable="false" />
              </div>
            </div>
                <div id= "adjustDay" >
                    <div class="fitem">
                        <label style="width: 60px;" >调休类型:</label>
                        <input class="easyui-textbox" id="editAdjustDayType" style="width:150px;height:27px" editable="false" />

                        <label style="width: 60px;" >休假项目:</label>
                        <input class="easyui-textbox" id="editHoliday" style="width:150px;height:27px" editable="false" />
                    </div>
                </div>
        </table>
    </form>
</div>

<div id="dlg-buttons" style="padding:5px;text-align:right;" >
    <a  id="passBtn" class="easyui-linkbutton" icon="icon-redo">通过</a>
    <a  id="rejectBtn" class="easyui-linkbutton" icon="icon-undo">驳回</a>
    <a  id="cancelBtn" class="easyui-linkbutton" icon="icon-cancel">取消</a>
</div>
<div id="choosePerson"  style="height: 560px;width:800px;display: none"   data-options="modal:true,footer:'#choose-buttons'">
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
</body>

</html>
