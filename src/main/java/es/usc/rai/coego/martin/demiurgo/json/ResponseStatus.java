package es.usc.rai.coego.martin.demiurgo.json;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseStatus {
	protected boolean ok;
	protected String description;

	public ResponseStatus() {
		ok = true;
		description = "ok";
	}
	
	public ResponseStatus(boolean ok, String description) {
		this.ok = ok;
		this.description = description;
	}

	public boolean isOk() {
		return ok;
	}

	public void setOk(boolean ok) {
		this.ok = ok;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
