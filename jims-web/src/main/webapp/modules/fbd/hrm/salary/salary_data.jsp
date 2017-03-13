<%--
  Created by IntelliJ IDEA.
  User:
  Date: 2016/9/17
  Time: 23:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>薪资档案</title>
    <%@ include file="/static/include/init.html" %>
    <%--<link rel="stylesheet" type="text/css" href="/static/easyui/css/bootstrap/easyui.css">--%>
</head>
<body class="easyui-layout">
<style type="text/css" rel="stylesheet">
    .fitem  {
        padding: 40px 40px 10px 40px;
        line-height:27px;
    }
</style>
<!--部门信息  start-->
<div id=examClass" data-options="region:'west'"  style="width:15%;height:100%;float: left;overflow-y:auto;">
    <table id="staff" class="easyui-datagrid">

    </table>
</div>

<!--部门信息  end-->


<!--薪资档案数据  start-->
<div id="examSubclass" data-options="region:'center'" style="width:85%;height:100%;float:left;">
    <table id="staffGrid" class="easyui-datagrid">

    </table>
</div>
<div id="ft" style="display: none">
    &nbsp;姓 名：<input id="PERSON_NAME" type="text" class="easyui-textbox" style="width:80px">
    性别：<select id="SEX" class="easyui-combobox" panelHeight="auto" data-options="editable:false" style="width:70px">
    <option value="">全部</option>
    <option value="02">男</option>
    <option value="01">女</option>
</select>
    身份证号：<input id="PERSON_CARD" type="text" class="easyui-textbox" style="width:140px">
    工资级别：<select id="TYPE_ID" class="easyui-combobox" panelHeight="auto" data-options="editable:false" style="width:140px"></select>
    工资状态：<select id="STATE" class="easyui-combobox" panelHeight="auto" data-options="editable:false" style="width:70px">
    <option value="">全部</option>
    <option value="0">正常</option>
    <option value="1">停发</option>
</select>
    <a id="searchBtn" class="easyui-linkbutton" iconCls="icon-search">查询</a>
    <a id="searchAllBtn" class="easyui-linkbutton" iconCls="icon-search">展示全部</a>
    <a id="clearBtn" class="easyui-linkbutton" iconCls="icon-cancel">清空</a>
    <br/>
    <%--<a  id="addBtn" class="easyui-linkbutton" iconCls="icon-add">增 加</a>--%>
    <%--<a  id="infoBtn" class="easyui-linkbutton" iconCls="icon-save">详 情</a>--%>
    <a  id="editBtn" class="easyui-linkbutton" iconCls="icon-edit">变更级别</a>
    <a  id="okBtn" class="easyui-linkbutton" data-options="iconCls:'icon-ok'">起薪</a>
    <a  id="noBtn" class="easyui-linkbutton" data-options="iconCls:'icon-no'">停薪</a>
    <%--<a  id="delBtn" class="easyui-linkbutton" iconCls="icon-remove">删 除</a>--%>
</div>
<!--薪资档案数据  end-->
<%--<div id="newDialog" style="height:360px;width:370px;display: none" data-options="modal:true,footer:'#dlg-buttons'">
    <form id="newForm" method="post" data-options="fit:true">
        <input type="hidden" id="DATA_ID" />

        <div  class="fitem" style="margin-top: 40px">
            <label style="width: 60px;">选择人员：</label>
            <input class="easyui-combotree"  data-options="required:true"  id="userTree"  editable="false" style="height:27px;width: 200px"/>
        </div>
        <div class="fitem" style="margin-top: 60px">
            <label style="width: 60px">人员类别：</label>
            <select id="HUMAN_TYPE_ID" class="easyui-combobox" panelHeight="auto" data-options="editable:false" style="height:27px;width:200px"></select>
        </div>
    </form>

</div>
<div id="dlg-buttons" style="padding:5px;text-align:right;">
    <a href="javascript:void(0)" id="submitBtn" class="easyui-linkbutton c6" iconCls="icon-ok">保存</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel"
       onclick="javascript:$('#newDialog').dialog('close')">关闭</a>
</div>--%>
<!--添加组织机构人员模态  end-->
<!-- 人员类别变更编辑框start -->
<div id="newDialog1" style="height:200px;width:370px;display: none" data-options="modal:true,footer:'#dlg-buttons1'">
    <form id="newForm1" method="post" data-options="fit:true">
        <div class="fitem">
            <label style="width: 70px">工资级别：</label>
            <select id="HUMAN_TYPE_ID1" class="easyui-combobox" panelHeight="auto" data-options="editable:false" style="height:27px;width:200px"></select>
        </div>
    </form>

</div>
<div id="dlg-buttons1" style="padding:5px;text-align:right;">
    <a href="javascript:void(0)" id="submitBtn1" class="easyui-linkbutton c6" iconCls="icon-ok">保存</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel"
       onclick="javascript:$('#newDialog1').dialog('close')">关闭</a>
</div>
<!-- 人员类别变更编辑框end -->

<%--<script type="text/javascript" src="/static/jquery/jquery-1.11.3.min.js"></script>--%>
<script type="text/javascript" src="/modules/fbd/hrm/salary/js/salary_data.js"></script>
<%--<script type="text/javascript" src="/static/js/head.js"></script>--%>
</body>
</html>
