package vn.sogo.lmscms.model.request;

import java.io.Serializable;

@SuppressWarnings("serial")
public class ExcuteUnitCourse implements Serializable {
	private Integer courseId;
	private String unitTitle;
	private String excuteType;
	public Integer getCourseId() {
		return courseId;
	}
	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}
	public String getUnitTitle() {
		return unitTitle;
	}
	public void setUnitTitle(String unitTitle) {
		this.unitTitle = unitTitle;
	}
	public String getExcuteType() {
		return excuteType;
	}
	public void setExcuteType(String excuteType) {
		this.excuteType = excuteType;
	}
	

    
	
}
