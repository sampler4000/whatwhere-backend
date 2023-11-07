package ee.spacexy.whatwhere.service.web.rest.mapper;

import java.util.List;

public interface BaseVMMapper<DTO, VM> {

    VM toVM(DTO dto);

    List<VM> toVM(List<DTO> dto);

    DTO toDTO(VM vm);

}
