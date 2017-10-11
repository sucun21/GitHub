package vn.sogo.lmscms.model;

import java.sql.Timestamp;

public class ActivityLogInfo {
	private Integer timelineId;
	private Integer timelineTypeId;
	private String postContent;
	private String postByName;
	private Boolean isShowtimeline;
	private String createdByName;
	private Timestamp createdDate;
	private String commentResultName;
	private String commentResultType;
	public Integer getTimelineId() {
		return timelineId;
	}
	public void setTimelineId(Integer timelineId) {
		this.timelineId = timelineId;
	}
	public Integer getTimelineTypeId() {
		return timelineTypeId;
	}
	public void setTimelineTypeId(Integer timelineTypeId) {
		this.timelineTypeId = timelineTypeId;
	}
	public String getPostContent() {
		return postContent;
	}
	public void setPostContent(String postContent) {
		this.postContent = postContent;
	}
	public String getPostByName() {
		return postByName;
	}
	public void setPostByName(String postByName) {
		this.postByName = postByName;
	}
	public Boolean getIsShowtimeline() {
		return isShowtimeline;
	}
	public void setIsShowtimeline(Boolean isShowtimeline) {
		this.isShowtimeline = isShowtimeline;
	}
	public String getCreatedByName() {
		return createdByName;
	}
	public void setCreatedByName(String createdByName) {
		this.createdByName = createdByName;
	}
	public Timestamp getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}
	public String getCommentResultName() {
		return commentResultName;
	}
	public void setCommentResultName(String commentResultName) {
		this.commentResultName = commentResultName;
	}
	public String getCommentResultType() {
		return commentResultType;
	}
	public void setCommentResultType(String commentResultType) {
		this.commentResultType = commentResultType;
	}
}
