package vn.sogo.lmscms.model.request;

public class ResetStudentPasswordRequest {
	private Integer actionUser;
	private Integer studentId;
	private String newPassword;
	public Integer getActionUser() {
		return actionUser;
	}
	public void setActionUser(Integer actionUser) {
		this.actionUser = actionUser;
	}
	public Integer getStudentId() {
		return studentId;
	}
	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	
	
}
