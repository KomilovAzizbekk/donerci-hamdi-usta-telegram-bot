package uz.mediasolutions.mdeliveryservice.controller.abs;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import uz.mediasolutions.mdeliveryservice.manual.ApiResult;
import uz.mediasolutions.mdeliveryservice.payload.ClickInvoiceDTO;
import uz.mediasolutions.mdeliveryservice.utills.constants.Rest;

import javax.validation.Valid;
import java.security.NoSuchAlgorithmException;

@RequestMapping(ClickController.CLICK)
public interface ClickController {

    String CLICK = Rest.BASE_PATH + "click/";
    String CREATE_INVOICE = "create-invoice";

    @PostMapping(CREATE_INVOICE)
    ApiResult<?> createInvoice(@RequestBody @Valid ClickInvoiceDTO clickInvoiceDTO) throws NoSuchAlgorithmException;
}
