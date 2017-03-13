<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>考评类型设置</title>
    <link rel="stylesheet" type="text/css" href="/static/easyui/css/bootstrap/easyui.css">
    <link rel="stylesheet" type="text/css" href="/modules/fbd/hrm/css/public.css">
</head>
<body>

<table id="dataGrid" class="easyui-datagrid">

</table>
<div id="tb" style="padding:5px">
    <a  id="addBtn" class="easyui-linkbutton" iconCls="icon-add">增 加</a>
    <a  id="editBtn" class="easyui-linkbutton" iconCls="icon-edit">修 改</a>
    <a  id="delBtn" class="easyui-linkbutton" iconCls="icon-remove">删 除</a>
    <a  id="okBtn" class="easyui-linkbutton" data-options="iconCls:'icon-ok'">启用</a>
    <a  id="noBtn" class="easyui-linkbutton" data-options="iconCls:'icon-no'">停用</a>
    <a  id="selfBtn" class="easyui-linkbutton" iconCls="icon-save">设为科室自评</a>
    <a  id="otherBtn" class="easyui-linkbutton" iconCls="icon-save">设为考评科室</a>
    <a  id="backBtn" class="easyui-linkbutton" iconCls="icon-reload">恢复普通类型</a>
</div>
<!-- 新增编辑框start -->
<div id="newDialog" style="height:160px;width:390px;display: none" data-options="modal:true,footer:'#dlg-buttons'">
    <form id="newForm" method="post" data-options="fit:true">
        <input type="hidden" id="typeId" />
        <div class="fitem" style="margin-top: 10px;" >
            <label style="width: 80px">类型名称：</label>
            <input id="typeName" class="easyui-textbox" style="width:200px;height:27px" >
        </div>
    </form>

</div>
<div id="dlg-buttons" style="padding:5px;text-align:right;">
    <a href="javascript:void(0)" id="submitBtn" class="easyui-linkbutton c6" iconCls="icon-ok">保存</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel"
       onclick="javascript:$('#newDialog').dialog('close')">关闭</a>
</div>
<!-- 新增编辑框end -->


<script type="text/javascript" src="/static/jquery/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="/modules/fbd/hrm/evaluation/js/evaluation_type.js"></script>
<script type="text/javascript" src="/static/js/head.js"></script>


</body>
</html>
