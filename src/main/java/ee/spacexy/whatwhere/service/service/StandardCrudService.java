package ee.spacexy.whatwhere.service.service;


import ee.spacexy.whatwhere.service.service.mapper.CRUDMapper;
import ee.spacexy.whatwhere.service.validation.BaseValidator;
import ee.spacexy.whatwhere.service.validation.ValidationUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
public abstract class StandardCrudService<M, DTO, ID> implements CRUDService<DTO, ID> {

    private final JpaRepository<M, ID> repository;
    private final CRUDMapper<M, DTO> mapper;
    private final BaseValidator<M> validator;

    @Transactional(readOnly = true)
    @Override
    public Optional<DTO> getById(ID id) {
        return repository.findById(id)
                .map(mapper::toDto);
    }

    @Transactional
    @Override
    public Optional<DTO> update(ID id, DTO dto) {
        return repository.findById(id)
                .map(m -> updateModel(dto, m))
                .map(m -> {
                    ValidationUtils.validate(m, validator);
                    return m;
                })
                .map(repository::save)
                .map(mapper::toDto);
    }

    protected M updateModel(DTO dto, M m) {
        return mapper.update(m, dto);
    }

    @Transactional
    @Override
    public DTO create(DTO dto) {
        final M m = createModel(dto);
        ValidationUtils.validate(m, validator);
        repository.save(m);
        return mapper.toDto(m);
    }

    protected M createModel(DTO dto) {
        return mapper.create(dto);
    }

    @Transactional
    @Override
    public Optional<Boolean> delete(ID id) {
        return repository.findById(id)
                .map(m -> {
                    repository.delete(m);
                    return true;
                });
    }

    @Transactional(readOnly = true)
    @Override
    public Page<DTO> find(DTO dto, Pageable pageable) {
        return repository.findAll(Example.of(mapper.create(dto)), pageable)
                .map(mapper::toDto);
    }
}
