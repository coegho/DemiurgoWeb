package es.usc.rai.coego.martin.demiurgo.json;

import java.util.List;

public class JsonPendingRoom {
	private String path;
	private int numObjects;
	private int numUsers;
	private List<String> decidingUsers;
	
	public JsonPendingRoom() { }
	
	public JsonPendingRoom(String path, int numObjects, int numUsers, List<String> decidingUsers) {
		this.path = path;
		this.numObjects = numObjects;
		this.numUsers = numUsers;
		this.decidingUsers = decidingUsers;
	}
	
	public String getPath() {
		return path;
	}
	
	public void setPath(String path) {
		this.path = path;
	}
	
	public int getNumObjects() {
		return numObjects;
	}
	
	public void setNumObjects(int numObjects) {
		this.numObjects = numObjects;
	}
	
	public int getNumUsers() {
		return numUsers;
	}
	
	public void setNumUsers(int numUsers) {
		this.numUsers = numUsers;
	}
	
	public List<String> getDecidingUsers() {
		return decidingUsers;
	}
	
	public void setDecidingUsers(List<String> decidingUsers) {
		this.decidingUsers = decidingUsers;
	}
}
