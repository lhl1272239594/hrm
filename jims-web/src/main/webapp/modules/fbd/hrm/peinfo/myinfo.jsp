<%--
  Created by IntelliJ IDEA.
  User:
  Date: 2016/9/1
  Time: 10:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>我的信息</title>
    <%@ include file="/static/include/init.html" %>
    <%--<link rel="stylesheet" type="text/css" href="/static/easyui/css/bootstrap/easyui.css">
    <link rel="stylesheet" type="text/css" href="style/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="style/themes/icon.css">--%>
</head>
<body>
<style type="text/css" rel="stylesheet">
    .fitem  {
        padding: 20px 10px 10px 10px;
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
<%--<div id="p" class="easyui-panel" title="个人信息" style="width:700px;height:100%;padding:10px;">

    <table id="dg" style="width:100%;height:auto;border:1px solid #ccc;">
        <tbody>
        <tr>
            <td ><strong>姓&nbsp;&nbsp;名：</strong></td>
            <td><input class="easyui-textbox" style="width:240px;height:27px" id="name" disabled></td>
            </tr>
        <tr>

            <td><strong>性&nbsp;&nbsp;别：</strong></td>
            <td><input class="easyui-textbox" style="width:240px;height:27px" id="sex" disabled></td>
        </tr>
        <tr>
            <td><strong>民&nbsp;&nbsp;族：</strong></td>
            <td><input class="easyui-textbox" style="width:240px;height:27px" id="mz" disabled></td>
        </tr>
        <tr>
            <td><strong>证件类型：</strong></td>
            <td><input class="easyui-textbox" style="width:240px;height:27px" id="zjlx" disabled value="身份证"></td>
            </tr>
        <tr>
            <td><strong>证件编号：</strong></td>
            <td><input class="easyui-textbox" style="width:240px;height:27px" id="sfz" disabled></td>
        </tr>
        <tr>
            <td><strong>电子邮件：</strong></td>
            <td><input class="easyui-textbox" style="width:240px;height:27px" id="email" disabled></td>
        </tr>
        <tr>
            <td><strong>联系方式：</strong></td>
            <td><input class="easyui-textbox" style="width:240px;height:27px" id="dh" disabled></td>
        </tr>
        <tr>
            <td><strong>工作单位：</strong></td>
            <td><input class="easyui-textbox" style="width:240px;height:27px" id="jg" disabled></td>
            </tr>
        <tr>
            <td><strong>所属部门：</strong></td>
            <td><input class="easyui-textbox" style="width:240px;height:27px" id="bm" disabled></td>
        </tr>
        <tr>
            <td><strong>职&nbsp;&nbsp;务：</strong></td>
            <td><input class="easyui-textbox" style="width:240px;height:27px" id="zw" disabled></td>
        </tr>

        </tbody>
    </table>

</div>--%>
<div id="p" class="easyui-panel" title="个人信息" style="width:700px;height:520px;padding:10px;">
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

<%--<script type="text/javascript" src="/static/jquery/jquery-1.11.3.min.js"></script>

<script type="text/javascript" src="/static/js/head.js"></script>--%>
<script type="text/javascript" src="/modules/fbd/hrm/peinfo/js/myinfo.js"></script>

</body>
</html>
