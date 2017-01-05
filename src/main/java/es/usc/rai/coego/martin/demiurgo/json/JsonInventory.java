package es.usc.rai.coego.martin.demiurgo.json;

import java.util.List;

public class JsonInventory {
	private String name;
	private List<JsonObject> objects;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public List<JsonObject> getObjects() {
		return objects;
	}
	
	public void setObjects(List<JsonObject> objects) {
		this.objects = objects;
	}
}
