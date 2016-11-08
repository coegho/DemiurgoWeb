package es.usc.rai.coego.martin.demiurgo.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cookie")
public class CookieController {

	@GetMapping("/")
	public String doSom() {
		/*String path = "index";
		if(request.getParameter("path") != null)
			path = request.getParameter("path");
		
		request.getSession().removeAttribute("token");
		
		Cookie[] cookies = request.getCookies();
		for (int i = 0; i < cookies.length; i++) {
			Cookie cookie = cookies[i];
			if (cookie.getName().equals("demiurgoweb_token")) {
				
				UserData user = DemiurgoConnector.getInterface().me(cookie.getValue());
				if(user == null) { //the token doesn't work anymore
					Cookie newCookie = new Cookie("demiurgo_token", "");
					newCookie.setMaxAge(-10000000);
					response.addCookie(newCookie);
					request.getSession().removeAttribute("token");
					response.sendRedirect("login");
				}
				else {
					request.getSession().setAttribute("token", cookie.getValue());
					request.getSession().setAttribute("user", user);
					response.sendRedirect(path);
				}
				return;
			}
		}
		response.sendRedirect("login");*/
		return "redirect:/login";
	}
}
