package es.usc.rai.coego.martin.demiurgo.json;

public class DeleteVariableRequest {
	private String path;
	private String varName;
	
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	
	public String getVarName() {
		return varName;
	}
	public void setVarName(String varName) {
		this.varName = varName;
	}
}
