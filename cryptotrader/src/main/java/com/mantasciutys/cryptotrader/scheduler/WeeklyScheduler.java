package com.mantasciutys.cryptotrader.scheduler;

import com.mantasciutys.cryptotrader.buy_timing.IOverallBuyDecision;
import com.mantasciutys.cryptotrader.buyer.IBuyer;
import com.mantasciutys.cryptotrader.enums.OrderSide;
import com.mantasciutys.cryptotrader.enums.OrderType;
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

    private final ProductService productService;
    private final IBuyer orderBuyer;
    private final BigDecimal amountToBuy;

    public WeeklyScheduler(ProductService productService, IBuyer orderBuyer,
                           @Value("${weekly.buy.amount.gbp}") BigDecimal amountToBuy) {
        this.productService = productService;
        this.orderBuyer = orderBuyer;
        this.amountToBuy = amountToBuy;
    }

    @Override
    @Scheduled(cron = "${trigger.buy.execution.weekly}")
    public void trigger() {
        LOGGER.info("Weekly buying action executing!");

        Product product = productService.getProductById("BTC-GBP");

        Order order = new Order.OrderBuilder("12345")
                .type(OrderType.MARKET.getOrderType())
                .side(OrderSide.BUY.getOrderSide())
                .product_id(product.getId())
                .funds(amountToBuy.toString())
                .build();

        LOGGER.info("Order to buy: " + order);

        orderBuyer.buyOrder(order, amountToBuy);
    }
}
