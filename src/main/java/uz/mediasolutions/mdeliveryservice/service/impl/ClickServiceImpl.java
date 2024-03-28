package uz.mediasolutions.mdeliveryservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import uz.mediasolutions.mdeliveryservice.payload.ClickCreateInvoiceDTO;
import uz.mediasolutions.mdeliveryservice.payload.ClickInvoiceDTO;
import uz.mediasolutions.mdeliveryservice.payload.CreateInvoiceResponseDTO;
import uz.mediasolutions.mdeliveryservice.service.abs.ClickService;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
@RequiredArgsConstructor
public class ClickServiceImpl implements ClickService {

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
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Internel server error!");
        }
    }
}
