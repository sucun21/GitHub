package vn.sogo.lmscms.model.mysql;

import javax.persistence.*;

/**
 * Created by VinhLe on 4/25/2017.
 */
@Entity
@Table(name = "sg_user_type", schema = "", catalog = "lms_portal")
public class SgUserTypeEntity {
    private Integer userTypeId;
    private String userTypeName;
    private String description;

    @Id
    @Column(name = "user_type_id")
    public Integer getUserTypeId() {
        return userTypeId;
    }

    public void setUserTypeId(Integer userTypeId) {
        this.userTypeId = userTypeId;
    }

    @Basic
    @Column(name = "user_type_name")
    public String getUserTypeName() {
        return userTypeName;
    }

    public void setUserTypeName(String userTypeName) {
        this.userTypeName = userTypeName;
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

        SgUserTypeEntity that = (SgUserTypeEntity) o;

        if (userTypeId != null ? !userTypeId.equals(that.userTypeId) : that.userTypeId != null) return false;
        if (userTypeName != null ? !userTypeName.equals(that.userTypeName) : that.userTypeName != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userTypeId != null ? userTypeId.hashCode() : 0;
        result = 31 * result + (userTypeName != null ? userTypeName.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }
}
