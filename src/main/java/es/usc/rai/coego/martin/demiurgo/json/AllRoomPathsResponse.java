package es.usc.rai.coego.martin.demiurgo.json;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AllRoomPathsResponse {
	private ResponseStatus status;
	private List<String> paths;
	
	public AllRoomPathsResponse() {
	}
	
	public AllRoomPathsResponse(ResponseStatus status, List<String> paths) {
		this.status = status;
		this.paths = paths;
	}
	
	public ResponseStatus getStatus() {
		return status;
	}
	public void setStatus(ResponseStatus status) {
		this.status = status;
	}
	public List<String> getPaths() {
		return paths;
	}
	public void setPaths(List<String> paths) {
		this.paths = paths;
	}
}
