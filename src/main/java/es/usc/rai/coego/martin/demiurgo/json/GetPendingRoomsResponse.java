package es.usc.rai.coego.martin.demiurgo.json;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GetPendingRoomsResponse {
	private ResponseStatus status;
	private List<JsonPendingRoom> pendingRooms;
	private int numUsers;
	private List<JsonUser> noObjUsers;

	public GetPendingRoomsResponse() {
	}

	public GetPendingRoomsResponse(ResponseStatus status, List<JsonPendingRoom> pendingRooms, int numUsers,
			List<JsonUser> noObjUsers) {
		super();
		this.status = status;
		this.pendingRooms = pendingRooms;
		this.numUsers = numUsers;
		this.noObjUsers = noObjUsers;
	}

	public ResponseStatus getStatus() {
		return status;
	}

	public void setStatus(ResponseStatus status) {
		this.status = status;
	}

	public List<JsonPendingRoom> getPendingRooms() {
		return pendingRooms;
	}

	public void setPendingRooms(List<JsonPendingRoom> pendingRooms) {
		this.pendingRooms = pendingRooms;
	}

	public int getNumUsers() {
		return numUsers;
	}

	public void setNumUsers(int numUsers) {
		this.numUsers = numUsers;
	}

	public List<JsonUser> getNoObjUsers() {
		return noObjUsers;
	}

	public void setNoObjUsers(List<JsonUser> noObjUsers) {
		this.noObjUsers = noObjUsers;
	}

}
