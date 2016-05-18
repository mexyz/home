<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>家庭财务管理系统</title>
<link href="../static/images/home.ico" rel="shortcut icon">
<!-- CSS -->
<link rel="stylesheet" href="../static/css/reset.css">
<link rel="stylesheet" href="../static/css/supersized.css">
<link rel="stylesheet" href="../static/css/style.css">

<!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
<!--[if lt IE 9]>
            <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
        <![endif]-->
<!-- Javascript -->
<script src="../static/js/common/jquery.min.js"></script>
<script src="../static/js/common/supersized.3.2.7.min.js"></script>
<script src="../static/js/common/supersized-init.js"></script>
<script src="../static/js/common/scripts.js"></script>
<script type="text/javascript">
	$(function() {
		$("#tj").click(function() {
			$.ajax({
				type : "post",
				url : "login",
				dataType : "json",
				data : $("form").serialize(),
				success : function(data) {
					if (data.msg == "N") {
						alert("用户名或密码错误");
					} else {
						location.href = "index";
					}

				}
			});
		});
	})
</script>
</head>
<body>
	<div class="page-container">
		<h1>家庭财务管理系统</h1>
		<form method="post">
			<input type="text" name="userName" class="username" placeholder="用户名">
			<input type="password" name="password" class="password"
				placeholder="密码">
			<button type="button" id="tj">登录</button>
			<div class="error">
				<span>+</span>
			</div>
		</form>
		<div class="connect"></div>
	</div>
</body>
</html>