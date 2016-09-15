package es.usc.rai.coego.martin.demiurgo.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/index")
public class IndexController {

	public String doSom() {
		/*String token = (String) request.getSession().getAttribute("token");
		UserData user = (UserData) request.getSession().getAttribute("user");
		if (token != null && user != null) {
			if(user.isAdmin())
				response.sendRedirect("gamemaster");
			else
				response.sendRedirect("player");
		}
		else {
			response.sendRedirect("cookie");
		}*/
		return "redirect:/cookie";
	}
}
