package vn.sogo.lmscms.model.mysql;

import javax.persistence.*;

/**
 * Created by VinhLe on 4/25/2017.
 */
@Entity
@Table(name = "sg_question_type", schema = "", catalog = "lms_portal")
public class SgQuestionTypeEntity {
    private Integer questionTypeId;
    private String questionTypeName;
    private String description;

    @Id
    @Column(name = "question_type_id")
    public Integer getQuestionTypeId() {
        return questionTypeId;
    }

    public void setQuestionTypeId(Integer questionTypeId) {
        this.questionTypeId = questionTypeId;
    }

    @Basic
    @Column(name = "question_type_name")
    public String getQuestionTypeName() {
        return questionTypeName;
    }

    public void setQuestionTypeName(String questionTypeName) {
        this.questionTypeName = questionTypeName;
    }

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SgQuestionTypeEntity that = (SgQuestionTypeEntity) o;

        if (questionTypeId != null ? !questionTypeId.equals(that.questionTypeId) : that.questionTypeId != null)
            return false;
        if (questionTypeName != null ? !questionTypeName.equals(that.questionTypeName) : that.questionTypeName != null)
            return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = questionTypeId != null ? questionTypeId.hashCode() : 0;
        result = 31 * result + (questionTypeName != null ? questionTypeName.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }
}
