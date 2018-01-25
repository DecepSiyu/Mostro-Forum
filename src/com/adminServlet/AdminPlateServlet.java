package com.adminServlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.controller.LoginServlet;
import com.postBean.Plate;

@WebServlet("/AdminRemovePlateServlet")
public class AdminPlateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String successPage = "search.jsp";

	public AdminPlateServlet() {
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
		String plateID = (String) request.getParameter("plate_id");
		try {
			removePlate(LoginServlet.connection, plateID);
			response.sendRedirect(successPage);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void removePlate(Connection connection, String plateID) throws SQLException {
		connection = LoginServlet.connection;
		Statement stmt = connection.createStatement();
		String sql = "delete from web_routine.plate_info where plate_id=\'" + plateID + "\'";
		stmt.execute(sql);
		System.out.printf("delete plate %s\n", plateID);
	}

	public static ArrayList<Plate> loadPlates(Connection connection) throws SQLException {
		if (connection == null) {
			return null;
		}

		ArrayList<Plate> arraylist = new ArrayList<Plate>();
		Statement stmt = connection.createStatement();
		String sql = "SELECT * from web_routine.plate_info ";
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()) {
			Plate plate = new Plate(rs.getString("plate_id"), rs.getString("name"));
			arraylist.add(plate);
		}
		rs.close();
		return arraylist;
	}

	public static int getPostNum(Connection connection, String plate_id) throws SQLException {
		connection = LoginServlet.connection;
		Statement stmt = connection.createStatement();
		String sql = "SELECT count(*) from web_routine.post_info where plate_id=\'" + plate_id + "\'";
		ResultSet rs = stmt.executeQuery(sql);
		rs.next();
		int sum = rs.getInt(1);
		rs.close();
		return sum;

	}

}