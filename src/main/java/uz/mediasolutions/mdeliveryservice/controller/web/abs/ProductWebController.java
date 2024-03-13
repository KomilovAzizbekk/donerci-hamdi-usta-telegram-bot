package uz.mediasolutions.mdeliveryservice.controller.web.abs;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import uz.mediasolutions.mdeliveryservice.manual.ApiResult;
import uz.mediasolutions.mdeliveryservice.payload.ProductWebDTO;
import uz.mediasolutions.mdeliveryservice.utills.constants.Rest;

import java.util.List;

@RequestMapping(ProductWebController.PRODUCT_WEB)
public interface ProductWebController {

    String PRODUCT_WEB = Rest.BASE_PATH + "product-web/";

    String GET = "get";

    @GetMapping(GET)
    ApiResult<List<ProductWebDTO>> getAll(@RequestParam("user_id") String chatId);

}
