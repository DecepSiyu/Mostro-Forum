package usrManage.usrServlet;

import java.io.IOException;
import java.sql.Date;
import java.sql.DriverManager;
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

		String usrname = (String) session.getAttribute("user");
		String strofDate = request.getParameter("birthday");// TODO 修改
		Date birthday = Date.valueOf(strofDate);
		String sex = request.getParameter("sex");
		String email = request.getParameter("Email");

		// update(usrname, birthday, sex, email);

		session.setAttribute("user_email", email);
		session.setAttribute("user_birthday", birthday.toString());
		session.setAttribute("user_sex", sex);

		response.sendRedirect(successPage);
	}

	public void update(String usrname, Date birthday, String sex, String email) {
		String driverClass = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
		String url = "jdbc:sqlserver://localhost:1433; DatabaseName = BBSM";
		String DBUSER = "Ting";
		String PASSWORD = "zt18798859427";
		try {
			Class.forName(driverClass);
			java.sql.Connection cn = DriverManager.getConnection(url, DBUSER, PASSWORD);
			Statement stmt = cn.createStatement();
			String sql = "update usr_info set sex=\'" + sex + "\' , birthday=\'" + birthday + "\' ,Email=\'" + email
					+ "\'" + "where usrname=\'" + usrname + "\'";
			stmt.execute(sql);
			cn.close();
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			ex.printStackTrace();
		}
	}
}
