package es.usc.rai.coego.martin.demiurgo.json;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GetPendingRoomsResponse {
	private ResponseStatus status;
	private List<String> pendingRooms;

	public GetPendingRoomsResponse() {

	}

	public GetPendingRoomsResponse(ResponseStatus status, List<String> pendingRooms) {
		super();
		this.status = status;
		this.pendingRooms = pendingRooms;
	}

	public ResponseStatus getStatus() {
		return status;
	}

	public void setStatus(ResponseStatus status) {
		this.status = status;
	}

	public List<String> getPendingRooms() {
		return pendingRooms;
	}

	public void setPendingRooms(List<String> pendingRooms) {
		this.pendingRooms = pendingRooms;
	}

}
