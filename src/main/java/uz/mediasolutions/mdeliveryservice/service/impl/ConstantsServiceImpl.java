package uz.mediasolutions.mdeliveryservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import uz.mediasolutions.mdeliveryservice.entity.Constants;
import uz.mediasolutions.mdeliveryservice.exceptions.RestException;
import uz.mediasolutions.mdeliveryservice.manual.ApiResult;
import uz.mediasolutions.mdeliveryservice.repository.ConstantsRepository;
import uz.mediasolutions.mdeliveryservice.service.abs.ConstantsService;

@Service
@RequiredArgsConstructor
public class ConstantsServiceImpl implements ConstantsService {

    private final ConstantsRepository constantsRepository;

    @Override
    public ApiResult<Constants> get() {
        Constants constants = constantsRepository.findById(1L).orElseThrow(
                () -> RestException.restThrow("ID NOT FOUND", HttpStatus.BAD_REQUEST));
        return ApiResult.success(constants);
    }

    @Override
    public ApiResult<?> edit(Long id, Constants constants) {
        Constants c = constantsRepository.findById(1L).orElseThrow(
                () -> RestException.restThrow("ID NOT FOUND", HttpStatus.BAD_REQUEST));
        c.setMinDeliveryPrice(constants.getMinDeliveryPrice());
        c.setMinOrderPrice(constants.getMinOrderPrice());
        c.setPricePerKilometer(constants.getPricePerKilometer());
        c.setMinOrderPriceForFreeDelivery(constants.getMinOrderPriceForFreeDelivery());
        c.setBotWorking(constants.getBotWorking());
        c.setRadiusFreeDelivery(constants.getRadiusFreeDelivery());
        constantsRepository.save(c);
        return ApiResult.success("EDITED SUCCESSFULLY");
    }
}
