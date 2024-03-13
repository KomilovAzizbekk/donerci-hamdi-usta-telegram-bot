package uz.mediasolutions.mdeliveryservice.service.web.abs;

import uz.mediasolutions.mdeliveryservice.manual.ApiResult;
import uz.mediasolutions.mdeliveryservice.payload.BasketWebDTO;
import uz.mediasolutions.mdeliveryservice.payload.OrderProductDTO;

public interface BasketWebService {

    ApiResult<BasketWebDTO> get(String chatId);

    ApiResult<?> add(String chatId, OrderProductDTO dto);

    ApiResult<?> edit(String chatId, Long id, OrderProductDTO dto);

}
