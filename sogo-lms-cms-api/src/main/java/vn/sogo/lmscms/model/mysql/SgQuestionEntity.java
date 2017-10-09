package vn.sogo.lmscms.model.mysql;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by VinhLe on 4/25/2017.
 */
@Entity
@Table(name = "sg_question", schema = "", catalog = "lms_portal")
public class SgQuestionEntity {
    private Integer questionId;
    private Integer questionTypeId;
    private Integer activityId;
    private String questionTitle;
    private String questionContent;
    private String rightAnswerIndex;
    private String questionHint;
    private Byte isActive;
    private Byte isDelete;
    private Timestamp createdDate;
    private Integer createdBy;
    private Timestamp modifiedDate;
    private Integer modifiedBy;

    private SgQuestionTypeEntity sgQuestionTypeByQuestionTypeId;

    @Id
    @Column(name = "question_id")
    public Integer getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

    @Basic
    @Column(name = "question_type_id", insertable = false, updatable = false)
    public Integer getQuestionTypeId() {
        return questionTypeId;
    }

    public void setQuestionTypeId(Integer questionTypeId) {
        this.questionTypeId = questionTypeId;
    }

    @Basic
    @Column(name = "activity_id")
    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    @Basic
    @Column(name = "question_title")
    public String getQuestionTitle() {
        return questionTitle;
    }

    public void setQuestionTitle(String questionTitle) {
        this.questionTitle = questionTitle;
    }

    @Basic
    @Column(name = "question_content")
    public String getQuestionContent() {
        return questionContent;
    }

    public void setQuestionContent(String questionContent) {
        this.questionContent = questionContent;
    }

    @Basic
    @Column(name = "right_answer_index")
    public String getRightAnswerIndex() {
        return rightAnswerIndex;
    }

    public void setRightAnswerIndex(String rightAnswerIndex) {
        this.rightAnswerIndex = rightAnswerIndex;
    }

    @Basic
    @Column(name = "question_hint")
    public String getQuestionHint() {
        return questionHint;
    }

    public void setQuestionHint(String questionHint) {
        this.questionHint = questionHint;
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

        SgQuestionEntity that = (SgQuestionEntity) o;

        if (questionId != null ? !questionId.equals(that.questionId) : that.questionId != null) return false;
        if (questionTypeId != null ? !questionTypeId.equals(that.questionTypeId) : that.questionTypeId != null)
            return false;
        if (activityId != null ? !activityId.equals(that.activityId) : that.activityId != null) return false;
        if (questionTitle != null ? !questionTitle.equals(that.questionTitle) : that.questionTitle != null)
            return false;
        if (questionContent != null ? !questionContent.equals(that.questionContent) : that.questionContent != null)
            return false;
        if (rightAnswerIndex != null ? !rightAnswerIndex.equals(that.rightAnswerIndex) : that.rightAnswerIndex != null)
            return false;
        if (questionHint != null ? !questionHint.equals(that.questionHint) : that.questionHint != null) return false;
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
        int result = questionId != null ? questionId.hashCode() : 0;
        result = 31 * result + (questionTypeId != null ? questionTypeId.hashCode() : 0);
        result = 31 * result + (activityId != null ? activityId.hashCode() : 0);
        result = 31 * result + (questionTitle != null ? questionTitle.hashCode() : 0);
        result = 31 * result + (questionContent != null ? questionContent.hashCode() : 0);
        result = 31 * result + (rightAnswerIndex != null ? rightAnswerIndex.hashCode() : 0);
        result = 31 * result + (questionHint != null ? questionHint.hashCode() : 0);
        result = 31 * result + (isActive != null ? isActive.hashCode() : 0);
        result = 31 * result + (isDelete != null ? isDelete.hashCode() : 0);
        result = 31 * result + (createdDate != null ? createdDate.hashCode() : 0);
        result = 31 * result + (createdBy != null ? createdBy.hashCode() : 0);
        result = 31 * result + (modifiedDate != null ? modifiedDate.hashCode() : 0);
        result = 31 * result + (modifiedBy != null ? modifiedBy.hashCode() : 0);
        return result;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_type_id", referencedColumnName = "question_type_id", nullable = false)
    public SgQuestionTypeEntity getSgQuestionTypeByQuestionTypeId() {
        return sgQuestionTypeByQuestionTypeId;
    }

    public void setSgQuestionTypeByQuestionTypeId(SgQuestionTypeEntity sgQuestionTypeByQuestionTypeId) {
        this.sgQuestionTypeByQuestionTypeId = sgQuestionTypeByQuestionTypeId;
    }
}
