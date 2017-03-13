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
    <title>科室人员管理(只能修改自己信息)</title>
    <link rel="stylesheet" type="text/css" href="/static/easyui/css/bootstrap/easyui.css">
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
<%--<div id=examClass"  data-options="region:'west'"  style="width:15%;height:100%;float: left;overflow-y:auto;">
    <table id="staff" class="easyui-datagrid">

    </table>
</div>--%>

<!--科室信息  end-->


<!--组织机构人员维护  start-->

    <table id="staffGrid" class="easyui-datagrid">

    </table>

<div id="ft" style="padding:5px;display: none">
   <%-- 姓名：
    <input id="NAME_SEARCH" class="easyui-textbox" style="width:80px;">
    身份证号：
    <input id="CARD_NUM" class="easyui-textbox" style="width:130px;">
   &lt;%&ndash; 科室:
    <select id="DEPT" class="easyui-combobox" panelHeight="auto" style="width:100px">
       &lt;%&ndash; <option value="java">全部</option>
        <option value="java">骨科</option>
        <option value="c">内科</option>
        <option value="basic">神经科</option>&ndash;%&gt;
    </select>&ndash;%&gt;
    人员分类：
    <select id="CLASSIFY" class="easyui-combobox"  editable="false" style="width:100px">
        &lt;%&ndash;<option value="java">全部</option>
        <option value="java1">男</option>
        <option value="c">女</option>
        <option value="c1">未知</option>&ndash;%&gt;
    </select>
    人员状态：
    <select id="TYPE" class="easyui-combobox"  editable="false" style="width:100px">

    </select>
    民族：
    <select id="NATION_SEARCH" class="easyui-combogrid"  editable="false" style="width:80px">

    </select>
    工资级别：
    <select id="SALARY_LEVEL" class="easyui-combobox"  editable="false" style="width:80px">

    </select>
    <br/>
    职称类别：
    <select id="TITLE_SEARCH" class="easyui-combobox"  editable="false" style="width:100px">

    </select>
    职称级别：
    <select id="TITLE_LEVEL_SEARCH" class="easyui-combobox"  editable="false" style="width:80px">

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
        &lt;%&ndash;<option value="java">全部</option>
        <option value="java1">男</option>
        <option value="c">女</option>
        <option value="c1">未知</option>&ndash;%&gt;
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
    <br/>
    <a id="addBtn" class="easyui-linkbutton" data-options="iconCls:'icon-add'">新增人员</a>--%>
    <a id="editBtn1" class="easyui-linkbutton" data-options="iconCls:'icon-edit'">修改人员</a>
    <%--<a id="removeBtn" class="easyui-linkbutton" data-options="iconCls:'icon-remove'">删除人员</a>
    <a id="backBtn" class="easyui-linkbutton" iconCls="icon-reload">重置密码</a>--%>
    <a id="infoBtn" class="easyui-linkbutton" data-options="iconCls:'icon-save'">详细信息</a>
    <%--<a id="exportXls" class="easyui-linkbutton" data-options="iconCls:'icon-edit'">导出</a>
    <a id="exportStaffXlsTemplate" class="easyui-linkbutton" data-options="iconCls:'icon-save'">下载导入模板</a>
    <a id="importStaffXls" class="easyui-linkbutton" data-options="iconCls:'icon-edit'">导入工作人员</a>--%>


    <!--<a  id="inBtn" class="easyui-linkbutton" data-options="iconCls:'icon-edit'">导入</a>-->

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
<!--添加组织机构人员模态  start-->
<div id="addStaff" style="height: 500px;width:620px;display: none" data-options="footer:'#addStaffFt'">
    <!--<div style="padding-top:10px;" class="fitem" id="hiddenDiv">
        <label  style="width: 150px;" for="selectCardNo"> 请输入身份证号：      </label>
        <input class="easyui-validatebox" id="selectCardNo" type="text" style="width: 320px"/>
        <a  id="select" class="easyui-linkbutton" icon="icon-search">检索</a>
        &lt;!&ndash;<a  id="saveStaff" class="easyui-linkbutton" icon="icon-ok">添加</a>&ndash;&gt;
    </div>
-->
    <form id="editForm" name="editForm" data-options="fit:true" method="post" enctype="multipart/form-data" data-ajax='false'>
        <input type="hidden" id="id"/>
        <input type="hidden" id="staffId"/>
        <input id="pic" type="hidden" editable="false"/>
        <div class="fitem">
            <label style="width: 140px;" for="name">姓&nbsp;&nbsp;名：</label>
            <input class="easyui-validatebox" id="name" style="width:316px;height:22px" type="text" />
            <span id="res-name"></span>
        </div>
        <div class="fitem">
            <label style="width: 140px;">形象照片：</label>
            <img id="imgShow" width="124" height="160" onerror="javascript:this.src='/modules/fbd/hrm/peinfo/js/img.png';"/>
            <input type="file" id="up_img" name="up_img" style="width:190px;border:0"/>
        </div>
       <%-- <div id="imgdiv"><img id="imgShow" width="124" height="176" />
        </div>--%>
       <%-- <input class="easyui-textbox" id="pic" style="width:140px;height:27px" type="text" editable="false"/>--%>

        <div class="fitem">
            <label style="width: 140px;" for="name">所属科室：</label>
            <input class="easyui-combobox" id="human_dept" style="width:320px;height:27px" editable="false"/>


            <span id="res-dept"></span>
        </div>
        <div class="fitem">
            <label style="width: 140px;" for="cardNo">身份证号：</label>
            <input class="easyui-textbox" id="cardNo" type="text" style="width:320px;height:27px"/>
            <span id="res-cardNo"></span>
        </div>
        <div class="fitem">
            <label style="width: 140px;" style="width: 150px;" for="role">职&nbsp;&nbsp;务：</label>
            <input class="easyui-combobox" id="role" type="text" style="width:320px;height:27px" editable="false"/>
            <span id="res-role"></span>
        </div>
        <div class="fitem">
            <label style="width: 140px;" for="title">职称类别：</label>
            <input class="easyui-combobox" id="title" style="width:320px;height:27px"  editable="false"/>
        </div>
        <div class="fitem">
            <label style="width: 140px;">职称级别：</label>
            <input class="easyui-combobox" id="title_level" style="width:320px;height:27px"  editable="false"/>
        </div>
        <div class="fitem">
            <label style="width: 140px;">人员分类：</label>
            <input class="easyui-combobox" style="width:320px;height:27px" id="human_classify" editable="false"/>
        </div>
        <div class="fitem">
            <label style="width: 140px;" for="human_type">人员状态：</label>
            <input class="easyui-combobox" style="width:320px;height:27px" id="human_type" editable="false"/>
        </div>
        <div class="fitem">
            <label style="width: 140px;">人员技能：</label>
            <input class="easyui-combobox" style="width:320px;height:27px" id="skill" editable="false"/>
        </div>
        <div class="fitem">
            <label style="width: 140px;">技能等级：</label>
            <input class="easyui-combobox" style="width:320px;height:27px" id="skillLevel" editable="false"/>
        </div>
        <div class="fitem">
            <label style="width: 140px;" >出生日期：</label>
            <input class="easyui-datebox" id="human_birth" style="width:320px;height:27px" editable="false"/>
        </div>
        <div class="fitem">
            <label style="width: 140px;">原始学历：</label>
            <input class="easyui-combobox" style="width:320px;height:27px" id="human_education" editable="false"/>
        </div>
        <div class="fitem">
            <label style="width: 140px;">获得时间：</label>
            <input class="easyui-datebox" style="width:320px;height:27px" id="human_education_time" editable="false"/>
        </div>
        <div class="fitem">
            <label style="width: 140px;">最终学历：</label>
            <input class="easyui-combobox" style="width:320px;height:27px" id="human_education_final" editable="false"/>
        </div>
        <div class="fitem">
            <label style="width: 140px;">获得时间：</label>
            <input class="easyui-datebox" style="width:320px;height:27px" id="human_education_final_time" editable="false"/>
        </div>
        <div class="fitem">
            <label style="width: 140px;" for="human_sex">性&nbsp;&nbsp;别：</label>
            <input class="easyui-combobox" style="width:320px;height:27px" id="human_sex" editable="false"/>
        </div>

        <div class="fitem">
            <label style="width: 140px;" for="phoneNum">联系电话：</label>
            <input class="easyui-textbox" id="phoneNum" style="width:320px;height:27px" type="text"/>
            <span id="res-phone"></span>
        </div>
        <div class="fitem">
            <label style="width: 140px;" for="email">邮&nbsp;&nbsp;箱：</label>
            <input class="easyui-textbox" id="email" style="width:320px;height:27px" type="text"/>
            <span id="res-email"></span>
        </div>
        <%--<div class="fitem">
            <label style="width: 150px;" for="nickName">昵称：</label>
            <input class="easyui-validatebox" id="nickName" style="width:320px" type="text" maxlength="12"/>
            <span id="res-nick"></span>
        </div>
        <div class="fitem" id="hiddenDiv0">
            <label style="width: 150px;" for="password">密码：</label>
            <input class="easyui-validatebox" id="password" style="width:320px" type="password"/>
            <span id="res-password"></span>
        </div>
        <div class="fitem" id="hiddenDiv1">
            <label style="width: 150px;" for="confirm-password">确认密码：</label>
            <input class="easyui-validatebox" id="confirm-password" style="width:320px" type="password"/>
            <span id="res-confirm-password"></span>
        </div>--%>

        <div class="fitem">
            <label style="width: 140px;" for="nation">民&nbsp;&nbsp;族：</label>
            <select class="easyui-combogrid" id="nation" style="width:320px;height:27px" editable="false">

            </select>
        </div>
        <div class="fitem">
            <label style="width: 140px;">政治面貌：</label>
            <select class="easyui-combobox" id="politic" style="width:320px;height:27px" editable="false">

            </select>
        </div>
        <div class="fitem">
            <label style="width: 140px;" for="nation">婚姻状况：</label>
            <select class="easyui-combobox" id="human_marry" style="width:320px;height:27px" editable="false">
                <option value=""></option>
                <option value="0">未婚</option>
                <option value="1">已婚</option>
            </select>
        </div>
        <div class="fitem">
            <label style="width: 140px;">参加工作时间：</label>
            <input class="easyui-datebox" style="width:320px;height:27px" id="work_time" editable="true"/>
        </div>
        <div class="fitem">
            <label style="width: 140px;">来院工作时间：</label>
            <input class="easyui-datebox" style="width:320px;height:27px" id="come_time" editable="true"/>
        </div>
        <div class="fitem">
            <label style="width: 140px;" for="human_address">目前住址：</label>
            <input class="easyui-textbox" id="human_address" style="width:320px;height:27px" type="text"/>
        </div>
        <div id="workExp" style="background: #d3e6e6">
            <div id="workExptb"  >
                    <div  class="fitem">
                        <%--<input type="hidden"  id="flag" />
                        <input type="hidden"  id="planId" />--%>
                            <label style="width: 140px;">工作经历：</label>
                        <a  id="addDetailBtn" class="easyui-linkbutton" data-options="iconCls:'icon-add'">新增</a>
                        <%--<a  id="saveDetailBtn" class="easyui-linkbutton" data-options="iconCls:'icon-save'">保存</a>--%>
                        <a  id="removeDetailBtn" class="easyui-linkbutton" data-options="iconCls:'icon-remove'">删除</a>
                    </div>
            </div>
            <table id="expGrid"　class="easyui-datagrid"></table>
        </div>
        <div id="socialRelationship" style="background: #d3e6e6">
            <div id="workExptb1"  >
                <div  class="fitem">
                    <%--<input type="hidden"  id="flag" />
                    <input type="hidden"  id="planId" />--%>
                        <label style="width: 140px;">社会关系：</label>
                    <a  id="addDetailBtn1" class="easyui-linkbutton" data-options="iconCls:'icon-add'">新增</a>
                    <%--<a  id="saveDetailBtn" class="easyui-linkbutton" data-options="iconCls:'icon-save'">保存</a>--%>
                    <a  id="removeDetailBtn1" class="easyui-linkbutton" data-options="iconCls:'icon-remove'">删除</a>
                </div>
            </div>
            <table id="relGrid"　class="easyui-datagrid"></table>
        </div>

        <div class="fitem" style="display: none;">
            <label style="width: 140px;" for="human_exp">工作经历：</label>
            <%--<input class="easyui-textbox" id="human_address" style="width:320px;height:27px" type="text"/>--%>
            <td colspan="4"><textarea id="human_exp"  class="easyui-validatebox validatebox-text validatebox-textarea"
                                      style="width:320px;height:80px;"></textarea></td>
        </div>
       <div class="fitem">
            <label style="width: 140px;">备&nbsp;&nbsp;注：</label>
            <td colspan="4"><textarea id="human_remark"  class="easyui-validatebox validatebox-text validatebox-textarea"
                                      style="width:320px;height:80px;"></textarea></td>
        </div>
        <%--<div class="fitem" >
            <label style="width: 140px;" for="human_remark">备&nbsp;&nbsp;注：</label>
            <input class="easyui-textbox" id="human_remark" style="width:320px;height:27px" type="text"/>
        </div>--%>

    </form>
</div>

<!--<div style="padding:5px;text-align:right;" id="addFile">
    <a  id="savBtn" class="easyui-linkbutton" icon="icon-ok">确定</a>
    <a  id="canBtn" class="easyui-linkbutton" icon="icon-cancel">取消</a>
</div>-->
<div style="padding:5px;text-align:right;display: none" id="addStaffFt">
    <a id="saveBtn" class="easyui-linkbutton" icon="icon-ok" onclick="upFile1()">确定</a>
    <a id="cancelBtn" class="easyui-linkbutton" icon="icon-cancel">取消</a>
</div>
<!--添加组织机构人员模态  end-->

<script type="text/javascript" src="/static/jquery/jquery-1.11.3.min.js"></script>
<%--<script type="text/javascript" src="/static/uploadify.css/jquery.uploadify.css.js"></script>--%>
<script type="text/javascript" src="/modules/fbd/hrm/peinfo/js/humanManage1.js"></script>
<script type="text/javascript" src="/static/js/head.js"></script>
<script type="text/javascript" src="/static/easyui/js/datagrid-detailview.js"></script>
<script>
   window.onload = function () {
        new uploadPreview({ UpBtn: "up_img", DivShow: "imgdiv", ImgShow: "imgShow" });
    }
</script>
</body>
</html>
