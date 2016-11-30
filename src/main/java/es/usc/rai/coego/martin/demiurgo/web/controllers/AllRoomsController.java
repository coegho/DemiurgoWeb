package es.usc.rai.coego.martin.demiurgo.web.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import es.usc.rai.coego.martin.demiurgo.json.AllRoomPathsResponse;
import es.usc.rai.coego.martin.demiurgo.web.beans.DemiurgoConnector;
import es.usc.rai.coego.martin.demiurgo.web.beans.LoggedUser;

@Controller
@RequestMapping("/seeallrooms")
public class AllRoomsController {
	@Autowired
	LoggedUser user;
	
	@Autowired
	DemiurgoConnector dc;

	private List<String> roomPaths;
	
	@ModelAttribute("paths")
	List<String> getRoomPaths() {
		return roomPaths;
	}
	
	@GetMapping
	public String seeAllRooms() {
		
		AllRoomPathsResponse res = dc.doGet(user.getToken(), "roompaths", AllRoomPathsResponse.class);
		roomPaths = res.getPaths();
		
		return "seeallrooms";
	}
}
