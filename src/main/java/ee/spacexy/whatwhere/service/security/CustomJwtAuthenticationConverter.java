package ee.spacexy.whatwhere.service.security;

import ee.spacexy.whatwhere.service.service.UserService;
import ee.spacexy.whatwhere.service.service.dto.UserDTO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;

import java.util.ArrayList;
import java.util.List;

public class CustomJwtAuthenticationConverter implements Converter<Jwt, AbstractAuthenticationToken> {
    private final JwtGrantedAuthoritiesConverter scopesConverter;
    private final UserService userService;

    public CustomJwtAuthenticationConverter(UserService userService) {
        this.userService = userService;
        scopesConverter = new JwtGrantedAuthoritiesConverter();
        scopesConverter.setAuthoritiesClaimName("scope");
        scopesConverter.setAuthorityPrefix("SCOPE_");
    }

    @Override
    public AbstractAuthenticationToken convert(Jwt jwt) {

        final String login = jwt.getClaim(Claims.CLAIM_PREFERRED_USERNAME);

        final List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.addAll(scopesConverter.convert(jwt));

        final UserDTO user = userService.getUserFromAuthentication(jwt);
        authorities.addAll(user.getAuthorities().stream().map(SimpleGrantedAuthority::new).toList());

        return new JwtAuthenticationToken(jwt, authorities, login);
    }
}
