package com.mantasciutys.cryptotrader.buyer;

import com.mantasciutys.cryptotrader.enums.OrderSide;
import com.mantasciutys.cryptotrader.enums.OrderType;
import com.mantasciutys.cryptotrader.exceptions.AccountDoesNotExistException;
import com.mantasciutys.cryptotrader.pojo.Account;
import com.mantasciutys.cryptotrader.pojo.Order;
import com.mantasciutys.cryptotrader.pojo.Product;
import com.mantasciutys.cryptotrader.scheduler.WeeklyScheduler;
import com.mantasciutys.cryptotrader.service.AccountService;
import com.mantasciutys.cryptotrader.service.OrderService;
import com.mantasciutys.cryptotrader.service.ProductService;
import com.mantasciutys.cryptotrader.util.AccountHelper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class OrderBuyer implements IBuyer {

    private static final Logger LOGGER = Logger.getLogger(OrderBuyer.class);

    private final AccountService accountService;
    private final OrderService orderService;

    public OrderBuyer(AccountService accountService, OrderService orderService) {
        this.accountService = accountService;
        this.orderService = orderService;
    }

    @Override
    public void buyOrder(Order order, BigDecimal amountToBuy) {
        try {
            List<Account> accounts = accountService.getAllAccountsForProfile();
            Account mainAccount = AccountHelper.getAccountGivenCurrency("GBP", accounts);

            BigDecimal accountBalance = mainAccount.getBalance();

            if (accountBalance.compareTo(amountToBuy) > 0) {
                Order boughtOrder = orderService.buyOrder(order);
                LOGGER.info("Bought order: " + boughtOrder);
            } else {
                LOGGER.warn("Asset was not bought because of insufficient funds!");
            }

        } catch (AccountDoesNotExistException e) {
            LOGGER.warn("Account has not been found!");
        }
    }
}
