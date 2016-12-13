package es.usc.rai.coego.martin.demiurgo.web.forms;

import javax.validation.constraints.NotNull;

public class UserPanelForm {
	@NotNull
	private String decision;
	
	public String getDecision() {
		return decision;
	}
	
	public void setDecision(String decision) {
		this.decision = decision;
	}
}
