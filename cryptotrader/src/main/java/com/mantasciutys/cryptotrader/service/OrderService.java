package com.mantasciutys.cryptotrader.service;

import com.mantasciutys.cryptotrader.authentication.CoinbaseWalletAuth;
import com.mantasciutys.cryptotrader.pojo.Account;
import com.mantasciutys.cryptotrader.pojo.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class OrderService {
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderService.class);

    private final WebClient apiClient;
    private final CoinbaseWalletAuth coinbaseWalletAuth;
    private final String ordersUri;

    public OrderService(WebClient apiClient, CoinbaseWalletAuth coinbaseWalletAuth, @Value("${base.api.url.order}") String ordersUri) {
        this.apiClient = apiClient;
        this.coinbaseWalletAuth = coinbaseWalletAuth;
        this.ordersUri = ordersUri;
    }

    public boolean buyOrder(Order order) {
        LOGGER.info("Started retrieving all accounts from the profile");

        apiClient
                .post()
                .uri(ordersUri)
                .body(Mono.just(order), Order.class)
                .headers( httpHeaders -> {
                            coinbaseWalletAuth.buildHeaders(HttpMethod.GET.name(), ordersUri);
                        }
                )
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Order.class);

        return true;
    }
}
