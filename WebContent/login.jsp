<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>登陆界面</title>

<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/meyer-reset/2.0/reset.min.css">
<link rel='stylesheet prefetch'
	href='https://fonts.googleapis.com/css?family=Roboto:400,100,300,500,700,900|RobotoDraft:400,100,300,500,700,900'>
<link rel='stylesheet prefetch'
	href='https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css'>

<link rel="stylesheet" href="css/style.css">
</head>


<body>

	<%
		String username = (String)session.getAttribute("user");
		if (username != null) {
			System.out.println(username + " logout");
			session.setAttribute("user", null);
		}
	%>
	<div class="pen-title">
		<h1>欢迎</h1>
	</div>
	<!-- Form Module-->
	<div class="module form-module">
		<div class="toggle">
			<i class="fa fa-times fa-pencil"></i>
			<div class="tooltip">点我注册！</div>
		</div>
		<div class="form">
			<h2>登陆你的账号</h2>
			<span>${message}</span>
			<form method="post" action="LoginServlet">
				<input type="text" placeholder="用户名" name="usrname" /> <input
					type="password" placeholder="密码" name="passwd" /> <span>${error}</span>
				<button type="submit" name="submit" value="login">登陆</button>
			</form>
		</div>
		<div class="form">
			<h2>创建一个账号</h2>
			<form method="post" action="RegistrateServlet">
				<input type="text" placeholder="用户名" name="usrname" /> <input
					type="password" placeholder="密码" name="passwd" /><span>${error}</span>
				<button type="submit" name="submit" value="registrate">注册</button>
			</form>
		</div>
	</div>
	<script
		src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>
	<script src="js/index.js"></script>
</body>
</html>


