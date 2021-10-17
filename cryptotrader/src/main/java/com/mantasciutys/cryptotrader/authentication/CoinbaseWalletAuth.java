package com.mantasciutys.cryptotrader.authentication;

import org.apache.commons.codec.binary.Hex;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Component;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Component
public class CoinbaseWalletAuth {

    private String apiKey;
    private String apiSecret;
    private long timestamp;

    // given a request, required headers for authentication added
    public HttpHeaders buildHeaders(HttpRequest request) {
        HttpHeaders headers = new HttpHeaders();
        String secretKeyEncrypted = buildSecretKey(request);

        headers.add("CB-ACCESS-KEY", apiKey);
        headers.add("CB-ACCESS-SIGN", secretKeyEncrypted);
        headers.add("CB-ACCESS-TIMESTAMP", String.valueOf(timestamp));

        return headers;
    }

    private String buildSecretKey(HttpRequest request) {
        generateTimestamp();
        String concatenatedKey = timestamp + request.getMethod().toString() + request.getURI();

        try {
            Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
            SecretKeySpec secret_key = new SecretKeySpec(apiSecret.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
            sha256_HMAC.init(secret_key);
            return Hex.encodeHexString(sha256_HMAC.doFinal(concatenatedKey.getBytes(StandardCharsets.UTF_8)));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }

        return "";
    }

    private void generateTimestamp() {
        timestamp = System.currentTimeMillis() / 1000L;
    }

    private void buildApiInfo() {
        apiKey = "";
        apiSecret = "";
    }
}
