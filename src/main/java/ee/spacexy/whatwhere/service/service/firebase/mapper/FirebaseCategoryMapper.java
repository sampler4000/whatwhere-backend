package ee.spacexy.whatwhere.service.service.firebase.mapper;

import ee.spacexy.whatwhere.service.service.dto.CategoryDTO;
import ee.spacexy.whatwhere.service.service.dto.FirebaseCategoryDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface FirebaseCategoryMapper extends FirebaseBaseMapper<CategoryDTO, FirebaseCategoryDTO> {
}
