package es.usc.rai.coego.martin.demiurgo.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import es.usc.rai.coego.martin.demiurgo.web.beans.DemiurgoConnector;
import es.usc.rai.coego.martin.demiurgo.web.beans.LoggedUser;

@Controller
public class RoomController {
	@Autowired
	LoggedUser user;
	
	@Autowired
	DemiurgoConnector dc;
	
	@GetMapping("/room")
	public String getRoom(@RequestParam(value="path")String path) {
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
