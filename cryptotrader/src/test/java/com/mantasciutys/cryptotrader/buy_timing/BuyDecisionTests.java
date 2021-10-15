package com.mantasciutys.cryptotrader.buy_timing;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

public class BuyDecisionTests {
    private IBuyDecision buyDecision;
    private List<ShouldBuyAsset> shouldBuyAssetList;

    @BeforeEach
    public void setUp() {
        shouldBuyAssetList = new ArrayList<>();
        buyDecision = new BuyDecision(shouldBuyAssetList);
    }

    @Test
    public void testShouldBuyNoElements() {
        Assertions.assertEquals(false, buyDecision.shouldBuy());
    }

    @Test
    public void testShouldBuy1Element() {
        ShouldBuyAsset shouldBuyAsset = Mockito.mock(BuyAtParticularTime.class);
        when(shouldBuyAsset.shouldBuy()).thenReturn(true);

        shouldBuyAssetList.add(shouldBuyAsset);

        Assertions.assertEquals(true, buyDecision.shouldBuy());
    }

    @Test
    public void testShouldBuy2Elements() {
        ShouldBuyAsset shouldBuyAsset1 = Mockito.mock(BuyAtParticularTime.class);
        when(shouldBuyAsset1.shouldBuy()).thenReturn(false);
        ShouldBuyAsset shouldBuyAsset2 = Mockito.mock(BuyAtParticularTime.class);
        when(shouldBuyAsset2.shouldBuy()).thenReturn(true);

        shouldBuyAssetList.add(shouldBuyAsset1);
        shouldBuyAssetList.add(shouldBuyAsset2);

        Assertions.assertEquals(true, buyDecision.shouldBuy());
    }
}
