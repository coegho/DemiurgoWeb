package es.usc.rai.coego.martin.demiurgo.json;

public class FixCodeRequest {
	private long id;
	private String path;
	private String newCode;
	
	public FixCodeRequest() {
	}
	
	public FixCodeRequest(long id, String path, String newCode) {
		this.id = id;
		this.path = path;
		this.newCode = newCode;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getNewCode() {
		return newCode;
	}
	public void setNewCode(String newCode) {
		this.newCode = newCode;
	}
}
