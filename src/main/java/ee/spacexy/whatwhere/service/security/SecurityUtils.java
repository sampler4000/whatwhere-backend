package ee.spacexy.whatwhere.service.security;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Utility class for Spring Security.
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class SecurityUtils {

    public static Optional<Authentication> getAuthentication() {
        return Optional.of(SecurityContextHolder.getContext())
            .map(SecurityContext::getAuthentication);
    }

    /**
     * Get the login of the current user.
     *
     * @return the login of the current user.
     */
    public static Optional<String> getCurrentUserLogin() {
        return getAuthentication()
            .flatMap(SecurityUtils::extractPrincipal);
    }

    private static Optional<String> extractPrincipal(Authentication authentication) {
        if (authentication.getPrincipal() instanceof UserDetails user) {
            return Optional.ofNullable(user.getUsername());
        } else if (authentication instanceof JwtAuthenticationToken jwt) {
            final Map<String, Object> claims = jwt.getToken().getClaims();
            return Optional.ofNullable(claims.get(Claims.CLAIM_USER_ID)).map(v -> (String) v);
        } else if (authentication.getPrincipal() instanceof DefaultOidcUser oidcUser) {
            final Map<String, Object> attributes = oidcUser.getAttributes();
            return Optional.ofNullable(attributes.get(Claims.CLAIM_PREFERRED_USERNAME)).map(v -> (String) v);
        } else if (authentication.getPrincipal() instanceof String p) {
            return Optional.of(p);
        }
        log.warn("Could not extract principal. Unsupported authentication class {}", authentication.getClass().getName());
        return Optional.empty();
    }


    /**
     * Check if a user is authenticated.
     *
     * @return true if the user is authenticated, false otherwise.
     */
    public static boolean isAuthenticated() {
        return getAuthorities()
            .stream()
            .map(GrantedAuthority::getAuthority)
            .noneMatch(AuthoritiesConstants.ANONYMOUS::equals);
    }

    public static List<GrantedAuthority> getAuthorities() {
        return getAuthentication()
            .map(Authentication::getAuthorities)
            .map(List::<GrantedAuthority>copyOf)
            .orElseGet(List::of);
    }

    /**
     * If the current user has a specific authority (security role).
     * <p>
     * The name of this method comes from the {@code isUserInRole()} method in the Servlet API.
     *
     * @param authority the authority to check.
     * @return true if the current user has the authority, false otherwise.
     */
    public static boolean isCurrentUserInRole(String authority) {
        return getAuthorities().stream()
            .map(GrantedAuthority::getAuthority)
            .anyMatch(authority::equals);
    }

    public static boolean hasAnyRole(List<String> authorityList) {
        return getAuthentication()
            .map(authentication -> authentication.getAuthorities().stream().anyMatch(grantedAuthority -> authorityList.contains(grantedAuthority.getAuthority())))
            .orElse(false);
    }

}
