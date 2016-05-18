<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>收支统计</title>
<jsp:include page="common.jsp" flush="true"/>
<script type="text/javascript" src="../static/js/common/echarts.common.min.js"></script>
<script type="text/javascript" src="../static/js/common/macarons.js"></script>
<script type="text/javascript" src="../static/js/earningSpendStats.js"></script>

</head>
<body>
 
 <div class="searchDiv">
<table class="table"><tr><th>统计维度:</th><td><select id="search_groupBy"></select></td><th>时间:</th><td class="lastCol"><input id="search_beginDate" type="text" class="easyui-datebox" data-options="editable:false"/>至<input id="search_endDate" type="text" class="easyui-datebox" data-options="editable:false"/></td></tr>
<tr class="lastrow" ><td colspan="4"><a id="searchBtn" class="easyui-linkbutton" data-options="iconCls:'icon-search'">搜索</a>&nbsp;&nbsp;&nbsp;<a id="clearBtn" class="easyui-linkbutton" data-options="iconCls:'icon-reload'">重置</a></td></tr></table>
</div>


<table id="dg"></table>
<br/>
<div id="chart" style="width: 80%;height:400px;margin-left: 10%;margin-right: 10%"></div>

<div id="chart2" style="width: 80%;height:400px;margin-left: 10%;margin-right: 10%"></div>

</body>
</html>