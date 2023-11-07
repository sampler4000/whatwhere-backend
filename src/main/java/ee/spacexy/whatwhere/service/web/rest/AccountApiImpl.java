package ee.spacexy.whatwhere.service.web.rest;


import ee.spacexy.whatwhere.service.service.UserService;
import ee.spacexy.whatwhere.service.web.api.AccountApiDelegate;
import ee.spacexy.whatwhere.web.model.UserVM;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

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
}
