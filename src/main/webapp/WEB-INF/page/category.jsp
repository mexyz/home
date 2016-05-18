<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>家庭财务管理系统</title>
<jsp:include page="common.jsp" flush="true"/>
<script type="text/javascript" src="../static/js/category.js"></script>
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

	
    


<div id="tb">
<c:forEach var="item" items="${op}">
<c:if test="${item=='增加分类'}">
<a href="javascript:void(0)" id="addBtn" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true"></a>
</c:if>
<c:if test="${item=='修改分类'}">
<a href="javascript:void(0)" id="modBtn" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true"></a>
</c:if>
<c:if test="${item=='删除分类'}">
<a href="javascript:void(0)" id="delBtn" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true"></a>
</c:if>
</c:forEach>
</div>

<div id="addDialog1">
<form id="addForm1" method="post">
<input name="type" type="hidden"/>
<table align="center" cellspacing="10" >
<tr><td style="text-align: right;"><font color="red">*</font>分类名称：</td><td><input class="easyui-textbox" type="text" name="name" class="easyui-validatebox" data-options="required:true,validType:['category','recategory']" prompt="2-12位中文字符" style="width: 200px"/></td></tr>
</table>
</form>
</div>


<div id="addDialog2">
<form id="addForm2" method="post">
<input name="type" type="hidden"/>
<input name="pId" type="hidden"/>
<table align="center" cellspacing="10" >
<tr><td style="text-align: right;"><font color="red">*</font>分类名称：</td><td><input class="easyui-textbox" type="text" name="name" class="easyui-validatebox" data-options="required:true,validType:['category','recategory']" prompt="2-12位中文字符" style="width: 200px"/></td></tr>
</table>
</form>
</div>



<div id="modDialog1">
<form id="modForm1" method="post">
<input name="caId" type="hidden"/>
<table align="center" cellspacing="10" >
<tr><td style="text-align: right;"><font color="red">*</font>分类名称：</td><td><input class="easyui-textbox" type="text" id="modName1" name="name" class="easyui-validatebox" data-options="required:true,validType:['category','recategory[1]']" prompt="2-12位中文字符" style="width: 200px"/></td></tr>
</table>
</form>
</div>


<div id="modDialog2">
<form id="modForm2" method="post">
<input name="caId" type="hidden"/>
<table align="center" cellspacing="10" >
<tr><td style="text-align: right;"><font color="red">*</font>分类名称：</td><td><input class="easyui-textbox" type="text" id="modName2" name="name" class="easyui-validatebox" data-options="required:true,validType:['category','recategory[2]']" prompt="2-12位中文字符" style="width: 200px"/></td></tr>
</table>
</form>
</div>
</body>
</html>