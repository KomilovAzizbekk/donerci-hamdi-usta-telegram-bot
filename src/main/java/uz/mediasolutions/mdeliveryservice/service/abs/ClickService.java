package uz.mediasolutions.mdeliveryservice.service.abs;

import uz.mediasolutions.mdeliveryservice.manual.ApiResult;
import uz.mediasolutions.mdeliveryservice.payload.ClickResDTO;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public interface ClickService {


    ApiResult<ClickResDTO> createInvoice(float amount, String phoneNumber, String merchantTransId) throws NoSuchAlgorithmException, IOException;
}
