package es.usc.rai.coego.martin.demiurgo.web.controllers;

import javax.validation.Valid;

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

import es.usc.rai.coego.martin.demiurgo.json.AllClassesResponse;
import es.usc.rai.coego.martin.demiurgo.json.CreateClassRequest;
import es.usc.rai.coego.martin.demiurgo.json.CreateClassResponse;
import es.usc.rai.coego.martin.demiurgo.json.JsonClass;
import es.usc.rai.coego.martin.demiurgo.web.beans.DemiurgoConnector;
import es.usc.rai.coego.martin.demiurgo.web.beans.LoggedUser;
import es.usc.rai.coego.martin.demiurgo.web.forms.CreateClassForm;

@Controller
public class ClassController {
	@Autowired
	LoggedUser user;

	@Autowired
	DemiurgoConnector dc;
	
	@ModelAttribute("user")
	public LoggedUser getUser() {
		return user;
	}
	
	@GetMapping("/classes")
	public String seeAllClasses(CreateClassForm createClassForm, Model model) {
		AllClassesResponse res = dc.doGet(user.getToken(), "allclasses", AllClassesResponse.class);
		model.addAttribute("classes", res.getClasses());
		return "classes";
	}
	
	@GetMapping("/class")
	public String seeClass(@RequestParam("name") String name, CreateClassForm createClassForm, Model model) {
		MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
		params.add("classname", name);
		JsonClass res = dc.doGet(user.getToken(), "getclass", params, JsonClass.class);
		model.addAttribute("cl", res);
		createClassForm.setClassName(res.getClassName());
		createClassForm.setCode(res.getCode());
		return "class";
	}
	
	@PostMapping("/classes")
	public String createClass(@Valid CreateClassForm createClassForm, BindingResult br, Model model) {
		if(br.hasErrors()) {
			AllClassesResponse res = dc.doGet(user.getToken(), "allclasses", AllClassesResponse.class);
			model.addAttribute("classes", res.getClasses());
			return "classes";
		}
		CreateClassRequest req = new CreateClassRequest();
		req.setName(createClassForm.getClassName());
		req.setCode(createClassForm.getCode());
		CreateClassResponse res = dc.doPost(user.getToken(), "createclass", req , CreateClassRequest.class, CreateClassResponse.class);
		if(res.getStatus().isOk()) {
			return "redirect:/class?name=" + res.getCreatedClass().getClassName();
		}
		else {
			AllClassesResponse res2 = dc.doGet(user.getToken(), "allclasses", AllClassesResponse.class);
			model.addAttribute("classes", res2.getClasses());
			model.addAttribute("parseErrors", res.getStatus().getDescription());
			return "classes";
		}
	}
	
	@PostMapping("/modifyclass")
	public String modifyClass(@Valid CreateClassForm createClassForm, BindingResult br, Model model) {
		CreateClassRequest req = new CreateClassRequest();
		req.setName(createClassForm.getClassName());
		req.setCode(createClassForm.getCode());
		CreateClassResponse res = dc.doPost(user.getToken(), "modifyclass", req , CreateClassRequest.class, CreateClassResponse.class);
		if(res.getStatus().isOk()) {
			return "redirect:/class?name=" + res.getCreatedClass().getClassName();
		}
		else {
			MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
			params.add("classname", createClassForm.getClassName());
			JsonClass res2 = dc.doGet(user.getToken(), "getclass", params, JsonClass.class);
			model.addAttribute("cl", res2);
			
			model.addAttribute("parseErrors", res.getStatus().getDescription());
			return "class";
		}
	}
}
