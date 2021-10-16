package com.mantasciutys.cryptotrader.buy_timing;

import com.mantasciutys.cryptotrader.util.IDateUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Calendar;

@Component
public class BuyAtParticularTime implements ShouldBuyAsset {

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

        return currentDayOfWeek == dayToBuy && currentHour == hourToBuy;
    }
}
