package com.mantasciutys.cryptotrader.buy_timing;

import com.mantasciutys.cryptotrader.asset_buyer.AssetBuyer;
import com.mantasciutys.cryptotrader.util.IDateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Calendar;

@Component
public class BuyAtParticularTime implements ShouldBuyAsset {

    private static final Logger LOGGER = LoggerFactory.getLogger(BuyAtParticularTime.class);

    private final int dayToBuy;
    private final int hourToBuy;
    private final IDateUtil dateUtil;

    public BuyAtParticularTime(@Value("${buy.asset.day}") int dayToBuy, @Value("${buy.asset.hour}") int hourToBuy,
                               IDateUtil dateUtil) {
        this.dayToBuy = dayToBuy;
        this.hourToBuy = hourToBuy;
        this.dateUtil = dateUtil;
    }

    @Override
    public boolean shouldBuy() {
        int currentDayOfWeek = dateUtil.getCurrentDayOfWeek();
        int currentHour = dateUtil.getCurrentHour();

        if (currentDayOfWeek == dayToBuy && currentHour == hourToBuy) {
            LOGGER.info("Asset will be bought. Current day of the week and hour match" +
                    " with a week and hour set in properties");
            return true;
        } else {
            LOGGER.info("Current day and hour do not match.");
            return false;
        }
    }
}
