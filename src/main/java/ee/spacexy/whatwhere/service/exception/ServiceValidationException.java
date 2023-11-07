package ee.spacexy.whatwhere.service.exception;

import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;

public class ServiceValidationException extends RuntimeException {

    private transient final BindingResult bindingResult;

    public ServiceValidationException(BindingResult bindingResult) {
        super(bindingResult.toString());
        this.bindingResult = bindingResult;
    }

    public ServiceValidationException(Object object, String error, String message) {
        super(message);
        this.bindingResult = new BindException(object, object.getClass().getSimpleName());
        this.bindingResult.reject(error, message);
    }

    public BindingResult getBindingResult() {
        return bindingResult;
    }

}
