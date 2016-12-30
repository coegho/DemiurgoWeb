package es.usc.rai.coego.martin.demiurgo.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import es.usc.rai.coego.martin.demiurgo.json.JsonAction;
import es.usc.rai.coego.martin.demiurgo.json.RoomHistoryResponse;
import es.usc.rai.coego.martin.demiurgo.web.beans.ActionFormatter;
import es.usc.rai.coego.martin.demiurgo.web.beans.DemiurgoConnector;
import es.usc.rai.coego.martin.demiurgo.web.beans.LoggedUser;

@Controller
public class HistoryController {

	@Autowired
	LoggedUser user;

	@Autowired
	DemiurgoConnector dc;

	@ModelAttribute("user")
	public LoggedUser getUser() {
		return user;
	}
	
	@GetMapping("/history")
	public String seeHistory(@RequestParam("path") String path,
			@RequestParam(value = "first", defaultValue = "-15") String first,
			@RequestParam(value = "count", defaultValue = "15") String count, Model model) {
		LinkedMultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("path", path);
		params.add("first", first);
		params.add("count", count);
		RoomHistoryResponse res = dc.doGet(user.getToken(), "history", params, RoomHistoryResponse.class);
		
		for(JsonAction a : res.getActions()) {
			a.setNarration(ActionFormatter.BBCodeToHtml(a.getNarration()));
		}
		model.addAttribute("actions", res.getActions());
		model.addAttribute("path", path);
		
		int f, c, t;
		f = Integer.parseInt(first);
		c = Integer.parseInt(count);
		t = res.getTotalActions();
		if(f < 0)
			f = t + f;
		
		if(res.getTotalActions() > c) {
			if(f > 0) {
				model.addAttribute("prevpag", ((f-c)<0 ? 0 : (f-c)));
			}
			if((f+c) < t) {
				model.addAttribute("postpag", f+c);
			}
		}
		model.addAttribute("countpag", c);
		return "history";
	}

}
