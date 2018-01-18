package com.usrServlet;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.usrBean.User;

@WebServlet("/UsrPasswdChangeServlet")

public class UsrPasswdChangeServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String successPage = "information.jsp";
	private static final String failPage = "information.jsp";

	public UsrPasswdChangeServlet() {
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
		HttpSession session = request.getSession();
		String old_passwd_input = request.getParameter("old-passwd");
		String new_passwd = request.getParameter("new-passwd");
		String new_passwd_again = request.getParameter("new-passwd-again");
		User user = (User) session.getAttribute("user");

		session.setAttribute("error", "");
		session.setAttribute("message", "");

		if (old_passwd_input.equals(user.getPassword())) {
			if (new_passwd.equals(new_passwd_again)) {
				System.out.println(user.getUsrname() + " new password: " + new_passwd);
				passwdChange(user.getUsrname(), new_passwd);
				session.setAttribute("message", "密码修改成功");
				response.sendRedirect(successPage);
			} else {
				session.setAttribute("error", "两次输入密码不一致");
				response.sendRedirect(failPage);
			}

		} else {
			session.setAttribute("error", "密码错误");
			response.sendRedirect(failPage);
		}

	}

	public void passwdChange(String usrname, String passwd) {
		String driverClass = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/?user=root";
		String DBUSER = "root";
		String PASSWORD = "menhui2012";
		try {
			Class.forName(driverClass);
			java.sql.Connection cn = DriverManager.getConnection(url, DBUSER, PASSWORD);
			Statement stmt = cn.createStatement();
			String sql = "UPDATE web_routine.usr_info SET passwd=\'" + passwd + "\' where usrname=\'" + usrname + "\'";
			stmt.execute(sql);
			cn.close();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		}
	}
}
