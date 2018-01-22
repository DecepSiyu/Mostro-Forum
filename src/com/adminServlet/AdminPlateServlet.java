package com.adminServlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.postBean.Plate;

@WebServlet("/AdminRemovePlateServlet")
public class AdminPlateServlet extends HttpServlet {

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
		// ����ҳ����룺
		// ɾ��������ݿ���룺
	}

	// ������������ʵ�������ݿ����ӹ���
	public void removePlate() {
		String driverClass = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
		String url = "jdbc:sqlserver://localhost:1433; DatabaseName = BBSM";
		String DBUSER = "Ting";
		String PASSWORD = "zt18798859427";
		try {
			Class.forName(driverClass);
			Connection cn = DriverManager.getConnection(url, DBUSER, PASSWORD);
			Statement stmt = cn.createStatement();
			// String sql="SELECT usrname, password from usr_info where
			// usrname=\'"+usrname+"\'";
			// ResultSet rs=stmt.executeQuery(sql);
			System.out.println("���ݿ����ӳɹ�");

			cn.close();// �رղ���
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			System.out.println("�����쳣");
			ex.printStackTrace();
		}
	}

	public static ArrayList<Plate> loadPlates(Connection connection) {
		return null;
	}
}
