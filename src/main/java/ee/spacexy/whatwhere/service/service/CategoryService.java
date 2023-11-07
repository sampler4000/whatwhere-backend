package ee.spacexy.whatwhere.service.service;

import ee.spacexy.whatwhere.service.domain.Category;
import ee.spacexy.whatwhere.service.repository.CategoryRepository;
import ee.spacexy.whatwhere.service.service.dto.CategoryDTO;
import ee.spacexy.whatwhere.service.service.mapper.CategoryMapper;
import ee.spacexy.whatwhere.service.service.validator.CategoryValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
public class CategoryService extends StandardCrudService<Category, CategoryDTO, UUID> {

    private final CategoryMapper mapper;

    private final CategoryRepository repository;

    private final CategoryValidator validator;

    public CategoryService(CategoryMapper mapper, CategoryRepository repository, CategoryValidator validator) {
        super(repository, mapper, validator);
        this.mapper = mapper;
        this.repository = repository;
        this.validator = validator;
    }


    @Transactional
    public List<CategoryDTO> getCategories() {
        return mapper.toDto(repository.findAll());
    }

}
