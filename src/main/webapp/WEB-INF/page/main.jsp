<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>家庭财务管理系统</title>
<jsp:include page="common.jsp" flush="true"/>
<script type="text/javascript" src="../static/js/common/echarts.common.min.js"></script>
<script type="text/javascript" src="../static/js/common/macarons.js"></script>
<script type="text/javascript" src="../static/js/main.js"></script>
</head>
<body class="easyui-layout" fit="true">


<table width="100%" height="100%" cellpadding="0" cellspacing="0">
<tr><td width="50%" height="50%" style="border-right:1px solid #95b8e7;">
<div class="easyui-panel" title="增加收支" data-options="fit:true,border:false">
<form id="addForm" method="post">
<table align="center" cellspacing="10" >
<tr><td style="text-align: right;"><font color="red">*</font>收支：</td><td><input type="radio" value="0" checked="checked" name="type"/>收入<input type="radio" value="1" name="type"/>支出</td></tr>
<tr><td style="text-align: right;"><font color="red">*</font>分类：</td><td><select id="add_caId" name="caId" ></select></td></tr>
<tr><td style="text-align: right;"><font color="red">*</font>成员：</td><td><select id="add_mId" name="mId" ></select></td></tr>
<tr><td style="text-align: right;"><font color="red">*</font>金额：</td><td><input id="add_money" name="money" class="easyui-numberbox" data-options="required:true,min:0,precision:2,width:144"/></td></tr>
<tr><td style="text-align: right;"><font color="red">*</font>时间：</td><td><input id="add_spend_date" name="szDate" class="easyui-datebox" data-options="required:true,editable:false,width:144"/></td></tr>
<tr><td style="text-align: center;" colspan="2"><a id="addBtn" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add'">提交</a>&nbsp;&nbsp;&nbsp;<a id="czBtn" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-reload'">重置</a></td></tr>
</table>
</form>
</div>
</td>
<td width="50%" height="50%">
<div class="easyui-panel" title="本月支出" data-options="fit:true,border:false">
<div id="chart" style="width: 80%;height:100%;margin-left: 10%;margin-right: 10%"></div>
</div>
</td></tr>
<tr height="50%"><td style="border-right:1px solid #95b8e7;border-top: 1px solid #95b8e7">
<div class="easyui-panel" title="本月分类支出" data-options="fit:true,border:false">
<div id="chart2" style="width: 80%;height:100%;margin-left: 10%;margin-right: 10%"></div>
</div></td><td style="border-top: 1px solid #95b8e7;">
<div class="easyui-panel" title="本月成员支出" data-options="fit:true,border:false">
<div id="chart3" style="width: 80%;height:100%;margin-left: 10%;margin-right: 10%"></div>
</div></td></tr>
</table>

</body>
</html>