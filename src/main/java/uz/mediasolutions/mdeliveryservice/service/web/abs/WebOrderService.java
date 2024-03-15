package uz.mediasolutions.mdeliveryservice.service.web.abs;

import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import uz.mediasolutions.mdeliveryservice.manual.ApiResult;
import uz.mediasolutions.mdeliveryservice.payload.OrderProductDTO;
import uz.mediasolutions.mdeliveryservice.payload.OrderWebDTO;

import java.util.List;

public interface WebOrderService {
    ApiResult<List<OrderWebDTO>> getAll(String chatId);

    ApiResult<OrderWebDTO> getById(String chatId, Long id);

    ApiResult<?> add(String chatId, List<OrderProductDTO> dtoList) throws TelegramApiException;

}
