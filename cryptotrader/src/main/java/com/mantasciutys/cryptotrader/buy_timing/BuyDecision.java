package com.mantasciutys.cryptotrader.buy_timing;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BuyDecision implements IBuyDecision {

    private List<ShouldBuyAsset> shouldBuyAssetList;

    public BuyDecision(List<ShouldBuyAsset> shouldBuyAssetList) {
        this.shouldBuyAssetList = shouldBuyAssetList;
    }

    @Override
    public boolean shouldBuy() {
        for (ShouldBuyAsset shouldBuyAsset : shouldBuyAssetList) {
            if (shouldBuyAsset.shouldBuy()) return true;
        }
        
        return false;
    }
}
