package com.mantasciutys.cryptotrader.service;

import com.mantasciutys.cryptotrader.pojo.Currency;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

@Service
public class CurrencyService {
    private final WebClient apiClient;
    private final String currenciesUrl;

    public CurrencyService(WebClient apiClient, @Value("${base.api.url.currencies}") String currenciesUrl) {
        this.apiClient = apiClient;
        this.currenciesUrl = currenciesUrl;
    }

    public Currency getCurrency(String currencyId) {
        return apiClient
                .get()
                .uri(currenciesUrl + "/" + currencyId)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Currency.class)
                .block();
    }

    public List<Currency> getAllCurrencies() {
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
