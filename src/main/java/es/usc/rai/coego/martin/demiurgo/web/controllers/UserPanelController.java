package es.usc.rai.coego.martin.demiurgo.web.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.xml.ws.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import es.usc.rai.coego.martin.demiurgo.json.MyUserResponse;
import es.usc.rai.coego.martin.demiurgo.json.PastActionsResponse;
import es.usc.rai.coego.martin.demiurgo.json.SubmitDecisionRequest;
import es.usc.rai.coego.martin.demiurgo.web.beans.ActionFormatter;
import es.usc.rai.coego.martin.demiurgo.web.beans.DemiurgoConnector;
import es.usc.rai.coego.martin.demiurgo.web.beans.LoggedUser;
import es.usc.rai.coego.martin.demiurgo.web.forms.UserPanelForm;

@Controller
public class UserPanelController {
	@Autowired
	LoggedUser user;

	@Autowired
	DemiurgoConnector dc;
	
	@ModelAttribute("user")
	public LoggedUser getUser() {
		return user;
	}
	
	@GetMapping("/user")
	public String seePanel(@RequestParam(value="first", defaultValue="-10") String first, @RequestParam(value="count", defaultValue="10") String count, UserPanelForm userPanelForm, Model model) {
		MultiValueMap<String,String> params = new LinkedMultiValueMap<>();
		params.add("first", first);
		params.add("count", count);
		PastActionsResponse res = dc.doGet(user.getToken(), "pastactions", params, PastActionsResponse.class);
		
		List<String> actions = new ArrayList<>();
		for(String a : res.getActions()) {
			actions.add(ActionFormatter.BBCodeToHtml(a));
		}
		model.addAttribute("actions", actions);
		
		MyUserResponse me = dc.doGet(user.getToken(), "me", null, MyUserResponse.class);
		model.addAttribute("current", me.getUser().getDecision());
		return "userpanel";
	}
	
	@PostMapping("/user")
	public String submitDecision(@Valid UserPanelForm userPanelForm, BindingResult br, Model model) {
		if(br.hasErrors()) {
			return "redirect:/user";
		}
		SubmitDecisionRequest req = new SubmitDecisionRequest();
		req.setDecision(userPanelForm.getDecision());
		dc.doPost(user.getToken(), "submitdecision", req, SubmitDecisionRequest.class,
				Response.class);
		
		return "redirect:/user";
	}
}
