package es.usc.rai.coego.martin.demiurgo.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import es.usc.rai.coego.martin.demiurgo.web.beans.LoggedUser;

@Controller
public class IndexController {
	@Autowired
	LoggedUser user;
	
	@RequestMapping({"/", "/index"})
	public String redirect() {
		return "redirect:/" + user.getRole();
	}
}
