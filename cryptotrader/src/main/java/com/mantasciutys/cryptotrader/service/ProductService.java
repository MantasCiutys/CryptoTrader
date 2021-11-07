package com.mantasciutys.cryptotrader.service;

import com.mantasciutys.cryptotrader.authentication.CoinbaseWalletAuth;
import com.mantasciutys.cryptotrader.pojo.Product;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Service
public class ProductService {
    private static final Logger LOGGER = Logger.getLogger(ProductService.class);

    private final RestTemplate restTemplate;
    private final CoinbaseWalletAuth coinbaseWalletAuth;
    private final String productIdUri;

    public ProductService(RestTemplate restTemplate, CoinbaseWalletAuth coinbaseWalletAuth, @Value("${base.api.url.product.id}") String productIdUri) {
        this.restTemplate = restTemplate;
        this.coinbaseWalletAuth = coinbaseWalletAuth;
        this.productIdUri = productIdUri;
    }

    public Product getProductById(String productId) {
        LOGGER.info("Started retrieving a given product");

        HttpHeaders headers = coinbaseWalletAuth.buildHeaders(HttpMethod.GET.name(), productIdUri, null);
        HttpEntity<String> httpEntity = new HttpEntity<>(headers);

        try {
            ResponseEntity<Product> responseEntity = restTemplate.exchange(productIdUri + productId,
                    HttpMethod.GET,
                    httpEntity,
                    Product.class
            );
            return responseEntity.getBody();
        } catch (HttpClientErrorException e) {
            LOGGER.error("Could not retrieve the product");
            e.printStackTrace();
            throw e;
        }
    }

}
