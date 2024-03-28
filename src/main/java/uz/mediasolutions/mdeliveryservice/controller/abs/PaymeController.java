package uz.mediasolutions.mdeliveryservice.controller.abs;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import uz.mediasolutions.mdeliveryservice.manual.ApiResult;
import uz.mediasolutions.mdeliveryservice.payload.PaymeCreateTransactionDTO;
import uz.mediasolutions.mdeliveryservice.utills.constants.Rest;

import javax.validation.Valid;

@RequestMapping(PaymeController.CLICK)
public interface PaymeController {

    String CLICK = Rest.BASE_PATH + "payme/";
    String CREATE_TRANSACTION = "create-transaction";

    @PostMapping(CREATE_TRANSACTION)
    ApiResult<?> createTransaction(@RequestBody @Valid PaymeCreateTransactionDTO dto);
}
