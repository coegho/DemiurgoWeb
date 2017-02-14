package es.usc.rai.coego.martin.demiurgo.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import es.usc.rai.coego.martin.demiurgo.json.MyUserResponse;
import es.usc.rai.coego.martin.demiurgo.web.beans.DemiurgoConnector;
import es.usc.rai.coego.martin.demiurgo.web.beans.LoggedUser;

@Controller
public class UserStatusController {
	@Autowired
	LoggedUser user;

	@Autowired
	DemiurgoConnector dc;
	
	@GetMapping("/status")
	public String seeStatus(Model model) {
		MyUserResponse res = dc.doGet(user.getToken(), "me", MyUserResponse.class);
		model.addAttribute("user", res.getUser());
		model.addAttribute("obj", res.getObj());
		return "status";
	}
}
