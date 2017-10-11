package vn.sogo.lmscms.model;

public class ActivityDetails {
	private Integer activityId;
	private Integer activityTypeId;
	private String activityName;
	private String activityData;
	private String activityDes;
	private Integer activityTime;
	private Integer activityLength;
	private String videoUrl;
	public Integer getActivityId() {
		return activityId;
	}
	public void setActivityId(Integer activityId) {
		this.activityId = activityId;
	}
	public Integer getActivityTypeId() {
		return activityTypeId;
	}
	public void setActivityTypeId(Integer activityTypeId) {
		this.activityTypeId = activityTypeId;
	}
	public String getActivityName() {
		return activityName;
	}
	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}
	public String getActivityData() {
		return activityData;
	}
	public void setActivityData(String activityData) {
		this.activityData = activityData;
	}
	public String getActivityDes() {
		return activityDes;
	}
	public void setActivityDes(String activityDes) {
		this.activityDes = activityDes;
	}
	public Integer getActivityTime() {
		return activityTime;
	}
	public void setActivityTime(Integer activityTime) {
		this.activityTime = activityTime;
	}
	public Integer getActivityLength() {
		return activityLength;
	}
	public void setActivityLength(Integer activityLength) {
		this.activityLength = activityLength;
	}
	public String getVideoUrl() {
		return videoUrl;
	}
	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}
}
