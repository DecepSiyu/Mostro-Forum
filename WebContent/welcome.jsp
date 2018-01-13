<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>主页</title>
<link rel="stylesheet" type="text/css" href="css/menu.css">
<link rel="stylesheet" type="text/css"
	href="http://netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css"
	rel="stylesheet">
<link href="css/bootstrap.min.css" rel="stylesheet">
<link rel="stylesheet" href="css/guide.css" media="screen"
	type="text/css" />
</head>

<body>


	<nav class="main-menu">
	<div class="logo"></div>
	<div class="settings"></div>
	<ul>
		<li><a href="welcome.jsp"> <i class="fa fa-home fa-lg"></i> <span
				class="nav-text">主页</span>
		</a></li>
		<!-- TODO 网址的跳转 -->
		<li class="has-subnav"><a href="${contextPath}/edit"> <i
				class="fa fa-plane fa-lg"></i> <span class="nav-text">发布消息</span>
		</a></li>
		<li><a href="#"> <i class="fa fa-picture-o fa-lg"></i> <span
				class="nav-text">我的关注</span>
		</a></li>
		<li><a href="#"> <i class="fa fa-align-left fa-lg"></i> <span
				class="nav-text">消息广场</span>
		</a></li>
		<li class="has-subnav"><a href="#"> <i
				class="fa fa-clock-o fa-lg"></i> <span class="nav-text">历史回复</span>
		</a></li>
		<li><a href="#"> <i class="fa fa-desktop fa-lg"></i> <span
				class="nav-text">个人信息</span>
		</a></li>
		<li><a href="login.jsp"> <i class="fa fa-glass fa-lg"></i> <span
				class="nav-text">离开</span>
		</a></li>

		<!-- 管理员权限 -->
		<li style="display: none"><a href="#"><i
				class="fa fa-flask fa-lg"></i><span class="nav-text">板块管理</span></a></li>
		<li style="display: none"><a href="#"><i
				class="fa fa-rocket fa-lg"></i><span class="nav-text">帖子管理</span></a>
	</ul>

	<ul class="logout">
		<li><a href="#"> <i class="fa fa-lightbulb-o fa-lg"></i> <span
				class="nav-text">博客</span>

		</a></li>
	</ul>
	</nav>

	<div style="text-align: center; clear: both">
		<script src="/gg_bd_ad_720x90.js" type="text/javascript"></script>


		<script src="/follow.js" type="text/javascript"></script>
	</div>

	<div class="container">
		<h2>欢迎回来，${user}</h2>
	</div>
	<%
		if (session.getAttribute("user") == null)
			response.sendRedirect("login.jsp");
		session.setAttribute("error", "");
		session.setAttribute("message", "");
	%>

</body>
</html>