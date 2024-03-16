package uz.mediasolutions.mdeliveryservice.service.abs;

import org.springframework.data.domain.Page;
import uz.mediasolutions.mdeliveryservice.manual.ApiResult;
import uz.mediasolutions.mdeliveryservice.payload.ProductDTO;
import uz.mediasolutions.mdeliveryservice.payload.ProductResDTO;

import java.io.IOException;

public interface ProductService {
    ApiResult<Page<ProductResDTO>> getAll(int page, int size, String search, boolean active);

    ApiResult<ProductResDTO> getById(Long id);

    ApiResult<?> add(ProductDTO dto);

    ApiResult<?> edit(Long id, ProductDTO dto) throws IOException;

    ApiResult<?> delete(Long id) throws IOException;

}
