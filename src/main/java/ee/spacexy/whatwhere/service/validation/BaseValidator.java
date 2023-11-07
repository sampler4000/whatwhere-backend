package ee.spacexy.whatwhere.service.validation;

import org.springframework.validation.Errors;
import org.springframework.validation.SmartValidator;
import org.springframework.validation.beanvalidation.SpringValidatorAdapter;

@SuppressWarnings("unchecked")
public abstract class BaseValidator<T> implements SmartValidator {

    private final Class<T> type;

    protected final SmartValidator rootValidator;

    public BaseValidator(Class<T> type, SpringValidatorAdapter validator) {
        this.type = type;
        this.rootValidator = validator;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return type.equals(clazz);
    }

    @Override
    public final void validate(Object target, Errors errors) {
        validateObject((T) target, errors);
    }

    @Override
    public final void validate(Object target, Errors errors, Object... validationHints) {
        validateObject((T) target, errors, validationHints);
    }

    protected abstract void validateObject(T o, Errors errors, Object... validationHints);

    protected void validateByAnnotations(Object t, Errors errors) {
        rootValidator.validate(t, errors); // Validate defaults
    }

    protected void validateByAnnotations(Object t, Errors errors, Object... validationHints) {
        rootValidator.validate(t, errors, validationHints); // Validate defaults
    }

}
