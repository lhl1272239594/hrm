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
    <title>排班管理</title>
    <%@ include file="/static/include/easyui.jsp" %>
    <script type="text/javascript" src="/modules/fbd/hrm/attendance/js/frequency_record.js"></script>
    <script type="text/javascript" src="/modules/fbd/hrm/attendance/js/user_tree.js"></script>

</head>


<body class="easyui-layout">
<style type="text/css" rel="stylesheet">
    .fitem {
        padding: 10px 40px 10px 40px;
        line-height: 27px;
    }

</style>
<%--排班头表--%>
<div id="tb1" style="padding:2px;background:#eee;">
    <div>
        员工姓名：<input class="easyui-textbox" id="userId1" style="width:140px" type="text"/>
        排班时间：

        <input id="freTimeMonth1" class="easyui-datebox" style="width:140px;height:27px" editable="false">
       <%-- <span id="day1">
          <input id="freTimeDay1" class="easyui-datebox" style="width:140px;height:27px" editable="false">
              </span>
--%>
        <a id="searchBtn1" class="easyui-linkbutton" iconCls="icon-search">查询</a>
        <a id="clearBtn1" class="easyui-linkbutton" iconCls="icon-cancel">清空</a>
    </div>
    <div>

        <a id="addBtn" class="easyui-linkbutton" data-options="iconCls:'icon-add'">新增</a>
        <%--<a id="editBtn" class="easyui-linkbutton" data-options="iconCls:'icon-edit'">修改</a>--%>
        <a id="delBtn1" class="easyui-linkbutton" data-options="iconCls:'icon-remove'">删除</a>
        <a id="viewBtn1" class="easyui-linkbutton" data-options="iconCls:'icon-tip'">查看</a>
        <%--  <a  href="#"  onclick="searchAllData()" class="easyui-linkbutton" data-options="iconCls:'icon-tip'">显示汇总记录</a>
              <a  href="#"  onclick="searchDetailData()" class="easyui-linkbutton" data-options="iconCls:'icon-tip'">显示详细记录</a>
         --%>  </div>
</div>


<table id="dataGrid" ></table>
<!--排班编辑窗口-->
<div id="newDialog" style="" data-options="modal:true">

    <div id="#tb" style="padding:2px;background:#eee;">
        员工姓名：<input class="easyui-textbox" id="userId" style="width:140px" type="text"/>
       <%-- 排班时间：

        <input id="freTimeMonth" class="easyui-datebox" style="width:140px;height:27px" editable="false">--%>
       <%-- <span id="day">
          <input id="freTimeDay" class="easyui-datebox" style="width:140px;height:27px" editable="false">
              </span>--%>

        <a id="searchBtn" class="easyui-linkbutton" iconCls="icon-search">查询</a>
        <a id="clearBtn" class="easyui-linkbutton" iconCls="icon-cancel">清空</a>
        <br/>

        <%--<a id="addBtn" class="easyui-linkbutton" data-options="iconCls:'icon-add'">新增</a>--%>
        <a id="editBtn" class="easyui-linkbutton" data-options="iconCls:'icon-edit'">修改</a>
        <a id="delBtn" class="easyui-linkbutton" data-options="iconCls:'icon-remove'">删除</a>
        <a id="viewBtn" class="easyui-linkbutton" data-options="iconCls:'icon-tip'">明细</a>
    <%--  <a  href="#"  onclick="searchAllData()" class="easyui-linkbutton" data-options="iconCls:'icon-tip'">显示汇总记录</a>
          <a  href="#"  onclick="searchDetailData()" class="easyui-linkbutton" data-options="iconCls:'icon-tip'">显示详细记录</a>
     --%>  </div>
    <table id="primaryGrid"   ></table>
</div>





<div id="editWin" style="height: 330px;width:420px;" data-options="modal:true,footer:'#dlg-buttons'">
    <form id="editForm" data-options="fit:true" method="post">
        <input type="hidden" id="id"/>
        <input type="hidden" id="flag"/>
        <input type="hidden" id="freItemId"/>
        <input type="hidden" id="editUserId"/>
        <input type="hidden" id="editDeptId"/>
        <input type="hidden" id="editDeptName"/>
        <input type="hidden" id="editUserFlag"/>
        <table class="editTable">
            <div class="fitem">
                <label style="width: 60px;">排班人员：</label>
                <input class="easyui-textbox" id="editUserName" editable="false" style="height:27px;width: 200px"/>
                <a id="chooseUser" class="easyui-linkbutton" onclick="chooseUser()">选择</a>
            </div>
            <div id="editAllDate">
                <div class="fitem">
                    <label style="width: 60px;">排班日期：</label>
                    <input id="editStartDate" class="easyui-datebox" style="width:90px;height:27px" editable="false"/>
                    到
                    <input id="editEndDate" class="easyui-datebox" style="width:90px;height:27px" editable="false">
                </div>
            </div>
            <div id="editOneDate">
                <div class="fitem">
                    <label style="width: 60px;">排班日期：</label>
                    <input id="editDate" class="easyui-datebox" style="width:200px;height:27px" editable="false"/>
                </div>
            </div>

            <div class="fitem">
                <label style="width: 60px;">排班规则：</label>
                <input class="easyui-combobox" id="editFreRule" style="width:200px;height:27px" editable="false"/>

            </div>
            <div id="editFreFirst">
                <div class="fitem">
                    <label id="firstFreItem" style="width: 60px;">初始班次：</label>
                    <label id="freItem" style="width: 60px;">已排班次：</label>
                    <input class="easyui-combogrid" id="editFreItem" style="width:200px;height:27px"
                           editable="false"/>

                </div>
            </div>
            <div id="order">
                <input class="easyui-textbox" id="editFreItemOrder" style="width:200px;height:27px" editable="false"/>
            </div>
        </table>

    </form>

</div>

<div id="db" style="padding:5px;background:#eee;">
        排班人员：<input class="easyui-textbox" disabled id="recordUserName" diabled style="height:27px;width: 80px"/>
        人员科室：<input class="easyui-textbox" disabled id="recordDeptName" diabled style="height:27px;width: 160px"/>
        排班规则：<input class="easyui-textbox" disabled id="recordFreRule" diabled style="height:27px;width:160px" />
        按月查询：<select class="easyui-combobox" id="TIME" panelHeight="140px"  editable="false" style="width:70px;height:27px;">
    <option value="">全部</option>
    <option value="01">&nbsp;1</option>
    <option value="02">&nbsp;2</option>
    <option value="03">&nbsp;3</option>
    <option value="04">&nbsp;4</option>
    <option value="05">&nbsp;5</option>
    <option value="06">&nbsp;6</option>
    <option value="07">&nbsp;7</option>
    <option value="08">&nbsp;8</option>
    <option value="09">&nbsp;9</option>
    <option value="10">&nbsp;10</option>
    <option value="11">&nbsp;11</option>
    <option value="12">&nbsp;12</option>
</select>
    <input type="hidden" id="editId"/>

    <a id="saveDetailBtn" class="easyui-linkbutton" data-options="iconCls:'icon-save'">保存</a>

</div>
<div id="detailWin" style="height: 560px;width:800px;" data-options="modal:true,footer:'#dlg-buttons'" >
    <table id="detailGrid"></table>
</div>

<div id="dlg-buttons" style="padding:5px;text-align:right;">
    <a id="saveBtn" class="easyui-linkbutton" icon="icon-ok">确定</a>
    <a id="cancelBtn" class="easyui-linkbutton" icon="icon-cancel">取消</a>
</div>
<div id="choosePerson" style="height: 500px;width:800px;" data-options="modal:true,footer:'#choose-buttons'">
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
</body>
<script type="text/javascript" src="/static/js/head.js"></script>

</html>
