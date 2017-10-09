package vn.sogo.lmscms.model.mysql;

import javax.persistence.*;

/**
 * Created by VinhLe on 4/25/2017.
 */
@Entity
@Table(name = "sg_activity_type", schema = "", catalog = "lms_portal")
public class SgActivityTypeEntity {
    private Integer activityTypeId;
    private String activityTypeName;
    private String description;

    @Id
    @Column(name = "activity_type_id")
    public Integer getActivityTypeId() {
        return activityTypeId;
    }

    public void setActivityTypeId(Integer activityTypeId) {
        this.activityTypeId = activityTypeId;
    }

    @Basic
    @Column(name = "activity_type_name")
    public String getActivityTypeName() {
        return activityTypeName;
    }

    public void setActivityTypeName(String activityTypeName) {
        this.activityTypeName = activityTypeName;
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

        SgActivityTypeEntity that = (SgActivityTypeEntity) o;

        if (activityTypeId != null ? !activityTypeId.equals(that.activityTypeId) : that.activityTypeId != null)
            return false;
        if (activityTypeName != null ? !activityTypeName.equals(that.activityTypeName) : that.activityTypeName != null)
            return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = activityTypeId != null ? activityTypeId.hashCode() : 0;
        result = 31 * result + (activityTypeName != null ? activityTypeName.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }
}
