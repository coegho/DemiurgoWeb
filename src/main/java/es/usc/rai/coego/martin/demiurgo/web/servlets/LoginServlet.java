package es.usc.rai.coego.martin.demiurgo.web.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Login
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getSession().getAttribute("token") != null)
			response.sendRedirect("index");
		else {
			request.setAttribute("error", false);
			RequestDispatcher view = request.getRequestDispatcher("jsp/login.jsp");
			view.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*String world = request.getParameter("world");
		String username = request.getParameter("username");
		String pass = request.getParameter("pass");

		String token = DemiurgoConnector.getInterface().login(world, username, pass);
		if (token != null) {
			request.getSession().setAttribute("token", token);
			DemiurgoConnector.getInterface().checkRoom(token, "/escenario1");
			UserData user = DemiurgoConnector.getInterface().me(token);
			request.getSession().setAttribute("user", user);
			Cookie cookie = new Cookie("demiurgoweb_token", token);
			cookie.setComment("Auth token for DemiurgoWeb");
			cookie.setMaxAge(60 * 60 * 24 * 7);
			response.addCookie(cookie);
			response.sendRedirect("index");
		} else {
			request.setAttribute("error", true);
			RequestDispatcher view = request.getRequestDispatcher("jsp/login.jsp");
			view.forward(request, response);
		}*/
	}

}
