package vn.sogo.lmscms.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by VinhLe on 4/22/2017.
 */
public class ResponseGetActivityStudyByLesson implements Serializable {

    private Integer activityId;
    private Integer activityTypeId;
    private Integer lessonId;
    private String activityName;
    private Integer activityLength;
    private byte[] activityData;
    private byte[] activityDes;
    private Integer activityTime;
    private String videoUrl;
    private Integer propertyId;
    private Integer sortOrder;

    public ResponseGetActivityStudyByLesson(Integer activityId, Integer activityTypeId, Integer lessonId, String activityName, Integer activityLength, byte[] activityData, byte[] activityDes, Integer activityTime, String videoUrl, Integer propertyId, Integer sortOrder) {
        this.activityId = activityId;
        this.activityTypeId = activityTypeId;
        this.lessonId = lessonId;
        this.activityName = activityName;
        this.activityLength = activityLength;
        this.activityData = activityData;
        this.activityDes = activityDes;
        this.activityTime = activityTime;
        this.videoUrl = videoUrl;
        this.propertyId = propertyId;
        this.sortOrder = sortOrder;
    }

    @JsonProperty("activity_id")
    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    @JsonProperty("activity_type_id")
    public Integer getActivityTypeId() {
        return activityTypeId;
    }

    public void setActivityTypeId(Integer activityTypeId) {
        this.activityTypeId = activityTypeId;
    }

    @JsonProperty("lesson_id")
    public Integer getLessonId() {
        return lessonId;
    }

    public void setLessonId(Integer lessonId) {
        this.lessonId = lessonId;
    }

    @JsonProperty("activity_name")
    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    @JsonProperty("activity_length")
    public Integer getActivityLength() {
        return activityLength;
    }

    public void setActivityLength(Integer activityLength) {
        this.activityLength = activityLength;
    }

    @JsonProperty("activity_data")
    public byte[] getActivityData() {
        return activityData;
    }

    public void setActivityData(byte[] activityData) {
        this.activityData = activityData;
    }

    @JsonProperty("activity_des")
    public byte[] getActivityDes() {
        return activityDes;
    }

    public void setActivityDes(byte[] activityDes) {
        this.activityDes = activityDes;
    }

    @JsonProperty("activity_time")
    public Integer getActivityTime() {
        return activityTime;
    }

    public void setActivityTime(Integer activityTime) {
        this.activityTime = activityTime;
    }

    @JsonProperty("video_url")
    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    @JsonProperty("property_id")
    public Integer getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(Integer propertyId) {
        this.propertyId = propertyId;
    }

    @JsonProperty("sort_order")
    public Integer getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }
}
