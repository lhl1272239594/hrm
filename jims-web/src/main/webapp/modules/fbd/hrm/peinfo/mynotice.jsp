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
    <title>我的公告通知</title>
    <%--<link rel="stylesheet" type="text/css" href="/static/easyui/css/bootstrap/easyui.css">--%>
    <%@ include file="/static/include/init.html" %>
</head>
<body>
<style type="text/css" rel="stylesheet">
    .fitem  {
        padding: 5px 10px 10px 10px;
        line-height:22px;
    }
</style>
<table id="dataGrid" class="easyui-datagrid">

</table>
<div id="tb" style="padding:5px;display: none">
    公告标题：<input id="NAME1" type="text" class="easyui-textbox" style="width:140px">

    <a id="searchBtn" class="easyui-linkbutton" iconCls="icon-search">查询</a>
    <a id="clearBtn" class="easyui-linkbutton" iconCls="icon-cancel">清空</a>
    <%--<a  id="addBtn" class="easyui-linkbutton" iconCls="icon-add">新增</a>
    <a  id="editBtn" class="easyui-linkbutton" iconCls="icon-edit">修改</a>--%>
    <a  id="infoBtn" class="easyui-linkbutton" data-options="iconCls:'icon-save'">详情</a>
    <%--<a  id="delBtn" class="easyui-linkbutton" iconCls="icon-remove">删除</a>--%>
    <%--<a  id="dealBtn" class="easyui-linkbutton" data-options="iconCls:'icon-ok'">发布</a>--%>


</div>
<!-- 新增招聘编辑框start -->
<%--<div id="newDialog" style="height:580px;width:684px;display: none;overflow: auto;z-index: -999" data-options="modal:true,footer:'#dlg-buttons'">
    <form id="newForm" method="post" data-options="fit:true">
        <input id="NOTICE_ID" type="hidden">
        <br/>
        <div class="fitem" >
            <label style="width: 75px;font-size: 14px">公告标题：</label>
            <input id="NAME" name="NAME" class="easyui-textbox" style="width:566px;height:27px">
        </div>
        <div  class="fitem">
            <label style="width: 75px;font-size: 14px">接收人员：</label>
            <input class="easyui-textbox" id="userName"  name="userName" editable="false" style="width:529px;height:27px"/><a href="#" class="easyui-linkbutton"  onclick="choose()" >选 择</a>
        </div>
        <div class="fitem" >
            <label style="width: 75px;font-size: 14px">公告内容：</label>
            <textarea id="notice_content" name="content" style="width:555px;height:326px;margin-left: 20px"></textarea>
        </div>
    </form>
</div>--%>
<div id="dlg-buttons" style="padding:5px;text-align:right;">
    <a href="javascript:void(0)" id="submitBtn" class="easyui-linkbutton c6" iconCls="icon-ok">保存</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel"
       onclick="javascript:$('#newDialog').dialog('close')">关闭</a>
</div>
<!-- 新增招聘编辑框end -->
<div id="w" class="easyui-window" title="公告详情" data-options="modal:true,minimizable:false,closed:true,iconCls:'icon-save'" style="width:800px;height:600px;padding:10px;">
    <table width="100%" border="0">
        <tbody>
        <tr>
            <td colspan="3" align="center" height="60"><strong><span id="bt" style="font-size:30px;"></span></strong></td>
        </tr>
        <tr>
            <%--<td align="left" style="vertical-align: top" width="8%" height="60"><span id="jsr" style="color:#888888">接收人：</span></td>
            <td align="left" style="vertical-align: top" width="20%" height="60"><span id="ry" style="color:#888888"></span></td>--%>
            <td align="right" width="72%" style="vertical-align: top"><span id="sj" style="color:#888888"></span></td>
        </tr>
        <tr>
            <td align="left" colspan="3">
                <p>
                <span id="bodys" style="font-size:20px;line-height:35px"></span>
                </p>
            </td>
        </tr>
        </tbody>
    </table>
</div>
<%--<script type="text/javascript" src="/static/jquery/jquery-1.11.3.min.js"></script>--%>
<script type="text/javascript" src="/modules/fbd/hrm/peinfo/js/mynotices.js"></script>

<%--<script type="text/javascript" src="/static/js/head.js"></script>--%>
<script type="text/javascript" charset="utf-8" src="/static/kindeditor/kindeditor.js"></script>
<script type="text/javascript" charset="utf-8" src="/static/kindeditor/lang/zh_CN.js"></script>
<script charset="utf-8" src="/static/kindeditor/plugins/code/prettify.js"></script>
<link rel="stylesheet" href="/static/kindeditor/themes/default/default.css" />

</body>
</html>
