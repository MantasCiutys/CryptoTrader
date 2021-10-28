package com.mantasciutys.cryptotrader.authentication;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class TestCoinbaseWalletAuth {

    @Autowired
    private CoinbaseWalletAuth coinbaseWalletAuth;

    @Test
    public void testBuildGetHeaders() {
        HttpHeaders headers = coinbaseWalletAuth.buildHeaders(HttpMethod.GET.name(), "dummy_uri", null);

        Assertions.assertNotNull(headers.get("CB-ACCESS-KEY"));
        Assertions.assertNotNull(headers.get("CB-ACCESS-SIGN"));
        Assertions.assertNotNull(headers.get("CB-ACCESS-TIMESTAMP"));
        Assertions.assertNotNull(headers.get("CB-ACCESS-PASSPHRASE"));
    }

    public void testConvertToJsonNullObject() {
        String converted = coinbaseWalletAuth.convertToJson(null);

        Assertions.assertEquals("", converted);
    }
}
