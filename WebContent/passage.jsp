<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">

<title>${title}</title>

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
		style="background-image: url('img/post-bg.jpg')">
	<div class="container">
		<div class="row">
			<div class="col-lg-8 offset-lg-2 col-md-10 offset-md-1">
				<div class="post-heading">
					<h1>${title}</h1>
					<h2 class="subheading">${subtitle}</h2>
					<span class="meta">作者： <a href="#">Mostro</a>${date}</span>
				</div>
			</div>
		</div>
	</div>
	</header>

	<!-- Post Content -->
	<article>
	<div class="container">
		<div class="row">
			<div class="col-lg-8 offset-lg-2 col-md-10 offset-md-1">
				<p>${part_1}</p>

				<p>${part_2}</p>

				<p>${part_3}</p>

				<h2 class="section-heading">${section_1}</h2>

				<p>${part_4}</p>

				<blockquote class="blockquote">${quote}</blockquote>

				<p></p>

				<h2 class="section-heading">${section_2}</h2>

				<a href="#"> <img class="img-responsive"
					src="img/post-sample-image.jpg" alt="">
				</a> <span class="caption text-muted">To go places and do things
					that have never been done before – that’s what living is all about.</span>

				<p>Space, the final frontier. These are the voyages of the
					Starship Enterprise. Its five-year mission: to explore strange new
					worlds, to seek out new life and new civilizations, to boldly go
					where no man has gone before.</p>

				<p>As I stand out here in the wonders of the unknown at Hadley,
					I sort of realize there’s a fundamental truth to our nature, Man
					must explore, and this is exploration at its greatest.</p>

				<p>
					Placeholder text by <a href="http://spaceipsum.com/">Space
						Ipsum</a>. Photographs by <a
						href="https://www.flickr.com/photos/nasacommons/">NASA on The
						Commons</a>.
				</p>
			</div>
		</div>
	</div>
	</article>

	<hr>

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
