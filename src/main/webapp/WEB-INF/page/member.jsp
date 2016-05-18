<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>家庭财务管理系统</title>
<jsp:include page="common.jsp" flush="true"/>
<script type="text/javascript" src="../static/js/member.js"></script>
</head>
<body class="easyui-layout" fit="true">
 <div data-options="region:'north',border:false">
 <div class="searchDiv">
 <table class="table"><tr><th>真实姓名:</th><td class="lastCol"><input id="realName" class="easyui-textbox" type="text"/></td></tr>
 <tr class="lastrow"><td colspan="2"><a id="searchBtn" class="easyui-linkbutton" data-options="iconCls:'icon-search'">搜索</a>&nbsp;&nbsp;&nbsp;<a id="clearBtn" class="easyui-linkbutton" data-options="iconCls:'icon-reload'">重置</a></td></tr>
 </table>
</div>
 </div>
 <div data-options="region:'center',border:false">
 <table id="dg"></table>
 </div>

	
    
	

<div id="addDialog">
<form id="addForm" method="post">
<table align="center" cellspacing="10" >
<tr><td style="text-align: right;"><font color="red">*</font>真实姓名：</td><td><input type="text" class="easyui-textbox" name="realName" class="easyui-validatebox" data-options="required:true,validType:'name'"/></td></tr>
<tr><td style="text-align: right;"><font color="red">*</font>称呼：</td><td><input type="text" class="easyui-textbox" name="callName" class="easyui-validatebox" data-options="required:true,validType:'call'"/></td></tr>
<tr><td style="text-align: right;"><font color="red">*</font>性别：</td><td><input type="radio" name="gender" checked="checked" value="0"/>男<input type="radio" name="gender" value="1"/>女</td></tr>
</table>
</form>
</div>
<div id="modDialog">
<form id="modForm" method="post">
<input type="hidden" name="mId"/>
<table align="center" cellspacing="10" >
<tr><td style="text-align: right;"><font color="red">*</font>真实姓名：</td><td><input type="text" class="easyui-textbox" id="modRealName" name="realName" class="easyui-validatebox" data-options="required:true,validType:'name'"/></td></tr>
<tr><td style="text-align: right;"><font color="red">*</font>称呼：</td><td><input type="text" class="easyui-textbox" id="modCallName" name="callName" class="easyui-validatebox" data-options="required:true,validType:'call'"/></td></tr>
<tr><td style="text-align: right;"><font color="red">*</font>性别：</td><td><input type="radio" name="gender" checked="checked" value="0"/>男<input type="radio" name="gender" value="1"/>女</td></tr>
</table>
</form>
</div>


<div id="tb">
<c:forEach var="item" items="${op}">
<c:if test="${item=='增加家庭成员'}">
<a href="javascript:void(0)" id="addBtn" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true"></a>
</c:if>
<c:if test="${item=='修改家庭成员'}">
<a href="javascript:void(0)" id="modBtn" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true"></a>
</c:if>
<c:if test="${item=='删除家庭成员'}">
<a href="javascript:void(0)" id="delBtn" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true"></a>
</c:if>
</c:forEach>
</div>
</body>
</html>