package gal.republica.coego.demiurgo.web.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gal.republica.coego.demiurgo.lib.ServerInterface;
import gal.republica.coego.demiurgo.lib.UserData;
import gal.republica.coego.demiurgo.web.DemiurgoConnector;

/**
 * Servlet implementation class GameMasterServlet
 */
@WebServlet("/gamemaster")
public class GameMasterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GameMasterServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String token = (String) request.getSession().getAttribute("token");
		UserData user = (UserData) request.getSession().getAttribute("user");
		if (token == null || user == null) {
			response.sendRedirect("cookie");
		} else {
			ServerInterface h = DemiurgoConnector.getInterface();
			List<String> p = h.getPendingRooms(token); //TODO: decisions without room
			if(h.getNoRoomDecisions(token).size() > 0)
				p.add("noroom");
			request.setAttribute("pending", p);
			RequestDispatcher view= request.getRequestDispatcher("jsp/gamemaster.jsp");
			view.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
