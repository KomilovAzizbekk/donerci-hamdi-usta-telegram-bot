package uz.mediasolutions.mdeliveryservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import uz.mediasolutions.mdeliveryservice.payload.ClickCreateInvoiceDTO;
import uz.mediasolutions.mdeliveryservice.payload.ClickInvoiceDTO;
import uz.mediasolutions.mdeliveryservice.payload.ClickResDTO;
import uz.mediasolutions.mdeliveryservice.service.abs.ClickService;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
@RequiredArgsConstructor
public class ClickServiceImpl implements ClickService {

    private final RestTemplate restTemplate;

    private static final String BASE_URL = "https://api.click.uz/v2/merchant/invoice/create";

    @Value("${click.merchant.user_id}")
    private static String MERCHANT_USER_ID;

    @Value("${click.merchant.secret_key}")
    private static String SECRET_KEY;

    @Value("${click.merchant.service_id}")
    private static Integer SERVICE_ID;

    @Value("${click.merchant.merchant_id}")
    private static Integer MERCHANT_ID;

    @Override
    @Transactional
    public HttpEntity<?> createInvoice(ClickInvoiceDTO dto) {
        try {
            long currentSeconds = System.currentTimeMillis() / 1000;
            String digest = generateDigest(currentSeconds);
            String authHeader = MERCHANT_USER_ID + ":" + digest + ":" + currentSeconds;

            // Create request header
            HttpHeaders headers = new HttpHeaders();
            headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
            headers.set("Content-Type", MediaType.APPLICATION_JSON_VALUE);
            headers.set("Auth", authHeader);

            // Create request body
            ClickCreateInvoiceDTO clickInvoiceDTO = new ClickCreateInvoiceDTO(
                    SERVICE_ID, dto.getAmount(), dto.getPhoneNumber(), dto.getMerchantTransId());

            HttpEntity<ClickCreateInvoiceDTO> requestEntity = new HttpEntity<>(clickInvoiceDTO, headers);

            ClickResDTO response = restTemplate.postForObject(BASE_URL, requestEntity, ClickResDTO.class);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Inernal server error!");
        }
    }


    private static String generateDigest(long timestamp) {
        String dataToDigest = timestamp + SECRET_KEY;
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            byte[] hashedBytes = digest.digest(dataToDigest.getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder();
            for (byte b : hashedBytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
}
