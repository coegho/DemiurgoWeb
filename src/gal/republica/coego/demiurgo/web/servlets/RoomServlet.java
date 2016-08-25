package gal.republica.coego.demiurgo.web.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gal.republica.coego.demiurgo.lib.Decision;
import gal.republica.coego.demiurgo.lib.ServerInterface;
import gal.republica.coego.demiurgo.lib.WorldRoomData;
import gal.republica.coego.demiurgo.web.DemiurgoConnector;

/**
 * Servlet implementation class RoomServlet
 */
@WebServlet("/room")
public class RoomServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RoomServlet() {
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
		if (token == null) {
			response.sendRedirect("cookie");
		} else {
			String path = request.getParameter("room");
			if (path == null) {
				response.sendRedirect("index");
			} else {
				ServerInterface rem = DemiurgoConnector.getInterface();

				if (path == "noroom") { // No room path provided

					List<Decision> decisions = rem.getNoRoomDecisions(token);

					request.setAttribute("path", "The Void");
					request.setAttribute("decisions", decisions);

					RequestDispatcher view = request.getRequestDispatcher("jsp/noroom.jsp");
					view.forward(request, response);
				} else { // Room path provided
					WorldRoomData room = rem.checkRoom(token, path);

					request.setAttribute("room", room);

					RequestDispatcher view = request.getRequestDispatcher("jsp/room.jsp");
					view.forward(request, response);
				}
			}
		}

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
