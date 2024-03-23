package uz.mediasolutions.mdeliveryservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import uz.mediasolutions.mdeliveryservice.exceptions.RestException;
import uz.mediasolutions.mdeliveryservice.manual.ApiResult;
import uz.mediasolutions.mdeliveryservice.payload.ClickResDTO;
import uz.mediasolutions.mdeliveryservice.service.abs.ClickService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class ClickServiceImpl implements ClickService {

    private static final String BASE_URL = "https://api.click.uz/v2/merchant/invoice/create";
    private static final String MERCHANT_USER_ID = "NQgUZqEDrD";
    private static final String MERCHANT_ID = "23300";
    private static final String SECRET_KEY = "NQgUZqEDrD";
    private static final Integer SERVICE_ID = 32625;

    @Override
    public ApiResult<ClickResDTO> createInvoice(float amount, String phoneNumber, String merchantTransId) throws NoSuchAlgorithmException, IOException {
        long timestamp = new Date().getTime() / 1000;
        String authHeader = generateAuthHeader(timestamp);

        String requestBody = String.format("{\"service_id\": %d, \"amount\": %.2f, \"phone_number\": \"%s\", \"merchant_trans_id\": \"%s\"}",
                SERVICE_ID, amount, phoneNumber, merchantTransId);

        URL url = new URL(BASE_URL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Accept", "application/json");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("Auth", authHeader);
        connection.setDoOutput(true);

        try (OutputStream os = connection.getOutputStream()) {
            byte[] input = requestBody.getBytes(StandardCharsets.UTF_8);
            os.write(input, 0, input.length);
        }
        System.out.println(connection.getResponseCode());
        System.out.println(connection.getResponseMessage());

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
            System.out.println(inputLine);
        }
        in.close();
        System.out.println(in);

        System.out.println(response);
//        ClickResDTO clickResDTO = parseResponse(response.toString());
        return ApiResult.success(new ClickResDTO());
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
        return MERCHANT_USER_ID + ":" + hexString + ":" + timestamp;
    }

}
