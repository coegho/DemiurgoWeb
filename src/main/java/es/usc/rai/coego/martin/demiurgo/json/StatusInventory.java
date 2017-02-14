package es.usc.rai.coego.martin.demiurgo.json;

import java.util.List;

public class StatusInventory {
	private String name;
	private List<StatusObject> objects;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public List<StatusObject> getObjects() {
		return objects;
	}
	
	public void setObjects(List<StatusObject> objects) {
		this.objects = objects;
	}
}
