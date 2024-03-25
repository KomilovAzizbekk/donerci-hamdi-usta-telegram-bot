package uz.mediasolutions.mdeliveryservice.service.abs;

import uz.mediasolutions.mdeliveryservice.manual.ApiResult;
import uz.mediasolutions.mdeliveryservice.payload.ClickInvoiceDTO;

public interface ClickService {


    ApiResult<?> createInvoice(ClickInvoiceDTO dto);
}
