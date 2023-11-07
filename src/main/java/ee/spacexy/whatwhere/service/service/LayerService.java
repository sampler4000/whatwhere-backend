package ee.spacexy.whatwhere.service.service;

import ee.spacexy.whatwhere.service.domain.Layer;
import ee.spacexy.whatwhere.service.repository.LayerRepository;
import ee.spacexy.whatwhere.service.service.dto.LayerDTO;
import ee.spacexy.whatwhere.service.service.mapper.LayerMapper;
import ee.spacexy.whatwhere.service.service.validator.LayerValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class LayerService extends StandardCrudService<Layer, LayerDTO, UUID> {

    private final LayerMapper mapper;

    private final LayerRepository repository;

    private final LayerValidator validator;

    public LayerService(LayerMapper mapper, LayerRepository repository, LayerValidator validator, LayerMapper mapper1, LayerRepository repository1, LayerValidator validator1) {
        super(repository, mapper, validator);
        this.mapper = mapper1;
        this.repository = repository1;
        this.validator = validator1;
    }

    @Transactional
    public List<LayerDTO> getLayers() {
        return mapper.toDto(repository.findAll());
    }

}
