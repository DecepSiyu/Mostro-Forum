package adminManage.adminServlet;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/AdminDeletePostServlet")
public class AdminDeletePostServlet extends HttpServlet{

	public AdminDeletePostServlet() {
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
		//还差页面代码：
		//删除帖子数据库代码：
	}
	
	//参数待定，仅实现了数据库链接功能
		public void deletePost()
		{
			String driverClass = "com.microsoft.sqlserver.jdbc.SQLServerDriver";  
			String url = "jdbc:sqlserver://localhost:1433; DatabaseName = BBSM";  
			String DBUSER="Ting";
			String PASSWORD="zt18798859427";
			try{
				Class.forName(driverClass);
			    java.sql.Connection cn=DriverManager.getConnection(url,DBUSER,PASSWORD);
				Statement stmt=cn.createStatement();
			  //  String sql="SELECT usrname, password from usr_info where usrname=\'"+usrname+"\'";
				//ResultSet rs=stmt.executeQuery(sql);
				System.out.println("数据库链接成功");
			    
			    cn.close();//关闭操作
			}
			catch(Exception ex){
			System.out.println(ex.getMessage());
			System.out.println("连接异常");
			ex.printStackTrace();
			}
		}


}
