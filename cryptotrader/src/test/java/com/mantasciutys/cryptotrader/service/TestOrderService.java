package com.mantasciutys.cryptotrader.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mantasciutys.cryptotrader.authentication.CoinbaseWalletAuth;
import com.mantasciutys.cryptotrader.enums.OrderSide;
import com.mantasciutys.cryptotrader.enums.OrderType;
import com.mantasciutys.cryptotrader.pojo.Order;
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
public class TestOrderService {

    @Autowired
    private OrderService orderService;

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
    public void testValidOrder() {
        Order order = new Order.OrderBuilder("12345")
                .type(OrderType.MARKET.getOrderType())
                .side(OrderSide.BUY.getOrderSide())
                .product_id("BTC-GBP")
                .funds("10")
                .post_only(true)
                .build();

        try {
            mockServer.expect(ExpectedCount.once(),
                            requestTo(new URI("https://api-public.sandbox.exchange.coinbase.com/orders")))
                    .andExpect(method(HttpMethod.POST))
                    .andRespond(withStatus(HttpStatus.OK)
                            .contentType(MediaType.APPLICATION_JSON)
                            .body(mapper.writeValueAsString(order)));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        Order returnedOrder = orderService.buyOrder(order);

        Assertions.assertEquals(OrderType.MARKET.getOrderType(), returnedOrder.getType());
        Assertions.assertEquals(OrderSide.BUY.getOrderSide(), returnedOrder.getSide());
    }

    @Test
    public void testInvalidOrderUnauthorized() {
        // created an order with an invalid type
        Order order = new Order.OrderBuilder("12345")
                .type("invalidType")
                .side(OrderSide.BUY.getOrderSide())
                .product_id("BTC-GBP")
                .funds("10")
                .post_only(true)
                .build();

        try {
            mockServer.expect(ExpectedCount.once(),
                            requestTo(new URI("https://api-public.sandbox.exchange.coinbase.com/orders")))
                    .andExpect(method(HttpMethod.POST))
                    .andRespond(withStatus(HttpStatus.UNAUTHORIZED)
                            .contentType(MediaType.APPLICATION_JSON)
                            .body(mapper.writeValueAsString(order)));
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        Assertions.assertThrows(HttpClientErrorException.class, () ->
                orderService.buyOrder(order));
    }
}
