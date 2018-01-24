package com.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.postBean.Post;
import com.usrBean.User;

@WebServlet("/CommentServlet")
public class CommentServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private static final String successPage = "passage.jsp";
	private static final String failPage = "passage.jsp";
	private HttpSession session;
	private HttpServletResponse response;

	public CommentServlet() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		session = request.getSession();
		String content = request.getParameter("comment");
		Post post = (Post) session.getAttribute("post");
		session.setAttribute("error", "");
		session.setAttribute("message", "");
		if (content.equals("")) {
			session.setAttribute("error", "评论不能为空");
			response.sendRedirect(failPage);
		} else {
			User user = (User) session.getAttribute("user");
			System.out.println("**************content**************\n" + content);
			System.out.println("**************author**************\n" + user.getUsrname());
			try {
				publishComment(user.getUsrname(), content, post.getPostID());
				session.setAttribute("message", "评论成功");
				response.sendRedirect(successPage);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void publishComment(String auther,  String content, String post_id) throws SQLException
	{
		Connection connection = LoginServlet.connection;
		Statement statement = connection.createStatement();
		String comment_id=UUID.randomUUID().toString().replace("-", "");
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String sql= String.format("insert into comment_info (comment_id, auther, publish_time, content, post_id) "
				+ "values (\'%s\',\'%s\',\'%s\',\'%s\',\'%s\')",comment_id,auther,
				simpleDateFormat.format(new java.util.Date()),content, post_id );
		statement.execute(sql);
	}
}
