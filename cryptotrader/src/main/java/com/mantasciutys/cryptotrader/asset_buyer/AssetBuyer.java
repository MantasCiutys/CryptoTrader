package com.mantasciutys.cryptotrader.asset_buyer;

import com.mantasciutys.cryptotrader.buy_timing.IBuyDecision;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AssetBuyer implements IAssetBuyer {

    private static final Logger LOGGER = LoggerFactory.getLogger(AssetBuyer.class);

    private final IBuyDecision buyDecision;
    private final double buyForCash;

    public AssetBuyer(IBuyDecision buyDecision, @Value("${buy.asset.amount.cash}") double buyForCash) {
        this.buyDecision = buyDecision;
        this.buyForCash = buyForCash;
    }

    @Override
    public boolean buyAsset() {
        if (buyDecision.shouldBuy()) {
            LOGGER.info("Some asset will be bought.");
            // logic/API to buy crypto
            return true;
        } else {
            LOGGER.info("No asset will be bought right now.");
            return false;
        }
    }
}
