package es.usc.rai.coego.martin.demiurgo.web.forms;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class ProcessCodeForm {
	@NotNull
	@Size(min=3)
	@Pattern(regexp = "^/([\\w\\d_])+(/([\\w\\d_])+)*$", flags={Pattern.Flag.CASE_INSENSITIVE})
	private String path;
	
	@NotNull
	private String code;
	
	public String getPath() {
		return path;
	}
	
	public void setPath(String path) {
		this.path = path;
	}
	
	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
}
