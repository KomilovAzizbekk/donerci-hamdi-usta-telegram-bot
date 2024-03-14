package uz.mediasolutions.mdeliveryservice.controller.web.abs;

import org.springframework.web.bind.annotation.*;
import uz.mediasolutions.mdeliveryservice.manual.ApiResult;
import uz.mediasolutions.mdeliveryservice.payload.BasketWebDTO;
import uz.mediasolutions.mdeliveryservice.payload.OrderProductDTO;
import uz.mediasolutions.mdeliveryservice.utills.constants.Rest;

import javax.validation.Valid;
import java.util.List;

@RequestMapping(WebOrderController.ORDER_WEB)
public interface WebOrderController {

    String ORDER_WEB = Rest.BASE_PATH + "order-web/";

    String GET_ALL = "get-all";

    String GET_BY_ID = "get/{id}";

    @GetMapping(GET_ALL)
    ApiResult<List<BasketWebDTO>> getAll(@RequestParam("user_id") String chatId);

    @GetMapping(GET_BY_ID)
    ApiResult<?> getById(@RequestParam("user_id") String chatId,
                     @RequestBody @Valid List<OrderProductDTO> dtoList);


}
