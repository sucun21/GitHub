package vn.sogo.lmscms.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by VinhLe on 4/20/2017.
 */
public class ResponseGetLessonByUnit implements Serializable/* extends BaseResponse*/ {

    private Integer courseId;
    private Integer unitId;
    private String unitTitle;
    private byte[] unitDes;
    private byte[] unitSummary;
    private Integer lessonId;
    private String lessonTitle;
    private byte[] lessonDes;
    private Integer sortOrder;

    /*public ResponseGetLessonByUnit(boolean isSuccess, String message) {
        super(isSuccess, message);
    }*/

    public ResponseGetLessonByUnit(Integer courseId, Integer unitId, String unitTitle, byte[] unitDes, byte[] unitSummary, Integer lessonId, String lessonTitle, byte[] lessonDes, Integer sortOrder) {
//        super(Boolean.TRUE, DefaultSuccess);

        this.courseId = courseId;
        this.unitId = unitId;
        this.unitTitle = unitTitle;
        this.unitDes = unitDes;
        this.unitSummary = unitSummary;
        this.lessonId = lessonId;
        this.lessonTitle = lessonTitle;
        this.lessonDes = lessonDes;
        this.sortOrder = sortOrder;
    }

    @JsonProperty("course_id")
    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    @JsonProperty("unit_id")
    public Integer getUnitId() {
        return unitId;
    }

    public void setUnitId(int unitId) {
        this.unitId = unitId;
    }

    @JsonProperty("unit_title")
    public String getUnitTitle() {
        return unitTitle;
    }

    public void setUnitTitle(String unitTitle) {
        this.unitTitle = unitTitle;
    }

    @JsonProperty("unit_des")
    public byte[] getUnitDes() {
        return unitDes;
    }

    public void setUnitDes(byte[] unitDes) {
        this.unitDes = unitDes;
    }

    @JsonProperty("unit_summary")
    public byte[] getUnitSummary() {
        return unitSummary;
    }

    public void setUnitSummary(byte[] unitSummary) {
        this.unitSummary = unitSummary;
    }

    @JsonProperty("lesson_id")
    public Integer getLessonId() {
        return lessonId;
    }

    public void setLessonId(int lessonId) {
        this.lessonId = lessonId;
    }

    @JsonProperty("lesson_title")
    public String getLessonTitle() {
        return lessonTitle;
    }

    public void setLessonTitle(String lessonTitle) {
        this.lessonTitle = lessonTitle;
    }

    @JsonProperty("lesson_des")
    public byte[] getLessonDes() {
        return lessonDes;
    }

    public void setLessonDes(byte[] lessonDes) {
        this.lessonDes = lessonDes;
    }

    @JsonProperty("sort_order")
    public Integer getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(int sortOrder) {
        this.sortOrder = sortOrder;
    }
}
