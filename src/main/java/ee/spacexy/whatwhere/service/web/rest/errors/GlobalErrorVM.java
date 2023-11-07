package ee.spacexy.whatwhere.service.web.rest.errors;

import lombok.Value;

@Value
public class GlobalErrorVM {

    String objectName;

    String message;

    String defaultMessage;

}
