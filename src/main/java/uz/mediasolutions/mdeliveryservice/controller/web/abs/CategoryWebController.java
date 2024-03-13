package uz.mediasolutions.mdeliveryservice.controller.web.abs;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import uz.mediasolutions.mdeliveryservice.manual.ApiResult;
import uz.mediasolutions.mdeliveryservice.payload.CategoryWebDTO;
import uz.mediasolutions.mdeliveryservice.utills.constants.Rest;

import java.util.List;

@RequestMapping(CategoryWebController.CATEGORY_WEB)
public interface CategoryWebController {

    String CATEGORY_WEB = Rest.BASE_PATH + "category-web/";

    String GET = "get";

    @GetMapping(GET)
    ApiResult<List<CategoryWebDTO>> get(@RequestParam("user_id") String chatId);

}
