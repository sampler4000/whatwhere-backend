package ee.spacexy.whatwhere.service.validation;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ValidationErrors {
    public static final String ERROR_NOT_NULL = "NotNull";
    public static final String ERROR_MIN = "Min";
}
