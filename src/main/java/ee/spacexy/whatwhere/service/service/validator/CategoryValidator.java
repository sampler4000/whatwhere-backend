package ee.spacexy.whatwhere.service.service.validator;

import ee.spacexy.whatwhere.service.domain.Category;
import ee.spacexy.whatwhere.service.validation.BaseValidator;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.beanvalidation.SpringValidatorAdapter;

@Component
public class CategoryValidator extends BaseValidator<Category> {

    public CategoryValidator(SpringValidatorAdapter validator) {
        super(Category.class, validator);
    }

    @Override
    protected void validateObject(Category o, Errors errors, Object... validationHints) {
        validateByAnnotations(o, errors, validationHints);
    }
}
