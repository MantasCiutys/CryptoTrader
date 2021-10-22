package com.mantasciutys.cryptotrader.scheduler;

import com.mantasciutys.cryptotrader.asset_buyer.IAssetBuyer;
import com.mantasciutys.cryptotrader.exceptions.AccountDoesNotExistException;
import com.mantasciutys.cryptotrader.pojo.*;
import com.mantasciutys.cryptotrader.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.math.BigDecimal;
import java.util.List;

@Configuration
@EnableScheduling
public class Scheduler {

    private static final Logger LOGGER = LoggerFactory.getLogger(Scheduler.class);

    private final IAssetBuyer assetBuyer;
    private final CurrencyService currencyService;
    private final AccountService accountService;
    private final ProductService productService;
    private final OrderService orderService;

    public Scheduler(IAssetBuyer assetBuyer, CurrencyService currencyService, AccountService accountService, ProductService productService, OrderService orderService) {
        this.assetBuyer = assetBuyer;
        this.currencyService = currencyService;
        this.accountService = accountService;
        this.productService = productService;
        this.orderService = orderService;
    }

    @Scheduled(fixedDelayString = "${trigger.buy.execution}")
    public void triggerBuy() {
        LOGGER.info("Method for buying triggered");

        List<Account> accounts = accountService.getAllAccountsForProfile();
        LOGGER.info(String.valueOf(accounts));

        try {
            Account mainAccount = AccountHelper.getAccountGivenCurrency("GBP", accounts);
            LOGGER.info("Main account: " + mainAccount);
            BigDecimal accountBalance = mainAccount.getBalance();
            LOGGER.info("Account balance is: " + accountBalance);
            Product product = productService.getProductById("BTC-GBP");
            LOGGER.info("Product: " + product);

            BigDecimal amountToBuy = new BigDecimal("10");

            Order order = new Order.OrderBuilder("12345")
                    .type("market")
                    .side("buy")
                    .product_id(product.getId())
                    .funds(amountToBuy.toString())
                    .build();

            LOGGER.info("Order to buy: " + order);

            Order boughtOrder = orderService.buyOrder(order);
            LOGGER.info("Bought order: " + boughtOrder);

            // for testing only
            Thread.sleep(3000);

            List<Fill> fills = orderService.getAllFills();
            LOGGER.info("Number of fills : " + fills.size());


        } catch (AccountDoesNotExistException e) {
            LOGGER.warn("Account has not been found!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (assetBuyer.buyAsset()) {
             // buy

        } else {
             // not buy
        }

    }
}
