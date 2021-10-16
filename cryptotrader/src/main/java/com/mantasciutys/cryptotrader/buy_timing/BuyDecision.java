package com.mantasciutys.cryptotrader.buy_timing;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BuyDecision implements IBuyDecision {

    private static final Logger LOGGER = LoggerFactory.getLogger(BuyDecision.class);

    private final List<ShouldBuyAsset> shouldBuyAssetList;

    public BuyDecision(List<ShouldBuyAsset> shouldBuyAssetList) {
        this.shouldBuyAssetList = shouldBuyAssetList;
    }

    @Override
    public boolean shouldBuy() {
        for (ShouldBuyAsset shouldBuyAsset : shouldBuyAssetList) {
            if (shouldBuyAsset.shouldBuy()) {
                LOGGER.info("Some condition for buying is true. Asset will be bought.");
                return true;
            }
        }
        LOGGER.info("No condition for buying is true. Asset will not be bought.");
        return false;
    }
}
