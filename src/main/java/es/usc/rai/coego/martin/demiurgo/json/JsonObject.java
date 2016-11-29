package es.usc.rai.coego.martin.demiurgo.json;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class JsonObject {
	private long id;
	private String classname;
	private long locationId;
	private List<JsonVariable> fields;

	public JsonObject() {
	}

	public JsonObject(long id, String classname, long locationId, List<JsonVariable> fields) {
		super();
		this.id = id;
		this.classname = classname;
		this.locationId = locationId;
		this.fields = fields;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getClassname() {
		return classname;
	}

	public void setClassname(String classname) {
		this.classname = classname;
	}

	public long getLocationId() {
		return locationId;
	}

	public void setLocationId(long locationId) {
		this.locationId = locationId;
	}

	public List<JsonVariable> getFields() {
		return fields;
	}

	public void setFields(List<JsonVariable> fields) {
		this.fields = fields;
	}
}