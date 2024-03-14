package uz.mediasolutions.mdeliveryservice.service.abs;

import org.springframework.data.domain.Page;
import uz.mediasolutions.mdeliveryservice.manual.ApiResult;
import uz.mediasolutions.mdeliveryservice.payload.CategoryDTO;

import java.io.IOException;

public interface CategoryService {
    ApiResult<Page<CategoryDTO>> getAll(int page, int size, String name);

    ApiResult<CategoryDTO> getById(Long id);

    ApiResult<?> add(CategoryDTO categoryDTO);

    ApiResult<?> edit(Long id, CategoryDTO categoryDTO) throws IOException;

    ApiResult<?> delete(Long id);

}
