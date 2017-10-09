package vn.sogo.lmscms.model.request;

public class AddActivityLogRequest {
	private String commentContent;
	private Integer commentTypeId;
	private Integer commentResultId;
	private Integer trainerId;
	private Integer courseTraineeId;
	private Integer studentId;
	private Boolean isAddTimeline;
	public String getCommentContent() {
		return commentContent;
	}
	public void setCommentContent(String commentContent) {
		this.commentContent = commentContent;
	}
	public Integer getCommentTypeId() {
		return commentTypeId;
	}
	public void setCommentTypeId(Integer commentTypeId) {
		this.commentTypeId = commentTypeId;
	}
	public Integer getCommentResultId() {
		return commentResultId;
	}
	public void setCommentResultId(Integer commentResultId) {
		this.commentResultId = commentResultId;
	}
	public Integer getTrainerId() {
		return trainerId;
	}
	public void setTrainerId(Integer trainerId) {
		this.trainerId = trainerId;
	}
	public Integer getCourseTraineeId() {
		return courseTraineeId;
	}
	public void setCourseTraineeId(Integer courseId) {
		this.courseTraineeId = courseId;
	}
	public Integer getStudentId() {
		return studentId;
	}
	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}
	public Boolean getIsAddTimeline() {
		return isAddTimeline;
	}
	public void setIsAddTimeline(Boolean isAddTimeline) {
		this.isAddTimeline = isAddTimeline;
	}
}
