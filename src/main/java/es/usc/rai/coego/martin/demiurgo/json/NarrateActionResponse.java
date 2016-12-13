package es.usc.rai.coego.martin.demiurgo.json;

public class NarrateActionResponse {
	private ResponseStatus status;
	private JsonAction action;

	public ResponseStatus getStatus() {
		return status;
	}

	public void setStatus(ResponseStatus status) {
		this.status = status;
	}
	
	public JsonAction getAction() {
		return action;
	}
	
	public void setAction(JsonAction action) {
		this.action = action;
	}
}
