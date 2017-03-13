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
    <title>个人所得税税率</title>
    <%@ include file="/static/include/init.html" %>
    <%--<link rel="stylesheet" type="text/css" href="/static/easyui/css/bootstrap/easyui.css">--%>
</head>
<body>
<style type="text/css" rel="stylesheet">
    .fitem  {
        padding: 14px 40px 10px 40px;
        line-height:27px;
    }
</style>
<table id="dataGrid" class="easyui-datagrid">

</table>
<div id="tb" style="padding:5px 5px; padding-top: 5px;display: none">
    <a  id="addBtn" class="easyui-linkbutton" iconCls="icon-add">新增</a>
    <a  id="editBtn" class="easyui-linkbutton" iconCls="icon-edit">修改</a>
    <a  id="delBtn" class="easyui-linkbutton" iconCls="icon-remove">删除</a>
    <a  id="okBtn" class="easyui-linkbutton" data-options="iconCls:'icon-ok'">启用</a>
    <a  id="noBtn" class="easyui-linkbutton" data-options="iconCls:'icon-no'">停用</a>

</div>
<!-- 新增编辑框start -->
<div id="newDialog" style="height:380px;width:400px;display: none" data-options="modal:true,footer:'#dlg-buttons'">
    <form id="newForm" method="post" data-options="fit:true">
        <input type="hidden" id="TAX_ID" />
        <div class="fitem">
            <label style="width: 120px">起征基数：</label>
            <input id="BASE_NUM" name="BASE_NUM" class="easyui-numberbox" data-options="groupSeparator:',',prefix:'¥'" min="0.00" max="100000000" precision="2" type="text" style="width:160px;height:27px">&nbsp;元
        </div>
        <div class="fitem">
            <label style="width: 120px">应纳所得下限：</label>
            <input id="LOW_LIMIT" name="LOW_LIMIT" class="easyui-numberbox" data-options="groupSeparator:',',prefix:'¥'" min="0.00" max="100000000" precision="2" type="text" style="width:160px;height:27px">&nbsp;元
        </div>
        <div class="fitem">
            <label style="width: 120px">应纳所得上限(含)：</label>
            <input id="HIGH_LIMIT" name="HIGH_LIMIT" class="easyui-numberbox" data-options="groupSeparator:',',prefix:'¥'" min="0.00" max="100000000" precision="2" type="text" style="width:160px;height:27px">&nbsp;元
        </div>
        <div class="fitem">
            <label style="width: 120px">税&nbsp;&nbsp;率：</label>
            <input id="RATE" name="RATE" class="easyui-numberbox" min="0.00" max="100000000" precision="2" type="text" style="width:160px;height:27px">
        </div>
        <div class="fitem">
            <label style="width: 120px">速算扣除数：</label>
            <input id="DECUTE_NUM" name="DECUTE_NUM" class="easyui-numberbox" data-options="groupSeparator:',',prefix:'¥'" min="0.00"  max="100000000" precision="2" type="text" style="width:160px;height:27px">&nbsp;元
        </div>
    </form>

</div>
<div id="dlg-buttons" style="padding:5px;text-align:right;">
    <a href="javascript:void(0)" id="submitBtn" class="easyui-linkbutton c6" iconCls="icon-ok">保存</a>
    <a id="close" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
</div>
<!-- 新增编辑框end -->




<%--<script type="text/javascript" src="/static/jquery/jquery-1.11.3.min.js"></script>--%>
<script type="text/javascript" src="/modules/fbd/hrm/salary/js/salary_tax.js"></script>
<%--<script type="text/javascript" src="/static/js/head.js"></script>--%>


</body>
</html>
