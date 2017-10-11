package vn.sogo.lmscms.model;

import java.io.Serializable;
import java.sql.Timestamp;

@SuppressWarnings("serial")
public class TrainerCourseInfo implements Serializable {
	private Integer trainerId;
	private Integer courseTrainerId;
	public Integer getTrainerId() {
		return trainerId;
	}
	public void setTrainerId(Integer trainerId) {
		this.trainerId = trainerId;
	}
	public Integer getCourseTrainerId() {
		return courseTrainerId;
	}
	public void setCourseTrainerId(Integer courseTrainerId) {
		this.courseTrainerId = courseTrainerId;
	}	
	
}
