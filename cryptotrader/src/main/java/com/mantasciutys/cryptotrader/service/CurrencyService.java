package com.mantasciutys.cryptotrader.service;

import com.mantasciutys.cryptotrader.buy_timing.BuyDecision;
import com.mantasciutys.cryptotrader.pojo.Currency;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
public class CurrencyService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CurrencyService.class);

    private final WebClient apiClient;
    private final String currenciesUrl;

    public CurrencyService(WebClient apiClient, @Value("${base.api.url.currencies}") String currenciesUrl) {
        this.apiClient = apiClient;
        this.currenciesUrl = currenciesUrl;
    }

    public List<Currency> getAllCurrencies() {
        LOGGER.info("Started retrieving all currencies from public API");

        Currency[] monoCurrencies = apiClient
                .get()
                .uri(currenciesUrl)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Currency[].class)
                .block();

        return Arrays.asList(monoCurrencies);
    }
}
