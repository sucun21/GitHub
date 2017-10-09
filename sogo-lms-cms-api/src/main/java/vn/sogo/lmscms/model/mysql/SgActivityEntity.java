package vn.sogo.lmscms.model.mysql;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Arrays;

/**
 * Created by VinhLe on 4/25/2017.
 */
@Entity
@Table(name = "sg_activity", schema = "", catalog = "lms_portal")
public class SgActivityEntity implements Serializable {
    private Integer activityId;
    private Integer activityTypeId;
    private Integer lessonId;
    private String activityName;
    private Integer activityLength;
    private byte[] activityDes;
    private byte[] activityData;
    private Integer activityTime;
    private String videoUrl;
    private Integer propertyId;
    private Integer sortOrder;
    private Byte isActive;
    private Byte isDelete;
    private Timestamp createdDate;
    private Integer createdBy;
    private Timestamp modifiedDate;
    private Integer modifiedBy;

    @Id
    @Column(name = "activity_id")
    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    @Basic
    @Column(name = "activity_type_id")
    public Integer getActivityTypeId() {
        return activityTypeId;
    }

    public void setActivityTypeId(Integer activityTypeId) {
        this.activityTypeId = activityTypeId;
    }

    @Basic
    @Column(name = "lesson_id")
    public Integer getLessonId() {
        return lessonId;
    }

    public void setLessonId(Integer lessonId) {
        this.lessonId = lessonId;
    }

    @Basic
    @Column(name = "activity_name")
    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    @Basic
    @Column(name = "activity_length")
    public Integer getActivityLength() {
        return activityLength;
    }

    public void setActivityLength(Integer activityLength) {
        this.activityLength = activityLength;
    }

    @Basic
    @Column(name = "activity_des")
    public byte[] getActivityDes() {
        return activityDes;
    }

    public void setActivityDes(byte[] activityDes) {
        this.activityDes = activityDes;
    }

    @Basic
    @Column(name = "activity_data")
    public byte[] getActivityData() {
        return activityData;
    }

    public void setActivityData(byte[] activityData) {
        this.activityData = activityData;
    }

    @Basic
    @Column(name = "activity_time")
    public Integer getActivityTime() {
        return activityTime;
    }

    public void setActivityTime(Integer activityTime) {
        this.activityTime = activityTime;
    }

    @Basic
    @Column(name = "video_url")
    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    @Basic
    @Column(name = "property_id")
    public Integer getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(Integer propertyId) {
        this.propertyId = propertyId;
    }

    @Basic
    @Column(name = "sort_order")
    public Integer getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(Integer sortOrder) {
        this.sortOrder = sortOrder;
    }

    @Basic
    @Column(name = "is_active")
    public Byte getIsActive() {
        return isActive;
    }

    public void setIsActive(Byte isActive) {
        this.isActive = isActive;
    }

    @Basic
    @Column(name = "is_delete")
    public Byte getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Byte isDelete) {
        this.isDelete = isDelete;
    }

    @Basic
    @Column(name = "created_date")
    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    @Basic
    @Column(name = "created_by")
    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    @Basic
    @Column(name = "modified_date")
    public Timestamp getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Timestamp modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    @Basic
    @Column(name = "modified_by")
    public Integer getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(Integer modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SgActivityEntity that = (SgActivityEntity) o;

        if (activityId != null ? !activityId.equals(that.activityId) : that.activityId != null) return false;
        if (activityTypeId != null ? !activityTypeId.equals(that.activityTypeId) : that.activityTypeId != null)
            return false;
        if (lessonId != null ? !lessonId.equals(that.lessonId) : that.lessonId != null) return false;
        if (activityName != null ? !activityName.equals(that.activityName) : that.activityName != null) return false;
        if (activityLength != null ? !activityLength.equals(that.activityLength) : that.activityLength != null)
            return false;
        if (!Arrays.equals(activityDes, that.activityDes)) return false;
        if (!Arrays.equals(activityData, that.activityData)) return false;
        if (activityTime != null ? !activityTime.equals(that.activityTime) : that.activityTime != null) return false;
        if (videoUrl != null ? !videoUrl.equals(that.videoUrl) : that.videoUrl != null) return false;
        if (propertyId != null ? !propertyId.equals(that.propertyId) : that.propertyId != null) return false;
        if (sortOrder != null ? !sortOrder.equals(that.sortOrder) : that.sortOrder != null) return false;
        if (isActive != null ? !isActive.equals(that.isActive) : that.isActive != null) return false;
        if (isDelete != null ? !isDelete.equals(that.isDelete) : that.isDelete != null) return false;
        if (createdDate != null ? !createdDate.equals(that.createdDate) : that.createdDate != null) return false;
        if (createdBy != null ? !createdBy.equals(that.createdBy) : that.createdBy != null) return false;
        if (modifiedDate != null ? !modifiedDate.equals(that.modifiedDate) : that.modifiedDate != null) return false;
        if (modifiedBy != null ? !modifiedBy.equals(that.modifiedBy) : that.modifiedBy != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = activityId != null ? activityId.hashCode() : 0;
        result = 31 * result + (activityTypeId != null ? activityTypeId.hashCode() : 0);
        result = 31 * result + (lessonId != null ? lessonId.hashCode() : 0);
        result = 31 * result + (activityName != null ? activityName.hashCode() : 0);
        result = 31 * result + (activityLength != null ? activityLength.hashCode() : 0);
        result = 31 * result + (activityDes != null ? Arrays.hashCode(activityDes) : 0);
        result = 31 * result + (activityData != null ? Arrays.hashCode(activityData) : 0);
        result = 31 * result + (activityTime != null ? activityTime.hashCode() : 0);
        result = 31 * result + (videoUrl != null ? videoUrl.hashCode() : 0);
        result = 31 * result + (propertyId != null ? propertyId.hashCode() : 0);
        result = 31 * result + (sortOrder != null ? sortOrder.hashCode() : 0);
        result = 31 * result + (isActive != null ? isActive.hashCode() : 0);
        result = 31 * result + (isDelete != null ? isDelete.hashCode() : 0);
        result = 31 * result + (createdDate != null ? createdDate.hashCode() : 0);
        result = 31 * result + (createdBy != null ? createdBy.hashCode() : 0);
        result = 31 * result + (modifiedDate != null ? modifiedDate.hashCode() : 0);
        result = 31 * result + (modifiedBy != null ? modifiedBy.hashCode() : 0);
        return result;
    }
}
