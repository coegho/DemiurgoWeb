package es.usc.rai.coego.martin.demiurgo.json;

import java.util.List;

public class RoomHistoryResponse {
	private ResponseStatus status;
	List<JsonAction> actions;
	int totalActions;
	
	public ResponseStatus getStatus() {
		return status;
	}
	public void setStatus(ResponseStatus status) {
		this.status = status;
	}
	public List<JsonAction> getActions() {
		return actions;
	}
	public void setActions(List<JsonAction> actions) {
		this.actions = actions;
	}
	public int getTotalActions() {
		return totalActions;
	}
	public void setTotalActions(int totalActions) {
		this.totalActions = totalActions;
	}
}
