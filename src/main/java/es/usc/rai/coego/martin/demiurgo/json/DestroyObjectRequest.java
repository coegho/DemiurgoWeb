package es.usc.rai.coego.martin.demiurgo.json;

public class DestroyObjectRequest {
	private long objId;
	private boolean destroyContents;
	
	public long getObjId() {
		return objId;
	}
	
	public void setObjId(long objId) {
		this.objId = objId;
	}
	
	public boolean isDestroyContents() {
		return destroyContents;
	}
	
	public void setDestroyContents(boolean destroyContents) {
		this.destroyContents = destroyContents;
	}
}
