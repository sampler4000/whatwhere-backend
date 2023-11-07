package ee.spacexy.whatwhere.service.service.validator;

import ee.spacexy.whatwhere.service.domain.Layer;
import ee.spacexy.whatwhere.service.validation.BaseValidator;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.beanvalidation.SpringValidatorAdapter;

@Component
public class LayerValidator extends BaseValidator<Layer> {

    public LayerValidator(SpringValidatorAdapter validator) {
        super(Layer.class, validator);
    }

    @Override
    protected void validateObject(Layer o, Errors errors, Object... validationHints) {
        validateByAnnotations(o, errors, validationHints);
    }
}
