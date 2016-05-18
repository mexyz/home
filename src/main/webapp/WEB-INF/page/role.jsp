<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>家庭财务管理系统</title>
<jsp:include page="common.jsp" flush="true"/>
<script type="text/javascript" src="../static/js/role.js"></script>
</head>
<body class="easyui-layout" fit="true">
 <div data-options="region:'north',border:false">
 <div class="searchDiv">
<table class="table"><tr  ><th>角色名:</th><td class="lastCol"><input id="roleName" class="easyui-textbox" type="text"/></td></tr>
<tr class="lastrow"><td colspan="2"><a id="searchBtn" class="easyui-linkbutton" data-options="iconCls:'icon-search'">搜索</a>&nbsp;&nbsp;&nbsp;<a id="clearBtn" class="easyui-linkbutton" data-options="iconCls:'icon-reload'">重置</a></td></tr>
</table>
</div>
 </div>
 <div data-options="region:'center',border:false">
 <table id="dg"></table>
 </div>

	
    
	

<div id="addDialog">
<form id="addForm" method="post">
<input name="mIds" type="hidden"/>
<table align="center" cellspacing="10" >
<tr><td style="text-align: right;"><font color="red">*</font>角色名：</td><td><input type="text" class="easyui-textbox" name="roleName" class="easyui-validatebox" data-options="required:true,validType:['roleName','reRoleName']" style="width: 200px;" prompt="2-10个中文字符"/></td></tr>
<!-- <tr><td style="text-align: right;"><font color="red">*</font>菜单权限：</td><td><input id="addRoleCombotree"/></td></tr> -->
</table>
</form>
</div>
<div id="modDialog">
<form id="modForm" method="post">
<input type="hidden" name="rId"/>
<table align="center" cellspacing="10" >
<tr><td style="text-align: right;"><font color="red">*</font>角色名：</td><td><input id="modRoleName" type="text" class="easyui-textbox" name="roleName" class="easyui-validatebox" data-options="required:true,validType:['roleName','modReRoleName']" style="width: 200px;" prompt="2-10个中文字符"/></td></tr>
<!-- <tr><td style="text-align: right;"><font color="red">*</font>菜单权限：</td><td><input id="addRoleCombotree"/></td></tr> -->
</table>
</form>
</div>


<div id="tb">
<c:forEach var="item" items="${op}">
<c:if test="${item=='增加角色'}">
<a href="javascript:void(0)" id="addBtn" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true"></a>
</c:if>
<c:if test="${item=='修改角色'}">
<a href="javascript:void(0)" id="modBtn" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true"></a>
</c:if>
<c:if test="${item=='删除角色'}">
<a href="javascript:void(0)" id="delBtn" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true"></a>
</c:if>
</c:forEach>
</div>  
</body>
</html>