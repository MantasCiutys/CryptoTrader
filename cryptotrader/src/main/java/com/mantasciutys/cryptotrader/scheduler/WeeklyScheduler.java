package com.mantasciutys.cryptotrader.scheduler;

import com.mantasciutys.cryptotrader.buy_timing.IOverallBuyDecision;
import com.mantasciutys.cryptotrader.exceptions.AccountDoesNotExistException;
import com.mantasciutys.cryptotrader.pojo.Account;
import com.mantasciutys.cryptotrader.pojo.Order;
import com.mantasciutys.cryptotrader.pojo.Product;
import com.mantasciutys.cryptotrader.util.AccountHelper;
import com.mantasciutys.cryptotrader.service.AccountService;
import com.mantasciutys.cryptotrader.service.OrderService;
import com.mantasciutys.cryptotrader.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.math.BigDecimal;
import java.util.List;

@Configuration
@EnableScheduling
/*
This scheduler is executed once a week, not related to any other conditions
 */
public class WeeklyScheduler implements IScheduler {

    private static final Logger LOGGER = LoggerFactory.getLogger(WeeklyScheduler.class);

    private final IOverallBuyDecision buyDecision;
    private final AccountService accountService;
    private final ProductService productService;
    private final OrderService orderService;
    private final BigDecimal amountToBuy;

    public WeeklyScheduler(IOverallBuyDecision buyDecision, AccountService accountService, ProductService productService, OrderService orderService,
                           @Value("${weekly.buy.amount.gbp}") BigDecimal amountToBuy) {
        this.buyDecision = buyDecision;
        this.accountService = accountService;
        this.productService = productService;
        this.orderService = orderService;
        this.amountToBuy = amountToBuy;
    }

    @Override
    @Scheduled(cron = "${trigger.buy.execution.weekly}")
    public void trigger() {
        LOGGER.info("Weekly buying action executing!");

        try {
            List<Account> accounts = accountService.getAllAccountsForProfile();
            Account mainAccount = AccountHelper.getAccountGivenCurrency("GBP", accounts);

            BigDecimal accountBalance = mainAccount.getBalance();
            Product product = productService.getProductById("BTC-GBP");

            if (accountBalance.compareTo(amountToBuy) > 0) {

                Order order = new Order.OrderBuilder("12345")
                        .type("market")
                        .side("buy")
                        .product_id(product.getId())
                        .funds(amountToBuy.toString())
                        .build();

                LOGGER.info("Order to buy: " + order);

                Order boughtOrder = orderService.buyOrder(order);
                LOGGER.info("Bought order: " + boughtOrder);
            } else {
                LOGGER.warn("Asset was not bought because of insufficient funds!");
            }

        } catch (AccountDoesNotExistException e) {
            LOGGER.warn("Account has not been found!");
        } catch (RuntimeException e) {
            LOGGER.error("Some error has been caught. Stack trace printed.");
            e.printStackTrace();
        }
    }
}
