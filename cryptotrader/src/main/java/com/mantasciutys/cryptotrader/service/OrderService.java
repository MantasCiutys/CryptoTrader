package com.mantasciutys.cryptotrader.service;

import com.mantasciutys.cryptotrader.authentication.CoinbaseWalletAuth;
import com.mantasciutys.cryptotrader.pojo.Account;
import com.mantasciutys.cryptotrader.pojo.Fill;
import com.mantasciutys.cryptotrader.pojo.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Arrays;
import java.util.List;

@Service
public class OrderService {
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderService.class);

    private final WebClient apiClient;
    private final CoinbaseWalletAuth coinbaseWalletAuth;
    private final RestTemplate restTemplate;
    private final String ordersUri;
    private final String fillsUri;

    public OrderService(WebClient apiClient, CoinbaseWalletAuth coinbaseWalletAuth, @Value("${base.api.url.order}") String ordersUri, RestTemplate restTemplate,
                        @Value("${base.api.url.fills}") String fillsUri) {
        this.apiClient = apiClient;
        this.coinbaseWalletAuth = coinbaseWalletAuth;
        this.ordersUri = ordersUri;
        this.restTemplate = restTemplate;
        this.fillsUri = fillsUri;
    }

    public Order buyOrder(Order order) {
        LOGGER.info("Sending order for buying...");

        HttpHeaders headers = coinbaseWalletAuth.buildHeaders(HttpMethod.POST.name(), ordersUri, order);
        HttpEntity<String> httpEntity = new HttpEntity<>(coinbaseWalletAuth.convertToJson(order), headers);

        ResponseEntity<Order> responseEntity = restTemplate.exchange(ordersUri,
                HttpMethod.POST,
                httpEntity,
                Order.class
        );

        return responseEntity.getBody();
    }

    public List<Fill> getAllFills() {
        LOGGER.info("Retrieving all fills...");

        String fullFillsUri = fillsUri;
        fullFillsUri += "?product_id=BTC-GBP&limit=100";

        HttpHeaders headers = coinbaseWalletAuth.buildHeaders(HttpMethod.GET.name(), fullFillsUri, null);
        HttpEntity<String> httpEntity = new HttpEntity<>(headers);

        ResponseEntity<Fill[]> responseEntity = restTemplate.exchange(fullFillsUri,
                HttpMethod.GET,
                httpEntity,
                Fill[].class
        );

        return Arrays.asList(responseEntity.getBody());
    }
}
