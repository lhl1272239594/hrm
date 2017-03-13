<%--
  Created by IntelliJ IDEA.
  User:
  Date: 2016/8/21
  Time: 10:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>创建工资</title>
    <link rel="stylesheet" type="text/css" href="/static/easyui/css/bootstrap/easyui.css">
</head>
<body>
<style type="text/css" rel="stylesheet">
    .fitem  {
        padding: 10px 20px 10px 20px;
        line-height:67px;
    }
    .info {
        color: #ff6b06;
    }
</style>
<table id="dataGrid" class="easyui-datagrid">

</table>
<div id="tb" style="padding:5px;display: none">
    姓 名：<input id="PERSON_NAME" type="text" class="easyui-textbox" style="width:140px">
    科 室：<select id="DEPT_ID" class="easyui-combobox" panelHeight="180px" data-options="editable:false" style="width:140px"></select>
    工资级别：<select id="TYPE_ID" class="easyui-combobox" panelHeight="auto" data-options="editable:false" style="width:140px"></select>
    <a id="searchBtn" class="easyui-linkbutton" iconCls="icon-search">查询</a>
    <a id="clearBtn" class="easyui-linkbutton" iconCls="icon-cancel">清空</a>
    <br/>
    <a  id="createBtn" class="easyui-linkbutton" iconCls="icon-add">创建当月工资</a>
    <a  id="recalBtn" class="easyui-linkbutton" iconCls="icon-reload">重新计算</a>
    <a  id="infoBtn" class="easyui-linkbutton" data-options="iconCls:'icon-save'">工资明细</a>
    <a  id="adjustBtn" class="easyui-linkbutton" iconCls="icon-edit">手工调整</a>
    <%--<a  id="dealBtn" class="easyui-linkbutton" data-options="iconCls:'icon-ok'">提交</a>--%>
    <%--<a  id="noBtn" class="easyui-linkbutton" data-options="iconCls:'icon-no'">停用</a>
    <a  id="delBtn" class="easyui-linkbutton" iconCls="icon-remove">删 除</a>--%>
</div>
<!-- 手工调整编辑框start -->
<div id="newDialog" style="height:320px;width:390px;display: none" data-options="modal:true,footer:'#dlg-buttons'">
    <form id="newForm" method="post" data-options="fit:true">

        <div class="fitem" >
            <label style="width: 80px">调整金额：</label>
            <input id="ADJUST_MONEY" name="ADJUST_MONEY" class="easyui-numberbox" data-options="groupSeparator:',',prefix:'¥'" min="0.00"  max="100000000" precision="2" type="text" style="width:190px;height:27px">&nbsp;元
        </div>

        <div class="fitem" >
            <label style="width: 80px">调整原因：</label>
            <input class="easyui-textbox"  id="ADJUST_REASON" data-options="multiline:true"  style="height:120px;width: 190px;"/>
        </div>
    </form>

</div>
<div id="dlg-buttons" style="padding:5px;text-align:right;">
    <a href="javascript:void(0)" id="submitBtn" class="easyui-linkbutton c6" iconCls="icon-ok">保存</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel"
       onclick="$('#newDialog').dialog('close')">关闭</a>
</div>
<!-- 手工调整编辑框end -->
<!-- 工资详单start -->
<div id="newDialog1" class="easyui-window" style="width:600px;height:500px;overflow: auto;display: none">
    <table id="dataGrid1" class="easyui-datagrid">

    </table>
    <div id="tb1" >

        <span class="info" style="margin-left: 5px">姓 名：</span><span class="info" id="NAME"></span>
        <span class="info" style="margin-left: 30px">工资月份：</span><span class="info" id="TIME"></span>
        <%--<span class="info" style="margin-left: 50px">单 位：</span><span class="info" id="ORG"></span>--%>
    </div>
</div>

<!-- 工资详单end -->
<!-- 创建工资编辑框start -->
<div id="chooseDept" style="height:240px;width:460px;display: none;overflow: auto;z-index: -999" data-options="modal:true,footer:'#dlg-buttons'">
    <form id="newForm1" method="post" data-options="fit:true">
        <br/>
        <div  class="fitem">
            <label style="width: 80px"><a href="#" class="easyui-linkbutton" style="float: left"  onclick="choose(1)" >选择科室：</a></label>
            <input class="easyui-textbox" id="objName" data-options="multiline:true"  editable="false" style="height:120px;width: 270px"/>
        </div>
    </form>
</div>
<div id="dlg-buttons" style="padding:5px;text-align:right;">
    <a href="javascript:void(0)" id="submitBtn1" class="easyui-linkbutton c6" iconCls="icon-ok">确定</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel"
       onclick="$('#chooseDept').dialog('close')">关闭</a>
</div>
<!-- 创建工资编辑框end -->
<%--选择部门--%>
<div id="choosePerson"  style="height: 500px;width:700px;display: none"   data-options="modal:true,footer:'#choose-buttons'">
    <div id="west" data-options="region:'west'"  style="width:38%;height:100%;float: left;overflow:auto;">

        <ul  id="userTree" class="easyui-tree" ></ul>
    </div>
    <div id="center" data-options="region:'center'"  style="width:13%;height:100%;float:left;">
        <div class="btn" style="margin-top: 180px">
            <a  onclick="addPerson()" style="float: left" class="easyui-linkbutton" data-options="iconCls:'icon-redo'">添加</a>
        </div>
        <div class="btn" style="margin-top: 30px">
            <a  onclick="removePerson()"style="float: left"  class="easyui-linkbutton" data-options="iconCls:'icon-undo'">移除</a>
        </div>
    </div>
    <div id="east" data-options="region:'east'"  style="width:49%;height:100%;float:left;">
        <table class="easyui-datagrid" id="personGrid">
        </table>
    </div>

    <div style="padding:5px;text-align:right;" id="choose-buttons">
        <a  onclick="savePerson()" class="easyui-linkbutton" icon="icon-ok">确定</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel"
           onclick="$('#choosePerson').window('close')">关闭</a>
    </div>
</div>

<script type="text/javascript" src="/static/jquery/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="/modules/fbd/hrm/salary/js/salary_tmp.js"></script>
<script type="text/javascript" src="/modules/fbd/hrm/salary/js/userTree1.js"></script>
<script type="text/javascript" src="/static/js/head.js"></script>


</body>
</html>
