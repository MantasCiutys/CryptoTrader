package com.mantasciutys.cryptotrader.buy_timing;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.List;

public class TestOverallBuyDecision {

    private IOverallBuyDecision overallBuyDecision;
    private List<ISingleBuyDecision> buyDecisionList;

    @Before
    public void setup() {
        buyDecisionList = new ArrayList<>();
        overallBuyDecision = new OverallBuyDecision(buyDecisionList);
    }

    @Test
    public void testEmptyBuyDecisionList() {
        Assertions.assertEquals(false, overallBuyDecision.shouldBuy());
    }
}
