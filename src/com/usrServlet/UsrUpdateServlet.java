package com.usrServlet;

import java.io.IOException;
import java.sql.Date;
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

	public void update(HttpSession session, String usrname, String textOfBirthday, String sex, String email) {
		String driverClass = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/?user=root";
		String DBUSER = "root";
		String PASSWORD = "menhui2012";
		try {
			Class.forName(driverClass);
			java.sql.Connection cn = DriverManager.getConnection(url, DBUSER, PASSWORD);
			Statement stmt = cn.createStatement();

			ResultSet rs = stmt.executeQuery("SELECT * from web_routine.usr_info where usrname=\'" + usrname + "\'");
			rs.next();
			String baseEmail = rs.getString("email");
			if (email.isEmpty() && baseEmail != null) {
				email = baseEmail.trim();
			}
			String baseSex = rs.getString("sex");
			if (sex.isEmpty() && baseSex != null) {
				sex = baseSex.trim();
			}
			Date baseBirthday = rs.getDate("birthday");
			if (textOfBirthday.isEmpty() && baseBirthday != null) {
				textOfBirthday = baseBirthday.toString();
			}

			stmt.execute("UPDATE web_routine.usr_info SET email=\'" + email + "\',sex=\'" + sex + "\' , birthday=\'"
					+ textOfBirthday + "\' WHERE usrname=\'" + usrname + "\';");
			System.out.println(usrname + " info :" + textOfBirthday + " " + email + " " + sex);

			cn.close();
			session.setAttribute("user_sex", sex);
			session.setAttribute("user_birthday", textOfBirthday);
			session.setAttribute("user_email", email);

		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		}
	}
}
