package uz.mediasolutions.mdeliveryservice.controller.abs;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import uz.mediasolutions.mdeliveryservice.manual.ApiResult;
import uz.mediasolutions.mdeliveryservice.payload.ClickResDTO;
import uz.mediasolutions.mdeliveryservice.utills.constants.Rest;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

@RequestMapping(ClickController.CLICK)
public interface ClickController {

    String CLICK = Rest.BASE_PATH + "click/";
    String CREATE_INVOICE = "create-invoice";

    @PostMapping(CREATE_INVOICE)
    ApiResult<ClickResDTO> createInvoice(@RequestParam float amount,
                                         @RequestParam(name = "phone_number") String phoneNumber,
                                         @RequestParam(name = "merchant_trans_id") String merchantTransId) throws NoSuchAlgorithmException, IOException;
}
