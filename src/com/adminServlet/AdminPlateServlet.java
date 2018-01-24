package com.adminServlet;

import java.io.IOException;
import java.sql.Connection;
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

import com.controller.LoginServlet;
import com.postBean.Plate;
import com.postBean.*;
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
		String plate_id= (String)request.getParameter("plate_id");
		try {
			removePlate(LoginServlet.connection, plate_id);
			response.sendRedirect(successPage);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}

	public void removePlate(Connection connection, String plate_id) throws SQLException {
		connection=LoginServlet.connection;
		Statement stmt = connection.createStatement();
		 String sql="delete from plate_info where plate_id=\'"+plate_id+"\'";
		 stmt.execute(sql);
		 System.out.println("成功删除板块");
	}

	public static ArrayList<Plate> loadPlates(Connection connection) throws SQLException {
		ArrayList<Plate> arraylist= new ArrayList<Plate>();
		connection=LoginServlet.connection;
		Statement stmt = connection.createStatement();
		 String sql="SELECT * from plate_info ";
		 ResultSet rs=stmt.executeQuery(sql);
		while(rs.next())
		{
			Plate plate= new Plate(rs.getString("plate_id"),rs.getString("name"));
			arraylist.add(plate);
		}
		rs.close();
		return arraylist;
	}
	
	public static int getPostNum(Connection connection, String plate_id) throws SQLException
	{
		connection=LoginServlet.connection;
		Statement stmt = connection.createStatement();
		 String sql="SELECT count(*) from post_info where plate_id=\'"+plate_id+"\'";
		 ResultSet rs=stmt.executeQuery(sql);
		 rs.next();
		int sum=rs.getInt(1);
		rs.close();
		return sum;
	
	}
	
}
