package es.usc.rai.coego.martin.demiurgo.web.forms;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class NarrateActionForm {
	@NotNull
	@Min(0)
	private long actionId;
	@NotNull
	private String narration;
	/*@NotNull
	@Pattern(regexp = "^/([\\w\\d_])+(/([\\w\\d_])+)*$", flags={Pattern.Flag.CASE_INSENSITIVE})
	private String path;*/
	
	public long getActionId() {
		return actionId;
	}
	
	public void setActionId(long actionId) {
		this.actionId = actionId;
	}
	
	public String getNarration() {
		return narration;
	}
	
	public void setNarration(String narration) {
		this.narration = narration;
	}
	
	/*public String getPath() {
		return path;
	}
	
	public void setPath(String path) {
		this.path = path;
	}*/
}
