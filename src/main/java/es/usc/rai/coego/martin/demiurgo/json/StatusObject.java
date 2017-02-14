package es.usc.rai.coego.martin.demiurgo.json;

import java.util.List;

public class StatusObject {
	private String name;
	private String description;
	private String imgUrl;
	private List<JsonVariable> fields;
	private List<StatusInventory> inventories;
	
	protected StatusObject() { }

	public StatusObject(String name, String description, String imgUrl, List<JsonVariable> fields, List<StatusInventory> inventories) {
		super();
		this.name = name;
		this.description = description;
		this.imgUrl = imgUrl;
		this.fields = fields;
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
	
	public List<JsonVariable> getFields() {
		return fields;
	}

	public void setFields(List<JsonVariable> fields) {
		this.fields = fields;
	}

	public List<StatusInventory> getInventories() {
		return inventories;
	}

	public void setInventories(List<StatusInventory> inventories) {
		this.inventories = inventories;
	}
}
