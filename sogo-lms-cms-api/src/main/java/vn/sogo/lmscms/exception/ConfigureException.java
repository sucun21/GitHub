package vn.sogo.lmscms.exception;

import java.io.Serializable;

public class ConfigureException extends Exception implements Serializable{

    /**
	 *
	 */
	private static final long serialVersionUID = 4548199754004569884L;

    public ConfigureException(){
        super();
    }

    public ConfigureException(String message) {
        super(message);
    }

    public ConfigureException(String message, Throwable cause) {
        super(message, cause);
    }

    public ConfigureException(Throwable cause) {
        super(cause);
    }

}
