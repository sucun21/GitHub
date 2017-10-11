package vn.sogo.lmscms.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class CourseInfo implements Serializable {
	private Integer courseId;
	private String courseTitle;
	private String courseDes;
	private String courseShortDes;
	private String courseImage;
	public String getCourseImage() {
		return courseImage;
	}
	public void setCourseImage(String courseImage) {
		this.courseImage = courseImage;
	}
	public Integer getCourseId() {
		return courseId;
	}
	public void setCourseId(Integer courseId) {
		this.courseId = courseId;
	}
	public String getCourseTitle() {
		return courseTitle;
	}
	public void setCourseTitle(String courseTitle) {
		this.courseTitle = courseTitle;
	}
	public String getCourseDes() {
		return courseDes;
	}
	public void setCourseDes(String courseDes) {
		this.courseDes = courseDes;
	}
	public String getCourseShortDes() {
		return courseShortDes;
	}
	public void setCourseShortDes(String courseShortDes) {
		this.courseShortDes = courseShortDes;
	}
}
