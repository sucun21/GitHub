package vn.sogo.lmscms.exception;

import java.io.Serializable;

public class LimitOverException extends Exception implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 5682797454145157903L;

  public  static  final String  MESSAGE_OVER_USER ="Limit over for user";

  public  static  final String  MESSAGE_OVER_COURSE="Limit over for course";

	public LimitOverException(){
		super();
	}

	public LimitOverException(String message) {
		super(message);
	}

    public LimitOverException(String message, Throwable cause) {
        super(message, cause);
    }

    public LimitOverException(Throwable cause) {
        super(cause);
    }

}
