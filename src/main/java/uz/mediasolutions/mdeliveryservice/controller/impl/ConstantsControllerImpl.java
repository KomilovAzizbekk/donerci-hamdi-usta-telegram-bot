package uz.mediasolutions.mdeliveryservice.controller.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RestController;
import uz.mediasolutions.mdeliveryservice.controller.abs.ConstantsController;
import uz.mediasolutions.mdeliveryservice.entity.Constants;
import uz.mediasolutions.mdeliveryservice.manual.ApiResult;
import uz.mediasolutions.mdeliveryservice.payload.BannerDTO;
import uz.mediasolutions.mdeliveryservice.service.abs.ConstantsService;

@RestController
@RequiredArgsConstructor
public class ConstantsControllerImpl implements ConstantsController {

    private final ConstantsService constantsService;

    @Override
    public ApiResult<Constants> get() {
        return constantsService.get();
    }

    @Override
    public ApiResult<?> edit(Long id, Constants constants) {
        return constantsService.edit(id, constants);
    }
}
