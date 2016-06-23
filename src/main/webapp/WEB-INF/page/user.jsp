<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>家庭财务管理系统</title>
<jsp:include page="common.jsp" flush="true"/>
<script type="text/javascript" src="../static/js/user.js"></script>
</head>
<body class="easyui-layout" fit="true">
 <div data-options="region:'north',border:false">
 <div class="searchDiv">
<table class="table"><tr><th>用户名:</th><td><input id="userName" class="easyui-textbox" type="text"/></td><th>角色:</th><td><input id="roleName" class="easyui-textbox" type="text"/></td><th>登录时间:</th><td class="lastCol"><input id="beginTime" class="easyui-datetimebox"/>至<input id="endTime" class="easyui-datetimebox"/></td></tr>
<tr class="lastrow"><td colspan="6"><a id="searchBtn" class="easyui-linkbutton" data-options="iconCls:'icon-search'">搜索</a>&nbsp;&nbsp;&nbsp;<a id="clearBtn" class="easyui-linkbutton" data-options="iconCls:'icon-reload'">重置</a></td></tr></table>
</div>
 </div>
 <div data-options="region:'center',border:false">
 <table id="dg"></table>
 </div>

<div id="tb">
<c:forEach var="item" items="${op}">
<c:if test="${item=='增加用户'}">
<a href="javascript:add()" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true"/>
</c:if>
<c:if test="${item=='修改用户'}">
<a href="javascript:void(0)" id="mb" class="easyui-menubutton" data-options="menu:'#mm',iconCls:'icon-edit',plain:true"></a>
<div id="mm" style="width:150px;">
	<div onclick="mod(0)">修改密码</div>
    <div onclick="mod(1)">修改角色</div>
</div>
</c:if>
<c:if test="${item=='删除用户'}">
<a href="javascript:delData()" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true"/>
</c:if>
</c:forEach>
</div>


<div id="addDialog">
<form id="addForm" method="post">
<table align="center" cellspacing="10" >
<tr><td style="text-align: right;"><font color="red">*</font>用户名：</td><td><input class="easyui-textbox" type="text" name="userName" class="easyui-validatebox" data-options="required:true,validType:['username','reusername']" prompt="6-12位数字、小写字母、下划线" style="width: 200px"/></td></tr>
<tr><td style="text-align: right;"><font color="red">*</font>密码：</td><td><input class="easyui-textbox" type="password" name="password" class="easyui-validatebox" data-options="required:true,validType:'password'" prompt="6-12位数字、大小写字母、下划线" style="width: 200px"/></td></tr>
<tr><td style="text-align: right;"><font color="red">*</font>确认密码：</td><td><input class="easyui-textbox" type="password" name="password2" class="easyui-validatebox" data-options="required:true,validType:'repassword[0]'" prompt="再次输入密码" style="width: 200px"/></td></tr>
<tr><td style="text-align: right;"><font color="red">*</font>角色：</td><td><select id="roleAdd" name="rId"></select></td></tr>
</table>
</form>
</div>
<div id="modPassDialog">
<form id="modPassForm" method="post">
<input type="hidden" name="uId"/>
<table align="center" cellspacing="10" >
<tr><td style="text-align: right;"><font color="red">*</font>用户名：</td><td id="modUserName"></td></tr>
<tr><td style="text-align: right;"><font color="red">*</font>密码：</td><td><input class="easyui-textbox" type="password" name="password" class="easyui-validatebox" data-options="required:true,validType:'password'" prompt="6-12位数字、大小写字母、下划线" style="width: 200px"/></td></tr>
<tr><td style="text-align: right;"><font color="red">*</font>确认密码：</td><td><input class="easyui-textbox" type="password" name="password2" class="easyui-validatebox" data-options="required:true,validType:'repassword[1]'" prompt="再次输入密码" style="width: 200px"/></td></tr>
</table>
</form>
</div>

<div id="modRoleDialog">
<form id="modRoleForm" method="post">
<input type="hidden" name="uId"/>
<table align="center" cellspacing="10" >
<tr><td style="text-align: right;"><font color="red">*</font>用户名：</td><td id="modRoleUserName"></td></tr>
<tr><td style="text-align: right;"><font color="red">*</font>角色：</td><td><select id="roleMod" name="rId" ></select></td></tr>
</table>
</form>
</div>
</body>
</html>