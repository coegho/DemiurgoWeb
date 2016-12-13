package es.usc.rai.coego.martin.demiurgo.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.HttpClientErrorException;

import es.usc.rai.coego.martin.demiurgo.json.GetPendingRoomsResponse;
import es.usc.rai.coego.martin.demiurgo.web.beans.DemiurgoConnector;
import es.usc.rai.coego.martin.demiurgo.web.beans.LoggedUser;
import es.usc.rai.coego.martin.demiurgo.web.forms.GmPanelForm;

@Controller
@RequestMapping("/gm")
public class GameMasterController {
	@Autowired
	LoggedUser user;
	@Autowired
	DemiurgoConnector dc;
	
	@ModelAttribute("username")
	public String getUsername() {
		return user.getName();
	}

	@RequestMapping
	public String SeePanel(GmPanelForm gmPanelForm, Model model) {
		try {

			GetPendingRoomsResponse res =  dc.doGet(user.getToken(), "pendingrooms", GetPendingRoomsResponse.class);
			model.addAttribute("pendingRooms",res.getPendingRooms());
			
		} catch (HttpClientErrorException ex) {
			System.out.println(ex.getLocalizedMessage()); // TODO
			if (ex.getStatusCode() == HttpStatus.FORBIDDEN) {

			}
		}
		return "gmpanel";
	}
}
