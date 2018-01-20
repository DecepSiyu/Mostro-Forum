package com.controller;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.usrBean.User;

@WebServlet("/PublishServlet")
public class PublishServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String successPage = "post.jsp";
	private static final String failPage = "post.jsp";

	public PublishServlet() {
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
		String title = request.getParameter("title");
		String plate = request.getParameter("plate");
		String content = request.getParameter("content");
		HttpSession session = request.getSession();
		session.setAttribute("error", "");
		session.setAttribute("message", "");
		if (title.equals("")) {
			session.setAttribute("error", "标题不能为空");
			session.setAttribute("content", content);
			response.sendRedirect(failPage);
		} else if (content.equals("")) {
			session.setAttribute("error", "正文不能为空");
			session.setAttribute("title", title);
			response.sendRedirect(failPage);
		} else {
			User user = (User) session.getAttribute("user");
			storePostInfo(title, plate, content, user.getUsrname());
			System.out.println("**************title**************\n" + title);
			System.out.println("**************plate**************\n" + plate);
			System.out.println("**************content**************\n" + content);

			session.setAttribute("message", "发布成功");
			response.sendRedirect(successPage);
		}
	}

	public void storePostInfo(String title, String plate, String content, String username) {
		String driverClass = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/?user=root";
		String DBUSER = "root";
		String PASSWORD = "menhui2012";
		try {
			Class.forName(driverClass);
			java.sql.Connection cn = DriverManager.getConnection(url, DBUSER, PASSWORD);
			Statement stmt = cn.createStatement();

			ResultSet resultSet = stmt
					.executeQuery("SELECT plate_id FROM web_routine.plate_info WHERE name=\'" + plate + "\';");
			resultSet.next();
			String plateID = resultSet.getString(1);
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

			String sql = String.format(
					"INSERT INTO web_routine.post_info "
							+ "(`post_id`,`title`, `content`,`auther`,`plate_id`,`publish_time`)"
							+ " values (LEFT(MD5(RAND()),10),\'%s\',\'%s\',\'%s\',\'%s\',\'%s\')",
					title, content, username, plateID, simpleDateFormat.format(new java.util.Date()));

			stmt.execute(sql);
			cn.close();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		}
	}
}
