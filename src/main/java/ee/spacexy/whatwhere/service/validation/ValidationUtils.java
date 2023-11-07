package ee.spacexy.whatwhere.service.validation;

import ee.spacexy.whatwhere.service.exception.ServiceValidationException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.SmartValidator;

public class ValidationUtils {

    private ValidationUtils() {
    }

    public static void validate(Object target, SmartValidator validator, Object... validationHints) {

        final BindException errors = new BindException(target, target.getClass().getSimpleName());

        validator.validate(target, errors, validationHints);

        if (errors.hasErrors()) {
            throw new ServiceValidationException(errors.getBindingResult());
        }
    }

    public static BindingResult isValid(Object target, SmartValidator validator, Object... validationHints) {

        final BindException errors = new BindException(target, target.getClass().getSimpleName());

        validator.validate(target, errors, validationHints);

        return errors.getBindingResult();
    }

}
