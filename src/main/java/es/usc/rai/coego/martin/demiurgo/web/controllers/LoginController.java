package es.usc.rai.coego.martin.demiurgo.web.controllers;

import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import es.usc.rai.coego.martin.demiurgo.json.LoginRequest;

@Controller
@RequestMapping("/login")
public class LoginController{

	
	@GetMapping("/")
	public String showForm() {
		/*String world = request.getParameter("world");
		String username = request.getParameter("username");
		String pass = request.getParameter("pass");

		String token = DemiurgoConnector.getInterface().login(world, username, pass);
		if (token != null) {
			request.getSession().setAttribute("token", token);
			DemiurgoConnector.getInterface().checkRoom(token, "/escenario1");
			UserData user = DemiurgoConnector.getInterface().me(token);
			request.getSession().setAttribute("user", user);
			Cookie cookie = new Cookie("demiurgoweb_token", token);
			cookie.setComment("Auth token for DemiurgoWeb");
			cookie.setMaxAge(60 * 60 * 24 * 7);
			response.addCookie(cookie);
			response.sendRedirect("index");
		} else {
			request.setAttribute("error", true);
			RequestDispatcher view = request.getRequestDispatcher("jsp/login.jsp");
			view.forward(request, response);
		}*/
		
        return "login";
    }
	
	@PostMapping("/")
	public String login(@RequestParam("world") String world, @RequestParam("username") String username, @RequestParam("pass") String pass) {
		RestTemplate restTemplate = new RestTemplate();
		
		/*MultiValueMap<String, String> map = new LinkedMultiValueMap<String, String>();
		map.add("name", username);
		map.add("password", pass);
		map.add("world", world);*/
		LoginRequest lr = new LoginRequest();
		lr.setName(username);
		lr.setPassword(pass);
		lr.setWorld(world);
		String url = "http://localhost:5324/demiurgo/webservice/login";
		
		HttpEntity<LoginRequest> request = new HttpEntity<>(lr);
		
		String token = restTemplate.postForObject(url, request, String.class);
        System.out.println("got token " + token);
		
		return "login";
	}

}