package com.mantasciutys.cryptotrader.service;

import com.mantasciutys.cryptotrader.authentication.CoinbaseWalletAuth;
import com.mantasciutys.cryptotrader.pojo.Account;
import com.mantasciutys.cryptotrader.pojo.Currency;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;

public class AccountService {
    private static final Logger LOGGER = LoggerFactory.getLogger(AccountService.class);

    private final WebClient apiClient;
    private final CoinbaseWalletAuth coinbaseWalletAuth;

    public AccountService(WebClient apiClient, CoinbaseWalletAuth coinbaseWalletAuth) {
        this.apiClient = apiClient;
        this.coinbaseWalletAuth = coinbaseWalletAuth;
    }

    public List<Account> getAllAccountsForProfile() {
        LOGGER.info("Started retrieving all accounts from the profile");

        /*
        Account[] monoAccounts = apiClient
                .get()
                .uri(currenciesUrl)
                .headers(coinbaseWalletAuth.buildHeaders())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Account[].class)
                .block();
*/
 //       return Arrays.asList(monoAccounts);
        return null;
    }

}
