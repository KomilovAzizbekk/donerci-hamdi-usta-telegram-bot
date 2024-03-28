package uz.mediasolutions.mdeliveryservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.mediasolutions.mdeliveryservice.manual.ApiResult;
import uz.mediasolutions.mdeliveryservice.payload.PaymeCreateTransactionDTO;
import uz.mediasolutions.mdeliveryservice.service.abs.PaymeService;

@Service
@RequiredArgsConstructor
public class PaymeServiceImpl implements PaymeService {

    public static String URL = "https://merchant/pay/";
    public static String AUTH = "Basic TG9naW46UGFzcw==";


    @Override
    public ApiResult<?> createTransaction(PaymeCreateTransactionDTO dto) {
        return null;
    }
}
