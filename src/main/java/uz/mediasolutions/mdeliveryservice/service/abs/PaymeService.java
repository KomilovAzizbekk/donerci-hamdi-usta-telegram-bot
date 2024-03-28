package uz.mediasolutions.mdeliveryservice.service.abs;

import uz.mediasolutions.mdeliveryservice.manual.ApiResult;
import uz.mediasolutions.mdeliveryservice.payload.PaymeCreateTransactionDTO;

public interface PaymeService {
    ApiResult<?> createTransaction(PaymeCreateTransactionDTO dto);
}
