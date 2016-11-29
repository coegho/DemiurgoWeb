package es.usc.rai.coego.martin.demiurgo.web.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.HttpClientErrorException;

import es.usc.rai.coego.martin.demiurgo.json.GetPendingRoomsResponse;
import es.usc.rai.coego.martin.demiurgo.web.beans.DemiurgoConnector;
import es.usc.rai.coego.martin.demiurgo.web.beans.LoggedUser;

@Controller
@RequestMapping("/gm")
public class GameMasterController {
	@Autowired
	LoggedUser user;
	@Autowired
	DemiurgoConnector dc;

	@RequestMapping
	public String SeePanel(Model model) {
		if (user.getToken() == null) {
			return "redirect:/cookie";
		}
		try {
			model.addAttribute("username", user.getName());

			GetPendingRoomsResponse res =  dc.doGet(user.getToken(), "pendingrooms", GetPendingRoomsResponse.class);
			List<String> pending = res.getPendingRooms();
			
			model.addAttribute("pendingtotal", pending.size());
			if(!pending.isEmpty()) {
				model.addAttribute("pending", pending);
			}
			
			return "gamemaster";
		} catch (HttpClientErrorException ex) {
			System.out.println(ex.getLocalizedMessage()); // TODO
			if (ex.getStatusCode() == HttpStatus.FORBIDDEN) {

			}
		}
		return "gamemaster";
	}
}
