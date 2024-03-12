package uz.mediasolutions.mdeliveryservice.controller.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RestController;
import uz.mediasolutions.mdeliveryservice.controller.abs.ProductController;
import uz.mediasolutions.mdeliveryservice.manual.ApiResult;
import uz.mediasolutions.mdeliveryservice.payload.ProductDTO;
import uz.mediasolutions.mdeliveryservice.payload.ProductResDTO;
import uz.mediasolutions.mdeliveryservice.service.abs.ProductService;

@RestController
@RequiredArgsConstructor
public class ProductControllerImpl implements ProductController {

    private final ProductService productService;

    @Override
    public ApiResult<Page<ProductResDTO>> getAll(int page, int size, String search) {
        return productService.getAll(page, size, search);
    }

    @Override
    public ApiResult<ProductResDTO> getById(Long id) {
        return productService.getById(id);
    }

    @Override
    public ApiResult<?> add(ProductDTO dto) {
        return productService.add(dto);
    }

    @Override
    public ApiResult<?> edit(Long id, ProductDTO dto) {
        return productService.edit(id, dto);
    }

    @Override
    public ApiResult<?> delete(Long id) {
        return productService.delete(id);
    }
}
