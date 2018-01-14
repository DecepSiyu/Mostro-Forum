package Mostro.Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/PublishServlet")
public class PublishServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String successPage = "#";
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
			System.out.println("**************title**************\n" + title);
			System.out.println("**************content**************\n" + content);
			response.sendRedirect(successPage);// TODO:跳转到哪？
		}
	}

}
