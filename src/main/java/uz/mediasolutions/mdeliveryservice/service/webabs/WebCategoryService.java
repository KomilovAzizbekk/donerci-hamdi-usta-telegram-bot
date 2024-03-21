package uz.mediasolutions.mdeliveryservice.service.webabs;

import uz.mediasolutions.mdeliveryservice.manual.ApiResult;
import uz.mediasolutions.mdeliveryservice.payload.CategoryWebDTO;

import java.util.List;

public interface WebCategoryService {
    ApiResult<List<CategoryWebDTO>> get(String chatId);
}
