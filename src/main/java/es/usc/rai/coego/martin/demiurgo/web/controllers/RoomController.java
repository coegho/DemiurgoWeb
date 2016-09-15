package es.usc.rai.coego.martin.demiurgo.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/room")
public class RoomController {

	@RequestMapping
	public String getNoRoom() {
		return "room";
	}
	
	@RequestMapping("{path}")
	public String getRoom(@PathVariable(value="path")String path) {
		/*String token = (String) request.getSession().getAttribute("token");
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
		}*/
		
		return "room";
	}
}
