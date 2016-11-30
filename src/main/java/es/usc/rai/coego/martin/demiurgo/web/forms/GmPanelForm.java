package es.usc.rai.coego.martin.demiurgo.web.forms;

import javax.validation.constraints.NotNull;

public class GmPanelForm {
	@NotNull
	private String room;
	
	public String getRoom() {
		return room;
	}
	
	public void setRoom(String room) {
		this.room = room;
	}
}
