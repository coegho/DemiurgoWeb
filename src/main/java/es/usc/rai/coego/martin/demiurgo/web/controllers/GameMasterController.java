package es.usc.rai.coego.martin.demiurgo.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

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
	
	@ModelAttribute("user")
	public LoggedUser getUser() {
		return user;
	}

	@RequestMapping
	public String SeePanel(Model model) {
		GetPendingRoomsResponse res =  dc.doGet(user.getToken(), "pendingrooms", GetPendingRoomsResponse.class);
		model.addAttribute("pendingRooms",res.getPendingRooms());
		model.addAttribute("numUsers", res.getNumUsers());
		model.addAttribute("noObjUsers", res.getNoObjUsers());
		
		return "gmpanel";
	}
}
