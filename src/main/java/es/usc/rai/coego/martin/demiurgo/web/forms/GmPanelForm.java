package es.usc.rai.coego.martin.demiurgo.web.forms;

import javax.validation.constraints.NotNull;

public class GmPanelForm {
	@NotNull
	private String path;
	
	public String getPath() {
		return path;
	}
	
	public void setPath(String path) {
		this.path = path;
	}
}
