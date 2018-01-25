package com.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.postBean.Comment;
import com.postBean.Post;

@WebServlet("/ViewPostServlet")
public class ViewPostServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ViewPostServlet() {
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
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		String postID = request.getParameter("post_id");
		postID = postID.trim();
		Post post = findPost(postID);
		HttpSession session = request.getSession();
		session.setAttribute("post", post);
		System.out.println("view:" + postID);
		response.sendRedirect("passage.jsp");

	}

	private Post findPost(String postID) {
		try {
			Connection connection = LoginServlet.connection;
			Statement statement = connection.createStatement();
			String sql = String.format("SELECT * from web_routine.post_info where post_id=\'%s\'", postID);
			ResultSet resultSet = statement.executeQuery(sql);
			resultSet.next();

			Post post = new Post(resultSet.getString("post_id"), resultSet.getString("title"),
					resultSet.getDate("publish_time"), resultSet.getString("auther"), resultSet.getString("content"));

			resultSet.close();
			return post;
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			ex.printStackTrace();
			return null;
		}
	}

	public static ArrayList<Comment> loadComment(Connection connection, int count, String post_id) throws SQLException {
		if (connection == null) {
			return null;
		}
		Statement statement = connection.createStatement();
		statement.executeQuery(String.format(
				"SELECT * FROM web_routine.comment_info where post_id=\'%s\' ORDER BY publish_time DESC LIMIT 0,%d ;",
				post_id, count));
		ResultSet resultSet = statement.getResultSet();
		ArrayList<Comment> comments = new ArrayList<Comment>(count);
		while (resultSet.next()) {
			String postID = resultSet.getString("post_id");
			Date date = resultSet.getDate("publish_time");
			String content = resultSet.getString("content");
			String auther = resultSet.getString("auther");
			String commentID = resultSet.getString("comment_id");
			Comment comment = new Comment(commentID, postID, date, content, auther);
			comments.add(comment);
		}
		return comments;
	}

}
