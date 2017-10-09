package vn.sogo.lmscms.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Created by VinhLe on 4/20/2017.
 */
public class ResponseCheckLogin extends BaseResponse {

    private String session;

    public ResponseCheckLogin(boolean isSuccess, String message) {
        super(isSuccess, message);
    }

    public ResponseCheckLogin(String message, String session) {
        super(Boolean.TRUE, message);

        this.session = session;
    }

    public ResponseCheckLogin(String session) {
        super(Boolean.TRUE, DefaultSuccess);

        this.session = session;
    }

    @JsonProperty("session_key")
    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }
}
