package com.mantasciutys.cryptotrader.scheduler;

import com.mantasciutys.cryptotrader.asset_buyer.IAssetBuyer;
import com.mantasciutys.cryptotrader.buy_timing.IBuyDecision;
import com.mantasciutys.cryptotrader.pojo.Currency;
import com.mantasciutys.cryptotrader.service.CurrencyService;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import reactor.core.publisher.Mono;

import java.util.List;

@Configuration
@EnableScheduling
public class Scheduler {

    private final IAssetBuyer assetBuyer;
    private final CurrencyService currencyService;

    public Scheduler(IAssetBuyer assetBuyer, CurrencyService currencyService) {
        this.assetBuyer = assetBuyer;
        this.currencyService = currencyService;
    }

    @Scheduled(fixedDelayString = "${trigger.buy.execution}")
    public void triggerBuy() {
         if (assetBuyer.buyAsset()) {
             // buy
         } else {
             // not buy
         }
         // this is currently for testing only!!!
        // makes sure API is responding and working
         List<Currency> currencies = currencyService.getAllCurrencies();
         System.out.println(currencies);
    }
}
