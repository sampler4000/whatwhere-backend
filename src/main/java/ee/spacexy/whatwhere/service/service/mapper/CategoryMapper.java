package ee.spacexy.whatwhere.service.service.mapper;

import ee.spacexy.whatwhere.service.domain.Category;
import ee.spacexy.whatwhere.service.service.dto.CategoryDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface CategoryMapper extends CRUDMapper<Category, CategoryDTO> {
}
