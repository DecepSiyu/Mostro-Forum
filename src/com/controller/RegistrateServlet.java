package com.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.usrBean.User;

@WebServlet("/RegistrateServlet")
public class RegistrateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String successPage = "welcome.jsp";
	private static final String failPage = "login.jsp";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegistrateServlet() {
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
			session.setAttribute("username", username);
			session.setAttribute("user", storeRegInfo(username, passwd));
			System.out.println("new user " + username + " registrate");
			response.sendRedirect(successPage);
		}
	}

	public User storeRegInfo(String usrname, String password) {
		User user = new User();
		user.setUsrname(usrname);
		user.setPassword(password);

		try {
			LoginServlet.connection = LoginServlet.getDateBaseConn();
			Connection connection = LoginServlet.connection;
			Statement stmt = connection.createStatement();
			String sql = "insert into web_routine.usr_info (`usrname`, `passwd`,`is_admin`) values (\'" + usrname
					+ "\',\'" + password + "\',false)";
			stmt.execute(sql);

			return user;
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		}
		return user;
	}
}
