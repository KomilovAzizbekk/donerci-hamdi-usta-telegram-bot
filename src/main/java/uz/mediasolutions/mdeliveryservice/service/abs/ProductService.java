package uz.mediasolutions.mdeliveryservice.service.abs;

import org.springframework.data.domain.Page;
import uz.mediasolutions.mdeliveryservice.manual.ApiResult;
import uz.mediasolutions.mdeliveryservice.payload.ProductVariationDTO;
import uz.mediasolutions.mdeliveryservice.payload.ProductVariationResDTO;

public interface ProductService {
    ApiResult<Page<ProductVariationResDTO>> getAll(int page, int size, String search);

    ApiResult<ProductVariationResDTO> getById(Long id);

    ApiResult<?> add(ProductVariationDTO dto);

    ApiResult<?> edit(Long id, ProductVariationDTO dto);

    ApiResult<?> delete(Long id);

}
