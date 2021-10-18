package com.mantasciutys.cryptotrader.authentication;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import javax.management.RuntimeErrorException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@Component
public class CoinbaseWalletAuth {

    private static final Logger LOGGER = LoggerFactory.getLogger(CoinbaseWalletAuth.class);
    private static final String CB_ACCESS_KEY_HEADER = "CB-ACCESS-KEY";
    private static final String CB_ACCESS_SIGN_HEADER = "CB-ACCESS-SIGN";
    private static final String CB_ACCESS_TIMESTAMP_HEADER = "CB-ACCESS-TIMESTAMP";
    private static final String CB_ACCESS_PASSPHRASE = "CB-ACCESS-PASSPHRASE";
    private static final String USER_AGENT_HEADER = "User-Agent";

    private String apiKey;
    private String apiSecret;
    private String apiPass;

    public CoinbaseWalletAuth(@Value("${coinbase.api.key}") String apiKey, @Value("${coinbase.api.secret}") String apiSecret,
                              @Value("${coinbase.api.passphrase}") String apiPass) {
        this.apiKey = apiKey;
        this.apiSecret = apiSecret;
        this.apiPass = apiPass;
    }

    // given a request, required headers for authentication added
    public HttpHeaders buildHeaders(String method, String uri) {
        HttpHeaders headers = new HttpHeaders();
        long timestamp = generateTimestamp();
        String secretKeyEncrypted = buildSecretKey(method, uri, timestamp);

        headers.add(CB_ACCESS_KEY_HEADER, apiKey);
        headers.add(CB_ACCESS_SIGN_HEADER, secretKeyEncrypted);
        headers.add(CB_ACCESS_TIMESTAMP_HEADER, String.valueOf(timestamp));
        headers.add(CB_ACCESS_PASSPHRASE, apiPass);
        headers.add(USER_AGENT_HEADER, "Testing my own application");

        return headers;
    }

    private String buildSecretKey(String method, String uri, long timestamp) {
        String encoded = "";
        LOGGER.info("Timestamp is " + timestamp);

        try {
            String prehash = timestamp + method.toUpperCase() + uri;
            byte[] secretDecoded = Base64.getDecoder().decode(apiSecret);
            SecretKeySpec keyspec = new SecretKeySpec(secretDecoded, Mac.getInstance("HmacSHA256").getAlgorithm());
            Mac sha256 = (Mac) Mac.getInstance("HmacSHA256").clone();
            sha256.init(keyspec);
            encoded = Base64.getEncoder().encodeToString(sha256.doFinal(prehash.getBytes()));
        } catch (CloneNotSupportedException | InvalidKeyException e) {
            e.printStackTrace();
            throw new RuntimeErrorException(new Error("Cannot set up authentication headers."));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return encoded;
    }

    private long generateTimestamp() {
        return System.currentTimeMillis() / 1000L;
    }
}
