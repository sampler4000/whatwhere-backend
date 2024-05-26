package ee.spacexy.whatwhere.service.web.rest;

import ee.spacexy.whatwhere.service.service.firebase.FirebaseService;
import ee.spacexy.whatwhere.service.web.api.FirebaseApiDelegate;
import ee.spacexy.whatwhere.service.web.rest.mapper.LayerVMMapper;
import ee.spacexy.whatwhere.web.model.LayerVM;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
@Component
public class FirebaseApiImpl implements FirebaseApiDelegate {

    private final FirebaseService firebaseService;
    private final LayerVMMapper layersVMMapper;

    @Override
    public ResponseEntity<List<LayerVM>> updateUserLayers(String userId, List<String> requestBody) {
        return ResponseEntity.ok(layersVMMapper.toVM(firebaseService.updateUserLayers(userId)));
    }
}
