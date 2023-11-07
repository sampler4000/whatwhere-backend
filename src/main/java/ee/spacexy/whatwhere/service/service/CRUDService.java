package ee.spacexy.whatwhere.service.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface CRUDService<DTO, ID> {

    Optional<DTO> getById(ID id);

    Optional<DTO> update(ID id, DTO dto);

    DTO create(DTO dto);

    Optional<Boolean> delete(ID id);

    Page<DTO> find(DTO dto, Pageable pageable);

}
