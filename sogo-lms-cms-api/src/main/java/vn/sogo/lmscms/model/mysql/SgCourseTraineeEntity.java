package vn.sogo.lmscms.model.mysql;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * Created by VinhLe on 4/25/2017.
 */
@Entity
@Table(name = "sg_course_trainee", schema = "", catalog = "lms_portal")
public class SgCourseTraineeEntity {
    private Integer courseTraineeId;
    private Integer courseId;
    private Integer traineeId;
    private String contractNo;
    private String activeCode;
    private Timestamp activeDate;
    private String activeStatus;
    private Date startDate;
    private Date endDate;
    private Byte isActive;
    private Byte isDelete;
    private Timestamp createdDate;
    private Integer createdBy;
    private Timestamp modifiedDate;
    private Integer modifiedBy;

    @Id
    @Column(name = "course_trainee_id")
    public Integer getCourseTraineeId() {
        return courseTraineeId;
    }

    public void setCourseTraineeId(Integer courseTraineeId) {
        this.courseTraineeId = courseTraineeId;
    }

    @Basic
    @Column(name = "course_id")
    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    @Basic
    @Column(name = "trainee_id")
    public Integer getTraineeId() {
        return traineeId;
    }

    public void setTraineeId(Integer traineeId) {
        this.traineeId = traineeId;
    }

    @Basic
    @Column(name = "contract_no")
    public String getContractNo() {
        return contractNo;
    }

    public void setContractNo(String contractNo) {
        this.contractNo = contractNo;
    }

    @Basic
    @Column(name = "active_code")
    public String getActiveCode() {
        return activeCode;
    }

    public void setActiveCode(String activeCode) {
        this.activeCode = activeCode;
    }

    @Basic
    @Column(name = "active_date")
    public Timestamp getActiveDate() {
        return activeDate;
    }

    public void setActiveDate(Timestamp activeDate) {
        this.activeDate = activeDate;
    }

    @Basic
    @Column(name = "active_status")
    public String getActiveStatus() {
        return activeStatus;
    }

    public void setActiveStatus(String activeStatus) {
        this.activeStatus = activeStatus;
    }

    @Basic
    @Column(name = "start_date")
    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @Basic
    @Column(name = "end_date")
    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
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

        SgCourseTraineeEntity that = (SgCourseTraineeEntity) o;

        if (courseTraineeId != null ? !courseTraineeId.equals(that.courseTraineeId) : that.courseTraineeId != null)
            return false;
        if (courseId != null ? !courseId.equals(that.courseId) : that.courseId != null) return false;
        if (traineeId != null ? !traineeId.equals(that.traineeId) : that.traineeId != null) return false;
        if (contractNo != null ? !contractNo.equals(that.contractNo) : that.contractNo != null) return false;
        if (activeCode != null ? !activeCode.equals(that.activeCode) : that.activeCode != null) return false;
        if (activeDate != null ? !activeDate.equals(that.activeDate) : that.activeDate != null) return false;
        if (activeStatus != null ? !activeStatus.equals(that.activeStatus) : that.activeStatus != null) return false;
        if (startDate != null ? !startDate.equals(that.startDate) : that.startDate != null) return false;
        if (endDate != null ? !endDate.equals(that.endDate) : that.endDate != null) return false;
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
        int result = courseTraineeId != null ? courseTraineeId.hashCode() : 0;
        result = 31 * result + (courseId != null ? courseId.hashCode() : 0);
        result = 31 * result + (traineeId != null ? traineeId.hashCode() : 0);
        result = 31 * result + (contractNo != null ? contractNo.hashCode() : 0);
        result = 31 * result + (activeCode != null ? activeCode.hashCode() : 0);
        result = 31 * result + (activeDate != null ? activeDate.hashCode() : 0);
        result = 31 * result + (activeStatus != null ? activeStatus.hashCode() : 0);
        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
        result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
        result = 31 * result + (isActive != null ? isActive.hashCode() : 0);
        result = 31 * result + (isDelete != null ? isDelete.hashCode() : 0);
        result = 31 * result + (createdDate != null ? createdDate.hashCode() : 0);
        result = 31 * result + (createdBy != null ? createdBy.hashCode() : 0);
        result = 31 * result + (modifiedDate != null ? modifiedDate.hashCode() : 0);
        result = 31 * result + (modifiedBy != null ? modifiedBy.hashCode() : 0);
        return result;
    }
}
