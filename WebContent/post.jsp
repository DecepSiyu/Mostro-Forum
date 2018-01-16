<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">

<head>

<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>新帖发布</title>

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
	<%@ include file="navigation.jsp"%>

	<!-- Page Header -->
	<header class="intro-header"
		style="background-image: url('img/contact-bg.jpg')">
	<div class="container">
		<div class="row">
			<div class="col-lg-8 offset-lg-2 col-md-10 offset-md-1">
				<div class="page-heading">
					<h1>发布新帖</h1>
					<hr class="small">
					<span class="subheading">请开始您的创作</span>
				</div>
			</div>
		</div>
	</div>
	</header>

	<!-- Main Content -->
	<div class="container">
		<div class="row">
			<div class="col-lg-8 offset-lg-2 col-md-10 offset-md-1">
				<form method="post" action="PublishServlet">
					<div class="control-group">
						<div class="form-group floating-label-form-group controls">
							<label>标题</label> <input type="text" class="form-control"
								placeholder="标题" name="title">
							<p class="help-block text-danger"></p>
						</div>
					</div>
					<div class="control-group">
						<div class="form-group floating-label-form-group controls">
							<label>板块</label> <input type="text" class="form-control"
								placeholder="板块" name="plate">
							<p class="help-block text-danger"></p>
						</div>
					</div>
					<div class="control-group">
						<div class="form-group floating-label-form-group controls">
							<label>正文</label>
							<textarea rows=10
								style='overflow: scroll; overflow-y: hidden; overflow-x: hidden'
								onfocus="window.activeobj=this;this.clock=setInterval(function(){activeobj.style.height=activeobj.scrollHeight+'px';},200);"
								onblur="clearInterval(this.clock);" placeholder="正文"
								name="content"></textarea>
							<p class="help-block text-danger"></p>
						</div>
					</div>
					<span><font size="2" color="gray">${error}</font> </span> <br>
					<div id="success"></div>
					<div class="form-group">
						<button type="submit" name="submit" value="publish"
							class="btn btn-secondary">发布</button>
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

	<!-- Contact Form JavaScript -->
	<script src="js/jqBootstrapValidation.js"></script>
	<script src="js/contact_me.js"></script>

	<!-- Theme JavaScript -->
	<script src="js/clean-blog.min.js"></script>

</body>

</html>
