package es.usc.rai.coego.martin.demiurgo.web.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import es.usc.rai.coego.martin.demiurgo.json.RegisterUserRequest;
import es.usc.rai.coego.martin.demiurgo.json.RegisterUserResponse;
import es.usc.rai.coego.martin.demiurgo.json.WorldListResponse;
import es.usc.rai.coego.martin.demiurgo.web.beans.DemiurgoConnector;
import es.usc.rai.coego.martin.demiurgo.web.forms.RegisterForm;

@Controller
public class RegisterController {
	@Autowired
	DemiurgoConnector dc;
	
	@GetMapping("/register")
	public String getRegisterForm(RegisterForm registerForm, Model model) {	
		WorldListResponse res = dc.doGet(null, "worlds", WorldListResponse.class);
		model.addAttribute("worlds", res.getWorlds());
		return "register";
	}
	
	@PostMapping("/register")
	public String registerUser(@Valid RegisterForm registerForm, BindingResult br, Model model) {
		if(br.hasErrors()) {
			return "register";
		}
		RegisterUserRequest req = new RegisterUserRequest();
		req.setUsername(registerForm.getUsername());
		req.setPassword(registerForm.getPass());
		req.setWorld(registerForm.getWorld());
		req.setMail(registerForm.getMail());
		RegisterUserResponse res = dc.doPost(null, "register", req, RegisterUserRequest.class, RegisterUserResponse.class);
		if(!res.getStatus().isOk()) {
			model.addAttribute("loginError", res.getStatus().getDescription());
			return "register";
		}
		model.addAttribute("active", res.isActive());
		return "registered";
	}
}
