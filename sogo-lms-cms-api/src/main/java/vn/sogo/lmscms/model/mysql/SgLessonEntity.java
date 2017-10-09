package vn.sogo.lmscms.model.mysql;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Arrays;

/**
 * Created by VinhLe on 4/25/2017.
 */
@Entity
@Table(name = "sg_lesson", schema = "", catalog = "lms_portal")
public class SgLessonEntity implements Serializable {
    private Integer lessonId;
    private Integer unitId;
    private String lessonTitle;
    private byte[] lessonDes;
    private Integer sortOrder;
    private Byte isActive;
    private Byte isDelete;
    private Timestamp createdDate;
    private Integer createdBy;
    private Timestamp modifiedDate;
    private Integer modifiedBy;

    private SgUnitEntity sgUnitEntityByUnitId;

    @Id
    @Column(name = "lesson_id")
    public Integer getLessonId() {
        return lessonId;
    }

    public void setLessonId(Integer lessonId) {
        this.lessonId = lessonId;
    }

    @Basic
    @Column(name = "unit_id", insertable = false, updatable = false)
    public Integer getUnitId() {
        return unitId;
    }

    public void setUnitId(Integer unitId) {
        this.unitId = unitId;
    }

    @Basic
    @Column(name = "lesson_title")
    public String getLessonTitle() {
        return lessonTitle;
    }

    public void setLessonTitle(String lessonTitle) {
        this.lessonTitle = lessonTitle;
    }

    @Basic
    @Column(name = "lesson_des")
    public byte[] getLessonDes() {
        return lessonDes;
    }

    public void setLessonDes(byte[] lessonDes) {
        this.lessonDes = lessonDes;
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

        SgLessonEntity that = (SgLessonEntity) o;

        if (lessonId != null ? !lessonId.equals(that.lessonId) : that.lessonId != null) return false;
        if (unitId != null ? !unitId.equals(that.unitId) : that.unitId != null) return false;
        if (lessonTitle != null ? !lessonTitle.equals(that.lessonTitle) : that.lessonTitle != null) return false;
        if (!Arrays.equals(lessonDes, that.lessonDes)) return false;
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
        int result = lessonId != null ? lessonId.hashCode() : 0;
        result = 31 * result + (unitId != null ? unitId.hashCode() : 0);
        result = 31 * result + (lessonTitle != null ? lessonTitle.hashCode() : 0);
        result = 31 * result + (lessonDes != null ? Arrays.hashCode(lessonDes) : 0);
        result = 31 * result + (sortOrder != null ? sortOrder.hashCode() : 0);
        result = 31 * result + (isActive != null ? isActive.hashCode() : 0);
        result = 31 * result + (isDelete != null ? isDelete.hashCode() : 0);
        result = 31 * result + (createdDate != null ? createdDate.hashCode() : 0);
        result = 31 * result + (createdBy != null ? createdBy.hashCode() : 0);
        result = 31 * result + (modifiedDate != null ? modifiedDate.hashCode() : 0);
        result = 31 * result + (modifiedBy != null ? modifiedBy.hashCode() : 0);
        return result;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "unit_id", referencedColumnName = "unit_id", nullable = false)
    public SgUnitEntity getSgUnitEntityByUnitId() {
        return sgUnitEntityByUnitId;
    }

    public void setSgUnitEntityByUnitId(SgUnitEntity sgUnitEntityByUnitId) {
        this.sgUnitEntityByUnitId = sgUnitEntityByUnitId;
    }
}
