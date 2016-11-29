package es.usc.rai.coego.martin.demiurgo.web.controllers;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import es.usc.rai.coego.martin.demiurgo.json.LoginRequest;
import es.usc.rai.coego.martin.demiurgo.json.MyUserResponse;
import es.usc.rai.coego.martin.demiurgo.web.beans.DemiurgoConnector;
import es.usc.rai.coego.martin.demiurgo.web.beans.LoggedUser;

@Controller
@RequestMapping("/login")
public class LoginController {
	@Autowired
	LoggedUser user;
	
	@Autowired
	DemiurgoConnector dc;

	@GetMapping()
	public String showForm(@CookieValue(value = "demiurgo_token", required = false) String cookieToken) {
		if (user.getToken() != null) {
			return "redirect:/index";
		}
		if (cookieToken != null) {
			return "redirect:/cookie";
		}
		return "login";
	}

	@PostMapping(params = { "world", "username", "pass" })
	public String login(@RequestParam("world") String world, @RequestParam("username") String username,
			@RequestParam("pass") String pass, ModelMap model, HttpServletResponse response) {
		try {
			RestTemplate restTemplate = new RestTemplate();

			LoginRequest lr = new LoginRequest();
			lr.setName(username);
			lr.setPassword(pass);
			lr.setWorld(world);
			String url = "http://localhost:5324/demiurgo/login"; // TODO:
																	// hardcoded
																	// url

			HttpEntity<LoginRequest> request = new HttpEntity<>(lr);

			String token = restTemplate.postForObject(url, request, String.class);

			System.out.println("got token " + token);// TODO: debug

			//////////////////
			MyUserResponse res =  dc.doGet(token, "me", MyUserResponse.class);
			
			//////////////////

			user.setToken(token);
			user.setName(username);
			user.setRole(res.getUser().getRole());
			user.setWorld(world);
			Cookie cookie = new Cookie("demiurgo_token", token);
			cookie.setComment("Auth token for DemiurgoWeb");
			cookie.setMaxAge(60 * 60 * 24 * 7);
			response.addCookie(cookie);
			return "redirect:/index";

		} catch (HttpClientErrorException ex) {
			System.out.println(ex.getLocalizedMessage());
			if (ex.getStatusCode() == HttpStatus.UNAUTHORIZED) {
				model.addAttribute("error", "ERROR: Bad credentials");
			} else {
				model.addAttribute("error", "ERROR: Cannot connect with server");
			}
			return "login";
		}
	}

}