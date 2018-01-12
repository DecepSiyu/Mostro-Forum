<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />


<!DOCTYPE html>
<html class="menu">
<head>
<title>主页</title>
<meta charset="UTF-8">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<meta name="description" content="">
<meta name="author" content="">

<title>侧边菜单</title>

<link rel="stylesheet" type="text/css" href="css/menu.css">
<link rel="stylesheet" type="text/css"
	href="http://netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css"
	rel="stylesheet">
<link href="${contextPath}/resources/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet" href="${contextPath}/resources/css/style.css" media="screen"
	type="text/css" />

<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>
<body>

	<nav class="main-menu">

		<div class="logo"></div>
		<div class="settings"></div>

		<ul>
			<li><a href="http://startific.com"> <i
					class="fa fa-home fa-lg"></i> <span class="nav-text">主页</span>
			</a></li>
			<!-- TODO 网址的跳转 -->
			<li class="has-subnav"><a href="${contextPath}/edit"> <i class="fa fa-plane fa-lg"></i> 
			<span class="nav-text">发布消息</span>
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
			<li><a href="#"> <i class="fa fa-glass fa-lg"></i> <span
					class="nav-text">离开</span>
			</a></li>
		
			<!-- 管理员权限 -->
			<li style="display:none"><a href="#"><i class="fa fa-flask fa-lg"></i><span 
			class="nav-text">板块管理</span></a></li>
		 	<li style="display:none"><a href="#"><i class="fa fa-rocket fa-lg"></i><span 
		 	class="nav-text">帖子管理</span></a>	</ul>
		 	
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
		<c:if test="${pageContext.request.userPrincipal.name != null}">
			<form id="logoutForm" method="POST" action="${contextPath}/logout">
				<input type="hidden" name="${_csrf.parameterName}"
					value="${_csrf.token}" />
			</form>

			<h2>
				欢迎回来 ${pageContext.request.userPrincipal.name} | <a
					onclick="document.forms['logoutForm'].submit()">注销</a>
			</h2>
		</c:if>
	</div>

	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
	<script src="${contextPath}/resources/js/bootstrap.min.js"></script>

</body>
</html>
