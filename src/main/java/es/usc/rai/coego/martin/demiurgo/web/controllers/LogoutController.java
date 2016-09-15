package es.usc.rai.coego.martin.demiurgo.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/logout")
public class LogoutController {

	public String doSom() {
		/*
		request.getSession().removeAttribute("token");
		Cookie cookie = new Cookie("demiurgoweb_token", "");
		cookie.setMaxAge(-10000000);
		response.addCookie(cookie);
		response.sendRedirect("login.jsp");
		 */
		return "redirect:/login";
	}
}
