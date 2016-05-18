<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="../static/images/home.ico" rel="shortcut icon">
<title>家庭财务管理系统</title>
<jsp:include page="common.jsp" flush="true"/>
<style type="text/css">
.accordion .accordion-header-selected {
  background: #95b8e7;
}
</style>
<script type="text/javascript">

var height=0;
var width=0;
$(function(){
	height=$(".tabs-panels").height();
	width=$(".tabs-panels").width();
	$('#tab').tabs('add',{
	    title:"主页",
	    content:'<iframe width="'+width+'px;" height="'+height+'px;" frameborder="0" marginheight="0" marginwidth="0" src="main.shtml"></iframe>',
	    closable:false,
	    tools:[{
	        iconCls:'icon-mini-refresh',
	        handler:function(){
	        	var tab = $('#tab').tabs('getSelected');  // get selected panel
	        	$('#tab').tabs('update', {
	        		tab: tab,
	        		options: {
	        			content:'<iframe width="'+width+'px;" height="'+height+'px;" frameborder="0" marginheight="0" marginwidth="0" src="main.shtml"></iframe>'
	        		}
	        	});
	        }
	    }]
	});
    
    
	
});

function gotoPage(id,text,href){
	
	if($('#tab').tabs('getTab',text)==null){
		$('#tab').tabs('add',{
		    title:text,
		    //href:node.attributes.url,
		    content:'<iframe width="'+width+'px;" height="'+height+'px;" frameborder="0" marginheight="0" marginwidth="0" src="'+href+'?mId='+id+'"></iframe>',
		    closable:true,
		    tools:[{
		        iconCls:'icon-mini-refresh',
		        handler:function(){
		        	var tab = $('#tab').tabs('getSelected');  // get selected panel
		        	$('#tab').tabs('update', {
		        		tab: tab,
		        		options: {
		        			content:'<iframe width="'+width+'px;" height="'+height+'px;" frameborder="0" marginheight="0" marginwidth="0" src="'+href+'?mId='+id+'"></iframe>'
		        		}
		        	});
		        }
		    }]
		});
	}else{
		$('#tab').tabs('select',text)
	}
	
}
</script>
</head>

   <body class="easyui-layout bg" >
       <div data-options="region:'north'" style="height:100px;background:url(../static/images/title.png) no-repeat;background-size:contain;">
       
      	<span style="right: 5px;position: absolute;bottom: 5px;color: white;">登录帐号:&nbsp;${user.userName}&nbsp;&nbsp;&nbsp;角色:&nbsp;${user.roleName}
      	<c:if test="${!empty user.loginTime}">&nbsp;&nbsp;&nbsp;上次登录时间:&nbsp;${user.loginTime}</c:if>&nbsp;&nbsp;&nbsp;<a href="logout" style="color: white;">安全退出</a></span>
       </div>
       <div data-options="region:'west',collapsible:false" style="width:150px;">
       <div class="easyui-accordion" border="false" fit="true">
       <c:forEach var="item" items="${menu}">
			<c:if test="${item.pid==0}">
			<div title="${item.text}" data-options="iconCls:'${item.icon}'" style="overflow:auto;padding:10px;">
				<ul style="list-style: none;padding: 0px;">
				<c:forEach var="m" items="${menu}">
					<c:if test="${m.pid==item.id}">
					<li style="padding: 0px;margin-bottom: 15px;">
					<div onclick="gotoPage('${m.id}','${m.text}','${m.href}')" class="${m.icon}-large" style="height: 64px;width: 64px;margin-left: auto;margin-right: auto;cursor: pointer;"></div>
					<div onclick="gotoPage('${m.id}','${m.text}','${m.href}')" style="height: 25px;width: 64px;margin-left: auto;margin-right: auto;text-align: center;vertical-align: middle;cursor: pointer;">${m.text}</div>
					</li>
					</c:if>
	       		</c:forEach>
				</ul>
			</div>
			</c:if>
       </c:forEach>
       
       </div>
       
       </div>
       <div data-options="region:'center'">
       
       	<div id="tab" class="easyui-tabs" fit="true" border="false" plain="true">
		</div>
       </div>
   </body>
</html>