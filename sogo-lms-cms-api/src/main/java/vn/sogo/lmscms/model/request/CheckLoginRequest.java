package vn.sogo.lmscms.model.request;

import java.io.Serializable;

/**
 * Created by VinhLe on 4/15/2017.
 */
@SuppressWarnings("serial")
public class CheckLoginRequest implements Serializable {

    private String email;
    private String password;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
