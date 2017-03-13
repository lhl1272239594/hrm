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
    <title>工资计算公式</title>
    <link rel="stylesheet" type="text/css" href="/static/easyui/css/bootstrap/easyui.css">
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
<table id="dataGrid" data-options="onLoadSuccess:mergeCells" class="easyui-datagrid">

</table>
<div id="tb" style="padding:5px;display: none">
    <%-- 部门：
     <select id="ORGID" class="easyui-combobox" panelHeight="auto" data-options="editable:false" style="width:140px">
         <option value="骨科">骨科</option>
         <option value="神经科">神经科</option>
         <option value="放射科">放射科</option>
         <option value="儿科">儿科</option>
     </select>--%>
    工资级别：
    <select id="TYPE_NAME" class="easyui-combobox" panelHeight="auto" data-options="editable:false" style="width:140px">

    </select>
    工资组成：
    <select id="PART_NAME2" class="easyui-combobox" panelHeight="auto" data-options="editable:false" style="width:140px">

    </select>
    <a id="searchBtn" class="easyui-linkbutton" iconCls="icon-search">查询</a>
    <a id="clearBtn" class="easyui-linkbutton" iconCls="icon-cancel">清空</a>
    <br/>
    <a  id="addBtn" class="easyui-linkbutton" iconCls="icon-add">新增</a>
    <a  id="delBtn" class="easyui-linkbutton" iconCls="icon-remove">删除</a>
    <a  id="okBtn" class="easyui-linkbutton" data-options="iconCls:'icon-ok'">启用</a>
    <a  id="noBtn" class="easyui-linkbutton" data-options="iconCls:'icon-no'">停用</a>
    <%-- <a  id="editBtn" class="easyui-linkbutton" iconCls="icon-edit">复制</a>--%>
</div>
<!-- 新增编辑框start -->
<div id="newDialog" style="height:auto;width:auto;display:none;overflow: auto" data-options="modal:true,footer:'#dlg-buttons'">
    <form id="newForm" method="post" data-options="fit:true">
        <input type="hidden" id="CONTENT_ID" />
        <div style="margin-left: 15px;margin-right: 15px;margin-top: 10px">
            <input id="sumValue"  type="hidden"/>
            <%--<fieldset style="border-color: #eaf2ff">
                <legend>公式内容</legend>
                <div style="margin-left: 12px;margin-right: 5px;margin-top: 5px;margin-bottom: 8px">
                    <input class="easyui-textbox"  id="sumText" data-options="multiline:true,readonly:true"  style="height:100px;width: 438px;"/>
                    <br/>
                    <input  id="sumValue" type="hidden"/>

                </div>
            </fieldset>--%>
        </div>
        <div style="margin-left: 10px;margin-right: 10px;margin-top: 10px">
            <table>
                <tr>

                    <td style="width: 220px">
                        <fieldset style="border-color: #eaf2ff">
                            <legend>工资级别：</legend>
                            <div style="margin-left: 15px;margin-right: 15px;margin-top: 6px;margin-bottom: 8px">
                                <select name="selectType" id="selectType" onclick="onSelect1(this.value)" multiple="multiple" panelHeight="130px"   style="width:180px;height: 120px">
                                </select>
                            </div>
                        </fieldset>
                    </td>
                    <td style="width: 10px"></td>
                    <td style="width: 220px">
                        <fieldset style="border-color: #eaf2ff">
                            <legend>工资组成部分：</legend>
                            <div style="margin-left: 15px;margin-right: 0px;margin-top: 6px;margin-bottom: 8px">
                                <select name="selectPart" id="selectPart" onclick="onSelect3(this.value)" multiple="multiple" panelHeight="130px"   style="width:180px;height: 120px">
                                    <%--<option value="B877532D9D3E4F7CBA1E4E9D80004BF4">基础工资</option>
                                    <option value="D662241598DC481D8997B21BB3E1253D">津贴工资</option>
                                    <option value="9CE4C58370CF443C859E4224C7714C92">社保</option>--%>
                                </select>
                            </div>
                        </fieldset>
                    </td>

                    <td style="width: 10px"></td>
                    <td style="width: 220px">
                        <fieldset style="border-color: #eaf2ff">
                            <legend>项目列表：</legend>
                            <div style="margin-left: 15px;margin-right: 15px;margin-top: 6px;margin-bottom: 8px">
                                <select name="select1" id="select1" multiple="multiple" onclick="onSelect(this.value,this.options[this.selectedIndex].text)" style="width:180px;height:120px">


                                </select>
                            </div>
                        </fieldset>

                    </td>
                </tr>
                <tr style="height: 8px"></tr>
                <tr>



                    <td style="width: 230px">
                        <fieldset style="border-color: #eaf2ff">
                            <legend>运算符号：</legend>
                            <div id="editor" style="margin-left: 25px;margin-right: 20px;margin-top: 0px;margin-bottom: 8px">
                                <a  id="button_1" style="width: 38px;height: 30px" class="easyui-linkbutton" iconCls="" onclick="onButton(1)">&nbsp;<font style="font-size: 16px">1</font>&nbsp;</a>
                                <a  id="button_2" style="width: 38px;height: 30px" class="easyui-linkbutton" iconCls="" onclick="onButton(2)">&nbsp;<font style="font-size: 16px">2</font>&nbsp;</a>
                                <a  id="button_3" style="width: 38px;height: 30px" class="easyui-linkbutton" iconCls="" onclick="onButton(3)">&nbsp;<font style="font-size: 16px">3</font>&nbsp;</a>
                                <a  id="button_4" style="width: 38px;height: 30px" class="easyui-linkbutton" iconCls="" onclick="onButton('+')">&nbsp;<font style="font-size: 16px">+</font>&nbsp;</a>
                                <br/>
                                <a  id="button_5" style="width: 38px;height: 30px" class="easyui-linkbutton" iconCls="" onclick="onButton(4)">&nbsp;<font style="font-size: 16px">4</font>&nbsp;</a>
                                <a  id="button_6" style="width: 38px;height: 30px" class="easyui-linkbutton" iconCls="" onclick="onButton(5)">&nbsp;<font style="font-size: 16px">5</font>&nbsp;</a>
                                <a  id="button_7" style="width: 38px;height: 30px" class="easyui-linkbutton" iconCls="" onclick="onButton(6)">&nbsp;<font style="font-size: 16px">6</font>&nbsp;</a>
                                <a  id="button_8" style="width: 38px;height: 30px" class="easyui-linkbutton" iconCls="" onclick="onButton('-')">&nbsp;<font style="font-size: 16px">-</font>&nbsp;</a>
                                <br/>
                                <a  id="button_9" style="width: 38px;height: 30px" class="easyui-linkbutton" iconCls="" onclick="onButton(7)">&nbsp;<font style="font-size: 16px">7</font>&nbsp;</a>
                                <a  id="button_10" style="width: 38px;height: 30px" class="easyui-linkbutton" iconCls="" onclick="onButton(8)">&nbsp;<font style="font-size: 16px">8</font>&nbsp;</a>
                                <a  id="button_11" style="width: 38px;height: 30px" class="easyui-linkbutton" iconCls="" onclick="onButton(9)">&nbsp;<font style="font-size: 16px">9</font>&nbsp;</a>
                                <a  id="button_12" style="width: 38px;height: 30px" class="easyui-linkbutton" iconCls="" onclick="onButton('*')">&nbsp;<font style="font-size: 16px">*</font>&nbsp;</a>
                                <br/>
                                <a  id="button_13" style="width: 38px;height: 30px" class="easyui-linkbutton" iconCls="" onclick="onButton(0)">&nbsp;<font style="font-size: 16px">0</font>&nbsp;</a>
                                <a  id="button_14" style="width: 38px;height: 30px" class="easyui-linkbutton" iconCls="" onclick="onButton('.')">&nbsp;<font style="font-size: 16px">.</font>&nbsp;</a>
                                <a  id="button_15" style="width: 38px;height: 30px" class="easyui-linkbutton" iconCls="" onclick="onButton('C')">&nbsp;<font style="font-size: 16px">C</font>&nbsp;</a>
                                <a  id="button_16" style="width: 38px;height: 30px" class="easyui-linkbutton" iconCls="" onclick="onButton('/')">&nbsp;<font style="font-size: 16px">/</font>&nbsp;</a>
                                <br/>
                                <a  id="button_17" style="width: 38px;height: 30px" class="easyui-linkbutton" iconCls="" onclick="onButton('(')">&nbsp;<font style="font-size: 16px">(</font>&nbsp;</a>
                                <a  id="button_18" style="width: 38px;height: 30px" class="easyui-linkbutton" iconCls="" onclick="onButton(')')">&nbsp;<font style="font-size: 16px">)</font>&nbsp;</a>
                                <a  id="button_19" style="width: 82px;height: 30px" class="easyui-linkbutton" iconCls="" onclick="onButton('clearAll')"><font style="font-size: 16px">全部清除</font></a>
                            </div>
                        </fieldset>


                    </td>
                    <td style="width: 10px"></td>
                    <td style="width: 220px">
                        <fieldset style="border-color: #eaf2ff">
                            <legend>绩效考评内容：</legend>
                            <div style="margin-left: 15px;margin-right: 15px;margin-top: 6px;margin-bottom: 8px">
                                <select name="selectPerformance" id="selectPerformance" onclick="onSelect2(this.value,this.options[this.selectedIndex].text)" multiple="multiple" panelHeight="130px"  style="width:180px;height: 139px">
                                    <%--<option value="A">当月工作考评</option>
                                    <option value="B">当月绩效考评</option>
                                    <option value="C">当月自评</option>--%>
                                </select>
                            </div>
                        </fieldset>
                    </td>
                    <td style="width: 10px"></td>
                    <td style="width: 220px">
                        <fieldset style="border-color: #eaf2ff">
                            <legend>公式内容：</legend>
                            <div style="margin-left: 12px;margin-right: 5px;margin-top: 5px;margin-bottom: 8px">
                                <input class="easyui-textbox"  id="sumText" data-options="multiline:true,readonly:true"  style="height:139px;width: 180px;"/>
                                <br/>
                                <%-- <input  id="sumValue" type="hidden"/>--%>

                            </div>
                        </fieldset>
                    </td>
                </tr>
                <%--<tr style="height: 8px"></tr>
                <tr>


                </tr>--%>
                <%--tr>
                    <td>--%>
                <input  id="TYPE_NAME1" class="easyui-textbox" style="width: 0px" type="hidden"/>
                <input  id="PART_NAME" class="easyui-textbox" style="width: 0px" type="hidden"/>
                <%-- </td>
             </tr>--%>
            </table>
        </div>




    </form>

</div>
<div id="dlg-buttons" style="padding:5px;text-align:right;">
    <a href="javascript:void(0)" id="submitBtn" class="easyui-linkbutton c6" iconCls="icon-ok">保存</a>
    <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-cancel"
       onclick="$('#newDialog').dialog('close')">关闭</a>
</div>
<!-- 新增编辑框end -->




<script type="text/javascript" src="/static/jquery/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="/modules/fbd/hrm/salary/js/salary_calculate.js"></script>
<script type="text/javascript" src="/static/js/head.js"></script>


</body>
</html>
