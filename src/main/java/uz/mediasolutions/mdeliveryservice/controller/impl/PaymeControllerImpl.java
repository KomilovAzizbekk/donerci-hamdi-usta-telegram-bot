package uz.mediasolutions.mdeliveryservice.controller.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.RestController;
import uz.mediasolutions.mdeliveryservice.controller.abs.PaymeController;
import uz.mediasolutions.mdeliveryservice.manual.ApiResult;
import uz.mediasolutions.mdeliveryservice.payload.PaymeCreateTransactionDTO;
import uz.mediasolutions.mdeliveryservice.payload.PaymeInvoiceDTO;
import uz.mediasolutions.mdeliveryservice.service.abs.PaymeService;

@RestController
@RequiredArgsConstructor
public class PaymeControllerImpl implements PaymeController {

    private final PaymeService paymeService;

    @Override
    public HttpEntity<?> createTransaction(PaymeInvoiceDTO dto) {
        return paymeService.createTransaction(dto);
    }
}
