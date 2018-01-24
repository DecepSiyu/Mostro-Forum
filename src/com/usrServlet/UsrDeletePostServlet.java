package com.usrServlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.controller.LoginServlet;
import com.postBean.Post;
import com.usrBean.User;

@WebServlet("/UsrDeletePostServlet")

public class UsrDeletePostServlet extends HttpServlet {
	private static final String successPage = "welcome.jsp";
	private static final String failPage = "passage.jsp";
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UsrDeletePostServlet() {
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");

		HttpSession session = request.getSession();
		session.setAttribute("error", "");
		// session.
		Post post = (Post) session.getAttribute("post");
		User user = (User) session.getAttribute("user");
		if (deletePost(post, user)) {
			response.sendRedirect(successPage);
		} else {
			session.setAttribute("error", "您不具备删除此帖的权限");
			response.sendRedirect(failPage);
		}
	}

	public boolean deletePost(Post post, User user) {
		try {
			Connection connection = LoginServlet.connection;

			if (user.isAdmin() || user.getUsrname().equals(post.getAuther().trim())) {
				Statement statement = connection.createStatement();
				String sql = String.format("DELETE FROM post_info WHERE post_id=\'%s\';",
						post.getPostID());
				statement.execute(sql);
				System.out.printf("user:%s delete post:%s\n", user.getUsrname(), post.getPostID());
				statement.close();
				return true;
			}

		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		}
		return false;
	}
}
