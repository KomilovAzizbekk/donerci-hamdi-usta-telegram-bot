package uz.mediasolutions.mdeliveryservice.controller.web.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import uz.mediasolutions.mdeliveryservice.controller.web.abs.WebBannerController;
import uz.mediasolutions.mdeliveryservice.manual.ApiResult;
import uz.mediasolutions.mdeliveryservice.payload.BannerDTO;
import uz.mediasolutions.mdeliveryservice.service.webabs.WebBannerService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class WebBannerControllerImpl implements WebBannerController {

    private final WebBannerService bannerWebService;

    @Override
    public ApiResult<List<BannerDTO>> get() {
        return bannerWebService.get();
    }
}
