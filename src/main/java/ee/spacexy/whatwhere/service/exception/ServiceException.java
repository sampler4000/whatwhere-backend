package ee.spacexy.whatwhere.service.exception;

public class ServiceException extends RuntimeException {

    private final String error;

    public ServiceException(String error, String message) {
        super(message);
        this.error = error;
    }

    public ServiceException(String error, String message, Throwable cause) {
        super(message, cause);
        this.error = error;
    }

    public String getError() {
        return error;
    }
}
