package vn.sogo.lmscms.model.mysql;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by VinhLe on 4/25/2017.
 */
@Entity
@Table(name = "sg_setting", schema = "", catalog = "lms_portal")
public class SgSettingEntity {
    private Integer settingId;
    private String settingKey;
    private String settingName;
    private String settingValue;
    private Timestamp createdDate;
    private Integer createdBy;
    private Timestamp modifiedDate;
    private Integer modifiedBy;

    @Id
    @Column(name = "setting_id")
    public Integer getSettingId() {
        return settingId;
    }

    public void setSettingId(Integer settingId) {
        this.settingId = settingId;
    }

    @Basic
    @Column(name = "setting_key")
    public String getSettingKey() {
        return settingKey;
    }

    public void setSettingKey(String settingKey) {
        this.settingKey = settingKey;
    }

    @Basic
    @Column(name = "setting_name")
    public String getSettingName() {
        return settingName;
    }

    public void setSettingName(String settingName) {
        this.settingName = settingName;
    }

    @Basic
    @Column(name = "setting_value")
    public String getSettingValue() {
        return settingValue;
    }

    public void setSettingValue(String settingValue) {
        this.settingValue = settingValue;
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

        SgSettingEntity that = (SgSettingEntity) o;

        if (settingId != null ? !settingId.equals(that.settingId) : that.settingId != null) return false;
        if (settingKey != null ? !settingKey.equals(that.settingKey) : that.settingKey != null) return false;
        if (settingName != null ? !settingName.equals(that.settingName) : that.settingName != null) return false;
        if (settingValue != null ? !settingValue.equals(that.settingValue) : that.settingValue != null) return false;
        if (createdDate != null ? !createdDate.equals(that.createdDate) : that.createdDate != null) return false;
        if (createdBy != null ? !createdBy.equals(that.createdBy) : that.createdBy != null) return false;
        if (modifiedDate != null ? !modifiedDate.equals(that.modifiedDate) : that.modifiedDate != null) return false;
        if (modifiedBy != null ? !modifiedBy.equals(that.modifiedBy) : that.modifiedBy != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = settingId != null ? settingId.hashCode() : 0;
        result = 31 * result + (settingKey != null ? settingKey.hashCode() : 0);
        result = 31 * result + (settingName != null ? settingName.hashCode() : 0);
        result = 31 * result + (settingValue != null ? settingValue.hashCode() : 0);
        result = 31 * result + (createdDate != null ? createdDate.hashCode() : 0);
        result = 31 * result + (createdBy != null ? createdBy.hashCode() : 0);
        result = 31 * result + (modifiedDate != null ? modifiedDate.hashCode() : 0);
        result = 31 * result + (modifiedBy != null ? modifiedBy.hashCode() : 0);
        return result;
    }
}
