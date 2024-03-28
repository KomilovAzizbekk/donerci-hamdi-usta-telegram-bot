package uz.mediasolutions.mdeliveryservice.service.abs;

import org.springframework.http.HttpEntity;
import uz.mediasolutions.mdeliveryservice.manual.ApiResult;
import uz.mediasolutions.mdeliveryservice.payload.PaymeCreateTransactionDTO;
import uz.mediasolutions.mdeliveryservice.payload.PaymeInvoiceDTO;

public interface PaymeService {
    HttpEntity<?> createTransaction(PaymeInvoiceDTO dto);
}
