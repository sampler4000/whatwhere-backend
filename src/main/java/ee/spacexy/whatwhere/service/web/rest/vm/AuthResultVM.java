package ee.spacexy.whatwhere.service.web.rest.vm;

import lombok.Value;

@Value
public class AuthResultVM {

    boolean authenticated;

    String token;

    String principalName;
}
