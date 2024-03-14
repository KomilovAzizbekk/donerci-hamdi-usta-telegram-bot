package uz.mediasolutions.mdeliveryservice.controller.web.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import uz.mediasolutions.mdeliveryservice.controller.web.abs.WebBasketController;
import uz.mediasolutions.mdeliveryservice.manual.ApiResult;
import uz.mediasolutions.mdeliveryservice.payload.BasketWebDTO;
import uz.mediasolutions.mdeliveryservice.payload.OrderProductDTO;
import uz.mediasolutions.mdeliveryservice.service.web.abs.WebBasketService;

@RestController
@RequiredArgsConstructor
public class WebBasketControllerImpl implements WebBasketController {

    private final WebBasketService basketWebService;

    @Override
    public ApiResult<BasketWebDTO> get(String chatId) {
        return basketWebService.get(chatId);
    }

    @Override
    public ApiResult<?> add(String chatId, OrderProductDTO dto) {
        return basketWebService.add(chatId, dto);
    }
}
