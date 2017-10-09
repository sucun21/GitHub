package vn.sogo.lmscms.exception;

import java.io.Serializable;

public class ConnectException extends Exception implements Serializable{

	private static final long serialVersionUID = 1155572910904458862L;

    public ConnectException(){
        super();
    }

    public ConnectException(String message) {
        super(message);
    }

    public ConnectException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConnectException(Throwable cause) {
        super(cause);
    }

}
