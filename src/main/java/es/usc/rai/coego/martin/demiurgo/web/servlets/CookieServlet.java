package es.usc.rai.coego.martin.demiurgo.web.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Cookie
 */
@WebServlet("/cookie")
public class CookieServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CookieServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		/*String path = "index";
		if(request.getParameter("path") != null)
			path = request.getParameter("path");
		
		request.getSession().removeAttribute("token");
		
		Cookie[] cookies = request.getCookies();
		for (int i = 0; i < cookies.length; i++) {
			Cookie cookie = cookies[i];
			if (cookie.getName().equals("demiurgoweb_token")) {
				
				UserData user = DemiurgoConnector.getInterface().me(cookie.getValue());
				if(user == null) { //the token doesn't work anymore
					Cookie newCookie = new Cookie("demiurgo_token", "");
					newCookie.setMaxAge(-10000000);
					response.addCookie(newCookie);
					request.getSession().removeAttribute("token");
					response.sendRedirect("login");
				}
				else {
					request.getSession().setAttribute("token", cookie.getValue());
					request.getSession().setAttribute("user", user);
					response.sendRedirect(path);
				}
				return;
			}
		}
		response.sendRedirect("login");*/
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
