package es.usc.rai.coego.martin.demiurgo.web.forms;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class CreateClassSecondForm {
	@NotNull
	@Pattern(regexp = "^([\\w\\d_])+$", flags={Pattern.Flag.CASE_INSENSITIVE})
	private String className;
	
	@NotNull
	private String code;
	
	public String getClassName() {
		return className;
	}
	
	public void setClassName(String className) {
		this.className = className;
	}
	
	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
}
