package com.mantasciutys.cryptotrader.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mantasciutys.cryptotrader.authentication.CoinbaseWalletAuth;
import com.mantasciutys.cryptotrader.pojo.Account;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;

import java.math.BigDecimal;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class TestAccountService {

    @Autowired
    private AccountService accountService;

    @Autowired
    private CoinbaseWalletAuth coinbaseWalletAuth;

    @Autowired
    private RestTemplate restTemplate;

    private MockRestServiceServer mockServer;
    private ObjectMapper mapper = new ObjectMapper();

    @Before
    public void init() {
        mockServer = MockRestServiceServer.bindTo(restTemplate).build();
    }

    @Test
    public void test() {
        Account account_1 = new Account();
        account_1.setId("123");
        account_1.setCurrency("GBP");
        account_1.setBalance(new BigDecimal("1000"));

        Account account_2 = new Account();
        account_2.setId("456");
        account_2.setCurrency("USD");
        account_2.setBalance(new BigDecimal("2500"));

        Account[] accounts = {account_1, account_2};

        try {
            mockServer.expect(ExpectedCount.once(),
                    requestTo(new URI("https://api-public.sandbox.exchange.coinbase.com/accounts")))
                    .andExpect(method(HttpMethod.GET))
                    .andRespond(withStatus(HttpStatus.OK)
                            .contentType(MediaType.APPLICATION_JSON)
                            .body(mapper.writeValueAsString(accounts)));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        List<Account> retrievedAccounts = accountService.getAllAccountsForProfile();
        mockServer.verify();

        Assert.assertEquals(2, retrievedAccounts.size());
    }
}
