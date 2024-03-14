package uz.mediasolutions.mdeliveryservice.controller.web.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import uz.mediasolutions.mdeliveryservice.controller.web.abs.WebProductController;
import uz.mediasolutions.mdeliveryservice.manual.ApiResult;
import uz.mediasolutions.mdeliveryservice.payload.ProductWebDTO;
import uz.mediasolutions.mdeliveryservice.service.web.abs.WebProductService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class WebProductControllerImpl implements WebProductController {

    private final WebProductService productWebService;

    @Override
    public ApiResult<List<ProductWebDTO>> getAllByCategoryId(String chatId, Long categoryId) {
        return productWebService.getAllByCategoryId(chatId, categoryId);
    }

}
