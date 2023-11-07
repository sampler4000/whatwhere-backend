package ee.spacexy.whatwhere.service.service.mapper;

import ee.spacexy.whatwhere.service.domain.Layer;
import ee.spacexy.whatwhere.service.service.dto.LayerDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface LayerMapper extends CRUDMapper<Layer, LayerDTO> {
    default String map(Object value) {
        if (value == null) {
            return null;
        }
        return value.toString();
    }
}
