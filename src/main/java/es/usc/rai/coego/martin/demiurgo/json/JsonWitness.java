package es.usc.rai.coego.martin.demiurgo.json;

public class JsonWitness {
	private String user;
	private String decision;
	
	public JsonWitness() { }
	
	public JsonWitness(String user, String decision) {
		this.user = user;
		this.decision = decision;
	}

	public String getUser() {
		return user;
	}
	
	public void setUser(String user) {
		this.user = user;
	}
	
	public String getDecision() {
		return decision;
	}
	
	public void setDecision(String decision) {
		this.decision = decision;
	}
}
