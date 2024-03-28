package uz.mediasolutions.mdeliveryservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import uz.mediasolutions.mdeliveryservice.entity.ClickInvoice;
import uz.mediasolutions.mdeliveryservice.entity.Transaction;
import uz.mediasolutions.mdeliveryservice.exceptions.RestException;
import uz.mediasolutions.mdeliveryservice.payload.ClickCreateInvoiceDTO;
import uz.mediasolutions.mdeliveryservice.payload.ClickInvoiceDTO;
import uz.mediasolutions.mdeliveryservice.payload.CreateInvoiceResponseDTO;
import uz.mediasolutions.mdeliveryservice.payload.StatusInvoiceResponseDTO;
import uz.mediasolutions.mdeliveryservice.repository.ClickInvoiceRepository;
import uz.mediasolutions.mdeliveryservice.repository.TransactionRepository;
import uz.mediasolutions.mdeliveryservice.service.abs.ClickService;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ClickServiceImpl implements ClickService {

    private final TransactionRepository transactionRepository;
    private final ClickInvoiceRepository clickInvoiceRepository;

    @Value("${click.base.url}")
    private String clickBaseUrl;

    @Value("${click.service.id}")
    private Integer clickServiceId;

    @Value("${click.merchant.id}")
    private Integer clickMerchantId;

    @Value("${click.merchant.user.id}")
    private Integer clickMerchantUserId;

    @Value("${click.secret.key}")
    private String clickSecretKey;

    private String generateDigest(long millisecond) {
        try {
            String input = millisecond + clickSecretKey;

            MessageDigest sha1 = MessageDigest.getInstance("SHA-1");
            byte[] hashBytes = sha1.digest(input.getBytes(StandardCharsets.UTF_8));

            // Convert the byte array to a hexadecimal string
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashBytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }

            String sha1Hash = hexString.toString();
            return sha1Hash;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public HttpEntity<?> createInvoice(ClickInvoiceDTO dto) {
        try {
            long currentSecond = System.currentTimeMillis() / 1000;
            String digest = generateDigest(currentSecond);
            String authHeader = clickMerchantUserId + ":" + digest + ":" + currentSecond;

            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
            headers.set("Content-Type", MediaType.APPLICATION_JSON_VALUE);
            headers.set("Auth", authHeader);
            System.out.println("authHeader => " + authHeader);

            ClickCreateInvoiceDTO createInvoiceDto = new ClickCreateInvoiceDTO(
                    clickServiceId, dto.getAmount(), dto.getPhoneNumber(), dto.getMerchantTransId());
            HttpEntity<ClickCreateInvoiceDTO> requestEntity = new HttpEntity<>(createInvoiceDto, headers);

            CreateInvoiceResponseDTO response = restTemplate.postForObject(clickBaseUrl + "invoice/create",
                    requestEntity, CreateInvoiceResponseDTO.class);

            Transaction transaction = transactionRepository.findById(Long.valueOf(dto.getMerchantTransId())).orElseThrow(
                    () -> RestException.restThrow("TRANSACTION ID NOT FOUND", HttpStatus.BAD_REQUEST));
            assert response != null;
            ClickInvoice clickInvoice = ClickInvoice.builder()
                    .amount(dto.getAmount())
                    .phoneNumber(dto.getPhoneNumber())
                    .transaction(transaction)
                    .invoiceId(response.getInvoiceId())
                    .build();
            clickInvoiceRepository.save(clickInvoice);

            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Internal server error!");
        }
    }

    @Override
    public HttpEntity<?> statusInvoice(Long invoiceId) {
        try {
            long currentSecond = System.currentTimeMillis() / 1000;
            String digest = generateDigest(currentSecond);
            String authHeader = clickMerchantUserId + ":" + digest + ":" + currentSecond;

            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
            headers.set("Content-Type", MediaType.APPLICATION_JSON_VALUE);
            headers.set("Auth", authHeader);
            System.out.println("authHeader => " + authHeader);

            HttpEntity<String> requestEntity = new HttpEntity<>(headers);

            ResponseEntity<StatusInvoiceResponseDTO> response = restTemplate.exchange(
                    clickBaseUrl + "invoice/status/{service_id}/{invoice_id}",
                    HttpMethod.GET,
                    requestEntity,
                    StatusInvoiceResponseDTO.class,
                    Map.of("service_id", clickServiceId, "invoice_id", invoiceId));

            ClickInvoice clickInvoice = clickInvoiceRepository.findByInvoiceId(invoiceId);
            clickInvoice.setInvoiceStatus(Objects.requireNonNull(response.getBody()).getInvoiceStatus());
            clickInvoice.setInvoiceStatusNote(response.getBody().getInvoiceStatusNote());
            clickInvoiceRepository.save(clickInvoice);

            return ResponseEntity.ok(response.getBody());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Internal server error!");
        }
    }

    @Override
    public HttpEntity<?> paymentStatusCheck(String merchantTransId) {
        try {
            long currentSecond = System.currentTimeMillis() / 1000;
            String digest = generateDigest(currentSecond);
            String authHeader = clickMerchantUserId + ":" + digest + ":" + currentSecond;

            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
            headers.set("Content-Type", MediaType.APPLICATION_JSON_VALUE);
            headers.set("Auth", authHeader);
            System.out.println("authHeader => " + authHeader);

            HttpEntity<String> requestEntity = new HttpEntity<>(headers);
            ResponseEntity<StatusInvoiceResponseDTO> response = restTemplate.exchange(
                    clickBaseUrl + "payment/status_by_mti/{service_id}/{merchant_trans_id}",
                    HttpMethod.GET,
                    requestEntity,
                    StatusInvoiceResponseDTO.class,
                    Map.of("service_id", clickServiceId, "merchant_trans_id", merchantTransId));
            return ResponseEntity.ok(response.getBody());

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Internal server error!");
        }
    }
}
