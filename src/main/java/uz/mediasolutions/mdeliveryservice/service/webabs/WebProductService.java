package uz.mediasolutions.mdeliveryservice.service.webabs;

import uz.mediasolutions.mdeliveryservice.manual.ApiResult;
import uz.mediasolutions.mdeliveryservice.payload.ProductWebDTO;

import java.util.List;

public interface WebProductService {

    ApiResult<List<ProductWebDTO>> getAllByCategoryId(String chatId, Long categoryId);

}
