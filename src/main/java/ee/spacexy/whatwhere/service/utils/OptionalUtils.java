package ee.spacexy.whatwhere.service.utils;

import java.util.Arrays;
import java.util.Optional;

public class OptionalUtils {
    public static boolean all(Optional<?>... optionals) {
        return Arrays.stream(optionals).allMatch(Optional::isPresent);
    }
}
