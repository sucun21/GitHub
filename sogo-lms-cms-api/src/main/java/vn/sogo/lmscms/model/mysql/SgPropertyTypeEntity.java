package vn.sogo.lmscms.model.mysql;

import javax.persistence.*;

/**
 * Created by VinhLe on 4/25/2017.
 */
@Entity
@Table(name = "sg_property_type", schema = "", catalog = "lms_portal")
public class SgPropertyTypeEntity {
    private Integer propertyTypeId;
    private String propertyTypeName;
    private String description;

    @Id
    @Column(name = "property_type_id")
    public Integer getPropertyTypeId() {
        return propertyTypeId;
    }

    public void setPropertyTypeId(Integer propertyTypeId) {
        this.propertyTypeId = propertyTypeId;
    }

    @Basic
    @Column(name = "property_type_name")
    public String getPropertyTypeName() {
        return propertyTypeName;
    }

    public void setPropertyTypeName(String propertyTypeName) {
        this.propertyTypeName = propertyTypeName;
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

        SgPropertyTypeEntity that = (SgPropertyTypeEntity) o;

        if (propertyTypeId != null ? !propertyTypeId.equals(that.propertyTypeId) : that.propertyTypeId != null)
            return false;
        if (propertyTypeName != null ? !propertyTypeName.equals(that.propertyTypeName) : that.propertyTypeName != null)
            return false;
        if (description != null ? !description.equals(that.description) : that.description != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = propertyTypeId != null ? propertyTypeId.hashCode() : 0;
        result = 31 * result + (propertyTypeName != null ? propertyTypeName.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }
}
