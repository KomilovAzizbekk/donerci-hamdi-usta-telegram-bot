package uz.mediasolutions.mdeliveryservice.controller.abs;

import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import uz.mediasolutions.mdeliveryservice.payload.ClickInvoiceDTO;
import uz.mediasolutions.mdeliveryservice.utills.constants.Rest;

import javax.validation.Valid;

@RequestMapping(ClickController.CLICK)
public interface ClickController {

    String CLICK = Rest.BASE_PATH + "click/";
    String CREATE_INVOICE = "create-invoice";

    @PostMapping(CREATE_INVOICE)
    HttpEntity<?> createInvoice(@RequestBody @Valid ClickInvoiceDTO dto);
}
