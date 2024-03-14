package uz.mediasolutions.mdeliveryservice.service.web.abs;

import uz.mediasolutions.mdeliveryservice.manual.ApiResult;
import uz.mediasolutions.mdeliveryservice.payload.VariationWebDTO;

import java.util.List;

public interface WebVariationService {

    ApiResult<List<VariationWebDTO>> getAllByProductId(String chatId, Long productId);
}
