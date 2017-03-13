<%--
  Created by IntelliJ IDEA.
  User:
  Date: 2016/11/3
  Time: 13:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>人员综合查询</title>
    <%@ include file="/static/include/init.html" %>
    <%--<link rel="stylesheet" href="/static/uploadify/uploadify.css" type="text/css"/>--%>

</head>
<body class="easyui-layout">
<style type="text/css" rel="stylesheet">
    .fitem {
        padding: 10px 20px 10px 20px;
        line-height:27px;
        /*line-height:17px;*/
    }
    .info {
        color: #ff4f04;
    }
</style>
<!--科室信息  start-->
<div id=examClass"  data-options="region:'west'"  style="width:15%;height:100%;float: left;overflow-y:auto;">
    <table id="staff" class="easyui-datagrid">

    </table>
</div>

<!--科室信息  end-->


<!--组织机构人员维护  start-->
<div id="examSubclass" data-options="region:'center'" style="width:85%;height:100%;float:left;">
    <table id="staffGrid" class="easyui-datagrid">

    </table>
</div>
<div id="ft" style="padding:5px;display: none">
    姓名：
    <input id="NAME_SEARCH" class="easyui-textbox" style="width:80px;">
    身份证号：
    <input id="CARD_NUM" class="easyui-textbox" style="width:130px;">
    <%-- 科室:
     <select id="DEPT" class="easyui-combobox" panelHeight="auto" style="width:100px">
        &lt;%&ndash; <option value="java">全部</option>
         <option value="java">骨科</option>
         <option value="c">内科</option>
         <option value="basic">神经科</option>&ndash;%&gt;
     </select>--%>
    人员分类：
    <select id="CLASSIFY" class="easyui-combobox"  editable="false" style="width:100px">
        <%--<option value="java">全部</option>
        <option value="java1">男</option>
        <option value="c">女</option>
        <option value="c1">未知</option>--%>
    </select>
    人员状态：
    <select id="TYPE" class="easyui-combobox"  editable="false" style="width:100px">

    </select>
    民族：
    <select id="NATION_SEARCH" class="easyui-combogrid"  editable="false" style="width:80px">

    </select>
    工资级别：
    <select id="SALARY_LEVEL" class="easyui-combobox"  editable="false" style="width:80px" >

    </select>
    <br/>
    职称类别：
    <select id="TITLE_SEARCH" class="easyui-combobox"  editable="false" style="width:100px" >

    </select>
    职称级别：
    <select id="TITLE_LEVEL_SEARCH" class="easyui-combobox"  editable="false" style="width:80px" >

    </select>
    原始学历：
    <select id="EDUCATION" class="easyui-combobox"  editable="false" style="width:80px">

    </select>
    最终学历：
    <select id="EDUCATION_FINAL" class="easyui-combobox"  editable="false" style="width:80px">

    </select>
    人员技能：
    <select id="SKILL_SEARCH" class="easyui-combobox"  editable="false" style="width:80px">

    </select>
    技能等级：
    <select id="SKILL_LEVEL_SEARCH" class="easyui-combobox"  editable="false" style="width:80px">

    </select>
    <br/>
    职务：
    <select id="ROLE_SEARCH" class="easyui-combobox"  editable="false" style="width:80px">
    </select>
    政治面貌：
    <select id="POLITIC_SEARCH" class="easyui-combobox"  editable="false" style="width:80px">

    </select>
    性别：
    <select id="SEX_SEARCH" class="easyui-combobox"  editable="false" style="width:80px">
        <%--<option value="java">全部</option>
        <option value="java1">男</option>
        <option value="c">女</option>
        <option value="c1">未知</option>--%>
    </select>
    婚姻状况：
    <select id="MARRY" class="easyui-combobox"  editable="false" style="width:80px">
        <option value="">全部</option>
        <option value="0">未婚</option>
        <option value="1">已婚</option>
    </select>
    年龄：
    <input id="AGE1" class="easyui-numberspinner" style="width:60px;"
           data-options="min:1,max:120,editable:true">
    至：
    <input id="AGE2" class="easyui-numberspinner" style="width:60px;"
           data-options="min:1,max:120,editable:true">
    <span class="info" id="TOTAL" style="font-size: 14px;font-weight: bold"></span>
    <a id="searchBtn" class="easyui-linkbutton" iconCls="icon-search">查询</a>
    <a id="clearBtn" class="easyui-linkbutton" iconCls="icon-cancel">清空</a>
    <a id="searchAllBtn" class="easyui-linkbutton" iconCls="icon-search">全部人员</a>
    <a id="infoBtn" class="easyui-linkbutton" data-options="iconCls:'icon-save'">详细信息</a>
</div>
<div id="w" class="easyui-window" title="详细信息" data-options="modal:true,closed:true,iconCls:'icon-save'" style="width:650px;height:480px;padding:10px;display: none">
    <table id="dg" style="width:100%;height:auto;">
        <tbody>
        <tr>
            <td width="70px"><strong>姓&nbsp;&nbsp;名：</strong></td>
            <td><input class="easyui-textbox" id="name1" style="width:140px" editable="false"></td>
            <td width="70px"><strong>性&nbsp;&nbsp;别：</strong></td>
            <td><input class="easyui-textbox" style="width:140px" id="sex1" editable="false"></td>
            <td rowspan="7"><img id="img" src="" onerror="javascript:this.src='/modules/fbd/hrm/peinfo/js/img.png';" width="124" height="176" alt=""/>&nbsp;<!--<a id="btn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add'">上传形象照片</a>-->
            </td>
        </tr>
        <tr>
            <td><strong>出生日期：</strong></td>
            <td><input class="easyui-textbox" id="age11" style="width:140px" editable="false"></td>
            <td><strong>民&nbsp;&nbsp;族：</strong></td>
            <td><input class="easyui-textbox" id="nation1" style="width:140px" editable="false"></td>
        </tr>
        <tr>
            <td><strong>科&nbsp;&nbsp;室：</strong></td>
            <td><input class="easyui-textbox" id="dept1" style="width:140px" editable="false"></td>
            <td><strong>身份证号：</strong></td>
            <td><input class="easyui-textbox" id="card_no1" style="width:140px" editable="false"></td>
        </tr>
        <tr>
            <td><strong>职&nbsp;&nbsp;务：</strong></td>
            <td><input class="easyui-textbox" style="width:140px" id="roleName1" editable="false"></td>
            <td><strong>政治面貌：</strong></td>
            <td><input class="easyui-textbox" style="width:140px" id="politic1" editable="false"></td>
        </tr>
        <tr>
            <td><strong>职称类别：</strong></td>
            <td><input class="easyui-textbox" style="width:140px" id="title1" editable="false"></td>
            <td><strong>职称级别：</strong></td>
            <td><input class="easyui-textbox" style="width:140px" id="titleLevel1" editable="false"></td>
        </tr>
        <tr>
            <td><strong>人员分类：</strong></td>
            <td><input class="easyui-textbox" style="width:140px" id="classify1" editable="false"></td>
            <td><strong>人员状态：</strong></td>
            <td><input class="easyui-textbox" style="width:140px" id="type1" editable="false"></td>
        </tr>
        <tr>
            <td><strong>人员技能：</strong></td>
            <td><input class="easyui-textbox" style="width:140px" id="skill1" editable="false"></td>
            <td><strong>技能等级：</strong></td>
            <td><input class="easyui-textbox" style="width:140px" id="skillLevel1" editable="false"></td>
        </tr>
        <tr>
            <td><strong>原始学历：</strong></td>
            <td><input class="easyui-textbox" style="width:140px" id="education1" editable="false"></td>
            <td><strong>获得时间：</strong></td>
            <td><input class="easyui-textbox" style="width:140px" id="education_time1" editable="false"></td>
        </tr>
        <tr>
            <td><strong>最终学历：</strong></td>
            <td><input class="easyui-textbox" style="width:140px" id="education_final1" editable="false"></td>
            <td><strong>获得时间：</strong></td>
            <td><input class="easyui-textbox" style="width:140px" id="education_final_time1" editable="false"></td>
        </tr>

        <tr>
            <td><strong>工作时间：</strong></td>
            <td><input class="easyui-textbox" style="width:140px" id="work_time1" editable="false"></td>
            <td><strong>婚姻状况：</strong></td>
            <td><input class="easyui-textbox" style="width:140px" id="marry1" editable="false"></td>
            <%--<td><strong>来院工作时间：</strong></td>
            <td><input class="easyui-textbox" style="width:140px" id="come_time1" editable="false"></td>--%>
        </tr>
        <tr>
            <td><strong>联系方式：</strong></td>
            <td><input class="easyui-textbox" type="text" id="tel1" style="width:140px" editable="false"></td>
            <td><strong>电子邮件：</strong></td>
            <td><input class="easyui-textbox" type="text" id="email1" style="width:140px" editable="false"></td>
        </tr>
        </tbody>
    </table>

    <table id="dg1" style="width:100%;height:auto;">
        <tbody>
        <tr>
            <td width="70px"><strong>目前住址：</strong></td>
            <td><input class="easyui-textbox" type="text" id="address" editable="false" style="width:522px;height:27px;margin-top: 5px"></td>

        </tr>
        <tr height="3px">
        </tr>
        <tr>
            <td width="70px"><strong>工作经历：</strong></td>
            <table id="expGrid1"  　class="easyui-datagrid"></table>
        </tr>
        <%--<tr>
            <td width="70px"><strong>工作经历：</strong></td>
            &lt;%&ndash;<td><input class="easyui-textbox" type="text" name="name" style="width:598px;height:80px" data-options="required:true" value="在病房工作10年余，先后二次进修学习，对内。儿。外科都有丰富的诊治经验，能独立完成普外科各种手术相关技能：在病房工作10年余，先后二次进修学习，对内。儿。外科都有丰富的诊治经验，能独立完成普外科各种手术"></td>
            &ndash;%&gt;<td colspan="4"><textarea id="exp" editable="false" class="easyui-validatebox validatebox-text validatebox-textarea"
                                          style="width:528px;height:80px;"></textarea></td>
        </tr>--%>
        <tr height="3px">
        </tr>
        <tr style="padding-top: 5px">
            <td width="70px"><strong>社会关系：</strong></td>
            <table id="relGrid1"  　class="easyui-datagrid"></table>
        </tr>
        <%--<tr>
            <td width="70px"><strong>备&nbsp;&nbsp;注：</strong></td>
            <td colspan="4"><textarea id="remark" editable="false"  class="easyui-validatebox validatebox-text validatebox-textarea"
                                      style="width:528px;height:80px;"></textarea></td>
        </tr>--%>
        </tbody>
    </table>
</div>
<!--组织机构人员维护  end-->

<script type="text/javascript" src="/static/easyui/js/datagrid-detailview.js"></script>
<script type="text/javascript" src="/modules/fbd/hrm/peinfo/js/humaninfo.js"></script>

<script>
    window.onload = function () {
        new uploadPreview({ UpBtn: "up_img", DivShow: "imgdiv", ImgShow: "imgShow" });
    }
</script>
</body>
</html>
