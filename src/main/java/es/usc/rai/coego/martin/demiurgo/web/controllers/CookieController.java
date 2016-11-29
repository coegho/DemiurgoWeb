package es.usc.rai.coego.martin.demiurgo.web.controllers;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import es.usc.rai.coego.martin.demiurgo.json.MyUserResponse;
import es.usc.rai.coego.martin.demiurgo.web.beans.LoggedUser;

@Controller
@RequestMapping("/cookie")
public class CookieController {
	@Autowired
	LoggedUser user;
	
	@RequestMapping
	public String readCookie(@CookieValue(name = "demiurgo_token", required = false) String cookieToken,
			@RequestParam(name="path", required=false) String path, HttpServletResponse response) {
		if (user.getToken() != null) {
			// already logged
			if(path != null) {
				return "redirect:/" + path;
			}
			else {
				return "redirect:/index";
			}
		}

		if (cookieToken != null) {
			user.setToken(cookieToken);
			try {
				RestTemplate restTemplate = new RestTemplate();
				String url = "http://localhost:5324/demiurgo/webservice/me"; // TODO:
																				// hardcoded
																				// url
				HttpHeaders headers = new HttpHeaders();
				headers.setContentType(MediaType.APPLICATION_JSON);
				headers.set("Authorization", "Bearer " + cookieToken);
				HttpEntity<String> entity = new HttpEntity<String>(headers);
				ResponseEntity<MyUserResponse> result = restTemplate.exchange(url, HttpMethod.GET, entity,
						MyUserResponse.class);
				user.setRole(result.getBody().getUser().getRole());

				return "redirect:/" + user.getRole();

			} catch (HttpClientErrorException ex) {
				user.setToken(null);
				Cookie newCookie = new Cookie("demiurgo_token", "");
				newCookie.setMaxAge(0);
				response.addCookie(newCookie); // delete the cookie
			}
			
		}

		return "redirect:/login"; // there is no cookie
	}
}
