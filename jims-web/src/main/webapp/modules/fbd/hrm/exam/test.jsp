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
    <title>在线考试</title>
    <link rel="stylesheet" type="text/css" href="/static/easyui/css/bootstrap/easyui.css">
    <link href="/static/css/exam/style.css" rel="stylesheet" type="text/css" />
    <link href="/static/css/exam/layout.css" rel="stylesheet" type="text/css" />
    <script src="/static/css/exam/maxheight.js" type="text/javascript"></script>
    <link rel="stylesheet" type="text/css" href="/modules/fbd/hrm/css/public.css">
</head>
<body class="easyui-layout">
<style type="text/css" rel="stylesheet">
    #testWin {
        overflow: scroll;
    }
    .info {
        line-height: 26px;
    }
    textarea {
        font-size:16px;
    }
</style>

<div  data-options="region:'center'" style="padding:1px;">
    <table id="testGrid" class="easyui-datagrid">

    </table>
</div>
<div id="tb" >

    <a  id="testBtn" class="easyui-linkbutton" data-options="iconCls:'icon-add'">进入考试</a>
</div>
<div id="infoWin"  data-options="modal:true,footer:'#info-buttons',closed: true"  title="考试说明">
    <textarea id="info" style="resize:none;font-size: 15px;width: 90%;height: 90%;border:0px solid black;overflow:hidden;margin:5px;" class="info" ></textarea>

</div>
<div style="padding:5px;text-align:right;" id="info-buttons">
    <a  id="testConfirmBtn" class="easyui-linkbutton" icon="icon-ok">确定</a>
    <a  id="cancelBtn" class="easyui-linkbutton" icon="icon-cancel">取消</a>
</div>

<div id="testWin" style="display: none">
    <form id="form1" method="post" >

        <!-- content -->
        <div style="height:140px;width: 100%">
            <div id="queContent">
                <div class="main-banner-small">
                    <div id="title" class="inner">
                        <h1></h1>
                        <div style="position:absolute;right:0;top:0;line-height:40px">
                            <a id="submitExam" href="###" class="test-submitBtn" style="color: white" onclick="submitValidate(); return false;">我要交卷</a>
                            <div style="background:#0d8b29;width:240px;float:right">
                                <div id="remainTime" style="font-size:14px;color:#fff;text-align:center"></div>
                            </div>
                        </div>

                    </div>

                </div>
                <!--答题卡-->
                <div class="question-card">
                    <div class="question-card-title">
                        <ul id="card_title">
                        </ul>
                    </div>
                    <div class="question-card-detail">
                        <div id="index_pdt" class="question-card-detail_index">
                        </div>
                        <div id="index_danxt" class="question-card-detail_index">
                        </div>
                        <div id="index_duoxt" class="question-card-detail_index">
                        </div>
                        <div id="index_jdt" class="question-card-detail_index">
                        </div>

                    </div>
                </div>
            </div>
        </div>
        <div class="container">
            <!-- main-banner-small begin -->

            <!-- main-banner-small end -->

            <div class="section">
                <!-- box begin -->

            </div>
            <div class="section-button">
                <!-- box begin -->
                <input type="button" value="<<上一题"  class="leftButton">
                <input type="button" value="下一题>>"  class="rightButton">

            </div>
        </div>
   </form>
</div>
<script type="text/javascript" src="/static/jquery/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="/static/js/head.js"></script>
<script type="text/javascript" src="/modules/fbd/hrm/exam/js/test.js"></script>


</body>
</html>