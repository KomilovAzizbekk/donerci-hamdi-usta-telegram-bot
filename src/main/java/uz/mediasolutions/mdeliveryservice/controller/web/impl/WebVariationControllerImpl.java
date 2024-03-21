package uz.mediasolutions.mdeliveryservice.controller.web.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import uz.mediasolutions.mdeliveryservice.controller.web.abs.WebVariationController;
import uz.mediasolutions.mdeliveryservice.manual.ApiResult;
import uz.mediasolutions.mdeliveryservice.payload.VariationWebDTO;
import uz.mediasolutions.mdeliveryservice.service.webabs.WebVariationService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class WebVariationControllerImpl implements WebVariationController {

    private final WebVariationService variationWebService;

    @Override
    public ApiResult<List<VariationWebDTO>> getAllByProductId(String chatId, Long productId) {
        return variationWebService.getAllByProductId(chatId, productId);
    }
}
