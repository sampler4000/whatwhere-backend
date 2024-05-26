package ee.spacexy.whatwhere.service.service.firebase;

import ee.spacexy.whatwhere.service.service.dto.LayerDTO;

import java.util.List;

public interface FirebaseService {
    List<LayerDTO> updateUserLayers(String userId);
}
