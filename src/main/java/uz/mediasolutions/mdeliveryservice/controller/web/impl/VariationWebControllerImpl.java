package uz.mediasolutions.mdeliveryservice.controller.web.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import uz.mediasolutions.mdeliveryservice.controller.web.abs.ProductWebController;
import uz.mediasolutions.mdeliveryservice.controller.web.abs.VariationWebController;
import uz.mediasolutions.mdeliveryservice.manual.ApiResult;
import uz.mediasolutions.mdeliveryservice.payload.ProductWebDTO;
import uz.mediasolutions.mdeliveryservice.payload.VariationWebDTO;
import uz.mediasolutions.mdeliveryservice.service.web.abs.VariationWebService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class VariationWebControllerImpl implements VariationWebController {

    private final VariationWebService variationWebService;

    @Override
    public ApiResult<List<VariationWebDTO>> getAllByProductId(String chatId, Long productId) {
        return variationWebService.getAllByProductId(chatId, productId);
    }
}
