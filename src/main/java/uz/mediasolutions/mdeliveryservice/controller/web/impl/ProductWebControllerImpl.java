package uz.mediasolutions.mdeliveryservice.controller.web.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import uz.mediasolutions.mdeliveryservice.controller.web.abs.ProductWebController;
import uz.mediasolutions.mdeliveryservice.manual.ApiResult;
import uz.mediasolutions.mdeliveryservice.payload.ProductWebDTO;
import uz.mediasolutions.mdeliveryservice.payload.VariationWebDTO;
import uz.mediasolutions.mdeliveryservice.service.web.abs.ProductWebService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductWebControllerImpl implements ProductWebController {

    private final ProductWebService productWebService;

    @Override
    public ApiResult<List<ProductWebDTO>> getAllByCategoryId(String chatId, Long categoryId) {
        return productWebService.getAllByCategoryId(chatId, categoryId);
    }

}
