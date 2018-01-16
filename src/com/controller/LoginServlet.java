package com.controller;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		String user = request.getParameter("usrname");
		String passwd = request.getParameter("passwd");

		login(request, response, user, passwd);

	}

	public static boolean checkLogin(String usrname, String password) {
		String driverClass = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
		String url = "jdbc:sqlserver://localhost:1433; DatabaseName = BBSM";
		String DBUSER = "Ting";
		String PASSWORD = "zt18798859427";
		try {
			Class.forName(driverClass);
			java.sql.Connection cn = DriverManager.getConnection(url, DBUSER, PASSWORD);
			Statement stmt = cn.createStatement();
			String sql = "SELECT usrname, password from usr_info where usrname=\'" + usrname + "\'";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				String db_usrname = rs.getString("usrname").trim();
				String db_password = rs.getString("password").trim();
				if (usrname.equals(db_usrname) && password.equals(db_password)) {
					return true;
				}
			}

			rs.close();
			cn.close();
			return false;
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			ex.printStackTrace();
			return false;
		}

	}

	public void login(HttpServletRequest request, HttpServletResponse response, String username, String passwd)
			throws IOException {
		HttpSession session = request.getSession();
		session.setAttribute("error", "");
		session.setAttribute("message", "");
		if (username.equals("mostro") && passwd.equals("123")) {// TODO 替换
			session.setAttribute("user", username);
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
