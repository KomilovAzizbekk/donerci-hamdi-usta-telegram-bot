package uz.mediasolutions.mdeliveryservice.service.webabs;

import uz.mediasolutions.mdeliveryservice.manual.ApiResult;
import uz.mediasolutions.mdeliveryservice.payload.BannerDTO;

import java.util.List;

public interface WebBannerService {
    ApiResult<List<BannerDTO>> get();
}
