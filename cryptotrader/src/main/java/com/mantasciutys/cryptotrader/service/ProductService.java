package com.mantasciutys.cryptotrader.service;

import com.mantasciutys.cryptotrader.authentication.CoinbaseWalletAuth;
import com.mantasciutys.cryptotrader.pojo.Currency;
import com.mantasciutys.cryptotrader.pojo.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class ProductService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductService.class);

    private final WebClient apiClient;
    private final RestTemplate restTemplate;
    private final CoinbaseWalletAuth coinbaseWalletAuth;
    private final String productIdUri;

    public ProductService(WebClient apiClient, RestTemplate restTemplate, CoinbaseWalletAuth coinbaseWalletAuth, @Value("${base.api.url.product.id}") String productIdUri) {
        this.apiClient = apiClient;
        this.restTemplate = restTemplate;
        this.coinbaseWalletAuth = coinbaseWalletAuth;
        this.productIdUri = productIdUri;
    }

    public Product getProductById(String productId) {
        LOGGER.info("Started retrieving a given product");

        Product product = apiClient
                .get()
                .uri(productIdUri + productId)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(Product.class)
                .block();

        return product;
    }

}
