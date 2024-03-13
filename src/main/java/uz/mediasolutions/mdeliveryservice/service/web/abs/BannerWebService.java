package uz.mediasolutions.mdeliveryservice.service.web.abs;

import uz.mediasolutions.mdeliveryservice.manual.ApiResult;
import uz.mediasolutions.mdeliveryservice.payload.BannerDTO;

import java.util.List;

public interface BannerWebService {
    ApiResult<List<BannerDTO>> get();
}
