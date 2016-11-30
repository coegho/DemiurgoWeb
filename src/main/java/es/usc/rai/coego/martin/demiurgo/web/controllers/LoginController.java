package es.usc.rai.coego.martin.demiurgo.web.controllers;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import es.usc.rai.coego.martin.demiurgo.json.LoginRequest;
import es.usc.rai.coego.martin.demiurgo.json.MyUserResponse;
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
			LoginForm form) {
		if (user.getToken() != null) {
			return "redirect:/index";
		}
		if (cookieToken != null) {
			return "redirect:/cookie";
		}
		return "login";
	}

	@PostMapping("/login")
	public String login(@Valid LoginForm form, HttpServletResponse response, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
            return "login";
        }
		
		try {
			RestTemplate restTemplate = new RestTemplate();

			LoginRequest lr = new LoginRequest();
			lr.setName(form.getUsername());
			lr.setPassword(form.getPass());
			lr.setWorld(form.getWorld());
			String url = "http://localhost:5324/demiurgo/login"; // TODO:
																	// hardcoded
																	// url

			HttpEntity<LoginRequest> request = new HttpEntity<>(lr);

			String token = restTemplate.postForObject(url, request, String.class);

			System.out.println("got token " + token);// TODO: debug

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