package es.usc.rai.coego.martin.demiurgo.web;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import es.usc.rai.coego.martin.demiurgo.json.MyUserResponse;
import es.usc.rai.coego.martin.demiurgo.web.beans.DemiurgoConnector;
import es.usc.rai.coego.martin.demiurgo.web.beans.LoggedUser;

public class LoginInterceptor extends HandlerInterceptorAdapter {
	@Autowired
	LoggedUser user;

	@Autowired
	DemiurgoConnector dc;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if (!user.isLogged()) {
			// Cannot find token in session
			// searching cookie
			String cookieToken = null;
			Cookie[] cookies = request.getCookies();
			if (cookies != null) {
				for (int i = 0; i < cookies.length; i++) {
					if (cookies[i].getName().equals("demiurgo_token")) {
						cookieToken = cookies[i].getValue();
						break;
					}
				}

				if (cookieToken != null) { // cookie found
					if (requestUserData(cookieToken)) {
						// good token, continue
						return true;
					} else { // wrong or out-date token
						Cookie newCookie = new Cookie("demiurgo_token", "");
						newCookie.setMaxAge(0);
						response.addCookie(newCookie); // delete the cookie
					}
				}
			}
			response.sendRedirect("/login");
			return false; // cannot find token anywhere

		} else {
			return true; // token found in session
		}
	}

	private boolean requestUserData(String token) {
		try {
			MyUserResponse res = dc.doGet(token, "me", MyUserResponse.class);
			user.setToken(token);
			user.setName(res.getUser().getName());
			user.setRole(res.getUser().getRole());
			user.setWorld(res.getWorld());
			return true;
		} catch (HttpClientErrorException ex) {
			return false;
		}
	}
}
