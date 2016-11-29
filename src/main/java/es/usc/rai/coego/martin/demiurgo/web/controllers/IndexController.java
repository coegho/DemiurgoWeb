package es.usc.rai.coego.martin.demiurgo.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import es.usc.rai.coego.martin.demiurgo.json.MyUserResponse;
import es.usc.rai.coego.martin.demiurgo.web.beans.LoggedUser;

@Controller
@RequestMapping({"/", "/index"})
public class IndexController {
	@Autowired
	LoggedUser user;
	
	@RequestMapping
	public String redirect(@CookieValue(name="demiurgo_token", required=false) String cookie) {
		if (user.getToken() != null) {
			String token = user.getToken();
			if (user.getRole() != null) {
				return "redirect:/" + user.getRole();
			}
			else {
				try {
				RestTemplate restTemplate = new RestTemplate();
				String url = "http://localhost:5324/demiurgo/webservice/me"; //TODO: hardcoded url
				HttpHeaders headers = new HttpHeaders();
				headers.setContentType(MediaType.APPLICATION_JSON);
				headers.set("Authorization", "Bearer " + token);
				HttpEntity<String> entity = new HttpEntity<String>(headers);
				ResponseEntity<MyUserResponse> result = restTemplate.exchange(url, HttpMethod.GET, entity,
						MyUserResponse.class);
				user.setRole(result.getBody().getUser().getRole());
				
				return "redirect:/" + user.getRole();
				
				} catch(HttpClientErrorException ex) {
					user.setToken(null);
				}
			}
		}
		if(cookie != null) {
			return "redirect:/cookie";
		}
		else {
			return "redirect:/login";
		}
	}
}
