package es.usc.rai.coego.martin.demiurgo.json;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class JsonRoom {
	private long id;
	private String longPath;
	private List<JsonVariable> variables;
	private List<JsonObject> objects;
	private List<JsonUser> users;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getLongPath() {
		return longPath;
	}

	public void setLongPath(String longPath) {
		this.longPath = longPath;
	}

	public List<JsonVariable> getVariables() {
		return variables;
	}

	public void setVariables(List<JsonVariable> variables) {
		this.variables = variables;
	}

	public List<JsonObject> getObjects() {
		return objects;
	}

	public void setObjects(List<JsonObject> objects) {
		this.objects = objects;
	}

	public List<JsonUser> getUsers() {
		return users;
	}
	
	public void setUsers(List<JsonUser> users) {
		this.users = users;
	}
}
