<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>

<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>试题管理</title>
    <link rel="stylesheet" type="text/css" href="/static/easyui/css/bootstrap/easyui.css">

</head>
<body class="easyui-layout">
<style type="text/css" rel="stylesheet">
    .fitem  {
        padding: 10px 40px 10px 40px;
        line-height:27px;
    }
    #danxt_ul ul {
        margin-left: 10px;
    }
    #danxt_ul ul li {
        margin: 10px 0;
    }
    #duoxt_ul ul {
        margin-left: 10px;
    }
    #duoxt_ul ul li {
        margin: 10px 0;
    }
</style>

<div  data-options="region:'center'" style="padding:1px;background:#eee;">
    <table id="questionGrid" class="easyui-datagrid">

    </table>
</div>
<div id="tb" >
    试题分类：
    <input id="itemTree" class="easyui-combotree" style="width:150px" editable="false"/>
    题型：
    <input class="easyui-combobox" style="width:150px;" id="type" editable="false"/>
    状态：
    <select id="state" class="easyui-combobox" panelHeight="auto" style="width:150px" editable="false">
        <option value="2">全部</option>
        <option value="1">启用</option>
        <option value="0">停用</option>
    </select>
    <a id="searchBtn" class="easyui-linkbutton" iconCls="icon-search">搜索</a>
    <a id="clearBtn" class="easyui-linkbutton" iconCls="icon-cancel">清空</a>
    <br>
    <a  id="addBtn" class="easyui-linkbutton" data-options="iconCls:'icon-add'">新增</a>
    <a  id="editBtn" class="easyui-linkbutton" data-options="iconCls:'icon-edit'">修改</a>
    <a  id="okBtn" class="easyui-linkbutton" data-options="iconCls:'icon-ok'">启用</a>
    <a  id="noBtn" class="easyui-linkbutton" data-options="iconCls:'icon-no'">停用</a>
</div>
<!--添加试题  start-->
<%--<div id="addQuestion"  style="height: 500px;width:500px;"   data-options="modal:true,footer:'#dlg-buttons'">


    <form id="queForm" data-options="fit:true" method="post">
        <input type="hidden" id="queId" />
        <div class="fitem">
            <label style="width: 60px;">试题分类：</label>
            <input id="itemTree1" editable="false" data-options="required:true" class="easyui-combotree" style="width:320px;height:27px" />
        </div>

        <div class="fitem">
            <label style="width: 60px;" >试题题型：</label>
            <input class="easyui-combobox" id="type1" data-options="required:true" style="width:120px;height:27px" editable="false"/>

            <label style="width: 67px;" >启用状态：</label>
            <select class="easyui-combobox" editable="false" data-options="required:true" id="state1" panelHeight="auto" style="width:120px;height:27px">
                <option value="1">启用</option>
                <option value="0">停用</option>
            </select>
        </div>

        <div  class="fitem">
            <label style="width: 60px;" >试题题目：</label>
            <input class="easyui-textbox"  id="queName" data-options="required:true,multiline:true" style="height:100px;width: 320px"/>
        </div>

        <div id="pdt">
            <div  class="fitem">
                <label style="width: 60px;"  >试题答案：</label>
                <span id="pdt_answer" class="radioSpan" >
                    <input type="radio" name="pdt_flag" value="0">否</input>
                    <input type="radio" name="pdt_flag" value="1">是</input>
                </span>
            </div>
        </div>
        <div id="danxt">
            <div  class="fitem">
                <label style="width: 60px;"  >试题答案：</label>
                <a class="easyui-linkbutton" icon="icon-add" onclick="danxt_add()" >增加选项</a>
                <div id="danxt_ul">
                    <ul>

                    </ul>
                </div>

            </div>
        </div>
        <div id="duoxt">
            <div  class="fitem">
                <label style="width: 60px;"  >试题答案：</label>
                <a class="easyui-linkbutton" icon="icon-add" onclick="duoxt_add()" >增加选项</a>
                <div id="duoxt_ul">
                    <ul >

                    </ul>
                </div>

            </div>
        </div>
        <div id="jdt">
            <div  class="fitem">
                <label style="width: 60px;" >试题答案：</label>
                <input class="easyui-textbox"  id="queAnswer" data-options="required:true,multiline:true" style="height:100px;width: 320px"/>
            </div>
        </div>

    </form>

</div>
<div style="padding:5px;text-align:right;" id="dlg-buttons">
    <a  id="saveBtn" class="easyui-linkbutton" icon="icon-ok">确定</a>
    <a  id="cancelBtn" class="easyui-linkbutton" icon="icon-cancel">取消</a>
</div>--%>

<script type="text/javascript" src="/static/jquery/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="/static/js/head.js"></script>
<script type="text/javascript" src="/modules/fbd/hrm/exam/js/exam-question.js"></script>

</body>
</html>