package vn.sogo.lmscms.model.mysql;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by VinhLe on 4/25/2017.
 */
@Entity
@Table(name = "sg_center", schema = "", catalog = "lms_portal")
public class SgCenterEntity {
    private Integer centerId;
    private String centerName;
    private String centerAddress;
    private String hotline;
    private Byte isActive;
    private Byte isDelete;
    private Timestamp createdDate;
    private Integer createdBy;
    private Timestamp modifiedDate;
    private Integer modifiedBy;

    @Id
    @Column(name = "center_id")
    public Integer getCenterId() {
        return centerId;
    }

    public void setCenterId(Integer centerId) {
        this.centerId = centerId;
    }

    @Basic
    @Column(name = "center_name")
    public String getCenterName() {
        return centerName;
    }

    public void setCenterName(String centerName) {
        this.centerName = centerName;
    }

    @Basic
    @Column(name = "center_address")
    public String getCenterAddress() {
        return centerAddress;
    }

    public void setCenterAddress(String centerAddress) {
        this.centerAddress = centerAddress;
    }

    @Basic
    @Column(name = "hotline")
    public String getHotline() {
        return hotline;
    }

    public void setHotline(String hotline) {
        this.hotline = hotline;
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

        SgCenterEntity that = (SgCenterEntity) o;

        if (centerId != null ? !centerId.equals(that.centerId) : that.centerId != null) return false;
        if (centerName != null ? !centerName.equals(that.centerName) : that.centerName != null) return false;
        if (centerAddress != null ? !centerAddress.equals(that.centerAddress) : that.centerAddress != null)
            return false;
        if (hotline != null ? !hotline.equals(that.hotline) : that.hotline != null) return false;
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
        int result = centerId != null ? centerId.hashCode() : 0;
        result = 31 * result + (centerName != null ? centerName.hashCode() : 0);
        result = 31 * result + (centerAddress != null ? centerAddress.hashCode() : 0);
        result = 31 * result + (hotline != null ? hotline.hashCode() : 0);
        result = 31 * result + (isActive != null ? isActive.hashCode() : 0);
        result = 31 * result + (isDelete != null ? isDelete.hashCode() : 0);
        result = 31 * result + (createdDate != null ? createdDate.hashCode() : 0);
        result = 31 * result + (createdBy != null ? createdBy.hashCode() : 0);
        result = 31 * result + (modifiedDate != null ? modifiedDate.hashCode() : 0);
        result = 31 * result + (modifiedBy != null ? modifiedBy.hashCode() : 0);
        return result;
    }
}
