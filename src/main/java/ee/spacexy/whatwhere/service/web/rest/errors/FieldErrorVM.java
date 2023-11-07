package ee.spacexy.whatwhere.service.web.rest.errors;

import lombok.Value;

@Value
public class FieldErrorVM {

    String objectName;

    String field;

    String message;

}
