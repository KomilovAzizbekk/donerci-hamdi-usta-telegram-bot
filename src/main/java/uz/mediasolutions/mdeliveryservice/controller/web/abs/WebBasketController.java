package uz.mediasolutions.mdeliveryservice.controller.web.abs;

import org.springframework.web.bind.annotation.*;
import uz.mediasolutions.mdeliveryservice.manual.ApiResult;
import uz.mediasolutions.mdeliveryservice.payload.BasketWebDTO;
import uz.mediasolutions.mdeliveryservice.payload.OrderProductDTO;
import uz.mediasolutions.mdeliveryservice.utills.constants.Rest;

import javax.validation.Valid;

@RequestMapping(WebBasketController.BASKET_WEB)
public interface WebBasketController {

    String BASKET_WEB = Rest.BASE_PATH + "basket-web/";

    String GET = "get";

    String ADD = "add";

    String EDIT = "edit/{id}";

    @GetMapping(GET)
    ApiResult<BasketWebDTO> get(@RequestParam("user_id") String chatId);

    @PostMapping(ADD)
    ApiResult<?> add(@RequestParam("user_id") String chatId,
                     @RequestBody @Valid OrderProductDTO dto);

    @PutMapping(EDIT)
    ApiResult<?> edit(@RequestParam("user_id") String chatId,
                      @PathVariable Long id,
                      @RequestBody @Valid OrderProductDTO dto);

}
