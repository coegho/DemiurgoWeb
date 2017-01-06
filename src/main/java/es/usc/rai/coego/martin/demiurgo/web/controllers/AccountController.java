package es.usc.rai.coego.martin.demiurgo.web.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import es.usc.rai.coego.martin.demiurgo.json.ChangePasswordRequest;
import es.usc.rai.coego.martin.demiurgo.json.ResponseStatus;
import es.usc.rai.coego.martin.demiurgo.web.beans.DemiurgoConnector;
import es.usc.rai.coego.martin.demiurgo.web.beans.LoggedUser;
import es.usc.rai.coego.martin.demiurgo.web.forms.ChangePasswordForm;

@Controller
public class AccountController {
	@Autowired
	LoggedUser user;

	@Autowired
	DemiurgoConnector dc;

	@ModelAttribute("user")
	public LoggedUser getUser() {
		return user;
	}

	@GetMapping("/account")
	public String seeAccountForm(@RequestParam(value = "chngd", defaultValue = "") String passwdChanged,
			ChangePasswordForm changePasswordForm, Model model) {
		if(passwdChanged.equals("1")) {
			model.addAttribute("chngd", true);
		}
		else if(passwdChanged.equals("0")) {
			model.addAttribute("chngd", false);
		}
		return "account";
	}

	@PostMapping("/changepasswd")
	public String changePassword(@Valid ChangePasswordForm changePasswordForm, BindingResult br) {
		if(br.hasErrors()) {
			return "account";
		}
		ChangePasswordRequest req = new ChangePasswordRequest();
		req.setUsername(user.getName());
		req.setOldPassword(changePasswordForm.getOldPassword());
		req.setNewPassword(changePasswordForm.getNewPassword());
		ResponseStatus res = dc.doPost(user.getToken(), "changepasswd", req, ChangePasswordRequest.class,
				ResponseStatus.class);
		
		return "redirect:/account?chngd="+((res.isOk()?1:0));
	}
}
