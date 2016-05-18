<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>收入管理</title>
<jsp:include page="common.jsp" flush="true"/>
<script type="text/javascript" src="../static/js/earning.js"></script>
</head>
<body class="easyui-layout" fit="true">
 <div data-options="region:'north',border:false,height:105">

 <table class="table">
 <tr><th>分类:</th><td><select id="search_caId" ></select></td><th>家庭成员:</th><td class="lastCol"><select id="search_mId" ></select></td></tr>
 <tr><th>时间:</th><td><input id="search_beginDate" type="text" class="easyui-datebox" data-options="editable:false,width:150"/>至<input id="search_endDate" type="text" class="easyui-datebox" data-options="editable:false,width:150"/></td><th>金额:</th><td class="lastCol"><input id="search_beginMoney" class="easyui-numberbox" data-options="min:0,precision:2,width:150"/>至<input id="search_endMoney" class="easyui-numberbox" data-options="min:0,precision:2,width:150"/></td></tr>
 <tr class="lastrow"><td colspan="4"><a id="searchBtn" class="easyui-linkbutton" data-options="iconCls:'icon-search'">搜索</a>&nbsp;&nbsp;&nbsp;<a id="clearBtn" class="easyui-linkbutton" data-options="iconCls:'icon-reload'">重置</a></td></tr>
 </table>
 </div>
 <div data-options="region:'center',border:false">
 <table id="dg"></table>
 </div>

<div id="tb">
<c:forEach var="item" items="${op}">
<c:if test="${item=='增加收入'}">
<a id="addBtn" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true"></a>
</c:if>
<c:if test="${item=='修改收入'}">
<a id="modBtn" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-edit',plain:true"></a>
</c:if>
<c:if test="${item=='删除收入'}">
<a id="delBtn" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-remove',plain:true"></a>
</c:if>
</c:forEach>
</div>


<div id="addDialog">
<form id="addForm" method="post">
<table align="center" cellspacing="10" >
<tr><td style="text-align: right;"><font color="red">*</font>分类：</td><td><select id="add_caId" name="caId" ></select></td></tr>
<tr><td style="text-align: right;"><font color="red">*</font>成员：</td><td><select id="add_mId" name="mId" ></select></td></tr>
<tr><td style="text-align: right;"><font color="red">*</font>金额：</td><td><input id="add_money" name="money" class="easyui-numberbox" data-options="required:true,min:0,precision:2,width:144"/></td></tr>
<tr><td style="text-align: right;"><font color="red">*</font>时间：</td><td><input id="add_earning_date" name="earningDate" class="easyui-datebox" data-options="required:true,editable:false,width:144"/></td></tr>
<tr><td style="text-align: right;">备注：</td><td><input id="add_remark" name="remark" class="easyui-textbox" data-options="width:144,height:50,multiline:true,validType:'length[0,200]'"/></td></tr>
</table>
</form>
</div>
<div id="modDialog">
<form id="modForm" method="post">
<input type="hidden" name="eId"/>
<table align="center" cellspacing="10" >
<tr><td style="text-align: right;"><font color="red">*</font>分类：</td><td><select id="mod_caId" name="caId" ></select></td></tr>
<tr><td style="text-align: right;"><font color="red">*</font>成员：</td><td><select id="mod_mId" name="mId" ></select></td></tr>
<tr><td style="text-align: right;"><font color="red">*</font>金额：</td><td><input id="mod_money" name="money" class="easyui-numberbox" data-options="required:true,min:0,precision:2,width:144"/></td></tr>
<tr><td style="text-align: right;"><font color="red">*</font>时间：</td><td><input id="mod_earning_date" name="earningDate" class="easyui-datebox" data-options="required:true,editable:false,width:144"/></td></tr>
<tr><td style="text-align: right;">备注：</td><td><input id="mod_remark" name="remark" class="easyui-textbox" data-options="width:144,height:50,multiline:true,validType:'length[0,200]'"/></td></tr>
</table>
</form>
</div>

</body>
</html>