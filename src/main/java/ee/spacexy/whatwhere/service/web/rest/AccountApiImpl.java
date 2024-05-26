package ee.spacexy.whatwhere.service.web.rest;


import ee.spacexy.whatwhere.service.security.SecurityUtils;
import ee.spacexy.whatwhere.service.service.UserService;
import ee.spacexy.whatwhere.service.web.api.AccountApiDelegate;
import ee.spacexy.whatwhere.web.model.RoleVM;
import ee.spacexy.whatwhere.web.model.UserVM;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * REST controller for managing the current user's account.
 */
@RequiredArgsConstructor
@Component
public class AccountApiImpl implements AccountApiDelegate {

    private final UserService userService;
//    private final UserVMMapper userVMMapper;

    @Override
    public ResponseEntity<UserVM> getAccount() {
        return ResponseEntity.ok(new UserVM());

//        return SecurityUtils.getAuthentication()
//                .map(auth -> {
//                    if (auth instanceof AbstractAuthenticationToken) {
//                        return userService.getUserFromAuthentication((AbstractAuthenticationToken) auth);
//                    } else {
//                        throw new AuthenticationCredentialsNotFoundException("User not found");
//                    }
//                })
//                .map(userVMMapper::toVM)
//                .map(ResponseEntity::ok)
//                .orElseThrow(() -> new AuthenticationCredentialsNotFoundException("User not found"));
//    }
    }

    @Override
    public ResponseEntity<List<RoleVM>> getRoles() {
        return SecurityUtils.getCurrentUserLogin().map(userService::getRoles)
                .map(roles -> roles.stream().map(roleDTO -> new RoleVM().authority(roleDTO.getAuthority())).collect(Collectors.toList()))
                .map(ResponseEntity::ok)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}
