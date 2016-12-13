package es.usc.rai.coego.martin.demiurgo.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import es.usc.rai.coego.martin.demiurgo.json.RoomHistoryResponse;
import es.usc.rai.coego.martin.demiurgo.web.beans.DemiurgoConnector;
import es.usc.rai.coego.martin.demiurgo.web.beans.LoggedUser;

@Controller
public class HistoryController {

	@Autowired
	LoggedUser user;

	@Autowired
	DemiurgoConnector dc;

	@GetMapping("/history")
	public String seeHistory(@RequestParam("path") String path,
			@RequestParam(value = "first", defaultValue = "-15") String first,
			@RequestParam(value = "count", defaultValue = "15") String count, Model model) {
		LinkedMultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("path", path);
		params.add("first", first);
		params.add("count", count);
		RoomHistoryResponse res = dc.doGet(user.getToken(), "history", params, RoomHistoryResponse.class);
		model.addAttribute("actions", res.getActions());
		model.addAttribute("path", path);
		return "history";
	}

}
