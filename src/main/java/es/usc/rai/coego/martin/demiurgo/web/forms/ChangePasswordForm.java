package es.usc.rai.coego.martin.demiurgo.web.forms;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class ChangePasswordForm {
	@NotNull
	@Size(min = 4)
	private String oldPassword;
	
	@NotNull
	@Size(min = 4)
	private String newPassword;

	public String getOldPassword() {
		return oldPassword;
	}
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	
	
}
