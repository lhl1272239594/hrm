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
    <title>合同管理</title>
    <%--
        <%@ include file="/static/include/easyui.jsp"%>
    --%>
    <link rel="stylesheet" type="text/css" href="/static/easyui/css/bootstrap/easyui.css">



</head>


<body class="easyui-layout">
<style type="text/css" rel="stylesheet">
    .fitem  {
        padding: 10px 40px 10px 40px;
        line-height:27px;
    }

</style>

<table id="primaryGrid"  class="easyui-datagrid" ></table>
<%--人员选择--%>
<div id="choosePerson"  style="height: 500px;width:700px;display: none"   data-options="modal:true,footer:'#choose-buttons'">
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
        <table title="已选人员" id="personGrid">
        </table>
    </div>

    <div style="padding:5px;text-align:right;" id="choose-buttons">
        <a  onclick="savePerson()" class="easyui-linkbutton" icon="icon-ok">确定</a>
        <a  onclick="cancelPerson()" class="easyui-linkbutton" icon="icon-cancel">取消</a>
    </div>
</div>
<div id="tb"  style="padding:5px;background:#eee;">

    合同名称：<input class="easyui-textbox" id="contractName" style="width:140px;height:27px"  type="text"/>
    合同编号：<input class="easyui-textbox" id="contractCode" style="width:140px;height:27px"  type="text"/>
    合同类型：<input class="easyui-combobox" id="contractType" style="width:140px;height:27px"
                data-options="multiline:true,editable:false"type="text"/>

    合同状态：<input class="easyui-combobox" id="statusType" style="width:140px;height:27px"
                data-options="multiline:true,editable:false"type="text"/>


    结束时间：<input id="startDate" class="easyui-datebox" data-options="multiline:true,editable:false"
                style="width:140px;height:27px"/>
    到<input id="endDate" class="easyui-datebox"
            data-options="multiline:true,editable:false" style="width:140px;height:27px">

    <a id="searchAllBtn" class="easyui-linkbutton" iconCls="icon-search">查询</a>
    <a id="clearBtn" class="easyui-linkbutton" iconCls="icon-cancel">清空</a>
    <br>
    <div >
        <a  id="addBtn" class="easyui-linkbutton" data-options="iconCls:'icon-add'">新增</a>
        <a  id="editBtn" class="easyui-linkbutton" data-options="iconCls:'icon-edit'">修改</a>
        <a  id="removeBtn" class="easyui-linkbutton" data-options="iconCls:'icon-remove'">删除</a>
    </div>
</div>




<div id="editWin"  style="height:350px;width:620px;"   data-options="modal:true,footer:'#dlg-buttons'">
    <form id="editForm" data-options="fit:true" method="post" enctype="multipart/form-data">
        <input type="hidden"  id="id" />
        <input type="hidden"  id="flag" />
        <input type="hidden"  id="url" />
        <input type="hidden"  id="editUserId" />
        <input type="hidden"  id="editDeptId" />
        <input type="hidden"  id="editUserFlag" />
        <table class="editTable">
            <div  class="fitem">
                <label style="width: 80px;" >合同名称：</label>
                <input  id="editContractName" class="easyui-textbox" disabled style="width: 150px;height:27px"/>

                <label style="width: 80px;" >合同编号：</label>
                <input  id="editContractCode" class="easyui-textbox" style="width: 150px;height:27px"  />
            </div>
            <div  class="fitem">
                <label style="width: 80px;" >所在部门：</label>
                <input  id="editDeptName" class="easyui-textbox" data-options="multiline:true,editable:false" style="width: 150px;height:27px"  />
                <label style="width: 80px;" >签订人员：</label>
                <input class="easyui-textbox" id="editUserName" data-options="multiline:true,editable:false"  style="height:27px;width: 150px"/>
                <a id="chooseUser" class="easyui-linkbutton"  onclick="chooseUser()" >选择</a>


            </div>
            <div class="fitem">
                <label style="width: 80px;">合同类型：</label>
                <input id="editContractType" class="easyui-combobox"  data-options="multiline:true,editable:false" style="width:150px;height:27px" />
            <label style="width: 80px;">试用期(天)：</label>
            <input id="editProbationPeriod" class="easyui-combobox"  data-options="multiline:true,editable:false" style="width:150px;height:27px" />
              </div>
            <div class="fitem">
                <label style="width: 80px;">开始时间：</label>
                <input id="editStartDate" class="easyui-datebox"  data-options="multiline:true,editable:false" style="width:150px;height:27px" />

                <label style="width: 80px;">结束时间：</label>
                <input id="editEndDate"  class="easyui-datebox"   data-options="multiline:true,editable:false" style="width:150px;height:27px">
            </div>
            <div class="fitem" >
                <label style="width: 80px;">签订次数：</label>
                <input  id="editSignNum" class="easyui-textbox" data-options="multiline:true,editable:false" style="width: 150px;height:27px"  />

                <label style="width: 80px;">合同文件：</label>
                <input class="easyui-button" type="file" id="myFiles"  name="myFiles" onChange="showInfo()"  style="width:200px"/>
            </div>
        </table>
    </form>

</div>
<div id="dlg-buttons" style="padding:5px;text-align:right;" >
    <a  id="saveBtn" class="easyui-linkbutton" icon="icon-ok" onclick="upFile()">保存</a>
    <a  id="cancelBtn" class="easyui-linkbutton" icon="icon-cancel">取消</a>
</div>
</body>
<script type="text/javascript" src="/static/jquery/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="/static/js/head.js"></script>
<script type="text/javascript" src="/modules/fbd/hrm/contract/js/contract.js"></script>
<script type="text/javascript" src="/modules/fbd/hrm/contract/js/contract_user_tree.js"></script>

</html>
