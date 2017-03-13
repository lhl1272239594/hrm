<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%
        String path = request.getContextPath();
        String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
    Object obj = session.getAttribute("LoginInfo");
    if(obj==null)
        {
%>
<script>

        window.location.href="/modules/sys/login.html";
        window.close();
</script>
<%
    }
%>
<!DOCTYPE html>
<!-- saved from url=(0021)http://www.baidu.com/ -->
<html>
<head>
    <meta charset="UTF-8">
    <title>人力资源系统</title>

   <link rel="stylesheet" type="text/css" href="/static/css/index.css">

  <link rel="stylesheet" type="text/css" href="/static/easyui/css/default/easyui.css">

    <link rel="stylesheet" type="text/css" href="/static/easyui/css/icon.css">


    <link rel="stylesheet" type="text/css" href="/modules/fbd/hrm/css/hrm.css">

    <script type="text/javascript" src="/static/jquery/jquery-1.11.3.min.js"></script>
    <script type="text/javascript" src="/static/js/head.js"></script>
    <script type="text/javascript" src="/modules/sys/js/template.js"></script>
    <script type="text/javascript" src="/modules/fbd/hrm/js/hrm.js"></script>
</head>


<body class="easyui-layout">
<!-- 北 -->
<div data-options="region:'north',border:false" style="height: 50px; border: 0px; padding: 0px; overflow: hidden;">
    <div class="accordion" style="text-align: right; height: 100%; background-color: #438EB9;">
        <div style="float: left"><img src="/static/images/index/logo.png" alt=""/></div>
        <div class="navbar-menu">
            <ul class="nav navbar-nav">
                <li class="dropdown messages-menu" id="userName">
                </li>
                <li class="dropdown messages-menu">
                    <a href="#" class="btn btn-primary btn-sm" id="exit">退出</a>
                </li>
            </ul>
        </div>
    </div>

</div>
<!-- 西 -->
<div data-options="region:'west',title:'系统导航',split:true" style="width:180px;" id="west">
</div>
<div data-options="region:'center'">
    <div class="easyui-tabs" data-options="fit:true" id="mainContent">
        <div title="首页" style="padding:10px">
            <div id="container" region="center">
                <div class="container-left">
                    <div id="schedule" class="w66 groupWrapper">
                        <div class="groupItem widget-tab">
                            <div class="itemHeader">
                                <span><img src="../../../../static/images/index/icon-workflow.png"></span>
                                <span>代办事项</span>
                                <div class="action" style="display: none;">
                                    <a href="javascript:void(0);" class="refresh" title="刷新"></a>
                                    <a href="javascript:void(0);" class="min" title="收起" style="display: block;"></a>
                                    <a href="javascript:void(0);" class="max" title="展开" style="display: none;"></a>
                                    <a href="javascript:void(0);" class="close" title="关闭"></a>
                                </div>
                            </div>
                            <div class="itemContent">
                                <div class="swiper-container">
                                </div>
                            </div>
                        </div>
                    </div>
                    <div id="iconLink" class="w66 groupWrapper">
                        <div class="groupItem widget-tab">
                            <div class="itemHeader">
                                <span><img src="../../../../static/images/index/H5-widget-icon05.png"></span>
                                <span>快捷方式</span>
                                <div class="action" style="display: none;">
                                    <a href="javascript:void(0);" class="refresh" title="刷新"></a>
                                    <a href="javascript:void(0);" class="min" title="收起" style="display: block;"></a>
                                    <a href="javascript:void(0);" class="max" title="展开" style="display: none;"></a>
                                    <a href="javascript:void(0);" class="close" title="关闭"></a>
                                </div>
                            </div>
                            <div class="itemContent">
                                <div class="swiper-container" style="overflow: auto">

                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="container-right">
                    <div id="notice" class="w66 groupWrapper">
                        <div class="groupItem widget-tab">
                            <div class="itemHeader">
                                <span><img src="../../../../static/images/index/H5-widget-icon02.png"></span>
                                <span>公告通知</span>
                                <div class="action" style="display: none;">
                                    <a href="javascript:void(0);" class="refresh" title="刷新"></a>
                                    <a href="javascript:void(0);" class="min" title="收起" style="display: block;"></a>
                                    <a href="javascript:void(0);" class="max" title="展开" style="display: none;"></a>
                                    <a href="javascript:void(0);" class="close" title="关闭"></a>
                                </div>
                            </div>
                            <div class="itemContent">
                                <div class="swiper-container">
                                    <ul style="padding:10px;margin:0px;">
                                        <div class="widgetContent" >
                                            <table class="viewTable"
                                                   style="display: table; width: 100%;">
                                                <tbody>
                                                <tr class="header widgetItem">
                                                    <td style="font-weight: bold;">标题
                                                    </td>
                                                    <td style="font-weight: bold;">日期
                                                    </td>
                                                </tr>


                                                </tbody>
                                            </table>
                                        </div>

                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div id="myData" class="w66 groupWrapper">
                        <div class="groupItem widget-tab">
                            <div class="itemHeader">
                                <span><img src="../../../../static/images/index/H5-widget-icon06.png"></span>
                                <span>信息统计</span>
                                <div class="action" style="display: none;">
                                    <a href="javascript:void(0);" class="refresh" title="刷新"></a>
                                    <a href="javascript:void(0);" class="min" title="收起" style="display: block;"></a>
                                    <a href="javascript:void(0);" class="max" title="展开" style="display: none;"></a>
                                    <a href="javascript:void(0);" class="close" title="关闭"></a>
                                </div>
                            </div>
                            <div class="itemContent">
                                <div class="swiper-container" style="overflow: auto">

                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

        </div>
    </div>
</div>
<%--<!-- 新增招聘编辑框end -->
<div id="noticeWin" class="easyui-window" title="公告详情" data-options="modal:true,closed:true,iconCls:'icon-tip'" style="width:900px;height:600px;padding:10px;">
    <div class="easyui-tabs" id="tabs" style="width:auto;height:100%;">
    </div>
</div>--%>

<div id="paramDialog"></div>
<div id="msg" style="height:160px;width:240px;display: none" data-options="modal:true,footer:'#dlg-buttons'">
    <br><div style="text-indent: 2em;font-size: 14px">登录超时，请重新登录！</div>
</div>
<div id="dlg-buttons" style="padding:5px;text-align:right;">
    <a href="javascript:void(0)" id="quit" class="easyui-linkbutton c6" iconCls="icon-ok">确定</a>
</div>
<input type="hidden" id="hospitalId" value="1">

</body>

</html>
