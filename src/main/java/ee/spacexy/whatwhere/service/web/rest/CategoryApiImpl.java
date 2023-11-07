package ee.spacexy.whatwhere.service.web.rest;

import ee.spacexy.whatwhere.service.service.CategoryService;
import ee.spacexy.whatwhere.service.service.dto.CategoryDTO;
import ee.spacexy.whatwhere.service.web.api.CategoryApiDelegate;
import ee.spacexy.whatwhere.service.web.rest.mapper.CategoryVMMapper;
import ee.spacexy.whatwhere.web.model.CategoryVM;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@PreAuthorize("hasAnyAuthority('ROLE_ADMIN')")
@Component
public class CategoryApiImpl implements CategoryApiDelegate {

    private final CategoryService categoryService;
    private final CategoryVMMapper categoryVMMapper;


    @Override
    public ResponseEntity<CategoryVM> createCategory(CategoryVM categoryVM) {
        final CategoryDTO result = categoryService.create(categoryVMMapper.toDTO(categoryVM));
        return ResponseEntity.ok(categoryVMMapper.toVM(result));
    }

    @Override
    public ResponseEntity<Void> deleteCategory(UUID id) {
        return categoryService.delete(id).map(r -> ResponseEntity.ok().<Void>build()).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Override
    public ResponseEntity<List<CategoryVM>> getCategories() {
        return ResponseEntity.ok(categoryVMMapper.toVM(categoryService.getCategories()));
    }

    @Override
    public ResponseEntity<CategoryVM> getCategory(UUID id) {
        return ResponseEntity.of(categoryService.getById(id).map(categoryVMMapper::toVM));
    }

    @Override
    public ResponseEntity<CategoryVM> updateCategory(UUID id, CategoryVM categoryVM) {
        return ResponseEntity.of(categoryService.update(id, categoryVMMapper.toDTO(categoryVM)).map(categoryVMMapper::toVM));
    }
}
