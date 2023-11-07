package ee.spacexy.whatwhere.service.web.rest.mapper;


import ee.spacexy.whatwhere.service.service.dto.LayerDTO;
import ee.spacexy.whatwhere.web.model.LayerVM;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface LayerVMMapper extends BaseVMMapper<LayerDTO, LayerVM> {
}
