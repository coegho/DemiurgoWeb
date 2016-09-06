package es.usc.rai.coego.martin.demiurgo.web.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SeeAllRoomsServlet
 */
@WebServlet(description = "See all the roompaths of the current world", urlPatterns = { "/seeallrooms" })
public class SeeAllRoomsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SeeAllRoomsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String token = (String) request.getSession().getAttribute("token");
		/*UserData user = (UserData) request.getSession().getAttribute("user");
		if (token == null || user == null) {
			response.sendRedirect("cookie");
		} else {
			RoomPathData p = DemiurgoConnector.getInterface().getAllRoomPaths(token);
			request.setAttribute("paths", p.iterator());
			RequestDispatcher view= request.getRequestDispatcher("jsp/seeallrooms.jsp");
			view.forward(request, response);
		}*/
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
