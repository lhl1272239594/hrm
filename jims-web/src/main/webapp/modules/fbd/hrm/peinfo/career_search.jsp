<%--
  Created by IntelliJ IDEA.
  User:
  Date: 2016/11/3
  Time: 13:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>人员综合查询</title>
    <link rel="stylesheet" type="text/css" href="/static/easyui/css/bootstrap/easyui.css">


</head>
<body class="easyui-layout">
<style type="text/css" rel="stylesheet">
    .fitem label {
        width: 60px;
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
    <input id="NAME" class="easyui-textbox" style="width:140px;">
    年份：<select class="easyui-combobox" id="YEAR" panelHeight="140px"  editable="false" style="width:140px;height:27px;">
    <option value="">&nbsp;全部</option>
    <option value="2000">&nbsp;2000</option>
    <option value="2001">&nbsp;2001</option>
    <option value="2002">&nbsp;2002</option>
    <option value="2003">&nbsp;2003</option>
    <option value="2004">&nbsp;2004</option>
    <option value="2005">&nbsp;2005</option>
    <option value="2006">&nbsp;2006</option>
    <option value="2007">&nbsp;2007</option>
    <option value="2008">&nbsp;2008</option>
    <option value="2009">&nbsp;2009</option>
    <option value="2010">&nbsp;2010</option>
    <option value="2011">&nbsp;2011</option>
    <option value="2012">&nbsp;2012</option>
    <option value="2013">&nbsp;2013</option>
    <option value="2014">&nbsp;2014</option>
    <option value="2015">&nbsp;2015</option>
    <option value="2016">&nbsp;2016</option>
    <option value="2017">&nbsp;2017</option>
    <option value="2018">&nbsp;2018</option>
    <option value="2019">&nbsp;2019</option>
    <option value="2020">&nbsp;2020</option>
    <option value="2021">&nbsp;2021</option>
    <option value="2022">&nbsp;2022</option>
    <option value="2023">&nbsp;2023</option>
    <option value="2024">&nbsp;2024</option>
    <option value="2025">&nbsp;2025</option>
    <option value="2026">&nbsp;2026</option>
    <option value="2027">&nbsp;2027</option>
    <option value="2028">&nbsp;2028</option>
    <option value="2029">&nbsp;2029</option>
    <option value="2030">&nbsp;2030</option>
    <option value="2031">&nbsp;2031</option>
    <option value="2032">&nbsp;2032</option>
    <option value="2033">&nbsp;2033</option>
    <option value="2034">&nbsp;2034</option>
    <option value="2035">&nbsp;2035</option>
    <option value="2036">&nbsp;2036</option>
    <option value="2037">&nbsp;2037</option>
    <option value="2038">&nbsp;2038</option>
    <option value="2039">&nbsp;2039</option>
    <option value="2040">&nbsp;2040</option>
    <option value="2041">&nbsp;2041</option>
    <option value="2042">&nbsp;2042</option>
    <option value="2043">&nbsp;2043</option>
    <option value="2044">&nbsp;2044</option>
    <option value="2045">&nbsp;2045</option>
    <option value="2046">&nbsp;2046</option>
    <option value="2047">&nbsp;2047</option>
    <option value="2048">&nbsp;2048</option>
    <option value="2049">&nbsp;2049</option>
    <option value="2050">&nbsp;2050</option>
</select>
    月份：<select class="easyui-combobox" id="MONTH" panelHeight="140px"  editable="false" style="width:140px;height:27px;">
    <option value="">&nbsp;全部</option>
    <option value="01">&nbsp;1</option>
    <option value="02">&nbsp;2</option>
    <option value="03">&nbsp;3</option>
    <option value="04">&nbsp;4</option>
    <option value="05">&nbsp;5</option>
    <option value="06">&nbsp;6</option>
    <option value="07">&nbsp;7</option>
    <option value="08">&nbsp;8</option>
    <option value="09">&nbsp;9</option>
    <option value="10">&nbsp;10</option>
    <option value="11">&nbsp;11</option>
    <option value="12">&nbsp;12</option>
</select>
    <a id="searchBtn" class="easyui-linkbutton" iconCls="icon-search">查询</a>
    <a id="clearBtn" class="easyui-linkbutton" iconCls="icon-cancel">清空</a>
    <a id="searchAllBtn" class="easyui-linkbutton" iconCls="icon-search">全部数据</a>


</div>

<script type="text/javascript" src="/static/jquery/jquery-1.11.3.min.js"></script>

<script type="text/javascript" src="/modules/fbd/hrm/peinfo/js/careersearch.js"></script>
<script type="text/javascript" src="/static/js/head.js"></script>
</body>
</html>
