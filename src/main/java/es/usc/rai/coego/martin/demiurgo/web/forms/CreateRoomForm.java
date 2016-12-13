package es.usc.rai.coego.martin.demiurgo.web.forms;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class CreateRoomForm {
	@NotNull
	@Pattern(regexp = "^/([\\w\\d_])+(/([\\w\\d_])+)*$", flags={Pattern.Flag.CASE_INSENSITIVE})
	private String path;
	
	public String getPath() {
		return path;
	}
	
	public void setPath(String path) {
		this.path = path;
	}
}
