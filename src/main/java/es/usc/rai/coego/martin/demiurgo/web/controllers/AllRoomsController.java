package es.usc.rai.coego.martin.demiurgo.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/seeallrooms")
public class AllRoomsController {

	public String seeAllRooms() {
		/*String token = (String) request.getSession().getAttribute("token");
		UserData user = (UserData) request.getSession().getAttribute("user");
		if (token == null || user == null) {
			response.sendRedirect("cookie");
		} else {
			RoomPathData p = DemiurgoConnector.getInterface().getAllRoomPaths(token);
			request.setAttribute("paths", p.iterator());
			RequestDispatcher view= request.getRequestDispatcher("jsp/seeallrooms.jsp");
			view.forward(request, response);
		}*/
		return "seeallrooms";
	}
}
