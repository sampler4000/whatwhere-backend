package ee.spacexy.whatwhere.service.web.rest.mapper;


import ee.spacexy.whatwhere.service.service.dto.CategoryDTO;
import ee.spacexy.whatwhere.web.model.CategoryVM;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CategoryVMMapper extends BaseVMMapper<CategoryDTO, CategoryVM> {
}
