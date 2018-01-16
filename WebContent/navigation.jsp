<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		String username = (String) session.getAttribute("user");
		if (username == null || username.equals("")) {
			response.sendRedirect("login.jsp");
		}

		//session.setAttribute("is_admin", false);
	%>
	<!-- Navigation -->
	<nav class="navbar fixed-top navbar-toggleable-md navbar-light"
		id="mainNav">
	<div class="container">
		<button class="navbar-toggler navbar-toggler-right" type="button"
			data-toggle="collapse" data-target="#navbarResponsive"
			aria-controls="navbarResponsive" aria-expanded="false"
			aria-label="Toggle navigation">
			Menu <i class="fa fa-bars"></i>
		</button>
		<a class="navbar-brand page-scroll" href="welcome.jsp">Mostro-Forum</a>
		<div class="collapse navbar-collapse" id="navbarResponsive">
			<ul class="navbar-nav ml-auto">
				<li class="nav-item"><a class="nav-link page-scroll"
					href="welcome.jsp">主页</a></li>
				<li class="nav-item"><a class="nav-link page-scroll"
					href="post.jsp">发帖</a></li>
				<li class="nav-item"><a class="nav-link page-scroll"
					href="information.jsp">我的信息</a></li>

				<li class="nav-item"
					style="display: <%/*if ((boolean) session.getAttribute("is_admin"))
				out.print("block");
			else//如果把is_admin设为true，就会显示信息查询这一栏
				out.print("none");*/%>"><a
					class="nav-link page-scroll" href="search.jsp">信息查询</a></li>

				<li class="nav-item"><a class="nav-link page-scroll"
					href="login.jsp">退出</a></li>
			</ul>
		</div>
	</div>
	</nav>

</body>
</html>