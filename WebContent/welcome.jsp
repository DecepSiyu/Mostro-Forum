<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">

<head>

<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>主页</title>

<!-- Bootstrap Core CSS -->
<link href="lib/bootstrap/css/bootstrap.min.css" rel="stylesheet">

<!-- Additional fonts for this theme -->
<link href="lib/font-awesome/css/font-awesome.min.css" rel="stylesheet"
	type="text/css">
<link
	href='https://fonts.googleapis.com/css?family=Lora:400,700,400italic,700italic'
	rel='stylesheet' type='text/css'>
<link
	href='https://fonts.googleapis.com/css?family=Open+Sans:300italic,400italic,600italic,700italic,800italic,400,300,600,700,800'
	rel='stylesheet' type='text/css'>

<!-- Custom styles for this theme -->
<link href="css/clean-blog.min.css" rel="stylesheet">

<!-- Temporary navbar container fix until Bootstrap 4 is patched -->
<style>
.navbar-toggler {
	z-index: 1;
}

@media ( max-width : 576px) {
	nav>.container {
		width: 100%;
	}
}
</style>

</head>

<body>
	<!-- Navigation -->
	<%@ include file="navigation.jsp"%>

	<!-- Page Header -->
	<header class="intro-header"
		style="background-image: url('img/home-bg.jpg')">
	<div class="container">
		<div class="row">
			<div class="col-lg-8 offset-lg-2 col-md-10 offset-md-1">
				<div class="site-heading">
					<h1>欢迎回来，${username}</h1>
					<span class="subheading">一个为企业提供的论坛</span>
				</div>
			</div>
		</div>
	</div>
	</header>

	<!-- Main Content -->
	<div class="container">
		<div class="row">
			<div class="col-lg-8 offset-lg-2 col-md-10 offset-md-1">
				<%@ page import="com.controller.*"%>
				<%@ page import="java.sql.*"%>
				<%
					ArrayList<Post> posts = LoginServlet.loadPosts(LoginServlet.connection, 200);
					session.setAttribute("posts", posts);
					if (posts != null) {
						for (int i = 0; i < posts.size(); i++) {
				%>
				<form id=<%=String.format("\"%s\"", posts.get(i).getPostID())%>
					action="ViewPostServlet" method="get">
					<div class="post-preview">
						<a href="passage.jsp" type="submit"
							onclick="document.getElementById(<%=String.format("\'%s\'", posts.get(i).getPostID())%>).submit();">
							<h2 class="post-title"><%=posts.get(i).getTitle()%></h2>
							<h3 class="post-subtitle"><%=posts.get(i).getBriefContent()%></h3> <input
							id="post_id_input" name="post_id" style="display: none"
							value=<%=String.format("\"%s\"", posts.get(i).getPostID())%>>
						</a>
						<p class="post-meta">
							<%="板块名称：" + posts.get(i).getPlate().getName()%>
							<a href="#"><%="作者：" + posts.get(i).getAuther()%> </a><%="创作时间：" + posts.get(i).getPublishTime()%>
						</p>
					</div>
				</form>
				<%
					}
					}
				%>
				<hr>
				<!-- Pager -->
				<div class="clearfix">

					<a class="btn btn-secondary float-right" href="#">下一页 &rarr;</a> <a
						class="btn btn-secondary float-right" href="#">&larr;上一页</a>
				</div>
			</div>
		</div>
	</div>

	<hr>

	<!-- Footer -->
	<footer>
	<div class="container">
		<div class="row">
			<div class="col-lg-8 offset-lg-2 col-md-10 offset-md-1"></div>
		</div>
	</div>
	</footer>

	<!-- jQuery Version 3.1.1 -->
	<script src="lib/jquery/jquery.js"></script>

	<!-- Tether -->
	<script src="lib/tether/tether.min.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script src="lib/bootstrap/js/bootstrap.min.js"></script>

	<!-- Theme JavaScript -->
	<script src="js/clean-blog.min.js"></script>

</body>

</html>

