package uz.mediasolutions.mdeliveryservice.controller.abs;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import uz.mediasolutions.mdeliveryservice.manual.ApiResult;
import uz.mediasolutions.mdeliveryservice.payload.ProductVariationDTO;
import uz.mediasolutions.mdeliveryservice.payload.ProductVariationResDTO;
import uz.mediasolutions.mdeliveryservice.utills.constants.Rest;

import javax.validation.Valid;

@RequestMapping(ProductController.PRODUCT)
public interface ProductController {

    String PRODUCT = Rest.BASE_PATH + "products/";

    String GET_ALL = "get-all";

    String GET_BY_ID = "get/{id}";

    String ADD = "add";

    String EDIT = "edit/{id}";

    String DELETE = "delete/{id}";

    @GetMapping(GET_ALL)
    ApiResult<Page<ProductVariationResDTO>> getAll(@RequestParam(defaultValue = Rest.DEFAULT_PAGE_NUMBER) int page,
                                                   @RequestParam(defaultValue = Rest.DEFAULT_PAGE_SIZE) int size,
                                                   @RequestParam(defaultValue = "null", required = false) String search);

    @GetMapping(GET_BY_ID)
    ApiResult<ProductVariationResDTO> getById(@PathVariable Long id);

    @PostMapping(ADD)
    ApiResult<?> add(@RequestBody @Valid ProductVariationDTO dto);

    @PutMapping(EDIT)
    ApiResult<?> edit(@PathVariable Long id, @RequestBody @Valid ProductVariationDTO dto);

    @DeleteMapping(DELETE)
    ApiResult<?> delete(@PathVariable Long id);

}
