package uz.mediasolutions.mdeliveryservice.service.abs;

import org.springframework.data.domain.Page;
import uz.mediasolutions.mdeliveryservice.manual.ApiResult;
import uz.mediasolutions.mdeliveryservice.payload.BannerDTO;

public interface BannerService {

    ApiResult<Page<BannerDTO>> getAll(int page, int size);

    ApiResult<BannerDTO> get(Long id);

    ApiResult<?> upload(BannerDTO dto);

    ApiResult<?> delete(Long id);

}
