package es.usc.rai.coego.martin.demiurgo.json;

import java.util.List;

/*
 * protected String className;
	protected DemiurgoClass parentClass;
	protected Map<String, ValueInterface> fields;
	protected Map<String, ClassMethod> methods;
	protected ClassMethod constructor;
	protected World world;
	protected String code;
 */
public class JsonClass {
	private String classname;
	private JsonClass parent;
	private List<JsonVariable> fields;
	private List<String> methods; //TODO
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
	public List<String> getMethods() {
		return methods;
	}
	public void setMethods(List<String> methods) {
		this.methods = methods;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
}
