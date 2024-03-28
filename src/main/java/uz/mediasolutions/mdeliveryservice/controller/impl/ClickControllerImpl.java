package uz.mediasolutions.mdeliveryservice.controller.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.RestController;
import uz.mediasolutions.mdeliveryservice.controller.abs.ClickController;
import uz.mediasolutions.mdeliveryservice.payload.ClickInvoiceDTO;
import uz.mediasolutions.mdeliveryservice.service.abs.ClickService;

@RestController
@RequiredArgsConstructor
public class ClickControllerImpl implements ClickController {

    private final ClickService clickService;

    @Override
    public HttpEntity<?> createInvoice(ClickInvoiceDTO dto) {
        return clickService.createInvoice(dto);
    }

    @Override
    public HttpEntity<?> statusInvoice(Long invoiceId) {
        return clickService.statusInvoice(invoiceId);
    }

    @Override
    public HttpEntity<?> paymentStatusCheck(String merchantTransId) {
        return clickService.paymentStatusCheck(merchantTransId);
    }
}
