package es.usc.rai.coego.martin.demiurgo.json;

import java.util.List;

public class JsonAction {
	private long id;
	private String narration;
	private String room;
	private List<JsonWitness> witnesses;
	private String date;
	private boolean published;
	
	public JsonAction() {
	}
	
	public JsonAction(long id, String narration, String room, List<JsonWitness> witnesses, String date, boolean published) {
		super();
		this.id = id;
		this.narration = narration;
		this.room = room;
		this.witnesses = witnesses;
		this.date = date;
		this.published = published;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNarration() {
		return narration;
	}

	public void setNarration(String narration) {
		this.narration = narration;
	}

	public String getRoom() {
		return room;
	}

	public void setRoom(String room) {
		this.room = room;
	}

	public List<JsonWitness> getWitnesses() {
		return witnesses;
	}

	public void setWitnesses(List<JsonWitness> witnesses) {
		this.witnesses = witnesses;
	}
	
	public String getDate() {
		return date;
	}
	
	public void setDate(String date) {
		this.date = date;
	}
	
	public boolean isPublished() {
		return published;
	}
	
	public void setPublished(boolean published) {
		this.published = published;
	}
}
