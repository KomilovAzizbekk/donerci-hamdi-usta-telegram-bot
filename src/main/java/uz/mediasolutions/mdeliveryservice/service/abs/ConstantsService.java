package uz.mediasolutions.mdeliveryservice.service.abs;

import uz.mediasolutions.mdeliveryservice.entity.Constants;
import uz.mediasolutions.mdeliveryservice.manual.ApiResult;

public interface ConstantsService {
    ApiResult<Constants> get();

    ApiResult<?> edit(Long id, Constants constants);
}
