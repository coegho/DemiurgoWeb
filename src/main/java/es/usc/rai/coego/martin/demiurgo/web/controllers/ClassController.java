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
import es.usc.rai.coego.martin.demiurgo.web.forms.CreateClassSecondForm;

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
	public String seeAllClasses(Model model, CreateClassForm createClassForm) {
		AllClassesResponse res = dc.doGet(user.getToken(), "allclasses", AllClassesResponse.class);
		model.addAttribute("classes", res.getClasses());
		return "classes";
	}

	@GetMapping("/class")
	public String seeClass(@RequestParam("name") String name, CreateClassSecondForm createClassSecondForm, Model model) {
		MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
		params.add("classname", name);
		JsonClass res = dc.doGet(user.getToken(), "getclass", params, JsonClass.class);
		model.addAttribute("cl", res);
		
		if(res.getParent() != null) {
			MultiValueMap<String, String> params2 = new LinkedMultiValueMap<String, String>();
			params2.add("classname", res.getParent().getClassName());
			JsonClass parent = dc.doGet(user.getToken(), "getclass", params2, JsonClass.class);
			model.addAttribute("parent", parent);
		}
		createClassSecondForm.setClassName(res.getClassName());
		createClassSecondForm.setCode(res.getCode());
		return "class";
	}

	@GetMapping("/createclass")
	public String createClassForm(@Valid CreateClassForm createClassForm, CreateClassSecondForm createClassSecondForm) {
		createClassSecondForm.setClassName(createClassForm.getClassName());
		createClassSecondForm.setCode(createClassForm.getClassName() + " {\n\n}");
		return "createclass";
	}

	@PostMapping("/classes")
	public String createClass(@Valid CreateClassSecondForm createClassSecondForm, BindingResult br, Model model) {
		if (br.hasErrors()) {
			return "createclass";
		}
		CreateClassRequest req = new CreateClassRequest();
		req.setName(createClassSecondForm.getClassName());
		req.setCode(createClassSecondForm.getCode());
		CreateClassResponse res = dc.doPost(user.getToken(), "createclass", req, CreateClassRequest.class,
				CreateClassResponse.class);
		if (res.getStatus().isOk()) {
			return "redirect:/class?name=" + res.getCreatedClass().getClassName();
		} else {
			model.addAttribute("parseErrors", res.getStatus().getDescription());
			return "createclass";
		}
	}

	@PostMapping("/modifyclass")
	public String modifyClass(@Valid CreateClassSecondForm createClassSecondForm, BindingResult br, Model model) {
		CreateClassRequest req = new CreateClassRequest();
		req.setName(createClassSecondForm.getClassName());
		req.setCode(createClassSecondForm.getCode());
		CreateClassResponse res = dc.doPost(user.getToken(), "modifyclass", req, CreateClassRequest.class,
				CreateClassResponse.class);
		if (res.getStatus().isOk()) {
			return "redirect:/class?name=" + res.getCreatedClass().getClassName();
		} else {
			MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
			params.add("classname", createClassSecondForm.getClassName());
			JsonClass res2 = dc.doGet(user.getToken(), "getclass", params, JsonClass.class);
			model.addAttribute("cl", res2);

			model.addAttribute("parseErrors", res.getStatus().getDescription());
			return "class";
		}
	}
}
