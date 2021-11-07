package com.mantasciutys.cryptotrader.service;

import com.mantasciutys.cryptotrader.authentication.CoinbaseWalletAuth;
import com.mantasciutys.cryptotrader.pojo.Order;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class OrderService {
    private static final Logger LOGGER = Logger.getLogger(OrderService.class);

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

        try {
            ResponseEntity<Order> responseEntity = restTemplate.exchange(ordersUri,
                    HttpMethod.POST,
                    httpEntity,
                    Order.class
            );
            return responseEntity.getBody();
        } catch (HttpClientErrorException e) {
            LOGGER.error("Error when buying an order");
            e.printStackTrace();
            throw e;
        }
    }
}
