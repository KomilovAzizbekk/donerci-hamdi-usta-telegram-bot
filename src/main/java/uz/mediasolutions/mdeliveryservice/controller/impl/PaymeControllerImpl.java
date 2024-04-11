package uz.mediasolutions.mdeliveryservice.controller.impl;

import lombok.RequiredArgsConstructor;
import net.minidev.json.JSONObject;
import org.springframework.web.bind.annotation.RestController;
import uz.mediasolutions.mdeliveryservice.controller.abs.PaymeController;
import uz.mediasolutions.mdeliveryservice.payload.payme.*;
import uz.mediasolutions.mdeliveryservice.service.abs.PaymeService;


@RestController
@RequiredArgsConstructor
public class PaymeControllerImpl implements PaymeController {

    private final PaymeService paymeService;

    @Override
    public JSONObject post(PaycomRequestForm requestForm, String authorization) {
        return paymeService.payWithPayme(requestForm, authorization);
    }

}
