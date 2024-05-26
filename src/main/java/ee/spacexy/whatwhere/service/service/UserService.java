package ee.spacexy.whatwhere.service.service;

import ee.spacexy.whatwhere.service.domain.User;
import ee.spacexy.whatwhere.service.repository.UserRepository;
import ee.spacexy.whatwhere.service.security.AuthoritiesConstants;
import ee.spacexy.whatwhere.service.service.dto.RoleDTO;
import ee.spacexy.whatwhere.service.service.dto.UserDTO;
import ee.spacexy.whatwhere.service.service.mapper.UserMapper;
import ee.spacexy.whatwhere.service.utils.Constants;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;


/**
 * Service class for managing users.
 */
@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;


    private static Map<String, Object> tokenAttributes(AbstractAuthenticationToken authToken) {
        if (authToken instanceof OAuth2AuthenticationToken) {
            return ((OAuth2AuthenticationToken) authToken).getPrincipal().getAttributes();
        } else if (authToken instanceof JwtAuthenticationToken) {
            return ((JwtAuthenticationToken) authToken).getTokenAttributes();
        } else {
            throw new IllegalArgumentException("AuthenticationToken is not OAuth2 or JWT");
        }
    }

    private static UserDTO getUser(Map<String, Object> details) {
        final UserDTO user = new UserDTO();
        if (details.get("preferred_username") != null) {
            user.setLogin(((String) details.get("preferred_username")).toLowerCase());
        } else if (user.getLogin() == null) {
            user.setLogin(((String) details.get("sub")));
        }
        if (details.get("name") != null) {
            user.setFullName((String) details.get("name"));
        }
        if (details.get("email_verified") != null) {
            user.setActivated((Boolean) details.get("email_verified"));
        }
        if (details.get("email") != null) {
            user.setEmail(((String) details.get("email")).toLowerCase());
        } else {
            user.setEmail((String) details.get("sub"));
        }
        if (details.get("langKey") != null) {
            user.setLangKey((String) details.get("langKey"));
        } else if (details.get("locale") != null) {
            // trim off country code if it exists
            String locale = (String) details.get("locale");
            if (locale.contains("_")) {
                locale = locale.substring(0, locale.indexOf('_'));
            } else if (locale.contains("-")) {
                locale = locale.substring(0, locale.indexOf('-'));
            }
            user.setLangKey(locale.toLowerCase());
        } else {
            user.setLangKey(Constants.DEFAULT_LANGUAGE);
        }
        if (details.get("picture") != null) {
            user.setImageUrl((String) details.get("picture"));
        }
        user.setActivated(true);
        return user;
    }

    private User createUser(UserDTO userFromToken) {

        final User user = new User();
        user.setLogin(userFromToken.getLogin());
        user.setFirstName(userFromToken.getFirstName());
        user.setLastName(userFromToken.getLastName());
        user.setFullName(userFromToken.getFullName());
        user.setEmail(userFromToken.getEmail());
        user.setImageUrl(userFromToken.getImageUrl());
        user.setLangKey(userFromToken.getLangKey());
        user.setActivated(true);
        user.setAuthorities(List.of(AuthoritiesConstants.USER));
        return userRepository.save(user);
    }

    @Transactional
    public UserDTO getUserFromAuthentication(AbstractAuthenticationToken authToken) {
        final UserDTO userFromToken = getUser(tokenAttributes(authToken));
        return userRepository.findByLogin(userFromToken.getLogin())
            .or(() -> Optional.of(createUser(userFromToken)))
            .map(userMapper::toDto)
            .orElseThrow();
    }

    @Transactional
    public UserDTO getUserFromAuthentication(Jwt jwt) {

        final UserDTO userFromToken = getUser(jwt.getClaims());

        return userRepository.findByLogin(userFromToken.getLogin())
            .or(() -> Optional.of(createUser(userFromToken)))
            .map(userMapper::toDto)
            .orElseThrow();
    }

    public List<RoleDTO> getRoles(String login) {
        User user = userRepository.findByLogin(login).orElseThrow();
        return user.getAuthorities()
            .stream()
            .map(authority -> RoleDTO.builder().authority(authority).build())
            .collect(Collectors.toList());
    }

}
