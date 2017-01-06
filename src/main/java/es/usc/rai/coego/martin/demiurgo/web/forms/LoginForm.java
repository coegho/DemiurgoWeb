package es.usc.rai.coego.martin.demiurgo.web.forms;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class LoginForm {
	@NotNull
	private String world;
	
	@NotNull
	@Size(min = 2, max = 128)
	@Pattern(regexp= "^[A-Z0-9_]+$", flags = {Pattern.Flag.CASE_INSENSITIVE})
	private String username;
	
	@NotNull
	@Size(min = 4)
	private String pass;

	public String getWorld() {
		return world;
	}

	public void setWorld(String world) {
		this.world = world;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}
}