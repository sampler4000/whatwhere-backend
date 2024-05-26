package ee.spacexy.whatwhere.service.service.firebase.mapper;

import ee.spacexy.whatwhere.service.service.dto.FirebaseLayerDTO;
import ee.spacexy.whatwhere.service.service.dto.LayerDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface FirebaseLayerMapper extends FirebaseBaseMapper<LayerDTO, FirebaseLayerDTO> {
}
