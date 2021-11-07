package com.mantasciutys.cryptotrader.scheduler;

import com.mantasciutys.cryptotrader.buy_timing.IOverallBuyDecision;
import com.mantasciutys.cryptotrader.exceptions.AccountDoesNotExistException;
import com.mantasciutys.cryptotrader.pojo.*;
import com.mantasciutys.cryptotrader.service.*;
import com.mantasciutys.cryptotrader.util.AccountHelper;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.math.BigDecimal;
import java.util.List;

@Configuration
@EnableScheduling
// This scheduler is executed very often
// It starts the process that determines whether an asset should be bought or not
public class Scheduler implements IScheduler {

    private static final Logger LOGGER = Logger.getLogger(Scheduler.class);

    private final IOverallBuyDecision buyDecision;
    private final AccountService accountService;
    private final ProductService productService;
    private final OrderService orderService;

    public Scheduler(IOverallBuyDecision buyDecision, AccountService accountService, ProductService productService, OrderService orderService) {
        this.buyDecision = buyDecision;
        this.accountService = accountService;
        this.productService = productService;
        this.orderService = orderService;
    }

    @Scheduled(fixedDelayString = "${trigger.buy.execution}")
    public void trigger() {
        LOGGER.info("Method for checking whether asset should be bought triggered");

        if (buyDecision.shouldBuy()) {
            // buy

        } else {
            // not buy
        }

        try {
            List<Account> accounts = accountService.getAllAccountsForProfile();
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
                    .post_only(true)
                    .build();

            LOGGER.info("Order to buy: " + order);

            Order boughtOrder = orderService.buyOrder(order);
            LOGGER.info("Bought order: " + boughtOrder);

        } catch (AccountDoesNotExistException e) {
            LOGGER.warn("Account has not been found!");
        } catch (RuntimeException e) {
            LOGGER.error("Some run time error occurred. Stack trace printed below");
            e.printStackTrace();
        }
    }
}
