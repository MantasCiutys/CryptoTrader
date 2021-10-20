package com.mantasciutys.cryptotrader.scheduler;

import com.mantasciutys.cryptotrader.asset_buyer.IAssetBuyer;
import com.mantasciutys.cryptotrader.exceptions.AccountDoesNotExistException;
import com.mantasciutys.cryptotrader.pojo.Account;
import com.mantasciutys.cryptotrader.pojo.Currency;
import com.mantasciutys.cryptotrader.pojo.Order;
import com.mantasciutys.cryptotrader.pojo.Product;
import com.mantasciutys.cryptotrader.service.AccountHelper;
import com.mantasciutys.cryptotrader.service.AccountService;
import com.mantasciutys.cryptotrader.service.CurrencyService;
import com.mantasciutys.cryptotrader.service.ProductService;
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

    public Scheduler(IAssetBuyer assetBuyer, CurrencyService currencyService, AccountService accountService, ProductService productService) {
        this.assetBuyer = assetBuyer;
        this.currencyService = currencyService;
        this.accountService = accountService;
        this.productService = productService;
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

        } catch (AccountDoesNotExistException e) {
            LOGGER.warn("Account has not been found!");
        }

        if (assetBuyer.buyAsset()) {
             // buy

        } else {
             // not buy
        }

    }
}
