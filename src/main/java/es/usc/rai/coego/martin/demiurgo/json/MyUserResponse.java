package es.usc.rai.coego.martin.demiurgo.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MyUserResponse {
	protected ResponseStatus status;
	protected JsonUser user;
	
	public ResponseStatus getStatus() {
		return status;
	}
	public void setStatus(ResponseStatus status) {
		this.status = status;
	}
	public JsonUser getUser() {
		return user;
	}
	public void setUser(JsonUser user) {
		this.user = user;
	}
	
	
}
