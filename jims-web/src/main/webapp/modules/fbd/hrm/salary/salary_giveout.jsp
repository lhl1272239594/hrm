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
    <title>工资发放</title>
    <link rel="stylesheet" type="text/css" href="/static/easyui/css/bootstrap/easyui.css">

</head>
<body class="easyui-layout">
<style type="text/css" rel="stylesheet">
    .fitem  {
        padding: 10px 20px 10px 20px;
        line-height:27px;
    }
</style>
<!--部门信息  start-->
<%--<div id=examClass" data-options="region:'west'"  style="width:15%;height:100%;float: left;overflow-y:auto;">
    <table id="staff" class="easyui-datagrid">

    </table>
</div>--%>
<div id="west" data-options="region:'west'"  style="width:17%;height:100%;float: left;overflow:auto;">
    <div id="tb1" style="display: none">
        &nbsp;<%--科室选择：--%>
        <%--<a id="clearBtnDept" class="easyui-linkbutton" iconCls="icon-cancel">清空选择</a>--%>
        <a  id="allNodeClose" class="easyui-linkbutton" data-options="iconCls:'icon-undo',plain:true">全部合并</a>
        <a  id="allNodeOpen" class="easyui-linkbutton" data-options="iconCls:'icon-redo',plain:true">全部展开</a>
    </div>
    <ul id="userTree" class="easyui-tree" ></ul>
</div>
<!--部门信息  end-->


<!--薪资档案数据  start-->
<div id="examSubclass" data-options="region:'center'" style="width:83%;height:100%;float:left;">
    <table id="dataGrid" class="easyui-datagrid">

    </table>
</div>
<div id="tb" style="display: none">
    &nbsp;姓 名：<input id="PERSON_NAME" type="text" class="easyui-textbox" style="width:140px">
   <%--科 室：<select id="DEPT_ID" class="easyui-combobox" panelHeight="180px" data-options="editable:false" style="width:140px"></select>--%>
    <a id="searchBtn" class="easyui-linkbutton" iconCls="icon-search">查询</a>
    <a id="searchAllBtn" class="easyui-linkbutton" iconCls="icon-search">展示全部</a>
    <a id="clearBtn" class="easyui-linkbutton" iconCls="icon-cancel">清空</a>
    <br/>
    <a  id="dealBtn" class="easyui-linkbutton" data-options="iconCls:'icon-ok'">工资发放</a>
</div>
<!-- 按部门发放编辑框start -->
<%--<div id="chooseDept" style="height:240px;width:460px;display: none;overflow: auto;z-index: -999" data-options="modal:true,footer:'#dlg-buttons'">
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
</div>--%>
<!-- 按部门发放编辑框end -->
<%--选择部门--%>
<%--<div id="choosePerson"  style="height: 520px;width:700px;display: none"   data-options="modal:true,footer:'#choose-buttons'">
   &lt;%&ndash; &lt;%&ndash;<div id="west" data-options="region:'west'"  style="width:35%;height:100%;float: left;overflow:auto;">

        <ul  id="userTree" class="easyui-tree" ></ul>
    </div>&ndash;%&gt;&ndash;%&gt;
    <div id="center" data-options="region:'center'"  style="width:10%;height:100%;float:left;">
        <div class="btn" style="margin-top: 200px">
            <a  onclick="addPerson()" style="float: left" class="easyui-linkbutton" data-options="iconCls:'icon-redo'">添加</a>
        </div>
        <div class="btn" style="margin-top: 30px">
            <a  onclick="removePerson()"style="float: left"  class="easyui-linkbutton" data-options="iconCls:'icon-undo'">移除</a>
        </div>
    </div>
    <div id="east" data-options="region:'east'"  style="width:55%;height:100%;float:left;">
        <table class="easyui-datagrid" id="personGrid">
        </table>
    </div>

    <div style="padding:5px;text-align:right;" id="choose-buttons">
        <a  onclick="savePerson()" class="easyui-linkbutton" icon="icon-ok">确定</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel"
           onclick="$('#choosePerson').window('close')">关闭</a>
    </div>
</div>--%>
<div id="east" data-options="region:'east'"  style="display: none">
    <table class="easyui-datagrid" id="personGrid">
    </table>
</div>
<script type="text/javascript" src="/static/jquery/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="/modules/fbd/hrm/salary/js/salary_giveout.js"></script>
<script type="text/javascript" src="/modules/fbd/hrm/salary/js/userTree2.js"></script>
<script type="text/javascript" src="/static/js/head.js"></script>
</body>
</html>
