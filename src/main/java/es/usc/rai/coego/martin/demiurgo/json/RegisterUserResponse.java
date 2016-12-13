package es.usc.rai.coego.martin.demiurgo.json;

public class RegisterUserResponse {
	private ResponseStatus status;
	private boolean active;
	
	public ResponseStatus getStatus() {
		return status;
	}
	
	public void setStatus(ResponseStatus status) {
		this.status = status;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}
}
