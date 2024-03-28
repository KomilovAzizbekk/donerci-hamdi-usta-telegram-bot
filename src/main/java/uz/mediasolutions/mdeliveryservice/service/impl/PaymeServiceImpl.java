package uz.mediasolutions.mdeliveryservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import uz.mediasolutions.mdeliveryservice.entity.ClickInvoice;
import uz.mediasolutions.mdeliveryservice.entity.Transaction;
import uz.mediasolutions.mdeliveryservice.exceptions.RestException;
import uz.mediasolutions.mdeliveryservice.manual.ApiResult;
import uz.mediasolutions.mdeliveryservice.payload.*;
import uz.mediasolutions.mdeliveryservice.service.abs.PaymeService;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymeServiceImpl implements PaymeService {


    @Value("${payme.base.url}")
    private String paymeBaseUrl;

    @Value("${payme.auth}")
    private String paymeAuth;


    @Override
    public HttpEntity<?> createTransaction(PaymeInvoiceDTO dto) {
        try {
            long currentSecond = System.currentTimeMillis() / 1000;

            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.set("Content-Type", "text/json");
            headers.set("Auth", paymeAuth);

            AccountDTO accountDTO = AccountDTO.builder()
                    .phone(dto.getPhone())
                    .build();

            ParamsDTO paramsDTO = ParamsDTO.builder()
                    .id(UUID.randomUUID().toString())
                    .time(currentSecond)
                    .amount(dto.getAmount())
                    .account(accountDTO)
                    .build();

            PaymeCreateTransactionDTO createTransactionDTO = new PaymeCreateTransactionDTO();
            createTransactionDTO.setMethod("CreateTransaction");
            createTransactionDTO.setParams(paramsDTO);
            HttpEntity<PaymeCreateTransactionDTO> requestEntity = new HttpEntity<>(createTransactionDTO, headers);

            CreateInvoiceResponseDTO response = restTemplate.postForObject(paymeBaseUrl,
                    requestEntity, CreateInvoiceResponseDTO.class);
//
//            Transaction transaction = transactionRepository.findById(Long.valueOf(dto.getMerchantTransId())).orElseThrow(
//                    () -> RestException.restThrow("TRANSACTION ID NOT FOUND", HttpStatus.BAD_REQUEST));
//            assert response != null;
//            ClickInvoice clickInvoice = ClickInvoice.builder()
//                    .amount(dto.getAmount())
//                    .phoneNumber(dto.getPhoneNumber())
//                    .transaction(transaction)
//                    .invoiceId(response.getInvoiceId())
//                    .build();
//            clickInvoiceRepository.save(clickInvoice);

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Internal server error!");
        }
    }
}
