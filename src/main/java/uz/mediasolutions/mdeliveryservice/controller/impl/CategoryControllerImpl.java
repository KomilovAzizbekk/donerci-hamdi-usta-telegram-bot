package uz.mediasolutions.mdeliveryservice.controller.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RestController;
import uz.mediasolutions.mdeliveryservice.controller.abs.CategoryController;
import uz.mediasolutions.mdeliveryservice.manual.ApiResult;
import uz.mediasolutions.mdeliveryservice.payload.CategoryDTO;
import uz.mediasolutions.mdeliveryservice.service.abs.CategoryService;

@RestController
@RequiredArgsConstructor
public class CategoryControllerImpl implements CategoryController {

    private final CategoryService categoryService;

    @Override
    public ApiResult<Page<CategoryDTO>> getAll(int page, int size, String name) {
        return categoryService.getAll(page, size, name);
    }

    @Override
    public ApiResult<CategoryDTO> getById(Long id) {
        return categoryService.getById(id);
    }

    @Override
    public ApiResult<?> add(CategoryDTO categoryDTO) {
        return categoryService.add(categoryDTO);
    }

    @Override
    public ApiResult<?> edit(Long id, CategoryDTO categoryDTO) {
        return categoryService.edit(id, categoryDTO);
    }

    @Override
    public ApiResult<?> delete(Long id) {
        return categoryService.delete(id);
    }
}
