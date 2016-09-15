package es.usc.rai.coego.martin.demiurgo.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/gamemaster")
public class GameMasterController {

	public String doSom() {
		/*String token = (String) request.getSession().getAttribute("token");
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
		}*/
		return "gamemaster";
	}
}
