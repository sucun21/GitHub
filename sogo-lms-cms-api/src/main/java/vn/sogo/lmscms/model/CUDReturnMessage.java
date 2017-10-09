package vn.sogo.lmscms.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class CUDReturnMessage implements Serializable {
	private Long id;
	private String message;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	public CUDReturnMessage(){
		this.message = "Thao tác thất bại";
	}
	
}
