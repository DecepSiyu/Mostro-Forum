package Mostro.Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String successPage = "welcome.jsp";
	private static final String failPage = "login.jsp";

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
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
		// TODO Auto-generated method stub
		response.setContentType("text/html; charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		String user = request.getParameter("usrname");
		String passwd = request.getParameter("passwd");

		HttpSession session = request.getSession();

		if (user.equals("mostro") && passwd.equals("123")) {
			session.setAttribute("user", user);
			response.sendRedirect(successPage);
		} else {
			if (user.equals("")) {
				session.setAttribute("error", "«Î ‰»Î”√ªß√˚");
			} else if (passwd.equals("")) {
				session.setAttribute("error", "«Î ‰»Î√‹¬Î");
			} else {
				session.setAttribute("error", "√‹¬Î¥ÌŒÛ");
			}
			response.sendRedirect(failPage);
		}
	}

}
