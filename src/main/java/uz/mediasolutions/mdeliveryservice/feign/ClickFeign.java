package uz.mediasolutions.mdeliveryservice.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import uz.mediasolutions.mdeliveryservice.payload.ClickInvoiceDTO;
import uz.mediasolutions.mdeliveryservice.payload.ClickResDTO;


@FeignClient(url = "https://api.click.uz/v2/merchant", name = "click")
public interface ClickFeign {

    @PostMapping("/invoice/create")
    ClickResDTO createInvoice(@RequestBody ClickInvoiceDTO clickInvoiceDTO,
//                              @RequestHeader(HttpHeaders.ACCEPT) String accept,
//                              @RequestHeader(HttpHeaders.CONTENT_TYPE) String contentType,
                              @RequestHeader(HttpHeaders.AUTHORIZATION) String auth);

}