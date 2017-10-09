package vn.sogo.lmscms.model.request;

import java.sql.Date;
import java.sql.Timestamp;

public class AddNewGroupClassRequest {
	private String groupClassName;
	private Integer courseId;
	private Integer centerId;
	private Integer trainerId;
	private Integer unitStartId;
	private Date startDate;
	private Timestamp startTime;
	private Integer startDayInWeek;
	private Integer createdBy;
	private Integer limitUser;
	private Integer facilitatorId;
	private Integer classTypeId;
	public String getGroupClassName() {
		return groupClassName;
	}
	public void setGroupClassName(String groupClassName) {
		this.groupClassName = groupClassName;
	}
	public Integer getCourseId() {
		return courseId;
	}
	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}
	public Integer getCenterId() {
		return centerId;
	}
	public void setCenterId(Integer centerId) {
		this.centerId = centerId;
	}
	public Integer getTrainerId() {
		return trainerId;
	}
	public void setTrainerId(Integer trainerId) {
		this.trainerId = trainerId;
	}
	public Integer getUnitStartId() {
		return unitStartId;
	}
	public void setUnitStartId(Integer unitStartId) {
		this.unitStartId = unitStartId;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Timestamp getStartTime() {
		return startTime;
	}
	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}
	public Integer getStartDayInWeek() {
		return startDayInWeek;
	}
	public void setStartDayInWeek(Integer startDayInWeek) {
		this.startDayInWeek = startDayInWeek;
	}
	public Integer getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}
	public Integer getLimitUser() {
		return limitUser;
	}
	public void setLimitUser(Integer limitUser) {
		this.limitUser = limitUser;
	}
	public Integer getFacilitatorId() {
		return facilitatorId;
	}
	public void setFacilitatorId(Integer facilitatorId) {
		this.facilitatorId = facilitatorId;
	}
	public Integer getClassTypeId() {
		return classTypeId;
	}
	public void setClassTypeId(Integer classTypeId) {
		this.classTypeId = classTypeId;
	}
}
