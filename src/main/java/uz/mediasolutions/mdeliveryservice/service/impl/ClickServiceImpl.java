package uz.mediasolutions.mdeliveryservice.service.impl;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.glassfish.grizzly.http.util.TimeStamp;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import uz.mediasolutions.mdeliveryservice.exceptions.RestException;
import uz.mediasolutions.mdeliveryservice.feign.ClickFeign;
import uz.mediasolutions.mdeliveryservice.manual.ApiResult;
import uz.mediasolutions.mdeliveryservice.payload.ClickInvoiceDTO;
import uz.mediasolutions.mdeliveryservice.payload.ClickResDTO;
import uz.mediasolutions.mdeliveryservice.service.abs.ClickService;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;

@Service
@RequiredArgsConstructor
@Slf4j
public class ClickServiceImpl implements ClickService {

    private final ClickFeign clickFeign;
    private final RestTemplate restTemplate;

    private static final String BASE_URL = "https://api.click.uz/v2/merchant/invoice/create";

    @Value("${click.merchant.user_id}")
    private static Integer MERCHANT_USER_ID;

    @Value("${click.merchant.secret_key}")
    private static String SECRET_KEY;

    @Value("${click.merchant.service_id}")
    private static Integer SERVICE_ID;

    @Value("${click.merchant.merchant_id}")
    private static Integer MERCHANT_ID;

//    @Override
//    @Transactional
//    public ApiResult<?> createInvoice(ClickInvoiceDTO clickInvoiceDTO) throws NoSuchAlgorithmException {
//
////        log.info("Token created {}", token);
//        Gson gson = new Gson();
//        ClickInvoiceDTO invoice = new ClickInvoiceDTO(
//                SERVICE_ID,
//                clickInvoiceDTO.getAmount(),
//                clickInvoiceDTO.getPhoneNumber(),
//                clickInvoiceDTO.getMerchantTransId());
//        log.info("Invoice {}", gson.toJson(invoice));
//
//        long timestamp = Instant.now().getEpochSecond();
//        String token = generateAuthHeader(timestamp);
//        log.info("Token {}", token);
//        ClickResDTO response = clickFeign.createInvoice(invoice, token);
//        log.info("Create invoice response {}", response);
//
//        return ApiResult.success(response);
//    }

    @Override
    @Transactional
    public ApiResult<?> createInvoice(ClickInvoiceDTO dto) throws NoSuchAlgorithmException {
        long timestamp = System.currentTimeMillis() / 1000; // Current timestamp in seconds

        String authHeader = generateAuthHeader(timestamp);

        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
        headers.set("Content-Type", MediaType.APPLICATION_JSON_VALUE);
        headers.set("Auth", authHeader);

        // Create request body
        String requestBody = String.format("{\"service_id\": %d, \"amount\": %.2f, \"phone_number\": \"%s\", \"merchant_trans_id\": \"%s\"}",
                SERVICE_ID, dto.getAmount(), dto.getPhoneNumber(), dto.getMerchantTransId());

        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

        ClickResDTO response = restTemplate.postForObject(BASE_URL, requestEntity, ClickResDTO.class);
        return ApiResult.success(response);
    }

    private static ClickResDTO parseResponse(String jsonResponse) {

        try {
            JSONObject responseJson = new JSONObject(jsonResponse);
            int errorCode = responseJson.getInt("error_code");
            String errorNote = responseJson.getString("error_note");
            long invoiceId = responseJson.getLong("invoice_id");

            ClickResDTO dto = ClickResDTO.builder()
                    .errorCode(errorCode)
                    .errorNote(errorNote)
                    .invoiceId(invoiceId)
                    .build();
            return dto;
        } catch (JSONException e) {
            throw RestException.restThrow("JSON EXCEPTION", HttpStatus.CONFLICT);
        }
    }

    private static String generateAuthHeader(long timestamp) throws NoSuchAlgorithmException {
        String message = timestamp + SECRET_KEY;
        MessageDigest digest = MessageDigest.getInstance("SHA-1");
        byte[] hashedBytes = digest.digest(message.getBytes(StandardCharsets.UTF_8));
        StringBuilder hexString = new StringBuilder();
        for (byte hashedByte : hashedBytes) {
            String hex = Integer.toHexString(0xff & hashedByte);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return MERCHANT_USER_ID + ":" + hexString.toString() + ":" + timestamp;
    }
}
