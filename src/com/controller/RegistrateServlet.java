package com.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/RegistrateServlet")
public class RegistrateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String successPage = "welcome.jsp";
	private static final String failPage = "login.jsp";

	/**
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * @see HttpServlet#HttpServlet()
	 */
	public RegistrateServlet() throws ClassNotFoundException, SQLException {
		super();
		LoginServlet.connection = LoginServlet.getDateBaseConn();
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
		// TODO Auto-generated method stub
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		String username = request.getParameter("usrname");
		String passwd = request.getParameter("passwd_1");
		String passwd_again = request.getParameter("passwd_2");

		registrate(request, response, username, passwd, passwd_again);
	}

	public void registrate(HttpServletRequest request, HttpServletResponse response, String username, String passwd,
			String passwd_again) throws IOException {
		HttpSession session = request.getSession();
		session.setAttribute("error", "");
		session.setAttribute("message", "");
		if (username.equals("")) {
			session.setAttribute("error", "用户名不能为空");
			response.sendRedirect(failPage);
		} else if (passwd.equals("")) {
			session.setAttribute("error", "密码不能为空");
			response.sendRedirect(failPage);
		} else if (!passwd.equals(passwd_again)) {
			session.setAttribute("error", "两次输入密码不一致");
			response.sendRedirect(failPage);
		} else {
			System.out.println("new user " + username + " registrate");
			session.setAttribute("username", username);
			storeRegInfo(username, passwd);
			response.sendRedirect(successPage);
		}
	}

	public void storeRegInfo(String usrname, String password) {
		try {
			Connection connection = LoginServlet.connection;
			Statement stmt = connection.createStatement();
			String sql = "insert into usr_info (usrname, passwd,is_admin) values (\'" + usrname
					+ "\',\'" + password + "\',0)";
			stmt.execute(sql);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		}
	}
}
