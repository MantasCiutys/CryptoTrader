package com.mantasciutys.cryptotrader.service;

import com.mantasciutys.cryptotrader.authentication.CoinbaseWalletAuth;
import com.mantasciutys.cryptotrader.pojo.Account;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;

@Service
public class AccountService {
    private static final Logger LOGGER = LoggerFactory.getLogger(AccountService.class);

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
        //Account[] monoAccounts = new Account[0];
        HttpHeaders headers = coinbaseWalletAuth.buildHeaders(HttpMethod.GET.name(), accountsUri);
        HttpEntity<String> httpEntity = new HttpEntity<>(headers);

        ResponseEntity<Account[]> responseEntity = restTemplate.exchange(accountsUri,
                HttpMethod.GET,
                httpEntity,
                Account[].class
                );

        return Arrays.asList(responseEntity.getBody());

        // Usage of WebClient commented out for now as it does not work.
        // RestTemplate does work in the meantime.
//        try {
//            monoAccounts = apiClient
//                    .get()
//                    .uri(accountsUri)
//                    .headers( httpHeaders -> { coinbaseWalletAuth.buildHeaders(null);  })
//                    .accept(MediaType.APPLICATION_JSON)
//                    .retrieve()
//                    .bodyToMono(Account[].class)
//                    .block(Duration.ofSeconds(5));
//
//
//        } catch (RuntimeException e) {
//            e.printStackTrace();
//            LOGGER.info("Error blyat");
//        }

        //LOGGER.info(monoAccounts.toString());

//        return Arrays.asList(monoAccounts);
        //return Collections.emptyList();
    }

}
