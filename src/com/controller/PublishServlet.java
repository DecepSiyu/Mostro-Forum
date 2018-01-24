package com.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.UUID;

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
	private static final String successPage = "welcome.jsp";
	private static final String failPage = "post.jsp";
	private HttpSession session;
	private HttpServletResponse response;

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
		session = request.getSession();
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

	public void storePostInfo(String title, String platename, String content, String username) throws IOException {
		if (platename.length() > 50) {
			session.setAttribute("error", "板块名过长");
			response.sendRedirect(failPage);
		}

		try {
			Connection connection = LoginServlet.connection;
			Statement statement = connection.createStatement();

			ResultSet resultSet = statement
					.executeQuery("SELECT plate_id FROM plate_info WHERE name=\'" + platename + "\';");

			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String post_id, plate_id;
			post_id=UUID.randomUUID().toString().replace("-", "");
			plate_id=UUID.randomUUID().toString().replace("-", "");
			if(!resultSet.next()) {
				String sql = String.format(
						"INSERT INTO post_info "
								+ "(post_id,title,content,auther,plate_id,publish_time)"
								+ " values (\'%s\',\'%s\',\'%s\',\'%s\',\'%s\',\'%s\')",
						post_id,title, content, username, plate_id,simpleDateFormat.format(new java.util.Date()));
				statement.execute(sql);// 随机生成帖子ID和板块ID

				sql = String.format("SELECT plate_id from post_info WHERE title=\'%s\';", title);
				statement = connection.createStatement();
				resultSet = statement.executeQuery(sql);// 找到刚刚生成的板块ID
				resultSet.next();
				String plateID = resultSet.getString(1);

				sql = String.format("INSERT INTO plate_info (plate_id,name) VALUES(\'%s\',\'%s\');",
						plateID, platename);
				statement.execute(sql);// 生成板块
				statement.close();
			} else {
				String plateID = resultSet.getString(1);

				String sql = String.format(
						"INSERT INTO post_info "
								+ "(post_id,title, content,auther,plate_id,publish_time)"
								+ " values (LEFT(MD5(RAND()),10),\'%s\',\'%s\',\'%s\',\'%s\',\'%s\')",
						title, content, username, plateID, simpleDateFormat.format(new java.util.Date()));

				statement.execute(sql);
				statement.close();
			}
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		}
	}
}
