package ee.spacexy.whatwhere.service.service.mapper;

import ee.spacexy.whatwhere.service.domain.User;
import ee.spacexy.whatwhere.service.service.dto.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper extends EntityMapper<User, UserDTO> {
}
