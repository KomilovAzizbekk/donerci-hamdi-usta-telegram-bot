package uz.mediasolutions.mdeliveryservice.controller.web.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import uz.mediasolutions.mdeliveryservice.controller.web.abs.WebCategoryController;
import uz.mediasolutions.mdeliveryservice.manual.ApiResult;
import uz.mediasolutions.mdeliveryservice.payload.CategoryWebDTO;
import uz.mediasolutions.mdeliveryservice.service.webabs.WebCategoryService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class WebCategoryControllerImpl implements WebCategoryController {

    private final WebCategoryService categoryWebService;

    @Override
    public ApiResult<List<CategoryWebDTO>> get(String chatId) {
        return categoryWebService.get(chatId);
    }
}
