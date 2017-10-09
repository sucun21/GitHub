package vn.sogo.lmscms.model.response;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@SuppressWarnings("serial")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ApiResponse<T> implements Serializable {

    protected final static String DefaultSuccess = "Lấy dữ liệu thành công";

    protected boolean isSuccess;
    protected String message;
    protected T data;

    @JsonProperty("is_success")
    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    @JsonProperty("message")
    public String getMessage() {
        return message;
    }

    public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public void setMessage(String message) {
        this.message = message;
    }

    public ApiResponse() {
        this.isSuccess = Boolean.TRUE;
        this.message = DefaultSuccess;
    }
}