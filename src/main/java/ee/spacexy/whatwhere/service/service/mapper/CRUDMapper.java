package ee.spacexy.whatwhere.service.service.mapper;

import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

public interface CRUDMapper<M, DTO> extends EntityMapper<M, DTO> {

    @Mapping(target = "id", ignore = true)
    M create(DTO dto);

    @Mapping(target = "id", ignore = true)
    M update(@MappingTarget M model, DTO dto);

}
