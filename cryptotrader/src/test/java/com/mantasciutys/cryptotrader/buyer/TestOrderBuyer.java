package com.mantasciutys.cryptotrader.buyer;

import com.mantasciutys.cryptotrader.enums.OrderSide;
import com.mantasciutys.cryptotrader.enums.OrderType;
import com.mantasciutys.cryptotrader.pojo.Account;
import com.mantasciutys.cryptotrader.pojo.Order;
import com.mantasciutys.cryptotrader.service.AccountService;
import com.mantasciutys.cryptotrader.service.OrderService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class TestOrderBuyer {

    @Mock
    private AccountService accountService;

    @Mock
    private OrderService orderService;

    @InjectMocks
    private OrderBuyer orderBuyer;

    @Before
    public void init() {

    }

    @Test
    public void testBuyValidOrderSufficientFunds() {
        BigDecimal amountToBuy = new BigDecimal("10");

        Order order = new Order.OrderBuilder("12345")
                .type(OrderType.MARKET.getOrderType())
                .side(OrderSide.BUY.getOrderSide())
                .product_id("BTC-GBP")
                .funds("10")
                .post_only(true)
                .build();

        Account account = new Account();
        account.setId("123");
        account.setBalance(new BigDecimal("100"));
        account.setCurrency("GBP");

        List<Account> accountList = new ArrayList<>();
        accountList.add(account);

        when(accountService.getAllAccountsForProfile()).thenReturn(accountList);

        orderBuyer.buyOrder(order, amountToBuy);

        verify(orderService, times(1)).buyOrder(order);
    }

    @Test
    public void testBuyValidOrderInsufficientFunds() {
        BigDecimal amountToBuy = new BigDecimal("10");

        Order order = new Order.OrderBuilder("12345")
                .type(OrderType.MARKET.getOrderType())
                .side(OrderSide.BUY.getOrderSide())
                .product_id("BTC-GBP")
                .funds("10")
                .post_only(true)
                .build();

        Account account = new Account();
        account.setId("123");
        account.setBalance(new BigDecimal("5"));
        account.setCurrency("GBP");

        List<Account> accountList = new ArrayList<>();
        accountList.add(account);

        when(accountService.getAllAccountsForProfile()).thenReturn(accountList);

        orderBuyer.buyOrder(order, amountToBuy);

        verify(orderService, times(0)).buyOrder(order);
    }

    @Test
    public void testBuyValidOrderInvalidAccCurrency() {
        BigDecimal amountToBuy = new BigDecimal("10");

        Order order = new Order.OrderBuilder("12345")
                .type(OrderType.MARKET.getOrderType())
                .side(OrderSide.BUY.getOrderSide())
                .product_id("BTC-GBP")
                .funds("10")
                .post_only(true)
                .build();

        Account account = new Account();
        account.setId("123");
        account.setBalance(new BigDecimal("100"));
        account.setCurrency("USD");

        List<Account> accountList = new ArrayList<>();
        accountList.add(account);

        when(accountService.getAllAccountsForProfile()).thenReturn(accountList);

        orderBuyer.buyOrder(order, amountToBuy);

        verify(orderService, times(0)).buyOrder(order);
    }
}
