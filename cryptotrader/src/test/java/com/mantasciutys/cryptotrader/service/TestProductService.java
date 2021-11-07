package com.mantasciutys.cryptotrader.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mantasciutys.cryptotrader.authentication.CoinbaseWalletAuth;
import com.mantasciutys.cryptotrader.pojo.Product;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class TestProductService {

    @Autowired
    private ProductService productService;

    @Autowired
    private CoinbaseWalletAuth coinbaseWalletAuth;

    @Autowired
    private RestTemplate restTemplate;

    private MockRestServiceServer mockServer;
    private ObjectMapper mapper = new ObjectMapper();

    @Before
    public void init() {
        mockServer = MockRestServiceServer.bindTo(restTemplate).build();
    }

    @Test
    public void testValidProduct() {
        String productId = "BTC-GBP";

        Product product = new Product();
        product.setId(productId);

        try {
            mockServer.expect(ExpectedCount.once(),
                            requestTo(new URI("https://api-public.sandbox.exchange.coinbase.com/products/" + productId)))
                    .andExpect(method(HttpMethod.GET))
                    .andRespond(withStatus(HttpStatus.OK)
                            .contentType(MediaType.APPLICATION_JSON)
                            .body(mapper.writeValueAsString(product)));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        Product retrievedProduct = productService.getProductById(productId);
        Assertions.assertEquals(productId, retrievedProduct.getId());
    }

    @Test
    public void testInvalidProduct() {
        String productId = "invalid";

        Product product = new Product();
        product.setId(productId);

        try {
            mockServer.expect(ExpectedCount.once(),
                            requestTo(new URI("https://api-public.sandbox.exchange.coinbase.com/products/" + productId)))
                    .andExpect(method(HttpMethod.GET))
                    .andRespond(withStatus(HttpStatus.NOT_FOUND)
                            .contentType(MediaType.APPLICATION_JSON)
                            .body(mapper.writeValueAsString(product)));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        Assertions.assertThrows(HttpClientErrorException.class, () -> productService.getProductById(productId));
    }
}
