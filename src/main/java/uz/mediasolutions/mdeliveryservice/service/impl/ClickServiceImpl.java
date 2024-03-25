package uz.mediasolutions.mdeliveryservice.service.impl;

import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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

@Service
@RequiredArgsConstructor
@Slf4j
public class ClickServiceImpl implements ClickService {

    private final ClickFeign clickFeign;

    private static final String MERCHANT_USER_ID = "NQgUZqEDrD";
    private static final String MERCHANT_ID = "23300";
    private static final String SECRET_KEY = "NQgUZqEDrD";
    private static final Integer SERVICE_ID = 32625;

    @Override
    @Transactional
    public ApiResult<?> createInvoice(ClickInvoiceDTO clickInvoiceDTO) {

//        log.info("Token created {}", token);
        Gson gson = new Gson();
        ClickInvoiceDTO invoice = new ClickInvoiceDTO(
                SERVICE_ID,
                clickInvoiceDTO.getAmount(),
                clickInvoiceDTO.getPhoneNumber(),
                clickInvoiceDTO.getMerchantTransId());
        log.info("Invoice {}", gson.toJson(invoice));

        String token = generateAuthHeader();
        log.info("Token {}", token);
        ClickResDTO response = clickFeign.createInvoice(invoice, token);
        log.info("Create invoice response {}", response);

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

    private String generateAuthHeader() {
        long timestamp = Instant.now().getEpochSecond();
        String digest = calculateDigest(timestamp);
        return "Auth: "+ MERCHANT_USER_ID + ":" + digest + ":" + timestamp;
    }

    private static String calculateDigest(long timestamp) {
        String message = timestamp + SECRET_KEY;
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-1");
            byte[] hash = digest.digest(message.getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

}
