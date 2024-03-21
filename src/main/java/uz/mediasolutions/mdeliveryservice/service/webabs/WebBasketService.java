package uz.mediasolutions.mdeliveryservice.service.webabs;

import uz.mediasolutions.mdeliveryservice.manual.ApiResult;
import uz.mediasolutions.mdeliveryservice.payload.BasketWebDTO;
import uz.mediasolutions.mdeliveryservice.payload.OrderProductDTO;

import java.util.List;

public interface WebBasketService {

    ApiResult<BasketWebDTO> get(String chatId);

    ApiResult<?> add(String chatId, List<OrderProductDTO> dtoList);

}
