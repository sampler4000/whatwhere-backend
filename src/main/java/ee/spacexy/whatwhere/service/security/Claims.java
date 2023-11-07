package ee.spacexy.whatwhere.service.security;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Claims {

    public static String CLAIM_PREFERRED_USERNAME = "preferred_username";
    public static String CLAIM_USER_ID = "user_id";

}
