package uz.mediasolutions.mdeliveryservice.controller.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import uz.mediasolutions.mdeliveryservice.controller.abs.ClickController;
import uz.mediasolutions.mdeliveryservice.manual.ApiResult;
import uz.mediasolutions.mdeliveryservice.payload.ClickResDTO;
import uz.mediasolutions.mdeliveryservice.service.abs.ClickService;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

@RestController
@RequiredArgsConstructor
public class ClickControllerImpl implements ClickController {

    private final ClickService clickService;


    @Override
    public ApiResult<ClickResDTO> createInvoice(float amount, String phoneNumber, String merchantTransId) throws NoSuchAlgorithmException, IOException {
        return clickService.createInvoice(amount, phoneNumber, merchantTransId);
    }
}
