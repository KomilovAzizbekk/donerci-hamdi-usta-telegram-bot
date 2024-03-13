package uz.mediasolutions.mdeliveryservice.controller.web.abs;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import uz.mediasolutions.mdeliveryservice.manual.ApiResult;
import uz.mediasolutions.mdeliveryservice.payload.BannerDTO;
import uz.mediasolutions.mdeliveryservice.payload.CategoryWebDTO;
import uz.mediasolutions.mdeliveryservice.utills.constants.Rest;

import java.util.List;

@RequestMapping(BannerWebController.BANNER_WEB)
public interface BannerWebController {

    String BANNER_WEB = Rest.BASE_PATH + "banner-web/";

    String GET = "get";

    @GetMapping(GET)
    ApiResult<List<BannerDTO>> get();

}
