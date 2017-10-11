package vn.sogo.lmscms.model.request;

public class GetStudentActivityLogRequest {
	private Integer studentId;
	private Integer courseTraineeId;
	public Integer getStudentId() {
		return studentId;
	}
	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}
	public Integer getCourseTraineeId() {
		return courseTraineeId;
	}
	public void setCourseTraineeId(Integer courseTraineeId) {
		this.courseTraineeId = courseTraineeId;
	}
	
}
