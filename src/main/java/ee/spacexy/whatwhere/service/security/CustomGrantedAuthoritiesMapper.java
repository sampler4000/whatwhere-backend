package ee.spacexy.whatwhere.service.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.oauth2.core.oidc.user.OidcUserAuthority;

import java.util.*;
import java.util.stream.Collectors;

public class CustomGrantedAuthoritiesMapper implements GrantedAuthoritiesMapper {

    public static List<GrantedAuthority> extractAuthorityFromClaims(Map<String, Object> claims) {
        return mapRolesToGrantedAuthorities(getRolesFromClaims(claims));
    }

    @SuppressWarnings("unchecked")
    private static Collection<String> getRolesFromClaims(Map<String, Object> claims) {
        return (Collection<String>) claims.getOrDefault("groups", claims.getOrDefault("roles", new ArrayList<>()));
    }

    private static List<GrantedAuthority> mapRolesToGrantedAuthorities(Collection<String> roles) {
        return roles.stream()
            .filter(role -> role.startsWith("ROLE_"))
            .map(SimpleGrantedAuthority::new)
            .collect(Collectors.toList());
    }

    @Override
    public Collection<? extends GrantedAuthority> mapAuthorities(Collection<? extends GrantedAuthority> authorities) {
        final Optional<OidcUserAuthority> userAuthority = authorities.stream().filter(a -> a instanceof OidcUserAuthority).map(a -> (OidcUserAuthority) a).findFirst();
        if (userAuthority.isPresent()) {
            final List<GrantedAuthority> result = new ArrayList<>();
            result.addAll(authorities);
            result.addAll(extractAuthorityFromClaims(userAuthority.get().getUserInfo().getClaims()));
            return result;
        }
        return authorities;
    }

}
