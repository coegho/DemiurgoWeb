package es.usc.rai.coego.martin.demiurgo.web.controllers;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import es.usc.rai.coego.martin.demiurgo.web.beans.LoggedUser;

@Controller
@RequestMapping("/logout")
public class LogoutController {
	@Autowired
	LoggedUser user;
	
	@RequestMapping
	public String logout(HttpServletResponse response) {
		user.setToken(null);
		user.setName(null);
		user.setRole(null);
		user.setWorld(null);
		
		Cookie cookie = new Cookie("demiurgo_token", "");
		cookie.setMaxAge(0);
		response.addCookie(cookie);
		
		return "redirect:/login";
	}
}
