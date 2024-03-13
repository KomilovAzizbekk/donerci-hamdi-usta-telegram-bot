package uz.mediasolutions.mdeliveryservice.controller.web.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import uz.mediasolutions.mdeliveryservice.controller.web.abs.BannerWebController;
import uz.mediasolutions.mdeliveryservice.controller.web.abs.CategoryWebController;
import uz.mediasolutions.mdeliveryservice.manual.ApiResult;
import uz.mediasolutions.mdeliveryservice.payload.BannerDTO;
import uz.mediasolutions.mdeliveryservice.payload.CategoryWebDTO;
import uz.mediasolutions.mdeliveryservice.service.web.abs.BannerWebService;
import uz.mediasolutions.mdeliveryservice.service.web.abs.CategoryWebService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BannerWebControllerImpl implements BannerWebController {

    private final BannerWebService bannerWebService;

    @Override
    public ApiResult<List<BannerDTO>> get() {
        return bannerWebService.get();
    }
}
