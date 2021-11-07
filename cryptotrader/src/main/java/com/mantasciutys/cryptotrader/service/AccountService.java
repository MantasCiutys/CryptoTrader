package com.mantasciutys.cryptotrader.service;

import com.mantasciutys.cryptotrader.authentication.CoinbaseWalletAuth;
import com.mantasciutys.cryptotrader.pojo.Account;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;

@Service
public class AccountService {
    private static final Logger LOGGER = Logger.getLogger(AccountService.class);

    private final WebClient apiClient;
    private final RestTemplate restTemplate;
    private final CoinbaseWalletAuth coinbaseWalletAuth;
    private final String accountsUri;

    public AccountService(WebClient apiClient, RestTemplate restTemplate, CoinbaseWalletAuth coinbaseWalletAuth, @Value("${base.api.url.accounts}") String accountsUri) {
        this.apiClient = apiClient;
        this.restTemplate = restTemplate;
        this.coinbaseWalletAuth = coinbaseWalletAuth;
        this.accountsUri = accountsUri;
    }

    public List<Account> getAllAccountsForProfile() {
        LOGGER.info("Started retrieving all accounts from the profile");

        HttpHeaders headers = coinbaseWalletAuth.buildHeaders(HttpMethod.GET.name(), accountsUri, null);
        HttpEntity<String> httpEntity = new HttpEntity<>(headers);

        try {
            ResponseEntity<Account[]> responseEntity = restTemplate.exchange(accountsUri,
                    HttpMethod.GET,
                    httpEntity,
                    Account[].class
            );
            return Arrays.asList(responseEntity.getBody());
        } catch (HttpClientErrorException e) {
            LOGGER.error("Error when buying an order");
            e.printStackTrace();
            throw e;
        }
    }
}
