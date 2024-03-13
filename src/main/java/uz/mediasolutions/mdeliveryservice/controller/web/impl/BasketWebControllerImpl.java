package uz.mediasolutions.mdeliveryservice.controller.web.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import uz.mediasolutions.mdeliveryservice.controller.web.abs.BannerWebController;
import uz.mediasolutions.mdeliveryservice.controller.web.abs.BasketWebController;
import uz.mediasolutions.mdeliveryservice.manual.ApiResult;
import uz.mediasolutions.mdeliveryservice.payload.BannerDTO;
import uz.mediasolutions.mdeliveryservice.payload.BasketWebDTO;
import uz.mediasolutions.mdeliveryservice.payload.OrderProductDTO;
import uz.mediasolutions.mdeliveryservice.service.web.abs.BannerWebService;
import uz.mediasolutions.mdeliveryservice.service.web.abs.BasketWebService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BasketWebControllerImpl implements BasketWebController {

    private final BasketWebService basketWebService;

    @Override
    public ApiResult<BasketWebDTO> get(String chatId) {
        return basketWebService.get(chatId);
    }

    @Override
    public ApiResult<?> add(String chatId, OrderProductDTO dto) {
        return basketWebService.add(chatId, dto);
    }

    @Override
    public ApiResult<?> edit(String chatId, Long id, OrderProductDTO dto) {
        return basketWebService.edit(chatId, id, dto);
    }
}
