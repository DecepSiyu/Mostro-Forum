package com.usrServlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.usrBean.User;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/UsrUpdateServlet")

public class UsrUpdateServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String successPage = "information.jsp";

	public UsrUpdateServlet() {
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

		String usrname = (String) session.getAttribute("username");

		String textOfBirthday = request.getParameter("birthday");
		String sex = (String) request.getParameter("sex");
		String email = (String) request.getParameter("email");
		update(session, usrname, textOfBirthday, sex, email);
		session.setAttribute("message", "信息修改成功");
		response.sendRedirect(successPage);
	}

	public static User loadUsrMsg(Connection connection, String username) throws SQLException {
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement
				.executeQuery(String.format("SELECT * from web_routine.usr_info where usrname=\'%s\'", username));
		resultSet.next();
		User user = new User();
		user.setBirthday(resultSet.getDate("birthday"));
		user.setAdmin(resultSet.getBoolean("is_admin"));
		user.setEmail(resultSet.getString("email").trim());
		user.setPassword(resultSet.getString("passwd").trim());
		user.setSex(resultSet.getString("sex").trim());
		user.setUsrname(username);
		return user;
	}

	public static void updateUsrMsg(Connection connection, User user) throws SQLException {
		Statement statement = connection.createStatement();

		statement.execute(String.format(
				"UPDATE web_routine.usr_info SET email=\'%s\',sex=\'%s\',birthday=\'%s\' WHERE usrname=\'%s\';",
				user.getEmail(), user.getSex(), user.getBirthday(), user.getUsrname()));

		System.out.println(
				user.getUsrname() + " info :" + user.getEmail() + " " + user.getBirthday() + " " + user.getSex());

	}

	public void update(HttpSession session, String usrname, String textOfBirthday, String sex, String email) {
		String driverClass = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/?user=root";
		String DBUSER = "root";
		String PASSWORD = "menhui2012";
		try {
			Class.forName(driverClass);
			java.sql.Connection cn = DriverManager.getConnection(url, DBUSER, PASSWORD);

			User user = loadUsrMsg(cn, usrname);
			if (email.isEmpty() && user.getEmail() != null) {
				email = user.getEmail();
			}
			if (sex.isEmpty() && user.getSex() != null) {
				sex = user.getSex();
			}
			if (textOfBirthday.isEmpty() && user.getBirthday() != null) {
				textOfBirthday = user.getBirthday().toString();
			}

			user.setBirthday(Date.valueOf(textOfBirthday));
			user.setEmail(email);
			user.setSex(sex);

			updateUsrMsg(cn, user);
			cn.close();

			session.setAttribute("user", user);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		}
	}
}
