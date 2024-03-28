package uz.mediasolutions.mdeliveryservice.controller.abs;

import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import uz.mediasolutions.mdeliveryservice.payload.ClickInvoiceDTO;
import uz.mediasolutions.mdeliveryservice.utills.constants.Rest;

@RequestMapping(ClickController.CLICK)
public interface ClickController {

    String CLICK = Rest.BASE_PATH + "click/merchant/";
    String CREATE_INVOICE = "create/invoice";

    @PostMapping(CREATE_INVOICE)
    HttpEntity<?> createInvoice(@RequestBody ClickInvoiceDTO dto);
}
