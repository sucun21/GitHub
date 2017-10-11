package vn.sogo.lmscms.model.request;

import java.io.Serializable;

@SuppressWarnings("serial")
public class GetUnitByCourseAndClassTypeRequest implements Serializable {
	private Integer courseId;
	private Integer classTypeId;
	public Integer getCourseId() {
		return courseId;
	}
	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}
	public Integer getClassTypeId() {
		return classTypeId;
	}
	public void setClassTypeId(Integer classTypeId) {
		this.classTypeId = classTypeId;
	}
}
