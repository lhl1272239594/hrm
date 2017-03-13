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
    <link rel="stylesheet" type="text/css" href="/modules/fbd/hrm/css/public.css">
</head>
<body class="easyui-layout">
<style type="text/css" rel="stylesheet">
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

    <table id="questionGrid" >

    </table>

<div id="tb">
    试题分类：
    <input id="itemTree" class="easyui-combotree combox_width"  editable="false"/>
    题型：
    <input class="easyui-combobox combox_width"   panelHeight="auto" id="type" editable="false"/>
    状态：
    <select id="state" class="easyui-combobox combox_width" panelHeight="auto"  editable="false">

    </select>
    <a id="searchBtn" class="easyui-linkbutton" iconCls="icon-search">查询</a>
    <a id="clearBtn" class="easyui-linkbutton" iconCls="icon-cancel">清空</a>
    <br>
    <a  id="addBtn" class="easyui-linkbutton" data-options="iconCls:'icon-add'">新增</a>
    <a  id="editBtn" class="easyui-linkbutton" data-options="iconCls:'icon-edit'">修改</a>
    <a  id="delBtn" class="easyui-linkbutton" data-options="iconCls:'icon-remove'">删除</a>
    <a  id="okBtn" class="easyui-linkbutton" data-options="iconCls:'icon-ok'">启用</a>
    <a  id="noBtn" class="easyui-linkbutton" data-options="iconCls:'icon-no'">停用</a>
    <a  id="infoBtn" class="easyui-linkbutton" data-options="iconCls:'icon-tip'">查看</a>
    <a  id="importXls" class="easyui-linkbutton" data-options="iconCls:'icon-edit'">导入试题</a>
</div>
<!--添加试题  start-->
<div id="addQuestion"  style="height: 500px;width:500px;display: none"   data-options="modal:true,footer:'#dlg-buttons'">


    <form id="queForm" data-options="fit:true" method="post">
        <input type="hidden" id="queId" />
        <div class="fitem">
            <label style="width: 60px;">试题分类：</label>
            <input id="itemTree1" editable="false" class="easyui-combotree" style="width:320px;height:27px" />
        </div>

        <div class="fitem">
            <label style="width: 60px;" >试题题型：</label>
            <input class="easyui-combobox"  panelHeight="auto"  id="type1"  style="width:120px;height:27px" editable="false"/>

        </div>

        <div  class="fitem">
            <label style="width: 60px;" >试题题目：</label>
            <input class="easyui-textbox"  id="queName" data-options="multiline:true" style="height:100px;width: 320px"/>
        </div>

        <div id="pdt">
            <div  class="fitem">
                <label style="width: 60px;"  >试题答案：</label>
                <span id="pdt_answer" class="radioSpan" >
                    <input type="radio" style="vertical-align:middle; margin-top:-2px; margin-bottom:1px;" name="pdt_flag" value="1"/> 对
                    <input type="radio" style="vertical-align:middle; margin-top:-2px; margin-bottom:1px;" name="pdt_flag" value="0"/> 错
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
                <textarea id="queAnswer"  class="easyui-validatebox validatebox-text validatebox-textarea"
                          style="width:320px;height:100px;"></textarea>
                <%--<input class="easyui-textbox"  id="queAnswer" data-options="multiline:true" style="height:100px;width: 320px"/>--%>
            </div>
        </div>

    </form>

</div>
<div style="padding:5px;text-align:right;" id="dlg-buttons">
    <a  id="saveBtn" class="easyui-linkbutton" icon="icon-ok">保存</a>
    <a  id="cancelBtn" class="easyui-linkbutton" icon="icon-cancel">取消</a>
</div>
<!--查看试题  start-->
<div id="infoQuestion"  style="height: 500px;width:500px;display: none"   data-options="modal:true">

        <div class="fitem">
            <label style="width: 60px;">试题分类：</label>
            <input id="itemName" editable="false" class="easyui-textbox" style="width:320px;height:27px" />
        </div>

        <div class="fitem">
            <label style="width: 60px;" >试题题型：</label>
            <input class="easyui-textbox"  panelHeight="auto"  id="type2"  style="width:120px;height:27px" editable="false"/>

        </div>

        <div  class="fitem">
            <label style="width: 60px;" >试题题目：</label>
            <input class="easyui-textbox"  id="queName1" data-options="multiline:true" style="height:100px;width: 320px"/>
        </div>

        <div id="pdt1">
            <div  class="fitem">
                <label style="width: 60px;"  >试题答案：</label>
                <span id="pdt_answer1" class="radioSpan" >
                    <input type="radio" style="vertical-align:middle; margin-top:-2px; margin-bottom:1px;" name="pdt_flag1" value="1" disabled="disabled"/> 对
                    <input type="radio" style="vertical-align:middle; margin-top:-2px; margin-bottom:1px;" name="pdt_flag1" value="0" disabled="disabled"/> 错
                </span>
            </div>
        </div>
        <div id="danxt1">
            <div  class="fitem">
                <label style="width: 60px;"  >试题答案：</label>
                <div id="danxt_ul1">
                    <ul>

                    </ul>
                </div>

            </div>
        </div>
        <div id="duoxt1">
            <div  class="fitem">
                <label style="width: 60px;"  >试题答案：</label>
                <div id="duoxt_ul1">
                    <ul >

                    </ul>
                </div>

            </div>
        </div>
        <div id="jdt1">
            <div  class="fitem">
                <label style="width: 60px;" >试题答案：</label>
                <textarea id="queAnswer1"  class="easyui-validatebox validatebox-text validatebox-textarea"
                          style="width:320px;height:100px;"></textarea>
            </div>
        </div>

</div>
<div id="importWin"  style="height:200px;width:400px;"   data-options="modal:true,footer:'#xls-buttons'">
    <form id="importForm" action='' method='post'/>
    <div  class="fitem">
        <label style="width: 60px;" >数据模版：</label>

        <input  class="easyui-linkbutton" id="exportXls" type='submit' value='点击下载'  style="width:200px;height:27px"/>
    </div>
    </form>
    <form id="editForm" method="post" enctype="multipart/form-data" name="editForm">
        <table class="editTable">
            <div  class="fitem" style="margin-top: 10px;">
                <label style="width: 60px;">数据导入：</label>
                <input   type="file" id="myFiles"  name="myFiles"   style="width:200px;height:27px"/>
            </div>
        </table>
    </form>

    <div id="xls-buttons" style="padding:5px;text-align:right;" >
        <a  id="importBtn" class="easyui-linkbutton" icon="icon-ok" >保存</a>
        <a id="exitBtn" class="easyui-linkbutton" icon="icon-cancel" href="javascript:$('#importWin').window('close');">取消</a>
    </div>
</div>
<script type="text/javascript" src="/static/jquery/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="/static/js/head.js"></script>
<script type="text/javascript" src="/modules/fbd/hrm/exam/js/question.js"></script>

</body>
</html>