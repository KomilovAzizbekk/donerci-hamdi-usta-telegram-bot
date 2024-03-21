package uz.mediasolutions.mdeliveryservice.controller.web.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import uz.mediasolutions.mdeliveryservice.controller.web.abs.WebOrderController;
import uz.mediasolutions.mdeliveryservice.manual.ApiResult;
import uz.mediasolutions.mdeliveryservice.payload.OrderProductDTO;
import uz.mediasolutions.mdeliveryservice.payload.OrderWebDTO;
import uz.mediasolutions.mdeliveryservice.service.webabs.WebOrderService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class WebOrderControllerImpl implements WebOrderController {

    private final WebOrderService webOrderService;

    @Override
    public ApiResult<List<OrderWebDTO>> getAll(String chatId) {
        return webOrderService.getAll(chatId);
    }

    @Override
    public ApiResult<OrderWebDTO> getById(String chatId, Long id) {
        return webOrderService.getById(chatId, id);
    }

    @Override
    public ApiResult<?> add(String chatId, List<OrderProductDTO> dtoList) throws TelegramApiException {
        return webOrderService.add(chatId, dtoList);
    }
}
