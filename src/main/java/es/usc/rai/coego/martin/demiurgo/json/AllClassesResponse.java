package es.usc.rai.coego.martin.demiurgo.json;

import java.util.List;

public class AllClassesResponse {
	private List<JsonClass> classes;
	
	public List<JsonClass> getClasses() {
		return classes;
	}
	
	public void setClasses(List<JsonClass> classes) {
		this.classes = classes;
	}
}
