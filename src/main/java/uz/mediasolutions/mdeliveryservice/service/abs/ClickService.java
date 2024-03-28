package uz.mediasolutions.mdeliveryservice.service.abs;

import org.springframework.http.HttpEntity;
import uz.mediasolutions.mdeliveryservice.payload.ClickInvoiceDTO;

public interface ClickService {

    HttpEntity<?> createInvoice(ClickInvoiceDTO dto);

    HttpEntity<?> statusInvoice(Long invoiceId);

    HttpEntity<?> paymentStatusCheck(String merchantTransId);

}
