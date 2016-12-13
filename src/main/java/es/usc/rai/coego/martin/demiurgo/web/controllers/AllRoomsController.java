package es.usc.rai.coego.martin.demiurgo.web.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import es.usc.rai.coego.martin.demiurgo.json.AllRoomPathsResponse;
import es.usc.rai.coego.martin.demiurgo.json.CreateRoomRequest;
import es.usc.rai.coego.martin.demiurgo.json.JsonRoom;
import es.usc.rai.coego.martin.demiurgo.web.beans.DemiurgoConnector;
import es.usc.rai.coego.martin.demiurgo.web.beans.LoggedUser;
import es.usc.rai.coego.martin.demiurgo.web.forms.CreateRoomForm;

@Controller
public class AllRoomsController {
	@Autowired
	LoggedUser user;
	
	@Autowired
	DemiurgoConnector dc;


	@GetMapping("/seeallrooms")
	public String seeAllRooms(CreateRoomForm createRoomForm, Model model) {
		AllRoomPathsResponse res = dc.doGet(user.getToken(), "roompaths", AllRoomPathsResponse.class);
		model.addAttribute("paths", res.getPaths());
		return "seeallrooms";
	}
	
	@PostMapping("createroom")
	public String createRoom(@Valid CreateRoomForm createRoomForm) {
		CreateRoomRequest req = new CreateRoomRequest();
		req.setPath(createRoomForm.getPath());
		JsonRoom r = dc.doPost(user.getToken(), "createroom", req, CreateRoomRequest.class, JsonRoom.class);
		return "redirect:/room?path="+r.getLongPath();
	}
}
