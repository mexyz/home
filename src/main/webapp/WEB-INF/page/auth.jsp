<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>家庭财务管理系统</title>
<jsp:include page="common.jsp" flush="true"/>
<script type="text/javascript" src="../static/js/auth.js"></script>
</head>
<body class="easyui-layout" fit="true">
<div data-options="region:'north',border:false">

</div>

<div data-options="region:'center',border:false">
<center>
	<table>
	<tr>
	<td style="vertical-align: top;"><table id="role"></table> </td>
	<td style="vertical-align: top;"><table id="member"></table></td>
	<td style="vertical-align: top;"><ul id="menu"></ul></td>
	<td style="vertical-align: top;"><table id="action"></table></td>
	</tr>
	<tr>
	<td colspan="4" style="text-align: center;padding-top: 20px;">
	<c:forEach var="item" items="${op}">
	<c:if test="${item=='保存权限'}">
	<a id="modBtn" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-save'">保存</a>
	</c:if>
	</c:forEach>
	</td>
	</tr>
	</table>
</center>
	
    
    
    
</div>

	
    

</body>
</html>