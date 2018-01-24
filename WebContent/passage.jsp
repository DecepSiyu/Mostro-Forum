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
	
	<%
		Post post = (Post) session.getAttribute("post");
		System.out.println(post);
		if (post != null) {
	%>

	<!-- Page Header -->
	<header class="intro-header"
		style="background-image: url('img/post-bg.jpg')">
	<div class="container">
		<div class="row">
			<div class="col-lg-8 offset-lg-2 col-md-10 offset-md-1">
				<div class="post-heading">
					<form id="delete_post" method="post" action="UsrDeletePostServlet">
						<h1><%=post.getTitle()%></h1>
						<h2 class="subheading"><%="发布时间：" + post.getPublishTime()%></h2>
						<span class="meta">作者： <a><%=post.getAuther()%></a>
						</span> <span class="meta" style="text-align: right;"> <a href="#"
							onclick="document.getElementById('delete_post').submit();">删除帖子</a>
						</span>
					</form>
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
				<span><font color="gray" size="2">${error}</font> </span>
				<p><%=post.getContent()%></p>
				<hr  />
			</div>
		</div>
	</div>
	</article>
	<!-- view Comment -->
	<div class="container">
		<div class="row">
		<div class="col-lg-8 offset-lg-2 col-md-10 offset-md-1">
		<%@  page import= "com.usrServlet.*"%>
		<%@  page import= "com.postBean.*"%>
		<%@  page import= "com.controller.*"%>
		<h1>评论</h1>		
	<hr  />
		<%
			ArrayList<Comment> comments = ViewPostServlet.loadComment(LoginServlet.connection, 200, post.getPostID());
					session.setAttribute("comments", comments);
					if (comments != null) {
						for (int i = 0; i < comments.size(); i++) {
				%>
				<form id=<%=String.format("\"%s\"", comments.get(i).getPostID())%>
					action="ViewPostServlet" method="get">
					<div class="comment-preview">
						<h3 class="comment-content"><%=comments.get(i).getContents()%></h3>
						 <input
							id="comment_id_input" name="comment_id" style="display: none"
							value=<%=String.format("\"%s\"", comments.get(i).getCommentID())%>>
						
						<p class="post-meta">
							<%="作者：" + comments.get(i).getAuther()%> <%="评论时间：" + comments.get(i).getPublishTime()%>
						</p>
					</div>
				</form>
				<%
					}
					}
				%>
				
				<!-- Pager -->
				<div class="clearfix">

					<a class="btn btn-secondary float-right" href="#">下一页 &rarr;</a> <a
						class="btn btn-secondary float-right" href="#">&larr;上一页</a>
				</div>
				</div>
				</div>
				</div>
				
				
	<!-- publish comments -->
	<div class="container">
		<div class="row">
		<div class="col-lg-10 offset-lg-2 col-md-10 offset-md-1">
		<form method="post" action="CommentServlet">
		<div class="control-group">
				<div class="form-group floating-label-form-group controls">
				
						<label>添加评论</label>
						<textarea rows=10 cols=60
							style='overflow: scroll; overflow-y: hidden; overflow-x: hidden'
							onfocus="window.activeobj=this;this.clock=setInterval(function(){activeobj.style.height=activeobj.scrollHeight+'px';},200);"
							onblur="clearInterval(this.clock);" placeholder="添加评论"
							name="comment"></textarea>
						<p class="help-block text-danger"></p>
					</div>
				</div>
				<span><font size="2" color="gray">${error}${message}</font> </span> <br>
				<div id="success"></div>
				<div class="form-group">
					<button type="submit" name="submit" value="publish"
						class="btn btn-secondary">发表评论</button>
				</form>
				</div>
		</div>
	</div>
	</article>
	<%
		}
	%>
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

</body>

</html>
<%
	session.setAttribute("error", "");
	session.setAttribute("message", "");
%>