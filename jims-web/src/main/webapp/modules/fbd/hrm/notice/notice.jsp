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
    <title>公告通知</title>
    <link rel="stylesheet" type="text/css" href="/static/easyui/css/bootstrap/easyui.css">
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
    状 态：<select id="STATE" class="easyui-combobox" panelHeight="auto" data-options="editable:false" style="width:140px">
    <option value="">请选择</option>
    <option value="0">起草</option>
    <option value="1">已发布</option>

</select>
    发布时间：<input type="text" id="startDate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})" class="Wdate"/>
    到<input type="text" id="endDate" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',readOnly:true})" class="Wdate"/>
    <a id="searchBtn" class="easyui-linkbutton" iconCls="icon-search">查询</a>
    <a id="clearBtn" class="easyui-linkbutton" iconCls="icon-cancel">清空</a>
    <br/>
    <a  id="addBtn" class="easyui-linkbutton" iconCls="icon-add">新增</a>
    <a  id="editBtn" class="easyui-linkbutton" iconCls="icon-edit">修改</a>
    <a  id="delBtn" class="easyui-linkbutton" iconCls="icon-remove">删除</a>
    <a  id="dealBtn" class="easyui-linkbutton" data-options="iconCls:'icon-ok'">发布</a>
    <a  id="infoBtn" class="easyui-linkbutton" data-options="iconCls:'icon-save'">详情</a>

</div>
<!-- 新增公告编辑框start -->
<div id="newDialog" style="height:500px;width:689px;display: none;overflow: auto;z-index: -999" data-options="modal:true,footer:'#dlg-buttons'">
    <form id="newForm" method="post" data-options="fit:true">
        <input id="NOTICE_ID" type="hidden">
        <br/>
        <div class="fitem" >
            <label style="width: 80px;font-size: 14px">公告标题：</label>
            <input id="NAME" name="NAME" class="easyui-textbox" style="width:551px;height:27px">
        </div>
        <div  class="fitem">
            <label style="width: 80px;font-size: 14px">接收人员：</label>
            <input class="easyui-textbox" id="userName"  name="userName" editable="false" style="width:514px;height:27px"/><a href="#" class="easyui-linkbutton"  onclick="choose()" >选 择</a>
        </div>
        <div class="fitem" >
            <label style="width: 80px;font-size: 14px">公告内容：</label>
            <textarea id="notice_content" name="content" style="width:540px;height:306px;margin-left: 24px"></textarea>
        </div>
    </form>
</div>
<div id="dlg-buttons" style="padding:5px;text-align:right;">
    <a href="javascript:void(0)" id="submitBtn" class="easyui-linkbutton c6" iconCls="icon-ok">保存</a>
    <a id="close" class="easyui-linkbutton" iconCls="icon-cancel">关闭</a>
</div>
<!-- 新增公告编辑框end -->
<div id="w" class="easyui-window" title="公告详情" data-options="modal:true, minimizable: false,closed:true,iconCls:'icon-save'" style="width:800px;height:600px;padding:10px;">
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
<%--选择人员start--%>
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
<%--选择人员end--%>
<script type="text/javascript" src="/static/jquery/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="/static/datepicker/WdatePicker.js"></script>
<script type="text/javascript" src="/modules/fbd/hrm/notice/js/notice.js"></script>
<script type="text/javascript" src="/modules/fbd/hrm/notice/js/userTree1.js"></script>
<script type="text/javascript" src="/static/js/head.js"></script>
<script type="text/javascript" charset="utf-8" src="/static/kindeditor/kindeditor.js"></script>
<script type="text/javascript" charset="utf-8" src="/static/kindeditor/lang/zh_CN.js"></script>
<script charset="utf-8" src="/static/kindeditor/plugins/code/prettify.js"></script>
<link rel="stylesheet" href="/static/kindeditor/themes/default/default.css" />

</body>
</html>
