package vn.sogo.lmscms.model.request;

import java.io.Serializable;

@SuppressWarnings("serial")
public class ExtendGroupClassRequest implements Serializable {
	private Integer groupClassId;
	private Integer modifiedBy;
	public Integer getGroupClassId() {
		return groupClassId;
	}
	public void setGroupClassId(Integer groupClassId) {
		this.groupClassId = groupClassId;
	}
	public Integer getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(Integer modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
}
