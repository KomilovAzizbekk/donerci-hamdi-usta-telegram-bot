package uz.mediasolutions.mdeliveryservice.service.abs;

import org.springframework.data.domain.Page;
import uz.mediasolutions.mdeliveryservice.manual.ApiResult;
import uz.mediasolutions.mdeliveryservice.payload.ProductDTO;

public interface ProductService {
    ApiResult<Page<ProductDTO>> getAll(int page, int size, String search);

    ApiResult<ProductDTO> getById(Long id);

    ApiResult<?> add(ProductDTO dto);

    ApiResult<?> edit(Long id, ProductDTO dto);

    ApiResult<?> delete(Long id);

}
