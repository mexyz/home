<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>家庭财务管理系统</title>
 <link rel="stylesheet" href="../static/css/reset.css">
        <link rel="stylesheet" href="../static/css/supersized.css">
        <link rel="stylesheet" href="../static/css/style.css">

        <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
        <!--[if lt IE 9]>
            <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
        <![endif]-->
<script src="../static/js/common/jquery.min.js"></script>
<script src="../static/js/common/supersized.3.2.7.min.js"></script>
<script src="../static/js/common/supersized-init.js"></script>
<script src="../static/js/common/scripts.js"></script>
<script type="text/javascript">
$(function(){
	$("#login").click(function(){
		 $.ajax({
				url:'login',
				type : "POST", 
				dataType:"json",
				data:{"userName":$("input[name='username']:first").val(),
					"password":$("input[name='password']:first").val()},
				success:function(data){
					if(data.msg=="Y"){
						location.href="index";
					}else{
						alert("用户名或密码错误");
					}
				}
			});
	});
});


</script>
</head>
<body>
<div class="page-container">
            <h1>登录</h1>
            <form action="login" method="post">
                <input type="text" name="username" class="username" placeholder="用户名">
                <input type="password" name="password" class="password" placeholder="密码">
                <button type="button" id="login">登录</button>
            </form>
        </div>
		

</body>
</html>