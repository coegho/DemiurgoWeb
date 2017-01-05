package es.usc.rai.coego.martin.demiurgo.web.controllers;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.HttpClientErrorException;

import es.usc.rai.coego.martin.demiurgo.json.MyUserResponse;
import es.usc.rai.coego.martin.demiurgo.json.WorldListResponse;
import es.usc.rai.coego.martin.demiurgo.web.beans.DemiurgoConnector;
import es.usc.rai.coego.martin.demiurgo.web.beans.LoggedUser;
import es.usc.rai.coego.martin.demiurgo.web.forms.LoginForm;

@Controller
public class LoginController {
	@Autowired
	LoggedUser user;

	@Autowired
	DemiurgoConnector dc;

	@GetMapping("/login")
	public String showForm(@CookieValue(value = "demiurgo_token", required = false) String cookieToken,
			LoginForm form, Model model) {
		if (user.getToken() != null || cookieToken != null) {
			return "redirect:/index";
		}
		WorldListResponse res = dc.doGet(null, "worlds", WorldListResponse.class);
		model.addAttribute("worlds", res.getWorlds());
		return "login";
	}

	@PostMapping("/login")
	public String login(@Valid LoginForm form, HttpServletResponse response, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
            return "login";
        }
		
		try {
			String token = dc.login(form.getUsername(), form.getPass(), form.getWorld());

			//////////////////
			MyUserResponse res = dc.doGet(token, "me", MyUserResponse.class);

			//////////////////

			user.setToken(token);
			user.setName(form.getUsername());
			user.setRole(res.getUser().getRole());
			user.setWorld(form.getWorld());
			Cookie cookie = new Cookie("demiurgo_token", token);
			cookie.setComment("Auth token for DemiurgoWeb");
			cookie.setMaxAge(60 * 60 * 24 * 7);
			response.addCookie(cookie);
			return "redirect:/index";

		} catch (HttpClientErrorException ex) {
			System.out.println(ex.getLocalizedMessage());
			if (ex.getStatusCode() == HttpStatus.UNAUTHORIZED) {
				bindingResult.addError(new ObjectError("auth", "ERROR: Bad credentials"));
			} else {
				bindingResult.addError(new ObjectError("auth", "ERROR: Cannot connect with server"));
			}
			return "login";
		}
	}

}