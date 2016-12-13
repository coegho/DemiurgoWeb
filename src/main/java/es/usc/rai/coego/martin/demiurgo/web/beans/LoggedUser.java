package es.usc.rai.coego.martin.demiurgo.web.beans;

public class LoggedUser {
	private String token;
	private String name;
	private String role;
	private String world;
	
	public LoggedUser() {
		token = name = role = world = null;
	}
	
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getWorld()  {
		return world;
	}
	public void setWorld(String world) {
		this.world = world;
	}

	public boolean isLogged() {
		return token != null &
				name != null &
				role != null &
				world != null;
	}
}
