package uz.mediasolutions.mdeliveryservice.controller.abs;

import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;
import uz.mediasolutions.mdeliveryservice.payload.ClickInvoiceDTO;
import uz.mediasolutions.mdeliveryservice.utills.constants.Rest;

@RequestMapping(ClickController.CLICK)
public interface ClickController {

    String CLICK = Rest.BASE_PATH + "click/merchant/";
    String CREATE_INVOICE = "create/invoice";
    String STATUS_INVOICE = "status/invoice/{invoiceId}";
    String PAYMENT_STATUS_CHECK = "payment/status/check/{merchantTransId}";

    @PostMapping(CREATE_INVOICE)
    HttpEntity<?> createInvoice(@RequestBody ClickInvoiceDTO dto);

    @GetMapping(STATUS_INVOICE)
    HttpEntity<?> statusInvoice(@PathVariable Long invoiceId);

    @GetMapping(PAYMENT_STATUS_CHECK)
    HttpEntity<?> paymentStatusCheck(@PathVariable String merchantTransId);
}
