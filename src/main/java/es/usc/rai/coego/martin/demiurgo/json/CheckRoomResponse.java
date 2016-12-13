package es.usc.rai.coego.martin.demiurgo.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CheckRoomResponse {
	private ResponseStatus status;
	private JsonRoom room;
	private JsonAction unpublishedAction;
	
	public ResponseStatus getStatus() {
		return status;
	}
	
	public void setStatus(ResponseStatus status) {
		this.status = status;
	}

	public JsonRoom getRoom() {
		return room;
	}

	public void setRoom(JsonRoom room) {
		this.room = room;
	}
	
	public JsonAction getUnpublishedAction() {
		return unpublishedAction;
	}
	
	public void setUnpublishedAction(JsonAction unpublishedAction) {
		this.unpublishedAction = unpublishedAction;
	}
}