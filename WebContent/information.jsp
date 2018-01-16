<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>

<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>个人信息</title>

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
		style="background-image: url('img/about-bg.jpg')">
		<div class="container">
			<div class="row">
				<div class="col-lg-8 offset-lg-2 col-md-10 offset-md-1">
					<div class="page-heading">
						<h1>我的信息</h1>
						<span class="subheading">这里是您的个人信息页</span>
					</div>
				</div>
			</div>
		</div>
	</header>

	<!-- Main Content -->
	<div class="container">
		<div class="row">
			<div class="col-lg-8 offset-lg-2 col-md-10 offset-md-1">
				<div></div>
				<div class="form-group">
					<button id="show-once-button" onclick="diag()"
						class="btn btn-secondary">修改密码</button>
				</div>

				<form id="passwd-change" method="post" style="display: none"
					action="UsrPasswdChangeServlet">
					<div class="control-group">
						<div class="form-group floating-label-form-group controls">
							<label>密码</label> <input type="password" class="form-control"
								placeholder="输入密码" name="old_passwd">
							<p class="help-block text-danger"></p>
						</div>
					</div>
					<div class="control-group">
						<div class="form-group floating-label-form-group controls">
							<label>新密码</label> <input type="password" class="form-control"
								placeholder="输入新密码" name="new_passwd">
							<p class="help-block text-danger"></p>
						</div>
					</div>
					<span><font size="2" color="gray">${error}</font> </span> <br>
					<div></div>
					<div class="form-group">
						<button type="submit" name="submit" class="btn btn-secondary">修改密码</button>
					</div>
				</form>

				<form id="normal-infochange" method="post" action="PublishServlet">
					<div class="control-group">
						<div class="form-group floating-label-form-group controls">
							<label>邮箱</label> <input type="text" class="form-control"
								placeholder="邮箱" name="email">
							<p class="help-block text-danger"></p>
						</div>
					</div>
					<div class="control-group">
						<div class="form-group floating-label-form-group controls">
							<label>性别</label> <input type="text" class="form-control"
								placeholder="性别" name="sex">
							<p class="help-block text-danger"></p>
						</div>
					</div>
					<div class="control-group">
						<div class="form-group floating-label-form-group controls">
							<label>生日</label> <input type="text" class="form-control"
								placeholder="生日" name="birthday">
							<p class="help-block text-danger"></p>
						</div>
					</div>
					<span><font size="2" color="gray">${error}</font> </span> <br>
					<div id="success"></div>
					<div class="form-group">
						<button type="submit" name="submit" class="btn btn-secondary">修改</button>
					</div>
				</form>
			</div>
		</div>
	</div>

	<hr>

	<!-- jQuery Version 3.1.1 -->
	<script src="lib/jquery/jquery.js"></script>

	<!-- Tether -->
	<script src="lib/tether/tether.min.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script src="lib/bootstrap/js/bootstrap.min.js"></script>

	<!-- Theme JavaScript -->
	<script src="js/clean-blog.min.js"></script>

	<script type="text/javascript">
		function diag() {
			document.getElementById("passwd-change").style.display = "block";
			document.getElementById("show-once-button").style.display = "none";
			document.getElementById("normal-infochange").style.display = "none";
		}
	</script>
</body>

</html>

<%
	
%>