package es.usc.rai.coego.martin.demiurgo.web.forms;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class CreateClassForm {
	@NotNull
	@Pattern(regexp = "^([\\w\\d_])+$", flags={Pattern.Flag.CASE_INSENSITIVE})
	private String className;
	
	public String getClassName() {
		return className;
	}
	
	public void setClassName(String className) {
		this.className = className;
	}
}
