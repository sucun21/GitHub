package vn.sogo.lmscms.exception;


public class ServiceException extends Exception {


    public ServiceException() {
        super("common.error.notdefine");
    }



    public ServiceException(String error) {
        super(error);
    }



    public ServiceException(Exception ex) {
        super(ex);
    }



    public ServiceException(String prefix, Exception ex) {
        super(ex);

    }
}
