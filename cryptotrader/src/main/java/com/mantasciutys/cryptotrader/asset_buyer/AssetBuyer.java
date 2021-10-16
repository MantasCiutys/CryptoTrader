package com.mantasciutys.cryptotrader.asset_buyer;

import com.mantasciutys.cryptotrader.buy_timing.IBuyDecision;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AssetBuyer implements IAssetBuyer {

    private final IBuyDecision buyDecision;
    private final double buyForCash;

    public AssetBuyer(IBuyDecision buyDecision, @Value("${buy.asset.amount.cash}") double buyForCash) {
        this.buyDecision = buyDecision;
        this.buyForCash = buyForCash;
    }

    @Override
    public boolean buyAsset() {
        if (buyDecision.shouldBuy()) {
            // logic/API to buy crypto
            return true;
        } else {
            // logic for not buying, probably just logging
            return false;
        }
    }
}
