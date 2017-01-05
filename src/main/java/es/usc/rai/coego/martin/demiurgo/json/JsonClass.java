package es.usc.rai.coego.martin.demiurgo.json;

import java.util.List;

public class JsonClass {
	private String classname;
	private JsonClass parent;
	private List<JsonVariable> fields;
	private List<JsonMethod> methods;
	private String code;
	
	public String getClassName() {
		return classname;
	}
	public void setClassName(String name) {
		this.classname = name;
	}
	public JsonClass getParent() {
		return parent;
	}
	public void setParent(JsonClass parent) {
		this.parent = parent;
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
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
}
