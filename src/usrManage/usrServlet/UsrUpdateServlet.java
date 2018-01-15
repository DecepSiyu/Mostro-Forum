package usrManage.usrServlet;

import java.io.IOException;
import java.sql.*;

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

public class UsrUpdateServlet extends HttpServlet{

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
		String usrname = request.getParameter("usrname");
		String passwd = request.getParameter("passwd");
		Date birthday= Date.valueOf(request.getParameter("birthday"));
		String sex= request.getParameter("sex");
		String email= request.getParameter("Email");
		//页面代码：(注意性别约束，email约束)
		
		//修改信息：性别、邮箱、生日，根据username查找
		update(usrname, birthday,sex,email);
	}

	public void update(String usrname,  Date birthday, String sex, String email)
	{
		String driverClass = "com.microsoft.sqlserver.jdbc.SQLServerDriver";  
		String url = "jdbc:sqlserver://localhost:1433; DatabaseName = BBSM";  
		String DBUSER="Ting";
		String PASSWORD="zt18798859427";
		try{
			Class.forName(driverClass);
		    java.sql.Connection cn=DriverManager.getConnection(url,DBUSER,PASSWORD);
			Statement stmt=cn.createStatement();
		    String sql="update usr_info set sex=\'"+
		    		sex+"\' , birthday=\'"+birthday+"\' ,Email=\'"+email+"\'"+
		    		"where usrname=\'"+usrname+"\'";
		   stmt.execute(sql);
		   cn.close();
		}
		catch(Exception ex){
		System.out.println(ex.getMessage());
		System.out.println("连接异常");
		ex.printStackTrace();
		}
	
	}
}
