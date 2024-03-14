package uz.mediasolutions.mdeliveryservice.service.web.abs;

import uz.mediasolutions.mdeliveryservice.manual.ApiResult;
import uz.mediasolutions.mdeliveryservice.payload.ProductWebDTO;

import java.util.List;

public interface ProductWebService {

    ApiResult<List<ProductWebDTO>> getAllByCategoryId(String chatId, Long categoryId);

}
