package rs.ac.uns.ftn.mykeepserver.web.dto;

public class ChangePasswordRequestDTO {

	private String oldPassword;
	private String newPassword;
	private String repeatedNewPassword;
	
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
	
	public String getRepeatedNewPassword() {
		return repeatedNewPassword;
	}
	
	public void setRepeatedNewPassword(String repeatedNewPassword) {
		this.repeatedNewPassword = repeatedNewPassword;
	}
	
}
