package vn.sogo.lmscms.model.request;

import java.io.Serializable;
import java.sql.Timestamp;

@SuppressWarnings("serial")
public class ExcuteTrainerCourse implements Serializable {
	private Integer courseId;
	private Integer trainerId;
	private String excuteType;
	public Integer getCourseId() {
		return courseId;
	}
	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}
	public String getExcuteType() {
		return excuteType;
	}
	public void setExcuteType(String excuteType) {
		this.excuteType = excuteType;
	}
	public Integer getTrainerId() {
		return trainerId;
	}
	public void setTrainerId(Integer trainerId) {
		this.trainerId = trainerId;
	}
    
	
}
