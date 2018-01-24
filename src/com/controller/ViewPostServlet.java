package com.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
			String sql = String.format("SELECT * from post_info where post_id=\'%s\'", postID);
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
}
