package ee.spacexy.whatwhere.service.web.rest;

import ee.spacexy.whatwhere.service.service.LayerService;
import ee.spacexy.whatwhere.service.service.dto.LayerDTO;
import ee.spacexy.whatwhere.service.web.api.LayerApiDelegate;
import ee.spacexy.whatwhere.service.web.rest.mapper.LayerVMMapper;
import ee.spacexy.whatwhere.web.model.LayerVM;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
@Component
public class LayerApiImpl implements LayerApiDelegate {

    private final LayerService layerService;
    private final LayerVMMapper layerVMMapper;

    @Override
    public ResponseEntity<LayerVM> createLayer(LayerVM layerVM) {
        final LayerDTO result = layerService.create(layerVMMapper.toDTO(layerVM));
        return ResponseEntity.ok(layerVMMapper.toVM(result));
    }

    @Override
    public ResponseEntity<Void> deleteLayer(UUID id) {
        return layerService.delete(id).map(r -> ResponseEntity.ok().<Void>build()).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<LayerVM> getLayer(UUID id) {
        return ResponseEntity.of(layerService.getById(id).map(layerVMMapper::toVM));
    }

    @Override
    public ResponseEntity<LayerVM> updateLayer(UUID id, LayerVM layerVM) {
        return ResponseEntity.of(layerService.update(id, layerVMMapper.toDTO(layerVM)).map(layerVMMapper::toVM));

    }

    @Override
    public ResponseEntity<List<LayerVM>> getLayerDefinitions() {
        return ResponseEntity.ok(layerVMMapper.toVM(layerService.getLayers()));
    }
}
