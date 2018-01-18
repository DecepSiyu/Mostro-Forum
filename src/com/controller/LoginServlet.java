package com.controller;

import java.io.IOException;
import java.sql.Date;
import java.sql.DriverManager;
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

import com.postBean.Post;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String successPage = "welcome.jsp";
	private static final String failPage = "login.jsp";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
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
		String username = request.getParameter("usrname");
		String passwd = request.getParameter("passwd");

		login(request, response, username, passwd);

	}

	private static ArrayList<Post> loadPosts(Statement statement, int count) throws SQLException {
		statement.executeQuery(
				String.format("SELECT * FROM web_routine.post_info ORDER BY publish_time DESC LIMIT 0,%d ;", count));
		ResultSet resultSet = statement.getResultSet();
		ArrayList<Post> posts = new ArrayList<Post>(count);
		int i = 0;
		while (resultSet.next()) {
			String postID = resultSet.getString("post_id");
			Date date = resultSet.getDate("publish_time");
			String content = resultSet.getString("content");
			String auther = resultSet.getString("auther");
			String title = resultSet.getString("title");
			posts.add(new Post(postID, title, date, auther, content));
		}
		return posts;
	}

	public static com.usrBean.User checkLogin(HttpSession session, String usrname, String password) {
		String driverClass = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/?user=root";
		String DBUSER = "root";
		String PASSWORD = "menhui2012";
		try {
			Class.forName(driverClass);
			java.sql.Connection cn = DriverManager.getConnection(url, DBUSER, PASSWORD);
			Statement statement = cn.createStatement();
			String sql = "SELECT * from web_routine.usr_info where usrname=\'" + usrname + "\'";
			ResultSet resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				com.usrBean.User user = new com.usrBean.User();
				user.setBirthday(resultSet.getDate("birthday"));
				if (resultSet.getString("email") != null)
					user.setEmail(resultSet.getString("email").trim());
				user.setPassword(resultSet.getString("passwd").trim());
				if (resultSet.getString("sex") != null)
					user.setSex(resultSet.getString("sex").trim());
				user.setUsrname(resultSet.getString("usrname").trim());
				user.setAdmin(resultSet.getBoolean("is_admin"));
				if (usrname.equals(user.getUsrname()) && password.equals(user.getPassword())) {
					session.setAttribute("posts", loadPosts(statement, 200)); // 载入帖子列表
					return user;
				}
			}

			resultSet.close();
			cn.close();
			return null;
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			ex.printStackTrace();
			return null;
		}

	}

	public void login(HttpServletRequest request, HttpServletResponse response, String username, String passwd)
			throws IOException {
		HttpSession session = request.getSession();
		session.setAttribute("error", "");
		session.setAttribute("message", "");
		com.usrBean.User user = null;
		if ((user = checkLogin(session, username, passwd)) != null) {
			session.setAttribute("username", username);
			session.setAttribute("user", user);
			System.out.println(username + " login");
			response.sendRedirect(successPage);
		} else {
			if (username.equals("")) {
				session.setAttribute("error", "用户名不能为空");
			} else if (passwd.equals("")) {
				session.setAttribute("error", "密码不能为空");
			} else {
				session.setAttribute("error", "用户名、密码不匹配");
			}
			response.sendRedirect(failPage);
		}
	}

}
