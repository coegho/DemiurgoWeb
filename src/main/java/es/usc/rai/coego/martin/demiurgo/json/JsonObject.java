package es.usc.rai.coego.martin.demiurgo.json;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class JsonObject {
	private long id;
	private String classname;
	private long locationId;
	private String name;
	private String description;
	private String imgUrl;
	private List<JsonVariable> fields;
	private List<JsonMethod> methods;
	private List<JsonInventory> inventories;

	protected JsonObject() {}
	
	public JsonObject(long id, String classname, long locationId, String name, String description, String imgUrl, List<JsonVariable> fields, List<JsonMethod> methods,
			List<JsonInventory> inventories) {
		super();
		this.id = id;
		this.classname = classname;
		this.name = (name!=null)?name:classname;
		this.description = description;
		this.imgUrl = imgUrl;
		this.locationId = locationId;
		this.fields = fields;
		this.methods = methods;
		this.inventories = inventories;
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

	public List<JsonMethod> getMethods() {
		return methods;
	}

	public void setMethods(List<JsonMethod> methods) {
		this.methods = methods;
	}
	
	public List<JsonInventory> getInventories() {
		return inventories;
	}
	
	public void setInventories(List<JsonInventory> inventories) {
		this.inventories = inventories;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	
}