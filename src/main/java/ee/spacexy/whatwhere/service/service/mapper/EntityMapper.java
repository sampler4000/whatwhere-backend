package ee.spacexy.whatwhere.service.service.mapper;

import java.util.List;

public interface EntityMapper<M, DTO> {

    DTO toDto(M m);

    List<DTO> toDto(List<M> m);

}
