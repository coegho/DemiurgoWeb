package es.usc.rai.coego.martin.demiurgo.json;

import java.util.List;

public class PastActionsResponse {
	private ResponseStatus status;
	private List<String> actions;
	
	public ResponseStatus getStatus() {
		return status;
	}
	
	public void setStatus(ResponseStatus status) {
		this.status = status;
	}
	
	public List<String> getActions() {
		return actions;
	}
	
	public void setActions(List<String> actions) {
		this.actions = actions;
	}
}
