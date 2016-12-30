package es.usc.rai.coego.martin.demiurgo.json;

public class CreateClassResponse {
	private ResponseStatus status;
	private JsonClass createdClass;
	
	public ResponseStatus getStatus() {
		return status;
	}
	
	public void setStatus(ResponseStatus status) {
		this.status = status;
	}
	public JsonClass getCreatedClass() {
		return createdClass;
	}
	public void setCreatedClass(JsonClass createdClass) {
		this.createdClass = createdClass;
	}
}
